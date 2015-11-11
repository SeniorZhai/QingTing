package com.alibaba.fastjson;

import java.io.IOException;

public abstract interface JSONStreamAware
{
  public abstract void writeJSONString(Appendable paramAppendable)
    throws IOException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.JSONStreamAware
 * JD-Core Version:    0.6.2
 */