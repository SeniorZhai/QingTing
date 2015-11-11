package com.intowow.sdk.j;

import java.security.MessageDigest;
import org.json.JSONArray;
import org.json.JSONObject;

public class l
{
  public static boolean a(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0) || (paramString.equals("null"));
  }

  public static String[] a(JSONObject paramJSONObject, String paramString)
  {
    int i = 0;
    JSONArray localJSONArray = paramJSONObject.optJSONArray(paramString);
    if (localJSONArray == null)
    {
      paramJSONObject = paramJSONObject.getString(paramString);
      if (!a(paramJSONObject))
        return new String[] { paramJSONObject };
      return new String[0];
    }
    paramJSONObject = new String[localJSONArray.length()];
    while (true)
    {
      if (i >= localJSONArray.length())
        return paramJSONObject;
      paramJSONObject[i] = localJSONArray.optString(i);
      i += 1;
    }
  }

  public static String b(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    if (i != -1)
      return paramString.substring(i + 1);
    return "";
  }

  public static String c(String paramString)
  {
    while (true)
    {
      StringBuilder localStringBuilder;
      int i;
      try
      {
        paramString = MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8"));
        localStringBuilder = new StringBuilder(paramString.length * 2);
        int j = paramString.length;
        i = 0;
        if (i >= j)
          return localStringBuilder.toString();
      }
      catch (Exception paramString)
      {
        return "";
      }
      int k = paramString[i];
      if ((k & 0xFF) < 16)
        localStringBuilder.append("0");
      localStringBuilder.append(Integer.toHexString(k & 0xFF));
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.l
 * JD-Core Version:    0.6.2
 */