package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ThrowableDeserializer extends JavaBeanDeserializer
{
  public ThrowableDeserializer(ParserConfig paramParserConfig, Class<?> paramClass)
  {
    super(paramParserConfig, paramClass);
  }

  private Throwable createException(String paramString, Throwable paramThrowable, Class<?> paramClass)
    throws Exception
  {
    Object localObject2 = null;
    Object localObject1 = null;
    Object localObject3 = null;
    Constructor[] arrayOfConstructor = paramClass.getConstructors();
    int j = arrayOfConstructor.length;
    int i = 0;
    if (i < j)
    {
      paramClass = arrayOfConstructor[i];
      Object localObject6;
      Object localObject5;
      Object localObject4;
      if (paramClass.getParameterTypes().length == 0)
      {
        localObject6 = localObject1;
        localObject5 = paramClass;
        localObject4 = localObject3;
      }
      while (true)
      {
        i += 1;
        localObject3 = localObject4;
        localObject2 = localObject5;
        localObject1 = localObject6;
        break;
        if ((paramClass.getParameterTypes().length == 1) && (paramClass.getParameterTypes()[0] == String.class))
        {
          localObject4 = localObject3;
          localObject5 = localObject2;
          localObject6 = paramClass;
        }
        else
        {
          localObject4 = localObject3;
          localObject5 = localObject2;
          localObject6 = localObject1;
          if (paramClass.getParameterTypes().length == 2)
          {
            localObject4 = localObject3;
            localObject5 = localObject2;
            localObject6 = localObject1;
            if (paramClass.getParameterTypes()[0] == String.class)
            {
              localObject4 = localObject3;
              localObject5 = localObject2;
              localObject6 = localObject1;
              if (paramClass.getParameterTypes()[1] == Throwable.class)
              {
                localObject4 = paramClass;
                localObject5 = localObject2;
                localObject6 = localObject1;
              }
            }
          }
        }
      }
    }
    if (localObject3 != null)
      return (Throwable)localObject3.newInstance(new Object[] { paramString, paramThrowable });
    if (localObject1 != null)
      return (Throwable)localObject1.newInstance(new Object[] { paramString });
    if (localObject2 != null)
      return (Throwable)localObject2.newInstance(new Object[0]);
    return null;
  }

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    if (localJSONLexer.token() == 8)
    {
      localJSONLexer.nextToken();
      paramType = null;
      return paramType;
    }
    label42: Object localObject3;
    Object localObject1;
    Object localObject2;
    HashMap localHashMap;
    if (paramDefaultJSONParser.getResolveStatus() == 2)
    {
      paramDefaultJSONParser.setResolveStatus(0);
      localObject3 = null;
      localObject1 = null;
      paramObject = localObject1;
      if (paramType != null)
      {
        paramObject = localObject1;
        if ((paramType instanceof Class))
        {
          paramType = (Class)paramType;
          paramObject = localObject1;
          if (Throwable.class.isAssignableFrom(paramType))
            paramObject = paramType;
        }
      }
      localObject2 = null;
      localObject1 = null;
      localHashMap = new HashMap();
      paramType = (Type)localObject3;
      label102: localObject3 = localJSONLexer.scanSymbol(paramDefaultJSONParser.getSymbolTable());
      if (localObject3 != null)
        break label218;
      if (localJSONLexer.token() != 13)
        break label193;
      localJSONLexer.nextToken(16);
      label141: if (paramObject != null)
        break label522;
      paramDefaultJSONParser = new Exception((String)localObject2, paramType);
    }
    while (true)
    {
      paramType = paramDefaultJSONParser;
      if (localObject1 == null)
        break;
      paramDefaultJSONParser.setStackTrace((StackTraceElement[])localObject1);
      return paramDefaultJSONParser;
      if (localJSONLexer.token() == 12)
        break label42;
      throw new JSONException("syntax error");
      label193: if ((localJSONLexer.token() == 16) && (localJSONLexer.isEnabled(Feature.AllowArbitraryCommas)))
        break label102;
      label218: localJSONLexer.nextTokenWithColon(4);
      Object localObject5;
      Object localObject4;
      Object localObject6;
      if (JSON.DEFAULT_TYPE_KEY.equals(localObject3))
        if (localJSONLexer.token() == 4)
        {
          localObject5 = TypeUtils.loadClass(localJSONLexer.stringVal());
          localJSONLexer.nextToken(16);
          localObject4 = localObject1;
          localObject3 = localObject2;
          localObject6 = paramType;
        }
      while (true)
      {
        paramType = (Type)localObject6;
        paramObject = localObject5;
        localObject2 = localObject3;
        localObject1 = localObject4;
        if (localJSONLexer.token() != 13)
          break;
        localJSONLexer.nextToken(16);
        paramType = (Type)localObject6;
        paramObject = localObject5;
        localObject2 = localObject3;
        localObject1 = localObject4;
        break label141;
        throw new JSONException("syntax error");
        if ("message".equals(localObject3))
        {
          if (localJSONLexer.token() == 8);
          for (localObject3 = null; ; localObject3 = localJSONLexer.stringVal())
          {
            localJSONLexer.nextToken();
            localObject6 = paramType;
            localObject5 = paramObject;
            localObject4 = localObject1;
            break;
            if (localJSONLexer.token() != 4)
              break label410;
          }
          label410: throw new JSONException("syntax error");
        }
        if ("cause".equals(localObject3))
        {
          localObject6 = (Throwable)deserialze(paramDefaultJSONParser, null, "cause");
          localObject5 = paramObject;
          localObject3 = localObject2;
          localObject4 = localObject1;
        }
        else if ("stackTrace".equals(localObject3))
        {
          localObject4 = (StackTraceElement[])paramDefaultJSONParser.parseObject([Ljava.lang.StackTraceElement.class);
          localObject6 = paramType;
          localObject5 = paramObject;
          localObject3 = localObject2;
        }
        else
        {
          localHashMap.put(localObject3, paramDefaultJSONParser.parse());
          localObject6 = paramType;
          localObject5 = paramObject;
          localObject3 = localObject2;
          localObject4 = localObject1;
        }
      }
      try
      {
        label522: paramObject = createException((String)localObject2, paramType, paramObject);
        paramDefaultJSONParser = paramObject;
        if (paramObject == null)
          paramDefaultJSONParser = new Exception((String)localObject2, paramType);
      }
      catch (Exception paramDefaultJSONParser)
      {
      }
    }
    throw new JSONException("create instance error", paramDefaultJSONParser);
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer
 * JD-Core Version:    0.6.2
 */