package fm.qingting.utils;

import android.content.Context;
import fm.qingting.qtradio.model.GlobalCfg;

public class LifeTime
{
  private static final String LastQuitTime = "last_quit_time";
  private static Context _context = null;
  public static boolean isFirstLaunchAfterInstall = false;
  public static long lastLaunchTime = -1L;
  public static long launchTime;

  public static long getLastQuitTimeMs(Context paramContext)
  {
    paramContext = GlobalCfg.getInstance(paramContext).getValueFromDB("last_quit_time");
    if ((paramContext != null) && (!paramContext.equalsIgnoreCase("")))
      return Long.valueOf(paramContext).longValue();
    return 0L;
  }

  public static void init(Context paramContext)
  {
    if (_context == null)
    {
      _context = paramContext;
      lastLaunchTime = GlobalCfg.getInstance(_context).getAppFirstStartTime();
      if (lastLaunchTime > 0L)
        break label94;
      isFirstLaunchAfterInstall = true;
      GlobalCfg.getInstance(_context).setAppFirstStartTime(System.currentTimeMillis() / 1000L);
      GlobalCfg.getInstance(_context).saveValueToDB();
    }
    while (true)
    {
      lastLaunchTime = GlobalCfg.getInstance(_context).getActivityStartTime();
      launchTime = System.currentTimeMillis() / 1000L;
      GlobalCfg.getInstance(_context).setActivityStartTime(launchTime);
      return;
      label94: isFirstLaunchAfterInstall = false;
    }
  }

  public static void setLastQuitTimeMs(long paramLong, Context paramContext)
  {
    GlobalCfg.getInstance(paramContext).setValueToDB("last_quit_time", "long", String.valueOf(paramLong));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.LifeTime
 * JD-Core Version:    0.6.2
 */