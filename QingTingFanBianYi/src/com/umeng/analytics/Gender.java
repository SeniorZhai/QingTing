package com.umeng.analytics;

import java.util.Locale;
import u.aly.ap;

public enum Gender
{
  public int value;

  private Gender(int paramInt)
  {
    this.value = paramInt;
  }

  public static Gender getGender(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return Unknown;
    case 1:
      return Male;
    case 2:
    }
    return Female;
  }

  public static ap transGender(Gender paramGender)
  {
    switch (4.a[paramGender.ordinal()])
    {
    default:
      return ap.c;
    case 1:
      return ap.a;
    case 2:
    }
    return ap.b;
  }

  public int value()
  {
    return this.value;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.Gender
 * JD-Core Version:    0.6.2
 */