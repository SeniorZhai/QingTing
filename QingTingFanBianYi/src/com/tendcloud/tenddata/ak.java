package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Message;
import java.util.Map;

class ak
  implements Runnable
{
  ak(j paramj, String paramString1, String paramString2, Map paramMap)
  {
  }

  public void run()
  {
    try
    {
      if (!j.u())
        return;
      j.a locala = new j.a();
      locala.a = this.a;
      locala.b = this.b;
      locala.g = this.c;
      Handler localHandler = d.a();
      localHandler.sendMessage(Message.obtain(localHandler, 4, locala));
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.ak
 * JD-Core Version:    0.6.2
 */