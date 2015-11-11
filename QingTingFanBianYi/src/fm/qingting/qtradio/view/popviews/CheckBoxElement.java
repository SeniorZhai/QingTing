package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.AbsCheckBoxElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class CheckBoxElement extends AbsCheckBoxElement
{
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = ViewLayout.createViewLayoutWithBoundsLT(48, 48, 48, 48, 0, 0, ViewLayout.FILL);
  private Paint mCheckBgPaint = new Paint();
  private final Rect mCheckRect = new Rect();
  private Paint mCheckStatePaint = new Paint();
  private final Paint mPaint = new Paint();

  public CheckBoxElement(Context paramContext)
  {
    super(paramContext);
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
  }

  protected void drawCheckState(Canvas paramCanvas)
  {
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mCheckRect.offset(this.mTranslationX, this.mTranslationY);
    if (isChecked())
    {
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2 - this.checkStateLayout.leftMargin, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, 2130837754), null, this.mCheckRect, this.mPaint);
    }
    while (true)
    {
      if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
        this.mCheckRect.offset(-this.mTranslationX, -this.mTranslationY);
      return;
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2 - this.checkStateLayout.leftMargin, this.mCheckBgPaint);
    }
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.checkbgLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckRect.set((this.checkbgLayout.width - this.checkStateLayout.width) / 2 + paramInt1, (paramInt2 + paramInt4 - this.checkStateLayout.height) / 2, (this.checkbgLayout.width + this.checkStateLayout.width) / 2 + paramInt1, (paramInt2 + paramInt4 + this.checkStateLayout.height) / 2);
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CheckBoxElement
 * JD-Core Version:    0.6.2
 */