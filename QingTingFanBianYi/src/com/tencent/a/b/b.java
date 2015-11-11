package com.tencent.a.b;

import android.location.Location;

public class b
{
  private static b b;
  public String a = "";
  private double c = 0.0D;
  private double d = 0.0D;
  private double e = 0.0D;
  private double f = 0.0D;
  private double g = 0.0D;
  private double h = 0.0D;
  private a i;
  private b j = null;
  private boolean k = false;

  public static b a()
  {
    if (b == null)
      b = new b();
    return b;
  }

  public static n a(String paramString1, String paramString2, byte[] paramArrayOfByte)
    throws o, r, Exception
  {
    int m = 1;
    if (l.b() != null);
    while (m == 0)
    {
      throw new o();
      m = 0;
    }
    try
    {
      paramString1 = q.a(false, paramString1, paramString2, null, paramArrayOfByte, false, true);
      return paramString1;
    }
    catch (Exception paramString1)
    {
    }
    throw paramString1;
  }

  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }

  public final void a(double paramDouble1, double paramDouble2, a parama)
  {
    this.i = parama;
    if ((this.g != 0.0D) && (this.h != 0.0D))
    {
      parama = new float[10];
      Location.distanceBetween(paramDouble1, paramDouble2, this.c, this.d, parama);
      if (parama[0] < 1500.0F)
        this.i.a(this.g + paramDouble1, this.h + paramDouble2);
    }
    while (this.k)
      return;
    this.a = ("{\"source\":101,\"access_token\":\"160e7bd42dec9428721034e0146fc6dd\",\"location\":{\"latitude\":" + paramDouble1 + ",\"longitude\":" + paramDouble2 + "}\t}");
    this.e = paramDouble1;
    this.f = paramDouble2;
    this.j = new b();
    this.j.start();
  }

  public static abstract interface a
  {
    public abstract void a(double paramDouble1, double paramDouble2);
  }

  public final class b extends Thread
  {
    public b()
    {
    }

    public final void run()
    {
      try
      {
        Object localObject = j.a(b.this.a.getBytes());
        b.a(b.this, true);
        localObject = b.a("http://ls.map.soso.com/deflect?c=1", "SOSO MAP LBS SDK", (byte[])localObject);
        b.a(b.this, false);
        arrayOfByte = j.b(((n)localObject).a);
        b.a(b.this, arrayOfByte, ((n)localObject).b);
        return;
      }
      catch (Exception localException1)
      {
        do
        {
          byte[] arrayOfByte;
          int i = 0;
          while (true)
          {
            i += 1;
            if (i > 3)
              break;
            try
            {
              sleep(2000L);
              n localn = b.a("http://ls.map.soso.com/deflect?c=1", "SOSO MAP LBS SDK", j.a(b.this.a.getBytes()));
              b.a(b.this, false);
              arrayOfByte = j.b(localn.a);
              b.a(b.this, arrayOfByte, localn.b);
              return;
            }
            catch (Exception localException2)
            {
            }
          }
          b.a(b.this, false);
        }
        while (b.a(b.this) == null);
        b.a(b.this).a(360.0D, 360.0D);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.b
 * JD-Core Version:    0.6.2
 */