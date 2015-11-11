package com.intowow.sdk.g;

import android.os.Bundle;
import android.os.Message;
import com.intowow.sdk.b.b;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.b.j;
import com.intowow.sdk.b.k.a;
import com.intowow.sdk.b.k.b;
import com.intowow.sdk.d.a;
import com.intowow.sdk.h.i;
import com.intowow.sdk.j.h;

public class d
  implements k.b
{
  private com.intowow.sdk.b.k a = null;

  public d(com.intowow.sdk.b.k paramk)
  {
    this.a = paramk;
  }

  private void a(Bundle paramBundle)
  {
    int i = paramBundle.getInt("network_type");
    this.a.c().a(i);
    this.a.j().a(i);
    this.a.i().a(i);
    boolean bool = com.intowow.sdk.h.k.a(i);
    if (this.a.m() != null)
    {
      if (!bool)
        break label88;
      this.a.m().a();
    }
    while (true)
    {
      this.a.n();
      if (bool)
        this.a.t();
      return;
      label88: this.a.m().b();
    }
  }

  private void b(Bundle paramBundle)
  {
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
      case 21:
        a(paramMessage);
        return;
      case 16:
      }
    }
    catch (Exception paramMessage)
    {
      h.a(paramMessage);
      return;
    }
    b(paramMessage);
    return;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.g.d
 * JD-Core Version:    0.6.2
 */