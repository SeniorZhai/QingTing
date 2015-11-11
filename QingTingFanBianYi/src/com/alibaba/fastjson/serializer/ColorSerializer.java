package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import java.awt.Color;
import java.io.IOException;
import java.lang.reflect.Type;

public class ColorSerializer
  implements ObjectSerializer
{
  public static final ColorSerializer instance = new ColorSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    paramObject1 = (Color)paramObject1;
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
      paramJSONSerializer.writeString(Color.class.getName());
      c = ',';
    }
    paramJSONSerializer.writeFieldValue(c, "r", paramObject1.getRed());
    paramJSONSerializer.writeFieldValue(',', "g", paramObject1.getGreen());
    paramJSONSerializer.writeFieldValue(',', "b", paramObject1.getBlue());
    if (paramObject1.getAlpha() > 0)
      paramJSONSerializer.writeFieldValue(',', "alpha", paramObject1.getAlpha());
    paramJSONSerializer.write('}');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.ColorSerializer
 * JD-Core Version:    0.6.2
 */