package org.android.agoo.service;

import android.os.RemoteException;
import com.umeng.message.proguard.W;
import com.umeng.message.proguard.aT;

class AgooService$1 extends IMessageService.Stub
{
  AgooService$1(AgooService paramAgooService)
  {
  }

  public boolean ping()
    throws RemoteException
  {
    if (AgooService.a(this.a) == null)
      return false;
    return AgooService.a(this.a).f();
  }

  public void probe()
    throws RemoteException
  {
    aT.a(new AgooService.1.1(this));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.AgooService.1
 * JD-Core Version:    0.6.2
 */