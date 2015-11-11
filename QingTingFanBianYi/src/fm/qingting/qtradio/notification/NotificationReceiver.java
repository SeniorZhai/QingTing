package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public final class NotificationReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.SHOW_NOTIFICATION"))
    {
      String str1 = paramIntent.getStringExtra("NOTIFICATION_ID");
      String str2 = paramIntent.getStringExtra("NOTIFICATION_API_KEY");
      String str3 = paramIntent.getStringExtra("NOTIFICATION_TITLE");
      String str4 = paramIntent.getStringExtra("NOTIFICATION_MESSAGE");
      String str5 = paramIntent.getStringExtra("NOTIFICATION_URI");
      String str6 = paramIntent.getStringExtra("channelname");
      int i = paramIntent.getIntExtra("channelid", 0);
      String str7 = paramIntent.getStringExtra("duetime");
      String str8 = paramIntent.getStringExtra("notify_type");
      int j = paramIntent.getIntExtra("programid", 0);
      int k = paramIntent.getIntExtra("categoryid", 0);
      int m = paramIntent.getIntExtra("parentid", 0);
      int n = paramIntent.getIntExtra("contentType", 0);
      int i1 = paramIntent.getIntExtra("alarmType", 0);
      new Notifier(paramContext).notify(str1, str2, str3, str4, str5, str7, str6, i, str8, k, j, m, n, i1, null, null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.NotificationReceiver
 * JD-Core Version:    0.6.2
 */