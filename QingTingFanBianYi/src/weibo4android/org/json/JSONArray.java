package weibo4android.org.json;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class JSONArray
{
  private ArrayList myArrayList;

  public JSONArray()
  {
    this.myArrayList = new ArrayList();
  }

  public JSONArray(Object paramObject)
    throws JSONException
  {
    this();
    if (paramObject.getClass().isArray())
    {
      int j = Array.getLength(paramObject);
      int i = 0;
      while (i < j)
      {
        put(Array.get(paramObject, i));
        i += 1;
      }
    }
    throw new JSONException("JSONArray initial value should be a string or collection or array.");
  }

  public JSONArray(Object paramObject, boolean paramBoolean)
    throws JSONException
  {
    this();
    if (paramObject.getClass().isArray())
    {
      int j = Array.getLength(paramObject);
      int i = 0;
      while (i < j)
      {
        put(new JSONObject(Array.get(paramObject, i), paramBoolean));
        i += 1;
      }
    }
    throw new JSONException("JSONArray initial value should be a string or collection or array.");
  }

  public JSONArray(String paramString)
    throws JSONException
  {
    this(new JSONTokener(paramString));
  }

  public JSONArray(Collection paramCollection)
  {
    if (paramCollection == null);
    for (paramCollection = new ArrayList(); ; paramCollection = new ArrayList(paramCollection))
    {
      this.myArrayList = paramCollection;
      return;
    }
  }

  public JSONArray(Collection paramCollection, boolean paramBoolean)
  {
    this.myArrayList = new ArrayList();
    if (paramCollection != null)
    {
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
        this.myArrayList.add(new JSONObject(paramCollection.next(), paramBoolean));
    }
  }

  public JSONArray(JSONTokener paramJSONTokener)
    throws JSONException
  {
    this();
    int i = paramJSONTokener.nextClean();
    char c;
    if (i == 91)
    {
      c = ']';
      if (paramJSONTokener.nextClean() != ']')
        break label47;
    }
    label47: 
    do
    {
      return;
      if (i == 40)
      {
        c = ')';
        break;
      }
      throw paramJSONTokener.syntaxError("A JSONArray text must start with '['");
      paramJSONTokener.back();
      while (true)
      {
        if (paramJSONTokener.nextClean() == ',')
        {
          paramJSONTokener.back();
          this.myArrayList.add(null);
        }
        while (true)
        {
          i = paramJSONTokener.nextClean();
          switch (i)
          {
          default:
            throw paramJSONTokener.syntaxError("Expected a ',' or ']'");
            paramJSONTokener.back();
            this.myArrayList.add(paramJSONTokener.nextValue());
          case 44:
          case 59:
          case 41:
          case 93:
          }
        }
        if (paramJSONTokener.nextClean() == ']')
          break;
        paramJSONTokener.back();
      }
    }
    while (c == i);
    throw paramJSONTokener.syntaxError("Expected a '" + new Character(c) + "'");
  }

  public Object get(int paramInt)
    throws JSONException
  {
    Object localObject = opt(paramInt);
    if (localObject == null)
      throw new JSONException("JSONArray[" + paramInt + "] not found.");
    return localObject;
  }

  public boolean getBoolean(int paramInt)
    throws JSONException
  {
    Object localObject = get(paramInt);
    if ((localObject.equals(Boolean.FALSE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("false"))))
      return false;
    if ((localObject.equals(Boolean.TRUE)) || (((localObject instanceof String)) && (((String)localObject).equalsIgnoreCase("true"))))
      return true;
    throw new JSONException("JSONArray[" + paramInt + "] is not a Boolean.");
  }

  public double getDouble(int paramInt)
    throws JSONException
  {
    Object localObject = get(paramInt);
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
    throw new JSONException("JSONArray[" + paramInt + "] is not a number.");
  }

  public int getInt(int paramInt)
    throws JSONException
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof Number))
      return ((Number)localObject).intValue();
    return (int)getDouble(paramInt);
  }

  public JSONArray getJSONArray(int paramInt)
    throws JSONException
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof JSONArray))
      return (JSONArray)localObject;
    throw new JSONException("JSONArray[" + paramInt + "] is not a JSONArray.");
  }

  public JSONObject getJSONObject(int paramInt)
    throws JSONException
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof JSONObject))
      return (JSONObject)localObject;
    throw new JSONException("JSONArray[" + paramInt + "] is not a JSONObject.");
  }

  public long getLong(int paramInt)
    throws JSONException
  {
    Object localObject = get(paramInt);
    if ((localObject instanceof Number))
      return ((Number)localObject).longValue();
    return ()getDouble(paramInt);
  }

  public String getString(int paramInt)
    throws JSONException
  {
    return get(paramInt).toString();
  }

  public boolean isNull(int paramInt)
  {
    return JSONObject.NULL.equals(opt(paramInt));
  }

  public String join(String paramString)
    throws JSONException
  {
    int j = length();
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < j)
    {
      if (i > 0)
        localStringBuffer.append(paramString);
      localStringBuffer.append(JSONObject.valueToString(this.myArrayList.get(i)));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public int length()
  {
    return this.myArrayList.size();
  }

  public Object opt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= length()))
      return null;
    return this.myArrayList.get(paramInt);
  }

  public boolean optBoolean(int paramInt)
  {
    return optBoolean(paramInt, false);
  }

  public boolean optBoolean(int paramInt, boolean paramBoolean)
  {
    try
    {
      boolean bool = getBoolean(paramInt);
      return bool;
    }
    catch (Exception localException)
    {
    }
    return paramBoolean;
  }

  public double optDouble(int paramInt)
  {
    return optDouble(paramInt, (0.0D / 0.0D));
  }

  public double optDouble(int paramInt, double paramDouble)
  {
    try
    {
      double d = getDouble(paramInt);
      return d;
    }
    catch (Exception localException)
    {
    }
    return paramDouble;
  }

  public int optInt(int paramInt)
  {
    return optInt(paramInt, 0);
  }

  public int optInt(int paramInt1, int paramInt2)
  {
    try
    {
      paramInt1 = getInt(paramInt1);
      return paramInt1;
    }
    catch (Exception localException)
    {
    }
    return paramInt2;
  }

  public JSONArray optJSONArray(int paramInt)
  {
    Object localObject = opt(paramInt);
    if ((localObject instanceof JSONArray))
      return (JSONArray)localObject;
    return null;
  }

  public JSONObject optJSONObject(int paramInt)
  {
    Object localObject = opt(paramInt);
    if ((localObject instanceof JSONObject))
      return (JSONObject)localObject;
    return null;
  }

  public long optLong(int paramInt)
  {
    return optLong(paramInt, 0L);
  }

  public long optLong(int paramInt, long paramLong)
  {
    try
    {
      long l = getLong(paramInt);
      return l;
    }
    catch (Exception localException)
    {
    }
    return paramLong;
  }

  public String optString(int paramInt)
  {
    return optString(paramInt, "");
  }

  public String optString(int paramInt, String paramString)
  {
    Object localObject = opt(paramInt);
    if (localObject != null)
      paramString = localObject.toString();
    return paramString;
  }

  public JSONArray put(double paramDouble)
    throws JSONException
  {
    Double localDouble = new Double(paramDouble);
    JSONObject.testValidity(localDouble);
    put(localDouble);
    return this;
  }

  public JSONArray put(int paramInt)
  {
    put(new Integer(paramInt));
    return this;
  }

  public JSONArray put(int paramInt, double paramDouble)
    throws JSONException
  {
    put(paramInt, new Double(paramDouble));
    return this;
  }

  public JSONArray put(int paramInt1, int paramInt2)
    throws JSONException
  {
    put(paramInt1, new Integer(paramInt2));
    return this;
  }

  public JSONArray put(int paramInt, long paramLong)
    throws JSONException
  {
    put(paramInt, new Long(paramLong));
    return this;
  }

  public JSONArray put(int paramInt, Object paramObject)
    throws JSONException
  {
    JSONObject.testValidity(paramObject);
    if (paramInt < 0)
      throw new JSONException("JSONArray[" + paramInt + "] not found.");
    if (paramInt < length())
    {
      this.myArrayList.set(paramInt, paramObject);
      return this;
    }
    while (paramInt != length())
      put(JSONObject.NULL);
    put(paramObject);
    return this;
  }

  public JSONArray put(int paramInt, Collection paramCollection)
    throws JSONException
  {
    put(paramInt, new JSONArray(paramCollection));
    return this;
  }

  public JSONArray put(int paramInt, Map paramMap)
    throws JSONException
  {
    put(paramInt, new JSONObject(paramMap));
    return this;
  }

  public JSONArray put(int paramInt, boolean paramBoolean)
    throws JSONException
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      put(paramInt, localBoolean);
      return this;
    }
  }

  public JSONArray put(long paramLong)
  {
    put(new Long(paramLong));
    return this;
  }

  public JSONArray put(Object paramObject)
  {
    this.myArrayList.add(paramObject);
    return this;
  }

  public JSONArray put(Collection paramCollection)
  {
    put(new JSONArray(paramCollection));
    return this;
  }

  public JSONArray put(Map paramMap)
  {
    put(new JSONObject(paramMap));
    return this;
  }

  public JSONArray put(boolean paramBoolean)
  {
    if (paramBoolean);
    for (Boolean localBoolean = Boolean.TRUE; ; localBoolean = Boolean.FALSE)
    {
      put(localBoolean);
      return this;
    }
  }

  public JSONObject toJSONObject(JSONArray paramJSONArray)
    throws JSONException
  {
    if ((paramJSONArray == null) || (paramJSONArray.length() == 0) || (length() == 0))
      return null;
    JSONObject localJSONObject = new JSONObject();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localJSONObject.put(paramJSONArray.getString(i), opt(i));
      i += 1;
    }
    return localJSONObject;
  }

  public String toString()
  {
    try
    {
      String str = '[' + join(",") + ']';
      return str;
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
    int k = 0;
    int m = length();
    if (m == 0)
      return "[]";
    StringBuffer localStringBuffer = new StringBuffer("[");
    if (m == 1)
      localStringBuffer.append(JSONObject.valueToString(this.myArrayList.get(0), paramInt1, paramInt2));
    while (true)
    {
      localStringBuffer.append(']');
      return localStringBuffer.toString();
      int n = paramInt2 + paramInt1;
      localStringBuffer.append('\n');
      int i = 0;
      while (i < m)
      {
        if (i > 0)
          localStringBuffer.append(",\n");
        int j = 0;
        while (j < n)
        {
          localStringBuffer.append(' ');
          j += 1;
        }
        localStringBuffer.append(JSONObject.valueToString(this.myArrayList.get(i), paramInt1, n));
        i += 1;
      }
      localStringBuffer.append('\n');
      paramInt1 = k;
      while (paramInt1 < paramInt2)
      {
        localStringBuffer.append(' ');
        paramInt1 += 1;
      }
    }
  }

  public Writer write(Writer paramWriter)
    throws JSONException
  {
    for (int i = 0; ; i = 1)
    {
      int j;
      Object localObject;
      try
      {
        int k = length();
        paramWriter.write(91);
        j = 0;
        if (j >= k)
          break label103;
        if (i != 0)
          paramWriter.write(44);
        localObject = this.myArrayList.get(j);
        if ((localObject instanceof JSONObject))
          ((JSONObject)localObject).write(paramWriter);
        else if ((localObject instanceof JSONArray))
          ((JSONArray)localObject).write(paramWriter);
      }
      catch (IOException paramWriter)
      {
        throw new JSONException(paramWriter);
      }
      paramWriter.write(JSONObject.valueToString(localObject));
      break label111;
      label103: paramWriter.write(93);
      return paramWriter;
      label111: j += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.org.json.JSONArray
 * JD-Core Version:    0.6.2
 */