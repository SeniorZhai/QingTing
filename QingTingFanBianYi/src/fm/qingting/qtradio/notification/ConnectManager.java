package fm.qingting.qtradio.notification;

import fm.qingting.qtradio.NotificationService;

public class ConnectManager
{
  private Thread connectthread;
  private boolean mPushSwitch = true;
  private final NotificationService notificationService;

  public ConnectManager(NotificationService paramNotificationService, boolean paramBoolean)
  {
    this.notificationService = paramNotificationService;
    this.mPushSwitch = paramBoolean;
  }

  public void restartThread()
  {
    try
    {
      if (!this.mPushSwitch)
        return;
      if (this.connectthread == null)
      {
        this.connectthread = new ConnectThread(this.notificationService);
        this.connectthread.start();
        return;
      }
    }
    catch (Exception localException)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.ConnectManager
 * JD-Core Version:    0.6.2
 */