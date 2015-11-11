package com.sina.weibo.sdk.auth;

import android.os.Bundle;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Oauth2AccessToken
{
  private static final String KEY_ACCESS_TOKEN = "access_token";
  private static final String KEY_EXPIRES_IN = "expires_in";
  private static final String KEY_REFRESH_TOKEN = "refresh_token";
  private static final String KEY_UID = "uid";
  private String mAccessToken = "";
  private long mExpiresTime = 0L;
  private String mRefreshToken = "";
  private String mUid = "";

  public Oauth2AccessToken()
  {
  }

  @Deprecated
  public Oauth2AccessToken(String paramString)
  {
    if ((paramString != null) && (paramString.indexOf("{") >= 0));
    try
    {
      paramString = new JSONObject(paramString);
      setUid(paramString.optString("uid"));
      setToken(paramString.optString("access_token"));
      setExpiresIn(paramString.optString("expires_in"));
      setRefreshToken(paramString.optString("refresh_token"));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }

  public Oauth2AccessToken(String paramString1, String paramString2)
  {
    this.mAccessToken = paramString1;
    this.mExpiresTime = System.currentTimeMillis();
    if (paramString2 != null)
      this.mExpiresTime += Long.parseLong(paramString2) * 1000L;
  }

  private static String getString(Bundle paramBundle, String paramString1, String paramString2)
  {
    if (paramBundle != null)
    {
      paramBundle = paramBundle.getString(paramString1);
      if (paramBundle != null)
        return paramBundle;
      return paramString2;
    }
    return paramString2;
  }

  public static Oauth2AccessToken parseAccessToken(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      Oauth2AccessToken localOauth2AccessToken = new Oauth2AccessToken();
      localOauth2AccessToken.setUid(getString(paramBundle, "uid", ""));
      localOauth2AccessToken.setToken(getString(paramBundle, "access_token", ""));
      localOauth2AccessToken.setExpiresIn(getString(paramBundle, "expires_in", ""));
      localOauth2AccessToken.setRefreshToken(getString(paramBundle, "refresh_token", ""));
      return localOauth2AccessToken;
    }
    return null;
  }

  public static Oauth2AccessToken parseAccessToken(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramString.indexOf("{") >= 0))
      try
      {
        paramString = new JSONObject(paramString);
        Oauth2AccessToken localOauth2AccessToken = new Oauth2AccessToken();
        localOauth2AccessToken.setUid(paramString.optString("uid"));
        localOauth2AccessToken.setToken(paramString.optString("access_token"));
        localOauth2AccessToken.setExpiresIn(paramString.optString("expires_in"));
        localOauth2AccessToken.setRefreshToken(paramString.optString("refresh_token"));
        return localOauth2AccessToken;
      }
      catch (JSONException paramString)
      {
        paramString.printStackTrace();
      }
    return null;
  }

  public long getExpiresTime()
  {
    return this.mExpiresTime;
  }

  public String getRefreshToken()
  {
    return this.mRefreshToken;
  }

  public String getToken()
  {
    return this.mAccessToken;
  }

  public String getUid()
  {
    return this.mUid;
  }

  public boolean isSessionValid()
  {
    return !TextUtils.isEmpty(this.mAccessToken);
  }

  public void setExpiresIn(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.equals("0")))
      setExpiresTime(System.currentTimeMillis() + Long.parseLong(paramString) * 1000L);
  }

  public void setExpiresTime(long paramLong)
  {
    this.mExpiresTime = paramLong;
  }

  public void setRefreshToken(String paramString)
  {
    this.mRefreshToken = paramString;
  }

  public void setToken(String paramString)
  {
    this.mAccessToken = paramString;
  }

  public void setUid(String paramString)
  {
    this.mUid = paramString;
  }

  public Bundle toBundle()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("uid", this.mUid);
    localBundle.putString("access_token", this.mAccessToken);
    localBundle.putString("refresh_token", this.mRefreshToken);
    localBundle.putString("expires_in", Long.toString(this.mExpiresTime));
    return localBundle;
  }

  public String toString()
  {
    return "uid: " + this.mUid + ", " + "access_token" + ": " + this.mAccessToken + ", " + "refresh_token" + ": " + this.mRefreshToken + ", " + "expires_in" + ": " + Long.toString(this.mExpiresTime);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.auth.Oauth2AccessToken
 * JD-Core Version:    0.6.2
 */