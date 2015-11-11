package com.alipay.sdk.exception;

import android.text.TextUtils;
import android.util.Log;

public final class AppErrorException extends Exception
{
  private static final long serialVersionUID = 4918321648798599467L;

  public AppErrorException(Class paramClass)
  {
    this(paramClass, null, null);
  }

  public AppErrorException(Class paramClass, String paramString)
  {
    this(paramClass, paramString, null);
  }

  public AppErrorException(Class paramClass, String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
    printException(paramClass, paramString, paramThrowable);
  }

  public AppErrorException(Class paramClass, Throwable paramThrowable)
  {
    this(paramClass, null, paramThrowable);
  }

  public static void printException(Class paramClass, String paramString, Throwable paramThrowable)
  {
    if (paramClass != null)
      Log.e("AppError", "AppError--" + paramClass.getCanonicalName());
    if (!TextUtils.isEmpty(paramString))
      Log.e("AppError", "AppError--" + paramString);
    if (paramThrowable != null);
    try
    {
      Log.e("AppError", "AppError--" + paramThrowable.getMessage());
      paramThrowable.printStackTrace();
      return;
    }
    catch (Exception paramClass)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.exception.AppErrorException
 * JD-Core Version:    0.6.2
 */