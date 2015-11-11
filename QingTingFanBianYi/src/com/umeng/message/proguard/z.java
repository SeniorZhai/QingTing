package com.umeng.message.proguard;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class z
{
  public static String a(Map<String, Object> paramMap, String paramString)
  {
    if ((paramMap == null) || (paramMap.isEmpty()))
      return paramString;
    StringBuilder localStringBuilder1 = new StringBuilder(paramString);
    Object localObject = paramMap.keySet();
    if (!paramString.endsWith("?"))
      localStringBuilder1.append("?");
    localObject = ((Set)localObject).iterator();
    if (((Iterator)localObject).hasNext())
    {
      paramString = (String)((Iterator)localObject).next();
      StringBuilder localStringBuilder2 = new StringBuilder().append(URLEncoder.encode(paramString)).append("=");
      if (paramMap.get(paramString) == null);
      for (paramString = ""; ; paramString = paramMap.get(paramString).toString())
      {
        localStringBuilder1.append(URLEncoder.encode(paramString) + "&");
        break;
      }
    }
    localStringBuilder1.deleteCharAt(localStringBuilder1.length() - 1);
    return localStringBuilder1.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.z
 * JD-Core Version:    0.6.2
 */