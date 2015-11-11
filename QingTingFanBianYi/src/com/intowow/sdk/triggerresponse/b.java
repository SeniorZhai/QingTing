package com.intowow.sdk.triggerresponse;

import com.intowow.sdk.h.j;
import com.intowow.sdk.j.l;
import org.json.JSONObject;

public class b
{
  public static TriggerResponse a(boolean paramBoolean1, j paramj, boolean paramBoolean2)
  {
    paramj = j.a(paramj);
    return new CommonTriggerHandler(paramBoolean1, paramj, paramj, paramBoolean2);
  }

  public static TriggerResponse a(boolean paramBoolean, JSONObject paramJSONObject)
  {
    try
    {
      Object localObject = a(paramJSONObject);
      switch (a()[localObject.ordinal()])
      {
      case 2:
      case 1:
      case 3:
      case 4:
      }
      while (true)
      {
        localObject = new CommonTriggerHandler();
        while (!((TriggerResponse)localObject).a(paramBoolean, paramJSONObject))
        {
          return null;
          localObject = new InstallHandler();
          continue;
          localObject = new RedirectHandler();
          continue;
          localObject = new ThirdPartyTrackingHanlder();
          continue;
          localObject = new CustomHandler();
        }
        return localObject;
      }
    }
    catch (Exception paramJSONObject)
    {
    }
    return null;
  }

  private static c a(JSONObject paramJSONObject)
  {
    paramJSONObject = paramJSONObject.getString("action");
    if (l.a(paramJSONObject))
      return c.e;
    return c.valueOf(paramJSONObject.toUpperCase());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.triggerresponse.b
 * JD-Core Version:    0.6.2
 */