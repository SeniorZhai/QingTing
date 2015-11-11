package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback.Stub;

class PayHelper$2 extends IRemoteServiceCallback.Stub
{
  PayHelper$2(PayHelper paramPayHelper)
  {
  }

  public boolean isHideLoadingScreen()
    throws RemoteException
  {
    return false;
  }

  public void payEnd(boolean paramBoolean, String paramString)
    throws RemoteException
  {
  }

  public void startActivity(String paramString1, String paramString2, int paramInt, Bundle paramBundle)
    throws RemoteException
  {
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    Bundle localBundle = paramBundle;
    if (paramBundle == null)
      localBundle = new Bundle();
    try
    {
      localBundle.putInt("CallingPid", paramInt);
      localIntent.putExtras(localBundle);
      localIntent.setClassName(paramString1, paramString2);
      PayHelper.b(this.a).startActivity(localIntent);
      return;
    }
    catch (Exception paramBundle)
    {
      while (true)
        paramBundle.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.PayHelper.2
 * JD-Core Version:    0.6.2
 */