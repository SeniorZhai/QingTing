package fm.qingting.framework.controller;

import android.widget.FrameLayout;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.IViewEventListener;

public abstract class SwitchAnimation
  implements ISwitchAnimation, IViewEventListener
{
  protected boolean animation;
  protected IView closedView;
  protected FrameLayout container;
  protected SwitchAnimationListener listener;
  protected IView openedView;
  protected int optionType;
  protected ViewController popingController;

  protected void animationEnd()
  {
    if (this.listener != null)
    {
      if (this.popingController != null)
        this.listener.controllerPopEnd(this.popingController);
      this.listener.switchEnd(this, this.container, this.openedView, this.closedView, this.animation, this.optionType);
    }
  }

  public void destory()
  {
    this.openedView = null;
    this.closedView = null;
    this.listener = null;
    this.container = null;
    this.popingController = null;
  }

  protected void doAnimation()
  {
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
    if ((paramIView2 == null) && (paramFrameLayout == null))
      return;
    this.openedView = paramIView1;
    this.closedView = paramIView2;
    this.animation = paramBoolean;
    this.container = paramFrameLayout;
    this.optionType = paramInt;
    if (this.listener != null)
      this.listener.switchStart(this, paramFrameLayout, paramIView1, paramIView2, paramBoolean, paramInt);
    doAnimation();
  }

  public void viewDidClosed(IView paramIView)
  {
  }

  public void viewDidOpened(IView paramIView)
  {
  }

  public void viewWillClose(IView paramIView)
  {
  }

  public void viewWillOpen(IView paramIView)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.SwitchAnimation
 * JD-Core Version:    0.6.2
 */