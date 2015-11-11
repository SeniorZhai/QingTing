package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.webview.LocalWebView;

public class LocalWebViewController extends ViewController
  implements INavigationBarListener
{
  private NavigationBarView barTopView;
  private LocalWebView mLocalWebView;

  public LocalWebViewController(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    super(paramContext);
    this.controllerName = "localwebView";
    this.barTopView = new NavigationBarView(paramContext);
    this.barTopView.setLeftItem(0);
    this.barTopView.setTitleItem(new NavigationBarItem(paramString2));
    this.barTopView.setBarListener(this);
    setNavigationBar(this.barTopView);
    this.mLocalWebView = new LocalWebView(paramContext, paramString1, paramBoolean);
    attachView(this.mLocalWebView);
  }

  public void controllerDidPopped()
  {
    if (this.mLocalWebView != null)
      this.mLocalWebView.destroy();
    super.controllerDidPopped();
  }

  public void onItemClick(int paramInt)
  {
    switch (paramInt)
    {
    case 3:
    default:
      return;
    case 2:
    }
    if (this.mLocalWebView != null)
    {
      if (this.mLocalWebView.canBack())
      {
        this.mLocalWebView.goBack();
        return;
      }
      this.mLocalWebView.destroy();
    }
    ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.LocalWebViewController
 * JD-Core Version:    0.6.2
 */