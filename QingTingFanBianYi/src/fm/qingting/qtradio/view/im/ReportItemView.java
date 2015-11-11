package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.AbsCheckBoxElement.OnCheckChangeListener;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.qtradio.view.settingviews.SingleCheckBoxElement;

public class ReportItemView extends QtView
{
  private final ViewLayout checkLayout = this.itemLayout.createChildLT(48, 48, 622, 32, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 112, 720, 112, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 111, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private SingleCheckBoxElement mCheck;
  private LineElement mLineElement;
  private TextViewElement mName;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(600, 50, 30, 31, ViewLayout.SCALE_FLAG_SLTCW);

  public ReportItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
    addElement(this.mBg, paramInt);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        ReportItemView.this.handleClickEvent();
      }
    });
    this.mName = new TextViewElement(paramContext);
    this.mName.setMaxLineLimit(1);
    this.mName.setColor(SkinManager.getTextColorNormal());
    this.mName.setAlignment(Layout.Alignment.ALIGN_NORMAL);
    addElement(this.mName);
    this.mCheck = new SingleCheckBoxElement(paramContext);
    addElement(this.mCheck, paramInt);
    this.mCheck.setEnabled(false);
    this.mCheck.setOnCheckChangeListener(new AbsCheckBoxElement.OnCheckChangeListener()
    {
      public void onCheckChanged(boolean paramAnonymousBoolean)
      {
        ReportItemView.this.handleClickEvent();
      }
    });
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  private void handleClickEvent()
  {
    if (this.mCheck.isChecked())
      return;
    dispatchActionEvent("check", null);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.checkLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.checkLayout.topMargin = ((this.itemLayout.height - this.checkLayout.height) / 2);
    this.mBg.measure(this.itemLayout);
    this.mName.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mName.measure(this.nameLayout);
    this.mCheck.measure(this.checkLayout);
    this.mLineElement.measure(this.lineLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (String)paramObject;
      this.mName.setText(paramString, true);
    }
    while (!paramString.equalsIgnoreCase("checkState"))
      return;
    if (((Boolean)paramObject).booleanValue())
      this.mCheck.check(false);
    while (true)
    {
      invalidate();
      return;
      this.mCheck.uncheck(false);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ReportItemView
 * JD-Core Version:    0.6.2
 */