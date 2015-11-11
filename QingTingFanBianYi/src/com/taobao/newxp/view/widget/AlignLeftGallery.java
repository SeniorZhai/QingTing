package com.taobao.newxp.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.Gallery;
import java.lang.reflect.Field;

public class AlignLeftGallery extends Gallery
{
  private static final String a = "AlignLeftGallery";
  private static int f;
  private static int g;
  private Camera b = new Camera();
  private int c;
  private int d;
  private boolean e;
  private int h;
  private IOnItemClickListener i;

  public AlignLeftGallery(Context paramContext)
  {
    super(paramContext);
    setStaticTransformationsEnabled(true);
  }

  public AlignLeftGallery(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
    setStaticTransformationsEnabled(true);
  }

  public AlignLeftGallery(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    a(paramContext, paramAttributeSet);
    setStaticTransformationsEnabled(true);
  }

  private void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, new int[] { 16842966 });
    this.d = paramContext.getDimensionPixelSize(0, 0);
    paramContext.recycle();
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected boolean getChildStaticTransformation(View paramView, Transformation paramTransformation)
  {
    paramTransformation.clear();
    paramTransformation.setTransformationType(2);
    this.b.save();
    paramView = paramTransformation.getMatrix();
    if (this.e)
    {
      f = getChildAt(0).getWidth();
      Log.i("AlignLeftGallery", "firstChildWidth = " + f);
      g = getChildAt(0).getPaddingLeft();
      this.e = false;
    }
    this.h = (f / 2 + g + this.d - this.c / 2);
    this.b.translate(this.h, 0.0F, 0.0F);
    this.b.getMatrix(paramView);
    this.b.restore();
    return true;
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    Log.i("AlignLeftGallery", "onSingleTapUp----------------------");
    try
    {
      paramMotionEvent = AlignLeftGallery.class.getSuperclass().getDeclaredField("mDownTouchPosition");
      paramMotionEvent.setAccessible(true);
      int j = paramMotionEvent.getInt(this);
      Log.i("AlignLeftGallery", "mDownTouchPosition = " + j);
      if ((this.i != null) && (j >= 0))
        this.i.onItemClick(j);
      return false;
    }
    catch (SecurityException paramMotionEvent)
    {
      while (true)
        paramMotionEvent.printStackTrace();
    }
    catch (NoSuchFieldException paramMotionEvent)
    {
      while (true)
        paramMotionEvent.printStackTrace();
    }
    catch (IllegalArgumentException paramMotionEvent)
    {
      while (true)
        paramMotionEvent.printStackTrace();
    }
    catch (IllegalAccessException paramMotionEvent)
    {
      while (true)
        paramMotionEvent.printStackTrace();
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Log.i("AlignLeftGallery", "onSizeChanged------- w = " + paramInt1 + " h = " + paramInt2 + "oldw = " + paramInt3 + "oldh = " + paramInt4);
    if (!this.e)
    {
      this.c = paramInt1;
      getLayoutParams().width = this.c;
      this.e = true;
    }
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    Log.i("AlignLeftGallery", "onTouchEvent----------------------");
    paramMotionEvent.offsetLocation(-this.h, 0.0F);
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setOnItemClickListener(IOnItemClickListener paramIOnItemClickListener)
  {
    this.i = paramIOnItemClickListener;
  }

  public static abstract interface IOnItemClickListener
  {
    public abstract void onItemClick(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.widget.AlignLeftGallery
 * JD-Core Version:    0.6.2
 */