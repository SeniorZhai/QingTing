package fm.qingting.qtradio.view.pinnedsection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class CustomSectionView extends ViewImpl
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 68, 720, 68, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect mTextBound = new Rect();
  private String mTitle;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(720, 45, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public CustomSectionView(Context paramContext)
  {
    super(paramContext);
  }

  private void drawBg(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    paramCanvas.clipRect(0, 0, this.itemLayout.width, this.itemLayout.height);
    paramCanvas.drawColor(SkinManager.getTagBackgroundColor());
    paramCanvas.restoreToCount(i);
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if (this.mTitle == null)
      return;
    TextPaint localTextPaint = SkinManager.getInstance().getSubTextPaint();
    localTextPaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    paramCanvas.drawText(this.mTitle, this.titleLayout.leftMargin, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, localTextPaint);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawBg(paramCanvas);
    drawTitle(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.itemLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mTitle = ((String)paramObject);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.pinnedsection.CustomSectionView
 * JD-Core Version:    0.6.2
 */