package com.taobao.munion.base;

import android.content.Context;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.InputStream;

public class h
{
  private c a = new c();

  public c a()
  {
    return this.a;
  }

  public InputStream a(Context paramContext, String paramString)
    throws IOException
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      Log.i("Looking for " + paramString + " in asset path...", new Object[0]);
      localObject1 = localObject2;
      paramContext = paramContext.getAssets().open(paramString);
      localObject1 = paramContext;
      Log.i("Found " + paramString + " in asset path", new Object[0]);
      return paramContext;
    }
    catch (IOException paramContext)
    {
      Log.i("No file found in assets with name [" + paramString + "].", new Object[0]);
    }
    return localObject1;
  }

  public InputStream a(String paramString)
    throws IOException
  {
    Object localObject = null;
    if (this.a != null)
    {
      Log.i("Looking for " + paramString + " in classpath...", new Object[0]);
      InputStream localInputStream = this.a.a().getResourceAsStream(paramString);
      localObject = localInputStream;
      if (localInputStream != null)
      {
        Log.i("Found " + paramString + " in classpath", new Object[0]);
        localObject = localInputStream;
      }
    }
    return localObject;
  }

  public void a(c paramc)
  {
    this.a = paramc;
  }

  public InputStream b(Context paramContext, String paramString)
    throws IOException
  {
    InputStream localInputStream = a(paramContext, paramString);
    paramContext = localInputStream;
    if (localInputStream == null)
      paramContext = a(paramString);
    if (paramContext == null)
      Log.i("Could not locate [" + paramString + "] in any location", new Object[0]);
    return paramContext;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.h
 * JD-Core Version:    0.6.2
 */