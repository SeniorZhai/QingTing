package cn.com.mma.mobile.tracking.api;

import android.content.Context;
import android.content.SharedPreferences;
import cn.com.mma.mobile.tracking.util.Logger;
import cn.com.mma.mobile.tracking.util.SharedPreferencedUtil;
import java.util.Map;

public class SendEventMessage
{
  private static Context context;
  private static SendMessageThread failedThread;
  private static SendMessageThread normalThread;
  private static SendEventMessage sendEventMessage = new SendEventMessage();

  public static SendEventMessage getSendEventMessage(Context paramContext)
  {
    context = paramContext;
    return sendEventMessage;
  }

  public void clearAll()
  {
    stopThread();
    SharedPreferencedUtil.clearAllDataInSP(context, "cn.com.mma.mobile.tracking.normal");
    SharedPreferencedUtil.clearAllDataInSP(context, "cn.com.mma.mobile.tracking.falied");
    SharedPreferencedUtil.clearAllDataInSP(context, "cn.com.mma.mobile.tracking.other");
  }

  public void clearErrorList()
  {
    SharedPreferencedUtil.clearAllDataInSP(context, "cn.com.mma.mobile.tracking.falied");
  }

  public void sendFailedList()
  {
    if ((failedThread != null) && ((failedThread.getState() == Thread.State.NEW) || (failedThread.isAlive())));
    SharedPreferences localSharedPreferences;
    do
    {
      return;
      localSharedPreferences = SharedPreferencedUtil.getSharedPreferences(context, "cn.com.mma.mobile.tracking.falied");
    }
    while ((localSharedPreferences == null) || (localSharedPreferences.getAll().isEmpty()));
    failedThread = new SendMessageThread("cn.com.mma.mobile.tracking.falied", context, false);
    failedThread.start();
  }

  public void sendNromalList()
  {
    Logger.d("thread_sendNormalList");
    if ((normalThread != null) && ((normalThread.getState() == Thread.State.NEW) || (normalThread.isAlive())));
    SharedPreferences localSharedPreferences;
    do
    {
      return;
      localSharedPreferences = SharedPreferencedUtil.getSharedPreferences(context, "cn.com.mma.mobile.tracking.normal");
    }
    while ((localSharedPreferences == null) || (localSharedPreferences.getAll().isEmpty()));
    normalThread = new SendMessageThread("cn.com.mma.mobile.tracking.normal", context, true);
    normalThread.start();
  }

  public void stopThread()
  {
    if ((normalThread != null) && (normalThread.isAlive()))
      normalThread.interrupt();
    if ((failedThread != null) && (failedThread.isAlive()))
      failedThread.interrupt();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.api.SendEventMessage
 * JD-Core Version:    0.6.2
 */