package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.utils.ScreenConfiguration;

class SearchNaviBgView extends QtView
  implements ViewElement.OnElementClickListener
{
  public static final int TYPE_TEXT = 1;
  public static final int TYPE_VOICE = 2;
  private final ViewLayout backLayout = this.standardLayout.createChildLT(80, 80, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout deleteLayout = this.standardLayout.createChildLT(64, 64, 556, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout inputLayout = this.standardLayout.createChildLT(540, 64, 80, 6, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBack;
  private ButtonViewElement mDeleteButtonViewElement;
  private ButtonViewElement mInputElement;
  private ButtonViewElement mSearchButtonViewElement;
  private int mType = 2;
  private ButtonViewElement mVoiceButtonViewElement;
  private ImageViewElement mZoomElement;
  private final ViewLayout searchLayout = this.standardLayout.createChildLT(100, 60, 620, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 114, 720, 114, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout voiceLayout = this.standardLayout.createChildLT(24, 36, 570, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout zoomLayout = this.standardLayout.createChildLT(36, 36, 100, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public SearchNaviBgView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mBack = new ButtonViewElement(paramContext);
    this.mBack.setBackground(2130837929, 2130837928);
    addElement(this.mBack, i);
    this.mBack.setOnElementClickListener(this);
    this.mInputElement = new ButtonViewElement(paramContext);
    this.mInputElement.setBackgroundColor(-1841946, -1841946);
    this.mInputElement.setRoundCorner(true);
    addElement(this.mInputElement);
    this.mZoomElement = new ImageViewElement(paramContext);
    this.mZoomElement.setImageRes(2130837937);
    addElement(this.mZoomElement, i);
    this.mDeleteButtonViewElement = new ButtonViewElement(paramContext);
    this.mDeleteButtonViewElement.setBackground(2130837931, 2130837931);
    this.mDeleteButtonViewElement.expandHotPot(ScreenConfiguration.getCustomExtraBound());
    addElement(this.mDeleteButtonViewElement, i);
    this.mDeleteButtonViewElement.setOnElementClickListener(this);
    this.mVoiceButtonViewElement = new ButtonViewElement(paramContext);
    this.mVoiceButtonViewElement.setBackground(2130838047, 2130838047);
    this.mVoiceButtonViewElement.expandHotPot(ScreenConfiguration.getCustomExtraBound());
    addElement(this.mVoiceButtonViewElement, i);
    this.mVoiceButtonViewElement.setOnElementClickListener(this);
    this.mSearchButtonViewElement = new ButtonViewElement(paramContext);
    this.mSearchButtonViewElement.setTextColor(-772816, -1);
    this.mSearchButtonViewElement.setText("搜索");
    addElement(this.mSearchButtonViewElement);
    this.mSearchButtonViewElement.setOnElementClickListener(this);
    setResource();
  }

  private void setResource()
  {
    switch (this.mType)
    {
    default:
      return;
    case 1:
      this.mDeleteButtonViewElement.setVisible(0);
      this.mVoiceButtonViewElement.setVisible(4);
      return;
    case 2:
    }
    this.mDeleteButtonViewElement.setVisible(4);
    this.mVoiceButtonViewElement.setVisible(0);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    InfoManager.getInstance().root().mSearchNode.setVoiceSearch(false);
    if (paramViewElement == this.mBack)
      dispatchActionEvent("popcontroller", null);
    do
    {
      return;
      if (paramViewElement == this.mDeleteButtonViewElement)
      {
        dispatchActionEvent("deleteText", null);
        return;
      }
      if (paramViewElement == this.mSearchButtonViewElement)
      {
        dispatchActionEvent("search", null);
        return;
      }
    }
    while (paramViewElement != this.mVoiceButtonViewElement);
    dispatchActionEvent("voiceSearch", null);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.backLayout.scaleToBounds(this.standardLayout);
    this.inputLayout.scaleToBounds(this.standardLayout);
    this.zoomLayout.scaleToBounds(this.standardLayout);
    this.deleteLayout.scaleToBounds(this.standardLayout);
    this.voiceLayout.scaleToBounds(this.standardLayout);
    this.searchLayout.scaleToBounds(this.standardLayout);
    this.mBack.measure(this.backLayout.leftMargin, (this.standardLayout.height - this.backLayout.height) / 2, this.backLayout.getRight(), (this.standardLayout.height + this.backLayout.height) / 2);
    this.mInputElement.measure(this.inputLayout.leftMargin, (this.standardLayout.height - this.inputLayout.height) / 2, this.inputLayout.getRight(), (this.standardLayout.height + this.inputLayout.height) / 2);
    this.mZoomElement.measure(this.zoomLayout.leftMargin, (this.standardLayout.height - this.zoomLayout.height) / 2, this.zoomLayout.getRight(), (this.standardLayout.height + this.zoomLayout.height) / 2);
    this.mDeleteButtonViewElement.measure(this.deleteLayout.leftMargin, (this.standardLayout.height - this.deleteLayout.height) / 2, this.deleteLayout.getRight(), (this.standardLayout.height + this.deleteLayout.height) / 2);
    this.mVoiceButtonViewElement.measure(this.voiceLayout.leftMargin, (this.standardLayout.height - this.voiceLayout.height) / 2, this.voiceLayout.getRight(), (this.standardLayout.height + this.voiceLayout.height) / 2);
    this.mSearchButtonViewElement.measure(this.searchLayout.leftMargin, (this.standardLayout.height - this.searchLayout.height) / 2, this.searchLayout.getRight(), (this.standardLayout.height + this.searchLayout.height) / 2);
    this.mInputElement.setRoundCornerRadius(this.inputLayout.topMargin);
    this.mSearchButtonViewElement.setTextSize(this.searchLayout.height * 0.5F);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void setType(int paramInt)
  {
    if (this.mType != paramInt)
    {
      this.mType = paramInt;
      setResource();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchNaviBgView
 * JD-Core Version:    0.6.2
 */