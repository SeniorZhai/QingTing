package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Type;
import java.util.Calendar;

public abstract class AbstractDateDeserializer
  implements ObjectDeserializer
{
  protected abstract <T> T cast(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject1, Object paramObject2);

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    Object localObject1;
    Object localObject3;
    if (localJSONLexer.token() == 2)
    {
      localObject1 = Long.valueOf(localJSONLexer.longValue());
      localJSONLexer.nextToken(16);
      localObject3 = paramType;
    }
    while (true)
    {
      return cast(paramDefaultJSONParser, (Type)localObject3, paramObject, localObject1);
      Object localObject2;
      if (localJSONLexer.token() == 4)
      {
        String str = localJSONLexer.stringVal();
        localObject2 = str;
        localJSONLexer.nextToken(16);
        localObject1 = localObject2;
        localObject3 = paramType;
        if (localJSONLexer.isEnabled(Feature.AllowISO8601DateFormat))
        {
          localObject3 = new JSONScanner(str);
          localObject1 = localObject2;
          if (((JSONScanner)localObject3).scanISO8601DateIfMatch())
            localObject1 = ((JSONScanner)localObject3).getCalendar().getTime();
          ((JSONScanner)localObject3).close();
          localObject3 = paramType;
        }
      }
      else if (localJSONLexer.token() == 8)
      {
        localJSONLexer.nextToken();
        localObject1 = null;
        localObject3 = paramType;
      }
      else if (localJSONLexer.token() == 12)
      {
        localJSONLexer.nextToken();
        if (localJSONLexer.token() == 4)
        {
          localObject1 = localJSONLexer.stringVal();
          localObject2 = paramType;
          if (JSON.DEFAULT_TYPE_KEY.equals(localObject1))
          {
            localJSONLexer.nextToken();
            paramDefaultJSONParser.accept(17);
            localObject1 = TypeUtils.loadClass(localJSONLexer.stringVal());
            if (localObject1 != null)
              paramType = (Type)localObject1;
            paramDefaultJSONParser.accept(4);
            paramDefaultJSONParser.accept(16);
            localObject2 = paramType;
          }
          localJSONLexer.nextTokenWithColon(2);
          if (localJSONLexer.token() == 2)
          {
            long l = localJSONLexer.longValue();
            localJSONLexer.nextToken();
            localObject1 = Long.valueOf(l);
            paramDefaultJSONParser.accept(13);
            localObject3 = localObject2;
          }
        }
        else
        {
          throw new JSONException("syntax error");
          throw new JSONException("syntax error : " + localJSONLexer.tokenName());
        }
      }
      else if (paramDefaultJSONParser.getResolveStatus() == 2)
      {
        paramDefaultJSONParser.setResolveStatus(0);
        paramDefaultJSONParser.accept(16);
        if (localJSONLexer.token() == 4)
        {
          if (!"val".equals(localJSONLexer.stringVal()))
            throw new JSONException("syntax error");
          localJSONLexer.nextToken();
          paramDefaultJSONParser.accept(17);
          localObject1 = paramDefaultJSONParser.parse();
          paramDefaultJSONParser.accept(13);
          localObject3 = paramType;
        }
        else
        {
          throw new JSONException("syntax error");
        }
      }
      else
      {
        localObject1 = paramDefaultJSONParser.parse();
        localObject3 = paramType;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer
 * JD-Core Version:    0.6.2
 */