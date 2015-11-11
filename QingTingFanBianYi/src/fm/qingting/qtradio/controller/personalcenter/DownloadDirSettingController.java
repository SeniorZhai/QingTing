package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.settingviews.DownloadDirSettingView;

public class DownloadDirSettingController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView mBarTopView;
  private DownloadDirSettingView mainView;

  public DownloadDirSettingController(Context paramContext)
  {
    super(paramContext);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setLeftItem(0);
    this.mBarTopView.setTitleItem(new NavigationBarItem("选择下载位置"));
    this.mBarTopView.setBarListener(this);
    setNavigationBar(this.mBarTopView);
    this.mainView = new DownloadDirSettingView(paramContext);
    attachView(this.mainView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mainView.update(paramString, paramObject);
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.DownloadDirSettingController
 * JD-Core Version:    0.6.2
 */