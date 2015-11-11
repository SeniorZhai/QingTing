package com.lmax.disruptor;

public abstract interface EventProcessor extends Runnable
{
  public abstract Sequence getSequence();

  public abstract void halt();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.EventProcessor
 * JD-Core Version:    0.6.2
 */