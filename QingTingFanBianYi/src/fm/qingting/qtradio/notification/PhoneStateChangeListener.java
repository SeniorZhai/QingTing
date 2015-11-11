package fm.qingting.qtradio.notification;

import android.telephony.PhoneStateListener;
import fm.qingting.qtradio.NotificationService;

public class PhoneStateChangeListener extends PhoneStateListener
{
  private final NotificationService notificationService;

  public PhoneStateChangeListener(NotificationService paramNotificationService)
  {
    this.notificationService = paramNotificationService;
  }

  public void onDataConnectionStateChanged(int paramInt)
  {
    super.onDataConnectionStateChanged(paramInt);
    if (paramInt == 2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.PhoneStateChangeListener
 * JD-Core Version:    0.6.2
 */