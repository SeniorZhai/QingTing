package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import fm.qingting.qtradio.NotificationService;

public class UpgradeReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getData().getSchemeSpecificPart().equalsIgnoreCase(paramContext.getPackageName()))
      paramContext.startService(new Intent(paramContext, NotificationService.class));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.UpgradeReceiver
 * JD-Core Version:    0.6.2
 */