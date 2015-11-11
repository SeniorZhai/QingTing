package fm.qingting.qtradio;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import cn.com.iresearch.mapptracker.fm.IRMonitor;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.RangeRandom;
import java.lang.reflect.Field;

public class QTActivity extends Activity
{
  private Handler quitHandler = new Handler();
  private Runnable timingQuit = new Runnable()
  {
    public void run()
    {
      QTActivity.this.quit();
    }
  };
  private Runnable timingWake = new Runnable()
  {
    public void run()
    {
      QTActivity.this.quitHandler.postDelayed(QTActivity.this.timingQuit, 3000L);
    }
  };
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

  private void initIRE()
  {
    int j = 0;
    try
    {
      String str = DeviceInfo.getAndroidOsVersion();
      int i = j;
      if (str != null)
      {
        i = j;
        if (str.length() > 0)
        {
          i = j;
          if (str.charAt(0) >= '6')
            i = 1;
        }
      }
      if (i == 0)
        change(Build.DISPLAY);
      while (true)
      {
        IRMonitor.getInstance(this).Init("833c6d6eb8031de1", "qingtingFM_android", false);
        return;
        change6(Build.DISPLAY);
      }
    }
    catch (Exception localException)
    {
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.wakeHandler.postDelayed(this.timingWake, 2000L);
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
    finish();
    Process.killProcess(Process.myPid());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.QTActivity
 * JD-Core Version:    0.6.2
 */