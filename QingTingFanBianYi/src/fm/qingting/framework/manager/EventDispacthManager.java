package fm.qingting.framework.manager;

import java.util.ArrayList;
import java.util.List;

public enum EventDispacthManager
{
  INSTANCE;

  private List<IActionEventHandler> listeners = null;

  public static EventDispacthManager getInstance()
  {
    return INSTANCE;
  }

  public void addListener(IActionEventHandler paramIActionEventHandler)
  {
    if (this.listeners == null)
      this.listeners = new ArrayList();
    this.listeners.add(paramIActionEventHandler);
  }

  public void dispatchAction(String paramString, Object paramObject)
  {
    if (this.listeners == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < this.listeners.size())
      {
        ((IActionEventHandler)this.listeners.get(i)).onAction(paramString, paramObject);
        i += 1;
      }
    }
  }

  public void removeAll()
  {
    if (this.listeners != null)
    {
      this.listeners.clear();
      this.listeners = null;
    }
  }

  public void removeListener(IActionEventHandler paramIActionEventHandler)
  {
    if (this.listeners == null)
      return;
    this.listeners.remove(paramIActionEventHandler);
  }

  public static abstract interface IActionEventHandler
  {
    public abstract void onAction(String paramString, Object paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.manager.EventDispacthManager
 * JD-Core Version:    0.6.2
 */