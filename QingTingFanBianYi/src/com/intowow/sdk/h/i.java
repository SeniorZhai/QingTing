package com.intowow.sdk.h;

import android.content.Context;
import android.os.Handler;
import com.intowow.sdk.b.k;
import com.intowow.sdk.b.k.a;
import com.intowow.sdk.b.k.a.a;
import com.intowow.sdk.d.a;
import com.intowow.sdk.j.d.a;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class i
{
  private k a = null;
  private a b = null;
  private String c = null;
  private int d = 6;
  private int e = 0;
  private boolean f = false;
  private long g = 60000L;
  private long h = 3000L;
  private b i = null;
  private ExecutorService j = null;
  private List<String> k = null;
  private Map<String, a> l = null;
  private String m = null;
  private Runnable n = new Runnable()
  {
    public void run()
    {
      i.a(i.this);
      i.b(i.this).execute(i.c(i.this));
    }
  };
  private Runnable o = new Runnable()
  {
    public void run()
    {
      try
      {
        i.d(i.this).a();
        label12: if (i.e(i.this) != null)
        {
          k.a locala = i.e(i.this).c();
          if ((locala != null) && (locala.b() == k.a.a.b))
          {
            i.a(i.this, i.e(i.this), i.f(i.this), i.g(i.this));
            return;
          }
        }
        i.a(i.this, false);
        return;
      }
      catch (Exception localException)
      {
        break label12;
      }
    }
  };

  public i(k paramk)
  {
    this.a = paramk;
    this.k = new LinkedList();
    this.l = new HashMap();
  }

  private d a(j paramj, e parame)
  {
    f();
    int i1 = this.a.k();
    return new d(this.c, this.d, paramj, this.e, this.a.f().r(), parame, this.m, i1);
  }

  private d a(String paramString, e parame)
  {
    f();
    int i1 = this.a.k();
    return new d(this.c, this.d, paramString, this.e, this.a.f().r(), parame, this.m, i1);
  }

  private void a(d paramd)
  {
    String str = com.intowow.sdk.j.i.e(this.a.b());
    if (!com.intowow.sdk.j.l.a(str))
      paramd.a(f.l, str);
    str = com.intowow.sdk.j.d.f(this.a.b());
    if (!com.intowow.sdk.j.l.a(str))
      paramd.a(f.m, str);
    str = com.intowow.sdk.j.d.e(this.a.b());
    if (!com.intowow.sdk.j.l.a(str))
      paramd.a(f.n, str);
    str = com.intowow.sdk.j.d.d(this.a.b());
    if (!com.intowow.sdk.j.l.a(str))
      paramd.a(f.p, str);
    str = com.intowow.sdk.j.d.a();
    if ((!com.intowow.sdk.j.l.a(str)) && (!"unknown".equals(str)))
      paramd.a(f.q, str);
    str = com.intowow.sdk.j.d.g(this.a.b());
    if (!com.intowow.sdk.j.l.a(str))
      paramd.a(f.r, str.replaceAll(":", ""));
  }

  private void a(String paramString)
  {
    if (com.intowow.sdk.a.e.a)
      com.intowow.sdk.j.h.a(String.format("[TRACKER] %s", new Object[] { paramString }), new Object[0]);
  }

  private boolean a(k paramk, Runnable paramRunnable, long paramLong)
  {
    if ((paramk != null) && (paramk.d() != null) && (paramRunnable != null) && (paramLong >= 0L))
    {
      paramk.d().postDelayed(paramRunnable, paramLong);
      return true;
    }
    return false;
  }

  private void b(String paramString)
  {
    while (true)
    {
      try
      {
        if (this.b != null)
          this.b.a(paramString);
        if (this.a.f() != null)
        {
          boolean bool = this.a.f().N();
          if (bool)
            return;
        }
        if (this.i == null)
        {
          this.k.add(paramString);
          continue;
        }
      }
      finally
      {
      }
      try
      {
        this.i.a(paramString);
        if (!this.f)
        {
          this.f = true;
          a(this.a, this.n, this.g);
        }
      }
      catch (Exception paramString)
      {
      }
    }
  }

  private void e()
  {
    try
    {
      Iterator localIterator = this.k.iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          if (this.k.size() > 0)
          {
            this.f = true;
            a(this.a, this.n, this.g);
          }
          this.k = new LinkedList();
          return;
        }
        String str = (String)localIterator.next();
        try
        {
          this.i.a(str);
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

  private void f()
  {
    if (this.c == null)
      this.c = this.a.f().a(0, null);
    if (this.m == null)
      this.m = this.a.f().M();
  }

  private void g()
  {
    if (this.l.size() == 0)
      return;
    try
    {
      JSONObject localJSONObject = new JSONObject();
      Iterator localIterator = this.l.keySet().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          this.l.clear();
          localJSONObject = a(j.o, e.d).a(f.B, localJSONObject).a();
          b(localJSONObject.toString());
          a(localJSONObject.toString());
          return;
        }
        String str = (String)localIterator.next();
        localJSONObject.put(str, ((a)this.l.get(str)).a());
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void a()
  {
    if (this.i != null);
    do
    {
      return;
      localObject = this.a.f().F();
    }
    while ((localObject == null) || (((com.intowow.sdk.a.j)localObject).e == null));
    Object localObject = ((com.intowow.sdk.a.j)localObject).e;
    this.g = ((com.intowow.sdk.a.l)localObject).b();
    this.h = ((com.intowow.sdk.a.l)localObject).d();
    this.i = h.a(this.a.b().getDir("I2WEVENTS", 0), (com.intowow.sdk.a.l)localObject);
    this.j = Executors.newSingleThreadExecutor();
    e();
  }

  public void a(int paramInt)
  {
    this.e = paramInt;
  }

  public void a(a parama)
  {
    this.b = parama;
  }

  public void a(j paramj, JSONObject paramJSONObject)
  {
    try
    {
      paramj = a(paramj, e.c).a(paramJSONObject).a();
      b(paramj.toString());
      a(paramj.toString());
      return;
    }
    catch (JSONException paramj)
    {
    }
  }

  public void a(d.a parama, int paramInt1, int paramInt2)
  {
    try
    {
      d locald = a(j.f, e.b);
      locald.a(f.i, parama.toString());
      locald.a(f.j, paramInt1);
      locald.a(f.k, paramInt2);
      parama = locald.a();
      b(parama.toString());
      a(parama.toString());
      return;
    }
    catch (JSONException parama)
    {
      com.intowow.sdk.j.h.a(parama);
    }
  }

  public void a(String paramString1, String paramString2, int paramInt)
  {
    a locala2 = (a)this.l.get(paramString1);
    a locala1 = locala2;
    if (locala2 == null)
    {
      this.l.put(paramString1, new a());
      locala1 = (a)this.l.get(paramString1);
    }
    locala1.a(paramInt, paramString2);
  }

  public void a(String paramString, JSONObject paramJSONObject)
  {
    try
    {
      paramString = a(paramString, e.e).a(paramJSONObject).a();
      b(paramString.toString());
      a(paramString.toString());
      return;
    }
    catch (JSONException paramString)
    {
    }
  }

  public void a(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = a(j.g, e.c).a(paramJSONObject).a();
      b(paramJSONObject.toString());
      a(paramJSONObject.toString());
      return;
    }
    catch (JSONException paramJSONObject)
    {
    }
  }

  public void b()
  {
    try
    {
      Object localObject = a(j.b, e.b).a(f.c, 0).a(f.f, com.intowow.sdk.j.i.a()).a(f.d, com.intowow.sdk.j.i.b()).a(f.e, com.intowow.sdk.j.i.c()).a(f.g, com.intowow.sdk.j.i.c(this.a.b())).a(f.h, com.intowow.sdk.j.i.d());
      a((d)localObject);
      localObject = ((d)localObject).a();
      b(((JSONObject)localObject).toString());
      a(((JSONObject)localObject).toString());
      return;
    }
    catch (JSONException localJSONException)
    {
    }
  }

  public void b(int paramInt)
  {
    try
    {
      JSONObject localJSONObject = a(j.e, e.b).a(f.u, paramInt).a();
      b(localJSONObject.toString());
      a(localJSONObject.toString());
      return;
    }
    catch (JSONException localJSONException)
    {
    }
  }

  public void b(JSONObject paramJSONObject)
  {
    if (paramJSONObject.optInt(f.a(f.u)) < this.h)
      return;
    try
    {
      paramJSONObject = a(j.j, e.c).a(paramJSONObject).a();
      b(paramJSONObject.toString());
      a(paramJSONObject.toString());
      return;
    }
    catch (JSONException paramJSONObject)
    {
    }
  }

  public void c()
  {
    try
    {
      Object localObject = a(j.c, e.b).a(f.c, 0).a(f.f, com.intowow.sdk.j.i.a()).a(f.d, com.intowow.sdk.j.i.b()).a(f.e, com.intowow.sdk.j.i.c()).a(f.g, com.intowow.sdk.j.i.c(this.a.b())).a(f.h, com.intowow.sdk.j.i.d());
      a((d)localObject);
      localObject = ((d)localObject).a();
      b(((JSONObject)localObject).toString());
      a(((JSONObject)localObject).toString());
      return;
    }
    catch (JSONException localJSONException)
    {
    }
  }

  public void c(int paramInt)
  {
    try
    {
      JSONObject localJSONObject = a(j.k, e.c).a(f.x, paramInt).a();
      b(localJSONObject.toString());
      a(localJSONObject.toString());
      return;
    }
    catch (JSONException localJSONException)
    {
    }
  }

  // ERROR //
  public void d()
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 435	com/intowow/sdk/h/j:d	Lcom/intowow/sdk/h/j;
    //   4: getstatic 356	com/intowow/sdk/h/e:b	Lcom/intowow/sdk/h/e;
    //   7: invokespecial 281	com/intowow/sdk/h/i:a	(Lcom/intowow/sdk/h/j;Lcom/intowow/sdk/h/e;)Lcom/intowow/sdk/h/d;
    //   10: astore_1
    //   11: aload_0
    //   12: getfield 45	com/intowow/sdk/h/i:a	Lcom/intowow/sdk/b/k;
    //   15: invokevirtual 118	com/intowow/sdk/b/k:b	()Landroid/content/Context;
    //   18: ldc_w 437
    //   21: invokevirtual 441	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   24: checkcast 443	android/location/LocationManager
    //   27: ldc_w 445
    //   30: invokevirtual 449	android/location/LocationManager:getLastKnownLocation	(Ljava/lang/String;)Landroid/location/Location;
    //   33: astore_2
    //   34: aload_2
    //   35: ifnull +27 -> 62
    //   38: aload_1
    //   39: getstatic 452	com/intowow/sdk/h/f:s	Lcom/intowow/sdk/h/f;
    //   42: aload_2
    //   43: invokevirtual 458	android/location/Location:getLongitude	()D
    //   46: invokevirtual 461	com/intowow/sdk/h/d:a	(Lcom/intowow/sdk/h/f;D)Lcom/intowow/sdk/h/d;
    //   49: pop
    //   50: aload_1
    //   51: getstatic 464	com/intowow/sdk/h/f:t	Lcom/intowow/sdk/h/f;
    //   54: aload_2
    //   55: invokevirtual 467	android/location/Location:getLatitude	()D
    //   58: invokevirtual 461	com/intowow/sdk/h/d:a	(Lcom/intowow/sdk/h/f;D)Lcom/intowow/sdk/h/d;
    //   61: pop
    //   62: aload_1
    //   63: invokevirtual 290	com/intowow/sdk/h/d:a	()Lorg/json/JSONObject;
    //   66: astore_1
    //   67: aload_0
    //   68: aload_1
    //   69: invokevirtual 293	org/json/JSONObject:toString	()Ljava/lang/String;
    //   72: invokespecial 295	com/intowow/sdk/h/i:b	(Ljava/lang/String;)V
    //   75: aload_0
    //   76: aload_1
    //   77: invokevirtual 293	org/json/JSONObject:toString	()Ljava/lang/String;
    //   80: invokespecial 296	com/intowow/sdk/h/i:a	(Ljava/lang/String;)V
    //   83: return
    //   84: astore_1
    //   85: return
    //   86: astore_2
    //   87: goto -25 -> 62
    //
    // Exception table:
    //   from	to	target	type
    //   0	11	84	org/json/JSONException
    //   11	34	84	org/json/JSONException
    //   38	62	84	org/json/JSONException
    //   62	83	84	org/json/JSONException
    //   11	34	86	java/lang/Exception
    //   38	62	86	java/lang/Exception
  }

  private class a
  {
    private Map<Integer, JSONArray> b = null;

    public a()
    {
    }

    public JSONObject a()
    {
      if (this.b.size() > 0)
        try
        {
          JSONObject localJSONObject = new JSONObject();
          Iterator localIterator = this.b.keySet().iterator();
          while (true)
          {
            if (!localIterator.hasNext())
              return localJSONObject;
            Integer localInteger = (Integer)localIterator.next();
            localJSONObject.put(String.valueOf(localInteger), this.b.get(localInteger));
          }
        }
        catch (Exception localException)
        {
        }
      return null;
    }

    public void a(int paramInt, String paramString)
    {
      JSONArray localJSONArray2 = (JSONArray)this.b.get(Integer.valueOf(paramInt));
      JSONArray localJSONArray1 = localJSONArray2;
      if (localJSONArray2 == null)
      {
        localJSONArray1 = new JSONArray();
        this.b.put(Integer.valueOf(paramInt), localJSONArray1);
      }
      localJSONArray1.put(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.h.i
 * JD-Core Version:    0.6.2
 */