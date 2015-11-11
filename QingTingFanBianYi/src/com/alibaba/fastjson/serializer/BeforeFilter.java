package com.alibaba.fastjson.serializer;

public abstract class BeforeFilter
  implements SerializeFilter
{
  private static final Character COMMA = Character.valueOf(',');
  private static final ThreadLocal<Character> seperatorLocal;
  private static final ThreadLocal<JSONSerializer> serializerLocal = new ThreadLocal();

  static
  {
    seperatorLocal = new ThreadLocal();
  }

  final char writeBefore(JSONSerializer paramJSONSerializer, Object paramObject, char paramChar)
  {
    serializerLocal.set(paramJSONSerializer);
    seperatorLocal.set(Character.valueOf(paramChar));
    writeBefore(paramObject);
    serializerLocal.set(null);
    return ((Character)seperatorLocal.get()).charValue();
  }

  public abstract void writeBefore(Object paramObject);

  protected final void writeKeyValue(String paramString, Object paramObject)
  {
    JSONSerializer localJSONSerializer = (JSONSerializer)serializerLocal.get();
    char c = ((Character)seperatorLocal.get()).charValue();
    localJSONSerializer.writeKeyValue(c, paramString, paramObject);
    if (c != ',')
      seperatorLocal.set(COMMA);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.BeforeFilter
 * JD-Core Version:    0.6.2
 */