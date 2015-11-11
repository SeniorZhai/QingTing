package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import java.lang.reflect.Type;

public class StackTraceElementDeserializer
  implements ObjectDeserializer
{
  public static final StackTraceElementDeserializer instance = new StackTraceElementDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    if (localJSONLexer.token() == 8)
    {
      localJSONLexer.nextToken();
      return null;
    }
    if ((localJSONLexer.token() != 12) && (localJSONLexer.token() != 16))
      throw new JSONException("syntax error: " + JSONToken.name(localJSONLexer.token()));
    Type localType = null;
    Object localObject3 = null;
    Object localObject2 = null;
    int j = 0;
    Object localObject1;
    int i;
    label150: 
    do
    {
      paramType = localJSONLexer.scanSymbol(paramDefaultJSONParser.getSymbolTable());
      if (paramType != null)
        break;
      if (localJSONLexer.token() == 13)
      {
        localJSONLexer.nextToken(16);
        localObject1 = localObject3;
        i = j;
        paramObject = localObject2;
        paramType = localType;
        return new StackTraceElement(paramType, (String)localObject1, paramObject, i);
      }
    }
    while ((localJSONLexer.token() == 16) && (localJSONLexer.isEnabled(Feature.AllowArbitraryCommas)));
    localJSONLexer.nextTokenWithColon(4);
    if (paramType == "className")
      if (localJSONLexer.token() == 8)
      {
        paramType = null;
        localObject1 = localObject3;
        i = j;
        paramObject = localObject2;
      }
    label660: label670: label749: 
    do
    {
      String str;
      do
      {
        while (true)
        {
          localType = paramType;
          localObject2 = paramObject;
          j = i;
          localObject3 = localObject1;
          if (localJSONLexer.token() != 13)
            break;
          localJSONLexer.nextToken(16);
          break label150;
          if (localJSONLexer.token() == 4)
          {
            paramType = localJSONLexer.stringVal();
            paramObject = localObject2;
            i = j;
            localObject1 = localObject3;
          }
          else
          {
            throw new JSONException("syntax error");
            if (paramType == "methodName")
            {
              if (localJSONLexer.token() == 8)
              {
                localObject1 = null;
                paramType = localType;
                paramObject = localObject2;
                i = j;
              }
              else if (localJSONLexer.token() == 4)
              {
                localObject1 = localJSONLexer.stringVal();
                paramType = localType;
                paramObject = localObject2;
                i = j;
              }
              else
              {
                throw new JSONException("syntax error");
              }
            }
            else if (paramType == "fileName")
            {
              if (localJSONLexer.token() == 8)
              {
                paramObject = null;
                paramType = localType;
                i = j;
                localObject1 = localObject3;
              }
              else if (localJSONLexer.token() == 4)
              {
                paramObject = localJSONLexer.stringVal();
                paramType = localType;
                i = j;
                localObject1 = localObject3;
              }
              else
              {
                throw new JSONException("syntax error");
              }
            }
            else if (paramType == "lineNumber")
            {
              if (localJSONLexer.token() == 8)
              {
                i = 0;
                paramType = localType;
                paramObject = localObject2;
                localObject1 = localObject3;
              }
              else if (localJSONLexer.token() == 2)
              {
                i = localJSONLexer.intValue();
                paramType = localType;
                paramObject = localObject2;
                localObject1 = localObject3;
              }
              else
              {
                throw new JSONException("syntax error");
              }
            }
            else
            {
              if (paramType != "nativeMethod")
                break label670;
              if (localJSONLexer.token() == 8)
              {
                localJSONLexer.nextToken(16);
                paramType = localType;
                paramObject = localObject2;
                i = j;
                localObject1 = localObject3;
              }
              else if (localJSONLexer.token() == 6)
              {
                localJSONLexer.nextToken(16);
                paramType = localType;
                paramObject = localObject2;
                i = j;
                localObject1 = localObject3;
              }
              else
              {
                if (localJSONLexer.token() != 7)
                  break label660;
                localJSONLexer.nextToken(16);
                paramType = localType;
                paramObject = localObject2;
                i = j;
                localObject1 = localObject3;
              }
            }
          }
        }
        throw new JSONException("syntax error");
        if (paramType != JSON.DEFAULT_TYPE_KEY)
          break label785;
        if (localJSONLexer.token() != 4)
          break label749;
        str = localJSONLexer.stringVal();
        paramType = localType;
        paramObject = localObject2;
        i = j;
        localObject1 = localObject3;
      }
      while (str.equals("java.lang.StackTraceElement"));
      throw new JSONException("syntax error : " + str);
      paramType = localType;
      paramObject = localObject2;
      i = j;
      localObject1 = localObject3;
    }
    while (localJSONLexer.token() == 8);
    throw new JSONException("syntax error");
    label785: throw new JSONException("syntax error : " + paramType);
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.StackTraceElementDeserializer
 * JD-Core Version:    0.6.2
 */