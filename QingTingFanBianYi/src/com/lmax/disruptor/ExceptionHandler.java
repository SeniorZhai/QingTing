package com.lmax.disruptor;

public abstract interface ExceptionHandler
{
  public abstract void handleEventException(Throwable paramThrowable, long paramLong, Object paramObject);

  public abstract void handleOnShutdownException(Throwable paramThrowable);

  public abstract void handleOnStartException(Throwable paramThrowable);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.ExceptionHandler
 * JD-Core Version:    0.6.2
 */