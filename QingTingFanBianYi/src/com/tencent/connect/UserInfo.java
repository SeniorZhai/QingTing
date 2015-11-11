package com.tencent.connect;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.utils.HttpUtils;

public class UserInfo extends BaseApi
{
  public static final String GRAPH_OPEN_ID = "oauth2.0/m_me";

  public UserInfo(Context paramContext, QQAuth paramQQAuth, QQToken paramQQToken)
  {
    super(paramContext, paramQQAuth, paramQQToken);
  }

  public UserInfo(Context paramContext, QQToken paramQQToken)
  {
    super(paramContext, paramQQToken);
  }

  public void getOpenId(IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "oauth2.0/m_me", localBundle, "GET", paramIUiListener);
  }

  public void getTenPayAddr(IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    localBundle.putString("ver", "1");
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "cft_info/get_tenpay_addr", localBundle, "GET", paramIUiListener);
  }

  public void getUserInfo(IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "user/get_simple_userinfo", localBundle, "GET", paramIUiListener);
  }

  public void getVipUserInfo(IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "user/get_vip_info", localBundle, "GET", paramIUiListener);
  }

  public void getVipUserRichInfo(IUiListener paramIUiListener)
  {
    Bundle localBundle = composeCGIParams();
    paramIUiListener = new BaseApi.TempRequestListener(this, paramIUiListener);
    HttpUtils.requestAsync(this.mToken, this.mContext, "user/get_vip_rich_info", localBundle, "GET", paramIUiListener);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.UserInfo
 * JD-Core Version:    0.6.2
 */