package com.lmax.disruptor;

import com.lmax.disruptor.util.Util;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public final class WorkerPool<T>
{
  private final RingBuffer<T> ringBuffer;
  private final AtomicBoolean started = new AtomicBoolean(false);
  private final WorkProcessor<?>[] workProcessors;
  private final Sequence workSequence = new Sequence(-1L);

  public WorkerPool(EventFactory<T> paramEventFactory, ClaimStrategy paramClaimStrategy, WaitStrategy paramWaitStrategy, ExceptionHandler paramExceptionHandler, WorkHandler<T>[] paramArrayOfWorkHandler)
  {
    this.ringBuffer = new RingBuffer(paramEventFactory, paramClaimStrategy, paramWaitStrategy);
    paramEventFactory = this.ringBuffer.newBarrier(new Sequence[0]);
    int j = paramArrayOfWorkHandler.length;
    this.workProcessors = new WorkProcessor[j];
    int i = 0;
    while (i < j)
    {
      this.workProcessors[i] = new WorkProcessor(this.ringBuffer, paramEventFactory, paramArrayOfWorkHandler[i], paramExceptionHandler, this.workSequence);
      i += 1;
    }
    this.ringBuffer.setGatingSequences(getWorkerSequences());
  }

  public WorkerPool(RingBuffer<T> paramRingBuffer, SequenceBarrier paramSequenceBarrier, ExceptionHandler paramExceptionHandler, WorkHandler<T>[] paramArrayOfWorkHandler)
  {
    this.ringBuffer = paramRingBuffer;
    int j = paramArrayOfWorkHandler.length;
    this.workProcessors = new WorkProcessor[j];
    int i = 0;
    while (i < j)
    {
      this.workProcessors[i] = new WorkProcessor(paramRingBuffer, paramSequenceBarrier, paramArrayOfWorkHandler[i], paramExceptionHandler, this.workSequence);
      i += 1;
    }
  }

  public void drainAndHalt()
  {
    Object localObject = getWorkerSequences();
    while (this.ringBuffer.getCursor() > Util.getMinimumSequence((Sequence[])localObject))
      Thread.yield();
    localObject = this.workProcessors;
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localObject[i].halt();
      i += 1;
    }
    this.started.set(false);
  }

  public Sequence[] getWorkerSequences()
  {
    Sequence[] arrayOfSequence = new Sequence[this.workProcessors.length];
    int i = 0;
    int j = this.workProcessors.length;
    while (i < j)
    {
      arrayOfSequence[i] = this.workProcessors[i].getSequence();
      i += 1;
    }
    return arrayOfSequence;
  }

  public void halt()
  {
    WorkProcessor[] arrayOfWorkProcessor = this.workProcessors;
    int j = arrayOfWorkProcessor.length;
    int i = 0;
    while (i < j)
    {
      arrayOfWorkProcessor[i].halt();
      i += 1;
    }
    this.started.set(false);
  }

  public RingBuffer<T> start(Executor paramExecutor)
  {
    if (!this.started.compareAndSet(false, true))
      throw new IllegalStateException("WorkerPool has already been started and cannot be restarted until halted.");
    long l = this.ringBuffer.getCursor();
    this.workSequence.set(l);
    WorkProcessor[] arrayOfWorkProcessor = this.workProcessors;
    int j = arrayOfWorkProcessor.length;
    int i = 0;
    while (i < j)
    {
      WorkProcessor localWorkProcessor = arrayOfWorkProcessor[i];
      localWorkProcessor.getSequence().set(l);
      paramExecutor.execute(localWorkProcessor);
      i += 1;
    }
    return this.ringBuffer;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.WorkerPool
 * JD-Core Version:    0.6.2
 */