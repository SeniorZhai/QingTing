package com.intowow.sdk.i.b;

import com.intowow.sdk.a.j;
import com.intowow.sdk.b.e.d;
import com.intowow.sdk.j.l;
import com.intowow.sdk.model.g;

public class b
{
  private e.d a = null;
  private com.intowow.sdk.f.b b = null;

  public b(com.intowow.sdk.f.b paramb, e.d paramd)
  {
    this.b = paramb;
    this.a = paramd;
  }

  private long a(String paramString1, String paramString2)
  {
    if (this.b == null)
      return 0L;
    j localj = this.a.a();
    if (localj != null)
    {
      long l2 = localj.c(paramString2);
      l1 = l2;
      if (l2 != 0L);
    }
    for (long l1 = localj.b(paramString1); ; l1 = 0L)
      return l1;
  }

  private g b(String paramString)
  {
    return this.b.f(paramString);
  }

  private String b(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      return paramString1;
    return paramString1 + "-" + paramString2;
  }

  private void b(String paramString, long paramLong)
  {
    this.a.a(paramString, String.valueOf(paramLong));
    this.b.b(paramString, paramLong);
  }

  private String c(String paramString)
  {
    return b(paramString, null);
  }

  public void a(String paramString, long paramLong)
  {
    Object localObject = b(paramString);
    if (localObject != null)
    {
      localObject = ((g)localObject).a();
      if ((this.b != null) && (!l.a((String)localObject)) && (!l.a(paramString)))
        break label48;
    }
    label48: j localj;
    do
    {
      do
      {
        return;
        localObject = null;
        break;
        localj = this.a.a();
      }
      while ((localj == null) || (localj.c == null));
      if (localj.c(paramString) != 0L)
        b(b((String)localObject, paramString), paramLong);
    }
    while (localj.b((String)localObject) == 0L);
    b(b((String)localObject, null), paramLong);
  }

  public boolean a(String paramString)
  {
    if (l.a(paramString));
    Object localObject;
    long l3;
    while (true)
    {
      return true;
      localObject = b(paramString);
      if (localObject != null);
      for (localObject = ((g)localObject).a(); !l.a((String)localObject); localObject = null)
      {
        l3 = a((String)localObject, paramString);
        if (l3 != 0L)
          break label53;
        return false;
      }
    }
    label53: paramString = b((String)localObject, paramString);
    long l1 = this.a.a(paramString);
    if (l1 <= 0L)
    {
      paramString = c((String)localObject);
      long l2 = this.a.a(paramString);
      l1 = l2;
      if (l2 <= 0L)
        return false;
    }
    if (Math.abs(System.currentTimeMillis() - l1) < l3);
    for (boolean bool = true; ; bool = false)
      return bool;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.b.b
 * JD-Core Version:    0.6.2
 */