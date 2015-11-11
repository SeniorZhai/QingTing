package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Type;

public class PointSerializer
  implements ObjectSerializer
{
  public static final PointSerializer instance = new PointSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramJSONSerializer = paramJSONSerializer.getWriter();
    paramObject1 = (Point)paramObject1;
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
      paramJSONSerializer.writeString(Point.class.getName());
      c = ',';
    }
    paramJSONSerializer.writeFieldValue(c, "x", paramObject1.getX());
    paramJSONSerializer.writeFieldValue(',', "y", paramObject1.getY());
    paramJSONSerializer.write('}');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.PointSerializer
 * JD-Core Version:    0.6.2
 */