package fm.qingting.qtradio.sensor;

import android.content.Context;

public class SpeedSensor
{
  private static SpeedSensor _instance = null;
  private final long DRIVING = 6L;
  private Context mContext;
  private double mCurrXACC = 0.0D;
  private double mCurrXSpeed = 0.0D;
  private double mCurrYACC = 0.0D;
  private double mCurrYSpeed = 0.0D;
  private double mCurrZACC = 0.0D;
  private double mCurrZSpeed = 0.0D;
  private long mLastTime = 0L;
  private double mLastXACC = 0.0D;
  private double mLastYACC = 0.0D;
  private double mLastZACC = 0.0D;
  private float mXGravity = 0.0F;
  private float mYGravity = 0.0F;
  private float mZGravity = 0.0F;

  public static SpeedSensor getInstance()
  {
    if (_instance == null)
      _instance = new SpeedSensor();
    return _instance;
  }

  public void init(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public boolean isDriving()
  {
    return false;
  }

  public void run()
  {
    if (this.mContext == null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.sensor.SpeedSensor
 * JD-Core Version:    0.6.2
 */