package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatSerializer
  implements ObjectSerializer
{
  private final String pattern;

  public SimpleDateFormatSerializer(String paramString)
  {
    this.pattern = paramString;
  }

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    if (paramObject1 == null)
    {
      paramJSONSerializer.getWriter().writeNull();
      return;
    }
    paramObject1 = (Date)paramObject1;
    paramJSONSerializer.write(new SimpleDateFormat(this.pattern).format(paramObject1));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.SimpleDateFormatSerializer
 * JD-Core Version:    0.6.2
 */