package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.view.im.ReportView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;

public class ReportController extends ViewController
  implements INavigationBarListener
{
  private IMMessage mMessage;
  private NavigationBarView mTopView;
  private ReportView mainView;

  public ReportController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "report";
    this.mTopView = new NavigationBarView(paramContext);
    this.mTopView.setLeftItem(0);
    this.mTopView.setTitleItem(new NavigationBarItem("确认举报"));
    this.mTopView.setBarListener(this);
    setNavigationBar(this.mTopView);
    this.mainView = new ReportView(paramContext);
    attachView(this.mainView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mMessage = ((IMMessage)paramObject);
      this.mainView.update(paramString, paramObject);
    }
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }

  protected void onViewEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if ((paramString.equalsIgnoreCase("report")) && (this.mMessage != null))
    {
      CloudCenter.getInstance().reportUser(this.mMessage.mFromID, this.mMessage.mMessage, ((Integer)paramObject2).intValue());
      this.mainView.update("reportSuccess", null);
      this.mTopView.setTitleItem(new NavigationBarItem("举报成功"));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ReportController
 * JD-Core Version:    0.6.2
 */