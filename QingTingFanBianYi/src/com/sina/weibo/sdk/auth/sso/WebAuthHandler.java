package com.sina.weibo.sdk.auth.sso;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.sina.weibo.sdk.WbAppInstallActivator;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.AuthRequestParam;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.UIUtils;
import com.sina.weibo.sdk.utils.Utility;

class WebAuthHandler
{
  private static final String NETWORK_NOT_AVAILABLE_EN = "Network is not available";
  private static final String NETWORK_NOT_AVAILABLE_ZH_CN = "无法连接到网络，请检查网络配置";
  private static final String NETWORK_NOT_AVAILABLE_ZH_TW = "無法連接到網络，請檢查網络配置";
  private static final String OAUTH2_BASE_URL = "https://open.weibo.cn/oauth2/authorize?";
  private static final int OBTAIN_AUTH_CODE = 0;
  private static final int OBTAIN_AUTH_TOKEN = 1;
  private static final String TAG = WebAuthHandler.class.getName();
  private AuthInfo mAuthInfo;
  private Context mContext;

  public WebAuthHandler(Context paramContext, AuthInfo paramAuthInfo)
  {
    this.mContext = paramContext;
    this.mAuthInfo = paramAuthInfo;
  }

  private void startDialog(WeiboAuthListener paramWeiboAuthListener, int paramInt)
  {
    if (paramWeiboAuthListener == null)
      return;
    Object localObject1 = new WeiboParameters(this.mAuthInfo.getAppKey());
    ((WeiboParameters)localObject1).put("client_id", this.mAuthInfo.getAppKey());
    ((WeiboParameters)localObject1).put("redirect_uri", this.mAuthInfo.getRedirectUrl());
    ((WeiboParameters)localObject1).put("scope", this.mAuthInfo.getScope());
    ((WeiboParameters)localObject1).put("response_type", "code");
    ((WeiboParameters)localObject1).put("version", "0030105000");
    Object localObject2 = Utility.getAid(this.mContext, this.mAuthInfo.getAppKey());
    if (!TextUtils.isEmpty((CharSequence)localObject2))
      ((WeiboParameters)localObject1).put("aid", (String)localObject2);
    if (1 == paramInt)
    {
      ((WeiboParameters)localObject1).put("packagename", this.mAuthInfo.getPackageName());
      ((WeiboParameters)localObject1).put("key_hash", this.mAuthInfo.getKeyHash());
    }
    localObject1 = "https://open.weibo.cn/oauth2/authorize?" + ((WeiboParameters)localObject1).encodeUrl();
    if (!NetworkHelper.hasInternetPermission(this.mContext))
    {
      UIUtils.showAlert(this.mContext, "Error", "Application requires permission to access the Internet");
      return;
    }
    localObject2 = new AuthRequestParam(this.mContext);
    ((AuthRequestParam)localObject2).setAuthInfo(this.mAuthInfo);
    ((AuthRequestParam)localObject2).setAuthListener(paramWeiboAuthListener);
    ((AuthRequestParam)localObject2).setUrl((String)localObject1);
    ((AuthRequestParam)localObject2).setSpecifyTitle("微博登录");
    paramWeiboAuthListener = ((AuthRequestParam)localObject2).createRequestParamBundle();
    localObject1 = new Intent(this.mContext, WeiboSdkBrowser.class);
    ((Intent)localObject1).putExtras(paramWeiboAuthListener);
    this.mContext.startActivity((Intent)localObject1);
  }

  public void anthorize(WeiboAuthListener paramWeiboAuthListener)
  {
    authorize(paramWeiboAuthListener, 1);
  }

  public void authorize(WeiboAuthListener paramWeiboAuthListener, int paramInt)
  {
    startDialog(paramWeiboAuthListener, paramInt);
    WbAppInstallActivator.getInstance(this.mContext, this.mAuthInfo.getAppKey()).activateWeiboInstall();
  }

  public AuthInfo getAuthInfo()
  {
    return this.mAuthInfo;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.auth.sso.WebAuthHandler
 * JD-Core Version:    0.6.2
 */