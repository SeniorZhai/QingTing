package com.lmax.disruptor;

import com.lmax.disruptor.util.MutableLong;
import com.lmax.disruptor.util.Util;
import java.util.concurrent.locks.LockSupport;

public abstract class AbstractMultithreadedClaimStrategy
  implements ClaimStrategy
{
  private final int bufferSize;
  private final Sequence claimSequence = new Sequence(-1L);
  private final ThreadLocal<MutableLong> minGatingSequenceThreadLocal = new ThreadLocal()
  {
    protected MutableLong initialValue()
    {
      return new MutableLong(-1L);
    }
  };

  public AbstractMultithreadedClaimStrategy(int paramInt)
  {
    this.bufferSize = paramInt;
  }

  private boolean hasAvailableCapacity(long paramLong, int paramInt, Sequence[] paramArrayOfSequence)
  {
    paramLong = paramInt + paramLong - this.bufferSize;
    MutableLong localMutableLong = (MutableLong)this.minGatingSequenceThreadLocal.get();
    if (paramLong > localMutableLong.get())
    {
      long l = Util.getMinimumSequence(paramArrayOfSequence);
      localMutableLong.set(l);
      if (paramLong > l)
        return false;
    }
    return true;
  }

  private void waitForFreeSlotAt(long paramLong, Sequence[] paramArrayOfSequence, MutableLong paramMutableLong)
  {
    paramLong -= this.bufferSize;
    if (paramLong > paramMutableLong.get())
    {
      long l;
      while (true)
      {
        l = Util.getMinimumSequence(paramArrayOfSequence);
        if (paramLong <= l)
          break;
        LockSupport.parkNanos(1L);
      }
      paramMutableLong.set(l);
    }
  }

  public long checkAndIncrement(int paramInt1, int paramInt2, Sequence[] paramArrayOfSequence)
    throws InsufficientCapacityException
  {
    long l1;
    long l2;
    do
    {
      l1 = this.claimSequence.get();
      if (!hasAvailableCapacity(l1, paramInt1, paramArrayOfSequence))
        break;
      l2 = l1 + paramInt2;
    }
    while (!this.claimSequence.compareAndSet(l1, l2));
    return l2;
    throw InsufficientCapacityException.INSTANCE;
  }

  public int getBufferSize()
  {
    return this.bufferSize;
  }

  public long getSequence()
  {
    return this.claimSequence.get();
  }

  public boolean hasAvailableCapacity(int paramInt, Sequence[] paramArrayOfSequence)
  {
    return hasAvailableCapacity(this.claimSequence.get(), paramInt, paramArrayOfSequence);
  }

  public long incrementAndGet(int paramInt, Sequence[] paramArrayOfSequence)
  {
    long l = this.claimSequence.addAndGet(paramInt);
    waitForFreeSlotAt(l, paramArrayOfSequence, (MutableLong)this.minGatingSequenceThreadLocal.get());
    return l;
  }

  public long incrementAndGet(Sequence[] paramArrayOfSequence)
  {
    long l = this.claimSequence.incrementAndGet();
    waitForFreeSlotAt(l, paramArrayOfSequence, (MutableLong)this.minGatingSequenceThreadLocal.get());
    return l;
  }

  public void setSequence(long paramLong, Sequence[] paramArrayOfSequence)
  {
    this.claimSequence.set(paramLong);
    waitForFreeSlotAt(paramLong, paramArrayOfSequence, (MutableLong)this.minGatingSequenceThreadLocal.get());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.AbstractMultithreadedClaimStrategy
 * JD-Core Version:    0.6.2
 */