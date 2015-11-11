package com.intowow.sdk.i.a;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.SplashAdActivity.IAdActivity;
import com.intowow.sdk.b.e;
import com.intowow.sdk.j.j;
import com.intowow.sdk.model.k;

public class a
  implements SplashAdActivity.IAdActivity
{
  private Handler a = null;
  private ProgressDialog b = null;
  private FragmentActivity c = null;
  private boolean d = false;
  private Runnable e = new Runnable()
  {
    public void run()
    {
      if (a.a(a.this) == null)
      {
        a.a(a.this, new ProgressDialog(a.b(a.this)));
        a.a(a.this).setMessage("Processing");
      }
      if (!a.a(a.this).isShowing())
        a.a(a.this).show();
    }
  };
  private Runnable f = new Runnable()
  {
    public void run()
    {
      if (!a.c(a.this))
        return;
      k localk = e.a(a.b(a.this)).o();
      switch (a()[localk.ordinal()])
      {
      case 1:
      case 4:
      case 5:
      case 2:
      default:
        a.f(a.this).postDelayed(a.g(a.this), 1000L);
        return;
      case 3:
        a.d(a.this);
        j.d(a.b(a.this));
        return;
      case 6:
        a.e(a.this);
        j.e(a.b(a.this));
        return;
      case 7:
      }
      a.e(a.this);
      j.f(a.b(a.this));
    }
  };

  public a(FragmentActivity paramFragmentActivity)
  {
    this.c = paramFragmentActivity;
  }

  private void a()
  {
    this.a.removeCallbacks(this.e);
    this.a.post(this.e);
  }

  private void b()
  {
    this.a.removeCallbacks(this.f);
    this.a.post(this.f);
  }

  private void c()
  {
    if ((this.b != null) && (this.b.isShowing()))
      this.b.dismiss();
  }

  private void d()
  {
    this.d = false;
    c();
    e.a(this.c).p();
  }

  public void closeActivity()
  {
  }

  public void onBackPressed()
  {
    if (this.d)
      d();
    this.c.finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    this.c.requestWindowFeature(1);
    this.c.getWindow().setFlags(1024, 1024);
    this.c.setRequestedOrientation(1);
    this.a = new Handler();
    this.d = true;
    a();
    b();
    paramBundle = new RelativeLayout.LayoutParams(-1, -1);
    RelativeLayout localRelativeLayout = new RelativeLayout(this.c);
    localRelativeLayout.setBackgroundColor(-16777216);
    localRelativeLayout.setLayoutParams(paramBundle);
    this.c.setContentView(localRelativeLayout);
  }

  public void onDestroy()
  {
    this.c = null;
    if (this.d)
      Process.killProcess(Process.myPid());
  }

  public void onPause()
  {
  }

  public void onResume()
  {
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
  }

  public void onStart()
  {
  }

  public void onStop()
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.a.a
 * JD-Core Version:    0.6.2
 */