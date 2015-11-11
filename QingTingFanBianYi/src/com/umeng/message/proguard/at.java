package com.umeng.message.proguard;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpException;

abstract class at
  implements aB
{
  protected static final int g = 8192;
  private static final char l = '\r';
  private static final char m = '\n';
  private static final String n = "UTF-8";
  private static final String o = "HttpChunked";
  private static final char[] x = { ' ' };
  protected volatile ay a = ay.d;
  protected volatile InputStream b = null;
  protected volatile int c = -1;
  protected volatile boolean d = true;
  protected volatile String e;
  protected volatile Map<String, String> f = new HashMap();
  private volatile ThreadPoolExecutor p = (ThreadPoolExecutor)Executors.newFixedThreadPool(5);
  private volatile aA q;
  private volatile Future<?> r = null;
  private volatile Future<?> s = null;
  private volatile String t;
  private volatile int u;
  private volatile int v = -1;
  private AtomicBoolean w = new AtomicBoolean(false);

  private void a(Context paramContext)
  {
    try
    {
      paramContext = new ag(paramContext);
      if (paramContext.a())
      {
        this.t = paramContext.d();
        this.u = paramContext.e();
        return;
      }
      this.t = null;
      this.u = -1;
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  public static char byteToChar(byte[] paramArrayOfByte)
  {
    return (char)((paramArrayOfByte[0] & 0xFF) << 8 | paramArrayOfByte[1] & 0xFF);
  }

  private void l()
  {
    this.v = -1;
  }

  private boolean m()
  {
    return (this.a == ay.c) || (this.a == ay.d);
  }

  protected void a()
  {
    callError(false);
    this.v = new Random().nextInt(10000);
  }

  protected final void a(int paramInt, Throwable paramThrowable)
  {
    disconnect(b());
    if (this.q != null)
      this.q.a(paramInt, new HashMap(), paramThrowable);
  }

  protected final void a(int paramInt, Map<String, String> paramMap)
  {
    disconnect(b());
    if (this.q != null)
      this.q.a(paramInt, paramMap, new HttpException("http chunked connectId:[" + b() + "] http Status code==" + paramInt));
  }

  protected abstract void a(String paramString);

  protected final void a(Map<String, String> paramMap)
  {
    if (this.q != null)
    {
      this.a = ay.b;
      this.q.a(b(), paramMap);
    }
  }

  protected final void a(char[] paramArrayOfChar)
  {
    if (this.q != null)
      this.q.a(paramArrayOfChar);
  }

  public void addHeader(String paramString1, String paramString2)
  {
    this.f.put(paramString1, paramString2);
  }

  protected int b()
  {
    return this.v;
  }

  protected final void b(String paramString)
  {
    this.q.a(paramString);
  }

  protected abstract void c();

  public void callError(boolean paramBoolean)
  {
    this.w.set(paramBoolean);
  }

  public final void close()
  {
    try
    {
      this.p.submit(new ax(this));
      if ((this.p != null) && (this.p.isShutdown()))
        this.p.shutdownNow();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public final void connect(Context paramContext, String paramString, long paramLong, aA paramaA)
  {
    if (paramaA == null)
    {
      Q.c("HttpChunked", "eventHandler == null ");
      return;
    }
    this.e = paramString;
    if ((this.a == ay.b) || (this.a == ay.a))
    {
      Q.c("HttpChunked", "http chunked connect url: [" + paramString + "] connectId:[" + b() + "] connecting......");
      return;
    }
    a(paramContext);
    this.q = paramaA;
    this.a = ay.a;
    this.r = this.p.submit(new au(this, paramString));
    this.s = this.p.submit(new av(this, paramLong, paramString));
  }

  protected abstract void d();

  public final void disconnect(int paramInt)
  {
    Q.c("HttpChunked", "http chunked disconnect(" + paramInt + ")");
    if (b() != paramInt)
    {
      Q.c("HttpChunked", "http chunked connect cId[" + paramInt + "] != mCId[" + b() + "]");
      this.a = ay.d;
      return;
    }
    if (m())
    {
      Q.c("HttpChunked", "http chunked connect[" + paramInt + "] connection has been closed");
      return;
    }
    this.a = ay.c;
    this.p.submit(new aw(this, paramInt));
    this.a = ay.d;
  }

  protected final void e()
  {
    InputStreamReader localInputStreamReader;
    StringBuffer localStringBuffer;
    int i1;
    int j;
    try
    {
      char[] arrayOfChar = new char[6];
      localInputStreamReader = new InputStreamReader(this.b, "UTF-8");
      localStringBuffer = new StringBuffer(8192);
      i1 = 0;
      while (true)
      {
        k = localInputStreamReader.read();
        if (k == -1)
          break;
        j = k;
        if (k != 32)
          break label410;
        a(x);
      }
    }
    catch (Throwable localThrowable)
    {
      if (!this.w.get())
      {
        this.w.set(true);
        Q.d("HttpChunked", "Throwable connectId:[" + b() + "]==>", localThrowable);
        a(504, localThrowable);
      }
    }
    return;
    label130: int i2;
    while (true)
    {
      j = i1;
      i2 = k;
      if (k != 64)
        break;
      int i = (char)k;
      localThrowable[0] = i;
      j = k;
      k = 0;
      label160: if (k >= 6)
        break label410;
      j = localInputStreamReader.read();
      localThrowable[(k + 1)] = ((char)j);
      if (k < 5)
        break label417;
      a(localThrowable);
      k = j;
    }
    label200: int k = j;
    if (i2 != 10)
    {
      k = j;
      if (j == 0)
        break label426;
      localStringBuffer.append('\r');
      k = 0;
    }
    while (true)
    {
      label234: i1 = localInputStreamReader.read();
      j = k;
      i2 = i1;
      if (i1 >= 0)
        break label200;
      i1 = k;
      if (TextUtils.isEmpty(localStringBuffer.toString()))
        break;
      b(localStringBuffer.toString());
      localStringBuffer.setLength(0);
      i1 = k;
      break;
      label410: label417: label426: 
      do
      {
        localStringBuffer.append((char)i2);
        break label234;
        if (this.a != ay.b)
          break;
        Q.c("HttpChunked", "connectId:[" + b() + "]==>server data is abort");
        if (!hasCallError())
        {
          callError(true);
          a(504, new IOException("connectId:[" + b() + "] server data is abort"));
        }
        disconnect(b());
        return;
        k = j;
        break label130;
        k += 1;
        break label160;
      }
      while (i2 != 13);
      k = 1;
    }
  }

  protected final void f()
  {
    if (this.s != null)
      this.s.cancel(true);
  }

  protected final void g()
  {
    if (this.r != null)
      this.r.cancel(true);
  }

  protected final void h()
  {
    if ((this.q == null) && (m()));
    while (this.a != ay.b)
      return;
    this.q.a();
  }

  public boolean hasCallError()
  {
    return this.w.get();
  }

  protected final String i()
  {
    return this.t;
  }

  protected final int j()
  {
    return this.u;
  }

  protected final boolean k()
  {
    return (this.t != null) && (this.u != -1);
  }

  public final ay readyState()
  {
    return this.a;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.at
 * JD-Core Version:    0.6.2
 */