package com.alipay.mobilesecuritysdk.datainfo;

import com.alipay.mobilesecuritysdk.util.CommonUtils;

public class AppInfo
{
  private String pkeyhash;
  private String pkgName;

  public String getPkeyhash()
  {
    return this.pkeyhash;
  }

  public String getPkgName()
  {
    return this.pkgName;
  }

  public void setPkeyhash(String paramString)
  {
    this.pkeyhash = paramString;
  }

  public void setPkgName(String paramString)
  {
    this.pkgName = paramString;
  }

  public boolean validate()
  {
    return (!CommonUtils.isBlank(this.pkgName)) && (!CommonUtils.isBlank(this.pkeyhash));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.datainfo.AppInfo
 * JD-Core Version:    0.6.2
 */