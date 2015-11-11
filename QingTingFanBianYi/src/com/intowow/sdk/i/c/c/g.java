package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.intowow.sdk.f.f;
import com.intowow.sdk.f.f.a;
import com.intowow.sdk.j.n;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.j;

public class g extends e
{
  public g(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
    this.H = a();
  }

  protected int a()
  {
    return this.g.a(f.a.g);
  }

  protected RelativeLayout a(ADProfile.d paramd1, ADProfile.d paramd2, ADProfile.d paramd3)
  {
    if ((!this.c.b(paramd1)) || (!this.c.b(paramd2)) || (!this.c.b(paramd3)))
      return null;
    RelativeLayout localRelativeLayout = b(a(), this.g.a(f.a.C));
    paramd1 = a(paramd1, this.g.a(f.a.D), this.g.a(f.a.E), this.g.a(f.a.F));
    paramd2 = a(paramd2, -2, -2, paramd1.getId(), this.g.a(f.a.G), this.g.a(f.a.H), Color.parseColor("#e2e2e2"));
    n.a(localRelativeLayout, new View[] { paramd1, paramd2, a(paramd3, this.g.a(f.a.I), -2, this.g.a(f.a.G), paramd2.getId(), paramd1.getId(), this.g.a(f.a.J), -1) });
    return localRelativeLayout;
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    paramRelativeLayout.removeAllViews();
    int i = a();
    com.intowow.sdk.i.c.a locala1 = a(i, this.g.a(f.a.z), 10, true, true, 0);
    com.intowow.sdk.i.c.a locala2 = a(i, this.g.a(f.a.A), 12, false, false, this.g.a(f.a.B));
    locala2.setBackgroundDrawable(this.i.b("image_gamecard_mask.9.png"));
    ImageButton localImageButton = a(i, this.g.a(f.a.B), 0, 0, 14, "btn_protrait_image_gamecard_android_nm.png", "btn_protrait_image_gamecard_android_at.png");
    i();
    a(i, this.g.a(f.a.C), 14, false, this.g.a(f.a.B));
    this.I = a(this.g.a(f.a.l), "btn_single_close_nm.png", "btn_single_close_at.png");
    n.a(paramRelativeLayout, new View[] { locala1, locala2, localImageButton, this.K, this.I });
  }

  protected int b()
  {
    return this.g.a(f.a.h);
  }

  protected RelativeLayout h()
  {
    if ((!this.c.b(ADProfile.d.v)) || (!this.c.b(ADProfile.d.e)) || (!this.c.b(ADProfile.d.f)))
      return null;
    RelativeLayout localRelativeLayout = b(a(), this.g.a(f.a.C));
    ImageView localImageView = a(ADProfile.d.v, this.g.a(f.a.D), this.g.a(f.a.E), this.g.a(f.a.F));
    TextView localTextView = a(ADProfile.d.e, this.g.a(f.a.I), -2, localImageView.getId(), this.g.a(f.a.F), this.g.a(f.a.K), -1);
    n.a(localRelativeLayout, new View[] { localImageView, localTextView, a(ADProfile.d.f, this.g.a(f.a.I), -2, this.g.a(f.a.G), localTextView.getId(), localImageView.getId(), this.g.a(f.a.J), Color.parseColor("#e2e2e2")) });
    return localRelativeLayout;
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new g(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.g
 * JD-Core Version:    0.6.2
 */