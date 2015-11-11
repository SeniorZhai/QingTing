package rui.lin.spectra;

import android.util.Log;

public class Logger
{
  private int mLogLevel = 4;
  private String mLogTag = "Logger";

  public Logger(int paramInt, String paramString)
  {
    this.mLogLevel = paramInt;
    this.mLogTag = paramString;
  }

  public void debug(String paramString, Object[] paramArrayOfObject)
  {
    if (this.mLogLevel <= 3)
      Log.d(this.mLogTag, String.format(paramString, paramArrayOfObject));
  }

  public void error(String paramString, Object[] paramArrayOfObject)
  {
    if (this.mLogLevel <= 6)
      Log.e(this.mLogTag, String.format(paramString, paramArrayOfObject));
  }

  public void info(String paramString, Object[] paramArrayOfObject)
  {
  }

  public void warning(String paramString, Object[] paramArrayOfObject)
  {
    if (this.mLogLevel <= 5)
      Log.w(this.mLogTag, String.format(paramString, paramArrayOfObject));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     rui.lin.spectra.Logger
 * JD-Core Version:    0.6.2
 */