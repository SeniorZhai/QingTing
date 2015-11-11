package com.tencent.connect.avatar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class c extends ImageView
{
  final String a = "TouchView";
  public boolean b = false;
  private Matrix c = new Matrix();
  private Matrix d = new Matrix();
  private int e = 0;
  private float f = 1.0F;
  private float g = 1.0F;
  private Bitmap h;
  private boolean i = false;
  private float j;
  private float k;
  private PointF l = new PointF();
  private PointF m = new PointF();
  private float n = 1.0F;
  private float o = 0.0F;
  private Rect p = new Rect();

  public c(Context paramContext)
  {
    super(paramContext);
    getDrawingRect(this.p);
    a();
  }

  private float a(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getPointerCount() < 2)
      return 0.0F;
    float f1 = paramMotionEvent.getX(0) - paramMotionEvent.getX(1);
    float f2 = paramMotionEvent.getY(0) - paramMotionEvent.getY(1);
    return FloatMath.sqrt(f1 * f1 + f2 * f2);
  }

  private void a()
  {
  }

  private void a(PointF paramPointF)
  {
    float f3 = 1.0F;
    if (this.h == null)
      return;
    float[] arrayOfFloat = new float[9];
    this.c.getValues(arrayOfFloat);
    float f4 = arrayOfFloat[2];
    float f5 = arrayOfFloat[5];
    float f6 = arrayOfFloat[0];
    float f8 = this.h.getWidth();
    float f7 = this.h.getHeight();
    float f2 = this.p.left - f4;
    float f1 = f2;
    if (f2 <= 1.0F)
      f1 = 1.0F;
    f4 = f4 + f8 * f6 - this.p.right;
    f2 = f4;
    if (f4 <= 1.0F)
      f2 = 1.0F;
    f8 = f1 * this.p.width() / (f2 + f1);
    float f9 = this.p.left;
    f2 = this.p.top - f5;
    f4 = f5 + f7 * f6 - this.p.bottom;
    f1 = f2;
    if (f2 <= 1.0F)
      f1 = 1.0F;
    if (f4 <= 1.0F);
    for (f2 = f3; ; f2 = f4)
    {
      paramPointF.set(f8 + f9, this.p.height() * f1 / (f1 + f2) + this.p.top);
      return;
    }
  }

  private void b()
  {
    if (this.h == null);
    while (true)
    {
      return;
      float f6 = this.p.width();
      float f3 = this.p.height();
      float[] arrayOfFloat = new float[9];
      this.c.getValues(arrayOfFloat);
      float f1 = arrayOfFloat[2];
      float f2 = arrayOfFloat[5];
      float f4 = arrayOfFloat[0];
      Object localObject = null;
      if (f4 > this.f)
      {
        this.o = (this.f / f4);
        this.c.postScale(this.o, this.o, this.m.x, this.m.y);
        setImageMatrix(this.c);
        localObject = new ScaleAnimation(1.0F / this.o, 1.0F, 1.0F / this.o, 1.0F, this.m.x, this.m.y);
      }
      while (localObject != null)
      {
        this.i = true;
        ((Animation)localObject).setDuration(300L);
        startAnimation((Animation)localObject);
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(300L);
              c.this.post(new Runnable()
              {
                public void run()
                {
                  c.this.clearAnimation();
                  c.a(c.this);
                }
              });
              c.a(c.this, false);
              return;
            }
            catch (InterruptedException localInterruptedException)
            {
              while (true)
                localInterruptedException.printStackTrace();
            }
          }
        }).start();
        return;
        if (f4 < this.g)
        {
          this.o = (this.g / f4);
          this.c.postScale(this.o, this.o, this.m.x, this.m.y);
          localObject = new ScaleAnimation(1.0F, this.o, 1.0F, this.o, this.m.x, this.m.y);
        }
        else
        {
          int i1 = 0;
          float f7 = this.h.getWidth() * f4;
          f4 *= this.h.getHeight();
          float f8 = this.p.left - f1;
          float f5 = this.p.top - f2;
          if (f8 < 0.0F)
          {
            f1 = this.p.left;
            i1 = 1;
          }
          if (f5 < 0.0F)
          {
            f2 = this.p.top;
            i1 = 1;
          }
          if (f7 - f8 < f6)
          {
            f1 = this.p.left - (f7 - f6);
            i1 = 1;
          }
          if (f4 - f5 < f3)
          {
            f2 = this.p.top - (f4 - f3);
            i1 = 1;
          }
          if (i1 != 0)
          {
            f3 = arrayOfFloat[2];
            f4 = arrayOfFloat[5];
            arrayOfFloat[2] = f1;
            arrayOfFloat[5] = f2;
            this.c.setValues(arrayOfFloat);
            setImageMatrix(this.c);
            localObject = new TranslateAnimation(f3 - f1, 0.0F, f4 - f2, 0.0F);
          }
          else
          {
            setImageMatrix(this.c);
          }
        }
      }
    }
  }

  private void c()
  {
    if (this.h == null)
      return;
    float[] arrayOfFloat = new float[9];
    this.c.getValues(arrayOfFloat);
    float f1 = Math.max(this.p.width() / this.h.getWidth(), this.p.height() / this.h.getHeight());
    this.j = (this.p.left - (this.h.getWidth() * f1 - this.p.width()) / 2.0F);
    this.k = (this.p.top - (this.h.getHeight() * f1 - this.p.height()) / 2.0F);
    arrayOfFloat[2] = this.j;
    arrayOfFloat[5] = this.k;
    arrayOfFloat[4] = f1;
    arrayOfFloat[0] = f1;
    this.c.setValues(arrayOfFloat);
    this.f = Math.min(2048.0F / this.h.getWidth(), 2048.0F / this.h.getHeight());
    this.g = f1;
    if (this.f < this.g)
      this.f = this.g;
    setImageMatrix(this.c);
  }

  public void a(Rect paramRect)
  {
    this.p = paramRect;
    if (this.h != null)
      c();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.i)
      return true;
    switch (paramMotionEvent.getAction() & 0xFF)
    {
    case 3:
    case 4:
    default:
    case 0:
    case 5:
    case 1:
    case 6:
    case 2:
    }
    while (true)
    {
      this.b = true;
      return true;
      this.c.set(getImageMatrix());
      this.d.set(this.c);
      this.l.set(paramMotionEvent.getX(), paramMotionEvent.getY());
      this.e = 1;
      continue;
      this.n = a(paramMotionEvent);
      if (this.n > 10.0F)
      {
        this.d.set(this.c);
        a(this.m);
        this.e = 2;
        continue;
        b();
        this.e = 0;
        continue;
        float f1;
        if (this.e == 1)
        {
          this.c.set(this.d);
          f1 = paramMotionEvent.getX();
          float f2 = this.l.x;
          float f3 = paramMotionEvent.getY();
          float f4 = this.l.y;
          this.c.postTranslate(f1 - f2, f3 - f4);
          setImageMatrix(this.c);
        }
        else if (this.e == 2)
        {
          this.c.set(this.c);
          f1 = a(paramMotionEvent);
          if (f1 > 10.0F)
          {
            this.c.set(this.d);
            f1 /= this.n;
            this.c.postScale(f1, f1, this.m.x, this.m.y);
          }
          setImageMatrix(this.c);
        }
      }
    }
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
    this.h = paramBitmap;
    if (paramBitmap != null)
      this.h = paramBitmap;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.avatar.c
 * JD-Core Version:    0.6.2
 */