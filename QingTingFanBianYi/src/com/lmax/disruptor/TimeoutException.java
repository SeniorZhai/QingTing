package com.lmax.disruptor;

public class TimeoutException extends Exception
{
  public static final TimeoutException INSTANCE = new TimeoutException();

  public Throwable fillInStackTrace()
  {
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.TimeoutException
 * JD-Core Version:    0.6.2
 */