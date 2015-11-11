package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;

public class ScheduleHeadView extends QtView
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(100, 50, 600, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 97, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mButtonViewElement;
  private LineElement mLineElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 98, 720, 98, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(500, 60, 110, 19, ViewLayout.SCALE_FLAG_SLTCW);

  public ScheduleHeadView(Context paramContext)
  {
    super(paramContext);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setColor(-1);
    this.mTitleElement.setText("播放列表");
    addElement(this.mTitleElement);
    this.mButtonViewElement = new ButtonViewElement(paramContext);
    this.mButtonViewElement.setTextColor(SkinManager.getTextColorHighlight(), -1);
    this.mButtonViewElement.setText("关闭");
    addElement(this.mButtonViewElement);
    this.mButtonViewElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        ControllerManager.getInstance().popLastController();
      }
    });
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(872415231);
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mButtonViewElement.measure(this.buttonLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mButtonViewElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mLineElement.measure(this.lineLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      if (paramObject == null)
        this.mTitleElement.setText("播放列表");
    }
    else
      return;
    this.mTitleElement.setText((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.ScheduleHeadView
 * JD-Core Version:    0.6.2
 */