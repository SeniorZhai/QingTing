package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class UserActionView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mLeftElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public UserActionView(Context paramContext)
  {
    super(paramContext);
    this.mLeftElement = new ButtonViewElement(paramContext);
    this.mLeftElement.setBackgroundColor(SkinManager.getGreenColor(), SkinManager.getBackgroundColor());
    this.mLeftElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getGreenColor());
    addElement(this.mLeftElement);
    this.mLeftElement.setOnElementClickListener(this);
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.standardLayout.width, 0, this.lineLayout.height);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLine(paramCanvas);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    dispatchActionEvent("useraction", null);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mLeftElement.measure(this.standardLayout);
    this.mLeftElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mLeftElement.setText((String)paramObject);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.UserActionView
 * JD-Core Version:    0.6.2
 */