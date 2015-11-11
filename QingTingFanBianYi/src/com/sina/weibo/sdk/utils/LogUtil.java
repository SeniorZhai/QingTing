package com.sina.weibo.sdk.utils;

import android.util.Log;

public class LogUtil
{
  public static boolean sIsLogEnable = false;

  public static void d(String paramString1, String paramString2)
  {
    if (sIsLogEnable)
    {
      StackTraceElement localStackTraceElement = java.lang.Thread.currentThread().getStackTrace()[3];
      Log.d(paramString1, new StringBuilder(String.valueOf(localStackTraceElement.getFileName())).append("(").append(localStackTraceElement.getLineNumber()).append(") ").append(localStackTraceElement.getMethodName()).toString() + ": " + paramString2);
    }
  }

  public static void disableLog()
  {
    sIsLogEnable = false;
  }

  public static void e(String paramString1, String paramString2)
  {
    if (sIsLogEnable)
    {
      StackTraceElement localStackTraceElement = java.lang.Thread.currentThread().getStackTrace()[3];
      Log.e(paramString1, new StringBuilder(String.valueOf(localStackTraceElement.getFileName())).append("(").append(localStackTraceElement.getLineNumber()).append(") ").append(localStackTraceElement.getMethodName()).toString() + ": " + paramString2);
    }
  }

  public static void enableLog()
  {
    sIsLogEnable = true;
  }

  public static String getStackTraceMsg()
  {
    StackTraceElement localStackTraceElement = java.lang.Thread.currentThread().getStackTrace()[3];
    return localStackTraceElement.getFileName() + "(" + localStackTraceElement.getLineNumber() + ") " + localStackTraceElement.getMethodName();
  }

  public static void i(String paramString1, String paramString2)
  {
    if (sIsLogEnable)
    {
      StackTraceElement localStackTraceElement = java.lang.Thread.currentThread().getStackTrace()[3];
      Log.i(paramString1, new StringBuilder(String.valueOf(localStackTraceElement.getFileName())).append("(").append(localStackTraceElement.getLineNumber()).append(") ").append(localStackTraceElement.getMethodName()).toString() + ": " + paramString2);
    }
  }

  public static void v(String paramString1, String paramString2)
  {
    if (sIsLogEnable)
    {
      StackTraceElement localStackTraceElement = java.lang.Thread.currentThread().getStackTrace()[3];
      Log.v(paramString1, new StringBuilder(String.valueOf(localStackTraceElement.getFileName())).append("(").append(localStackTraceElement.getLineNumber()).append(") ").append(localStackTraceElement.getMethodName()).toString() + ": " + paramString2);
    }
  }

  public static void w(String paramString1, String paramString2)
  {
    if (sIsLogEnable)
    {
      StackTraceElement localStackTraceElement = java.lang.Thread.currentThread().getStackTrace()[3];
      Log.w(paramString1, new StringBuilder(String.valueOf(localStackTraceElement.getFileName())).append("(").append(localStackTraceElement.getLineNumber()).append(") ").append(localStackTraceElement.getMethodName()).toString() + ": " + paramString2);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.LogUtil
 * JD-Core Version:    0.6.2
 */