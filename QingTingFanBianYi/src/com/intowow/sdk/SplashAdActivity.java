package com.intowow.sdk;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.intowow.sdk.b.e;
import com.intowow.sdk.i.a.a;
import com.intowow.sdk.i.a.b;
import com.intowow.sdk.j.j;

public class SplashAdActivity extends FragmentActivity
{
  public static final String INTENT_PREVIEW_FETCH_ADLIST = "INTENT_PREVIEW_FETCH_ADLIST";
  private boolean a = false;
  private IAdActivity b = null;

  private void a()
  {
    if (this.b != null);
    try
    {
      this.b.closeActivity();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void a(Bundle paramBundle1, Bundle paramBundle2)
  {
    if (SplashAD.ActivityType.values()[paramBundle2.getInt("ACTIVITY_TYPE")] == SplashAD.ActivityType.SINGLE_OFFER)
      this.b = new b(this);
    this.b.onCreate(paramBundle1);
  }

  public IAdActivity getImpl()
  {
    return this.b;
  }

  public void onBackPressed()
  {
    try
    {
      if (this.b != null)
        this.b.onBackPressed();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      Bundle localBundle = getIntent().getExtras();
      if (j.a(localBundle))
      {
        this.b = new a(this);
        this.b.onCreate(localBundle);
        return;
      }
      a(paramBundle, localBundle);
      return;
    }
    catch (Exception paramBundle)
    {
      a();
    }
  }

  public void onDestroy()
  {
    super.onDestroy();
    try
    {
      if (this.b != null)
      {
        this.b.onDestroy();
        this.b = null;
      }
      e.a(this).a(null);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    try
    {
      boolean bool1 = j.a(paramIntent.getExtras());
      boolean bool2 = this.b instanceof a;
      if (bool1)
      {
        if (!bool2)
        {
          this.b = new a(this);
          this.b.onCreate(paramIntent.getExtras());
        }
      }
      else if (e.a(getApplicationContext()).q())
      {
        a(null, paramIntent.getExtras());
        return;
      }
    }
    catch (Exception paramIntent)
    {
      a();
    }
  }

  protected void onPause()
  {
    super.onPause();
    try
    {
      if (this.b != null)
        this.b.onPause();
      this.a = false;
      return;
    }
    catch (Exception localException)
    {
      a();
    }
  }

  public void onResume()
  {
    super.onResume();
    try
    {
      if (!this.a)
      {
        if (this.b != null)
          this.b.onStart();
        this.a = true;
      }
      if (this.b != null)
        this.b.onResume();
      e.a(this).a(this.b);
      return;
    }
    catch (Exception localException)
    {
      a();
    }
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    try
    {
      if (this.b != null)
        this.b.onSaveInstanceState(paramBundle);
      label17: super.onSaveInstanceState(paramBundle);
      return;
    }
    catch (Exception localException)
    {
      break label17;
    }
  }

  protected void onStart()
  {
    super.onStart();
    try
    {
      if (this.b != null)
        this.b.onStart();
      this.a = true;
      return;
    }
    catch (Exception localException)
    {
      a();
    }
  }

  protected void onStop()
  {
    super.onStop();
    try
    {
      if (this.b != null)
        this.b.onStop();
      return;
    }
    catch (Exception localException)
    {
      a();
    }
  }

  public static abstract interface IAdActivity
  {
    public abstract void closeActivity();

    public abstract void onBackPressed();

    public abstract void onCreate(Bundle paramBundle);

    public abstract void onDestroy();

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onSaveInstanceState(Bundle paramBundle);

    public abstract void onStart();

    public abstract void onStop();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.SplashAdActivity
 * JD-Core Version:    0.6.2
 */