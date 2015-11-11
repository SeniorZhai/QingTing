package com.tencent.connect.auth;

public class QQToken
{
  public static final int AUTH_QQ = 2;
  public static final int AUTH_QZONE = 3;
  public static final int AUTH_WEB = 1;
  private String a;
  private String b;
  private String c;
  private int d = 1;
  private long e = -1L;

  public QQToken(String paramString)
  {
    this.a = paramString;
  }

  public String getAccessToken()
  {
    return this.b;
  }

  public String getAppId()
  {
    return this.a;
  }

  public int getAuthSource()
  {
    return this.d;
  }

  public long getExpireTimeInSecond()
  {
    return this.e;
  }

  public String getOpenId()
  {
    return this.c;
  }

  public boolean isSessionValid()
  {
    return (this.b != null) && (System.currentTimeMillis() < this.e);
  }

  public void setAccessToken(String paramString1, String paramString2)
    throws NumberFormatException
  {
    this.b = paramString1;
    this.e = 0L;
    if (paramString2 != null)
      this.e = (System.currentTimeMillis() + Long.parseLong(paramString2) * 1000L);
  }

  public void setAppId(String paramString)
  {
    this.a = paramString;
  }

  public void setAuthSource(int paramInt)
  {
    this.d = paramInt;
  }

  public void setOpenId(String paramString)
  {
    this.c = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.auth.QQToken
 * JD-Core Version:    0.6.2
 */