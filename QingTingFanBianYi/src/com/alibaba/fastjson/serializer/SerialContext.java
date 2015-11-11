package com.alibaba.fastjson.serializer;

public class SerialContext
{
  private final Object fieldName;
  private final Object object;
  private final SerialContext parent;

  public SerialContext(SerialContext paramSerialContext, Object paramObject1, Object paramObject2)
  {
    this.parent = paramSerialContext;
    this.object = paramObject1;
    this.fieldName = paramObject2;
  }

  public Object getFieldName()
  {
    return this.fieldName;
  }

  public Object getObject()
  {
    return this.object;
  }

  public SerialContext getParent()
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

  public String toString()
  {
    return getPath();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.serializer.SerialContext
 * JD-Core Version:    0.6.2
 */