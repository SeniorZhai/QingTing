package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.util.FieldInfo;

final class NumberFieldSerializer extends FieldSerializer
{
  public NumberFieldSerializer(FieldInfo paramFieldInfo)
  {
    super(paramFieldInfo);
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
    paramJSONSerializer = paramJSONSerializer.getWriter();
    if (paramObject == null)
    {
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero))
      {
        paramJSONSerializer.write('0');
        return;
      }
      paramJSONSerializer.writeNull();
      return;
    }
    paramJSONSerializer.append(paramObject.toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.NumberFieldSerializer
 * JD-Core Version:    0.6.2
 */