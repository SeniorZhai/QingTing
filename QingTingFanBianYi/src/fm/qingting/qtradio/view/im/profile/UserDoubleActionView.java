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

public class UserDoubleActionView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mLeftElement;
  private ButtonViewElement mRightElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public UserDoubleActionView(Context paramContext)
  {
    super(paramContext);
    this.mLeftElement = new ButtonViewElement(paramContext);
    this.mLeftElement.setBackgroundColor(SkinManager.getTextColorHighlight(), SkinManager.getBackgroundColor());
    this.mLeftElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getTextColorNormal());
    addElement(this.mLeftElement);
    this.mLeftElement.setText("发送消息");
    this.mLeftElement.setOnElementClickListener(this);
    this.mRightElement = new ButtonViewElement(paramContext);
    this.mRightElement.setBackgroundColor(SkinManager.getGreenColor(), SkinManager.getBackgroundColor());
    this.mRightElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getGreenColor());
    addElement(this.mRightElement);
    this.mRightElement.setText("加入之");
    this.mRightElement.setOnElementClickListener(this);
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.standardLayout.width, 0, this.lineLayout.height);
    SkinManager.getInstance().drawVerticalLine(paramCanvas, 0, this.standardLayout.height, this.standardLayout.width / 2, this.lineLayout.height);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLine(paramCanvas);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mLeftElement)
      dispatchActionEvent("useractionleft", null);
    while (paramViewElement != this.mRightElement)
      return;
    dispatchActionEvent("useractionRight", null);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mLeftElement.measure(0, 0, this.standardLayout.width / 2, this.standardLayout.height);
    this.mRightElement.measure(this.standardLayout.width / 2, 0, this.standardLayout.width, this.standardLayout.height);
    this.mLeftElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mRightElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setDataLeft"))
      this.mLeftElement.setText((String)paramObject);
    while (!paramString.equalsIgnoreCase("setDataRight"))
      return;
    this.mRightElement.setText((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.UserDoubleActionView
 * JD-Core Version:    0.6.2
 */