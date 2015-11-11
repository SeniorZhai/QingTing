package com.sina.weibo.sdk.net.openapi;

import android.content.Context;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;

public class RefreshTokenApi
{
  private static final String REFRESH_TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
  private Context mContext;

  private RefreshTokenApi(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }

  public static RefreshTokenApi create(Context paramContext)
  {
    return new RefreshTokenApi(paramContext);
  }

  public void refreshToken(String paramString1, String paramString2, RequestListener paramRequestListener)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters(paramString1);
    localWeiboParameters.put("client_id", paramString1);
    localWeiboParameters.put("grant_type", "refresh_token");
    localWeiboParameters.put("refresh_token", paramString2);
    new AsyncWeiboRunner(this.mContext).requestAsync("https://api.weibo.com/oauth2/access_token", localWeiboParameters, "POST", paramRequestListener);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.net.openapi.RefreshTokenApi
 * JD-Core Version:    0.6.2
 */