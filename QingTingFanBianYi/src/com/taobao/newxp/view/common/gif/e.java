package com.taobao.newxp.view.common.gif;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class e extends Drawable
  implements b, j
{
  private static final int r = 6;
  private d e = null;
  private h f = h.b;
  private int g = -1;
  private int h = 0;
  private c i = null;
  private boolean j = false;
  private boolean k = false;
  private boolean l = false;
  private Bitmap m = null;
  private i n = null;
  private int o = 0;
  private int p = 0;
  private Handler q = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      try
      {
        e.a(e.this);
        return;
      }
      catch (Exception paramAnonymousMessage)
      {
        Log.e("GifView", paramAnonymousMessage.toString());
      }
    }
  };
  private a s;
  private Bitmap t;
  private int u;
  private final Rect v = new Rect();
  private boolean w;
  private boolean x;
  private int y;
  private int z;

  public e()
  {
    this.s = new a((Bitmap)null);
    this.s.g = this.u;
    this.i = new c();
    this.i.a(this);
  }

  private e(a parama, Resources paramResources)
  {
    this.s = parama;
    if (paramResources != null);
    for (this.u = paramResources.getDisplayMetrics().densityDpi; ; this.u = parama.g)
    {
      paramResources = localObject;
      if (parama != null)
        paramResources = parama.a;
      a(paramResources);
      return;
    }
  }

  private void a(Bitmap paramBitmap)
  {
    if (paramBitmap != this.t)
    {
      this.t = paramBitmap;
      if (paramBitmap == null)
        break label49;
      if (this.u == 0)
        this.u = this.t.getDensity();
      q();
    }
    while (true)
    {
      this.w = true;
      invalidateSelf();
      return;
      label49: this.z = -1;
      this.y = -1;
    }
  }

  private void j()
  {
    l();
    if (this.m != null)
      this.m = null;
    if (this.e != null)
    {
      k();
      this.e.destroy();
      this.e = null;
    }
    this.h = 0;
    this.e = new d(this);
    if (this.k)
      this.e.a();
  }

  private void k()
  {
    if ((this.e != null) && (this.e.getState() != Thread.State.TERMINATED))
      this.e.interrupt();
  }

  private void l()
  {
    if (this.l)
      return;
    this.i.c();
    this.j = false;
  }

  private void m()
  {
    if (this.l)
      return;
    l();
    this.h = 0;
    this.i.d();
  }

  private int n()
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
    {
      this.m = localf.a;
      this.u = new a(this.m).g;
      this.s.g = this.u;
    }
    return localf.b;
  }

  private void o()
  {
    if ((this.m == null) || ((this.m != null) && (!this.m.isRecycled())))
    {
      a(this.m);
      if ((this.n != null) && ((this.o == 2) || (this.o == 3)))
      {
        this.p += 1;
        this.n.b(this.p);
      }
      invalidateSelf();
    }
  }

  private void p()
  {
    if (this.q != null)
    {
      Message localMessage = this.q.obtainMessage();
      this.q.sendMessage(localMessage);
    }
  }

  private void q()
  {
    this.y = this.t.getScaledWidth(this.u);
    this.z = this.t.getScaledHeight(this.u);
  }

  public void a()
  {
    l();
    k();
    this.i.e();
    this.e.destroy();
    this.e = null;
    this.i = null;
  }

  public void a(int paramInt)
  {
    if (paramInt > 1)
    {
      this.g = paramInt;
      b();
    }
  }

  public void a(Resources paramResources, int paramInt)
  {
    j();
    this.e.a(paramResources, paramInt);
    this.e.start();
  }

  public void a(Canvas paramCanvas)
  {
    b(paramCanvas.getDensity());
  }

  public void a(Shader.TileMode paramTileMode)
  {
    a(paramTileMode, this.s.f);
  }

  public void a(Shader.TileMode paramTileMode1, Shader.TileMode paramTileMode2)
  {
    a locala = this.s;
    if ((locala.e != paramTileMode1) || (locala.f != paramTileMode2))
    {
      locala.e = paramTileMode1;
      locala.f = paramTileMode2;
      locala.h = true;
      invalidateSelf();
    }
  }

  public void a(DisplayMetrics paramDisplayMetrics)
  {
    b(paramDisplayMetrics.densityDpi);
  }

  public void a(i parami, int paramInt)
  {
    this.n = parami;
    if ((paramInt >= 1) && (paramInt <= 3))
      this.o = paramInt;
  }

  public void a(String paramString)
  {
    j();
    this.e.a(paramString);
    this.e.start();
  }

  public void a(boolean paramBoolean)
  {
    this.s.d.setAntiAlias(paramBoolean);
    invalidateSelf();
  }

  public void a(byte[] paramArrayOfByte)
  {
    j();
    this.e.a(paramArrayOfByte);
    this.e.start();
  }

  public void b()
  {
    this.k = true;
    if (this.e != null)
      this.e.a();
  }

  public void b(int paramInt)
  {
    if (this.u != paramInt)
    {
      int i1 = paramInt;
      if (paramInt == 0)
        i1 = 160;
      this.u = i1;
      if (this.t != null)
        q();
      invalidateSelf();
    }
  }

  public final void b(Shader.TileMode paramTileMode)
  {
    a(this.s.e, paramTileMode);
  }

  public void c()
  {
    if (this.l);
    while (!this.j)
      return;
    this.i.b();
  }

  public void c(int paramInt)
  {
    if (this.s.c != paramInt)
    {
      this.s.c = paramInt;
      this.w = true;
      invalidateSelf();
    }
  }

  public void d()
  {
    if (this.l)
      return;
    this.i.a();
  }

  public void draw(Canvas paramCanvas)
  {
    Bitmap localBitmap = this.t;
    a locala;
    Object localObject2;
    Shader.TileMode localTileMode;
    if (localBitmap != null)
    {
      locala = this.s;
      if (locala.h)
      {
        localObject2 = locala.e;
        localTileMode = locala.f;
        if ((localObject2 != null) || (localTileMode != null))
          break label135;
        locala.d.setShader(null);
      }
    }
    while (true)
    {
      locala.h = false;
      copyBounds(this.v);
      if (locala.d.getShader() != null)
        break;
      if (this.w)
      {
        Gravity.apply(locala.c, this.y, this.z, getBounds(), this.v);
        this.w = false;
      }
      paramCanvas.drawBitmap(localBitmap, null, this.v, locala.d);
      return;
      label135: Paint localPaint = locala.d;
      Object localObject1 = localObject2;
      if (localObject2 == null)
        localObject1 = Shader.TileMode.CLAMP;
      localObject2 = localTileMode;
      if (localTileMode == null)
        localObject2 = Shader.TileMode.CLAMP;
      localPaint.setShader(new BitmapShader(localBitmap, (Shader.TileMode)localObject1, (Shader.TileMode)localObject2));
    }
    if (this.w)
    {
      copyBounds(this.v);
      this.w = false;
    }
    paramCanvas.drawRect(this.v, locala.d);
  }

  public final Paint e()
  {
    return this.s.d;
  }

  public final Bitmap f()
  {
    return this.t;
  }

  public int g()
  {
    return this.s.c;
  }

  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.s.b;
  }

  public final Drawable.ConstantState getConstantState()
  {
    this.s.b = getChangingConfigurations();
    return this.s;
  }

  public int getIntrinsicHeight()
  {
    return this.z;
  }

  public int getIntrinsicWidth()
  {
    return this.y;
  }

  public int getOpacity()
  {
    if (this.s.c != 119);
    Bitmap localBitmap;
    do
    {
      return -3;
      localBitmap = this.t;
    }
    while ((localBitmap == null) || (localBitmap.hasAlpha()) || (this.s.d.getAlpha() < 255));
    return -1;
  }

  public Shader.TileMode h()
  {
    return this.s.e;
  }

  public Shader.TileMode i()
  {
    return this.s.f;
  }

  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
    throws XmlPullParserException, IOException
  {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
  }

  public void loopEnd()
  {
    this.h += 1;
    if ((this.g > 0) && (this.h >= this.g))
    {
      l();
      k();
    }
    if (this.n != null)
    {
      if ((this.o == 1) || (this.o == 3))
        this.n.a(this.h);
      this.p = 0;
    }
  }

  public Drawable mutate()
  {
    if ((!this.x) && (super.mutate() == this))
    {
      this.s = new a(this.s);
      this.x = true;
    }
    return this;
  }

  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
    this.w = true;
  }

  public void parseReturn(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    case 3:
      do
      {
        do
        {
          do
          {
            return;
            Log.d("parseReturn", "FIRST");
          }
          while ((this.f != h.c) && (this.f != h.b));
          this.m = this.e.g();
          p();
          return;
          Log.d("parseReturn", "FINISH");
          if (this.e.d() == 1)
          {
            this.m = this.e.g();
            p();
            l();
            k();
            this.l = true;
            return;
          }
        }
        while (this.j);
        m();
        this.j = true;
        return;
        Log.d("parseReturn", "CACHE_FINISH");
      }
      while (this.j);
      m();
      this.j = true;
      return;
    case 4:
    }
    Log.d("parseReturn", "ERROR");
  }

  public int reDraw()
  {
    int i1 = n();
    o();
    return i1;
  }

  public void setAlpha(int paramInt)
  {
    if (paramInt != this.s.d.getAlpha())
    {
      this.s.d.setAlpha(paramInt);
      invalidateSelf();
    }
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.s.d.setColorFilter(paramColorFilter);
    invalidateSelf();
  }

  public void setDither(boolean paramBoolean)
  {
    this.s.d.setDither(paramBoolean);
    invalidateSelf();
  }

  public void setFilterBitmap(boolean paramBoolean)
  {
    this.s.d.setFilterBitmap(paramBoolean);
    invalidateSelf();
  }

  static final class a extends Drawable.ConstantState
  {
    Bitmap a;
    int b;
    int c = 119;
    Paint d = new Paint(6);
    Shader.TileMode e = null;
    Shader.TileMode f = null;
    int g = 160;
    boolean h;

    a(Bitmap paramBitmap)
    {
      this.a = paramBitmap;
    }

    a(a parama)
    {
      this(parama.a);
      this.b = parama.b;
      this.c = parama.c;
      this.e = parama.e;
      this.f = parama.f;
      this.g = parama.g;
      this.d = new Paint(parama.d);
      this.h = parama.h;
    }

    public int getChangingConfigurations()
    {
      return this.b;
    }

    public Drawable newDrawable()
    {
      return new e(this, null, null);
    }

    public Drawable newDrawable(Resources paramResources)
    {
      return new e(this, paramResources, null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.common.gif.e
 * JD-Core Version:    0.6.2
 */