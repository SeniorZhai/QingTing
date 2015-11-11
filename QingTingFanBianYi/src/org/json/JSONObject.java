package org.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JSONObject
{
  public static final Object NULL = new Null(null);
  private HashMap myHashMap;

  public JSONObject()
  {
    this.myHashMap = new HashMap();
  }

  public JSONObject(Object paramObject)
  {
    this();
    Method[] arrayOfMethod = paramObject.getClass().getMethods();
    while (true)
    {
      Method localMethod;
      if (i < arrayOfMethod.length)
        localMethod = arrayOfMethod[i];
      try
      {
        Object localObject = localMethod.getName();
        String str = "";
        if (((String)localObject).startsWith("get"))
        {
          str = ((String)localObject).substring(3);
          if ((str.length() <= 0) || (!Character.isUpperCase(str.charAt(0))) || (localMethod.getParameterTypes().length != 0))
            break label178;
          if (str.length() != 1)
            break label129;
          localObject = str.toLowerCase();
        }
        while (true)
        {
          put((String)localObject, localMethod.invoke(paramObject, null));
          break label178;
          if (!((String)localObject).startsWith("is"))
            break;
          str = ((String)localObject).substring(2);
          break;
          label129: localObject = str;
          if (!Character.isUpperCase(str.charAt(1)))
            localObject = str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return;
      }
      catch (Exception localException)
      {
        label178: i += 1;
      }
    }
  }

  public JSONObject(Object paramObject, String[] paramArrayOfString)
  {
    this();
    Class localClass = paramObject.getClass();
    int i = 0;
    while (true)
    {
      String str;
      if (i < paramArrayOfString.length)
        str = paramArrayOfString[i];
      try
      {
        put(str, localClass.getField(str).get(paramObject));
        label42: i += 1;
        continue;
        return;
      }
      catch (Exception localException)
      {
        break label42;
      }
    }
  }

  public JSONObject(String paramString)
    throws JSONException
  {
    this(new JSONTokener(paramString));
  }

  public JSONObject(Map paramMap)
  {
    if (paramMap == null);
    for (paramMap = new HashMap(); ; paramMap = new HashMap(paramMap))
    {
      this.myHashMap = paramMap;
      return;
    }
  }

  public JSONObject(JSONObject paramJSONObject, String[] paramArrayOfString)
    throws JSONException
  {
    this();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      putOpt(paramArrayOfString[i], paramJSONObject.opt(paramArrayOfString[i]));
      i += 1;
    }
  }

  public JSONObject(JSONTokener paramJSONTokener)
    throws JSONException
  {
    this();
    if (paramJSONTokener.nextClean() != '{')
      throw paramJSONTokener.syntaxError("A JSONObject text must begin with '{'");
    do
    {
      paramJSONTokener.back();
      String str;
      int i;
      switch (paramJSONTokener.nextClean())
      {
      default:
        paramJSONTokener.back();
        str = paramJSONTokener.nextValue().toString();
        i = paramJSONTokener.nextClean();
        if (i == 61)
          if (paramJSONTokener.next() != '>')
            paramJSONTokener.back();
      case '\000':
        while (i == 58)
        {
          put(str, paramJSONTokener.nextValue());
          switch (paramJSONTokener.nextClean())
          {
          default:
            throw paramJSONTokener.syntaxError("Expected a ',' or '}'");
            throw paramJSONTokener.syntaxError("A JSONObject text must end with '}'");
          case ',':
          case ';':
          case '}':
          }
        }
        throw paramJSONTokener.syntaxError("Expected a ':' after a key");
      case '}':
      }
    }
    while (paramJSONTokener.nextClean() != '}');
  }

  public static String doubleToString(double paramDouble)
  {
    Object localObject;
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble)))
      localObject = "null";
    String str;
    do
    {
      do
      {
        do
        {
          do
          {
            return localObject;
            str = Double.toString(paramDouble);
            localObject = str;
          }
          while (str.indexOf('.') <= 0);
          localObject = str;
        }
        while (str.indexOf('e') >= 0);
        localObject = str;
      }
      while (str.indexOf('E') >= 0);
      while (str.endsWith("0"))
        str = str.substring(0, str.length() - 1);
      localObject = str;
    }
    while (!str.endsWith("."));
    return str.substring(0, str.length() - 1);
  }

  public static String[] getNames(Object paramObject)
  {
    String[] arrayOfString = null;
    if (paramObject == null)
      paramObject = arrayOfString;
    Field[] arrayOfField;
    int j;
    do
    {
      return paramObject;
      arrayOfField = paramObject.getClass().getFields();
      j = arrayOfField.length;
      paramObject = arrayOfString;
    }
    while (j == 0);
    arrayOfString = new String[j];
    int i = 0;
    while (true)
    {
      paramObject = arrayOfString;
      if (i >= j)
        break;
      arrayOfString[i] = arrayOfField[i].getName();
      i += 1;
    }
  }

  public static String[] getNames(JSONObject paramJSONObject)
  {
    int i = paramJSONObject.length();
    if (i == 0)
      return null;
    paramJSONObject = paramJSONObject.keys();
    String[] arrayOfString = new String[i];
    i = 0;
    while (paramJSONObject.hasNext())
    {
      arrayOfString[i] = ((String)paramJSONObject.next());
      i += 1;
    }
    return arrayOfString;
  }

  public static String numberToString(Number paramNumber)
    throws JSONException
  {
    if (paramNumber == null)
      throw new JSONException("Null pointer");
    testValidity(paramNumber);
    String str = paramNumber.toString();
    paramNumber = str;
    if (str.indexOf('.') > 0)
    {
      paramNumber = str;
      if (str.indexOf('e') < 0)
      {
        paramNumber = str;
        if (str.indexOf('E') < 0)
        {
          while (str.endsWith("0"))
            str = str.substring(0, str.length() - 1);
          paramNumber = str;
          if (str.endsWith("."))
            paramNumber = str.substring(0, str.length() - 1);
        }
      }
    }
    return paramNumber;
  }

  public static String quote(String paramString)
  {
    int j = 0;
    if ((paramString == null) || (paramString.length() == 0))
      return "\"\"";
    int m = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(m + 4);
    localStringBuffer.append('"');
    int k = 0;
    if (j < m)
    {
      int i = paramString.charAt(j);
      switch (i)
      {
      default:
        if ((i < 32) || ((i >= 128) && (i < 160)) || ((i >= 8192) && (i < 8448)))
        {
          String str = "000" + Integer.toHexString(i);
          localStringBuffer.append("\\u" + str.substring(str.length() - 4));
        }
        break;
      case 34:
      case 92:
      case 47:
      case 8:
      case 9:
      case 10:
      case 12:
      case 13:
      }
      while (true)
      {
        j += 1;
        k = i;
        break;
        localStringBuffer.append('\\');
        localStringBuffer.append(i);
        continue;
        if (k == 60)
          localStringBuffer.append('\\');
        localStringBuffer.append(i);
        continue;
        localStringBuffer.append("\\b");
        continue;
        localStringBuffer.append("\\t");
        continue;
        localStringBuffer.append("\\n");
        continue;
        localStringBuffer.append("\\f");
        continue;
        localStringBuffer.append("\\r");
        continue;
        localStringBuffer.append(i);
      }
    }
    localStringBuffer.append('"');
    return localStringBuffer.toString();
  }

  static void testValidity(Object paramObject)
    throws JSONException
  {
    if (paramObject != null)
      if ((paramObject instanceof Double))
      {
        if ((((Double)paramObject).isInfinite()) || (((Double)paramObject).isNaN()))
          throw new JSONException("JSON does not allow non-finite numbers.");
      }
      else if (((paramObject instanceof Float)) && ((((Float)paramObject).isInfinite()) || (((Float)paramObject).isNaN())))
        throw new JSONException("JSON does not allow non-finite numbers.");
  }

  static String valueToString(Object paramObject)
    throws JSONException
  {
    if ((paramObject == null) || (paramObject.equals(null)))
      return "null";
    if ((paramObject instanceof JSONString))
    {
      try
      {
        paramObject = ((JSONString)paramObject).toJSONString();
        if ((paramObject instanceof String))
          return (String)paramObject;
      }
      catch (Exception paramObject)
      {
        throw new JSONException(paramObject);
      }
      throw new JSONException("Bad value from toJSONString: " + paramObject);
    }
    if ((paramObject instanceof Number))
      return numberToString((Number)paramObject);
    if (((paramObject instanceof Boolean)) || ((paramObject instanceof JSONObject)) || ((paramObject instanceof JSONArray)))
      return paramObject.toString();
    if ((paramObject instanceof Map))
      return new JSONObject((Map)paramObject).toString();
    if ((paramObject instanceof Collection))
      return new JSONArray((Collection)paramObject).toString();
    if (paramObject.getClass().isArray())
      return new JSONArray(paramObject).toString();
    return quote(paramObject.toString());
  }

  static String valueToString(Object paramObject, int paramInt1, int paramInt2)
    throws JSONException
  {
    if ((paramObject == null) || (paramObject.equals(null)))
      return "null";
    try
    {
      if ((paramObject instanceof JSONString))
      {
        String str = ((JSONString)paramObject).toJSONString();
        if ((str instanceof String))
        {
          str = (String)str;
          return str;
        }
      }
    }
    catch (Exception localException)
    {
      if ((paramObject instanceof Number))
        return numberToString((Number)paramObject);
      if ((paramObject instanceof Boolean))
        return paramObject.toString();
      if ((paramObject instanceof JSONObject))
        return ((JSONObject)paramObject).toString(paramInt1, paramInt2);
      if ((paramObject instanceof JSONArray))
        return ((JSONArray)paramObject).toString(paramInt1, paramInt2);
      if ((paramObject instanceof Map))
        return new JSONObject((Map)paramObject).toString(paramInt1, paramInt2);
      if ((paramObject instanceof Collection))
        return new JSONArray((Collection)paramObject).toString(paramInt1, paramInt2);
      if (paramObject.getClass().isArray())
        return new JSONArray(paramObject).toString(paramInt1, paramInt2);
    }
    return quote(paramObject.toString());
  }

  public JSONObject accumulate(String paramString, Object paramObject)
    throws JSONException
  {
    testValidity(paramObject);
    Object localObject = opt(paramString);
    if (localObject == null)
    {
      localObject = paramObject;
      if ((paramObject instanceof JSONArray))
        localObject = new JSONArray().put(paramObject);
      put(paramString, localObject);
      return this;
    }
    if ((localObject instanceof JSONArray))
    {
      ((JSONArray)localObject).put(paramObject);
      return this;
    }
    put(paramString, new JSONArray().put(localObject).put(paramObject));
    return this;
  }

  public JSONObject append(String paramString, Object paramObject)
    throws JSONException
  {
    testValidity(paramObject);
    Object localObject = opt(paramString);
    if (localObject == null)
    {
      put(paramString, new JSONArray().put(paramObject));
      return this;
    }
    if ((localObject instanceof JSONArray))
    {
      put(paramString, ((JSONArray)localObject).put(paramObject));
      return this;
    }
    throw new JSONException("JSONObject[" + paramString + "] is not a JSONArray.");
  }

  public Object get(String paramString)
    throws JSONException
  {
    Object localObject = opt(paramString);
    if (localObject == null)
      throw new JSONException("JSONObject[" + quote(paramString) + "] not found.");
    return localObject;
  }

  public boolean getBoolean(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject.equals(Boolean.FALSE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("false"))))
      return false;
    if ((localObject.equals(Boolean.TRUE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("true"))))
      return true;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a Boolean.");
  }

  public double getDouble(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    try
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).doubleValue();
      double d = Double.valueOf((String)localObject).doubleValue();
      return d;
    }
    catch (Exception localException)
    {
    }
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a number.");
  }

  public int getInt(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject instanceof Number))
      return ((Number)localObject).intValue();
    return (int)getDouble(paramString);
  }

  public JSONArray getJSONArray(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject instanceof JSONArray))
      return (JSONArray)localObject;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a JSONArray.");
  }

  public JSONObject getJSONObject(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject instanceof JSONObject))
      return (JSONObject)localObject;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a JSONObject.");
  }

  public long getLong(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if ((localObject instanceof Number))
      return ((Number)localObject).longValue();
    return ()getDouble(paramString);
  }

  public String getString(String paramString)
    throws JSONException
  {
    return get(paramString).toString();
  }

  public boolean has(String paramString)
  {
    return this.myHashMap.containsKey(paramString);
  }

  public boolean isNull(String paramString)
  {
    return NULL.equals(opt(paramString));
  }

  public Iterator keys()
  {
    return this.myHashMap.keySet().iterator();
  }

  public int length()
  {
    return this.myHashMap.size();
  }

  public JSONArray names()
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = keys();
    while (((Iterator)localObject).hasNext())
      localJSONArray.put(((Iterator)localObject).next());
    localObject = localJSONArray;
    if (localJSONArray.length() == 0)
      localObject = null;
    return localObject;
  }

  public Object opt(String paramString)
  {
    if (paramString == null)
      return null;
    return this.myHashMap.get(paramString);
  }

  public boolean optBoolean(String paramString)
  {
    return optBoolean(paramString, false);
  }

  public boolean optBoolean(String paramString, boolean paramBoolean)
  {
    try
    {
      boolean bool = getBoolean(paramString);
      return bool;
    }
    catch (Exception paramString)
    {
    }
    return paramBoolean;
  }

  public double optDouble(String paramString)
  {
    return optDouble(paramString, (0.0D / 0.0D));
  }

  public double optDouble(String paramString, double paramDouble)
  {
    try
    {
      paramString = opt(paramString);
      if ((paramString instanceof Number))
        return ((Number)paramString).doubleValue();
      double d = new Double((String)paramString).doubleValue();
      return d;
    }
    catch (Exception paramString)
    {
    }
    return paramDouble;
  }

  public int optInt(String paramString)
  {
    return optInt(paramString, 0);
  }

  public int optInt(String paramString, int paramInt)
  {
    try
    {
      int i = getInt(paramString);
      return i;
    }
    catch (Exception paramString)
    {
    }
    return paramInt;
  }

  public JSONArray optJSONArray(String paramString)
  {
    paramString = opt(paramString);
    if ((paramString instanceof JSONArray))
      return (JSONArray)paramString;
    return null;
  }

  public JSONObject optJSONObject(String paramString)
  {
    paramString = opt(paramString);
    if ((paramString instanceof JSONObject))
      return (JSONObject)paramString;
    return null;
  }

  public long optLong(String paramString)
  {
    return optLong(paramString, 0L);
  }

  public long optLong(String paramString, long paramLong)
  {
    try
    {
      long l = getLong(paramString);
      return l;
    }
    catch (Exception paramString)
    {
    }
    return paramLong;
  }

  public String optString(String paramString)
  {
    return optString(paramString, "");
  }

  public String optString(String paramString1, String paramString2)
  {
    paramString1 = opt(paramString1);
    if (paramString1 != null)
      paramString2 = paramString1.toString();
    return paramString2;
  }

  public JSONObject put(String paramString, double paramDouble)
    throws JSONException
  {
    put(paramString, new Double(paramDouble));
    return this;
  }

  public JSONObject put(String paramString, int paramInt)
    throws JSONException
  {
    put(paramString, new Integer(paramInt));
    return this;
  }

  public JSONObject put(String paramString, long paramLong)
    throws JSONException
  {
    put(paramString, new Long(paramLong));
    return this;
  }

  public JSONObject put(String paramString, Object paramObject)
    throws JSONException
  {
    if (paramString == null)
      throw new JSONException("Null key.");
    if (paramObject != null)
    {
      testValidity(paramObject);
      this.myHashMap.put(paramString, paramObject);
      return this;
    }
    remove(paramString);
    return this;
  }

  public JSONObject put(String paramString, Collection paramCollection)
    throws JSONException
  {
    put(paramString, new JSONArray(paramCollection));
    return this;
  }

  public JSONObject put(String paramString, Map paramMap)
    throws JSONException
  {
    put(paramString, new JSONObject(paramMap));
    return this;
  }

  public JSONObject put(String paramString, boolean paramBoolean)
    throws JSONException
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      put(paramString, localBoolean);
      return this;
    }
  }

  public JSONObject putOpt(String paramString, Object paramObject)
    throws JSONException
  {
    if ((paramString != null) && (paramObject != null))
      put(paramString, paramObject);
    return this;
  }

  public Object remove(String paramString)
  {
    return this.myHashMap.remove(paramString);
  }

  public JSONArray toJSONArray(JSONArray paramJSONArray)
    throws JSONException
  {
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0))
      return null;
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localJSONArray.put(opt(paramJSONArray.getString(i)));
      i += 1;
    }
    return localJSONArray;
  }

  public String toString()
  {
    try
    {
      Object localObject1 = keys();
      StringBuffer localStringBuffer = new StringBuffer("{");
      while (((Iterator)localObject1).hasNext())
      {
        if (localStringBuffer.length() > 1)
          localStringBuffer.append(',');
        Object localObject2 = ((Iterator)localObject1).next();
        localStringBuffer.append(quote(localObject2.toString()));
        localStringBuffer.append(':');
        localStringBuffer.append(valueToString(this.myHashMap.get(localObject2)));
      }
      localStringBuffer.append('}');
      localObject1 = localStringBuffer.toString();
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String toString(int paramInt)
    throws JSONException
  {
    return toString(paramInt, 0);
  }

  String toString(int paramInt1, int paramInt2)
    throws JSONException
  {
    int j = 0;
    int i = length();
    if (i == 0)
      return "{}";
    Object localObject1 = keys();
    StringBuffer localStringBuffer = new StringBuffer("{");
    int k = paramInt2 + paramInt1;
    if (i == 1)
    {
      localObject1 = ((Iterator)localObject1).next();
      localStringBuffer.append(quote(localObject1.toString()));
      localStringBuffer.append(": ");
      localStringBuffer.append(valueToString(this.myHashMap.get(localObject1), paramInt1, paramInt2));
    }
    while (true)
    {
      localStringBuffer.append('}');
      return localStringBuffer.toString();
      Object localObject2;
      localStringBuffer.append(quote(localObject2.toString()));
      localStringBuffer.append(": ");
      localStringBuffer.append(valueToString(this.myHashMap.get(localObject2), paramInt1, k));
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = ((Iterator)localObject1).next();
        if (localStringBuffer.length() > 1)
          localStringBuffer.append(",\n");
        while (true)
        {
          i = 0;
          while (i < k)
          {
            localStringBuffer.append(' ');
            i += 1;
          }
          break;
          localStringBuffer.append('\n');
        }
      }
      if (localStringBuffer.length() > 1)
      {
        localStringBuffer.append('\n');
        paramInt1 = j;
        while (paramInt1 < paramInt2)
        {
          localStringBuffer.append(' ');
          paramInt1 += 1;
        }
      }
    }
  }

  public Writer write(Writer paramWriter)
    throws JSONException
  {
    for (int i = 0; ; i = 1)
    {
      Object localObject;
      try
      {
        Iterator localIterator = keys();
        paramWriter.write(123);
        if (!localIterator.hasNext())
          break label126;
        if (i != 0)
          paramWriter.write(44);
        localObject = localIterator.next();
        paramWriter.write(quote(localObject.toString()));
        paramWriter.write(58);
        localObject = this.myHashMap.get(localObject);
        if ((localObject instanceof JSONObject))
          ((JSONObject)localObject).write(paramWriter);
        else if ((localObject instanceof JSONArray))
          ((JSONArray)localObject).write(paramWriter);
      }
      catch (IOException paramWriter)
      {
        throw new JSONException(paramWriter);
      }
      paramWriter.write(valueToString(localObject));
      continue;
      label126: paramWriter.write(125);
      return paramWriter;
    }
  }

  private static final class Null
  {
    protected final Object clone()
    {
      return this;
    }

    public boolean equals(Object paramObject)
    {
      return (paramObject == null) || (paramObject == this);
    }

    public String toString()
    {
      return "null";
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.JSONObject
 * JD-Core Version:    0.6.2
 */