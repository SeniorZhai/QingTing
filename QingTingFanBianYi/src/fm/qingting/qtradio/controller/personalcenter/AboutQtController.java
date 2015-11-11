package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.aboutQt.AboutQtView;
import fm.qingting.social.SocialEventListener;
import fm.qingting.social.api.SinaWeiboApi;

public class AboutQtController extends ViewController
  implements INavigationBarListener
{
  private static Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        Toast.makeText(InfoManager.getInstance().getContext(), "关注成功！", 0).show();
        return;
      case 2:
        Toast.makeText(InfoManager.getInstance().getContext(), "关注失败...", 0).show();
        return;
      case 3:
      }
      Toast.makeText(InfoManager.getInstance().getContext(), "取消关注", 0).show();
    }
  };
  private AboutQtView qtView;

  public AboutQtController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "aboutqt";
    this.qtView = new AboutQtView(paramContext);
    attachView(this.qtView);
    NavigationBarView localNavigationBarView = new NavigationBarView(paramContext);
    localNavigationBarView.setBackgroundResource(0);
    localNavigationBarView.setLeftItem(0);
    localNavigationBarView.setTitleItem(new NavigationBarItem("关于" + paramContext.getResources().getString(2131492864)));
    localNavigationBarView.setBarListener(this);
    setNavigationBar(localNavigationBarView);
    setNavigationBarMode(INavigationSetting.Mode.OVERLAY);
  }

  private void addWeiboFriend()
  {
    SinaWeiboApi.follow(getContext(), "蜻蜓FM", new SocialEventListener()
    {
      public void onCancel(Object paramAnonymousObject)
      {
        AboutQtController.mHandler.sendEmptyMessage(3);
      }

      public void onComplete(Object paramAnonymousObject1, Object paramAnonymousObject2)
      {
        AboutQtController.mHandler.sendEmptyMessage(1);
      }

      public void onException(Object paramAnonymousObject)
      {
        AboutQtController.mHandler.sendEmptyMessage(2);
      }
    });
  }

  private void openWeb()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://qingting.fm"));
    getContext().startActivity(localIntent);
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("followQt"))
      addWeiboFriend();
    while (!paramString.equalsIgnoreCase("toWebQt"))
      return;
    openWeb();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.AboutQtController
 * JD-Core Version:    0.6.2
 */