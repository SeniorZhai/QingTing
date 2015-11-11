package weibo4android.org.json;

import java.util.Iterator;

public class JSONML
{
  private static Object parse(XMLTokener paramXMLTokener, boolean paramBoolean, JSONArray paramJSONArray)
    throws JSONException
  {
    while (true)
    {
      Object localObject2 = paramXMLTokener.nextContent();
      Object localObject1;
      if (localObject2 == XML.LT)
      {
        localObject1 = paramXMLTokener.nextToken();
        if ((localObject1 instanceof Character))
        {
          if (localObject1 == XML.SLASH)
          {
            paramJSONArray = paramXMLTokener.nextToken();
            if (!(paramJSONArray instanceof String))
              throw new JSONException("Expected a closing name instead of '" + paramJSONArray + "'.");
            if (paramXMLTokener.nextToken() != XML.GT)
              throw paramXMLTokener.syntaxError("Misshaped close tag");
          }
          else
          {
            if (localObject1 == XML.BANG)
            {
              int i = paramXMLTokener.next();
              if (i == 45)
              {
                if (paramXMLTokener.next() == '-')
                  paramXMLTokener.skipPast("-->");
                paramXMLTokener.back();
                continue;
              }
              if (i == 91)
              {
                if ((paramXMLTokener.nextToken().equals("CDATA")) && (paramXMLTokener.next() == '['))
                {
                  if (paramJSONArray == null)
                    continue;
                  paramJSONArray.put(paramXMLTokener.nextCDATA());
                  continue;
                }
                throw paramXMLTokener.syntaxError("Expected 'CDATA['");
              }
              int j = 1;
              label191: localObject1 = paramXMLTokener.nextMeta();
              if (localObject1 == null)
                throw paramXMLTokener.syntaxError("Missing '>' after '<!'.");
              if (localObject1 == XML.LT)
                i = j + 1;
              while (true)
              {
                j = i;
                if (i > 0)
                  break label191;
                break;
                i = j;
                if (localObject1 == XML.GT)
                  i = j - 1;
              }
            }
            if (localObject1 == XML.QUEST)
            {
              paramXMLTokener.skipPast("?>");
              continue;
            }
            throw paramXMLTokener.syntaxError("Misshaped tag");
          }
        }
        else
        {
          if (!(localObject1 instanceof String))
            throw paramXMLTokener.syntaxError("Bad tagName '" + localObject1 + "'.");
          String str = (String)localObject1;
          JSONArray localJSONArray = new JSONArray();
          JSONObject localJSONObject = new JSONObject();
          if (paramBoolean)
          {
            localJSONArray.put(str);
            if (paramJSONArray != null)
              paramJSONArray.put(localJSONArray);
            localObject1 = null;
          }
          while (true)
          {
            localObject2 = localObject1;
            if (localObject1 == null)
              localObject2 = paramXMLTokener.nextToken();
            if (localObject2 == null)
            {
              throw paramXMLTokener.syntaxError("Misshaped tag");
              localJSONObject.put("tagName", str);
              if (paramJSONArray == null)
                break;
              paramJSONArray.put(localJSONObject);
              break;
            }
            if (!(localObject2 instanceof String))
            {
              if ((paramBoolean) && (localJSONObject.length() > 0))
                localJSONArray.put(localJSONObject);
              if (localObject2 != XML.SLASH)
                break label575;
              if (paramXMLTokener.nextToken() == XML.GT)
                break label559;
              throw paramXMLTokener.syntaxError("Misshaped tag");
            }
            localObject2 = (String)localObject2;
            if ((!paramBoolean) && ((localObject2 == "tagName") || (localObject2 == "childNode")))
              throw paramXMLTokener.syntaxError("Reserved attribute.");
            localObject1 = paramXMLTokener.nextToken();
            if (localObject1 == XML.EQ)
            {
              localObject1 = paramXMLTokener.nextToken();
              if (!(localObject1 instanceof String))
                throw paramXMLTokener.syntaxError("Missing value");
              localJSONObject.accumulate((String)localObject2, JSONObject.stringToValue((String)localObject1));
              localObject1 = null;
            }
            else
            {
              localJSONObject.accumulate((String)localObject2, "");
            }
          }
          label559: if (paramJSONArray == null)
          {
            if (paramBoolean)
            {
              paramJSONArray = localJSONArray;
              return paramJSONArray;
            }
            return localJSONObject;
            label575: if (localObject2 != XML.GT)
              throw paramXMLTokener.syntaxError("Misshaped tag");
            localObject1 = (String)parse(paramXMLTokener, paramBoolean, localJSONArray);
            if (localObject1 != null)
            {
              if (!((String)localObject1).equals(str))
                throw paramXMLTokener.syntaxError("Mismatched '" + str + "' and '" + (String)localObject1 + "'");
              if ((!paramBoolean) && (localJSONArray.length() > 0))
                localJSONObject.put("childNodes", localJSONArray);
              if (paramJSONArray == null)
              {
                if (paramBoolean)
                  return localJSONArray;
                return localJSONObject;
              }
            }
          }
        }
      }
      else if (paramJSONArray != null)
      {
        localObject1 = localObject2;
        if ((localObject2 instanceof String))
          localObject1 = JSONObject.stringToValue((String)localObject2);
        paramJSONArray.put(localObject1);
      }
    }
  }

  public static JSONArray toJSONArray(String paramString)
    throws JSONException
  {
    return toJSONArray(new XMLTokener(paramString));
  }

  public static JSONArray toJSONArray(XMLTokener paramXMLTokener)
    throws JSONException
  {
    return (JSONArray)parse(paramXMLTokener, true, null);
  }

  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    return toJSONObject(new XMLTokener(paramString));
  }

  public static JSONObject toJSONObject(XMLTokener paramXMLTokener)
    throws JSONException
  {
    return (JSONObject)parse(paramXMLTokener, false, null);
  }

  public static String toString(JSONArray paramJSONArray)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str1 = paramJSONArray.getString(0);
    XML.noSpace(str1);
    str1 = XML.escape(str1);
    localStringBuffer.append('<');
    localStringBuffer.append(str1);
    Object localObject = paramJSONArray.opt(1);
    if ((localObject instanceof JSONObject))
    {
      localObject = (JSONObject)localObject;
      Iterator localIterator = ((JSONObject)localObject).keys();
      while (localIterator.hasNext())
      {
        String str2 = localIterator.next().toString();
        XML.noSpace(str2);
        String str3 = ((JSONObject)localObject).optString(str2);
        if (str3 != null)
        {
          localStringBuffer.append(' ');
          localStringBuffer.append(XML.escape(str2));
          localStringBuffer.append('=');
          localStringBuffer.append('"');
          localStringBuffer.append(XML.escape(str3));
          localStringBuffer.append('"');
        }
      }
    }
    for (int i = 1; ; i = 2)
    {
      int k = paramJSONArray.length();
      if (i >= k)
      {
        localStringBuffer.append('/');
        localStringBuffer.append('>');
        return localStringBuffer.toString();
      }
      localStringBuffer.append('>');
      label193: localObject = paramJSONArray.get(i);
      int j = i + 1;
      if (localObject != null)
      {
        if (!(localObject instanceof String))
          break label270;
        localStringBuffer.append(XML.escape(localObject.toString()));
      }
      while (true)
      {
        i = j;
        if (j < k)
          break label193;
        localStringBuffer.append('<');
        localStringBuffer.append('/');
        localStringBuffer.append(str1);
        localStringBuffer.append('>');
        break;
        label270: if ((localObject instanceof JSONObject))
          localStringBuffer.append(toString((JSONObject)localObject));
        else if ((localObject instanceof JSONArray))
          localStringBuffer.append(toString((JSONArray)localObject));
      }
    }
  }

  public static String toString(JSONObject paramJSONObject)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    String str1 = paramJSONObject.optString("tagName");
    if (str1 == null)
      return XML.escape(paramJSONObject.toString());
    XML.noSpace(str1);
    str1 = XML.escape(str1);
    localStringBuffer.append('<');
    localStringBuffer.append(str1);
    Object localObject = paramJSONObject.keys();
    while (((Iterator)localObject).hasNext())
    {
      String str2 = ((Iterator)localObject).next().toString();
      if ((!str2.equals("tagName")) && (!str2.equals("childNodes")))
      {
        XML.noSpace(str2);
        String str3 = paramJSONObject.optString(str2);
        if (str3 != null)
        {
          localStringBuffer.append(' ');
          localStringBuffer.append(XML.escape(str2));
          localStringBuffer.append('=');
          localStringBuffer.append('"');
          localStringBuffer.append(XML.escape(str3));
          localStringBuffer.append('"');
        }
      }
    }
    paramJSONObject = paramJSONObject.optJSONArray("childNodes");
    if (paramJSONObject == null)
    {
      localStringBuffer.append('/');
      localStringBuffer.append('>');
    }
    while (true)
    {
      return localStringBuffer.toString();
      localStringBuffer.append('>');
      int j = paramJSONObject.length();
      int i = 0;
      if (i < j)
      {
        localObject = paramJSONObject.get(i);
        if (localObject != null)
        {
          if (!(localObject instanceof String))
            break label255;
          localStringBuffer.append(XML.escape(localObject.toString()));
        }
        while (true)
        {
          i += 1;
          break;
          label255: if ((localObject instanceof JSONObject))
            localStringBuffer.append(toString((JSONObject)localObject));
          else if ((localObject instanceof JSONArray))
            localStringBuffer.append(toString((JSONArray)localObject));
        }
      }
      localStringBuffer.append('<');
      localStringBuffer.append('/');
      localStringBuffer.append(str1);
      localStringBuffer.append('>');
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.org.json.JSONML
 * JD-Core Version:    0.6.2
 */