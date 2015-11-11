package fm.qingting.qtradio.manager;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.view.link.CustomLinkView;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;

public class LinkManager
{
  private static final int SHOW_LENGTH = 10000;
  private static Handler sCountToTenHandler = new Handler();
  private static Runnable sCountToTenRunnable = new Runnable()
  {
    public void run()
    {
      LinkManager.cancelLinkIfExists(null);
    }
  };
  private static RecommendItemNode sNode;
  private static boolean sShowing = false;
  private static IView sView;

  static
  {
    sNode = null;
  }

  public static void cancelLinkIfExists(Context paramContext)
  {
    if (sView != null)
    {
      Context localContext = paramContext;
      if (paramContext == null)
        localContext = InfoManager.getInstance().getContext();
      paramContext = (WindowManager)localContext.getSystemService("window");
      sView.getView().setVisibility(8);
      paramContext.removeView(sView.getView());
      sView.close(false);
      sView = null;
      resetPlayView();
    }
    sShowing = false;
  }

  private static IView generateView(Context paramContext, int paramInt)
  {
    return new CustomLinkView(paramContext, paramInt);
  }

  public static RecommendItemNode getLastNode()
  {
    return sNode;
  }

  private static int getLeft()
  {
    return 0;
  }

  private static int getTop()
  {
    return ScreenConfiguration.getMiniHeight();
  }

  public static boolean isShown()
  {
    return sShowing;
  }

  private static void resetPlayView()
  {
    ViewController localViewController = ControllerManager.getInstance().getLastViewController();
    if (localViewController.controllerName.equalsIgnoreCase("mainplayview"))
      localViewController.config("resetSomeViews", null);
  }

  public static void showLink(Context paramContext, RecommendItemNode paramRecommendItemNode, boolean paramBoolean, Point paramPoint)
  {
    int i = 0;
    if (isShown())
      return;
    QTMSGManage.getInstance().sendStatistcsMessage("showLink", "show_" + InfoManager.getInstance().getInBackground());
    sNode = paramRecommendItemNode;
    WindowManager localWindowManager = (WindowManager)paramContext.getSystemService("window");
    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
    localLayoutParams.flags = 40;
    localLayoutParams.format = -3;
    localLayoutParams.windowAnimations = 0;
    if (paramPoint != null)
      i = paramPoint.x;
    sView = generateView(paramContext, i);
    sView.update("setData", paramRecommendItemNode);
    if (paramBoolean);
    for (i = paramPoint.y; ; i = getTop())
    {
      localLayoutParams.gravity = 83;
      localLayoutParams.x = getLeft();
      localLayoutParams.y = i;
      localLayoutParams.width = -1;
      localLayoutParams.height = -2;
      localWindowManager.addView(sView.getView(), localLayoutParams);
      sShowing = true;
      sCountToTenHandler.removeCallbacks(sCountToTenRunnable);
      sCountToTenHandler.postDelayed(sCountToTenRunnable, 10000L);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.LinkManager
 * JD-Core Version:    0.6.2
 */