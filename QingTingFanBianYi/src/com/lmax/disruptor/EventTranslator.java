package com.lmax.disruptor;

public abstract interface EventTranslator<T>
{
  public abstract void translateTo(T paramT, long paramLong);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.EventTranslator
 * JD-Core Version:    0.6.2
 */