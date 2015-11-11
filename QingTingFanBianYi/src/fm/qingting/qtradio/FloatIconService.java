package fm.qingting.qtradio;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Handler;
import android.os.IBinder;
import fm.qingting.qtradio.view.floaticon.FloatViewManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class FloatIconService extends Service
{
  private Handler handler = new Handler();
  private List<String> mHomeNames;
  private Timer timer;

  private List<String> getHomes()
  {
    if (this.mHomeNames == null)
    {
      this.mHomeNames = new ArrayList();
      Object localObject1 = getPackageManager();
      Object localObject2 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject2).addCategory("android.intent.category.HOME");
      localObject1 = ((PackageManager)localObject1).queryIntentActivities((Intent)localObject2, 65536).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        this.mHomeNames.add(((ResolveInfo)localObject2).activityInfo.packageName);
      }
    }
    return this.mHomeNames;
  }

  private boolean isHome()
  {
    List localList = ((ActivityManager)getSystemService("activity")).getRunningTasks(1);
    return getHomes().contains(((ActivityManager.RunningTaskInfo)localList.get(0)).topActivity.getPackageName());
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.timer.cancel();
    this.timer = null;
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (this.timer == null)
    {
      this.timer = new Timer();
      this.timer.scheduleAtFixedRate(new RefreshTask(), 0L, 1000L);
    }
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  class RefreshTask extends TimerTask
  {
    RefreshTask()
    {
    }

    public void run()
    {
      boolean bool = FloatIconService.this.isHome();
      if ((bool) && (!FloatViewManager.INSTANCE.isWindowShowing()))
        FloatIconService.this.handler.post(new Runnable()
        {
          public void run()
          {
            FloatViewManager.INSTANCE.createSmallWindow(FloatIconService.this.getApplicationContext());
          }
        });
      do
      {
        return;
        if ((!bool) && (FloatViewManager.INSTANCE.isWindowShowing()))
        {
          FloatIconService.this.handler.post(new Runnable()
          {
            public void run()
            {
              FloatViewManager.INSTANCE.removeSmallWindow(FloatIconService.this.getApplicationContext());
              FloatViewManager.INSTANCE.removeBigWindow(FloatIconService.this.getApplicationContext());
            }
          });
          return;
        }
      }
      while ((!bool) || (!FloatViewManager.INSTANCE.isWindowShowing()));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.FloatIconService
 * JD-Core Version:    0.6.2
 */