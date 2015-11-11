package com.intowow.sdk.i.c.c;

import android.app.Activity;
import android.widget.RelativeLayout;
import com.intowow.sdk.f.e;
import com.intowow.sdk.f.e.a;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.i;
import com.intowow.sdk.model.j;

public class h extends d
{
  public h(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  protected int a()
  {
    if (ADProfile.i.b(this.c.g()))
      return this.h.b();
    return this.h.b();
  }

  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    this.y = a(this.h.a(e.a.C), "btn_single_close_nm.png", "btn_single_close_at.png");
    paramRelativeLayout.addView(this.y);
  }

  protected int b()
  {
    if (ADProfile.i.b(this.c.g()))
      return this.h.c();
    return this.h.a(e.a.h);
  }

  public static class a
    implements c
  {
    public a a(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
    {
      return new h(paramActivity, paramj, paramADProfile, parama);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.h
 * JD-Core Version:    0.6.2
 */