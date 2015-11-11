package fm.qingting.framework.tween;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class AsyncFrameTimer
{
  private static AsyncFrameTimer _instance;
  private Map<IFrameHandler, Object> _handlerSet;
  private Handler _mainLoopHandler;
  private Runnable frameTask;
  private byte[] lock = new byte[0];
  private int period = 0;
  private Handler timerHandler;

  private AsyncFrameTimer(int paramInt)
  {
    this.period = paramInt;
    HandlerThread localHandlerThread = new HandlerThread("asyncTimerThread");
    localHandlerThread.start();
    this._mainLoopHandler = new MainLoopHandler(Looper.getMainLooper());
    this.timerHandler = new Handler(localHandlerThread.getLooper());
    this._handlerSet = new WeakHashMap();
    this.frameTask = new Runnable()
    {
      public void run()
      {
        AsyncFrameTimer.this.sendTweenNotify();
        AsyncFrameTimer.this.timerHandler.postDelayed(AsyncFrameTimer.this.frameTask, AsyncFrameTimer.this.period);
      }
    };
  }

  public static AsyncFrameTimer getInstance()
  {
    try
    {
      AsyncFrameTimer localAsyncFrameTimer = getInstance(20);
      return localAsyncFrameTimer;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static AsyncFrameTimer getInstance(int paramInt)
  {
    try
    {
      if (_instance == null)
        _instance = new AsyncFrameTimer(paramInt);
      AsyncFrameTimer localAsyncFrameTimer = _instance;
      return localAsyncFrameTimer;
    }
    finally
    {
    }
  }

  private void sendTweenNotify()
  {
    try
    {
      this._mainLoopHandler.sendEmptyMessage(0);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addListener(IFrameHandler paramIFrameHandler)
  {
    try
    {
      synchronized (this.lock)
      {
        this._handlerSet.put(paramIFrameHandler, null);
        this.timerHandler.removeCallbacks(this.frameTask);
        this.timerHandler.post(this.frameTask);
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
          this.timerHandler.removeCallbacks(this.frameTask);
        return;
      }
    }
    finally
    {
    }
  }

  private class MainLoopHandler extends Handler
  {
    public MainLoopHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      paramMessage = new HashMap();
      while (true)
      {
        synchronized (AsyncFrameTimer.this.lock)
        {
          paramMessage.putAll(AsyncFrameTimer.this._handlerSet);
          ??? = paramMessage.keySet().iterator();
          if (!((Iterator)???).hasNext())
          {
            paramMessage.clear();
            return;
          }
        }
        IFrameHandler localIFrameHandler = (IFrameHandler)((Iterator)???).next();
        if (localIFrameHandler != null)
          localIFrameHandler.OnFrame();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.tween.AsyncFrameTimer
 * JD-Core Version:    0.6.2
 */