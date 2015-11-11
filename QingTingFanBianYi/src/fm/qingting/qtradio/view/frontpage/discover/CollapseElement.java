package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class CollapseElement extends ViewElement
{
  private final ViewLayout iconLayout = this.itemLayout.createChildLT(28, 15, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(240, 90, 240, 90, 0, 0, ViewLayout.FILL);
  private boolean mCollapsed = true;
  private final Rect mIconRect = new Rect();
  private final Paint mPaint = new Paint();

  public CollapseElement(Context paramContext)
  {
    super(paramContext);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    if (isPressed())
      paramCanvas.drawColor(SkinManager.getBackgroundColor());
    int i = getLeftMargin();
    int j = getTopMargin();
    this.mIconRect.offset(i, j);
    int k = paramCanvas.save();
    if (!this.mCollapsed)
      paramCanvas.rotate(180.0F, this.mIconRect.exactCenterX(), this.mIconRect.exactCenterY());
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, 2130837699), null, this.mIconRect, this.mPaint);
    paramCanvas.restoreToCount(k);
    this.mIconRect.offset(-i, -j);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.itemLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.iconLayout.scaleToBounds(this.itemLayout);
    this.mIconRect.set((this.itemLayout.width - this.iconLayout.width) / 2, (this.itemLayout.height - this.iconLayout.height) / 2, (this.itemLayout.width + this.iconLayout.width) / 2, (this.itemLayout.height + this.iconLayout.height) / 2);
  }

  void toggle()
  {
    if (!this.mCollapsed);
    for (boolean bool = true; ; bool = false)
    {
      this.mCollapsed = bool;
      invalidateElement();
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.CollapseElement
 * JD-Core Version:    0.6.2
 */