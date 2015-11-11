package com.taobao.munion.base.volley;

import java.util.Collections;
import java.util.Map;

public abstract interface b
{
  public abstract a a(String paramString);

  public abstract void a();

  public abstract void a(String paramString, a parama);

  public abstract void a(String paramString, boolean paramBoolean);

  public abstract void b();

  public abstract void b(String paramString);

  public static class a
  {
    public byte[] a;
    public String b;
    public long c;
    public long d;
    public long e;
    public Map<String, String> f = Collections.emptyMap();

    public boolean a()
    {
      return this.d < System.currentTimeMillis();
    }

    public boolean b()
    {
      return this.e < System.currentTimeMillis();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.b
 * JD-Core Version:    0.6.2
 */