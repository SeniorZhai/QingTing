package com.lmax.disruptor;

import java.util.concurrent.atomic.AtomicLongArray;

public final class MultiThreadedClaimStrategy extends AbstractMultithreadedClaimStrategy
  implements ClaimStrategy
{
  private static final int RETRIES = 1000;
  private final int pendingMask;
  private final AtomicLongArray pendingPublication;

  public MultiThreadedClaimStrategy(int paramInt)
  {
    this(paramInt, 1024);
  }

  public MultiThreadedClaimStrategy(int paramInt1, int paramInt2)
  {
    super(paramInt1);
    if (Integer.bitCount(paramInt2) != 1)
      throw new IllegalArgumentException("pendingBufferSize must be a power of 2, was: " + paramInt2);
    this.pendingPublication = new AtomicLongArray(paramInt2);
    this.pendingMask = (paramInt2 - 1);
  }

  public void serialisePublishing(long paramLong, Sequence paramSequence, int paramInt)
  {
    int i = 1000;
    while (paramLong - paramSequence.get() > this.pendingPublication.length())
    {
      int j = i - 1;
      i = j;
      if (j == 0)
      {
        Thread.yield();
        i = 1000;
      }
    }
    long l2 = paramLong - paramInt;
    for (long l1 = l2 + 1L; l1 < paramLong; l1 += 1L)
      this.pendingPublication.lazySet((int)l1 & this.pendingMask, l1);
    this.pendingPublication.set((int)paramLong & this.pendingMask, paramLong);
    l1 = paramSequence.get();
    if (l1 >= paramLong);
    do
    {
      return;
      while (!paramSequence.compareAndSet(l1, paramLong))
      {
        l1 = Math.max(l2, l1);
        paramLong = l1 + 1L;
      }
      l1 = paramLong;
      l2 = paramLong + 1L;
      paramLong = l2;
    }
    while (this.pendingPublication.get((int)l2 & this.pendingMask) == l2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.MultiThreadedClaimStrategy
 * JD-Core Version:    0.6.2
 */