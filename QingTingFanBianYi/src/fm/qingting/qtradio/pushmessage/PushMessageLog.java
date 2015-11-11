package fm.qingting.qtradio.pushmessage;

import android.content.Context;
import android.os.Bundle;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;

public class PushMessageLog
{
  public static final String ClickPushMsg = "ClickGeTuiPushMsg";
  public static final String RecvPushMsg = "RecvGeTuiPushMsg";
  public static final String SendPushMsg = "SendGeTuiPushMsg";
  public static final String StartPushServiceMsg = "StartPushServiceMsg";

  public static void sendGetuiMsgFromServiceLog(Context paramContext, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramContext == null);
    do
    {
      return;
      QTLogger.getInstance().setContext(paramContext);
      paramContext = QTLogger.getInstance().buildCommonLog();
    }
    while (paramContext == null);
    paramContext = paramContext + "\"" + paramBoolean1 + "\"";
    paramContext = paramContext + ",\"" + paramBoolean2 + "\"";
    LogModule.getInstance().send("RecvGeTuiMsgFromService", paramContext);
  }

  public static void sendGetuiMsgLog(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || ((paramString1 == null) && (paramString2 == null)));
    String str;
    do
    {
      return;
      str = paramString1;
      if (paramString1 == null)
        str = "";
      paramString1 = paramString2;
      if (paramString2 == null)
        paramString1 = "";
      QTLogger.getInstance().setContext(paramContext);
      paramContext = QTLogger.getInstance().buildCommonLog();
    }
    while (paramContext == null);
    paramContext = paramContext + "\"" + str + "\"";
    paramContext = paramContext + ",\"" + paramString1 + "\"";
    LogModule.getInstance().send("RecvGeTuiMsg", paramContext);
  }

  public static void sendPushLog(Context paramContext, Bundle paramBundle, String paramString)
  {
    int i = paramBundle.getInt("channelid");
    int j = paramBundle.getInt("categoryid");
    int k = paramBundle.getInt("programid");
    String str = paramBundle.getString("NOTIFICATION_MESSAGE");
    sendPushLog(paramContext, paramBundle.getString("push_task_id"), paramBundle.getString("push_tag_id"), Integer.valueOf(j).intValue(), 0, Integer.valueOf(i).intValue(), Integer.valueOf(k).intValue(), str, paramString);
  }

  public static void sendPushLog(Context paramContext, MessageNotification paramMessageNotification, String paramString)
  {
    sendPushLog(paramContext, paramMessageNotification.mTaskId, paramMessageNotification.mTag, paramMessageNotification.mCategoryId, 0, paramMessageNotification.mChannleId, paramMessageNotification.mProgramId, paramMessageNotification.mContent, paramString);
  }

  public static void sendPushLog(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null));
    do
    {
      return;
      QTLogger.getInstance().setContext(paramContext);
      paramContext = QTLogger.getInstance().buildCommonLog();
    }
    while (paramContext == null);
    paramContext = paramContext + "\"" + paramString1 + "\"";
    paramContext = paramContext + ",\"" + paramString2 + "\"";
    LogModule.getInstance().send("PushCID", paramContext);
  }

  public static void sendPushLog(Context paramContext, String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString3, String paramString4)
  {
    if ((paramContext == null) || (paramString4 == null) || (paramString1 == null) || (paramString2 == null));
    do
    {
      return;
      QTLogger.getInstance().setContext(paramContext);
      paramContext = QTLogger.getInstance().buildCommonLog();
    }
    while (paramContext == null);
    paramContext = paramContext + "\"" + paramString1 + "\"";
    paramContext = paramContext + ",\"" + paramString2 + "\"";
    paramContext = paramContext + ",\"" + paramInt1 + "\"";
    paramContext = paramContext + ",\"" + paramInt2 + "\"";
    paramContext = paramContext + ",\"" + paramInt3 + "\"";
    paramString1 = paramContext + ",\"" + paramInt4 + "\"";
    paramContext = paramString3;
    if (paramString3 == null)
      paramContext = "";
    paramContext = paramString1 + ",\"" + paramContext + "\"";
    LogModule.getInstance().send(paramString4, paramContext);
  }

  public static void sendPushOutOfDateLog(Context paramContext, long paramLong)
  {
    if (paramContext == null);
    do
    {
      return;
      QTLogger.getInstance().setContext(paramContext);
      paramContext = QTLogger.getInstance().buildCommonLog();
    }
    while (paramContext == null);
    paramContext = paramContext + "\"" + String.valueOf(paramLong) + "\"";
    paramContext = paramContext + ",\"" + String.valueOf(System.currentTimeMillis() / 1000L) + "\"";
    LogModule.getInstance().send("PushOutOfDate", paramContext);
  }

  public static void sendPushUserLog(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null));
    do
    {
      return;
      QTLogger.getInstance().setContext(paramContext);
      paramContext = QTLogger.getInstance().buildCommonLog();
    }
    while (paramContext == null);
    paramContext = paramContext + "\"" + paramString + "\"";
    LogModule.getInstance().send("PushUser", paramContext);
  }

  public static void sendXiaoMsgLog(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || ((paramString1 == null) && (paramString2 == null)));
    String str;
    do
    {
      return;
      str = paramString1;
      if (paramString1 == null)
        str = "";
      paramString1 = paramString2;
      if (paramString2 == null)
        paramString1 = "";
      QTLogger.getInstance().setContext(paramContext);
      paramContext = QTLogger.getInstance().buildCommonLog();
    }
    while (paramContext == null);
    paramContext = paramContext + "\"" + str + "\"";
    paramContext = paramContext + ",\"" + paramString1 + "\"";
    LogModule.getInstance().send("RecvXiaoMiMsg", paramContext);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.PushMessageLog
 * JD-Core Version:    0.6.2
 */