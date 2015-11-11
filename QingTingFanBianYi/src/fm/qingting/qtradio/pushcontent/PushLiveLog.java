package fm.qingting.qtradio.pushcontent;

import android.content.Context;
import android.os.Bundle;
import fm.qingting.qtradio.push.bean.ChannelUpdateInfoBean;

public class PushLiveLog
{
  public static final String ClickedLive = "ClickedLive";
  public static final String PushedLive = "PushedLive";
  public static final String RecvLive = "RecvLive";
  public static final String clickNotification = "ClickLiveNotify";
  public static final String inApp = "inApp";
  public static final String launchApp = "launchApp";
  public static final String playLiveDirectly = "playLiveDirectly";
  public static final String putNotification = "PutLiveNotify";

  public static void sendClickLiveLog(Bundle paramBundle, int paramInt, Context paramContext)
  {
  }

  public static void sendClickNotification(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (!paramString1.equalsIgnoreCase("")) && (paramString2 == null));
  }

  public static void sendPushLiveLog(boolean paramBoolean, Context paramContext)
  {
  }

  public static void sendPutNotification(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramString.equalsIgnoreCase("")));
  }

  public static void sendRecvLiveLog(ChannelUpdateInfoBean paramChannelUpdateInfoBean, Context paramContext)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushcontent.PushLiveLog
 * JD-Core Version:    0.6.2
 */