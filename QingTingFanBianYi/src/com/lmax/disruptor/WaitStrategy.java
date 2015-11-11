package com.lmax.disruptor;

import java.util.concurrent.TimeUnit;

public abstract interface WaitStrategy
{
  public abstract void signalAllWhenBlocking();

  public abstract long waitFor(long paramLong, Sequence paramSequence, Sequence[] paramArrayOfSequence, SequenceBarrier paramSequenceBarrier)
    throws AlertException, InterruptedException;

  @Deprecated
  public abstract long waitFor(long paramLong1, Sequence paramSequence, Sequence[] paramArrayOfSequence, SequenceBarrier paramSequenceBarrier, long paramLong2, TimeUnit paramTimeUnit)
    throws AlertException, InterruptedException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.WaitStrategy
 * JD-Core Version:    0.6.2
 */