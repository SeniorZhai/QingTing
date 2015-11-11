package fm.qingting.qtradio.push.log;

import android.content.Context;
import fm.qingting.qtradio.abtest.ABTest;
import fm.qingting.qtradio.abtest.ABTestItem;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.utils.DateUtil;

public class UpdatePushLog
{
  public static final int LaunchApp = 1;
  public static final int PlayDirectly = 2;
  public static final String ServiceAlive = "ServiceLive";
  public static final String ServiceAliveLogSentTime = "ServiceAliveLogSentTime";

  private static String getCommonLogWithPushType(Context paramContext)
  {
    return ABTest.getCommonLogWithPushType(paramContext, new ABTestItem[0]);
  }

  public static void sendLiveLog(int paramInt, Context paramContext)
  {
    String str1 = getCommonLogWithPushType(paramContext);
    String str2 = GlobalCfg.getInstance(paramContext).getValueFromDB("ServiceAliveLogSentTime");
    long l = DateUtil.getCurrentMillis();
    if (str2 != null)
    {
      if (DateUtil.isDifferentDayMs(Long.parseLong(str2), l))
      {
        LogModule.getInstance().send("ServiceLive", str1 + "\"" + paramInt + "\"");
        GlobalCfg.getInstance(paramContext).setValueToDB("ServiceAliveLogSentTime", "Long", String.valueOf(l));
        GlobalCfg.getInstance(paramContext).saveValueToDB();
      }
      return;
    }
    LogModule.getInstance().send("ServiceLive", str1 + "\"" + paramInt + "\"");
    GlobalCfg.getInstance(paramContext).setValueToDB("ServiceAliveLogSentTime", "Long", String.valueOf(l));
    GlobalCfg.getInstance(paramContext).saveValueToDB();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.log.UpdatePushLog
 * JD-Core Version:    0.6.2
 */