package com.tendcloud.tenddata;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpHost;

public class b
{
  static TelephonyManager a;
  static boolean b = false;
  static final long c = 300000L;
  static long d = -300000L;
  private static final String[] e = { "UNKNOWN", "GPRS", "EDGE", "UMTS", "CDMA", "EVDO_0", "EVDO_A", "1xRTT", "HSDPA", "HSUPA", "HSPA", "IDEN", "EVDO_B", "LTE", "EHRPD", "HSPAP" };

  static void a(Context paramContext)
  {
    a = (TelephonyManager)paramContext.getSystemService("phone");
  }

  public static boolean a()
  {
    return !TextUtils.isEmpty(Proxy.getDefaultHost());
  }

  public static HttpHost b()
  {
    if (a())
      return new HttpHost(Proxy.getDefaultHost(), Proxy.getDefaultPort());
    return null;
  }

  // ERROR //
  public static boolean b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 109
    //   3: invokestatic 114	com/tendcloud/tenddata/x:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   6: ifeq +50 -> 56
    //   9: aload_0
    //   10: ldc 116
    //   12: invokevirtual 75	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   15: checkcast 118	android/net/ConnectivityManager
    //   18: astore_0
    //   19: aload_0
    //   20: invokevirtual 122	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   23: astore_1
    //   24: aload_1
    //   25: ifnull +8 -> 33
    //   28: aload_1
    //   29: invokevirtual 127	android/net/NetworkInfo:isConnected	()Z
    //   32: ireturn
    //   33: aload_0
    //   34: iconst_0
    //   35: invokevirtual 131	android/net/ConnectivityManager:getNetworkInfo	(I)Landroid/net/NetworkInfo;
    //   38: astore_0
    //   39: aload_0
    //   40: ifnull +86 -> 126
    //   43: aload_0
    //   44: invokevirtual 135	android/net/NetworkInfo:getState	()Landroid/net/NetworkInfo$State;
    //   47: getstatic 140	android/net/NetworkInfo$State:UNKNOWN	Landroid/net/NetworkInfo$State;
    //   50: invokevirtual 144	android/net/NetworkInfo$State:equals	(Ljava/lang/Object;)Z
    //   53: ifeq +73 -> 126
    //   56: invokestatic 150	android/os/SystemClock:elapsedRealtime	()J
    //   59: getstatic 62	com/tendcloud/tenddata/b:d	J
    //   62: lsub
    //   63: ldc2_w 14
    //   66: lcmp
    //   67: ifle +55 -> 122
    //   70: invokestatic 150	android/os/SystemClock:elapsedRealtime	()J
    //   73: putstatic 62	com/tendcloud/tenddata/b:d	J
    //   76: aconst_null
    //   77: astore_0
    //   78: aload_0
    //   79: astore_1
    //   80: invokestatic 152	com/tendcloud/tenddata/b:b	()Lorg/apache/http/HttpHost;
    //   83: astore_2
    //   84: aload_2
    //   85: ifnonnull +43 -> 128
    //   88: aload_0
    //   89: astore_1
    //   90: new 154	java/net/Socket
    //   93: dup
    //   94: aload_2
    //   95: invokevirtual 157	org/apache/http/HttpHost:getHostName	()Ljava/lang/String;
    //   98: aload_2
    //   99: invokevirtual 160	org/apache/http/HttpHost:getPort	()I
    //   102: invokespecial 161	java/net/Socket:<init>	(Ljava/lang/String;I)V
    //   105: astore_0
    //   106: aload_0
    //   107: astore_1
    //   108: aload_0
    //   109: astore_2
    //   110: iconst_1
    //   111: putstatic 58	com/tendcloud/tenddata/b:b	Z
    //   114: aload_0
    //   115: ifnull +7 -> 122
    //   118: aload_0
    //   119: invokevirtual 164	java/net/Socket:close	()V
    //   122: getstatic 58	com/tendcloud/tenddata/b:b	Z
    //   125: ireturn
    //   126: iconst_0
    //   127: ireturn
    //   128: aload_0
    //   129: astore_1
    //   130: new 154	java/net/Socket
    //   133: dup
    //   134: ldc 166
    //   136: bipush 80
    //   138: invokespecial 161	java/net/Socket:<init>	(Ljava/lang/String;I)V
    //   141: astore_0
    //   142: goto -36 -> 106
    //   145: astore_0
    //   146: aload_1
    //   147: astore_2
    //   148: iconst_0
    //   149: putstatic 58	com/tendcloud/tenddata/b:b	Z
    //   152: aload_1
    //   153: ifnull -31 -> 122
    //   156: aload_1
    //   157: invokevirtual 164	java/net/Socket:close	()V
    //   160: goto -38 -> 122
    //   163: astore_0
    //   164: goto -42 -> 122
    //   167: astore_0
    //   168: aconst_null
    //   169: astore_2
    //   170: aload_2
    //   171: ifnull +7 -> 178
    //   174: aload_2
    //   175: invokevirtual 164	java/net/Socket:close	()V
    //   178: aload_0
    //   179: athrow
    //   180: astore_0
    //   181: goto -59 -> 122
    //   184: astore_1
    //   185: goto -7 -> 178
    //   188: astore_0
    //   189: goto -19 -> 170
    //
    // Exception table:
    //   from	to	target	type
    //   80	84	145	java/lang/Exception
    //   90	106	145	java/lang/Exception
    //   110	114	145	java/lang/Exception
    //   130	142	145	java/lang/Exception
    //   156	160	163	java/lang/Exception
    //   80	84	167	finally
    //   90	106	167	finally
    //   130	142	167	finally
    //   118	122	180	java/lang/Exception
    //   174	178	184	java/lang/Exception
    //   110	114	188	finally
    //   148	152	188	finally
  }

  public static boolean c(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.ACCESS_NETWORK_STATE"))
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      return (paramContext != null) && (1 == paramContext.getType()) && (paramContext.isConnected());
    }
    return false;
  }

  public static String d(Context paramContext)
  {
    String str;
    if (!b(paramContext))
      str = "OFFLINE";
    do
    {
      return str;
      if (c(paramContext))
        return "WIFI";
      str = e[0];
    }
    while (!x.a(paramContext, "android.permission.READ_PHONE_STATE"));
    if (a == null)
      a(paramContext);
    int i = a.getNetworkType();
    if ((i >= 0) && (i < e.length))
      return e[i];
    return e[0];
  }

  public static String e(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      if (a == null)
        a(paramContext);
      return a.getNetworkOperator();
    }
    return "";
  }

  public static String f(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      if (a == null)
        a(paramContext);
      return a.getSimOperator();
    }
    return "";
  }

  public static String g(Context paramContext)
  {
    if (x.a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      if (a == null)
        a(paramContext);
      return a.getSimOperatorName();
    }
    return "";
  }

  public static String h(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((x.a(paramContext, "android.permission.ACCESS_COARSE_LOCATION")) || (x.a(paramContext, "android.permission.ACCESS_FINE_LOCATION")));
    try
    {
      if (a == null)
        a(paramContext);
      Object localObject = a.getCellLocation();
      if ((localObject instanceof GsmCellLocation))
      {
        localObject = (GsmCellLocation)localObject;
        if (localObject != null)
        {
          localStringBuilder.append("gsm:");
          localStringBuilder.append(((GsmCellLocation)localObject).getCid()).append(':').append(((GsmCellLocation)localObject).getLac());
          if (Integer.valueOf(Build.VERSION.SDK).intValue() >= 9)
            localStringBuilder.append(':').append(a.a((GsmCellLocation)localObject));
        }
      }
      while (true)
      {
        label115: return ':' + e(paramContext);
        if ((localObject instanceof CdmaCellLocation))
        {
          localObject = (CdmaCellLocation)localObject;
          if (localObject != null)
          {
            localStringBuilder.append("cdma:");
            localStringBuilder.append(((CdmaCellLocation)localObject).getBaseStationId()).append(':').append(((CdmaCellLocation)localObject).getNetworkId()).append(':').append(((CdmaCellLocation)localObject).getSystemId()).append(':').append(((CdmaCellLocation)localObject).getBaseStationLatitude()).append(':').append(((CdmaCellLocation)localObject).getBaseStationLongitude());
          }
        }
      }
    }
    catch (Exception localException)
    {
      break label115;
    }
  }

  public static String i(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (x.a(paramContext, "android.permission.ACCESS_WIFI_STATE"))
    {
      Object localObject2 = (WifiManager)paramContext.getSystemService("wifi");
      if (((WifiManager)localObject2).isWifiEnabled())
      {
        WifiInfo localWifiInfo = ((WifiManager)localObject2).getConnectionInfo();
        Object localObject1 = null;
        paramContext = (Context)localObject1;
        char c1;
        if (localWifiInfo != null)
        {
          paramContext = (Context)localObject1;
          if (localWifiInfo.getBSSID() != null)
          {
            paramContext = localWifiInfo.getBSSID();
            localObject1 = localStringBuilder.append(paramContext).append('/').append(localWifiInfo.getRssi()).append('/').append(localWifiInfo.getSSID()).append('/');
            if (!localWifiInfo.getHiddenSSID())
              break label259;
            c1 = 'y';
            ((StringBuilder)localObject1).append(c1).append(';').append(';');
          }
        }
        localObject1 = ((WifiManager)localObject2).getScanResults();
        if (localObject1 != null)
        {
          localObject1 = ((List)localObject1).iterator();
          label151: 
          do
          {
            if (!((Iterator)localObject1).hasNext())
              break;
            localObject2 = (ScanResult)((Iterator)localObject1).next();
          }
          while ((((ScanResult)localObject2).BSSID == null) || (((ScanResult)localObject2).BSSID.equals(paramContext)));
          localObject2 = localStringBuilder.append(((ScanResult)localObject2).BSSID).append('/').append(((ScanResult)localObject2).level).append('/').append(localWifiInfo.getSSID()).append('/');
          if (localWifiInfo.getHiddenSSID());
          for (c1 = 'y'; ; c1 = 'n')
          {
            ((StringBuilder)localObject2).append(c1).append(';');
            break label151;
            label259: c1 = 'n';
            break;
          }
        }
      }
    }
    return localStringBuilder.toString();
  }

  private static class a
  {
    static long a(GsmCellLocation paramGsmCellLocation)
    {
      try
      {
        int i = paramGsmCellLocation.getPsc();
        return i;
      }
      catch (Exception paramGsmCellLocation)
      {
      }
      return -1L;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.b
 * JD-Core Version:    0.6.2
 */