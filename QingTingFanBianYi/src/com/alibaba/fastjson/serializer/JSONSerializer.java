package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.util.ServiceLoader;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.sql.Clob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

public class JSONSerializer
{
  private List<AfterFilter> afterFilters = null;
  private List<BeforeFilter> beforeFilters = null;
  private final SerializeConfig config;
  private SerialContext context;
  private DateFormat dateFormat;
  private String dateFormatPattern;
  private String indent = "\t";
  private int indentCount = 0;
  private List<NameFilter> nameFilters = null;
  private final SerializeWriter out;
  private List<PropertyFilter> propertyFilters = null;
  private List<PropertyPreFilter> propertyPreFilters = null;
  private IdentityHashMap<Object, SerialContext> references = null;
  private List<ValueFilter> valueFilters = null;

  public JSONSerializer()
  {
    this(new SerializeWriter(), SerializeConfig.getGlobalInstance());
  }

  @Deprecated
  public JSONSerializer(JSONSerializerMap paramJSONSerializerMap)
  {
    this(new SerializeWriter(), paramJSONSerializerMap);
  }

  public JSONSerializer(SerializeConfig paramSerializeConfig)
  {
    this(new SerializeWriter(), paramSerializeConfig);
  }

  public JSONSerializer(SerializeWriter paramSerializeWriter)
  {
    this(paramSerializeWriter, SerializeConfig.getGlobalInstance());
  }

  public JSONSerializer(SerializeWriter paramSerializeWriter, SerializeConfig paramSerializeConfig)
  {
    this.out = paramSerializeWriter;
    this.config = paramSerializeConfig;
  }

  public static final void write(SerializeWriter paramSerializeWriter, Object paramObject)
  {
    new JSONSerializer(paramSerializeWriter).write(paramObject);
  }

  public static final void write(Writer paramWriter, Object paramObject)
  {
    SerializeWriter localSerializeWriter = new SerializeWriter();
    try
    {
      new JSONSerializer(localSerializeWriter).write(paramObject);
      localSerializeWriter.writeTo(paramWriter);
      return;
    }
    catch (IOException paramWriter)
    {
      throw new JSONException(paramWriter.getMessage(), paramWriter);
    }
    finally
    {
      localSerializeWriter.close();
    }
    throw paramWriter;
  }

  public void close()
  {
    this.out.close();
  }

  public void config(SerializerFeature paramSerializerFeature, boolean paramBoolean)
  {
    this.out.config(paramSerializerFeature, paramBoolean);
  }

  public boolean containsReference(Object paramObject)
  {
    if (this.references == null)
      return false;
    return this.references.containsKey(paramObject);
  }

  public void decrementIdent()
  {
    this.indentCount -= 1;
  }

  public List<AfterFilter> getAfterFilters()
  {
    if (this.afterFilters == null)
      this.afterFilters = new ArrayList();
    return this.afterFilters;
  }

  public List<AfterFilter> getAfterFiltersDirect()
  {
    return this.afterFilters;
  }

  public List<BeforeFilter> getBeforeFilters()
  {
    if (this.beforeFilters == null)
      this.beforeFilters = new ArrayList();
    return this.beforeFilters;
  }

  public List<BeforeFilter> getBeforeFiltersDirect()
  {
    return this.beforeFilters;
  }

  public SerialContext getContext()
  {
    return this.context;
  }

  public DateFormat getDateFormat()
  {
    if ((this.dateFormat == null) && (this.dateFormatPattern != null))
      this.dateFormat = new SimpleDateFormat(this.dateFormatPattern);
    return this.dateFormat;
  }

  public String getDateFormatPattern()
  {
    if ((this.dateFormat instanceof SimpleDateFormat))
      return ((SimpleDateFormat)this.dateFormat).toPattern();
    return this.dateFormatPattern;
  }

  public int getIndentCount()
  {
    return this.indentCount;
  }

  public SerializeConfig getMapping()
  {
    return this.config;
  }

  public List<NameFilter> getNameFilters()
  {
    if (this.nameFilters == null)
      this.nameFilters = new ArrayList();
    return this.nameFilters;
  }

  public List<NameFilter> getNameFiltersDirect()
  {
    return this.nameFilters;
  }

  public ObjectSerializer getObjectWriter(Class<?> paramClass)
  {
    Object localObject4 = (ObjectSerializer)this.config.get(paramClass);
    Object localObject1 = localObject4;
    Object localObject5;
    Type localType;
    Object localObject2;
    if (localObject4 == null)
      try
      {
        localObject1 = ServiceLoader.load(AutowiredObjectSerializer.class, Thread.currentThread().getContextClassLoader()).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject4 = ((Iterator)localObject1).next();
          if ((localObject4 instanceof AutowiredObjectSerializer))
          {
            localObject4 = (AutowiredObjectSerializer)localObject4;
            localObject5 = ((AutowiredObjectSerializer)localObject4).getAutowiredFor().iterator();
            while (((Iterator)localObject5).hasNext())
            {
              localType = (Type)((Iterator)localObject5).next();
              this.config.put(localType, localObject4);
            }
          }
        }
      }
      catch (ClassCastException localClassCastException1)
      {
        localObject2 = (ObjectSerializer)this.config.get(paramClass);
      }
    localObject4 = localObject2;
    if (localObject2 == null)
    {
      localObject5 = JSON.class.getClassLoader();
      localObject4 = localObject2;
      if (localObject5 != Thread.currentThread().getContextClassLoader())
        try
        {
          localObject2 = ServiceLoader.load(AutowiredObjectSerializer.class, (ClassLoader)localObject5).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject4 = ((Iterator)localObject2).next();
            if ((localObject4 instanceof AutowiredObjectSerializer))
            {
              localObject4 = (AutowiredObjectSerializer)localObject4;
              localObject5 = ((AutowiredObjectSerializer)localObject4).getAutowiredFor().iterator();
              while (((Iterator)localObject5).hasNext())
              {
                localType = (Type)((Iterator)localObject5).next();
                this.config.put(localType, localObject4);
              }
            }
          }
        }
        catch (ClassCastException localClassCastException2)
        {
          localObject4 = (ObjectSerializer)this.config.get(paramClass);
        }
    }
    Object localObject3 = localObject4;
    if (localObject4 == null)
    {
      if (!Map.class.isAssignableFrom(paramClass))
        break label295;
      this.config.put(paramClass, MapSerializer.instance);
    }
    label794: 
    while (true)
    {
      localObject3 = (ObjectSerializer)this.config.get(paramClass);
      return localObject3;
      label295: if (List.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, ListSerializer.instance);
      }
      else if (Collection.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, CollectionSerializer.instance);
      }
      else if (Date.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, DateSerializer.instance);
      }
      else if (JSONAware.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, JSONAwareSerializer.instance);
      }
      else if (JSONStreamAware.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, JSONStreamAwareSerializer.instance);
      }
      else if ((paramClass.isEnum()) || ((paramClass.getSuperclass() != null) && (paramClass.getSuperclass().isEnum())))
      {
        this.config.put(paramClass, EnumSerializer.instance);
      }
      else if (paramClass.isArray())
      {
        localObject3 = paramClass.getComponentType();
        localObject4 = getObjectWriter((Class)localObject3);
        this.config.put(paramClass, new ArraySerializer((Class)localObject3, (ObjectSerializer)localObject4));
      }
      else if (Throwable.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, new ExceptionSerializer(paramClass));
      }
      else if (TimeZone.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, TimeZoneSerializer.instance);
      }
      else if (Appendable.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, AppendableSerializer.instance);
      }
      else if (Charset.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, CharsetSerializer.instance);
      }
      else if (Enumeration.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, EnumerationSeriliazer.instance);
      }
      else if (Calendar.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, CalendarSerializer.instance);
      }
      else if (Clob.class.isAssignableFrom(paramClass))
      {
        this.config.put(paramClass, ClobSeriliazer.instance);
      }
      else
      {
        int m = 0;
        int n = 0;
        localObject3 = paramClass.getInterfaces();
        int i1 = localObject3.length;
        int k = 0;
        while (true)
        {
          int i = m;
          int j = n;
          if (k < i1)
          {
            localObject4 = localObject3[k];
            if (!((Class)localObject4).getName().equals("net.sf.cglib.proxy.Factory"))
              break label762;
            i = 1;
            j = n;
          }
          while (true)
          {
            if ((i == 0) && (j == 0))
              break label794;
            localObject3 = getObjectWriter(paramClass.getSuperclass());
            this.config.put(paramClass, localObject3);
            return localObject3;
            label762: if (!((Class)localObject4).getName().equals("javassist.util.proxy.ProxyObject"))
              break;
            j = 1;
            i = m;
          }
          k += 1;
        }
        if (Proxy.isProxyClass(paramClass))
          this.config.put(paramClass, this.config.createJavaBeanSerializer(paramClass));
        else
          this.config.put(paramClass, this.config.createJavaBeanSerializer(paramClass));
      }
    }
  }

  public List<PropertyFilter> getPropertyFilters()
  {
    if (this.propertyFilters == null)
      this.propertyFilters = new ArrayList();
    return this.propertyFilters;
  }

  public List<PropertyFilter> getPropertyFiltersDirect()
  {
    return this.propertyFilters;
  }

  public List<PropertyPreFilter> getPropertyPreFilters()
  {
    if (this.propertyPreFilters == null)
      this.propertyPreFilters = new ArrayList();
    return this.propertyPreFilters;
  }

  public List<PropertyPreFilter> getPropertyPreFiltersDirect()
  {
    return this.propertyPreFilters;
  }

  public SerialContext getSerialContext(Object paramObject)
  {
    if (this.references == null)
      return null;
    return (SerialContext)this.references.get(paramObject);
  }

  public List<ValueFilter> getValueFilters()
  {
    if (this.valueFilters == null)
      this.valueFilters = new ArrayList();
    return this.valueFilters;
  }

  public List<ValueFilter> getValueFiltersDirect()
  {
    return this.valueFilters;
  }

  public SerializeWriter getWriter()
  {
    return this.out;
  }

  public void incrementIndent()
  {
    this.indentCount += 1;
  }

  public boolean isEnabled(SerializerFeature paramSerializerFeature)
  {
    return this.out.isEnabled(paramSerializerFeature);
  }

  public final boolean isWriteAsArray(Object paramObject, Type paramType)
  {
    return this.out.isEnabled(SerializerFeature.BeanToArray);
  }

  public final boolean isWriteClassName(Type paramType, Object paramObject)
  {
    if (!this.out.isEnabled(SerializerFeature.WriteClassName));
    while (true)
    {
      return false;
      if ((paramType == null) && (isEnabled(SerializerFeature.NotWriteRootClassName)))
        if (this.context.getParent() != null)
          break label47;
      label47: for (int i = 1; i == 0; i = 0)
        return true;
    }
  }

  public void popContext()
  {
    if (this.context != null)
      this.context = this.context.getParent();
  }

  public void println()
  {
    this.out.write('\n');
    int i = 0;
    while (i < this.indentCount)
    {
      this.out.write(this.indent);
      i += 1;
    }
  }

  public void setContext(SerialContext paramSerialContext)
  {
    this.context = paramSerialContext;
  }

  public void setContext(SerialContext paramSerialContext, Object paramObject1, Object paramObject2)
  {
    if (isEnabled(SerializerFeature.DisableCircularReferenceDetect))
      return;
    this.context = new SerialContext(paramSerialContext, paramObject1, paramObject2);
    if (this.references == null)
      this.references = new IdentityHashMap();
    this.references.put(paramObject1, this.context);
  }

  public void setContext(Object paramObject1, Object paramObject2)
  {
    setContext(this.context, paramObject1, paramObject2);
  }

  public void setDateFormat(String paramString)
  {
    this.dateFormatPattern = paramString;
    if (this.dateFormat != null)
      this.dateFormat = null;
  }

  public void setDateFormat(DateFormat paramDateFormat)
  {
    this.dateFormat = paramDateFormat;
    if (this.dateFormatPattern != null)
      this.dateFormatPattern = null;
  }

  public String toString()
  {
    return this.out.toString();
  }

  public final void write(Object paramObject)
  {
    if (paramObject == null)
    {
      this.out.writeNull();
      return;
    }
    ObjectSerializer localObjectSerializer = getObjectWriter(paramObject.getClass());
    try
    {
      localObjectSerializer.write(this, paramObject, null, null);
      return;
    }
    catch (IOException paramObject)
    {
    }
    throw new JSONException(paramObject.getMessage(), paramObject);
  }

  public final void write(String paramString)
  {
    StringSerializer.instance.write(this, paramString);
  }

  protected final void writeKeyValue(char paramChar, String paramString, Object paramObject)
  {
    if (paramChar != 0)
      this.out.write(paramChar);
    this.out.writeFieldName(paramString);
    write(paramObject);
  }

  public void writeNull()
  {
    this.out.writeNull();
  }

  public void writeReference(Object paramObject)
  {
    SerialContext localSerialContext1 = getContext();
    if (paramObject == localSerialContext1.getObject())
    {
      this.out.write("{\"$ref\":\"@\"}");
      return;
    }
    SerialContext localSerialContext2 = localSerialContext1.getParent();
    if ((localSerialContext2 != null) && (paramObject == localSerialContext2.getObject()))
    {
      this.out.write("{\"$ref\":\"..\"}");
      return;
    }
    while (true)
    {
      if (localSerialContext1.getParent() == null)
      {
        if (paramObject != localSerialContext1.getObject())
          break;
        this.out.write("{\"$ref\":\"$\"}");
        return;
      }
      localSerialContext1 = localSerialContext1.getParent();
    }
    paramObject = getSerialContext(paramObject).getPath();
    this.out.write("{\"$ref\":\"");
    this.out.write(paramObject);
    this.out.write("\"}");
  }

  public final void writeWithFieldName(Object paramObject1, Object paramObject2)
  {
    writeWithFieldName(paramObject1, paramObject2, null);
  }

  public final void writeWithFieldName(Object paramObject1, Object paramObject2, Type paramType)
  {
    if (paramObject1 == null);
    try
    {
      this.out.writeNull();
      return;
      getObjectWriter(paramObject1.getClass()).write(this, paramObject1, paramObject2, paramType);
      return;
    }
    catch (IOException paramObject1)
    {
    }
    throw new JSONException(paramObject1.getMessage(), paramObject1);
  }

  public final void writeWithFormat(Object paramObject, String paramString)
  {
    if ((paramObject instanceof Date))
    {
      DateFormat localDateFormat = getDateFormat();
      Object localObject = localDateFormat;
      if (localDateFormat == null)
        localObject = new SimpleDateFormat(paramString);
      paramObject = ((DateFormat)localObject).format((Date)paramObject);
      this.out.writeString(paramObject);
      return;
    }
    write(paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.JSONSerializer
 * JD-Core Version:    0.6.2
 */