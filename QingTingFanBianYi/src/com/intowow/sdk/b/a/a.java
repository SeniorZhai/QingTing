package com.intowow.sdk.b.a;

import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.f;
import com.intowow.sdk.model.ADProfile.l;
import java.util.Date;
import java.util.Set;

public class a
{
  private static boolean a(ADProfile paramADProfile)
  {
    return (paramADProfile != null) && (paramADProfile.a());
  }

  public static boolean a(ADProfile paramADProfile, int paramInt)
  {
    if (!a(paramADProfile));
    do
    {
      return false;
      Set localSet = paramADProfile.A().c();
      paramADProfile = paramADProfile.A().d();
      if (localSet != null)
        return localSet.contains(Integer.valueOf(paramInt));
      if (paramADProfile == null)
        break;
    }
    while (paramADProfile.contains(Integer.valueOf(paramInt)));
    return true;
    return true;
  }

  public static boolean a(ADProfile paramADProfile, long paramLong)
  {
    if (!a(paramADProfile));
    while ((!paramADProfile.a(new Date(paramLong).getHours())) || (paramADProfile.i() > paramLong) || (paramADProfile.j() <= paramLong))
      return false;
    return true;
  }

  private static boolean a(ADProfile paramADProfile, long paramLong1, long paramLong2)
  {
    return paramLong1 - paramADProfile.y() < paramLong2;
  }

  public static boolean a(ADProfile paramADProfile, long paramLong1, long paramLong2, int paramInt)
  {
    if (paramADProfile.o());
    while ((a(paramADProfile, paramLong1, paramLong2)) || (b(paramADProfile)) || (c(paramADProfile)) || (b(paramADProfile, paramLong1)) || (!a(paramADProfile, paramInt)))
      return true;
    return false;
  }

  private static boolean b(ADProfile paramADProfile)
  {
    int i = paramADProfile.m();
    return (i != -1) && (paramADProfile.t() >= i);
  }

  private static boolean b(ADProfile paramADProfile, long paramLong)
  {
    if (paramADProfile.z() != null)
      return paramADProfile.z().a(paramLong);
    return false;
  }

  private static boolean c(ADProfile paramADProfile)
  {
    int i = paramADProfile.u();
    double d = paramADProfile.z().c();
    return (i + 1) / (paramADProfile.t() + 1) < d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.b.a.a
 * JD-Core Version:    0.6.2
 */