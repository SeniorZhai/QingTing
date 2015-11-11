package com.alipay.mobilesecuritysdk.datainfo;

import java.util.ArrayList;
import java.util.List;

public class UploadInfo
{
  private List<AppInfo> appinfos = new ArrayList();
  private List<LocationInfo> locates = new ArrayList();
  private List<String> tid;

  public List<AppInfo> getAppinfos()
  {
    return this.appinfos;
  }

  public List<LocationInfo> getLocates()
  {
    return this.locates;
  }

  public List<String> getTid()
  {
    return this.tid;
  }

  public void setAppinfos(List<AppInfo> paramList)
  {
    this.appinfos = paramList;
  }

  public void setLocates(List<LocationInfo> paramList)
  {
    this.locates = paramList;
  }

  public void setTid(List<String> paramList)
  {
    this.tid = paramList;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.datainfo.UploadInfo
 * JD-Core Version:    0.6.2
 */