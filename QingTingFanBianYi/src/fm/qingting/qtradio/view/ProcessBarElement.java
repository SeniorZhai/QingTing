package fm.qingting.qtradio.view;

import android.content.Context;
import android.graphics.Canvas;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class ProcessBarElement extends ViewElement
{
  private float mProcess = 0.0F;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 4, 720, 4, 0, 0, ViewLayout.FILL);

  public ProcessBarElement(Context paramContext)
  {
    super(paramContext);
  }

  protected void drawProgressBg(Canvas paramCanvas)
  {
    int i = (int)(this.mProcess * this.standardLayout.width);
    int j = paramCanvas.save();
    paramCanvas.clipRect(i, getTopMargin(), this.standardLayout.width, getBottomMargin());
    paramCanvas.drawColor(-10461088);
    paramCanvas.restoreToCount(j);
    j = paramCanvas.save();
    paramCanvas.clipRect(0, getTopMargin(), i, getBottomMargin());
    paramCanvas.drawColor(SkinManager.getTextColorHighlight());
    paramCanvas.restoreToCount(j);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    drawProgressBg(paramCanvas);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.standardLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  void setProcess(float paramFloat)
  {
    this.mProcess = paramFloat;
    invalidateElement();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.ProcessBarElement
 * JD-Core Version:    0.6.2
 */