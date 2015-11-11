package fm.qingting.qtradio.im.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.room.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class IMMessage
{
  public int chatType;
  public String mFromAvatar = "";
  public String mFromGroupId = "";
  public String mFromID = "";
  public String mFromName = "";
  public String mGender = "n";
  public String mGroupName = "";
  public String mImage;
  public int mLike;
  public String mMessage = "";
  public String mMsgId = "";
  public String mToUserId = "";
  public long msgSeq = -1L;
  public long publish;

  public static JSONObject buildBarrage(String paramString1, String paramString2, UserInfo paramUserInfo)
  {
    if (paramUserInfo == null);
    while ((paramString1 == null) && (paramString2 == null))
      return null;
    paramString2 = new JSONObject();
    try
    {
      paramString2.put("msg", paramString1);
      paramString2.put("sendTime", Long.valueOf(System.currentTimeMillis() / 1000L));
      return paramString2;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public static String buildGroupInfo(String paramString1, String paramString2, int paramInt)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("groupDesc", paramString1);
      localJSONObject.put("ownerId", paramString2);
      localJSONObject.put("approve", Integer.valueOf(paramInt));
      label35: return localJSONObject.toString();
    }
    catch (Exception paramString1)
    {
      break label35;
    }
  }

  public static JSONObject buildIMMessage(String paramString, UserInfo paramUserInfo, GroupInfo paramGroupInfo)
  {
    if ((paramString == null) || (paramUserInfo == null) || (paramString.equalsIgnoreCase("")) || (paramGroupInfo == null))
      return null;
    paramUserInfo = new JSONObject();
    try
    {
      paramUserInfo.put("msg", paramString);
      paramUserInfo.put("sendTime", Long.valueOf(System.currentTimeMillis() / 1000L));
      paramUserInfo.put("groupName", paramGroupInfo.groupName);
      return paramUserInfo;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static JSONObject buildIMMessage(String paramString, UserInfo paramUserInfo1, UserInfo paramUserInfo2)
  {
    if ((paramString == null) || (paramUserInfo1 == null) || (paramString.equalsIgnoreCase("")) || (paramUserInfo2 == null))
      return null;
    paramUserInfo1 = new JSONObject();
    try
    {
      paramUserInfo1.put("msg", paramString);
      paramUserInfo1.put("sendTime", Long.valueOf(System.currentTimeMillis() / 1000L));
      return paramUserInfo1;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public static List<IMMessage> parseData(List<String> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return null;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      IMMessage localIMMessage = new IMMessage();
      if (parseData((String)paramList.get(i), localIMMessage))
        localArrayList.add(localIMMessage);
      i += 1;
    }
    return localArrayList;
  }

  public static boolean parseData(JSONObject paramJSONObject, IMMessage paramIMMessage)
  {
    if ((paramJSONObject == null) || (paramIMMessage == null))
      return false;
    try
    {
      String str = paramJSONObject.getString("msg");
      long l2 = paramJSONObject.getLongValue("sendTime");
      long l1 = l2;
      if (l2 == 0L)
        l1 = paramJSONObject.getLongValue("publish");
      paramIMMessage.mToUserId = paramJSONObject.getString("toUserId");
      paramIMMessage.mMessage = str;
      paramIMMessage.publish = l1;
      return true;
    }
    catch (Exception paramJSONObject)
    {
    }
    return false;
  }

  public static boolean parseData(IMMessage paramIMMessage1, IMMessage paramIMMessage2)
  {
    if ((paramIMMessage1 == null) || (paramIMMessage2 == null))
      return false;
    return parseData(paramIMMessage1.mMessage, paramIMMessage2);
  }

  public static boolean parseData(String paramString, IMMessage paramIMMessage)
  {
    if ((paramString == null) || (paramIMMessage == null))
      return false;
    while (true)
      try
      {
        JSONObject localJSONObject = (JSONObject)JSON.parse(paramString);
        String str1 = localJSONObject.getString("msg");
        paramString = localJSONObject.getString("fromUserId");
        if (paramString == null)
        {
          paramString = localJSONObject.getString("userId");
          int i = localJSONObject.getIntValue("chatType");
          String str2 = localJSONObject.getString("groupId");
          long l2 = localJSONObject.getLongValue("sendTime");
          long l1 = l2;
          if (l2 == 0L)
            l1 = localJSONObject.getLongValue("publish");
          String str3 = localJSONObject.getString("groupName");
          paramIMMessage.mToUserId = localJSONObject.getString("toUserId");
          paramIMMessage.mFromAvatar = localJSONObject.getString("fromAvatar");
          if (paramIMMessage.mFromAvatar == null)
            paramIMMessage.mFromAvatar = localJSONObject.getString("userAvatar");
          paramIMMessage.mMessage = str1;
          paramIMMessage.mFromID = paramString;
          paramIMMessage.chatType = i;
          paramIMMessage.mFromGroupId = str2;
          paramIMMessage.publish = l1;
          paramIMMessage.mGroupName = str3;
          return true;
        }
      }
      catch (Exception paramString)
      {
        return false;
      }
  }

  public static boolean parseEvent(String paramString, IMMessage paramIMMessage)
  {
    if ((paramString == null) || (paramIMMessage == null))
      return false;
    try
    {
      Object localObject = (JSONObject)JSON.parse(paramString);
      if (localObject != null)
      {
        paramString = ((JSONObject)localObject).getString("from");
        String str1 = ((JSONObject)localObject).getString("to");
        String str2 = ((JSONObject)localObject).getString("event");
        paramIMMessage.mFromID = paramString;
        paramIMMessage.mFromGroupId = str1;
        paramIMMessage.mFromName = ((JSONObject)localObject).getString("fromName");
        paramIMMessage.mGender = ((JSONObject)localObject).getString("fromGender");
        paramIMMessage.mMsgId = ((JSONObject)localObject).getString("id");
        localObject = ((JSONObject)localObject).getJSONArray("body");
        if (localObject != null)
        {
          parseData(((JSONArray)localObject).getJSONObject(0), paramIMMessage);
          if (paramIMMessage != null)
            if (str2.equalsIgnoreCase("peer"))
            {
              paramIMMessage.chatType = 0;
              paramIMMessage.mFromID = paramString;
              paramIMMessage.mToUserId = str1;
            }
            else if (str2.equalsIgnoreCase("group"))
            {
              paramIMMessage.chatType = 1;
              paramIMMessage.mFromGroupId = str1;
              paramIMMessage.mFromID = paramString;
            }
        }
      }
    }
    catch (Exception paramString)
    {
    }
    return false;
    return true;
  }

  public GroupInfo buildGroupInfo()
  {
    GroupInfo localGroupInfo = new GroupInfo();
    localGroupInfo.groupId = this.mFromGroupId;
    localGroupInfo.groupName = this.mGroupName;
    return localGroupInfo;
  }

  public UserInfo buildUserInfo()
  {
    UserInfo localUserInfo = new UserInfo();
    localUserInfo.userKey = this.mFromID;
    localUserInfo.snsInfo.sns_name = this.mFromName;
    localUserInfo.snsInfo.sns_avatar = this.mFromAvatar;
    localUserInfo.snsInfo.sns_gender = this.mGender;
    return localUserInfo;
  }

  public boolean isBarrage()
  {
    return (this.chatType == 2) || (this.chatType == 3);
  }

  public boolean isGroupMsg()
  {
    return this.chatType == 1;
  }

  public boolean isPGCBarrage()
  {
    return this.chatType == 3;
  }

  public boolean isUGCBarrage()
  {
    return this.chatType == 2;
  }

  public String toString()
  {
    return JSON.toJSONString(this);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.message.IMMessage
 * JD-Core Version:    0.6.2
 */