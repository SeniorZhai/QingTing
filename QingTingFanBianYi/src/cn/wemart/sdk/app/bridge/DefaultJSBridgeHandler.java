package cn.wemart.sdk.app.bridge;

import cn.wemart.sdk.app.Logger;

public class DefaultJSBridgeHandler
  implements WemartJSBridgeHandler
{
  private String TAG = "WemartJSBridge";

  public void handler(String paramString, WemartJSBridgeCallBack paramWemartJSBridgeCallBack)
  {
    Logger.i(this.TAG, "获取Web端数据：" + paramString);
    if (paramWemartJSBridgeCallBack != null)
      paramWemartJSBridgeCallBack.onCallBack("返回Native端默认数据");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.bridge.DefaultJSBridgeHandler
 * JD-Core Version:    0.6.2
 */