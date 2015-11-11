package com.alipay.sdk.authjs;

import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

class JsBridge$2 extends TimerTask
{
  JsBridge$2(JsBridge paramJsBridge, CallInfo paramCallInfo)
  {
  }

  public void run()
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("toastCallBack", "true");
      CallInfo localCallInfo = new CallInfo("callback");
      localCallInfo.a(this.a.a());
      localCallInfo.a(localJSONObject);
      JsBridge.a(this.b).a(localCallInfo);
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.authjs.JsBridge.2
 * JD-Core Version:    0.6.2
 */