package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public final class ListSerializer
  implements ObjectSerializer
{
  public static final ListSerializer instance = new ListSerializer();

  public final void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    boolean bool = paramJSONSerializer.isEnabled(SerializerFeature.WriteClassName);
    SerializeWriter localSerializeWriter = paramJSONSerializer.getWriter();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (bool)
    {
      localObject1 = localObject2;
      if ((paramType instanceof ParameterizedType))
        localObject1 = ((ParameterizedType)paramType).getActualTypeArguments()[0];
    }
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
    localObject2 = (List)paramObject1;
    int j = ((List)localObject2).size();
    int k = j - 1;
    if (k == -1)
    {
      localSerializeWriter.append("[]");
      return;
    }
    paramType = paramJSONSerializer.getContext();
    paramJSONSerializer.setContext(paramType, paramObject1, paramObject2);
    int i;
    Object localObject3;
    Object localObject4;
    if (j > 1)
    {
      try
      {
        if (!localSerializeWriter.isEnabled(SerializerFeature.PrettyFormat))
          break label310;
        localSerializeWriter.append('[');
        paramJSONSerializer.incrementIndent();
        i = 0;
        if (i >= j)
          break label287;
        if (i != 0)
          localSerializeWriter.append(',');
        paramJSONSerializer.println();
        localObject3 = ((List)localObject2).get(i);
        if (localObject3 != null)
          if (paramJSONSerializer.containsReference(localObject3))
          {
            paramJSONSerializer.writeReference(localObject3);
          }
          else
          {
            localObject4 = paramJSONSerializer.getObjectWriter(localObject3.getClass());
            paramJSONSerializer.setContext(new SerialContext(paramType, paramObject1, paramObject2));
            ((ObjectSerializer)localObject4).write(paramJSONSerializer, localObject3, Integer.valueOf(i), (Type)localObject1);
          }
      }
      finally
      {
        paramJSONSerializer.setContext(paramType);
      }
      paramJSONSerializer.getWriter().writeNull();
      break label694;
      label287: paramJSONSerializer.decrementIdent();
      paramJSONSerializer.println();
      localSerializeWriter.append(']');
      paramJSONSerializer.setContext(paramType);
    }
    else
    {
      label310: localSerializeWriter.append('[');
      i = 0;
    }
    while (true)
    {
      if (i < k)
      {
        localObject3 = ((List)localObject2).get(i);
        if (localObject3 == null)
        {
          localSerializeWriter.append("null,");
        }
        else
        {
          localObject4 = localObject3.getClass();
          if (localObject4 == Integer.class)
          {
            localSerializeWriter.writeIntAndChar(((Integer)localObject3).intValue(), ',');
          }
          else if (localObject4 == Long.class)
          {
            long l = ((Long)localObject3).longValue();
            if (bool)
            {
              localSerializeWriter.writeLongAndChar(l, 'L');
              localSerializeWriter.write(',');
            }
            else
            {
              localSerializeWriter.writeLongAndChar(l, ',');
            }
          }
          else
          {
            paramJSONSerializer.setContext(new SerialContext(paramType, paramObject1, paramObject2));
            if (paramJSONSerializer.containsReference(localObject3))
              paramJSONSerializer.writeReference(localObject3);
            while (true)
            {
              localSerializeWriter.append(',');
              break;
              paramJSONSerializer.getObjectWriter(localObject3.getClass()).write(paramJSONSerializer, localObject3, Integer.valueOf(i), (Type)localObject1);
            }
          }
        }
      }
      else
      {
        localObject2 = ((List)localObject2).get(k);
        if (localObject2 == null)
          localSerializeWriter.append("null]");
        while (true)
        {
          paramJSONSerializer.setContext(paramType);
          return;
          localObject3 = localObject2.getClass();
          if (localObject3 == Integer.class)
          {
            localSerializeWriter.writeIntAndChar(((Integer)localObject2).intValue(), ']');
          }
          else
          {
            if (localObject3 != Long.class)
              break;
            if (bool)
            {
              localSerializeWriter.writeLongAndChar(((Long)localObject2).longValue(), 'L');
              localSerializeWriter.write(']');
            }
            else
            {
              localSerializeWriter.writeLongAndChar(((Long)localObject2).longValue(), ']');
            }
          }
        }
        paramJSONSerializer.setContext(new SerialContext(paramType, paramObject1, paramObject2));
        if (paramJSONSerializer.containsReference(localObject2))
          paramJSONSerializer.writeReference(localObject2);
        while (true)
        {
          localSerializeWriter.append(']');
          break;
          paramJSONSerializer.getObjectWriter(localObject2.getClass()).write(paramJSONSerializer, localObject2, Integer.valueOf(k), (Type)localObject1);
        }
        label694: i += 1;
        break;
      }
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ListSerializer
 * JD-Core Version:    0.6.2
 */