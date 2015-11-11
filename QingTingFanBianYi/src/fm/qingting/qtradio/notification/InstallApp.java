package fm.qingting.qtradio.notification;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.utils.DateUtil;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public class InstallApp
{
  private static InstallApp _instance;
  private boolean hasInstall = false;
  private boolean hasStarted = false;
  private Context mContext;

  public static InstallApp getInstance()
  {
    if (_instance == null)
      _instance = new InstallApp();
    return _instance;
  }

  private void log(String paramString)
  {
  }

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void install()
  {
    try
    {
      if (this.mContext != null)
      {
        if (this.hasInstall)
          return;
        this.hasInstall = true;
        Object localObject1 = Environment.getExternalStorageDirectory();
        localObject1 = new File(localObject1 + File.separator + "system_");
        if (((File)localObject1).exists())
        {
          localObject1 = new File((File)localObject1, "1.apk");
          if (((File)localObject1).exists())
          {
            String str = GlobalCfg.getInstance(this.mContext).getSellUrl();
            log(str);
            Object localObject2 = GlobalCfg.getInstance(this.mContext).getSellUrlInstall();
            log((String)localObject2);
            if ((str != null) && (localObject2 != null))
            {
              if (str.equalsIgnoreCase((String)localObject2));
            }
            else
            {
              boolean bool;
              do
              {
                localObject2 = new Intent("android.intent.action.VIEW");
                ((Intent)localObject2).setFlags(268435456);
                ((Intent)localObject2).setDataAndType(Uri.fromFile((File)localObject1), "application/vnd.android.package-archive");
                this.mContext.startActivity((Intent)localObject2);
                GlobalCfg.getInstance(this.mContext).setSellUrlInstall(str);
                localObject1 = GlobalCfg.getInstance(this.mContext).getSellAppsPackage();
                AppPublishLog.sendInstallAppLog(this.mContext, (String)localObject1);
                return;
                if (str == null)
                  break;
                bool = str.equalsIgnoreCase("");
              }
              while (!bool);
            }
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void startApp()
  {
    if ((this.mContext == null) || (this.hasStarted))
    {
      return;
      break label95;
    }
    label95: 
    do
    {
      PackageManager localPackageManager;
      Object localObject2;
      do
      {
        do
        {
          do
          {
            do
            {
              localObject1 = GlobalCfg.getInstance(this.mContext).getSellAppsPackage();
              if ((localObject1 == null) || (((String)localObject1).equalsIgnoreCase("")))
              {
                this.hasStarted = true;
                return;
              }
            }
            while (!DateUtil.isDifferentDayMs(GlobalCfg.getInstance(this.mContext).getSellAppsStart(), DateUtil.getCurrentMillis()));
            localPackageManager = this.mContext.getPackageManager();
          }
          while (localPackageManager == null);
          localObject2 = localPackageManager.getInstalledApplications(8192);
        }
        while (localObject2 == null);
        localObject2 = ((List)localObject2).iterator();
      }
      while (!((Iterator)localObject2).hasNext());
      Object localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
      if (((((ApplicationInfo)localObject3).flags & 0x80) != 0) || ((((ApplicationInfo)localObject3).flags & 0x1) != 0))
        break;
      localObject3 = ((ApplicationInfo)localObject3).packageName;
      if ((localObject3 == null) || (!((String)localObject3).equalsIgnoreCase((String)localObject1)))
        break;
      localObject1 = localPackageManager.getLaunchIntentForPackage((String)localObject3);
    }
    while (localObject1 == null);
    GlobalCfg.getInstance(this.mContext).setSellAppsStart(DateUtil.getCurrentMillis());
    this.mContext.startActivity((Intent)localObject1);
    this.hasStarted = true;
    Object localObject1 = GlobalCfg.getInstance(this.mContext).getSellAppsPackage();
    AppPublishLog.sendWakeupAppLog(this.mContext, (String)localObject1);
  }

  public void startPackage()
  {
    try
    {
      boolean bool = this.hasInstall;
      if (bool);
      return;
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.InstallApp
 * JD-Core Version:    0.6.2
 */