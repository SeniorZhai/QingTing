package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.PlayProcessSyncUtil;
import fm.qingting.utils.ScreenConfiguration;
import java.util.Locale;

public class AccurateSeekView extends QtView
  implements ViewElement.OnElementClickListener
{
  private static final String HOURFORMAT = "%02d:%02d:%02d";
  private final ViewLayout actionLayout = this.standardLayout.createChildLT(276, 140, 222, 40, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout iconLayout = this.actionLayout.createChildLT(56, 58, 41, 41, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.actionLayout.createChildLT(1, 100, 138, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mActionBg;
  private ButtonViewElement mBackwardElement;
  private ButtonViewElement mForwardElement;
  private TextViewElement mLeftTimeElement;
  private int mLeftTimeOffset = 0;
  private LineElement mLineElement;
  private TextViewElement mRightTimeElement;
  private ButtonViewElement mTimeBg;
  private final ViewLayout roundLayout = this.standardLayout.createChildLT(8, 8, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout timeLayout = this.standardLayout.createChildLT(440, 100, 140, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public AccurateSeekView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    setBackgroundColor(-1728053248);
    this.mTimeBg = new ButtonViewElement(paramContext);
    this.mTimeBg.setBackgroundColor(-1728053248, -1728053248);
    this.mTimeBg.setRoundCorner(true);
    addElement(this.mTimeBg);
    this.mLeftTimeElement = new TextViewElement(paramContext);
    this.mLeftTimeElement.setColor(SkinManager.getTextColorHighlight());
    this.mLeftTimeElement.setAlignment(Layout.Alignment.ALIGN_OPPOSITE);
    this.mLeftTimeElement.setMaxLineLimit(1);
    addElement(this.mLeftTimeElement);
    this.mRightTimeElement = new TextViewElement(paramContext);
    this.mRightTimeElement.setColor(-1);
    this.mRightTimeElement.setMaxLineLimit(1);
    addElement(this.mRightTimeElement);
    this.mActionBg = new ButtonViewElement(paramContext);
    this.mActionBg.setRoundCorner(true);
    this.mActionBg.setBackgroundColor(-1, -1);
    addElement(this.mActionBg);
    this.mBackwardElement = new ButtonViewElement(paramContext);
    this.mBackwardElement.setBackground(2130837762, 2130837761);
    addElement(this.mBackwardElement, i);
    this.mBackwardElement.expandHotPot(ScreenConfiguration.getCustomExtraBound());
    this.mBackwardElement.setOnElementClickListener(this);
    this.mForwardElement = new ButtonViewElement(paramContext);
    this.mForwardElement.setBackground(2130837776, 2130837775);
    addElement(this.mForwardElement, i);
    this.mForwardElement.expandHotPot(ScreenConfiguration.getCustomExtraBound());
    this.mForwardElement.setOnElementClickListener(this);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(0);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  private String durationToStr(int paramInt)
  {
    int i = paramInt / 3600;
    int j = paramInt / 60;
    if (i < 0)
      return "";
    return String.format(Locale.US, "%02d:%02d:%02d", new Object[] { Integer.valueOf(i), Integer.valueOf(j % 60), Integer.valueOf(paramInt % 60) });
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    dispatchActionEvent("extendDismissLength", null);
    if (paramViewElement == this.mBackwardElement)
      PlayProcessSyncUtil.getInstance().backwardThirtySeconds();
    while (paramViewElement != this.mForwardElement)
      return;
    PlayProcessSyncUtil.getInstance().forwardThirtySeconds();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.timeLayout.scaleToBounds(this.standardLayout);
    this.actionLayout.scaleToBounds(this.standardLayout);
    this.iconLayout.scaleToBounds(this.actionLayout);
    this.roundLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.actionLayout);
    this.mActionBg.measure(this.actionLayout.leftMargin, this.standardLayout.height - this.actionLayout.getBottom(), this.actionLayout.getRight(), this.standardLayout.height - this.actionLayout.topMargin);
    int j = this.actionLayout.leftMargin;
    int i = this.mActionBg.getTopMargin();
    this.mBackwardElement.measure(this.iconLayout.leftMargin + j, this.iconLayout.topMargin + i, this.iconLayout.getRight() + j, this.iconLayout.getBottom() + i);
    this.mLineElement.measure(this.lineLayout.leftMargin + j, this.lineLayout.topMargin + i, j + this.lineLayout.getRight(), this.lineLayout.getBottom() + i);
    j = this.standardLayout.width / 2;
    this.mForwardElement.measure(this.iconLayout.leftMargin + j, this.iconLayout.topMargin + i, j + this.iconLayout.getRight(), i + this.iconLayout.getBottom());
    i = (this.standardLayout.height - this.timeLayout.height) / 2;
    this.mTimeBg.measure(this.timeLayout.leftMargin, i, this.timeLayout.getRight(), this.timeLayout.height + i);
    this.mLeftTimeElement.measure(this.timeLayout.leftMargin, i, this.standardLayout.width / 2 - this.roundLayout.width, this.timeLayout.height + i);
    this.mRightTimeElement.measure(this.standardLayout.width / 2, i, this.timeLayout.getRight(), this.timeLayout.height + i);
    this.mLeftTimeElement.setTextSize(SkinManager.getInstance().getLargeTextSize());
    this.mRightTimeElement.setTextSize(SkinManager.getInstance().getLargeTextSize());
    this.mTimeBg.setRoundCornerRadius(this.roundLayout.width);
    this.mActionBg.setRoundCornerRadius(this.roundLayout.width);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("progresschanged"))
    {
      i = ((Integer)paramObject).intValue();
      this.mLeftTimeElement.setText(durationToStr(i + this.mLeftTimeOffset));
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("leftTimeOffset"))
      {
        this.mLeftTimeOffset = ((Integer)paramObject).intValue();
        return;
      }
      if (paramString.equalsIgnoreCase("rightTime"))
      {
        i = ((Integer)paramObject).intValue();
        this.mRightTimeElement.setText("/" + durationToStr(i));
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("progress"));
    int i = ((Integer)paramObject).intValue();
    this.mLeftTimeElement.setText(durationToStr(i + this.mLeftTimeOffset));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.AccurateSeekView
 * JD-Core Version:    0.6.2
 */