package org.android.agoo.service;

import android.content.Context;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.aM;

class AgooService$ReElection$1
  implements Runnable
{
  AgooService$ReElection$1(AgooService.ReElection paramReElection, Context paramContext)
  {
  }

  public void run()
  {
    Q.c("AgooService", "election---onReceive--->[current-thread-name:" + Thread.currentThread().getName() + "][" + aM.a(System.currentTimeMillis()) + "] ");
    AgooService.a(this.b.a, this.a.getPackageName(), "ERROR_NEED_ELECTION");
    AgooService.ReElection.a(this.b, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.AgooService.ReElection.1
 * JD-Core Version:    0.6.2
 */