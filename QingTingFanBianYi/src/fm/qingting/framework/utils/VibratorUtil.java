package fm.qingting.framework.utils;

import android.content.Context;
import android.os.Vibrator;

public class VibratorUtil
{
  public static void Vibrate(Context paramContext, long paramLong)
  {
    try
    {
      ((Vibrator)paramContext.getSystemService("vibrator")).vibrate(paramLong);
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public static void Vibrate(Context paramContext, long[] paramArrayOfLong, boolean paramBoolean)
  {
    try
    {
      paramContext = (Vibrator)paramContext.getSystemService("vibrator");
      if (paramBoolean);
      for (int i = 1; ; i = -1)
      {
        paramContext.vibrate(paramArrayOfLong, i);
        return;
      }
    }
    catch (Exception paramContext)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.VibratorUtil
 * JD-Core Version:    0.6.2
 */