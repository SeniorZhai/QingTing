package fm.qingting.qtradio.pushmessage;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.qtradio.data.IMDatabaseDS;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.utils.ProcessDetect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class IMHandler
  implements MessagePump.IRecvPushMsgListener
{
  private static final String IM = "qingting:im";
  private static IMHandler _instance;
  private long SEND_NOTIFICATION_INTERVAL = 2000L;
  private boolean loginSuccess = true;
  private String mAlias;
  private Context mContext;
  private String mLastFromId = null;
  private String mMsgType = "pullmsg";
  private int mRecvMsgCnt = 0;
  private long mRecvMsgTime = 0L;
  private Map<String, Integer> mapUnReadCnt = new HashMap();
  private long msgSeq = -1L;

  private boolean canHandle(String paramString)
  {
    if (paramString == null);
    while (!paramString.equalsIgnoreCase("qingting:im"))
      return false;
    return ProcessDetect.processExists(this.mContext.getPackageName() + ":local", null);
  }

  private boolean canHandle(String paramString, int paramInt)
  {
    if (paramString == null);
    do
    {
      return false;
      if (paramInt == 0)
        return true;
    }
    while ((paramInt != 1) || (!IMContacts.getInstance().hasWatchedGroup(paramString)));
    return true;
  }

  public static IMHandler getInstance()
  {
    if (_instance == null)
      _instance = new IMHandler();
    return _instance;
  }

  private void handleIM(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return;
      try
      {
        paramString = (JSONObject)JSON.parse(paramString);
        String str = paramString.getString("event");
        if (str != null)
        {
          if (this.msgSeq == -1L)
            this.msgSeq = GlobalCfg.getInstance(this.mContext).getMsgSeq();
          if (str.equalsIgnoreCase("peer"))
          {
            if ((this.loginSuccess) && (canHandle(paramString.getString("from"), 0)))
              recvMsg(0, null, paramString.getJSONArray("body"));
          }
          else if ((str.equalsIgnoreCase("group")) && (this.loginSuccess))
          {
            str = paramString.getString("to");
            if (canHandle(str, 1))
            {
              recvMsg(1, str, paramString.getJSONArray("body"));
              return;
            }
          }
        }
      }
      catch (Exception paramString)
      {
      }
    }
  }

  private boolean handleMsg(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return false;
    try
    {
      paramString = (JSONObject)JSON.parse(paramString);
      if (paramString != null)
      {
        paramString = paramString.getString("msg");
        if (paramString != null)
        {
          handleIM(paramString);
          return true;
        }
      }
    }
    catch (Exception paramString)
    {
    }
    return false;
  }

  private void recvMsg(int paramInt, String paramString, JSONArray paramJSONArray)
  {
    int i = 0;
    if (paramJSONArray != null);
    while (true)
    {
      try
      {
        IMMessage localIMMessage;
        if (paramJSONArray.size() == 1)
        {
          localIMMessage = new IMMessage();
          IMMessage.parseData(paramJSONArray.getJSONObject(0), localIMMessage);
          localIMMessage.chatType = paramInt;
          if (localIMMessage != null)
          {
            this.msgSeq += 1L;
            localIMMessage.msgSeq = this.msgSeq;
            this.mRecvMsgCnt += 1;
            if (localIMMessage.isGroupMsg())
            {
              if ((localIMMessage.mFromGroupId == null) || (localIMMessage.mFromGroupId.equalsIgnoreCase("")))
                localIMMessage.mFromGroupId = paramString;
              if (localIMMessage.mGroupName == null)
              {
                paramString = IMContacts.getInstance().getWatchedGroup(localIMMessage.mFromGroupId);
                if (paramString != null)
                  localIMMessage.mGroupName = paramString.groupName;
              }
              else
              {
                IMDatabaseDS.getInstance().appendGroupMessage(localIMMessage, true);
                paramString = (Integer)this.mapUnReadCnt.get(localIMMessage.mFromGroupId);
                if (paramString == null)
                {
                  this.mapUnReadCnt.put(localIMMessage.mFromGroupId, Integer.valueOf(1));
                  storeUnReadMsgCnt();
                  sendNotification(localIMMessage);
                  return;
                }
                paramInt = paramString.intValue();
                this.mapUnReadCnt.put(localIMMessage.mFromGroupId, Integer.valueOf(paramInt + 1));
                continue;
              }
            }
            else
            {
              IMDatabaseDS.getInstance().appendPrivateMessage(localIMMessage, true);
              paramString = (Integer)this.mapUnReadCnt.get(localIMMessage.mFromID);
              if (paramString == null)
              {
                this.mapUnReadCnt.put(localIMMessage.mFromID, Integer.valueOf(1));
                storeUnReadMsgCnt();
                continue;
              }
              paramInt = paramString.intValue();
              this.mapUnReadCnt.put(localIMMessage.mFromID, Integer.valueOf(paramInt + 1));
              continue;
            }
          }
        }
        else if (paramJSONArray.size() > 1)
        {
          paramString = new ArrayList();
          if (i < paramJSONArray.size())
          {
            localIMMessage = new IMMessage();
            IMMessage.parseData(paramJSONArray.getJSONObject(i), localIMMessage);
            localIMMessage.chatType = paramInt;
            this.msgSeq += 1L;
            localIMMessage.msgSeq = this.msgSeq;
            if (localIMMessage == null)
              break label470;
            paramString.add(localIMMessage);
            break label470;
          }
          if (paramString.size() > 0)
          {
            if (!((IMMessage)paramString.get(0)).isGroupMsg())
            {
              IMDatabaseDS.getInstance().appendListPrivateMessage(paramString, true);
              this.mRecvMsgCnt += paramString.size();
              return;
            }
            IMDatabaseDS.getInstance().appendListGroupMessage(paramString, true);
            continue;
          }
        }
        return;
      }
      catch (Exception paramString)
      {
        return;
      }
      label470: i += 1;
    }
  }

  private void sendNotification(IMMessage paramIMMessage)
  {
    if (paramIMMessage == null)
      return;
    long l = System.currentTimeMillis();
    if (l - this.mRecvMsgTime < this.SEND_NOTIFICATION_INTERVAL)
    {
      this.mRecvMsgTime = l;
      return;
    }
    this.mRecvMsgTime = l;
    String str1 = "";
    String str2;
    if (paramIMMessage.isGroupMsg())
    {
      str2 = paramIMMessage.mFromGroupId;
      if (str2 != null)
      {
        if ((this.mLastFromId != null) && (!str2.equalsIgnoreCase(this.mLastFromId)))
          break label176;
        if (paramIMMessage.mGroupName == null)
          paramIMMessage.mGroupName = "蜻蜓fm";
        str1 = paramIMMessage.mGroupName;
        str1 = str1 + "发来";
        str1 = str1 + String.valueOf(this.mRecvMsgCnt);
        str1 = str1 + "消息";
        this.mLastFromId = str2;
      }
    }
    while (true)
    {
      MessageNotification.sendIMNotification(paramIMMessage, this.mContext, str1);
      return;
      label176: str1 = "您收到" + String.valueOf(this.mRecvMsgCnt);
      str1 = str1 + "消息";
      this.mLastFromId = "#";
      continue;
      str2 = paramIMMessage.mFromID;
      if (str2 != null)
        if ((this.mLastFromId == null) || (str2.equalsIgnoreCase(this.mLastFromId)))
        {
          str1 = paramIMMessage.mFromName;
          str1 = str1 + "发来";
          str1 = str1 + String.valueOf(this.mRecvMsgCnt);
          str1 = str1 + "消息";
          this.mLastFromId = str2;
        }
        else
        {
          str1 = "蜻蜓fm收到" + String.valueOf(this.mRecvMsgCnt);
          str1 = str1 + "消息";
          this.mLastFromId = "#";
        }
    }
  }

  private void storeUnReadMsgCnt()
  {
    if (this.mapUnReadCnt.size() > 0)
    {
      Iterator localIterator = this.mapUnReadCnt.entrySet().iterator();
      Object localObject1 = "";
      Object localObject2 = "";
      int i = 0;
      while (localIterator.hasNext())
      {
        Object localObject3 = (Map.Entry)localIterator.next();
        int j = ((Integer)((Map.Entry)localObject3).getValue()).intValue();
        localObject3 = (String)localObject2 + (String)((Map.Entry)localObject3).getKey();
        String str = (String)localObject1 + String.valueOf(j);
        localObject2 = str;
        localObject1 = localObject3;
        if (i != this.mapUnReadCnt.size() - 1)
        {
          localObject1 = (String)localObject3 + "_";
          localObject2 = str + "_";
        }
        i += 1;
        localObject3 = localObject1;
        localObject1 = localObject2;
        localObject2 = localObject3;
      }
      GlobalCfg.getInstance(this.mContext).setUnReadCnt((String)localObject1);
      GlobalCfg.getInstance(this.mContext).setUnReadID((String)localObject2);
    }
  }

  public void clearMsgCnt()
  {
    this.mLastFromId = null;
    this.mRecvMsgCnt = 0;
    this.mRecvMsgTime = 0L;
    initUnReadMsg();
  }

  public void init(Context paramContext)
  {
    MessagePump.getInstance().registerRecvMsg(this);
    this.mContext = paramContext;
  }

  public void initUnReadMsg()
  {
    Object localObject2 = GlobalCfg.getInstance(this.mContext).getUnReadID();
    Object localObject1 = GlobalCfg.getInstance(this.mContext).getUnReadCnt();
    if ((localObject2 != null) && (localObject1 != null))
    {
      localObject2 = ((String)localObject2).split("_");
      if (localObject2 != null)
        break label43;
    }
    while (true)
    {
      return;
      label43: localObject1 = ((String)localObject1).split("_");
      if ((localObject1 != null) && (localObject1.length == localObject2.length))
      {
        int i = 0;
        while (i < localObject2.length)
        {
          this.mapUnReadCnt.put(localObject2[i], Integer.valueOf(localObject1[i]));
          i += 1;
        }
      }
    }
  }

  public boolean onRecvPushMsg(PushMessage paramPushMessage, int paramInt)
  {
    if (paramPushMessage == null);
    while ((paramInt != 0) || (!canHandle(paramPushMessage.mTopic)))
      return false;
    return handleMsg(paramPushMessage.mMessage);
  }

  public void setAlias(String paramString)
  {
    this.mAlias = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.IMHandler
 * JD-Core Version:    0.6.2
 */