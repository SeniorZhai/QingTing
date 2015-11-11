package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public final class CollectionResolveFieldDeserializer extends FieldDeserializer
{
  private final Collection collection;

  public CollectionResolveFieldDeserializer(DefaultJSONParser paramDefaultJSONParser, Collection paramCollection)
  {
    super(null, null);
    this.collection = paramCollection;
  }

  public void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
  }

  public void setValue(Object paramObject1, Object paramObject2)
  {
    this.collection.add(paramObject2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.CollectionResolveFieldDeserializer
 * JD-Core Version:    0.6.2
 */