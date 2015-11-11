package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.virtualcategoryview.VirtualCategoryAllContentView;
import java.util.HashMap;

public class VirtualCategoryAllContentController extends ViewController
  implements INavigationBarListener
{
  public static final String NAME = "vcacc";
  private final String CANCEL = "取消";
  private final String FILTER = "筛选";
  private boolean mInFilter = false;
  private CategoryNode mNode;
  private NavigationBarView mTopView;
  private VirtualCategoryAllContentView mainView;

  public VirtualCategoryAllContentController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "vcacc";
    this.mTopView = new NavigationBarView(paramContext);
    this.mTopView.setLeftItem(0);
    this.mTopView.setRightItem("筛选");
    this.mTopView.setBarListener(this);
    setNavigationBar(this.mTopView);
    this.mainView = new VirtualCategoryAllContentView(paramContext);
    attachView(this.mainView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setNode"))
    {
      this.mNode = ((CategoryNode)paramObject);
      this.mTopView.setTitleItem(new NavigationBarItem(this.mNode.name));
      this.mainView.update(paramString, paramObject);
    }
    do
    {
      do
      {
        return;
        if (!paramString.equalsIgnoreCase("resetFilterState"))
          break;
      }
      while (!this.mInFilter);
      this.mInFilter = false;
      this.mTopView.setRightItem("筛选");
      return;
      if (paramString.equalsIgnoreCase("resetFilter"))
      {
        this.mainView.update(paramString, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("setData"))
      {
        this.mainView.update(paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setId"));
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public boolean hasMiniPlayer()
  {
    return true;
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      EventDispacthManager.getInstance().dispatchAction("hideCategoryFilterIfExist", null);
      ControllerManager.getInstance().popLastController();
      return;
    case 3:
    }
    if (this.mInFilter)
    {
      this.mInFilter = false;
      this.mTopView.setRightItem("筛选");
    }
    while (true)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("node", this.mNode);
      localHashMap.put("attributes", this.mainView.getValue("getAttributes", null));
      EventDispacthManager.getInstance().dispatchAction("toggleCategoryFilter", localHashMap);
      return;
      this.mInFilter = true;
      this.mTopView.setRightItem("取消");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.VirtualCategoryAllContentController
 * JD-Core Version:    0.6.2
 */