package fm.qingting.qtradio.pushmessage;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.DataLoadWrapper;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegionMessageHandler
  implements MessagePump.IRecvPushMsgListener
{
  private static RegionMessageHandler _instance;
  private String RegionTopic = "region:other";
  private ActivityNode mActivityNode;
  private int mCategoryId;
  private int mChannelId;
  private String mContent;
  private int mContentType;
  private Context mContext;
  private long mExpireTime;
  private String mMsgType = "pullmsg";
  private int mProgramId;
  private String mTaskId;
  private String mTitle;
  private IResultRecvHandler resultRecver = new IResultRecvHandler()
  {
    public void onRecvResult(Result paramAnonymousResult, Object paramAnonymousObject1, IResultToken paramAnonymousIResultToken, Object paramAnonymousObject2)
    {
      if (paramAnonymousResult.getSuccess())
      {
        paramAnonymousObject1 = paramAnonymousIResultToken.getType();
        if (!paramAnonymousObject1.equalsIgnoreCase("GET_VIRTUAL_PROGRAM_INFO"))
          break label51;
        paramAnonymousResult = (ProgramNode)paramAnonymousResult.getData();
        if (paramAnonymousResult != null)
        {
          RegionMessageHandler.this.writeToDB(paramAnonymousResult);
          RegionMessageHandler.this.sendNotification();
        }
      }
      label51: 
      do
      {
        do
          return;
        while (!paramAnonymousObject1.equalsIgnoreCase("GET_LIVE_CHANNEL_INFO"));
        paramAnonymousResult = (Node)paramAnonymousResult.getData();
      }
      while ((paramAnonymousResult == null) || (!paramAnonymousResult.nodeName.equalsIgnoreCase("channel")));
      RegionMessageHandler.this.writeToDB(paramAnonymousResult);
      RegionMessageHandler.this.sendNotification();
    }
  };

  private void _writeToDB(List<Node> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("nodes", paramList);
    DataManager.getInstance().getData("updatedb_pull_node", null, localHashMap);
  }

  private void buildTopic()
  {
  }

  private boolean canHandle(String paramString)
  {
    if (paramString == null);
    while (!this.RegionTopic.equalsIgnoreCase(paramString))
      return false;
    return true;
  }

  public static RegionMessageHandler getInstance()
  {
    if (_instance == null)
      _instance = new RegionMessageHandler();
    return _instance;
  }

  private boolean handleMsg(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return false;
      try
      {
        resetInfo();
        paramString = (JSONObject)JSON.parse(paramString);
        String str = paramString.getString("type");
        this.mTitle = paramString.getString("title");
        this.mContent = paramString.getString("content");
        this.mTaskId = paramString.getString("uuid");
        this.mExpireTime = paramString.getLongValue("expiretime");
        if (!isOutOfDate())
        {
          if (str.equalsIgnoreCase("virtual_program"))
          {
            this.mCategoryId = paramString.getIntValue("cat_id");
            this.mChannelId = paramString.getIntValue("channel_id");
            this.mProgramId = paramString.getIntValue("program_id");
            this.mContentType = 1;
            DataLoadWrapper.loadVProgramInfo(Integer.valueOf(this.mProgramId).intValue(), this.resultRecver);
          }
          while (true)
          {
            PushMessageLog.sendPushLog(this.mContext, this.mTaskId, this.RegionTopic, this.mCategoryId, 0, this.mChannelId, this.mProgramId, this.mContent, "RecvGeTuiPushMsg");
            return false;
            if (str.equalsIgnoreCase("live_channel"))
            {
              this.mCategoryId = paramString.getIntValue("cat_id");
              this.mChannelId = paramString.getIntValue("channel_id");
              this.mProgramId = 0;
              this.mContentType = 5;
              DataLoadWrapper.loadLiveChannelNode(Integer.valueOf(this.mChannelId).intValue(), this.resultRecver);
            }
            else
            {
              if (!str.equalsIgnoreCase("activity"))
                break;
              this.mActivityNode = new ActivityNode();
              this.mActivityNode.contentUrl = paramString.getString("content_url");
              this.mActivityNode.titleIconUrl = paramString.getString("title_icon");
              this.mActivityNode.infoUrl = paramString.getString("info_url");
              this.mActivityNode.infoTitle = this.mTitle;
              this.mActivityNode.desc = this.mContent;
              this.mContentType = 4;
              sendActivityNotification();
            }
          }
        }
      }
      catch (Exception paramString)
      {
      }
    }
    return false;
  }

  private boolean isOutOfDate()
  {
    if (this.mExpireTime == 0L);
    while (this.mExpireTime >= System.currentTimeMillis() / 1000L)
      return false;
    return true;
  }

  private void resetInfo()
  {
    this.mCategoryId = 0;
    this.mChannelId = 0;
    this.mProgramId = 0;
    this.mTitle = null;
    this.mContent = null;
    this.mTaskId = null;
    this.mExpireTime = 0L;
    this.mActivityNode = null;
  }

  private void sendActivityNotification()
  {
    if (this.mActivityNode != null)
    {
      MessageNotification.sendActivityNotification(this.mActivityNode, "push_activity", this.mTaskId, this.RegionTopic, this.mContentType, this.mContext);
      PushMessageLog.sendPushLog(this.mContext, this.mTaskId, this.RegionTopic, this.mCategoryId, 0, this.mChannelId, this.mProgramId, this.mContent, "SendGeTuiPushMsg");
    }
  }

  private void sendNotification()
  {
    MessageNotification localMessageNotification = new MessageNotification(this.mContext);
    localMessageNotification.mCategoryId = this.mCategoryId;
    localMessageNotification.mChannleId = this.mChannelId;
    localMessageNotification.mProgramId = this.mProgramId;
    localMessageNotification.mTitle = this.mTitle;
    localMessageNotification.mContent = this.mContent;
    localMessageNotification.mMsgType = this.mMsgType;
    localMessageNotification.mContentType = this.mContentType;
    localMessageNotification.mTag = this.RegionTopic;
    localMessageNotification.mTaskId = this.mTaskId;
    MessageNotification.sendSimpleNotification(localMessageNotification);
    PushMessageLog.sendPushLog(this.mContext, this.mTaskId, this.RegionTopic, this.mCategoryId, 0, this.mChannelId, this.mProgramId, this.mContent, "SendGeTuiPushMsg");
  }

  private void writeToDB(Node paramNode)
  {
    if (paramNode == null)
      return;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramNode);
    _writeToDB(localArrayList);
  }

  public void init(Context paramContext)
  {
    MessagePump.getInstance().registerRecvMsg(this);
    this.mContext = paramContext;
    buildTopic();
  }

  public boolean onRecvPushMsg(PushMessage paramPushMessage, int paramInt)
  {
    if (paramPushMessage == null);
    while ((paramInt != 0) || (!canHandle(paramPushMessage.mTopic)))
      return false;
    return handleMsg(paramPushMessage.mMessage);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.pushmessage.RegionMessageHandler
 * JD-Core Version:    0.6.2
 */