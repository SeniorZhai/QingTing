package com.lmax.disruptor;

public final class RingBuffer<T> extends Sequencer
{
  private final Object[] entries;
  private final int indexMask;

  public RingBuffer(EventFactory<T> paramEventFactory, int paramInt)
  {
    this(paramEventFactory, new MultiThreadedClaimStrategy(paramInt), new BlockingWaitStrategy());
  }

  public RingBuffer(EventFactory<T> paramEventFactory, ClaimStrategy paramClaimStrategy, WaitStrategy paramWaitStrategy)
  {
    super(paramClaimStrategy, paramWaitStrategy);
    if (Integer.bitCount(paramClaimStrategy.getBufferSize()) != 1)
      throw new IllegalArgumentException("bufferSize must be a power of 2");
    this.indexMask = (paramClaimStrategy.getBufferSize() - 1);
    this.entries = new Object[paramClaimStrategy.getBufferSize()];
    fill(paramEventFactory);
  }

  private void fill(EventFactory<T> paramEventFactory)
  {
    int i = 0;
    while (i < this.entries.length)
    {
      this.entries[i] = paramEventFactory.newInstance();
      i += 1;
    }
  }

  public T get(long paramLong)
  {
    return this.entries[((int)paramLong & this.indexMask)];
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.RingBuffer
 * JD-Core Version:    0.6.2
 */