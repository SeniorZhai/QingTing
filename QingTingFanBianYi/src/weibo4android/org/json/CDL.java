package weibo4android.org.json;

public class CDL
{
  private static String getValue(JSONTokener paramJSONTokener)
    throws JSONException
  {
    char c;
    do
      c = paramJSONTokener.next();
    while ((c == ' ') || (c == '\t'));
    switch (c)
    {
    default:
      paramJSONTokener.back();
      return paramJSONTokener.nextTo(',');
    case '\000':
      return null;
    case '"':
    case '\'':
      return paramJSONTokener.nextString(c);
    case ',':
    }
    paramJSONTokener.back();
    return "";
  }

  public static JSONArray rowToJSONArray(JSONTokener paramJSONTokener)
    throws JSONException
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = getValue(paramJSONTokener);
    if ((localObject == null) || ((localJSONArray.length() == 0) && (((String)localObject).length() == 0)))
      localObject = null;
    char c;
    do
    {
      do
      {
        do
        {
          return localObject;
          localJSONArray.put(localObject);
          do
          {
            c = paramJSONTokener.next();
            if (c == ',')
              break;
          }
          while (c == ' ');
          localObject = localJSONArray;
        }
        while (c == '\n');
        localObject = localJSONArray;
      }
      while (c == '\r');
      localObject = localJSONArray;
    }
    while (c == 0);
    throw paramJSONTokener.syntaxError("Bad character '" + c + "' (" + c + ").");
  }

  public static JSONObject rowToJSONObject(JSONArray paramJSONArray, JSONTokener paramJSONTokener)
    throws JSONException
  {
    paramJSONTokener = rowToJSONArray(paramJSONTokener);
    if (paramJSONTokener != null)
      return paramJSONTokener.toJSONObject(paramJSONArray);
    return null;
  }

  public static String rowToString(JSONArray paramJSONArray)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i < paramJSONArray.length())
    {
      if (i > 0)
        localStringBuffer.append(',');
      Object localObject = paramJSONArray.opt(i);
      if (localObject != null)
      {
        localObject = localObject.toString();
        if (((String)localObject).indexOf(',') < 0)
          break label112;
        if (((String)localObject).indexOf('"') < 0)
          break label89;
        localStringBuffer.append('\'');
        localStringBuffer.append((String)localObject);
        localStringBuffer.append('\'');
      }
      while (true)
      {
        i += 1;
        break;
        label89: localStringBuffer.append('"');
        localStringBuffer.append((String)localObject);
        localStringBuffer.append('"');
        continue;
        label112: localStringBuffer.append((String)localObject);
      }
    }
    localStringBuffer.append('\n');
    return localStringBuffer.toString();
  }

  public static JSONArray toJSONArray(String paramString)
    throws JSONException
  {
    return toJSONArray(new JSONTokener(paramString));
  }

  public static JSONArray toJSONArray(JSONArray paramJSONArray, String paramString)
    throws JSONException
  {
    return toJSONArray(paramJSONArray, new JSONTokener(paramString));
  }

  public static JSONArray toJSONArray(JSONArray paramJSONArray, JSONTokener paramJSONTokener)
    throws JSONException
  {
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
      return null;
    JSONArray localJSONArray = new JSONArray();
    while (true)
    {
      JSONObject localJSONObject = rowToJSONObject(paramJSONArray, paramJSONTokener);
      if (localJSONObject == null)
      {
        if (localJSONArray.length() == 0)
          break;
        return localJSONArray;
      }
      localJSONArray.put(localJSONObject);
    }
  }

  public static JSONArray toJSONArray(JSONTokener paramJSONTokener)
    throws JSONException
  {
    return toJSONArray(rowToJSONArray(paramJSONTokener), paramJSONTokener);
  }

  public static String toString(JSONArray paramJSONArray)
    throws JSONException
  {
    Object localObject = paramJSONArray.optJSONObject(0);
    if (localObject != null)
    {
      localObject = ((JSONObject)localObject).names();
      if (localObject != null)
        return rowToString((JSONArray)localObject) + toString((JSONArray)localObject, paramJSONArray);
    }
    return null;
  }

  public static String toString(JSONArray paramJSONArray1, JSONArray paramJSONArray2)
    throws JSONException
  {
    if ((paramJSONArray1 == null) || (paramJSONArray1.length() == 0))
      return null;
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramJSONArray2.length())
    {
      JSONObject localJSONObject = paramJSONArray2.optJSONObject(i);
      if (localJSONObject != null)
        localStringBuffer.append(rowToString(localJSONObject.toJSONArray(paramJSONArray1)));
      i += 1;
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.org.json.CDL
 * JD-Core Version:    0.6.2
 */