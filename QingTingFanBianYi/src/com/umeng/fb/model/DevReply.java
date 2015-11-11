package com.umeng.fb.model;

import org.json.JSONException;
import org.json.JSONObject;

public class DevReply extends Reply
{
  protected String user_name;

  private DevReply(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    super(paramString1, paramString2, paramString3, paramString4, Reply.TYPE.DEV_REPLY);
    this.user_name = paramString5;
  }

  public DevReply(JSONObject paramJSONObject)
    throws JSONException
  {
    super(paramJSONObject);
    if (this.type != Reply.TYPE.DEV_REPLY)
      throw new JSONException(DevReply.class.getName() + ".type must be " + Reply.TYPE.DEV_REPLY);
    this.user_name = paramJSONObject.optString("user_name", "");
  }

  public JSONObject toJson()
  {
    JSONObject localJSONObject = super.toJson();
    if (localJSONObject != null)
      try
      {
        localJSONObject.put("user_name", this.user_name);
        return localJSONObject;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.model.DevReply
 * JD-Core Version:    0.6.2
 */