package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.f.e;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.j;

public class n extends l
{
  public n(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  protected ImageButton G()
  {
    ImageButton localImageButton = new ImageButton(this.a);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.h.a(e.a.S), this.h.a(e.a.T));
    localLayoutParams.addRule(12);
    localLayoutParams.bottomMargin = this.h.a(e.a.U);
    localLayoutParams.addRule(11);
    localLayoutParams.rightMargin = this.h.a(e.a.U);
    localImageButton.setLayoutParams(localLayoutParams);
    localImageButton.setScaleType(ImageView.ScaleType.FIT_XY);
    localImageButton.setBackgroundDrawable(this.i.b("btn_landscape_video_install_android_nm.png"));
    localImageButton.setOnTouchListener(com.intowow.sdk.j.n.a(this.i.b("btn_landscape_video_install_android_at.png"), this.i.b("btn_landscape_video_install_android_nm.png")));
    localImageButton.setOnClickListener(this.e);
    return localImageButton;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    this.U = G();
    this.T = D();
    com.intowow.sdk.j.n.a(paramRelativeLayout, new View[] { this.z, this.U, this.E, this.D });
    a(paramRelativeLayout);
    B();
    com.intowow.sdk.j.n.a(paramRelativeLayout, new View[] { this.F, this.s, this.C, this.T });
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
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new n(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.n
 * JD-Core Version:    0.6.2
 */