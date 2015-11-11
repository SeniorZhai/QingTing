package fm.qingting.framework.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.UUID;

public class SystemInfo
{
  private static SystemInfo instance;
  private Activity activity;

  public static String getCpuName()
  {
    try
    {
      Object localObject = new BufferedReader(new FileReader("/proc/cpuinfo")).readLine().split(":\\s+", 2);
      int i = 0;
      while (true)
      {
        if (i >= localObject.length)
        {
          localObject = localObject[1];
          return localObject;
        }
        i += 1;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return null;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public static String getCurCpuFreq()
  {
    try
    {
      String str = new BufferedReader(new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")).readLine().trim();
      return str;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return "N/A";
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return "N/A";
  }

  public static SystemInfo getInstance()
  {
    if (instance == null)
      instance = new SystemInfo();
    return instance;
  }

  public static String getMinCpuFreq()
  {
    String str1 = "";
    try
    {
      InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" }).start().getInputStream();
      byte[] arrayOfByte = new byte[24];
      while (true)
      {
        if (localInputStream.read(arrayOfByte) == -1)
        {
          localInputStream.close();
          return str1.trim();
        }
        str1 = str1 + new String(arrayOfByte);
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        localIOException.printStackTrace();
        String str2 = "N/A";
      }
    }
  }

  public static String getUUID(Context paramContext)
  {
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    String str;
    if (((TelephonyManager)localObject).getDeviceId() == null)
    {
      str = "unknown";
      if (((TelephonyManager)localObject).getSimSerialNumber() != null)
        break label85;
      localObject = "unknown";
      label30: paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      if (paramContext != null)
        break label93;
      paramContext = "unknown";
    }
    label85: label93: 
    while (true)
    {
      return new UUID(paramContext.hashCode(), str.hashCode() << 32 | ((String)localObject).hashCode()).toString();
      str = ((TelephonyManager)localObject).getDeviceId();
      break;
      localObject = ((TelephonyManager)localObject).getSimSerialNumber();
      break label30;
    }
  }

  public String getActivityManager()
  {
    StringBuffer localStringBuffer = new StringBuffer("/n======/nActivityManager/n");
    List localList = ((ActivityManager)this.activity.getSystemService("activity")).getRunningAppProcesses();
    int i = 0;
    while (true)
    {
      if (i >= localList.size())
        return localStringBuffer.toString();
      localStringBuffer.append(((ActivityManager.RunningAppProcessInfo)localList.get(i)).toString() + "/n");
      i += 1;
    }
  }

  public long getAvailMem(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    paramContext.getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1048576L;
  }

  public String getCpu()
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/proc/cpuinfo" }).start().getInputStream();
      str1 = str2;
      byte[] arrayOfByte = new byte[1024];
      str1 = str2;
      if (localInputStream.read(arrayOfByte) != -1)
      {
        str1 = str2;
        System.out.println(new String(arrayOfByte));
        str1 = str2;
        str2 = "" + new String(arrayOfByte);
        str1 = str2;
        return str2.toString();
      }
      str1 = str2;
      localInputStream.close();
      str1 = str2;
      return str1.toString();
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public float getMaxCpuFreq()
  {
    String str1 = "";
    try
    {
      InputStream localInputStream = new ProcessBuilder(new String[] { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" }).start().getInputStream();
      arrayOfByte = new byte[24];
      if (localInputStream.read(arrayOfByte) == -1)
        localInputStream.close();
    }
    catch (IOException localIOException)
    {
      try
      {
        while (true)
        {
          byte[] arrayOfByte;
          float f = Float.parseFloat(str1);
          return f;
          str1 = str1 + new String(arrayOfByte);
          continue;
          localIOException = localIOException;
          localIOException.printStackTrace();
          String str2 = "N/A";
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
    }
    return 0.0F;
  }

  public String getNetWorkIP()
  {
    Object localObject = new CMDExecute();
    try
    {
      localObject = ((CMDExecute)localObject).run(new String[] { "/system/bin/netcfg" }, "/system/bin/");
      return localObject;
    }
    catch (IOException localIOException)
    {
    }
    return "/n======/n";
  }

  public float getOldBrightness()
  {
    try
    {
      int i = Settings.System.getInt(this.activity.getContentResolver(), "screen_brightness");
      return i;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
    }
    return 255.0F;
  }

  public String getTelephonyManager(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    return (new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf(new StringBuilder(String.valueOf("/n======/nTelephonyManager/n")).append("getDataState() = ").append(paramContext.getDataState()).append("/n").toString())).append("getDeviceId() = ").append(paramContext.getDeviceId()).append("/n").toString())).append("getLine1Number() = ").append(paramContext.getLine1Number()).append("/n").toString())).append("getSimSerialNumber() = ").append(paramContext.getSimSerialNumber()).append("/n").toString())).append("getSubscriberId() = ").append(paramContext.getSubscriberId()).append("/n").toString() + "isNetworkRoaming() = " + paramContext.isNetworkRoaming() + "/n").toString();
  }

  public void getTotalMemory()
  {
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          return;
        Log.e("test.java", "---" + str);
      }
    }
    catch (IOException localIOException)
    {
    }
  }

  public String getWifiInfo()
  {
    WifiManager localWifiManager = (WifiManager)this.activity.getSystemService("wifi");
    StringBuilder localStringBuilder = new StringBuilder("/nState:");
    if (localWifiManager.getWifiState() != 3)
      return "/n======/nWifiState:/nWIFI_STATE_UNKNOWN/n";
    return "/n======/nWifiState:/nWIFI_STATE_KNOWN/n".toString();
  }

  public void init(Activity paramActivity)
  {
    this.activity = paramActivity;
  }

  public boolean isConnected()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)this.activity.getSystemService("connectivity");
    TelephonyManager localTelephonyManager = (TelephonyManager)this.activity.getSystemService("phone");
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localConnectivityManager.getBackgroundDataSetting()));
    int i;
    int j;
    do
    {
      return false;
      i = localNetworkInfo.getType();
      j = localNetworkInfo.getSubtype();
      if (i == 1)
        return localNetworkInfo.isConnected();
    }
    while ((i != 0) || (j != 3) || (localTelephonyManager.isNetworkRoaming()));
    return localNetworkInfo.isConnected();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.SystemInfo
 * JD-Core Version:    0.6.2
 */