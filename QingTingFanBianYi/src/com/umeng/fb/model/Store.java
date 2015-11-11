package com.umeng.fb.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Store
{
  private static Store INSTANCE;
  private static final String PREFS_CONVERSATIONS = "umeng_feedback_conversations";
  private static final String PREFS_USER_INFO = "umeng_feedback_user_info";
  private static final String PREFS_USER_INFO_KEY = "user";
  private static final String PREFS_USER_INFO_LAST_SYNC_KEY = "last_sync_at";
  private static final String PREFS_USER_INFO_LAST_UPDATE_KEY = "last_update_at";
  private static final String TAG = Store.class.getName();
  private Context mContext;

  private Store(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }

  public static Store getInstance(Context paramContext)
  {
    if (INSTANCE == null)
      INSTANCE = new Store(paramContext);
    return INSTANCE;
  }

  void clear()
  {
    this.mContext.getSharedPreferences("umeng_feedback_conversations", 0).edit().clear().commit();
    this.mContext.getSharedPreferences("umeng_feedback_user_info", 0).edit().clear().commit();
  }

  public List<String> getAllConversationIds()
  {
    Object localObject = this.mContext.getSharedPreferences("umeng_feedback_conversations", 0).getAll();
    ArrayList localArrayList = new ArrayList();
    localObject = ((Map)localObject).keySet().iterator();
    while (((Iterator)localObject).hasNext())
      localArrayList.add((String)((Iterator)localObject).next());
    return localArrayList;
  }

  public Conversation getConversationById(String paramString)
  {
    try
    {
      paramString = new Conversation(paramString, new JSONArray(this.mContext.getSharedPreferences("umeng_feedback_conversations", 0).getString(paramString, "")), this.mContext);
      return paramString;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public UserInfo getUserInfo()
  {
    Object localObject = this.mContext.getSharedPreferences("umeng_feedback_user_info", 0).getString("user", "");
    if ("".equals(localObject))
      return null;
    try
    {
      localObject = new UserInfo(new JSONObject((String)localObject));
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }

  public long getUserInfoLastSyncAt()
  {
    return this.mContext.getSharedPreferences("umeng_feedback_user_info", 0).getLong("last_sync_at", 0L);
  }

  public long getUserInfoLastUpdateAt()
  {
    return this.mContext.getSharedPreferences("umeng_feedback_user_info", 0).getLong("last_update_at", 0L);
  }

  public void saveCoversation(Conversation paramConversation)
  {
    this.mContext.getSharedPreferences("umeng_feedback_conversations", 0).edit().putString(paramConversation.getId(), paramConversation.toJson().toString()).commit();
  }

  public void saveUserInfo(UserInfo paramUserInfo)
  {
    this.mContext.getSharedPreferences("umeng_feedback_user_info", 0).edit().putString("user", paramUserInfo.toJson().toString()).putLong("last_update_at", System.currentTimeMillis()).commit();
  }

  public void setUserInfoLastSyncAt(long paramLong)
  {
    this.mContext.getSharedPreferences("umeng_feedback_user_info", 0).edit().putLong("last_sync_at", paramLong).commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.Store
 * JD-Core Version:    0.6.2
 */