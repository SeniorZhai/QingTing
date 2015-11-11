package com.lmax.disruptor;

import com.lmax.disruptor.util.Util;
import java.util.concurrent.TimeUnit;

public final class BusySpinWaitStrategy
  implements WaitStrategy
{
  public void signalAllWhenBlocking()
  {
  }

  public long waitFor(long paramLong, Sequence paramSequence, Sequence[] paramArrayOfSequence, SequenceBarrier paramSequenceBarrier)
    throws AlertException, InterruptedException
  {
    long l2;
    long l1;
    if (paramArrayOfSequence.length == 0)
      while (true)
      {
        l2 = paramSequence.get();
        l1 = l2;
        if (l2 >= paramLong)
          break;
        paramSequenceBarrier.checkAlert();
      }
    while (true)
    {
      l2 = Util.getMinimumSequence(paramArrayOfSequence);
      l1 = l2;
      if (l2 >= paramLong)
        break;
      paramSequenceBarrier.checkAlert();
    }
    return l1;
  }

  public long waitFor(long paramLong1, Sequence paramSequence, Sequence[] paramArrayOfSequence, SequenceBarrier paramSequenceBarrier, long paramLong2, TimeUnit paramTimeUnit)
    throws AlertException, InterruptedException
  {
    long l2 = paramTimeUnit.toMillis(paramLong2);
    long l3 = System.currentTimeMillis();
    long l1;
    if (paramArrayOfSequence.length == 0)
    {
      do
      {
        l1 = paramSequence.get();
        paramLong2 = l1;
        if (l1 >= paramLong1)
          break;
        paramSequenceBarrier.checkAlert();
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
      paramSequenceBarrier.checkAlert();
    }
    while (System.currentTimeMillis() - l3 <= l2);
    return l1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.BusySpinWaitStrategy
 * JD-Core Version:    0.6.2
 */