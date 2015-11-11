package fm.qingting.qtradio.social;

import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.room.RoomDataCenter;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.TencentChat;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.room.WeiboChat;
import fm.qingting.qtradio.tencentAgent.QQAgent;
import fm.qingting.qtradio.tencentAgent.TencentAgent;
import fm.qingting.qtradio.weiboAgent.WeiboAgent;
import fm.qingting.qtradio.weixin.WeixinAgent;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class CloudCenter
  implements IResultRecvHandler, RootNode.IInfoUpdateEventListener
{
  static final String TAG = "CloudCenter";
  private static CloudCenter _instance;
  private int PULL_INTERVAL = 60;
  private int UPLOAD_PLAYHISTORY_INTERVAL = 60;
  private long lastPullTime = 0L;
  private int mIsLiveRoomAdmin = -1;
  private String mLiveRoomAdmin = "";
  private boolean mNeedSocialType = false;
  private UserInfo mRegisterUser;
  private int mSocialType = 0;
  private Map<String, List<IUserEventListener>> mapUserEventListeners = new HashMap();
  private Map<String, UserProfile> mapUserProfile = new HashMap();
  private long uploadPlayHistoryTime = 0L;

  private CloudCenter()
  {
    init();
  }

  private void dispatchUserEvent(String paramString)
  {
    if (this.mapUserEventListeners.containsKey(paramString))
    {
      List localList = (List)this.mapUserEventListeners.get(paramString);
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        ((IUserEventListener)localList.get(i)).onUserNotification(paramString);
        i += 1;
      }
      paramString = localList.iterator();
      i = 0;
      while ((paramString.hasNext()) && (i < j))
      {
        paramString.next();
        paramString.remove();
        i += 1;
      }
    }
  }

  public static CloudCenter getInstance()
  {
    if (_instance == null)
      _instance = new CloudCenter();
    return _instance;
  }

  private void handleReportUser(String paramString)
  {
    if ((paramString == null) || (!isLiveRoomAdmin()))
      return;
    UserInfo localUserInfo = new UserInfo();
    localUserInfo.userKey = paramString;
    localUserInfo.userId = paramString;
    IMAgent.getInstance().sendUserMsg("setblack", localUserInfo, 0);
  }

  private void init()
  {
    this.mSocialType = 0;
    this.mNeedSocialType = false;
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 0);
  }

  private void mergeMiniFav(List<MiniFavNode> paramList)
  {
    if ((paramList == null) || (this.mRegisterUser == null))
      return;
    InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.mergeWithFavNodes(paramList);
  }

  private void updateUserKey(String paramString)
  {
    if (this.mRegisterUser != null)
      this.mRegisterUser.userKey = paramString;
  }

  public void getGroupInfo(String paramString)
  {
  }

  public void getPlayHistoryFromCloud(IUserEventListener paramIUserEventListener)
  {
    if ((this.mRegisterUser == null) || (TextUtils.isEmpty(this.mRegisterUser.userKey)));
    while (true)
    {
      return;
      try
      {
        Log.d("CloudCenter", "sym:开始从云端获取播放历史");
        HashMap localHashMap = new HashMap();
        localHashMap.put("userid", this.mRegisterUser.userKey);
        DataManager.getInstance().getData("get_play_history", this, localHashMap);
        if (paramIUserEventListener != null)
        {
          registerUserEventListener(paramIUserEventListener, "RPH");
          return;
        }
      }
      catch (Exception paramIUserEventListener)
      {
        paramIUserEventListener.printStackTrace();
      }
    }
  }

  public int getSocialType()
  {
    return this.mSocialType;
  }

  public UserProfile getUserProfile(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (!this.mapUserProfile.containsKey(paramString))
      return null;
    return (UserProfile)this.mapUserProfile.get(paramString);
  }

  public boolean hasAddFriendBySocialType()
  {
    return (this.mSocialType & 0x1) != 0;
  }

  public boolean hasAgreeAddFriendBySocialType()
  {
    return (this.mSocialType & 0x2) != 0;
  }

  public boolean hasChatMessaggBySocialType()
  {
    return (this.mSocialType & 0x3) != 0;
  }

  public boolean isLiveRoomAdmin()
  {
    if (this.mIsLiveRoomAdmin == 0)
      return false;
    if (this.mIsLiveRoomAdmin == 1)
      return true;
    if (this.mRegisterUser != null)
    {
      if (this.mLiveRoomAdmin.contains(this.mRegisterUser.userKey))
      {
        this.mIsLiveRoomAdmin = 1;
        return true;
      }
      this.mIsLiveRoomAdmin = 0;
    }
    this.mIsLiveRoomAdmin = 0;
    return false;
  }

  public boolean isLiveRoomAdmin(String paramString)
  {
    if (paramString == null);
    while (!this.mLiveRoomAdmin.contains(paramString))
      return false;
    return true;
  }

  public boolean isLogin(boolean paramBoolean)
  {
    boolean bool = false;
    if (paramBoolean)
      if (!WeiboAgent.getInstance().isLoggedIn().booleanValue())
      {
        paramBoolean = bool;
        if (!TencentAgent.getInstance().isLoggedIn().booleanValue());
      }
      else
      {
        paramBoolean = true;
      }
    do
    {
      return paramBoolean;
      if ((WeiboAgent.getInstance().isLoggedIn().booleanValue()) || (TencentAgent.getInstance().isLoggedIn().booleanValue()) || (QQAgent.getInstance().isLoggedIn()))
        break;
      paramBoolean = bool;
    }
    while (!WeixinAgent.getInstance().isLoggedIn());
    return true;
  }

  public void loadUserProfile(String paramString, IUserEventListener paramIUserEventListener)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    while (true)
    {
      return;
      try
      {
        HashMap localHashMap = new HashMap();
        localHashMap.put("userkey", paramString);
        DataManager.getInstance().getData("get_social_user_data", this, localHashMap);
        if (paramIUserEventListener != null)
        {
          registerUserEventListener(paramIUserEventListener, "RUP");
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
  }

  public void login(int paramInt, OnLoginEventListerner paramOnLoginEventListerner)
  {
    if (paramInt == 2);
    try
    {
      TencentAgent.getInstance().login(paramOnLoginEventListerner);
      return;
      if (paramInt == 1)
      {
        WeiboAgent.getInstance().login(paramOnLoginEventListerner);
        return;
      }
      if (paramInt == 5)
      {
        QQAgent.getInstance().login(paramOnLoginEventListerner);
        return;
      }
      if (paramInt == 6)
        WeixinAgent.getInstance().login(paramOnLoginEventListerner);
      return;
    }
    catch (Exception paramOnLoginEventListerner)
    {
    }
  }

  public void logout()
  {
    TencentChat.getInstance().logout();
    WeiboChat.getInstance().logout();
    QQAgent.getInstance().logout();
    WeixinAgent.getInstance().logout();
    IMContacts.getInstance().clearAll();
    InfoManager.getInstance().getUserProfile().clearAll();
    GlobalCfg.getInstance(InfoManager.getInstance().getContext()).setActiveUserKey("");
    IMAgent.getInstance().logout();
    this.mRegisterUser = null;
    this.lastPullTime = 0L;
    InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.clear();
    Toast.makeText(InfoManager.getInstance().getContext(), "本地收藏电台列表已同步到云端,请登录获取", 1).show();
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 0)
      uploadFavoriteChannelToCloud();
  }

  public void onRecvResult(Result paramResult, Object paramObject1, IResultToken paramIResultToken, Object paramObject2)
  {
    int j = 0;
    paramObject1 = paramIResultToken.getType();
    int i;
    if (paramResult.getSuccess())
    {
      if (!paramObject1.equalsIgnoreCase("create_user"))
        break label243;
      paramResult = (String)paramResult.getData();
      RoomDataCenter.getInstance().setLoginUserKey(paramResult);
      updateUserKey(paramResult);
      if (!this.mRegisterUser.snsInfo.sns_site.equalsIgnoreCase("tencent"))
        break label165;
      i = 1;
      if (i != 0)
        InfoManager.getInstance().getUserProfile().setUserKey(paramResult, i);
      InfoManager.getInstance().getUserProfile().init();
      InfoManager.getInstance().getUserProfile().loadUserInfo(paramResult, null);
      InfoManager.getInstance().getUserProfile().loadFollowers(null);
      InfoManager.getInstance().getUserProfile().loadFollowings(null);
      pullFavoriteChannelFromCloud(null);
      getPlayHistoryFromCloud(null);
      GlobalCfg.getInstance(InfoManager.getInstance().getContext()).setActiveUserKey(paramResult);
      InfoManager.getInstance().getUserProfile().updateUserInfo();
      IMAgent.getInstance().connect();
    }
    label165: label243: 
    do
    {
      do
      {
        do
        {
          do
          {
            return;
            i = j;
            if (this.mRegisterUser.snsInfo.sns_site.equalsIgnoreCase("weibo"))
              break;
            if (this.mRegisterUser.snsInfo.sns_site.equalsIgnoreCase("qq"))
            {
              i = 5;
              break;
            }
            i = j;
            if (!this.mRegisterUser.snsInfo.sns_site.equalsIgnoreCase("wechat"))
              break;
            i = 6;
            break;
            if (!paramObject1.equalsIgnoreCase("get_social_user_data"))
              break label303;
            paramResult = (UserProfile)paramResult.getData();
          }
          while (paramResult == null);
          paramObject1 = paramResult.getUserKey();
        }
        while ((paramObject1 == null) || (paramObject1.equalsIgnoreCase("")));
        this.mapUserProfile.put(paramObject1, paramResult);
        dispatchUserEvent("RUP");
        return;
        if (paramObject1.equalsIgnoreCase("pull_collection_data"))
        {
          paramResult = (List)paramResult.getData();
          if (paramResult != null)
          {
            mergeMiniFav(paramResult);
            return;
          }
          uploadFavoriteChannelToCloud();
          return;
        }
        if (!paramObject1.equalsIgnoreCase("get_play_history"))
          break label405;
        paramResult = (List)paramResult.getData();
      }
      while (paramResult == null);
      Log.d("CloudCenter", "sym:获取到云端播放历史: " + paramResult.size());
      InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.mergeFromCloud(paramResult);
      return;
    }
    while (!paramObject1.equalsIgnoreCase("set_play_history"));
    label303: Log.d("CloudCenter", "sym:上传播放记录成功");
    label405:
  }

  public void pullFavoriteChannelFromCloud(IUserEventListener paramIUserEventListener)
  {
    if ((this.mRegisterUser == null) || (this.mRegisterUser.userKey == null) || (this.mRegisterUser.userKey.equalsIgnoreCase("")));
    while (true)
    {
      return;
      try
      {
        if (System.currentTimeMillis() / 1000L - this.lastPullTime >= this.PULL_INTERVAL)
        {
          this.lastPullTime = (System.currentTimeMillis() / 1000L);
          HashMap localHashMap = new HashMap();
          localHashMap.put("user", this.mRegisterUser.userKey);
          DataManager.getInstance().getData("pull_collection_data", this, localHashMap);
          if (paramIUserEventListener != null)
          {
            registerUserEventListener(paramIUserEventListener, "RFC");
            return;
          }
        }
      }
      catch (Exception paramIUserEventListener)
      {
        paramIUserEventListener.printStackTrace();
      }
    }
  }

  public void registerUser(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null)
    {
      this.mRegisterUser = null;
      return;
    }
    while (true)
    {
      HashMap localHashMap;
      try
      {
        localHashMap = new HashMap();
        if (paramUserInfo.snsInfo.sns_id == null)
          break;
        if (paramUserInfo.snsInfo.sns_site.equalsIgnoreCase("tencent"))
        {
          localHashMap.put("sns_id", paramUserInfo.snsInfo.sns_id);
          localHashMap.put("sns_type", "1");
          localHashMap.put("device_id", InfoManager.getInstance().getDeviceId());
          localHashMap.put("app", "Master");
          localHashMap.put("phone", "Android");
          DataManager.getInstance().getData("create_user", this, localHashMap);
          this.mRegisterUser = paramUserInfo;
          return;
        }
      }
      catch (Exception paramUserInfo)
      {
        paramUserInfo.printStackTrace();
        return;
      }
      if (paramUserInfo.snsInfo.sns_site.equalsIgnoreCase("weibo"))
      {
        localHashMap.put("sns_id", paramUserInfo.snsInfo.sns_id);
        localHashMap.put("sns_type", "0");
      }
      else if (paramUserInfo.snsInfo.sns_site.equalsIgnoreCase("qq"))
      {
        localHashMap.put("sns_id", paramUserInfo.snsInfo.sns_id);
        localHashMap.put("sns_type", "2");
      }
      else if (paramUserInfo.snsInfo.sns_site.equalsIgnoreCase("wechat"))
      {
        localHashMap.put("sns_id", paramUserInfo.snsInfo.sns_id);
        localHashMap.put("sns_type", "3");
      }
      else
      {
        localHashMap.put("sns_id", paramUserInfo.snsInfo.sns_id);
        localHashMap.put("sns_type", "4");
      }
    }
  }

  public void registerUserEventListener(IUserEventListener paramIUserEventListener, String paramString)
  {
    if ((paramIUserEventListener != null) && (paramString != null))
    {
      if (this.mapUserEventListeners.containsKey(paramString))
        ((List)this.mapUserEventListeners.get(paramString)).add(paramIUserEventListener);
    }
    else
      return;
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramIUserEventListener);
    this.mapUserEventListeners.put(paramString, localArrayList);
  }

  public void reportUser(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString2 == null) || (this.mRegisterUser == null))
      return;
    try
    {
      HashMap localHashMap1 = new HashMap();
      HashMap localHashMap2 = new HashMap();
      localHashMap2.put("informer", this.mRegisterUser.userKey);
      localHashMap2.put("blacker", paramString1);
      localHashMap2.put("reason", String.valueOf(paramInt));
      localHashMap2.put("last_message", paramString2);
      localHashMap1.put("postdata", localHashMap2);
      DataManager.getInstance().getData("report_user", this, localHashMap1);
      handleReportUser(paramString1);
      return;
    }
    catch (Exception paramString1)
    {
    }
  }

  public void requstGroup(String paramString)
  {
  }

  public void resetSocialType()
  {
    this.mSocialType = 0;
  }

  public void setLiveRoomAdmin(String paramString)
  {
    this.mLiveRoomAdmin = paramString;
  }

  public void setNeedSocialType(boolean paramBoolean)
  {
    this.mNeedSocialType = paramBoolean;
  }

  public void uploadFavoriteChannelToCloud()
  {
    if ((this.mRegisterUser == null) || (this.mRegisterUser.userKey == null) || (this.mRegisterUser.userKey.equalsIgnoreCase("")));
    List localList;
    do
    {
      return;
      localList = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes();
    }
    while (localList == null);
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (true)
    {
      try
      {
        if (i < localList.size())
        {
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("id", String.valueOf(((ChannelNode)localList.get(i)).channelId));
          localJSONObject.put("catid", String.valueOf(((ChannelNode)localList.get(i)).categoryId));
          localJSONObject.put("parentid", String.valueOf(((ChannelNode)localList.get(i)).channelId));
          localJSONObject.put("type", ((ChannelNode)localList.get(i)).channelType);
          localJSONObject.put("name", ((ChannelNode)localList.get(i)).title);
          localJSONObject.put("time", System.currentTimeMillis() / 1000L);
          localJSONArray.put(localJSONObject);
          i += 1;
          continue;
        }
        if (localList.size() == 0)
        {
          localHashMap2.put("value", "\"\"");
          localHashMap1.put("postdata", localHashMap2);
          localHashMap1.put("user", URLEncoder.encode(this.mRegisterUser.userKey, "UTF-8"));
          DataManager.getInstance().getData("set_user_data", this, localHashMap1);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      localHashMap2.put("value", localJSONArray.toString());
    }
  }

  public void uploadPlayHistoryToCloud()
  {
    if ((this.mRegisterUser == null) || (TextUtils.isEmpty(this.mRegisterUser.userKey)));
    List localList;
    do
    {
      do
        return;
      while (System.currentTimeMillis() / 1000L - this.uploadPlayHistoryTime < this.UPLOAD_PLAYHISTORY_INTERVAL);
      this.uploadPlayHistoryTime = (System.currentTimeMillis() / 1000L);
      localList = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes();
    }
    while (localList == null);
    Log.d("CloudCenter", "sym:开始上传播放历史");
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    JSONArray localJSONArray = new JSONArray();
    int i = 0;
    while (true)
    {
      try
      {
        if (i < localList.size())
        {
          PlayHistoryNode localPlayHistoryNode = (PlayHistoryNode)localList.get(i);
          JSONObject localJSONObject = new JSONObject();
          if (!(localPlayHistoryNode.playNode instanceof ProgramNode))
            break label401;
          localJSONObject.put("pid", ((ProgramNode)localPlayHistoryNode.playNode).id);
          localJSONObject.put("pname", ((ProgramNode)localPlayHistoryNode.playNode).title);
          localJSONObject.put("resid", ((ProgramNode)localPlayHistoryNode.playNode).resId);
          localJSONObject.put("cid", localPlayHistoryNode.channelId);
          localJSONObject.put("cname", localPlayHistoryNode.channelName);
          localJSONObject.put("cavatar", localPlayHistoryNode.channelThumb);
          localJSONObject.put("ctype", localPlayHistoryNode.playContent);
          localJSONObject.put("catid", localPlayHistoryNode.categoryId);
          localJSONObject.put("playtime", localPlayHistoryNode.playTime);
          localJSONArray.put(localJSONObject);
          break label401;
        }
        if (localList.size() == 0)
        {
          localHashMap2.put("value", "\"\"");
          localHashMap1.put("postdata", localHashMap2);
          localHashMap1.put("userid", URLEncoder.encode(this.mRegisterUser.userKey, "UTF-8"));
          DataManager.getInstance().getData("set_play_history", this, localHashMap1);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      localHashMap2.put("value", localJSONArray.toString());
      continue;
      label401: i += 1;
    }
  }

  public static abstract interface IUserEventListener
  {
    public static final String RECV_ADD_FRIEND = "RAF";
    public static final String RECV_AGREE_ADD_FRIEND = "RAAF";
    public static final String RECV_CHAT_MESSAGE = "RCM";
    public static final String RECV_FAV_CHANNELS = "RFC";
    public static final String RECV_PLAY_HISTORY = "RPH";
    public static final String RECV_USER_PROFILE = "RUP";
    public static final String SOCIAL_LOGIN = "SL";

    public abstract void onUserNotification(String paramString);
  }

  public static abstract interface OnLoginEventListerner
  {
    public abstract void onLoginFailed(int paramInt);

    public abstract void onLoginSuccessed(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.social.CloudCenter
 * JD-Core Version:    0.6.2
 */