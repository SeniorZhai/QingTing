package com.alibaba.fastjson.parser;

import java.lang.reflect.Type;

public class ParseContext
{
  private final Object fieldName;
  private Object object;
  private final ParseContext parent;
  private Type type;

  public ParseContext(ParseContext paramParseContext, Object paramObject1, Object paramObject2)
  {
    this.parent = paramParseContext;
    this.object = paramObject1;
    this.fieldName = paramObject2;
  }

  public Object getObject()
  {
    return this.object;
  }

  public ParseContext getParentContext()
  {
    return this.parent;
  }

  public String getPath()
  {
    if (this.parent == null)
      return "$";
    if ((this.fieldName instanceof Integer))
      return this.parent.getPath() + "[" + this.fieldName + "]";
    return this.parent.getPath() + "." + this.fieldName;
  }

  public Type getType()
  {
    return this.type;
  }

  public void setObject(Object paramObject)
  {
    this.object = paramObject;
  }

  public void setType(Type paramType)
  {
    this.type = paramType;
  }

  public String toString()
  {
    return getPath();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.ParseContext
 * JD-Core Version:    0.6.2
 */