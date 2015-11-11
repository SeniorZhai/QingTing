package com.lmax.disruptor;

import com.lmax.disruptor.util.PaddedLong;
import com.lmax.disruptor.util.Util;
import java.util.concurrent.locks.LockSupport;

public final class SingleThreadedClaimStrategy
  implements ClaimStrategy
{
  private final int bufferSize;
  private final PaddedLong claimSequence = new PaddedLong(-1L);
  private final PaddedLong minGatingSequence = new PaddedLong(-1L);

  public SingleThreadedClaimStrategy(int paramInt)
  {
    this.bufferSize = paramInt;
  }

  private void waitForFreeSlotAt(long paramLong, Sequence[] paramArrayOfSequence)
  {
    paramLong -= this.bufferSize;
    if (paramLong > this.minGatingSequence.get())
    {
      long l;
      while (true)
      {
        l = Util.getMinimumSequence(paramArrayOfSequence);
        if (paramLong <= l)
          break;
        LockSupport.parkNanos(1L);
      }
      this.minGatingSequence.set(l);
    }
  }

  public long checkAndIncrement(int paramInt1, int paramInt2, Sequence[] paramArrayOfSequence)
    throws InsufficientCapacityException
  {
    if (!hasAvailableCapacity(paramInt1, paramArrayOfSequence))
      throw InsufficientCapacityException.INSTANCE;
    return incrementAndGet(paramInt2, paramArrayOfSequence);
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
    long l1 = this.claimSequence.get() + paramInt - this.bufferSize;
    if (l1 > this.minGatingSequence.get())
    {
      long l2 = Util.getMinimumSequence(paramArrayOfSequence);
      this.minGatingSequence.set(l2);
      if (l1 > l2)
        return false;
    }
    return true;
  }

  public long incrementAndGet(int paramInt, Sequence[] paramArrayOfSequence)
  {
    long l = this.claimSequence.get() + paramInt;
    this.claimSequence.set(l);
    waitForFreeSlotAt(l, paramArrayOfSequence);
    return l;
  }

  public long incrementAndGet(Sequence[] paramArrayOfSequence)
  {
    long l = this.claimSequence.get() + 1L;
    this.claimSequence.set(l);
    waitForFreeSlotAt(l, paramArrayOfSequence);
    return l;
  }

  public void serialisePublishing(long paramLong, Sequence paramSequence, int paramInt)
  {
    paramSequence.set(paramLong);
  }

  public void setSequence(long paramLong, Sequence[] paramArrayOfSequence)
  {
    this.claimSequence.set(paramLong);
    waitForFreeSlotAt(paramLong, paramArrayOfSequence);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.SingleThreadedClaimStrategy
 * JD-Core Version:    0.6.2
 */