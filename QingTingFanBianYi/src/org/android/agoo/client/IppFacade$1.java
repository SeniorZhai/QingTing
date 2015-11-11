package org.android.agoo.client;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import com.umeng.message.proguard.Q;

final class IppFacade$1
  implements Runnable
{
  private ServiceConnection b = new IppFacade.1.1(this);

  IppFacade$1(Context paramContext)
  {
  }

  private boolean a(String paramString)
  {
    return this.a.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }

  public void run()
  {
    try
    {
      if (a("com.eg.android.AlipayGphone"))
      {
        Intent localIntent = new Intent();
        localIntent.setAction("org.agoo.android.intent.action.PING2");
        if (this.a.bindService(localIntent, this.b, 1))
        {
          Q.c(IppFacade.a(), "ippfacade binded--->[org.agoo.android.intent.action.PING2] success");
          return;
        }
        Q.d(IppFacade.a(), "ippfacade binded--->[org.agoo.android.intent.action.PING2] failed");
        return;
      }
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.IppFacade.1
 * JD-Core Version:    0.6.2
 */