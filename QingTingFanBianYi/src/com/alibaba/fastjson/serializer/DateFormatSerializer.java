package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

public class DateFormatSerializer
  implements ObjectSerializer
{
  public static final DateFormatSerializer instance = new DateFormatSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramObject2 = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      paramObject2.writeNull();
      return;
    }
    String str = ((SimpleDateFormat)paramObject1).toPattern();
    if ((paramObject2.isEnabled(SerializerFeature.WriteClassName)) && (paramObject1.getClass() != paramType))
    {
      paramObject2.write('{');
      paramObject2.writeFieldName(JSON.DEFAULT_TYPE_KEY);
      paramJSONSerializer.write(paramObject1.getClass().getName());
      paramObject2.writeFieldValue(',', "val", str);
      paramObject2.write('}');
      return;
    }
    paramObject2.writeString(str);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.DateFormatSerializer
 * JD-Core Version:    0.6.2
 */