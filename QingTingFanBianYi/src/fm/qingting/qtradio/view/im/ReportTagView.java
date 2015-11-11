package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ReportTagView extends QtView
{
  private TextViewElement mTextViewElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 88, 720, 88, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(600, 50, 30, 19, ViewLayout.SCALE_FLAG_SLTCW);

  public ReportTagView(Context paramContext)
  {
    super(paramContext);
    this.mTextViewElement = new TextViewElement(paramContext);
    this.mTextViewElement.setText("举报内容");
    this.mTextViewElement.setColor(SkinManager.getTextColorNormal());
    this.mTextViewElement.setMaxLineLimit(1);
    addElement(this.mTextViewElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.standardLayout);
    this.mTextViewElement.measure(this.textLayout);
    this.mTextViewElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportTagView
 * JD-Core Version:    0.6.2
 */