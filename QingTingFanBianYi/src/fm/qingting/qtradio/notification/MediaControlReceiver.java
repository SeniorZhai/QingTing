package fm.qingting.qtradio.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MediaControlReceiver extends BroadcastReceiver
{
  private void togglePlay(Context paramContext, String paramString)
  {
    if (paramContext == null)
      return;
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.UPDATE_PLAY_INFO_QT");
    localIntent.putExtra(paramString, "");
    paramContext.sendOrderedBroadcast(localIntent, null);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getStringExtra("type");
      if ((paramIntent != null) && (!paramIntent.equalsIgnoreCase("")))
        togglePlay(paramContext, paramIntent);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.MediaControlReceiver
 * JD-Core Version:    0.6.2
 */