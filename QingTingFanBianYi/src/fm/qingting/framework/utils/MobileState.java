package fm.qingting.framework.utils;

import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteOrder;

public class MobileState
{
  private static MobileState instance;
  private Context mContext;

  private MobileState(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private int getHeadSetStateByMgr()
  {
    if (this.mContext == null);
    while (true)
    {
      return -1;
      try
      {
        AudioManager localAudioManager = (AudioManager)this.mContext.getSystemService("audio");
        if (localAudioManager != null)
        {
          boolean bool = localAudioManager.isWiredHeadsetOn();
          if (bool)
            return 1;
        }
      }
      catch (Exception localException)
      {
      }
    }
    return -1;
  }

  public static MobileState getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new MobileState(paramContext);
    return instance;
  }

  public static int getNetWorkType(Context paramContext)
  {
    int i = 5;
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (paramContext == null)
      return -1;
    int j = paramContext.getType();
    if (j == 0)
    {
      if (paramContext.getExtraInfo() == null)
        return 5;
      if (paramContext.getExtraInfo().toLowerCase().equals("cmnet"))
        i = 3;
    }
    while (true)
    {
      return i;
      if (paramContext.getExtraInfo().toLowerCase().equals("3gnet"))
      {
        i = 2;
      }
      else if (paramContext.getExtraInfo().toLowerCase().equals("ctnet"))
      {
        i = 2;
      }
      else if (paramContext.getExtraInfo().toLowerCase().equals("ctwap"))
      {
        i = 3;
      }
      else if (paramContext.getExtraInfo().toLowerCase().equals("cmwap"))
      {
        i = 3;
        continue;
        if (j == 1)
          i = 1;
      }
    }
  }

  public static String getWifiIpAddress(Context paramContext)
  {
    if (paramContext == null);
    while (true)
    {
      return null;
      try
      {
        int j = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getIpAddress();
        int i = j;
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN))
          i = Integer.reverseBytes(j);
        paramContext = BigInteger.valueOf(i).toByteArray();
        if (paramContext != null)
          try
          {
            paramContext = InetAddress.getByAddress(paramContext).getHostAddress();
            return paramContext;
          }
          catch (Exception paramContext)
          {
            return null;
          }
      }
      catch (Exception paramContext)
      {
      }
    }
    return null;
  }

  public static boolean isCMNETOR3GNET(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.getType() == 0) && (paramContext.getExtraInfo() != null);
  }

  public static boolean isNetWorkEnable(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
        bool = paramContext.isAvailable();
      return bool;
    }
    catch (Exception paramContext)
    {
    }
    return false;
  }

  // ERROR //
  public int getheadsetState()
  {
    // Byte code:
    //   0: new 138	java/io/FileReader
    //   3: dup
    //   4: ldc 140
    //   6: invokespecial 143	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   9: astore_1
    //   10: aload_1
    //   11: ifnonnull +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: sipush 1024
    //   19: newarray char
    //   21: astore_2
    //   22: new 61	java/lang/String
    //   25: dup
    //   26: aload_2
    //   27: iconst_0
    //   28: aload_1
    //   29: aload_2
    //   30: iconst_0
    //   31: sipush 1024
    //   34: invokevirtual 147	java/io/FileReader:read	([CII)I
    //   37: invokespecial 150	java/lang/String:<init>	([CII)V
    //   40: invokevirtual 153	java/lang/String:trim	()Ljava/lang/String;
    //   43: invokestatic 156	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   46: invokevirtual 159	java/lang/Integer:intValue	()I
    //   49: istore_3
    //   50: iload_3
    //   51: ireturn
    //   52: astore_1
    //   53: aload_1
    //   54: invokevirtual 162	java/lang/Exception:printStackTrace	()V
    //   57: aload_0
    //   58: invokespecial 164	fm/qingting/framework/utils/MobileState:getHeadSetStateByMgr	()I
    //   61: ireturn
    //   62: astore_1
    //   63: goto -10 -> 53
    //
    // Exception table:
    //   from	to	target	type
    //   0	10	52	java/lang/Exception
    //   16	50	62	java/lang/Exception
  }

  public boolean hasHeadSet()
  {
    if (this.mContext == null);
    while (true)
    {
      return false;
      try
      {
        AudioManager localAudioManager = (AudioManager)this.mContext.getSystemService("audio");
        if (localAudioManager != null)
        {
          boolean bool = localAudioManager.isWiredHeadsetOn();
          if (bool)
            return true;
        }
      }
      catch (Exception localException)
      {
      }
    }
    return false;
  }

  public boolean isMobilEnable(Context paramContext)
  {
    boolean bool = false;
    if (((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(0) != null)
      bool = true;
    return bool;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.MobileState
 * JD-Core Version:    0.6.2
 */