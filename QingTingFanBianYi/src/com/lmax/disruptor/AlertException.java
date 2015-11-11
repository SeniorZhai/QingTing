package com.lmax.disruptor;

public class AlertException extends Exception
{
  public static final AlertException INSTANCE = new AlertException();

  public Throwable fillInStackTrace()
  {
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.AlertException
 * JD-Core Version:    0.6.2
 */