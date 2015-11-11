package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import java.util.Collection;

public class ObjectFieldSerializer extends FieldSerializer
{
  private ObjectSerializer fieldSerializer;
  private String format;
  private Class<?> runtimeFieldClass;
  boolean writeEnumUsingToString = false;
  boolean writeNullBooleanAsFalse = false;
  boolean writeNullListAsEmpty = false;
  boolean writeNullStringAsEmpty = false;
  private boolean writeNumberAsZero = false;

  public ObjectFieldSerializer(FieldInfo paramFieldInfo)
  {
    super(paramFieldInfo);
    paramFieldInfo = (JSONField)paramFieldInfo.getAnnotation(JSONField.class);
    if (paramFieldInfo != null)
    {
      this.format = paramFieldInfo.format();
      if (this.format.trim().length() == 0)
        this.format = null;
      paramFieldInfo = paramFieldInfo.serialzeFeatures();
      int j = paramFieldInfo.length;
      int i = 0;
      if (i < j)
      {
        Object localObject = paramFieldInfo[i];
        if (localObject == SerializerFeature.WriteNullNumberAsZero)
          this.writeNumberAsZero = true;
        while (true)
        {
          i += 1;
          break;
          if (localObject == SerializerFeature.WriteNullStringAsEmpty)
            this.writeNullStringAsEmpty = true;
          else if (localObject == SerializerFeature.WriteNullBooleanAsFalse)
            this.writeNullBooleanAsFalse = true;
          else if (localObject == SerializerFeature.WriteNullListAsEmpty)
            this.writeNullListAsEmpty = true;
          else if (localObject == SerializerFeature.WriteEnumUsingToString)
            this.writeEnumUsingToString = true;
        }
      }
    }
  }

  public void writeProperty(JSONSerializer paramJSONSerializer, Object paramObject)
    throws Exception
  {
    writePrefix(paramJSONSerializer);
    writeValue(paramJSONSerializer, paramObject);
  }

  public void writeValue(JSONSerializer paramJSONSerializer, Object paramObject)
    throws Exception
  {
    if (this.format != null)
    {
      paramJSONSerializer.writeWithFormat(paramObject, this.format);
      return;
    }
    if (this.fieldSerializer == null)
      if (paramObject != null)
        break label84;
    label84: for (this.runtimeFieldClass = this.fieldInfo.getFieldClass(); ; this.runtimeFieldClass = paramObject.getClass())
    {
      this.fieldSerializer = paramJSONSerializer.getObjectWriter(this.runtimeFieldClass);
      if (paramObject != null)
        break label196;
      if ((!this.writeNumberAsZero) || (!Number.class.isAssignableFrom(this.runtimeFieldClass)))
        break;
      paramJSONSerializer.getWriter().write('0');
      return;
    }
    if ((this.writeNullStringAsEmpty) && (String.class == this.runtimeFieldClass))
    {
      paramJSONSerializer.getWriter().write("\"\"");
      return;
    }
    if ((this.writeNullBooleanAsFalse) && (Boolean.class == this.runtimeFieldClass))
    {
      paramJSONSerializer.getWriter().write("false");
      return;
    }
    if ((this.writeNullListAsEmpty) && (Collection.class.isAssignableFrom(this.runtimeFieldClass)))
    {
      paramJSONSerializer.getWriter().write("[]");
      return;
    }
    this.fieldSerializer.write(paramJSONSerializer, null, this.fieldInfo.getName(), null);
    return;
    label196: if ((this.writeEnumUsingToString == true) && (this.runtimeFieldClass.isEnum()))
    {
      paramJSONSerializer.getWriter().writeString(((Enum)paramObject).name());
      return;
    }
    Class localClass = paramObject.getClass();
    if (localClass == this.runtimeFieldClass)
    {
      this.fieldSerializer.write(paramJSONSerializer, paramObject, this.fieldInfo.getName(), this.fieldInfo.getFieldType());
      return;
    }
    paramJSONSerializer.getObjectWriter(localClass).write(paramJSONSerializer, paramObject, this.fieldInfo.getName(), this.fieldInfo.getFieldType());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ObjectFieldSerializer
 * JD-Core Version:    0.6.2
 */