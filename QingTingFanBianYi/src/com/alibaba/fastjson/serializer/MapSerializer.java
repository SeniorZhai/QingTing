package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapSerializer
  implements ObjectSerializer
{
  public static MapSerializer instance = new MapSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    SerializeWriter localSerializeWriter = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      localSerializeWriter.writeNull();
      return;
    }
    Object localObject1 = (Map)paramObject1;
    paramType = (Type)localObject1;
    if (localSerializeWriter.isEnabled(SerializerFeature.SortField))
    {
      paramType = (Type)localObject1;
      if (!(localObject1 instanceof SortedMap))
      {
        paramType = (Type)localObject1;
        if ((localObject1 instanceof LinkedHashMap));
      }
    }
    try
    {
      paramType = new TreeMap((Map)localObject1);
      if (paramJSONSerializer.containsReference(paramObject1))
      {
        paramJSONSerializer.writeReference(paramObject1);
        return;
      }
      SerialContext localSerialContext = paramJSONSerializer.getContext();
      paramJSONSerializer.setContext(localSerialContext, paramObject1, paramObject2);
      while (true)
      {
        int i;
        Object localObject2;
        Object localObject4;
        try
        {
          localSerializeWriter.write('{');
          paramJSONSerializer.incrementIndent();
          paramObject2 = null;
          localObject1 = null;
          i = 1;
          if (localSerializeWriter.isEnabled(SerializerFeature.WriteClassName))
          {
            localSerializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            localSerializeWriter.writeString(paramObject1.getClass().getName());
            i = 0;
          }
          Iterator localIterator = paramType.entrySet().iterator();
          if (!localIterator.hasNext())
            break;
          localObject3 = (Map.Entry)localIterator.next();
          localObject2 = ((Map.Entry)localObject3).getValue();
          localObject4 = ((Map.Entry)localObject3).getKey();
          if ((localObject4 == null) || ((localObject4 instanceof String)))
          {
            localObject3 = (String)localObject4;
            if ((!FilterUtils.applyName(paramJSONSerializer, paramObject1, (String)localObject3)) || (!FilterUtils.apply(paramJSONSerializer, paramObject1, (String)localObject3, localObject2)))
              continue;
            localObject3 = FilterUtils.processKey(paramJSONSerializer, paramObject1, (String)localObject3, localObject2);
            localObject2 = FilterUtils.processValue(paramJSONSerializer, paramObject1, (String)localObject3, localObject2);
            if ((localObject2 == null) && (!paramJSONSerializer.isEnabled(SerializerFeature.WriteMapNullValue)))
              continue;
            if (i == 0)
              localSerializeWriter.write(',');
            if (localSerializeWriter.isEnabled(SerializerFeature.PrettyFormat))
              paramJSONSerializer.println();
            localSerializeWriter.writeFieldName((String)localObject3, true);
            i = 0;
            if (localObject2 != null)
              break label374;
            localSerializeWriter.writeNull();
            continue;
          }
        }
        finally
        {
          paramJSONSerializer.setContext(localSerialContext);
        }
        if (i == 0)
          localSerializeWriter.write(',');
        paramJSONSerializer.write(localObject4);
        localSerializeWriter.write(':');
        continue;
        label374: Object localObject3 = localObject2.getClass();
        if (localObject3 == paramObject2)
        {
          ((ObjectSerializer)localObject1).write(paramJSONSerializer, localObject2, localObject4, null);
        }
        else
        {
          paramObject2 = localObject3;
          localObject1 = paramJSONSerializer.getObjectWriter((Class)localObject3);
          ((ObjectSerializer)localObject1).write(paramJSONSerializer, localObject2, localObject4, null);
        }
      }
      paramJSONSerializer.setContext(localSerialContext);
      paramJSONSerializer.decrementIdent();
      if ((localSerializeWriter.isEnabled(SerializerFeature.PrettyFormat)) && (paramType.size() > 0))
        paramJSONSerializer.println();
      localSerializeWriter.write('}');
      return;
    }
    catch (Exception paramType)
    {
      while (true)
        paramType = (Type)localObject1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.MapSerializer
 * JD-Core Version:    0.6.2
 */