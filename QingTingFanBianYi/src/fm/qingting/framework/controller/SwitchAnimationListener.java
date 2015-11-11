package fm.qingting.framework.controller;

import android.widget.FrameLayout;
import fm.qingting.framework.view.IView;

abstract interface SwitchAnimationListener
{
  public abstract void controllerPopEnd(ViewController paramViewController);

  public abstract void switchEnd(ISwitchAnimation paramISwitchAnimation, FrameLayout paramFrameLayout, IView paramIView1, IView paramIView2, boolean paramBoolean, int paramInt);

  public abstract void switchStart(ISwitchAnimation paramISwitchAnimation, FrameLayout paramFrameLayout, IView paramIView1, IView paramIView2, boolean paramBoolean, int paramInt);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.SwitchAnimationListener
 * JD-Core Version:    0.6.2
 */