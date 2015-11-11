package com.lmax.disruptor;

import java.util.concurrent.TimeUnit;

final class ProcessingSequenceBarrier
  implements SequenceBarrier
{
  private volatile boolean alerted = false;
  private final Sequence cursorSequence;
  private final Sequence[] dependentSequences;
  private final WaitStrategy waitStrategy;

  public ProcessingSequenceBarrier(WaitStrategy paramWaitStrategy, Sequence paramSequence, Sequence[] paramArrayOfSequence)
  {
    this.waitStrategy = paramWaitStrategy;
    this.cursorSequence = paramSequence;
    this.dependentSequences = paramArrayOfSequence;
  }

  public void alert()
  {
    this.alerted = true;
    this.waitStrategy.signalAllWhenBlocking();
  }

  public void checkAlert()
    throws AlertException
  {
    if (this.alerted)
      throw AlertException.INSTANCE;
  }

  public void clearAlert()
  {
    this.alerted = false;
  }

  public long getCursor()
  {
    return this.cursorSequence.get();
  }

  public boolean isAlerted()
  {
    return this.alerted;
  }

  public long waitFor(long paramLong)
    throws AlertException, InterruptedException
  {
    checkAlert();
    return this.waitStrategy.waitFor(paramLong, this.cursorSequence, this.dependentSequences, this);
  }

  public long waitFor(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    throws AlertException, InterruptedException
  {
    checkAlert();
    return this.waitStrategy.waitFor(paramLong1, this.cursorSequence, this.dependentSequences, this, paramLong2, paramTimeUnit);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.ProcessingSequenceBarrier
 * JD-Core Version:    0.6.2
 */