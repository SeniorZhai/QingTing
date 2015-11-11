package com.alibaba.fastjson.parser;

@Deprecated
public class JavaBeanMapping extends ParserConfig
{
  private static final JavaBeanMapping instance = new JavaBeanMapping();

  public static JavaBeanMapping getGlobalInstance()
  {
    return instance;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.JavaBeanMapping
 * JD-Core Version:    0.6.2
 */