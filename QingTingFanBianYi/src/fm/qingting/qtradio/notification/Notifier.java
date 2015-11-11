package fm.qingting.qtradio.notification;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import fm.qingting.qtradio.QTRadioActivity;
import java.util.Random;

public class Notifier
{
  private Context context;
  private final boolean mNewStyle = false;
  private NotificationManager notificationManager;
  private Random random;

  public Notifier(Context paramContext)
  {
    this.context = paramContext;
    this.notificationManager = ((NotificationManager)paramContext.getSystemService("notification"));
    this.random = new Random(System.currentTimeMillis());
  }

  public static int getNotificationIcon()
  {
    return 2130837874;
  }

  @TargetApi(16)
  public void notify(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, int paramInt1, String paramString8, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, String paramString9, String paramString10)
  {
    paramString6 = new Intent(this.context, QTRadioActivity.class);
    paramString6.putExtra("NOTIFICATION_ID", paramString1);
    paramString6.putExtra("NOTIFICATION_API_KEY", paramString2);
    paramString6.putExtra("NOTIFICATION_TITLE", paramString3);
    paramString6.putExtra("NOTIFICATION_MESSAGE", paramString4);
    paramString6.putExtra("NOTIFICATION_URI", paramString5);
    paramString6.putExtra("channelname", paramString7);
    paramString6.putExtra("channelid", paramInt1);
    paramString6.putExtra("push_task_id", paramString9);
    paramString6.putExtra("push_tag_id", paramString10);
    paramString6.putExtra("categoryid", paramInt2);
    paramString6.putExtra("programid", paramInt3);
    paramString6.putExtra("parentid", paramInt4);
    paramString6.putExtra("contentType", paramInt5);
    paramString6.putExtra("alarmType", paramInt6);
    paramString6.putExtra("notify_type", paramString8);
    paramString6.setFlags(268435456);
    paramString6.setFlags(536870912);
    paramString6.setFlags(67108864);
    paramString1 = PendingIntent.getActivity(this.context, this.random.nextInt(), paramString6, 134217728);
    paramString2 = new NotificationCompat.Builder(this.context);
    paramString2.setSmallIcon(getNotificationIcon());
    paramString2.setTicker(paramString4);
    paramString2.setWhen(System.currentTimeMillis());
    paramString2.setAutoCancel(true);
    paramString2.setDefaults(1);
    paramString2.setContentTitle(paramString3);
    paramString2.setContentText(paramString4);
    paramString2.setContentIntent(paramString1);
    paramString1 = paramString2.build();
    this.notificationManager.cancelAll();
    this.notificationManager.notify(this.random.nextInt(), paramString1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.Notifier
 * JD-Core Version:    0.6.2
 */