package com.lmax.disruptor;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class IgnoreExceptionHandler
  implements ExceptionHandler
{
  private static final Logger LOGGER = Logger.getLogger(IgnoreExceptionHandler.class.getName());
  private final Logger logger;

  public IgnoreExceptionHandler()
  {
    this.logger = LOGGER;
  }

  public IgnoreExceptionHandler(Logger paramLogger)
  {
    this.logger = paramLogger;
  }

  public void handleEventException(Throwable paramThrowable, long paramLong, Object paramObject)
  {
    this.logger.log(Level.INFO, "Exception processing: " + paramLong + " " + paramObject, paramThrowable);
  }

  public void handleOnShutdownException(Throwable paramThrowable)
  {
    this.logger.log(Level.INFO, "Exception during onShutdown()", paramThrowable);
  }

  public void handleOnStartException(Throwable paramThrowable)
  {
    this.logger.log(Level.INFO, "Exception during onStart()", paramThrowable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.lmax.disruptor.IgnoreExceptionHandler
 * JD-Core Version:    0.6.2
 */