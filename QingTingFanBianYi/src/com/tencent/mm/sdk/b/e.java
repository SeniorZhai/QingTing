package com.tencent.mm.sdk.b;

import java.util.TimeZone;

public final class e
{
  private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
  private static final long[] t = { 300L, 200L, 300L, 200L };
  private static final long[] u = { 300L, 50L, 300L, 50L };
  private static final char[] v = { 60, 62, 34, 39, 38 };
  private static final String[] w = { "&lt;", "&gt;", "&quot;", "&apos;", "&amp;" };

  public static boolean c(String paramString)
  {
    return (paramString == null) || (paramString.length() <= 0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.b.e
 * JD-Core Version:    0.6.2
 */