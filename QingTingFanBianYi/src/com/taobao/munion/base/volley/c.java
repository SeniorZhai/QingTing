package com.taobao.munion.base.volley;

import android.os.Process;
import com.taobao.munion.base.Log;
import java.util.concurrent.BlockingQueue;

public class c extends Thread
{
  private static final boolean a = Log.DEBUG;
  private final BlockingQueue<l<?>> b;
  private final BlockingQueue<l<?>> c;
  private final b d;
  private final o e;
  private volatile boolean f = false;

  public c(BlockingQueue<l<?>> paramBlockingQueue1, BlockingQueue<l<?>> paramBlockingQueue2, b paramb, o paramo)
  {
    this.b = paramBlockingQueue1;
    this.c = paramBlockingQueue2;
    this.d = paramb;
    this.e = paramo;
  }

  public void a()
  {
    this.f = true;
    interrupt();
  }

  public void run()
  {
    if (a)
      Log.v("start new dispatcher", new Object[0]);
    Process.setThreadPriority(10);
    this.d.a();
    while (true)
    {
      try
      {
        l locall = (l)this.b.take();
        locall.a("cache-queue-take");
        if (!locall.j())
          break label73;
        locall.b("cache-discard-canceled");
        continue;
      }
      catch (InterruptedException localInterruptedException)
      {
      }
      if (this.f)
      {
        return;
        label73: b.a locala = this.d.a(localInterruptedException.g());
        if (locala == null)
        {
          localInterruptedException.a("cache-miss");
          this.c.put(localInterruptedException);
        }
        else if (locala.a())
        {
          localInterruptedException.a("cache-hit-expired");
          localInterruptedException.a(locala);
          this.c.put(localInterruptedException);
        }
        else
        {
          localInterruptedException.a("cache-hit");
          n localn = localInterruptedException.a(new i(locala.a, locala.f));
          localInterruptedException.a("cache-hit-parsed");
          if (!locala.b())
          {
            this.e.a(localInterruptedException, localn);
          }
          else
          {
            localInterruptedException.a("cache-hit-refresh-needed");
            localInterruptedException.a(locala);
            localn.d = true;
            this.e.a(localInterruptedException, localn, new Runnable()
            {
              public void run()
              {
                try
                {
                  c.a(c.this).put(localInterruptedException);
                  return;
                }
                catch (InterruptedException localInterruptedException)
                {
                }
              }
            });
          }
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.c
 * JD-Core Version:    0.6.2
 */