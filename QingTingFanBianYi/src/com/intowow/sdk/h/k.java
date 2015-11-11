package com.intowow.sdk.h;

public class k
{
  public static boolean a(int paramInt)
  {
    return (paramInt != 0) && (paramInt != 1);
  }

  public static boolean b(int paramInt)
  {
    return (paramInt != 2) && (paramInt != 5);
  }

  public static String c(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "UNKNOWN";
    case 1:
      return "NONE";
    case 2:
      return "WIFI";
    case 3:
      return "2G";
    case 4:
      return "3G";
    case 5:
    }
    return "4G";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.h.k
 * JD-Core Version:    0.6.2
 */