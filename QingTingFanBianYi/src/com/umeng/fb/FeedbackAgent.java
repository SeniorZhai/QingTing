package com.umeng.fb;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.NotificationCompat.Builder;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import com.umeng.fb.model.Store;
import com.umeng.fb.model.UserInfo;
import com.umeng.fb.res.DrawableMapper;
import com.umeng.fb.res.StringMapper;
import com.umeng.fb.util.Log;
import java.util.List;
import java.util.Locale;

public class FeedbackAgent
{
  private static final String TAG = FeedbackAgent.class.getName();
  private Context mContext;
  private Store store;

  public FeedbackAgent(Context paramContext)
  {
    this.mContext = paramContext;
    this.store = Store.getInstance(this.mContext);
  }

  private void showReplyNotification(String paramString)
  {
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    String str = this.mContext.getString(StringMapper.umeng_fb_notification_ticker_text(this.mContext));
    Object localObject = new Intent(this.mContext, ConversationActivity.class);
    ((Intent)localObject).setFlags(131072);
    localObject = PendingIntent.getActivity(this.mContext, 0, (Intent)localObject, 0);
    localNotificationManager.notify(0, new NotificationCompat.Builder(this.mContext).setSmallIcon(DrawableMapper.umeng_fb_statusbar_icon(this.mContext)).setContentTitle(str).setTicker(str).setContentText(paramString).setAutoCancel(true).setContentIntent((PendingIntent)localObject).build());
  }

  public List<String> getAllConversationIds()
  {
    return this.store.getAllConversationIds();
  }

  public Conversation getConversationById(String paramString)
  {
    return this.store.getConversationById(paramString);
  }

  public Conversation getDefaultConversation()
  {
    List localList = getAllConversationIds();
    if ((localList == null) || (localList.size() < 1))
    {
      Log.d(TAG, "getDefaultConversation: No conversation saved locally. Create a new one.");
      return new Conversation(this.mContext);
    }
    Log.d(TAG, "getDefaultConversation: There are " + localList.size() + " saved locally, use the first one by default.");
    return getConversationById((String)localList.get(0));
  }

  public UserInfo getUserInfo()
  {
    return this.store.getUserInfo();
  }

  public long getUserInfoLastUpdateAt()
  {
    return this.store.getUserInfoLastUpdateAt();
  }

  public void setDebug(boolean paramBoolean)
  {
    Log.LOG = paramBoolean;
  }

  public void setUserInfo(UserInfo paramUserInfo)
  {
    this.store.saveUserInfo(paramUserInfo);
  }

  public void startFeedbackActivity()
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this.mContext, ConversationActivity.class);
      this.mContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void sync()
  {
    Conversation.SyncListener local1 = new Conversation.SyncListener()
    {
      public void onReceiveDevReply(List<DevReply> paramAnonymousList)
      {
        if ((paramAnonymousList == null) || (paramAnonymousList.size() < 1))
          return;
        String str;
        if (paramAnonymousList.size() == 1)
          str = FeedbackAgent.this.mContext.getResources().getString(StringMapper.umeng_fb_notification_content_formatter_single_msg(FeedbackAgent.this.mContext));
        for (paramAnonymousList = String.format(Locale.US, str, new Object[] { ((DevReply)paramAnonymousList.get(0)).getContent() }); ; paramAnonymousList = String.format(Locale.US, str, new Object[] { Integer.valueOf(paramAnonymousList.size()) }))
        {
          try
          {
            FeedbackAgent.this.showReplyNotification(paramAnonymousList);
            return;
          }
          catch (Exception paramAnonymousList)
          {
            paramAnonymousList.printStackTrace();
            return;
          }
          str = FeedbackAgent.this.mContext.getResources().getString(StringMapper.umeng_fb_notification_content_formatter_multiple_msg(FeedbackAgent.this.mContext));
        }
      }

      public void onSendUserReply(List<Reply> paramAnonymousList)
      {
      }
    };
    getDefaultConversation().sync(local1);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.FeedbackAgent
 * JD-Core Version:    0.6.2
 */