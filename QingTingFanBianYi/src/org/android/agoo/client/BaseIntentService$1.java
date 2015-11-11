package org.android.agoo.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.umeng.message.proguard.Q;
import org.android.agoo.service.IElectionService;
import org.android.agoo.service.IElectionService.Stub;

class BaseIntentService$1
  implements ServiceConnection
{
  BaseIntentService$1(BaseIntentService paramBaseIntentService)
  {
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Q.c("BaseIntentService", "electionConnection pack[" + paramComponentName.getPackageName() + "]");
    try
    {
      paramComponentName = this.a.getApplicationContext();
      BaseIntentService.a(this.a, IElectionService.Stub.asInterface(paramIBinder));
      if (BaseIntentService.a(this.a) != null)
      {
        BaseIntentService.a(this.a).election(paramComponentName.getPackageName(), AgooSettings.getAgooReleaseTime(), "token");
        BaseIntentService.a(this.a, paramComponentName);
      }
      return;
    }
    catch (Throwable paramComponentName)
    {
      Q.d("BaseIntentService", "onServiceConnected", paramComponentName);
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Q.c("BaseIntentService", "electionDisconnected pack[" + paramComponentName.getPackageName() + "]");
    BaseIntentService.a(this.a, null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseIntentService.1
 * JD-Core Version:    0.6.2
 */