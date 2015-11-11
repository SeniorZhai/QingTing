package com.alipay.sdk.auth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;

public class AuthHelper
{
  private static final String a = "com.eg.android.AlipayGphone";
  private static final int b = 65;

  public static void a(Activity paramActivity, APAuthInfo paramAPAuthInfo)
  {
    if (a(paramActivity))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("alipayauth://platformapi/startapp");
      localStringBuilder.append("?appId=20000122");
      localStringBuilder.append("&approveType=005");
      localStringBuilder.append("&scope=kuaijie");
      localStringBuilder.append("&productId=");
      localStringBuilder.append(paramAPAuthInfo.b());
      localStringBuilder.append("&thirdpartyId=");
      localStringBuilder.append(paramAPAuthInfo.a());
      localStringBuilder.append("&redirectUri=");
      localStringBuilder.append(paramAPAuthInfo.d());
      a(paramActivity, localStringBuilder.toString());
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("app_id=");
    localStringBuilder.append(paramAPAuthInfo.a());
    localStringBuilder.append("&partner=");
    localStringBuilder.append(paramAPAuthInfo.c());
    localStringBuilder.append("&scope=kuaijie");
    localStringBuilder.append("&login_goal=auth");
    localStringBuilder.append("&redirect_url=");
    localStringBuilder.append(paramAPAuthInfo.d());
    localStringBuilder.append("&view=wap");
    localStringBuilder.append("&prod_code=");
    localStringBuilder.append(paramAPAuthInfo.b());
    Intent localIntent = new Intent(paramActivity, AuthActivity.class);
    localIntent.putExtra("params", localStringBuilder.toString());
    localIntent.putExtra("redirectUri", paramAPAuthInfo.d());
    paramActivity.startActivity(localIntent);
  }

  public static void a(Activity paramActivity, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      paramActivity.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
    }
  }

  private static boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
      if (paramContext == null)
        return false;
      int i = paramContext.versionCode;
      if (i >= 65)
        return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return false;
  }

  private static void b(Activity paramActivity, APAuthInfo paramAPAuthInfo)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("alipayauth://platformapi/startapp");
    localStringBuilder.append("?appId=20000122");
    localStringBuilder.append("&approveType=005");
    localStringBuilder.append("&scope=kuaijie");
    localStringBuilder.append("&productId=");
    localStringBuilder.append(paramAPAuthInfo.b());
    localStringBuilder.append("&thirdpartyId=");
    localStringBuilder.append(paramAPAuthInfo.a());
    localStringBuilder.append("&redirectUri=");
    localStringBuilder.append(paramAPAuthInfo.d());
    a(paramActivity, localStringBuilder.toString());
  }

  private static void c(Activity paramActivity, APAuthInfo paramAPAuthInfo)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("app_id=");
    localStringBuilder.append(paramAPAuthInfo.a());
    localStringBuilder.append("&partner=");
    localStringBuilder.append(paramAPAuthInfo.c());
    localStringBuilder.append("&scope=kuaijie");
    localStringBuilder.append("&login_goal=auth");
    localStringBuilder.append("&redirect_url=");
    localStringBuilder.append(paramAPAuthInfo.d());
    localStringBuilder.append("&view=wap");
    localStringBuilder.append("&prod_code=");
    localStringBuilder.append(paramAPAuthInfo.b());
    Intent localIntent = new Intent(paramActivity, AuthActivity.class);
    localIntent.putExtra("params", localStringBuilder.toString());
    localIntent.putExtra("redirectUri", paramAPAuthInfo.d());
    paramActivity.startActivity(localIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.auth.AuthHelper
 * JD-Core Version:    0.6.2
 */