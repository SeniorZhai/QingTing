package fm.qingting.qtradio.notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import fm.qingting.qtradio.QTRadioService;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.push.bean.PushType;
import fm.qingting.qtradio.push.log.NDPushLog;
import fm.qingting.qtradio.pushmessage.PushMessageLog;

public class InstantPlayReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      if ((!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PAUSE")) && (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY")) && (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_NEXT")) && (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_PRE")) && (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_NEXT_CATEGORY")) && (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.CAR_PLAY_PRE_CATEGORY")))
        break label97;
      new Intent().setAction(paramIntent.getAction());
      paramContext.sendBroadcast(paramIntent);
    }
    label97: Bundle localBundle;
    do
    {
      return;
      ((NotificationManager)paramContext.getSystemService("notification")).cancelAll();
      localBundle = paramIntent.getExtras();
    }
    while (localBundle == null);
    String str = localBundle.getString("notify_type");
    LogModule.getInstance().init(paramContext);
    if ((str != null) && (str.equalsIgnoreCase("push_live_channel")));
    while (true)
    {
      PushMessageLog.sendPushLog(paramContext, localBundle, "ClickGeTuiPushMsg");
      paramIntent = new Intent(paramContext, QTRadioService.class);
      paramIntent.setAction("fm.qingting.qtradio.START_SERVICE");
      paramIntent.putExtras(localBundle);
      paramContext.startService(paramIntent);
      return;
      if (str.equalsIgnoreCase(PushType.ContentUpdate.name()))
        NDPushLog.sendNDClickLog(paramIntent.getExtras(), 2, PushType.ContentUpdate, paramContext);
      else if (str.equalsIgnoreCase(PushType.Download.name()))
        NDPushLog.sendNDClickLog(paramIntent.getExtras(), 2, PushType.Download, paramContext);
      else if (str.equalsIgnoreCase(PushType.Novel.name()))
        NDPushLog.sendNDClickLog(paramIntent.getExtras(), 2, PushType.Novel, paramContext);
      else if (str.equalsIgnoreCase(PushType.ResumeProgram.name()))
        NDPushLog.sendNDClickLog(paramIntent.getExtras(), 2, PushType.ResumeProgram, paramContext);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.InstantPlayReceiver
 * JD-Core Version:    0.6.2
 */