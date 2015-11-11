package com.ixintui.push;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.ixintui.plugin.IPushActivity;
import com.ixintui.pushsdk.a.a;

public class PushActivity extends Activity
{
  private IPushActivity a;

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (this.a != null)
      this.a.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onBackPressed()
  {
    super.onBackPressed();
    if (this.a != null)
      this.a.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (this.a == null)
      this.a = ((IPushActivity)a.a(this, "com.ixintui.push.PushActivityImpl"));
    if (this.a != null)
      this.a.onCreate(this, paramBundle);
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    if (this.a != null)
      this.a.onNewIntent(paramIntent);
  }

  protected void onPause()
  {
    super.onPause();
    if (this.a != null)
      this.a.onPause();
  }

  protected void onPostCreate(Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    if (this.a != null)
      this.a.onPostCreate(paramBundle);
  }

  protected void onPostResume()
  {
    super.onPostResume();
    if (this.a != null)
      this.a.onPostResume();
  }

  protected void onRestart()
  {
    super.onRestart();
    if (this.a != null)
      this.a.onRestart();
  }

  protected void onResume()
  {
    super.onResume();
    if (this.a != null)
      this.a.onResume();
  }

  protected void onStart()
  {
    super.onStart();
    if (this.a != null)
      this.a.onStart();
  }

  protected void onStop()
  {
    super.onStop();
    if (this.a != null)
      this.a.onStop();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.push.PushActivity
 * JD-Core Version:    0.6.2
 */