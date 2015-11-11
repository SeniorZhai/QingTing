package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import fm.qingting.qtradio.NotificationService;

public class ScreenOnReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null);
    do
    {
      return;
      if (paramIntent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE"))
      {
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.connectivity.change");
        paramContext.startService(paramIntent);
        return;
      }
    }
    while (!paramIntent.getAction().equalsIgnoreCase("android.intent.action.KILLSERVICE"));
    paramIntent = new Intent(paramContext, NotificationService.class);
    paramIntent.setAction("android.intent.action.KILLSERVICE");
    paramContext.startService(paramIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.ScreenOnReceiver
 * JD-Core Version:    0.6.2
 */