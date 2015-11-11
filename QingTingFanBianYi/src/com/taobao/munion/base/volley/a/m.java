package com.taobao.munion.base.volley.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import java.io.File;

public class m
{
  private static final String a = "volley";

  public static com.taobao.munion.base.volley.m a(Context paramContext)
  {
    return a(paramContext, null);
  }

  public static com.taobao.munion.base.volley.m a(Context paramContext, g paramg)
  {
    File localFile = new File(paramContext.getCacheDir(), "volley");
    Object localObject = "volley/0";
    try
    {
      String str = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 0);
      paramContext = str + "/" + paramContext.versionCode;
      localObject = paramContext;
      label64: paramContext = paramg;
      if (paramg == null)
        if (Build.VERSION.SDK_INT < 9)
          break label118;
      label118: for (paramContext = new h(); ; paramContext = new e(AndroidHttpClient.newInstance((String)localObject)))
      {
        paramContext = new a(paramContext);
        paramContext = new com.taobao.munion.base.volley.m(new d(localFile), paramContext);
        paramContext.a();
        return paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      break label64;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.m
 * JD-Core Version:    0.6.2
 */