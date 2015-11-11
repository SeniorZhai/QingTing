package com.talkingdata.pingan.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class an
  implements g, r
{
  public String a = "";
  public String b = "";
  public h c = new h();
  public u d = new u();
  public List e = new ArrayList();
  public long f = 0L;
  public long g = 0L;
  public long h = 0L;
  public Long[][] i;

  public int a()
  {
    return p.c(5) + p.c(this.a) + p.c(this.b) + this.c.a() + this.d.a();
  }

  public void a(p paramp)
  {
    paramp.b(6);
    paramp.a(this.a);
    paramp.a(this.b);
    paramp.a(this.c);
    paramp.a(this.d);
    paramp.b(this.e.size());
    Object localObject = this.e.iterator();
    while (((Iterator)localObject).hasNext())
      paramp.a((ac)((Iterator)localObject).next());
    if (this.i == null)
      paramp.b();
    while (true)
    {
      return;
      paramp.b(this.i.length);
      localObject = this.i;
      int k = localObject.length;
      int j = 0;
      while (j < k)
      {
        paramp.a(localObject[j]);
        j += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.talkingdata.pingan.sdk.an
 * JD-Core Version:    0.6.2
 */