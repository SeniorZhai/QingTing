package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ScreenConfiguration;

class ButtonElement extends ViewElement
{
  private Paint mBgPaint = new Paint();
  private Paint mFramePaint = new Paint();
  private RectF mFrameRectF = new RectF();
  private Paint mHighlightPaint = new Paint();
  private Paint mNormalPaint = new Paint();
  private Rect mTextBound = new Rect();
  private String mTitle = " ";

  public ButtonElement(Context paramContext)
  {
    super(paramContext);
    this.mFramePaint.setColor(-11711155);
    this.mFramePaint.setStyle(Paint.Style.STROKE);
    this.mBgPaint.setColor(SkinManager.getTextColorHighlight());
    this.mBgPaint.setStyle(Paint.Style.FILL);
    this.mNormalPaint.setColor(-1);
    this.mHighlightPaint.setColor(-1);
    this.mFramePaint.setStrokeWidth(ScreenConfiguration.getWidth() / 360.0F);
    this.mMeasureSpec = 0;
  }

  private void drawButton(Canvas paramCanvas)
  {
    float f1 = ScreenConfiguration.getWidth() / 90.0F;
    Object localObject = this.mFrameRectF;
    if (isPressed());
    for (Paint localPaint = this.mBgPaint; ; localPaint = this.mFramePaint)
    {
      paramCanvas.drawRoundRect((RectF)localObject, f1, f1, localPaint);
      if ((this.mTitle != null) && (!this.mTitle.equalsIgnoreCase("")))
        break;
      return;
    }
    this.mNormalPaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    localObject = this.mTitle;
    f1 = (getLeftMargin() + getRightMargin() - this.mTextBound.left - this.mTextBound.right) / 2;
    float f2 = this.mFrameRectF.centerY();
    float f3 = (this.mTextBound.top + this.mTextBound.bottom) / 2;
    if (isPressed());
    for (localPaint = this.mHighlightPaint; ; localPaint = this.mNormalPaint)
    {
      paramCanvas.drawText((String)localObject, f1, f2 - f3, localPaint);
      return;
    }
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawButton(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mFrameRectF.set(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setFrameColor(int paramInt1, int paramInt2)
  {
    this.mBgPaint.setColor(paramInt1);
    this.mFramePaint.setColor(paramInt2);
  }

  public void setText(String paramString)
  {
    this.mTitle = paramString;
  }

  public void setTextColor(int paramInt1, int paramInt2)
  {
    this.mHighlightPaint.setColor(paramInt1);
    this.mNormalPaint.setColor(paramInt2);
  }

  public void setTextSize(float paramFloat)
  {
    this.mHighlightPaint.setTextSize(paramFloat);
    this.mNormalPaint.setTextSize(paramFloat);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.ButtonElement
 * JD-Core Version:    0.6.2
 */