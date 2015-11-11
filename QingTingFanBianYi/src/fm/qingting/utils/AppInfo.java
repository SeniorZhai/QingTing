package fm.qingting.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import fm.qingting.qtradio.model.GlobalCfg;

public class AppInfo
{
  public static final String OldVersionNumber = "OldVersionNumber";

  public static String getChannelName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getString(2131492868);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static String getCurrentInternalVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getString(2131492875);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static int getCurrentVersionCode(Context paramContext)
  {
    int i = 10000;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      if (paramContext != null)
        i = paramContext.versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 10000;
  }

  public static String getFirstInstalledVersionName(Context paramContext)
  {
    String str2 = GlobalCfg.getInstance(paramContext).getValueFromDB("OldVersionNumber");
    String str1 = str2;
    if (str2 == null)
    {
      recordVersion(paramContext);
      str1 = getCurrentInternalVersion(paramContext);
    }
    return str1;
  }

  public static String getVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getString(2131492874);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static void recordVersion(Context paramContext)
  {
    if (GlobalCfg.getInstance(paramContext).getValueFromDB("OldVersionNumber") == null)
    {
      String str = getCurrentInternalVersion(paramContext);
      GlobalCfg.getInstance(paramContext).setValueToDB("OldVersionNumber", "String", str);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.AppInfo
 * JD-Core Version:    0.6.2
 */