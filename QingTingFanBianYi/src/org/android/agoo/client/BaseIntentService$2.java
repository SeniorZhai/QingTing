package org.android.agoo.client;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.aR;
import com.umeng.message.proguard.aS;

class BaseIntentService$2
  implements Runnable
{
  BaseIntentService$2(BaseIntentService paramBaseIntentService, String paramString1, String paramString2, Context paramContext)
  {
  }

  public void run()
  {
    if ((!TextUtils.isEmpty(this.a)) && (TextUtils.equals(this.a, this.b)))
    {
      Q.c("BaseIntentService", "restart---->[currentSudoPack:" + this.a + "]:[start]");
      if (BaseIntentService.b(this.d))
      {
        Q.c("BaseIntentService", "enabledService---->[" + this.b + "/" + this.d.getAgooService() + "]");
        aR.b(this.c, this.d.getAgooService());
      }
      aS.a(this.c, this.d.getAgooService());
      return;
    }
    Q.c("BaseIntentService", "restart---->[currentSudoPack:" + this.a + "][currentPack:" + this.b + "]:[stop]");
    if (BaseIntentService.b(this.d))
    {
      Q.c("BaseIntentService", "disableService---->[" + this.b + "/" + this.d.getAgooService() + "]");
      aR.a(this.c, this.d.getAgooService());
    }
    aS.b(this.c, this.d.getAgooService());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseIntentService.2
 * JD-Core Version:    0.6.2
 */