package com.neusoft.ssp.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.neusoft.ssp.aidl.MyAIDL;
import com.neusoft.ssp.aidl.MyAIDL.Stub;

final class a
  implements ServiceConnection
{
  a(SSP_API paramSSP_API)
  {
  }

  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Log.v("ccy", "ccy ssplib come onServiceConnected");
    SSP_API.a(this.a, MyAIDL.Stub.asInterface(paramIBinder));
    try
    {
      Log.v("ccy", "ccy ssplib context==" + SSP_API.a(this.a));
      if (SSP_API.a(this.a) != null)
      {
        paramComponentName = SSP_API.a(this.a).getPackageName() + "," + SSP_API.b(this.a);
        SSP_API.c(this.a).register(paramComponentName, SSP_API.d(this.a));
      }
      return;
    }
    catch (RemoteException paramComponentName)
    {
      Log.v("ccy", "ccy ssplib RemoteException==" + paramComponentName);
    }
  }

  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    SSP_API.a(this.a, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.api.a
 * JD-Core Version:    0.6.2
 */