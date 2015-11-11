package com.lmax.disruptor.dsl;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.util.Util;

public class EventHandlerGroup<T>
{
  private final Disruptor<T> disruptor;
  private final EventProcessorRepository<T> eventProcessorRepository;
  private final EventProcessor[] eventProcessors;

  EventHandlerGroup(Disruptor<T> paramDisruptor, EventProcessorRepository<T> paramEventProcessorRepository, EventProcessor[] paramArrayOfEventProcessor)
  {
    this.disruptor = paramDisruptor;
    this.eventProcessorRepository = paramEventProcessorRepository;
    this.eventProcessors = paramArrayOfEventProcessor;
  }

  public EventHandlerGroup<T> and(EventHandler<T>[] paramArrayOfEventHandler)
  {
    EventProcessor[] arrayOfEventProcessor = new EventProcessor[this.eventProcessors.length + paramArrayOfEventHandler.length];
    int i = 0;
    while (i < paramArrayOfEventHandler.length)
    {
      arrayOfEventProcessor[i] = this.eventProcessorRepository.getEventProcessorFor(paramArrayOfEventHandler[i]);
      i += 1;
    }
    System.arraycopy(this.eventProcessors, 0, arrayOfEventProcessor, paramArrayOfEventHandler.length, this.eventProcessors.length);
    return new EventHandlerGroup(this.disruptor, this.eventProcessorRepository, arrayOfEventProcessor);
  }

  public EventHandlerGroup<T> and(EventProcessor[] paramArrayOfEventProcessor)
  {
    EventProcessor[] arrayOfEventProcessor = new EventProcessor[this.eventProcessors.length + paramArrayOfEventProcessor.length];
    int j = paramArrayOfEventProcessor.length;
    int i = 0;
    while (i < j)
    {
      EventProcessor localEventProcessor = paramArrayOfEventProcessor[i];
      this.eventProcessorRepository.add(localEventProcessor);
      i += 1;
    }
    System.arraycopy(paramArrayOfEventProcessor, 0, arrayOfEventProcessor, 0, paramArrayOfEventProcessor.length);
    System.arraycopy(this.eventProcessors, 0, arrayOfEventProcessor, paramArrayOfEventProcessor.length, this.eventProcessors.length);
    return new EventHandlerGroup(this.disruptor, this.eventProcessorRepository, arrayOfEventProcessor);
  }

  public SequenceBarrier asSequenceBarrier()
  {
    return this.disruptor.getRingBuffer().newBarrier(Util.getSequencesFor(this.eventProcessors));
  }

  public EventHandlerGroup<T> handleEventsWith(EventHandler<T>[] paramArrayOfEventHandler)
  {
    return this.disruptor.createEventProcessors(this.eventProcessors, paramArrayOfEventHandler);
  }

  public EventHandlerGroup<T> then(EventHandler<T>[] paramArrayOfEventHandler)
  {
    return handleEventsWith(paramArrayOfEventHandler);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.dsl.EventHandlerGroup
 * JD-Core Version:    0.6.2
 */