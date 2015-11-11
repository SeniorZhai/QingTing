package com.tencent.stat.common;

import android.util.Log;
import com.tencent.stat.StatConfig;

public final class StatLogger
{
  private boolean debugEnable = true;
  private int logLevel = 2;
  private String tag = "default";

  public StatLogger()
  {
  }

  public StatLogger(String paramString)
  {
    this.tag = paramString;
  }

  private String getLoggerClassInfo()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if (arrayOfStackTraceElement == null)
      return null;
    int j = arrayOfStackTraceElement.length;
    int i = 0;
    label19: StackTraceElement localStackTraceElement;
    if (i < j)
    {
      localStackTraceElement = arrayOfStackTraceElement[i];
      if (!localStackTraceElement.isNativeMethod())
        break label43;
    }
    label43: 
    while ((localStackTraceElement.getClassName().equals(Thread.class.getName())) || (localStackTraceElement.getClassName().equals(getClass().getName())))
    {
      i += 1;
      break label19;
      break;
    }
    return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + localStackTraceElement.getFileName() + ":" + localStackTraceElement.getLineNumber() + "]";
  }

  public void d(Object paramObject)
  {
    if (isDebugEnable())
      debug(paramObject);
  }

  public void debug(Object paramObject)
  {
    String str;
    if (this.logLevel <= 3)
    {
      str = getLoggerClassInfo();
      if (str != null)
        break label32;
    }
    label32: for (paramObject = paramObject.toString(); ; paramObject = str + " - " + paramObject)
    {
      Log.d(this.tag, paramObject);
      return;
    }
  }

  public void e(Exception paramException)
  {
    if (StatConfig.isDebugEnable())
      error(paramException);
  }

  public void e(Object paramObject)
  {
    if (isDebugEnable())
      error(paramObject);
  }

  public void error(Exception paramException)
  {
    if (this.logLevel <= 6)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      String str = getLoggerClassInfo();
      StackTraceElement[] arrayOfStackTraceElement = paramException.getStackTrace();
      if (str != null)
        localStringBuffer.append(str + " - " + paramException + "\r\n");
      while ((arrayOfStackTraceElement != null) && (arrayOfStackTraceElement.length > 0))
      {
        int j = arrayOfStackTraceElement.length;
        int i = 0;
        while (i < j)
        {
          paramException = arrayOfStackTraceElement[i];
          if (paramException != null)
            localStringBuffer.append("[ " + paramException.getFileName() + ":" + paramException.getLineNumber() + " ]\r\n");
          i += 1;
        }
        localStringBuffer.append(paramException + "\r\n");
      }
      Log.e(this.tag, localStringBuffer.toString());
    }
  }

  public void error(Object paramObject)
  {
    String str;
    if (this.logLevel <= 6)
    {
      str = getLoggerClassInfo();
      if (str != null)
        break label33;
    }
    label33: for (paramObject = paramObject.toString(); ; paramObject = str + " - " + paramObject)
    {
      Log.e(this.tag, paramObject);
      return;
    }
  }

  public int getLogLevel()
  {
    return this.logLevel;
  }

  public void i(Object paramObject)
  {
    if (isDebugEnable())
      info(paramObject);
  }

  public void info(Object paramObject)
  {
    String str;
    if (this.logLevel <= 4)
    {
      str = getLoggerClassInfo();
      if (str != null)
        break label32;
    }
    label32: for (paramObject = paramObject.toString(); ; paramObject = str + " - " + paramObject)
    {
      Log.i(this.tag, paramObject);
      return;
    }
  }

  public boolean isDebugEnable()
  {
    return this.debugEnable;
  }

  public void setDebugEnable(boolean paramBoolean)
  {
    this.debugEnable = paramBoolean;
  }

  public void setLogLevel(int paramInt)
  {
    this.logLevel = paramInt;
  }

  public void setTag(String paramString)
  {
    this.tag = paramString;
  }

  public void v(Object paramObject)
  {
    if (isDebugEnable())
      verbose(paramObject);
  }

  public void verbose(Object paramObject)
  {
    String str;
    if (this.logLevel <= 2)
    {
      str = getLoggerClassInfo();
      if (str != null)
        break label32;
    }
    label32: for (paramObject = paramObject.toString(); ; paramObject = str + " - " + paramObject)
    {
      Log.v(this.tag, paramObject);
      return;
    }
  }

  public void w(Object paramObject)
  {
    if (isDebugEnable())
      warn(paramObject);
  }

  public void warn(Object paramObject)
  {
    String str;
    if (this.logLevel <= 5)
    {
      str = getLoggerClassInfo();
      if (str != null)
        break label32;
    }
    label32: for (paramObject = paramObject.toString(); ; paramObject = str + " - " + paramObject)
    {
      Log.w(this.tag, paramObject);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.common.StatLogger
 * JD-Core Version:    0.6.2
 */