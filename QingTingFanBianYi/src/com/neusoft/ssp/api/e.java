package com.neusoft.ssp.api;

import java.util.TimerTask;

final class e extends TimerTask
{
  e(SSP_NEW_QT_FM_API paramSSP_NEW_QT_FM_API)
  {
  }

  public final void run()
  {
    this.a.LogChuxl("task。。。");
    if (!SSP_NEW_QT_FM_API.a())
    {
      this.a.replyHeartBeat();
      this.a.LogChuxl("replyHeartBeat");
      SSP_NEW_QT_FM_API.a(false);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.api.e
 * JD-Core Version:    0.6.2
 */