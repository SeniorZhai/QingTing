package com.ta.utdid2.device;

public class Device
{
  private String deviceId = "";
  private String imei = "";
  private String imsi = "";
  private long mCheckSum = 0L;
  private long mCreateTimestamp = 0L;
  private String utdid = "";

  long getCheckSum()
  {
    return this.mCheckSum;
  }

  long getCreateTimestamp()
  {
    return this.mCreateTimestamp;
  }

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public String getImei()
  {
    return this.imei;
  }

  public String getImsi()
  {
    return this.imsi;
  }

  public String getUtdid()
  {
    return this.utdid;
  }

  void setCheckSum(long paramLong)
  {
    this.mCheckSum = paramLong;
  }

  void setCreateTimestamp(long paramLong)
  {
    this.mCreateTimestamp = paramLong;
  }

  void setDeviceId(String paramString)
  {
    this.deviceId = paramString;
  }

  void setImei(String paramString)
  {
    this.imei = paramString;
  }

  void setImsi(String paramString)
  {
    this.imsi = paramString;
  }

  void setUtdid(String paramString)
  {
    this.utdid = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.device.Device
 * JD-Core Version:    0.6.2
 */