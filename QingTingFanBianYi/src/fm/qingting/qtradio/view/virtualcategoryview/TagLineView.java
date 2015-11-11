package fm.qingting.qtradio.view.virtualcategoryview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class TagLineView extends ViewImpl
{
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint mLinePaint = new Paint();
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 2, 720, 2, 0, 0, ViewLayout.FILL);

  public TagLineView(Context paramContext)
  {
    super(paramContext);
    this.mLinePaint.setColor(SkinManager.getDividerColor());
  }

  private void drawLine(Canvas paramCanvas)
  {
    float f = this.standardLayout.height - this.lineLayout.height / 2.0F;
    paramCanvas.drawLine(0.0F, f, this.standardLayout.width, f, this.mLinePaint);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawLine(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mLinePaint.setStrokeWidth(this.lineLayout.height);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualcategoryview.TagLineView
 * JD-Core Version:    0.6.2
 */