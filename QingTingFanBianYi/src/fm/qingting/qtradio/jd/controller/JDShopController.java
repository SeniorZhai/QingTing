package fm.qingting.qtradio.jd.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.jd.data.CommodityInfo;
import fm.qingting.qtradio.jd.view.JDShopView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class JDShopController extends ViewController
  implements INavigationBarListener
{
  NavigationBarView mTopView;
  private JDShopView mView;

  public JDShopController(Context paramContext)
  {
    super(paramContext);
    this.mTopView = new NavigationBarView(paramContext);
    this.mTopView.setLeftItem(0);
    this.mTopView.setBarListener(this);
    setNavigationBar(this.mTopView);
    this.mView = new JDShopView(paramContext);
    attachView(this.mView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (CommodityInfo)paramObject;
      this.mTopView.setTitleItem(new NavigationBarItem(paramString.getTitle()));
      this.mView.update("setData", paramString.getShopUrl());
    }
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.jd.controller.JDShopController
 * JD-Core Version:    0.6.2
 */