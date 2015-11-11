package fm.qingting.qtradio.logger;

import android.util.Log;
import com.lmax.disruptor.EventHandler;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class LogEventHandler
  implements EventHandler<ValueEvent>
{
  private static final String _content_ = "content";
  private static final String _log_thread_id_ = "_log_thread_id_12432543jndslnfdsj";
  private static final String _type_ = "type";
  private static boolean isStart = false;

  public void onEvent(ValueEvent paramValueEvent, long paramLong, boolean paramBoolean)
    throws Exception
  {
    if (!isStart)
    {
      isStart = true;
      System.setProperty("_log_thread_id_12432543jndslnfdsj", String.valueOf(Thread.currentThread().getId()));
    }
    String str = paramValueEvent.getType();
    Object localObject2 = paramValueEvent.getValue();
    paramValueEvent = new DefaultHttpClient();
    try
    {
      Object localObject3 = new HttpPost(ConfigHelper.getPostURL());
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(new BasicNameValuePair("type", str));
      localArrayList.add(new BasicNameValuePair("content", (String)localObject2));
      ((HttpPost)localObject3).setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
      localObject2 = paramValueEvent.execute((HttpUriRequest)localObject3);
      int i = ((HttpResponse)localObject2).getStatusLine().getStatusCode();
      localObject3 = ((HttpResponse)localObject2).getStatusLine().getReasonPhrase();
      Log.w("QTLogger", "type:" + str + ",Respnse: " + i + " " + (String)localObject3);
      EntityUtils.toString(((HttpResponse)localObject2).getEntity(), "UTF-8").substring(1);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
    }
    finally
    {
      paramValueEvent.getConnectionManager().shutdown();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.logger.LogEventHandler
 * JD-Core Version:    0.6.2
 */