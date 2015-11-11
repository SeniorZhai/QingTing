package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONAware;
import java.io.IOException;
import java.lang.reflect.Type;

public class JSONAwareSerializer
  implements ObjectSerializer
{
  public static JSONAwareSerializer instance = new JSONAwareSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer.getWriter().write(((JSONAware)paramObject1).toJSONString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.JSONAwareSerializer
 * JD-Core Version:    0.6.2
 */