package fm.qingting.qtradio.view.education.shareguide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import fm.qingting.framework.view.AbsCheckBoxElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ShareCheckBoxElement extends AbsCheckBoxElement
{
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(34, 34, 1, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = ViewLayout.createViewLayoutWithBoundsLT(40, 40, 40, 40, 0, 0, ViewLayout.FILL);
  private Paint mCheckBgPaint = new Paint();
  private final Rect mCheckRect = new Rect();
  private Rect mTextBound = new Rect();
  private Paint mTextPaint = new Paint();

  public ShareCheckBoxElement(Context paramContext)
  {
    super(paramContext);
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mTextPaint.setColor(SkinManager.getTextColorSubInfo());
  }

  protected void drawCheckState(Canvas paramCanvas)
  {
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mCheckRect.offset(this.mTranslationX, this.mTranslationY);
    paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2 - this.checkStateLayout.leftMargin, this.mCheckBgPaint);
    if (isChecked())
    {
      this.mTextPaint.getTextBounds("√", 0, "√".length(), this.mTextBound);
      paramCanvas.drawText("√", this.mCheckRect.centerX() - this.mTextBound.width() / 2, this.mCheckRect.centerY() - (this.mTextBound.top + this.mTextBound.bottom) / 2, this.mTextPaint);
    }
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mCheckRect.offset(-this.mTranslationX, -this.mTranslationY);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.checkbgLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckRect.set((this.checkbgLayout.width - this.checkStateLayout.width) / 2 + paramInt1, (paramInt2 + paramInt4 - this.checkStateLayout.height) / 2, (this.checkbgLayout.width + this.checkStateLayout.width) / 2 + paramInt1, (paramInt2 + paramInt4 + this.checkStateLayout.height) / 2);
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
    this.mTextPaint.setTextSize(this.checkStateLayout.height * 1.2F);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.education.shareguide.ShareCheckBoxElement
 * JD-Core Version:    0.6.2
 */