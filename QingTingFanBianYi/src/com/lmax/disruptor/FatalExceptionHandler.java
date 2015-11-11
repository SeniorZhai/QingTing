package com.lmax.disruptor;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class FatalExceptionHandler
  implements ExceptionHandler
{
  private static final Logger LOGGER = Logger.getLogger(FatalExceptionHandler.class.getName());
  private final Logger logger;

  public FatalExceptionHandler()
  {
    this.logger = LOGGER;
  }

  public FatalExceptionHandler(Logger paramLogger)
  {
    this.logger = paramLogger;
  }

  public void handleEventException(Throwable paramThrowable, long paramLong, Object paramObject)
  {
    this.logger.log(Level.SEVERE, "Exception processing: " + paramLong + " " + paramObject, paramThrowable);
    throw new RuntimeException(paramThrowable);
  }

  public void handleOnShutdownException(Throwable paramThrowable)
  {
    this.logger.log(Level.SEVERE, "Exception during onShutdown()", paramThrowable);
  }

  public void handleOnStartException(Throwable paramThrowable)
  {
    this.logger.log(Level.SEVERE, "Exception during onStart()", paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.FatalExceptionHandler
 * JD-Core Version:    0.6.2
 */