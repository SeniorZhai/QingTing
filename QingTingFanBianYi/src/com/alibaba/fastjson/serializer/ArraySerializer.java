package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ArraySerializer
  implements ObjectSerializer
{
  private final ObjectSerializer compObjectSerializer;
  private final Class<?> componentType;

  public ArraySerializer(Class<?> paramClass, ObjectSerializer paramObjectSerializer)
  {
    this.componentType = paramClass;
    this.compObjectSerializer = paramObjectSerializer;
  }

  public final void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    SerializeWriter localSerializeWriter = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      if (localSerializeWriter.isEnabled(SerializerFeature.WriteNullListAsEmpty))
      {
        localSerializeWriter.write("[]");
        return;
      }
      localSerializeWriter.writeNull();
      return;
    }
    Object[] arrayOfObject = (Object[])paramObject1;
    int j = arrayOfObject.length;
    paramType = paramJSONSerializer.getContext();
    paramJSONSerializer.setContext(paramType, paramObject1, paramObject2);
    while (true)
    {
      int i;
      try
      {
        localSerializeWriter.append('[');
        i = 0;
        if (i >= j)
          break label179;
        if (i != 0)
          localSerializeWriter.append(',');
        paramObject1 = arrayOfObject[i];
        if (paramObject1 == null)
          localSerializeWriter.append("null");
        else if (paramObject1.getClass() == this.componentType)
          this.compObjectSerializer.write(paramJSONSerializer, paramObject1, Integer.valueOf(i), null);
      }
      finally
      {
        paramJSONSerializer.setContext(paramType);
      }
      paramJSONSerializer.getObjectWriter(paramObject1.getClass()).write(paramJSONSerializer, paramObject1, Integer.valueOf(i), null);
      break label194;
      label179: localSerializeWriter.append(']');
      paramJSONSerializer.setContext(paramType);
      return;
      label194: i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ArraySerializer
 * JD-Core Version:    0.6.2
 */