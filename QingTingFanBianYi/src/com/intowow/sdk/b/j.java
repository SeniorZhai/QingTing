package com.intowow.sdk.b;

import com.intowow.sdk.a.i;
import com.intowow.sdk.a.i.d;
import com.intowow.sdk.a.i.e;
import com.intowow.sdk.e.a.a;
import com.intowow.sdk.e.b.b;
import com.intowow.sdk.e.b.c;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.c;
import com.intowow.sdk.model.ADProfile.c.a;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.e;
import com.intowow.sdk.model.ADProfile.i;
import com.intowow.sdk.model.ADProfile.k;
import com.intowow.sdk.model.ADProfile.m;
import com.intowow.sdk.model.ADProfile.n;
import com.intowow.sdk.model.ADProfile.q;
import com.intowow.sdk.model.g;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class j
{
  private boolean a = false;
  private com.intowow.sdk.a.b b = null;
  private k c = null;
  private boolean d = false;

  public j(k paramk)
  {
    this.c = paramk;
    a();
    this.d = this.c.f().v();
  }

  private com.intowow.sdk.e.a a(final ADProfile paramADProfile, ADProfile.c paramc)
  {
    String str1;
    String str2;
    long l;
    switch (c()[paramc.a().ordinal()])
    {
    default:
      paramc = null;
      str1 = null;
      str2 = null;
      l = 0L;
    case 2:
    case 3:
    }
    while ((str2 == null) || (str1 == null) || (l == 0L))
    {
      return null;
      paramc = (ADProfile.k)paramc;
      l = paramc.f();
      str2 = paramc.d();
      str1 = paramc.e();
      paramc = paramc.b();
      continue;
      paramc = (ADProfile.q)paramc;
      l = paramc.g();
      str2 = paramc.d();
      str1 = paramc.e();
      paramc = paramc.b();
    }
    com.intowow.sdk.e.a locala = new com.intowow.sdk.e.a();
    locala.a(l);
    locala.c(str2);
    locala.b(com.intowow.sdk.j.k.a(this.c.b()).a() + str1);
    locala.a(paramADProfile.e());
    locala.a(paramc);
    locala.c(System.currentTimeMillis());
    locala.a(new b.b()
    {
      public void a(com.intowow.sdk.e.a paramAnonymousa)
      {
      }

      public void a(com.intowow.sdk.e.a paramAnonymousa, int paramAnonymousInt)
      {
        if (j.a(j.this).m() != null)
          j.a(j.this).m().a(String.format("Failed to download asset[%s] for error[%s]", new Object[] { paramAnonymousa.e(), com.intowow.sdk.e.b.a(paramAnonymousInt) }));
      }

      public void b(com.intowow.sdk.e.a paramAnonymousa)
      {
      }

      public void c(com.intowow.sdk.e.a paramAnonymousa)
      {
        try
        {
          if (com.intowow.sdk.j.b.a(j.a(j.this).b(), paramADProfile.p()))
          {
            if (paramADProfile.s() == ADProfile.n.b)
              return;
            j.a(j.this).a(paramADProfile);
            return;
          }
        }
        catch (Exception paramAnonymousa)
        {
          com.intowow.sdk.j.h.a(paramAnonymousa);
        }
      }
    });
    return locala;
  }

  private String d()
  {
    Object localObject = this.c.c();
    if (((k.a)localObject).b() == k.a.a.c);
    for (localObject = null; localObject != null; localObject = ((k.a)localObject).c())
    {
      localObject = this.c.f().f((String)localObject);
      if (localObject == null)
        break;
      return ((g)localObject).a();
    }
    return "";
  }

  public void a()
  {
    com.intowow.sdk.f.b localb = this.c.f();
    if ((localb != null) && (localb.F() != null))
    {
      this.a = localb.F().b();
      this.b = localb.F().c;
    }
  }

  public void a(String paramString)
  {
    Object localObject3;
    com.intowow.sdk.e.b localb;
    Object localObject1;
    boolean bool2;
    boolean bool3;
    Object localObject4;
    Object localObject6;
    Object localObject2;
    int m;
    while (true)
    {
      try
      {
        localObject3 = this.c.f();
        localb = this.c.h();
        localObject1 = this.c.i();
        if ((!this.a) || (!this.d) || (localObject3 == null) || (localb == null) || (localObject1 == null))
        {
          if (com.intowow.sdk.a.e.a)
          {
            bool2 = this.a;
            bool3 = this.d;
            if (localObject3 != null)
            {
              bool1 = true;
              com.intowow.sdk.j.h.b("Sanity checks : group[%s]IsServing[%s]IsAssetReady[%s]dmOk[%s]", new Object[] { paramString, Boolean.valueOf(bool2), Boolean.valueOf(bool3), Boolean.valueOf(bool1) });
            }
          }
          else
          {
            return;
          }
          bool1 = false;
          continue;
        }
        localObject4 = ((b)localObject1).a();
        if (localObject4 == b.a.a)
        {
          localb.c();
          continue;
        }
      }
      finally
      {
      }
      localb.d();
      localObject1 = ((com.intowow.sdk.f.b)localObject3).i();
      localObject6 = ((com.intowow.sdk.f.b)localObject3).e();
      localObject2 = ((com.intowow.sdk.f.b)localObject3).e(paramString);
      m = ((com.intowow.sdk.f.b)localObject3).h();
      if ((localObject1 != null) && (localObject6 != null) && (((List)localObject6).size() != 0) && (localObject2 != null))
        break;
      if (com.intowow.sdk.a.e.a)
      {
        if (localObject1 == null)
          break label936;
        bool1 = true;
        if ((localObject6 == null) || (((List)localObject6).size() <= 0))
          break label942;
        bool2 = true;
        label241: if (localObject2 == null)
          break label948;
        bool3 = true;
        label248: com.intowow.sdk.j.h.b("return : group[%s]prefetchCfgOk[%s]allProfilesOk[%s]pgOk[%s]", new Object[] { paramString, Boolean.valueOf(bool1), Boolean.valueOf(bool2), Boolean.valueOf(bool3) });
      }
    }
    boolean bool1 = paramString.equals(d());
    Object localObject5 = ((i)localObject1).a(paramString);
    long l1 = ((com.intowow.sdk.f.b)localObject3).s();
    long l2 = ((i)localObject1).c;
    int i;
    label331: label352: label369: int j;
    label451: int k;
    label561: label585: String[] arrayOfString;
    int n;
    if (((g)localObject2).b() == com.intowow.sdk.model.h.c)
    {
      i = 1;
      PriorityQueue localPriorityQueue;
      if (this.c.c() != null)
      {
        localObject1 = this.c.c().b();
        if (((com.intowow.sdk.f.b)localObject3).F() == null)
          break label982;
        localObject2 = ((com.intowow.sdk.f.b)localObject3).F().f;
        localPriorityQueue = new PriorityQueue(5, new j.a.a());
        localObject2 = com.intowow.sdk.j.e.a((i)localObject2, this.c.c().a());
        localObject6 = ((List)localObject6).iterator();
      }
      while (true)
      {
        if (!((Iterator)localObject6).hasNext())
        {
          localObject1 = ((i.d)localObject5).a(((com.intowow.sdk.f.b)localObject3).g(paramString) + this.c.a(paramString));
          if (localObject1 != null)
            break label810;
          i = 0;
          break label954;
          localObject2 = localb.b();
          localObject1 = (a)localPriorityQueue.poll();
          i = j;
          while ((i > 0) && (localObject1 != null))
          {
            localObject3 = a.a((a)localObject1);
            if ((((ADProfile)localObject3).s() != ADProfile.n.b) && (!((Set)localObject2).contains(Integer.valueOf(((ADProfile)localObject3).e()))))
            {
              localObject1 = new b.c(bool1, k, ((a)localObject1).b(), ((a)localObject1).a(), ((ADProfile)localObject3).d(), 0);
              localObject4 = ((ADProfile)localObject3).p();
              localObject5 = ((ADProfile.e)localObject4).a().iterator();
              j = 0;
              if (((Iterator)localObject5).hasNext())
                break label828;
              if (j != 0)
                break label908;
              this.c.a((ADProfile)localObject3);
            }
            localObject1 = (a)localPriorityQueue.poll();
            i -= 1;
          }
          break;
          localObject1 = k.a.a.a;
          break label352;
        }
        ADProfile localADProfile = (ADProfile)((Iterator)localObject6).next();
        ADProfile.m localm = localADProfile.f();
        if ((localADProfile.s() != ADProfile.n.c) && (localADProfile.i() <= l1 + l2) && (localADProfile.j() >= l1))
        {
          k = 0;
          if ((i == 0) || (localm != ADProfile.m.c))
          {
            arrayOfString = localADProfile.h();
            n = arrayOfString.length;
            j = 0;
            break label987;
          }
          label699: if (((localm == ADProfile.m.b) || (localm == ADProfile.m.c)) && ((localADProfile.s() == ADProfile.n.b) || (com.intowow.sdk.b.a.a.a(localADProfile, l1, this.b.c(paramString), m)) || ((localObject1 == k.a.a.b) && (com.intowow.sdk.j.e.a((List)localObject2, localADProfile))) || (!com.intowow.sdk.b.a.a.a(localADProfile, m))))
            break label1004;
          localPriorityQueue.add(new a(localADProfile, (b.a)localObject4));
        }
      }
    }
    label810: label828: label961: label982: label987: label998: label1004: label1013: 
    while (true)
    {
      if (arrayOfString[j].equals(paramString))
      {
        j = 1;
        break label998;
        i = ((i.e)localObject1).b;
        while (localObject1 != null)
        {
          k = ((i.e)localObject1).a;
          break label961;
          localObject6 = ((ADProfile.e)localObject4).b((ADProfile.d)((Iterator)localObject5).next());
          if ((((ADProfile.c)localObject6).a() == ADProfile.c.a.d) || (com.intowow.sdk.j.b.a(this.c.b(), (ADProfile.c)localObject6) == com.intowow.sdk.j.b.a.d))
            break label561;
          localObject6 = a((ADProfile)localObject3, (ADProfile.c)localObject6);
          if (localObject6 == null)
            break label561;
          ((com.intowow.sdk.e.a)localObject6).a((b.c)localObject1);
          localb.a((com.intowow.sdk.e.a)localObject6);
          j = 1;
          break label561;
          com.intowow.sdk.j.h.b("dynamic downloader enqueue [%s][%d]", new Object[] { paramString, Integer.valueOf(((ADProfile)localObject3).e()) });
          break label585;
          bool1 = false;
          break;
          bool2 = false;
          break label241;
          bool3 = false;
          break label248;
        }
        k = 0;
        j = i;
        if (i != 0)
          break label451;
        j = 1;
        break label451;
        i = 0;
        break label331;
        localObject2 = null;
        break label369;
      }
      while (true)
      {
        if (j < n)
          break label1013;
        j = k;
        if (j == 0)
          break;
        break label699;
        break;
        j += 1;
      }
    }
  }

  public void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }

  public void b()
  {
    com.intowow.sdk.f.b localb1;
    Object localObject3;
    i locali;
    Object localObject5;
    boolean bool1;
    label157: Object localObject4;
    try
    {
      localb1 = this.c.f();
      com.intowow.sdk.e.b localb = this.c.h();
      localObject3 = this.c.i();
      locali = localb1.i();
      localObject5 = localb1.e();
      if ((!this.a) || (localb1 == null) || (!this.d) || (localb == null) || (localObject3 == null) || (locali == null) || (localObject5 == null) || (((List)localObject5).size() == 0))
        if (com.intowow.sdk.a.e.a)
        {
          boolean bool2 = this.a;
          boolean bool3 = this.d;
          if ((localObject5 == null) || (((List)localObject5).size() <= 0))
            break label157;
          bool1 = true;
          com.intowow.sdk.j.h.b("Sanity checks : isServing[%s]isAssetReady[%s]profilesOK[%s]", new Object[] { Boolean.valueOf(bool2), Boolean.valueOf(bool3), Boolean.valueOf(bool1) });
        }
      while (true)
      {
        return;
        bool1 = false;
        break;
        localObject4 = ((b)localObject3).a();
        if (localObject4 != b.a.a)
          break label189;
        localb.c();
      }
    }
    finally
    {
    }
    label189: int j = localb1.h();
    localObject1.d();
    long l1 = localb1.s();
    long l2 = locali.c;
    Object localObject2;
    label233: label248: HashMap localHashMap;
    label293: label338: Object localObject6;
    Object localObject7;
    int k;
    int i;
    label458: Object localObject8;
    if (this.c.c() != null)
    {
      localObject2 = this.c.c().b();
      if (localb1.F() != null)
      {
        localObject3 = localb1.F().f;
        if (com.intowow.sdk.j.e.a((k.a.a)localObject2, this.c, (i)localObject3))
          break label922;
        localHashMap = new HashMap();
        localObject3 = com.intowow.sdk.j.e.a((i)localObject3, this.c.c().a());
        localObject5 = ((List)localObject5).iterator();
      }
    }
    else
    {
      do
      {
        if (!((Iterator)localObject5).hasNext())
        {
          localObject3 = new PriorityQueue(10, new a.a());
          localObject4 = d();
          localObject5 = localHashMap.keySet().iterator();
          if (((Iterator)localObject5).hasNext())
            break label557;
          this.c.h().a((PriorityQueue)localObject3);
          break;
          localObject2 = k.a.a.a;
          break label233;
        }
        localObject6 = (ADProfile)((Iterator)localObject5).next();
      }
      while ((((ADProfile)localObject6).s() == ADProfile.n.c) || (((ADProfile)localObject6).i() > l1 + l2) || (((ADProfile)localObject6).j() < l1) || ((localObject2 == k.a.a.b) && (com.intowow.sdk.j.e.a((List)localObject3, (ADProfile)localObject6))) || (!com.intowow.sdk.b.a.a.a((ADProfile)localObject6, j)));
      localObject7 = ((ADProfile)localObject6).h();
      k = localObject7.length;
      i = 0;
      if (i >= k)
        break label931;
      localObject8 = localObject7[i];
      if (com.intowow.sdk.b.a.a.a((ADProfile)localObject6, l1, this.b.c((String)localObject8), j))
        break label924;
      if (!localHashMap.containsKey(localObject8))
        localHashMap.put(localObject8, new PriorityQueue(5, new j.a.a()));
      ((PriorityQueue)localHashMap.get(localObject8)).add(new a((ADProfile)localObject6, (b.a)localObject4));
      break label924;
      label557: localObject6 = (String)((Iterator)localObject5).next();
      i = localb1.g((String)localObject6);
      j = this.c.a((String)localObject6);
      bool1 = ((String)localObject6).equals(localObject4);
      localObject2 = locali.a((String)localObject6).a(i + j);
      if (localObject2 == null)
      {
        i = 0;
        break label933;
        label623: if (localb1.N())
          break label943;
        j = i;
        if (bool1)
        {
          j = i;
          if (i == 0)
            break label943;
        }
      }
    }
    while (true)
    {
      localObject7 = (PriorityQueue)localHashMap.get(localObject6);
      localObject2 = (a)((PriorityQueue)localObject7).poll();
      i = j;
      ADProfile.e locale;
      Iterator localIterator;
      while ((i > 0) && (localObject2 != null))
      {
        localObject8 = a.a((a)localObject2);
        if (((ADProfile)localObject8).s() != ADProfile.n.b)
        {
          localObject2 = new b.c(bool1, k, ((a)localObject2).b(), ((a)localObject2).a(), ((ADProfile)localObject8).d(), 0);
          locale = ((ADProfile)localObject8).p();
          localIterator = locale.a().iterator();
          j = 0;
          label750: if (localIterator.hasNext())
            break label810;
          if (j != 0)
            break label890;
          this.c.a((ADProfile)localObject8);
        }
        label774: localObject2 = (a)((PriorityQueue)localObject7).poll();
        i -= 1;
      }
      break label338;
      i = ((i.e)localObject2).b;
      label810: label890: 
      while (localObject2 != null)
      {
        k = ((i.e)localObject2).a;
        break label623;
        Object localObject9 = locale.b((ADProfile.d)localIterator.next());
        if ((((ADProfile.c)localObject9).a() == ADProfile.c.a.d) || (com.intowow.sdk.j.b.a(this.c.b(), (ADProfile.c)localObject9) == com.intowow.sdk.j.b.a.d))
          break label750;
        localObject9 = a((ADProfile)localObject8, (ADProfile.c)localObject9);
        if (localObject9 == null)
          break label750;
        ((com.intowow.sdk.e.a)localObject9).a((b.c)localObject2);
        ((PriorityQueue)localObject3).add(localObject9);
        j = 1;
        break label750;
        com.intowow.sdk.j.h.b("schedule downloader enqueue [%s][%d]", new Object[] { localObject6, Integer.valueOf(((ADProfile)localObject8).e()) });
        break label774;
        localObject3 = null;
        break label248;
        break;
        i += 1;
        break label458;
        break label293;
      }
      label922: label924: label931: label933: k = 0;
      break label623;
      label943: j = 1;
    }
  }

  static class a
  {
    private ADProfile a;
    private int b;

    public a(ADProfile paramADProfile, b.a parama)
    {
      this.a = paramADProfile;
      if (a(parama));
      for (int i = 1; ; i = 0)
      {
        this.b = i;
        return;
      }
    }

    private boolean a(b.a parama)
    {
      if (ADProfile.i.d(this.a.g()))
        if (parama != b.a.c);
      while (parama == b.a.b)
      {
        return true;
        return false;
      }
      return false;
    }

    public int a()
    {
      return this.b;
    }

    public int b()
    {
      return this.a.B();
    }

    public int c()
    {
      return this.a.d();
    }

    public int d()
    {
      return this.a.e();
    }

    public long e()
    {
      return this.a.i();
    }

    static class a
      implements Comparator<j.a>
    {
      public int a(j.a parama1, j.a parama2)
      {
        if (parama2.b() != parama1.b())
          return parama2.b() - parama1.b();
        if (parama2.c() != parama1.c())
          return parama2.c() - parama1.c();
        if (parama2.e() != parama1.e())
        {
          if (parama2.e() - parama1.e() > 0L)
            return -1;
          return 1;
        }
        return parama2.d() - parama1.d();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.j
 * JD-Core Version:    0.6.2
 */