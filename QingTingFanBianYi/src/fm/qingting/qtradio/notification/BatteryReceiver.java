package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import fm.qingting.qtradio.NotificationService;

public class BatteryReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null);
    do
    {
      return;
      if (paramIntent.getAction().equalsIgnoreCase("android.intent.action.BATTERY_CHANGED"))
      {
        Log.e("batteryReceiver", "recv android.intent.action.BATTERY_CHANGED");
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.connectivity.change");
        paramContext.startService(paramIntent);
        return;
      }
    }
    while (!paramIntent.getAction().equalsIgnoreCase("android.intent.action.USER_PRESENT"));
    Log.e("batteryReceiver", "recv android.intent.action.USER_PRESENT");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.BatteryReceiver
 * JD-Core Version:    0.6.2
 */