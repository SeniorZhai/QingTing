package com.tencent.weibo.sdk.android.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.List;

public class LetterListView extends View
{
  List<String> b;
  int choose = -1;
  OnTouchingLetterChangedListener onTouchingLetterChangedListener;
  Paint paint = new Paint();
  boolean showBkg = false;

  public LetterListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public LetterListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public LetterListView(Context paramContext, List<String> paramList)
  {
    super(paramContext);
    this.b = paramList;
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    float f = paramMotionEvent.getY();
    int j = this.choose;
    paramMotionEvent = this.onTouchingLetterChangedListener;
    int k = (int)(f / getHeight() * this.b.size());
    switch (i)
    {
    default:
    case 0:
    case 2:
      do
      {
        do
        {
          return true;
          this.showBkg = true;
        }
        while ((j == k) || (paramMotionEvent == null) || (k < 0) || (k >= this.b.size()));
        paramMotionEvent.onTouchingLetterChanged(k);
        this.choose = k;
        invalidate();
        return true;
      }
      while ((j == k) || (paramMotionEvent == null) || (k < 0) || (k >= this.b.size()));
      paramMotionEvent.onTouchingLetterChanged(k);
      this.choose = k;
      invalidate();
      return true;
    case 1:
    }
    this.showBkg = false;
    this.choose = -1;
    invalidate();
    return true;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.showBkg)
      paramCanvas.drawColor(Color.parseColor("#00000000"));
    int i = getHeight();
    int j = getWidth();
    int k;
    if (this.b.size() > 0)
    {
      k = i / this.b.size();
      i = 0;
    }
    while (true)
    {
      if (i >= this.b.size())
        return;
      this.paint.setColor(Color.parseColor("#2796c4"));
      this.paint.setTextSize(17.0F);
      this.paint.setTypeface(Typeface.DEFAULT_BOLD);
      this.paint.setAntiAlias(true);
      if (i == this.choose)
      {
        this.paint.setColor(-7829368);
        this.paint.setFakeBoldText(true);
      }
      float f1 = (j - 30) / 2;
      float f2 = this.paint.measureText((String)this.b.get(i)) / 2.0F;
      float f3 = k * i + k;
      paramCanvas.drawText(((String)this.b.get(i)).toUpperCase(), f1 - f2, f3, this.paint);
      this.paint.reset();
      i += 1;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramMotionEvent);
  }

  public void setB(List<String> paramList)
  {
    this.b = paramList;
    invalidate();
  }

  public void setOnTouchingLetterChangedListener(OnTouchingLetterChangedListener paramOnTouchingLetterChangedListener)
  {
    this.onTouchingLetterChangedListener = paramOnTouchingLetterChangedListener;
  }

  public static abstract interface OnTouchingLetterChangedListener
  {
    public abstract void onTouchingLetterChanged(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.LetterListView
 * JD-Core Version:    0.6.2
 */