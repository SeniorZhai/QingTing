package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import fm.qingting.qtradio.NotificationService;

public class QTAlarmReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent == null) || (paramContext == null));
    do
    {
      do
        return;
      while (paramIntent.getAction() == null);
      if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.alarmintent"))
      {
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.alarmintent");
        paramContext.startService(paramIntent);
        return;
      }
      if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.reserveintent"))
      {
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.reserveintent");
        paramContext.startService(paramIntent);
        return;
      }
      if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.notifyintent"))
      {
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.notifyintent");
        paramContext.startService(paramIntent);
        return;
      }
      if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.protectintent"))
      {
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.protectintent");
        paramContext.startService(paramIntent);
        return;
      }
      if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.killintent"))
      {
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.killintent");
        paramContext.startService(paramIntent);
        return;
      }
      if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.start"))
      {
        Log.e("qtalarm", "recv start");
        paramIntent = new Intent(paramContext, NotificationService.class);
        paramIntent.setAction("fm.qingting.start");
        paramContext.startService(paramIntent);
        return;
      }
    }
    while (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.quit"));
    paramIntent = new Intent(paramContext, NotificationService.class);
    paramIntent.setAction("fm.qingting.quit");
    paramContext.startService(paramIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.QTAlarmReceiver
 * JD-Core Version:    0.6.2
 */