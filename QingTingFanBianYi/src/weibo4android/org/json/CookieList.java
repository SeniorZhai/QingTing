package weibo4android.org.json;

import java.util.Iterator;

public class CookieList
{
  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    paramString = new JSONTokener(paramString);
    while (paramString.more())
    {
      String str = Cookie.unescape(paramString.nextTo('='));
      paramString.next('=');
      localJSONObject.put(str, Cookie.unescape(paramString.nextTo(';')));
      paramString.next();
    }
    return localJSONObject;
  }

  public static String toString(JSONObject paramJSONObject)
    throws JSONException
  {
    int i = 0;
    Iterator localIterator = paramJSONObject.keys();
    StringBuffer localStringBuffer = new StringBuffer();
    while (localIterator.hasNext())
    {
      String str = localIterator.next().toString();
      if (!paramJSONObject.isNull(str))
      {
        if (i != 0)
          localStringBuffer.append(';');
        localStringBuffer.append(Cookie.escape(str));
        localStringBuffer.append("=");
        localStringBuffer.append(Cookie.escape(paramJSONObject.getString(str)));
        i = 1;
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.org.json.CookieList
 * JD-Core Version:    0.6.2
 */