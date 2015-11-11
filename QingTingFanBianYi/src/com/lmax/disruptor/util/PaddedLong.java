package com.lmax.disruptor.util;

public final class PaddedLong extends MutableLong
{
  public volatile long p1;
  public volatile long p2;
  public volatile long p3;
  public volatile long p4;
  public volatile long p5;
  public volatile long p6 = 7L;

  public PaddedLong()
  {
  }

  public PaddedLong(long paramLong)
  {
    super(paramLong);
  }

  public long sumPaddingToPreventOptimisation()
  {
    return this.p1 + this.p2 + this.p3 + this.p4 + this.p5 + this.p6;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.util.PaddedLong
 * JD-Core Version:    0.6.2
 */