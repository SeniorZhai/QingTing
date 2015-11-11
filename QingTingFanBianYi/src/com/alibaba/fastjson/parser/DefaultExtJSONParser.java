package com.alibaba.fastjson.parser;

@Deprecated
public class DefaultExtJSONParser extends DefaultJSONParser
{
  public DefaultExtJSONParser(String paramString)
  {
    this(paramString, ParserConfig.getGlobalInstance());
  }

  public DefaultExtJSONParser(String paramString, ParserConfig paramParserConfig)
  {
    super(paramString, paramParserConfig);
  }

  public DefaultExtJSONParser(String paramString, ParserConfig paramParserConfig, int paramInt)
  {
    super(paramString, paramParserConfig, paramInt);
  }

  public DefaultExtJSONParser(char[] paramArrayOfChar, int paramInt1, ParserConfig paramParserConfig, int paramInt2)
  {
    super(paramArrayOfChar, paramInt1, paramParserConfig, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.DefaultExtJSONParser
 * JD-Core Version:    0.6.2
 */