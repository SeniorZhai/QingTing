package com.mediav.ads.sdk.adcore;

import android.os.Message;
import java.util.HashMap;

class ResultRunable
  implements Runnable
{
  private static final String UNKOWN_ERROR = "unkown error";
  private Message msg = null;

  public ResultRunable(Message paramMessage)
  {
    this.msg = paramMessage;
  }

  public void run()
  {
    HttpRequester.Listener localListener;
    try
    {
      HashMap localHashMap = (HashMap)this.msg.obj;
      localListener = (HttpRequester.Listener)localHashMap.get("callback");
      if (this.msg.what == 0)
      {
        localListener.onGetDataSucceed((byte[])localHashMap.get("data"));
        return;
      }
      if (this.msg.what == 1)
      {
        localListener.onGetDataFailed((String)localHashMap.get("error"));
        return;
      }
    }
    catch (Exception localException)
    {
      ((HttpRequester.Listener)((HashMap)this.msg.obj).get("callback")).onGetDataFailed("HttpRequester get data error:" + localException.getMessage());
      return;
    }
    if (this.msg.what == 2)
    {
      localListener.onGetDataFailed((String)localException.get("error"));
      return;
    }
    localListener.onGetDataFailed("unkown error");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediav.ads.sdk.adcore.ResultRunable
 * JD-Core Version:    0.6.2
 */