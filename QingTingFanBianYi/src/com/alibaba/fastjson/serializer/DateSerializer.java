package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSerializer
  implements ObjectSerializer
{
  public static final DateSerializer instance = new DateSerializer();

  public void write(JSONSerializer paramJSONSerializer, Object paramObject1, Object paramObject2, Type paramType)
    throws IOException
  {
    paramObject2 = paramJSONSerializer.getWriter();
    if (paramObject1 == null)
    {
      paramObject2.writeNull();
      return;
    }
    if ((paramObject2.isEnabled(SerializerFeature.WriteClassName)) && (paramObject1.getClass() != paramType))
    {
      if (paramObject1.getClass() == Date.class)
      {
        paramObject2.write("new Date(");
        paramObject2.writeLongAndChar(((Date)paramObject1).getTime(), ')');
        return;
      }
      paramObject2.write('{');
      paramObject2.writeFieldName(JSON.DEFAULT_TYPE_KEY);
      paramJSONSerializer.write(paramObject1.getClass().getName());
      paramObject2.writeFieldValue(',', "val", ((Date)paramObject1).getTime());
      paramObject2.write('}');
      return;
    }
    paramType = (Date)paramObject1;
    if (paramObject2.isEnabled(SerializerFeature.WriteDateUseDateFormat))
    {
      paramObject1 = paramJSONSerializer.getDateFormat();
      paramJSONSerializer = paramObject1;
      if (paramObject1 == null)
        paramJSONSerializer = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT);
      paramObject2.writeString(paramJSONSerializer.format(paramType));
      return;
    }
    long l = paramType.getTime();
    if (paramJSONSerializer.isEnabled(SerializerFeature.UseISO8601DateFormat))
    {
      int i;
      int j;
      int k;
      int m;
      int n;
      int i1;
      if (paramJSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes))
      {
        paramObject2.append('\'');
        paramObject1 = Calendar.getInstance();
        paramObject1.setTimeInMillis(l);
        i = paramObject1.get(1);
        j = paramObject1.get(2) + 1;
        k = paramObject1.get(5);
        m = paramObject1.get(11);
        n = paramObject1.get(12);
        i1 = paramObject1.get(13);
        int i2 = paramObject1.get(14);
        if (i2 == 0)
          break label355;
        paramObject1 = "0000-00-00T00:00:00.000".toCharArray();
        IOUtils.getChars(i2, 23, paramObject1);
        IOUtils.getChars(i1, 19, paramObject1);
        IOUtils.getChars(n, 16, paramObject1);
        IOUtils.getChars(m, 13, paramObject1);
        IOUtils.getChars(k, 10, paramObject1);
        IOUtils.getChars(j, 7, paramObject1);
        IOUtils.getChars(i, 4, paramObject1);
      }
      while (true)
      {
        paramObject2.write(paramObject1);
        if (!paramJSONSerializer.isEnabled(SerializerFeature.UseSingleQuotes))
          break label458;
        paramObject2.append('\'');
        return;
        paramObject2.append('"');
        break;
        label355: if ((i1 == 0) && (n == 0) && (m == 0))
        {
          paramObject1 = "0000-00-00".toCharArray();
          IOUtils.getChars(k, 10, paramObject1);
          IOUtils.getChars(j, 7, paramObject1);
          IOUtils.getChars(i, 4, paramObject1);
        }
        else
        {
          paramObject1 = "0000-00-00T00:00:00".toCharArray();
          IOUtils.getChars(i1, 19, paramObject1);
          IOUtils.getChars(n, 16, paramObject1);
          IOUtils.getChars(m, 13, paramObject1);
          IOUtils.getChars(k, 10, paramObject1);
          IOUtils.getChars(j, 7, paramObject1);
          IOUtils.getChars(i, 4, paramObject1);
        }
      }
      label458: paramObject2.append('"');
      return;
    }
    paramObject2.writeLong(l);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.DateSerializer
 * JD-Core Version:    0.6.2
 */