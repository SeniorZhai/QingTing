package fm.qingting.framework.controller;

import android.widget.FrameLayout;
import fm.qingting.framework.view.IView;

public abstract interface ISwitchAnimation
{
  public abstract void destory();

  public abstract void setPopingController(ViewController paramViewController);

  public abstract void setSwitchAnimationListener(SwitchAnimationListener paramSwitchAnimationListener);

  public abstract void startAnimation(FrameLayout paramFrameLayout, IView paramIView1, IView paramIView2, boolean paramBoolean, int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.ISwitchAnimation
 * JD-Core Version:    0.6.2
 */