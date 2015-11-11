package com.intowow.sdk.g;

import android.os.Bundle;
import android.os.Message;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.b.k;
import com.intowow.sdk.b.k.a;
import com.intowow.sdk.b.k.a.a;
import com.intowow.sdk.b.k.b;
import com.intowow.sdk.d.a;
import com.intowow.sdk.h.i;
import com.intowow.sdk.j.h;
import com.intowow.sdk.model.g;

public class c
  implements k.b
{
  private k a = null;

  public c(k paramk)
  {
    this.a = paramk;
  }

  private void a(Bundle paramBundle)
  {
    paramBundle = this.a.f().F();
    if (paramBundle != null)
    {
      this.a.h().b(paramBundle.l());
      this.a.h().a(paramBundle.k());
      this.a.l().a();
      this.a.j().a();
      if (this.a.m() != null)
        this.a.m().a();
      this.a.o();
    }
  }

  private void b(Bundle paramBundle)
  {
    this.a.l().a();
    this.a.l().b();
  }

  private void c(Bundle paramBundle)
  {
    this.a.l().a();
    this.a.l().b();
    if (this.a.c().b() == k.a.a.c)
      this.a.f().I();
  }

  private void d(Bundle paramBundle)
  {
    this.a.l().a(true);
  }

  private void e(Bundle paramBundle)
  {
    paramBundle = paramBundle.getString("placement");
    String str = this.a.c().d();
    g localg = this.a.f().f(paramBundle);
    this.a.c().a(paramBundle);
    if (localg == null);
    while ((str != null) && (localg.a().equals(str)))
      return;
    this.a.c().b(localg.a());
    this.a.l().b();
  }

  public void a(Message paramMessage)
  {
    try
    {
      paramMessage = paramMessage.getData();
      h.b localb = h.b.values()[paramMessage.getInt("type")];
      switch (a()[localb.ordinal()])
      {
      case 8:
        c(paramMessage);
        return;
      case 6:
      case 7:
      case 9:
      case 15:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      }
    }
    catch (Exception paramMessage)
    {
      h.a(paramMessage);
      return;
    }
    a(paramMessage);
    return;
    b(paramMessage);
    return;
    d(paramMessage);
    return;
    e(paramMessage);
    return;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.g.c
 * JD-Core Version:    0.6.2
 */