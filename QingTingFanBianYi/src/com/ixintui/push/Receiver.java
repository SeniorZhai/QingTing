package com.ixintui.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ixintui.plugin.IPushReceiver;
import com.ixintui.pushsdk.a.a;

public class Receiver extends BroadcastReceiver
{
  public static final int RECEIVER_VERSION = 2;
  private IPushReceiver a;

  private String a()
  {
    try
    {
      String str = (String)a.a(this.a, "getResult", null, null);
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (this.a == null)
      this.a = ((IPushReceiver)a.a(paramContext, "com.ixintui.push.PushReceiverImpl"));
    if (this.a != null)
    {
      this.a.onReceive(paramContext, paramIntent);
      if (a() != null)
        setResultData(a());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.push.Receiver
 * JD-Core Version:    0.6.2
 */