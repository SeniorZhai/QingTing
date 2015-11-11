package com.lmax.disruptor;

import com.lmax.disruptor.util.Util;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class BlockingWaitStrategy
  implements WaitStrategy
{
  private final Lock lock = new ReentrantLock();
  private volatile int numWaiters = 0;
  private final Condition processorNotifyCondition = this.lock.newCondition();

  public void signalAllWhenBlocking()
  {
    if (this.numWaiters != 0)
      this.lock.lock();
    try
    {
      this.processorNotifyCondition.signalAll();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  public long waitFor(long paramLong, Sequence paramSequence, Sequence[] paramArrayOfSequence, SequenceBarrier paramSequenceBarrier)
    throws AlertException, InterruptedException
  {
    long l2 = paramSequence.get();
    long l1 = l2;
    if (l2 < paramLong)
    {
      this.lock.lock();
      try
      {
        this.numWaiters += 1;
        while (true)
        {
          l1 = paramSequence.get();
          if (l1 >= paramLong)
            break;
          paramSequenceBarrier.checkAlert();
          this.processorNotifyCondition.await(1L, TimeUnit.MILLISECONDS);
        }
      }
      finally
      {
        this.numWaiters -= 1;
        this.lock.unlock();
      }
      this.numWaiters -= 1;
      this.lock.unlock();
    }
    if (paramArrayOfSequence.length != 0)
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
    long l2 = paramSequence.get();
    long l1 = l2;
    if (l2 < paramLong1)
      this.lock.lock();
    try
    {
      this.numWaiters += 1;
      boolean bool;
      do
      {
        l1 = paramSequence.get();
        if (l1 >= paramLong1)
          break;
        paramSequenceBarrier.checkAlert();
        bool = this.processorNotifyCondition.await(paramLong2, paramTimeUnit);
      }
      while (bool);
      this.numWaiters -= 1;
      this.lock.unlock();
      if (paramArrayOfSequence.length != 0)
        while (true)
        {
          paramLong2 = Util.getMinimumSequence(paramArrayOfSequence);
          l1 = paramLong2;
          if (paramLong2 >= paramLong1)
            break;
          paramSequenceBarrier.checkAlert();
        }
    }
    finally
    {
      this.numWaiters -= 1;
      this.lock.unlock();
    }
    return l1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.BlockingWaitStrategy
 * JD-Core Version:    0.6.2
 */