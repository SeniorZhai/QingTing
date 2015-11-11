package com.taobao.newxp.view.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.taobao.newxp.common.Log;

public class SwipeView extends HorizontalScrollView
{
  private static int b = 60;
  protected boolean a = false;
  private LinearLayout c;
  private Context d;
  private int e;
  private int f;
  private int g;
  private boolean h = false;
  private boolean i = false;
  private boolean j = false;
  private int k = 0;
  private int l = 0;
  private OnPageChangedListener m = null;
  private SwipeOnTouchListener n;
  private View.OnTouchListener o;
  private SwipeViewPointer p = null;

  public SwipeView(Context paramContext)
  {
    super(paramContext);
    this.d = paramContext;
    a();
  }

  public SwipeView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.d = paramContext;
    a();
  }

  public SwipeView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.d = paramContext;
    a();
  }

  private void a()
  {
    Log.a("uk.co.jasonfry.android.tools.ui.SwipeView", "Initialising SwipeView");
    this.c = new LinearLayout(this.d);
    this.c.setOrientation(0);
    super.addView(this.c, -1, new FrameLayout.LayoutParams(-1, -1));
    setSmoothScrollingEnabled(true);
    setHorizontalFadingEdgeEnabled(false);
    setHorizontalScrollBarEnabled(false);
    this.e = ((WindowManager)this.d.getSystemService("window")).getDefaultDisplay().getWidth();
    this.l = this.e;
    this.k = 0;
    this.n = new SwipeOnTouchListener(null);
    super.setOnTouchListener(this.n);
  }

  private void a(int paramInt, boolean paramBoolean)
  {
    boolean bool = false;
    int i2 = this.k;
    int i1;
    if ((paramInt >= getPageCount()) && (getPageCount() > 0))
    {
      i1 = paramInt - 1;
      if (!paramBoolean)
        break label123;
      smoothScrollTo(this.l * i1, 0);
    }
    while (true)
    {
      this.k = i1;
      if ((this.m != null) && (i2 != i1))
        this.m.onPageChanged(i2, i1);
      if ((this.p != null) && (i2 != i1))
        this.p.setCurrentPage(i1);
      paramBoolean = bool;
      if (!this.a)
        paramBoolean = true;
      this.a = paramBoolean;
      return;
      i1 = paramInt;
      if (paramInt >= 0)
        break;
      i1 = 0;
      break;
      label123: scrollTo(this.l * i1, 0);
    }
  }

  private void a(MotionEvent paramMotionEvent)
  {
    float f1;
    float f2;
    if ((!this.h) && (!this.i))
    {
      f1 = Math.abs(this.f - paramMotionEvent.getX());
      f2 = Math.abs(this.g - paramMotionEvent.getY());
      if (f2 <= f1 + 5.0F)
        break label57;
      this.i = true;
    }
    label57: 
    while (f1 <= f2 + 5.0F)
      return;
    this.h = true;
  }

  public void addPageControlPointer(int paramInt)
  {
    if (this.p != null)
      this.p.addPageCount(paramInt);
  }

  public void addView(View paramView)
  {
    addView(paramView, -1);
  }

  public void addView(View paramView, int paramInt)
  {
    Object localObject;
    if (paramView.getLayoutParams() == null)
      localObject = new FrameLayout.LayoutParams(this.l, -1);
    while (true)
    {
      addView(paramView, paramInt, (ViewGroup.LayoutParams)localObject);
      return;
      localObject = paramView.getLayoutParams();
      ((ViewGroup.LayoutParams)localObject).width = this.l;
    }
  }

  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    requestLayout();
    invalidate();
    this.c.addView(paramView, paramInt, paramLayoutParams);
  }

  public void addView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    paramLayoutParams.width = this.l;
    addView(paramView, -1, paramLayoutParams);
  }

  public int calculatePageSize(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
  {
    return setPageWidth(paramMarginLayoutParams.leftMargin + paramMarginLayoutParams.width + paramMarginLayoutParams.rightMargin);
  }

  public LinearLayout getChildContainer()
  {
    return this.c;
  }

  public int getCurrentPage()
  {
    return this.k;
  }

  public OnPageChangedListener getOnPageChangedListener()
  {
    return this.m;
  }

  public SwipeViewPointer getPageControl()
  {
    return this.p;
  }

  public int getPageCount()
  {
    return this.c.getChildCount();
  }

  public int getPageWidth()
  {
    return this.l;
  }

  public int getSwipeThreshold()
  {
    return b;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = super.onInterceptTouchEvent(paramMotionEvent);
    if (paramMotionEvent.getAction() == 0)
    {
      this.f = ((int)paramMotionEvent.getX());
      this.g = ((int)paramMotionEvent.getY());
      if (!this.j)
      {
        this.h = false;
        this.i = false;
      }
    }
    while (this.i)
    {
      return false;
      if (paramMotionEvent.getAction() == 2)
        a(paramMotionEvent);
    }
    if (this.h)
    {
      this.j = true;
      return true;
    }
    return bool;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.a)
    {
      scrollToPage(this.k);
      this.a = false;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
  }

  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    return false;
  }

  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    return true;
  }

  public void requestChildFocus(View paramView1, View paramView2)
  {
    requestFocus();
  }

  public void scrollToPage(int paramInt)
  {
    a(paramInt, false);
  }

  public void setOnPageChangedListener(OnPageChangedListener paramOnPageChangedListener)
  {
    this.m = paramOnPageChangedListener;
  }

  public void setOnTouchListener(View.OnTouchListener paramOnTouchListener)
  {
    this.o = paramOnTouchListener;
  }

  public void setPageControl(SwipeViewPointer paramSwipeViewPointer)
  {
    this.p = paramSwipeViewPointer;
    if (paramSwipeViewPointer != null)
    {
      paramSwipeViewPointer.setPageCount(getPageCount());
      paramSwipeViewPointer.setCurrentPage(this.k);
      paramSwipeViewPointer.setOnPageControlClickListener(new SwipeViewPointer.OnPageControlClickListener()
      {
        public void goBackwards()
        {
          SwipeView.this.smoothScrollToPage(SwipeView.a(SwipeView.this) - 1);
        }

        public void goForwards()
        {
          SwipeView.this.smoothScrollToPage(SwipeView.a(SwipeView.this) + 1);
        }
      });
    }
  }

  public int setPageWidth(int paramInt)
  {
    this.l = paramInt;
    return (this.e - this.l) / 2;
  }

  public void setSwipeThreshold(int paramInt)
  {
    b = paramInt;
  }

  public void smoothScrollToPage(int paramInt)
  {
    a(paramInt, true);
  }

  public static abstract interface OnPageChangedListener
  {
    public abstract void onPageChanged(int paramInt1, int paramInt2);
  }

  private class SwipeOnTouchListener
    implements View.OnTouchListener
  {
    private boolean b = false;
    private int c;
    private int d;
    private boolean e = true;

    private SwipeOnTouchListener()
    {
    }

    private boolean a(MotionEvent paramMotionEvent)
    {
      SwipeView.a(SwipeView.this, (int)paramMotionEvent.getX());
      SwipeView.b(SwipeView.this, (int)paramMotionEvent.getY());
      this.e = false;
      return false;
    }

    private boolean b(MotionEvent paramMotionEvent)
    {
      int j = SwipeView.d(SwipeView.this) - (int)paramMotionEvent.getX();
      int i;
      if (j < 0)
        if (this.c + 4 <= j)
        {
          i = 1;
          if ((i == this.d) || (this.e))
            break label195;
          SwipeView.a(SwipeView.this, (int)paramMotionEvent.getX());
        }
      label195: for (this.c = (SwipeView.d(SwipeView.this) - (int)paramMotionEvent.getX()); ; this.c = j)
      {
        this.d = i;
        if (!SwipeView.c(SwipeView.this))
          break label203;
        this.b = true;
        SwipeView.this.dispatchTouchEvent(MotionEvent.obtain(paramMotionEvent.getDownTime(), paramMotionEvent.getEventTime(), 0, SwipeView.d(SwipeView.this), SwipeView.e(SwipeView.this), paramMotionEvent.getPressure(), paramMotionEvent.getSize(), paramMotionEvent.getMetaState(), paramMotionEvent.getXPrecision(), paramMotionEvent.getYPrecision(), paramMotionEvent.getDeviceId(), paramMotionEvent.getEdgeFlags()));
        SwipeView.a(SwipeView.this, false);
        return true;
        i = -1;
        break;
        if (this.c - 4 <= j)
        {
          i = 1;
          break;
        }
        i = -1;
        break;
      }
      label203: return false;
    }

    private boolean c(MotionEvent paramMotionEvent)
    {
      float f2 = SwipeView.this.getScrollX();
      float f1 = SwipeView.f(SwipeView.this).getMeasuredWidth() / SwipeView.g(SwipeView.this);
      f2 /= SwipeView.g(SwipeView.this);
      if (this.d == 1)
        if (this.c > SwipeView.f())
          if (SwipeView.a(SwipeView.this) < f1 - 1.0F)
            f1 = (int)(f2 + 1.0F) * SwipeView.g(SwipeView.this);
      while (true)
      {
        SwipeView.this.smoothScrollToPage((int)f1 / SwipeView.g(SwipeView.this));
        this.e = true;
        this.c = 0;
        SwipeView.b(SwipeView.this, false);
        SwipeView.c(SwipeView.this, false);
        return true;
        f1 = SwipeView.a(SwipeView.this) * SwipeView.g(SwipeView.this);
        continue;
        if (Math.round(f2) == f1 - 1.0F)
        {
          f1 = (int)(f2 + 1.0F) * SwipeView.g(SwipeView.this);
        }
        else
        {
          f1 = SwipeView.a(SwipeView.this) * SwipeView.g(SwipeView.this);
          continue;
          if (this.c < -SwipeView.f())
            f1 = (int)f2 * SwipeView.g(SwipeView.this);
          else if (Math.round(f2) == 0)
            f1 = (int)f2 * SwipeView.g(SwipeView.this);
          else
            f1 = SwipeView.a(SwipeView.this) * SwipeView.g(SwipeView.this);
        }
      }
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      if (((SwipeView.b(SwipeView.this) != null) && (!SwipeView.c(SwipeView.this))) || ((SwipeView.b(SwipeView.this) != null) && (this.b) && (SwipeView.b(SwipeView.this).onTouch(paramView, paramMotionEvent))))
      {
        if (paramMotionEvent.getAction() == 1)
          c(paramMotionEvent);
        return true;
      }
      if (this.b)
      {
        this.b = false;
        return false;
      }
      switch (paramMotionEvent.getAction())
      {
      default:
        return false;
      case 0:
        return a(paramMotionEvent);
      case 2:
        return b(paramMotionEvent);
      case 1:
      }
      return c(paramMotionEvent);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.widget.SwipeView
 * JD-Core Version:    0.6.2
 */