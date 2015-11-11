package fm.qingting.qtradio.im;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultRecvHandler;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class IMDataLoadWrapper
{
  public static void addGroup(String paramString1, String paramString2, IResultRecvHandler paramIResultRecvHandler)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("group", paramString2);
    localHashMap.put("user", paramString1);
    DataManager.getInstance().getData("follow_group", paramIResultRecvHandler, localHashMap);
  }

  public static void exitGroup(String paramString1, String paramString2, IResultRecvHandler paramIResultRecvHandler)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("group", paramString2);
    localHashMap.put("user", paramString1);
    DataManager.getInstance().getData("unfollow_group", paramIResultRecvHandler, localHashMap);
  }

  public static void followUser(String paramString1, String paramString2, IResultRecvHandler paramIResultRecvHandler)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("following", paramString2);
    localHashMap.put("user", paramString1);
    DataManager.getInstance().getData("add_following", paramIResultRecvHandler, localHashMap);
  }

  public static void loadBaseUserInfo(String paramString, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("user", paramString);
    DataManager.getInstance().getData("get_im_base_user_info", paramIResultRecvHandler, localHashMap);
  }

  public static void loadFollowerList(String paramString, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("user", paramString);
    DataManager.getInstance().getData("get_user_followers", paramIResultRecvHandler, localHashMap);
  }

  public static void loadFollowingList(String paramString, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("user", paramString);
    DataManager.getInstance().getData("get_user_followings", paramIResultRecvHandler, localHashMap);
  }

  public static void loadGroupInfo(String paramString, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("group", paramString);
    DataManager.getInstance().getData("get_group_info", paramIResultRecvHandler, localHashMap);
  }

  public static void loadGroupUserList(String paramString, int paramInt1, int paramInt2, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("group", paramString);
    localHashMap.put("curpage", String.valueOf(paramInt1));
    localHashMap.put("size", String.valueOf(paramInt2));
    DataManager.getInstance().getData("get_group_users", paramIResultRecvHandler, localHashMap);
  }

  public static void loadUserInfo(String paramString, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramString == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("user", paramString);
    DataManager.getInstance().getData("get_user_info", paramIResultRecvHandler, localHashMap);
  }

  public static void unFollowUser(String paramString1, String paramString2, IResultRecvHandler paramIResultRecvHandler)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("unfollow", paramString2);
    localHashMap.put("user", paramString1);
    DataManager.getInstance().getData("cancel_following", paramIResultRecvHandler, localHashMap);
  }

  public static void updateUserInfo(UserProfile paramUserProfile, IResultRecvHandler paramIResultRecvHandler)
  {
    if (paramUserProfile == null)
      return;
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    try
    {
      localHashMap2.put("userName", paramUserProfile.getUserInfo().snsInfo.sns_name);
      localHashMap2.put("avatar", paramUserProfile.getUserInfo().snsInfo.sns_avatar);
      localHashMap2.put("gender", paramUserProfile.getUserInfo().snsInfo.sns_gender);
      if ((paramUserProfile.getUserInfo().snsInfo.signature != null) && (!paramUserProfile.getUserInfo().snsInfo.signature.equalsIgnoreCase("")))
        localHashMap2.put("signature", paramUserProfile.getUserInfo().snsInfo.signature);
      localHashMap1.put("postdata", localHashMap2);
      localHashMap1.put("user", URLEncoder.encode(paramUserProfile.getUserKey(), "UTF-8"));
      DataManager.getInstance().getData("update_user_info", paramIResultRecvHandler, localHashMap1);
      return;
    }
    catch (Exception paramUserProfile)
    {
      paramUserProfile.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.IMDataLoadWrapper
 * JD-Core Version:    0.6.2
 */