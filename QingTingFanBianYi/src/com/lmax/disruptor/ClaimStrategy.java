package com.lmax.disruptor;

public abstract interface ClaimStrategy
{
  public abstract long checkAndIncrement(int paramInt1, int paramInt2, Sequence[] paramArrayOfSequence)
    throws InsufficientCapacityException;

  public abstract int getBufferSize();

  public abstract long getSequence();

  public abstract boolean hasAvailableCapacity(int paramInt, Sequence[] paramArrayOfSequence);

  public abstract long incrementAndGet(int paramInt, Sequence[] paramArrayOfSequence);

  public abstract long incrementAndGet(Sequence[] paramArrayOfSequence);

  public abstract void serialisePublishing(long paramLong, Sequence paramSequence, int paramInt);

  public abstract void setSequence(long paramLong, Sequence[] paramArrayOfSequence);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.ClaimStrategy
 * JD-Core Version:    0.6.2
 */