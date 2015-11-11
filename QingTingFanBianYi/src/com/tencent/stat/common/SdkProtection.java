package com.tencent.stat.common;

import android.content.Context;

public class SdkProtection
{
  static long valueNotSet = -1L;

  public static boolean beginCheck(Context paramContext)
  {
    long l1 = getPreferencesValue(paramContext, "1.0.0_begin_protection");
    long l2 = getPreferencesValue(paramContext, "1.0.0_end__protection");
    if ((l1 > 0L) && (l2 == valueNotSet))
      return false;
    if (l1 == valueNotSet)
      setPreferencesValue(paramContext, "1.0.0_begin_protection", System.currentTimeMillis());
    return true;
  }

  public static void endCheck(Context paramContext)
  {
    if (getPreferencesValue(paramContext, "1.0.0_end__protection") == valueNotSet)
      setPreferencesValue(paramContext, "1.0.0_end__protection", System.currentTimeMillis());
  }

  static long getPreferencesValue(Context paramContext, String paramString)
  {
    return StatPreferences.getLong(paramContext, paramString, valueNotSet);
  }

  static void setPreferencesValue(Context paramContext, String paramString, long paramLong)
  {
    StatPreferences.putLong(paramContext, paramString, paramLong);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.common.SdkProtection
 * JD-Core Version:    0.6.2
 */