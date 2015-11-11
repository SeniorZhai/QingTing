package fm.qingting.qtradio.notification;

import android.content.Context;
import fm.qingting.nativec.WatchDog;

public class WatchDogThread extends Thread
{
  private Context mContext;
  private long wait = 5000L;

  public WatchDogThread(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void run()
  {
    try
    {
      while (!isInterrupted())
      {
        Thread.sleep(this.wait);
        if (this.mContext != null)
          WatchDog.monitor(this.mContext.getPackageName() + ":notification", this.mContext.getPackageName() + ".NotificationService", this.mContext.getPackageName(), this.mContext);
      }
      return;
    }
    catch (Exception localException)
    {
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.WatchDogThread
 * JD-Core Version:    0.6.2
 */