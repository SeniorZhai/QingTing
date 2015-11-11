package fm.qingting.qtradio.controller.virtual;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.mycollection.MyCollectionView;
import java.util.List;

public class MyCollectionController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView barTopView;
  private boolean mManage = false;
  private MyCollectionView mainView;

  public MyCollectionController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "mycollection";
    this.mainView = new MyCollectionView(paramContext);
    attachView(this.mainView);
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setTitleItem(new NavigationBarItem("我的收藏"));
    this.barTopView.setRightItem("编辑");
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
  }

  private void setData()
  {
    InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.sortCollectionNodeByPlayOrder();
    List localList = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes();
    if ((localList == null) || (localList.size() == 0))
      this.barTopView.setRightItemVisibility(4);
    while (true)
    {
      this.mainView.update("setData", localList);
      return;
      this.barTopView.setRightItemVisibility(0);
    }
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      setData();
    while (!paramString.equalsIgnoreCase("resetData"))
      return;
    setData();
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 2:
      ControllerManager.getInstance().popLastController();
      return;
    case 3:
    }
    if ((this.mManage) && (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes().size() == 0))
    {
      ControllerManager.getInstance().popLastController();
      return;
    }
    Object localObject = this.mainView;
    String str;
    if (this.mManage)
    {
      str = "hideManage";
      ((MyCollectionView)localObject).update(str, null);
      localObject = this.barTopView;
      if (!this.mManage)
        break label133;
      str = "编辑";
      label105: ((NavigationBarView)localObject).setRightItem(str);
      if (this.mManage)
        break label139;
    }
    label133: label139: for (boolean bool = true; ; bool = false)
    {
      this.mManage = bool;
      return;
      str = "showManage";
      break;
      str = "取消";
      break label105;
    }
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("emptynow"))
    {
      this.mainView.update("hideManage", null);
      this.barTopView.setRightItem("编辑");
      this.barTopView.setRightItemVisibility(4);
      this.mManage = false;
    }
    while (!paramString.equalsIgnoreCase("notEmpty"))
      return;
    this.barTopView.setRightItemVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.virtual.MyCollectionController
 * JD-Core Version:    0.6.2
 */