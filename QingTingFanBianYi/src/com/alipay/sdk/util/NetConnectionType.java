package com.alipay.sdk.util;

public enum NetConnectionType
{
  private int p;
  private String q;

  private NetConnectionType(int arg3, String arg4)
  {
    int i1;
    this.p = i1;
    Object localObject;
    this.q = localObject;
  }

  public static NetConnectionType a(int paramInt)
  {
    NetConnectionType[] arrayOfNetConnectionType = values();
    int i2 = arrayOfNetConnectionType.length;
    int i1 = 0;
    while (i1 < i2)
    {
      NetConnectionType localNetConnectionType = arrayOfNetConnectionType[i1];
      if (localNetConnectionType.p == paramInt)
        return localNetConnectionType;
      i1 += 1;
    }
    return o;
  }

  private void a(String paramString)
  {
    this.q = paramString;
  }

  private int b()
  {
    return this.p;
  }

  public final String a()
  {
    return this.q;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.NetConnectionType
 * JD-Core Version:    0.6.2
 */