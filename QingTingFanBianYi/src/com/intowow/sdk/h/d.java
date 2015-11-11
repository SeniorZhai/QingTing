package com.intowow.sdk.h;

import org.json.JSONObject;

public class d
{
  private String a;
  private int b;
  private String c;
  private int d;
  private e e;
  private String f;
  private long g;
  private int h;
  private boolean i;
  private JSONObject j;

  public d(String paramString1, int paramInt1, j paramj, int paramInt2, long paramLong, e parame, String paramString2, int paramInt3)
  {
    this.a = paramString1;
    this.b = paramInt1;
    this.c = j.a(paramj);
    this.d = paramInt2;
    this.e = parame;
    this.i = false;
    this.f = paramString2;
    this.g = paramLong;
    this.h = paramInt3;
    this.j = new JSONObject();
  }

  public d(String paramString1, int paramInt1, String paramString2, int paramInt2, long paramLong, e parame, String paramString3, int paramInt3)
  {
    this.a = paramString1;
    this.b = paramInt1;
    this.c = paramString2;
    this.d = paramInt2;
    this.e = parame;
    this.i = false;
    this.f = paramString3;
    this.g = paramLong;
    this.h = paramInt3;
    this.j = new JSONObject();
  }

  public static void a(JSONObject paramJSONObject, f paramf, Object paramObject)
  {
    paramJSONObject.put(f.a(paramf), paramObject);
  }

  public d a(f paramf, double paramDouble)
  {
    a(this.j, paramf, Double.valueOf(paramDouble));
    this.i = true;
    return this;
  }

  public d a(f paramf, int paramInt)
  {
    a(this.j, paramf, Integer.valueOf(paramInt));
    this.i = true;
    return this;
  }

  public d a(f paramf, String paramString)
  {
    a(this.j, paramf, paramString);
    this.i = true;
    return this;
  }

  public d a(f paramf, JSONObject paramJSONObject)
  {
    a(this.j, paramf, paramJSONObject);
    this.i = true;
    return this;
  }

  public d a(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      this.j = paramJSONObject;
      this.i = true;
      return this;
    }
    this.j = null;
    this.i = false;
    return this;
  }

  public JSONObject a()
  {
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put(c.a(c.a), this.c);
    localJSONObject.put(c.a(c.b), this.e);
    localJSONObject.put(c.a(c.c), this.b);
    localJSONObject.put(c.a(c.d), System.currentTimeMillis() + this.g);
    localJSONObject.put(c.a(c.e), this.a);
    localJSONObject.put(c.a(c.f), this.d);
    localJSONObject.put(c.a(c.j), this.f);
    if (this.h >= 0)
      localJSONObject.put(c.a(c.i), this.h);
    if (com.intowow.sdk.a.e.a)
      localJSONObject.put(c.a(c.g), com.intowow.sdk.a.e.a);
    if (this.i)
      localJSONObject.put(c.a(c.h), this.j);
    return localJSONObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.h.d
 * JD-Core Version:    0.6.2
 */