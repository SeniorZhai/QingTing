package com.mediav.ads.sdk.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import com.mediav.ads.sdk.adcore.UpdateBridge;
import com.mediav.ads.sdk.interfaces.IBridge;
import com.mediav.ads.sdk.interfaces.ServiceBridge;
import com.mediav.ads.sdk.log.MVLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public final class MvService extends Service
{
  private ServiceBridge serviceBridge;

  protected void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    this.serviceBridge.dump(paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.serviceBridge.onBind(paramIntent);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    this.serviceBridge.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate()
  {
    IBridge localIBridge = UpdateBridge.getBridge(this);
    if (localIBridge == null)
    {
      MVLog.e(1, "unable get updateBridge.");
      return;
    }
    this.serviceBridge = localIBridge.getServiceBridge(this);
    this.serviceBridge.onCreate();
  }

  public void onDestroy()
  {
    this.serviceBridge.onDestroy();
    this.serviceBridge = null;
  }

  public void onLowMemory()
  {
    this.serviceBridge.onLowMemory();
  }

  public void onRebind(Intent paramIntent)
  {
    this.serviceBridge.onRebind(paramIntent);
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return this.serviceBridge.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  public void onTaskRemoved(Intent paramIntent)
  {
    this.serviceBridge.onTaskRemoved(paramIntent);
  }

  public void onTrimMemory(int paramInt)
  {
    this.serviceBridge.onTrimMemory(paramInt);
  }

  public boolean onUnbind(Intent paramIntent)
  {
    return this.serviceBridge.onUnbind(paramIntent);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.service.MvService
 * JD-Core Version:    0.6.2
 */