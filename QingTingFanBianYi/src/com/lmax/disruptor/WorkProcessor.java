package com.lmax.disruptor;

import java.util.concurrent.atomic.AtomicBoolean;

public final class WorkProcessor<T>
  implements EventProcessor
{
  private final ExceptionHandler exceptionHandler;
  private final RingBuffer<T> ringBuffer;
  private final AtomicBoolean running = new AtomicBoolean(false);
  private final Sequence sequence = new Sequence(-1L);
  private final SequenceBarrier sequenceBarrier;
  private final WorkHandler<T> workHandler;
  private final Sequence workSequence;

  public WorkProcessor(RingBuffer<T> paramRingBuffer, SequenceBarrier paramSequenceBarrier, WorkHandler<T> paramWorkHandler, ExceptionHandler paramExceptionHandler, Sequence paramSequence)
  {
    this.ringBuffer = paramRingBuffer;
    this.sequenceBarrier = paramSequenceBarrier;
    this.workHandler = paramWorkHandler;
    this.exceptionHandler = paramExceptionHandler;
    this.workSequence = paramSequence;
  }

  private void notifyShutdown()
  {
    if ((this.workHandler instanceof LifecycleAware));
    try
    {
      ((LifecycleAware)this.workHandler).onShutdown();
      return;
    }
    catch (Throwable localThrowable)
    {
      this.exceptionHandler.handleOnShutdownException(localThrowable);
    }
  }

  private void notifyStart()
  {
    if ((this.workHandler instanceof LifecycleAware));
    try
    {
      ((LifecycleAware)this.workHandler).onStart();
      return;
    }
    catch (Throwable localThrowable)
    {
      this.exceptionHandler.handleOnStartException(localThrowable);
    }
  }

  public Sequence getSequence()
  {
    return this.sequence;
  }

  public void halt()
  {
    this.running.set(false);
    this.sequenceBarrier.alert();
  }

  public void run()
  {
    if (!this.running.compareAndSet(false, true))
      throw new IllegalStateException("Thread is already running");
    this.sequenceBarrier.clearAlert();
    notifyStart();
    int i = 1;
    long l2 = this.sequence.get();
    Object localObject1 = null;
    while (true)
    {
      long l1 = l2;
      int j = i;
      int k;
      Object localObject4;
      long l3;
      Object localObject5;
      if (i != 0)
      {
        k = 0;
        j = 0;
        localObject4 = localObject1;
        l3 = l2;
        i = k;
        localObject5 = localObject1;
      }
      try
      {
        l1 = this.workSequence.incrementAndGet();
        localObject4 = localObject1;
        l3 = l1;
        i = k;
        localObject5 = localObject1;
        l2 = l1;
        this.sequence.set(l1 - 1L);
        localObject4 = localObject1;
        l3 = l1;
        i = j;
        localObject5 = localObject1;
        l2 = l1;
        this.sequenceBarrier.waitFor(l1);
        localObject4 = localObject1;
        l3 = l1;
        i = j;
        localObject5 = localObject1;
        l2 = l1;
        localObject1 = this.ringBuffer.get(l1);
        localObject4 = localObject1;
        l3 = l1;
        i = j;
        localObject5 = localObject1;
        l2 = l1;
        this.workHandler.onEvent(localObject1);
        i = 1;
        l2 = l1;
      }
      catch (AlertException localAlertException)
      {
        Object localObject2 = localObject4;
        l2 = l3;
        if (!this.running.get())
        {
          notifyShutdown();
          this.running.set(false);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        this.exceptionHandler.handleEventException(localThrowable, l2, localObject5);
        i = 1;
        Object localObject3 = localObject5;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.WorkProcessor
 * JD-Core Version:    0.6.2
 */