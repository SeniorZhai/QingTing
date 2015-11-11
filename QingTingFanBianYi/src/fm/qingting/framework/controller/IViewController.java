package fm.qingting.framework.controller;

import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;

@Deprecated
public abstract interface IViewController
{
  public abstract void attachView(IView paramIView);

  @Deprecated
  public abstract void config(String paramString, Object paramObject);

  public abstract void detachView();

  public abstract Object getValue(String paramString, Object paramObject);

  public abstract IView getView();

  public abstract void setAvailable(boolean paramBoolean);

  public abstract void setEventHandler(IEventHandler paramIEventHandler);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.controller.IViewController
 * JD-Core Version:    0.6.2
 */