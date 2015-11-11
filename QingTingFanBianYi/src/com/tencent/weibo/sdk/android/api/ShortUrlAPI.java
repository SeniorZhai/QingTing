package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class ShortUrlAPI extends BaseAPI
{
  private static final String SERVER_URL_LONG2SHORT = "http://open.t.qq.com/api/short_url/shorten";
  private static final String SERVER_URL_SHORT2LONG = "http://open.t.qq.com/api/short_url/expand";

  public ShortUrlAPI(AccountModel paramAccountModel)
  {
    super(paramAccountModel);
  }

  public void expandShortUrl(Context paramContext, String paramString1, String paramString2, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("short_url", paramString2);
    startRequest(paramContext, "http://open.t.qq.com/api/short_url/expand", localReqParam, paramHttpCallback, paramClass, "GET", paramInt);
  }

  public void getShortUrl(Context paramContext, String paramString1, String paramString2, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("long_url", paramString2);
    startRequest(paramContext, "http://open.t.qq.com/api/short_url/shorten", localReqParam, paramHttpCallback, paramClass, "GET", paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.ShortUrlAPI
 * JD-Core Version:    0.6.2
 */