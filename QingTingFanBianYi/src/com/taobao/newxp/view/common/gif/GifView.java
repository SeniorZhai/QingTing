package com.taobao.newxp.view.common.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GifView extends ImageView
  implements b, j
{
  private d e = null;
  private Bitmap f = null;
  private c g = null;
  private boolean h = false;
  private int i = -1;
  private boolean j = false;
  private int k = 0;
  private int l = 0;
  private i m = null;
  private boolean n = false;
  private int o = 0;
  private h p = h.b;
  private Handler q = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      try
      {
        GifView.a(GifView.this);
        return;
      }
      catch (Exception paramAnonymousMessage)
      {
        Log.e("GifView", paramAnonymousMessage.toString());
      }
    }
  };

  public GifView(Context paramContext)
  {
    super(paramContext);
    setScaleType(ImageView.ScaleType.FIT_XY);
    this.g = new c();
    this.g.a(this);
  }

  public GifView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public GifView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    setScaleType(ImageView.ScaleType.FIT_XY);
    this.g = new c();
    this.g.a(this);
  }

  private void a()
  {
    b();
    if (this.f != null)
      this.f = null;
    if (this.e != null)
    {
      c();
      this.e.destroy();
      this.e = null;
    }
    this.k = 0;
    this.e = new d(this);
    if (this.j)
      this.e.a();
  }

  private void a(Resources paramResources, int paramInt)
  {
    a();
    this.e.a(paramResources, paramInt);
    this.e.start();
  }

  private void a(byte[] paramArrayOfByte)
  {
    a();
    this.e.a(paramArrayOfByte);
    this.e.start();
  }

  private void b()
  {
    if (this.n)
      return;
    if (this.g != null)
      this.g.c();
    this.h = false;
  }

  private void c()
  {
    if ((this.e != null) && (this.e.getState() != Thread.State.TERMINATED))
    {
      this.e.interrupt();
      this.e.destroy();
    }
  }

  private void d()
  {
    if (this.n)
      return;
    b();
    this.k = 0;
    this.g.d();
  }

  private int e()
  {
    f localf;
    if (this.e != null)
    {
      localf = this.e.i();
      if (localf != null);
    }
    else
    {
      return -1;
    }
    if (localf.a != null)
      this.f = localf.a;
    return localf.b;
  }

  private void f()
  {
    if (this.q != null)
    {
      Message localMessage = this.q.obtainMessage();
      this.q.sendMessage(localMessage);
    }
  }

  private void g()
  {
    if ((this.f == null) || ((this.f != null) && (!this.f.isRecycled())))
    {
      setImageBitmap(this.f);
      invalidate();
      if ((this.m != null) && ((this.o == 2) || (this.o == 3)))
      {
        this.l += 1;
        this.m.b(this.l);
      }
    }
  }

  public void destroy()
  {
    b();
    c();
    if (this.g != null)
      this.g.e();
    if (this.e != null)
      this.e.destroy();
    this.e = null;
    this.g = null;
  }

  public void dispatchWindowVisibilityChanged(int paramInt)
  {
    if ((paramInt == 8) || (paramInt == 4))
      pauseGifAnimation();
    while (true)
    {
      super.dispatchWindowVisibilityChanged(paramInt);
      return;
      if (paramInt == 0)
        restartGifAnimation();
    }
  }

  public void loopEnd()
  {
    this.k += 1;
    if ((this.i > 0) && (this.k >= this.i))
    {
      b();
      c();
    }
    if (this.m != null)
    {
      if ((this.o == 1) || (this.o == 3))
        this.m.a(this.k);
      this.l = 0;
    }
  }

  protected void onWindowVisibilityChanged(int paramInt)
  {
  }

  public void parseReturn(int paramInt)
  {
    if ((getVisibility() == 8) || (getVisibility() == 4));
    do
    {
      do
      {
        do
        {
          return;
          switch (paramInt)
          {
          default:
            return;
          case 1:
            Log.d("parseReturn", "FIRST");
          case 2:
          case 3:
          case 4:
          }
        }
        while ((this.p != h.c) && (this.p != h.b));
        this.f = this.e.g();
        f();
        return;
        Log.d("parseReturn", "FINISH");
        if (this.e.d() == 1)
        {
          e();
          f();
          b();
          c();
          this.n = true;
          return;
        }
      }
      while (this.h);
      d();
      this.h = true;
      return;
      Log.d("parseReturn", "CACHE_FINISH");
    }
    while (this.h);
    d();
    this.h = true;
    return;
    Log.e("parseReturn", "ERROR");
  }

  public void pauseGifAnimation()
  {
    if (this.n)
      return;
    this.g.a();
  }

  public int reDraw()
  {
    int i1 = e();
    g();
    return i1;
  }

  public void restartGifAnimation()
  {
    if (this.n);
    while (!this.h)
      return;
    this.g.b();
  }

  public void setGifImage(int paramInt)
  {
    a(getResources(), paramInt);
  }

  public void setGifImage(String paramString)
  {
    a();
    this.e.a(paramString);
    this.e.start();
  }

  public void setGifImage(byte[] paramArrayOfByte)
  {
    a(paramArrayOfByte);
  }

  public void setGifImageType(h paramh)
  {
    if (this.e == null)
      this.p = paramh;
  }

  public void setListener(i parami, int paramInt)
  {
    this.m = parami;
    if ((paramInt >= 1) && (paramInt <= 3))
      this.o = paramInt;
  }

  public void setLoopAnimation()
  {
    this.j = true;
    if (this.e != null)
      this.e.a();
  }

  public void setLoopNumber(int paramInt)
  {
    if (paramInt > 1)
    {
      this.i = paramInt;
      setLoopAnimation();
    }
  }

  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    if ((paramInt == 8) || (paramInt == 4))
      b();
    while (paramInt != 0)
      return;
    d();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.common.gif.GifView
 * JD-Core Version:    0.6.2
 */