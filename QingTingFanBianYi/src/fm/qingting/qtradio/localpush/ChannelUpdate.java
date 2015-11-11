package fm.qingting.qtradio.localpush;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.framework.utils.MobileState;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DataLoadWrapper;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ProgramScheduleList;
import fm.qingting.qtradio.pushmessage.MessageNotification;
import fm.qingting.utils.ProcessDetect;
import fm.qingting.utils.TimeUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChannelUpdate
{
  private static final int CheckChannel = 1;
  private static final int CheckUpdate = 0;
  private static final int MAX_CHANNEL_UPDATE = 3;
  private static final int SendMessage = 2;
  private static ChannelUpdate instance;
  public static final String mMsgType = "channelUpdate";
  private static HandlerThread t = new HandlerThread("Channelupdate_Thread");
  private Context mContext;
  private List<ChannelNode> mLstChannelNodes = new ArrayList();
  private List<Integer> mLstChannelUpdateIDs;
  private List<ChannelNode> mLstFavouriteNodes = null;
  private List<ProgramNode> mLstProgramNodes = new ArrayList();
  private long mSendNotificationTime = 0L;
  private IResultRecvHandler resultRecver = new IResultRecvHandler()
  {
    public void onRecvResult(Result paramAnonymousResult, Object paramAnonymousObject1, IResultToken paramAnonymousIResultToken, Object paramAnonymousObject2)
    {
      paramAnonymousObject1 = paramAnonymousIResultToken.getType();
      if (paramAnonymousResult.getSuccess())
      {
        if (paramAnonymousObject1.equalsIgnoreCase("GET_VIRTUAL_CHANNEL_INFO"))
        {
          paramAnonymousResult = (ChannelNode)paramAnonymousResult.getData();
          if (!ChannelUpdate.this.handleChannelUpdate(paramAnonymousResult))
          {
            paramAnonymousResult = new Message();
            paramAnonymousResult.what = 1;
            ChannelUpdate.this.updateHandler.sendMessageDelayed(paramAnonymousResult, 3600000L);
          }
        }
        int j;
        do
        {
          do
          {
            do
            {
              return;
              paramAnonymousResult = new Message();
              paramAnonymousResult.what = 0;
              ChannelUpdate.this.updateHandler.sendMessageDelayed(paramAnonymousResult, 3600000L);
              return;
            }
            while (!paramAnonymousObject1.equalsIgnoreCase("GET_VIRTUAL_PROGRAM_SCHEDULE"));
            paramAnonymousObject1 = (Map)paramAnonymousObject2;
            paramAnonymousIResultToken = (String)paramAnonymousObject1.get("id");
          }
          while (paramAnonymousIResultToken == null);
          j = Integer.valueOf(paramAnonymousIResultToken).intValue();
          paramAnonymousResult = (ProgramScheduleList)paramAnonymousResult.getData();
        }
        while ((paramAnonymousResult == null) || (paramAnonymousResult.getLstProgramNode(0) == null) || (paramAnonymousResult.getLstProgramNode(0).size() <= 0));
        int i = 0;
        while (i < paramAnonymousResult.getLstProgramNode(0).size())
        {
          ((ProgramNode)paramAnonymousResult.getLstProgramNode(0).get(i)).channelId = j;
          i += 1;
        }
        try
        {
          i = Integer.valueOf((String)paramAnonymousObject1.get("order")).intValue();
          paramAnonymousResult.updateToDB(i);
          ChannelUpdate.this.mLstProgramNodes.add(paramAnonymousResult.getLstProgramNode(0).get(0));
          paramAnonymousResult = new Message();
          paramAnonymousResult.what = 2;
          if (ChannelUpdate.this.mLstChannelUpdateIDs.size() == 0)
          {
            ChannelUpdate.this.updateHandler.sendMessageDelayed(paramAnonymousResult, 1000L);
            return;
          }
        }
        catch (Exception paramAnonymousObject1)
        {
          while (true)
            i = 0;
          ChannelUpdate.this.updateHandler.sendMessageDelayed(paramAnonymousResult, 3600000L);
          return;
        }
      }
      paramAnonymousResult = new Message();
      paramAnonymousResult.what = 0;
      ChannelUpdate.this.updateHandler.sendMessageDelayed(paramAnonymousResult, 3600000L);
    }
  };
  private long sendNotification = 0L;
  private UpdateHandler updateHandler = new UpdateHandler(t.getLooper());

  static
  {
    t.start();
  }

  private boolean checkChannel()
  {
    if ((this.mLstFavouriteNodes == null) || (this.mLstFavouriteNodes.size() == 0));
    while (this.mLstChannelNodes.size() >= 3)
      return false;
    int i = 0;
    boolean bool1 = false;
    if ((i < this.mLstFavouriteNodes.size()) && (i < 10))
    {
      int j = 0;
      while (true)
      {
        if ((j >= this.mLstChannelNodes.size()) || (((ChannelNode)this.mLstChannelNodes.get(j)).channelId == ((ChannelNode)this.mLstFavouriteNodes.get(i)).channelId))
        {
          boolean bool2 = bool1;
          if (j == this.mLstChannelNodes.size())
          {
            bool2 = bool1;
            if (((ChannelNode)this.mLstFavouriteNodes.get(i)).channelType == 1)
            {
              DataLoadWrapper.loadVChannelInfo(((ChannelNode)this.mLstFavouriteNodes.get(i)).channelId, this.resultRecver);
              bool2 = true;
            }
          }
          i += 1;
          bool1 = bool2;
          break;
        }
        j += 1;
      }
    }
    return bool1;
  }

  private ChannelNode getChannelNode(int paramInt)
  {
    int i = 0;
    while (i < this.mLstFavouriteNodes.size())
    {
      if (paramInt == ((ChannelNode)this.mLstFavouriteNodes.get(i)).channelId)
        return (ChannelNode)this.mLstFavouriteNodes.get(i);
      i += 1;
    }
    return null;
  }

  private void getFavNodesFromDB()
  {
    if (this.mLstFavouriteNodes == null)
      this.mLstFavouriteNodes = ((List)DataManager.getInstance().getData("get_favourite_channels", null, null).getResult().getData());
  }

  public static ChannelUpdate getInstance()
  {
    try
    {
      if (instance == null)
        instance = new ChannelUpdate();
      ChannelUpdate localChannelUpdate = instance;
      return localChannelUpdate;
    }
    finally
    {
    }
  }

  private boolean handleChannelUpdate(ChannelNode paramChannelNode)
  {
    if (paramChannelNode != null)
    {
      ChannelNode localChannelNode = getChannelNode(paramChannelNode.channelId);
      if ((localChannelNode != null) && (localChannelNode.getUpdateTime() != 0L) && (paramChannelNode.getUpdateTime() != 0L) && (localChannelNode.getUpdateTime() != paramChannelNode.getUpdateTime()))
      {
        localChannelNode.updatePartialInfo(paramChannelNode);
        updateFavDB(paramChannelNode);
        this.mLstChannelNodes.add(paramChannelNode);
        DataLoadWrapper.loadVirtualProgramsScheduleNode(paramChannelNode.channelId, 1, this.resultRecver);
        return true;
      }
    }
    return false;
  }

  private boolean hasFavNodes()
  {
    if ((this.mLstFavouriteNodes == null) || (this.mLstFavouriteNodes.size() == 0));
    while (true)
    {
      return false;
      int i = 0;
      while (i < this.mLstFavouriteNodes.size())
      {
        if (((ChannelNode)this.mLstFavouriteNodes.get(i)).channelType == 1)
          return true;
        i += 1;
      }
    }
  }

  private boolean hasWifi()
  {
    return 1 == MobileState.getNetWorkType(this.mContext);
  }

  private boolean needCheckUpdate()
  {
    if (!hasFavNodes());
    int i;
    do
    {
      do
        return false;
      while ((ProcessDetect.processExists(this.mContext.getPackageName() + ":local", null)) || (TimeUtil.getDayofYear(this.mSendNotificationTime / 1000L) == TimeUtil.getDayofYear(System.currentTimeMillis() / 1000L)) || (this.mLstChannelUpdateIDs.size() >= 3));
      i = TimeUtil.getHourOfDay(System.currentTimeMillis() / 1000L);
    }
    while ((i < 18) || (i > 23));
    return true;
  }

  private boolean sendMessageNotification()
  {
    int i = TimeUtil.getHourOfDay(System.currentTimeMillis() / 1000L);
    if ((i < 18) || (i > 23));
    label483: 
    while (true)
    {
      return false;
      if (System.currentTimeMillis() - this.sendNotification < 1800000L)
        return true;
      this.sendNotification = System.currentTimeMillis();
      if ((this.mLstProgramNodes.size() > 0) && (this.mLstChannelUpdateIDs.size() < 3) && (this.mLstChannelNodes.size() > 0))
      {
        i = 0;
        while (true)
        {
          if (i >= this.mLstProgramNodes.size())
            break label483;
          int k = ((ProgramNode)this.mLstProgramNodes.get(i)).channelId;
          int j = 0;
          label121: if ((j >= this.mLstChannelUpdateIDs.size()) || (((ProgramNode)this.mLstProgramNodes.get(i)).channelId == ((Integer)this.mLstChannelUpdateIDs.get(j)).intValue()))
          {
            if (j == this.mLstChannelUpdateIDs.size())
              j = 0;
          }
          else
            while (true)
            {
              if ((j >= this.mLstChannelNodes.size()) || (((ChannelNode)this.mLstChannelNodes.get(j)).channelId == k))
              {
                if (j == this.mLstChannelNodes.size())
                  break;
                this.mLstChannelUpdateIDs.add(Integer.valueOf(k));
                MessageNotification localMessageNotification = new MessageNotification(this.mContext);
                localMessageNotification.mCategoryId = ((ChannelNode)this.mLstChannelNodes.get(j)).categoryId;
                localMessageNotification.mChannleId = ((ProgramNode)this.mLstProgramNodes.get(i)).channelId;
                localMessageNotification.mProgramId = ((ProgramNode)this.mLstProgramNodes.get(i)).uniqueId;
                localMessageNotification.mTitle = ("<" + ((ChannelNode)this.mLstChannelNodes.get(j)).title + ">" + "有更新啦");
                localMessageNotification.mContent = ((ProgramNode)this.mLstProgramNodes.get(i)).title;
                localMessageNotification.mMsgType = "channelUpdate";
                localMessageNotification.mContentType = 1;
                MessageNotification.sendSimpleNotification(localMessageNotification);
                sendPushedOneItemLog((ProgramNode)this.mLstProgramNodes.get(i), localMessageNotification.mCategoryId, ((ChannelNode)this.mLstChannelNodes.get(j)).title);
                this.mSendNotificationTime = System.currentTimeMillis();
                GlobalCfg.getInstance(this.mContext).setChannelUpdateTime(this.mSendNotificationTime);
                return true;
                j += 1;
                break label121;
              }
              j += 1;
            }
          i += 1;
        }
      }
    }
  }

  private void sendPushedOneItemLog(ProgramNode paramProgramNode, int paramInt, String paramString)
  {
    if ((this.mContext == null) || (paramProgramNode == null));
    do
    {
      return;
      QTLogger.getInstance().setContext(this.mContext);
      str = QTLogger.getInstance().buildCommonLog();
    }
    while (str == null);
    String str = str + "\"" + String.valueOf(paramInt) + "\"";
    str = str + ",\"\"";
    str = str + ",\"" + String.valueOf(paramProgramNode.channelId) + "\"";
    paramString = str + ",\"" + paramString + "\"";
    paramString = paramString + ",\"" + String.valueOf(paramProgramNode.id) + "\"";
    paramProgramNode = paramString + ",\"" + paramProgramNode.title + "\"";
    LogModule.getInstance().send("PushedOneItem", paramProgramNode);
  }

  private void updateFavDB(ChannelNode paramChannelNode)
  {
    if (paramChannelNode == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("channel", paramChannelNode);
    DataManager.getInstance().getData("update_favourite_channels", null, localHashMap);
  }

  public int getFavNodesNum()
  {
    if (this.mLstFavouriteNodes == null)
      return 0;
    int i = 0;
    int k;
    for (int j = 0; i < this.mLstFavouriteNodes.size(); j = k)
    {
      k = j;
      if (!((ChannelNode)this.mLstFavouriteNodes.get(i)).isLiveChannel())
        k = j + 1;
      i += 1;
    }
    return j;
  }

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
    getFavNodesFromDB();
    this.mSendNotificationTime = GlobalCfg.getInstance(this.mContext).getChannelUpdateTime();
    this.mLstChannelUpdateIDs = new ArrayList();
  }

  public void start()
  {
    if (!hasWifi())
      return;
    Message localMessage = new Message();
    localMessage.what = 0;
    this.updateHandler.sendMessageDelayed(localMessage, 3600000L);
  }

  public class UpdateHandler extends Handler
  {
    public UpdateHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (paramMessage == null);
      do
      {
        return;
        switch (paramMessage.what)
        {
        default:
          return;
        case 0:
          if (ChannelUpdate.this.needCheckUpdate())
          {
            paramMessage = new Message();
            paramMessage.what = 1;
            ChannelUpdate.this.updateHandler.sendMessageDelayed(paramMessage, 1000L);
            return;
          }
          paramMessage = new Message();
          paramMessage.what = 0;
          ChannelUpdate.this.updateHandler.sendMessageDelayed(paramMessage, 3600000L);
          return;
        case 1:
        case 2:
        }
      }
      while (ChannelUpdate.this.checkChannel());
      paramMessage = new Message();
      paramMessage.what = 0;
      ChannelUpdate.this.updateHandler.sendMessageDelayed(paramMessage, 3600000L);
      return;
      if (ChannelUpdate.this.sendMessageNotification())
      {
        paramMessage = new Message();
        paramMessage.what = 2;
        ChannelUpdate.this.updateHandler.sendMessageDelayed(paramMessage, 3600000L);
        return;
      }
      paramMessage = new Message();
      paramMessage.what = 0;
      ChannelUpdate.this.updateHandler.sendMessageDelayed(paramMessage, 3600000L);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.localpush.ChannelUpdate
 * JD-Core Version:    0.6.2
 */