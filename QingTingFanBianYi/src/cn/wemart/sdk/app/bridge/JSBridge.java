package cn.wemart.sdk.app.bridge;

public abstract interface JSBridge
{
  public abstract void send(String paramString);

  public abstract void send(String paramString, WemartJSBridgeCallBack paramWemartJSBridgeCallBack);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.bridge.JSBridge
 * JD-Core Version:    0.6.2
 */