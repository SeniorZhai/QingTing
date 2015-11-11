package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import fm.qingting.qtradio.NotificationService;

public class ConnectivityReceiver extends BroadcastReceiver
{
  private NotificationService notificationService;

  public ConnectivityReceiver(NotificationService paramNotificationService)
  {
    this.notificationService = paramNotificationService;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext != null)
    {
      Log.d("NetworkState", "Network Type  = " + paramContext.getTypeName());
      Log.d("NetworkState", "Network State = " + paramContext.getState());
      if (paramContext.isConnected());
      return;
    }
    Log.e("NetworkState", "Network unavailable");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.ConnectivityReceiver
 * JD-Core Version:    0.6.2
 */