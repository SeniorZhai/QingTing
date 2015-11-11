package com.alibaba.fastjson.serializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

public class FileSerializer
  implements ObjectSerializer
{
  public static FileSerializer instance = new FileSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramObject2 = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      paramObject2.writeNull();
      return;
    }
    paramJSONSerializer.write(((File)paramObject1).getPath());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.FileSerializer
 * JD-Core Version:    0.6.2
 */