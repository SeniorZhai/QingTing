package fm.qingting.qtradio.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.e("onReceive", paramIntent.getDataString());
    if (paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED"))
    {
      paramContext = paramIntent.getDataString();
      if ((paramContext != null) && ((paramContext.contains("cn.goapk.market")) || (paramContext.contains("com.baidu.appsearch"))))
        OnlineUpdateHelper.getInstance().quickDownload();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.BootReceiver
 * JD-Core Version:    0.6.2
 */