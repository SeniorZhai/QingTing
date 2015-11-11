package fm.qingting.qtradio.view.floaticon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FloatToggleReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent == null);
    do
    {
      return;
      paramContext = paramIntent.getAction();
    }
    while ((paramContext == null) || (!paramContext.equalsIgnoreCase("fm.qingting.qtradio.action_float_toggle")));
    boolean bool = paramIntent.getBooleanExtra("fm.qingting.qtradio.float_toggle", false);
    FloatViewManager.INSTANCE.setEnable(bool);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.floaticon.FloatToggleReceiver
 * JD-Core Version:    0.6.2
 */