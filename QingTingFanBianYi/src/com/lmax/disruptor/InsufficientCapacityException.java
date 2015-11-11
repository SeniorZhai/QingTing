package com.lmax.disruptor;

public class InsufficientCapacityException extends Exception
{
  public static final InsufficientCapacityException INSTANCE = new InsufficientCapacityException();

  public Throwable fillInStackTrace()
  {
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.InsufficientCapacityException
 * JD-Core Version:    0.6.2
 */