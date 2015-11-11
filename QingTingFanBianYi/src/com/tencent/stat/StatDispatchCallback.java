package com.tencent.stat;

abstract interface StatDispatchCallback
{
  public abstract void onDispatchFailure();

  public abstract void onDispatchSuccess();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.StatDispatchCallback
 * JD-Core Version:    0.6.2
 */