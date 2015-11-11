package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import fm.qingting.framework.view.ViewElement;

public class LineElement extends ViewElement
{
  public static final int HORIZONAL = 1;
  public static final int VERTICAL = 0;
  private final Paint mLinePaint = new Paint();
  private int mOrientation = 1;

  public LineElement(Context paramContext)
  {
    super(paramContext);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    float f4 = 0.0F;
    float f3;
    float f2;
    float f1;
    switch (this.mOrientation)
    {
    default:
      f3 = 0.0F;
      f2 = 0.0F;
      f1 = 0.0F;
    case 1:
    case 0:
    }
    while (true)
    {
      paramCanvas.drawLine(f1, f2, f3, f4, this.mLinePaint);
      return;
      f1 = getLeftMargin();
      f4 = getTopMargin() + getHeight() / 2.0F;
      f3 = getRightMargin();
      f2 = f4;
      continue;
      f3 = getLeftMargin() + getWidth() / 2.0F;
      f2 = getTopMargin();
      f4 = getBottomMargin();
      f1 = f3;
    }
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    switch (this.mOrientation)
    {
    default:
      return;
    case 1:
      this.mLinePaint.setStrokeWidth(paramInt4 - paramInt2);
      return;
    case 0:
    }
    this.mLinePaint.setStrokeWidth(paramInt3 - paramInt1);
  }

  public void setColor(int paramInt)
  {
    this.mLinePaint.setColor(paramInt);
  }

  public void setOrientation(int paramInt)
  {
    this.mOrientation = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.LineElement
 * JD-Core Version:    0.6.2
 */