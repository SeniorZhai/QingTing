package com.alipay.mobilesecuritysdk.deviceID;

import java.util.List;

public class IdResponseInfo
{
  private List<String> arrList;
  private String fuction;
  private String mapdid;
  private String mapdtk;
  private String mcheckcode;
  private String merrorcode;
  private String mrule;
  private boolean msuccess;
  private String mtime;
  private String mversion;

  public List<String> getArrList()
  {
    return this.arrList;
  }

  public String getFuction()
  {
    return this.fuction;
  }

  public String getMapdid()
  {
    return this.mapdid;
  }

  public String getMapdtk()
  {
    return this.mapdtk;
  }

  public String getMcheckcode()
  {
    return this.mcheckcode;
  }

  public String getMrule()
  {
    return this.mrule;
  }

  public String getMtime()
  {
    return this.mtime;
  }

  public String getMversion()
  {
    return this.mversion;
  }

  public String isMerrorcode()
  {
    return this.merrorcode;
  }

  public boolean isMsuccess()
  {
    return this.msuccess;
  }

  public void setArrList(List<String> paramList)
  {
    this.arrList = paramList;
  }

  public void setFuction(String paramString)
  {
    this.fuction = paramString;
  }

  public void setMapdid(String paramString)
  {
    this.mapdid = paramString;
  }

  public void setMapdtk(String paramString)
  {
    this.mapdtk = paramString;
  }

  public void setMcheckcode(String paramString)
  {
    this.mcheckcode = paramString;
  }

  public void setMerrorcode(String paramString)
  {
    this.merrorcode = paramString;
  }

  public void setMrule(String paramString)
  {
    this.mrule = paramString;
  }

  public void setMsuccess(boolean paramBoolean)
  {
    this.msuccess = paramBoolean;
  }

  public void setMtime(String paramString)
  {
    this.mtime = paramString;
  }

  public void setMversion(String paramString)
  {
    this.mversion = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.IdResponseInfo
 * JD-Core Version:    0.6.2
 */