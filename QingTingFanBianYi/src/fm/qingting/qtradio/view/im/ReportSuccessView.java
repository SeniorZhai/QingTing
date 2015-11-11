package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ReportSuccessView extends QtView
{
  private final String DETAIL = "感谢您的帮助，工作人员将尽快处理！\n蜻蜓因为有您而变得更好^_^";
  private final String SUCCESS = "举报成功！";
  private final ViewLayout detailLayout = this.standardLayout.createChildLT(720, 45, 0, 370, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout logoLayout = this.standardLayout.createChildLT(156, 156, 282, 100, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mDetailElement;
  private ImageViewElement mLogoElement;
  private TextViewElement mSuccessElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout successLayout = this.standardLayout.createChildLT(700, 45, 10, 300, ViewLayout.SCALE_FLAG_SLTCW);

  public ReportSuccessView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mLogoElement = new ImageViewElement(paramContext);
    this.mLogoElement.setImageRes(2130837799);
    addElement(this.mLogoElement);
    this.mSuccessElement = new TextViewElement(paramContext);
    this.mSuccessElement.setMaxLineLimit(1);
    this.mSuccessElement.setColor(-12998087);
    this.mSuccessElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mSuccessElement.setText("举报成功！", false);
    addElement(this.mSuccessElement);
    this.mDetailElement = new TextViewElement(paramContext);
    this.mDetailElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mDetailElement.setColor(SkinManager.getTextColorNormal());
    this.mDetailElement.setText("感谢您的帮助，工作人员将尽快处理！\n蜻蜓因为有您而变得更好^_^", false);
    addElement(this.mDetailElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.logoLayout.scaleToBounds(this.standardLayout);
    this.successLayout.scaleToBounds(this.standardLayout);
    this.detailLayout.scaleToBounds(this.standardLayout);
    this.mLogoElement.measure(this.logoLayout);
    this.mSuccessElement.measure(this.successLayout);
    this.mDetailElement.measure(this.detailLayout);
    this.mSuccessElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mDetailElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
    super.onMeasure(paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportSuccessView
 * JD-Core Version:    0.6.2
 */