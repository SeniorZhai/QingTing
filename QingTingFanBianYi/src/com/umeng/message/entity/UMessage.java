package com.umeng.message.entity;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UMessage
{
  public static final String DISPLAY_TYPE_CUSTOM = "custom";
  public static final String DISPLAY_TYPE_NOTIFICATION = "notification";
  public static final String NOTIFICATION_GO_ACTIVITY = "go_activity";
  public static final String NOTIFICATION_GO_APP = "go_app";
  public static final String NOTIFICATION_GO_CUSTOM = "go_custom";
  public static final String NOTIFICATION_GO_URL = "go_url";
  private JSONObject a;
  public String activity;
  public String after_open;
  public String alias;
  public int builder_id;
  public String custom;
  public String display_type;
  public Map<String, String> extra;
  public String icon;
  public String img;
  public String largeIcon;
  public String msg_id;
  public boolean play_lights;
  public boolean play_sound;
  public boolean play_vibrate;
  public long random_min;
  public boolean screen_on;
  public String sound;
  public String text;
  public String ticker;
  public String title;
  public String url;

  public UMessage(JSONObject paramJSONObject)
    throws JSONException
  {
    this.a = paramJSONObject;
    this.msg_id = paramJSONObject.getString("msg_id");
    this.display_type = paramJSONObject.getString("display_type");
    this.alias = paramJSONObject.optString("alias");
    this.random_min = paramJSONObject.optLong("random_min");
    Object localObject = paramJSONObject.getJSONObject("body");
    this.ticker = ((JSONObject)localObject).optString("ticker");
    this.title = ((JSONObject)localObject).optString("title");
    this.text = ((JSONObject)localObject).optString("text");
    this.play_vibrate = ((JSONObject)localObject).optBoolean("play_vibrate", true);
    this.play_lights = ((JSONObject)localObject).optBoolean("play_lights", true);
    this.play_sound = ((JSONObject)localObject).optBoolean("play_sound", true);
    this.screen_on = ((JSONObject)localObject).optBoolean("screen_on", false);
    this.url = ((JSONObject)localObject).optString("url");
    this.img = ((JSONObject)localObject).optString("img");
    this.sound = ((JSONObject)localObject).optString("sound");
    this.icon = ((JSONObject)localObject).optString("icon");
    this.after_open = ((JSONObject)localObject).optString("after_open");
    this.largeIcon = ((JSONObject)localObject).optString("largeIcon");
    this.activity = ((JSONObject)localObject).optString("activity");
    this.custom = ((JSONObject)localObject).optString("custom");
    this.builder_id = ((JSONObject)localObject).optInt("builder_id", 0);
    paramJSONObject = paramJSONObject.optJSONObject("extra");
    if ((paramJSONObject != null) && (paramJSONObject.keys() != null))
    {
      this.extra = new HashMap();
      localObject = paramJSONObject.keys();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        this.extra.put(str, paramJSONObject.getString(str));
      }
    }
  }

  public JSONObject getRaw()
  {
    return this.a;
  }

  public boolean hasResourceFromInternet()
  {
    return (isLargeIconFromInternet()) || (isSoundFromInternet());
  }

  public boolean isLargeIconFromInternet()
  {
    return !TextUtils.isEmpty(this.img);
  }

  public boolean isSoundFromInternet()
  {
    return (!TextUtils.isEmpty(this.sound)) && ((this.sound.startsWith("http://")) || (this.sound.startsWith("https://")));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.entity.UMessage
 * JD-Core Version:    0.6.2
 */