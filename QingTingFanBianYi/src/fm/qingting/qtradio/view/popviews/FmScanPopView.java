package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class FmScanPopView extends QtView
{
  private final ViewLayout bgLayout = this.standardLayout.createChildLT(540, 300, 90, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout buttonLayout = this.bgLayout.createChildLT(500, 130, 20, 150, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout largeTextLayout = this.bgLayout.createChildLT(500, 45, 20, 40, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private ButtonViewElement mBg;
  private ButtonViewElement mButton;
  private int mCount = 0;
  private TextViewElement mInfo;
  private TextViewElement mTitle;
  private final ViewLayout middleTextLayout = this.bgLayout.createChildLT(500, 45, 20, 90, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout roundLayout = this.bgLayout.createChildLT(8, 8, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public FmScanPopView(Context paramContext)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getPopBgColor(), SkinManager.getPopBgColor());
    this.mBg.setRoundCorner(true);
    addElement(this.mBg);
    this.mTitle = new TextViewElement(paramContext);
    this.mTitle.setMaxLineLimit(1);
    this.mTitle.setColor(SkinManager.getTextColorNormal());
    this.mTitle.setText("正在为您扫描...", false);
    addElement(this.mTitle);
    this.mInfo = new TextViewElement(paramContext);
    this.mInfo.setMaxLineLimit(1);
    this.mInfo.setColor(SkinManager.getTextColorNormal());
    this.mInfo.setText(String.format("已有%d个免流量电台可用", new Object[] { Integer.valueOf(this.mCount) }), false);
    addElement(this.mInfo);
    this.mButton = new ButtonViewElement(paramContext);
    this.mButton.setText("停止扫描，马上收听");
    this.mButton.setRoundCorner(true);
    this.mButton.setBackgroundColor(SkinManager.getPopButtonHighlightColor(), SkinManager.getPopButtonNormalColor());
    this.mButton.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getPopTextColor());
    addElement(this.mButton);
    this.mButton.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        FmScanPopView.this.dispatchActionEvent("scanCancel", null);
      }
    });
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.bgLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.bgLayout);
    this.largeTextLayout.scaleToBounds(this.bgLayout);
    this.middleTextLayout.scaleToBounds(this.bgLayout);
    this.roundLayout.scaleToBounds(this.bgLayout);
    this.mBg.measure(this.bgLayout.leftMargin, (this.standardLayout.height - this.bgLayout.height) / 2, this.bgLayout.getRight(), (this.standardLayout.height + this.bgLayout.height) / 2);
    this.mBg.setRoundCornerRadius(this.roundLayout.width);
    this.mTitle.measure(this.bgLayout.leftMargin + this.largeTextLayout.leftMargin, this.mBg.getTopMargin() + this.largeTextLayout.topMargin, this.bgLayout.leftMargin + this.largeTextLayout.getRight(), this.mBg.getTopMargin() + this.largeTextLayout.getBottom());
    this.mInfo.measure(this.bgLayout.leftMargin + this.middleTextLayout.leftMargin, this.mBg.getTopMargin() + this.middleTextLayout.topMargin, this.bgLayout.leftMargin + this.middleTextLayout.getRight(), this.mBg.getTopMargin() + this.middleTextLayout.getBottom());
    this.mButton.measure(this.bgLayout.leftMargin + this.buttonLayout.leftMargin, this.mBg.getTopMargin() + this.buttonLayout.topMargin, this.bgLayout.leftMargin + this.buttonLayout.getRight(), this.mBg.getTopMargin() + this.buttonLayout.getBottom());
    this.mButton.setRoundCornerRadius(this.roundLayout.width);
    this.mTitle.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mInfo.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mButton.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if ((paramString.equalsIgnoreCase("setBubbleData")) && (paramObject != null))
    {
      this.mCount = ((Integer)paramObject).intValue();
      this.mInfo.setText(String.format("已有%d个免流量电台可用", new Object[] { Integer.valueOf(this.mCount) }), true);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.FmScanPopView
 * JD-Core Version:    0.6.2
 */