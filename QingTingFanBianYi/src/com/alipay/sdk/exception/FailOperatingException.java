package com.alipay.sdk.exception;

import android.text.TextUtils;
import android.util.Log;

public final class FailOperatingException extends Exception
{
  private static final long serialVersionUID = 5908800852030168641L;

  public FailOperatingException()
  {
    this(null, null);
  }

  public FailOperatingException(String paramString)
  {
    this(paramString, null);
  }

  public FailOperatingException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    printException(paramString, paramThrowable);
  }

  public FailOperatingException(Throwable paramThrowable)
  {
    this(null, paramThrowable);
  }

  public static void printException(String paramString, Throwable paramThrowable)
  {
    if (!TextUtils.isEmpty(paramString))
      Log.i("FailOperating", "FailOperating--" + paramString);
    if (paramThrowable != null);
    try
    {
      Log.i("FailOperating", "FailOperating--" + paramThrowable.getMessage());
      paramThrowable.printStackTrace();
      return;
    }
    catch (Exception paramString)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.exception.FailOperatingException
 * JD-Core Version:    0.6.2
 */