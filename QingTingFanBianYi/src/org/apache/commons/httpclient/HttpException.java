package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class HttpException extends IOException
{
  static Class class$java$lang$Throwable;
  private final Throwable cause;
  private String reason;
  private int reasonCode = 200;

  public HttpException()
  {
    this.cause = null;
  }

  public HttpException(String paramString)
  {
    super(paramString);
    this.cause = null;
  }

  public HttpException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    this.cause = paramThrowable;
    try
    {
      Class localClass;
      if (class$java$lang$Throwable == null)
      {
        paramString = class$("java.lang.Throwable");
        class$java$lang$Throwable = paramString;
        if (class$java$lang$Throwable != null)
          break label84;
        localClass = class$("java.lang.Throwable");
        class$java$lang$Throwable = localClass;
      }
      while (true)
      {
        localClass.getMethod("initCause", new Class[] { paramString }).invoke(this, new Object[] { paramThrowable });
        return;
        paramString = class$java$lang$Throwable;
        break;
        label84: localClass = class$java$lang$Throwable;
      }
    }
    catch (Exception paramString)
    {
    }
  }

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  public Throwable getCause()
  {
    return this.cause;
  }

  public String getReason()
  {
    return this.reason;
  }

  public int getReasonCode()
  {
    return this.reasonCode;
  }

  public void printStackTrace()
  {
    printStackTrace(System.err);
  }

  public void printStackTrace(PrintStream paramPrintStream)
  {
    try
    {
      getClass().getMethod("getStackTrace", new Class[0]);
      super.printStackTrace(paramPrintStream);
      return;
    }
    catch (Exception localException)
    {
      do
        super.printStackTrace(paramPrintStream);
      while (this.cause == null);
      paramPrintStream.print("Caused by: ");
      this.cause.printStackTrace(paramPrintStream);
    }
  }

  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    try
    {
      getClass().getMethod("getStackTrace", new Class[0]);
      super.printStackTrace(paramPrintWriter);
      return;
    }
    catch (Exception localException)
    {
      do
        super.printStackTrace(paramPrintWriter);
      while (this.cause == null);
      paramPrintWriter.print("Caused by: ");
      this.cause.printStackTrace(paramPrintWriter);
    }
  }

  public void setReason(String paramString)
  {
    this.reason = paramString;
  }

  public void setReasonCode(int paramInt)
  {
    this.reasonCode = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpException
 * JD-Core Version:    0.6.2
 */