package com.mediav.ads.sdk.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

public abstract interface ActivityBridge
{
  public abstract boolean dispatchKeyEvent(KeyEvent paramKeyEvent);

  public abstract void onConfigurationChanged(Configuration paramConfiguration);

  public abstract void onCreate(Bundle paramBundle);

  public abstract void onDestroy();

  public abstract void onInit(Activity paramActivity);

  public abstract void onLowMemory();

  public abstract void onNewIntent(Intent paramIntent);

  public abstract void onPause();

  public abstract void onRestart();

  public abstract void onRestoreInstanceState(Bundle paramBundle);

  public abstract void onResume();

  public abstract void onSaveInstanceState(Bundle paramBundle);

  public abstract void onStart();

  public abstract void onStop();

  public abstract void onTrimMemory(int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.interfaces.ActivityBridge
 * JD-Core Version:    0.6.2
 */