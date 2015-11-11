package org.apache.commons.httpclient.util;

import java.io.InterruptedIOException;
import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExceptionUtil
{
  private static final Method INIT_CAUSE_METHOD;
  private static final Log LOG;
  private static final Class SOCKET_TIMEOUT_CLASS;
  static Class class$java$lang$Throwable;
  static Class class$org$apache$commons$httpclient$util$ExceptionUtil;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$util$ExceptionUtil == null)
    {
      localClass = class$("org.apache.commons.httpclient.util.ExceptionUtil");
      class$org$apache$commons$httpclient$util$ExceptionUtil = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      INIT_CAUSE_METHOD = getInitCauseMethod();
      SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
      return;
      localClass = class$org$apache$commons$httpclient$util$ExceptionUtil;
    }
  }

  private static Class SocketTimeoutExceptionClass()
  {
    try
    {
      Class localClass = Class.forName("java.net.SocketTimeoutException");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    return null;
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

  private static Method getInitCauseMethod()
  {
    try
    {
      Class localClass1;
      Class localClass2;
      if (class$java$lang$Throwable == null)
      {
        localClass1 = class$("java.lang.Throwable");
        class$java$lang$Throwable = localClass1;
        if (class$java$lang$Throwable != null)
          break label54;
        localClass2 = class$("java.lang.Throwable");
        class$java$lang$Throwable = localClass2;
      }
      while (true)
      {
        return localClass2.getMethod("initCause", new Class[] { localClass1 });
        localClass1 = class$java$lang$Throwable;
        break;
        label54: localClass2 = class$java$lang$Throwable;
      }
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
    }
    return null;
  }

  public static void initCause(Throwable paramThrowable1, Throwable paramThrowable2)
  {
    if (INIT_CAUSE_METHOD != null);
    try
    {
      INIT_CAUSE_METHOD.invoke(paramThrowable1, new Object[] { paramThrowable2 });
      return;
    }
    catch (Exception paramThrowable1)
    {
      LOG.warn("Exception invoking Throwable.initCause", paramThrowable1);
    }
  }

  public static boolean isSocketTimeoutException(InterruptedIOException paramInterruptedIOException)
  {
    if (SOCKET_TIMEOUT_CLASS != null)
      return SOCKET_TIMEOUT_CLASS.isInstance(paramInterruptedIOException);
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.ExceptionUtil
 * JD-Core Version:    0.6.2
 */