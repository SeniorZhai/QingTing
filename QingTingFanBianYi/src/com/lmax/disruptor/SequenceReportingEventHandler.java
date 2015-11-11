package com.lmax.disruptor;

public abstract interface SequenceReportingEventHandler<T> extends EventHandler<T>
{
  public abstract void setSequenceCallback(Sequence paramSequence);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.SequenceReportingEventHandler
 * JD-Core Version:    0.6.2
 */