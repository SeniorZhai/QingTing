package fm.qingting.qtradio.view.personalcenter.clock.djringtone;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class DjRingtoneFooterView extends ViewImpl
  implements View.OnClickListener
{
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 622, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private boolean checked = true;
  private int hashCode = -15;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 150, 720, 800, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint mCheckBgPaint = new Paint();
  private Rect mCheckBgRect = new Rect();
  private Paint mCheckStatePaint = new Paint();
  private Paint mPaint = new Paint();
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(500, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect textBound = new Rect();

  public DjRingtoneFooterView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hashCode = paramInt;
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    setOnClickListener(this);
  }

  private void drawCheckState(Canvas paramCanvas)
  {
    if (this.checked)
    {
      paramCanvas.drawCircle(this.mCheckBgRect.centerX(), this.mCheckBgRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hashCode, 2130837754), null, this.mCheckBgRect, this.mPaint);
      return;
    }
    paramCanvas.drawCircle(this.mCheckBgRect.centerX(), this.mCheckBgRect.centerY(), this.checkbgLayout.width / 2, this.mCheckBgPaint);
  }

  private void drawSilent(Canvas paramCanvas)
  {
    if (this.checked);
    for (TextPaint localTextPaint = SkinManager.getInstance().getHighlightTextPaint(); ; localTextPaint = SkinManager.getInstance().getNormalTextPaint())
    {
      localTextPaint.getTextBounds("直接播放电台", 0, "直接播放电台".length(), this.textBound);
      float f = (this.itemLayout.height - this.textBound.top - this.textBound.bottom) / 2;
      paramCanvas.drawText("直接播放电台", this.nameLayout.leftMargin, f, localTextPaint);
      return;
    }
  }

  public void onClick(View paramView)
  {
    if (!this.checked)
      dispatchActionEvent("uncheckAll", Integer.valueOf(-1));
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    drawSilent(paramCanvas);
    drawCheckState(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.itemLayout.scaleToBounds(paramInt1, paramInt2);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
    this.mCheckBgRect.set(this.checkbgLayout.leftMargin + (this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, this.checkbgLayout.leftMargin + (this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("uncheck"))
    {
      this.checked = false;
      invalidate();
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("check"))
      {
        this.checked = true;
        invalidate();
        return;
      }
      if (paramString.equalsIgnoreCase("setData"))
      {
        invalidate();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setChecked"));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.djringtone.DjRingtoneFooterView
 * JD-Core Version:    0.6.2
 */