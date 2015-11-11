package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class InetSocketAddressDeserializer
  implements ObjectDeserializer
{
  public static final InetSocketAddressDeserializer instance = new InetSocketAddressDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramObject = paramDefaultJSONParser.getLexer();
    if (paramObject.token() == 8)
    {
      paramObject.nextToken();
      return null;
    }
    paramDefaultJSONParser.accept(12);
    paramType = null;
    int i = 0;
    String str = paramObject.stringVal();
    paramObject.nextToken(17);
    if (str.equals("address"))
    {
      paramDefaultJSONParser.accept(17);
      paramType = (InetAddress)paramDefaultJSONParser.parseObject(InetAddress.class);
    }
    while (true)
    {
      if (paramObject.token() != 16)
        break label164;
      paramObject.nextToken();
      break;
      if (str.equals("port"))
      {
        paramDefaultJSONParser.accept(17);
        if (paramObject.token() != 2)
          throw new JSONException("port is not int");
        i = paramObject.intValue();
        paramObject.nextToken();
      }
      else
      {
        paramDefaultJSONParser.accept(17);
        paramDefaultJSONParser.parse();
      }
    }
    label164: paramDefaultJSONParser.accept(13);
    return new InetSocketAddress(paramType, i);
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.InetSocketAddressDeserializer
 * JD-Core Version:    0.6.2
 */