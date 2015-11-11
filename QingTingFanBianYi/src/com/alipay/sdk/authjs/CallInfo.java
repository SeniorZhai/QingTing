package com.alipay.sdk.authjs;

import org.json.JSONException;
import org.json.JSONObject;

public class CallInfo
{
  public static final String a = "CallInfo";
  public static final String b = "call";
  public static final String c = "callback";
  public static final String d = "bundleName";
  public static final String e = "clientId";
  public static final String f = "param";
  public static final String g = "func";
  public static final String h = "msgType";
  private String i;
  private String j;
  private String k;
  private String l;
  private JSONObject m;
  private boolean n = false;

  public CallInfo(String paramString)
  {
    this.l = paramString;
  }

  private static String a(CallError paramCallError)
  {
    switch (1.a[paramCallError.ordinal()])
    {
    default:
      return "none";
    case 1:
      return "function not found";
    case 2:
      return "invalid parameter";
    case 3:
    }
    return "runtime error";
  }

  private void a(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  private void d(String paramString)
  {
    this.l = paramString;
  }

  private boolean e()
  {
    return this.n;
  }

  private String f()
  {
    return this.j;
  }

  private String g()
  {
    return this.l;
  }

  public final String a()
  {
    return this.i;
  }

  public final void a(String paramString)
  {
    this.i = paramString;
  }

  public final void a(JSONObject paramJSONObject)
  {
    this.m = paramJSONObject;
  }

  public final String b()
  {
    return this.k;
  }

  public final void b(String paramString)
  {
    this.j = paramString;
  }

  public final JSONObject c()
  {
    return this.m;
  }

  public final void c(String paramString)
  {
    this.k = paramString;
  }

  public final String d()
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("clientId", this.i);
    localJSONObject.put("func", this.k);
    localJSONObject.put("param", this.m);
    localJSONObject.put("msgType", this.l);
    return localJSONObject.toString();
  }

  public static enum CallError
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.authjs.CallInfo
 * JD-Core Version:    0.6.2
 */