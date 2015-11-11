package com.taobao.munion.base.utdid.a.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.Random;

public class e
{
  public static final String a()
  {
    int i = (int)(System.currentTimeMillis() / 1000L);
    int j = (int)System.nanoTime();
    int k = new Random().nextInt();
    int m = new Random().nextInt();
    byte[] arrayOfByte1 = c.a(i);
    byte[] arrayOfByte2 = c.a(j);
    byte[] arrayOfByte3 = c.a(k);
    byte[] arrayOfByte4 = c.a(m);
    byte[] arrayOfByte5 = new byte[16];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 0, 4);
    System.arraycopy(arrayOfByte2, 0, arrayOfByte5, 4, 4);
    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, 8, 4);
    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, 12, 4);
    return b.b(arrayOfByte5, 2);
  }

  public static String a(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getDeviceId();
        localObject1 = paramContext;
        paramContext = (Context)localObject1;
        if (f.a((String)localObject1))
          paramContext = a();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        localObject1 = localObject2;
        continue;
        paramContext = null;
      }
    }
  }

  public static String b(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        paramContext = paramContext.getSubscriberId();
        localObject1 = paramContext;
        paramContext = (Context)localObject1;
        if (f.a((String)localObject1))
          paramContext = a();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      while (true)
      {
        localObject1 = localObject2;
        continue;
        paramContext = null;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.utdid.a.a.e
 * JD-Core Version:    0.6.2
 */