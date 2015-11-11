package com.umeng.message.proguard;

import android.os.SystemClock;
import org.apache.http.HttpException;

class av
  implements Runnable
{
  av(at paramat, long paramLong, String paramString)
  {
  }

  public void run()
  {
    try
    {
      SystemClock.sleep(this.a);
      label7: if ((this.c.a == ay.a) && (!this.c.hasCallError()))
      {
        this.c.callError(true);
        Q.e("HttpChunked", "http chunked connect url: [" + this.b + "] connectId:[" + this.c.b() + "] http Status code==" + 408);
        this.c.a(408, new HttpException("connectId:[" + this.c.b() + "] http Status code==" + 408));
        at.a(this.c);
        this.c.g();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      break label7;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.av
 * JD-Core Version:    0.6.2
 */