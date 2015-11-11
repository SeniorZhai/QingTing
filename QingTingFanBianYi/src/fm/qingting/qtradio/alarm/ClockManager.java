package fm.qingting.qtradio.alarm;

import android.os.Handler;
import android.os.SystemClock;
import fm.qingting.qtradio.model.Clock;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

public class ClockManager
{
  private static ClockManager instance;
  private boolean alarmAvailable = false;
  private Runnable clockRunnable = new Runnable()
  {
    public void run()
    {
      long l = SystemClock.uptimeMillis();
      int i = (int)(System.currentTimeMillis() / 1000L);
      ClockManager.this.dispatchClockEvent(i);
      if ((ClockManager.this.timerAvailable) && (i >= ClockManager.this.timerTarget))
      {
        ClockManager.this.dispatchTimeEvent(ClockManager.this.timer);
        ClockManager.this.timer.available = false;
        ClockManager.this.refreshTimerAvailable();
      }
      ClockManager.this.handler.postAtTime(ClockManager.this.clockRunnable, l + (1000L - l % 1000L));
    }
  };
  private Handler handler = new Handler();
  private ReferenceQueue<IClockListener> listenerReferenceQueue = new ReferenceQueue();
  private HashSet<WeakReference<IClockListener>> listeners = new HashSet();
  private Clock timer;
  private boolean timerAvailable = false;
  private int timerTarget = 0;

  private ClockManager()
  {
    this.handler.postDelayed(this.clockRunnable, 100L);
  }

  private void dispatchClockEvent(int paramInt)
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (localIterator.hasNext())
    {
      IClockListener localIClockListener = (IClockListener)((WeakReference)localIterator.next()).get();
      if (localIClockListener != null)
        localIClockListener.onClockTime(paramInt);
    }
  }

  private void dispatchTimeEvent(Clock paramClock)
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (localIterator.hasNext())
    {
      IClockListener localIClockListener = (IClockListener)((WeakReference)localIterator.next()).get();
      if (localIClockListener != null)
        localIClockListener.onTime(paramClock);
    }
  }

  private void dispatchTimeStartEvent(Clock paramClock)
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (localIterator.hasNext())
    {
      IClockListener localIClockListener = (IClockListener)((WeakReference)localIterator.next()).get();
      if (localIClockListener != null)
        localIClockListener.onTimeStart(paramClock);
    }
  }

  private void dispatchTimeStopEvent(Clock paramClock)
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (localIterator.hasNext())
    {
      IClockListener localIClockListener = (IClockListener)((WeakReference)localIterator.next()).get();
      if (localIClockListener != null)
        localIClockListener.onTimeStop(paramClock);
    }
  }

  private void dispatchTimerRemovedEvent()
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (localIterator.hasNext())
    {
      IClockListener localIClockListener = (IClockListener)((WeakReference)localIterator.next()).get();
      if (localIClockListener != null)
        localIClockListener.onTimerRemoved();
    }
  }

  public static ClockManager getInstance()
  {
    if (instance == null)
      instance = new ClockManager();
    return instance;
  }

  private void refreshTimerAvailable()
  {
    if ((this.timer == null) || (!this.timer.available))
    {
      this.timerTarget = -1;
      if (this.timerAvailable)
      {
        dispatchTimeStopEvent(this.timer);
        this.timerAvailable = false;
      }
    }
    do
    {
      do
        return;
      while (!this.timer.available);
      int i = (int)(System.currentTimeMillis() / 1000L);
      this.timerTarget = (i + this.timer.getTimeLeft(i));
    }
    while (this.timerAvailable);
    this.timerAvailable = true;
    dispatchTimeStartEvent(this.timer);
  }

  private void removeUnavailableListener()
  {
    while (true)
    {
      Reference localReference = this.listenerReferenceQueue.poll();
      if (localReference == null)
        break;
      this.listeners.remove(localReference);
    }
  }

  public void addListener(IClockListener paramIClockListener)
  {
    removeListener(paramIClockListener);
    this.listeners.add(new WeakReference(paramIClockListener, this.listenerReferenceQueue));
    removeUnavailableListener();
  }

  public void addTimer(Clock paramClock)
  {
    this.timer = paramClock;
    refreshTimerAvailable();
  }

  public boolean getAlarmAvailable()
  {
    return this.alarmAvailable;
  }

  public Clock getTimer()
  {
    return new Clock(this.timer);
  }

  public boolean getTimerAvailable()
  {
    return this.timerAvailable;
  }

  public int getTimerLeft()
  {
    return this.timerTarget - (int)(System.currentTimeMillis() / 1000L);
  }

  public void initClock()
  {
  }

  public void removeListener(IClockListener paramIClockListener)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if ((IClockListener)((WeakReference)localIterator.next()).get() == paramIClockListener)
        localIterator.remove();
    removeUnavailableListener();
  }

  public void removeTimer()
  {
    this.timer = null;
    this.timerAvailable = false;
    refreshTimerAvailable();
    dispatchTimerRemovedEvent();
  }

  public static abstract interface IClockListener
  {
    public abstract void onClockTime(int paramInt);

    public abstract void onTime(Clock paramClock);

    public abstract void onTimeStart(Clock paramClock);

    public abstract void onTimeStop(Clock paramClock);

    public abstract void onTimerRemoved();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.alarm.ClockManager
 * JD-Core Version:    0.6.2
 */