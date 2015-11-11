package fm.qingting.framework.utils;

import java.util.ArrayList;
import java.util.List;

public class PerformanceTimer
{
  private List<Long> _lap;
  private long _lastTime;
  private List<Long> _split;
  private boolean _start = false;
  private long _startTime;

  public String getLap()
  {
    return this._lap.toString();
  }

  public String getSplit()
  {
    return this._split.toString();
  }

  public void output()
  {
    output("PerformanceTimer", "time:");
  }

  public void output(String paramString1, String paramString2)
  {
  }

  public void split()
  {
    if (!this._start)
      return;
    long l = System.currentTimeMillis();
    this._lap.add(Long.valueOf(l - this._startTime));
    this._split.add(Long.valueOf(l - this._lastTime));
    this._lastTime = l;
  }

  public void start()
  {
    long l = System.currentTimeMillis();
    this._lastTime = l;
    this._startTime = l;
    this._lap = new ArrayList();
    this._split = new ArrayList();
    this._start = true;
  }

  public void stop()
  {
    this._start = false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.PerformanceTimer
 * JD-Core Version:    0.6.2
 */