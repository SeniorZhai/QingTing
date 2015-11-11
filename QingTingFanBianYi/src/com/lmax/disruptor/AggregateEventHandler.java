package com.lmax.disruptor;

public final class AggregateEventHandler<T>
  implements EventHandler<T>, LifecycleAware
{
  private final EventHandler<T>[] eventHandlers;

  public AggregateEventHandler(EventHandler<T>[] paramArrayOfEventHandler)
  {
    this.eventHandlers = paramArrayOfEventHandler;
  }

  public void onEvent(T paramT, long paramLong, boolean paramBoolean)
    throws Exception
  {
    EventHandler[] arrayOfEventHandler = this.eventHandlers;
    int j = arrayOfEventHandler.length;
    int i = 0;
    while (i < j)
    {
      arrayOfEventHandler[i].onEvent(paramT, paramLong, paramBoolean);
      i += 1;
    }
  }

  public void onShutdown()
  {
    EventHandler[] arrayOfEventHandler = this.eventHandlers;
    int j = arrayOfEventHandler.length;
    int i = 0;
    while (i < j)
    {
      EventHandler localEventHandler = arrayOfEventHandler[i];
      if ((localEventHandler instanceof LifecycleAware))
        ((LifecycleAware)localEventHandler).onShutdown();
      i += 1;
    }
  }

  public void onStart()
  {
    EventHandler[] arrayOfEventHandler = this.eventHandlers;
    int j = arrayOfEventHandler.length;
    int i = 0;
    while (i < j)
    {
      EventHandler localEventHandler = arrayOfEventHandler[i];
      if ((localEventHandler instanceof LifecycleAware))
        ((LifecycleAware)localEventHandler).onStart();
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.AggregateEventHandler
 * JD-Core Version:    0.6.2
 */