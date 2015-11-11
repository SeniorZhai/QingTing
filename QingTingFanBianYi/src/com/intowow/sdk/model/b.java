package com.intowow.sdk.model;

import com.intowow.sdk.j.h;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class b
{
  private int a = 0;
  private long b = 0L;
  private List<ADProfile> c = new ArrayList();

  public static b a(JSONObject paramJSONObject)
  {
    int i = 0;
    while (true)
    {
      try
      {
        b localb = new b();
        localb.a = paramJSONObject.getInt("count");
        if (paramJSONObject.has("server_time"))
          localb.b = paramJSONObject.getLong("server_time");
        paramJSONObject = paramJSONObject.getJSONArray("items");
        if (i >= localb.a)
          return localb;
        ADProfile localADProfile = ADProfile.a(paramJSONObject.getJSONObject(i));
        if ((localADProfile != null) && (localADProfile.a()))
          localb.c.add(localADProfile);
        else
          h.b("not vaild", new Object[0]);
      }
      catch (Exception paramJSONObject)
      {
        return null;
      }
      i += 1;
    }
  }

  public int a()
  {
    return this.a;
  }

  public List<ADProfile> b()
  {
    return this.c;
  }

  public long c()
  {
    return this.b;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.model.b
 * JD-Core Version:    0.6.2
 */