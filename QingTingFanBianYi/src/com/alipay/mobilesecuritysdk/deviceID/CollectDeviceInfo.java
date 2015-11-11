package com.alipay.mobilesecuritysdk.deviceID;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CollectDeviceInfo
{
  private static CollectDeviceInfo collectSingleton = new CollectDeviceInfo();

  public static CollectDeviceInfo getInstance()
  {
    return collectSingleton;
  }

  public String getBandVer()
  {
    try
    {
      Object localObject1 = Class.forName("android.os.SystemProperties");
      Object localObject2 = ((Class)localObject1).newInstance();
      localObject1 = (String)((Class)localObject1).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject2, new Object[] { "gsm.version.baseband", "no message" });
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String getBluMac()
  {
    try
    {
      Object localObject = BluetoothAdapter.getDefaultAdapter();
      if (localObject != null)
      {
        boolean bool = ((BluetoothAdapter)localObject).isEnabled();
        if (!bool)
          return "";
      }
      localObject = ((BluetoothAdapter)localObject).getAddress();
      return localObject;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String getCpuFre()
  {
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "";
    arrayOfString1[1] = "";
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
      String[] arrayOfString2 = localBufferedReader.readLine().split("\\s+");
      int i = 2;
      while (true)
      {
        if (i >= arrayOfString2.length)
        {
          arrayOfString2 = localBufferedReader.readLine().split("\\s+");
          arrayOfString1[1] = (arrayOfString1[1] + arrayOfString2[2]);
          localBufferedReader.close();
          return arrayOfString1[1];
        }
        arrayOfString1[0] = (arrayOfString1[0] + arrayOfString2[i] + " ");
        i += 1;
      }
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  // ERROR //
  public String getCpuNum()
  {
    // Byte code:
    //   0: ldc 64
    //   2: astore_1
    //   3: new 74	java/io/FileReader
    //   6: dup
    //   7: ldc 76
    //   9: invokespecial 79	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   12: astore 4
    //   14: aload 4
    //   16: ifnull +121 -> 137
    //   19: aload_1
    //   20: astore_2
    //   21: new 72	java/io/BufferedReader
    //   24: dup
    //   25: aload 4
    //   27: sipush 1024
    //   30: invokespecial 82	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   33: astore 5
    //   35: aload_1
    //   36: astore_2
    //   37: aload 5
    //   39: invokevirtual 85	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore_3
    //   43: aload_3
    //   44: astore_2
    //   45: aload_3
    //   46: astore_1
    //   47: aload 5
    //   49: invokevirtual 108	java/io/BufferedReader:close	()V
    //   52: aload_3
    //   53: astore_2
    //   54: aload_3
    //   55: astore_1
    //   56: aload 4
    //   58: invokevirtual 114	java/io/FileReader:close	()V
    //   61: aload_3
    //   62: astore_1
    //   63: aload_1
    //   64: ifnull +50 -> 114
    //   67: aload_1
    //   68: aload_1
    //   69: bipush 58
    //   71: invokevirtual 118	java/lang/String:indexOf	(I)I
    //   74: iconst_1
    //   75: iadd
    //   76: invokevirtual 122	java/lang/String:substring	(I)Ljava/lang/String;
    //   79: invokevirtual 125	java/lang/String:trim	()Ljava/lang/String;
    //   82: areturn
    //   83: astore_3
    //   84: aload_1
    //   85: astore_2
    //   86: ldc 127
    //   88: new 93	java/lang/StringBuilder
    //   91: dup
    //   92: ldc 128
    //   94: invokespecial 98	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   97: aload_3
    //   98: invokevirtual 131	java/io/IOException:getLocalizedMessage	()Ljava/lang/String;
    //   101: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   107: invokestatic 137	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   110: pop
    //   111: goto -48 -> 63
    //   114: ldc 64
    //   116: areturn
    //   117: astore_2
    //   118: goto -55 -> 63
    //   121: astore_1
    //   122: aload_2
    //   123: astore_1
    //   124: goto -61 -> 63
    //   127: astore_1
    //   128: aload_2
    //   129: astore_1
    //   130: goto -67 -> 63
    //   133: astore_3
    //   134: goto -50 -> 84
    //   137: goto -74 -> 63
    //
    // Exception table:
    //   from	to	target	type
    //   21	35	83	java/io/IOException
    //   3	14	117	java/io/FileNotFoundException
    //   21	35	121	java/io/FileNotFoundException
    //   86	111	121	java/io/FileNotFoundException
    //   37	43	127	java/io/FileNotFoundException
    //   47	52	127	java/io/FileNotFoundException
    //   56	61	127	java/io/FileNotFoundException
    //   37	43	133	java/io/IOException
    //   47	52	133	java/io/IOException
    //   56	61	133	java/io/IOException
  }

  public String getDeviceMx(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = Integer.toString(paramContext.widthPixels) + "*" + Integer.toString(paramContext.heightPixels);
      return paramContext;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public String getImei(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null)
        localObject1 = paramContext.getDeviceId();
      return localObject1;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public String getImsi(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null);
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null)
        localObject1 = paramContext.getSubscriberId();
      return localObject1;
    }
    catch (Exception paramContext)
    {
    }
    return null;
  }

  public String getMacAddress(Context paramContext)
  {
    return ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
  }

  public String getNetworkType(Context paramContext)
  {
    return Integer.toString(((TelephonyManager)paramContext.getSystemService("phone")).getNetworkType());
  }

  public String getOsVer()
  {
    return Build.VERSION.RELEASE;
  }

  public String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }

  public String getPhoneModel()
  {
    return Build.MODEL;
  }

  public String getResolution(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().toString();
  }

  public String getRomName()
  {
    return Build.DISPLAY;
  }

  public long getSDCardMemory()
  {
    long[] arrayOfLong = new long[2];
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l1 = localStatFs.getBlockSize();
        long l2 = localStatFs.getBlockCount();
        long l3 = localStatFs.getAvailableBlocks();
        arrayOfLong[0] = (l1 * l2);
        arrayOfLong[1] = (l1 * l3);
      }
      label63: return arrayOfLong[0];
    }
    catch (Exception localException)
    {
      break label63;
    }
  }

  public String getSDKVer()
  {
    try
    {
      i = Build.VERSION.class.getField("SDK_INT").getInt(null);
      return Integer.toString(i);
    }
    catch (Exception localException1)
    {
      while (true)
        try
        {
          i = Integer.parseInt((String)Build.VERSION.class.getField("SDK").get(null));
        }
        catch (Exception localException2)
        {
          int i = 2;
        }
    }
  }

  public long getTotalMemory()
  {
    long l2 = 0L;
    long l1 = l2;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      l1 = l2;
      l2 = Integer.valueOf(localBufferedReader.readLine().split("\\s+")[1]).intValue();
      l1 = l2;
      localBufferedReader.close();
      return l2;
    }
    catch (IOException localIOException)
    {
      Log.i("deviceid", "getTotalMemory" + localIOException.getLocalizedMessage());
    }
    return l1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.CollectDeviceInfo
 * JD-Core Version:    0.6.2
 */