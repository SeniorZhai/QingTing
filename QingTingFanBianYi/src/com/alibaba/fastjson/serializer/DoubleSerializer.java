package com.alibaba.fastjson.serializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DecimalFormat;

public class DoubleSerializer
  implements ObjectSerializer
{
  public static final DoubleSerializer instance = new DoubleSerializer();
  private DecimalFormat decimalFormat = null;

  public DoubleSerializer()
  {
  }

  public DoubleSerializer(String paramString)
  {
    this(new DecimalFormat(paramString));
  }

  public DoubleSerializer(DecimalFormat paramDecimalFormat)
  {
    this.decimalFormat = paramDecimalFormat;
  }

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramType = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      if (paramJSONSerializer.isEnabled(SerializerFeature.WriteNullNumberAsZero))
      {
        paramType.write('0');
        return;
      }
      paramType.writeNull();
      return;
    }
    double d = ((Double)paramObject1).doubleValue();
    if (Double.isNaN(d))
    {
      paramType.writeNull();
      return;
    }
    if (Double.isInfinite(d))
    {
      paramType.writeNull();
      return;
    }
    if (this.decimalFormat == null)
    {
      paramObject2 = Double.toString(d);
      paramObject1 = paramObject2;
      if (!paramObject2.endsWith(".0"));
    }
    for (paramObject1 = paramObject2.substring(0, paramObject2.length() - 2); ; paramObject1 = this.decimalFormat.format(d))
    {
      paramType.append(paramObject1);
      if (!paramJSONSerializer.isEnabled(SerializerFeature.WriteClassName))
        break;
      paramType.write('D');
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.DoubleSerializer
 * JD-Core Version:    0.6.2
 */