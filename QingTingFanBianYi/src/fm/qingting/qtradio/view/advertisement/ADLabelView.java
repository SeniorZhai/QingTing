package fm.qingting.qtradio.view.advertisement;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ADLabelView extends ViewImpl
{
  private final ViewLayout bgLayout = ViewLayout.createViewLayoutWithBoundsLT(400, 60, 480, 800, 10, 300, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private final int grayBgColor = -297450171;
  private Paint grayBgPaint = new Paint();
  private boolean inRect = false;
  private String info = "jdAD";
  private final ViewLayout largeTextLayout = this.bgLayout.createChildLT(380, 30, 30, 30, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private Paint largeTextPaint = new Paint();
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public ADLabelView(Context paramContext)
  {
    super(paramContext);
    this.grayBgPaint.setColor(-297450171);
    this.grayBgPaint.setStyle(Paint.Style.FILL);
    this.largeTextPaint.setColor(-1);
  }

  private void drawInfo(Canvas paramCanvas)
  {
    Rect localRect = new Rect();
    this.largeTextPaint.getTextBounds(this.info, 0, this.info.length(), localRect);
    float f1 = localRect.bottom - localRect.top;
    float f3 = localRect.right - localRect.left;
    float f2 = this.bgLayout.getLeft() + this.largeTextLayout.leftMargin;
    f3 = (this.bgLayout.width - f3) / 2.0F;
    float f4 = this.bgLayout.getTop();
    float f5 = this.bgLayout.height;
    paramCanvas.drawText(this.info, f3 + f2, f5 - f1 + f4, this.largeTextPaint);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
      return true;
    case 0:
      this.inRect = true;
      return true;
    case 3:
      this.inRect = false;
      return true;
    case 2:
      int i = (int)paramMotionEvent.getX();
      int j = (int)paramMotionEvent.getY();
      paramMotionEvent = this.bgLayout.getRect();
      getLocalVisibleRect(paramMotionEvent);
      this.inRect = paramMotionEvent.contains(i, j);
      return true;
    case 4:
      this.inRect = false;
      return true;
    case 1:
    }
    setPressed(false);
    if (this.inRect)
      dispatchActionEvent("cancelPop", null);
    this.inRect = false;
    return true;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.filter);
    float f1 = (this.standardLayout.width - this.bgLayout.width) / 2.0F;
    float f2 = this.bgLayout.topMargin;
    paramCanvas.drawRoundRect(new RectF(f1, f2, this.bgLayout.width + f1, this.bgLayout.height + f2), this.bgLayout.leftMargin, this.bgLayout.leftMargin, this.grayBgPaint);
    drawInfo(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.bgLayout.scaleToBounds(this.standardLayout);
    this.largeTextLayout.scaleToBounds(this.bgLayout);
    this.largeTextPaint.setTextSize(this.bgLayout.width * 0.06F);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if ((!paramString.equalsIgnoreCase("setBubbleData")) || (paramObject != null));
    try
    {
      this.info = ((String)paramObject);
      invalidate();
      return;
    }
    catch (Exception paramString)
    {
      while (true)
        this.info = "Prompt information";
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.advertisement.ADLabelView
 * JD-Core Version:    0.6.2
 */