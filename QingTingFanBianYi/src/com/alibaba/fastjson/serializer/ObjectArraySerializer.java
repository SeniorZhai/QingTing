package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;

public class ObjectArraySerializer
  implements ObjectSerializer
{
  public static final ObjectArraySerializer instance = new ObjectArraySerializer();

  public final void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    SerializeWriter localSerializeWriter = paramJSONSerializer.getWriter();
    Object[] arrayOfObject = (Object[])paramObject1;
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
    int j = arrayOfObject.length;
    int k = j - 1;
    if (k == -1)
    {
      localSerializeWriter.append("[]");
      return;
    }
    SerialContext localSerialContext = paramJSONSerializer.getContext();
    paramJSONSerializer.setContext(localSerialContext, paramObject1, paramObject2);
    paramObject2 = null;
    paramObject1 = null;
    while (true)
    {
      int i;
      Object localObject;
      try
      {
        localSerializeWriter.append('[');
        if (localSerializeWriter.isEnabled(SerializerFeature.PrettyFormat))
        {
          paramJSONSerializer.incrementIndent();
          paramJSONSerializer.println();
          i = 0;
          if (i < j)
          {
            if (i != 0)
            {
              localSerializeWriter.write(',');
              paramJSONSerializer.println();
            }
            paramJSONSerializer.write(arrayOfObject[i]);
            i += 1;
            continue;
          }
          paramJSONSerializer.decrementIdent();
          paramJSONSerializer.println();
          localSerializeWriter.write(']');
          return;
        }
        i = 0;
        if (i >= k)
          break label300;
        localObject = arrayOfObject[i];
        if (localObject == null)
        {
          localSerializeWriter.append("null,");
        }
        else if (paramJSONSerializer.containsReference(localObject))
        {
          paramJSONSerializer.writeReference(localObject);
          localSerializeWriter.append(',');
        }
      }
      finally
      {
        paramJSONSerializer.setContext(localSerialContext);
      }
      paramType = localObject.getClass();
      if (paramType == paramObject2)
      {
        paramObject1.write(paramJSONSerializer, localObject, null, null);
      }
      else
      {
        paramObject2 = paramType;
        paramObject1 = paramJSONSerializer.getObjectWriter(paramType);
        paramObject1.write(paramJSONSerializer, localObject, null, null);
        continue;
        label300: paramObject1 = arrayOfObject[k];
        if (paramObject1 == null)
        {
          localSerializeWriter.append("null]");
          paramJSONSerializer.setContext(localSerialContext);
          return;
        }
        if (paramJSONSerializer.containsReference(paramObject1))
          paramJSONSerializer.writeReference(paramObject1);
        while (true)
        {
          localSerializeWriter.append(']');
          break;
          paramJSONSerializer.writeWithFieldName(paramObject1, Integer.valueOf(k));
        }
        i += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ObjectArraySerializer
 * JD-Core Version:    0.6.2
 */