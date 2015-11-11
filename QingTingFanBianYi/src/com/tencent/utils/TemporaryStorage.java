package com.tencent.utils;

import java.util.HashMap;

public class TemporaryStorage
{
  private static HashMap<String, Object> a = new HashMap();

  public static Object get(String paramString)
  {
    return a.remove(paramString);
  }

  public static Object set(String paramString, Object paramObject)
  {
    return a.put(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.utils.TemporaryStorage
 * JD-Core Version:    0.6.2
 */