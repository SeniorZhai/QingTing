package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import com.sina.weibo.sdk.ApiUtils;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;

public class SecurityHelper
{
  public static boolean checkResponseAppLegal(Context paramContext, WeiboAppManager.WeiboInfo paramWeiboInfo, Intent paramIntent)
  {
    if ((paramWeiboInfo != null) && (paramWeiboInfo.getSupportApi() <= 10352));
    while (true)
    {
      return true;
      if (paramWeiboInfo != null)
      {
        if (paramIntent != null);
        for (paramWeiboInfo = paramIntent.getStringExtra("_weibo_appPackage"); (paramWeiboInfo == null) || (paramIntent.getStringExtra("_weibo_transaction") == null) || (!ApiUtils.validateWeiboSign(paramContext, paramWeiboInfo)); paramWeiboInfo = null)
          return false;
      }
    }
  }

  public static boolean containSign(Signature[] paramArrayOfSignature, String paramString)
  {
    if ((paramArrayOfSignature == null) || (paramString == null));
    while (true)
    {
      return false;
      int j = paramArrayOfSignature.length;
      int i = 0;
      while (i < j)
      {
        if (paramString.equals(MD5.hexdigest(paramArrayOfSignature[i].toByteArray())))
          return true;
        i += 1;
      }
    }
  }

  public static boolean validateAppSignatureForIntent(Context paramContext, Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext == null);
    do
    {
      return false;
      paramIntent = paramContext.resolveActivity(paramIntent, 0);
    }
    while (paramIntent == null);
    paramIntent = paramIntent.activityInfo.packageName;
    try
    {
      boolean bool = containSign(paramContext.getPackageInfo(paramIntent, 64).signatures, "18da2bf10352443a00a5e046d9fca6bd");
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.SecurityHelper
 * JD-Core Version:    0.6.2
 */