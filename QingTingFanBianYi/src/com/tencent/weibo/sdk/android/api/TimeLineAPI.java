package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class TimeLineAPI extends BaseAPI
{
  private static final String SERVER_URL_HOMETIMELINE = "http://open.t.qq.com/api/statuses/home_timeline";
  private static final String SERVER_URL_HTTIMELINE = "http://open.t.qq.com/api/statuses/ht_timeline_ext";
  private static final String SERVER_URL_USERTIMELINE = "http://open.t.qq.com/api/statuses/user_timeline";

  public TimeLineAPI(AccountModel paramAccountModel)
  {
    super(paramAccountModel);
  }

  public void getHTTimeLine(Context paramContext, String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, String paramString4, String paramString5, int paramInt4, int paramInt5, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt6)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("pageflag", Integer.valueOf(paramInt2));
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt1));
    localReqParam.addParam("tweetid", paramString2);
    localReqParam.addParam("time", paramString3);
    localReqParam.addParam("flag", Integer.valueOf(paramInt3));
    if ((paramString4 != null) && (!"".equals(paramString4)))
      localReqParam.addParam("httext", paramString4);
    if ((paramString5 != null) && (!"".equals(paramString5)) && (!"0".equals(paramString5)))
      localReqParam.addParam("htid", paramString5);
    localReqParam.addParam("type", Integer.valueOf(paramInt4));
    localReqParam.addParam("contenttype", Integer.valueOf(paramInt5));
    startRequest(paramContext, "http://open.t.qq.com/api/statuses/ht_timeline_ext", localReqParam, paramHttpCallback, paramClass, "GET", paramInt6);
  }

  public void getHomeTimeLine(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt6)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString);
    localReqParam.addParam("pageflag", Integer.valueOf(paramInt1));
    localReqParam.addParam("type", Integer.valueOf(paramInt4));
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt3));
    localReqParam.addParam("pagetime", Integer.valueOf(paramInt2));
    localReqParam.addParam("contenttype", Integer.valueOf(paramInt5));
    startRequest(paramContext, "http://open.t.qq.com/api/statuses/home_timeline", localReqParam, paramHttpCallback, paramClass, "GET", paramInt6);
  }

  public void getUserTimeLine(Context paramContext, int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString1, String paramString2, int paramInt5, int paramInt6, String paramString3, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt7)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString3);
    localReqParam.addParam("pageflag", Integer.valueOf(paramInt1));
    localReqParam.addParam("pagetime", Integer.valueOf(paramInt2));
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt3));
    localReqParam.addParam("lastid", Integer.valueOf(paramInt4));
    if ((paramString1 != null) && (!"".equals(paramString1)))
      localReqParam.addParam("name", paramString1);
    if ((paramString2 != null) && (!"".equals(paramString2)))
      localReqParam.addParam("fopenid", paramString2);
    localReqParam.addParam("type", Integer.valueOf(paramInt5));
    localReqParam.addParam("contenttype", Integer.valueOf(paramInt6));
    startRequest(paramContext, "http://open.t.qq.com/api/statuses/user_timeline", localReqParam, paramHttpCallback, paramClass, "GET", paramInt7);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.TimeLineAPI
 * JD-Core Version:    0.6.2
 */