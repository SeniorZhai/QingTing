package fm.qingting.qtradio.selfprotect;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import fm.qingting.qtradio.notification.QTAlarmReceiver;
import fm.qingting.utils.ProcessDetect;

public class SelfProtect
{
  private static SelfProtect _instance;
  private Context mContext;
  private int protectDuration = 10000;
  private int workDuration = 30000;

  public static SelfProtect getInstance()
  {
    if (_instance == null)
      _instance = new SelfProtect();
    return _instance;
  }

  private void kill(long paramLong)
  {
    if (this.mContext == null)
      return;
    AlarmManager localAlarmManager = (AlarmManager)this.mContext.getSystemService("alarm");
    Intent localIntent = new Intent("fm.qingting.killintent");
    localIntent.setClass(this.mContext, QTAlarmReceiver.class);
    localAlarmManager.set(1, paramLong, PendingIntent.getBroadcast(this.mContext, 0, localIntent, 134217728));
    log("send kill intent after 30s");
  }

  private void log(String paramString)
  {
    Log.e("selfprotect", paramString);
  }

  private void protect(long paramLong)
  {
    if (this.mContext == null)
      return;
    AlarmManager localAlarmManager = (AlarmManager)this.mContext.getSystemService("alarm");
    Intent localIntent = new Intent("fm.qingting.protectintent");
    localIntent.setClass(this.mContext, QTAlarmReceiver.class);
    localAlarmManager.set(1, paramLong, PendingIntent.getBroadcast(this.mContext, 0, localIntent, 134217728));
    log("send protect intent after 40s");
  }

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void run()
  {
    if (this.mContext == null);
    while (ProcessDetect.processExists(this.mContext.getPackageName() + ":local", null))
      return;
    long l = System.currentTimeMillis() + this.workDuration;
    kill(l);
    protect(l + this.protectDuration);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.selfprotect.SelfProtect
 * JD-Core Version:    0.6.2
 */