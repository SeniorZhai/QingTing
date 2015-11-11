package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONStreamAware;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONStreamAwareSerializer
  implements ObjectSerializer
{
  public static JSONStreamAwareSerializer instance = new JSONStreamAwareSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    ((JSONStreamAware)paramObject1).writeJSONString(paramJSONSerializer);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.JSONStreamAwareSerializer
 * JD-Core Version:    0.6.2
 */