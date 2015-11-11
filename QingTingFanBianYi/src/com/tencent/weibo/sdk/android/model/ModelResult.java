package com.tencent.weibo.sdk.android.model;

import java.util.List;

public class ModelResult
{
  private String error_message = "success";
  private boolean isExpires = false;
  private boolean isLastPage;
  private String lat;
  private List<BaseVO> list;
  private String lon;
  private Object obj;
  private int p;
  private int ps;
  private boolean success = true;
  private int total;

  public void add(BaseVO paramBaseVO)
  {
    this.list.add(paramBaseVO);
  }

  public BaseVO get()
  {
    return (BaseVO)this.list.get(0);
  }

  public String getError_message()
  {
    return this.error_message;
  }

  public String getLat()
  {
    return this.lat;
  }

  public List<BaseVO> getList()
  {
    return this.list;
  }

  public String getLon()
  {
    return this.lon;
  }

  public Object getObj()
  {
    return this.obj;
  }

  public int getP()
  {
    return this.p;
  }

  public int getPs()
  {
    return this.ps;
  }

  public int getTotal()
  {
    return this.total;
  }

  public boolean isExpires()
  {
    return this.isExpires;
  }

  public boolean isLastPage()
  {
    return this.isLastPage;
  }

  public boolean isSuccess()
  {
    return this.success;
  }

  public void setError_message(String paramString)
  {
    this.error_message = paramString;
  }

  public void setExpires(boolean paramBoolean)
  {
    this.isExpires = paramBoolean;
  }

  public void setLastPage(boolean paramBoolean)
  {
    this.isLastPage = paramBoolean;
  }

  public void setLat(String paramString)
  {
    this.lat = paramString;
  }

  public void setList(List<BaseVO> paramList)
  {
    this.list = paramList;
  }

  public void setLon(String paramString)
  {
    this.lon = paramString;
  }

  public void setObj(Object paramObject)
  {
    this.obj = paramObject;
  }

  public void setP(int paramInt)
  {
    this.p = paramInt;
  }

  public void setPs(int paramInt)
  {
    this.ps = paramInt;
  }

  public void setSuccess(boolean paramBoolean)
  {
    this.success = paramBoolean;
  }

  public void setTotal(int paramInt)
  {
    this.total = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.model.ModelResult
 * JD-Core Version:    0.6.2
 */