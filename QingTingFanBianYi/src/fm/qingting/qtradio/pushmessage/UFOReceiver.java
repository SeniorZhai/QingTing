package fm.qingting.qtradio.pushmessage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import fm.qingting.qtradio.NotificationService;

public class UFOReceiver extends BroadcastReceiver
{
  private void log(String paramString)
  {
    if (paramString != null);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent == null) || (paramContext == null));
    while ((paramIntent.getAction() == null) || (!paramIntent.getAction().equalsIgnoreCase("fm.qingting.qtradio.GEXIN_MESSAGE")))
      return;
    Intent localIntent = new Intent(paramContext, NotificationService.class);
    localIntent.setAction("fm.qingting.qtradio.GEXIN_MESSAGE_BAK");
    paramIntent = paramIntent.getExtras();
    if (paramIntent != null)
    {
      String str = paramIntent.getString("msg");
      localIntent.putExtra("msg", str);
      log(str);
      str = paramIntent.getString("alias");
      localIntent.putExtra("alias", str);
      log(str);
      str = paramIntent.getString("topic");
      log(str);
      localIntent.putExtra("topic", str);
      str = paramIntent.getString("reg");
      log(str);
      localIntent.putExtra("reg", str);
      paramIntent = paramIntent.getString("type");
      log(paramIntent);
      localIntent.putExtra("type", String.valueOf(paramIntent));
    }
    paramContext.startService(localIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.UFOReceiver
 * JD-Core Version:    0.6.2
 */