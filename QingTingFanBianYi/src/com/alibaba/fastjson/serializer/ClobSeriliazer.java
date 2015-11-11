package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.sql.Clob;
import java.sql.SQLException;

public class ClobSeriliazer
  implements ObjectSerializer
{
  public static final ClobSeriliazer instance = new ClobSeriliazer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    if (paramObject1 == null);
    try
    {
      paramJSONSerializer.writeNull();
      return;
      paramObject1 = ((Clob)paramObject1).getCharacterStream();
      paramObject2 = new StringWriter();
      paramType = new char[1024];
      while (true)
      {
        int i = paramObject1.read(paramType);
        if (i == -1)
          break;
        paramObject2.write(paramType, 0, i);
      }
    }
    catch (SQLException paramJSONSerializer)
    {
      throw new IOException("write clob error", paramJSONSerializer);
    }
    paramObject1.close();
    paramJSONSerializer.write(paramObject2.toString());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ClobSeriliazer
 * JD-Core Version:    0.6.2
 */