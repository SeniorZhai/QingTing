package fm.qingting.qtradio.controller.personalcenter;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.qtradio.view.personalcenter.feedback.FeedbackView;

public class ContactInfoController extends ViewController
  implements INavigationBarListener
{
  public ContactInfoController(Context paramContext)
  {
    super(paramContext);
    attachView(new FeedbackView(paramContext));
    paramContext = new NavigationBarView(paramContext);
    paramContext.setLeftItem(0);
    paramContext.setTitleItem(new NavigationBarItem("联系信息"));
    paramContext.setRightItem(2);
    paramContext.setBarListener(this);
    setNavigationBar(paramContext);
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
    {
      this.view.update("closeKeyboard", null);
      ControllerManager.getInstance().popLastController();
    }
    while (paramInt != 3)
      return;
    this.view.update("saveInfo", null);
    this.view.update("closeKeyboard", null);
    ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.personalcenter.ContactInfoController
 * JD-Core Version:    0.6.2
 */