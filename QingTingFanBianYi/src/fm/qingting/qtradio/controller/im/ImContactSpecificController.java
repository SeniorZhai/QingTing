package fm.qingting.qtradio.controller.im;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.model.INavigationBarListener;
import fm.qingting.framework.model.NavigationBarItem;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.im.ImContactsSpecificView;
import fm.qingting.qtradio.view.navigation.NavigationBarView;
import fm.qingting.utils.QTMSGManage;
import java.util.List;
import java.util.Locale;

public class ImContactSpecificController extends ViewController
  implements INavigationBarListener, RootNode.IInfoUpdateEventListener
{
  private static final String FOLLOWER_MODEL = "粉丝(%d)";
  private static final String FOLLOWING_MODEL = "关注(%d)";
  private NavigationBarView mBarTopView;
  private boolean mFollowing = false;
  private ImContactsSpecificView mainView;

  public ImContactSpecificController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "contactspecific";
    this.mainView = new ImContactsSpecificView(paramContext);
    attachView(this.mainView);
    this.mBarTopView = new NavigationBarView(paramContext);
    this.mBarTopView.setTitleItem(new NavigationBarItem("关注"));
    this.mBarTopView.setLeftItem(0);
    setNavigationBar(this.mBarTopView);
    this.mBarTopView.setBarListener(this);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 5);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 4);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mFollowing = ((Boolean)paramObject).booleanValue();
      if (!this.mFollowing)
        break label143;
      paramObject = InfoManager.getInstance().getUserProfile().getFollowings();
      if (paramObject != null)
        break label93;
      this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "关注(%d)", new Object[] { Integer.valueOf(0) })));
      InfoManager.getInstance().getUserProfile().loadFollowings(this);
    }
    while (true)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("imfollowinglist");
      return;
      label93: this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "关注(%d)", new Object[] { Integer.valueOf(paramObject.size()) })));
      this.mainView.update(paramString, paramObject);
    }
    label143: paramObject = InfoManager.getInstance().getUserProfile().getFollowers();
    if (paramObject == null)
    {
      this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "粉丝(%d)", new Object[] { Integer.valueOf(0) })));
      InfoManager.getInstance().getUserProfile().loadFollowers(this);
    }
    while (true)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("imfollowerlist");
      return;
      this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "粉丝(%d)", new Object[] { Integer.valueOf(paramObject.size()) })));
      this.mainView.update(paramString, paramObject);
    }
  }

  public void controllerDidPopped()
  {
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(5, this);
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(4, this);
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void onInfoUpdated(int paramInt)
  {
    if ((paramInt == 5) && (this.mFollowing))
    {
      localList = InfoManager.getInstance().getUserProfile().getFollowings();
      if (localList == null)
      {
        this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "关注(%d)", new Object[] { Integer.valueOf(0) })));
        this.mainView.update("setData", localList);
      }
    }
    while ((paramInt != 4) || (this.mFollowing))
      while (true)
      {
        return;
        this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "关注(%d)", new Object[] { Integer.valueOf(localList.size()) })));
      }
    List localList = InfoManager.getInstance().getUserProfile().getFollowers();
    if (localList == null)
      this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "粉丝(%d)", new Object[] { Integer.valueOf(0) })));
    while (true)
    {
      this.mainView.update("setData", localList);
      return;
      this.mBarTopView.setTitleItem(new NavigationBarItem(String.format(Locale.CHINESE, "粉丝(%d)", new Object[] { Integer.valueOf(localList.size()) })));
    }
  }

  public void onItemClick(int paramInt)
  {
    if (paramInt == 2)
      ControllerManager.getInstance().popLastController();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.im.ImContactSpecificController
 * JD-Core Version:    0.6.2
 */