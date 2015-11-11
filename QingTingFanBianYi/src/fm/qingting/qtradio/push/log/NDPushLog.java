package fm.qingting.qtradio.push.log;

import android.content.Context;
import android.os.Bundle;
import fm.qingting.qtradio.abtest.ABTest;
import fm.qingting.qtradio.abtest.ABTestItem;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.push.bean.PushBean;
import fm.qingting.qtradio.push.bean.PushType;

public class NDPushLog
{
  public static final String NDPushable = "NDPushable";
  public static final String NDPusheClicked = "NDPusheClicked";
  public static final String NDPushedOneItem = "NDPushedOneItem";
  public static final String ProgramNotMatchError = "ProgramNotMatchError";

  private static String getCommonLogWithPushType(Context paramContext)
  {
    return ABTest.getCommonLogWithPushType(paramContext, new ABTestItem[0]);
  }

  public static void sendNDClickLog(Bundle paramBundle, int paramInt, PushType paramPushType, Context paramContext)
  {
    paramContext = getCommonLogWithPushType(paramContext);
    paramContext = paramContext + "\"" + paramBundle.getString("categoryid") + "\"";
    paramContext = paramContext + ",\"" + paramBundle.getString("parentid") + "\"";
    paramContext = paramContext + ",\"" + paramBundle.getString("channelid") + "\"";
    paramContext = paramContext + ",\"" + paramBundle.getString("channelname") + "\"";
    paramContext = paramContext + ",\"" + paramBundle.getString("programid") + "\"";
    paramBundle = paramContext + ",\"" + paramBundle.getString("program_name") + "\"";
    paramBundle = paramBundle + ",\"" + paramInt + "\"";
    paramBundle = paramBundle + ",\"" + PushType.getPushType(paramPushType) + "\"";
    LogModule.getInstance().send("NDPusheClicked", paramBundle);
  }

  public static void sendNDPushedLog(PushBean paramPushBean, Context paramContext)
  {
    paramContext = getCommonLogWithPushType(paramContext);
    paramContext = paramContext + "\"" + paramPushBean.catId + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.parentId + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.channelId + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.name + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.pid + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.pname + "\"";
    paramPushBean = paramContext + ",\"" + PushType.getPushType(paramPushBean.push_type) + "\"";
    LogModule.getInstance().send("NDPushedOneItem", paramPushBean);
  }

  public static void sendProgramNotMatchError(PushBean paramPushBean, Context paramContext)
  {
    paramContext = getCommonLogWithPushType(paramContext);
    paramContext = paramContext + "\"" + paramPushBean.catId + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.parentId + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.channelId + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.name + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.pid + "\"";
    paramContext = paramContext + ",\"" + paramPushBean.pname + "\"";
    paramPushBean = paramContext + ",\"" + PushType.getPushType(paramPushBean.push_type) + "\"";
    LogModule.getInstance().send("ProgramNotMatchError", paramPushBean);
  }

  public static void sendPushableLog(int paramInt, boolean paramBoolean, PushType paramPushType, Context paramContext)
  {
    int i = 1;
    paramContext = getCommonLogWithPushType(paramContext);
    paramContext = paramContext + "\"" + paramInt + "\"";
    paramContext = new StringBuilder().append(paramContext).append(",\"");
    if (paramBoolean == true);
    for (paramInt = i; ; paramInt = 0)
    {
      paramContext = paramInt + "\"";
      paramPushType = paramContext + ",\"" + PushType.getPushType(paramPushType) + "\"";
      LogModule.getInstance().send("NDPushable", paramPushType);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.log.NDPushLog
 * JD-Core Version:    0.6.2
 */