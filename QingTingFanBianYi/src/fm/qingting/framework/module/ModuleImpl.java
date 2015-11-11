package fm.qingting.framework.module;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import fm.qingting.framework.event.IEventHandler;

public abstract class ModuleImpl
  implements IModule
{
  protected boolean _activate = false;
  protected Context _context;
  protected IEventHandler _eventHandler;
  protected View _view;

  public ModuleImpl(Context paramContext)
  {
    this._context = paramContext;
  }

  public boolean getActivate()
  {
    return this._activate;
  }

  public String getModuleName()
  {
    return "";
  }

  protected <T extends Enum<T>> T getType(Class<T> paramClass, String paramString)
  {
    try
    {
      paramClass = Enum.valueOf(paramClass, paramString.toUpperCase());
      return paramClass;
    }
    catch (IllegalArgumentException paramClass)
    {
    }
    return null;
  }

  public View getView()
  {
    return this._view;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public void setActivate(boolean paramBoolean)
  {
    this._activate = paramBoolean;
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this._eventHandler = paramIEventHandler;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.module.ModuleImpl
 * JD-Core Version:    0.6.2
 */