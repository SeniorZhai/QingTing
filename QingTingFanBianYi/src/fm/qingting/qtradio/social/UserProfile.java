package fm.qingting.qtradio.social;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.IMContacts;
import fm.qingting.qtradio.im.IMDataLoadWrapper;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfile
  implements IResultRecvHandler
{
  public List<Node> lstFavNodes;
  private List<UserInfo> lstFollowers;
  private List<UserInfo> lstFollowings;
  private List<GroupInfo> lstGroups;
  private int mFollowerCnt;
  private int mFollowingCnt;
  private UserInfo mUserInfo;
  private String mUserKey = "";

  private void addGroup(GroupInfo paramGroupInfo)
  {
    if (paramGroupInfo == null)
      return;
    if (this.lstGroups == null)
      this.lstGroups = new ArrayList();
    int i = 0;
    while (true)
    {
      if (i >= this.lstGroups.size())
        break label71;
      if (((GroupInfo)this.lstGroups.get(i)).groupId.equalsIgnoreCase(paramGroupInfo.groupId))
        break;
      i += 1;
    }
    label71: this.lstGroups.add(paramGroupInfo);
  }

  private UserInfo parseUserInfo(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      UserInfo localUserInfo = new UserInfo();
      localUserInfo.userKey = paramJSONObject.getString("userid");
      localUserInfo.snsInfo.sns_avatar = paramJSONObject.getString("avatar");
      localUserInfo.snsInfo.sns_name = paramJSONObject.getString("username");
      localUserInfo.snsInfo.signature = paramJSONObject.getString("signature");
      localUserInfo.snsInfo.age = paramJSONObject.getIntValue("age");
      localUserInfo.snsInfo.sns_gender = paramJSONObject.getString("gender");
      String str = paramJSONObject.getString("is_blocked");
      if ((str != null) && (!str.equalsIgnoreCase("")))
        if (Integer.valueOf(str).intValue() != 0)
          break label155;
      label155: for (localUserInfo.isBlocked = false; ; localUserInfo.isBlocked = true)
      {
        paramJSONObject = paramJSONObject.getString("level");
        if ((paramJSONObject != null) && (!paramJSONObject.equalsIgnoreCase("")))
          localUserInfo.level = Integer.valueOf(paramJSONObject).intValue();
        return localUserInfo;
      }
    }
    return null;
  }

  public void clearAll()
  {
    if (this.lstFollowers != null)
      this.lstFollowers.clear();
    if (this.lstFollowings != null)
      this.lstFollowings.clear();
    if (this.lstGroups != null)
      this.lstGroups.clear();
    this.mFollowerCnt = 0;
    this.mFollowingCnt = 0;
  }

  public void followGroup(String paramString)
  {
    if ((paramString != null) && (this.mUserKey != null) && (!this.mUserKey.equalsIgnoreCase("")))
      IMDataLoadWrapper.addGroup(this.mUserKey, paramString, this);
    GroupInfo localGroupInfo = IMAgent.getInstance().getGroupInfo(paramString);
    if (localGroupInfo == null)
      IMAgent.getInstance().loadGroupInfo(paramString, null);
    while (true)
    {
      if ((this.lstGroups != null) && (localGroupInfo != null))
        this.lstGroups.add(localGroupInfo);
      return;
      IMContacts.getInstance().addWatchContacts(localGroupInfo);
    }
  }

  public void followUser(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null);
    do
    {
      return;
      if ((paramUserInfo.userKey != null) && (this.mUserKey != null) && (!this.mUserKey.equalsIgnoreCase("")))
        IMDataLoadWrapper.followUser(this.mUserKey, paramUserInfo.userKey, this);
      IMContacts.getInstance().addWatchContacts(paramUserInfo);
    }
    while ((this.lstFollowings == null) || (paramUserInfo == null));
    this.lstFollowings.add(paramUserInfo);
  }

  public int getContactsCnt()
  {
    int i = 0;
    if (this.lstFollowers != null)
      i = 0 + this.lstFollowers.size();
    int j = i;
    if (this.lstFollowings != null)
      j = i + this.lstFollowings.size();
    return j;
  }

  public int getFollowerCnt()
  {
    if (this.lstFollowers == null)
      return 0;
    return this.lstFollowers.size();
  }

  public List<UserInfo> getFollowers()
  {
    return this.lstFollowers;
  }

  public int getFollowingCnt()
  {
    if (this.lstFollowings == null)
      return 0;
    return this.lstFollowings.size();
  }

  public List<UserInfo> getFollowings()
  {
    return this.lstFollowings;
  }

  public int getGroupCnt()
  {
    if (this.lstGroups == null)
      return 0;
    return this.lstGroups.size();
  }

  public List<GroupInfo> getGroups()
  {
    return this.lstGroups;
  }

  public UserInfo getUserInfo()
  {
    return this.mUserInfo;
  }

  public String getUserKey()
  {
    return this.mUserKey;
  }

  public void init()
  {
    int j = 0;
    List localList = IMContacts.getInstance().getWatchedGroupContacts();
    int i;
    if ((localList != null) && (localList.size() > 0))
    {
      if (this.lstGroups == null)
        this.lstGroups = new ArrayList();
      i = 0;
      while (i < localList.size())
      {
        this.lstGroups.add(localList.get(i));
        i += 1;
      }
    }
    localList = IMContacts.getInstance().getWatchedUserContacts();
    if ((localList != null) && (localList.size() > 0))
    {
      i = j;
      if (this.lstFollowings == null)
      {
        this.lstFollowings = new ArrayList();
        i = j;
      }
      while (i < localList.size())
      {
        this.lstFollowings.add(localList.get(i));
        IMAgent.getInstance().addBaseUserInfo(((UserInfo)localList.get(i)).userKey, ((UserInfo)localList.get(i)).snsInfo.sns_avatar, ((UserInfo)localList.get(i)).snsInfo.sns_gender);
        i += 1;
      }
    }
  }

  public boolean isPhoneOwner()
  {
    if (this.mUserKey == null);
    String str;
    do
    {
      return false;
      str = InfoManager.getInstance().getUserProfile().getUserKey();
    }
    while ((str == null) || (!str.equalsIgnoreCase(this.mUserKey)));
    return true;
  }

  public void loadFollowers(RootNode.IInfoUpdateEventListener paramIInfoUpdateEventListener)
  {
    if (this.mUserKey != null)
      IMDataLoadWrapper.loadFollowerList(this.mUserKey, this);
    if (paramIInfoUpdateEventListener != null)
      InfoManager.getInstance().root().registerInfoUpdateListener(paramIInfoUpdateEventListener, 4);
  }

  public void loadFollowings(RootNode.IInfoUpdateEventListener paramIInfoUpdateEventListener)
  {
    if (this.mUserKey != null)
      IMDataLoadWrapper.loadFollowingList(this.mUserKey, this);
    if (paramIInfoUpdateEventListener != null)
      InfoManager.getInstance().root().registerInfoUpdateListener(paramIInfoUpdateEventListener, 5);
  }

  public void loadUserInfo(String paramString, RootNode.IInfoUpdateEventListener paramIInfoUpdateEventListener)
  {
    IMDataLoadWrapper.loadUserInfo(paramString, this);
    if (paramIInfoUpdateEventListener != null)
      InfoManager.getInstance().root().registerInfoUpdateListener(paramIInfoUpdateEventListener, 3);
  }

  public void onRecvResult(Result paramResult, Object paramObject1, IResultToken paramIResultToken, Object paramObject2)
  {
    int j = 0;
    int i = 0;
    paramObject1 = paramIResultToken.getType();
    if (paramResult.getSuccess());
    while (true)
    {
      try
      {
        if (paramObject1.equalsIgnoreCase("get_user_followers"))
        {
          paramObject1 = (String)((HashMap)paramObject2).get("user");
          if ((paramObject1 != null) && (this.mUserKey != null))
          {
            if (!paramObject1.equalsIgnoreCase(this.mUserKey))
              return;
            if (this.lstFollowers == null)
            {
              this.lstFollowers = new ArrayList();
              paramResult = ((JSONObject)paramResult.getData()).getJSONArray("data");
              if (paramResult == null)
                continue;
              if (i < paramResult.size())
              {
                paramObject1 = parseUserInfo(paramResult.getJSONObject(i));
                if (paramObject1 == null)
                  continue;
                this.lstFollowers.add(paramObject1);
                continue;
              }
            }
            else
            {
              this.lstFollowers.clear();
              continue;
            }
            this.mFollowerCnt = this.lstFollowers.size();
            InfoManager.getInstance().root().setInfoUpdate(4);
          }
        }
        else
        {
          boolean bool;
          if (paramObject1.equalsIgnoreCase("get_user_followings"))
          {
            paramObject1 = (String)((HashMap)paramObject2).get("user");
            if ((paramObject1 != null) && (this.mUserKey != null) && (paramObject1.equalsIgnoreCase(this.mUserKey)))
            {
              if (this.lstFollowings == null)
              {
                this.lstFollowings = new ArrayList();
                paramResult = ((JSONObject)paramResult.getData()).getJSONArray("data");
                if (paramResult == null)
                  continue;
                bool = isPhoneOwner();
                i = j;
                if (i < paramResult.size())
                {
                  paramObject1 = parseUserInfo(paramResult.getJSONObject(i));
                  if (paramObject1 == null)
                    break label827;
                  this.lstFollowings.add(paramObject1);
                  if (!bool)
                    break label827;
                  IMContacts.getInstance().addWatchContacts(paramObject1);
                  break label827;
                }
              }
              else
              {
                this.lstFollowings.clear();
                continue;
              }
              this.mFollowingCnt = this.lstFollowings.size();
              InfoManager.getInstance().root().setInfoUpdate(5);
            }
          }
          else if (paramObject1.equalsIgnoreCase("get_user_info"))
          {
            paramObject1 = (String)((HashMap)paramObject2).get("user");
            if ((paramObject1 != null) && (this.mUserKey != null) && (paramObject1.equalsIgnoreCase(this.mUserKey)))
            {
              paramResult = ((JSONObject)paramResult.getData()).getJSONObject("data");
              if ((paramResult != null) && (this.mUserKey != null))
              {
                if (this.mUserInfo == null)
                  this.mUserInfo = new UserInfo();
                this.mUserInfo.userKey = this.mUserKey;
                paramObject1 = paramResult.getString("avatar");
                if ((paramObject1 != null) && (!paramObject1.equalsIgnoreCase("")))
                  this.mUserInfo.snsInfo.sns_avatar = paramObject1;
                paramObject1 = paramResult.getString("username");
                if ((paramObject1 != null) && (!paramObject1.equalsIgnoreCase("")))
                  this.mUserInfo.snsInfo.sns_name = paramObject1;
                paramObject1 = paramResult.getString("signature");
                if ((paramObject1 != null) && (!paramObject1.equalsIgnoreCase("")))
                  this.mUserInfo.snsInfo.signature = paramObject1;
                paramObject1 = paramResult.getString("is_blocked");
                if ((paramObject1 != null) && (!paramObject1.equalsIgnoreCase("")))
                {
                  if (Integer.valueOf(paramObject1).intValue() == 0)
                    this.mUserInfo.isBlocked = false;
                }
                else
                {
                  paramObject1 = paramResult.getString("level");
                  if ((paramObject1 != null) && (!paramObject1.equalsIgnoreCase("")))
                    this.mUserInfo.level = Integer.valueOf(paramObject1).intValue();
                  this.mFollowerCnt = paramResult.getIntValue("num_of_follower");
                  this.mFollowingCnt = paramResult.getIntValue("num_of_following");
                  this.mUserInfo.snsInfo.sns_gender = paramResult.getString("gender");
                  paramResult = paramResult.getJSONArray("groups");
                  if (paramResult == null)
                    continue;
                  if (this.lstGroups == null)
                    this.lstGroups = new ArrayList();
                  bool = isPhoneOwner();
                  i = 0;
                  if (i >= paramResult.size())
                    continue;
                  paramObject1 = new GroupInfo();
                  paramIResultToken = paramResult.getJSONObject(i);
                  paramObject1.groupId = paramIResultToken.getString("id");
                  paramObject1.groupName = paramIResultToken.getString("groupname");
                  paramObject1.groupDesc = paramIResultToken.getString("description");
                  addGroup(paramObject1);
                  if (!bool)
                    break label836;
                  IMContacts.getInstance().addWatchContacts(paramObject1);
                  break label836;
                }
                this.mUserInfo.isBlocked = true;
                continue;
                if (bool)
                  IMContacts.getInstance().updateGroupInfo();
                InfoManager.getInstance().root().setInfoUpdate(3);
              }
            }
          }
        }
        return;
        i += 1;
        continue;
      }
      catch (Exception paramResult)
      {
        return;
      }
      label827: i += 1;
      continue;
      label836: i += 1;
    }
  }

  public void setUserInfo(UserInfo paramUserInfo)
  {
    this.mUserInfo = paramUserInfo;
    if (paramUserInfo != null)
      this.mUserKey = paramUserInfo.userKey;
  }

  public void setUserKey(String paramString)
  {
    this.mUserKey = paramString;
  }

  public void setUserKey(String paramString, int paramInt)
  {
    this.mUserKey = paramString;
    if (paramInt == 0)
      SharedCfg.getInstance().setWeiboSocialUserKey(paramString);
    do
    {
      return;
      if (paramInt == 1)
      {
        SharedCfg.getInstance().setTencentSocialUserKey(paramString);
        return;
      }
      if (paramInt == 5)
      {
        SharedCfg.getInstance().setQQUserKey(paramString);
        return;
      }
    }
    while (paramInt != 6);
    SharedCfg.getInstance().setWeChatUserKey(paramString);
  }

  public void unfollowGroup(String paramString)
  {
    if ((paramString != null) && (this.mUserKey != null) && (!this.mUserKey.equalsIgnoreCase("")))
      IMDataLoadWrapper.exitGroup(this.mUserKey, paramString, this);
    IMContacts.getInstance().unWatchGroupContacts(paramString);
    int i;
    if (this.lstGroups != null)
      i = 0;
    while (true)
    {
      if (i < this.lstGroups.size())
      {
        if (((GroupInfo)this.lstGroups.get(i)).groupId.equalsIgnoreCase(paramString))
          this.lstGroups.remove(i);
      }
      else
      {
        IMAgent.getInstance().leaveGroup(paramString);
        return;
      }
      i += 1;
    }
  }

  public void unfollowUser(String paramString)
  {
    if ((paramString != null) && (this.mUserKey != null) && (!this.mUserKey.equalsIgnoreCase("")))
      IMDataLoadWrapper.unFollowUser(this.mUserKey, paramString, this);
    IMContacts.getInstance().unWatchUserContacts(paramString);
    int i;
    if ((this.lstFollowings != null) && (paramString != null))
      i = 0;
    while (true)
    {
      if (i < this.lstFollowings.size())
      {
        if ((((UserInfo)this.lstFollowings.get(i)).userKey != null) && (((UserInfo)this.lstFollowings.get(i)).userKey.equalsIgnoreCase(paramString)))
          this.lstFollowings.remove(i);
      }
      else
        return;
      i += 1;
    }
  }

  public void updateUserInfo()
  {
    IMDataLoadWrapper.updateUserInfo(this, this);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.social.UserProfile
 * JD-Core Version:    0.6.2
 */