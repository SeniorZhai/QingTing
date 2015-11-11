package fm.qingting.framework.module;

import android.view.KeyEvent.Callback;
import android.view.View;
import fm.qingting.framework.event.IEventHandler;

public abstract interface IModule extends KeyEvent.Callback
{
  public abstract boolean getActivate();

  public abstract String getModuleName();

  public abstract View getView();

  public abstract void setActivate(boolean paramBoolean);

  public abstract void setEventHandler(IEventHandler paramIEventHandler);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.module.IModule
 * JD-Core Version:    0.6.2
 */