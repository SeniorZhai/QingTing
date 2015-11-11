package com.tendcloud.tenddata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
  implements g, m
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
    int m = q.c(7);
    int n = q.c(this.a);
    int i1 = q.c(this.b);
    int i2 = q.c(this.c);
    int i3 = q.c(this.g);
    int i4 = q.c(this.k);
    int i5 = q.c(this.h.size());
    Iterator localIterator = this.h.iterator();
    for (m = m + n + i1 + i2 + i3 + i4 + i5; localIterator.hasNext(); m = ((ai)localIterator.next()).a() + m);
    n = q.c(this.i.size());
    localIterator = this.i.iterator();
    for (m = n + m; localIterator.hasNext(); m = ((y)localIterator.next()).a() + m);
    return m;
  }

  public void a(q paramq)
  {
    paramq.b(7);
    paramq.a(this.a);
    paramq.a(this.b);
    paramq.a(this.c);
    paramq.a(this.g);
    paramq.b(this.h.size());
    Iterator localIterator = this.h.iterator();
    while (localIterator.hasNext())
      paramq.a((ai)localIterator.next());
    paramq.b(this.i.size());
    localIterator = this.i.iterator();
    while (localIterator.hasNext())
      paramq.a((y)localIterator.next());
    paramq.a(this.k);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.c
 * JD-Core Version:    0.6.2
 */