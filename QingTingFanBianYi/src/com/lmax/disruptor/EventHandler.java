package com.lmax.disruptor;

public abstract interface EventHandler<T>
{
  public abstract void onEvent(T paramT, long paramLong, boolean paramBoolean)
    throws Exception;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.EventHandler
 * JD-Core Version:    0.6.2
 */