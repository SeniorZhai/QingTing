package com.intowow.sdk.b;

import com.intowow.sdk.a.b;
import com.intowow.sdk.b.a.a;
import com.intowow.sdk.j.e;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.m;
import com.intowow.sdk.model.ADProfile.n;
import com.intowow.sdk.model.g;
import com.intowow.sdk.model.h;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class f
{
  private List<ADProfile> a = null;
  private List<a> b = null;
  private Map<String, List<ADProfile>> c = null;
  private com.intowow.sdk.model.i d = null;
  private boolean e = false;
  private Map<String, Long> f = null;
  private b g = null;

  public f(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }

  private boolean a(ADProfile paramADProfile)
  {
    return paramADProfile.f() == ADProfile.m.b;
  }

  private boolean a(ADProfile paramADProfile, long paramLong, String paramString, int paramInt)
  {
    if (this.e)
      return false;
    return a.a(paramADProfile, paramLong, this.g.c(paramString), paramInt);
  }

  private boolean a(ADProfile paramADProfile, String paramString, long paramLong)
  {
    if (paramADProfile.s() == ADProfile.n.c);
    while ((!a.a(paramADProfile, paramLong)) || (this.f.containsKey(paramString)))
      return false;
    return true;
  }

  private boolean b(ADProfile paramADProfile)
  {
    return paramADProfile.f() == ADProfile.m.c;
  }

  private boolean c(ADProfile paramADProfile)
  {
    return paramADProfile.s() == ADProfile.n.b;
  }

  public ADProfile a(long paramLong, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean, com.intowow.sdk.a.i parami, e.c paramc)
  {
    while (true)
    {
      Object localObject1;
      Object localObject2;
      try
      {
        localObject1 = this.d;
        if (localObject1 == null)
        {
          paramString1 = null;
          return paramString1;
        }
        localObject1 = this.d.b(paramString2);
        if (localObject1 == null)
        {
          paramString1 = null;
          continue;
        }
        paramString2 = ((g)localObject1).b();
        parami = e.a(parami, paramInt3);
        Iterator localIterator;
        if (paramInt1 == 1)
        {
          localObject2 = paramString1 + "_CPD";
          localIterator = this.b.iterator();
          if (localIterator.hasNext());
        }
        else
        {
          if ((paramString2 == h.c) && (paramInt1 <= 3))
          {
            localObject2 = paramString1 + "_CPH";
            paramString1 = this.a.iterator();
            if (paramString1.hasNext())
              continue;
          }
          paramString1 = (List)this.c.get(((g)localObject1).a());
          paramInt1 = 0;
          if (paramString1 == null)
            break label718;
          localObject2 = paramString1.iterator();
          paramInt1 = 0;
          paramString1 = null;
          if (((Iterator)localObject2).hasNext())
            break label505;
          paramString2 = paramString1;
          paramString1 = paramString2;
          if (paramString2 == null)
            continue;
          if (paramString2.B() >= paramInt1)
            break label706;
          paramString1 = null;
          continue;
        }
        a locala = (a)localIterator.next();
        if ((!locala.b.equals(((g)localObject1).a())) || (!a(locala.a, (String)localObject2, paramLong)) || (locala.a.o()) || ((paramc != null) && (!paramc.a(locala.a))) || ((!c(locala.a)) && (e.a(parami, locala.a))) || (!a.a(locala.a, paramInt2)))
          continue;
        if ((c(locala.a)) && (paramString2 == h.c))
          this.f.put(localObject2, Long.valueOf(paramLong));
        paramString1 = locala.a;
        continue;
        paramString2 = (ADProfile)paramString1.next();
        if ((!a(paramString2, (String)localObject2, paramLong)) || (paramString2.o()) || ((paramc != null) && (!paramc.a(paramString2))) || ((!c(paramString2)) && (e.a(parami, paramString2))) || (!a.a(paramString2, paramInt2)))
          continue;
        paramString1 = paramString2;
        if (!c(paramString2))
          continue;
        this.f.put(localObject2, Long.valueOf(paramLong));
        paramString1 = paramString2;
        continue;
      }
      finally
      {
      }
      label505: paramString2 = (ADProfile)((Iterator)localObject2).next();
      if ((paramString2.s() != ADProfile.n.c) && (a.a(paramString2, paramLong)) && ((paramc == null) || (paramc.a(paramString2))) && ((c(paramString2)) || (!e.a(parami, paramString2))) && (!a(paramString2, paramLong, ((g)localObject1).a(), paramInt2)) && (a.a(paramString2, paramInt2)))
      {
        int j = paramString2.B();
        int i;
        if ((paramBoolean) && (!c(paramString2)))
        {
          paramInt3 = 0;
          break label724;
          if (paramString1 != null)
          {
            int k = paramString1.B();
            if (j > k)
            {
              if (paramInt3 == 0)
                break label748;
              paramString1 = paramString2;
              paramInt1 = i;
              continue;
            }
            paramInt1 = i;
            if (j != k)
              continue;
            paramInt1 = i;
            if (paramInt3 == 0)
              continue;
            paramInt1 = i;
            if (paramString1.x() <= paramString2.x())
              continue;
            paramString1 = paramString2;
            paramInt1 = i;
            continue;
            label706: paramString2.b(paramLong);
            paramString1 = paramString2;
            continue;
            label718: paramString2 = null;
            continue;
          }
        }
        else
        {
          while (true)
          {
            label724: i = paramInt1;
            if (paramInt1 >= j)
              break;
            i = j;
            break;
            paramInt3 = 1;
          }
          label748: paramString1 = null;
          paramInt1 = i;
          continue;
          paramInt1 = i;
          if (paramInt3 != 0)
          {
            paramString1 = paramString2;
            paramInt1 = i;
          }
        }
      }
    }
  }

  public void a()
  {
  }

  public void a(long paramLong, ADProfile paramADProfile, String paramString)
  {
    try
    {
      if (paramADProfile.f() == ADProfile.m.b)
        this.f.put(paramString + "_CPD", Long.valueOf(paramLong));
      while (true)
      {
        return;
        if (paramADProfile.f() == ADProfile.m.c)
          this.f.put(paramString + "_CPH", Long.valueOf(paramLong));
      }
    }
    finally
    {
    }
    throw paramADProfile;
  }

  public void a(List<ADProfile> paramList, com.intowow.sdk.model.i parami, b paramb)
  {
    while (true)
    {
      int i;
      String str;
      try
      {
        this.a = new LinkedList();
        this.b = new LinkedList();
        this.c = new HashMap();
        if ((paramList == null) || (parami == null));else
        {
          this.d = parami;
          this.g = paramb;
          paramList = paramList.iterator();
        }
        if (!paramList.hasNext())
          continue;
        parami = (ADProfile)paramList.next();
        paramb = parami.h();
        int j = paramb.length;
        i = 0;
        if (i >= j)
          continue;
        str = paramb[i];
        if (a(parami))
          this.b.add(new a(parami, str));
        else if (b(parami))
          this.a.add(parami);
      }
      finally
      {
      }
      if (!this.c.containsKey(str))
        this.c.put(str, new LinkedList());
      ((List)this.c.get(str)).add(parami);
      i += 1;
    }
  }

  public void b()
  {
  }

  private class a
  {
    public ADProfile a = null;
    public String b = null;

    public a(ADProfile paramString, String arg3)
    {
      this.a = paramString;
      Object localObject;
      this.b = localObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.f
 * JD-Core Version:    0.6.2
 */