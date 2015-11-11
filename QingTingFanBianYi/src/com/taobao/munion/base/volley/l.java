package com.taobao.munion.base.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.Log.a;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class l<T>
  implements Comparable<l<T>>
{
  private static final String a = "UTF-8";
  private static final long m = 3000L;
  private final Log.a b;
  private final int c;
  private final String d;
  private final int e;
  private final n.a f;
  private Integer g;
  private m h;
  private boolean i;
  private boolean j;
  private boolean k;
  private long l;
  private p n;
  private b.a o;
  private Object p;

  public l(int paramInt, String paramString, n.a parama)
  {
    if (Log.a.a);
    for (Log.a locala = new Log.a(); ; locala = null)
    {
      this.b = locala;
      this.i = true;
      this.j = false;
      this.k = false;
      this.l = 0L;
      this.o = null;
      this.c = paramInt;
      this.d = paramString;
      this.f = parama;
      a(new d());
      this.e = c(paramString);
      return;
    }
  }

  public l(String paramString, n.a parama)
  {
    this(-1, paramString, parama);
  }

  private byte[] a(Map<String, String> paramMap, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      paramMap = paramMap.entrySet().iterator();
      while (paramMap.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramMap.next();
        localStringBuilder.append(URLEncoder.encode((String)localEntry.getKey(), paramString));
        localStringBuilder.append('=');
        localStringBuilder.append(URLEncoder.encode((String)localEntry.getValue(), paramString));
        localStringBuilder.append('&');
      }
    }
    catch (UnsupportedEncodingException paramMap)
    {
      throw new RuntimeException("Encoding not supported: " + paramString, paramMap);
    }
    paramMap = localStringBuilder.toString().getBytes(paramString);
    return paramMap;
  }

  private static int c(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = Uri.parse(paramString);
      if (paramString != null)
      {
        paramString = paramString.getHost();
        if (paramString != null)
          return paramString.hashCode();
      }
    }
    return 0;
  }

  public int a(l<T> paraml)
  {
    b localb1 = u();
    b localb2 = paraml.u();
    if (localb1 == localb2)
      return this.g.intValue() - paraml.g.intValue();
    return localb2.ordinal() - localb1.ordinal();
  }

  public final l<?> a(int paramInt)
  {
    this.g = Integer.valueOf(paramInt);
    return this;
  }

  public l<?> a(b.a parama)
  {
    this.o = parama;
    return this;
  }

  public l<?> a(m paramm)
  {
    this.h = paramm;
    return this;
  }

  public l<?> a(p paramp)
  {
    this.n = paramp;
    return this;
  }

  public final l<?> a(boolean paramBoolean)
  {
    this.i = paramBoolean;
    return this;
  }

  protected abstract n<T> a(i parami);

  protected s a(s params)
  {
    return params;
  }

  public void a(String paramString)
  {
    if (Log.a.a)
      this.b.a(paramString, Thread.currentThread().getId());
    while (this.l != 0L)
      return;
    this.l = SystemClock.elapsedRealtime();
  }

  public int b()
  {
    return this.c;
  }

  public l<?> b(Object paramObject)
  {
    this.p = paramObject;
    return this;
  }

  public void b(s params)
  {
    if (this.f != null)
      this.f.a(params);
  }

  void b(final String paramString)
  {
    if (this.h != null)
      this.h.b(this);
    final long l1;
    if (Log.a.a)
    {
      l1 = Thread.currentThread().getId();
      if (Looper.myLooper() != Looper.getMainLooper())
        new Handler(Looper.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            l.b(l.this).a(paramString, l1);
            l.b(l.this).a(l.this.toString());
          }
        });
    }
    do
    {
      return;
      this.b.a(paramString, l1);
      this.b.a(toString());
      return;
      l1 = SystemClock.elapsedRealtime() - this.l;
    }
    while (l1 < 3000L);
    Log.d("%d ms: %s", new Object[] { Long.valueOf(l1), toString() });
  }

  public Object c()
  {
    return this.p;
  }

  protected abstract void c(T paramT);

  public int d()
  {
    return this.e;
  }

  public final int e()
  {
    if (this.g == null)
      throw new IllegalStateException("getSequence called before setSequence");
    return this.g.intValue();
  }

  public String f()
  {
    return this.d;
  }

  public String g()
  {
    return f();
  }

  public b.a h()
  {
    return this.o;
  }

  public void i()
  {
    this.j = true;
  }

  public boolean j()
  {
    return this.j;
  }

  public Map<String, String> k()
    throws a
  {
    return Collections.emptyMap();
  }

  protected Map<String, String> l()
    throws a
  {
    return p();
  }

  protected String m()
  {
    return q();
  }

  public String n()
  {
    return r();
  }

  public byte[] o()
    throws a
  {
    Map localMap = l();
    if ((localMap != null) && (localMap.size() > 0))
      return a(localMap, m());
    return null;
  }

  protected Map<String, String> p()
    throws a
  {
    return null;
  }

  protected String q()
  {
    return "UTF-8";
  }

  public String r()
  {
    return "application/x-www-form-urlencoded; charset=" + q();
  }

  public byte[] s()
    throws a
  {
    Map localMap = p();
    if ((localMap != null) && (localMap.size() > 0))
      return a(localMap, q());
    return null;
  }

  public final boolean t()
  {
    return this.i;
  }

  public String toString()
  {
    String str2 = "0x" + Integer.toHexString(d());
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.j);
    for (String str1 = "[X] "; ; str1 = "[ ] ")
      return str1 + f() + " " + str2 + " " + u() + " " + this.g;
  }

  public b u()
  {
    return b.b;
  }

  public final int v()
  {
    return this.n.a();
  }

  public p w()
  {
    return this.n;
  }

  public void x()
  {
    this.k = true;
  }

  public boolean y()
  {
    return this.k;
  }

  public static abstract interface a
  {
    public static final int a = -1;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
  }

  public static enum b
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.l
 * JD-Core Version:    0.6.2
 */