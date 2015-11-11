package com.intowow.sdk.j;

import android.content.Context;
import com.intowow.sdk.model.ADProfile.c;
import com.intowow.sdk.model.ADProfile.c.a;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.e;
import com.intowow.sdk.model.ADProfile.k;
import com.intowow.sdk.model.ADProfile.q;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class b
{
  public static a a(Context paramContext, ADProfile.c paramc)
  {
    if (!k.e())
      return a.a;
    paramContext = new File(k.a(paramContext).a() + a(paramc));
    if ((paramContext == null) || (!paramContext.exists()))
      return a.a;
    if (paramContext.length() == b(paramc))
      return a.d;
    return a.b;
  }

  private static String a(ADProfile.c paramc)
  {
    switch (a()[paramc.a().ordinal()])
    {
    default:
      return null;
    case 2:
      return ((ADProfile.k)paramc).e();
    case 3:
    }
    return ((ADProfile.q)paramc).e();
  }

  public static boolean a(Context paramContext, ADProfile.e parame)
  {
    Iterator localIterator = parame.a().iterator();
    ADProfile.c localc;
    do
    {
      if (!localIterator.hasNext())
        return true;
      localc = parame.b((ADProfile.d)localIterator.next());
    }
    while ((localc.a() == ADProfile.c.a.d) || (a(paramContext, localc) == a.d));
    return false;
  }

  public static boolean a(ADProfile.e parame, List<ADProfile.c.a> paramList)
  {
    Iterator localIterator1 = parame.a().iterator();
    ADProfile.c localc;
    do
    {
      if (!localIterator1.hasNext())
        return false;
      localc = parame.b((ADProfile.d)localIterator1.next());
    }
    while (localc.a() == ADProfile.c.a.d);
    Iterator localIterator2 = paramList.iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator2.hasNext())
      {
        if (i != 0)
          break;
        return true;
      }
      ADProfile.c.a locala = (ADProfile.c.a)localIterator2.next();
      if (localc.a() == locala)
        i = 1;
    }
  }

  private static long b(ADProfile.c paramc)
  {
    switch (a()[paramc.a().ordinal()])
    {
    default:
      return 0L;
    case 2:
      return ((ADProfile.k)paramc).f();
    case 3:
    }
    return ((ADProfile.q)paramc).g();
  }

  public static enum a
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.b
 * JD-Core Version:    0.6.2
 */