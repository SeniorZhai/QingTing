package fm.qingting.qtradio.animation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import fm.qingting.framework.controller.SwitchAnimation;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.utils.ScreenConfiguration;

public class SearchSpecialEnterAnimation extends SwitchAnimation
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
      this.openedView.addViewEventListener(this);
      this.container.bringChildToFront(this.openedView.getView());
      this.openedView.setOpenAnimation(getOpenAnimation());
      this.openedView.open(true);
      this.closedView.close(false);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected Animation getOpenAnimation()
  {
    AnimationSet localAnimationSet = new AnimationSet(false);
    ScaleAnimation localScaleAnimation = new ScaleAnimation(1.0F, 1.0F, 1.0F - (ScreenConfiguration.getNaviHeight() + ScreenConfiguration.getMiniHeight()) / ScreenConfiguration.getHeight(), 1.0F, ScreenConfiguration.getWidth() / 2, (ScreenConfiguration.getHeight() - ScreenConfiguration.getMiniHeight() + ScreenConfiguration.getNaviHeight()) / 2);
    localScaleAnimation.setDuration(InfoManager.getInstance().getContext().getResources().getInteger(2131427328));
    localScaleAnimation.setFillAfter(true);
    localScaleAnimation.setInterpolator(InfoManager.getInstance().getContext(), 2131099658);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
    localAlphaAnimation.setDuration(InfoManager.getInstance().getContext().getResources().getInteger(2131427329));
    localAlphaAnimation.setFillAfter(true);
    localAlphaAnimation.setInterpolator(InfoManager.getInstance().getContext(), 2131099656);
    localAnimationSet.addAnimation(localScaleAnimation);
    localAnimationSet.addAnimation(localAlphaAnimation);
    return localAnimationSet;
  }

  public void viewDidClosed(IView paramIView)
  {
  }

  public void viewDidOpened(IView paramIView)
  {
    this.openedView.removeViewEventListener(this);
    animationEnd();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.animation.SearchSpecialEnterAnimation
 * JD-Core Version:    0.6.2
 */