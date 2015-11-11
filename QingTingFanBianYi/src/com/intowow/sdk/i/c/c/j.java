package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.a.b.b;
import com.intowow.sdk.f.f;
import com.intowow.sdk.f.f.a;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.j;
import com.intowow.sdk.triggerresponse.RedirectHandler;
import com.intowow.sdk.triggerresponse.TriggerResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class j extends s
{
  protected Runnable x = new Runnable()
  {
    public void run()
    {
      Iterator localIterator = j.this.u.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          if (j.this.j != null)
            j.this.j.postDelayed(j.this.x, 100L);
          return;
        }
        ((b)localIterator.next()).a(0);
      }
    }
  };

  public j(Activity paramActivity, com.intowow.sdk.model.j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
    this.u = new ArrayList();
  }

  private void i()
  {
    if ((this.E) && (this.C) && (this.j != null) && (this.u.size() > 0))
    {
      this.j.removeCallbacks(this.x);
      this.j.post(this.x);
    }
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    if (this.r != null)
      this.t = this.r.a(ADProfile.j.c);
    a(ADProfile.j.c);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.g.a(f.a.l), this.g.a(f.a.l));
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (j.this.a != null)
          j.this.a.onBackPressed();
      }
    });
    localImageButton.setBackgroundDrawable(this.i.b("btn_single_close_nm.png"));
    localImageButton.setOnTouchListener(n.a(this.i.b("btn_single_close_at.png"), this.i.b("btn_single_close_nm.png")));
    paramRelativeLayout.addView(localImageButton);
  }

  protected boolean a(WebView paramWebView, String paramString)
  {
    boolean bool = false;
    this.j.removeCallbacks(this.F);
    if (this.E)
    {
      TriggerResponse localTriggerResponse = this.c.a("*", com.intowow.sdk.h.j.i);
      if ((localTriggerResponse == null) || (!(localTriggerResponse instanceof RedirectHandler)))
        break label75;
      localTriggerResponse.a(paramString);
      TriggerResponse.a(localTriggerResponse, this.a, paramString);
    }
    while (true)
    {
      this.e.onClick(paramWebView);
      bool = true;
      return bool;
      label75: TriggerResponse.a(false, this.a, paramString);
    }
  }

  public boolean c()
  {
    if (!super.c())
      return false;
    i();
    return true;
  }

  public boolean d()
  {
    if (!super.d())
      return false;
    Iterator localIterator;
    if (this.j != null)
    {
      this.j.removeCallbacks(this.x);
      localIterator = this.u.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
        return true;
      ((b)localIterator.next()).a();
    }
  }

  protected void h()
  {
    i();
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, com.intowow.sdk.model.j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new j(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.j
 * JD-Core Version:    0.6.2
 */