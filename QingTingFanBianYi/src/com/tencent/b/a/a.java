package com.tencent.b.a;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class a extends f
  implements Handler.Callback
{
  private b a;
  private FileWriter b;
  private File c;
  private char[] d;
  private volatile d e;
  private volatile d f;
  private volatile d g;
  private volatile d h;
  private volatile boolean i = false;
  private HandlerThread j;
  private Handler k;

  public a(int paramInt, boolean paramBoolean, e parame, b paramb)
  {
    super(paramInt, paramBoolean, parame);
    a(paramb);
    this.e = new d();
    this.f = new d();
    this.g = this.e;
    this.h = this.f;
    this.d = new char[paramb.f()];
    paramb.b();
    h();
    this.j = new HandlerThread(paramb.c(), paramb.i());
    if (this.j != null)
      this.j.start();
    if ((this.j.isAlive()) && (this.j.getLooper() != null))
      this.k = new Handler(this.j.getLooper(), this);
    f();
  }

  public a(b paramb)
  {
    this(63, true, e.a, paramb);
  }

  private void f()
  {
    if (this.k != null)
      this.k.sendEmptyMessageDelayed(1024, c().g());
  }

  // ERROR //
  private void g()
  {
    // Byte code:
    //   0: invokestatic 118	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   3: aload_0
    //   4: getfield 72	com/tencent/b/a/a:j	Landroid/os/HandlerThread;
    //   7: if_acmpeq +4 -> 11
    //   10: return
    //   11: aload_0
    //   12: getfield 32	com/tencent/b/a/a:i	Z
    //   15: ifne -5 -> 10
    //   18: aload_0
    //   19: iconst_1
    //   20: putfield 32	com/tencent/b/a/a:i	Z
    //   23: aload_0
    //   24: invokespecial 120	com/tencent/b/a/a:j	()V
    //   27: aload_0
    //   28: getfield 48	com/tencent/b/a/a:h	Lcom/tencent/b/a/d;
    //   31: aload_0
    //   32: invokespecial 60	com/tencent/b/a/a:h	()Ljava/io/Writer;
    //   35: aload_0
    //   36: getfield 55	com/tencent/b/a/a:d	[C
    //   39: invokevirtual 123	com/tencent/b/a/d:a	(Ljava/io/Writer;[C)V
    //   42: aload_0
    //   43: getfield 48	com/tencent/b/a/a:h	Lcom/tencent/b/a/d;
    //   46: invokevirtual 124	com/tencent/b/a/d:b	()V
    //   49: aload_0
    //   50: iconst_0
    //   51: putfield 32	com/tencent/b/a/a:i	Z
    //   54: return
    //   55: astore_1
    //   56: aload_0
    //   57: getfield 48	com/tencent/b/a/a:h	Lcom/tencent/b/a/d;
    //   60: invokevirtual 124	com/tencent/b/a/d:b	()V
    //   63: goto -14 -> 49
    //   66: astore_1
    //   67: aload_0
    //   68: getfield 48	com/tencent/b/a/a:h	Lcom/tencent/b/a/d;
    //   71: invokevirtual 124	com/tencent/b/a/d:b	()V
    //   74: aload_1
    //   75: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   27	42	55	java/io/IOException
    //   27	42	66	finally
  }

  private Writer h()
  {
    File localFile = c().a();
    if ((localFile != null) && (!localFile.equals(this.c)))
    {
      this.c = localFile;
      i();
    }
    try
    {
      this.b = new FileWriter(this.c, true);
      return this.b;
    }
    catch (IOException localIOException)
    {
    }
    return null;
  }

  private void i()
  {
    try
    {
      if (this.b != null)
      {
        this.b.flush();
        this.b.close();
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  private void j()
  {
    try
    {
      if (this.g == this.e)
        this.g = this.f;
      for (this.h = this.e; ; this.h = this.f)
      {
        return;
        this.g = this.e;
      }
    }
    finally
    {
    }
  }

  public void a()
  {
    if (this.k.hasMessages(1024))
      this.k.removeMessages(1024);
  }

  protected void a(int paramInt, Thread paramThread, long paramLong, String paramString1, String paramString2, Throwable paramThrowable)
  {
    a(e().a(paramInt, paramThread, paramLong, paramString1, paramString2, paramThrowable));
  }

  public void a(b paramb)
  {
    this.a = paramb;
  }

  protected void a(String paramString)
  {
    this.g.a(paramString);
    if (this.g.a() >= c().f())
      a();
  }

  public void b()
  {
    i();
    this.j.quit();
  }

  public b c()
  {
    return this.a;
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1024:
    }
    while (true)
    {
      return true;
      g();
      f();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.b.a.a
 * JD-Core Version:    0.6.2
 */