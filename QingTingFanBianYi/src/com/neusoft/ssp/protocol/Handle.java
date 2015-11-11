package com.neusoft.ssp.protocol;

public class Handle
{
  protected int address;

  protected Handle(int paramInt)
  {
    this.address = paramInt;
  }

  public int getAddress()
  {
    return this.address;
  }

  protected void setAddress(int paramInt)
  {
    this.address = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.protocol.Handle
 * JD-Core Version:    0.6.2
 */