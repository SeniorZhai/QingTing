package fm.qingting.qtradio.push.log;

import android.content.Context;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;

public class ClickNotificationLog
{
  public static final String LogType = "ClickedNotification";

  public static void sendClickNotification(int paramInt1, int paramInt2, String paramString1, int paramInt3, String paramString2, String paramString3, String paramString4, Context paramContext)
  {
    if (paramContext == null);
    do
    {
      return;
      QTLogger.getInstance().setContext(paramContext);
      paramString4 = QTLogger.getInstance().buildCommonLog();
    }
    while (paramString4 == null);
    paramString4 = paramString4 + "\"" + String.valueOf(paramInt1) + "\"";
    paramString4 = paramString4 + ",\"\"";
    paramString4 = paramString4 + ",\"" + String.valueOf(paramInt2) + "\"";
    paramString1 = paramString4 + ",\"" + paramString1 + "\"";
    paramString1 = paramString1 + ",\"" + String.valueOf(paramInt3) + "\"";
    paramString1 = paramString1 + ",\"" + paramString2 + "\"";
    paramString1 = paramString1 + ",\"" + paramString3 + "\"";
    paramString1 = paramString1 + ",\"0\"";
    LogModule.getInstance().send("ClickedNotification", paramString1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.log.ClickNotificationLog
 * JD-Core Version:    0.6.2
 */