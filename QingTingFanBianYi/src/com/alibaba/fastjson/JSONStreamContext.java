package com.alibaba.fastjson;

class JSONStreamContext
{
  static final int ArrayValue = 1005;
  static final int PropertyKey = 1002;
  static final int PropertyValue = 1003;
  static final int StartArray = 1004;
  static final int StartObject = 1001;
  private final JSONStreamContext parent;
  private int state;

  public JSONStreamContext(JSONStreamContext paramJSONStreamContext, int paramInt)
  {
    this.parent = paramJSONStreamContext;
    this.state = paramInt;
  }

  public JSONStreamContext getParent()
  {
    return this.parent;
  }

  public int getState()
  {
    return this.state;
  }

  public void setState(int paramInt)
  {
    this.state = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.JSONStreamContext
 * JD-Core Version:    0.6.2
 */