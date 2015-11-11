package org.json;

public class Cookie
{
  public static String escape(String paramString)
  {
    paramString = paramString.trim();
    StringBuffer localStringBuffer = new StringBuffer();
    int j = paramString.length();
    int i = 0;
    if (i < j)
    {
      char c = paramString.charAt(i);
      if ((c < ' ') || (c == '+') || (c == '%') || (c == '=') || (c == ';'))
      {
        localStringBuffer.append('%');
        localStringBuffer.append(Character.forDigit((char)(c >>> '\004' & 0xF), 16));
        localStringBuffer.append(Character.forDigit((char)(c & 0xF), 16));
      }
      while (true)
      {
        i += 1;
        break;
        localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString();
  }

  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    JSONTokener localJSONTokener = new JSONTokener(paramString);
    localJSONObject.put("name", localJSONTokener.nextTo('='));
    localJSONTokener.next('=');
    localJSONObject.put("value", localJSONTokener.nextTo(';'));
    localJSONTokener.next();
    if (localJSONTokener.more())
    {
      String str = unescape(localJSONTokener.nextTo("=;"));
      if (localJSONTokener.next() != '=')
        if (str.equals("secure"))
          paramString = Boolean.TRUE;
      while (true)
      {
        localJSONObject.put(str, paramString);
        break;
        throw localJSONTokener.syntaxError("Missing '=' in cookie parameter.");
        paramString = unescape(localJSONTokener.nextTo(';'));
        localJSONTokener.next();
      }
    }
    return localJSONObject;
  }

  public static String toString(JSONObject paramJSONObject)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(escape(paramJSONObject.getString("name")));
    localStringBuffer.append("=");
    localStringBuffer.append(escape(paramJSONObject.getString("value")));
    if (paramJSONObject.has("expires"))
    {
      localStringBuffer.append(";expires=");
      localStringBuffer.append(paramJSONObject.getString("expires"));
    }
    if (paramJSONObject.has("domain"))
    {
      localStringBuffer.append(";domain=");
      localStringBuffer.append(escape(paramJSONObject.getString("domain")));
    }
    if (paramJSONObject.has("path"))
    {
      localStringBuffer.append(";path=");
      localStringBuffer.append(escape(paramJSONObject.getString("path")));
    }
    if (paramJSONObject.optBoolean("secure"))
      localStringBuffer.append(";secure");
    return localStringBuffer.toString();
  }

  public static String unescape(String paramString)
  {
    int k = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < k)
    {
      char c2 = paramString.charAt(i);
      char c1;
      int j;
      if (c2 == '+')
      {
        c1 = ' ';
        j = i;
      }
      while (true)
      {
        localStringBuffer.append(c1);
        i = j + 1;
        break;
        c1 = c2;
        j = i;
        if (c2 == '%')
        {
          c1 = c2;
          j = i;
          if (i + 2 < k)
          {
            int m = JSONTokener.dehexchar(paramString.charAt(i + 1));
            int n = JSONTokener.dehexchar(paramString.charAt(i + 2));
            c1 = c2;
            j = i;
            if (m >= 0)
            {
              c1 = c2;
              j = i;
              if (n >= 0)
              {
                c1 = (char)(m * 16 + n);
                j = i + 2;
              }
            }
          }
        }
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.Cookie
 * JD-Core Version:    0.6.2
 */