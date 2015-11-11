package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ReportConfirmView extends QtView
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(720, 100, 0, 25, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mButtonViewElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 150, 720, 150, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ReportConfirmView(Context paramContext)
  {
    super(paramContext);
    this.mButtonViewElement = new ButtonViewElement(paramContext);
    this.mButtonViewElement.setBackgroundColor(SkinManager.getBackgroundColor(), -1);
    this.mButtonViewElement.setTextColor(SkinManager.getTextColorNormal(), SkinManager.getTextColorHighlight());
    this.mButtonViewElement.setText("确认举报");
    this.mButtonViewElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        ReportConfirmView.this.dispatchActionEvent("report", null);
      }
    });
    addElement(this.mButtonViewElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.mButtonViewElement.measure(this.buttonLayout);
    this.mButtonViewElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportConfirmView
 * JD-Core Version:    0.6.2
 */