package fm.qingting.framework.controller;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class StatisticsFMManage extends Application
{
  private static final String KEY_AVAIABLE = "key_avaiable";
  private static final String KEY_FM = "";
  private static final String KEY_FMTIME = "key_fmtime";
  private static final String KEY_INSTALL = "key_install";
  private static final String KEY_ISSEBDUSE = "usefm";
  private static final String KEY_ISUSED = "key_isused";
  private static final String KEY_NETTIME = "key_nettime";
  private static final String KEY_SCANTIME = "key_scantime";
  private static final String KEY_SENDUM = "key_sendum";
  private static final String KEY_VERSION = "key_vertionfm";
  private static final long MillToMin = 60000L;
  private static StatisticsFMManage instance;
  private SharedPreferences.Editor editor;
  private Context mContext;
  private boolean mUsedFM = false;
  private boolean mUsedNET = false;
  private SharedPreferences sharedPreferences;

  private StatisticsFMManage(Context paramContext)
  {
    this.mContext = paramContext;
    this.sharedPreferences = ((Activity)paramContext).getPreferences(0);
    this.editor = this.sharedPreferences.edit();
  }

  public static StatisticsFMManage getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new StatisticsFMManage(paramContext);
    return instance;
  }

  private void sendMessage()
  {
  }

  public void clearFMtime()
  {
    this.editor.putLong("key_fmtime", 0L);
    this.editor.commit();
  }

  public void clearNETtime()
  {
    this.editor.putLong("key_nettime", 0L);
    this.editor.commit();
  }

  public void clearScanTime()
  {
    this.editor.putLong("key_scantime", 0L);
    this.editor.commit();
  }

  public boolean getAvaiable()
  {
    return this.sharedPreferences.getBoolean("key_avaiable", true);
  }

  public String getFMTimeDistribution()
  {
    long l = getFmtime();
    if (0L == l)
      return null;
    double d = l / 60000.0D;
    if (d < 1.0D)
      return "FM_LESS_THAN_1";
    if (d < 10.0D)
      return "FM_LESS_THAN_10";
    if (d < 30.0D)
      return "FM_LESS_THAN_30";
    return "FM_MORE_THAN_30";
  }

  public long getFmtime()
  {
    return this.sharedPreferences.getLong("key_fmtime", 0L);
  }

  public boolean getISSendUM()
  {
    return this.sharedPreferences.getBoolean("key_sendum", false);
  }

  public boolean getISSendUse()
  {
    return this.sharedPreferences.getBoolean("usefm" + getVertion(), true);
  }

  public boolean getISUsed()
  {
    return this.sharedPreferences.getBoolean("key_isused" + getVertion(), false);
  }

  public String getInstallation()
  {
    return this.sharedPreferences.getString("key_install", "yes");
  }

  public long getNETtime()
  {
    return this.sharedPreferences.getLong("key_nettime", 0L);
  }

  public long getScanTime()
  {
    return this.sharedPreferences.getLong("key_scantime", 0L);
  }

  public String getUM()
  {
    return this.sharedPreferences.getString("", "yes");
  }

  public int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }

  public String getVertion()
  {
    return this.sharedPreferences.getString("key_vertionfm", "");
  }

  public boolean hasUsedFm()
  {
    return this.mUsedFM;
  }

  public boolean hasUsedNET()
  {
    return this.mUsedNET;
  }

  public void init(Context paramContext)
  {
  }

  public boolean isNeeDSendUse()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (getISSendUse())
    {
      bool1 = bool2;
      if (this.mUsedFM)
      {
        setSendUse(false);
        bool1 = true;
      }
    }
    return bool1;
  }

  public boolean isNeedSendAvailable()
  {
    boolean bool = false;
    if (getAvaiable())
    {
      setAvaiable(false);
      bool = true;
    }
    return bool;
  }

  public boolean isNeedSendUM()
  {
    return false;
  }

  public boolean isUsed()
  {
    return this.mUsedFM;
  }

  public void setAvaiable(boolean paramBoolean)
  {
    this.editor.putBoolean("key_avaiable", paramBoolean);
    this.editor.commit();
  }

  public void setFMtime(long paramLong)
  {
    paramLong = getFmtime() + paramLong;
    this.editor.putLong("key_fmtime", paramLong);
    this.editor.commit();
    if (paramLong > 0L)
      sendMessage();
  }

  public void setInstallation(String paramString)
  {
    this.editor.putString("key_install", paramString);
    this.editor.commit();
  }

  public void setNETtime(long paramLong)
  {
    paramLong = getNETtime() + paramLong;
    this.editor.putLong("key_nettime", paramLong);
    this.editor.commit();
    if (paramLong > 60000L)
      this.mUsedNET = true;
  }

  public void setScanDuration(long paramLong)
  {
    long l = getScanTime();
    this.editor.putLong("key_scantime", l + paramLong);
    this.editor.commit();
  }

  public void setSendUM(boolean paramBoolean)
  {
    this.editor.putBoolean("key_sendum", paramBoolean);
    this.editor.commit();
  }

  public void setSendUse(boolean paramBoolean)
  {
    this.editor.putBoolean("usefm" + getVertion(), paramBoolean);
    this.editor.commit();
  }

  public void setUM(String paramString)
  {
    this.editor.putString("", paramString);
    this.editor.commit();
  }

  public void setUsed(boolean paramBoolean)
  {
    this.mUsedFM = paramBoolean;
    this.editor.putBoolean("key_isused" + getVertion(), paramBoolean);
    this.editor.commit();
    Log.e("statics.java", "setUsed: " + paramBoolean);
  }

  public void setVertion(Context paramContext)
  {
    paramContext = "version" + String.valueOf(getVersionCode(paramContext));
    this.editor.putString("key_vertionfm", paramContext);
    this.editor.commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.StatisticsFMManage
 * JD-Core Version:    0.6.2
 */