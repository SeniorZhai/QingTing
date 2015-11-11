package org.json;

import java.util.Iterator;

public class XML
{
  public static final Character AMP = new Character('&');
  public static final Character APOS = new Character('\'');
  public static final Character BANG = new Character('!');
  public static final Character EQ = new Character('=');
  public static final Character GT = new Character('>');
  public static final Character LT = new Character('<');
  public static final Character QUEST = new Character('?');
  public static final Character QUOT = new Character('"');
  public static final Character SLASH = new Character('/');

  public static String escape(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    int j = paramString.length();
    if (i < j)
    {
      char c = paramString.charAt(i);
      switch (c)
      {
      default:
        localStringBuffer.append(c);
      case '&':
      case '<':
      case '>':
      case '"':
      }
      while (true)
      {
        i += 1;
        break;
        localStringBuffer.append("&amp;");
        continue;
        localStringBuffer.append("&lt;");
        continue;
        localStringBuffer.append("&gt;");
        continue;
        localStringBuffer.append("&quot;");
      }
    }
    return localStringBuffer.toString();
  }

  private static boolean parse(XMLTokener paramXMLTokener, JSONObject paramJSONObject, String paramString)
    throws JSONException
  {
    boolean bool = true;
    Object localObject = paramXMLTokener.nextToken();
    int i;
    if (localObject == BANG)
    {
      i = paramXMLTokener.next();
      if (i == 45)
        if (paramXMLTokener.next() == '-')
        {
          paramXMLTokener.skipPast("-->");
          bool = false;
        }
    }
    do
    {
      return bool;
      paramXMLTokener.back();
      int j;
      do
      {
        j = 1;
        paramJSONObject = paramXMLTokener.nextMeta();
        if (paramJSONObject != null)
          break;
        throw paramXMLTokener.syntaxError("Missing '>' after '<!'.");
      }
      while (i != 91);
      if ((paramXMLTokener.nextToken().equals("CDATA")) && (paramXMLTokener.next() == '['))
      {
        paramXMLTokener = paramXMLTokener.nextCDATA();
        if (paramXMLTokener.length() > 0)
          paramJSONObject.accumulate("content", paramXMLTokener);
        return false;
      }
      throw paramXMLTokener.syntaxError("Expected 'CDATA['");
      if (paramJSONObject == LT)
        i = j + 1;
      while (true)
      {
        j = i;
        if (i > 0)
          break;
        return false;
        i = j;
        if (paramJSONObject == GT)
          i = j - 1;
      }
      if (localObject == QUEST)
      {
        paramXMLTokener.skipPast("?>");
        return false;
      }
      if (localObject != SLASH)
        break;
      paramJSONObject = paramXMLTokener.nextToken();
      if (paramString == null)
        throw paramXMLTokener.syntaxError("Mismatched close tag" + paramJSONObject);
      if (!paramJSONObject.equals(paramString))
        throw paramXMLTokener.syntaxError("Mismatched " + paramString + " and " + paramJSONObject);
    }
    while (paramXMLTokener.nextToken() == GT);
    throw paramXMLTokener.syntaxError("Misshaped close tag");
    if ((localObject instanceof Character))
      throw paramXMLTokener.syntaxError("Misshaped tag");
    String str = (String)localObject;
    JSONObject localJSONObject = new JSONObject();
    paramString = null;
    while (true)
    {
      localObject = paramString;
      if (paramString == null)
        localObject = paramXMLTokener.nextToken();
      if (!(localObject instanceof String))
        break;
      localObject = (String)localObject;
      paramString = paramXMLTokener.nextToken();
      if (paramString == EQ)
      {
        paramString = paramXMLTokener.nextToken();
        if (!(paramString instanceof String))
          throw paramXMLTokener.syntaxError("Missing value");
        localJSONObject.accumulate((String)localObject, paramString);
        paramString = null;
      }
      else
      {
        localJSONObject.accumulate((String)localObject, "");
      }
    }
    if (localObject == SLASH)
    {
      if (paramXMLTokener.nextToken() != GT)
        throw paramXMLTokener.syntaxError("Misshaped tag");
      paramJSONObject.accumulate(str, localJSONObject);
      return false;
    }
    if (localObject == GT)
    {
      do
        while (true)
        {
          paramString = paramXMLTokener.nextContent();
          if (paramString == null)
          {
            if (str != null)
              throw paramXMLTokener.syntaxError("Unclosed tag " + str);
            return false;
          }
          if (!(paramString instanceof String))
            break;
          paramString = (String)paramString;
          if (paramString.length() > 0)
            localJSONObject.accumulate("content", paramString);
        }
      while ((paramString != LT) || (!parse(paramXMLTokener, localJSONObject, str)));
      if (localJSONObject.length() == 0)
        paramJSONObject.accumulate(str, "");
      while (true)
      {
        return false;
        if ((localJSONObject.length() == 1) && (localJSONObject.opt("content") != null))
          paramJSONObject.accumulate(str, localJSONObject.opt("content"));
        else
          paramJSONObject.accumulate(str, localJSONObject);
      }
    }
    throw paramXMLTokener.syntaxError("Misshaped tag");
  }

  public static JSONObject toJSONObject(String paramString)
    throws JSONException
  {
    JSONObject localJSONObject = new JSONObject();
    paramString = new XMLTokener(paramString);
    while ((paramString.more()) && (paramString.skipPast("<")))
      parse(paramString, localJSONObject, null);
    return localJSONObject;
  }

  public static String toString(Object paramObject)
    throws JSONException
  {
    return toString(paramObject, null);
  }

  public static String toString(Object paramObject, String paramString)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Object localObject1;
    Object localObject2;
    int j;
    int i;
    if ((paramObject instanceof JSONObject))
    {
      if (paramString != null)
      {
        localStringBuffer.append('<');
        localStringBuffer.append(paramString);
        localStringBuffer.append('>');
      }
      paramObject = (JSONObject)paramObject;
      localObject1 = paramObject.keys();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = ((Iterator)localObject1).next().toString();
        Object localObject3 = paramObject.get((String)localObject2);
        if ((localObject3 instanceof String))
          String str = (String)localObject3;
        if (((String)localObject2).equals("content"))
        {
          if ((localObject3 instanceof JSONArray))
          {
            localObject2 = (JSONArray)localObject3;
            j = ((JSONArray)localObject2).length();
            i = 0;
            while (i < j)
            {
              if (i > 0)
                localStringBuffer.append('\n');
              localStringBuffer.append(escape(((JSONArray)localObject2).get(i).toString()));
              i += 1;
            }
          }
          else
          {
            localStringBuffer.append(escape(localObject3.toString()));
          }
        }
        else if ((localObject3 instanceof JSONArray))
        {
          localObject3 = (JSONArray)localObject3;
          j = ((JSONArray)localObject3).length();
          i = 0;
          while (i < j)
          {
            localStringBuffer.append(toString(((JSONArray)localObject3).get(i), (String)localObject2));
            i += 1;
          }
        }
        else if (localObject3.equals(""))
        {
          localStringBuffer.append('<');
          localStringBuffer.append((String)localObject2);
          localStringBuffer.append("/>");
        }
        else
        {
          localStringBuffer.append(toString(localObject3, (String)localObject2));
        }
      }
      if (paramString != null)
      {
        localStringBuffer.append("</");
        localStringBuffer.append(paramString);
        localStringBuffer.append('>');
      }
      return localStringBuffer.toString();
    }
    if ((paramObject instanceof JSONArray))
    {
      localObject1 = (JSONArray)paramObject;
      j = ((JSONArray)localObject1).length();
      i = 0;
      if (i < j)
      {
        localObject2 = ((JSONArray)localObject1).opt(i);
        if (paramString == null);
        for (paramObject = "array"; ; paramObject = paramString)
        {
          localStringBuffer.append(toString(localObject2, paramObject));
          i += 1;
          break;
        }
      }
      return localStringBuffer.toString();
    }
    if (paramObject == null);
    for (paramObject = "null"; paramString == null; paramObject = escape(paramObject.toString()))
      return "\"" + paramObject + "\"";
    if (paramObject.length() == 0)
      return "<" + paramString + "/>";
    return "<" + paramString + ">" + paramObject + "</" + paramString + ">";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.XML
 * JD-Core Version:    0.6.2
 */