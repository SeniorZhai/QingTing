package com.lmax.disruptor;

import java.util.concurrent.atomic.AtomicBoolean;

public final class BatchEventProcessor<T>
  implements EventProcessor
{
  private final EventHandler<T> eventHandler;
  private ExceptionHandler exceptionHandler = new FatalExceptionHandler();
  private final RingBuffer<T> ringBuffer;
  private final AtomicBoolean running = new AtomicBoolean(false);
  private final Sequence sequence = new Sequence(-1L);
  private final SequenceBarrier sequenceBarrier;

  public BatchEventProcessor(RingBuffer<T> paramRingBuffer, SequenceBarrier paramSequenceBarrier, EventHandler<T> paramEventHandler)
  {
    this.ringBuffer = paramRingBuffer;
    this.sequenceBarrier = paramSequenceBarrier;
    this.eventHandler = paramEventHandler;
    if ((paramEventHandler instanceof SequenceReportingEventHandler))
      ((SequenceReportingEventHandler)paramEventHandler).setSequenceCallback(this.sequence);
  }

  private void notifyShutdown()
  {
    if ((this.eventHandler instanceof LifecycleAware));
    try
    {
      ((LifecycleAware)this.eventHandler).onShutdown();
      return;
    }
    catch (Throwable localThrowable)
    {
      this.exceptionHandler.handleOnShutdownException(localThrowable);
    }
  }

  private void notifyStart()
  {
    if ((this.eventHandler instanceof LifecycleAware));
    try
    {
      ((LifecycleAware)this.eventHandler).onStart();
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
    Object localObject1 = null;
    long l1 = this.sequence.get() + 1L;
    Object localObject4 = localObject1;
    long l2 = l1;
    Object localObject5 = localObject1;
    long l3 = l1;
    while (true)
    {
      try
      {
        long l4 = this.sequenceBarrier.waitFor(l1);
        if (l1 <= l4)
        {
          localObject4 = localObject1;
          l2 = l1;
          localObject5 = localObject1;
          l3 = l1;
          localObject1 = this.ringBuffer.get(l1);
          localObject4 = localObject1;
          l2 = l1;
          localObject5 = localObject1;
          l3 = l1;
          EventHandler localEventHandler = this.eventHandler;
          if (l1 != l4)
            break label255;
          bool = true;
          localObject4 = localObject1;
          l2 = l1;
          localObject5 = localObject1;
          l3 = l1;
          localEventHandler.onEvent(localObject1, l1, bool);
          l1 += 1L;
          continue;
        }
        localObject4 = localObject1;
        l2 = l1;
        localObject5 = localObject1;
        l3 = l1;
        this.sequence.set(l1 - 1L);
      }
      catch (AlertException localAlertException)
      {
        Object localObject2 = localObject4;
        l1 = l2;
        if (this.running.get())
          break;
        notifyShutdown();
        this.running.set(false);
        return;
      }
      catch (Throwable localThrowable)
      {
        this.exceptionHandler.handleEventException(localThrowable, l3, localObject5);
        this.sequence.set(l3);
        l1 = l3 + 1L;
        Object localObject3 = localObject5;
      }
      break;
      label255: boolean bool = false;
    }
  }

  public void setExceptionHandler(ExceptionHandler paramExceptionHandler)
  {
    if (paramExceptionHandler == null)
      throw new NullPointerException();
    this.exceptionHandler = paramExceptionHandler;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.BatchEventProcessor
 * JD-Core Version:    0.6.2
 */