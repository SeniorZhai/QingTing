package fm.qingting.qtradio.view.moreContentView;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.playview.LineElement;

public class BannerItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout closeLayout = this.itemLayout.createChildLT(20, 20, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 96, 720, 96, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mCloseBtn;
  private ImageViewElement mImgBackground;
  private LineElement mLineElement;

  public BannerItemView(Context paramContext)
  {
    super(paramContext);
    this.mImgBackground = new ImageViewElement(paramContext);
    this.mImgBackground.setImageRes(2130838012);
    addElement(this.mImgBackground);
    this.mImgBackground.setOnElementClickListener(this);
    this.mCloseBtn = new ButtonViewElement(paramContext);
    this.mCloseBtn.setBackground(0, 2130838013);
    this.mCloseBtn.expandHotPot(40);
    addElement(this.mCloseBtn);
    this.mCloseBtn.setOnElementClickListener(this);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mCloseBtn)
      InfoManager.getInstance().root().setInfoUpdate(11);
    while (paramViewElement != this.mImgBackground)
      return;
    ControllerManager.getInstance().redirectToActiviyByUrl("http://sss.qingting.fm/pugc/introduction/", "主播入驻", true);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.closeLayout.scaleToBounds(this.itemLayout);
    this.mImgBackground.measure(0, 0, this.itemLayout.getRight(), this.itemLayout.getBottom());
    paramInt1 = (this.itemLayout.height - this.closeLayout.height) / 2;
    paramInt2 = this.itemLayout.width - this.closeLayout.getLeft() - this.closeLayout.width;
    this.mCloseBtn.measure(paramInt2, paramInt1, this.closeLayout.width + paramInt2, this.closeLayout.height + paramInt1);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.lineLayout.topMargin, this.lineLayout.getRight(), this.lineLayout.getBottom());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.moreContentView.BannerItemView
 * JD-Core Version:    0.6.2
 */