package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import fm.qingting.qtradio.NotificationService;

public class BootCompletedReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null);
    while (!paramIntent.getAction().equalsIgnoreCase("android.intent.action.BOOT_COMPLETED"))
      return;
    paramContext.startService(new Intent(paramContext, NotificationService.class));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.BootCompletedReceiver
 * JD-Core Version:    0.6.2
 */