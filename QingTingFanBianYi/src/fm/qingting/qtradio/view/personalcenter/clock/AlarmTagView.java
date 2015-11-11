package fm.qingting.qtradio.view.personalcenter.clock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class AlarmTagView extends ViewImpl
{
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(720, 75, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mHasBackground = true;
  private Rect mTextBound = new Rect();
  private String mTitle;
  private Paint mTitlePaint = new Paint();
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 72, 720, 72, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(720, 45, 34, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public AlarmTagView(Context paramContext)
  {
    super(paramContext);
    this.mTitlePaint.setColor(SkinManager.getTextColorHighlight());
  }

  private void drawBg(Canvas paramCanvas)
  {
    if (this.mHasBackground)
      paramCanvas.drawColor(SkinManager.getTagBackgroundColor());
  }

  private void drawLines(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.standardLayout.width, 0, this.lineLayout.height);
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.standardLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if (this.mTitle == null)
      return;
    this.mTitlePaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
    paramCanvas.drawText(this.mTitle, this.titleLayout.leftMargin, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
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
    drawLines(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mTitlePaint.setTextSize(this.titleLayout.height * 0.7F);
    setMeasuredDimension(this.standardLayout.width, this.itemLayout.height);
  }

  public void setBackground(boolean paramBoolean)
  {
    this.mHasBackground = paramBoolean;
  }

  public void setTagName(String paramString)
  {
    this.mTitle = paramString;
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.AlarmTagView
 * JD-Core Version:    0.6.2
 */