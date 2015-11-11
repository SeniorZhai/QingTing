package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ArrayListTypeFieldDeserializer extends FieldDeserializer
{
  private ObjectDeserializer deserializer;
  private int itemFastMatchToken;
  private final Type itemType;

  public ArrayListTypeFieldDeserializer(ParserConfig paramParserConfig, Class<?> paramClass, FieldInfo paramFieldInfo)
  {
    super(paramClass, paramFieldInfo);
    if ((getFieldType() instanceof ParameterizedType))
    {
      this.itemType = ((ParameterizedType)getFieldType()).getActualTypeArguments()[0];
      return;
    }
    this.itemType = Object.class;
  }

  public int getFastMatchToken()
  {
    return 14;
  }

  public final void parseArray(DefaultJSONParser paramDefaultJSONParser, Type paramType, Collection paramCollection)
  {
    Type localType = this.itemType;
    Object localObject3 = this.deserializer;
    Object localObject2 = localType;
    Object localObject1 = localObject3;
    ParameterizedType localParameterizedType;
    int k;
    int j;
    int m;
    if ((localType instanceof TypeVariable))
    {
      localObject2 = localType;
      localObject1 = localObject3;
      if ((paramType instanceof ParameterizedType))
      {
        localObject2 = (TypeVariable)localType;
        localParameterizedType = (ParameterizedType)paramType;
        localObject1 = null;
        if ((localParameterizedType.getRawType() instanceof Class))
          localObject1 = (Class)localParameterizedType.getRawType();
        k = -1;
        j = k;
        if (localObject1 != null)
        {
          i = 0;
          m = ((Class)localObject1).getTypeParameters().length;
        }
      }
    }
    while (true)
    {
      j = k;
      if (i < m)
      {
        if (localObject1.getTypeParameters()[i].getName().equals(((TypeVariable)localObject2).getName()))
          j = i;
      }
      else
      {
        localObject2 = localType;
        localObject1 = localObject3;
        if (j != -1)
        {
          localType = localParameterizedType.getActualTypeArguments()[j];
          localObject2 = localType;
          localObject1 = localObject3;
          if (!localType.equals(this.itemType))
          {
            localObject1 = paramDefaultJSONParser.getConfig().getDeserializer(localType);
            localObject2 = localType;
          }
        }
        localObject3 = paramDefaultJSONParser.getLexer();
        if (((JSONLexer)localObject3).token() == 14)
          break;
        paramCollection = "exepct '[', but " + JSONToken.name(((JSONLexer)localObject3).token());
        paramDefaultJSONParser = paramCollection;
        if (paramType != null)
          paramDefaultJSONParser = paramCollection + ", type : " + paramType;
        throw new JSONException(paramDefaultJSONParser);
      }
      i += 1;
    }
    paramType = (Type)localObject1;
    if (localObject1 == null)
    {
      paramType = paramDefaultJSONParser.getConfig().getDeserializer((Type)localObject2);
      this.deserializer = paramType;
      this.itemFastMatchToken = this.deserializer.getFastMatchToken();
    }
    ((JSONLexer)localObject3).nextToken(this.itemFastMatchToken);
    int i = 0;
    while (true)
    {
      if (((JSONLexer)localObject3).isEnabled(Feature.AllowArbitraryCommas))
        while (((JSONLexer)localObject3).token() == 16)
          ((JSONLexer)localObject3).nextToken();
      if (((JSONLexer)localObject3).token() == 15)
      {
        ((JSONLexer)localObject3).nextToken(16);
        return;
      }
      paramCollection.add(paramType.deserialze(paramDefaultJSONParser, (Type)localObject2, Integer.valueOf(i)));
      paramDefaultJSONParser.checkListResolve(paramCollection);
      if (((JSONLexer)localObject3).token() == 16)
        ((JSONLexer)localObject3).nextToken(this.itemFastMatchToken);
      i += 1;
    }
  }

  public void parseField(DefaultJSONParser paramDefaultJSONParser, Object paramObject, Type paramType, Map<String, Object> paramMap)
  {
    if (paramDefaultJSONParser.getLexer().token() == 8)
    {
      setValue(paramObject, null);
      return;
    }
    ArrayList localArrayList = new ArrayList();
    ParseContext localParseContext = paramDefaultJSONParser.getContext();
    paramDefaultJSONParser.setContext(localParseContext, paramObject, this.fieldInfo.getName());
    parseArray(paramDefaultJSONParser, paramType, localArrayList);
    paramDefaultJSONParser.setContext(localParseContext);
    if (paramObject == null)
    {
      paramMap.put(this.fieldInfo.getName(), localArrayList);
      return;
    }
    setValue(paramObject, localArrayList);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ArrayListTypeFieldDeserializer
 * JD-Core Version:    0.6.2
 */