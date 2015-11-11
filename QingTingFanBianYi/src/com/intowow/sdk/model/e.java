package com.intowow.sdk.model;

import org.json.JSONObject;

public class e
{
  private String a = null;
  private int b = 0;

  public static e a(JSONObject paramJSONObject)
  {
    try
    {
      e locale = new e();
      locale.a = paramJSONObject.optString("geo_group", null);
      locale.b = paramJSONObject.optInt("geo_id", 0);
      return locale;
    }
    catch (Exception paramJSONObject)
    {
    }
    return null;
  }

  public String a()
  {
    return this.a;
  }

  public int b()
  {
    return this.b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.model.e
 * JD-Core Version:    0.6.2
 */