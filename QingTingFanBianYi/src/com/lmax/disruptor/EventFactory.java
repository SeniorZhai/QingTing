package com.lmax.disruptor;

public abstract interface EventFactory<T>
{
  public abstract T newInstance();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.EventFactory
 * JD-Core Version:    0.6.2
 */