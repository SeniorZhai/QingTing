package fm.qingting.framework.tween;

import android.os.Handler;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SyncFrameTimer
{
  private static SyncFrameTimer _instance;
  private Set<IFrameHandler> _handlerSet;
  Handler handler;
  private byte[] lock = new byte[0];
  private int period = 0;
  Runnable r;

  private SyncFrameTimer(final int paramInt)
  {
    this.period = paramInt;
    this._handlerSet = new HashSet();
    this.handler = new Handler();
    this.r = new Runnable()
    {
      public void run()
      {
        SyncFrameTimer.this.handler.postDelayed(SyncFrameTimer.this.r, paramInt);
        SyncFrameTimer.this.sendFrameNotify();
      }
    };
    this.handler.postDelayed(this.r, paramInt);
  }

  public static SyncFrameTimer getInstance()
  {
    try
    {
      SyncFrameTimer localSyncFrameTimer = getInstance(40);
      return localSyncFrameTimer;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static SyncFrameTimer getInstance(int paramInt)
  {
    try
    {
      if (_instance == null)
        _instance = new SyncFrameTimer(paramInt);
      SyncFrameTimer localSyncFrameTimer = _instance;
      return localSyncFrameTimer;
    }
    finally
    {
    }
  }

  private void sendFrameNotify()
  {
    while (true)
    {
      try
      {
        HashSet localHashSet = new HashSet();
        synchronized (this.lock)
        {
          localHashSet.addAll(this._handlerSet);
          ??? = localHashSet.iterator();
          boolean bool = ((Iterator)???).hasNext();
          if (!bool)
            return;
        }
      }
      finally
      {
      }
      IFrameHandler localIFrameHandler = (IFrameHandler)localObject2.next();
      if (localIFrameHandler != null)
        localIFrameHandler.OnFrame();
    }
  }

  public void addListener(IFrameHandler paramIFrameHandler)
  {
    try
    {
      synchronized (this.lock)
      {
        this._handlerSet.add(paramIFrameHandler);
        this.handler.removeCallbacks(this.r);
        this.handler.postDelayed(this.r, this.period);
        return;
      }
    }
    finally
    {
    }
  }

  public int getPeriod()
  {
    return this.period;
  }

  public void removeListener(IFrameHandler paramIFrameHandler)
  {
    try
    {
      synchronized (this.lock)
      {
        this._handlerSet.remove(paramIFrameHandler);
        if (this._handlerSet.size() == 0)
          this.handler.removeCallbacks(this.r);
        return;
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.tween.SyncFrameTimer
 * JD-Core Version:    0.6.2
 */