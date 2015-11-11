package com.tencent.a.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;

public final class k
{
  private static int a = 10000;
  private static int b = 15000;
  private static int c = 5000;
  private static int d = 20000;
  private static int e = 25000;
  private static int f = 15000;
  private static ArrayList<a> g;
  private static long h;
  private static long i;
  private static long j;
  private static long k;
  private static long l;
  private static long m;
  private static long n;
  private static long o;
  private static long p;
  private static long q;
  private static int r;
  private static int s;
  private static int t;
  private static int u;

  static
  {
    a = 12000;
    b = 20000;
    c = 8000;
    d = 20000;
    e = 25000;
    f = 15000;
    Object localObject = (ConnectivityManager)l.b().getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if (localObject != null)
      {
        int i1 = ((NetworkInfo)localObject).getType();
        if ((((NetworkInfo)localObject).isConnected()) && (i1 == 0))
        {
          localObject = ((TelephonyManager)l.b().getSystemService("phone")).getSubscriberId();
          if ((localObject != null) && (((String)localObject).length() > 3) && (!((String)localObject).startsWith("46000")) && (!((String)localObject).startsWith("46002")))
          {
            a = 15000;
            b = 25000;
            c = 10000;
            d = 25000;
            e = 35000;
            f = 15000;
          }
        }
      }
    }
  }

  public static int a()
  {
    int i1 = a;
    if ((j > 0L) && (k > 0L))
      i1 = (int)(Math.max(m, j) + k - l);
    while (true)
    {
      Object localObject = (ConnectivityManager)l.b().getSystemService("connectivity");
      int i2 = i1;
      if (localObject != null)
      {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
        i2 = i1;
        if (localObject != null)
        {
          if ((((NetworkInfo)localObject).isConnected()) || (!((NetworkInfo)localObject).isAvailable()))
            break label199;
          i2 = b;
        }
      }
      while (true)
      {
        i2 = u * c + i2;
        i1 = i2;
        if (i2 <= c)
          i1 = c;
        i2 = i1;
        if (i1 <= k)
          i2 = (int)(k + c);
        i1 = i2;
        if (i2 >= b)
          i1 = b;
        a locala = b(Thread.currentThread().getId());
        localObject = locala;
        if (locala == null)
          localObject = a(Thread.currentThread().getId());
        i2 = i1;
        if (i1 < ((a)localObject).g + c)
          i2 = ((a)localObject).g + c;
        ((a)localObject).g = i2;
        return i2;
        label199: i2 = i1;
        if (k > 0L)
        {
          i2 = i1;
          if (k < c)
            i2 = c;
        }
      }
    }
  }

  private static a a(long paramLong)
  {
    if (g == null)
      g = new ArrayList();
    int i3;
    int i2;
    synchronized (g)
    {
      int i1;
      Object localObject1;
      if (g.size() > 20)
      {
        int i4 = g.size();
        i3 = 0;
        i1 = 0;
        i2 = 0;
        if (i3 < i4 / 2)
          break label253;
        if (i1 != 0)
        {
          g.get(0);
          h = 0L;
          g.get(0);
          i = 0L;
          k = ((a)g.get(0)).c;
          l = ((a)g.get(0)).c;
          o = ((a)g.get(0)).d;
          p = ((a)g.get(0)).d;
          if (((a)g.get(0)).f > 0L)
            r = (int)(((a)g.get(0)).e * 1000 / ((a)g.get(0)).f);
          s = r;
          localObject1 = g.iterator();
        }
      }
      label253: 
      do
      {
        a locala;
        do
        {
          if (!((Iterator)localObject1).hasNext())
          {
            localObject1 = new a();
            ((a)localObject1).a = paramLong;
            g.add(localObject1);
            return localObject1;
            if ((((a)g.get(i2)).f <= 0L) && (System.currentTimeMillis() - ((a)g.get(i2)).b <= 600000L))
              break label499;
            g.remove(i2);
            i1 = 1;
            break;
          }
          locala = (a)((Iterator)localObject1).next();
          if (0L > h)
            h = 0L;
          if (0L < i)
            i = 0L;
          if (locala.c > k)
            k = locala.c;
          if (locala.c < l)
            l = locala.c;
          if (locala.d > o)
            o = locala.d;
          if (locala.d < p)
            p = locala.d;
        }
        while (locala.f <= 0L);
        i1 = (int)(locala.e * 1000 / locala.f);
        if (i1 > r)
          r = i1;
      }
      while (i1 >= s);
      s = i1;
    }
    while (true)
    {
      i3 += 1;
      break;
      label499: i2 += 1;
    }
  }

  public static void a(int paramInt)
  {
    a locala1 = b(Thread.currentThread().getId());
    if (locala1 == null)
      return;
    locala1.f = (System.currentTimeMillis() - locala1.b);
    locala1.b = System.currentTimeMillis();
    locala1.e = paramInt;
    long l1;
    if (locala1.f == 0L)
      l1 = 1L;
    while (true)
    {
      paramInt = (int)(paramInt * 1000 / l1);
      t = paramInt;
      if (paramInt > r)
      {
        paramInt = t;
        label77: r = paramInt;
        if (t >= s)
          break label192;
        paramInt = t;
        label94: s = paramInt;
        if (g == null);
      }
      synchronized (g)
      {
        Iterator localIterator = g.iterator();
        if (!localIterator.hasNext())
        {
          if ((u > 0) && (locala1.c < c) && (locala1.d < f))
            u -= 1;
          locala1.g = ((int)locala1.c);
          return;
          l1 = locala1.f;
          continue;
          paramInt = r;
          break label77;
          label192: if (s == 0)
          {
            paramInt = t;
            break label94;
          }
          paramInt = s;
          break label94;
        }
        a locala2 = (a)localIterator.next();
        paramInt = locala2.e;
        l1 = locala2.f;
      }
    }
  }

  public static void a(HttpURLConnection paramHttpURLConnection)
  {
    a locala = b(Thread.currentThread().getId());
    paramHttpURLConnection = locala;
    if (locala == null)
      paramHttpURLConnection = a(Thread.currentThread().getId());
    if (paramHttpURLConnection == null)
      return;
    paramHttpURLConnection.b = System.currentTimeMillis();
  }

  public static void a(boolean paramBoolean)
  {
    if (!paramBoolean)
      u += 1;
    a locala = c(Thread.currentThread().getId());
    if (locala != null)
      long l1 = locala.b;
  }

  public static int b()
  {
    int i1 = d;
    if ((n > 0L) && (o > 0L))
      i1 = (int)(Math.max(q, n) + o - p);
    while (true)
    {
      Object localObject = (ConnectivityManager)l.b().getSystemService("connectivity");
      int i2 = i1;
      if (localObject != null)
      {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
        i2 = i1;
        if (localObject != null)
        {
          if ((((NetworkInfo)localObject).isConnected()) || (!((NetworkInfo)localObject).isAvailable()))
            break label214;
          i2 = e;
        }
      }
      while (true)
      {
        i2 = u * c + i2;
        i1 = i2;
        if (i2 <= f)
          i1 = f;
        i2 = i1;
        if (i1 <= o)
          i2 = (int)(o + f);
        i1 = i2;
        if (i2 >= e)
          i1 = e;
        localObject = b(Thread.currentThread().getId());
        i2 = i1;
        if (localObject != null)
        {
          i2 = i1;
          if (i1 < ((a)localObject).h + f)
            i2 = ((a)localObject).h + f;
          i1 = i2;
          if (i2 < ((a)localObject).g + f)
            i1 = ((a)localObject).g + f;
          ((a)localObject).h = i1;
          i2 = i1;
        }
        return i2;
        label214: i2 = i1;
        if (o > 0L)
        {
          i2 = i1;
          if (o < f)
            i2 = f;
        }
      }
    }
  }

  private static a b(long paramLong)
  {
    if (g == null)
      return null;
    synchronized (g)
    {
      Iterator localIterator = g.iterator();
      a locala;
      do
      {
        if (!localIterator.hasNext())
          return null;
        locala = (a)localIterator.next();
      }
      while (locala.a != paramLong);
      return locala;
    }
  }

  private static a c(long paramLong)
  {
    if (g != null);
    while (true)
    {
      int i1;
      synchronized (g)
      {
        i1 = g.size();
        i1 -= 1;
        if (i1 < 0)
          return null;
        if (((a)g.get(i1)).a == paramLong)
        {
          a locala = (a)g.remove(i1);
          return locala;
        }
      }
      i1 -= 1;
    }
  }

  public static void c()
  {
    ??? = b(Thread.currentThread().getId());
    if (??? == null);
    long l1;
    label80: 
    do
    {
      return;
      ((a)???).c = (System.currentTimeMillis() - ((a)???).b);
      ((a)???).b = System.currentTimeMillis();
      m = ((a)???).c;
      if (((a)???).c <= k)
        break;
      l1 = ((a)???).c;
      k = l1;
      if (((a)???).c >= l)
        break label144;
      l1 = ((a)???).c;
      l = l1;
    }
    while (g == null);
    while (true)
    {
      int i1;
      synchronized (g)
      {
        Iterator localIterator = g.iterator();
        i1 = 0;
        if (localIterator.hasNext())
          break label169;
        if (i1 > 0)
          j /= i1;
        return;
      }
      l1 = k;
      break;
      label144: if (l == 0L)
      {
        l1 = ((a)???).c;
        break label80;
      }
      l1 = l;
      break label80;
      label169: a locala = (a)localObject2.next();
      if (locala.c > 0L)
      {
        j += locala.c;
        i1 += 1;
      }
    }
  }

  public static void d()
  {
    ??? = b(Thread.currentThread().getId());
    if (??? == null);
    long l1;
    label80: 
    do
    {
      return;
      ((a)???).d = (System.currentTimeMillis() - ((a)???).b);
      ((a)???).b = System.currentTimeMillis();
      q = ((a)???).d;
      if (((a)???).d <= o)
        break;
      l1 = ((a)???).d;
      o = l1;
      if (((a)???).d >= p)
        break label144;
      l1 = ((a)???).d;
      p = l1;
    }
    while (g == null);
    while (true)
    {
      int i1;
      synchronized (g)
      {
        Iterator localIterator = g.iterator();
        i1 = 0;
        if (localIterator.hasNext())
          break label169;
        if (i1 > 0)
          n /= i1;
        return;
      }
      l1 = o;
      break;
      label144: if (p == 0L)
      {
        l1 = ((a)???).d;
        break label80;
      }
      l1 = p;
      break label80;
      label169: a locala = (a)localObject2.next();
      if (locala.d > 0L)
      {
        n += locala.d;
        i1 += 1;
      }
    }
  }

  public static final class a
  {
    public long a;
    public long b;
    public long c;
    public long d;
    public int e;
    public long f;
    public int g;
    public int h;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.k
 * JD-Core Version:    0.6.2
 */