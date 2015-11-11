package fm.qingting.nativec;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import fm.qingting.qtradio.abtest.ABTest;
import fm.qingting.qtradio.abtest.ABTestConfig;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.utils.AppInfo;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.ProcessDetect;

public class WatchDog
{
  private static final int EnableDebug = 0;
  private static String LogApiUrl = "http://logger.qingting.fm/logger.php";
  private static final int MonitorInterval = 900;
  private static Context _context;
  private static String _packageName;
  private static String _processName;
  private static String _serviceName;

  public static void monitor(String paramString1, String paramString2, String paramString3, Context paramContext)
  {
    _context = paramContext;
    _processName = paramString1;
    _serviceName = paramString2;
    _packageName = paramString3;
    new Thread()
    {
      public void run()
      {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        try
        {
          sleep(5000L);
          str2 = WatchDog._packageName + "/" + "ihaveadreamwatchdog";
          if (ProcessDetect.processExists(str2, null))
          {
            Log.e("WatchDog", "watch dog is running. Don't launch it twice.");
            return;
          }
        }
        catch (InterruptedException localInterruptedException1)
        {
          while (true)
            localInterruptedException1.printStackTrace();
          str3 = Environment.getExternalStorageDirectory() + "/log.txt";
          str4 = DeviceInfo.getUniqueId(WatchDog._context);
          str5 = AppInfo.getCurrentInternalVersion(WatchDog._context);
          str6 = WatchDog._context.getString(2131492869);
          str7 = DeviceInfo.getDeviceName().replace(" ", "-");
          str8 = DeviceInfo.getAndroidOsVersion();
          str1 = ABTest.buildUserTypeString(WatchDog._context, ABTestConfig.items).trim();
          if (str1.length() != 0)
            break label430;
        }
        String str1 = "NA";
        label430: 
        while (true)
        {
          String str9 = AppInfo.getFirstInstalledVersionName(WatchDog._context);
          Context localContext = WatchDog._context;
          String str10 = WatchDog._context.getPackageName();
          String str11 = WatchDog._processName;
          String str12 = WatchDog._serviceName;
          String str13 = WatchDog._packageName;
          String str14 = WatchDog.LogApiUrl;
          String str15 = WatchDog._packageName;
          NativeLaucher.RunExecutable(localContext, str10, "libwatchdog.so", "ihaveadreamwatchdog", str3, new String[] { str11, str12, str13, str14, String.valueOf(900), String.valueOf(0) }, new String[] { "+8", "Android", str4, "NA", str5, str6, str7, str8, "NA", "NA", "NA", "NA", "NA", "NA", "NA", str1, "NA", "0", str15, str9 });
          try
          {
            sleep(5000L);
            if (!ProcessDetect.processExists(str2, null))
            {
              Log.e("WatchDog", "watch dog not launched. There may be some error with it.");
              str1 = QTLogger.getInstance().buildCommonLog();
              str1 = str1.substring(0, str1.length() - 1);
              LogModule.getInstance().send("WatchdogNotLaunched", str1);
              return;
            }
          }
          catch (InterruptedException localInterruptedException2)
          {
            localInterruptedException2.printStackTrace();
            return;
          }
          Log.e("WatchDog", "watch dog starts running now.");
          return;
        }
      }
    }
    .start();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.nativec.WatchDog
 * JD-Core Version:    0.6.2
 */