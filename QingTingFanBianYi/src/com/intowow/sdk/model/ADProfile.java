package com.intowow.sdk.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.intowow.sdk.a.e;
import com.intowow.sdk.h.j;
import com.intowow.sdk.j.h;
import com.intowow.sdk.j.l;
import com.intowow.sdk.triggerresponse.TriggerResponse;
import com.intowow.sdk.triggerresponse.b;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class ADProfile
  implements Parcelable
{
  public static final Parcelable.Creator<ADProfile> CREATOR = new Parcelable.Creator()
  {
    public ADProfile a(Parcel paramAnonymousParcel)
    {
      return new ADProfile(paramAnonymousParcel);
    }

    public ADProfile[] a(int paramAnonymousInt)
    {
      return new ADProfile[paramAnonymousInt];
    }
  };
  private long a = 0L;
  private long b = 0L;
  private n c = n.a;
  private int d = 0;
  private int e = 0;
  private int f = -1;
  private int g = 0;
  private int h = 0;
  private int i = 0;
  private int j = 0;
  private JSONObject k = null;
  private boolean l = false;
  private boolean m = false;
  private m n = m.a;
  private i o = i.a;
  private String[] p = null;
  private l q = null;
  private f r = null;
  private b s = null;
  private h t = null;
  private e u = null;
  private String v = null;

  public ADProfile(Parcel paramParcel)
  {
    paramParcel = paramParcel.readString();
    try
    {
      this.k = new JSONObject(paramParcel);
      E();
      return;
    }
    catch (Exception paramParcel)
    {
    }
  }

  public ADProfile(JSONObject paramJSONObject)
  {
    this.k = paramJSONObject;
    E();
  }

  private void E()
  {
    boolean bool5 = true;
    int i2;
    int i1;
    try
    {
      this.f = this.k.getInt("adid");
      this.g = this.k.getInt("updated_time");
      this.n = m.a(this.k.getString("price_type"));
      this.o = i.a(this.k.getString("format"));
      JSONArray localJSONArray;
      if (this.k.has("placement_groups"))
      {
        localJSONArray = this.k.getJSONArray("placement_groups");
        i2 = localJSONArray.length();
        this.p = new String[i2];
        i1 = 0;
        break label578;
      }
      while (true)
      {
        this.m = this.k.getBoolean("global_capped");
        if (this.k.has("ad_version"))
          this.i = this.k.getInt("ad_version");
        if (this.k.has("creative_id"))
          this.j = this.k.getInt("creative_id");
        this.q = l.a(this.k.getJSONObject("impression_setting"));
        this.r = f.a(this.k.getJSONObject("delivery_setting"));
        this.s = b.a(this.o, this.k.getJSONObject("action_setting"));
        this.t = h.a(this.k.getJSONObject("effect_setting"));
        this.u = e.a(this.f, this.k.getJSONObject("assets"));
        this.h = 0;
        if (this.k.has("priority"))
          this.h = this.k.getInt("priority");
        if ((this.n != m.a) && (this.o != i.a))
          break;
        h.b("not vaild id[%d]PriceType[%s]Format[%s]", new Object[] { Integer.valueOf(this.f), this.n.toString(), this.o.toString() });
        return;
        label348: this.p[i1] = localJSONArray.getString(i1);
        i1 += 1;
        break label578;
        this.p = new String[1];
        this.p[0] = this.k.getString("placement_group");
      }
    }
    catch (Exception localException)
    {
      h.a(localException);
      return;
    }
    boolean bool1;
    label449: boolean bool2;
    label459: boolean bool3;
    label469: boolean bool4;
    if ((this.q == null) || (this.r == null) || (this.s == null) || (this.t == null) || (this.u == null))
    {
      i1 = this.f;
      if (this.q != null)
        break label586;
      bool1 = true;
      if (this.r != null)
        break label592;
      bool2 = true;
      if (this.s != null)
        break label598;
      bool3 = true;
      if (this.t != null)
        break label604;
      bool4 = true;
      label479: if (this.u != null)
        break label610;
    }
    while (true)
    {
      h.b("not vaild id[%d]imp[%s]Del[%s]Act[%s]Eff[%s]Ass[%s]", new Object[] { Integer.valueOf(i1), Boolean.valueOf(bool1), Boolean.valueOf(bool2), Boolean.valueOf(bool3), Boolean.valueOf(bool4), Boolean.valueOf(bool5) });
      return;
      this.a = 0L;
      this.d = 0;
      this.e = 0;
      this.b = 0L;
      this.c = n.a;
      this.l = true;
      return;
      label578: if (i1 < i2)
        break label348;
      break;
      label586: bool1 = false;
      break label449;
      label592: bool2 = false;
      break label459;
      label598: bool3 = false;
      break label469;
      label604: bool4 = false;
      break label479;
      label610: bool5 = false;
    }
  }

  public static ADProfile a(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new ADProfile(paramJSONObject);
      boolean bool = paramJSONObject.a();
      if (bool)
        return paramJSONObject;
    }
    catch (Exception paramJSONObject)
    {
    }
    return null;
  }

  public f A()
  {
    return this.r;
  }

  public int B()
  {
    int i1 = 13;
    switch (D()[this.n.ordinal()])
    {
    default:
      i1 = 0;
    case 4:
    case 5:
      return i1;
    case 2:
      return 15;
    case 3:
      return 14;
    case 6:
      return 12;
    case 7:
    }
    return 11;
  }

  public boolean C()
  {
    TriggerResponse localTriggerResponse = a("*", j.i);
    if (localTriggerResponse == null)
      return false;
    return localTriggerResponse.d();
  }

  public c a(d paramd)
  {
    if (!this.l)
      return null;
    return this.u.b(paramd);
  }

  public TriggerResponse a(String paramString, j paramj)
  {
    if ((!this.l) || (this.s == null))
      return null;
    return this.s.a(paramString, paramj);
  }

  public void a(long paramLong)
  {
    if (!this.l)
      return;
    this.a = paramLong;
  }

  public void a(n paramn)
  {
    if (!this.l)
      return;
    this.c = paramn;
  }

  public boolean a()
  {
    return this.l;
  }

  public boolean a(int paramInt)
  {
    if (!this.l)
      return false;
    return this.r.a(paramInt);
  }

  public JSONObject b()
  {
    if (!this.l)
      return null;
    return this.k;
  }

  public void b(int paramInt)
  {
    if (!this.l)
      return;
    this.d = paramInt;
  }

  public void b(long paramLong)
  {
    if (!this.l)
      return;
    this.b = paramLong;
  }

  public boolean b(d paramd)
  {
    if (!this.l)
      return false;
    return this.u.a(paramd);
  }

  public int c()
  {
    if (!this.l)
      return 0;
    return this.g;
  }

  public void c(int paramInt)
  {
    if (!this.l)
      return;
    this.e = paramInt;
  }

  public int d()
  {
    if (!this.l)
      return 0;
    return this.h;
  }

  public int describeContents()
  {
    return 0;
  }

  public int e()
  {
    if (!this.l)
      return -1;
    return this.f;
  }

  public m f()
  {
    if (!this.l)
      return m.a;
    return this.n;
  }

  public i g()
  {
    if (!this.l)
      return i.a;
    return this.o;
  }

  public String[] h()
  {
    if (!this.l)
      return null;
    return this.p;
  }

  public long i()
  {
    if (!this.l)
      return 0L;
    return this.r.a();
  }

  public long j()
  {
    if (!this.l)
      return 0L;
    return this.r.b();
  }

  public int k()
  {
    if (!this.l)
      return 0;
    return this.i;
  }

  public int l()
  {
    if (!this.l)
      return 0;
    return this.j;
  }

  public int m()
  {
    if (!this.l)
      return -1;
    return this.q.b();
  }

  public Map<Integer, ADProfile.l.a> n()
  {
    if (!this.l)
      return null;
    return this.q.a();
  }

  public boolean o()
  {
    if (!this.l)
      return true;
    return this.m;
  }

  public e p()
  {
    if (!this.l)
      return null;
    return this.u;
  }

  public h q()
  {
    if (!this.l)
      return null;
    return this.t;
  }

  public b r()
  {
    if (!this.l)
      return null;
    return this.s;
  }

  public n s()
  {
    if (!this.l)
      return n.a;
    return this.c;
  }

  public int t()
  {
    if (!this.l)
      return 0;
    return this.d;
  }

  public int u()
  {
    return this.e;
  }

  public int v()
  {
    if (!this.l)
      return 0;
    int i1 = this.d + 1;
    this.d = i1;
    return i1;
  }

  public int w()
  {
    if (!this.l)
      return 0;
    int i1 = this.e + 1;
    this.e = i1;
    return i1;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.k == null)
      this.k = new JSONObject();
    paramParcel.writeString(this.k.toString());
  }

  public long x()
  {
    if (!this.l)
      return 0L;
    return this.a;
  }

  public long y()
  {
    if (!this.l)
      return 0L;
    return this.b;
  }

  public l z()
  {
    return this.q;
  }

  public static enum a
  {
  }

  public static class b
  {
    private Map<String, ADProfile.p> a = new HashMap();

    public static b a(ADProfile.i parami, JSONObject paramJSONObject)
    {
      try
      {
        b localb = new b();
        if (paramJSONObject.has("*"))
        {
          ADProfile.p localp = ADProfile.p.a(paramJSONObject.getJSONObject("*"));
          a("*", localp, paramJSONObject.getJSONObject("*"));
          localb.a.put("*", localp);
        }
        while (true)
        {
          paramJSONObject = localb;
          if (!ADProfile.i.d(parami))
            break;
          parami = new ADProfile.p();
          a("video", parami, null);
          localb.a.put("video", parami);
          return localb;
          paramJSONObject = new ADProfile.p();
          a("*", paramJSONObject, null);
          localb.a.put("*", paramJSONObject);
        }
      }
      catch (Exception parami)
      {
        paramJSONObject = null;
      }
      return paramJSONObject;
    }

    private static void a(String paramString, ADProfile.p paramp, JSONObject paramJSONObject)
    {
      if (paramString.equals("*"))
      {
        if ((paramJSONObject == null) || (!paramJSONObject.has("impression")))
          paramp.a(j.h, b.a(e.a, j.h, false));
        paramp.a(j.l, b.a(e.a, j.l, true));
        paramp.a(j.m, b.a(e.a, j.m, true));
        paramp.a(j.n, b.a(e.a, j.n, true));
      }
    }

    public TriggerResponse a(String paramString, j paramj)
    {
      if (this.a.containsKey(paramString))
        return ((ADProfile.p)this.a.get(paramString)).a(paramj);
      return null;
    }
  }

  public static abstract class c
  {
    protected String a = null;
    protected String b = null;
    protected long c = 0L;
    protected int d = 0;
    protected int e = 0;
    protected long f = 0L;
    protected a g = a.a;
    protected String h = null;
    protected String i = null;

    protected c(a parama, String paramString1, long paramLong, String paramString2)
    {
      this.g = parama;
      this.h = paramString1;
      this.f = paramLong;
      this.i = paramString2;
    }

    public static c a(int paramInt, String paramString, JSONObject paramJSONObject)
    {
      try
      {
        a locala = a.a(paramJSONObject.getString("type"));
        switch (c()[locala.ordinal()])
        {
        case 2:
          return ADProfile.k.b(paramInt, paramString, paramJSONObject);
        case 3:
          return ADProfile.q.b(paramInt, paramString, paramJSONObject);
        case 4:
          paramString = ADProfile.o.b(paramInt, paramString, paramJSONObject);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
    }

    public static String a(String paramString1, String paramString2)
    {
      String str = paramString1;
      if (!l.a(paramString2))
      {
        str = paramString1;
        if (paramString2.length() > 10)
          str = paramString2.substring(0, 10);
      }
      return str;
    }

    public a a()
    {
      return this.g;
    }

    public String b()
    {
      return this.i;
    }

    public static enum a
    {
      public static a a(String paramString)
      {
        try
        {
          paramString = valueOf(paramString.toUpperCase());
          return paramString;
        }
        catch (Exception paramString)
        {
        }
        return a;
      }
    }
  }

  public static enum d
  {
    public static d a(String paramString)
    {
      try
      {
        paramString = valueOf(paramString.toUpperCase());
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return null;
    }
  }

  public static class e
  {
    private Map<ADProfile.d, ADProfile.c> a = new HashMap();

    public static e a(int paramInt, JSONObject paramJSONObject)
    {
      try
      {
        e locale = new e();
        Iterator localIterator = paramJSONObject.keys();
        while (true)
        {
          if (!localIterator.hasNext())
            return locale;
          String str = (String)localIterator.next();
          ADProfile.c localc = ADProfile.c.a(paramInt, str, paramJSONObject.getJSONObject(str));
          if (localc != null)
            locale.a.put(ADProfile.d.a(str), localc);
        }
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }

    public Set<ADProfile.d> a()
    {
      return this.a.keySet();
    }

    public boolean a(ADProfile.d paramd)
    {
      return this.a.containsKey(paramd);
    }

    public ADProfile.c b(ADProfile.d paramd)
    {
      return (ADProfile.c)this.a.get(paramd);
    }
  }

  public static class f
  {
    private long a = 0L;
    private long b = 0L;
    private String c = null;
    private int d = 0;
    private Set<Integer> e = null;
    private Set<Integer> f = null;

    public f(long paramLong1, long paramLong2, String paramString, int paramInt, Set<Integer> paramSet1, Set<Integer> paramSet2)
    {
      this.a = paramLong1;
      this.b = paramLong2;
      this.c = paramString;
      this.d = paramInt;
      this.e = paramSet1;
      this.f = paramSet2;
    }

    public static f a(JSONObject paramJSONObject)
    {
      int j = 0;
      while (true)
      {
        try
        {
          long l1 = paramJSONObject.getLong("start_date");
          long l2 = paramJSONObject.getLong("end_date");
          String str = paramJSONObject.getString("time_slots");
          int k = paramJSONObject.getInt("impressions");
          if (!paramJSONObject.has("geographic_constraints"))
            break label233;
          localObject = paramJSONObject.getJSONObject("geographic_constraints");
          if (!((JSONObject)localObject).has("whitelist"))
            break label228;
          JSONArray localJSONArray = ((JSONObject)localObject).getJSONArray("whitelist");
          if (localJSONArray.length() <= 0)
            break label228;
          paramJSONObject = new HashSet(localJSONArray.length());
          int i = 0;
          if (i >= localJSONArray.length())
          {
            if (!((JSONObject)localObject).has("blacklist"))
              break label223;
            localJSONArray = ((JSONObject)localObject).getJSONArray("blacklist");
            if (localJSONArray.length() <= 0)
              break label223;
            localObject = new HashSet(localJSONArray.length());
            i = j;
            if (i >= localJSONArray.length())
              return new f(l1 * 1000L, l2 * 1000L, str, k, paramJSONObject, (Set)localObject);
          }
          else
          {
            paramJSONObject.add(Integer.valueOf(localJSONArray.getInt(i)));
            i += 1;
            continue;
          }
          ((Set)localObject).add(Integer.valueOf(localJSONArray.getInt(i)));
          i += 1;
          continue;
        }
        catch (Exception paramJSONObject)
        {
          return null;
        }
        label223: Object localObject = null;
        continue;
        label228: paramJSONObject = null;
        continue;
        label233: localObject = null;
        paramJSONObject = null;
      }
    }

    public long a()
    {
      return this.a;
    }

    public boolean a(int paramInt)
    {
      if (this.c == null);
      while (this.c.charAt(paramInt) != '1')
        return false;
      return true;
    }

    public long b()
    {
      return this.b;
    }

    public Set<Integer> c()
    {
      return this.e;
    }

    public Set<Integer> d()
    {
      return this.f;
    }
  }

  public static enum g
  {
    public static g a(String paramString)
    {
      try
      {
        paramString = valueOf(paramString.toUpperCase());
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return a;
    }
  }

  public static class h
  {
    private SparseArray<Object> a = null;

    public static h a(JSONObject paramJSONObject)
    {
      try
      {
        h localh = new h();
        Iterator localIterator = paramJSONObject.keys();
        while (true)
        {
          if (!localIterator.hasNext())
            return localh;
          String str = (String)localIterator.next();
          ADProfile.g localg = ADProfile.g.a(str);
          if (localg != ADProfile.g.g)
            localh.a(localg, Double.valueOf(paramJSONObject.getDouble(str)));
          else
            localh.a(localg, a.a(paramJSONObject.getJSONObject(str)));
        }
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }

    private void a(ADProfile.g paramg, Object paramObject)
    {
      this.a.put(paramg.ordinal(), paramObject);
    }

    public a a()
    {
      Object localObject = this.a.get(ADProfile.g.g.ordinal());
      if ((localObject != null) && ((localObject instanceof a)))
        return (a)localObject;
      return null;
    }

    public boolean a(ADProfile.g paramg)
    {
      return this.a.get(paramg.ordinal()) != null;
    }

    public static class a
    {
      private int a = 0;
      private int b = 0;
      private int c = 0;
      private int d = 0;

      static a a(JSONObject paramJSONObject)
      {
        a locala = new a();
        locala.a = paramJSONObject.optInt("x", 0);
        locala.b = paramJSONObject.optInt("y", 0);
        locala.c = paramJSONObject.optInt("width", 0);
        locala.d = paramJSONObject.optInt("height", 0);
        return locala;
      }

      public int a()
      {
        return this.a;
      }

      public int b()
      {
        return this.b;
      }

      public int c()
      {
        return this.c;
      }

      public int d()
      {
        return this.d;
      }
    }
  }

  public static enum i
  {
    public static i a(String paramString)
    {
      try
      {
        paramString = valueOf(paramString.toUpperCase());
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return a;
    }

    public static boolean a(i parami)
    {
      return parami.toString().startsWith("SPLASH2");
    }

    public static boolean b(i parami)
    {
      return (parami == g) || (parami == f);
    }

    public static boolean c(i parami)
    {
      switch (a()[parami.ordinal()])
      {
      case 3:
      case 5:
      case 7:
      default:
        return false;
      case 2:
      case 4:
      case 6:
      case 8:
      }
      return true;
    }

    public static boolean d(i parami)
    {
      switch (a()[parami.ordinal()])
      {
      default:
        return false;
      case 2:
      case 3:
      case 4:
      case 5:
      }
      return true;
    }
  }

  public static enum j
  {
  }

  public static class k extends ADProfile.c
  {
    private k(String paramString1, String paramString2, int paramInt1, int paramInt2, long paramLong1, String paramString3, long paramLong2, String paramString4)
    {
      super(paramString1, paramLong2, paramString4);
      this.a = paramString2;
      this.d = paramInt1;
      this.e = paramInt2;
      this.c = paramLong1;
      this.b = paramString3;
    }

    public static k b(int paramInt, String paramString, JSONObject paramJSONObject)
    {
      try
      {
        String str1 = paramJSONObject.getString("url");
        long l1 = paramJSONObject.getLong("size");
        long l2 = paramJSONObject.getLong("updated_time");
        int i = paramJSONObject.getInt("width");
        int j = paramJSONObject.getInt("height");
        String str2 = paramJSONObject.optString("md5", null);
        String str3 = a(paramString, str2);
        String str4 = l.b(str1);
        if (str4.equals(""));
        for (paramJSONObject = ""; ; paramJSONObject = ".")
          return new k(paramString, str1, i, j, l1, String.format("%d_%s%s%s", new Object[] { Integer.valueOf(paramInt), str3, paramJSONObject, str4 }), l2 * 1000L, str2);
      }
      catch (Exception paramString)
      {
      }
      return null;
    }

    public String d()
    {
      return this.a;
    }

    public String e()
    {
      return this.b;
    }

    public long f()
    {
      return this.c;
    }
  }

  public static class l
  {
    private int a = -1;
    private double b = 0.0D;
    private Map<Integer, a> c = null;

    public l(int paramInt, double paramDouble, Map<Integer, a> paramMap)
    {
      this.a = paramInt;
      this.b = paramDouble;
      this.c = paramMap;
    }

    @SuppressLint({"UseSparseArrays"})
    public static l a(JSONObject paramJSONObject)
    {
      int j = 0;
      int i = 0;
      while (true)
      {
        try
        {
          if (paramJSONObject.has("freq_caps"))
          {
            JSONArray localJSONArray1 = paramJSONObject.getJSONArray("freq_caps");
            if (localJSONArray1.length() > 0)
            {
              localHashMap = new HashMap(localJSONArray1.length());
              if (i >= localJSONArray1.length())
              {
                if (!paramJSONObject.has("max_impressions"))
                  break label233;
                i = paramJSONObject.getInt("max_impressions");
                if (!paramJSONObject.has("min_ctr"))
                  break label228;
                d = paramJSONObject.getDouble("min_ctr");
                return new l(i, d, localHashMap);
              }
              JSONArray localJSONArray2 = localJSONArray1.getJSONArray(i);
              a locala = new a(localJSONArray2.getInt(0), localJSONArray2.getInt(1));
              localHashMap.put(Integer.valueOf(localJSONArray2.getInt(0)), locala);
              i += 1;
              continue;
            }
          }
          HashMap localHashMap = new HashMap(1);
          if (!paramJSONObject.has("freq_cap"))
            break label239;
          i = paramJSONObject.getInt("freq_cap");
          if (paramJSONObject.has("sliding_window"))
            j = paramJSONObject.getInt("sliding_window");
          localHashMap.put(Integer.valueOf(j), new a(j, i));
          continue;
        }
        catch (Exception paramJSONObject)
        {
          return null;
        }
        label228: double d = 0.0D;
        continue;
        label233: i = -1;
        continue;
        label239: i = 0;
      }
    }

    public Map<Integer, a> a()
    {
      return this.c;
    }

    public boolean a(long paramLong)
    {
      Iterator localIterator = this.c.keySet().iterator();
      Integer localInteger;
      do
      {
        if (!localIterator.hasNext())
          return false;
        localInteger = (Integer)localIterator.next();
      }
      while (!((a)this.c.get(localInteger)).b(paramLong));
      return true;
    }

    public int b()
    {
      return this.a;
    }

    public double c()
    {
      return this.b;
    }

    public static class a
    {
      private int a = 0;
      private int b = 0;
      private int c = 0;
      private long d = 0L;
      private long e = 0L;

      public a(int paramInt1, int paramInt2)
      {
        this.a = paramInt1;
        this.b = paramInt2;
        this.e = (this.a * 3600000L);
      }

      public int a()
      {
        return this.a;
      }

      public void a(int paramInt)
      {
        this.c = paramInt;
      }

      public void a(long paramLong)
      {
        this.d = paramLong;
      }

      public int b()
      {
        int i = this.c + 1;
        this.c = i;
        return i;
      }

      public boolean b(long paramLong)
      {
        return (this.d + this.e > paramLong) && (this.c >= this.b);
      }

      public long c()
      {
        return this.d;
      }

      public long d()
      {
        return this.e;
      }
    }
  }

  public static enum m
  {
    public static m a(String paramString)
    {
      try
      {
        paramString = valueOf(paramString.toUpperCase());
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return a;
    }
  }

  public static enum n
  {
  }

  public static class o extends ADProfile.c
  {
    private String j = null;

    private o(String paramString1, String paramString2, long paramLong)
    {
      super(paramString1, paramLong, null);
      this.j = paramString2;
    }

    public static o b(int paramInt, String paramString, JSONObject paramJSONObject)
    {
      try
      {
        paramString = new o(paramString, paramJSONObject.getString("text"), 0L);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return null;
    }

    public String d()
    {
      return this.j;
    }
  }

  public static class p
  {
    private Map<j, TriggerResponse> a = new HashMap();

    public static p a(JSONObject paramJSONObject)
    {
      try
      {
        p localp = new p();
        Iterator localIterator = paramJSONObject.keys();
        while (true)
        {
          if (!localIterator.hasNext())
            return localp;
          Object localObject = (String)localIterator.next();
          j localj = j.a((String)localObject);
          if (localj != j.a)
          {
            localObject = paramJSONObject.getJSONObject((String)localObject);
            localObject = b.a(e.a, (JSONObject)localObject);
            if (localObject != null)
              localp.a.put(localj, localObject);
          }
        }
      }
      catch (Exception paramJSONObject)
      {
      }
      return null;
    }

    public TriggerResponse a(j paramj)
    {
      if (this.a.containsKey(paramj))
        return (TriggerResponse)this.a.get(paramj);
      return null;
    }

    public void a(j paramj, TriggerResponse paramTriggerResponse)
    {
      this.a.put(paramj, paramTriggerResponse);
    }
  }

  public static class q extends ADProfile.c
  {
    private int j = 0;

    private q(String paramString1, String paramString2, int paramInt1, int paramInt2, long paramLong1, int paramInt3, String paramString3, long paramLong2, String paramString4)
    {
      super(paramString1, paramLong2, paramString4);
      this.a = paramString2;
      this.d = paramInt1;
      this.e = paramInt2;
      this.c = paramLong1;
      this.j = paramInt3;
      this.b = paramString3;
    }

    public static q b(int paramInt, String paramString, JSONObject paramJSONObject)
    {
      try
      {
        String str1 = paramJSONObject.getString("url");
        long l1 = paramJSONObject.getLong("size");
        long l2 = paramJSONObject.getLong("updated_time");
        int i = paramJSONObject.getInt("width");
        int k = paramJSONObject.getInt("height");
        int m = paramJSONObject.getInt("duration");
        String str2 = paramJSONObject.optString("md5", null);
        String str3 = a(paramString, str2);
        String str4 = l.b(str1);
        if (str4.equals(""));
        for (paramJSONObject = ""; ; paramJSONObject = ".")
          return new q(paramString, str1, i, k, l1, m, String.format("%d_%s%s%s", new Object[] { Integer.valueOf(paramInt), str3, paramJSONObject, str4 }), l2 * 1000L, str2);
      }
      catch (Exception paramString)
      {
      }
      return null;
    }

    public String d()
    {
      return this.a;
    }

    public String e()
    {
      return this.b;
    }

    public int f()
    {
      return this.j;
    }

    public long g()
    {
      return this.c;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.model.ADProfile
 * JD-Core Version:    0.6.2
 */