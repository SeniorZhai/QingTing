package fm.qingting.framework.controller;

import android.view.View;
import android.widget.FrameLayout;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.IViewEventListener;

public class PopAnimation
  implements ISwitchAnimation, IViewEventListener
{
  protected boolean animation;
  protected IView closedView;
  private FrameLayout container;
  protected SwitchAnimationListener listener;
  protected IView opennedView;
  private int optionType;
  private ViewController popingController;
  private String switchType;

  public PopAnimation()
  {
  }

  public PopAnimation(String paramString)
  {
    this.switchType = paramString;
  }

  public void destory()
  {
    this.opennedView = null;
    this.closedView = null;
    this.listener = null;
    this.container = null;
    this.popingController = null;
  }

  public void setPopingController(ViewController paramViewController)
  {
    this.popingController = paramViewController;
  }

  public void setSwitchAnimationListener(SwitchAnimationListener paramSwitchAnimationListener)
  {
    this.listener = paramSwitchAnimationListener;
  }

  public void startAnimation(FrameLayout paramFrameLayout, IView paramIView1, IView paramIView2, boolean paramBoolean, int paramInt)
  {
    if ((paramIView2 == null) && (paramFrameLayout == null));
    do
    {
      return;
      this.opennedView = paramIView1;
      this.closedView = paramIView2;
      this.animation = paramBoolean;
      this.container = paramFrameLayout;
      this.optionType = paramInt;
      if (this.listener != null)
        this.listener.switchStart(this, paramFrameLayout, paramIView1, paramIView2, paramBoolean, paramInt);
      if (this.opennedView != null)
      {
        this.opennedView.setActivate(true);
        this.opennedView.addViewEventListener(this);
        this.opennedView.getView().setVisibility(0);
        this.opennedView.open(false);
      }
      this.closedView.setActivate(false);
      this.closedView.getView().setVisibility(0);
      if (this.container.indexOfChild(this.closedView.getView()) >= 0)
        this.container.bringChildToFront(this.closedView.getView());
      this.closedView.addViewEventListener(this);
      this.closedView.close(paramBoolean);
    }
    while (this.switchType == null);
    EventDispacthManager.getInstance().dispatchAction(this.switchType, null);
  }

  public void viewDidClosed(IView paramIView)
  {
    paramIView.getView().clearAnimation();
    paramIView.getView().setVisibility(8);
    paramIView.removeViewEventListener(this);
    if (this.listener != null)
    {
      if (this.popingController != null)
        this.listener.controllerPopEnd(this.popingController);
      this.listener.switchEnd(this, this.container, this.opennedView, this.closedView, this.animation, this.optionType);
    }
  }

  public void viewDidOpened(IView paramIView)
  {
    paramIView.removeViewEventListener(this);
  }

  public void viewWillClose(IView paramIView)
  {
    paramIView.getView().setVisibility(0);
  }

  public void viewWillOpen(IView paramIView)
  {
    if (this.opennedView != null)
      this.opennedView.getView().setVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.PopAnimation
 * JD-Core Version:    0.6.2
 */