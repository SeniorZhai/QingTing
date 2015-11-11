package com.talkingdata.pingan.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
  implements g, l
{
  public static final int d = 1;
  public static final int e = 2;
  public static final int f = 3;
  public String a = "";
  public long b = 0L;
  public int c = 0;
  public int g = 0;
  public List h = new ArrayList();
  public List i = new ArrayList();
  public int j = 0;
  public int k = 0;

  public int a()
  {
    int m = p.c(7);
    int n = p.c(this.a);
    int i1 = p.c(this.b);
    int i2 = p.c(this.c);
    int i3 = p.c(this.g);
    int i4 = p.c(this.k);
    int i5 = p.c(this.h.size());
    Iterator localIterator = this.h.iterator();
    for (m = m + n + i1 + i2 + i3 + i4 + i5; localIterator.hasNext(); m = ((am)localIterator.next()).a() + m);
    n = p.c(this.i.size());
    localIterator = this.i.iterator();
    for (m = n + m; localIterator.hasNext(); m = ((ab)localIterator.next()).a() + m);
    return m;
  }

  public void a(p paramp)
  {
    paramp.b(7);
    paramp.a(this.a);
    paramp.a(this.b);
    paramp.a(this.c);
    paramp.a(this.g);
    paramp.b(this.h.size());
    Iterator localIterator = this.h.iterator();
    while (localIterator.hasNext())
      paramp.a((am)localIterator.next());
    paramp.b(this.i.size());
    localIterator = this.i.iterator();
    while (localIterator.hasNext())
      paramp.a((ab)localIterator.next());
    paramp.a(this.k);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.c
 * JD-Core Version:    0.6.2
 */