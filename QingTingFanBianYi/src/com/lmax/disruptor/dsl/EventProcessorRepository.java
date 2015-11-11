package com.lmax.disruptor.dsl;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.SequenceBarrier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class EventProcessorRepository<T>
  implements Iterable<EventProcessorInfo<T>>
{
  private final Map<EventProcessor, EventProcessorInfo<T>> eventProcessorInfoByEventProcessor = new IdentityHashMap();
  private final Map<EventHandler<?>, EventProcessorInfo<T>> eventProcessorInfoByHandler = new IdentityHashMap();

  private EventProcessorInfo<T> getEventProcessorInfo(EventHandler<T> paramEventHandler)
  {
    return (EventProcessorInfo)this.eventProcessorInfoByHandler.get(paramEventHandler);
  }

  private EventProcessorInfo<T> getEventProcessorInfo(EventProcessor paramEventProcessor)
  {
    return (EventProcessorInfo)this.eventProcessorInfoByEventProcessor.get(paramEventProcessor);
  }

  public void add(EventProcessor paramEventProcessor)
  {
    EventProcessorInfo localEventProcessorInfo = new EventProcessorInfo(paramEventProcessor, null, null);
    this.eventProcessorInfoByEventProcessor.put(paramEventProcessor, localEventProcessorInfo);
  }

  public void add(EventProcessor paramEventProcessor, EventHandler<T> paramEventHandler, SequenceBarrier paramSequenceBarrier)
  {
    paramSequenceBarrier = new EventProcessorInfo(paramEventProcessor, paramEventHandler, paramSequenceBarrier);
    this.eventProcessorInfoByHandler.put(paramEventHandler, paramSequenceBarrier);
    this.eventProcessorInfoByEventProcessor.put(paramEventProcessor, paramSequenceBarrier);
  }

  public SequenceBarrier getBarrierFor(EventHandler<T> paramEventHandler)
  {
    paramEventHandler = getEventProcessorInfo(paramEventHandler);
    if (paramEventHandler != null)
      return paramEventHandler.getBarrier();
    return null;
  }

  public EventProcessor getEventProcessorFor(EventHandler<T> paramEventHandler)
  {
    EventProcessorInfo localEventProcessorInfo = getEventProcessorInfo(paramEventHandler);
    if (localEventProcessorInfo == null)
      throw new IllegalArgumentException("The event handler " + paramEventHandler + " is not processing events.");
    return localEventProcessorInfo.getEventProcessor();
  }

  public EventProcessor[] getLastEventProcessorsInChain()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.eventProcessorInfoByEventProcessor.values().iterator();
    while (localIterator.hasNext())
    {
      EventProcessorInfo localEventProcessorInfo = (EventProcessorInfo)localIterator.next();
      if (localEventProcessorInfo.isEndOfChain())
        localArrayList.add(localEventProcessorInfo.getEventProcessor());
    }
    return (EventProcessor[])localArrayList.toArray(new EventProcessor[localArrayList.size()]);
  }

  public Iterator<EventProcessorInfo<T>> iterator()
  {
    return this.eventProcessorInfoByEventProcessor.values().iterator();
  }

  public void unMarkEventProcessorsAsEndOfChain(EventProcessor[] paramArrayOfEventProcessor)
  {
    int j = paramArrayOfEventProcessor.length;
    int i = 0;
    while (i < j)
    {
      getEventProcessorInfo(paramArrayOfEventProcessor[i]).markAsUsedInBarrier();
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.dsl.EventProcessorRepository
 * JD-Core Version:    0.6.2
 */