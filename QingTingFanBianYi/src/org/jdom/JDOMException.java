package org.jdom;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import org.xml.sax.SAXException;

public class JDOMException extends Exception
{
  private static final String CVS_ID = "@(#) $RCSfile: JDOMException.java,v $ $Revision: 1.26 $ $Date: 2008/12/10 00:59:51 $ $Name: jdom_1_1_1 $";
  private Throwable cause;

  public JDOMException()
  {
    super("Error occurred in JDOM application.");
  }

  public JDOMException(String paramString)
  {
    super(paramString);
  }

  public JDOMException(String paramString, Throwable paramThrowable)
  {
    super(paramString);
    this.cause = paramThrowable;
  }

  private static Throwable getNestedException(Throwable paramThrowable)
  {
    Object localObject;
    if ((paramThrowable instanceof JDOMException))
      localObject = ((JDOMException)paramThrowable).getCause();
    do
    {
      Throwable localThrowable;
      do
      {
        do
        {
          return localObject;
          if ((paramThrowable instanceof SAXException))
            return ((SAXException)paramThrowable).getException();
          if ((paramThrowable instanceof SQLException))
            return ((SQLException)paramThrowable).getNextException();
          if ((paramThrowable instanceof InvocationTargetException))
            return ((InvocationTargetException)paramThrowable).getTargetException();
          if ((paramThrowable instanceof ExceptionInInitializerError))
            return ((ExceptionInInitializerError)paramThrowable).getException();
          localThrowable = getNestedExceptionFromField(paramThrowable, "java.rmi.RemoteException", "detail");
          localObject = localThrowable;
        }
        while (localThrowable != null);
        localThrowable = getNestedException(paramThrowable, "javax.naming.NamingException", "getRootCause");
        localObject = localThrowable;
      }
      while (localThrowable != null);
      paramThrowable = getNestedException(paramThrowable, "javax.servlet.ServletException", "getRootCause");
      localObject = paramThrowable;
    }
    while (paramThrowable != null);
    return null;
  }

  private static Throwable getNestedException(Throwable paramThrowable, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Class.forName(paramString1);
      if (paramString1.isAssignableFrom(paramThrowable.getClass()))
      {
        paramThrowable = (Throwable)paramString1.getMethod(paramString2, new Class[0]).invoke(paramThrowable, new Object[0]);
        return paramThrowable;
      }
    }
    catch (Exception paramThrowable)
    {
    }
    return null;
  }

  private static Throwable getNestedExceptionFromField(Throwable paramThrowable, String paramString1, String paramString2)
  {
    try
    {
      paramString1 = Class.forName(paramString1);
      if (paramString1.isAssignableFrom(paramThrowable.getClass()))
      {
        paramThrowable = (Throwable)paramString1.getField(paramString2).get(paramThrowable);
        return paramThrowable;
      }
    }
    catch (Exception paramThrowable)
    {
    }
    return null;
  }

  public Throwable getCause()
  {
    return this.cause;
  }

  public String getMessage()
  {
    Object localObject2 = super.getMessage();
    Object localObject3;
    for (Object localObject1 = this; ; localObject1 = localObject3)
    {
      Throwable localThrowable = getNestedException((Throwable)localObject1);
      localObject1 = localObject2;
      if (localThrowable != null)
      {
        localObject1 = localThrowable.getMessage();
        localObject3 = localObject1;
        if ((localThrowable instanceof SAXException))
        {
          Exception localException = ((SAXException)localThrowable).getException();
          localObject3 = localObject1;
          if (localException != null)
          {
            localObject3 = localObject1;
            if (localObject1 != null)
            {
              localObject3 = localObject1;
              if (((String)localObject1).equals(localException.getMessage()))
                localObject3 = null;
            }
          }
        }
        localObject1 = localObject2;
        if (localObject3 != null)
          if (localObject2 == null)
            break label119;
      }
      label119: for (localObject1 = (String)localObject2 + ": " + (String)localObject3; (localThrowable instanceof JDOMException); localObject1 = localObject3)
        return localObject1;
      localObject3 = localThrowable;
      localObject2 = localObject1;
    }
  }

  public Throwable initCause(Throwable paramThrowable)
  {
    this.cause = paramThrowable;
    return this;
  }

  public void printStackTrace()
  {
    super.printStackTrace();
    Object localObject = this;
    while (true)
    {
      localObject = getNestedException((Throwable)localObject);
      if (localObject != null)
      {
        System.err.print("Caused by: ");
        ((Throwable)localObject).printStackTrace();
        if (!(localObject instanceof JDOMException));
      }
      else
      {
        return;
      }
    }
  }

  public void printStackTrace(PrintStream paramPrintStream)
  {
    super.printStackTrace(paramPrintStream);
    Object localObject = this;
    while (true)
    {
      localObject = getNestedException((Throwable)localObject);
      if (localObject != null)
      {
        paramPrintStream.print("Caused by: ");
        ((Throwable)localObject).printStackTrace(paramPrintStream);
        if (!(localObject instanceof JDOMException));
      }
      else
      {
        return;
      }
    }
  }

  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    super.printStackTrace(paramPrintWriter);
    Object localObject = this;
    while (true)
    {
      localObject = getNestedException((Throwable)localObject);
      if (localObject != null)
      {
        paramPrintWriter.print("Caused by: ");
        ((Throwable)localObject).printStackTrace(paramPrintWriter);
        if (!(localObject instanceof JDOMException));
      }
      else
      {
        return;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.jdom.JDOMException
 * JD-Core Version:    0.6.2
 */