package com.ixintui.push;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.ixintui.plugin.IPushService;
import com.ixintui.pushsdk.a.a;

public class PushService extends Service
{
  private IPushService a;

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.a = ((IPushService)a.a(this, "com.ixintui.push.PushServiceImpl"));
    if (this.a != null)
    {
      this.a.init(this);
      this.a.onCreate();
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (this.a != null)
      this.a.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (this.a != null)
      return this.a.onStartCommand(paramIntent, paramInt1, paramInt2);
    stopSelf();
    return 2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.push.PushService
 * JD-Core Version:    0.6.2
 */