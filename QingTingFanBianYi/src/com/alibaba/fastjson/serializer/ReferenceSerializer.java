package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

public class ReferenceSerializer
  implements ObjectSerializer
{
  public static final ReferenceSerializer instance = new ReferenceSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    if ((paramObject1 instanceof AtomicReference));
    for (paramObject1 = ((AtomicReference)paramObject1).get(); ; paramObject1 = ((Reference)paramObject1).get())
    {
      paramJSONSerializer.write(paramObject1);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ReferenceSerializer
 * JD-Core Version:    0.6.2
 */