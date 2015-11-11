package org.android.agoo.net.mtop;

import java.util.Map;

public class MtopRequest
{
  public static final String SPLIT_STR = "&";
  private volatile String a;
  private volatile String b;
  private volatile String c;
  private volatile String d;
  private volatile String e;
  private volatile String f;
  private volatile String g;
  private volatile long h = -1L;
  private volatile boolean i = true;
  private String j;
  private Map<String, Object> k = null;
  private Map<String, String> l = null;

  public String getApi()
  {
    return this.a;
  }

  public String getAppKey()
  {
    return this.f;
  }

  public String getAppSecret()
  {
    return this.j;
  }

  public String getDeviceId()
  {
    return this.g;
  }

  public String getEcode()
  {
    return this.e;
  }

  public Map<String, Object> getParams()
  {
    return this.k;
  }

  public String getSId()
  {
    return this.d;
  }

  public Map<String, String> getSysParams()
  {
    return this.l;
  }

  public long getTime()
  {
    return this.h;
  }

  public String getTtId()
  {
    return this.c;
  }

  public String getV()
  {
    return this.b;
  }

  public boolean isHasSigin()
  {
    return this.i;
  }

  public void putParams(String paramString, Object paramObject)
  {
    this.k.put(paramString, paramObject);
  }

  public void putSysParams(String paramString1, String paramString2)
  {
    this.k.put(paramString1, paramString2);
  }

  public void setApi(String paramString)
  {
    this.a = paramString;
  }

  public void setAppKey(String paramString)
  {
    this.f = paramString;
  }

  public void setAppSecret(String paramString)
  {
    this.j = paramString;
  }

  public void setDeviceId(String paramString)
  {
    this.g = paramString;
  }

  public void setEcode(String paramString)
  {
    this.e = paramString;
  }

  public void setHasSigin(boolean paramBoolean)
  {
    this.i = paramBoolean;
  }

  public void setSId(String paramString)
  {
    this.d = paramString;
  }

  public void setTime(long paramLong)
  {
    this.h = paramLong;
  }

  public void setTtId(String paramString)
  {
    this.c = paramString;
  }

  public void setV(String paramString)
  {
    this.b = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.net.mtop.MtopRequest
 * JD-Core Version:    0.6.2
 */