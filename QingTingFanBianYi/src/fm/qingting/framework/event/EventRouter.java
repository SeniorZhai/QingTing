package fm.qingting.framework.event;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class EventRouter
{
  private static EventRouter _instance;
  private Map<String, Set<IEventHandler>> _handlerMap = new HashMap();

  private void checkMap(String paramString)
  {
    if (!this._handlerMap.containsKey(paramString))
      this._handlerMap.put(paramString, new HashSet());
  }

  public static EventRouter getInstance()
  {
    try
    {
      if (_instance == null)
        _instance = new EventRouter();
      EventRouter localEventRouter = _instance;
      return localEventRouter;
    }
    finally
    {
    }
  }

  public void addEventListener(String paramString, IEventHandler paramIEventHandler)
  {
    if ((paramIEventHandler == null) || (paramString == null))
      throw new NullTypeHandlerException(null);
    synchronized (this._handlerMap)
    {
      checkMap(paramString);
      ((Set)this._handlerMap.get(paramString)).add(paramIEventHandler);
      return;
    }
  }

  public void dispatchEvent(AppEvent paramAppEvent)
  {
    if (this._handlerMap.get(paramAppEvent.type) == null);
    while (true)
    {
      return;
      HashSet localHashSet = new HashSet();
      synchronized (this._handlerMap)
      {
        localHashSet.addAll((Collection)this._handlerMap.get(paramAppEvent.type));
        ??? = localHashSet.iterator();
        if (!((Iterator)???).hasNext())
          continue;
        ((IEventHandler)((Iterator)???).next()).onEvent(paramAppEvent.source, paramAppEvent.type, paramAppEvent.param);
      }
    }
  }

  public void removeEventListener(String paramString, IEventHandler paramIEventHandler)
  {
    synchronized (this._handlerMap)
    {
      checkMap(paramString);
      ((Set)this._handlerMap.get(paramString)).remove(paramIEventHandler);
      return;
    }
  }

  private class NullTypeHandlerException extends RuntimeException
  {
    private static final long serialVersionUID = 7009684814333539406L;

    private NullTypeHandlerException()
    {
    }

    public String getMessage()
    {
      return "type or handler is null";
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.event.EventRouter
 * JD-Core Version:    0.6.2
 */