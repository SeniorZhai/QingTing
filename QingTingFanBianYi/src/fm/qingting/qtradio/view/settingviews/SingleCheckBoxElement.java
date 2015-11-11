package fm.qingting.qtradio.view.settingviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.AbsCheckBoxElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class SingleCheckBoxElement extends AbsCheckBoxElement
{
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = ViewLayout.createViewLayoutWithBoundsLT(48, 48, 48, 48, 0, 0, ViewLayout.FILL);
  private final Rect mCheckRect = new Rect();
  private final Paint mCheckStatePaint = new Paint();
  private final Paint mPaint = new Paint();

  public SingleCheckBoxElement(Context paramContext)
  {
    super(paramContext);
    this.mMeasureSpec = 0;
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
  }

  protected void drawCheckState(Canvas paramCanvas)
  {
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mCheckRect.offset(this.mTranslationX, this.mTranslationY);
    if (isChecked())
    {
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, 2130837754), null, this.mCheckRect, this.mPaint);
    }
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mCheckRect.offset(-this.mTranslationX, -this.mTranslationY);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.checkbgLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckRect.set((this.checkbgLayout.width - this.checkStateLayout.width) / 2 + paramInt1, (paramInt2 + paramInt4 - this.checkStateLayout.height) / 2, (this.checkbgLayout.width + this.checkStateLayout.width) / 2 + paramInt1, (paramInt2 + paramInt4 + this.checkStateLayout.height) / 2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.settingviews.SingleCheckBoxElement
 * JD-Core Version:    0.6.2
 */