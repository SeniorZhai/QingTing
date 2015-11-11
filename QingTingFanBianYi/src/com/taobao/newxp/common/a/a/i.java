package com.taobao.newxp.common.a.a;

import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import com.taobao.munion.base.anticheat.b;
import com.taobao.newxp.common.a.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class i
{
  public String a;
  public String b;
  public String c;
  private int d;
  private int e;
  private int f;
  private int g;
  private long h = 0L;
  private long i = 0L;
  private long j = 0L;
  private String k;
  private g l;

  private i(Bundle paramBundle)
  {
    paramBundle = a.a().b();
    if (paramBundle != null)
    {
      this.c = paramBundle.c;
      this.d = paramBundle.f;
      this.e = paramBundle.g;
      this.k = paramBundle.d;
    }
  }

  private int a()
  {
    long l1 = 0L;
    int n = 0;
    int m = 0;
    int i2 = n;
    if (this.c != null)
    {
      i2 = n;
      if (this.c.length() > 0)
      {
        i2 = n;
        if (this.l != null)
        {
          if (this.l.i > 0L)
            l1 = this.l.i;
          if (this.l.a > 0);
          for (n = this.l.a; ; n = 0)
          {
            long l2 = (l1 + this.h) % 9L;
            l1 = l2;
            if (l2 >= 100L)
              l1 = 100L;
            int i1 = 0;
            while (true)
            {
              i2 = m;
              if (i1 >= l1)
                break;
              int i3 = i1 * n % this.c.length();
              i2 = m;
              if (this.c.length() > i3)
                i2 = m + this.c.charAt(i3);
              i1 += 1;
              m = i2;
            }
          }
        }
      }
    }
    return i2;
  }

  public static i a(Bundle paramBundle)
  {
    return new i(paramBundle);
  }

  private String b(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(-1);
    localStringBuilder.append(",");
    localStringBuilder.append(this.d);
    localStringBuilder.append(",");
    localStringBuilder.append(this.e);
    localStringBuilder.append(",");
    localStringBuilder.append(this.l.a);
    localStringBuilder.append(",");
    localStringBuilder.append(-1);
    localStringBuilder.append(",");
    localStringBuilder.append(this.l.i);
    localStringBuilder.append(",");
    localStringBuilder.append(this.f);
    localStringBuilder.append(",");
    localStringBuilder.append(this.g);
    localStringBuilder.append(",");
    localStringBuilder.append(b.g(paramContext));
    localStringBuilder.append(",");
    localStringBuilder.append(b.h(paramContext));
    localStringBuilder.append(",");
    return localStringBuilder.toString();
  }

  public String a(Context paramContext)
    throws UnsupportedEncodingException
  {
    Object localObject = (j)a.a().b(3);
    this.f = ((j)localObject).a();
    this.g = ((j)localObject).c();
    this.l = ((j)localObject).d();
    this.h = ((j)localObject).e();
    this.i = ((j)localObject).f();
    this.j = (this.i - this.h);
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("v=");
    ((StringBuilder)localObject).append("1.0");
    ((StringBuilder)localObject).append("&");
    ((StringBuilder)localObject).append("s=");
    ((StringBuilder)localObject).append(a());
    ((StringBuilder)localObject).append("&");
    ((StringBuilder)localObject).append("n=");
    ((StringBuilder)localObject).append(this.k);
    ((StringBuilder)localObject).append("&");
    ((StringBuilder)localObject).append("i=");
    ((StringBuilder)localObject).append(b.c(paramContext));
    ((StringBuilder)localObject).append("&");
    ((StringBuilder)localObject).append("u=");
    ((StringBuilder)localObject).append(this.c);
    ((StringBuilder)localObject).append("&");
    ((StringBuilder)localObject).append("et=");
    ((StringBuilder)localObject).append(this.h);
    ((StringBuilder)localObject).append("&");
    ((StringBuilder)localObject).append("t=");
    ((StringBuilder)localObject).append(this.j);
    ((StringBuilder)localObject).append("&");
    ((StringBuilder)localObject).append("m=");
    ((StringBuilder)localObject).append(b(paramContext));
    return URLEncoder.encode(new String(Base64.encode(((StringBuilder)localObject).toString().getBytes(), 0), "UTF-8"), "UTF-8");
  }

  private static class a
  {
    static final String a = "v=";
    static final String b = "s=";
    static final String c = "n=";
    static final String d = "i=";
    static final String e = "u=";
    static final String f = "et=";
    static final String g = "t=";
    static final String h = "m=";
    static final String i = "&";
    static final String j = ",";
  }

  private static class b
  {
    static final String a = "1.0";
    static final int b = -1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.a.i
 * JD-Core Version:    0.6.2
 */