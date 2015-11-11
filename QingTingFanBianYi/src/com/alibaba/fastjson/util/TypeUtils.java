package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import java.beans.Introspector;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeUtils
{
  public static boolean compatibleWithJavaBean = false;
  private static ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap();

  static
  {
    addBaseClassMappings();
  }

  public static void addBaseClassMappings()
  {
    mappings.put("byte", Byte.TYPE);
    mappings.put("short", Short.TYPE);
    mappings.put("int", Integer.TYPE);
    mappings.put("long", Long.TYPE);
    mappings.put("float", Float.TYPE);
    mappings.put("double", Double.TYPE);
    mappings.put("boolean", Boolean.TYPE);
    mappings.put("char", Character.TYPE);
    mappings.put("[byte", [B.class);
    mappings.put("[short", [S.class);
    mappings.put("[int", [I.class);
    mappings.put("[long", [J.class);
    mappings.put("[float", [F.class);
    mappings.put("[double", [D.class);
    mappings.put("[boolean", [Z.class);
    mappings.put("[char", [C.class);
    mappings.put(HashMap.class.getName(), HashMap.class);
  }

  public static void addClassMapping(String paramString, Class<?> paramClass)
  {
    String str = paramString;
    if (paramString == null)
      str = paramClass.getName();
    mappings.put(str, paramClass);
  }

  public static final <T> T cast(Object paramObject, Class<T> paramClass, ParserConfig paramParserConfig)
  {
    Object localObject;
    if (paramObject == null)
      localObject = null;
    do
    {
      Map localMap;
      do
      {
        do
        {
          do
          {
            return localObject;
            if (paramClass == null)
              throw new IllegalArgumentException("clazz is null");
            localObject = paramObject;
          }
          while (paramClass == paramObject.getClass());
          if (!(paramObject instanceof Map))
            break;
          localObject = paramObject;
        }
        while (paramClass == Map.class);
        localMap = (Map)paramObject;
        if (paramClass != Object.class)
          break;
        localObject = paramObject;
      }
      while (!localMap.containsKey(JSON.DEFAULT_TYPE_KEY));
      return castToJavaBean((Map)paramObject, paramClass, paramParserConfig);
      if ((paramClass.isArray()) && ((paramObject instanceof Collection)))
      {
        localObject = (Collection)paramObject;
        int i = 0;
        paramObject = Array.newInstance(paramClass.getComponentType(), ((Collection)localObject).size());
        localObject = ((Collection)localObject).iterator();
        while (((Iterator)localObject).hasNext())
        {
          Array.set(paramObject, i, cast(((Iterator)localObject).next(), paramClass.getComponentType(), paramParserConfig));
          i += 1;
        }
        return paramObject;
      }
      localObject = paramObject;
    }
    while (paramClass.isAssignableFrom(paramObject.getClass()));
    if ((paramClass == Boolean.TYPE) || (paramClass == Boolean.class))
      return castToBoolean(paramObject);
    if ((paramClass == Byte.TYPE) || (paramClass == Byte.class))
      return castToByte(paramObject);
    if ((paramClass == Short.TYPE) || (paramClass == Short.class))
      return castToShort(paramObject);
    if ((paramClass == Integer.TYPE) || (paramClass == Integer.class))
      return castToInt(paramObject);
    if ((paramClass == Long.TYPE) || (paramClass == Long.class))
      return castToLong(paramObject);
    if ((paramClass == Float.TYPE) || (paramClass == Float.class))
      return castToFloat(paramObject);
    if ((paramClass == Double.TYPE) || (paramClass == Double.class))
      return castToDouble(paramObject);
    if (paramClass == String.class)
      return castToString(paramObject);
    if (paramClass == BigDecimal.class)
      return castToBigDecimal(paramObject);
    if (paramClass == BigInteger.class)
      return castToBigInteger(paramObject);
    if (paramClass == java.util.Date.class)
      return castToDate(paramObject);
    if (paramClass == java.sql.Date.class)
      return castToSqlDate(paramObject);
    if (paramClass == Timestamp.class)
      return castToTimestamp(paramObject);
    if (paramClass.isEnum())
      return castToEnum(paramObject, paramClass, paramParserConfig);
    if (Calendar.class.isAssignableFrom(paramClass))
    {
      paramParserConfig = castToDate(paramObject);
      if (paramClass == Calendar.class)
        paramObject = Calendar.getInstance();
      while (true)
      {
        paramObject.setTime(paramParserConfig);
        return paramObject;
        try
        {
          paramObject = (Calendar)paramClass.newInstance();
        }
        catch (Exception paramObject)
        {
          throw new JSONException("can not cast to : " + paramClass.getName(), paramObject);
        }
      }
    }
    if (((paramObject instanceof String)) && (((String)paramObject).length() == 0))
      return null;
    throw new JSONException("can not cast to : " + paramClass.getName());
  }

  public static final <T> T cast(Object paramObject, ParameterizedType paramParameterizedType, ParserConfig paramParserConfig)
  {
    Object localObject2 = paramParameterizedType.getRawType();
    Type localType;
    Object localObject1;
    if ((localObject2 == List.class) || (localObject2 == ArrayList.class))
    {
      localType = paramParameterizedType.getActualTypeArguments()[0];
      if ((paramObject instanceof Iterable))
      {
        paramParameterizedType = new ArrayList();
        localObject1 = ((Iterable)paramObject).iterator();
        while (true)
        {
          paramObject = paramParameterizedType;
          if (!((Iterator)localObject1).hasNext())
            break;
          paramParameterizedType.add(cast(((Iterator)localObject1).next(), localType, paramParserConfig));
        }
      }
    }
    if ((localObject2 == Map.class) || (localObject2 == HashMap.class))
    {
      localType = paramParameterizedType.getActualTypeArguments()[0];
      localObject1 = paramParameterizedType.getActualTypeArguments()[1];
      if ((paramObject instanceof Map))
      {
        paramParameterizedType = new HashMap();
        paramObject = ((Map)paramObject).entrySet().iterator();
        while (paramObject.hasNext())
        {
          localObject2 = (Map.Entry)paramObject.next();
          paramParameterizedType.put(cast(((Map.Entry)localObject2).getKey(), localType, paramParserConfig), cast(((Map.Entry)localObject2).getValue(), (Type)localObject1, paramParserConfig));
        }
        paramObject = paramParameterizedType;
        return paramObject;
      }
    }
    if (((paramObject instanceof String)) && (((String)paramObject).length() == 0))
      return null;
    if ((paramParameterizedType.getActualTypeArguments().length == 1) && ((paramParameterizedType.getActualTypeArguments()[0] instanceof WildcardType)))
      return cast(paramObject, (Type)localObject2, paramParserConfig);
    throw new JSONException("can not cast to : " + paramParameterizedType);
  }

  public static final <T> T cast(Object paramObject, Type paramType, ParserConfig paramParserConfig)
  {
    if (paramObject == null)
      paramObject = null;
    do
    {
      return paramObject;
      if ((paramType instanceof Class))
        return cast(paramObject, (Class)paramType, paramParserConfig);
      if ((paramType instanceof ParameterizedType))
        return cast(paramObject, (ParameterizedType)paramType, paramParserConfig);
      if (((paramObject instanceof String)) && (((String)paramObject).length() == 0))
        return null;
    }
    while ((paramType instanceof TypeVariable));
    throw new JSONException("can not cast to : " + paramType);
  }

  public static final BigDecimal castToBigDecimal(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof BigDecimal))
      return (BigDecimal)paramObject;
    if ((paramObject instanceof BigInteger))
      return new BigDecimal((BigInteger)paramObject);
    paramObject = paramObject.toString();
    if (paramObject.length() == 0)
      return null;
    return new BigDecimal(paramObject);
  }

  public static final BigInteger castToBigInteger(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof BigInteger))
      return (BigInteger)paramObject;
    if (((paramObject instanceof Float)) || ((paramObject instanceof Double)))
      return BigInteger.valueOf(((Number)paramObject).longValue());
    paramObject = paramObject.toString();
    if (paramObject.length() == 0)
      return null;
    return new BigInteger(paramObject);
  }

  public static final Boolean castToBoolean(Object paramObject)
  {
    boolean bool = true;
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Boolean))
      return (Boolean)paramObject;
    if ((paramObject instanceof Number))
    {
      if (((Number)paramObject).intValue() == 1);
      while (true)
      {
        return Boolean.valueOf(bool);
        bool = false;
      }
    }
    if ((paramObject instanceof String))
    {
      String str = (String)paramObject;
      if (str.length() == 0)
        return null;
      if ("true".equals(str))
        return Boolean.TRUE;
      if ("false".equals(str))
        return Boolean.FALSE;
      if ("1".equals(str))
        return Boolean.TRUE;
    }
    throw new JSONException("can not cast to int, value : " + paramObject);
  }

  public static final Byte castToByte(Object paramObject)
  {
    if (paramObject == null);
    do
    {
      return null;
      if ((paramObject instanceof Number))
        return Byte.valueOf(((Number)paramObject).byteValue());
      if (!(paramObject instanceof String))
        break;
      paramObject = (String)paramObject;
    }
    while (paramObject.length() == 0);
    return Byte.valueOf(Byte.parseByte(paramObject));
    throw new JSONException("can not cast to byte, value : " + paramObject);
  }

  public static final byte[] castToBytes(Object paramObject)
  {
    if ((paramObject instanceof byte[]))
      return (byte[])paramObject;
    if ((paramObject instanceof String))
      return Base64.decodeFast((String)paramObject);
    throw new JSONException("can not cast to int, value : " + paramObject);
  }

  public static final Character castToChar(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Character))
      return (Character)paramObject;
    if ((paramObject instanceof String))
    {
      String str = (String)paramObject;
      if (str.length() == 0)
        return null;
      if (str.length() != 1)
        throw new JSONException("can not cast to byte, value : " + paramObject);
      return Character.valueOf(str.charAt(0));
    }
    throw new JSONException("can not cast to byte, value : " + paramObject);
  }

  public static final java.util.Date castToDate(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Calendar))
      return ((Calendar)paramObject).getTime();
    if ((paramObject instanceof java.util.Date))
      return (java.util.Date)paramObject;
    long l = -1L;
    if ((paramObject instanceof Number))
      l = ((Number)paramObject).longValue();
    if ((paramObject instanceof String))
    {
      String str = (String)paramObject;
      if (str.indexOf('-') != -1)
      {
        if (str.length() == JSON.DEFFAULT_DATE_FORMAT.length())
          paramObject = JSON.DEFFAULT_DATE_FORMAT;
        while (true)
        {
          paramObject = new SimpleDateFormat(paramObject);
          try
          {
            paramObject = paramObject.parse(str);
            return paramObject;
            if (str.length() == 10)
              paramObject = "yyyy-MM-dd";
            else if (str.length() == "yyyy-MM-dd HH:mm:ss".length())
              paramObject = "yyyy-MM-dd HH:mm:ss";
            else
              paramObject = "yyyy-MM-dd HH:mm:ss.SSS";
          }
          catch (ParseException paramObject)
          {
            throw new JSONException("can not cast to Date, value : " + str);
          }
        }
      }
      if (str.length() == 0)
        return null;
      l = Long.parseLong(str);
    }
    if (l < 0L)
      throw new JSONException("can not cast to Date, value : " + paramObject);
    return new java.util.Date(l);
  }

  public static final Double castToDouble(Object paramObject)
  {
    if (paramObject == null);
    do
    {
      return null;
      if ((paramObject instanceof Number))
        return Double.valueOf(((Number)paramObject).doubleValue());
      if (!(paramObject instanceof String))
        break;
      paramObject = paramObject.toString();
    }
    while (paramObject.length() == 0);
    return Double.valueOf(Double.parseDouble(paramObject));
    throw new JSONException("can not cast to double, value : " + paramObject);
  }

  public static final <T> T castToEnum(Object paramObject, Class<T> paramClass, ParserConfig paramParserConfig)
  {
    try
    {
      if ((paramObject instanceof String))
      {
        paramObject = (String)paramObject;
        if (paramObject.length() == 0)
          return null;
        return Enum.valueOf(paramClass, paramObject);
      }
      if ((paramObject instanceof Number))
      {
        int j = ((Number)paramObject).intValue();
        paramObject = (Object[])paramClass.getMethod("values", new Class[0]).invoke(null, new Object[0]);
        int k = paramObject.length;
        int i = 0;
        while (i < k)
        {
          paramParserConfig = (Enum)paramObject[i];
          int m = paramParserConfig.ordinal();
          if (m == j)
            break label172;
          i += 1;
        }
      }
    }
    catch (Exception paramObject)
    {
      throw new JSONException("can not cast to : " + paramClass.getName(), paramObject);
    }
    throw new JSONException("can not cast to : " + paramClass.getName());
    label172: return paramParserConfig;
  }

  public static final Float castToFloat(Object paramObject)
  {
    if (paramObject == null);
    do
    {
      return null;
      if ((paramObject instanceof Number))
        return Float.valueOf(((Number)paramObject).floatValue());
      if (!(paramObject instanceof String))
        break;
      paramObject = paramObject.toString();
    }
    while (paramObject.length() == 0);
    return Float.valueOf(Float.parseFloat(paramObject));
    throw new JSONException("can not cast to float, value : " + paramObject);
  }

  public static final Integer castToInt(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Integer))
      return (Integer)paramObject;
    if ((paramObject instanceof Number))
      return Integer.valueOf(((Number)paramObject).intValue());
    if ((paramObject instanceof String))
    {
      paramObject = (String)paramObject;
      if (paramObject.length() == 0)
        return null;
      return Integer.valueOf(Integer.parseInt(paramObject));
    }
    throw new JSONException("can not cast to int, value : " + paramObject);
  }

  public static final <T> T castToJavaBean(Object paramObject, Class<T> paramClass)
  {
    return cast(paramObject, paramClass, ParserConfig.getGlobalInstance());
  }

  public static final <T> T castToJavaBean(Map<String, Object> paramMap, Class<T> paramClass, ParserConfig paramParserConfig)
  {
    if (paramClass == StackTraceElement.class);
    Object localObject2;
    try
    {
      paramClass = (String)paramMap.get("className");
      paramParserConfig = (String)paramMap.get("methodName");
      localObject1 = (String)paramMap.get("fileName");
      paramMap = (Number)paramMap.get("lineNumber");
      if (paramMap == null);
      for (int i = 0; ; i = paramMap.intValue())
        return new StackTraceElement(paramClass, paramParserConfig, (String)localObject1, i);
      localObject1 = paramMap.get(JSON.DEFAULT_TYPE_KEY);
      if (!(localObject1 instanceof String))
        break label180;
      localObject1 = (String)localObject1;
      localObject2 = loadClass((String)localObject1);
      if (localObject2 == null)
        throw new ClassNotFoundException((String)localObject1 + " not found");
    }
    catch (Exception paramMap)
    {
      throw new JSONException(paramMap.getMessage(), paramMap);
    }
    if (!localObject2.equals(paramClass))
      return castToJavaBean(paramMap, (Class)localObject2, paramParserConfig);
    label180: if (paramClass.isInterface())
    {
      if ((paramMap instanceof JSONObject));
      for (paramMap = (JSONObject)paramMap; ; paramMap = new JSONObject(paramMap))
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { paramClass }, paramMap);
    }
    Object localObject1 = paramParserConfig;
    if (paramParserConfig == null)
      localObject1 = ParserConfig.getGlobalInstance();
    paramParserConfig = ((ParserConfig)localObject1).getFieldDeserializers(paramClass);
    paramClass = paramClass.getDeclaredConstructor(new Class[0]);
    if (!paramClass.isAccessible())
      paramClass.setAccessible(true);
    paramClass = paramClass.newInstance(new Object[0]);
    paramParserConfig = paramParserConfig.entrySet().iterator();
    while (paramParserConfig.hasNext())
    {
      localObject2 = (Map.Entry)paramParserConfig.next();
      Object localObject3 = (String)((Map.Entry)localObject2).getKey();
      localObject2 = (FieldDeserializer)((Map.Entry)localObject2).getValue();
      if (paramMap.containsKey(localObject3))
      {
        localObject3 = paramMap.get(localObject3);
        Method localMethod = ((FieldDeserializer)localObject2).getMethod();
        if (localMethod != null)
        {
          localMethod.invoke(paramClass, new Object[] { cast(localObject3, localMethod.getGenericParameterTypes()[0], (ParserConfig)localObject1) });
        }
        else
        {
          localObject2 = ((FieldDeserializer)localObject2).getField();
          ((Field)localObject2).set(paramClass, cast(localObject3, ((Field)localObject2).getGenericType(), (ParserConfig)localObject1));
        }
      }
    }
    return paramClass;
  }

  public static final Long castToLong(Object paramObject)
  {
    if (paramObject == null);
    Object localObject;
    do
    {
      return null;
      if ((paramObject instanceof Number))
        return Long.valueOf(((Number)paramObject).longValue());
      if (!(paramObject instanceof String))
        break;
      localObject = (String)paramObject;
    }
    while (((String)localObject).length() == 0);
    try
    {
      long l = Long.parseLong((String)localObject);
      return Long.valueOf(l);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      JSONScanner localJSONScanner = new JSONScanner((String)localObject);
      localObject = null;
      if (localJSONScanner.scanISO8601DateIfMatch(false))
        localObject = localJSONScanner.getCalendar();
      localJSONScanner.close();
      if (localObject != null)
        return Long.valueOf(((Calendar)localObject).getTimeInMillis());
    }
    throw new JSONException("can not cast to long, value : " + paramObject);
  }

  public static final Short castToShort(Object paramObject)
  {
    if (paramObject == null);
    do
    {
      return null;
      if ((paramObject instanceof Number))
        return Short.valueOf(((Number)paramObject).shortValue());
      if (!(paramObject instanceof String))
        break;
      paramObject = (String)paramObject;
    }
    while (paramObject.length() == 0);
    return Short.valueOf(Short.parseShort(paramObject));
    throw new JSONException("can not cast to short, value : " + paramObject);
  }

  public static final java.sql.Date castToSqlDate(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Calendar))
      return new java.sql.Date(((Calendar)paramObject).getTimeInMillis());
    if ((paramObject instanceof java.sql.Date))
      return (java.sql.Date)paramObject;
    if ((paramObject instanceof java.util.Date))
      return new java.sql.Date(((java.util.Date)paramObject).getTime());
    long l = 0L;
    if ((paramObject instanceof Number))
      l = ((Number)paramObject).longValue();
    if ((paramObject instanceof String))
    {
      String str = (String)paramObject;
      if (str.length() == 0)
        return null;
      l = Long.parseLong(str);
    }
    if (l <= 0L)
      throw new JSONException("can not cast to Date, value : " + paramObject);
    return new java.sql.Date(l);
  }

  public static final String castToString(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return paramObject.toString();
  }

  public static final Timestamp castToTimestamp(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Calendar))
      return new Timestamp(((Calendar)paramObject).getTimeInMillis());
    if ((paramObject instanceof Timestamp))
      return (Timestamp)paramObject;
    if ((paramObject instanceof java.util.Date))
      return new Timestamp(((java.util.Date)paramObject).getTime());
    long l = 0L;
    if ((paramObject instanceof Number))
      l = ((Number)paramObject).longValue();
    if ((paramObject instanceof String))
    {
      String str = (String)paramObject;
      if (str.length() == 0)
        return null;
      l = Long.parseLong(str);
    }
    if (l <= 0L)
      throw new JSONException("can not cast to Date, value : " + paramObject);
    return new Timestamp(l);
  }

  public static void clearClassMapping()
  {
    mappings.clear();
    addBaseClassMappings();
  }

  public static List<FieldInfo> computeGetters(Class<?> paramClass, Map<String, String> paramMap)
  {
    return computeGetters(paramClass, paramMap, true);
  }

  public static List<FieldInfo> computeGetters(Class<?> paramClass, Map<String, String> paramMap, boolean paramBoolean)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    Method[] arrayOfMethod = paramClass.getMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    Object localObject4;
    if (i < j)
    {
      localObject4 = arrayOfMethod[i];
      Object localObject5 = ((Method)localObject4).getName();
      if (Modifier.isStatic(((Method)localObject4).getModifiers()));
      label247: 
      do
      {
        do
        {
          do
          {
            JSONField localJSONField;
            do
            {
              do
              {
                while (true)
                {
                  i += 1;
                  break;
                  if ((!((Method)localObject4).getReturnType().equals(Void.TYPE)) && (((Method)localObject4).getParameterTypes().length == 0) && (((Method)localObject4).getReturnType() != ClassLoader.class) && ((!((Method)localObject4).getName().equals("getMetaClass")) || (!((Method)localObject4).getReturnType().getName().equals("groovy.lang.MetaClass"))))
                  {
                    localObject2 = (JSONField)((Method)localObject4).getAnnotation(JSONField.class);
                    localObject1 = localObject2;
                    if (localObject2 == null)
                      localObject1 = getSupperMethodAnnotation(paramClass, (Method)localObject4);
                    if (localObject1 == null)
                      break label247;
                    if (((JSONField)localObject1).serialize())
                    {
                      if (((JSONField)localObject1).name().length() == 0)
                        break label247;
                      localObject2 = ((JSONField)localObject1).name();
                      localObject1 = localObject2;
                      if (paramMap != null)
                      {
                        localObject1 = (String)paramMap.get(localObject2);
                        if (localObject1 == null);
                      }
                      else
                      {
                        localLinkedHashMap.put(localObject1, new FieldInfo((String)localObject1, (Method)localObject4, null));
                      }
                    }
                  }
                }
                if (!((String)localObject5).startsWith("get"))
                  break label468;
              }
              while ((((String)localObject5).length() < 4) || (((String)localObject5).equals("getClass")));
              c = ((String)localObject5).charAt(3);
              if (!Character.isUpperCase(c))
                break label725;
              if (!compatibleWithJavaBean)
                break label689;
              localObject1 = Introspector.decapitalize(((String)localObject5).substring(3));
              if (isJSONTypeIgnore(paramClass, (String)localObject1))
                break label757;
              localObject3 = ParserConfig.getField(paramClass, (String)localObject1);
              localObject2 = localObject1;
              if (localObject3 == null)
                break label419;
              localJSONField = (JSONField)((Field)localObject3).getAnnotation(JSONField.class);
              localObject2 = localObject1;
              if (localJSONField == null)
                break label419;
            }
            while (!localJSONField.serialize());
            localObject2 = localObject1;
            if (localJSONField.name().length() == 0)
              break label419;
            localObject1 = localJSONField.name();
            localObject2 = localObject1;
            if (paramMap == null)
              break label419;
            localObject2 = (String)paramMap.get(localObject1);
          }
          while (localObject2 == null);
          localObject1 = localObject2;
          if (paramMap == null)
            break label445;
          localObject1 = (String)paramMap.get(localObject2);
        }
        while (localObject1 == null);
        localLinkedHashMap.put(localObject1, new FieldInfo((String)localObject1, (Method)localObject4, (Field)localObject3));
      }
      while ((!((String)localObject5).startsWith("is")) || (((String)localObject5).length() < 3));
      label309: label445: label468: char c = ((String)localObject5).charAt(2);
      label419: if (Character.isUpperCase(c))
        if (compatibleWithJavaBean)
          localObject1 = Introspector.decapitalize(((String)localObject5).substring(2));
      while (true)
      {
        localObject2 = ParserConfig.getField(paramClass, (String)localObject1);
        localObject3 = localObject2;
        if (localObject2 == null)
          localObject3 = ParserConfig.getField(paramClass, (String)localObject5);
        localObject2 = localObject1;
        if (localObject3 != null)
        {
          localObject5 = (JSONField)((Field)localObject3).getAnnotation(JSONField.class);
          localObject2 = localObject1;
          if (localObject5 != null)
          {
            if (!((JSONField)localObject5).serialize())
              break;
            localObject2 = localObject1;
            if (((JSONField)localObject5).name().length() != 0)
            {
              localObject1 = ((JSONField)localObject5).name();
              localObject2 = localObject1;
              if (paramMap != null)
              {
                localObject2 = (String)paramMap.get(localObject1);
                if (localObject2 == null)
                  break;
              }
            }
          }
        }
        localObject1 = localObject2;
        if (paramMap != null)
        {
          localObject1 = (String)paramMap.get(localObject2);
          if (localObject1 == null)
            break;
        }
        localLinkedHashMap.put(localObject1, new FieldInfo((String)localObject1, (Method)localObject4, (Field)localObject3));
        break;
        label689: localObject1 = Character.toLowerCase(((String)localObject5).charAt(3)) + ((String)localObject5).substring(4);
        break label309;
        label725: if (c == '_')
        {
          localObject1 = ((String)localObject5).substring(4);
          break label309;
        }
        if (c != 'f')
          break;
        localObject1 = ((String)localObject5).substring(3);
        break label309;
        label757: break;
        localObject1 = Character.toLowerCase(((String)localObject5).charAt(2)) + ((String)localObject5).substring(3);
        continue;
        if (c == '_')
        {
          localObject1 = ((String)localObject5).substring(3);
        }
        else
        {
          if (c != 'f')
            break;
          localObject1 = ((String)localObject5).substring(2);
        }
      }
    }
    Object localObject3 = paramClass.getFields();
    j = localObject3.length;
    i = 0;
    if (i < j)
    {
      arrayOfMethod = localObject3[i];
      if (Modifier.isStatic(arrayOfMethod.getModifiers()));
      while (true)
      {
        i += 1;
        break;
        localObject4 = (JSONField)arrayOfMethod.getAnnotation(JSONField.class);
        localObject2 = arrayOfMethod.getName();
        localObject1 = localObject2;
        if (localObject4 != null)
        {
          if (((JSONField)localObject4).serialize())
          {
            localObject1 = localObject2;
            if (((JSONField)localObject4).name().length() != 0)
              localObject1 = ((JSONField)localObject4).name();
          }
        }
        else
        {
          localObject2 = localObject1;
          if (paramMap != null)
          {
            localObject2 = (String)paramMap.get(localObject1);
            if (localObject2 == null);
          }
          else if (!localLinkedHashMap.containsKey(localObject2))
          {
            localLinkedHashMap.put(localObject2, new FieldInfo((String)localObject2, null, arrayOfMethod));
          }
        }
      }
    }
    Object localObject1 = new ArrayList();
    i = 0;
    paramMap = null;
    Object localObject2 = (JSONType)paramClass.getAnnotation(JSONType.class);
    paramClass = paramMap;
    if (localObject2 != null)
    {
      paramMap = ((JSONType)localObject2).orders();
      if ((paramMap == null) || (paramMap.length != localLinkedHashMap.size()))
        break label1163;
      int k = 1;
      int m = paramMap.length;
      j = 0;
      i = k;
      paramClass = paramMap;
      if (j < m)
      {
        if (localLinkedHashMap.containsKey(paramMap[j]))
          break label1154;
        i = 0;
      }
    }
    for (paramClass = paramMap; ; paramClass = paramMap)
    {
      if (i == 0)
        break label1171;
      j = paramClass.length;
      i = 0;
      while (i < j)
      {
        ((List)localObject1).add((FieldInfo)localLinkedHashMap.get(paramClass[i]));
        i += 1;
      }
      label1154: j += 1;
      break;
      label1163: i = 0;
    }
    label1171: paramClass = localLinkedHashMap.values().iterator();
    while (paramClass.hasNext())
      ((List)localObject1).add((FieldInfo)paramClass.next());
    if (paramBoolean)
      Collections.sort((List)localObject1);
    return localObject1;
  }

  public static Class<?> getClass(Type paramType)
  {
    if (paramType.getClass() == Class.class)
      return (Class)paramType;
    if ((paramType instanceof ParameterizedType))
      return getClass(((ParameterizedType)paramType).getRawType());
    return Object.class;
  }

  public static JSONField getSupperMethodAnnotation(Class<?> paramClass, Method paramMethod)
  {
    paramClass = paramClass.getInterfaces();
    int i1 = paramClass.length;
    int i = 0;
    while (i < i1)
    {
      Method[] arrayOfMethod = paramClass[i].getMethods();
      int i2 = arrayOfMethod.length;
      int j = 0;
      if (j < i2)
      {
        Object localObject = arrayOfMethod[j];
        if (!((Method)localObject).getName().equals(paramMethod.getName()));
        while (((Method)localObject).getParameterTypes().length != paramMethod.getParameterTypes().length)
        {
          j += 1;
          break;
        }
        int n = 1;
        int k = 0;
        while (true)
        {
          int m = n;
          if (k < ((Method)localObject).getParameterTypes().length)
          {
            if (!localObject.getParameterTypes()[k].equals(paramMethod.getParameterTypes()[k]))
              m = 0;
          }
          else
          {
            if (m == 0)
              break;
            localObject = (JSONField)((Method)localObject).getAnnotation(JSONField.class);
            if (localObject == null)
              break;
            return localObject;
          }
          k += 1;
        }
      }
      i += 1;
    }
    return null;
  }

  private static boolean isJSONTypeIgnore(Class<?> paramClass, String paramString)
  {
    Object localObject = (JSONType)paramClass.getAnnotation(JSONType.class);
    int i;
    if ((localObject != null) && (((JSONType)localObject).ignores() != null))
    {
      localObject = ((JSONType)localObject).ignores();
      int j = localObject.length;
      i = 0;
      if (i < j)
        if (!paramString.equalsIgnoreCase(localObject[i]));
    }
    while ((paramClass.getSuperclass() != Object.class) && (paramClass.getSuperclass() != null) && (isJSONTypeIgnore(paramClass.getSuperclass(), paramString)))
    {
      return true;
      i += 1;
      break;
    }
    return false;
  }

  public static Class<?> loadClass(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      localObject = null;
    Class localClass1;
    do
    {
      return localObject;
      localClass1 = (Class)mappings.get(paramString);
      localObject = localClass1;
    }
    while (localClass1 != null);
    if (paramString.charAt(0) == '[')
      return Array.newInstance(loadClass(paramString.substring(1)), 0).getClass();
    if ((paramString.startsWith("L")) && (paramString.endsWith(";")))
      return loadClass(paramString.substring(1, paramString.length() - 1));
    Object localObject = localClass1;
    try
    {
      ClassLoader localClassLoader = Thread.currentThread().getContextClassLoader();
      localObject = localClass1;
      if (localClassLoader != null)
      {
        localObject = localClass1;
        localClass1 = localClassLoader.loadClass(paramString);
        localObject = localClass1;
        addClassMapping(paramString, localClass1);
        return localClass1;
      }
    }
    catch (Throwable localThrowable)
    {
      try
      {
        Class localClass2 = Class.forName(paramString);
        localObject = localClass2;
        addClassMapping(paramString, localClass2);
        return localClass2;
      }
      catch (Throwable paramString)
      {
      }
    }
    return localObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.util.TypeUtils
 * JD-Core Version:    0.6.2
 */