package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import java.awt.Font;
import java.io.IOException;
import java.lang.reflect.Type;

public class FontSerializer
  implements ObjectSerializer
{
  public static final FontSerializer instance = new FontSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    paramObject1 = (Font)paramObject1;
    if (paramObject1 == null)
    {
      paramJSONSerializer.writeNull();
      return;
    }
    char c = '{';
    if (paramJSONSerializer.isEnabled(SerializerFeature.WriteClassName))
    {
      paramJSONSerializer.write('{');
      paramJSONSerializer.writeFieldName(JSON.DEFAULT_TYPE_KEY);
      paramJSONSerializer.writeString(Font.class.getName());
      c = ',';
    }
    paramJSONSerializer.writeFieldValue(c, "name", paramObject1.getName());
    paramJSONSerializer.writeFieldValue(',', "style", paramObject1.getStyle());
    paramJSONSerializer.writeFieldValue(',', "size", paramObject1.getSize());
    paramJSONSerializer.write('}');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.FontSerializer
 * JD-Core Version:    0.6.2
 */