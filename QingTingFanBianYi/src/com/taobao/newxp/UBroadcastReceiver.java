package com.taobao.newxp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class UBroadcastReceiver extends BroadcastReceiver
{
  public void a()
  {
  }

  public void b()
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    switch (paramIntent.getExtras().getInt("boradcast_action_key", -1))
    {
    default:
      return;
    case 17:
      a();
      return;
    case 18:
    }
    b();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.UBroadcastReceiver
 * JD-Core Version:    0.6.2
 */