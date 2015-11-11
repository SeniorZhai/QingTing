package com.lmax.disruptor;

public abstract interface LifecycleAware
{
  public abstract void onShutdown();

  public abstract void onStart();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.LifecycleAware
 * JD-Core Version:    0.6.2
 */