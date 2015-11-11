package fm.qingting.qtradio.im;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IMContacts
{
  private static IMContacts _instance;
  private int RECENT_CONTACTS_SIZE = 20;
  private List<GroupInfo> mLstRecentGroupInfo;
  private List<UserInfo> mLstRecentUserInfo;
  private List<GroupInfo> mLstWatchedGroupInfo;
  private List<UserInfo> mLstWatchedUserInfo;
  private boolean needToWriteRGToDB = false;
  private boolean needToWriteRUToDB = false;
  private boolean needToWriteWGToDB = false;
  private boolean needToWriteWUToDB = false;

  public static IMContacts getInstance()
  {
    if (_instance == null)
      _instance = new IMContacts();
    return _instance;
  }

  public void addRecentContacts(GroupInfo paramGroupInfo)
  {
    if (paramGroupInfo == null)
      return;
    if (this.mLstRecentGroupInfo == null)
      getRecentGroupContacts();
    int i = 0;
    while (true)
    {
      if (i >= this.mLstRecentGroupInfo.size())
        break label65;
      if (paramGroupInfo.groupId.equalsIgnoreCase(((GroupInfo)this.mLstRecentGroupInfo.get(i)).groupId))
        break;
      i += 1;
    }
    label65: if (this.mLstRecentGroupInfo.size() >= this.RECENT_CONTACTS_SIZE)
      this.mLstRecentGroupInfo.remove(this.RECENT_CONTACTS_SIZE - 1);
    this.mLstRecentGroupInfo.add(0, paramGroupInfo);
    this.needToWriteRGToDB = true;
  }

  public void addRecentContacts(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null)
      return;
    if (this.mLstRecentUserInfo == null)
      getRecentUserContacts();
    int i = 0;
    while (true)
    {
      if (i >= this.mLstRecentUserInfo.size())
        break label65;
      if (paramUserInfo.userKey.equalsIgnoreCase(((UserInfo)this.mLstRecentUserInfo.get(i)).userKey))
        break;
      i += 1;
    }
    label65: if (this.mLstRecentUserInfo.size() >= this.RECENT_CONTACTS_SIZE)
      this.mLstRecentUserInfo.remove(this.RECENT_CONTACTS_SIZE - 1);
    this.mLstRecentUserInfo.add(0, paramUserInfo);
    this.needToWriteRUToDB = true;
  }

  public void addRecentContacts(String paramString)
  {
    if (paramString == null);
    label62: 
    do
    {
      return;
      if (this.mLstRecentGroupInfo == null)
        getRecentGroupContacts();
      int i = 0;
      while (true)
      {
        if (i >= this.mLstRecentGroupInfo.size())
          break label62;
        if (paramString.equalsIgnoreCase(((GroupInfo)this.mLstRecentGroupInfo.get(i)).groupId))
          break;
        i += 1;
      }
      if (this.mLstRecentGroupInfo.size() >= this.RECENT_CONTACTS_SIZE)
        this.mLstRecentGroupInfo.remove(this.RECENT_CONTACTS_SIZE - 1);
      paramString = IMAgent.getInstance().getGroupInfo(paramString);
    }
    while (paramString == null);
    this.mLstRecentGroupInfo.add(0, paramString);
    this.needToWriteRGToDB = true;
  }

  public void addRecentGroupContacts(String paramString1, String paramString2, String paramString3)
  {
    if (hasRecentGroupContacts(paramString1))
      return;
    paramString3 = new GroupInfo();
    paramString3.groupId = paramString1;
    paramString3.groupName = paramString2;
    this.mLstRecentGroupInfo.add(paramString3);
    this.needToWriteRGToDB = true;
  }

  public void addRecentUserContacts(String paramString1, String paramString2, String paramString3)
  {
    if (hasRecentUserContacts(paramString1))
      return;
    UserInfo localUserInfo = new UserInfo();
    localUserInfo.userKey = paramString1;
    localUserInfo.snsInfo.sns_name = paramString2;
    localUserInfo.snsInfo.sns_avatar = paramString3;
    this.mLstRecentUserInfo.add(localUserInfo);
    this.needToWriteRUToDB = true;
  }

  public void addWatchContacts(GroupInfo paramGroupInfo)
  {
    if (paramGroupInfo == null)
      return;
    if (this.mLstWatchedGroupInfo == null)
      getWatchedGroupContacts();
    int i = 0;
    while (true)
    {
      if (i >= this.mLstWatchedGroupInfo.size())
        break label65;
      if (paramGroupInfo.groupId.equalsIgnoreCase(((GroupInfo)this.mLstWatchedGroupInfo.get(i)).groupId))
        break;
      i += 1;
    }
    label65: String str = InfoManager.getInstance().getUserProfile().getUserKey();
    if (str != null)
    {
      str = QTLogger.getInstance().buildIMGroupRelationLog(str, paramGroupInfo.groupId, 1);
      if (str != null)
        LogModule.getInstance().send("GroupRelation", str);
    }
    this.mLstWatchedGroupInfo.add(0, paramGroupInfo);
    this.needToWriteWGToDB = true;
  }

  public void addWatchContacts(UserInfo paramUserInfo)
  {
    if (paramUserInfo == null)
      return;
    if (this.mLstWatchedUserInfo == null)
      getWatchedUserContacts();
    int i = 0;
    while (true)
    {
      if (i >= this.mLstWatchedUserInfo.size())
        break label65;
      if (paramUserInfo.userKey.equalsIgnoreCase(((UserInfo)this.mLstWatchedUserInfo.get(i)).userKey))
        break;
      i += 1;
    }
    label65: String str = InfoManager.getInstance().getUserProfile().getUserKey();
    if (str != null)
    {
      str = QTLogger.getInstance().buildIMUserRelationLog(str, paramUserInfo.userKey, 1);
      if (str != null)
        LogModule.getInstance().send("UserRelation", str);
    }
    this.mLstWatchedUserInfo.add(0, paramUserInfo);
    this.needToWriteWUToDB = true;
  }

  public void clearAll()
  {
    if (this.mLstWatchedUserInfo != null)
      this.mLstWatchedUserInfo.clear();
    if (this.mLstWatchedGroupInfo != null)
      this.mLstWatchedGroupInfo.clear();
    if (this.mLstRecentGroupInfo != null)
      this.mLstRecentGroupInfo.clear();
    if (this.mLstRecentUserInfo != null)
      this.mLstRecentUserInfo.clear();
    DataManager.getInstance().getData("deletedb_im_contacts", null, null);
  }

  public List<GroupInfo> getRecentGroupContacts()
  {
    if (this.mLstRecentGroupInfo == null)
    {
      Object localObject = new HashMap();
      ((Map)localObject).put("type", "rg");
      localObject = DataManager.getInstance().getData("getdb_im_group_contacts", null, (Map)localObject).getResult();
      if (((Result)localObject).getSuccess())
        this.mLstRecentGroupInfo = ((List)((Result)localObject).getData());
    }
    return this.mLstRecentGroupInfo;
  }

  public List<UserInfo> getRecentUserContacts()
  {
    if (this.mLstRecentUserInfo == null)
    {
      Object localObject = new HashMap();
      ((Map)localObject).put("type", "ru");
      localObject = DataManager.getInstance().getData("getdb_im_user_contacts", null, (Map)localObject).getResult();
      if (((Result)localObject).getSuccess())
        this.mLstRecentUserInfo = ((List)((Result)localObject).getData());
    }
    return this.mLstRecentUserInfo;
  }

  public GroupInfo getWatchedGroup(String paramString)
  {
    if (paramString == null)
      return null;
    if (this.mLstWatchedGroupInfo == null)
      getWatchedGroupContacts();
    int i = 0;
    while (i < this.mLstWatchedGroupInfo.size())
    {
      if (((GroupInfo)this.mLstWatchedGroupInfo.get(i)).groupId.equalsIgnoreCase(paramString))
        return (GroupInfo)this.mLstWatchedGroupInfo.get(i);
      i += 1;
    }
    return null;
  }

  public List<GroupInfo> getWatchedGroupContacts()
  {
    if (this.mLstWatchedGroupInfo == null)
    {
      Object localObject = new HashMap();
      ((Map)localObject).put("type", "wg");
      localObject = DataManager.getInstance().getData("getdb_im_group_contacts", null, (Map)localObject).getResult();
      if (((Result)localObject).getSuccess())
        this.mLstWatchedGroupInfo = ((List)((Result)localObject).getData());
    }
    return this.mLstWatchedGroupInfo;
  }

  public List<UserInfo> getWatchedUserContacts()
  {
    if (this.mLstWatchedUserInfo == null)
    {
      Object localObject = new HashMap();
      ((Map)localObject).put("type", "wu");
      localObject = DataManager.getInstance().getData("getdb_im_user_contacts", null, (Map)localObject).getResult();
      if (((Result)localObject).getSuccess())
        this.mLstWatchedUserInfo = ((List)((Result)localObject).getData());
    }
    return this.mLstWatchedUserInfo;
  }

  public boolean hasRecentGroupContacts(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      if (this.mLstRecentGroupInfo == null)
        getRecentGroupContacts();
      int i = 0;
      while (i < this.mLstRecentGroupInfo.size())
      {
        if (paramString.equalsIgnoreCase(((GroupInfo)this.mLstRecentGroupInfo.get(i)).groupId))
          return true;
        i += 1;
      }
    }
  }

  public boolean hasRecentUserContacts(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      if (this.mLstRecentUserInfo == null)
        getRecentUserContacts();
      int i = 0;
      while (i < this.mLstRecentUserInfo.size())
      {
        if (paramString.equalsIgnoreCase(((UserInfo)this.mLstRecentUserInfo.get(i)).userKey))
          return true;
        i += 1;
      }
    }
  }

  public boolean hasWatchedGroup(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      if (this.mLstWatchedGroupInfo == null)
        getWatchedGroupContacts();
      int i = 0;
      while (i < this.mLstWatchedGroupInfo.size())
      {
        if (((GroupInfo)this.mLstWatchedGroupInfo.get(i)).groupId.equalsIgnoreCase(paramString))
          return true;
        i += 1;
      }
    }
  }

  public boolean hasWatchedUser(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return false;
      if (this.mLstWatchedUserInfo == null)
        getWatchedUserContacts();
      String str = InfoManager.getInstance().getUserProfile().getUserKey();
      if ((str != null) && (str.equalsIgnoreCase(paramString)))
        return true;
      int i = 0;
      while (i < this.mLstWatchedUserInfo.size())
      {
        if ((((UserInfo)this.mLstWatchedUserInfo.get(i)).userKey != null) && (((UserInfo)this.mLstWatchedUserInfo.get(i)).userKey.equalsIgnoreCase(paramString)))
          return true;
        i += 1;
      }
    }
  }

  public void init()
  {
  }

  public boolean needToWriteRGToDB()
  {
    return this.needToWriteRGToDB;
  }

  public boolean needToWriteRUToDB()
  {
    return this.needToWriteRUToDB;
  }

  public boolean needToWriteWGToDB()
  {
    return this.needToWriteWGToDB;
  }

  public boolean needToWriteWUToDB()
  {
    return this.needToWriteWUToDB;
  }

  public void removeRecentGroupContact(String paramString)
  {
    if (paramString == null)
      return;
    if (this.mLstRecentGroupInfo == null)
      getRecentGroupContacts();
    int i = 0;
    while (true)
    {
      if (i < this.mLstRecentGroupInfo.size())
      {
        if (paramString.equalsIgnoreCase(((GroupInfo)this.mLstRecentGroupInfo.get(i)).groupId))
          this.mLstRecentGroupInfo.remove(i);
      }
      else
      {
        this.needToWriteRGToDB = true;
        return;
      }
      i += 1;
    }
  }

  public void removeRecentUserContact(String paramString)
  {
    if (paramString == null)
      return;
    if (this.mLstRecentUserInfo == null)
      getRecentUserContacts();
    int i = 0;
    while (true)
    {
      if (i < this.mLstRecentUserInfo.size())
      {
        if (paramString.equalsIgnoreCase(((UserInfo)this.mLstRecentUserInfo.get(i)).userKey))
          this.mLstRecentUserInfo.remove(i);
      }
      else
      {
        this.needToWriteRUToDB = true;
        return;
      }
      i += 1;
    }
  }

  public void unWatchGroupContacts(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mLstWatchedGroupInfo.size())
      {
        if (((GroupInfo)this.mLstWatchedGroupInfo.get(i)).groupId.equalsIgnoreCase(paramString))
        {
          this.mLstWatchedGroupInfo.remove(i);
          String str = InfoManager.getInstance().getUserProfile().getUserKey();
          if (str != null)
          {
            paramString = QTLogger.getInstance().buildIMGroupRelationLog(str, paramString, 0);
            if (paramString != null)
              LogModule.getInstance().send("GroupRelation", paramString);
          }
          this.needToWriteWGToDB = true;
          return;
        }
        i += 1;
      }
    }
  }

  public void unWatchUserContacts(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mLstWatchedUserInfo.size())
      {
        if ((((UserInfo)this.mLstWatchedUserInfo.get(i)).userKey != null) && (((UserInfo)this.mLstWatchedUserInfo.get(i)).userKey.equalsIgnoreCase(paramString)))
        {
          this.mLstWatchedUserInfo.remove(i);
          String str = InfoManager.getInstance().getUserProfile().getUserKey();
          if (str != null)
          {
            paramString = QTLogger.getInstance().buildIMUserRelationLog(str, paramString, 0);
            if (paramString != null)
              LogModule.getInstance().send("UserRelation", paramString);
          }
          this.needToWriteWUToDB = true;
          return;
        }
        i += 1;
      }
    }
  }

  public void updateGroupInfo()
  {
    int j = 0;
    getWatchedGroupContacts();
    int i;
    if (this.mLstWatchedGroupInfo != null)
    {
      i = 0;
      while (i < this.mLstWatchedGroupInfo.size())
      {
        IMAgent.getInstance().loadGroupInfo(((GroupInfo)this.mLstWatchedGroupInfo.get(i)).groupId, null);
        i += 1;
      }
    }
    getRecentGroupContacts();
    if (this.mLstRecentGroupInfo != null)
    {
      i = j;
      while (i < this.mLstRecentGroupInfo.size())
      {
        IMAgent.getInstance().loadGroupInfo(((GroupInfo)this.mLstRecentGroupInfo.get(i)).groupId, null);
        i += 1;
      }
    }
  }

  public void updateGroupInfo(String paramString)
  {
    if (paramString == null);
    while (true)
    {
      return;
      GroupInfo localGroupInfo = IMAgent.getInstance().getGroupInfo(paramString);
      if (localGroupInfo != null)
      {
        int i = 0;
        while (i < this.mLstWatchedGroupInfo.size())
        {
          if (((GroupInfo)this.mLstWatchedGroupInfo.get(i)).groupId.equalsIgnoreCase(paramString))
          {
            ((GroupInfo)this.mLstWatchedGroupInfo.get(i)).update(localGroupInfo);
            this.needToWriteWGToDB = true;
            return;
          }
          i += 1;
        }
      }
    }
  }

  public void writeToDB()
  {
    HashMap localHashMap;
    if (this.needToWriteRGToDB)
    {
      this.needToWriteRGToDB = false;
      localHashMap = new HashMap();
      localHashMap.put("rg", this.mLstRecentGroupInfo);
      localHashMap.put("type", "rg");
      DataManager.getInstance().getData("updatedb_im_contacts", null, localHashMap);
    }
    if (this.needToWriteRUToDB)
    {
      this.needToWriteRUToDB = false;
      localHashMap = new HashMap();
      localHashMap.put("ru", this.mLstRecentUserInfo);
      localHashMap.put("type", "ru");
      DataManager.getInstance().getData("updatedb_im_contacts", null, localHashMap);
    }
    if (this.needToWriteWGToDB)
    {
      this.needToWriteWGToDB = false;
      localHashMap = new HashMap();
      localHashMap.put("wg", this.mLstWatchedGroupInfo);
      localHashMap.put("type", "wg");
      DataManager.getInstance().getData("updatedb_im_contacts", null, localHashMap);
    }
    if (this.needToWriteWUToDB)
    {
      this.needToWriteWUToDB = false;
      localHashMap = new HashMap();
      localHashMap.put("wu", this.mLstWatchedUserInfo);
      localHashMap.put("type", "wu");
      DataManager.getInstance().getData("updatedb_im_contacts", null, localHashMap);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.im.IMContacts
 * JD-Core Version:    0.6.2
 */