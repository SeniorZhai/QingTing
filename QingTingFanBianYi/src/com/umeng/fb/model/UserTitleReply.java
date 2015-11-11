package com.umeng.fb.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserTitleReply extends Reply
{
  private static final String JSON_KEY_THREAD = "thread";
  protected String thread;

  public UserTitleReply(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1, paramString2, paramString3, paramString4, Reply.TYPE.NEW_FEEDBACK);
    this.thread = paramString1;
  }

  UserTitleReply(JSONObject paramJSONObject)
    throws JSONException
  {
    super(paramJSONObject);
    if (this.type != Reply.TYPE.NEW_FEEDBACK)
      throw new JSONException(UserTitleReply.class.getName() + ".type must be " + Reply.TYPE.NEW_FEEDBACK);
    this.thread = paramJSONObject.optString("thread");
  }

  public JSONObject toJson()
  {
    JSONObject localJSONObject = super.toJson();
    try
    {
      localJSONObject.put("thread", this.thread);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return localJSONObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.UserTitleReply
 * JD-Core Version:    0.6.2
 */