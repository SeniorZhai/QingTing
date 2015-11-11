package master.flame.danmaku.danmaku.util;

import android.app.ActivityManager;
import android.content.Context;

public class AndroidUtils
{
  public static int getMemoryClass(Context paramContext)
  {
    return ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.util.AndroidUtils
 * JD-Core Version:    0.6.2
 */