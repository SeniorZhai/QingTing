package fm.qingting.qtradio.view.link;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import fm.qingting.framework.view.ViewElement;

public class LinkArrowElement extends ViewElement
{
  private int mLeft;
  private Paint mPaint = new Paint();
  private Path mTriangularPath = new Path();

  public LinkArrowElement(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mLeft = paramInt;
    this.mPaint.setColor(-1308622848);
    this.mPaint.setStyle(Paint.Style.FILL);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.drawPath(this.mTriangularPath, this.mPaint);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mTriangularPath.rewind();
    paramInt1 = getWidth();
    paramInt2 = getHeight();
    paramInt3 = getBottomMargin();
    this.mTriangularPath.moveTo(this.mLeft - paramInt1 / 2.0F, paramInt3 - paramInt2);
    this.mTriangularPath.lineTo(this.mLeft + paramInt1 / 2.0F, paramInt3 - paramInt2);
    this.mTriangularPath.lineTo(this.mLeft, paramInt3);
    this.mTriangularPath.lineTo(this.mLeft - paramInt1 / 2.0F, paramInt3 - paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.link.LinkArrowElement
 * JD-Core Version:    0.6.2
 */