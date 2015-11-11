package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public final class ListResolveFieldDeserializer extends FieldDeserializer
{
  private final int index;
  private final List list;
  private final DefaultJSONParser parser;

  public ListResolveFieldDeserializer(DefaultJSONParser paramDefaultJSONParser, List paramList, int paramInt)
  {
    super(null, null);
    this.parser = paramDefaultJSONParser;
    this.index = paramInt;
    this.list = paramList;
  }

  public void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
  }

  public void setValue(Object paramObject1, Object paramObject2)
  {
    this.list.set(this.index, paramObject2);
    Object localObject;
    if ((this.list instanceof JSONArray))
    {
      paramObject1 = (JSONArray)this.list;
      localObject = paramObject1.getRelatedArray();
      if ((localObject != null) && (Array.getLength(localObject) > this.index))
        if (paramObject1.getComponentType() == null)
          break label86;
    }
    label86: for (paramObject1 = TypeUtils.cast(paramObject2, paramObject1.getComponentType(), this.parser.getConfig()); ; paramObject1 = paramObject2)
    {
      Array.set(localObject, this.index, paramObject1);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ListResolveFieldDeserializer
 * JD-Core Version:    0.6.2
 */