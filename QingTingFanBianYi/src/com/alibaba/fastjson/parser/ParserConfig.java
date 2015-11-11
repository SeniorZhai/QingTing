package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.asm.ASMException;
import com.alibaba.fastjson.parser.deserializer.ASMDeserializerFactory;
import com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer.InnerJavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ArrayDeserializer;
import com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.AtomicIntegerArrayDeserializer;
import com.alibaba.fastjson.parser.deserializer.AtomicLongArrayDeserializer;
import com.alibaba.fastjson.parser.deserializer.AutowiredObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.BigDecimalDeserializer;
import com.alibaba.fastjson.parser.deserializer.BigIntegerDeserializer;
import com.alibaba.fastjson.parser.deserializer.BooleanDeserializer;
import com.alibaba.fastjson.parser.deserializer.BooleanFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.CalendarDeserializer;
import com.alibaba.fastjson.parser.deserializer.CharArrayDeserializer;
import com.alibaba.fastjson.parser.deserializer.CharacterDeserializer;
import com.alibaba.fastjson.parser.deserializer.CharsetDeserializer;
import com.alibaba.fastjson.parser.deserializer.ClassDerializer;
import com.alibaba.fastjson.parser.deserializer.CollectionDeserializer;
import com.alibaba.fastjson.parser.deserializer.ColorDeserializer;
import com.alibaba.fastjson.parser.deserializer.DateDeserializer;
import com.alibaba.fastjson.parser.deserializer.DateFormatDeserializer;
import com.alibaba.fastjson.parser.deserializer.DefaultFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.EnumDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FileDeserializer;
import com.alibaba.fastjson.parser.deserializer.FloatDeserializer;
import com.alibaba.fastjson.parser.deserializer.FontDeserializer;
import com.alibaba.fastjson.parser.deserializer.InetAddressDeserializer;
import com.alibaba.fastjson.parser.deserializer.InetSocketAddressDeserializer;
import com.alibaba.fastjson.parser.deserializer.IntegerDeserializer;
import com.alibaba.fastjson.parser.deserializer.IntegerFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.JSONArrayDeserializer;
import com.alibaba.fastjson.parser.deserializer.JSONObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.LocaleDeserializer;
import com.alibaba.fastjson.parser.deserializer.LongDeserializer;
import com.alibaba.fastjson.parser.deserializer.LongFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.NumberDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.PatternDeserializer;
import com.alibaba.fastjson.parser.deserializer.PointDeserializer;
import com.alibaba.fastjson.parser.deserializer.RectangleDeserializer;
import com.alibaba.fastjson.parser.deserializer.ReferenceDeserializer;
import com.alibaba.fastjson.parser.deserializer.SqlDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer;
import com.alibaba.fastjson.parser.deserializer.StringDeserializer;
import com.alibaba.fastjson.parser.deserializer.StringFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimeDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimeZoneDeserializer;
import com.alibaba.fastjson.parser.deserializer.TimestampDeserializer;
import com.alibaba.fastjson.parser.deserializer.URIDeserializer;
import com.alibaba.fastjson.parser.deserializer.URLDeserializer;
import com.alibaba.fastjson.parser.deserializer.UUIDDeserializer;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.DeserializeBeanInfo;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.IdentityHashMap;
import com.alibaba.fastjson.util.ServiceLoader;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public class ParserConfig
{
  private static ParserConfig global = new ParserConfig();
  private boolean asmEnable;
  private final IdentityHashMap<Type, ObjectDeserializer> derializers = new IdentityHashMap();
  private final Set<Class<?>> primitiveClasses = new HashSet();
  protected final SymbolTable symbolTable;

  public ParserConfig()
  {
    boolean bool;
    if (!ASMUtils.isAndroid())
      bool = true;
    while (true)
    {
      this.asmEnable = bool;
      this.symbolTable = new SymbolTable();
      this.primitiveClasses.add(Boolean.TYPE);
      this.primitiveClasses.add(Boolean.class);
      this.primitiveClasses.add(Character.TYPE);
      this.primitiveClasses.add(Character.class);
      this.primitiveClasses.add(Byte.TYPE);
      this.primitiveClasses.add(Byte.class);
      this.primitiveClasses.add(Short.TYPE);
      this.primitiveClasses.add(Short.class);
      this.primitiveClasses.add(Integer.TYPE);
      this.primitiveClasses.add(Integer.class);
      this.primitiveClasses.add(Long.TYPE);
      this.primitiveClasses.add(Long.class);
      this.primitiveClasses.add(Float.TYPE);
      this.primitiveClasses.add(Float.class);
      this.primitiveClasses.add(Double.TYPE);
      this.primitiveClasses.add(Double.class);
      this.primitiveClasses.add(BigInteger.class);
      this.primitiveClasses.add(BigDecimal.class);
      this.primitiveClasses.add(String.class);
      this.primitiveClasses.add(java.util.Date.class);
      this.primitiveClasses.add(java.sql.Date.class);
      this.primitiveClasses.add(Time.class);
      this.primitiveClasses.add(Timestamp.class);
      this.derializers.put(SimpleDateFormat.class, DateFormatDeserializer.instance);
      this.derializers.put(Timestamp.class, TimestampDeserializer.instance);
      this.derializers.put(java.sql.Date.class, SqlDateDeserializer.instance);
      this.derializers.put(Time.class, TimeDeserializer.instance);
      this.derializers.put(java.util.Date.class, DateDeserializer.instance);
      this.derializers.put(Calendar.class, CalendarDeserializer.instance);
      this.derializers.put(JSONObject.class, JSONObjectDeserializer.instance);
      this.derializers.put(JSONArray.class, JSONArrayDeserializer.instance);
      this.derializers.put(Map.class, MapDeserializer.instance);
      this.derializers.put(HashMap.class, MapDeserializer.instance);
      this.derializers.put(LinkedHashMap.class, MapDeserializer.instance);
      this.derializers.put(TreeMap.class, MapDeserializer.instance);
      this.derializers.put(ConcurrentMap.class, MapDeserializer.instance);
      this.derializers.put(ConcurrentHashMap.class, MapDeserializer.instance);
      this.derializers.put(Collection.class, CollectionDeserializer.instance);
      this.derializers.put(List.class, CollectionDeserializer.instance);
      this.derializers.put(ArrayList.class, CollectionDeserializer.instance);
      this.derializers.put(Object.class, JavaObjectDeserializer.instance);
      this.derializers.put(String.class, StringDeserializer.instance);
      this.derializers.put(Character.TYPE, CharacterDeserializer.instance);
      this.derializers.put(Character.class, CharacterDeserializer.instance);
      this.derializers.put(Byte.TYPE, NumberDeserializer.instance);
      this.derializers.put(Byte.class, NumberDeserializer.instance);
      this.derializers.put(Short.TYPE, NumberDeserializer.instance);
      this.derializers.put(Short.class, NumberDeserializer.instance);
      this.derializers.put(Integer.TYPE, IntegerDeserializer.instance);
      this.derializers.put(Integer.class, IntegerDeserializer.instance);
      this.derializers.put(Long.TYPE, LongDeserializer.instance);
      this.derializers.put(Long.class, LongDeserializer.instance);
      this.derializers.put(BigInteger.class, BigIntegerDeserializer.instance);
      this.derializers.put(BigDecimal.class, BigDecimalDeserializer.instance);
      this.derializers.put(Float.TYPE, FloatDeserializer.instance);
      this.derializers.put(Float.class, FloatDeserializer.instance);
      this.derializers.put(Double.TYPE, NumberDeserializer.instance);
      this.derializers.put(Double.class, NumberDeserializer.instance);
      this.derializers.put(Boolean.TYPE, BooleanDeserializer.instance);
      this.derializers.put(Boolean.class, BooleanDeserializer.instance);
      this.derializers.put(Class.class, ClassDerializer.instance);
      this.derializers.put([C.class, CharArrayDeserializer.instance);
      this.derializers.put(AtomicBoolean.class, BooleanDeserializer.instance);
      this.derializers.put(AtomicInteger.class, IntegerDeserializer.instance);
      this.derializers.put(AtomicLong.class, LongDeserializer.instance);
      this.derializers.put(AtomicReference.class, ReferenceDeserializer.instance);
      this.derializers.put(WeakReference.class, ReferenceDeserializer.instance);
      this.derializers.put(SoftReference.class, ReferenceDeserializer.instance);
      this.derializers.put(UUID.class, UUIDDeserializer.instance);
      this.derializers.put(TimeZone.class, TimeZoneDeserializer.instance);
      this.derializers.put(Locale.class, LocaleDeserializer.instance);
      this.derializers.put(InetAddress.class, InetAddressDeserializer.instance);
      this.derializers.put(Inet4Address.class, InetAddressDeserializer.instance);
      this.derializers.put(Inet6Address.class, InetAddressDeserializer.instance);
      this.derializers.put(InetSocketAddress.class, InetSocketAddressDeserializer.instance);
      this.derializers.put(File.class, FileDeserializer.instance);
      this.derializers.put(URI.class, URIDeserializer.instance);
      this.derializers.put(URL.class, URLDeserializer.instance);
      this.derializers.put(Pattern.class, PatternDeserializer.instance);
      this.derializers.put(Charset.class, CharsetDeserializer.instance);
      this.derializers.put(Number.class, NumberDeserializer.instance);
      this.derializers.put(AtomicIntegerArray.class, AtomicIntegerArrayDeserializer.instance);
      this.derializers.put(AtomicLongArray.class, AtomicLongArrayDeserializer.instance);
      this.derializers.put(StackTraceElement.class, StackTraceElementDeserializer.instance);
      this.derializers.put(Serializable.class, JavaObjectDeserializer.instance);
      this.derializers.put(Cloneable.class, JavaObjectDeserializer.instance);
      this.derializers.put(Comparable.class, JavaObjectDeserializer.instance);
      this.derializers.put(Closeable.class, JavaObjectDeserializer.instance);
      try
      {
        this.derializers.put(Class.forName("java.awt.Point"), PointDeserializer.instance);
        this.derializers.put(Class.forName("java.awt.Font"), FontDeserializer.instance);
        this.derializers.put(Class.forName("java.awt.Rectangle"), RectangleDeserializer.instance);
        this.derializers.put(Class.forName("java.awt.Color"), ColorDeserializer.instance);
        return;
        bool = false;
      }
      catch (Throwable localThrowable)
      {
      }
    }
  }

  public static Field getField(Class<?> paramClass, String paramString)
  {
    Object localObject2 = getField0(paramClass, paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = getField0(paramClass, "_" + paramString);
    localObject2 = localObject1;
    if (localObject1 == null)
      localObject2 = getField0(paramClass, "m_" + paramString);
    return localObject2;
  }

  private static Field getField0(Class<?> paramClass, String paramString)
  {
    Field[] arrayOfField = paramClass.getDeclaredFields();
    int j = arrayOfField.length;
    int i = 0;
    while (i < j)
    {
      Field localField = arrayOfField[i];
      if (paramString.equals(localField.getName()))
        return localField;
      i += 1;
    }
    if ((paramClass.getSuperclass() != null) && (paramClass.getSuperclass() != Object.class))
      return getField(paramClass.getSuperclass(), paramString);
    return null;
  }

  public static ParserConfig getGlobalInstance()
  {
    return global;
  }

  public FieldDeserializer createFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    boolean bool = this.asmEnable;
    if (!Modifier.isPublic(paramClass.getModifiers()))
      bool = false;
    if (paramFieldInfo.getFieldClass() == Class.class)
      bool = false;
    if (ASMDeserializerFactory.getInstance().isExternalClass(paramClass))
      bool = false;
    if (!bool)
      return createFieldDeserializerWithoutASM(paramParserConfig, paramClass, paramFieldInfo);
    try
    {
      FieldDeserializer localFieldDeserializer = ASMDeserializerFactory.getInstance().createFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
      return localFieldDeserializer;
    }
    catch (Throwable localThrowable)
    {
    }
    return createFieldDeserializerWithoutASM(paramParserConfig, paramClass, paramFieldInfo);
  }

  public FieldDeserializer createFieldDeserializerWithoutASM(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    Class localClass = paramFieldInfo.getFieldClass();
    if ((localClass == Boolean.TYPE) || (localClass == Boolean.class))
      return new BooleanFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
    if ((localClass == Integer.TYPE) || (localClass == Integer.class))
      return new IntegerFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
    if ((localClass == Long.TYPE) || (localClass == Long.class))
      return new LongFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
    if (localClass == String.class)
      return new StringFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
    if ((localClass == List.class) || (localClass == ArrayList.class))
      return new ArrayListTypeFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
    return new DefaultFieldDeserializer(paramParserConfig, paramClass, paramFieldInfo);
  }

  public ObjectDeserializer createJavaBeanDeserializer(Class<?> paramClass, Type paramType)
  {
    boolean bool2 = this.asmEnable;
    boolean bool1 = bool2;
    if (bool2)
    {
      bool1 = bool2;
      if (!Modifier.isPublic(paramClass.getModifiers()))
        bool1 = false;
    }
    if (paramClass.getTypeParameters().length != 0)
      bool1 = false;
    bool2 = bool1;
    if (ASMDeserializerFactory.getInstance().isExternalClass(paramClass))
      bool2 = false;
    bool1 = bool2;
    Object localObject1;
    if (bool2)
    {
      bool1 = bool2;
      if (paramClass.isInterface())
        bool1 = false;
      localObject1 = DeserializeBeanInfo.computeSetters(paramClass, paramType);
      if (((DeserializeBeanInfo)localObject1).getFieldList().size() > 200)
        bool1 = false;
      bool2 = bool1;
      if (((DeserializeBeanInfo)localObject1).getDefaultConstructor() == null)
      {
        bool2 = bool1;
        if (!paramClass.isInterface())
          bool2 = false;
      }
      localObject1 = ((DeserializeBeanInfo)localObject1).getFieldList().iterator();
    }
    while (true)
    {
      bool1 = bool2;
      Object localObject2;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (FieldInfo)((Iterator)localObject1).next();
        if (!((FieldInfo)localObject2).isGetOnly())
          break label230;
      }
      for (bool1 = false; ; bool1 = false)
      {
        bool2 = bool1;
        if (bool1)
        {
          bool2 = bool1;
          if (paramClass.isMemberClass())
          {
            bool2 = bool1;
            if (!Modifier.isStatic(paramClass.getModifiers()))
              bool2 = false;
          }
        }
        if (bool2)
          break label279;
        return new JavaBeanDeserializer(this, paramClass, paramType);
        label230: localObject2 = ((FieldInfo)localObject2).getFieldClass();
        if (Modifier.isPublic(((Class)localObject2).getModifiers()))
          break;
      }
      if ((((Class)localObject2).isMemberClass()) && (!Modifier.isStatic(((Class)localObject2).getModifiers())))
        bool2 = false;
    }
    try
    {
      label279: localObject1 = ASMDeserializerFactory.getInstance().createJavaBeanDeserializer(this, paramClass, paramType);
      return localObject1;
    }
    catch (ASMException localASMException)
    {
      return new JavaBeanDeserializer(this, paramClass, paramType);
    }
    catch (Exception paramType)
    {
    }
    throw new JSONException("create asm deserializer error, " + paramClass.getName(), paramType);
  }

  public IdentityHashMap<Type, ObjectDeserializer> getDerializers()
  {
    return this.derializers;
  }

  public ObjectDeserializer getDeserializer(FieldInfo paramFieldInfo)
  {
    return getDeserializer(paramFieldInfo.getFieldClass(), paramFieldInfo.getFieldType());
  }

  public ObjectDeserializer getDeserializer(Class<?> paramClass, Type paramType)
  {
    Object localObject1 = (ObjectDeserializer)this.derializers.get(paramType);
    if (localObject1 != null)
      return localObject1;
    localObject1 = paramType;
    if (paramType == null)
      localObject1 = paramClass;
    paramType = (ObjectDeserializer)this.derializers.get(localObject1);
    if (paramType != null)
      return paramType;
    Object localObject2 = (JSONType)paramClass.getAnnotation(JSONType.class);
    if (localObject2 != null)
    {
      localObject2 = ((JSONType)localObject2).mappingTo();
      if (localObject2 != Void.class)
        return getDeserializer((Class)localObject2, (Type)localObject2);
    }
    if (((localObject1 instanceof WildcardType)) || ((localObject1 instanceof TypeVariable)) || ((localObject1 instanceof ParameterizedType)))
      paramType = (ObjectDeserializer)this.derializers.get(paramClass);
    if (paramType != null)
      return paramType;
    paramType = Thread.currentThread().getContextClassLoader();
    try
    {
      paramType = ServiceLoader.load(AutowiredObjectDeserializer.class, paramType).iterator();
      while (paramType.hasNext())
      {
        localObject2 = (AutowiredObjectDeserializer)paramType.next();
        Iterator localIterator = ((AutowiredObjectDeserializer)localObject2).getAutowiredFor().iterator();
        while (localIterator.hasNext())
        {
          Type localType = (Type)localIterator.next();
          this.derializers.put(localType, localObject2);
        }
      }
    }
    catch (Exception paramType)
    {
      paramType = (ObjectDeserializer)this.derializers.get(localObject1);
      if (paramType != null)
        return paramType;
      if (!paramClass.isEnum())
        break label260;
    }
    paramClass = new EnumDeserializer(paramClass);
    while (true)
    {
      putDeserializer((Type)localObject1, paramClass);
      return paramClass;
      label260: if (paramClass.isArray())
        return ArrayDeserializer.instance;
      if ((paramClass == Set.class) || (paramClass == HashSet.class) || (paramClass == Collection.class) || (paramClass == List.class) || (paramClass == ArrayList.class))
        paramClass = CollectionDeserializer.instance;
      else if (Collection.class.isAssignableFrom(paramClass))
        paramClass = CollectionDeserializer.instance;
      else if (Map.class.isAssignableFrom(paramClass))
        paramClass = MapDeserializer.instance;
      else if (Throwable.class.isAssignableFrom(paramClass))
        paramClass = new ThrowableDeserializer(this, paramClass);
      else
        paramClass = createJavaBeanDeserializer(paramClass, (Type)localObject1);
    }
  }

  public ObjectDeserializer getDeserializer(Type paramType)
  {
    Object localObject = (ObjectDeserializer)this.derializers.get(paramType);
    if (localObject != null)
      return localObject;
    if ((paramType instanceof Class))
      return getDeserializer((Class)paramType, paramType);
    if ((paramType instanceof ParameterizedType))
    {
      localObject = ((ParameterizedType)paramType).getRawType();
      if ((localObject instanceof Class))
        return getDeserializer((Class)localObject, paramType);
      return getDeserializer((Type)localObject);
    }
    return JavaObjectDeserializer.instance;
  }

  public Map<String, FieldDeserializer> getFieldDeserializers(Class<?> paramClass)
  {
    paramClass = getDeserializer(paramClass);
    if ((paramClass instanceof JavaBeanDeserializer))
      return ((JavaBeanDeserializer)paramClass).getFieldDeserializerMap();
    if ((paramClass instanceof ASMJavaBeanDeserializer))
      return ((ASMJavaBeanDeserializer)paramClass).getInnterSerializer().getFieldDeserializerMap();
    return Collections.emptyMap();
  }

  public SymbolTable getSymbolTable()
  {
    return this.symbolTable;
  }

  public boolean isAsmEnable()
  {
    return this.asmEnable;
  }

  public boolean isPrimitive(Class<?> paramClass)
  {
    return this.primitiveClasses.contains(paramClass);
  }

  public void putDeserializer(Type paramType, ObjectDeserializer paramObjectDeserializer)
  {
    this.derializers.put(paramType, paramObjectDeserializer);
  }

  public void setAsmEnable(boolean paramBoolean)
  {
    this.asmEnable = paramBoolean;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.ParserConfig
 * JD-Core Version:    0.6.2
 */