package com.taobao.newxp.view.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.taobao.newxp.common.Log;
import java.util.ArrayList;

public class SwipeViewPointer extends LinearLayout
{
  private int a = 7;
  private Drawable b;
  private Drawable c;
  private ArrayList<ImageView> d;
  private int e = 0;
  private int f = 0;
  private Context g;
  private OnPageControlClickListener h = null;

  public SwipeViewPointer(Context paramContext)
  {
    super(paramContext);
    this.g = paramContext;
    a();
  }

  public SwipeViewPointer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.g = paramContext;
  }

  private void a()
  {
    Log.a("uk.co.jasonfry.android.tools.ui.PageControl", "Initialising PageControl");
    this.d = new ArrayList();
    this.b = new ShapeDrawable();
    this.c = new ShapeDrawable();
    this.b.setBounds(0, 0, this.a, this.a);
    this.c.setBounds(0, 0, this.a, this.a);
    OvalShape localOvalShape1 = new OvalShape();
    localOvalShape1.resize(this.a, this.a);
    OvalShape localOvalShape2 = new OvalShape();
    localOvalShape2.resize(this.a, this.a);
    ((ShapeDrawable)this.b).getPaint().setColor(-12303292);
    ((ShapeDrawable)this.c).getPaint().setColor(-3355444);
    ((ShapeDrawable)this.b).setShape(localOvalShape1);
    ((ShapeDrawable)this.c).setShape(localOvalShape2);
    this.a = ((int)(this.a * getResources().getDisplayMetrics().density));
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (SwipeViewPointer.a(SwipeViewPointer.this) != null);
        switch (paramAnonymousMotionEvent.getAction())
        {
        default:
          return true;
        case 1:
        }
        if (SwipeViewPointer.this.getOrientation() == 0)
          if (paramAnonymousMotionEvent.getX() < SwipeViewPointer.this.getWidth() / 2)
            if (SwipeViewPointer.b(SwipeViewPointer.this) > 0)
              SwipeViewPointer.a(SwipeViewPointer.this).goBackwards();
        while (true)
        {
          return false;
          if (SwipeViewPointer.b(SwipeViewPointer.this) < SwipeViewPointer.c(SwipeViewPointer.this) - 1)
          {
            SwipeViewPointer.a(SwipeViewPointer.this).goForwards();
            continue;
            if (paramAnonymousMotionEvent.getY() < SwipeViewPointer.this.getHeight() / 2)
            {
              if (SwipeViewPointer.b(SwipeViewPointer.this) > 0)
                SwipeViewPointer.a(SwipeViewPointer.this).goBackwards();
            }
            else if (SwipeViewPointer.b(SwipeViewPointer.this) < SwipeViewPointer.c(SwipeViewPointer.this) - 1)
              SwipeViewPointer.a(SwipeViewPointer.this).goForwards();
          }
        }
      }
    });
  }

  private void b()
  {
    ImageView localImageView = new ImageView(this.g);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.a, this.a);
    localLayoutParams.setMargins(this.a / 2, this.a, this.a / 2, this.a);
    localImageView.setLayoutParams(localLayoutParams);
    localImageView.setBackgroundDrawable(this.c);
    this.d.add(localImageView);
    addView(localImageView);
  }

  public void addPageCount(int paramInt)
  {
    this.e += paramInt;
    int i = 0;
    while (i < paramInt)
    {
      b();
      i += 1;
    }
  }

  public Drawable getActiveDrawable()
  {
    return this.b;
  }

  public int getCurrentPage()
  {
    return this.f;
  }

  public Drawable getInactiveDrawable()
  {
    return this.c;
  }

  public int getIndicatorSize()
  {
    return this.a;
  }

  public OnPageControlClickListener getOnPageControlClickListener()
  {
    return this.h;
  }

  public int getPageCount()
  {
    return this.e;
  }

  protected void onFinishInflate()
  {
    a();
  }

  public void setActiveDrawable(Drawable paramDrawable)
  {
    this.b = paramDrawable;
    if ((this.d != null) && (this.d.size() > 0))
      ((ImageView)this.d.get(this.f)).setBackgroundDrawable(this.b);
  }

  public void setCurrentPage(int paramInt)
  {
    if (paramInt < this.e)
    {
      ((ImageView)this.d.get(this.f)).setBackgroundDrawable(this.c);
      ((ImageView)this.d.get(paramInt)).setBackgroundDrawable(this.b);
      this.f = paramInt;
    }
  }

  public void setInactiveDrawable(Drawable paramDrawable)
  {
    this.c = paramDrawable;
    if ((this.d != null) && (this.d.size() > 0))
    {
      int i = 0;
      while (i < this.e)
      {
        ((ImageView)this.d.get(i)).setBackgroundDrawable(this.c);
        i += 1;
      }
      ((ImageView)this.d.get(this.f)).setBackgroundDrawable(this.b);
    }
  }

  public void setIndicatorSize(int paramInt)
  {
    this.a = paramInt;
    paramInt = 0;
    while (paramInt < this.e)
    {
      ((ImageView)this.d.get(paramInt)).setLayoutParams(new LinearLayout.LayoutParams(this.a, this.a));
      paramInt += 1;
    }
  }

  public void setOnPageControlClickListener(OnPageControlClickListener paramOnPageControlClickListener)
  {
    this.h = paramOnPageControlClickListener;
  }

  public void setPageCount(int paramInt)
  {
    this.e = paramInt;
    int i = 0;
    while (i < paramInt)
    {
      b();
      i += 1;
    }
  }

  public static abstract interface OnPageControlClickListener
  {
    public abstract void goBackwards();

    public abstract void goForwards();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.widget.SwipeViewPointer
 * JD-Core Version:    0.6.2
 */