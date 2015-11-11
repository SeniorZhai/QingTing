package com.intowow.sdk.g;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.b.k;
import com.intowow.sdk.b.k.b;
import com.intowow.sdk.j.h;
import com.intowow.sdk.model.g;
import com.intowow.sdk.triggerresponse.TriggerResponse;
import com.intowow.sdk.triggerresponse.e;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class a
  implements k.b
{
  private k a = null;
  private Map<String, Integer> b = null;
  private Map<String, String> c = null;
  private boolean d = false;
  private ExecutorService e = null;
  private Object f = null;

  public a(k paramk)
  {
    this.a = paramk;
    this.b = new HashMap();
    this.c = new HashMap();
    this.e = Executors.newSingleThreadExecutor();
    this.f = new Object();
  }

  private void a(Bundle paramBundle)
  {
    int i = paramBundle.getInt("adid");
    this.a.j().c(i);
    this.a.h().a();
  }

  private void b(Bundle paramBundle)
  {
    String str1 = paramBundle.getString("token");
    String str2 = paramBundle.getString("placement");
    int i = paramBundle.getInt("place");
    this.a.j().a(str2, str1, i);
    b(str2);
  }

  private void b(String paramString)
  {
    try
    {
      Object localObject = this.a.f();
      if (localObject == null);
      while (true)
      {
        return;
        localObject = ((com.intowow.sdk.f.b)localObject).f();
        if (localObject != null)
        {
          if (!this.c.containsKey(paramString))
          {
            localObject = ((com.intowow.sdk.model.i)localObject).b(paramString);
            if (localObject != null)
              this.c.put(paramString, ((g)localObject).a());
          }
          paramString = (String)this.c.get(paramString);
          if (paramString != null)
          {
            if (!this.b.containsKey(paramString))
              this.b.put(paramString, Integer.valueOf(0));
            this.b.put(paramString, Integer.valueOf(((Integer)this.b.get(paramString)).intValue() + 1));
          }
          if (!this.d)
          {
            this.a.d().postDelayed(new Runnable()
            {
              public void run()
              {
                a.a(a.this);
              }
            }
            , 60000L);
            this.d = true;
          }
          this.a.l().a(paramString);
        }
      }
    }
    finally
    {
    }
    throw paramString;
  }

  private void c(Bundle paramBundle)
  {
    com.intowow.sdk.h.j localj = com.intowow.sdk.h.j.values()[paramBundle.getInt("ad_event_type")];
    int i = paramBundle.getInt("adid");
    TriggerResponse localTriggerResponse = (TriggerResponse)paramBundle.getParcelable("response");
    String str = paramBundle.getString("placement");
    try
    {
      paramBundle = com.intowow.sdk.h.a.a(paramBundle);
      this.a.j().a(localj, paramBundle);
      label54: if (localj == com.intowow.sdk.h.j.h)
      {
        this.a.f().d(i);
        paramBundle = this.a.f().f(str);
        if (paramBundle != null)
          this.a.l().a(paramBundle.a());
      }
      try
      {
        while (true)
        {
          localTriggerResponse.a(e.a(this.a));
          return;
          if (localj == com.intowow.sdk.h.j.i)
            this.a.f().c(i);
        }
      }
      catch (Exception paramBundle)
      {
      }
    }
    catch (Exception paramBundle)
    {
      break label54;
    }
  }

  private void d(Bundle paramBundle)
  {
    try
    {
      paramBundle = com.intowow.sdk.h.a.b(paramBundle);
      this.a.j().b(paramBundle);
      return;
    }
    catch (Exception paramBundle)
    {
    }
  }

  private void e()
  {
    try
    {
      Map localMap = this.b;
      if (localMap == null);
      while (true)
      {
        return;
        if (this.a.f().a(this.b))
        {
          this.a.f().G();
          this.b.clear();
        }
        this.d = false;
      }
    }
    finally
    {
    }
  }

  public int a(String paramString)
  {
    if (!this.b.containsKey(paramString))
      return 0;
    return ((Integer)this.b.get(paramString)).intValue();
  }

  public Map<String, Integer> a()
  {
    return this.b;
  }

  public void a(Message paramMessage)
  {
    try
    {
      paramMessage = paramMessage.getData();
      h.b localb = h.b.values()[paramMessage.getInt("type")];
      switch (d()[localb.ordinal()])
      {
      case 17:
        c(paramMessage);
        return;
      case 18:
      case 19:
      case 20:
      }
    }
    catch (Exception paramMessage)
    {
      h.a(paramMessage);
      return;
    }
    b(paramMessage);
    return;
    a(paramMessage);
    return;
    d(paramMessage);
    return;
  }

  public ExecutorService b()
  {
    return this.e;
  }

  public Object c()
  {
    return this.f;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.g.a
 * JD-Core Version:    0.6.2
 */