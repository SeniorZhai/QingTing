package com.umeng.fb.model;

import android.text.TextUtils;
import com.umeng.fb.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo
{
  private static final String TAG = UserInfo.class.getName();
  int ageGroup = -1;
  Map<String, String> contact;
  String gender = "";
  Map<String, String> remark;

  public UserInfo()
  {
    this.contact = new HashMap();
    this.remark = new HashMap();
  }

  UserInfo(JSONObject paramJSONObject)
    throws JSONException
  {
    this.ageGroup = paramJSONObject.optInt("age_group", -1);
    this.gender = paramJSONObject.optString("gender", "");
    this.contact = new HashMap();
    this.remark = new HashMap();
    Object localObject1 = paramJSONObject.optJSONObject("contact");
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = ((JSONObject)localObject1).keys();
      while (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        this.contact.put(str, ((JSONObject)localObject1).getString(str));
      }
    }
    paramJSONObject = paramJSONObject.optJSONObject("remark");
    Log.d(TAG, "" + paramJSONObject);
    if (paramJSONObject != null)
    {
      localObject1 = paramJSONObject.keys();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (String)((Iterator)localObject1).next();
        this.remark.put(localObject2, paramJSONObject.getString((String)localObject2));
      }
    }
  }

  public int getAgeGroup()
  {
    return this.ageGroup;
  }

  public Map<String, String> getContact()
  {
    return this.contact;
  }

  public String getGender()
  {
    return this.gender;
  }

  public Map<String, String> getRemark()
  {
    return this.remark;
  }

  public void setAgeGroup(int paramInt)
  {
    this.ageGroup = paramInt;
  }

  public void setContact(Map<String, String> paramMap)
  {
    this.contact = paramMap;
  }

  public void setGender(String paramString)
  {
    this.gender = paramString;
  }

  public void setRemark(Map<String, String> paramMap)
  {
    this.remark = paramMap;
  }

  public JSONObject toJson()
  {
    JSONObject localJSONObject1 = new JSONObject();
    Map.Entry localEntry;
    do
    {
      try
      {
        if (this.ageGroup > -1)
          localJSONObject1.put("age_group", this.ageGroup);
        if (!TextUtils.isEmpty(this.gender))
          localJSONObject1.put("gender", this.gender);
        if ((this.contact == null) || (this.contact.size() <= 0))
          continue;
        JSONObject localJSONObject2 = new JSONObject();
        localIterator = this.contact.entrySet().iterator();
        while (localIterator.hasNext())
        {
          localEntry = (Map.Entry)localIterator.next();
          localJSONObject2.put((String)localEntry.getKey(), localEntry.getValue());
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        return localJSONObject1;
      }
      localJSONObject1.put("contact", localJSONException);
    }
    while ((this.remark == null) || (this.remark.size() <= 0));
    JSONObject localJSONObject3 = new JSONObject();
    Iterator localIterator = this.remark.entrySet().iterator();
    while (localIterator.hasNext())
    {
      localEntry = (Map.Entry)localIterator.next();
      localJSONObject3.put((String)localEntry.getKey(), localEntry.getValue());
    }
    localJSONObject1.put("remark", localJSONObject3);
    return localJSONObject1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.UserInfo
 * JD-Core Version:    0.6.2
 */