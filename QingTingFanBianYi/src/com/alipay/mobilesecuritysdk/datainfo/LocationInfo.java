package com.alipay.mobilesecuritysdk.datainfo;

import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.math.BigDecimal;
import java.util.List;

public class LocationInfo
{
  private final int DEFINE_NUM = 14400;
  private final double MAX_LATITUDE = 90.0D;
  private final double MAX_LONGITUDE = 180.0D;
  private final double MIN_LATITUDE = -90.0D;
  private final double MIN_LONGITUDE = -180.0D;
  private String cid;
  private String lac;
  private String latitude;
  private String longitude;
  private String mcc;
  private String mnc;
  private String phonetype;
  private List<String> tid;
  private String time;
  private List<WifiCollectInfo> wifi;

  private String toString(double paramDouble)
  {
    return String.valueOf(new BigDecimal(paramDouble).setScale(5, 4).doubleValue());
  }

  public String getCid()
  {
    return this.cid;
  }

  public String getLac()
  {
    return this.lac;
  }

  public String getLatitude()
  {
    return this.latitude;
  }

  public String getLongitude()
  {
    return this.longitude;
  }

  public String getMcc()
  {
    return this.mcc;
  }

  public String getMnc()
  {
    return this.mnc;
  }

  public String getPhonetype()
  {
    return this.phonetype;
  }

  public List<String> getTid()
  {
    return this.tid;
  }

  public String getTime()
  {
    return this.time;
  }

  public List<WifiCollectInfo> getWifi()
  {
    return this.wifi;
  }

  public void setCid(String paramString)
  {
    this.cid = paramString;
  }

  public void setLac(String paramString)
  {
    this.lac = paramString;
  }

  public void setLatitude(double paramDouble)
  {
    if ((paramDouble < 90.0D) && (paramDouble > -90.0D))
      this.latitude = toString(paramDouble);
  }

  public void setLatitude(int paramInt)
  {
    setLatitude(paramInt / 14400.0D);
  }

  public void setLatitude(String paramString)
  {
    this.latitude = paramString;
  }

  public void setLongitude(double paramDouble)
  {
    if ((paramDouble < 180.0D) && (paramDouble > -180.0D))
      this.longitude = toString(paramDouble);
  }

  public void setLongitude(int paramInt)
  {
    setLongitude(paramInt / 14400.0D);
  }

  public void setLongitude(String paramString)
  {
    this.longitude = paramString;
  }

  public void setMcc(String paramString)
  {
    this.mcc = paramString;
  }

  public void setMnc(String paramString)
  {
    this.mnc = paramString;
  }

  public void setPhonetype(String paramString)
  {
    this.phonetype = paramString;
  }

  public void setTid(List<String> paramList)
  {
    this.tid = paramList;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }

  public void setWifi(List<WifiCollectInfo> paramList)
  {
    this.wifi = paramList;
  }

  public boolean validate()
  {
    return ((!CommonUtils.isBlank(this.latitude)) && (!CommonUtils.isBlank(this.longitude))) || ((!CommonUtils.isBlank(this.cid)) && (!CommonUtils.isBlank(this.lac)));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.datainfo.LocationInfo
 * JD-Core Version:    0.6.2
 */