package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class CollectionDeserializer
  implements ObjectDeserializer
{
  public static final CollectionDeserializer instance = new CollectionDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    if (paramDefaultJSONParser.getLexer().token() == 8)
    {
      paramDefaultJSONParser.getLexer().nextToken(16);
      return null;
    }
    Class localClass = getRawClass(paramType);
    Object localObject;
    if (localClass == AbstractCollection.class)
    {
      localObject = new ArrayList();
      if (!(paramType instanceof ParameterizedType))
        break label213;
    }
    label213: for (paramType = ((ParameterizedType)paramType).getActualTypeArguments()[0]; ; paramType = Object.class)
      while (true)
      {
        paramDefaultJSONParser.parseArray(paramType, (Collection)localObject, paramObject);
        return localObject;
        if (localClass.isAssignableFrom(HashSet.class))
        {
          localObject = new HashSet();
          break;
        }
        if (localClass.isAssignableFrom(LinkedHashSet.class))
        {
          localObject = new LinkedHashSet();
          break;
        }
        if (localClass.isAssignableFrom(TreeSet.class))
        {
          localObject = new TreeSet();
          break;
        }
        if (localClass.isAssignableFrom(ArrayList.class))
        {
          localObject = new ArrayList();
          break;
        }
        try
        {
          localObject = (Collection)localClass.newInstance();
        }
        catch (Exception paramDefaultJSONParser)
        {
          throw new JSONException("create instane error, class " + localClass.getName());
        }
      }
  }

  public int getFastMatchToken()
  {
    return 14;
  }

  public Class<?> getRawClass(Type paramType)
  {
    if ((paramType instanceof Class))
      return (Class)paramType;
    if ((paramType instanceof ParameterizedType))
      return getRawClass(((ParameterizedType)paramType).getRawType());
    throw new JSONException("TODO");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.CollectionDeserializer
 * JD-Core Version:    0.6.2
 */