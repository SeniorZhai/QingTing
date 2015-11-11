package com.google.protobuf;

public abstract interface RpcController
{
  public abstract String errorText();

  public abstract boolean failed();

  public abstract boolean isCanceled();

  public abstract void notifyOnCancel(RpcCallback<Object> paramRpcCallback);

  public abstract void reset();

  public abstract void setFailed(String paramString);

  public abstract void startCancel();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.RpcController
 * JD-Core Version:    0.6.2
 */