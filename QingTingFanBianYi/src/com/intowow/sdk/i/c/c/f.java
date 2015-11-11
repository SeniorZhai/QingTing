package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.j;

public class f extends e
{
  public f(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
    this.H = this.h.a(e.a.D);
    this.E = 1500;
  }

  protected int a()
  {
    return this.h.a(e.a.g);
  }

  protected RelativeLayout a(ADProfile.d paramd1, ADProfile.d paramd2, ADProfile.d paramd3)
  {
    if ((!this.c.b(paramd1)) || (!this.c.b(paramd2)) || (!this.c.b(paramd3)))
      return null;
    RelativeLayout localRelativeLayout = b(this.h.a(e.a.D), this.h.a(e.a.E));
    paramd1 = a(paramd1, this.h.a(e.a.I), this.h.a(e.a.J), this.h.a(e.a.K));
    paramd2 = a(paramd2, -2, -2, paramd1.getId(), this.h.a(e.a.G), this.h.a(e.a.L), Color.parseColor("#e2e2e2"));
    n.a(localRelativeLayout, new View[] { paramd1, paramd2, a(paramd3, this.h.a(e.a.M), -2, this.h.a(e.a.G), paramd2.getId(), paramd1.getId(), this.h.a(e.a.N), -1) });
    return localRelativeLayout;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    paramRelativeLayout.removeAllViews();
    int i = a();
    com.intowow.sdk.i.c.a locala1 = a(i, b(), 13, true, false, 0);
    com.intowow.sdk.i.c.a locala2 = a(i, this.h.a(e.a.F), 12, false, false, 0);
    locala2.setBackgroundDrawable(this.i.b("image_landscape_gamecard_mask.9.png"));
    ImageButton localImageButton = a(this.h.a(e.a.Q), this.h.a(e.a.R), this.h.a(e.a.G), this.h.a(e.a.G), 11, "btn_landscape_image_gamecard_install_android_nm.png", "btn_landscape_image_gamecard_install_android_at.png");
    i();
    a(this.h.a(e.a.D), this.h.a(e.a.E), 9, true, this.h.a(e.a.H));
    this.I = a(this.h.a(e.a.C), "btn_single_close_nm.png", "btn_single_close_at.png");
    n.a(paramRelativeLayout, new View[] { locala1, locala2, localImageButton, this.K, this.I });
  }

  protected int b()
  {
    return this.h.a(e.a.h);
  }

  protected RelativeLayout h()
  {
    if ((!this.c.b(ADProfile.d.v)) || (!this.c.b(ADProfile.d.e)) || (!this.c.b(ADProfile.d.f)))
      return null;
    RelativeLayout localRelativeLayout = b(this.h.a(e.a.D), this.h.a(e.a.E));
    ImageView localImageView = a(ADProfile.d.v, this.h.a(e.a.I), this.h.a(e.a.J), this.h.a(e.a.K));
    TextView localTextView = a(ADProfile.d.e, this.h.a(e.a.M), -2, localImageView.getId(), this.h.a(e.a.G), this.h.a(e.a.O), -1);
    n.a(localRelativeLayout, new View[] { localImageView, localTextView, a(ADProfile.d.f, this.h.a(e.a.M), -2, this.h.a(e.a.G), localTextView.getId(), localImageView.getId(), this.h.a(e.a.P), Color.parseColor("#e2e2e2")) });
    return localRelativeLayout;
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new f(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.f
 * JD-Core Version:    0.6.2
 */