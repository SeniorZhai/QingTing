package fm.qingting.qtradio.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import fm.qingting.framework.controller.SwitchAnimation;
import fm.qingting.framework.view.IView;

public class MoveLtoRUncoverAnimation extends SwitchAnimation
{
  public void destory()
  {
    super.destory();
  }

  protected void doAnimation()
  {
    try
    {
      this.openedView.getView().clearAnimation();
      this.closedView.getView().clearAnimation();
      this.openedView.getView().setVisibility(0);
      this.closedView.getView().setVisibility(0);
      this.closedView.addViewEventListener(this);
      this.container.bringChildToFront(this.closedView.getView());
      this.closedView.setCloseAnimation(getCloseAnimation());
      this.openedView.open(false);
      this.closedView.close(true);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected Animation getCloseAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(0.0F, this.container.getWidth(), 0.0F, 0.0F);
    localTranslateAnimation.setFillAfter(true);
    localTranslateAnimation.setDuration(350L);
    return localTranslateAnimation;
  }

  protected Animation getOpenAnimation()
  {
    TranslateAnimation localTranslateAnimation = new TranslateAnimation(-this.container.getWidth(), 0.0F, 0.0F, 0.0F);
    localTranslateAnimation.setFillAfter(true);
    localTranslateAnimation.setDuration(350L);
    return localTranslateAnimation;
  }

  public void viewDidClosed(IView paramIView)
  {
    this.closedView.removeViewEventListener(this);
    animationEnd();
  }

  public void viewDidOpened(IView paramIView)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.animation.MoveLtoRUncoverAnimation
 * JD-Core Version:    0.6.2
 */