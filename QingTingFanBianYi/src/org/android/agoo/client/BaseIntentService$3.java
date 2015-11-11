package org.android.agoo.client;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.umeng.message.proguard.Q;
import org.android.agoo.service.IMessageService.Stub;

class BaseIntentService$3
  implements ServiceConnection
{
  BaseIntentService$3(BaseIntentService paramBaseIntentService)
  {
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    Q.c("BaseIntentService", "messageConnected pack[" + paramComponentName.getPackageName() + "]");
    BaseIntentService.a(this.a, true);
    BaseIntentService.a(this.a, IMessageService.Stub.asInterface(paramIBinder));
    paramComponentName = this.a.getApplicationContext();
    BaseIntentService.b(this.a, paramComponentName);
    BaseIntentService.c(this.a, paramComponentName);
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    Q.c("BaseIntentService", "messageDisconnected pack[" + paramComponentName.getPackageName() + "]");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseIntentService.3
 * JD-Core Version:    0.6.2
 */