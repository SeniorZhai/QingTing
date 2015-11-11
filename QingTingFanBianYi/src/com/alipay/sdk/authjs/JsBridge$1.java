package com.alipay.sdk.authjs;

import org.json.JSONException;

class JsBridge$1
  implements Runnable
{
  JsBridge$1(JsBridge paramJsBridge, CallInfo paramCallInfo)
  {
  }

  public void run()
  {
    CallInfo.CallError localCallError = JsBridge.a(this.b, this.a);
    if (localCallError != CallInfo.CallError.a);
    try
    {
      JsBridge.a(this.b, this.a.a(), localCallError);
      return;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.authjs.JsBridge.1
 * JD-Core Version:    0.6.2
 */