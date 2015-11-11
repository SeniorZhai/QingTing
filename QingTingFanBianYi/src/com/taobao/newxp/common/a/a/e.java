package com.taobao.newxp.common.a.a;

import android.content.Context;
import com.taobao.newxp.common.c.d;
import com.taobao.newxp.controller.ExchangeDataService;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class e
{
  private String a = ExchangeDataService.getVerInfo().c();
  private String b = ExchangeDataService.getVerInfo().b();
  private String c = ExchangeDataService.getVerInfo().a();
  private String d;
  private String e = "";
  private String f;
  private String g;
  private String h;

  private e(Context paramContext, String paramString)
  {
    paramContext = paramContext.getApplicationContext();
    this.d = com.taobao.munion.base.anticheat.b.c(paramContext);
    this.f = com.taobao.munion.base.anticheat.b.a(paramContext.getApplicationContext());
    this.g = paramString;
    this.h = com.taobao.newxp.common.b.b.a(paramString);
  }

  public static e a(Context paramContext, String paramString)
  {
    return new e(paramContext, paramString);
  }

  public String a()
    throws UnsupportedEncodingException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("url=");
    localStringBuilder.append(this.g);
    localStringBuilder.append("&");
    localStringBuilder.append("refpid=");
    localStringBuilder.append(this.a);
    localStringBuilder.append("&");
    localStringBuilder.append("e=");
    localStringBuilder.append(this.b);
    localStringBuilder.append("&");
    localStringBuilder.append("a=");
    localStringBuilder.append(this.c);
    localStringBuilder.append("&");
    localStringBuilder.append("i=");
    localStringBuilder.append(this.d);
    localStringBuilder.append("&");
    localStringBuilder.append("f=");
    localStringBuilder.append(this.e);
    localStringBuilder.append("&");
    localStringBuilder.append("u=");
    localStringBuilder.append(this.f);
    localStringBuilder.append("&");
    localStringBuilder.append("v=");
    localStringBuilder.append("1.0");
    localStringBuilder.append("&");
    localStringBuilder.append("s=");
    localStringBuilder.append(this.h);
    return URLEncoder.encode(localStringBuilder.toString(), "UTF-8");
  }

  private static class a
  {
    static final String a = "url=";
    static final String b = "refpid=";
    static final String c = "e=";
    static final String d = "a=";
    static final String e = "i=";
    static final String f = "f=";
    static final String g = "u=";
    static final String h = "v=";
    static final String i = "s=";
    static final String j = "&";
    static final String k = ",";
    static final String l = "|";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.a.a.e
 * JD-Core Version:    0.6.2
 */