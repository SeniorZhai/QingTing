package org.android.agoo.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.message.proguard.aT;

public abstract class BaseBroadcastReceiver extends BroadcastReceiver
{
  protected abstract String a(Context paramContext);

  protected void a(Context paramContext, Intent paramIntent)
  {
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    try
    {
      aT.a(new BaseBroadcastReceiver.1(this, paramContext, paramIntent));
      BaseIntentService.a(paramContext, paramIntent, a(paramContext));
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseBroadcastReceiver
 * JD-Core Version:    0.6.2
 */