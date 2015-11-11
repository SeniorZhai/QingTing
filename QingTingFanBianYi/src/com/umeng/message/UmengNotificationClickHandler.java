package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UmengNotificationClickHandler
  implements UHandler
{
  private static final String a = UmengNotificationClickHandler.class.getName();

  private Intent a(Intent paramIntent, UMessage paramUMessage)
  {
    if ((paramIntent == null) || (paramUMessage == null) || (paramUMessage.extra == null));
    while (true)
    {
      return paramIntent;
      paramUMessage = paramUMessage.extra.entrySet().iterator();
      while (paramUMessage.hasNext())
      {
        Object localObject = (Map.Entry)paramUMessage.next();
        String str = (String)((Map.Entry)localObject).getKey();
        localObject = (String)((Map.Entry)localObject).getValue();
        if (str != null)
          paramIntent.putExtra(str, (String)localObject);
      }
    }
  }

  public void dealWithCustomAction(Context paramContext, UMessage paramUMessage)
  {
  }

  public void handleMessage(Context paramContext, UMessage paramUMessage)
  {
    try
    {
      if (TextUtils.isEmpty(paramUMessage.after_open))
        break label92;
      if (TextUtils.equals("go_url", paramUMessage.after_open))
      {
        openUrl(paramContext, paramUMessage);
        return;
      }
      if (TextUtils.equals("go_activity", paramUMessage.after_open))
      {
        openActivity(paramContext, paramUMessage);
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    if (TextUtils.equals("go_custom", paramUMessage.after_open))
    {
      dealWithCustomAction(paramContext, paramUMessage);
      return;
    }
    if (TextUtils.equals("go_app", paramUMessage.after_open))
    {
      launchApp(paramContext, paramUMessage);
      return;
    }
    label92: if ((paramUMessage.url != null) && (!TextUtils.isEmpty(paramUMessage.url.trim())))
    {
      openUrl(paramContext, paramUMessage);
      return;
    }
    if ((paramUMessage.activity != null) && (!TextUtils.isEmpty(paramUMessage.activity.trim())))
    {
      openActivity(paramContext, paramUMessage);
      return;
    }
    if ((paramUMessage.custom != null) && (!TextUtils.isEmpty(paramUMessage.custom.trim())))
    {
      dealWithCustomAction(paramContext, paramUMessage);
      return;
    }
    launchApp(paramContext, paramUMessage);
  }

  public void launchApp(Context paramContext, UMessage paramUMessage)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent == null)
    {
      Log.b(a, "handleMessage(): cannot find app: " + paramContext.getPackageName());
      return;
    }
    localIntent.setPackage(null);
    localIntent.addFlags(268435456);
    a(localIntent, paramUMessage);
    paramContext.startActivity(localIntent);
    Log.c(a, "handleMessage(): lunach app: " + paramContext.getPackageName());
  }

  public void openActivity(Context paramContext, UMessage paramUMessage)
  {
    if ((paramUMessage.activity == null) || (TextUtils.isEmpty(paramUMessage.activity.trim())))
      return;
    Intent localIntent = new Intent();
    a(localIntent, paramUMessage);
    localIntent.setClassName(paramContext, paramUMessage.activity);
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public void openUrl(Context paramContext, UMessage paramUMessage)
  {
    if ((paramUMessage.url == null) || (TextUtils.isEmpty(paramUMessage.url.trim())))
      return;
    Log.c(a, "handleMessage(): open url: " + paramUMessage.url);
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(paramUMessage.url));
    a(localIntent, paramUMessage);
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengNotificationClickHandler
 * JD-Core Version:    0.6.2
 */