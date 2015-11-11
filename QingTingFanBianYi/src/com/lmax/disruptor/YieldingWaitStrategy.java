package com.lmax.disruptor;

import com.lmax.disruptor.util.Util;
import java.util.concurrent.TimeUnit;

public final class YieldingWaitStrategy
  implements WaitStrategy
{
  private static final int SPIN_TRIES = 100;

  private int applyWaitMethod(SequenceBarrier paramSequenceBarrier, int paramInt)
    throws AlertException
  {
    paramSequenceBarrier.checkAlert();
    if (paramInt == 0)
    {
      Thread.yield();
      return paramInt;
    }
    return paramInt - 1;
  }

  public void signalAllWhenBlocking()
  {
  }

  public long waitFor(long paramLong, Sequence paramSequence, Sequence[] paramArrayOfSequence, SequenceBarrier paramSequenceBarrier)
    throws AlertException, InterruptedException
  {
    int i = 100;
    int j = i;
    long l2;
    long l1;
    if (paramArrayOfSequence.length == 0)
      while (true)
      {
        l2 = paramSequence.get();
        l1 = l2;
        if (l2 >= paramLong)
          break;
        i = applyWaitMethod(paramSequenceBarrier, i);
      }
    while (true)
    {
      l2 = Util.getMinimumSequence(paramArrayOfSequence);
      l1 = l2;
      if (l2 >= paramLong)
        break;
      j = applyWaitMethod(paramSequenceBarrier, j);
    }
    return l1;
  }

  public long waitFor(long paramLong1, Sequence paramSequence, Sequence[] paramArrayOfSequence, SequenceBarrier paramSequenceBarrier, long paramLong2, TimeUnit paramTimeUnit)
    throws AlertException, InterruptedException
  {
    long l2 = paramTimeUnit.toMillis(paramLong2);
    long l3 = System.currentTimeMillis();
    int i = 100;
    int j = i;
    long l1;
    if (paramArrayOfSequence.length == 0)
    {
      do
      {
        l1 = paramSequence.get();
        paramLong2 = l1;
        if (l1 >= paramLong1)
          break;
        i = applyWaitMethod(paramSequenceBarrier, i);
      }
      while (System.currentTimeMillis() - l3 <= l2);
      paramLong2 = l1;
      return paramLong2;
    }
    do
    {
      l1 = Util.getMinimumSequence(paramArrayOfSequence);
      paramLong2 = l1;
      if (l1 >= paramLong1)
        break;
      j = applyWaitMethod(paramSequenceBarrier, j);
    }
    while (System.currentTimeMillis() - l3 <= l2);
    return l1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.YieldingWaitStrategy
 * JD-Core Version:    0.6.2
 */