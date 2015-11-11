package com.intowow.sdk.a;

import com.intowow.sdk.model.ADProfile.i;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;

public class d
{
  private Set<ADProfile.i> a = new HashSet();

  public static d a(JSONArray paramJSONArray)
  {
    if (paramJSONArray == null)
      return null;
    d locald = new d();
    int i = 0;
    while (true)
    {
      if (i >= paramJSONArray.length())
        return locald;
      try
      {
        locald.a.add(ADProfile.i.valueOf(paramJSONArray.optString(i).toUpperCase()));
        label47: i += 1;
      }
      catch (Exception localException)
      {
        break label47;
      }
    }
  }

  public boolean a(ADProfile.i parami)
  {
    return this.a.contains(parami);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.a.d
 * JD-Core Version:    0.6.2
 */