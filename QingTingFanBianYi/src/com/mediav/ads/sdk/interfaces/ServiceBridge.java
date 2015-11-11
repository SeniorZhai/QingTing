package com.mediav.ads.sdk.interfaces;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract interface ServiceBridge
{
  public abstract void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);

  public abstract IBinder onBind(Intent paramIntent);

  public abstract void onConfigurationChanged(Configuration paramConfiguration);

  public abstract void onCreate();

  public abstract void onDestroy();

  public abstract void onLowMemory();

  public abstract void onRebind(Intent paramIntent);

  public abstract int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2);

  public abstract void onTaskRemoved(Intent paramIntent);

  public abstract void onTrimMemory(int paramInt);

  public abstract boolean onUnbind(Intent paramIntent);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.ServiceBridge
 * JD-Core Version:    0.6.2
 */