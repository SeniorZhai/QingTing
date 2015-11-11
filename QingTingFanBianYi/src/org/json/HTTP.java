package org.json;

import java.util.Iterator;

public class HTTP
{
  public static final String CRLF = "\r\n";

  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    paramString = new HTTPTokener(paramString);
    String str = paramString.nextToken();
    if (str.toUpperCase().startsWith("HTTP"))
    {
      localJSONObject.put("HTTP-Version", str);
      localJSONObject.put("Status-Code", paramString.nextToken());
      localJSONObject.put("Reason-Phrase", paramString.nextTo('\000'));
      paramString.next();
    }
    while (paramString.more())
    {
      str = paramString.nextTo(':');
      paramString.next(':');
      localJSONObject.put(str, paramString.nextTo('\000'));
      paramString.next();
      continue;
      localJSONObject.put("Method", str);
      localJSONObject.put("Request-URI", paramString.nextToken());
      localJSONObject.put("HTTP-Version", paramString.nextToken());
    }
    return localJSONObject;
  }

  public static String toString(JSONObject paramJSONObject)
    throws JSONException
  {
    Iterator localIterator = paramJSONObject.keys();
    StringBuffer localStringBuffer = new StringBuffer();
    if ((paramJSONObject.has("Status-Code")) && (paramJSONObject.has("Reason-Phrase")))
    {
      localStringBuffer.append(paramJSONObject.getString("HTTP-Version"));
      localStringBuffer.append(' ');
      localStringBuffer.append(paramJSONObject.getString("Status-Code"));
      localStringBuffer.append(' ');
      localStringBuffer.append(paramJSONObject.getString("Reason-Phrase"));
    }
    while (true)
    {
      localStringBuffer.append("\r\n");
      while (localIterator.hasNext())
      {
        String str = localIterator.next().toString();
        if ((!str.equals("HTTP-Version")) && (!str.equals("Status-Code")) && (!str.equals("Reason-Phrase")) && (!str.equals("Method")) && (!str.equals("Request-URI")) && (!paramJSONObject.isNull(str)))
        {
          localStringBuffer.append(str);
          localStringBuffer.append(": ");
          localStringBuffer.append(paramJSONObject.getString(str));
          localStringBuffer.append("\r\n");
        }
      }
      if ((!paramJSONObject.has("Method")) || (!paramJSONObject.has("Request-URI")))
        break;
      localStringBuffer.append(paramJSONObject.getString("Method"));
      localStringBuffer.append(' ');
      localStringBuffer.append('"');
      localStringBuffer.append(paramJSONObject.getString("Request-URI"));
      localStringBuffer.append('"');
      localStringBuffer.append(' ');
      localStringBuffer.append(paramJSONObject.getString("HTTP-Version"));
    }
    throw new JSONException("Not enough material for an HTTP header.");
    localStringBuffer.append("\r\n");
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.HTTP
 * JD-Core Version:    0.6.2
 */