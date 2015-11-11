package com.sina.weibo.sdk.auth;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.utils.Utility;

public class AuthInfo
{
  private String mAppKey = "";
  private String mKeyHash = "";
  private String mPackageName = "";
  private String mRedirectUrl = "";
  private String mScope = "";

  public AuthInfo(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    this.mAppKey = paramString1;
    this.mRedirectUrl = paramString2;
    this.mScope = paramString3;
    this.mPackageName = paramContext.getPackageName();
    this.mKeyHash = Utility.getSign(paramContext, this.mPackageName);
  }

  public static AuthInfo parseBundleData(Context paramContext, Bundle paramBundle)
  {
    return new AuthInfo(paramContext, paramBundle.getString("appKey"), paramBundle.getString("redirectUri"), paramBundle.getString("scope"));
  }

  public String getAppKey()
  {
    return this.mAppKey;
  }

  public Bundle getAuthBundle()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("appKey", this.mAppKey);
    localBundle.putString("redirectUri", this.mRedirectUrl);
    localBundle.putString("scope", this.mScope);
    localBundle.putString("packagename", this.mPackageName);
    localBundle.putString("key_hash", this.mKeyHash);
    return localBundle;
  }

  public String getKeyHash()
  {
    return this.mKeyHash;
  }

  public String getPackageName()
  {
    return this.mPackageName;
  }

  public String getRedirectUrl()
  {
    return this.mRedirectUrl;
  }

  public String getScope()
  {
    return this.mScope;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.auth.AuthInfo
 * JD-Core Version:    0.6.2
 */