package com.intowow.sdk.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.intowow.sdk.a.b.e;
import com.intowow.sdk.a.j;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.c;
import com.intowow.sdk.model.ADProfile.c.a;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.e;
import com.intowow.sdk.model.ADProfile.g;
import com.intowow.sdk.model.ADProfile.i;
import com.intowow.sdk.model.ADProfile.k;
import com.intowow.sdk.model.ADProfile.m;
import com.intowow.sdk.model.ADProfile.n;
import com.intowow.sdk.model.g;
import com.intowow.sdk.model.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class a
  implements h.a, k.c
{
  private com.intowow.sdk.f.b a = null;
  private f b = null;
  private k c = null;
  private com.intowow.sdk.i.a d = null;
  private Set<Integer> e = new HashSet();
  private Map<String, d> f = null;
  private final h.b[] g = { h.b.d, h.b.e, h.b.g, h.b.h, h.b.f, h.b.k };

  public a(k paramk, com.intowow.sdk.f.b paramb, com.intowow.sdk.i.a parama)
  {
    this.c = paramk;
    this.a = paramb;
    this.b = new f(this.a.N());
    this.d = parama;
    this.f = new HashMap();
    e();
    this.c.a(this);
  }

  private ADProfile a(g paramg, ADProfile paramADProfile)
  {
    if (paramg == null);
    label168: 
    while (true)
    {
      return paramADProfile;
      j localj = e.a(f()).s();
      if (localj != null)
      {
        paramg = localj.a(paramg.a());
        if (paramg != null)
        {
          long l = paramg.b;
          paramg = paramg.a();
          boolean bool = ADProfile.i.d(paramADProfile.g());
          int j = paramg.length;
          int i = 0;
          if (i >= j)
            paramg = null;
          while (true)
            while (true)
            {
              if (paramg == null)
                break label168;
              return ADProfile.a(paramg);
              localj = paramg[i];
              if (((localj.equals("VIDEO")) && (bool)) || ((localj.equals("IMAGE")) && (!bool)));
              try
              {
                paramg = new JSONObject(paramADProfile.b().toString());
                try
                {
                  paramg.optJSONObject("effect_setting").put(ADProfile.g.h.toString().toLowerCase(), l);
                }
                catch (Exception localException)
                {
                }
                continue;
                i += 1;
              }
              catch (Exception paramg)
              {
                paramg = null;
              }
            }
        }
      }
    }
  }

  private ADProfile a(String paramString, ADProfile paramADProfile)
  {
    return a(e.a(f()).b(paramString), paramADProfile);
  }

  private void a(int paramInt, String paramString)
  {
    if (this.a != null)
    {
      paramString = this.a.b(paramInt);
      this.a.a(paramString, ADProfile.n.a);
    }
  }

  private void b()
  {
    if (this.f == null)
      return;
    long l;
    synchronized (this.f)
    {
      l = System.currentTimeMillis();
      Iterator localIterator = this.f.keySet().iterator();
      if (!localIterator.hasNext())
        return;
    }
    Object localObject2 = (String)localObject1.next();
    localObject2 = (d)this.f.get(localObject2);
    Object localObject3 = new ArrayList();
    Object localObject4 = ((d)localObject2).a.iterator();
    label96: if (!((Iterator)localObject4).hasNext())
    {
      localObject3 = ((List)localObject3).iterator();
      label115: if (((Iterator)localObject3).hasNext())
      {
        localObject4 = (c)((Iterator)localObject3).next();
        if (!(((c)localObject4).b instanceof b))
          break label210;
        ((b)((c)localObject4).b).a();
      }
    }
    while (true)
    {
      ((d)localObject2).a((c)localObject4);
      break label115;
      break;
      c localc = (c)((Iterator)localObject4).next();
      if (l - localc.c <= 5000L)
        break label96;
      ((List)localObject3).add(localc);
      break label96;
      label210: ((a)((c)localObject4).b).a(null, -1);
    }
  }

  private void c()
  {
    this.b.a();
  }

  private void d()
  {
    this.b.b();
    this.e.clear();
    com.intowow.sdk.i.a.a();
  }

  private void e()
  {
    List localList = this.a.e();
    com.intowow.sdk.model.i locali = this.a.f();
    j localj = this.a.F();
    if ((localList == null) || (localList.size() == 0) || (locali == null) || (localj == null) || (localj.c == null))
      return;
    this.b.a(localList, locali, localj.c);
  }

  private Context f()
  {
    if (this.c != null)
      return this.c.b();
    return null;
  }

  public ADProfile a(String paramString1, String paramString2, int paramInt, e.c paramc)
  {
    while (true)
    {
      try
      {
        boolean bool = this.a.v();
        if (!bool)
        {
          paramString1 = null;
          return paramString1;
        }
        if (this.a.F() != null)
        {
          locali = this.a.F().f;
          int i = 0;
          if (this.c.c() != null)
            i = this.c.c().a();
          paramString1 = this.b.a(this.a.s(), paramString1, paramString2, paramInt, this.a.h(), i, true, locali, paramc);
          if ((paramString1 != null) && (paramString1.s() == ADProfile.n.b))
          {
            if (!this.e.contains(Integer.valueOf(paramString1.e())))
            {
              this.e.add(Integer.valueOf(paramString1.e()));
              if (!com.intowow.sdk.j.b.a(this.c.b(), paramString1.p()))
              {
                a(paramString1.e(), null);
                paramString1 = null;
                continue;
              }
            }
            paramString1 = a(paramString2, paramString1);
            continue;
          }
          paramString1 = null;
          continue;
        }
      }
      finally
      {
      }
      com.intowow.sdk.a.i locali = null;
    }
  }

  public List<h.b> a()
  {
    return Arrays.asList(this.g);
  }

  public void a(Bundle paramBundle)
  {
    paramBundle = h.b.values()[paramBundle.getInt("type")];
    if (paramBundle == h.b.d)
      c();
    do
    {
      return;
      if (paramBundle == h.b.e)
      {
        d();
        return;
      }
      if ((paramBundle == h.b.g) || (paramBundle == h.b.h) || (paramBundle == h.b.f))
      {
        e();
        return;
      }
    }
    while (paramBundle != h.b.k);
    b();
  }

  public void a(ADProfile paramADProfile)
  {
    if ((this.a == null) || (this.d == null) || (this.f == null) || (paramADProfile == null) || (paramADProfile.h() == null))
      return;
    long l = this.a.s();
    boolean bool = com.intowow.sdk.b.a.a.a(paramADProfile, l);
    Object localObject2;
    if ((bool) && (ADProfile.i.d(paramADProfile.g())))
    {
      localObject2 = paramADProfile.p();
      localObject3 = ((ADProfile.e)localObject2).a().iterator();
    }
    Object localObject5;
    ADProfile.m localm;
    Object localObject6;
    int j;
    int i;
    do
    {
      if (!((Iterator)localObject3).hasNext())
        synchronized (this.f)
        {
          localObject5 = paramADProfile.h();
          localObject2 = null;
          localObject1 = null;
          localm = paramADProfile.f();
          if ((localm != ADProfile.m.b) && (localm != ADProfile.m.c))
            break label582;
          localObject2 = null;
          localObject6 = new ArrayList();
          j = localObject5.length;
          i = 0;
          if (i < j)
            break;
          if (((List)localObject6).size() != 0)
            break label356;
          return;
        }
      localObject1 = ((ADProfile.e)localObject2).b((ADProfile.d)((Iterator)localObject3).next());
    }
    while (((ADProfile.c)localObject1).a() != ADProfile.c.a.b);
    Object localObject1 = ((ADProfile.k)localObject1).e();
    Object localObject4 = paramADProfile.e() + "_" + (String)localObject1;
    if (f() != null);
    for (localObject1 = com.intowow.sdk.j.k.a(f()).a() + (String)localObject1; ; localObject1 = null)
    {
      this.d.a(new com.intowow.sdk.i.a.a()
      {
        public void a()
        {
        }

        public void a(int paramAnonymousInt, String paramAnonymousString)
        {
          a.a(a.this, paramAnonymousInt, paramAnonymousString);
        }

        public void a(Bitmap paramAnonymousBitmap)
        {
        }

        public String b()
        {
          return this.b;
        }

        public String c()
        {
          return this.c;
        }
      });
      break;
    }
    localObject4 = localObject5[i];
    Object localObject3 = (d)this.f.get(localObject4);
    localObject1 = localObject2;
    if (localObject3 != null)
    {
      localObject1 = localObject2;
      if (localObject2 == null)
        localObject1 = localObject4;
      ((List)localObject6).addAll(((d)localObject3).a);
      break label881;
      label356: localObject3 = this.a.e((String)localObject2);
      if (localObject3 == null)
        break label895;
      localObject2 = ((g)localObject3).b();
      label377: localObject3 = a((g)localObject3, paramADProfile);
      localObject4 = ((List)localObject6).iterator();
      while (true)
      {
        if (!((Iterator)localObject4).hasNext())
        {
          localObject2 = localObject1;
          label407: ((d)localObject2).a.clear();
          return;
        }
        localObject5 = (c)((Iterator)localObject4).next();
        if ((((c)localObject5).b instanceof b))
        {
          if (bool)
            ((b)((c)localObject5).b).a((ADProfile)localObject3);
          else
            ((b)((c)localObject5).b).a();
        }
        else
        {
          localObject6 = (a)((c)localObject5).b;
          if (bool)
          {
            ((a)localObject6).a(((c)localObject5).a, (ADProfile)localObject3);
            if ((localObject2 != null) && (localObject2 == h.c))
              this.b.a(this.a.s(), paramADProfile, ((c)localObject5).a + "_" + localm);
          }
          else
          {
            ((a)localObject6).a((ADProfile)localObject3, -1);
          }
        }
      }
      label582: localObject3 = null;
      j = localObject5.length;
      i = 0;
      localObject1 = localObject2;
      localObject2 = localObject3;
      if (i >= j)
      {
        if (localObject1 != null);
      }
      else
      {
        localObject3 = localObject5[i];
        localObject4 = (d)this.f.get(localObject3);
        if (localObject4 == null)
          break label870;
        if (((d)localObject4).a.size() == 0)
        {
          localObject3 = localObject1;
          localObject1 = localObject2;
          localObject2 = localObject3;
          break label900;
        }
        if (localObject1 == null)
          break label917;
        if (((c)((d)localObject1).a.get(0)).c <= ((c)((d)localObject4).a.get(0)).c)
          break label870;
        localObject2 = localObject4;
        localObject1 = localObject3;
        break label900;
      }
      localObject3 = a(this.a.e((String)localObject2), paramADProfile);
      i = 0;
    }
    while (true)
    {
      localObject2 = localObject1;
      if (i >= ((d)localObject1).a.size())
        break label407;
      localObject2 = (c)((d)localObject1).a.get(i);
      if ((i == 0) && (bool))
      {
        paramADProfile.b(l);
        if ((((c)localObject2).b instanceof b))
          ((b)((c)localObject2).b).a((ADProfile)localObject3);
        else
          ((a)((c)localObject2).b).a(((c)localObject2).a, (ADProfile)localObject3);
      }
      else if ((((c)localObject2).b instanceof b))
      {
        ((b)((c)localObject2).b).a();
      }
      else
      {
        ((a)((c)localObject2).b).a(null, -1);
        break label926;
        label870: localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
        break label900;
        label881: i += 1;
        localObject2 = localObject1;
        localObject1 = localObject3;
        break;
        label895: localObject2 = null;
        break label377;
        while (true)
        {
          label900: i += 1;
          localObject3 = localObject2;
          localObject2 = localObject1;
          localObject1 = localObject3;
          break;
          label917: localObject2 = localObject4;
          localObject1 = localObject3;
        }
      }
      label926: i += 1;
    }
  }

  static abstract interface a
  {
    public abstract void a(ADProfile paramADProfile, int paramInt);

    public abstract void a(String paramString, ADProfile paramADProfile);
  }

  public static abstract interface b
  {
    public abstract void a();

    public abstract void a(ADProfile paramADProfile);
  }

  class c
  {
    public String a;
    public Object b;
    public long c;
  }

  class d
  {
    public List<a.c> a;

    public void a(a.c paramc)
    {
      Iterator localIterator = this.a.iterator();
      a.c localc;
      do
      {
        if (!localIterator.hasNext())
          return;
        localc = (a.c)localIterator.next();
      }
      while (!localc.a.equals(paramc.a));
      this.a.remove(localc);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.a
 * JD-Core Version:    0.6.2
 */