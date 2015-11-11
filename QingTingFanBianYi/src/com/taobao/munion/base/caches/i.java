package com.taobao.munion.base.caches;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;

public class i
{
  public static final char a = '~';
  private static final char b = '_';
  private static final String c = "0000000000000";
  private static final String d = "utf-8";
  private static final String e = "";
  private long f;
  private long g;
  private String h;
  private String i;
  private String j;
  private String k;
  private String l;
  private long m;
  private boolean n = true;

  public int a(i parami)
  {
    if (this == parami)
      return 0;
    if (this.f > parami.f)
      return 1;
    return -1;
  }

  public long a()
  {
    return this.f;
  }

  public void a(long paramLong)
  {
    this.f = paramLong;
  }

  public void a(String paramString)
  {
    this.h = paramString;
  }

  public void a(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  public String b()
  {
    return this.h;
  }

  public void b(long paramLong)
  {
    this.m = paramLong;
  }

  public void b(String paramString)
  {
    this.l = paramString;
  }

  public String c()
  {
    return this.l;
  }

  public void c(long paramLong)
  {
    this.g = paramLong;
  }

  public void c(String paramString)
  {
    this.i = paramString;
  }

  public String d()
  {
    return this.i;
  }

  public void d(String paramString)
  {
    this.k = paramString;
  }

  public long e()
  {
    return this.m;
  }

  public void e(String paramString)
  {
    this.j = paramString;
  }

  public byte[] f()
  {
    Object localObject = new StringBuilder();
    if (this.f > 0L)
      ((StringBuilder)localObject).append(this.f);
    while (true)
    {
      if (this.n)
      {
        ((StringBuilder)localObject).append('~');
        label40: if (this.g <= 0L)
          break label233;
        ((StringBuilder)localObject).append(this.g);
        label58: if (!this.n)
          break label243;
        ((StringBuilder)localObject).append('~');
        label72: if (this.h != null)
          break label253;
        ((StringBuilder)localObject).append("");
        label86: if (!this.n)
          break label265;
        ((StringBuilder)localObject).append('~');
        label100: if (this.j != null)
          break label275;
        ((StringBuilder)localObject).append("");
        label114: if (!this.n)
          break label287;
        ((StringBuilder)localObject).append('~');
        label128: if (this.i != null)
          break label297;
        ((StringBuilder)localObject).append("");
        label142: if (!this.n)
          break label309;
        ((StringBuilder)localObject).append('~');
        label156: if (this.k != null)
          break label319;
        ((StringBuilder)localObject).append("");
        label170: if (!this.n)
          break label331;
        ((StringBuilder)localObject).append('~');
        label184: if (!TextUtils.isEmpty(this.l))
          break label341;
        ((StringBuilder)localObject).append("utf-8");
      }
      try
      {
        while (true)
        {
          localObject = ((StringBuilder)localObject).toString().getBytes("UTF-8");
          return localObject;
          ((StringBuilder)localObject).append("0000000000000");
          break;
          ((StringBuilder)localObject).append('_');
          break label40;
          label233: ((StringBuilder)localObject).append("0000000000000");
          break label58;
          label243: ((StringBuilder)localObject).append('_');
          break label72;
          label253: ((StringBuilder)localObject).append(this.h);
          break label86;
          label265: ((StringBuilder)localObject).append('_');
          break label100;
          label275: ((StringBuilder)localObject).append(this.j);
          break label114;
          label287: ((StringBuilder)localObject).append('_');
          break label128;
          label297: ((StringBuilder)localObject).append(this.i);
          break label142;
          label309: ((StringBuilder)localObject).append('_');
          break label156;
          label319: ((StringBuilder)localObject).append(this.k);
          break label170;
          label331: ((StringBuilder)localObject).append('_');
          break label184;
          label341: ((StringBuilder)localObject).append(this.l);
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        localUnsupportedEncodingException.printStackTrace();
      }
    }
    return null;
  }

  public String g()
  {
    return this.k;
  }

  public long h()
  {
    return this.g;
  }

  public String i()
  {
    return this.j;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.i
 * JD-Core Version:    0.6.2
 */