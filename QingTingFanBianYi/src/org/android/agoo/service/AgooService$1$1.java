package org.android.agoo.service;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.proguard.P;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.U;
import com.umeng.message.proguard.W;

class AgooService$1$1
  implements Runnable
{
  AgooService$1$1(AgooService.1 param1)
  {
  }

  public void run()
  {
    Q.c("AgooService", "messageServiceBinder probe--->[thread_name:" + Thread.currentThread().getName() + "]");
    String str = P.d(AgooService.b(this.a.a));
    if ((!TextUtils.isEmpty(str)) && (TextUtils.equals(AgooService.c(this.a.a), str)) && (AgooService.a(this.a.a) != null) && (AgooService.a(this.a.a).f()))
    {
      Q.c("AgooService", "messageService connect[ok]");
      return;
    }
    if ((TextUtils.isEmpty(AgooService.d(this.a.a))) && (!TextUtils.equals(AgooService.d(this.a.a), AgooService.e(this.a.a).getPackageName())))
      U.a(AgooService.f(this.a.a), AgooService.d(this.a.a));
    Q.c("AgooService", "messageServiceBinder[need_election]");
    AgooService.a(this.a.a, AgooService.g(this.a.a).getPackageName(), "ERROR_NEED_ELECTION");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.AgooService.1.1
 * JD-Core Version:    0.6.2
 */