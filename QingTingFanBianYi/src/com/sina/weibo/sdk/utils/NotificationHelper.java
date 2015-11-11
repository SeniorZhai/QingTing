package com.sina.weibo.sdk.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import java.io.File;

public class NotificationHelper
{
  private static final int NOTIFICATION_ID = 1;
  private static final String WEIBO = "Weibo";
  private static final String WEIBO_ZH_CN = "微博";
  private static final String WEIBO_ZH_TW = "微博";

  private static PendingIntent buildInstallApkIntent(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
      return PendingIntent.getActivity(paramContext, 0, localIntent, 16);
    }
    return PendingIntent.getActivity(paramContext, 0, new Intent(), 16);
  }

  private static Notification buildNotification(Context paramContext, String paramString1, String paramString2)
  {
    String str = ResourceManager.getString(paramContext, "Weibo", "微博", "微博");
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(paramContext);
    localBuilder.setAutoCancel(true);
    localBuilder.setWhen(System.currentTimeMillis());
    localBuilder.setLargeIcon(((BitmapDrawable)ResourceManager.getDrawable(paramContext, "ic_com_sina_weibo_sdk_weibo_logo.png")).getBitmap());
    localBuilder.setSmallIcon(getNotificationIcon(paramContext));
    localBuilder.setContentTitle(str);
    localBuilder.setTicker(paramString1);
    localBuilder.setContentText(paramString1);
    localBuilder.setContentIntent(buildInstallApkIntent(paramContext, paramString2));
    return localBuilder.build();
  }

  private static int getNotificationIcon(Context paramContext)
  {
    int i = getResourceId(paramContext, "com_sina_weibo_sdk_weibo_logo", "drawable");
    if (i > 0)
      return i;
    return 17301659;
  }

  private static int getResourceId(Context paramContext, String paramString1, String paramString2)
  {
    String str = paramContext.getApplicationContext().getPackageName();
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getResourcesForApplication(str).getIdentifier(paramString1, paramString2, str);
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }

  public static void showNotification(Context paramContext, String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      return;
    ((NotificationManager)paramContext.getSystemService("notification")).notify(1, buildNotification(paramContext, paramString1, paramString2));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.NotificationHelper
 * JD-Core Version:    0.6.2
 */