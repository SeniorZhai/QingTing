package com.intowow.sdk.f;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.intowow.sdk.a.f;
import com.intowow.sdk.b.h.a;
import com.intowow.sdk.b.h.b;
import com.intowow.sdk.i.c.c.r;
import com.intowow.sdk.j.m;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.i;
import com.intowow.sdk.model.ADProfile.l.a;
import com.intowow.sdk.model.ADProfile.n;
import com.intowow.sdk.model.e;
import com.intowow.sdk.model.g;
import com.intowow.sdk.model.l;
import com.intowow.sdk.model.l.a;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class b
  implements h.a
{
  private Context a = null;
  private com.intowow.sdk.c.a b = null;
  private boolean c = false;
  private com.intowow.sdk.b.h d = null;
  private com.intowow.sdk.a.j e = null;
  private com.intowow.sdk.model.i f = null;
  private com.intowow.sdk.model.b g = null;
  private l h = null;
  private e i = null;
  private ConcurrentHashMap<String, List<ADProfile>> j = null;
  private ConcurrentHashMap<String, Long> k = null;
  private List<ADProfile> l = null;
  private SparseArray<ADProfile> m = null;
  private boolean n = false;
  private String o = null;
  private final h.b[] p = { h.b.d, h.b.e };

  public b(Context paramContext, com.intowow.sdk.b.h paramh)
  {
    a(paramContext, paramh);
  }

  private void Q()
  {
    this.j.clear();
    this.m.clear();
    this.l.clear();
    com.intowow.sdk.j.h.b("mADList.getProfiles() size " + this.g.b().size(), new Object[0]);
    Iterator localIterator;
    if ((this.g != null) && (this.g.b() != null))
    {
      localIterator = this.g.b().iterator();
      if (localIterator.hasNext());
    }
    else
    {
      localIterator = this.b.e().iterator();
      label109: if (localIterator.hasNext())
        break label325;
      localIterator = this.b.d().iterator();
    }
    while (true)
    {
      Object localObject3;
      if (!localIterator.hasNext())
      {
        return;
        localObject1 = (ADProfile)localIterator.next();
        if (!a((ADProfile)localObject1))
        {
          com.intowow.sdk.j.h.b("Format not OK " + ((ADProfile)localObject1).g().toString(), new Object[0]);
          break;
        }
        com.intowow.sdk.j.h.b("ad list add " + ((ADProfile)localObject1).e(), new Object[0]);
        this.l.add(localObject1);
        localObject2 = ((ADProfile)localObject1).h();
        int i2 = localObject2.length;
        int i1 = 0;
        while (true)
        {
          if (i1 >= i2)
          {
            this.m.put(((ADProfile)localObject1).e(), localObject1);
            break;
          }
          localObject3 = localObject2[i1];
          if (!this.j.containsKey(localObject3))
            this.j.put(localObject3, new LinkedList());
          ((List)this.j.get(localObject3)).add(localObject1);
          i1 += 1;
        }
        label325: localObject1 = (com.intowow.sdk.model.c)localIterator.next();
        localObject2 = (ADProfile)this.m.get(((com.intowow.sdk.model.c)localObject1).a());
        if (localObject2 == null)
          break label109;
        ((ADProfile)localObject2).a(((com.intowow.sdk.model.c)localObject1).b());
        ((ADProfile)localObject2).a(ADProfile.n.values()[localObject1.d()]);
        ((ADProfile)localObject2).b(((com.intowow.sdk.model.c)localObject1).e());
        ((ADProfile)localObject2).c(((com.intowow.sdk.model.c)localObject1).f());
        break label109;
      }
      Object localObject1 = (com.intowow.sdk.model.a)localIterator.next();
      Object localObject2 = (ADProfile)this.m.get(((com.intowow.sdk.model.a)localObject1).a());
      if (localObject2 != null)
      {
        localObject2 = ((ADProfile)localObject2).n();
        localObject3 = ((Map)localObject2).keySet().iterator();
        while (((Iterator)localObject3).hasNext())
        {
          ADProfile.l.a locala = (ADProfile.l.a)((Map)localObject2).get((Integer)((Iterator)localObject3).next());
          if (((com.intowow.sdk.model.a)localObject1).b() == locala.a())
          {
            locala.a(((com.intowow.sdk.model.a)localObject1).c());
            locala.a(((com.intowow.sdk.model.a)localObject1).d());
          }
        }
      }
    }
  }

  private void R()
  {
    String str = h(".ph_cfg");
    if (str != null);
    try
    {
      this.f = com.intowow.sdk.model.i.a(new JSONObject(str));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void S()
  {
    String str = h(".geographic_info");
    if (str != null);
    try
    {
      this.i = e.a(new JSONObject(str));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void T()
  {
    if ((this.e != null) && (this.e.i != null))
    {
      if (this.c)
        com.intowow.sdk.j.c.a(this.e.i.b);
    }
    else
      return;
    int i1 = com.intowow.sdk.j.c.c();
    com.intowow.sdk.j.c.a(this.e.i.a(i1));
  }

  private void U()
  {
    String str = h(".serving_cfg");
    if (str != null);
    try
    {
      this.e = com.intowow.sdk.a.j.a(new JSONObject(str));
      T();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void V()
  {
    Object localObject = h(".padlist");
    if (localObject != null);
    try
    {
      this.g = com.intowow.sdk.model.b.a(new JSONObject((String)localObject));
      Q();
      this.n = true;
      k(".padlist");
      label43: if (this.n)
        if ((this.g != null) && (this.g.b() != null) && (this.g.b().size() > 0))
        {
          localObject = (ADProfile)this.g.b().get(0);
          com.intowow.sdk.j.j.a(this.a, this, (ADProfile)localObject);
        }
      do
      {
        return;
        localObject = h(".adlist");
      }
      while (localObject == null);
      try
      {
        this.g = com.intowow.sdk.model.b.a(new JSONObject((String)localObject));
        Q();
        return;
      }
      catch (Exception localException1)
      {
      }
    }
    catch (Exception localException2)
    {
      break label43;
    }
  }

  private SparseArray<Long> W()
  {
    SparseArray localSparseArray = new SparseArray();
    Iterator localIterator;
    if (this.g != null)
      localIterator = this.g.b().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localSparseArray;
      ADProfile localADProfile = (ADProfile)localIterator.next();
      localSparseArray.put(localADProfile.e(), Long.valueOf(localADProfile.y()));
    }
  }

  private void X()
  {
    int i1 = (int)Math.floor((System.currentTimeMillis() - 259200000L) / 1000L);
    String str = h(".req_history");
    if (str != null);
    try
    {
      this.h = l.a(new JSONObject(str), i1);
      return;
      this.h = new l();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  private void Y()
  {
    try
    {
      Iterator localIterator;
      if (this.l != null)
        localIterator = this.l.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        ((ADProfile)localIterator.next()).b(0L);
      }
    }
    finally
    {
    }
  }

  private void Z()
  {
    String str = com.intowow.sdk.j.k.a(this.a).b();
    Object localObject1 = new File(str);
    int i2;
    int i1;
    if (((File)localObject1).exists())
    {
      localObject1 = ((File)localObject1).list();
      i2 = localObject1.length;
      i1 = 0;
    }
    while (true)
    {
      if (i1 >= i2)
        return;
      Object localObject2 = localObject1[i1];
      localObject2 = new File(str + (String)localObject2);
      if (((File)localObject2).exists())
        ((File)localObject2).delete();
      i1 += 1;
    }
  }

  private void a(Context paramContext, com.intowow.sdk.b.h paramh)
  {
    this.a = paramContext;
    this.d = paramh;
    this.c = com.intowow.sdk.j.c.b();
    this.b = new com.intowow.sdk.c.a(this.a);
    this.l = new LinkedList();
    this.j = new ConcurrentHashMap();
    this.m = new SparseArray();
    this.o = com.intowow.sdk.j.i.b(this.a);
    if (a(0, null) == null)
      b(0, m.a());
    if (a(6, "N").equals("Y"))
    {
      b(1, "N");
      b(5, "0");
      b(6, "N");
      Z();
    }
    U();
    R();
    S();
    V();
    X();
    this.k = this.b.b();
  }

  private void a(SparseArray<Long> paramSparseArray)
  {
    if ((this.l == null) || (this.l.size() == 0) || (paramSparseArray == null) || (paramSparseArray.size() == 0));
    while (true)
    {
      return;
      Iterator localIterator = this.l.iterator();
      while (localIterator.hasNext())
      {
        ADProfile localADProfile = (ADProfile)localIterator.next();
        Long localLong = (Long)paramSparseArray.get(localADProfile.e());
        if (localLong != null)
          localADProfile.b(localLong.longValue());
      }
    }
  }

  private void a(com.intowow.sdk.a.j paramj, ConcurrentHashMap<String, Long> paramConcurrentHashMap, com.intowow.sdk.c.a parama)
  {
    if ((paramConcurrentHashMap == null) || (paramConcurrentHashMap.size() == 0));
    while (paramj.c == null)
      return;
    Iterator localIterator = paramConcurrentHashMap.keySet().iterator();
    label30: String str2;
    Object localObject;
    String str1;
    if (localIterator.hasNext())
    {
      str2 = (String)localIterator.next();
      localObject = null;
      if (str2.indexOf("-") == -1)
        break label135;
      localObject = str2.split("-");
      str1 = localObject[0];
      localObject = localObject[1];
    }
    while (true)
    {
      if (paramj.b(str1) == 0L)
        a(str1, parama, paramConcurrentHashMap);
      if ((localObject == null) || (paramj.c((String)localObject) != 0L))
        break label30;
      a(str2, parama, paramConcurrentHashMap);
      break label30;
      break;
      label135: str1 = str2;
    }
  }

  private void a(String paramString, com.intowow.sdk.c.a parama, ConcurrentHashMap<String, Long> paramConcurrentHashMap)
  {
    parama.a(paramString);
    paramConcurrentHashMap.remove(paramString);
  }

  private boolean a(ADProfile paramADProfile)
  {
    if ((!com.intowow.sdk.j.c.a()) && (ADProfile.i.d(paramADProfile.g())));
    while ((this.e != null) && (this.e.a(paramADProfile.g())))
      return false;
    return r.a().get(paramADProfile.g().ordinal());
  }

  private boolean a(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = new BufferedWriter(new FileWriter(com.intowow.sdk.j.k.a(this.a).c() + paramString1, false));
      paramString1.write(paramString2);
      paramString1.flush();
      paramString1.close();
      return true;
    }
    catch (Exception paramString1)
    {
    }
    return false;
  }

  private void aa()
  {
    try
    {
      this.e = null;
      this.f = null;
      this.g = null;
      this.h = null;
      this.j = null;
      this.l = null;
      this.m = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void b(long paramLong)
  {
    try
    {
      this.b.b(9, String.valueOf(paramLong));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private long j(String paramString)
  {
    try
    {
      paramString = new File(com.intowow.sdk.j.k.a(this.a).c() + paramString);
      if (paramString.exists())
      {
        long l1 = paramString.lastModified();
        return l1;
      }
    }
    catch (Exception paramString)
    {
    }
    return 0L;
  }

  private void k(String paramString)
  {
    paramString = new File(com.intowow.sdk.j.k.a(this.a).c() + paramString);
    if (paramString.exists())
      paramString.delete();
  }

  public boolean A()
  {
    boolean bool2 = false;
    try
    {
      boolean bool1 = this.n;
      if (bool1)
        bool1 = bool2;
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          }
          while (this.e == null);
          bool1 = bool2;
        }
        while (!this.e.b());
        bool1 = bool2;
      }
      while (this.e.c() == null);
      long l2 = j(".adlist");
      if (this.e == null);
      for (long l1 = 0L; ; l1 = this.e.e())
      {
        bool1 = bool2;
        if (Math.abs(System.currentTimeMillis() - l2) <= l1)
          break;
        bool1 = true;
        break;
      }
    }
    finally
    {
    }
  }

  public boolean B()
  {
    boolean bool2 = false;
    try
    {
      boolean bool1 = this.n;
      if (bool1)
        bool1 = bool2;
      do
      {
        do
        {
          do
          {
            return bool1;
            bool1 = bool2;
          }
          while (this.e == null);
          bool1 = bool2;
        }
        while (!this.e.b());
        bool1 = bool2;
      }
      while (this.e.n() == null);
      long l2 = j(".geographic_info");
      if (this.e == null);
      for (long l1 = 0L; ; l1 = this.e.m())
      {
        bool1 = bool2;
        if (Math.abs(System.currentTimeMillis() - l2) <= l1)
          break;
        bool1 = true;
        break;
      }
    }
    finally
    {
    }
  }

  public String C()
  {
    try
    {
      Object localObject1 = this.e;
      if (localObject1 == null);
      for (localObject1 = null; ; localObject1 = this.e.c())
        return localObject1;
    }
    finally
    {
    }
  }

  public String D()
  {
    try
    {
      Object localObject1 = this.e;
      if (localObject1 == null);
      for (localObject1 = null; ; localObject1 = this.e.j())
        return localObject1;
    }
    finally
    {
    }
  }

  public String E()
  {
    try
    {
      Object localObject1 = this.e;
      if (localObject1 == null);
      for (localObject1 = null; ; localObject1 = this.e.n())
        return localObject1;
    }
    finally
    {
    }
  }

  public com.intowow.sdk.a.j F()
  {
    try
    {
      com.intowow.sdk.a.j localj = this.e;
      return localj;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void G()
  {
    try
    {
      Object localObject1 = this.h;
      if (localObject1 == null);
      while (true)
      {
        return;
        try
        {
          localObject1 = l.a(this.h);
          if (localObject1 != null)
            a(".req_history", ((JSONObject)localObject1).toString());
        }
        catch (Exception localException)
        {
        }
      }
    }
    finally
    {
    }
  }

  public l H()
  {
    try
    {
      l locall = this.h;
      if (locall == null);
      for (locall = null; ; locall = this.h)
        return locall;
    }
    finally
    {
    }
  }

  public void I()
  {
    try
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            long l = System.currentTimeMillis();
            SparseBooleanArray localSparseBooleanArray = new SparseBooleanArray();
            Object localObject1 = new SparseBooleanArray();
            Object localObject2 = b.a(b.this).e().iterator();
            int j;
            int i;
            while (true)
            {
              if (!((Iterator)localObject2).hasNext())
              {
                if (com.intowow.sdk.j.k.e())
                {
                  localObject2 = new File(com.intowow.sdk.j.k.a(b.d(b.this)).a());
                  if (((File)localObject2).exists())
                  {
                    localObject2 = ((File)localObject2).listFiles();
                    j = localObject2.length;
                    i = 0;
                    break;
                  }
                }
                j = localSparseBooleanArray.size();
                i = 0;
                break label355;
              }
              localc = (com.intowow.sdk.model.c)((Iterator)localObject2).next();
              if ((localc.d() == ADProfile.n.c.ordinal()) && (l - localc.b() > 3600000L))
                localSparseBooleanArray.put(localc.a(), true);
              else if ((!b.b(b.this)) && (b.c(b.this).get(localc.a()) == null))
                localSparseBooleanArray.put(localc.a(), true);
              else
                ((SparseBooleanArray)localObject1).put(localc.a(), true);
            }
            localc = localObject2[i];
            arrayOfString = localc.getName().split("_");
            k = arrayOfString.length;
            label345: label355: 
            while (i < j)
              while (true)
              {
                try
                {
                  com.intowow.sdk.model.c localc;
                  String[] arrayOfString;
                  int k;
                  if (!((SparseBooleanArray)localObject1).get(Integer.parseInt(arrayOfString[0])))
                    localc.delete();
                  i += 1;
                  break label345;
                  b.a(b.this).b(localSparseBooleanArray.keyAt(i));
                  localObject1 = new Bundle();
                  ((Bundle)localObject1).putInt("type", h.b.s.ordinal());
                  ((Bundle)localObject1).putInt("adid", localSparseBooleanArray.keyAt(i));
                  b.e(b.this).a((Bundle)localObject1);
                  i += 1;
                }
                catch (Exception localException2)
                {
                  continue;
                }
                if (i >= j)
                  break;
              }
            return;
          }
          catch (Exception localException1)
          {
          }
        }
      }).start();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void J()
  {
    try
    {
      if (this.b != null)
        this.b.a();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void K()
  {
    try
    {
      k(".serving_cfg");
      k(".ph_cfg");
      k(".adlist");
      k(".req_history");
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void L()
  {
    try
    {
      aa();
      a(this.a, this.d);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String M()
  {
    return this.o;
  }

  public boolean N()
  {
    return this.n;
  }

  public Context O()
  {
    return this.a;
  }

  public com.intowow.sdk.model.b P()
  {
    return this.g;
  }

  public String a(int paramInt, String paramString)
  {
    return this.b.a(paramInt, paramString);
  }

  public List<h.b> a()
  {
    return Arrays.asList(this.p);
  }

  public void a(int paramInt)
  {
    try
    {
      this.b.b(15, paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(int paramInt1, int paramInt2)
  {
    this.b.c(paramInt1, paramInt2);
  }

  public void a(long paramLong)
  {
    try
    {
      this.b.b(7, String.valueOf(paramLong));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(Bundle paramBundle)
  {
    paramBundle = h.b.values()[paramBundle.getInt("type")];
    if (paramBundle == h.b.d)
      I();
    while (paramBundle != h.b.e)
      return;
    Y();
  }

  public void a(ADProfile paramADProfile, ADProfile.n paramn)
  {
    try
    {
      if (this.n)
        paramADProfile.a(paramn);
      while (true)
      {
        return;
        paramADProfile.a(paramn);
        this.b.b(paramADProfile.e(), paramn.ordinal());
      }
    }
    finally
    {
    }
    throw paramADProfile;
  }

  public void a(com.intowow.sdk.model.k paramk)
  {
    try
    {
      this.b.b(11, paramk.toString());
      return;
    }
    finally
    {
      paramk = finally;
    }
    throw paramk;
  }

  public void a(String paramString)
  {
    try
    {
      this.b.b(14, paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void a(String paramString, long paramLong)
  {
    try
    {
      b(1, paramString);
      b(5, String.valueOf(paramLong));
      if (paramString.equals("Y"))
        b(6, "N");
      paramString = new Bundle();
      paramString.putInt("type", h.b.i.ordinal());
      this.d.a(paramString);
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public void a(JSONObject paramJSONObject)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      localJSONArray.put(paramJSONObject);
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("count", 1);
      localJSONObject.put("items", localJSONArray);
      if (a(".padlist", localJSONObject.toString()))
      {
        a(com.intowow.sdk.model.k.c);
        int i1 = paramJSONObject.getInt("adid");
        paramJSONObject = (ADProfile)com.intowow.sdk.model.b.a(localJSONObject).b().get(0);
        this.b.a(i1, paramJSONObject.n().keySet());
      }
      while (true)
      {
        label107: return;
        a(com.intowow.sdk.model.k.g);
      }
    }
    catch (Exception paramJSONObject)
    {
      break label107;
    }
    finally
    {
    }
    throw paramJSONObject;
  }

  public void a(boolean paramBoolean)
  {
    try
    {
      com.intowow.sdk.c.a locala = this.b;
      if (paramBoolean);
      for (String str = "Y"; ; str = "N")
      {
        locala.b(2, str);
        return;
      }
    }
    finally
    {
    }
  }

  public boolean a(Map<String, Integer> paramMap)
  {
    try
    {
      Object localObject = this.h;
      boolean bool;
      if ((localObject == null) || (paramMap == null))
      {
        bool = false;
        return bool;
      }
      long l1 = System.currentTimeMillis();
      int i1 = (int)((l1 - l1 % 86400000L) / 1000L);
      localObject = paramMap.keySet().iterator();
      while (true)
      {
        if (!((Iterator)localObject).hasNext())
        {
          bool = true;
          break;
        }
        String str = (String)((Iterator)localObject).next();
        int i2 = ((Integer)paramMap.get(str)).intValue();
        this.h.a(str, i1, i2);
      }
    }
    finally
    {
    }
    throw paramMap;
  }

  public SparseArray<String> b()
  {
    return this.b.c();
  }

  public ADProfile b(int paramInt)
  {
    try
    {
      ADProfile localADProfile = (ADProfile)this.m.get(paramInt);
      return localADProfile;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void b(int paramInt, String paramString)
  {
    this.b.b(paramInt, paramString);
  }

  public void b(String paramString)
  {
    try
    {
      this.b.b(13, paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public void b(String paramString, long paramLong)
  {
    this.b.a(paramString, paramLong);
    this.k.put(paramString, Long.valueOf(paramLong));
  }

  public void b(boolean paramBoolean)
  {
    try
    {
      com.intowow.sdk.c.a locala = this.b;
      if (paramBoolean);
      for (String str = "Y"; ; str = "N")
      {
        locala.b(12, str);
        return;
      }
    }
    finally
    {
    }
  }

  public boolean b(JSONObject paramJSONObject)
  {
    try
    {
      e locale = e.a(paramJSONObject);
      if ((locale != null) && (a(".geographic_info", paramJSONObject.toString())))
      {
        this.i = locale;
        bool = true;
        return bool;
      }
    }
    catch (Exception paramJSONObject)
    {
      while (true)
        boolean bool = false;
    }
    finally
    {
    }
    throw paramJSONObject;
  }

  public List<com.intowow.sdk.model.c> c()
  {
    return this.b.e();
  }

  public void c(int paramInt)
  {
    try
    {
      boolean bool = this.n;
      if (bool);
      while (true)
      {
        return;
        ADProfile localADProfile = (ADProfile)this.m.get(paramInt);
        if (localADProfile != null)
        {
          int i1 = localADProfile.w();
          this.b.a(paramInt, i1);
        }
      }
    }
    finally
    {
    }
  }

  public void c(String paramString)
  {
    try
    {
      this.b.b(4, paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  // ERROR //
  public void c(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic 271	com/intowow/sdk/model/i:a	(Lorg/json/JSONObject;)Lcom/intowow/sdk/model/i;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +74 -> 82
    //   11: aload_0
    //   12: ldc_w 260
    //   15: aload_1
    //   16: invokevirtual 553	org/json/JSONObject:toString	()Ljava/lang/String;
    //   19: invokespecial 555	com/intowow/sdk/f/b:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   22: ifeq +60 -> 82
    //   25: aload_0
    //   26: aload_2
    //   27: putfield 59	com/intowow/sdk/f/b:f	Lcom/intowow/sdk/model/i;
    //   30: getstatic 689	com/intowow/sdk/a/e:a	Z
    //   33: ifeq +13 -> 46
    //   36: ldc_w 691
    //   39: iconst_0
    //   40: anewarray 4	java/lang/Object
    //   43: invokestatic 130	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   46: aload_0
    //   47: getfield 55	com/intowow/sdk/f/b:d	Lcom/intowow/sdk/b/h;
    //   50: ifnull +32 -> 82
    //   53: new 602	android/os/Bundle
    //   56: dup
    //   57: invokespecial 619	android/os/Bundle:<init>	()V
    //   60: astore_1
    //   61: aload_1
    //   62: ldc_w 600
    //   65: getstatic 693	com/intowow/sdk/b/h$b:g	Lcom/intowow/sdk/b/h$b;
    //   68: invokevirtual 622	com/intowow/sdk/b/h$b:ordinal	()I
    //   71: invokevirtual 626	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   74: aload_0
    //   75: getfield 55	com/intowow/sdk/f/b:d	Lcom/intowow/sdk/b/h;
    //   78: aload_1
    //   79: invokevirtual 630	com/intowow/sdk/b/h:a	(Landroid/os/Bundle;)V
    //   82: aload_0
    //   83: monitorexit
    //   84: return
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    //   90: astore_1
    //   91: goto -9 -> 82
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	85	finally
    //   11	46	85	finally
    //   46	82	85	finally
    //   2	7	90	java/lang/Exception
    //   11	46	90	java/lang/Exception
    //   46	82	90	java/lang/Exception
  }

  public List<com.intowow.sdk.model.a> d()
  {
    return this.b.d();
  }

  public void d(int paramInt)
  {
    while (true)
    {
      long l1;
      Map localMap;
      ArrayList localArrayList;
      Iterator localIterator;
      try
      {
        boolean bool = this.n;
        if (bool)
          return;
        ADProfile localADProfile = (ADProfile)this.m.get(paramInt);
        if (localADProfile == null)
          continue;
        l1 = System.currentTimeMillis();
        localMap = localADProfile.n();
        localArrayList = new ArrayList();
        localIterator = localMap.keySet().iterator();
        if (!localIterator.hasNext())
        {
          localADProfile.a(l1);
          i1 = localADProfile.v();
          this.b.a(paramInt, l1, i1, localArrayList);
          continue;
        }
      }
      finally
      {
      }
      ADProfile.l.a locala = (ADProfile.l.a)localMap.get((Integer)localIterator.next());
      long l2 = locala.c();
      long l3 = locala.d();
      if ((l3 != 0L) && (l2 + l3 < l1))
      {
        locala.a(0);
        locala.a(l1);
      }
      int i1 = locala.b();
      localArrayList.add(new com.intowow.sdk.model.a(paramInt, locala.a(), locala.c(), i1));
    }
  }

  public void d(String paramString)
  {
    try
    {
      this.b.b(8, paramString);
      return;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  // ERROR //
  public boolean d(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_1
    //   6: invokestatic 304	com/intowow/sdk/a/j:a	(Lorg/json/JSONObject;)Lcom/intowow/sdk/a/j;
    //   9: astore_2
    //   10: iload 4
    //   12: istore_3
    //   13: aload_2
    //   14: ifnull +47 -> 61
    //   17: aload_0
    //   18: getfield 57	com/intowow/sdk/f/b:e	Lcom/intowow/sdk/a/j;
    //   21: ifnull +21 -> 42
    //   24: iload 4
    //   26: istore_3
    //   27: aload_2
    //   28: getfield 712	com/intowow/sdk/a/j:a	J
    //   31: aload_0
    //   32: getfield 57	com/intowow/sdk/f/b:e	Lcom/intowow/sdk/a/j;
    //   35: getfield 712	com/intowow/sdk/a/j:a	J
    //   38: lcmp
    //   39: ifeq +22 -> 61
    //   42: aload_0
    //   43: ldc_w 301
    //   46: aload_1
    //   47: invokevirtual 553	org/json/JSONObject:toString	()Ljava/lang/String;
    //   50: invokespecial 555	com/intowow/sdk/f/b:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   53: istore_3
    //   54: iload_3
    //   55: ifne +10 -> 65
    //   58: iload 4
    //   60: istore_3
    //   61: aload_0
    //   62: monitorexit
    //   63: iload_3
    //   64: ireturn
    //   65: getstatic 689	com/intowow/sdk/a/e:a	Z
    //   68: ifeq +50 -> 118
    //   71: new 103	java/lang/StringBuilder
    //   74: dup
    //   75: ldc_w 714
    //   78: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   81: new 716	java/text/SimpleDateFormat
    //   84: dup
    //   85: ldc_w 718
    //   88: invokespecial 719	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   91: new 721	java/util/Date
    //   94: dup
    //   95: aload_2
    //   96: getfield 712	com/intowow/sdk/a/j:a	J
    //   99: invokespecial 723	java/util/Date:<init>	(J)V
    //   102: invokevirtual 727	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   105: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: iconst_0
    //   112: anewarray 4	java/lang/Object
    //   115: invokestatic 130	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   118: aload_0
    //   119: aload_2
    //   120: putfield 57	com/intowow/sdk/f/b:e	Lcom/intowow/sdk/a/j;
    //   123: aload_0
    //   124: invokespecial 306	com/intowow/sdk/f/b:T	()V
    //   127: aload_0
    //   128: aload_0
    //   129: getfield 57	com/intowow/sdk/f/b:e	Lcom/intowow/sdk/a/j;
    //   132: aload_0
    //   133: getfield 69	com/intowow/sdk/f/b:k	Ljava/util/concurrent/ConcurrentHashMap;
    //   136: aload_0
    //   137: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   140: invokespecial 729	com/intowow/sdk/f/b:a	(Lcom/intowow/sdk/a/j;Ljava/util/concurrent/ConcurrentHashMap;Lcom/intowow/sdk/c/a;)V
    //   143: aload_0
    //   144: getfield 49	com/intowow/sdk/f/b:a	Landroid/content/Context;
    //   147: invokestatic 734	com/intowow/sdk/b/e:a	(Landroid/content/Context;)Lcom/intowow/sdk/b/e;
    //   150: aload_0
    //   151: getfield 57	com/intowow/sdk/f/b:e	Lcom/intowow/sdk/a/j;
    //   154: invokevirtual 737	com/intowow/sdk/b/e:a	(Lcom/intowow/sdk/a/j;)V
    //   157: aload_0
    //   158: getfield 55	com/intowow/sdk/f/b:d	Lcom/intowow/sdk/b/h;
    //   161: ifnull +32 -> 193
    //   164: new 602	android/os/Bundle
    //   167: dup
    //   168: invokespecial 619	android/os/Bundle:<init>	()V
    //   171: astore_1
    //   172: aload_1
    //   173: ldc_w 600
    //   176: getstatic 739	com/intowow/sdk/b/h$b:f	Lcom/intowow/sdk/b/h$b;
    //   179: invokevirtual 622	com/intowow/sdk/b/h$b:ordinal	()I
    //   182: invokevirtual 626	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   185: aload_0
    //   186: getfield 55	com/intowow/sdk/f/b:d	Lcom/intowow/sdk/b/h;
    //   189: aload_1
    //   190: invokevirtual 630	com/intowow/sdk/b/h:a	(Landroid/os/Bundle;)V
    //   193: iconst_1
    //   194: istore_3
    //   195: goto -134 -> 61
    //   198: astore_1
    //   199: aload_0
    //   200: monitorexit
    //   201: aload_1
    //   202: athrow
    //   203: astore_1
    //   204: iload 4
    //   206: istore_3
    //   207: goto -146 -> 61
    //
    // Exception table:
    //   from	to	target	type
    //   5	10	198	finally
    //   17	24	198	finally
    //   27	42	198	finally
    //   42	54	198	finally
    //   65	118	198	finally
    //   118	193	198	finally
    //   5	10	203	java/lang/Exception
    //   17	24	203	java/lang/Exception
    //   27	42	203	java/lang/Exception
    //   42	54	203	java/lang/Exception
    //   65	118	203	java/lang/Exception
    //   118	193	203	java/lang/Exception
  }

  public g e(String paramString)
  {
    try
    {
      if (this.f != null)
      {
        paramString = this.f.a(paramString);
        return paramString;
      }
      paramString = null;
    }
    finally
    {
    }
  }

  public List<ADProfile> e()
  {
    return this.l;
  }

  // ERROR //
  public void e(JSONObject paramJSONObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 75	com/intowow/sdk/f/b:n	Z
    //   6: istore 10
    //   8: iload 10
    //   10: ifeq +6 -> 16
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: aload_0
    //   17: invokespecial 745	com/intowow/sdk/f/b:W	()Landroid/util/SparseArray;
    //   20: astore_2
    //   21: aload_1
    //   22: invokestatic 312	com/intowow/sdk/model/b:a	(Lorg/json/JSONObject;)Lcom/intowow/sdk/model/b;
    //   25: astore_3
    //   26: aload_0
    //   27: aload_3
    //   28: invokevirtual 746	com/intowow/sdk/model/b:c	()J
    //   31: invokestatic 344	java/lang/System:currentTimeMillis	()J
    //   34: lsub
    //   35: invokespecial 747	com/intowow/sdk/f/b:b	(J)V
    //   38: getstatic 689	com/intowow/sdk/a/e:a	Z
    //   41: ifeq +67 -> 108
    //   44: aload_3
    //   45: ifnull +63 -> 108
    //   48: new 103	java/lang/StringBuilder
    //   51: dup
    //   52: ldc_w 749
    //   55: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   58: aload_3
    //   59: invokevirtual 750	com/intowow/sdk/model/b:a	()I
    //   62: invokevirtual 121	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   65: ldc_w 752
    //   68: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: new 716	java/text/SimpleDateFormat
    //   74: dup
    //   75: ldc_w 718
    //   78: invokespecial 719	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   81: new 721	java/util/Date
    //   84: dup
    //   85: aload_3
    //   86: invokevirtual 746	com/intowow/sdk/model/b:c	()J
    //   89: invokespecial 723	java/util/Date:<init>	(J)V
    //   92: invokevirtual 727	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   95: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: iconst_0
    //   102: anewarray 4	java/lang/Object
    //   105: invokestatic 130	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   108: aload_0
    //   109: getfield 49	com/intowow/sdk/f/b:a	Landroid/content/Context;
    //   112: invokestatic 734	com/intowow/sdk/b/e:a	(Landroid/content/Context;)Lcom/intowow/sdk/b/e;
    //   115: bipush 9
    //   117: aload_0
    //   118: invokevirtual 755	com/intowow/sdk/f/b:r	()J
    //   121: invokevirtual 758	com/intowow/sdk/b/e:a	(IJ)V
    //   124: aload_3
    //   125: ifnull -112 -> 13
    //   128: aload_0
    //   129: ldc_w 324
    //   132: aload_1
    //   133: invokevirtual 553	org/json/JSONObject:toString	()Ljava/lang/String;
    //   136: invokespecial 555	com/intowow/sdk/f/b:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   139: ifeq -126 -> 13
    //   142: aload_0
    //   143: getfield 61	com/intowow/sdk/f/b:g	Lcom/intowow/sdk/model/b;
    //   146: ifnonnull +116 -> 262
    //   149: aload_3
    //   150: invokevirtual 113	com/intowow/sdk/model/b:b	()Ljava/util/List;
    //   153: invokeinterface 134 1 0
    //   158: astore_1
    //   159: aload_1
    //   160: invokeinterface 140 1 0
    //   165: ifne +56 -> 221
    //   168: aload_0
    //   169: aload_3
    //   170: putfield 61	com/intowow/sdk/f/b:g	Lcom/intowow/sdk/model/b;
    //   173: aload_0
    //   174: invokespecial 314	com/intowow/sdk/f/b:Q	()V
    //   177: aload_0
    //   178: aload_2
    //   179: invokespecial 760	com/intowow/sdk/f/b:a	(Landroid/util/SparseArray;)V
    //   182: aload_0
    //   183: getfield 55	com/intowow/sdk/f/b:d	Lcom/intowow/sdk/b/h;
    //   186: ifnull -173 -> 13
    //   189: new 602	android/os/Bundle
    //   192: dup
    //   193: invokespecial 619	android/os/Bundle:<init>	()V
    //   196: astore_1
    //   197: aload_1
    //   198: ldc_w 600
    //   201: getstatic 762	com/intowow/sdk/b/h$b:h	Lcom/intowow/sdk/b/h$b;
    //   204: invokevirtual 622	com/intowow/sdk/b/h$b:ordinal	()I
    //   207: invokevirtual 626	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   210: aload_0
    //   211: getfield 55	com/intowow/sdk/f/b:d	Lcom/intowow/sdk/b/h;
    //   214: aload_1
    //   215: invokevirtual 630	com/intowow/sdk/b/h:a	(Landroid/os/Bundle;)V
    //   218: goto -205 -> 13
    //   221: aload_1
    //   222: invokeinterface 150 1 0
    //   227: checkcast 152	com/intowow/sdk/model/ADProfile
    //   230: astore 4
    //   232: aload_0
    //   233: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   236: aload 4
    //   238: invokevirtual 170	com/intowow/sdk/model/ADProfile:e	()I
    //   241: aload 4
    //   243: invokevirtual 232	com/intowow/sdk/model/ADProfile:n	()Ljava/util/Map;
    //   246: invokeinterface 238 1 0
    //   251: invokevirtual 659	com/intowow/sdk/c/a:a	(ILjava/util/Set;)V
    //   254: goto -95 -> 159
    //   257: astore_1
    //   258: aload_0
    //   259: monitorexit
    //   260: aload_1
    //   261: athrow
    //   262: new 488	android/util/SparseBooleanArray
    //   265: dup
    //   266: invokespecial 763	android/util/SparseBooleanArray:<init>	()V
    //   269: astore 5
    //   271: aload_0
    //   272: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   275: invokevirtual 144	com/intowow/sdk/c/a:e	()Ljava/util/List;
    //   278: invokeinterface 134 1 0
    //   283: astore_1
    //   284: aload_1
    //   285: invokeinterface 140 1 0
    //   290: ifne +123 -> 413
    //   293: new 97	android/util/SparseArray
    //   296: dup
    //   297: invokespecial 327	android/util/SparseArray:<init>	()V
    //   300: astore_1
    //   301: new 97	android/util/SparseArray
    //   304: dup
    //   305: invokespecial 327	android/util/SparseArray:<init>	()V
    //   308: astore 4
    //   310: aload_3
    //   311: invokevirtual 113	com/intowow/sdk/model/b:b	()Ljava/util/List;
    //   314: invokeinterface 134 1 0
    //   319: astore 6
    //   321: aload 6
    //   323: invokeinterface 140 1 0
    //   328: ifne +106 -> 434
    //   331: aload_0
    //   332: getfield 61	com/intowow/sdk/f/b:g	Lcom/intowow/sdk/model/b;
    //   335: invokevirtual 113	com/intowow/sdk/model/b:b	()Ljava/util/List;
    //   338: invokeinterface 134 1 0
    //   343: astore 5
    //   345: aload 5
    //   347: invokeinterface 140 1 0
    //   352: ifne +155 -> 507
    //   355: aload_1
    //   356: invokevirtual 436	android/util/SparseArray:size	()I
    //   359: istore 9
    //   361: iconst_0
    //   362: istore 8
    //   364: iload 8
    //   366: iload 9
    //   368: if_icmpge -200 -> 168
    //   371: aload_1
    //   372: iload 8
    //   374: invokevirtual 766	android/util/SparseArray:valueAt	(I)Ljava/lang/Object;
    //   377: checkcast 152	com/intowow/sdk/model/ADProfile
    //   380: astore 4
    //   382: aload_0
    //   383: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   386: aload 4
    //   388: invokevirtual 170	com/intowow/sdk/model/ADProfile:e	()I
    //   391: aload 4
    //   393: invokevirtual 232	com/intowow/sdk/model/ADProfile:n	()Ljava/util/Map;
    //   396: invokeinterface 238 1 0
    //   401: invokevirtual 659	com/intowow/sdk/c/a:a	(ILjava/util/Set;)V
    //   404: iload 8
    //   406: iconst_1
    //   407: iadd
    //   408: istore 8
    //   410: goto -46 -> 364
    //   413: aload 5
    //   415: aload_1
    //   416: invokeinterface 150 1 0
    //   421: checkcast 196	com/intowow/sdk/model/c
    //   424: invokevirtual 198	com/intowow/sdk/model/c:a	()I
    //   427: iconst_1
    //   428: invokevirtual 769	android/util/SparseBooleanArray:put	(IZ)V
    //   431: goto -147 -> 284
    //   434: aload 6
    //   436: invokeinterface 150 1 0
    //   441: checkcast 152	com/intowow/sdk/model/ADProfile
    //   444: astore 7
    //   446: aload 5
    //   448: aload 7
    //   450: invokevirtual 170	com/intowow/sdk/model/ADProfile:e	()I
    //   453: invokevirtual 491	android/util/SparseBooleanArray:get	(I)Z
    //   456: ifne +25 -> 481
    //   459: aload_0
    //   460: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   463: aload 7
    //   465: invokevirtual 170	com/intowow/sdk/model/ADProfile:e	()I
    //   468: aload 7
    //   470: invokevirtual 232	com/intowow/sdk/model/ADProfile:n	()Ljava/util/Map;
    //   473: invokeinterface 238 1 0
    //   478: invokevirtual 659	com/intowow/sdk/c/a:a	(ILjava/util/Set;)V
    //   481: aload 4
    //   483: aload 7
    //   485: invokevirtual 170	com/intowow/sdk/model/ADProfile:e	()I
    //   488: aload 7
    //   490: invokevirtual 181	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   493: aload_1
    //   494: aload 7
    //   496: invokevirtual 170	com/intowow/sdk/model/ADProfile:e	()I
    //   499: aload 7
    //   501: invokevirtual 181	android/util/SparseArray:put	(ILjava/lang/Object;)V
    //   504: goto -183 -> 321
    //   507: aload 5
    //   509: invokeinterface 150 1 0
    //   514: checkcast 152	com/intowow/sdk/model/ADProfile
    //   517: astore 6
    //   519: aload 6
    //   521: invokevirtual 170	com/intowow/sdk/model/ADProfile:e	()I
    //   524: istore 9
    //   526: aload 4
    //   528: iload 9
    //   530: invokevirtual 201	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   533: ifnonnull +33 -> 566
    //   536: aload_0
    //   537: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   540: iload 9
    //   542: getstatic 772	com/intowow/sdk/model/ADProfile$n:c	Lcom/intowow/sdk/model/ADProfile$n;
    //   545: invokevirtual 611	com/intowow/sdk/model/ADProfile$n:ordinal	()I
    //   548: invokevirtual 613	com/intowow/sdk/c/a:b	(II)V
    //   551: aload_3
    //   552: invokevirtual 113	com/intowow/sdk/model/b:b	()Ljava/util/List;
    //   555: aload 6
    //   557: invokeinterface 174 2 0
    //   562: pop
    //   563: goto -218 -> 345
    //   566: aload 6
    //   568: invokevirtual 776	com/intowow/sdk/model/ADProfile:s	()Lcom/intowow/sdk/model/ADProfile$n;
    //   571: getstatic 772	com/intowow/sdk/model/ADProfile$n:c	Lcom/intowow/sdk/model/ADProfile$n;
    //   574: if_acmpeq -229 -> 345
    //   577: aload_1
    //   578: iload 9
    //   580: invokevirtual 778	android/util/SparseArray:remove	(I)V
    //   583: aload 4
    //   585: iload 9
    //   587: invokevirtual 201	android/util/SparseArray:get	(I)Ljava/lang/Object;
    //   590: checkcast 152	com/intowow/sdk/model/ADProfile
    //   593: astore 7
    //   595: aload 6
    //   597: invokevirtual 779	com/intowow/sdk/model/ADProfile:c	()I
    //   600: aload 7
    //   602: invokevirtual 779	com/intowow/sdk/model/ADProfile:c	()I
    //   605: if_icmplt +100 -> 705
    //   608: aload 6
    //   610: invokevirtual 781	com/intowow/sdk/model/ADProfile:l	()I
    //   613: aload 7
    //   615: invokevirtual 781	com/intowow/sdk/model/ADProfile:l	()I
    //   618: if_icmpne +87 -> 705
    //   621: aload_0
    //   622: getfield 49	com/intowow/sdk/f/b:a	Landroid/content/Context;
    //   625: aload 6
    //   627: invokevirtual 784	com/intowow/sdk/model/ADProfile:p	()Lcom/intowow/sdk/model/ADProfile$e;
    //   630: invokestatic 789	com/intowow/sdk/j/b:a	(Landroid/content/Context;Lcom/intowow/sdk/model/ADProfile$e;)Z
    //   633: ifeq +82 -> 715
    //   636: aload_0
    //   637: getfield 49	com/intowow/sdk/f/b:a	Landroid/content/Context;
    //   640: aload 7
    //   642: invokevirtual 784	com/intowow/sdk/model/ADProfile:p	()Lcom/intowow/sdk/model/ADProfile$e;
    //   645: invokestatic 789	com/intowow/sdk/j/b:a	(Landroid/content/Context;Lcom/intowow/sdk/model/ADProfile$e;)Z
    //   648: ifeq +57 -> 705
    //   651: goto +64 -> 715
    //   654: iload 8
    //   656: ifeq -311 -> 345
    //   659: aload_0
    //   660: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   663: iload 9
    //   665: getstatic 791	com/intowow/sdk/model/ADProfile$n:a	Lcom/intowow/sdk/model/ADProfile$n;
    //   668: invokevirtual 611	com/intowow/sdk/model/ADProfile$n:ordinal	()I
    //   671: invokevirtual 613	com/intowow/sdk/c/a:b	(II)V
    //   674: aload_0
    //   675: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   678: iload 9
    //   680: aload 7
    //   682: invokevirtual 232	com/intowow/sdk/model/ADProfile:n	()Ljava/util/Map;
    //   685: invokeinterface 238 1 0
    //   690: invokevirtual 793	com/intowow/sdk/c/a:b	(ILjava/util/Set;)V
    //   693: aload_0
    //   694: getfield 51	com/intowow/sdk/f/b:b	Lcom/intowow/sdk/c/a;
    //   697: iload 9
    //   699: invokevirtual 794	com/intowow/sdk/c/a:a	(I)V
    //   702: goto -357 -> 345
    //   705: iconst_1
    //   706: istore 8
    //   708: goto -54 -> 654
    //   711: astore_1
    //   712: goto -699 -> 13
    //   715: iconst_0
    //   716: istore 8
    //   718: goto -64 -> 654
    //
    // Exception table:
    //   from	to	target	type
    //   2	8	257	finally
    //   16	44	257	finally
    //   48	108	257	finally
    //   108	124	257	finally
    //   128	159	257	finally
    //   159	168	257	finally
    //   168	218	257	finally
    //   221	254	257	finally
    //   262	284	257	finally
    //   284	321	257	finally
    //   321	345	257	finally
    //   345	361	257	finally
    //   371	404	257	finally
    //   413	431	257	finally
    //   434	481	257	finally
    //   481	504	257	finally
    //   507	563	257	finally
    //   566	651	257	finally
    //   659	702	257	finally
    //   16	44	711	java/lang/Exception
    //   48	108	711	java/lang/Exception
    //   108	124	711	java/lang/Exception
    //   128	159	711	java/lang/Exception
    //   159	168	711	java/lang/Exception
    //   168	218	711	java/lang/Exception
    //   221	254	711	java/lang/Exception
    //   262	284	711	java/lang/Exception
    //   284	321	711	java/lang/Exception
    //   321	345	711	java/lang/Exception
    //   345	361	711	java/lang/Exception
    //   371	404	711	java/lang/Exception
    //   413	431	711	java/lang/Exception
    //   434	481	711	java/lang/Exception
    //   481	504	711	java/lang/Exception
    //   507	563	711	java/lang/Exception
    //   566	651	711	java/lang/Exception
    //   659	702	711	java/lang/Exception
  }

  public g f(String paramString)
  {
    try
    {
      if (this.f != null)
      {
        paramString = this.f.b(paramString);
        return paramString;
      }
      paramString = null;
    }
    finally
    {
    }
  }

  public com.intowow.sdk.model.i f()
  {
    return this.f;
  }

  public int g(String paramString)
  {
    int i2 = 0;
    try
    {
      l locall = this.h;
      int i1;
      if (locall == null)
        i1 = i2;
      while (true)
      {
        return i1;
        paramString = this.h.a(paramString);
        i1 = i2;
        if (paramString != null)
        {
          i1 = i2;
          if (paramString != null)
            i1 = paramString.a();
        }
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public e g()
  {
    return this.i;
  }

  public int h()
  {
    String str = l();
    if (str != null)
      return Integer.parseInt(str);
    if (this.i != null)
      return this.i.b();
    return -1;
  }

  // ERROR //
  public String h(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: new 374	java/io/File
    //   7: dup
    //   8: new 103	java/lang/StringBuilder
    //   11: dup
    //   12: aload_0
    //   13: getfield 49	com/intowow/sdk/f/b:a	Landroid/content/Context;
    //   16: invokestatic 370	com/intowow/sdk/j/k:a	(Landroid/content/Context;)Lcom/intowow/sdk/j/k;
    //   19: invokevirtual 498	com/intowow/sdk/j/k:c	()Ljava/lang/String;
    //   22: invokestatic 386	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   25: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   28: aload_1
    //   29: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokespecial 375	java/io/File:<init>	(Ljava/lang/String;)V
    //   38: astore_1
    //   39: aload_1
    //   40: invokevirtual 378	java/io/File:exists	()Z
    //   43: ifeq +181 -> 224
    //   46: new 103	java/lang/StringBuilder
    //   49: dup
    //   50: ldc_w 812
    //   53: invokespecial 108	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   56: astore 4
    //   58: new 814	java/io/InputStreamReader
    //   61: dup
    //   62: new 816	java/io/FileInputStream
    //   65: dup
    //   66: aload_1
    //   67: invokespecial 819	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   70: ldc_w 821
    //   73: invokespecial 824	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   76: astore_1
    //   77: new 826	java/io/BufferedReader
    //   80: dup
    //   81: aload_1
    //   82: invokespecial 829	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   85: astore_2
    //   86: aload_2
    //   87: invokevirtual 832	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   90: astore 5
    //   92: aload 5
    //   94: ifnonnull +42 -> 136
    //   97: aload 4
    //   99: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   102: astore 4
    //   104: aload 4
    //   106: astore_3
    //   107: aload_1
    //   108: astore 4
    //   110: aload_3
    //   111: astore_1
    //   112: aload 4
    //   114: ifnull +8 -> 122
    //   117: aload 4
    //   119: invokevirtual 833	java/io/InputStreamReader:close	()V
    //   122: aload_1
    //   123: astore_3
    //   124: aload_2
    //   125: ifnull +9 -> 134
    //   128: aload_2
    //   129: invokevirtual 834	java/io/BufferedReader:close	()V
    //   132: aload_1
    //   133: astore_3
    //   134: aload_3
    //   135: areturn
    //   136: aload 4
    //   138: aload 5
    //   140: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: pop
    //   144: goto -58 -> 86
    //   147: astore 4
    //   149: aload_1
    //   150: ifnull +7 -> 157
    //   153: aload_1
    //   154: invokevirtual 833	java/io/InputStreamReader:close	()V
    //   157: aload_2
    //   158: ifnull -24 -> 134
    //   161: aload_2
    //   162: invokevirtual 834	java/io/BufferedReader:close	()V
    //   165: aconst_null
    //   166: areturn
    //   167: astore_1
    //   168: aconst_null
    //   169: areturn
    //   170: astore_3
    //   171: aconst_null
    //   172: astore_1
    //   173: aconst_null
    //   174: astore_2
    //   175: aload_1
    //   176: ifnull +7 -> 183
    //   179: aload_1
    //   180: invokevirtual 833	java/io/InputStreamReader:close	()V
    //   183: aload_2
    //   184: ifnull +7 -> 191
    //   187: aload_2
    //   188: invokevirtual 834	java/io/BufferedReader:close	()V
    //   191: aload_3
    //   192: athrow
    //   193: astore_2
    //   194: aload_1
    //   195: areturn
    //   196: astore_1
    //   197: goto -6 -> 191
    //   200: astore_3
    //   201: aconst_null
    //   202: astore_2
    //   203: goto -28 -> 175
    //   206: astore_3
    //   207: goto -32 -> 175
    //   210: astore_1
    //   211: aconst_null
    //   212: astore_2
    //   213: aconst_null
    //   214: astore_1
    //   215: goto -66 -> 149
    //   218: astore_2
    //   219: aconst_null
    //   220: astore_2
    //   221: goto -72 -> 149
    //   224: aconst_null
    //   225: astore_3
    //   226: aconst_null
    //   227: astore 4
    //   229: aload_2
    //   230: astore_1
    //   231: aload_3
    //   232: astore_2
    //   233: goto -121 -> 112
    //
    // Exception table:
    //   from	to	target	type
    //   86	92	147	java/lang/Exception
    //   97	104	147	java/lang/Exception
    //   136	144	147	java/lang/Exception
    //   153	157	167	java/lang/Exception
    //   161	165	167	java/lang/Exception
    //   4	77	170	finally
    //   117	122	193	java/lang/Exception
    //   128	132	193	java/lang/Exception
    //   179	183	196	java/lang/Exception
    //   187	191	196	java/lang/Exception
    //   77	86	200	finally
    //   86	92	206	finally
    //   97	104	206	finally
    //   136	144	206	finally
    //   4	77	210	java/lang/Exception
    //   77	86	218	java/lang/Exception
  }

  public long i(String paramString)
  {
    if (this.k.containsKey(paramString))
      return ((Long)this.k.get(paramString)).longValue();
    return 0L;
  }

  public com.intowow.sdk.a.i i()
  {
    if ((this.e == null) || (this.e.f == null))
      return null;
    return this.e.f;
  }

  public boolean j()
  {
    try
    {
      boolean bool = this.b.a(2, "N").equals("Y");
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String k()
  {
    try
    {
      String str = this.b.a(14, null);
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String l()
  {
    try
    {
      String str = this.b.a(15, null);
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String m()
  {
    try
    {
      String str = this.b.a(13, null);
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean n()
  {
    try
    {
      boolean bool = this.b.a(12, "N").equals("Y");
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String o()
  {
    try
    {
      String str = this.b.a(4, "0.0.0");
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String p()
  {
    try
    {
      String str = this.b.a(8, "0");
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long q()
  {
    try
    {
      long l1 = Long.parseLong(this.b.a(7, "0"));
      return l1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long r()
  {
    try
    {
      long l1 = Long.parseLong(this.b.a(9, "0"));
      return l1;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public long s()
  {
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = r();
      return l1 + l2;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String t()
  {
    try
    {
      String str = this.b.a(11, com.intowow.sdk.model.k.a.toString());
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void u()
  {
    try
    {
      this.n = false;
      k(".padlist");
      a(com.intowow.sdk.model.k.a);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean v()
  {
    boolean bool1 = true;
    try
    {
      String str = a(1, null);
      if (str != null)
      {
        boolean bool2 = str.equals("Y");
        if (!bool2);
      }
      while (true)
      {
        return bool1;
        bool1 = false;
      }
    }
    finally
    {
    }
  }

  public long w()
  {
    return Long.parseLong(a(5, "0"));
  }

  public void x()
  {
    try
    {
      b(6, "Y");
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean y()
  {
    try
    {
      long l2 = j(".serving_cfg");
      long l1;
      if (this.e == null)
      {
        l1 = 3600000L;
        l2 = Math.abs(System.currentTimeMillis() - l2);
        if (l2 <= l1)
          break label59;
      }
      label59: for (boolean bool = true; ; bool = false)
      {
        return bool;
        l1 = this.e.b;
        break;
      }
    }
    finally
    {
    }
  }

  public boolean z()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    try
    {
      if (this.e != null)
      {
        String str = this.e.j();
        if (str == null)
          bool1 = bool2;
      }
      else
      {
        return bool1;
      }
      if (this.f == null);
      for (long l1 = 0L; ; l1 = this.f.a())
      {
        bool1 = bool2;
        if (l1 >= this.e.i())
          break;
        bool1 = true;
        break;
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.f.b
 * JD-Core Version:    0.6.2
 */