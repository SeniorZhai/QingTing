package com.tendcloud.tenddata;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class aa
{
  public static void a(Context paramContext, String paramString1, String paramString2, long paramLong)
  {
    paramContext.getSharedPreferences(paramString1, 0).edit().putLong(paramString2, paramLong).commit();
  }

  public static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext.getSharedPreferences(paramString1, 0).edit().putString(paramString2, paramString3).commit();
  }

  public static long b(Context paramContext, String paramString1, String paramString2, long paramLong)
  {
    return paramContext.getSharedPreferences(paramString1, 0).getLong(paramString2, paramLong);
  }

  public static String b(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    return paramContext.getSharedPreferences(paramString1, 0).getString(paramString2, paramString3);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.aa
 * JD-Core Version:    0.6.2
 */