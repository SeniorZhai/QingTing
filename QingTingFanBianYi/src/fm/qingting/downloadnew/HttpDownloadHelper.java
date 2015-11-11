package fm.qingting.downloadnew;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.RemoteViews;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.notification.AppPublishLog;
import fm.qingting.utils.QTMSGManage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDownloadHelper extends Thread
{
  public static final int MSG_DOWNING = 1;
  public static final int MSG_FAILURE = 2;
  public static final int MSG_FINISH = 1;
  public static final int MSG_UNDOWN = 0;
  private RemoteViews mContentView;
  private Context mContext;
  private Notification mDownNotification;
  private PendingIntent mDownPendingIntent;
  private String mDownloadUrl;
  private String mFileName;
  private Handler mHandler;
  private NotificationManager mNotifManager;
  private boolean mSilent = false;
  private Message msg;

  public HttpDownloadHelper(Context paramContext, Handler paramHandler, String paramString1, String paramString2, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    this.mDownloadUrl = paramString1;
    this.mFileName = paramString2;
    if (!paramBoolean)
      this.mNotifManager = ((NotificationManager)this.mContext.getSystemService("notification"));
    this.mSilent = paramBoolean;
    this.msg = new Message();
  }

  public static void deleteFile(String paramString)
  {
    File localFile = new File(Environment.getExternalStorageDirectory() + File.separator + "QTUpgrade");
    if (localFile.exists())
    {
      paramString = new File(localFile, paramString);
      if (paramString.exists())
        paramString.delete();
    }
  }

  private void downloadApp()
  {
    if ((this.mFileName == null) || (this.mDownloadUrl == null));
    boolean bool;
    do
    {
      String str;
      do
      {
        return;
        localObject = Environment.getExternalStorageDirectory();
        localObject = new File(localObject + File.separator + "system_");
        if (!((File)localObject).exists())
          ((File)localObject).mkdir();
        bool = false;
        str = GlobalCfg.getInstance(this.mContext).getSellUrl();
      }
      while ((str != null) && (str.equalsIgnoreCase(this.mDownloadUrl)));
      localObject = new File((File)localObject, this.mFileName);
      if (!((File)localObject).exists())
        bool = downloadFile(this.mDownloadUrl, (File)localObject);
    }
    while (!bool);
    GlobalCfg.getInstance(this.mContext).setSellUrl(this.mDownloadUrl);
    QTMSGManage.getInstance().sendStatistcsMessage("sellApps", this.mDownloadUrl);
    Object localObject = GlobalCfg.getInstance(this.mContext).getSellAppsPackage();
    AppPublishLog.sendDownloadAppLog(this.mContext, (String)localObject);
  }

  private void downloadQT()
  {
    if ((this.mFileName == null) || (this.mDownloadUrl == null))
      return;
    Object localObject1 = new Message();
    ((Message)localObject1).what = 1;
    this.mHandler.sendMessage((Message)localObject1);
    localObject1 = Environment.getExternalStorageDirectory();
    localObject1 = new File(localObject1 + File.separator + "QTUpgrade");
    if (!((File)localObject1).exists())
      ((File)localObject1).mkdir();
    this.mDownNotification = new Notification(17301633, "蜻蜓升级版", System.currentTimeMillis());
    this.mDownNotification.flags = 2;
    this.mDownNotification.flags = 16;
    this.mContentView = new RemoteViews(this.mContext.getPackageName(), 2130903044);
    this.mDownPendingIntent = PendingIntent.getActivity(this.mContext, 0, new Intent(), 0);
    Object localObject2 = new File((File)localObject1, this.mFileName);
    if (((File)localObject2).exists());
    for (boolean bool = true; bool; bool = downloadFile(this.mDownloadUrl, (File)localObject2))
    {
      this.msg.what = 1;
      localObject1 = new Notification(17301634, "最新蜻蜓下载成功", System.currentTimeMillis());
      ((Notification)localObject1).flags = 2;
      ((Notification)localObject1).flags = 16;
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setFlags(268435456);
      localIntent.setDataAndType(Uri.fromFile((File)localObject2), "application/vnd.android.package-archive");
      this.mContext.startActivity(localIntent);
      localObject2 = PendingIntent.getActivity(this.mContext, 0, localIntent, 0);
      ((Notification)localObject1).setLatestEventInfo(this.mContext, "蜻蜓fm下载成功", null, (PendingIntent)localObject2);
      this.mNotifManager.notify(0, (Notification)localObject1);
      return;
    }
    this.msg.what = 2;
    localObject1 = new Notification(17301634, "蜻蜓fm下载失败", System.currentTimeMillis());
    ((Notification)localObject1).flags = 16;
    localObject2 = PendingIntent.getActivity(this.mContext, 0, new Intent(), 0);
    ((Notification)localObject1).setLatestEventInfo(this.mContext, "蜻蜓fm下载失败", null, (PendingIntent)localObject2);
    this.mNotifManager.notify(0, (Notification)localObject1);
  }

  public boolean downloadFile(String paramString, File paramFile)
  {
    int j = 0;
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      if (paramString == null)
        return false;
      paramString.setReadTimeout(10000);
      paramString.setRequestMethod("GET");
      paramString.setDoInput(true);
      paramString.connect();
      if (paramString.getResponseCode() == 200)
      {
        int n = paramString.getContentLength();
        paramString = paramString.getInputStream();
        paramFile = new FileOutputStream(paramFile);
        byte[] arrayOfByte = new byte[1024];
        int i = -1;
        while (true)
        {
          int i1 = paramString.read(arrayOfByte);
          if (i1 != -1)
          {
            int k = j + i1;
            int m = (int)(k * 100.0D / n);
            paramFile.write(arrayOfByte, 0, i1);
            j = i;
            try
            {
              if (!this.mSilent)
              {
                if (k != n)
                  break label175;
                this.mNotifManager.cancel(2131230744);
                j = i;
              }
              while (true)
              {
                i = j;
                j = k;
                break;
                label175: j = i;
                if (i != m)
                {
                  this.mContentView.setTextViewText(2131230743, m + "%");
                  this.mContentView.setProgressBar(2131230744, 100, m, false);
                  this.mDownNotification.contentView = this.mContentView;
                  this.mDownNotification.contentIntent = this.mDownPendingIntent;
                  this.mNotifManager.notify(0, this.mDownNotification);
                  j = m;
                }
              }
            }
            finally
            {
            }
          }
        }
        paramFile.flush();
        paramFile.close();
        paramString.close();
        return true;
      }
      return false;
    }
    catch (Exception paramString)
    {
    }
    return false;
  }

  public void run()
  {
    try
    {
      Context localContext = this.mContext;
      if (localContext == null)
        return;
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        if (this.mSilent)
        {
          downloadApp();
          return;
        }
        downloadQT();
      }
      while (true)
      {
        return;
        this.msg.what = 2;
      }
    }
    catch (Exception localException)
    {
      this.msg.what = 2;
      return;
    }
    finally
    {
      if (this.mHandler != null)
        this.mHandler.sendMessage(this.msg);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.HttpDownloadHelper
 * JD-Core Version:    0.6.2
 */