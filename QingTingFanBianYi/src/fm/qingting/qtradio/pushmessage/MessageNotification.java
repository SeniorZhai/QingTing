package fm.qingting.qtradio.pushmessage;

import android.annotation.TargetApi;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.RemoteViews;
import fm.qingting.qtradio.QTRadioActivity;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.notification.Notifier;
import java.util.Random;

public class MessageNotification
{
  public static int GLOBAL_MESSAGE = 0;
  private static final String NOTIFICATION_ID = "11";
  private static final Random random = new Random(System.currentTimeMillis());
  public int mCategoryId;
  public int mChannleId;
  public String mContent;
  public int mContentType;
  private Context mContext;
  public String mMsgType;
  public int mProgramId;
  public String mTag;
  public String mTaskId;
  public String mTitle;

  public MessageNotification(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public static void clearNotification(Context paramContext)
  {
    ((NotificationManager)paramContext.getSystemService("notification")).cancelAll();
  }

  @TargetApi(16)
  public static void sendActivityNotification(ActivityNode paramActivityNode, String paramString1, String paramString2, String paramString3, int paramInt, Context paramContext)
  {
    if ((paramActivityNode == null) || (paramString1 == null) || (paramContext == null))
      return;
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Intent localIntent = new Intent(paramContext, QTRadioActivity.class);
    localIntent.putExtra("notify_type", paramString1);
    localIntent.putExtra("NOTIFICATION_TITLE", paramActivityNode.infoTitle);
    localIntent.putExtra("NOTIFICATION_MESSAGE", paramActivityNode.desc);
    localIntent.putExtra("ACTIVITY_CONTENTURL", paramActivityNode.contentUrl);
    localIntent.putExtra("ACTIVITY_INFOURL", paramActivityNode.infoUrl);
    localIntent.putExtra("ACTIVITY_TITLEICON", paramActivityNode.titleIconUrl);
    localIntent.putExtra("push_task_id", paramString2);
    localIntent.putExtra("push_tag_id", paramString3);
    localIntent.putExtra("contentType", paramInt);
    paramString1 = PendingIntent.getActivity(paramContext, random.nextInt(), localIntent, 134217728);
    paramString2 = new NotificationCompat.Builder(paramContext);
    paramString2.setSmallIcon(Notifier.getNotificationIcon());
    paramString2.setTicker(paramActivityNode.desc);
    paramString2.setWhen(System.currentTimeMillis());
    paramString2.setAutoCancel(true);
    paramString2.setDefaults(1);
    paramString2.setContentTitle(paramActivityNode.infoTitle);
    paramString2.setContentText(paramActivityNode.desc);
    paramString2.setContentIntent(paramString1);
    paramActivityNode = paramString2.build();
    localNotificationManager.cancelAll();
    localNotificationManager.notify(random.nextInt(), paramActivityNode);
  }

  public static void sendIMNotification(IMMessage paramIMMessage, Context paramContext, String paramString)
  {
    if ((paramIMMessage == null) || (paramContext == null))
      return;
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Object localObject = new Intent(paramContext, QTRadioActivity.class);
    ((Intent)localObject).putExtra("notify_type", "im");
    ((Intent)localObject).putExtra("NOTIFICATION_TITLE", paramString);
    ((Intent)localObject).putExtra("NOTIFICATION_MESSAGE", paramIMMessage.mMessage);
    ((Intent)localObject).putExtra("chatType", paramIMMessage.chatType);
    ((Intent)localObject).putExtra("fromUserId", paramIMMessage.mFromID);
    ((Intent)localObject).putExtra("fromName", paramIMMessage.mFromName);
    ((Intent)localObject).putExtra("groupId", paramIMMessage.mFromGroupId);
    ((Intent)localObject).putExtra("msg", paramIMMessage.mMessage);
    ((Intent)localObject).putExtra("sendTime", paramIMMessage.publish);
    ((Intent)localObject).putExtra("fromGender", paramIMMessage.mGender);
    localObject = PendingIntent.getActivity(paramContext, random.nextInt(), (Intent)localObject, 134217728);
    paramContext = new NotificationCompat.Builder(paramContext);
    paramContext.setSmallIcon(Notifier.getNotificationIcon());
    paramContext.setTicker(paramString);
    paramContext.setWhen(System.currentTimeMillis());
    paramContext.setAutoCancel(true);
    paramContext.setDefaults(1);
    paramContext.setContentTitle(paramString);
    paramContext.setContentText(paramIMMessage.mMessage);
    paramContext.setContentIntent((PendingIntent)localObject);
    paramIMMessage = paramContext.build();
    localNotificationManager.cancelAll();
    localNotificationManager.notify(random.nextInt(), paramIMMessage);
  }

  public static void sendImNotification(String paramString1, String paramString2, Bundle paramBundle, Context paramContext, boolean paramBoolean)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramBundle == null) || (paramString2 == null))
      return;
    NotificationManager localNotificationManager = (NotificationManager)paramContext.getSystemService("notification");
    Intent localIntent = new Intent(paramContext, QTRadioActivity.class);
    localIntent.putExtra("notify_type", "im");
    localIntent.putExtras(paramBundle);
    paramBundle = PendingIntent.getActivity(paramContext, random.nextInt(), localIntent, 134217728);
    paramContext = new NotificationCompat.Builder(paramContext);
    paramContext.setSmallIcon(Notifier.getNotificationIcon());
    paramContext.setTicker(paramString1);
    paramContext.setWhen(System.currentTimeMillis());
    paramContext.setAutoCancel(true);
    if (paramBoolean)
      paramContext.setDefaults(5);
    paramContext.setContentTitle(paramString1);
    paramContext.setContentText(paramString2);
    paramContext.setContentIntent(paramBundle);
    paramString1 = paramContext.build();
    localNotificationManager.cancelAll();
    localNotificationManager.notify(random.nextInt(), paramString1);
  }

  @TargetApi(16)
  public static void sendNotification(MessageNotification paramMessageNotification, String paramString)
  {
    if ((paramMessageNotification == null) || (paramMessageNotification.mContext == null))
      return;
    NotificationManager localNotificationManager = (NotificationManager)paramMessageNotification.mContext.getSystemService("notification");
    Object localObject = new Intent(paramMessageNotification.mContext, QTRadioActivity.class);
    ((Intent)localObject).putExtra("categoryid", paramMessageNotification.mCategoryId);
    ((Intent)localObject).putExtra("parentid", 0);
    ((Intent)localObject).putExtra("channelid", paramMessageNotification.mChannleId);
    ((Intent)localObject).putExtra("programid", paramMessageNotification.mProgramId);
    ((Intent)localObject).putExtra("channelname", paramMessageNotification.mTitle);
    ((Intent)localObject).putExtra("notify_type", paramString);
    ((Intent)localObject).putExtra("live_topic", paramMessageNotification.mContent);
    ((Intent)localObject).putExtra("NOTIFICATION_MESSAGE", paramMessageNotification.mContent);
    ((Intent)localObject).putExtra("push_task_id", paramMessageNotification.mTaskId);
    ((Intent)localObject).putExtra("push_tag_id", paramMessageNotification.mTag);
    ((Intent)localObject).putExtra("contentType", paramMessageNotification.mContentType);
    PendingIntent localPendingIntent = PendingIntent.getActivity(paramMessageNotification.mContext, random.nextInt(), (Intent)localObject, 134217728);
    Intent localIntent;
    if (QtApiLevelManager.isApiLevelSupported(16))
    {
      localIntent = new Intent("fm.qingting.qtradio.INSTANT_PLAY");
      if (paramMessageNotification.mContentType == 5)
      {
        localIntent.putExtra("setplaychannelnode", paramMessageNotification.mChannleId);
        localIntent.putExtras((Intent)localObject);
        paramString = new Notification.Builder(paramMessageNotification.mContext);
        paramString.setSmallIcon(Notifier.getNotificationIcon());
        paramString.setTicker(paramMessageNotification.mContent);
        paramString.setWhen(System.currentTimeMillis());
        paramString.setAutoCancel(true);
        paramString.setDefaults(1);
        paramString = paramString.build();
        localObject = new RemoteViews(paramMessageNotification.mContext.getPackageName(), 2130903046);
        ((RemoteViews)localObject).setOnClickPendingIntent(2131230752, PendingIntent.getBroadcast(paramMessageNotification.mContext, 0, localIntent, 134217728));
        ((RemoteViews)localObject).setTextViewText(2131230754, paramMessageNotification.mTitle);
        ((RemoteViews)localObject).setTextViewText(2131230755, paramMessageNotification.mContent);
        paramString.contentIntent = localPendingIntent;
        paramString.contentView = ((RemoteViews)localObject);
      }
    }
    for (paramMessageNotification = paramString; ; paramMessageNotification = paramString.build())
    {
      localNotificationManager.cancelAll();
      localNotificationManager.notify(random.nextInt(), paramMessageNotification);
      return;
      if (paramMessageNotification.mContentType != 1)
        break;
      localIntent.putExtra("setplaynode", paramMessageNotification.mProgramId);
      break;
      paramString = new NotificationCompat.Builder(paramMessageNotification.mContext);
      paramString.setSmallIcon(Notifier.getNotificationIcon());
      paramString.setTicker(paramMessageNotification.mContent);
      paramString.setWhen(System.currentTimeMillis());
      paramString.setAutoCancel(true);
      paramString.setDefaults(1);
      paramString.setContentTitle(paramMessageNotification.mTitle);
      paramString.setContentText(paramMessageNotification.mContent);
      paramString.setContentIntent(localPendingIntent);
    }
  }

  public static void sendSimpleNotification(MessageNotification paramMessageNotification)
  {
    if ((paramMessageNotification == null) || (paramMessageNotification.mContext == null))
      return;
    new Notifier(paramMessageNotification.mContext).notify("11", "", paramMessageNotification.mTitle, paramMessageNotification.mContent, "", "", "", paramMessageNotification.mChannleId, paramMessageNotification.mMsgType, paramMessageNotification.mCategoryId, paramMessageNotification.mProgramId, 0, paramMessageNotification.mContentType, 0, paramMessageNotification.mTaskId, paramMessageNotification.mTag);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.MessageNotification
 * JD-Core Version:    0.6.2
 */