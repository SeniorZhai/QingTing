package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.mediav.ads.sdk.interfaces.ActivityBridge;

public class MvActivity extends Activity
{
  public static ActivityBridge activityBridge;

  public MvActivity()
  {
    if (activityBridge != null)
      activityBridge.onInit(this);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (activityBridge != null)
      return activityBridge.dispatchKeyEvent(paramKeyEvent);
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if (activityBridge != null)
      activityBridge.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (activityBridge != null)
      activityBridge.onCreate(paramBundle);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (activityBridge != null)
    {
      activityBridge.onDestroy();
      activityBridge = null;
    }
  }

  public void onLowMemory()
  {
    super.onLowMemory();
    if (activityBridge != null)
      activityBridge.onLowMemory();
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if (activityBridge != null)
      activityBridge.onNewIntent(paramIntent);
  }

  protected void onPause()
  {
    super.onPause();
    if (activityBridge != null)
      activityBridge.onPause();
  }

  protected void onRestart()
  {
    super.onRestart();
    if (activityBridge != null)
      activityBridge.onRestart();
  }

  protected void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    if (activityBridge != null)
      activityBridge.onRestoreInstanceState(paramBundle);
  }

  protected void onResume()
  {
    super.onResume();
    if (activityBridge != null)
      activityBridge.onResume();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (activityBridge != null)
      activityBridge.onSaveInstanceState(paramBundle);
  }

  protected void onStart()
  {
    super.onStart();
    if (activityBridge != null)
      activityBridge.onStart();
  }

  protected void onStop()
  {
    super.onStop();
    if (activityBridge != null)
      activityBridge.onStop();
  }

  public void onTrimMemory(int paramInt)
  {
    super.onTrimMemory(paramInt);
    if (activityBridge != null)
      activityBridge.onTrimMemory(paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.adcore.MvActivity
 * JD-Core Version:    0.6.2
 */