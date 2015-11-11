package com.taobao.munion.base.volley.a;

import android.os.Handler;
import android.os.Looper;
import com.taobao.munion.base.volley.b;
import com.taobao.munion.base.volley.i;
import com.taobao.munion.base.volley.l;
import com.taobao.munion.base.volley.l.b;
import com.taobao.munion.base.volley.n;

public class c extends l<Object>
{
  private final b a;
  private final Runnable b;

  public c(b paramb, Runnable paramRunnable)
  {
    super(0, null, null);
    this.a = paramb;
    this.b = paramRunnable;
  }

  protected n<Object> a(i parami)
  {
    return null;
  }

  protected void c(Object paramObject)
  {
  }

  public boolean j()
  {
    this.a.b();
    if (this.b != null)
      new Handler(Looper.getMainLooper()).postAtFrontOfQueue(this.b);
    return true;
  }

  public l.b u()
  {
    return l.b.d;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.c
 * JD-Core Version:    0.6.2
 */