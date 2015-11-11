package com.umeng.message.proguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import org.android.agoo.net.mtop.MtopHttpChunked;

class ab extends BroadcastReceiver
{
  private volatile int b = 0;

  ab(aa paramaa)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getAction();
    if (TextUtils.equals(paramContext, "android.intent.action.SCREEN_ON"))
    {
      Q.c("MessagePush", "screen_on");
      paramIntent = aa.a(this.a).readyState();
      if ((paramIntent != ay.b) && (paramIntent != ay.a))
        aa.a(this.a, aa.b(this.a), "screen_on_connect");
    }
    do
    {
      do
        return;
      while (!TextUtils.equals(paramContext, "android.net.conn.CONNECTIVITY_CHANGE"));
      this.b += 1;
    }
    while (this.b <= 1);
    Q.c("MessagePush", "network_change");
    aa.a(this.a, aa.c(this.a), "network_change_connect");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ab
 * JD-Core Version:    0.6.2
 */