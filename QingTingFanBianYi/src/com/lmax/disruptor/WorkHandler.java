package com.lmax.disruptor;

public abstract interface WorkHandler<T>
{
  public abstract void onEvent(T paramT)
    throws Exception;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.WorkHandler
 * JD-Core Version:    0.6.2
 */