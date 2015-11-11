package com.alipay.sdk.protocol;

public enum MiniStatus
{
  private String e;

  private MiniStatus(String arg3)
  {
    Object localObject;
    this.e = localObject;
  }

  public static MiniStatus a(String paramString)
  {
    Object localObject = null;
    MiniStatus[] arrayOfMiniStatus = values();
    int j = arrayOfMiniStatus.length;
    int i = 0;
    if (i < j)
    {
      MiniStatus localMiniStatus = arrayOfMiniStatus[i];
      if (!paramString.startsWith(localMiniStatus.e))
        break label49;
      localObject = localMiniStatus;
    }
    label49: 
    while (true)
    {
      i += 1;
      break;
      return localObject;
    }
  }

  private String a()
  {
    return this.e;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.protocol.MiniStatus
 * JD-Core Version:    0.6.2
 */