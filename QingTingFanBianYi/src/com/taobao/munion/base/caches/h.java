package com.taobao.munion.base.caches;

public class h
{
  private static h a;

  public static h a()
  {
    try
    {
      if (a == null)
        a = new h();
      h localh = a;
      return localh;
    }
    finally
    {
    }
  }

  public g a(String paramString1, String paramString2, int paramInt, boolean paramBoolean)
  {
    if ((paramString2 == null) || (paramInt < 10))
      return null;
    String str;
    if ((paramBoolean) && (r.a()))
    {
      str = k.a(b.b, paramString1, paramString2, true);
      paramString1 = k.a(b.b, paramString1, paramString2);
    }
    for (paramString2 = str; ; paramString2 = paramString1)
    {
      paramString1 = new g(paramString2, paramString1, paramInt, paramBoolean);
      paramString1.d();
      return paramString1;
      paramBoolean = false;
      paramString1 = k.a(b.b, paramString1, paramString2);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.caches.h
 * JD-Core Version:    0.6.2
 */