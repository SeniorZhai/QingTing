package fm.qingting.qtradio.log;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import fm.qingting.framework.utils.MobileState;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.thread.QThread;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class LogModule extends QThread
{
  private static final int CheckAndSendLog = 0;
  private static final String _content_ = "content";
  private static LogModule _ins;
  private static final String _type_ = "type";
  private Context _context;
  HttpClient httpclient = null;
  HttpPost httppost = null;
  private LogDB logDB = null;

  private LogModule()
  {
    super("LogModule");
    setStartMsgNum(0);
    this.httppost = new HttpPost("http://logger.qingting.fm/logger.php");
  }

  public static LogModule getInstance()
  {
    if (_ins == null)
      _ins = new LogModule();
    return _ins;
  }

  private void initClient()
  {
    if (this.httpclient == null)
    {
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 5000);
      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 10000);
      this.httpclient = new DefaultHttpClient(localBasicHttpParams);
    }
  }

  private boolean send(LogBean paramLogBean)
  {
    if (this.httppost == null)
      log("[http client not initialized]");
    while (true)
    {
      return false;
      String str = paramLogBean.getType();
      paramLogBean = paramLogBean.getContent();
      try
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(new BasicNameValuePair("type", str));
        localArrayList.add(new BasicNameValuePair("content", paramLogBean));
        this.httppost.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
        initClient();
        int i = this.httpclient.execute(this.httppost).getStatusLine().getStatusCode();
        log("status code:" + i + "," + str);
        if (i == 200)
          return true;
      }
      catch (IOException localIOException)
      {
        log(localIOException + "," + localIOException.getLocalizedMessage() + "," + localIOException.getCause() + "," + str + "," + paramLogBean);
      }
    }
    return false;
  }

  protected void handleMsg(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      log("[error]undefined msg:" + paramMessage.what);
      this.handler.sendEmptyMessageDelayed(0, LogConfig.LongerPollInterval);
      return;
    case 0:
    }
    if (this.logDB == null)
    {
      log("[error]logDB==null");
      this.handler.sendEmptyMessageDelayed(0, LogConfig.LongerPollInterval);
      return;
    }
    switch (MobileState.getNetWorkType(this._context))
    {
    case 0:
    default:
      return;
    case -1:
      log("no network. try in " + LogConfig.LongerPollInterval / 1000L + "s.");
      this.handler.sendEmptyMessageDelayed(0, LogConfig.LongerPollInterval);
      return;
    case 1:
    case 2:
    case 3:
    }
    Object localObject = this.logDB.fetch(LogConfig.MaxItemNumToSendPerTime);
    log("fetched:" + ((List)localObject).size() + " logs");
    if (((List)localObject).size() <= 0)
    {
      log("in " + LogConfig.LongerPollInterval / 1000L + "s.");
      this.handler.sendEmptyMessageDelayed(0, LogConfig.LongerPollInterval);
      return;
    }
    paramMessage = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      LogBean localLogBean = (LogBean)((Iterator)localObject).next();
      if (send(localLogBean))
        paramMessage.add(Long.valueOf(localLogBean.getId()));
    }
    log("sent:" + paramMessage.size() + " logs");
    int i = 0;
    while ((paramMessage != null) && (paramMessage.size() > 0))
    {
      localObject = this.logDB.remove(paramMessage);
      if ((localObject != null) && (((List)localObject).size() > 0))
      {
        int j = i + 1;
        i = j;
        paramMessage = (Message)localObject;
        if (j >= 5)
        {
          log("[delete logs faile]");
          paramMessage = ((List)localObject).iterator();
          while (paramMessage.hasNext())
            log(String.valueOf((Long)paramMessage.next()));
          send("RemoveLogFail", QTLogger.getInstance().buildCommonLog());
        }
      }
    }
    log("in " + LogConfig.NormalPollInterval / 1000L + "s.");
    this.handler.sendEmptyMessageDelayed(0, LogConfig.NormalPollInterval);
  }

  public void init(Context paramContext)
  {
    this._context = paramContext;
    if (this.logDB == null)
    {
      this.logDB = new LogDB(this._context);
      log("logModule's LogDB initilized");
    }
  }

  public void send(String paramString1, String paramString2)
  {
    if (this.logDB == null)
    {
      Log.e("LogModule", "LogModule's logDB is not initialized. call start(Context)");
      return;
    }
    String str = paramString2;
    if (!paramString2.endsWith("\n"))
      str = paramString2 + "\n";
    this.logDB.store(new LogBean(paramString1, str));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.log.LogModule
 * JD-Core Version:    0.6.2
 */