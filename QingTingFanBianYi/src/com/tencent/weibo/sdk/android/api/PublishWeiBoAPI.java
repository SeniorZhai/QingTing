package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class PublishWeiBoAPI extends BaseAPI
{
  public static final String MUTUAL_LIST_URL = "http://open.t.qq.com/api/friends/mutual_list";
  public static final String RECENT_USED_URL = "http://open.t.qq.com/api/ht/recent_used";

  public PublishWeiBoAPI(AccountModel paramAccountModel)
  {
    super(paramAccountModel);
  }

  public void mutual_list(Context paramContext, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("format", "json");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    if (paramInt1 != 0)
      localReqParam.addParam("fopenid", Integer.valueOf(paramInt1));
    localReqParam.addParam("startindex", Integer.valueOf(paramInt2));
    localReqParam.addParam("install", Integer.valueOf(paramInt3));
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt4));
    localReqParam.addParam("name", Util.getSharePersistent(paramContext, "NAME"));
    startRequest(paramContext, "http://open.t.qq.com/api/friends/mutual_list", localReqParam, paramHttpCallback, paramClass, "GET", 4);
  }

  public void recent_used(Context paramContext, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt1, int paramInt2, int paramInt3)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("format", "json");
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt1));
    localReqParam.addParam("page", Integer.valueOf(paramInt2));
    localReqParam.addParam("sorttype", Integer.valueOf(paramInt3));
    startRequest(paramContext, "http://open.t.qq.com/api/ht/recent_used", localReqParam, paramHttpCallback, paramClass, "GET", 4);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.PublishWeiBoAPI
 * JD-Core Version:    0.6.2
 */