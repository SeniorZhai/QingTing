package fm.qingting.framework.controller;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import fm.qingting.framework.view.FrameLayoutViewImpl;
import fm.qingting.framework.view.INavigationSetting;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.IViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NavigationController extends ViewController
  implements SwitchAnimationListener
{
  public static final int OPTION_TYPE_POP = 1;
  public static final int OPTION_TYPE_PUSH = 2;
  private FrameLayoutViewImpl container;
  private LinkedList<NavigationItem> controllerStack = new LinkedList();
  private boolean isAnimation = false;
  private INavigationEventListener navigationEventListener;
  private NavigationItem navigationItem;
  private INavigationSetting navigationSetting;
  private ISwitchAnimation popAnimation;
  private ISwitchAnimation pushAnimation;

  public NavigationController(Context paramContext)
  {
    this(paramContext, new NavigationControllerContainerView(paramContext));
  }

  public NavigationController(Context paramContext, FrameLayoutViewImpl paramFrameLayoutViewImpl)
  {
    super(paramContext, paramFrameLayoutViewImpl);
    this.container = paramFrameLayoutViewImpl;
  }

  private void dispatchPopEvent(ViewController paramViewController, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramViewController);
    dispatchPopEvent(localArrayList, paramBoolean);
  }

  private void dispatchPopEvent(List<ViewController> paramList, boolean paramBoolean)
  {
    if (this.navigationEventListener == null)
      return;
    this.navigationEventListener.onPopControllers(paramList, paramBoolean);
  }

  private void dispatchPushEvent(ViewController paramViewController, boolean paramBoolean)
  {
    if (this.navigationEventListener == null)
      return;
    this.navigationEventListener.onPushController(paramViewController, paramBoolean);
  }

  private void removerStackController(ViewController paramViewController)
  {
  }

  public void controllerPopEnd(ViewController paramViewController)
  {
    ViewController localViewController = ((NavigationItem)this.controllerStack.getLast()).viewController;
    if (localViewController != null)
      localViewController.controllerReappeared();
    paramViewController.controllerDidPopped();
  }

  public List<ViewController> getAllControllers()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.controllerStack.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      localArrayList.add(((NavigationItem)localIterator.next()).viewController);
    }
  }

  public List<ViewController> getAllHiddenControllers()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.controllerStack.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      NavigationItem localNavigationItem = (NavigationItem)localIterator.next();
      if (localIterator.hasNext())
        localArrayList.add(localNavigationItem.viewController);
    }
  }

  public int getCount()
  {
    return this.controllerStack.size();
  }

  public ViewController getLastViewController()
  {
    if (this.controllerStack.getLast() == null)
      return null;
    return ((NavigationItem)this.controllerStack.getLast()).viewController;
  }

  public ViewController getRootViewController()
  {
    return ((NavigationItem)this.controllerStack.getFirst()).viewController;
  }

  public FrameLayoutViewImpl getViewContainer()
  {
    return this.container;
  }

  public ViewController getViewController(int paramInt)
  {
    return ((NavigationItem)this.controllerStack.get(paramInt)).viewController;
  }

  public boolean isAnimating()
  {
    return this.isAnimation;
  }

  protected void onDestroy()
  {
    Iterator localIterator = getAllControllers().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.controllerStack.clear();
        this.controllerStack = null;
        this.container.removeAllViews();
        this.container = null;
        this.navigationEventListener = null;
        if (this.popAnimation != null)
        {
          this.popAnimation.destory();
          this.popAnimation = null;
        }
        if (this.pushAnimation != null)
        {
          this.pushAnimation.destory();
          this.pushAnimation = null;
        }
        this.navigationSetting = null;
        super.onDestroy();
        return;
      }
      ((ViewController)localIterator.next()).destroy();
    }
  }

  public void popToRootViewController(boolean paramBoolean)
  {
    if (this.isAnimation);
    while (this.controllerStack.size() <= 1)
      return;
    Object localObject = (NavigationItem)this.controllerStack.removeLast();
    ViewController localViewController = ((NavigationItem)localObject).viewController;
    IView localIView1;
    label77: NavigationItem localNavigationItem;
    IView localIView2;
    if (paramBoolean)
    {
      localObject = ((NavigationItem)localObject).popAnimation;
      ((ISwitchAnimation)localObject).setSwitchAnimationListener(this);
      this.popAnimation = ((ISwitchAnimation)localObject);
      localViewController.setNavigationController(null);
      if (localViewController.getShellView() != null)
        break label227;
      localIView1 = localViewController.getView();
      localIView1.setActivate(false);
      localViewController.stopActivate();
      localNavigationItem = (NavigationItem)this.controllerStack.getFirst();
      if (localNavigationItem.viewController.getShellView() == null)
        break label236;
      localIView2 = localNavigationItem.viewController.getShellView();
      label122: localIView2.setActivate(true);
      if (this.container.indexOfChild(localIView2.getView()) >= 0)
        break label249;
      this.container.addView(localIView2.getView(), this.container.getChildCount());
    }
    while (true)
    {
      this.controllerStack.clear();
      this.controllerStack.add(localNavigationItem);
      ((ISwitchAnimation)localObject).setPopingController(localViewController);
      ((ISwitchAnimation)localObject).startAnimation(this.container, localIView2, localIView1, paramBoolean, 1);
      dispatchPopEvent(localViewController, paramBoolean);
      return;
      localObject = new PopAnimation();
      break;
      label227: localIView1 = localViewController.getShellView();
      break label77;
      label236: localIView2 = localNavigationItem.viewController.getView();
      break label122;
      label249: this.container.bringChildToFront(localIView2.getView());
    }
  }

  public void popToRootViewControllerUsingAnimation(ISwitchAnimation paramISwitchAnimation)
  {
    if (paramISwitchAnimation == null);
    while ((this.isAnimation) || (this.controllerStack.size() <= 1))
      return;
    ViewController localViewController = ((NavigationItem)this.controllerStack.removeLast()).viewController;
    paramISwitchAnimation.setSwitchAnimationListener(this);
    this.popAnimation = paramISwitchAnimation;
    localViewController.setNavigationController(null);
    IView localIView1;
    NavigationItem localNavigationItem;
    IView localIView2;
    if (localViewController.getShellView() == null)
    {
      localIView1 = localViewController.getView();
      localIView1.setActivate(false);
      localViewController.stopActivate();
      localNavigationItem = (NavigationItem)this.controllerStack.getFirst();
      if (localNavigationItem.viewController.getShellView() == null)
        break label213;
      localIView2 = localNavigationItem.viewController.getShellView();
      label114: localIView2.setActivate(true);
      if (this.container.indexOfChild(localIView2.getView()) >= 0)
        break label225;
      this.container.addView(localIView2.getView(), this.container.getChildCount());
    }
    while (true)
    {
      this.controllerStack.clear();
      this.controllerStack.add(localNavigationItem);
      paramISwitchAnimation.setPopingController(localViewController);
      paramISwitchAnimation.startAnimation(this.container, localIView2, localIView1, true, 1);
      dispatchPopEvent(localViewController, true);
      return;
      localIView1 = localViewController.getShellView();
      break;
      label213: localIView2 = localNavigationItem.viewController.getView();
      break label114;
      label225: this.container.bringChildToFront(localIView2.getView());
    }
  }

  public void popToViewController(int paramInt, boolean paramBoolean)
  {
    if (this.isAnimation);
    while (this.controllerStack.size() <= paramInt)
      return;
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (NavigationItem)this.controllerStack.removeLast();
    ViewController localViewController = ((NavigationItem)localObject1).viewController;
    localArrayList.add(localViewController);
    ISwitchAnimation localISwitchAnimation = ((NavigationItem)localObject1).popAnimation;
    localISwitchAnimation.setSwitchAnimationListener(this);
    this.popAnimation = localISwitchAnimation;
    localViewController.setNavigationController(null);
    Object localObject2;
    label141: label187: int i;
    if (localViewController.getShellView() == null)
    {
      localObject1 = localViewController.getView();
      ((IView)localObject1).setActivate(false);
      localViewController.stopActivate();
      localObject2 = (NavigationItem)this.controllerStack.get(paramInt);
      if (((NavigationItem)localObject2).viewController.getShellView() == null)
        break label246;
      localObject2 = ((NavigationItem)localObject2).viewController.getShellView();
      ((IView)localObject2).setActivate(true);
      if (this.container.indexOfChild(((IView)localObject2).getView()) >= 0)
        break label259;
      this.container.addView(((IView)localObject2).getView(), this.container.getChildCount());
      i = this.controllerStack.size();
    }
    while (true)
    {
      if (i <= paramInt + 1)
      {
        localISwitchAnimation.setPopingController(localViewController);
        localISwitchAnimation.startAnimation(this.container, (IView)localObject2, (IView)localObject1, paramBoolean, 1);
        dispatchPopEvent(localArrayList, paramBoolean);
        return;
        localObject1 = localViewController.getShellView();
        break;
        label246: localObject2 = ((NavigationItem)localObject2).viewController.getView();
        break label141;
        label259: this.container.bringChildToFront(((IView)localObject2).getView());
        break label187;
      }
      localArrayList.add(((NavigationItem)this.controllerStack.removeLast()).viewController);
      i -= 1;
    }
  }

  public void popViewController()
  {
    popViewController(true);
  }

  public void popViewController(boolean paramBoolean)
  {
    if (this.isAnimation);
    while (this.controllerStack.size() <= 1)
      return;
    Object localObject = (NavigationItem)this.controllerStack.removeLast();
    ViewController localViewController = ((NavigationItem)localObject).viewController;
    ISwitchAnimation localISwitchAnimation = ((NavigationItem)localObject).popAnimation;
    localISwitchAnimation.setSwitchAnimationListener(this);
    this.popAnimation = localISwitchAnimation;
    localViewController.setNavigationController(null);
    IView localIView;
    if (localViewController.getShellView() == null)
    {
      localObject = localViewController.getView();
      ((IView)localObject).setActivate(false);
      localViewController.stopActivate();
      localIView = ((NavigationItem)this.controllerStack.getLast()).viewController.getShellView();
      if (localIView == null)
        break label258;
      label109: localIView.setActivate(true);
      if (this.navigationSetting != null)
      {
        int i = this.controllerStack.size() - 2;
        if (i >= 0)
          this.navigationSetting.navigationWillPopped(((NavigationItem)this.controllerStack.get(i)).viewController, this.container, localIView);
      }
      this.container.bringChildToFront(((IView)localObject).getView());
      if (this.container.indexOfChild(localIView.getView()) >= 0)
        break label278;
      this.container.addView(localIView.getView(), this.container.getChildCount());
    }
    while (true)
    {
      localISwitchAnimation.setPopingController(localViewController);
      localISwitchAnimation.startAnimation(this.container, localIView, (IView)localObject, paramBoolean, 1);
      dispatchPopEvent(localViewController, paramBoolean);
      return;
      localObject = localViewController.getShellView();
      break;
      label258: localIView = ((NavigationItem)this.controllerStack.getLast()).viewController.getView();
      break label109;
      label278: this.container.bringChildToFront(localIView.getView());
    }
  }

  public void popViewControllerShell(boolean paramBoolean)
  {
    if (this.isAnimation);
    while (this.controllerStack.size() <= 1)
      return;
    Object localObject = (NavigationItem)this.controllerStack.removeLast();
    ViewController localViewController = ((NavigationItem)localObject).viewController;
    ISwitchAnimation localISwitchAnimation = ((NavigationItem)localObject).popAnimation;
    IView localIView;
    if (localViewController.getShellView() == null)
    {
      localObject = localViewController.getView();
      localISwitchAnimation.setSwitchAnimationListener(this);
      this.popAnimation = localISwitchAnimation;
      localViewController.setNavigationController(null);
      localViewController.stopActivate();
      localViewController.controllerDidPopped();
      localIView = ((NavigationItem)this.controllerStack.getLast()).viewController.getShellView();
      if (localIView == null)
        break label234;
      label107: if (this.navigationSetting != null)
      {
        int i = this.controllerStack.size() - 2;
        if (i >= 0)
          this.navigationSetting.navigationWillPopped(((NavigationItem)this.controllerStack.get(i)).viewController, this.container, localIView);
      }
      localIView.setActivate(true);
      if (this.container.indexOfChild(localIView.getView()) >= 0)
        break label254;
      this.container.addView(localIView.getView(), this.container.getChildCount());
    }
    while (true)
    {
      localISwitchAnimation.startAnimation(this.container, localIView, (IView)localObject, paramBoolean, 1);
      dispatchPopEvent(localViewController, paramBoolean);
      return;
      localObject = localViewController.getShellView();
      break;
      label234: localIView = ((NavigationItem)this.controllerStack.getLast()).viewController.getView();
      break label107;
      label254: this.container.bringChildToFront(localIView.getView());
    }
  }

  public void pushViewController(ViewController paramViewController)
  {
    pushViewController(paramViewController, true);
  }

  public void pushViewController(ViewController paramViewController, boolean paramBoolean)
  {
    pushViewController(paramViewController, paramBoolean, null, null);
  }

  public void pushViewController(ViewController paramViewController, boolean paramBoolean, ISwitchAnimation paramISwitchAnimation1, ISwitchAnimation paramISwitchAnimation2)
  {
    if ((paramViewController == null) || (this.isAnimation));
    while (this.popAnimation != null)
      return;
    ViewController localViewController;
    label32: Object localObject1;
    Object localObject2;
    if (this.controllerStack.size() == 0)
    {
      localViewController = null;
      if (localViewController == paramViewController)
        break label277;
      localObject1 = paramISwitchAnimation1;
      if (paramISwitchAnimation1 == null)
        localObject1 = new PushAnimation();
      localObject2 = paramISwitchAnimation2;
      if (paramISwitchAnimation2 == null)
        localObject2 = new PopAnimation();
      ((ISwitchAnimation)localObject1).setSwitchAnimationListener(this);
      this.pushAnimation = ((ISwitchAnimation)localObject1);
      paramISwitchAnimation1 = this.navigationSetting.getLayoutView(getContext(), paramViewController.getNavigationBarMode());
      if ((paramISwitchAnimation1 != null) && (paramViewController.getShellView() == null))
        paramISwitchAnimation1.LayoutView(paramViewController);
      paramISwitchAnimation1 = null;
      if (localViewController != null)
      {
        if (localViewController.getShellView() == null)
          break label279;
        paramISwitchAnimation1 = localViewController.getShellView();
        label143: paramISwitchAnimation1.setActivate(false);
        localViewController.stopActivate();
      }
      if (paramViewController.getShellView() == null)
        break label288;
      paramISwitchAnimation2 = paramViewController.getShellView();
      label168: paramISwitchAnimation2.setActivate(true);
      if (this.container.indexOfChild(paramISwitchAnimation2.getView()) >= 0)
        break label297;
      this.container.addView(paramISwitchAnimation2.getView(), this.container.getChildCount());
    }
    while (true)
    {
      paramViewController.setNavigationController(this);
      this.navigationItem = null;
      this.navigationItem = new NavigationItem(paramViewController, (ISwitchAnimation)localObject2);
      ((ISwitchAnimation)localObject1).startAnimation(this.container, paramISwitchAnimation2, paramISwitchAnimation1, paramBoolean, 2);
      dispatchPushEvent(paramViewController, paramBoolean);
      return;
      localViewController = ((NavigationItem)this.controllerStack.getLast()).viewController;
      break label32;
      label277: break;
      label279: paramISwitchAnimation1 = localViewController.getView();
      break label143;
      label288: paramISwitchAnimation2 = paramViewController.getView();
      break label168;
      label297: this.container.bringChildToFront(paramISwitchAnimation2.getView());
    }
  }

  public void pushViewController(ViewController paramViewController, boolean paramBoolean, ISwitchAnimation paramISwitchAnimation1, ISwitchAnimation paramISwitchAnimation2, String paramString)
  {
    if ((paramViewController == null) || (this.isAnimation));
    while (this.popAnimation != null)
      return;
    ViewController localViewController;
    label32: Object localObject1;
    Object localObject2;
    if (this.controllerStack.size() == 0)
    {
      localViewController = null;
      if (localViewController == paramViewController)
        break label283;
      localObject1 = paramISwitchAnimation1;
      if (paramISwitchAnimation1 == null)
        localObject1 = new PushAnimation();
      localObject2 = paramISwitchAnimation2;
      if (paramISwitchAnimation2 == null)
        localObject2 = new PopAnimation(paramString);
      ((ISwitchAnimation)localObject1).setSwitchAnimationListener(this);
      this.pushAnimation = ((ISwitchAnimation)localObject1);
      paramISwitchAnimation1 = this.navigationSetting.getLayoutView(getContext(), paramViewController.getNavigationBarMode());
      if ((paramISwitchAnimation1 != null) && (paramViewController.getShellView() == null))
        paramISwitchAnimation1.LayoutView(paramViewController);
      paramISwitchAnimation1 = null;
      if (localViewController != null)
      {
        if (localViewController.getShellView() == null)
          break label285;
        paramISwitchAnimation1 = localViewController.getShellView();
        label145: paramISwitchAnimation1.setActivate(false);
        localViewController.stopActivate();
      }
      if (paramViewController.getShellView() == null)
        break label294;
      paramISwitchAnimation2 = paramViewController.getShellView();
      label170: paramISwitchAnimation2.setActivate(true);
      if (this.container.indexOfChild(paramISwitchAnimation2.getView()) >= 0)
        break label303;
      this.container.addView(paramISwitchAnimation2.getView(), this.container.getChildCount());
    }
    while (true)
    {
      paramViewController.setNavigationController(this);
      paramViewController.controllerDidPushed();
      this.navigationItem = null;
      this.navigationItem = new NavigationItem(paramViewController, (ISwitchAnimation)localObject2);
      ((ISwitchAnimation)localObject1).startAnimation(this.container, paramISwitchAnimation2, paramISwitchAnimation1, paramBoolean, 2);
      dispatchPushEvent(paramViewController, paramBoolean);
      return;
      localViewController = ((NavigationItem)this.controllerStack.getLast()).viewController;
      break label32;
      label283: break;
      label285: paramISwitchAnimation1 = localViewController.getView();
      break label145;
      label294: paramISwitchAnimation2 = paramViewController.getView();
      break label170;
      label303: this.container.bringChildToFront(paramISwitchAnimation2.getView());
    }
  }

  public ViewController removeController(int paramInt)
  {
    if (this.controllerStack.size() <= paramInt)
      return null;
    return ((NavigationItem)this.controllerStack.remove(paramInt)).viewController;
  }

  public void setNavigationEventListener(INavigationEventListener paramINavigationEventListener)
  {
    this.navigationEventListener = paramINavigationEventListener;
  }

  public void setNavigationSetting(INavigationSetting paramINavigationSetting)
  {
    this.navigationSetting = paramINavigationSetting;
  }

  public void setRootController(ViewController paramViewController, String paramString)
  {
    pushViewController(paramViewController);
  }

  public void switchEnd(ISwitchAnimation paramISwitchAnimation, final FrameLayout paramFrameLayout, IView paramIView1, final IView paramIView2, boolean paramBoolean, int paramInt)
  {
    if ((paramInt == 2) && (this.navigationItem != null))
    {
      this.navigationItem.viewController.controllerDidPushed();
      removerStackController(this.navigationItem.viewController);
      this.controllerStack.add(this.navigationItem);
    }
    if (paramInt == 2)
    {
      if (paramIView2 != null)
      {
        paramIView2.getView().clearAnimation();
        paramIView2.getView().setVisibility(8);
      }
      this.container.bringChildToFront(paramIView1.getView());
    }
    while (true)
    {
      if (this.pushAnimation != null)
      {
        this.pushAnimation.destory();
        this.pushAnimation = null;
      }
      if (this.popAnimation != null)
      {
        this.popAnimation.destory();
        this.popAnimation = null;
      }
      this.isAnimation = false;
      return;
      if ((paramIView2 != null) && (paramFrameLayout.indexOfChild(paramIView2.getView()) >= 0))
      {
        paramIView2.getView().clearAnimation();
        paramIView2.getView().setVisibility(8);
        this.container.post(new Runnable()
        {
          public void run()
          {
            paramFrameLayout.removeView(paramIView2.getView());
          }
        });
      }
    }
  }

  public void switchStart(ISwitchAnimation paramISwitchAnimation, FrameLayout paramFrameLayout, IView paramIView1, IView paramIView2, boolean paramBoolean, int paramInt)
  {
    this.isAnimation = true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.NavigationController
 * JD-Core Version:    0.6.2
 */