package fm.qingting.qtradio.manager.advertisement.qtsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;

public class QTShakeDetectorManage
  implements SensorEventListener
{
  static final int UPDATE_INTERVAL = 100;
  public static QTShakeDetectorManage instance;
  private HashSet<WeakReference<OnShakeListener>> listeners = new HashSet();
  private Context mContentContext;
  long mLastUpdateTime;
  float mLastX;
  float mLastY;
  float mLastZ;
  private SensorManager mSensorManager;
  public int shakeThreshold = 3000;

  public static QTShakeDetectorManage getInstance()
  {
    if (instance == null)
      instance = new QTShakeDetectorManage();
    return instance;
  }

  public void addListener(OnShakeListener paramOnShakeListener)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if (((WeakReference)localIterator.next()).get() == paramOnShakeListener)
        return;
    this.listeners.add(new WeakReference(paramOnShakeListener));
  }

  public void dispatchSensorEvent()
  {
    removeUnavailableListener();
    Iterator localIterator = new HashSet(this.listeners).iterator();
    while (localIterator.hasNext())
    {
      OnShakeListener localOnShakeListener = (OnShakeListener)((WeakReference)localIterator.next()).get();
      if (localOnShakeListener != null)
        localOnShakeListener.onShake();
    }
  }

  public void initSensor(Context paramContext)
  {
    try
    {
      this.mContentContext = paramContext;
      this.mSensorManager = ((SensorManager)this.mContentContext.getSystemService("sensor"));
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public boolean isNeedGetSensorManage()
  {
    return this.mSensorManager == null;
  }

  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    long l1 = System.currentTimeMillis();
    long l2 = l1 - this.mLastUpdateTime;
    if (l2 < 100L)
      return;
    this.mLastUpdateTime = l1;
    float f1 = paramSensorEvent.values[0];
    float f2 = paramSensorEvent.values[1];
    float f3 = paramSensorEvent.values[2];
    float f4 = f1 - this.mLastX;
    float f5 = f2 - this.mLastY;
    float f6 = f3 - this.mLastZ;
    this.mLastX = f1;
    this.mLastY = f2;
    this.mLastZ = f3;
    f1 = FloatMath.sqrt(f4 * f4 + f5 * f5 + f6 * f6) / (float)l2 * 10000.0F;
    if (f1 > this.shakeThreshold)
      dispatchSensorEvent();
    Log.d("mSensorManager", String.valueOf(f1) + "  " + "Running");
  }

  public void removeListener(OnShakeListener paramOnShakeListener)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if (((WeakReference)localIterator.next()).get() == paramOnShakeListener)
        localIterator.remove();
  }

  public void removeUnavailableListener()
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if ((OnShakeListener)((WeakReference)localIterator.next()).get() == null)
        localIterator.remove();
  }

  public void start()
  {
    this.mLastUpdateTime = 0L;
    if ((this.mSensorManager == null) && (this.mContentContext != null));
    try
    {
      this.mSensorManager = ((SensorManager)this.mContentContext.getSystemService("sensor"));
      Sensor localSensor = this.mSensorManager.getDefaultSensor(1);
      if (localSensor == null);
      while (this.mSensorManager.registerListener(this, localSensor, 1))
        return;
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void stop()
  {
    try
    {
      if (this.mSensorManager != null)
        this.mSensorManager.unregisterListener(this);
      this.mSensorManager = null;
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public static abstract interface OnShakeListener
  {
    public abstract void onShake();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.advertisement.qtsensor.QTShakeDetectorManage
 * JD-Core Version:    0.6.2
 */