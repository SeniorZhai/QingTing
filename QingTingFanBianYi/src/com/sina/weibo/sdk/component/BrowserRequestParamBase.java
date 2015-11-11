package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

public abstract class BrowserRequestParamBase
{
  public static final int EXEC_REQUEST_ACTION_CANCEL = 3;
  public static final int EXEC_REQUEST_ACTION_ERROR = 2;
  public static final int EXEC_REQUEST_ACTION_OK = 1;
  public static final String EXTRA_KEY_LAUNCHER = "key_launcher";
  protected static final String EXTRA_KEY_SPECIFY_TITLE = "key_specify_title";
  protected static final String EXTRA_KEY_URL = "key_url";
  protected Context mContext;
  protected BrowserLauncher mLaucher;
  protected String mSpecifyTitle;
  protected String mUrl;

  public BrowserRequestParamBase(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
  }

  public Bundle createRequestParamBundle()
  {
    Bundle localBundle = new Bundle();
    if (!TextUtils.isEmpty(this.mUrl))
      localBundle.putString("key_url", this.mUrl);
    if (this.mLaucher != null)
      localBundle.putSerializable("key_launcher", this.mLaucher);
    if (!TextUtils.isEmpty(this.mSpecifyTitle))
      localBundle.putString("key_specify_title", this.mSpecifyTitle);
    onCreateRequestParamBundle(localBundle);
    return localBundle;
  }

  public abstract void execRequest(Activity paramActivity, int paramInt);

  public BrowserLauncher getLauncher()
  {
    return this.mLaucher;
  }

  public String getSpecifyTitle()
  {
    return this.mSpecifyTitle;
  }

  public String getUrl()
  {
    return this.mUrl;
  }

  protected abstract void onCreateRequestParamBundle(Bundle paramBundle);

  protected abstract void onSetupRequestParam(Bundle paramBundle);

  public void setLauncher(BrowserLauncher paramBrowserLauncher)
  {
    this.mLaucher = paramBrowserLauncher;
  }

  public void setSpecifyTitle(String paramString)
  {
    this.mSpecifyTitle = paramString;
  }

  public void setUrl(String paramString)
  {
    this.mUrl = paramString;
  }

  public void setupRequestParam(Bundle paramBundle)
  {
    this.mUrl = paramBundle.getString("key_url");
    this.mLaucher = ((BrowserLauncher)paramBundle.getSerializable("key_launcher"));
    this.mSpecifyTitle = paramBundle.getString("key_specify_title");
    onSetupRequestParam(paramBundle);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.BrowserRequestParamBase
 * JD-Core Version:    0.6.2
 */