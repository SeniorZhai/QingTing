package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.VirtualNode;
import fm.qingting.qtradio.view.categoryorder.CategoryOrderManageView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.popviews.CategoryResortPopView.CategoryResortInfo;
import java.util.List;

public class CategoryOrderManageController extends ViewController
{
  private NavigationBarView mTopView;
  private CategoryOrderManageView mView;

  public CategoryOrderManageController(Context paramContext)
  {
    super(paramContext);
    this.mTopView = new NavigationBarView(paramContext);
    this.mTopView.setLeftItem(0);
    this.mTopView.setRightItem("管理");
    this.mTopView.setTitleItem(new NavigationBarItem("全部分类"));
    setNavigationBar(this.mTopView);
    this.mView = new CategoryOrderManageView(paramContext, InfoManager.getInstance().root().mContentCategory.mVirtualNode.getLstCategoryNodes().subList(0, 10));
    attachView(this.mView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = new CategoryResortPopView.CategoryResortInfo(1, InfoManager.getInstance().root().mContentCategory.mVirtualNode.getLstCategoryNodes().size());
      this.mView.update("setData", paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.CategoryOrderManageController
 * JD-Core Version:    0.6.2
 */