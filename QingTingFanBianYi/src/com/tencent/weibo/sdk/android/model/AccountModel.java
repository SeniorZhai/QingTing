package com.tencent.weibo.sdk.android.model;

public class AccountModel
{
  private String accessToken;
  private long expiresIn;
  private String name;
  private String nike;
  private String openID;
  private String openKey;
  private String refreshToken;

  public AccountModel()
  {
  }

  public AccountModel(String paramString)
  {
    this.accessToken = paramString;
  }

  public String getAccessToken()
  {
    return this.accessToken;
  }

  public long getExpiresIn()
  {
    return this.expiresIn;
  }

  public String getName()
  {
    return this.name;
  }

  public String getNike()
  {
    return this.nike;
  }

  public String getOpenID()
  {
    return this.openID;
  }

  public String getOpenKey()
  {
    return this.openKey;
  }

  public String getRefreshToken()
  {
    return this.refreshToken;
  }

  public void setAccessToken(String paramString)
  {
    this.accessToken = paramString;
  }

  public void setExpiresIn(long paramLong)
  {
    this.expiresIn = paramLong;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setNike(String paramString)
  {
    this.nike = paramString;
  }

  public void setOpenID(String paramString)
  {
    this.openID = paramString;
  }

  public void setOpenKey(String paramString)
  {
    this.openKey = paramString;
  }

  public void setRefreshToken(String paramString)
  {
    this.refreshToken = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.model.AccountModel
 * JD-Core Version:    0.6.2
 */