package fm.qingting.qtradio.notification;

import android.util.Log;
import fm.qingting.qtradio.NotificationService;

public class MessageManager
{
  private boolean hasPaused = false;
  private Thread msgThread;
  private final NotificationService notificationService;

  public MessageManager(NotificationService paramNotificationService)
  {
    this.notificationService = paramNotificationService;
    this.hasPaused = false;
  }

  public void pauseThread()
  {
    if (this.msgThread == null);
    do
    {
      return;
      Log.e("clock", "messageMgr.pauseThread");
    }
    while (!this.msgThread.isAlive());
    this.hasPaused = true;
    this.msgThread.interrupt();
  }

  public void restartThread()
  {
    Log.e("clock", "messageMgr.restartThread");
    if (this.msgThread != null)
      this.msgThread.interrupt();
    this.msgThread = new MessageThread(this.notificationService);
    this.msgThread.start();
    this.hasPaused = false;
  }

  public void restartThreadIfNeed()
  {
    if (this.hasPaused)
      restartThread();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.MessageManager
 * JD-Core Version:    0.6.2
 */