package com.mediav.ads.sdk.log;

import android.content.Context;
import android.util.Log;
import java.util.HashMap;

public class MVLog
{
  private static final String TAG = "MEDIAV";
  private static Context context;
  public static boolean logcatSwitch = false;

  public static void d(String paramString)
  {
    if ((paramString != null) && (logcatSwitch))
      Log.d("MEDIAV", paramString);
  }

  public static void d(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (logcatSwitch))
      Log.d(paramString1, paramString2);
  }

  public static void e(int paramInt, String paramString)
  {
    e(paramInt, paramString, null);
  }

  public static void e(int paramInt, String paramString, Throwable paramThrowable)
  {
    if (paramString != null)
    {
      if (logcatSwitch)
        Log.e("MEDIAV", paramString);
      if (context != null)
      {
        HashMap localHashMap = new HashMap();
        int i = paramInt / 100;
        localHashMap.put("etype", i + "");
        localHashMap.put("ecode", paramInt + "");
        localHashMap.put("emsg", Utils.base64Encode(paramString));
        localHashMap.put("etime", System.currentTimeMillis() + "");
        if (paramThrowable != null)
        {
          localHashMap.put("exception", Utils.base64Encode(paramThrowable.getMessage()));
          localHashMap.put("trace", Utils.base64Encode(Utils.stackTraceToString(paramThrowable)));
        }
        localHashMap.putAll(getBasicParameters());
        new Thread(new Runnable()
        {
          public void run()
          {
            LogUploader.postLog(this.val$logdata, MVLog.context, true);
          }
        }).start();
      }
    }
  }

  public static void e(String paramString)
  {
    if (paramString != null)
      Log.e("MEDIAV", paramString);
  }

  public static void e(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (logcatSwitch))
      Log.e(paramString1, paramString2);
  }

  private static HashMap<String, String> getBasicParameters()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("apppackagename", Utils.getAppPackageName());
    localHashMap.put("appname", Utils.getAppname());
    localHashMap.put("appv", Utils.getAppVersion());
    localHashMap.put("sdkv", "1100");
    localHashMap.put("channel", "1");
    localHashMap.put("os", Utils.getSysteminfo());
    localHashMap.put("imei", Utils.getIMEI());
    localHashMap.put("imei_md5", Utils.getIMEIWhitMD5());
    localHashMap.put("imsi", Utils.getIMSI());
    localHashMap.put("imsi_md5", Utils.getIMSIWhitMD5());
    localHashMap.put("mac", Utils.getMac());
    localHashMap.put("mac_md5", Utils.getMacWhitMD5());
    localHashMap.put("model", Utils.getProductModel());
    localHashMap.put("screenwidth", Utils.getDeviceScreenSizeWithString(Boolean.valueOf(true)));
    localHashMap.put("screenheight", Utils.getDeviceScreenSizeWithString(Boolean.valueOf(false)));
    localHashMap.put("so", Utils.getScreenOrientation());
    localHashMap.put("density", Utils.getDeviceDensity() + "");
    localHashMap.put("appname", Utils.getAppname());
    localHashMap.put("apppkg", Utils.getAppPackageName());
    localHashMap.put("net", Utils.getCurrentNetWorkInfo());
    localHashMap.put("androidid", Utils.getAndroidid());
    localHashMap.put("androidid_md5", Utils.getAndroididWithMD5());
    localHashMap.put("brand", Utils.getBrand());
    localHashMap.put("carrier", Utils.getNetworkOperator());
    localHashMap.put("m2id", Utils.getm2id());
    localHashMap.put("serialid", Utils.getDeviceSerial());
    localHashMap.put("devicetype", Utils.getDeviceType() + "");
    return localHashMap;
  }

  public static void i(String paramString)
  {
    if ((paramString != null) && (logcatSwitch))
      Log.i("MEDIAV", paramString);
  }

  public static void i(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (logcatSwitch))
      Log.i(paramString1, paramString2);
  }

  public static void init(Context paramContext)
  {
    context = paramContext;
    Utils.init(context);
    new Thread(new Runnable()
    {
      public void run()
      {
        LogFileManager.uploadAllLogs(MVLog.context);
      }
    }).start();
  }

  public static void w(Exception paramException)
  {
    if ((paramException != null) && (logcatSwitch))
      paramException.printStackTrace();
  }

  public static void w(String paramString1, String paramString2)
  {
    if ((paramString2 != null) && (logcatSwitch))
      Log.w(paramString1, paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.log.MVLog
 * JD-Core Version:    0.6.2
 */