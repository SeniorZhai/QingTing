package com.neusoft.ssp.aidl;

import android.os.IInterface;

public abstract interface MyAIDL extends IInterface
{
  public abstract void register(String paramString, ITaskCallback paramITaskCallback);

  public abstract void send(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.aidl.MyAIDL
 * JD-Core Version:    0.6.2
 */