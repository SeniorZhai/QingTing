package com.alibaba.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class JSONObject extends JSON
  implements Map<String, Object>, JSONAware, Cloneable, Serializable, InvocationHandler
{
  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private static final long serialVersionUID = 1L;
  private final Map<String, Object> map;

  public JSONObject()
  {
    this(16, false);
  }

  public JSONObject(int paramInt)
  {
    this(paramInt, false);
  }

  public JSONObject(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.map = new LinkedHashMap(paramInt);
      return;
    }
    this.map = new HashMap(paramInt);
  }

  public JSONObject(Map<String, Object> paramMap)
  {
    this.map = paramMap;
  }

  public JSONObject(boolean paramBoolean)
  {
    this(16, paramBoolean);
  }

  public void clear()
  {
    this.map.clear();
  }

  public Object clone()
  {
    return new JSONObject(new HashMap(this.map));
  }

  public boolean containsKey(Object paramObject)
  {
    return this.map.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    return this.map.containsValue(paramObject);
  }

  public Set<Map.Entry<String, Object>> entrySet()
  {
    return this.map.entrySet();
  }

  public boolean equals(Object paramObject)
  {
    return this.map.equals(paramObject);
  }

  public Object get(Object paramObject)
  {
    return this.map.get(paramObject);
  }

  public BigDecimal getBigDecimal(String paramString)
  {
    return TypeUtils.castToBigDecimal(get(paramString));
  }

  public BigInteger getBigInteger(String paramString)
  {
    return TypeUtils.castToBigInteger(get(paramString));
  }

  public Boolean getBoolean(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return null;
    return TypeUtils.castToBoolean(paramString);
  }

  public boolean getBooleanValue(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return false;
    return TypeUtils.castToBoolean(paramString).booleanValue();
  }

  public Byte getByte(String paramString)
  {
    return TypeUtils.castToByte(get(paramString));
  }

  public byte getByteValue(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0;
    return TypeUtils.castToByte(paramString).byteValue();
  }

  public byte[] getBytes(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return null;
    return TypeUtils.castToBytes(paramString);
  }

  public java.util.Date getDate(String paramString)
  {
    return TypeUtils.castToDate(get(paramString));
  }

  public Double getDouble(String paramString)
  {
    return TypeUtils.castToDouble(get(paramString));
  }

  public double getDoubleValue(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0.0D;
    return TypeUtils.castToDouble(paramString).doubleValue();
  }

  public Float getFloat(String paramString)
  {
    return TypeUtils.castToFloat(get(paramString));
  }

  public float getFloatValue(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0.0F;
    return TypeUtils.castToFloat(paramString).floatValue();
  }

  public int getIntValue(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0;
    return TypeUtils.castToInt(paramString).intValue();
  }

  public Integer getInteger(String paramString)
  {
    return TypeUtils.castToInt(get(paramString));
  }

  public JSONArray getJSONArray(String paramString)
  {
    paramString = this.map.get(paramString);
    if ((paramString instanceof JSONArray))
      return (JSONArray)paramString;
    return (JSONArray)toJSON(paramString);
  }

  public JSONObject getJSONObject(String paramString)
  {
    paramString = this.map.get(paramString);
    if ((paramString instanceof JSONObject))
      return (JSONObject)paramString;
    return (JSONObject)toJSON(paramString);
  }

  public Long getLong(String paramString)
  {
    return TypeUtils.castToLong(get(paramString));
  }

  public long getLongValue(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0L;
    return TypeUtils.castToLong(paramString).longValue();
  }

  public <T> T getObject(String paramString, Class<T> paramClass)
  {
    return TypeUtils.castToJavaBean(this.map.get(paramString), paramClass);
  }

  public Short getShort(String paramString)
  {
    return TypeUtils.castToShort(get(paramString));
  }

  public short getShortValue(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return 0;
    return TypeUtils.castToShort(paramString).shortValue();
  }

  public java.sql.Date getSqlDate(String paramString)
  {
    return TypeUtils.castToSqlDate(get(paramString));
  }

  public String getString(String paramString)
  {
    paramString = get(paramString);
    if (paramString == null)
      return null;
    return paramString.toString();
  }

  public Timestamp getTimestamp(String paramString)
  {
    return TypeUtils.castToTimestamp(get(paramString));
  }

  public int hashCode()
  {
    return this.map.hashCode();
  }

  public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject)
    throws Throwable
  {
    paramObject = paramMethod.getParameterTypes();
    Object localObject;
    if (paramObject.length == 1)
    {
      if (paramMethod.getReturnType() != Void.TYPE)
        throw new JSONException("illegal setter");
      localObject = null;
      JSONField localJSONField = (JSONField)paramMethod.getAnnotation(JSONField.class);
      paramObject = localObject;
      if (localJSONField != null)
      {
        paramObject = localObject;
        if (localJSONField.name().length() != 0)
          paramObject = localJSONField.name();
      }
      localObject = paramObject;
      if (paramObject == null)
      {
        paramObject = paramMethod.getName();
        if (!paramObject.startsWith("set"))
          throw new JSONException("illegal setter");
        paramObject = paramObject.substring(3);
        if (paramObject.length() == 0)
          throw new JSONException("illegal setter");
        localObject = Character.toLowerCase(paramObject.charAt(0)) + paramObject.substring(1);
      }
      this.map.put(localObject, paramArrayOfObject[0]);
      return null;
    }
    if (paramObject.length == 0)
    {
      if (paramMethod.getReturnType() == Void.TYPE)
        throw new JSONException("illegal getter");
      paramArrayOfObject = null;
      localObject = (JSONField)paramMethod.getAnnotation(JSONField.class);
      paramObject = paramArrayOfObject;
      if (localObject != null)
      {
        paramObject = paramArrayOfObject;
        if (((JSONField)localObject).name().length() != 0)
          paramObject = ((JSONField)localObject).name();
      }
      paramArrayOfObject = paramObject;
      if (paramObject == null)
      {
        paramObject = paramMethod.getName();
        if (!paramObject.startsWith("get"))
          break label350;
        paramObject = paramObject.substring(3);
        if (paramObject.length() == 0)
          throw new JSONException("illegal getter");
      }
      for (paramArrayOfObject = Character.toLowerCase(paramObject.charAt(0)) + paramObject.substring(1); ; paramArrayOfObject = Character.toLowerCase(paramObject.charAt(0)) + paramObject.substring(1))
      {
        return TypeUtils.cast(this.map.get(paramArrayOfObject), paramMethod.getGenericReturnType(), ParserConfig.getGlobalInstance());
        label350: if (!paramObject.startsWith("is"))
          break;
        paramObject = paramObject.substring(2);
        if (paramObject.length() == 0)
          throw new JSONException("illegal getter");
      }
      throw new JSONException("illegal getter");
    }
    throw new UnsupportedOperationException(paramMethod.toGenericString());
  }

  public boolean isEmpty()
  {
    return this.map.isEmpty();
  }

  public Set<String> keySet()
  {
    return this.map.keySet();
  }

  public Object put(String paramString, Object paramObject)
  {
    return this.map.put(paramString, paramObject);
  }

  public void putAll(Map<? extends String, ? extends Object> paramMap)
  {
    this.map.putAll(paramMap);
  }

  public Object remove(Object paramObject)
  {
    return this.map.remove(paramObject);
  }

  public int size()
  {
    return this.map.size();
  }

  public Collection<Object> values()
  {
    return this.map.values();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.JSONObject
 * JD-Core Version:    0.6.2
 */