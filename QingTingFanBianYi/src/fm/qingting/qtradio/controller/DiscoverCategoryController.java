package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.view.frontpage.DiscoverCategoryView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class DiscoverCategoryController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mBarCustomView;
  private DiscoverCategoryView mainView;

  public DiscoverCategoryController(Context paramContext, CategoryNode paramCategoryNode)
  {
    super(paramContext);
    this.mBarCustomView = new NavigationBarView(paramContext);
    this.mBarCustomView.setLeftItem(0);
    this.mBarCustomView.setRightItem(1);
    this.mBarCustomView.setBarListener(this);
    setNavigationBar(this.mBarCustomView);
    this.mBarCustomView.setTitleItem(new NavigationBarItem(paramCategoryNode.name));
    this.mainView = new DiscoverCategoryView(paramContext, paramCategoryNode);
    attachView(this.mainView);
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
    while (paramInt != 3)
      return;
    ControllerManager.getInstance().redirectToSearchView(false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.DiscoverCategoryController
 * JD-Core Version:    0.6.2
 */