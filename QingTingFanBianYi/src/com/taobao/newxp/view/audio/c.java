package com.taobao.newxp.view.audio;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class c
{
  private URL a = null;
  private b b;

  private InputStream a(String paramString)
  {
    try
    {
      this.a = new URL(paramString);
      paramString = ((HttpURLConnection)this.a.openConnection()).getInputStream();
      return paramString;
    }
    catch (MalformedURLException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  private String b(String paramString1, String paramString2)
  {
    Object localObject2 = null;
    InputStream localInputStream2 = null;
    InputStream localInputStream1 = localInputStream2;
    Object localObject1 = localObject2;
    try
    {
      b localb = new b();
      localInputStream1 = localInputStream2;
      localObject1 = localObject2;
      String str = paramString1.substring(paramString1.lastIndexOf("/") + 1);
      localInputStream1 = localInputStream2;
      localObject1 = localObject2;
      System.out.println(str);
      localInputStream1 = localInputStream2;
      localObject1 = localObject2;
      boolean bool = localb.c(paramString2 + str);
      if (bool)
      {
        paramString1 = str;
        if (0 == 0);
      }
      do
      {
        do
        {
          try
          {
            throw new NullPointerException();
            return paramString1;
          }
          catch (IOException paramString1)
          {
            paramString1.printStackTrace();
            return str;
          }
          localInputStream1 = localInputStream2;
          localObject1 = localObject2;
          localInputStream2 = a(paramString1);
          localInputStream1 = localInputStream2;
          localObject1 = localInputStream2;
          if (localb.a(paramString2, str, localInputStream2) != null)
            break;
          paramString1 = "error";
        }
        while (localInputStream2 == null);
        try
        {
          localInputStream2.close();
          return "error";
        }
        catch (IOException paramString1)
        {
          paramString1.printStackTrace();
          return "error";
        }
        paramString1 = str;
      }
      while (localInputStream2 == null);
      try
      {
        localInputStream2.close();
        return str;
      }
      catch (IOException paramString1)
      {
        paramString1.printStackTrace();
        return str;
      }
    }
    catch (Exception paramString1)
    {
      do
      {
        localObject1 = localInputStream1;
        paramString1.printStackTrace();
        paramString1 = "error";
      }
      while (localInputStream1 == null);
      try
      {
        localInputStream1.close();
        return "error";
      }
      catch (IOException paramString1)
      {
        paramString1.printStackTrace();
        return "error";
      }
    }
    finally
    {
      if (localObject1 == null);
    }
    try
    {
      ((InputStream)localObject1).close();
      throw paramString1;
    }
    catch (IOException paramString2)
    {
      while (true)
        paramString2.printStackTrace();
    }
  }

  public b a()
  {
    return this.b;
  }

  public void a(b paramb)
  {
    this.b = paramb;
  }

  public void a(String paramString1, String paramString2)
  {
    new a(paramString2).execute(new String[] { paramString1 });
  }

  class a extends AsyncTask<String, Integer, String>
  {
    private String b;

    public a(String arg2)
    {
      Object localObject;
      this.b = localObject;
    }

    protected String a(String[] paramArrayOfString)
    {
      return c.a(c.this, paramArrayOfString[0], this.b);
    }

    protected void a(String paramString)
    {
      if (c.a(c.this) != null)
        c.a(c.this).a(c.c.a, paramString);
    }
  }

  public static abstract interface b
  {
    public abstract void a(c.c paramc, String paramString);
  }

  public static enum c
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.audio.c
 * JD-Core Version:    0.6.2
 */