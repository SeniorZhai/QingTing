package com.taobao.newxp.view;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import java.util.HashMap;

public class HorizontalStrip extends AbsHorizontalStrip
{
  protected final float g;
  private e h;
  private final c i = new c();
  private int j;

  public HorizontalStrip(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public HorizontalStrip(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.g = paramContext.getResources().getDisplayMetrics().density;
    this.j = -16777216;
  }

  private int b(int paramInt)
  {
    float f = this.g;
    this.h.a(paramInt, this.i, f);
    return this.i.a();
  }

  private int c(int paramInt)
  {
    float f = this.g;
    this.h.a(paramInt, this.i, f);
    return this.i.b();
  }

  private int d(int paramInt)
  {
    return getChildAt(paramInt).getWidth();
  }

  private void d()
  {
    int k = 0;
    while (k < this.h.a())
    {
      View localView = getChildAt(k);
      Drawable localDrawable = this.h.a(k);
      if (((localView instanceof UMScreenshot)) && (localDrawable != null))
        ((UMScreenshot)localView).setScreenshotDrawable(localDrawable);
      k += 1;
    }
    requestLayout();
  }

  protected float a(int paramInt)
  {
    if (this.f.containsKey(Integer.valueOf(paramInt)))
      return ((Float[])this.f.get(Integer.valueOf(paramInt)))[0].floatValue();
    return 0.0F;
  }

  protected float b(float paramFloat)
  {
    int k = 0;
    int m = 0;
    for (int n = 0; k < getChildCount(); n = m)
    {
      m += d(k);
      if (m <= paramFloat);
      k += 1;
    }
    return n;
  }

  protected float c(float paramFloat)
  {
    int k = 0;
    int m = 0;
    for (int n = 0; k < getChildCount(); n = m)
    {
      m += d(k);
      if (m <= paramFloat);
      k += 1;
    }
    return n;
  }

  protected void c()
  {
    removeAllViews();
    this.f.clear();
    if (this.h == null)
      return;
    int k = 0;
    while (k < this.h.a())
    {
      View localView = this.h.a(this.a, this, k);
      localView.setOnFocusChangeListener(new b(this, k));
      addView(localView);
      k += 1;
    }
    d();
  }

  protected float getLeftFadingEdgeStrength()
  {
    return 0.0F;
  }

  protected float getRightFadingEdgeStrength()
  {
    return 0.0F;
  }

  public int getSolidColor()
  {
    return this.j;
  }

  public void onDestroyView()
  {
    setAdapter(null);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.h == null);
    do
    {
      return;
      int i1 = getHeight();
      this.e = 0.0F;
      int m = getPaddingLeft();
      int k = m;
      if (this.h.b())
        k = m + this.c;
      int n = 0;
      m = k;
      k = n;
      if (k < getChildCount())
      {
        View localView = getChildAt(k);
        if ((localView.getLayoutParams() != null) && (localView.getLayoutParams().width > 0));
        for (n = localView.getLayoutParams().width + m; ; n = localView.getMeasuredWidth() + m)
        {
          measureChild(localView, localView.getLayoutParams().width, localView.getLayoutParams().height);
          localView.layout(m, 0, n, i1);
          float f1 = m;
          float f2 = n;
          this.f.put(Integer.valueOf(k), new Float[] { Float.valueOf(f1), Float.valueOf(f2) });
          m = this.c + n;
          this.e = n;
          k += 1;
          break;
          localView.setMinimumWidth(paramInt3 - paramInt1);
          localView.setMinimumHeight(paramInt4 - paramInt2);
          localView.measure(paramInt3 - paramInt1, paramInt4 - paramInt2);
        }
      }
    }
    while (!this.h.b());
    this.e += this.c;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), paramInt2);
    if (this.h != null)
    {
      this.h.b(getHeight());
      setAdapter(this.h);
    }
  }

  public void setAdapter(e parame)
  {
    this.h = parame;
    c();
  }

  public class a extends DataSetObserver
  {
    HorizontalStrip a;

    public a(HorizontalStrip arg2)
    {
      Object localObject;
      this.a = localObject;
    }

    public void onChanged()
    {
      super.onChanged();
    }

    public void onInvalidated()
    {
      super.onInvalidated();
    }
  }

  class b
    implements View.OnFocusChangeListener
  {
    HorizontalStrip a;
    int b;

    public b(HorizontalStrip paramInt, int arg3)
    {
      this.a = paramInt;
      int i;
      this.b = i;
    }

    public void onFocusChange(View paramView, boolean paramBoolean)
    {
      if (paramBoolean)
        this.a.onChildAcquiredFocus(this.b);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.HorizontalStrip
 * JD-Core Version:    0.6.2
 */