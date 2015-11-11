package com.lmax.disruptor.dsl;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.SequenceBarrier;

public class ExceptionHandlerSetting<T>
{
  private final EventHandler<T> eventHandler;
  private final EventProcessorRepository<T> eventProcessorRepository;

  ExceptionHandlerSetting(EventHandler<T> paramEventHandler, EventProcessorRepository<T> paramEventProcessorRepository)
  {
    this.eventHandler = paramEventHandler;
    this.eventProcessorRepository = paramEventProcessorRepository;
  }

  public void with(ExceptionHandler paramExceptionHandler)
  {
    ((BatchEventProcessor)this.eventProcessorRepository.getEventProcessorFor(this.eventHandler)).setExceptionHandler(paramExceptionHandler);
    this.eventProcessorRepository.getBarrierFor(this.eventHandler).alert();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.dsl.ExceptionHandlerSetting
 * JD-Core Version:    0.6.2
 */