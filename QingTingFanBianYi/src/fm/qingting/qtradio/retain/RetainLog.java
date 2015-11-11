package fm.qingting.qtradio.retain;

import android.content.Context;
import fm.qingting.qtradio.abtest.ABTest;
import fm.qingting.qtradio.log.LogModule;

public class RetainLog
{
  public static final String AppAlive = "AppAlive";

  public static void sendAppLiveLog(Context paramContext)
  {
    paramContext = ABTest.buildCommonLogWithABTest(paramContext, false);
    LogModule.getInstance().send("AppAlive", paramContext);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.retain.RetainLog
 * JD-Core Version:    0.6.2
 */