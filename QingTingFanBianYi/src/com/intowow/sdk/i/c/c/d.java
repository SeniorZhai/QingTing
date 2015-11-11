package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.j;
import com.intowow.sdk.model.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class d extends a
{
  protected com.intowow.sdk.i.c.a x = null;
  protected ImageButton y = null;
  protected Runnable z = new Runnable()
  {
    public void run()
    {
      Iterator localIterator = d.this.u.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          if (d.this.j != null)
            d.this.j.postDelayed(d.this.z, 100L);
          return;
        }
        ((b)localIterator.next()).a(0);
      }
    }
  };

  public d(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
    this.u = new ArrayList();
  }

  protected ImageButton a(int paramInt, String paramString1, String paramString2)
  {
    if (this.b == j.d)
      return null;
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(paramInt, paramInt);
    localLayoutParams.addRule(10);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (d.this.a != null)
          d.this.a.onBackPressed();
      }
    });
    localImageButton.setBackgroundDrawable(this.i.b(paramString1));
    localImageButton.setOnTouchListener(n.a(this.i.b(paramString2), this.i.b(paramString1)));
    return localImageButton;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    a(ADProfile.j.b);
    int i = a();
    int j = b();
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(i, j);
    localLayoutParams.addRule(13);
    this.x = a(i, j, localLayoutParams);
    this.x.setOnClickListener(this.e);
    a(ADProfile.d.m, this.x);
    paramRelativeLayout.addView(this.x);
  }

  public boolean c()
  {
    if (!super.c())
      return false;
    if ((this.j != null) && (this.u.size() > 0))
    {
      this.j.removeCallbacks(this.z);
      this.j.post(this.z);
    }
    return true;
  }

  public boolean d()
  {
    if (!super.d())
      return false;
    Iterator localIterator;
    if (this.j != null)
    {
      this.j.removeCallbacks(this.z);
      localIterator = this.u.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
        return true;
      ((b)localIterator.next()).a();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.d
 * JD-Core Version:    0.6.2
 */