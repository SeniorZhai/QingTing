package weibo4android.org.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class JSONObject
{
  public static final Object NULL = new Null(null);
  private Map map;

  public JSONObject()
  {
    this.map = new HashMap();
  }

  public JSONObject(Object paramObject)
  {
    this();
    populateInternalMap(paramObject, false);
  }

  public JSONObject(Object paramObject, boolean paramBoolean)
  {
    this();
    populateInternalMap(paramObject, paramBoolean);
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
        putOpt(str, localClass.getField(str).get(paramObject));
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
    Object localObject = paramMap;
    if (paramMap == null)
      localObject = new HashMap();
    this.map = ((Map)localObject);
  }

  public JSONObject(Map paramMap, boolean paramBoolean)
  {
    this.map = new HashMap();
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        this.map.put(localEntry.getKey(), new JSONObject(localEntry.getValue(), paramBoolean));
      }
    }
  }

  public JSONObject(JSONObject paramJSONObject, String[] paramArrayOfString)
    throws JSONException
  {
    this();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      putOnce(paramArrayOfString[i], paramJSONObject.opt(paramArrayOfString[i]));
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
          putOnce(str, paramJSONTokener.nextValue());
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

  private boolean isStandardProperty(Class paramClass)
  {
    return (paramClass.isPrimitive()) || (paramClass.isAssignableFrom(Byte.class)) || (paramClass.isAssignableFrom(Short.class)) || (paramClass.isAssignableFrom(Integer.class)) || (paramClass.isAssignableFrom(Long.class)) || (paramClass.isAssignableFrom(Float.class)) || (paramClass.isAssignableFrom(Double.class)) || (paramClass.isAssignableFrom(Character.class)) || (paramClass.isAssignableFrom(String.class)) || (paramClass.isAssignableFrom(Boolean.class));
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

  private void populateInternalMap(Object paramObject, boolean paramBoolean)
  {
    int i = 0;
    Object localObject1 = paramObject.getClass();
    if (((Class)localObject1).getClassLoader() == null)
      paramBoolean = false;
    Method[] arrayOfMethod;
    Object localObject2;
    if (paramBoolean)
    {
      arrayOfMethod = ((Class)localObject1).getMethods();
      if (i >= arrayOfMethod.length)
        break label441;
      localObject2 = arrayOfMethod[i];
    }
    label441: label442: 
    while (true)
    {
      try
      {
        String str = ((Method)localObject2).getName();
        localObject1 = "";
        if (str.startsWith("get"))
        {
          localObject1 = str.substring(3);
          if ((((String)localObject1).length() > 0) && (Character.isUpperCase(((String)localObject1).charAt(0))) && (((Method)localObject2).getParameterTypes().length == 0))
          {
            if (((String)localObject1).length() != 1)
              continue;
            localObject1 = ((String)localObject1).toLowerCase();
            localObject2 = ((Method)localObject2).invoke(paramObject, (Object[])null);
            if (localObject2 != null)
              continue;
            this.map.put(localObject1, NULL);
          }
          i += 1;
          break;
          arrayOfMethod = ((Class)localObject1).getDeclaredMethods();
          break;
        }
        if (!str.startsWith("is"))
          continue;
        localObject1 = str.substring(2);
        continue;
        if (Character.isUpperCase(((String)localObject1).charAt(1)))
          break label442;
        localObject1 = ((String)localObject1).substring(0, 1).toLowerCase() + ((String)localObject1).substring(1);
        continue;
        if (localObject2.getClass().isArray())
        {
          this.map.put(localObject1, new JSONArray(localObject2, paramBoolean));
          continue;
        }
      }
      catch (Exception paramObject)
      {
        throw new RuntimeException(paramObject);
      }
      if ((localObject2 instanceof Collection))
      {
        this.map.put(localObject1, new JSONArray((Collection)localObject2, paramBoolean));
      }
      else if ((localObject2 instanceof Map))
      {
        this.map.put(localObject1, new JSONObject((Map)localObject2, paramBoolean));
      }
      else if (isStandardProperty(localObject2.getClass()))
      {
        this.map.put(localObject1, localObject2);
      }
      else if ((localObject2.getClass().getPackage().getName().startsWith("java")) || (localObject2.getClass().getClassLoader() == null))
      {
        this.map.put(localObject1, localObject2.toString());
      }
      else
      {
        this.map.put(localObject1, new JSONObject(localObject2, paramBoolean));
        continue;
        return;
      }
    }
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

  public static Object stringToValue(String paramString)
  {
    if (paramString.equals(""));
    int i;
    do
    {
      return paramString;
      if (paramString.equalsIgnoreCase("true"))
        return Boolean.TRUE;
      if (paramString.equalsIgnoreCase("false"))
        return Boolean.FALSE;
      if (paramString.equalsIgnoreCase("null"))
        return NULL;
      i = paramString.charAt(0);
    }
    while (((i < 48) || (i > 57)) && (i != 46) && (i != 45) && (i != 43));
    if ((i != 48) || ((paramString.length() > 2) && ((paramString.charAt(1) == 'x') || (paramString.charAt(1) == 'X'))));
    try
    {
      Integer localInteger1 = new Integer(Integer.parseInt(paramString.substring(2), 16));
      return localInteger1;
      try
      {
        localInteger1 = new Integer(Integer.parseInt(paramString, 8));
        return localInteger1;
      }
      catch (Exception localException1)
      {
      }
      try
      {
        label159: Integer localInteger2 = new Integer(paramString);
        return localInteger2;
      }
      catch (Exception localException2)
      {
        try
        {
          Long localLong = new Long(paramString);
          return localLong;
        }
        catch (Exception localException3)
        {
          try
          {
            Double localDouble = new Double(paramString);
            return localDouble;
          }
          catch (Exception localException4)
          {
            return paramString;
          }
        }
      }
    }
    catch (Exception localException5)
    {
      break label159;
    }
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
    return opt(paramString);
  }

  public boolean getBoolean(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if (localObject == null)
      return false;
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
    if (localObject == null)
      return 0.0D;
    try
    {
      if ((localObject instanceof Number))
        return ((Number)localObject).doubleValue();
      if (localObject.toString().length() > 0)
      {
        double d = Double.valueOf(localObject.toString()).doubleValue();
        return d;
      }
      return 0.0D;
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
    if (localObject == null)
      return 0;
    if ((localObject instanceof Number))
      return ((Number)localObject).intValue();
    return (int)getDouble(paramString);
  }

  public JSONArray getJSONArray(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if (localObject == null)
      return null;
    if ((localObject instanceof JSONArray))
      return (JSONArray)localObject;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a JSONArray.");
  }

  public JSONObject getJSONObject(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if (localObject == null)
      return null;
    if ((localObject instanceof JSONObject))
      return (JSONObject)localObject;
    throw new JSONException("JSONObject[" + quote(paramString) + "] is not a JSONObject.");
  }

  public long getLong(String paramString)
    throws JSONException
  {
    Object localObject = get(paramString);
    if (localObject == null)
      return 0L;
    if ((localObject instanceof String))
    {
      if (localObject.toString().length() > 0)
        return Long.valueOf(localObject.toString()).longValue();
      return 0L;
    }
    if ((localObject instanceof Number))
      return ((Number)localObject).longValue();
    return ()getDouble(paramString);
  }

  public String getString(String paramString)
    throws JSONException
  {
    paramString = get(paramString);
    if (paramString == null)
      return "";
    return paramString.toString();
  }

  public boolean has(String paramString)
  {
    return this.map.containsKey(paramString);
  }

  public boolean isNull(String paramString)
  {
    return NULL.equals(opt(paramString));
  }

  public Iterator keys()
  {
    return this.map.keySet().iterator();
  }

  public int length()
  {
    return this.map.size();
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
    return this.map.get(paramString);
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
      this.map.put(paramString, paramObject);
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

  public JSONObject putOnce(String paramString, Object paramObject)
    throws JSONException
  {
    if ((paramString != null) && (paramObject != null))
    {
      if (opt(paramString) != null)
        throw new JSONException("Duplicate key \"" + paramString + "\"");
      put(paramString, paramObject);
    }
    return this;
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
    return this.map.remove(paramString);
  }

  public Iterator sortedKeys()
  {
    return new TreeSet(this.map.keySet()).iterator();
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
        localStringBuffer.append(valueToString(this.map.get(localObject2)));
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
    Object localObject1 = sortedKeys();
    StringBuffer localStringBuffer = new StringBuffer("{");
    int k = paramInt2 + paramInt1;
    if (i == 1)
    {
      localObject1 = ((Iterator)localObject1).next();
      localStringBuffer.append(quote(localObject1.toString()));
      localStringBuffer.append(": ");
      localStringBuffer.append(valueToString(this.map.get(localObject1), paramInt1, paramInt2));
    }
    while (true)
    {
      localStringBuffer.append('}');
      return localStringBuffer.toString();
      Object localObject2;
      localStringBuffer.append(quote(localObject2.toString()));
      localStringBuffer.append(": ");
      localStringBuffer.append(valueToString(this.map.get(localObject2), paramInt1, k));
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
          break label128;
        if (i != 0)
          paramWriter.write(44);
        localObject = localIterator.next();
        paramWriter.write(quote(localObject.toString()));
        paramWriter.write(58);
        localObject = this.map.get(localObject);
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
      label128: paramWriter.write(125);
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
 * Qualified Name:     weibo4android.org.json.JSONObject
 * JD-Core Version:    0.6.2
 */