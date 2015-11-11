package com.taobao.newxp.controller;

import android.os.Handler;
import android.os.Message;
import com.taobao.newxp.common.ExchangeConstants;

public class f extends Thread
{
  public d.a a;
  final Handler b = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      f.this.a.timeup();
    }
  };

  public f(d.a parama)
  {
    this.a = parama;
  }

  public void run()
  {
    try
    {
      if (ExchangeConstants.DEBUG_MODE)
        ExchangeConstants.REFRESH_INTERVAL = 3000;
      Thread.sleep(ExchangeConstants.REFRESH_INTERVAL);
      this.b.sendEmptyMessage(0);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.controller.f
 * JD-Core Version:    0.6.2
 */