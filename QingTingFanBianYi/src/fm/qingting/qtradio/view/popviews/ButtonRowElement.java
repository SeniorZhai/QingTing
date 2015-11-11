package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.MotionEvent;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.List;

public class ButtonRowElement extends ViewElement
  implements ViewElement.OnElementClickListener
{
  private ButtonViewElement[] mButtons;
  private Paint mHighlightPaint = new Paint();
  private RectF mHighlightRectF = new RectF();
  private boolean mInTouchMode = false;
  private int mLastTouchChildIndex = -1;
  private int mLineWidth = 1;
  private OnButtonRowClickListener mListener;
  private float mRoundCornerRadius = 0.0F;

  public ButtonRowElement(Context paramContext)
  {
    super(paramContext);
    this.mHighlightPaint.setColor(SkinManager.getItemHighlightMaskColor());
    this.mHighlightPaint.setStyle(Paint.Style.FILL);
  }

  private void drawButtons(Canvas paramCanvas)
  {
    if ((this.mButtons == null) || (this.mButtons.length == 0));
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mButtons.length)
      {
        ButtonViewElement localButtonViewElement = this.mButtons[i];
        localButtonViewElement.setTranslationX(getTranslationX());
        localButtonViewElement.setTranslationY(getTranslationY());
        localButtonViewElement.draw(paramCanvas);
        i += 1;
      }
    }
  }

  private void drawHighlight(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramCanvas.save();
    paramCanvas.clipRect(paramInt2, getTopMargin(), paramInt3, getBottomMargin());
    if (this.mButtons.length == 1)
    {
      this.mHighlightRectF.set(paramInt2, getTopMargin() - this.mRoundCornerRadius, paramInt3, getBottomMargin());
      paramCanvas.drawRoundRect(this.mHighlightRectF, this.mRoundCornerRadius, this.mRoundCornerRadius, this.mHighlightPaint);
    }
    while (true)
    {
      paramCanvas.restoreToCount(i);
      return;
      if (paramInt1 == 0)
      {
        this.mHighlightRectF.set(paramInt2, getTopMargin() - this.mRoundCornerRadius, paramInt3 + this.mRoundCornerRadius, getBottomMargin());
        paramCanvas.drawRoundRect(this.mHighlightRectF, this.mRoundCornerRadius, this.mRoundCornerRadius, this.mHighlightPaint);
      }
      else if (paramInt1 == this.mButtons.length - 1)
      {
        this.mHighlightRectF.set(paramInt2 - this.mRoundCornerRadius, getTopMargin() - this.mRoundCornerRadius, paramInt3, getBottomMargin());
        paramCanvas.drawRoundRect(this.mHighlightRectF, this.mRoundCornerRadius, this.mRoundCornerRadius, this.mHighlightPaint);
      }
      else
      {
        this.mHighlightRectF.set(paramInt2, getTopMargin(), paramInt3, getBottomMargin());
        paramCanvas.drawRect(this.mHighlightRectF, this.mHighlightPaint);
      }
    }
  }

  private void drawLinesAndHighlight(Canvas paramCanvas)
  {
    if (this.mButtons == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mButtons.length)
      {
        ButtonViewElement localButtonViewElement = this.mButtons[i];
        if (localButtonViewElement.isHighlighted())
          drawHighlight(paramCanvas, i, localButtonViewElement.getLeftMargin(), localButtonViewElement.getRightMargin());
        if (i > 0)
          SkinManager.getInstance().drawVerticalLine(paramCanvas, getTopMargin(), getBottomMargin(), localButtonViewElement.getLeftMargin(), this.mLineWidth);
        i += 1;
      }
    }
  }

  private void drawTopLine(Canvas paramCanvas)
  {
    if (this.mLineWidth == 0)
      return;
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, getLeftMargin(), getRightMargin(), getTopMargin(), this.mLineWidth);
  }

  private int handleChildrenTouchEvent(MotionEvent paramMotionEvent)
  {
    int j;
    if ((this.mButtons == null) || (this.mButtons.length == 0))
    {
      j = -1;
      return j;
    }
    int i = 0;
    while (true)
    {
      if (i >= this.mButtons.length)
        break label52;
      j = i;
      if (this.mButtons[i].handleTouchEvent(paramMotionEvent))
        break;
      i += 1;
    }
    label52: return -1;
  }

  private void measureButtons(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mButtons == null);
    while (true)
    {
      return;
      int j = (paramInt3 - paramInt1) / this.mButtons.length;
      int i = 0;
      paramInt3 = paramInt1;
      paramInt1 = i;
      while (paramInt1 < this.mButtons.length)
      {
        this.mButtons[paramInt1].measure(paramInt3, paramInt2, paramInt3 + j, paramInt4);
        this.mButtons[paramInt1].setTextSize(SkinManager.getInstance().getNormalTextSize());
        paramInt3 += j;
        paramInt1 += 1;
      }
    }
  }

  public boolean handleTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() != 0) && (!this.mInTouchMode));
    int i;
    do
    {
      return true;
      switch (paramMotionEvent.getAction())
      {
      default:
        return true;
      case 0:
        this.mLastTouchChildIndex = handleChildrenTouchEvent(paramMotionEvent);
        this.mInTouchMode = true;
        return true;
      case 2:
        i = handleChildrenTouchEvent(paramMotionEvent);
      case 1:
      case 3:
      }
    }
    while (this.mLastTouchChildIndex == i);
    this.mInTouchMode = false;
    return true;
    handleChildrenTouchEvent(paramMotionEvent);
    return true;
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    drawLinesAndHighlight(paramCanvas);
    drawButtons(paramCanvas);
    drawTopLine(paramCanvas);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if ((this.mButtons == null) || (this.mListener == null));
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mButtons.length)
      {
        if (this.mButtons[i] == paramViewElement)
          this.mListener.OnRowClick(paramViewElement, i);
        i += 1;
      }
    }
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    measureButtons(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setButtons(List<String> paramList)
  {
    int j = 0;
    if (this.mButtons != null)
      this.mButtons = null;
    for (int i = 1; ; i = 0)
    {
      this.mButtons = new ButtonViewElement[paramList.size()];
      while (j < paramList.size())
      {
        ButtonViewElement localButtonViewElement = new ButtonViewElement(getContext());
        localButtonViewElement.setText((String)paramList.get(j));
        localButtonViewElement.setTextColor(SkinManager.getTextColorHighlight(), SkinManager.getTextColorHighlight());
        localButtonViewElement.setOnElementClickListener(this);
        localButtonViewElement.setBelonging(this);
        this.mButtons[j] = localButtonViewElement;
        j += 1;
      }
      if (i != 0)
        measureButtons(getLeftMargin() - this.mTranslationX, getTopMargin() - this.mTranslationY, getRightMargin() - this.mTranslationX, getBottomMargin() - this.mTranslationY);
      return;
    }
  }

  public void setOnRowClickListenrer(OnButtonRowClickListener paramOnButtonRowClickListener)
  {
    this.mListener = paramOnButtonRowClickListener;
  }

  public void setOtherParams(float paramFloat, int paramInt)
  {
    this.mRoundCornerRadius = paramFloat;
    this.mLineWidth = paramInt;
  }

  public static abstract interface OnButtonRowClickListener
  {
    public abstract void OnRowClick(ViewElement paramViewElement, int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ButtonRowElement
 * JD-Core Version:    0.6.2
 */