package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.H5AuthActivity;
import com.alipay.sdk.app.Result;

public class AuthHelper
{
  public static final Object a = AuthTask.class;
  private static final String b = "com.eg.android.AlipayGphone";
  private static final int c = 73;

  public static String a(Activity paramActivity, String paramString)
  {
    if (a(paramActivity))
    {
      Object localObject2 = new PayHelper(paramActivity);
      if (paramString.contains("\""));
      for (Object localObject1 = paramString + "&bizcontext=\"{\"appkey\":\"2014052600006128\"}\""; ; localObject1 = paramString + "&bizcontext={\"appkey\":\"2014052600006128\"}")
      {
        localObject2 = ((PayHelper)localObject2).a((String)localObject1);
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2))
          localObject1 = b(paramActivity, paramString);
        return localObject1;
      }
    }
    return b(paramActivity, paramString);
  }

  private static boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.eg.android.AlipayGphone", 128);
      if (paramContext == null)
        return false;
      int i = paramContext.versionCode;
      if (i >= 73)
        return true;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return false;
  }

  private static String b(Activity arg0, String paramString)
  {
    Intent localIntent = new Intent(???, H5AuthActivity.class);
    localIntent.putExtra("params", paramString);
    ???.startActivity(localIntent);
    try
    {
      synchronized (a)
      {
        a.wait();
        label36: paramString = Result.a();
        ??? = paramString;
        if (TextUtils.isEmpty(paramString))
          ??? = Result.b();
        return ???;
      }
    }
    catch (InterruptedException paramString)
    {
      break label36;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.AuthHelper
 * JD-Core Version:    0.6.2
 */