package fm.qingting.qtradio.headset;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

public class MediaButtonReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = 1;
    if (!"android.intent.action.MEDIA_BUTTON".equals(paramIntent.getAction()));
    while (true)
    {
      return;
      paramIntent = (KeyEvent)paramIntent.getParcelableExtra("android.intent.extra.KEY_EVENT");
      if (paramIntent != null)
      {
        if (paramIntent.getAction() == 1);
        while (i != 0)
        {
          i = paramIntent.getKeyCode();
          long l1 = paramIntent.getEventTime();
          long l2 = paramIntent.getDownTime();
          paramIntent = Message.obtain();
          paramIntent.what = 0;
          Bundle localBundle = new Bundle();
          localBundle.putInt("key_code", i);
          localBundle.putLong("event_time", l1 - l2);
          paramIntent.setData(localBundle);
          HeadSet.getInstance(paramContext).getHandler().sendMessage(paramIntent);
          return;
          i = 0;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.headset.MediaButtonReceiver
 * JD-Core Version:    0.6.2
 */