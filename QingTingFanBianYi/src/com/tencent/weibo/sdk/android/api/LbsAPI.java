package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class LbsAPI extends BaseAPI
{
  private static final String SERVER_URL_GetAROUNDNEW = "http://open.t.qq.com/api/lbs/get_around_new";
  private static final String SERVER_URL_GetAROUNDPEOPLE = "http://open.t.qq.com/api/lbs/get_around_people";

  public LbsAPI(AccountModel paramAccountModel)
  {
    super(paramAccountModel);
  }

  public void getAroundNew(Context paramContext, String paramString1, double paramDouble1, double paramDouble2, String paramString2, int paramInt1, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt2)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("longitude", Double.valueOf(paramDouble1));
    localReqParam.addParam("latitude", Double.valueOf(paramDouble2));
    localReqParam.addParam("pageinfo", paramString2);
    localReqParam.addParam("pagesize", Integer.valueOf(paramInt1));
    startRequest(paramContext, "http://open.t.qq.com/api/lbs/get_around_new", localReqParam, paramHttpCallback, paramClass, "POST", paramInt2);
  }

  public void getAroundPeople(Context paramContext, String paramString1, double paramDouble1, double paramDouble2, String paramString2, int paramInt1, int paramInt2, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt3)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("longitude", Double.valueOf(paramDouble1));
    localReqParam.addParam("latitude", Double.valueOf(paramDouble2));
    localReqParam.addParam("pageinfo", paramString2);
    localReqParam.addParam("pagesize", Integer.valueOf(paramInt1));
    localReqParam.addParam("gender", Integer.valueOf(paramInt2));
    startRequest(paramContext, "http://open.t.qq.com/api/lbs/get_around_people", localReqParam, paramHttpCallback, paramClass, "POST", paramInt3);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.LbsAPI
 * JD-Core Version:    0.6.2
 */