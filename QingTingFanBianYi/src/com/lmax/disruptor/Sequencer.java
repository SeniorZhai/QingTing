package com.lmax.disruptor;

import com.lmax.disruptor.util.Util;

public class Sequencer
{
  public static final long INITIAL_CURSOR_VALUE = -1L;
  private final ClaimStrategy claimStrategy;
  private final Sequence cursor = new Sequence(-1L);
  private Sequence[] gatingSequences;
  private final WaitStrategy waitStrategy;

  public Sequencer(ClaimStrategy paramClaimStrategy, WaitStrategy paramWaitStrategy)
  {
    this.claimStrategy = paramClaimStrategy;
    this.waitStrategy = paramWaitStrategy;
  }

  private void publish(long paramLong, int paramInt)
  {
    this.claimStrategy.serialisePublishing(paramLong, this.cursor, paramInt);
    this.waitStrategy.signalAllWhenBlocking();
  }

  public long claim(long paramLong)
  {
    if (this.gatingSequences == null)
      throw new NullPointerException("gatingSequences must be set before claiming sequences");
    this.claimStrategy.setSequence(paramLong, this.gatingSequences);
    return paramLong;
  }

  public void forcePublish(long paramLong)
  {
    this.cursor.set(paramLong);
    this.waitStrategy.signalAllWhenBlocking();
  }

  public int getBufferSize()
  {
    return this.claimStrategy.getBufferSize();
  }

  public long getCursor()
  {
    return this.cursor.get();
  }

  public boolean hasAvailableCapacity(int paramInt)
  {
    return this.claimStrategy.hasAvailableCapacity(paramInt, this.gatingSequences);
  }

  public SequenceBarrier newBarrier(Sequence[] paramArrayOfSequence)
  {
    return new ProcessingSequenceBarrier(this.waitStrategy, this.cursor, paramArrayOfSequence);
  }

  public BatchDescriptor newBatchDescriptor(int paramInt)
  {
    return new BatchDescriptor(Math.min(paramInt, this.claimStrategy.getBufferSize()));
  }

  public long next()
  {
    if (this.gatingSequences == null)
      throw new NullPointerException("gatingSequences must be set before claiming sequences");
    return this.claimStrategy.incrementAndGet(this.gatingSequences);
  }

  public BatchDescriptor next(BatchDescriptor paramBatchDescriptor)
  {
    if (this.gatingSequences == null)
      throw new NullPointerException("gatingSequences must be set before claiming sequences");
    paramBatchDescriptor.setEnd(this.claimStrategy.incrementAndGet(paramBatchDescriptor.getSize(), this.gatingSequences));
    return paramBatchDescriptor;
  }

  public void publish(long paramLong)
  {
    publish(paramLong, 1);
  }

  public void publish(BatchDescriptor paramBatchDescriptor)
  {
    publish(paramBatchDescriptor.getEnd(), paramBatchDescriptor.getSize());
  }

  public long remainingCapacity()
  {
    long l1 = Util.getMinimumSequence(this.gatingSequences);
    long l2 = this.cursor.get();
    return getBufferSize() - (l2 - l1);
  }

  public void setGatingSequences(Sequence[] paramArrayOfSequence)
  {
    this.gatingSequences = paramArrayOfSequence;
  }

  public long tryNext(int paramInt)
    throws InsufficientCapacityException
  {
    if (this.gatingSequences == null)
      throw new NullPointerException("gatingSequences must be set before claiming sequences");
    if (paramInt < 1)
      throw new IllegalArgumentException("Required capacity must be greater than 0");
    return this.claimStrategy.checkAndIncrement(paramInt, 1, this.gatingSequences);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.Sequencer
 * JD-Core Version:    0.6.2
 */