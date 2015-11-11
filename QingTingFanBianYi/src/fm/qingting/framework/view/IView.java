package fm.qingting.framework.view;

import android.graphics.Point;
import android.view.View;
import android.view.animation.Animation;
import fm.qingting.framework.event.IEventHandler;

public abstract interface IView extends ITransparentView, IScaleView
{
  public abstract void addViewEventListener(IViewEventListener paramIViewEventListener);

  public abstract void close(boolean paramBoolean);

  public abstract boolean getActivate();

  public abstract Point getCenterPoint();

  @Deprecated
  public abstract Object getValue(String paramString, Object paramObject);

  public abstract View getView();

  public abstract boolean isAnimating();

  public abstract boolean isOpened();

  public abstract void open(boolean paramBoolean);

  public abstract void removeViewEventListener(IViewEventListener paramIViewEventListener);

  public abstract void setActivate(boolean paramBoolean);

  public abstract void setCloseAnimation(Animation paramAnimation);

  public abstract void setEventHandler(IEventHandler paramIEventHandler);

  public abstract void setOpenAnimation(Animation paramAnimation);

  public abstract void update(String paramString, Object paramObject);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.IView
 * JD-Core Version:    0.6.2
 */