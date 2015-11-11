package fm.qingting.qtradio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import cn.com.iresearch.mapptracker.fm.IRMonitor;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.ServerConfig;
import fm.qingting.qtradio.data.CommonDS;
import fm.qingting.qtradio.data.DBManager;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.RangeRandom;
import java.lang.reflect.Field;

public class ShieldActivity extends Activity
{
  private boolean enableIRE = true;
  private Context mContext;
  private String mTCKey = null;
  private Handler quitHandler = new Handler();
  private Runnable timingQuit = new Runnable()
  {
    public void run()
    {
      ShieldActivity.this.quit();
    }
  };
  private Runnable timingWake = new Runnable()
  {
    public void run()
    {
      if (ShieldActivity.this.mContext != null)
      {
        if (!ShieldActivity.this.useTc)
        {
          MobclickAgent.flush(ShieldActivity.this.mContext);
          MobclickAgent.onEvent(ShieldActivity.this.mContext, "shieldv2");
        }
        TCAgent.onEvent(ShieldActivity.this.mContext, "shieldv2");
      }
      ShieldActivity.this.quitHandler.postDelayed(ShieldActivity.this.timingQuit, 2000L);
    }
  };
  private boolean useTc = false;
  private Handler wakeHandler = new Handler();

  private void change(String paramString)
  {
    Object localObject = paramString.getClass();
    while (true)
    {
      try
      {
        Field localField1 = ((Class)localObject).getDeclaredField("value");
        Field localField2 = ((Class)localObject).getDeclaredField("count");
        localField2.setAccessible(true);
        localField1.setAccessible(true);
        localObject = paramString + "CM";
        int j = (int)(System.currentTimeMillis() / 1000L % 7);
        if (j == 0)
        {
          return;
          if (i < j)
          {
            localObject = (String)localObject + String.valueOf(i);
            i += 1;
            continue;
          }
          localObject = ((String)localObject).toCharArray();
          localField2.set(paramString, Integer.valueOf(localObject.length));
          localField1.set(paramString, localObject);
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      int i = 0;
    }
  }

  private void change6(String paramString)
  {
    Object localObject = paramString.getClass();
    try
    {
      localObject = ((Class)localObject).getDeclaredField("count");
      ((Field)localObject).setAccessible(true);
      int i = (int)RangeRandom.Random(paramString.length());
      if (i <= 1)
        return;
      ((Field)localObject).set(paramString, Integer.valueOf(i));
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  private void handleMessageOnCreate(Intent paramIntent)
  {
    if (paramIntent == null);
    do
    {
      do
      {
        return;
        paramIntent = paramIntent.getExtras();
      }
      while (paramIntent == null);
      paramIntent = paramIntent.getString("prometheus");
    }
    while ((paramIntent == null) || (paramIntent.equalsIgnoreCase("")));
    this.useTc = true;
    this.mTCKey = paramIntent;
  }

  private void initApollo()
  {
    if ((this.mTCKey != null) && (!this.mTCKey.equalsIgnoreCase("")))
      setDisplayEffect(this.mTCKey);
  }

  private void initIRE()
  {
    String str = DeviceInfo.getAndroidOsVersion();
    if ((str != null) && (str.length() > 0) && (str.charAt(0) >= '6'));
    for (int i = 1; ; i = 0)
    {
      if (i == 0)
        change(Build.DISPLAY);
      while (true)
      {
        IRMonitor.getInstance(this).Init("833c6d6eb8031de1", "qingtingFM_android", false);
        this.enableIRE = true;
        return;
        change6(Build.DISPLAY);
      }
    }
  }

  private void setDisplayEffect(String paramString)
  {
    try
    {
      Object localObject = getSharedPreferences("tdid", 0);
      if (localObject != null)
      {
        localObject = ((SharedPreferences)localObject).edit();
        ((SharedPreferences.Editor)localObject).putString("pref.deviceid.key", paramString);
        ((SharedPreferences.Editor)localObject).commit();
      }
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    handleMessageOnCreate(getIntent());
    if (!this.useTc)
    {
      MobclickAgent.openActivityDurationTrack(false);
      MobclickAgent.setDebugMode(false);
    }
    DBManager.getInstance().init(this);
    paramBundle = getResources().openRawResource(2131165187);
    ServerConfig.getInstance().setServerConfig(paramBundle);
    DataManager.getInstance().addDataSource(CommonDS.getInstance());
    initApollo();
    TCAgent.init(this);
    LogModule.getInstance().init(this);
    QTLogger.getInstance().setContext(this);
    this.wakeHandler.postDelayed(this.timingWake, 2000L);
    this.mContext = this;
    initIRE();
  }

  public void onDestroy()
  {
    super.onDestroy();
    Process.killProcess(Process.myPid());
  }

  public void onPause()
  {
    try
    {
      super.onPause();
      if (!this.useTc)
        MobclickAgent.onPause(this);
      TCAgent.onPause(this);
      if (this.enableIRE)
        IRMonitor.getInstance(this).onPause();
      return;
    }
    catch (Exception localException)
    {
    }
    catch (Error localError)
    {
    }
  }

  public void onResume()
  {
    try
    {
      super.onResume();
      if (!this.useTc)
        MobclickAgent.onResume(this);
      TCAgent.onResume(this);
      if (this.enableIRE)
        IRMonitor.getInstance(this).onResume();
      return;
    }
    catch (Exception localException)
    {
    }
    catch (Error localError)
    {
    }
  }

  public void quit()
  {
    if (!this.useTc)
      MobclickAgent.onKillProcess(this);
    finish();
    Process.killProcess(Process.myPid());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.ShieldActivity
 * JD-Core Version:    0.6.2
 */