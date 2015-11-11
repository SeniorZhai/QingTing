package com.alibaba.fastjson.parser;

import java.util.HashMap;
import java.util.Map;

public class Keywords
{
  public static Keywords DEFAULT_KEYWORDS = new Keywords(localHashMap);
  private final Map<String, Integer> keywords;

  static
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("null", Integer.valueOf(8));
    localHashMap.put("new", Integer.valueOf(9));
    localHashMap.put("true", Integer.valueOf(6));
    localHashMap.put("false", Integer.valueOf(7));
  }

  public Keywords(Map<String, Integer> paramMap)
  {
    this.keywords = paramMap;
  }

  public Integer getKeyword(String paramString)
  {
    return (Integer)this.keywords.get(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.Keywords
 * JD-Core Version:    0.6.2
 */