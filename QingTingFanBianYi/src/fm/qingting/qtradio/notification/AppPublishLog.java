package fm.qingting.qtradio.notification;

import android.content.Context;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.utils.DeviceInfo;

public class AppPublishLog
{
  public static final String DownloadApp = "DownloadApp";
  public static final String InstallApp = "InstallApp";
  public static final String WakeupApp = "WakeupApp";

  public static void sendDownloadAppLog(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    String str = "" + "\"";
    paramContext = str + DeviceInfo.getUniqueId(paramContext);
    paramContext = paramContext + "\"";
    paramContext = paramContext + ",";
    paramContext = paramContext + "\"";
    paramContext = paramContext + paramString;
    paramContext = paramContext + "\"";
    paramContext = paramContext + "\n";
    LogModule.getInstance().send("DownloadApp", paramContext);
  }

  public static void sendInstallAppLog(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    String str = "" + "\"";
    paramContext = str + DeviceInfo.getUniqueId(paramContext);
    paramContext = paramContext + "\"";
    paramContext = paramContext + ",";
    paramContext = paramContext + "\"";
    paramContext = paramContext + paramString;
    paramContext = paramContext + "\"";
    paramContext = paramContext + "\n";
    LogModule.getInstance().send("InstallApp", paramContext);
  }

  public static void sendWakeupAppLog(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    String str = "" + "\"";
    paramContext = str + DeviceInfo.getUniqueId(paramContext);
    paramContext = paramContext + "\"";
    paramContext = paramContext + ",";
    paramContext = paramContext + "\"";
    paramContext = paramContext + paramString;
    paramContext = paramContext + "\"";
    paramContext = paramContext + "\n";
    LogModule.getInstance().send("WakeupApp", paramContext);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.AppPublishLog
 * JD-Core Version:    0.6.2
 */