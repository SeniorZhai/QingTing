package com.neusoft.ssp.aidl;

import android.os.IInterface;

public abstract interface ITaskCallback extends IInterface
{
  public abstract void notifyRequest(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.aidl.ITaskCallback
 * JD-Core Version:    0.6.2
 */