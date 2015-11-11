package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

public abstract class AbsCheckBoxElement extends ViewElement
{
  private boolean mChecked = false;
  private boolean mEnable = true;
  private boolean mInTouchMode = false;
  private float mLastMotionX;
  private float mLastMotionY;
  private OnCheckChangeListener mListener;
  private int touchSlop = 10;

  public AbsCheckBoxElement(Context paramContext)
  {
    super(paramContext);
  }

  private boolean positionInElement()
  {
    float f1 = this.mLastMotionX;
    float f2 = this.mLastMotionY;
    return (f1 > getLeftMargin() - this.touchSlop) && (f1 < getRightMargin() + this.touchSlop) && (f2 > getTopMargin() - this.touchSlop) && (f2 < getBottomMargin() + this.touchSlop);
  }

  public void check()
  {
    check(true);
  }

  public void check(boolean paramBoolean)
  {
    if (!this.mChecked)
    {
      this.mChecked = true;
      onCheckChanged(this.mChecked);
      invalidateElement();
      if ((paramBoolean) && (this.mListener != null))
        this.mListener.onCheckChanged(this.mChecked);
    }
  }

  protected abstract void drawCheckState(Canvas paramCanvas);

  public boolean handleTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.mEnable);
    while ((paramMotionEvent.getAction() != 0) && (!this.mInTouchMode))
      return false;
    this.mLastMotionX = paramMotionEvent.getX();
    this.mLastMotionY = paramMotionEvent.getY();
    if ((this.mInTouchMode) && (!positionInElement()))
    {
      this.mInTouchMode = false;
      invalidateElement();
      return false;
    }
    switch (paramMotionEvent.getAction())
    {
    case 2:
    default:
    case 0:
    case 1:
    case 3:
    }
    while (true)
    {
      return true;
      if (positionInElement())
      {
        this.mInTouchMode = true;
      }
      else
      {
        this.mInTouchMode = false;
        return false;
        this.mInTouchMode = false;
        toggle();
        invalidateElement();
        continue;
        this.mInTouchMode = false;
        invalidateElement();
      }
    }
  }

  public boolean isChecked()
  {
    return this.mChecked;
  }

  protected void onCheckChanged(boolean paramBoolean)
  {
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.save();
    drawCheckState(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }

  public void setEnabled(boolean paramBoolean)
  {
    this.mEnable = paramBoolean;
  }

  public void setOnCheckChangeListener(OnCheckChangeListener paramOnCheckChangeListener)
  {
    this.mListener = paramOnCheckChangeListener;
  }

  public void toggle()
  {
    toggle(true);
  }

  public void toggle(boolean paramBoolean)
  {
    if (this.mChecked);
    for (boolean bool = false; ; bool = true)
    {
      this.mChecked = bool;
      onCheckChanged(this.mChecked);
      invalidateElement();
      if ((paramBoolean) && (this.mListener != null))
        this.mListener.onCheckChanged(this.mChecked);
      return;
    }
  }

  public void uncheck()
  {
    uncheck(true);
  }

  public void uncheck(boolean paramBoolean)
  {
    if (this.mChecked)
    {
      this.mChecked = false;
      onCheckChanged(this.mChecked);
      invalidateElement();
      if ((paramBoolean) && (this.mListener != null))
        this.mListener.onCheckChanged(this.mChecked);
    }
  }

  public static abstract interface OnCheckChangeListener
  {
    public abstract void onCheckChanged(boolean paramBoolean);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.AbsCheckBoxElement
 * JD-Core Version:    0.6.2
 */