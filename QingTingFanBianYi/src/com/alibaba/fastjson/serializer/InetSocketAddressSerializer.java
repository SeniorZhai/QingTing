package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;

public class InetSocketAddressSerializer
  implements ObjectSerializer
{
  public static InetSocketAddressSerializer instance = new InetSocketAddressSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    if (paramObject1 == null)
    {
      paramJSONSerializer.writeNull();
      return;
    }
    paramObject2 = paramJSONSerializer.getWriter();
    paramObject1 = (InetSocketAddress)paramObject1;
    paramType = paramObject1.getAddress();
    paramObject2.write('{');
    if (paramType != null)
    {
      paramObject2.writeFieldName("address");
      paramJSONSerializer.write(paramType);
      paramObject2.write(',');
    }
    paramObject2.writeFieldName("port");
    paramObject2.writeInt(paramObject1.getPort());
    paramObject2.write('}');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.InetSocketAddressSerializer
 * JD-Core Version:    0.6.2
 */