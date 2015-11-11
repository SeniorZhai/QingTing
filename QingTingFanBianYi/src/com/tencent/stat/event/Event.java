package com.tencent.stat.event;

import android.content.Context;
import android.util.Log;
import com.tencent.stat.StatConfig;
import com.tencent.stat.StatStore;
import com.tencent.stat.common.StatCommonHelper;
import com.tencent.stat.common.User;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Event
{
  protected String appkey;
  protected Context ctx;
  protected int sessionId;
  protected long timestamp;
  protected User user = null;

  Event(Context paramContext, int paramInt)
  {
    this.ctx = paramContext;
    this.appkey = StatConfig.getAppKey(paramContext);
    this.timestamp = (System.currentTimeMillis() / 1000L);
    this.sessionId = paramInt;
    this.user = StatStore.getInstance(paramContext).getUser(paramContext);
  }

  public boolean encode(JSONObject paramJSONObject)
  {
    try
    {
      StatCommonHelper.jsonPut(paramJSONObject, "ky", this.appkey);
      paramJSONObject.put("et", getType().GetIntValue());
      paramJSONObject.put("ui", this.user.getUid());
      StatCommonHelper.jsonPut(paramJSONObject, "mc", this.user.getMac());
      paramJSONObject.put("si", this.sessionId);
      paramJSONObject.put("ts", this.timestamp);
      boolean bool = onEncode(paramJSONObject);
      return bool;
    }
    catch (JSONException paramJSONObject)
    {
      Log.e("Event", "Failed to encode", paramJSONObject);
    }
    return false;
  }

  public Context getContext()
  {
    return this.ctx;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public abstract EventType getType();

  public abstract boolean onEncode(JSONObject paramJSONObject)
    throws JSONException;

  public String toJsonString()
  {
    JSONObject localJSONObject = new JSONObject();
    encode(localJSONObject);
    return localJSONObject.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.event.Event
 * JD-Core Version:    0.6.2
 */