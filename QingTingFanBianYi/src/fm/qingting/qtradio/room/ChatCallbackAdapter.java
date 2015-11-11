package fm.qingting.qtradio.room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract interface ChatCallbackAdapter
{
  public abstract void callback(JSONArray paramJSONArray)
    throws JSONException;

  public abstract void on(String paramString, JSONArray paramJSONArray);

  public abstract void on(String paramString, JSONObject paramJSONObject);

  public abstract void onConnect();

  public abstract void onConnectFailure();

  public abstract void onDisconnect();

  public abstract void onMessage(String paramString);

  public abstract void onMessage(JSONObject paramJSONObject);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.room.ChatCallbackAdapter
 * JD-Core Version:    0.6.2
 */