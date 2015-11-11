package com.intowow.sdk.j;

import android.content.Context;
import com.intowow.sdk.a.j;
import com.intowow.sdk.b.k;
import com.intowow.sdk.f.b;
import com.intowow.sdk.model.e;

public class a
{
  public static String a(Context paramContext, b paramb)
  {
    if ((paramContext == null) || (paramb == null))
      paramContext = null;
    double[] arrayOfDouble;
    do
    {
      return paramContext;
      paramb = paramb.E();
      if (paramb == null)
        return null;
      arrayOfDouble = d.a(paramContext);
      paramContext = paramb;
    }
    while (arrayOfDouble == null);
    return String.format("%s?lat=%f&long=%f", new Object[] { paramb, Double.valueOf(arrayOfDouble[0]), Double.valueOf(arrayOfDouble[1]) });
  }

  public static String a(k paramk)
  {
    if ((paramk == null) || (paramk.f() == null) || (paramk.q() == null));
    String str3;
    do
    {
      return null;
      str3 = paramk.f().C();
    }
    while (str3 == null);
    String str2;
    label47: String str1;
    if (paramk.f() != null)
    {
      str2 = paramk.f().M();
      if (paramk.f().g() == null)
        break label129;
      str1 = paramk.f().g().a();
      label68: if (!paramk.f().n())
        break label134;
      str1 = "TEST";
      label81: if (str1 == null)
        break label155;
    }
    for (paramk = String.format("%s/adlist/%s/%s/%s", new Object[] { str3, str2, str1, Integer.valueOf(c.d()) }); ; paramk = String.format("%s/adlist/%s/%s", new Object[] { str3, str2, Integer.valueOf(c.d()) }))
    {
      return paramk;
      str2 = i.b(paramk.b());
      break label47;
      label129: str1 = null;
      break label68;
      label134: if (paramk.f().k() == null)
        break label81;
      str1 = paramk.f().k();
      break label81;
      label155: paramk = paramk.q();
      if ((paramk.n() != null) || (paramk.j() == null))
        break;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.a
 * JD-Core Version:    0.6.2
 */