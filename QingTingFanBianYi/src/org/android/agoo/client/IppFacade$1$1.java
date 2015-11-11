package org.android.agoo.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

class IppFacade$1$1
  implements ServiceConnection
{
  IppFacade$1$1(IppFacade.1 param1)
  {
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    try
    {
      this.a.a.unbindService(this);
      return;
    }
    catch (Throwable paramComponentName)
    {
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.IppFacade.1.1
 * JD-Core Version:    0.6.2
 */