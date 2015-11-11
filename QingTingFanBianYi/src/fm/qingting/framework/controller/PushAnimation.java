package fm.qingting.framework.controller;

import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.IViewEventListener;

public class PushAnimation
  implements ISwitchAnimation, IViewEventListener
{
  private boolean animation;
  private IView closedView;
  private FrameLayout container;
  private SwitchAnimationListener listener;
  private IView opennedView;
  private int optionType;

  public void destory()
  {
    this.opennedView = null;
    this.closedView = null;
    this.container = null;
    this.listener = null;
  }

  public void setPopingController(ViewController paramViewController)
  {
  }

  public void setSwitchAnimationListener(SwitchAnimationListener paramSwitchAnimationListener)
  {
    this.listener = paramSwitchAnimationListener;
  }

  public void startAnimation(FrameLayout paramFrameLayout, IView paramIView1, IView paramIView2, boolean paramBoolean, int paramInt)
  {
    if ((paramIView1 == null) && (paramFrameLayout == null))
      return;
    this.animation = paramBoolean;
    this.opennedView = paramIView1;
    this.closedView = paramIView2;
    this.container = paramFrameLayout;
    this.optionType = paramInt;
    if (this.listener != null)
      this.listener.switchStart(this, paramFrameLayout, paramIView1, paramIView2, paramBoolean, paramInt);
    if (paramIView2 != null)
    {
      paramIView2.setActivate(false);
      paramIView2.addViewEventListener(this);
    }
    paramIView1.setActivate(true);
    paramIView1.getView().setVisibility(0);
    paramIView1.addViewEventListener(this);
    if (paramFrameLayout.indexOfChild(paramIView1.getView()) >= 0)
      paramIView1.getView().bringToFront();
    int i = paramFrameLayout.getMeasuredWidth();
    paramInt = paramFrameLayout.getMeasuredHeight();
    if ((i != 0) && (paramInt != 0) && (paramBoolean))
    {
      i = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
      paramInt = View.MeasureSpec.makeMeasureSpec(paramInt, 1073741824);
      paramIView1.getView().measure(i, paramInt);
      paramIView1.open(true);
      return;
    }
    paramIView1.open(false);
  }

  public void viewDidClosed(IView paramIView)
  {
    paramIView.removeViewEventListener(this);
    paramIView.getView().clearAnimation();
    paramIView.getView().setVisibility(8);
  }

  public void viewDidOpened(IView paramIView)
  {
    paramIView.removeViewEventListener(this);
    if (this.closedView != null)
      this.closedView.close(false);
    paramIView.getView().setVisibility(0);
    if (this.listener != null)
      this.listener.switchEnd(this, this.container, this.opennedView, this.closedView, this.animation, this.optionType);
  }

  public void viewWillClose(IView paramIView)
  {
  }

  public void viewWillOpen(IView paramIView)
  {
    if (this.closedView != null)
      this.closedView.getView().setVisibility(0);
    paramIView.getView().setVisibility(0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.PushAnimation
 * JD-Core Version:    0.6.2
 */