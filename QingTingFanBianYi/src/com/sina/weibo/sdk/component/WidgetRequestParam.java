package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;

public class WidgetRequestParam extends BrowserRequestParamBase
{
  public static final String EXTRA_KEY_WIDGET_CALLBACK = "key_widget_callback";
  public static final String REQ_PARAM_ATTENTION_FUID = "fuid";
  public static final String REQ_PARAM_COMMENT_CATEGORY = "category";
  public static final String REQ_PARAM_COMMENT_CONTENT = "content";
  public static final String REQ_PARAM_COMMENT_TOPIC = "q";
  private String mAppKey;
  private String mAppPackage;
  private String mAttentionFuid;
  private WeiboAuthListener mAuthListener;
  private String mAuthListenerKey;
  private String mCommentCategory;
  private String mCommentContent;
  private String mCommentTopic;
  private String mHashKey;
  private String mToken;
  private WidgetRequestCallback mWidgetRequestCallback;
  private String mWidgetRequestCallbackKey;

  public WidgetRequestParam(Context paramContext)
  {
    super(paramContext);
    this.mLaucher = BrowserLauncher.WIDGET;
  }

  private String buildUrl(String paramString)
  {
    paramString = Uri.parse(paramString).buildUpon();
    paramString.appendQueryParameter("version", "0030105000");
    if (!TextUtils.isEmpty(this.mAppKey))
      paramString.appendQueryParameter("source", this.mAppKey);
    if (!TextUtils.isEmpty(this.mToken))
      paramString.appendQueryParameter("access_token", this.mToken);
    String str = Utility.getAid(this.mContext, this.mAppKey);
    if (!TextUtils.isEmpty(str))
      paramString.appendQueryParameter("aid", str);
    if (!TextUtils.isEmpty(this.mAppPackage))
      paramString.appendQueryParameter("packagename", this.mAppPackage);
    if (!TextUtils.isEmpty(this.mHashKey))
      paramString.appendQueryParameter("key_hash", this.mHashKey);
    if (!TextUtils.isEmpty(this.mAttentionFuid))
      paramString.appendQueryParameter("fuid", this.mAttentionFuid);
    if (!TextUtils.isEmpty(this.mCommentTopic))
      paramString.appendQueryParameter("q", this.mCommentTopic);
    if (!TextUtils.isEmpty(this.mCommentContent))
      paramString.appendQueryParameter("content", this.mCommentContent);
    if (!TextUtils.isEmpty(this.mCommentCategory))
      paramString.appendQueryParameter("category", this.mCommentCategory);
    return paramString.build().toString();
  }

  public void execRequest(Activity paramActivity, int paramInt)
  {
    if (paramInt == 3)
      WeiboSdkBrowser.closeBrowser(paramActivity, this.mAuthListenerKey, this.mWidgetRequestCallbackKey);
  }

  public String getAppKey()
  {
    return this.mAppKey;
  }

  public String getAttentionFuid()
  {
    return this.mAttentionFuid;
  }

  public WeiboAuthListener getAuthListener()
  {
    return this.mAuthListener;
  }

  public String getAuthListenerKey()
  {
    return this.mAuthListenerKey;
  }

  public String getCommentCategory()
  {
    return this.mCommentCategory;
  }

  public String getCommentContent()
  {
    return this.mCommentContent;
  }

  public String getCommentTopic()
  {
    return this.mCommentTopic;
  }

  public String getToken()
  {
    return this.mToken;
  }

  public WidgetRequestCallback getWidgetRequestCallback()
  {
    return this.mWidgetRequestCallback;
  }

  public String getWidgetRequestCallbackKey()
  {
    return this.mWidgetRequestCallbackKey;
  }

  public void onCreateRequestParamBundle(Bundle paramBundle)
  {
    this.mAppPackage = this.mContext.getPackageName();
    if (!TextUtils.isEmpty(this.mAppPackage))
      this.mHashKey = MD5.hexdigest(Utility.getSign(this.mContext, this.mAppPackage));
    paramBundle.putString("access_token", this.mToken);
    paramBundle.putString("source", this.mAppKey);
    paramBundle.putString("packagename", this.mAppPackage);
    paramBundle.putString("key_hash", this.mHashKey);
    paramBundle.putString("fuid", this.mAttentionFuid);
    paramBundle.putString("q", this.mCommentTopic);
    paramBundle.putString("content", this.mCommentContent);
    paramBundle.putString("category", this.mCommentCategory);
    WeiboCallbackManager localWeiboCallbackManager = WeiboCallbackManager.getInstance(this.mContext);
    if (this.mAuthListener != null)
    {
      this.mAuthListenerKey = localWeiboCallbackManager.genCallbackKey();
      localWeiboCallbackManager.setWeiboAuthListener(this.mAuthListenerKey, this.mAuthListener);
      paramBundle.putString("key_listener", this.mAuthListenerKey);
    }
    if (this.mWidgetRequestCallback != null)
    {
      this.mWidgetRequestCallbackKey = localWeiboCallbackManager.genCallbackKey();
      localWeiboCallbackManager.setWidgetRequestCallback(this.mWidgetRequestCallbackKey, this.mWidgetRequestCallback);
      paramBundle.putString("key_widget_callback", this.mWidgetRequestCallbackKey);
    }
  }

  protected void onSetupRequestParam(Bundle paramBundle)
  {
    this.mAppKey = paramBundle.getString("source");
    this.mAppPackage = paramBundle.getString("packagename");
    this.mHashKey = paramBundle.getString("key_hash");
    this.mToken = paramBundle.getString("access_token");
    this.mAttentionFuid = paramBundle.getString("fuid");
    this.mCommentTopic = paramBundle.getString("q");
    this.mCommentContent = paramBundle.getString("content");
    this.mCommentCategory = paramBundle.getString("category");
    this.mAuthListenerKey = paramBundle.getString("key_listener");
    if (!TextUtils.isEmpty(this.mAuthListenerKey))
      this.mAuthListener = WeiboCallbackManager.getInstance(this.mContext).getWeiboAuthListener(this.mAuthListenerKey);
    this.mWidgetRequestCallbackKey = paramBundle.getString("key_widget_callback");
    if (!TextUtils.isEmpty(this.mWidgetRequestCallbackKey))
      this.mWidgetRequestCallback = WeiboCallbackManager.getInstance(this.mContext).getWidgetRequestCallback(this.mWidgetRequestCallbackKey);
    this.mUrl = buildUrl(this.mUrl);
  }

  public void setAppKey(String paramString)
  {
    this.mAppKey = paramString;
  }

  public void setAttentionFuid(String paramString)
  {
    this.mAttentionFuid = paramString;
  }

  public void setAuthListener(WeiboAuthListener paramWeiboAuthListener)
  {
    this.mAuthListener = paramWeiboAuthListener;
  }

  public void setCommentCategory(String paramString)
  {
    this.mCommentCategory = paramString;
  }

  public void setCommentContent(String paramString)
  {
    this.mCommentContent = paramString;
  }

  public void setCommentTopic(String paramString)
  {
    this.mCommentTopic = paramString;
  }

  public void setToken(String paramString)
  {
    this.mToken = paramString;
  }

  public void setWidgetRequestCallback(WidgetRequestCallback paramWidgetRequestCallback)
  {
    this.mWidgetRequestCallback = paramWidgetRequestCallback;
  }

  public static abstract interface WidgetRequestCallback
  {
    public abstract void onWebViewResult(String paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.WidgetRequestParam
 * JD-Core Version:    0.6.2
 */