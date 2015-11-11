package com.umeng.fb.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserReply extends Reply
{
  public UserReply(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1, paramString2, paramString3, paramString4, Reply.TYPE.USER_REPLY);
  }

  UserReply(JSONObject paramJSONObject)
    throws JSONException
  {
    super(paramJSONObject);
    if (this.type != Reply.TYPE.USER_REPLY)
      throw new JSONException(UserReply.class.getName() + ".type must be " + Reply.TYPE.USER_REPLY);
  }

  public JSONObject toJson()
  {
    return super.toJson();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.UserReply
 * JD-Core Version:    0.6.2
 */