package fm.qingting.qtradio.im;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class ImBlackList
{
  private static final String ID = "imblacklist";
  private static final String QUARYID = "imlist";
  private static List<BlackListItem> sBlackList;

  public static void addToBlackList(Context paramContext, IMMessage paramIMMessage)
  {
    if (sBlackList == null)
    {
      sBlackList = getBlackList(paramContext);
      if (sBlackList == null)
        sBlackList = new ArrayList();
    }
    if (inBlackList(paramContext, paramIMMessage.mFromID))
      return;
    BlackListItem localBlackListItem = new BlackListItem();
    localBlackListItem.name = paramIMMessage.mFromName;
    localBlackListItem.id = paramIMMessage.mFromID;
    localBlackListItem.avatar = BaseUserInfoPool.getAvatar(paramIMMessage.mFromID);
    sBlackList.add(0, localBlackListItem);
    saveBlackList(paramContext);
  }

  public static void addToBlackList(Context paramContext, UserInfo paramUserInfo)
  {
    if (sBlackList == null)
    {
      sBlackList = getBlackList(paramContext);
      if (sBlackList == null)
        sBlackList = new ArrayList();
    }
    if (inBlackList(paramContext, paramUserInfo.userKey))
      return;
    BlackListItem localBlackListItem = new BlackListItem();
    localBlackListItem.name = paramUserInfo.snsInfo.sns_name;
    localBlackListItem.avatar = paramUserInfo.snsInfo.sns_avatar;
    localBlackListItem.id = paramUserInfo.userKey;
    sBlackList.add(0, localBlackListItem);
    saveBlackList(paramContext);
  }

  public static List<BlackListItem> getBlackList(Context paramContext)
  {
    int i = 0;
    if (sBlackList != null)
      return sBlackList;
    sBlackList = new ArrayList();
    paramContext = paramContext.getSharedPreferences("imblacklist", 0).getString("imlist", "");
    if ((paramContext == null) || (paramContext.length() == 0))
      return null;
    paramContext = (JSONArray)JSONArray.parse(paramContext);
    if (paramContext != null)
      while (i < paramContext.size())
      {
        JSONObject localJSONObject = paramContext.getJSONObject(i);
        BlackListItem localBlackListItem = new BlackListItem();
        localBlackListItem.name = localJSONObject.getString("name");
        localBlackListItem.avatar = localJSONObject.getString("avatar");
        localBlackListItem.id = localJSONObject.getString("id");
        sBlackList.add(localBlackListItem);
        i += 1;
      }
    return sBlackList;
  }

  public static int getBlackListCnt(Context paramContext)
  {
    if (sBlackList == null)
    {
      sBlackList = getBlackList(paramContext);
      if (sBlackList == null)
        sBlackList = new ArrayList();
    }
    return sBlackList.size();
  }

  public static boolean inBlackList(Context paramContext, String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      if (sBlackList == null)
      {
        sBlackList = getBlackList(paramContext);
        if (sBlackList == null)
          sBlackList = new ArrayList();
      }
      int i = 0;
      while (i < sBlackList.size())
      {
        if (((BlackListItem)sBlackList.get(i)).id.equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  private static void refreshViewIfNeed()
  {
    ViewController localViewController = ControllerManager.getInstance().getLastViewController();
    if (localViewController.controllerName.equalsIgnoreCase("blockedmembers"))
      localViewController.config("resetData", null);
  }

  public static void removeFromBlackList(Context paramContext, BlackListItem paramBlackListItem)
  {
    if (!inBlackList(paramContext, paramBlackListItem.id))
      return;
    sBlackList.remove(paramBlackListItem);
    saveBlackList(paramContext);
    refreshViewIfNeed();
  }

  public static void saveBlackList(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("imblacklist", 0).edit();
    if ((sBlackList == null) || (sBlackList.size() == 0))
    {
      paramContext.putString("imlist", "");
      paramContext.commit();
    }
    String str;
    do
    {
      return;
      str = JSON.toJSONString(sBlackList);
    }
    while (str == null);
    paramContext.putString("imlist", str);
    paramContext.commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.ImBlackList
 * JD-Core Version:    0.6.2
 */