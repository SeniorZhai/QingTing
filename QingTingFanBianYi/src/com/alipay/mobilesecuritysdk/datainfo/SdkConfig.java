package com.alipay.mobilesecuritysdk.datainfo;

public class SdkConfig
{
  private static SdkConfig configSingleton = new SdkConfig();
  private int appInterval;
  private long appLUT;
  private int locateInterval;
  private long locateLUT;
  private int locationMaxLines;
  private int mainSwitchInterval;
  private long mainSwitchLUT;
  private String mainSwitchState;

  public static SdkConfig getInstance()
  {
    return configSingleton;
  }

  public int getAppInterval()
  {
    return this.appInterval;
  }

  public long getAppLUT()
  {
    return this.appLUT;
  }

  public int getLocateInterval()
  {
    return this.locateInterval;
  }

  public long getLocateLUT()
  {
    return this.locateLUT;
  }

  public int getLocationMaxLines()
  {
    return this.locationMaxLines;
  }

  public int getMainSwitchInterval()
  {
    return this.mainSwitchInterval;
  }

  public long getMainSwitchLUT()
  {
    return this.mainSwitchLUT;
  }

  public String getMainSwitchState()
  {
    return this.mainSwitchState;
  }

  public void setAppInterval(int paramInt)
  {
    this.appInterval = paramInt;
  }

  public void setAppLUT(long paramLong)
  {
    this.appLUT = paramLong;
  }

  public void setLocateInterval(int paramInt)
  {
    this.locateInterval = paramInt;
  }

  public void setLocateLUT(long paramLong)
  {
    this.locateLUT = paramLong;
  }

  public void setLocationMaxLines(int paramInt)
  {
    this.locationMaxLines = paramInt;
  }

  public void setMainSwitchInterval(int paramInt)
  {
    this.mainSwitchInterval = paramInt;
  }

  public void setMainSwitchLUT(long paramLong)
  {
    this.mainSwitchLUT = paramLong;
  }

  public void setMainSwitchState(String paramString)
  {
    this.mainSwitchState = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.datainfo.SdkConfig
 * JD-Core Version:    0.6.2
 */