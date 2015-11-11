package com.sina.weibo.sdk.net.openapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;

public class ShareWeiboApi
{
  private static final String REPOST_URL = "https://api.weibo.com/2/statuses/repost.json";
  private static final String TAG = ShareWeiboApi.class.getName();
  private static final String UPDATE_URL = "https://api.weibo.com/2/statuses/update.json";
  private static final String UPLOAD_URL = "https://api.weibo.com/2/statuses/upload.json";
  private String mAccessToken;
  private String mAppKey;
  private Context mContext;

  private ShareWeiboApi(Context paramContext, String paramString1, String paramString2)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mAppKey = paramString1;
    this.mAccessToken = paramString2;
  }

  private WeiboParameters buildUpdateParams(String paramString1, String paramString2, String paramString3)
  {
    WeiboParameters localWeiboParameters = new WeiboParameters(this.mAppKey);
    localWeiboParameters.put("status", paramString1);
    if (!TextUtils.isEmpty(paramString3))
      localWeiboParameters.put("long", paramString3);
    if (!TextUtils.isEmpty(paramString2))
      localWeiboParameters.put("lat", paramString2);
    if (!TextUtils.isEmpty(this.mAppKey))
      localWeiboParameters.put("source", this.mAppKey);
    return localWeiboParameters;
  }

  public static ShareWeiboApi create(Context paramContext, String paramString1, String paramString2)
  {
    return new ShareWeiboApi(paramContext, paramString1, paramString2);
  }

  private void requestAsync(String paramString1, WeiboParameters paramWeiboParameters, String paramString2, RequestListener paramRequestListener)
  {
    if ((TextUtils.isEmpty(this.mAccessToken)) || (TextUtils.isEmpty(paramString1)) || (paramWeiboParameters == null) || (TextUtils.isEmpty(paramString2)) || (paramRequestListener == null))
    {
      LogUtil.e(TAG, "Argument error!");
      return;
    }
    paramWeiboParameters.put("access_token", this.mAccessToken);
    new AsyncWeiboRunner(this.mContext).requestAsync(paramString1, paramWeiboParameters, paramString2, paramRequestListener);
  }

  public void repost(String paramString1, String paramString2, int paramInt, RequestListener paramRequestListener)
  {
    paramString2 = buildUpdateParams(paramString2, null, null);
    paramString2.put("id", paramString1);
    paramString2.put("is_comment", String.valueOf(paramInt));
    requestAsync("https://api.weibo.com/2/statuses/repost.json", paramString2, "POST", paramRequestListener);
  }

  public void update(String paramString1, String paramString2, String paramString3, RequestListener paramRequestListener)
  {
    requestAsync("https://api.weibo.com/2/statuses/update.json", buildUpdateParams(paramString1, paramString2, paramString3), "POST", paramRequestListener);
  }

  public void upload(String paramString1, Bitmap paramBitmap, String paramString2, String paramString3, RequestListener paramRequestListener)
  {
    paramString1 = buildUpdateParams(paramString1, paramString2, paramString3);
    paramString1.put("pic", paramBitmap);
    requestAsync("https://api.weibo.com/2/statuses/upload.json", paramString1, "POST", paramRequestListener);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.net.openapi.ShareWeiboApi
 * JD-Core Version:    0.6.2
 */