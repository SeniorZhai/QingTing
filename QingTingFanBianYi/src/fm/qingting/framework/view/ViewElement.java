package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

public abstract class ViewElement
{
  public static final int INVISIBLE = 4;
  public static final int VISIBLE = 0;
  private ViewElement mBelonging;
  private Rect mBound = new Rect();
  protected OnElementClickListener mClickListener;
  private Context mContext;
  protected boolean mEnable = true;
  protected int mExpandDistance = 0;
  private boolean mInTouchMode = false;
  protected int mMeasureSpec = 1073741824;
  protected int mOwnerId = hashCode();
  private IView mParent;
  private final Handler mPressDelayHandler = new Handler();
  private final Runnable mPressDelayRunnable = new Runnable()
  {
    public void run()
    {
      ViewElement.this.mPressed = true;
      ViewElement.this.invalidateElement(ViewElement.this.mBound);
    }
  };
  private boolean mPressed = false;
  private boolean mSelected = false;
  protected int mTranslationX;
  protected int mTranslationY;
  private UnsetPressedState mUnsetPressedState;
  private int mVisiblity = 0;

  public ViewElement(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private boolean inBound(float paramFloat1, float paramFloat2)
  {
    int i = (int)(paramFloat1 - this.mTranslationX);
    int j = (int)(paramFloat2 - this.mTranslationY);
    Rect localRect = this.mBound;
    int k = this.mExpandDistance;
    return (i > localRect.left - k) && (i < localRect.right + k) && (j > localRect.top - k) && (j < localRect.bottom + k);
  }

  private void removeDelayTimer()
  {
    this.mPressDelayHandler.removeCallbacks(this.mPressDelayRunnable);
    if (this.mUnsetPressedState != null)
      this.mPressDelayHandler.removeCallbacks(this.mUnsetPressedState);
  }

  private void startDelayTimer()
  {
    this.mPressDelayHandler.removeCallbacks(this.mPressDelayRunnable);
    this.mPressDelayHandler.postDelayed(this.mPressDelayRunnable, ViewConfiguration.getTapTimeout());
  }

  public void draw(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mBound.offset(this.mTranslationX, this.mTranslationY);
    if (this.mMeasureSpec == 1073741824)
      paramCanvas.clipRect(this.mBound);
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mBound.offset(-this.mTranslationX, -this.mTranslationY);
    onDrawElement(paramCanvas);
    paramCanvas.restoreToCount(i);
  }

  public void expandHotPot(int paramInt)
  {
    this.mExpandDistance = paramInt;
  }

  public int getBottomMargin()
  {
    return this.mTranslationY + this.mBound.bottom;
  }

  public final Rect getBound()
  {
    return this.mBound;
  }

  protected Context getContext()
  {
    return this.mContext;
  }

  public int getHeight()
  {
    return this.mBound.height();
  }

  public int getLeftMargin()
  {
    return this.mTranslationX + this.mBound.left;
  }

  public int getRightMargin()
  {
    return this.mTranslationX + this.mBound.right;
  }

  public int getTopMargin()
  {
    return this.mTranslationY + this.mBound.top;
  }

  public int getTranslationX()
  {
    return this.mTranslationX;
  }

  public int getTranslationY()
  {
    return this.mTranslationY;
  }

  public int getVisiblity()
  {
    return this.mVisiblity;
  }

  public int getWidth()
  {
    return this.mBound.width();
  }

  public boolean handleTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mClickListener == null);
    while ((paramMotionEvent.getAction() != 0) && (!this.mInTouchMode))
      return false;
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    if ((this.mInTouchMode) && (!inBound(f1, f2)))
    {
      this.mInTouchMode = false;
      removeDelayTimer();
      this.mPressed = false;
      invalidateElement(this.mBound);
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
      if (inBound(f1, f2))
      {
        this.mInTouchMode = true;
        startDelayTimer();
      }
      else
      {
        this.mInTouchMode = false;
        return false;
        this.mInTouchMode = false;
        removeDelayTimer();
        if (this.mEnable)
          this.mClickListener.onElementClick(this);
        if (this.mPressed)
        {
          this.mPressed = false;
          invalidateElement(this.mBound);
        }
        else
        {
          if (this.mUnsetPressedState == null)
            this.mUnsetPressedState = new UnsetPressedState(null);
          this.mPressed = true;
          invalidateElement(this.mBound);
          this.mPressDelayHandler.postDelayed(this.mUnsetPressedState, ViewConfiguration.getPressedStateDuration());
          continue;
          this.mInTouchMode = false;
          removeDelayTimer();
          if (this.mPressed)
          {
            this.mPressed = false;
            invalidateElement(this.mBound);
          }
        }
      }
    }
  }

  protected void invalidateAll()
  {
    if (this.mParent != null)
      this.mParent.getView().invalidate();
    while (this.mBelonging == null)
      return;
    this.mBelonging.invalidateAll();
  }

  protected void invalidateElement()
  {
    if (this.mParent != null)
      this.mParent.getView().invalidate(getLeftMargin(), getTopMargin(), getRightMargin(), getBottomMargin());
    while (this.mBelonging == null)
      return;
    this.mBelonging.invalidateElement(getLeftMargin(), getTopMargin(), getRightMargin(), getBottomMargin());
  }

  protected void invalidateElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mParent != null)
      this.mParent.getView().invalidate(paramInt1, paramInt2, paramInt3, paramInt4);
    while (this.mBelonging == null)
      return;
    this.mBelonging.invalidateElement(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void invalidateElement(Rect paramRect)
  {
    if (this.mParent != null)
      this.mParent.getView().invalidate(getLeftMargin(), getTopMargin(), getRightMargin(), getBottomMargin());
    while (this.mBelonging == null)
      return;
    this.mBelonging.invalidateElement(getLeftMargin(), getTopMargin(), getRightMargin(), getBottomMargin());
  }

  public boolean isHighlighted()
  {
    return this.mPressed;
  }

  protected boolean isPressed()
  {
    return this.mPressed;
  }

  public final boolean isSelected()
  {
    return this.mSelected;
  }

  public void measure(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mBound.set(paramInt1, paramInt2, paramInt3, paramInt4);
    onMeasureElement(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void measure(ViewLayout paramViewLayout)
  {
    measure(paramViewLayout.leftMargin, paramViewLayout.topMargin, paramViewLayout.getRight(), paramViewLayout.getBottom());
  }

  protected abstract void onDrawElement(Canvas paramCanvas);

  protected abstract void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public void setAlpha(int paramInt)
  {
  }

  public final void setBelonging(ViewElement paramViewElement)
  {
    if (this.mBelonging != null)
      throw new IllegalStateException("already has belonging");
    this.mBelonging = paramViewElement;
  }

  public void setOnElementClickListener(OnElementClickListener paramOnElementClickListener)
  {
    this.mClickListener = paramOnElementClickListener;
  }

  protected void setOwenerId(int paramInt)
  {
    this.mOwnerId = paramInt;
  }

  protected void setParent(IView paramIView)
  {
    if (this.mParent != null)
      throw new IllegalStateException("already has parent!");
    this.mParent = paramIView;
  }

  public final void setSelected(boolean paramBoolean)
  {
    this.mSelected = paramBoolean;
  }

  public void setTranslationX(int paramInt)
  {
    this.mTranslationX = paramInt;
  }

  public void setTranslationY(int paramInt)
  {
    this.mTranslationY = paramInt;
  }

  public int setVisible(int paramInt)
  {
    if (this.mVisiblity != paramInt)
      invalidateAll();
    this.mVisiblity = paramInt;
    return paramInt;
  }

  public static abstract interface OnElementClickListener
  {
    public abstract void onElementClick(ViewElement paramViewElement);
  }

  private final class UnsetPressedState
    implements Runnable
  {
    private UnsetPressedState()
    {
    }

    public void run()
    {
      if (ViewElement.this.mPressed)
      {
        ViewElement.this.mPressed = false;
        ViewElement.this.invalidateElement(ViewElement.this.mBound);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.ViewElement
 * JD-Core Version:    0.6.2
 */