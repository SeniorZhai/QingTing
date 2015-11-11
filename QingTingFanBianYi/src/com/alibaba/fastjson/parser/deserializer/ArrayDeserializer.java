package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collection;

public class ArrayDeserializer
  implements ObjectDeserializer
{
  public static final ArrayDeserializer instance = new ArrayDeserializer();

  private <T> T toObjectArray(DefaultJSONParser paramDefaultJSONParser, Class<?> paramClass, JSONArray paramJSONArray)
  {
    if (paramJSONArray == null)
      return null;
    int m = paramJSONArray.size();
    Object localObject4 = Array.newInstance(paramClass, m);
    int i = 0;
    if (i < m)
    {
      Object localObject3 = paramJSONArray.get(i);
      if (localObject3 == paramJSONArray)
        Array.set(localObject4, i, localObject4);
      while (true)
      {
        i += 1;
        break;
        if (paramClass.isArray())
        {
          if (paramClass.isInstance(localObject3));
          for (localObject1 = localObject3; ; localObject1 = toObjectArray(paramDefaultJSONParser, paramClass, (JSONArray)localObject3))
          {
            Array.set(localObject4, i, localObject1);
            break;
          }
        }
        Object localObject2 = null;
        Object localObject1 = localObject2;
        if ((localObject3 instanceof JSONArray))
        {
          int k = 0;
          JSONArray localJSONArray = (JSONArray)localObject3;
          int n = localJSONArray.size();
          int j = 0;
          while (j < n)
          {
            if (localJSONArray.get(j) == paramJSONArray)
            {
              localJSONArray.set(i, localObject4);
              k = 1;
            }
            j += 1;
          }
          localObject1 = localObject2;
          if (k != 0)
            localObject1 = localJSONArray.toArray();
        }
        localObject2 = localObject1;
        if (localObject1 == null)
          localObject2 = TypeUtils.cast(localObject3, paramClass, paramDefaultJSONParser.getConfig());
        Array.set(localObject4, i, localObject2);
      }
    }
    paramJSONArray.setRelatedArray(localObject4);
    paramJSONArray.setComponentType(paramClass);
    return localObject4;
  }

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    Object localObject1 = paramDefaultJSONParser.getLexer();
    if (((JSONLexer)localObject1).token() == 8)
    {
      ((JSONLexer)localObject1).nextToken(16);
      return null;
    }
    if (((JSONLexer)localObject1).token() == 4)
    {
      paramDefaultJSONParser = ((JSONLexer)localObject1).bytesValue();
      ((JSONLexer)localObject1).nextToken(16);
      return paramDefaultJSONParser;
    }
    if ((paramType instanceof GenericArrayType))
    {
      paramType = ((GenericArrayType)paramType).getGenericComponentType();
      if ((paramType instanceof TypeVariable))
      {
        TypeVariable localTypeVariable = (TypeVariable)paramType;
        paramType = paramDefaultJSONParser.getContext().getType();
        if ((paramType instanceof ParameterizedType))
        {
          ParameterizedType localParameterizedType = (ParameterizedType)paramType;
          Object localObject2 = localParameterizedType.getRawType();
          localObject1 = null;
          paramType = null;
          if ((localObject2 instanceof Class))
          {
            localObject2 = ((Class)localObject2).getTypeParameters();
            int i = 0;
            while (true)
            {
              localObject1 = paramType;
              if (i >= localObject2.length)
                break;
              if (localObject2[i].getName().equals(localTypeVariable.getName()))
                paramType = localParameterizedType.getActualTypeArguments()[i];
              i += 1;
            }
          }
          if ((localObject1 instanceof Class))
            paramType = (Class)localObject1;
        }
      }
    }
    while (true)
    {
      localObject1 = new JSONArray();
      paramDefaultJSONParser.parseArray(paramType, (Collection)localObject1, paramObject);
      return toObjectArray(paramDefaultJSONParser, paramType, (JSONArray)localObject1);
      paramType = Object.class;
      continue;
      paramType = Object.class;
      continue;
      paramType = (Class)paramType;
      continue;
      paramType = ((Class)paramType).getComponentType();
    }
  }

  public int getFastMatchToken()
  {
    return 14;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ArrayDeserializer
 * JD-Core Version:    0.6.2
 */