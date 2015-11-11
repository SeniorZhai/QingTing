package com.android.volley.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build.VERSION;
import com.android.volley.RequestQueue;
import java.io.File;

public class Volley
{
  private static final String DEFAULT_CACHE_DIR = "volley";

  public static RequestQueue newRequestQueue(Context paramContext, int paramInt)
  {
    return newRequestQueue(paramContext, null, paramInt);
  }

  public static RequestQueue newRequestQueue(Context paramContext, HttpStack paramHttpStack, int paramInt)
  {
    File localFile = new File(paramContext.getCacheDir(), "volley");
    Object localObject = "volley/0";
    try
    {
      String str = paramContext.getPackageName();
      paramContext = paramContext.getPackageManager().getPackageInfo(str, 0);
      paramContext = str + "/" + paramContext.versionCode;
      localObject = paramContext;
      label65: paramContext = paramHttpStack;
      if (paramHttpStack == null)
        if (Build.VERSION.SDK_INT < 9)
          break label121;
      label121: for (paramContext = new HurlStack(); ; paramContext = new HttpClientStack(AndroidHttpClient.newInstance((String)localObject)))
      {
        paramContext = new BasicNetwork(paramContext);
        paramContext = new RequestQueue(new DiskBasedCache(localFile), paramContext, paramInt);
        paramContext.start();
        return paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      break label65;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.Volley
 * JD-Core Version:    0.6.2
 */