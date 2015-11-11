package com.taobao.newxp.net;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.taobao.munion.base.volley.a.a;
import com.taobao.munion.base.volley.a.d;
import com.taobao.munion.base.volley.a.e;
import com.taobao.munion.base.volley.a.h;
import com.taobao.munion.base.volley.m;
import java.io.File;

public class l
  implements c
{
  private m b;

  public <T> com.taobao.munion.base.volley.l<T> a(com.taobao.munion.base.volley.l<T> paraml)
  {
    if (this.b != null)
      return this.b.a(paraml);
    throw new RuntimeException("MunionVolley is not initized..");
  }

  public c a(Context paramContext)
  {
    File localFile = new File(paramContext.getCacheDir(), "taobao_munion");
    String str1 = "volley/0";
    try
    {
      String str2 = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str2, 0);
      paramContext = str2 + "/" + paramContext.versionCode;
      if (Build.VERSION.SDK_INT >= 9);
      for (paramContext = new h(); ; paramContext = new e(AndroidHttpClient.newInstance(paramContext)))
      {
        paramContext = new a(paramContext);
        this.b = new m(new d(localFile), paramContext);
        this.b.a();
        return this;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      while (true)
        paramContext = str1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.l
 * JD-Core Version:    0.6.2
 */