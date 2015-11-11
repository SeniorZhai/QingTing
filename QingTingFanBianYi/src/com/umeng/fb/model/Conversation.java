package com.umeng.fb.model;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.umeng.fb.net.FbClient;
import com.umeng.fb.util.DeviceConfig;
import com.umeng.fb.util.Helper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Conversation
{
  private static final String TAG = Conversation.class.getName();
  private static ExecutorService executor = Executors.newSingleThreadExecutor();
  private String appkey;
  private String id;
  private Context mContext;
  private Map<String, Reply> replyIdMap;
  List<Reply> replyList = new ArrayList();
  private String user_id;

  public Conversation(Context paramContext)
  {
    this.mContext = paramContext;
    this.appkey = DeviceConfig.getAppkey(this.mContext);
    this.id = Helper.generateFeedbackId(this.mContext);
    this.user_id = DeviceConfig.getDeviceIdUmengMD5(this.mContext);
    this.replyIdMap = new ConcurrentHashMap();
  }

  Conversation(String paramString, JSONArray paramJSONArray, Context paramContext)
    throws JSONException
  {
    this.mContext = paramContext;
    this.appkey = DeviceConfig.getAppkey(this.mContext);
    this.id = paramString;
    this.user_id = DeviceConfig.getDeviceIdUmengMD5(this.mContext);
    this.replyIdMap = new HashMap();
    if ((paramJSONArray == null) || (paramJSONArray.length() < 1))
      return;
    int i = 0;
    while (i < paramJSONArray.length())
    {
      paramContext = paramJSONArray.getJSONObject(i);
      String str = paramContext.getString("type");
      paramString = null;
      if (Reply.TYPE.NEW_FEEDBACK.toString().equals(str))
        paramString = new UserTitleReply(paramContext);
      while (paramString == null)
      {
        throw new JSONException("Failed to create Conversation using given JSONArray: " + paramJSONArray + " at element " + i + ": " + paramContext);
        if (Reply.TYPE.USER_REPLY.toString().equals(str))
          paramString = new UserReply(paramContext);
        else if (Reply.TYPE.DEV_REPLY.toString().equals(str))
          paramString = new DevReply(paramContext);
      }
      if (!this.replyIdMap.containsKey(paramString.reply_id))
        this.replyIdMap.put(paramString.reply_id, paramString);
      i += 1;
    }
    commitChange();
  }

  private void commitChange()
  {
    Store.getInstance(this.mContext).saveCoversation(this);
  }

  public void addUserReply(String paramString)
  {
    if (this.replyIdMap.size() < 1);
    for (paramString = new UserTitleReply(paramString, this.appkey, this.user_id, this.id); ; paramString = new UserReply(paramString, this.appkey, this.user_id, this.id))
    {
      if (!this.replyIdMap.containsKey(paramString.reply_id))
        this.replyIdMap.put(paramString.reply_id, paramString);
      commitChange();
      return;
    }
  }

  public String getId()
  {
    return this.id;
  }

  public List<Reply> getReplyList()
  {
    try
    {
      this.replyList.clear();
      this.replyList.addAll(this.replyIdMap.values());
      Collections.sort(this.replyList);
      List localList = this.replyList;
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void sync(SyncListener paramSyncListener)
  {
    paramSyncListener = new Runnable()
    {
      public void run()
      {
        ArrayList localArrayList = new ArrayList();
        Object localObject3 = new ArrayList();
        Object localObject2 = null;
        Object localObject1 = "";
        Object localObject4 = Conversation.this.replyIdMap.entrySet().iterator();
        Object localObject5;
        while (((Iterator)localObject4).hasNext())
        {
          localObject5 = (Reply)((Map.Entry)((Iterator)localObject4).next()).getValue();
          if (((localObject5 instanceof UserReply)) || ((localObject5 instanceof UserTitleReply)))
          {
            if (((Reply)localObject5).status == Reply.STATUS.NOT_SENT)
              localArrayList.add(localObject5);
          }
          else if (((localObject5 instanceof DevReply)) && ((localObject2 == null) || (((Date)localObject2).compareTo(((Reply)localObject5).getDatetime()) < 0)))
          {
            localObject2 = ((Reply)localObject5).getDatetime();
            localObject1 = ((Reply)localObject5).reply_id;
          }
        }
        ((List)localObject3).add(Conversation.this.id);
        localObject2 = localArrayList.iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject4 = (Reply)((Iterator)localObject2).next();
          boolean bool = new FbClient(Conversation.this.mContext).sendReply((Reply)localObject4);
          if (bool == true)
          {
            localObject5 = Message.obtain();
            ((Message)localObject5).what = 2;
            ((Message)localObject5).obj = localObject4;
            if (bool);
            for (int i = 1; ; i = 0)
            {
              ((Message)localObject5).arg1 = i;
              this.val$handler.sendMessage((Message)localObject5);
              break;
            }
          }
        }
        localObject1 = new FbClient(Conversation.this.mContext).getDevReply((List)localObject3, (String)localObject1, Conversation.this.appkey);
        localObject2 = Message.obtain();
        ((Message)localObject2).what = 1;
        localObject3 = new Conversation.MessageWrapper();
        ((Conversation.MessageWrapper)localObject3).devReplyListRetrieved = ((List)localObject1);
        ((Conversation.MessageWrapper)localObject3).userReplyListToSend = localArrayList;
        ((Message)localObject2).obj = localObject3;
        this.val$handler.sendMessage((Message)localObject2);
      }
    };
    executor.execute(paramSyncListener);
  }

  JSONArray toJson()
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = this.replyIdMap.entrySet().iterator();
    while (localIterator.hasNext())
      localJSONArray.put(((Reply)((Map.Entry)localIterator.next()).getValue()).toJson());
    return localJSONArray;
  }

  static class MessageWrapper
  {
    List<DevReply> devReplyListRetrieved;
    List<Reply> userReplyListToSend;
  }

  class SyncHandler extends Handler
  {
    static final int CALLBACK = 1;
    static final int UPDATE_SEND_USER_REPLY_RESULT = 2;
    Conversation.SyncListener mListener;

    public SyncHandler(Conversation.SyncListener arg2)
    {
      Object localObject;
      this.mListener = localObject;
    }

    public void handleMessage(Message paramMessage)
    {
      int i = 1;
      Object localObject;
      if (paramMessage.what == 2)
      {
        localObject = (Reply)paramMessage.obj;
        if (paramMessage.arg1 == 1)
          if (i != 0)
            ((Reply)localObject).status = Reply.STATUS.SENT;
      }
      do
      {
        do
        {
          return;
          i = 0;
          break;
        }
        while (paramMessage.what != 1);
        localObject = (Conversation.MessageWrapper)paramMessage.obj;
        paramMessage = ((Conversation.MessageWrapper)localObject).devReplyListRetrieved;
        localObject = ((Conversation.MessageWrapper)localObject).userReplyListToSend;
        if (paramMessage != null)
        {
          Iterator localIterator = paramMessage.iterator();
          while (localIterator.hasNext())
          {
            Reply localReply = (Reply)localIterator.next();
            if (Conversation.this.replyIdMap.containsKey(localReply.reply_id))
              localIterator.remove();
            else
              Conversation.this.replyIdMap.put(localReply.reply_id, localReply);
          }
        }
        Conversation.this.commitChange();
      }
      while (this.mListener == null);
      this.mListener.onReceiveDevReply(paramMessage);
      this.mListener.onSendUserReply((List)localObject);
    }
  }

  public static abstract interface SyncListener
  {
    public abstract void onReceiveDevReply(List<DevReply> paramList);

    public abstract void onSendUserReply(List<Reply> paramList);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.Conversation
 * JD-Core Version:    0.6.2
 */