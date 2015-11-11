package fm.qingting.qtradio.manager;

import android.os.Build.VERSION;

public class QtApiLevelManager
{
  public static boolean isApiLevelSupported(int paramInt)
  {
    return Build.VERSION.SDK_INT >= paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.QtApiLevelManager
 * JD-Core Version:    0.6.2
 */