package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.f.e;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.g;
import com.intowow.sdk.model.ADProfile.h;
import com.intowow.sdk.model.ADProfile.h.a;
import com.intowow.sdk.model.j;

public class o extends l
{
  private ImageButton V = null;
  private ADProfile.h.a W = null;

  public o(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  private ImageButton G()
  {
    if (this.c.q().a(ADProfile.g.g))
    {
      this.W = this.c.q().a();
      float f1 = a() / 1280.0F;
      float f2 = b() / 720.0F;
      localLayoutParams = new RelativeLayout.LayoutParams((int)(this.W.c() * f1), (int)(this.W.d() * f2));
      localLayoutParams.addRule(10);
      localLayoutParams.addRule(9);
      localLayoutParams.leftMargin = ((int)(f1 * this.W.a()));
      localLayoutParams.topMargin = ((int)(this.W.b() * f2));
      localImageButton = new ImageButton(this.a);
      localImageButton.setLayoutParams(localLayoutParams);
      localImageButton.setBackgroundDrawable(null);
      return localImageButton;
    }
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.h.a(e.a.m), this.h.a(e.a.n));
    localLayoutParams.addRule(12);
    localLayoutParams.addRule(11);
    ImageButton localImageButton = new ImageButton(this.a);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setLayoutParams(localLayoutParams);
    a(ADProfile.d.p, localImageButton);
    return localImageButton;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    this.V = G();
    if (this.V != null)
      this.V.setOnClickListener(this.e);
    this.T = D();
    n.a(paramRelativeLayout, new View[] { this.z, this.V, this.E, this.D });
    a(paramRelativeLayout);
    B();
    n.a(paramRelativeLayout, new View[] { this.F, this.s, this.C, this.T });
  }

  protected RelativeLayout.LayoutParams h()
  {
    return new RelativeLayout.LayoutParams(a(), b());
  }

  protected boolean k()
  {
    return true;
  }

  protected void n()
  {
    super.n();
    u();
    if (this.V != null)
      this.V.setVisibility(0);
  }

  protected void o()
  {
    super.o();
    if (this.V != null)
      this.V.setVisibility(8);
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new o(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.o
 * JD-Core Version:    0.6.2
 */