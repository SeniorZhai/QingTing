package com.sina.weibo.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Pair;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.DownloadService;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.NotificationHelper;
import com.sina.weibo.sdk.utils.Utility;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

public class WbAppInstallActivator
{
  private static final String TAG = WbAppInstallActivator.class.getName();
  public static final String WB_APK_FILE_DIR = Environment.getExternalStorageDirectory() + "/Android/org_share_data/";
  private static WbAppInstallActivator mInstance;
  private boolean isFree = true;
  private String mAppkey;
  private Context mContext;
  private CountDownLatch mCountDownlatch;
  private NotificationInfo mNotificationInfo;

  private WbAppInstallActivator(Context paramContext, String paramString)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mAppkey = paramString;
  }

  private static boolean checkApkSign(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null);
    do
    {
      return false;
      if (paramPackageInfo.signatures != null)
        break;
    }
    while (Build.VERSION.SDK_INT >= 11);
    return true;
    String str = "";
    int i = 0;
    while (true)
    {
      if (i >= paramPackageInfo.signatures.length)
        return "18da2bf10352443a00a5e046d9fca6bd".equals(str);
      byte[] arrayOfByte = paramPackageInfo.signatures[i].toByteArray();
      if (arrayOfByte != null)
        str = MD5.hexdigest(arrayOfByte);
      i += 1;
    }
  }

  private static boolean checkPackageName(PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo == null);
    do
    {
      return false;
      paramPackageInfo = paramPackageInfo.packageName;
    }
    while ((!"com.sina.weibo".equals(paramPackageInfo)) && (!"com.sina.weibog3".equals(paramPackageInfo)));
    return true;
  }

  public static WbAppInstallActivator getInstance(Context paramContext, String paramString)
  {
    try
    {
      if (mInstance == null)
        mInstance = new WbAppInstallActivator(paramContext, paramString);
      paramContext = mInstance;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  private static boolean isWeiboApk(PackageInfo paramPackageInfo)
  {
    return (checkPackageName(paramPackageInfo)) && (checkApkSign(paramPackageInfo));
  }

  private void loadNotificationInfo()
  {
    String str = this.mAppkey;
    requestNotificationInfo(this.mContext, str, new RequestListener()
    {
      public void onComplete(String paramAnonymousString)
      {
        WbAppInstallActivator.this.mNotificationInfo = new WbAppInstallActivator.NotificationInfo(paramAnonymousString);
        WbAppInstallActivator.this.mCountDownlatch.countDown();
      }

      public void onWeiboException(WeiboException paramAnonymousWeiboException)
      {
        LogUtil.d(WbAppInstallActivator.TAG, "requestNotificationInfo WeiboException Msg : " + paramAnonymousWeiboException.getMessage());
        WbAppInstallActivator.this.mCountDownlatch.countDown();
      }
    });
  }

  private static void requestNotificationInfo(Context paramContext, String paramString, RequestListener paramRequestListener)
  {
    String str1 = paramContext.getPackageName();
    String str2 = Utility.getSign(paramContext, str1);
    WeiboParameters localWeiboParameters = new WeiboParameters(paramString);
    localWeiboParameters.put("appkey", paramString);
    localWeiboParameters.put("packagename", str1);
    localWeiboParameters.put("key_hash", str2);
    new AsyncWeiboRunner(paramContext).requestAsync("http://api.weibo.cn/2/client/common_config", localWeiboParameters, "GET", paramRequestListener);
  }

  private static void showNotification(Context paramContext, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    NotificationHelper.showNotification(paramContext, paramString1, paramString2);
  }

  private static void startDownloadService(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(paramContext, DownloadService.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("notification_content", paramString1);
    localBundle.putString("download_url", paramString2);
    localIntent.putExtras(localBundle);
    paramContext.startService(localIntent);
  }

  private static Pair<Integer, File> walkDir(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    File[] arrayOfFile;
    do
    {
      do
      {
        return null;
        paramString = new File(paramString);
      }
      while ((!paramString.exists()) || (!paramString.isDirectory()));
      arrayOfFile = paramString.listFiles();
    }
    while (arrayOfFile == null);
    int j = 0;
    paramString = null;
    int m = arrayOfFile.length;
    int i = 0;
    if (i >= m)
      return new Pair(Integer.valueOf(j), paramString);
    File localFile = arrayOfFile[i];
    Object localObject2 = localFile.getName();
    int k = j;
    Object localObject1 = paramString;
    if (localFile.isFile())
    {
      k = j;
      localObject1 = paramString;
      if (((String)localObject2).endsWith(".apk"))
      {
        localObject2 = paramContext.getPackageManager().getPackageArchiveInfo(localFile.getAbsolutePath(), 64);
        if (isWeiboApk((PackageInfo)localObject2))
          break label163;
        localObject1 = paramString;
        k = j;
      }
    }
    while (true)
    {
      i += 1;
      j = k;
      paramString = (String)localObject1;
      break;
      label163: k = j;
      localObject1 = paramString;
      if (((PackageInfo)localObject2).versionCode > j)
      {
        k = ((PackageInfo)localObject2).versionCode;
        localObject1 = localFile;
      }
    }
  }

  public void activateWeiboInstall()
  {
    WeiboAppManager.WeiboInfo localWeiboInfo = WeiboAppManager.getInstance(this.mContext).getWeiboInfo();
    if ((localWeiboInfo != null) && (localWeiboInfo.isLegal()));
    for (int i = 0; ; i = 1)
    {
      if ((i != 0) && (this.isFree))
      {
        this.isFree = false;
        this.mCountDownlatch = new CountDownLatch(1);
        loadNotificationInfo();
        new Thread(new Runnable()
        {
          public void run()
          {
            Pair localPair = WbAppInstallActivator.walkDir(WbAppInstallActivator.this.mContext, this.val$dir);
            try
            {
              WbAppInstallActivator.this.mCountDownlatch.await();
              String str1;
              String str2;
              if ((WbAppInstallActivator.this.mNotificationInfo != null) && (WbAppInstallActivator.this.mNotificationInfo.isNotificationInfoValid()))
              {
                str1 = WbAppInstallActivator.NotificationInfo.access$0(WbAppInstallActivator.this.mNotificationInfo);
                str2 = WbAppInstallActivator.NotificationInfo.access$1(WbAppInstallActivator.this.mNotificationInfo);
                if ((localPair == null) || (localPair.second == null) || (((Integer)localPair.first).intValue() < WbAppInstallActivator.NotificationInfo.access$2(WbAppInstallActivator.this.mNotificationInfo)))
                  break label134;
                WbAppInstallActivator.showNotification(WbAppInstallActivator.this.mContext, str2, ((File)localPair.second).getAbsolutePath());
              }
              while (true)
              {
                return;
                label134: if ((NetworkHelper.isWifiValid(WbAppInstallActivator.this.mContext)) && (!TextUtils.isEmpty(str1)))
                  WbAppInstallActivator.startDownloadService(WbAppInstallActivator.this.mContext, str2, str1);
              }
            }
            catch (InterruptedException localInterruptedException)
            {
              localInterruptedException.printStackTrace();
              return;
            }
            finally
            {
              WbAppInstallActivator.this.isFree = true;
            }
          }
        }).start();
      }
      return;
    }
  }

  public static class NotificationInfo
  {
    private String downloadUrl;
    private String notificationContent;
    private int versionCode;

    public NotificationInfo(String paramString)
    {
      try
      {
        paramString = new JSONObject(paramString);
        if ((paramString.has("error")) || (paramString.has("error_code")))
        {
          LogUtil.d(WbAppInstallActivator.TAG, "parse NotificationInfo error !!!");
          return;
        }
        this.downloadUrl = paramString.optString("sdk_url", "");
        this.notificationContent = paramString.optString("sdk_push", "");
        this.versionCode = paramString.optInt("version_code");
        return;
      }
      catch (JSONException paramString)
      {
        LogUtil.d(WbAppInstallActivator.TAG, "parse NotificationInfo error: " + paramString.getMessage());
      }
    }

    public String getDownloadUrl()
    {
      return this.downloadUrl;
    }

    public String getNotificationContent()
    {
      return this.notificationContent;
    }

    public int getVersionCode()
    {
      return this.versionCode;
    }

    public boolean isNotificationInfoValid()
    {
      return !TextUtils.isEmpty(this.notificationContent);
    }

    public void setDownloadUrl(String paramString)
    {
      this.downloadUrl = paramString;
    }

    public void setNotificationContent(String paramString)
    {
      this.notificationContent = paramString;
    }

    public void setVersionCode(int paramInt)
    {
      this.versionCode = paramInt;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.WbAppInstallActivator
 * JD-Core Version:    0.6.2
 */