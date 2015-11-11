package com.miaozhen.mzmonitor;

import android.content.Context;

public final class k
{
  private static String[] a = { "ec0fe20119c925a8", "d304fd244a67db36", "5aeae0e31c86ab84", "6c64a61e7edb9b54", "3b044c3507f0899c", "ecbe51ab2c79224d", "bdb4a3a756759e1c", "0f29a61dac5c227e", "8c77f8b8f5a8f2e9", "0e000ebd6e211ef4", "5bcbcbd118632aff", "381712371a7e869e", "5ff31aaf8e07b74d", "55a3ac9a01d1d2b0", "2317d45ce0048f87", "c03a3e2a68d851f0" };

  public static String a(Context paramContext, String paramString)
  {
    Object localObject = f.a(paramContext);
    long l = c.a.a();
    localObject = ((f)localObject).e();
    StringBuffer localStringBuffer1 = new StringBuffer();
    StringBuffer localStringBuffer2 = new StringBuffer();
    localStringBuffer2.append(paramString.toLowerCase());
    localStringBuffer2.append(a[((int)(l % 16L))]);
    localStringBuffer2.append(((String)localObject).toLowerCase());
    localStringBuffer1.append(c.a.a(localStringBuffer2.toString()));
    localStringBuffer1.append("v");
    localStringBuffer1.append(j.d(paramContext));
    localStringBuffer1.append("t");
    localStringBuffer1.append(l);
    localStringBuffer1.append("k");
    localStringBuffer1.append(((String)localObject).toLowerCase());
    return localStringBuffer1.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.k
 * JD-Core Version:    0.6.2
 */