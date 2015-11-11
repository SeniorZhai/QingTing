package com.tencent.weibo.sdk.android.api;

import android.content.Context;
import android.util.Log;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.BaseVO;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;
import com.tencent.weibo.sdk.android.network.HttpReqWeiBo;
import com.tencent.weibo.sdk.android.network.HttpService;
import com.tencent.weibo.sdk.android.network.ReqParam;
import java.io.PrintStream;

public abstract class BaseAPI
{
  public static final String API_SERVER = "http://open.t.qq.com/api";
  public static final String HTTP_REQUEST_METHOD_GET = "GET";
  public static final String HTTP_REQUEST_METHOD_POST = "POST";
  private HttpCallback callback = new HttpCallback()
  {
    public void onResult(Object paramAnonymousObject)
    {
      Log.d("sss", paramAnonymousObject);
      if (paramAnonymousObject != null)
      {
        Object localObject = ((ModelResult)paramAnonymousObject).getObj().toString().split("&");
        paramAnonymousObject = localObject[0].split("=")[1];
        BaseAPI.this.mAccessToken = paramAnonymousObject;
        String str1 = localObject[1].split("=")[1];
        String str2 = localObject[2].split("=")[1];
        String str3 = localObject[3].split("=")[1];
        String str4 = localObject[4].split("=")[1];
        localObject = localObject[5].split("=")[1];
        Util.saveSharePersistent(BaseAPI.this.mContext, "ACCESS_TOKEN", paramAnonymousObject);
        Util.saveSharePersistent(BaseAPI.this.mContext, "EXPIRES_IN", str1);
        Util.saveSharePersistent(BaseAPI.this.mContext, "OPEN_ID", str3);
        Util.saveSharePersistent(BaseAPI.this.mContext, "REFRESH_TOKEN", str2);
        Util.saveSharePersistent(BaseAPI.this.mContext, "NAME", str4);
        Util.saveSharePersistent(BaseAPI.this.mContext, "NICK", (String)localObject);
        Util.saveSharePersistent(BaseAPI.this.mContext, "AUTHORIZETIME", String.valueOf(System.currentTimeMillis() / 1000L));
        BaseAPI.this.weibo = new HttpReqWeiBo(BaseAPI.this.mContext, BaseAPI.this.mRequestUrl, BaseAPI.this.mmCallBack, BaseAPI.this.mmTargetClass, BaseAPI.this.mRequestMethod, Integer.valueOf(BaseAPI.this.mResultType));
        BaseAPI.this.mParams.addParam("access_token", BaseAPI.this.mAccessToken);
        BaseAPI.this.weibo.setParam(BaseAPI.this.mParams);
        HttpService.getInstance().addImmediateReq(BaseAPI.this.weibo);
      }
    }
  };
  private String mAccessToken;
  private AccountModel mAccount;
  private Context mContext;
  private ReqParam mParams;
  private String mRequestMethod;
  private String mRequestUrl;
  private int mResultType;
  private HttpCallback mmCallBack;
  private Class<? extends BaseVO> mmTargetClass;
  private HttpReqWeiBo weibo;

  public BaseAPI(AccountModel paramAccountModel)
  {
    this.mAccount = paramAccountModel;
    if (this.mAccount != null)
      this.mAccessToken = this.mAccount.getAccessToken();
  }

  private ReqParam refreshToken(Context paramContext)
  {
    ReqParam localReqParam = new ReqParam();
    String str = Util.getSharePersistent(paramContext, "CLIENT_ID");
    paramContext = Util.getSharePersistent(paramContext, "REFRESH_TOKEN");
    localReqParam.addParam("client_id", str);
    localReqParam.addParam("grant_type", "refresh_token");
    localReqParam.addParam("refresh_token", paramContext);
    localReqParam.addParam("state", Integer.valueOf((int)Math.random() * 1000 + 111));
    return localReqParam;
  }

  public boolean isAuthorizeExpired(Context paramContext)
  {
    boolean bool2 = false;
    String str = Util.getSharePersistent(paramContext, "AUTHORIZETIME");
    System.out.println("===== : " + str);
    paramContext = Util.getSharePersistent(paramContext, "EXPIRES_IN");
    System.out.println("====== : " + paramContext);
    long l = System.currentTimeMillis() / 1000L;
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (str != null)
      {
        bool1 = bool2;
        if (Long.valueOf(str).longValue() + Long.valueOf(paramContext).longValue() < l)
          bool1 = true;
      }
    }
    return bool1;
  }

  protected void startRequest(Context paramContext, String paramString1, ReqParam paramReqParam, HttpCallback paramHttpCallback, Class<? extends BaseVO> paramClass, String paramString2, int paramInt)
  {
    if (isAuthorizeExpired(paramContext))
    {
      this.mContext = paramContext;
      this.mRequestUrl = paramString1;
      this.mParams = paramReqParam;
      this.mmCallBack = paramHttpCallback;
      this.mmTargetClass = paramClass;
      this.mRequestMethod = paramString2;
      this.mResultType = paramInt;
      this.weibo = new HttpReqWeiBo(paramContext, "http://open.t.qq.com/cgi-bin/oauth2/access_token", this.callback, null, "GET", Integer.valueOf(4));
      this.weibo.setParam(refreshToken(paramContext));
      HttpService.getInstance().addImmediateReq(this.weibo);
      return;
    }
    this.weibo = new HttpReqWeiBo(paramContext, paramString1, paramHttpCallback, paramClass, paramString2, Integer.valueOf(paramInt));
    paramReqParam.addParam("access_token", this.mAccessToken);
    this.weibo.setParam(paramReqParam);
    HttpService.getInstance().addImmediateReq(this.weibo);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.BaseAPI
 * JD-Core Version:    0.6.2
 */