package fm.qingting.qtradio.wo;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;

public class WoTextView extends QtView
{
  private TextViewElement mText;
  private int maxLineLimit = 20;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 100, 480, 100, 0, 0, ViewLayout.FILL);

  public WoTextView(Context paramContext)
  {
    super(paramContext);
    this.mText = new TextViewElement(paramContext);
    this.mText.setMaxLineLimit(this.maxLineLimit);
    addElement(this.mText);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.mText.measure(this.standardLayout);
    setMeasuredDimension(this.standardLayout.width, this.mText.getHeight() + 2);
  }

  public void setMaxLineLimit(int paramInt)
  {
    if (this.mText != null)
      this.mText.setMaxLineLimit(paramInt);
  }

  public void setText(String paramString)
  {
    if (this.mText != null)
      this.mText.setText(paramString);
  }

  public void setTextColor(int paramInt)
  {
    if (this.mText != null)
      this.mText.setColor(paramInt);
  }

  public void setTextSize(float paramFloat)
  {
    if (this.mText != null)
      this.mText.setTextSize(paramFloat);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wo.WoTextView
 * JD-Core Version:    0.6.2
 */