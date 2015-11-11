package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.ReqParam;

public class FriendAPI extends BaseAPI
{
  private static final String SERVER_URL_ADD = "http://open.t.qq.com/api/friends/add";
  private static final String SERVER_URL_CHECK = "http://open.t.qq.com/api/friends/check";
  private static final String SERVER_URL_FANSLIST = "http://open.t.qq.com/api/friends/fanslist";
  private static final String SERVER_URL_GetINTIMATEFRIENDS = "http://open.t.qq.com/api/friends/get_intimate_friends";
  private static final String SERVER_URL_IDOLLIST = "http://open.t.qq.com/api/friends/idollist";
  private static final String SERVER_URL_MUTUALLIST = "http://open.t.qq.com/api/friends/mutual_list";

  public FriendAPI(AccountModel paramAccountModel)
  {
    super(paramAccountModel);
  }

  public void addFriend(Context paramContext, String paramString1, String paramString2, String paramString3, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt)
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
      localReqParam.addParam("fopenids", paramString3);
    startRequest(paramContext, "http://open.t.qq.com/api/friends/add", localReqParam, paramHttpCallback, paramClass, "POST", paramInt);
  }

  public void friendCheck(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt2)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("names", paramString2);
    localReqParam.addParam("fopenids", paramString3);
    localReqParam.addParam("flag", Integer.valueOf(paramInt1));
    startRequest(paramContext, "http://open.t.qq.com/api/friends/check", localReqParam, paramHttpCallback, paramClass, "GET", paramInt2);
  }

  public void friendFansList(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt6)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString);
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt1));
    localReqParam.addParam("startindex", Integer.valueOf(paramInt2));
    localReqParam.addParam("mode", Integer.valueOf(paramInt3));
    localReqParam.addParam("install", Integer.valueOf(paramInt4));
    localReqParam.addParam("sex", Integer.valueOf(paramInt5));
    startRequest(paramContext, "http://open.t.qq.com/api/friends/fanslist", localReqParam, paramHttpCallback, paramClass, "GET", paramInt6);
  }

  public void friendIDolList(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt5)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString);
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt1));
    localReqParam.addParam("startindex", Integer.valueOf(paramInt2));
    localReqParam.addParam("mode", Integer.valueOf(paramInt3));
    localReqParam.addParam("install", Integer.valueOf(paramInt4));
    startRequest(paramContext, "http://open.t.qq.com/api/friends/idollist", localReqParam, paramHttpCallback, paramClass, "GET", paramInt5);
  }

  public void getIntimateFriends(Context paramContext, String paramString, int paramInt1, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt2)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString);
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt1));
    startRequest(paramContext, "http://open.t.qq.com/api/friends/get_intimate_friends", localReqParam, paramHttpCallback, paramClass, "GET", paramInt2);
  }

  public void getMutualList(Context paramContext, String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, int paramInt3, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, int paramInt4)
  {
    ReqParam localReqParam = new ReqParam();
    localReqParam.addParam("scope", "all");
    localReqParam.addParam("clientip", Util.getLocalIPAddress(paramContext));
    localReqParam.addParam("oauth_version", "2.a");
    localReqParam.addParam("oauth_consumer_key", Util.getSharePersistent(paramContext, "CLIENT_ID"));
    localReqParam.addParam("openid", Util.getSharePersistent(paramContext, "OPEN_ID"));
    localReqParam.addParam("format", paramString1);
    localReqParam.addParam("reqnum", Integer.valueOf(paramInt2));
    localReqParam.addParam("install", Integer.valueOf(paramInt3));
    localReqParam.addParam("startindex", Integer.valueOf(paramInt1));
    if ((paramString2 != null) && (!"".equals(paramString2)))
      localReqParam.addParam("name", paramString2);
    if ((paramString3 != null) && (!"".equals(paramString3)))
      localReqParam.addParam("fopenid", paramString3);
    startRequest(paramContext, "http://open.t.qq.com/api/friends/mutual_list", localReqParam, paramHttpCallback, paramClass, "GET", paramInt4);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.FriendAPI
 * JD-Core Version:    0.6.2
 */