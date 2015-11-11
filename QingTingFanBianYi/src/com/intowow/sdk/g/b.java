package com.intowow.sdk.g;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.intowow.sdk.ScheduleReceiver;
import com.intowow.sdk.b.d.a;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.b.k.a;
import com.intowow.sdk.b.k.a.a;
import com.intowow.sdk.b.k.b;
import java.util.Calendar;
import org.json.JSONObject;

public class b
  implements k.b
{
  private com.intowow.sdk.b.k a = null;
  private Runnable b = new Runnable()
  {
    public void run()
    {
      if (b.a(b.this) != null)
      {
        Bundle localBundle = new Bundle();
        localBundle.putInt("type", h.b.k.ordinal());
        b.a(b.this).e().a(localBundle);
        b.a(b.this).d().postDelayed(b.b(b.this), 5000L);
      }
    }
  };

  public b(com.intowow.sdk.b.k paramk)
  {
    this.a = paramk;
  }

  private void a(Bundle paramBundle)
  {
    this.a.c().a(k.a.a.b);
    this.a.j().d();
    c();
    a();
    this.a.t();
    if (this.a.f().y())
    {
      this.a.n();
      return;
    }
    e();
    f();
  }

  private void b(Bundle paramBundle)
  {
    this.a.c().a(k.a.a.c);
    int i = paramBundle.getInt("duration");
    this.a.j().b(i);
    this.a.h().a();
    d();
    this.a.c().b("");
    this.a.c().a("");
    this.a.l().b();
  }

  private void c()
  {
    if (this.a != null)
    {
      this.a.d().removeCallbacks(this.b);
      this.a.d().postDelayed(this.b, 5000L);
    }
  }

  private void c(Bundle paramBundle)
  {
    paramBundle = this.a.c();
    if (paramBundle.b() != k.a.a.c)
      return;
    long l;
    if ((this.a != null) && (this.a.j() != null))
    {
      l = com.intowow.sdk.j.k.b(com.intowow.sdk.j.k.a(this.a.b()).a());
      if (l == -1L)
        break label236;
    }
    label236: for (int i = (int)(l / 1024L / 1024L); ; i = -1)
    {
      this.a.j().a(com.intowow.sdk.j.d.b(this.a.b()), com.intowow.sdk.j.d.c(this.a.b()), i);
      com.intowow.sdk.f.b localb = this.a.f();
      if ((localb.F() == null) || (localb.F().f == null))
        break;
      if ((com.intowow.sdk.a.e.a) && (this.a.m() != null))
        this.a.m().a("Start background fetch");
      this.a.t();
      com.intowow.sdk.a.i locali = localb.F().f;
      if (com.intowow.sdk.j.e.a(paramBundle.b(), this.a, locali))
        break;
      if (!localb.y())
        break label242;
      if ((com.intowow.sdk.a.e.a) && (this.a.m() != null))
        this.a.m().a("background for checkSDK");
      this.a.n();
      return;
    }
    label242: if ((com.intowow.sdk.a.e.a) && (this.a.m() != null))
      this.a.m().a("background for updateAdList");
    f();
  }

  private void d()
  {
    if (this.a != null)
      this.a.d().removeCallbacks(this.b);
  }

  private void e()
  {
    Object localObject = this.a.f();
    if ((localObject != null) && (((com.intowow.sdk.f.b)localObject).F() != null) && (!((com.intowow.sdk.f.b)localObject).F().b()));
    do
    {
      do
        return;
      while (!this.a.f().B());
      localObject = com.intowow.sdk.j.a.a(this.a.b(), this.a.f());
    }
    while (localObject == null);
    this.a.g().a((String)localObject, null, new d.a()
    {
      public void a(int paramAnonymousInt)
      {
      }

      public void a(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          if (b.a(b.this).f().b(paramAnonymousJSONObject))
            b.c(b.this);
          return;
        }
        catch (Exception paramAnonymousJSONObject)
        {
        }
      }
    });
  }

  private void f()
  {
    Object localObject = this.a.f();
    if ((localObject != null) && (((com.intowow.sdk.f.b)localObject).F() != null) && (!((com.intowow.sdk.f.b)localObject).F().b()));
    do
    {
      return;
      if (!this.a.f().A())
      {
        this.a.l().b();
        return;
      }
      localObject = com.intowow.sdk.j.a.a(this.a);
    }
    while (localObject == null);
    this.a.g().a((String)localObject, null, new d.a()
    {
      public void a(int paramAnonymousInt)
      {
      }

      public void a(JSONObject paramAnonymousJSONObject)
      {
        try
        {
          b.a(b.this).f().e(paramAnonymousJSONObject);
          return;
        }
        catch (Exception paramAnonymousJSONObject)
        {
        }
      }
    });
  }

  public void a()
  {
    Object localObject = this.a.f();
    if ((((com.intowow.sdk.f.b)localObject).F() != null) && (((com.intowow.sdk.f.b)localObject).F().f != null))
    {
      long l = ((com.intowow.sdk.f.b)localObject).F().f.a;
      localObject = new Intent(this.a.b(), ScheduleReceiver.class);
      localObject = PendingIntent.getBroadcast(this.a.b(), 0, (Intent)localObject, 0);
      AlarmManager localAlarmManager = (AlarmManager)this.a.b().getSystemService("alarm");
      localAlarmManager.cancel((PendingIntent)localObject);
      localAlarmManager.setRepeating(0, Calendar.getInstance().getTimeInMillis(), l, (PendingIntent)localObject);
    }
  }

  public void a(Message paramMessage)
  {
    try
    {
      paramMessage = paramMessage.getData();
      h.b localb = h.b.values()[paramMessage.getInt("type")];
      switch (b()[localb.ordinal()])
      {
      case 4:
        a(paramMessage);
        return;
      case 5:
      case 10:
      }
    }
    catch (Exception paramMessage)
    {
      com.intowow.sdk.j.h.a(paramMessage);
      return;
    }
    b(paramMessage);
    return;
    c(paramMessage);
    return;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.g.b
 * JD-Core Version:    0.6.2
 */