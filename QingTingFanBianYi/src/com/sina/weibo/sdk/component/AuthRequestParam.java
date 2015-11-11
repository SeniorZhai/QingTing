package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;

public class AuthRequestParam extends BrowserRequestParamBase
{
  public static final String EXTRA_KEY_AUTHINFO = "key_authinfo";
  public static final String EXTRA_KEY_LISTENER = "key_listener";
  private AuthInfo mAuthInfo;
  private WeiboAuthListener mAuthListener;
  private String mAuthListenerKey;

  public AuthRequestParam(Context paramContext)
  {
    super(paramContext);
    this.mLaucher = BrowserLauncher.AUTH;
  }

  public void execRequest(Activity paramActivity, int paramInt)
  {
    if (paramInt == 3)
    {
      if (this.mAuthListener != null)
        this.mAuthListener.onCancel();
      WeiboSdkBrowser.closeBrowser(paramActivity, this.mAuthListenerKey, null);
    }
  }

  public AuthInfo getAuthInfo()
  {
    return this.mAuthInfo;
  }

  public WeiboAuthListener getAuthListener()
  {
    return this.mAuthListener;
  }

  public String getAuthListenerKey()
  {
    return this.mAuthListenerKey;
  }

  public void onCreateRequestParamBundle(Bundle paramBundle)
  {
    if (this.mAuthInfo != null)
      paramBundle.putBundle("key_authinfo", this.mAuthInfo.getAuthBundle());
    if (this.mAuthListener != null)
    {
      WeiboCallbackManager localWeiboCallbackManager = WeiboCallbackManager.getInstance(this.mContext);
      this.mAuthListenerKey = localWeiboCallbackManager.genCallbackKey();
      localWeiboCallbackManager.setWeiboAuthListener(this.mAuthListenerKey, this.mAuthListener);
      paramBundle.putString("key_listener", this.mAuthListenerKey);
    }
  }

  protected void onSetupRequestParam(Bundle paramBundle)
  {
    Bundle localBundle = paramBundle.getBundle("key_authinfo");
    if (localBundle != null)
      this.mAuthInfo = AuthInfo.parseBundleData(this.mContext, localBundle);
    this.mAuthListenerKey = paramBundle.getString("key_listener");
    if (!TextUtils.isEmpty(this.mAuthListenerKey))
      this.mAuthListener = WeiboCallbackManager.getInstance(this.mContext).getWeiboAuthListener(this.mAuthListenerKey);
  }

  public void setAuthInfo(AuthInfo paramAuthInfo)
  {
    this.mAuthInfo = paramAuthInfo;
  }

  public void setAuthListener(WeiboAuthListener paramWeiboAuthListener)
  {
    this.mAuthListener = paramWeiboAuthListener;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.AuthRequestParam
 * JD-Core Version:    0.6.2
 */