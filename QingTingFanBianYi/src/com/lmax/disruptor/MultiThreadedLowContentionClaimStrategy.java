package com.lmax.disruptor;

public final class MultiThreadedLowContentionClaimStrategy extends AbstractMultithreadedClaimStrategy
{
  public MultiThreadedLowContentionClaimStrategy(int paramInt)
  {
    super(paramInt);
  }

  public void serialisePublishing(long paramLong, Sequence paramSequence, int paramInt)
  {
    long l = paramInt;
    while (paramLong - l != paramSequence.get());
    paramSequence.set(paramLong);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.MultiThreadedLowContentionClaimStrategy
 * JD-Core Version:    0.6.2
 */