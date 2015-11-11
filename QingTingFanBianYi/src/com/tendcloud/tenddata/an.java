package com.tendcloud.tenddata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class an
  implements g, s
{
  public String a = "";
  public String b = "";
  public h c = new h();
  public v d = new v();
  public List e = new ArrayList();
  public long f = 0L;
  public long g = 0L;
  public long h = 0L;
  public Long[][] i;

  public int a()
  {
    return q.c(5) + q.c(this.a) + q.c(this.b) + this.c.a() + this.d.a();
  }

  public void a(q paramq)
  {
    paramq.b(6);
    paramq.a(this.a);
    paramq.a(this.b);
    paramq.a(this.c);
    paramq.a(this.d);
    paramq.b(this.e.size());
    Object localObject = this.e.iterator();
    while (((Iterator)localObject).hasNext())
      paramq.a((z)((Iterator)localObject).next());
    if (this.i == null)
      paramq.b();
    while (true)
    {
      return;
      paramq.b(this.i.length);
      localObject = this.i;
      int k = localObject.length;
      int j = 0;
      while (j < k)
      {
        paramq.a(localObject[j]);
        j += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.an
 * JD-Core Version:    0.6.2
 */