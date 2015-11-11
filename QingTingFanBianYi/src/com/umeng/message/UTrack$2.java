package com.umeng.message;

import com.umeng.common.message.Log;
import java.util.ArrayList;

class UTrack$2
  implements Runnable
{
  UTrack$2(UTrack paramUTrack)
  {
  }

  public void run()
  {
    try
    {
      for (Object localObject1 = MsgLogStore.getInstance(UTrack.a(this.a)).getMsgLogs(1); ((ArrayList)localObject1).size() > 0; localObject1 = MsgLogStore.getInstance(UTrack.a(this.a)).getMsgLogs(1))
      {
        localObject1 = (MsgLogStore.MsgLog)((ArrayList)localObject1).get(0);
        UTrack.a(this.a, ((MsgLogStore.MsgLog)localObject1).msgId, ((MsgLogStore.MsgLog)localObject1).actionType, ((MsgLogStore.MsgLog)localObject1).time);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      return;
    }
    finally
    {
      Log.c(UTrack.a(), "sendCachedMsgLog finished, clear cacheLogSending flag");
      UTrack.a(false);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UTrack.2
 * JD-Core Version:    0.6.2
 */