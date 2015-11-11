package com.lmax.disruptor.dsl;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventProcessor;
import com.lmax.disruptor.SequenceBarrier;

class EventProcessorInfo<T>
{
  private final SequenceBarrier barrier;
  private boolean endOfChain = true;
  private final EventProcessor eventprocessor;
  private final EventHandler<T> handler;

  EventProcessorInfo(EventProcessor paramEventProcessor, EventHandler<T> paramEventHandler, SequenceBarrier paramSequenceBarrier)
  {
    this.eventprocessor = paramEventProcessor;
    this.handler = paramEventHandler;
    this.barrier = paramSequenceBarrier;
  }

  public SequenceBarrier getBarrier()
  {
    return this.barrier;
  }

  public EventProcessor getEventProcessor()
  {
    return this.eventprocessor;
  }

  public EventHandler<T> getHandler()
  {
    return this.handler;
  }

  public boolean isEndOfChain()
  {
    return this.endOfChain;
  }

  public void markAsUsedInBarrier()
  {
    this.endOfChain = false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.dsl.EventProcessorInfo
 * JD-Core Version:    0.6.2
 */