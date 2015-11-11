package com.lmax.disruptor.dsl;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.ClaimStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.EventPublisher;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.util.Util;
import java.util.Iterator;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public class Disruptor<T>
{
  private final EventProcessorRepository<T> eventProcessorRepository = new EventProcessorRepository();
  private final EventPublisher<T> eventPublisher;
  private ExceptionHandler exceptionHandler;
  private final Executor executor;
  private final RingBuffer<T> ringBuffer;
  private final AtomicBoolean started = new AtomicBoolean(false);

  public Disruptor(EventFactory<T> paramEventFactory, int paramInt, Executor paramExecutor)
  {
    this(new RingBuffer(paramEventFactory, paramInt), paramExecutor);
  }

  public Disruptor(EventFactory<T> paramEventFactory, Executor paramExecutor, ClaimStrategy paramClaimStrategy, WaitStrategy paramWaitStrategy)
  {
    this(new RingBuffer(paramEventFactory, paramClaimStrategy, paramWaitStrategy), paramExecutor);
  }

  private Disruptor(RingBuffer<T> paramRingBuffer, Executor paramExecutor)
  {
    this.ringBuffer = paramRingBuffer;
    this.executor = paramExecutor;
    this.eventPublisher = new EventPublisher(paramRingBuffer);
  }

  private void checkNotStarted()
  {
    if (this.started.get())
      throw new IllegalStateException("All event handlers must be added before calling starts.");
  }

  private void checkOnlyStartedOnce()
  {
    if (!this.started.compareAndSet(false, true))
      throw new IllegalStateException("Disruptor.start() must only be called once.");
  }

  private boolean hasBacklog()
  {
    long l = this.ringBuffer.getCursor();
    EventProcessor[] arrayOfEventProcessor = this.eventProcessorRepository.getLastEventProcessorsInChain();
    int j = arrayOfEventProcessor.length;
    int i = 0;
    while (i < j)
    {
      if (l != arrayOfEventProcessor[i].getSequence().get())
        return true;
      i += 1;
    }
    return false;
  }

  public EventHandlerGroup<T> after(EventHandler<T>[] paramArrayOfEventHandler)
  {
    EventProcessor[] arrayOfEventProcessor = new EventProcessor[paramArrayOfEventHandler.length];
    int i = 0;
    int j = paramArrayOfEventHandler.length;
    while (i < j)
    {
      arrayOfEventProcessor[i] = this.eventProcessorRepository.getEventProcessorFor(paramArrayOfEventHandler[i]);
      i += 1;
    }
    return new EventHandlerGroup(this, this.eventProcessorRepository, arrayOfEventProcessor);
  }

  public EventHandlerGroup<T> after(EventProcessor[] paramArrayOfEventProcessor)
  {
    int j = paramArrayOfEventProcessor.length;
    int i = 0;
    while (i < j)
    {
      EventProcessor localEventProcessor = paramArrayOfEventProcessor[i];
      this.eventProcessorRepository.add(localEventProcessor);
      i += 1;
    }
    return new EventHandlerGroup(this, this.eventProcessorRepository, paramArrayOfEventProcessor);
  }

  EventHandlerGroup<T> createEventProcessors(EventProcessor[] paramArrayOfEventProcessor, EventHandler<T>[] paramArrayOfEventHandler)
  {
    checkNotStarted();
    EventProcessor[] arrayOfEventProcessor = new EventProcessor[paramArrayOfEventHandler.length];
    SequenceBarrier localSequenceBarrier = this.ringBuffer.newBarrier(Util.getSequencesFor(paramArrayOfEventProcessor));
    int i = 0;
    int j = paramArrayOfEventHandler.length;
    while (i < j)
    {
      EventHandler<T> localEventHandler = paramArrayOfEventHandler[i];
      BatchEventProcessor localBatchEventProcessor = new BatchEventProcessor(this.ringBuffer, localSequenceBarrier, localEventHandler);
      if (this.exceptionHandler != null)
        localBatchEventProcessor.setExceptionHandler(this.exceptionHandler);
      this.eventProcessorRepository.add(localBatchEventProcessor, localEventHandler, localSequenceBarrier);
      arrayOfEventProcessor[i] = localBatchEventProcessor;
      i += 1;
    }
    if (arrayOfEventProcessor.length > 0)
      this.eventProcessorRepository.unMarkEventProcessorsAsEndOfChain(paramArrayOfEventProcessor);
    return new EventHandlerGroup(this, this.eventProcessorRepository, arrayOfEventProcessor);
  }

  public SequenceBarrier getBarrierFor(EventHandler<T> paramEventHandler)
  {
    return this.eventProcessorRepository.getBarrierFor(paramEventHandler);
  }

  public RingBuffer<T> getRingBuffer()
  {
    return this.ringBuffer;
  }

  public void halt()
  {
    Iterator localIterator = this.eventProcessorRepository.iterator();
    while (localIterator.hasNext())
      ((EventProcessorInfo)localIterator.next()).getEventProcessor().halt();
  }

  public EventHandlerGroup<T> handleEventsWith(EventHandler<T>[] paramArrayOfEventHandler)
  {
    return createEventProcessors(new EventProcessor[0], paramArrayOfEventHandler);
  }

  public EventHandlerGroup<T> handleEventsWith(EventProcessor[] paramArrayOfEventProcessor)
  {
    int j = paramArrayOfEventProcessor.length;
    int i = 0;
    while (i < j)
    {
      EventProcessor localEventProcessor = paramArrayOfEventProcessor[i];
      this.eventProcessorRepository.add(localEventProcessor);
      i += 1;
    }
    return new EventHandlerGroup(this, this.eventProcessorRepository, paramArrayOfEventProcessor);
  }

  public ExceptionHandlerSetting<?> handleExceptionsFor(EventHandler<T> paramEventHandler)
  {
    return new ExceptionHandlerSetting(paramEventHandler, this.eventProcessorRepository);
  }

  public void handleExceptionsWith(ExceptionHandler paramExceptionHandler)
  {
    this.exceptionHandler = paramExceptionHandler;
  }

  public void publishEvent(EventTranslator<T> paramEventTranslator)
  {
    this.eventPublisher.publishEvent(paramEventTranslator);
  }

  public void shutdown()
  {
    while (hasBacklog());
    halt();
  }

  public RingBuffer<T> start()
  {
    Object localObject = this.eventProcessorRepository.getLastEventProcessorsInChain();
    this.ringBuffer.setGatingSequences(Util.getSequencesFor((EventProcessor[])localObject));
    checkOnlyStartedOnce();
    localObject = this.eventProcessorRepository.iterator();
    while (((Iterator)localObject).hasNext())
    {
      EventProcessorInfo localEventProcessorInfo = (EventProcessorInfo)((Iterator)localObject).next();
      this.executor.execute(localEventProcessorInfo.getEventProcessor());
    }
    return this.ringBuffer;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.dsl.Disruptor
 * JD-Core Version:    0.6.2
 */