package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.manager.SkinManager;

public class LabelViewElement extends ViewElement
{
  private final Paint mBgPaint = new Paint();
  private String mId;
  private final Paint mLabelPaint = new Paint();
  private final Rect mTextBound = new Rect();
  private final RectF mTipRectF = new RectF();

  public LabelViewElement(Context paramContext)
  {
    super(paramContext);
    this.mBgPaint.setColor(SkinManager.getTextColorHighlight());
    this.mBgPaint.setStyle(Paint.Style.FILL);
    this.mLabelPaint.setColor(-1);
    this.mLabelPaint.setTextSize(SkinManager.getInstance().getTinyTextSize());
  }

  private void drawTip(Canvas paramCanvas)
  {
    if (this.mId == null);
    int i;
    do
    {
      return;
      i = IMAgent.getInstance().getUnreadCnt(this.mId);
    }
    while (i <= 0);
    if (i >= 100);
    int j;
    int k;
    for (String str = "99+"; ; str = String.valueOf(i))
    {
      this.mLabelPaint.getTextBounds(str, 0, str.length(), this.mTextBound);
      i = this.mTextBound.right + this.mTextBound.left;
      j = getUpperLimit();
      k = getHeight();
      if (i > j)
        break;
      float f1 = getLeftMargin() + k / 2;
      float f2 = getTopMargin() + k / 2;
      paramCanvas.drawCircle(f1, f2, k / 2, this.mBgPaint);
      paramCanvas.drawText(str, f1 - (this.mTextBound.right + this.mTextBound.left) / 2, f2 - (this.mTextBound.top + this.mTextBound.bottom) / 2, this.mLabelPaint);
      return;
    }
    this.mTipRectF.set(getLeftMargin(), getTopMargin(), i + (getLeftMargin() + k) - j, getBottomMargin());
    paramCanvas.drawRoundRect(this.mTipRectF, k / 2, k / 2, this.mBgPaint);
    paramCanvas.drawText(str, this.mTipRectF.centerX() - (this.mTextBound.right + this.mTextBound.left) / 2, this.mTipRectF.centerY() - (this.mTextBound.top + this.mTextBound.bottom) / 2, this.mLabelPaint);
  }

  private int getUpperLimit()
  {
    return getHeight() * 2 / 3;
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    drawTip(paramCanvas);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }

  public void setId(String paramString)
  {
    if (paramString == null)
    {
      setVisible(4);
      return;
    }
    this.mId = paramString;
    setVisible(0);
    invalidateElement();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.LabelViewElement
 * JD-Core Version:    0.6.2
 */