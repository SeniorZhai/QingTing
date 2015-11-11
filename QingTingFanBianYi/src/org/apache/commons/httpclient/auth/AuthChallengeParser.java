package org.apache.commons.httpclient.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.util.ParameterParser;

public final class AuthChallengeParser
{
  public static Map extractParams(String paramString)
    throws MalformedChallengeException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Challenge may not be null");
    int i = paramString.indexOf(' ');
    if (i == -1)
      throw new MalformedChallengeException("Invalid challenge: " + paramString);
    HashMap localHashMap = new HashMap();
    paramString = new ParameterParser().parse(paramString.substring(i + 1, paramString.length()), ',');
    i = 0;
    while (true)
    {
      if (i >= paramString.size())
        return localHashMap;
      NameValuePair localNameValuePair = (NameValuePair)paramString.get(i);
      localHashMap.put(localNameValuePair.getName().toLowerCase(), localNameValuePair.getValue());
      i += 1;
    }
  }

  public static String extractScheme(String paramString)
    throws MalformedChallengeException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Challenge may not be null");
    int i = paramString.indexOf(' ');
    if (i == -1);
    for (String str = paramString; str.equals(""); str = paramString.substring(0, i))
      throw new MalformedChallengeException("Invalid challenge: " + paramString);
    return str.toLowerCase();
  }

  public static Map parseChallenges(Header[] paramArrayOfHeader)
    throws MalformedChallengeException
  {
    if (paramArrayOfHeader == null)
      throw new IllegalArgumentException("Array of challenges may not be null");
    HashMap localHashMap = new HashMap(paramArrayOfHeader.length);
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfHeader.length)
        return localHashMap;
      String str = paramArrayOfHeader[i].getValue();
      localHashMap.put(extractScheme(str), str);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthChallengeParser
 * JD-Core Version:    0.6.2
 */