package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicReference;

public class ReferenceDeserializer
  implements ObjectDeserializer
{
  public static final ReferenceDeserializer instance = new ReferenceDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramType = (ParameterizedType)paramType;
    paramDefaultJSONParser = paramDefaultJSONParser.parseObject(paramType.getActualTypeArguments()[0]);
    paramType = paramType.getRawType();
    if (paramType == AtomicReference.class)
      return new AtomicReference(paramDefaultJSONParser);
    if (paramType == WeakReference.class)
      return new WeakReference(paramDefaultJSONParser);
    if (paramType == SoftReference.class)
      return new SoftReference(paramDefaultJSONParser);
    throw new UnsupportedOperationException(paramType.toString());
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ReferenceDeserializer
 * JD-Core Version:    0.6.2
 */