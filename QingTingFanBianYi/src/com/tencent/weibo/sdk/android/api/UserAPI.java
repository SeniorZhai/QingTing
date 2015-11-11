package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class UserAPI extends BaseAPI
{
  private static final String SERVER_URL_USERINFO = "http://open.t.qq.com/api/user/info";
  private static final String SERVER_URL_USERINFOS = "http://open.t.qq.com/api/user/infos";
  private static final String SERVER_URL_USEROTHERINFO = "http://open.t.qq.com/api/user/other_info";

  public UserAPI(AccountModel paramAccountModel)
  {
    super(paramAccountModel);
  }

  public void getUserInfo(Context paramContext, String paramString, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString);
    startRequest(paramContext, "http://open.t.qq.com/api/user/info", localReqParam, paramHttpCallback, paramClass, "GET", paramInt);
  }

  public void getUserInfos(Context paramContext, String paramString1, String paramString2, String paramString3, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    if ((paramString2 != null) && (!"".equals(paramString2)))
      localReqParam.addParam("names", paramString2);
    if ((paramString3 != null) && (!"".equals(paramString3)))
      localReqParam.addParam("fopenids", paramString3);
    startRequest(paramContext, "http://open.t.qq.com/api/user/infos", localReqParam, paramHttpCallback, paramClass, "GET", paramInt);
  }

  public void getUserOtherInfo(Context paramContext, String paramString1, String paramString2, String paramString3, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    if ((paramString2 != null) && (!"".equals(paramString2)))
      localReqParam.addParam("name", paramString2);
    if ((paramString3 != null) && (!"".equals(paramString3)))
      localReqParam.addParam("fopenid", paramString3);
    startRequest(paramContext, "http://open.t.qq.com/api/user/other_info", localReqParam, paramHttpCallback, paramClass, "GET", paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.UserAPI
 * JD-Core Version:    0.6.2
 */