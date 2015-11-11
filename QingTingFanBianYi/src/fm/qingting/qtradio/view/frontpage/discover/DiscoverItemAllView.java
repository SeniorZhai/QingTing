package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.IconManage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.utils.QTMSGManage;

public class DiscoverItemAllView extends QtView
  implements ViewElement.OnElementClickListener
{
  private static final int GOOD_VOICE = 3596;
  private static final String STATISTIC_ALL = "v6_category_all_click";
  private static final String STATISTIC_RANK = "v6_category_rank_click";
  private final int SEPARATOR = 361;
  private final ViewLayout allIconLayout = this.itemLayout.createChildLT(68, 68, 75, 26, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout allLayout = this.itemLayout.createChildLT(359, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout allTitleLayout = this.itemLayout.createChildLT(222, 50, 163, 35, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 120, 720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBgAll;
  private ButtonViewElement mBgRank;
  private ImageViewElement mIconAll;
  private ImageViewElement mIconRank;
  private CategoryNode mNode;
  private TextViewElement mTitleAll;
  private TextViewElement mTitleRank;
  private final int offset = 45;
  private final ViewLayout rankIconLayout = this.itemLayout.createChildLT(68, 68, 436, 26, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout rankLayout = this.itemLayout.createChildLT(359, 120, 361, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout rankTitleLayout = this.itemLayout.createChildLT(359, 50, 524, 35, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverItemAllView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBgAll = new ButtonViewElement(paramContext);
    this.mBgAll.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBgAll);
    this.mBgAll.setOnElementClickListener(this);
    this.mIconAll = new ImageViewElement(paramContext);
    addElement(this.mIconAll, paramInt);
    this.mTitleAll = new TextViewElement(paramContext);
    this.mTitleAll.setColor(SkinManager.getTextColorNormal());
    this.mTitleAll.setMaxLineLimit(1);
    addElement(this.mTitleAll);
    this.mBgRank = new ButtonViewElement(paramContext);
    this.mBgRank.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBgRank);
    this.mBgRank.setOnElementClickListener(this);
    this.mIconRank = new ImageViewElement(paramContext);
    addElement(this.mIconRank, paramInt);
    this.mTitleRank = new TextViewElement(paramContext);
    this.mTitleRank.setColor(SkinManager.getTextColorNormal());
    this.mTitleRank.setMaxLineLimit(1);
    addElement(this.mTitleRank);
  }

  private void gotoAll()
  {
    if (this.mNode != null)
      QTMSGManage.getInstance().sendStatistcsMessage("v6_category_all_click", this.mNode.name);
    PlayerAgent.getInstance().addPlaySource(26);
    ControllerManager.getInstance().setChannelSource(0);
    if (this.mNode.isNovel())
    {
      ControllerManager.getInstance().openNovelAllContentController(this.mNode);
      return;
    }
    ControllerManager.getInstance().openVirtualCategoryAllContentController(this.mNode);
  }

  private void gotoRank()
  {
    if (this.mNode != null)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("v6_category_rank_click", this.mNode.name);
      String str = "http://wx.qingting.fm/billboard/cat_retain/" + this.mNode.categoryId + "?phonetype=android";
      if (this.mNode.categoryId == 3596)
        str = "http://wx.qingting.fm/billboard/pugc/";
      ControllerManager.getInstance().redirectToActiviyByUrl(str, "排行榜", true);
      PlayerAgent.getInstance().addPlaySource(45);
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mBgAll)
      gotoAll();
    while (paramViewElement != this.mBgRank)
      return;
    gotoRank();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.allLayout.scaleToBounds(this.itemLayout);
    this.allIconLayout.scaleToBounds(this.itemLayout);
    this.allTitleLayout.scaleToBounds(this.itemLayout);
    this.rankLayout.scaleToBounds(this.itemLayout);
    this.rankIconLayout.scaleToBounds(this.itemLayout);
    this.rankTitleLayout.scaleToBounds(this.itemLayout);
    this.mBgAll.measure(this.allLayout);
    this.mIconAll.measure(this.allIconLayout);
    this.mTitleAll.measure(this.allTitleLayout);
    this.mTitleAll.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mBgRank.measure(this.rankLayout);
    this.mIconRank.measure(this.rankIconLayout);
    this.mTitleRank.measure(this.rankTitleLayout);
    this.mTitleRank.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((CategoryNode)paramObject);
      this.mTitleAll.setText(CategoryNameWrapper.wrapNameAll(this.mNode), false);
      this.mIconAll.setImageRes(IconManage.getNormalRes(this.mNode.sectionId));
      this.mTitleRank.setText(CategoryNameWrapper.wrapNameRank(this.mNode), false);
      this.mIconRank.setImageRes(2130837798);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverItemAllView
 * JD-Core Version:    0.6.2
 */