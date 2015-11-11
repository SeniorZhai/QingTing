package fm.qingting.qtradio.room;

import android.content.Context;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ProgramTopicNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import java.util.ArrayList;
import java.util.List;

public class LiveRoomChat extends Chat
{
  private List<String> lstCheckIn = new ArrayList();
  private Context mContext;

  private boolean allowCheckIn(String paramString)
  {
    if (paramString == null)
      return false;
    int i = 0;
    while (true)
    {
      if (i >= this.lstCheckIn.size())
        break label48;
      if (((String)this.lstCheckIn.get(i)).equalsIgnoreCase(paramString))
        break;
      i += 1;
    }
    label48: return true;
  }

  public void askSongName(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    QTChat.getInstance().askForSongName(paramString);
  }

  public void askSongNameTogether(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    QTChat.getInstance().askForSongNameTogether(paramString);
  }

  public void checkIn(int paramInt)
  {
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (localNode != null)
    {
      localObject1 = localObject2;
      if (localNode.nodeName.equalsIgnoreCase("program"))
        localObject1 = ((ProgramNode)localNode).title;
    }
    if (paramInt == 0)
    {
      if (!WeiboChat.getInstance().login())
        TencentChat.getInstance().login();
      QTChat.getInstance().checkIn(paramInt, (String)localObject1);
      if (localNode != null)
      {
        if (WeiboAgent.getInstance().isSessionValid().booleanValue())
          WeiboChat.getInstance().checkIn(paramInt, (String)localObject1);
        if (TencentAgent.getInstance().isSessionValid().booleanValue())
          TencentChat.getInstance().checkIn(paramInt, (String)localObject1);
        if (localObject1 != null)
          this.lstCheckIn.add(localObject1);
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              if (paramInt == 1)
              {
                QTChat.getInstance().checkIn(paramInt, (String)localObject1);
                return;
              }
              if (paramInt != 2)
                break;
              WeiboChat.getInstance().login();
            }
            while ((localNode == null) || (!allowCheckIn((String)localObject1)) || (!WeiboAgent.getInstance().isSessionValid().booleanValue()));
            WeiboChat.getInstance().checkIn(paramInt, (String)localObject1);
          }
          while (localObject1 == null);
          this.lstCheckIn.add(localObject1);
          return;
        }
        while (paramInt != 3);
        TencentChat.getInstance().login();
      }
      while ((localNode == null) || (!allowCheckIn((String)localObject1)) || (!TencentAgent.getInstance().isSessionValid().booleanValue()));
      TencentChat.getInstance().checkIn(paramInt, (String)localObject1);
    }
    while (localObject1 == null);
    this.lstCheckIn.add(localObject1);
  }

  public void exit()
  {
    QTChat.getInstance().exit();
  }

  public void flower(int paramInt, UserInfo paramUserInfo)
  {
    if (paramInt == 0)
    {
      WeiboChat.getInstance().login();
      QTChat.getInstance().flower(paramInt, paramUserInfo);
      WeiboChat.getInstance().flower(paramUserInfo);
    }
    do
    {
      return;
      if (paramInt == 1)
      {
        QTChat.getInstance().flower(paramInt, paramUserInfo);
        return;
      }
    }
    while (paramInt != 2);
    WeiboChat.getInstance().login();
    WeiboChat.getInstance().flower(paramUserInfo);
  }

  public void getHistory(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")))
      return;
    if (WeiboChat.getInstance().getUserInfo() != null)
      QTChat.getInstance().login(WeiboChat.getInstance().getUserInfo());
    while (true)
    {
      QTChat.getInstance().getHistory(paramString1, paramString2, paramInt);
      return;
      if (TencentChat.getInstance().getUserInfo() != null)
        QTChat.getInstance().login(TencentChat.getInstance().getUserInfo());
    }
  }

  public void getOnlineFriends(String paramString)
  {
    QTChat.getInstance().getOnlineFriends(paramString);
  }

  public void getTopic(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    QTChat.getInstance().getTopic(paramString);
  }

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public void join(String paramString1, String paramString2, Node paramNode, int paramInt)
  {
    QTChat.getInstance().join(paramString1, paramString2, paramInt);
    WeiboChat.getInstance().getUserInfo();
    WeiboChat.getInstance().updatePlayingProgramUserInfo();
    if (paramNode != null)
    {
      if (paramNode.nodeName.equalsIgnoreCase("programtopic"))
      {
        paramString1 = ((ProgramTopicNode)paramNode).mid;
        paramString2 = ((ProgramTopicNode)paramNode).platform;
        if ((paramString2 != null) && (paramString2.equalsIgnoreCase("weibo")))
          WeiboChat.getInstance().setWeiboId(paramString1);
      }
      return;
    }
    WeiboChat.getInstance().setWeiboId(null);
  }

  public void leave()
  {
    QTChat.getInstance().leave();
  }

  public void login(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null);
    while ((WeiboChat.getInstance().login()) || (TencentChat.getInstance().login()))
      return;
    QTChat.getInstance().login(paramUserInfo);
  }

  public void logout(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null)
      return;
    QTChat.getInstance().logout(paramUserInfo);
  }

  public void send(ChatData paramChatData)
  {
    if (paramChatData == null);
    do
    {
      do
      {
        do
        {
          return;
          if (!WeiboChat.getInstance().login())
            TencentChat.getInstance().login();
          QTChat.getInstance().send(paramChatData.content, paramChatData.toUser, paramChatData.replyedContent, paramChatData.conentType);
        }
        while (this.mContext == null);
        if ((paramChatData.toUser != null) && (paramChatData.replyedContent != null))
          break;
        if (SharedCfg.getInstance().getShareToWeibo())
          WeiboChat.getInstance().send(paramChatData.content);
      }
      while (!SharedCfg.getInstance().getShareToTencent());
      TencentChat.getInstance().send(paramChatData.content);
      return;
      if (SharedCfg.getInstance().getShareToWeibo())
        WeiboChat.getInstance().send(paramChatData.content, paramChatData.toUser, paramChatData.replyedContent);
    }
    while (!SharedCfg.getInstance().getShareToTencent());
    TencentChat.getInstance().send(paramChatData.content, paramChatData.toUser, paramChatData.replyedContent);
  }

  public void speakTo(int paramInt, UserInfo paramUserInfo, String paramString)
  {
    if ((paramString == null) || (paramUserInfo == null));
    do
    {
      return;
      if (paramInt == 0)
      {
        WeiboChat.getInstance().login();
        QTChat.getInstance().speakTo(paramUserInfo, paramString);
        WeiboChat.getInstance().speakTo(paramUserInfo, paramString);
        return;
      }
      if (paramInt == 1)
      {
        QTChat.getInstance().speakTo(paramUserInfo, paramString);
        return;
      }
    }
    while (paramInt != 2);
    WeiboChat.getInstance().login();
    WeiboChat.getInstance().speakTo(paramUserInfo, paramString);
  }

  public void tellSongName(String paramString1, UserInfo paramUserInfo, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")))
      return;
    QTChat.getInstance().tellSongName(paramUserInfo, paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.LiveRoomChat
 * JD-Core Version:    0.6.2
 */