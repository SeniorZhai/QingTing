package com.intowow.sdk.a;

import org.json.JSONObject;

public class a
{
  private String a = null;

  public static a a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    a locala = new a();
    locala.a = paramJSONObject.optString("endpoint", null);
    return locala;
  }

  public String a()
  {
    return this.a;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.a
 * JD-Core Version:    0.6.2
 */