package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDeserializer
  implements ObjectDeserializer
{
  public static final InetAddressDeserializer instance = new InetAddressDeserializer();

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    paramDefaultJSONParser = (String)paramDefaultJSONParser.parse();
    if (paramDefaultJSONParser == null);
    while (paramDefaultJSONParser.length() == 0)
      return null;
    try
    {
      paramDefaultJSONParser = InetAddress.getByName(paramDefaultJSONParser);
      return paramDefaultJSONParser;
    }
    catch (UnknownHostException paramDefaultJSONParser)
    {
    }
    throw new JSONException("deserialize error", paramDefaultJSONParser);
  }

  public int getFastMatchToken()
  {
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.InetAddressDeserializer
 * JD-Core Version:    0.6.2
 */