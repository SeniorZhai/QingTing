package com.sina.weibo.sdk.component;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.view.LoadingBar;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.AsyncWeiboRunner;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.Utility;

public class WeiboSdkBrowser extends Activity
  implements BrowserRequestCallBack
{
  public static final String BROWSER_CLOSE_SCHEME = "sinaweibo://browser/close";
  public static final String BROWSER_WIDGET_SCHEME = "sinaweibo://browser/datatransfer";
  private static final String CANCEL_EN = "Close";
  private static final String CANCEL_ZH_CN = "关闭";
  private static final String CANCEL_ZH_TW = "关闭";
  private static final String CHANNEL_DATA_ERROR_EN = "channel_data_error";
  private static final String CHANNEL_DATA_ERROR_ZH_CN = "重新加载";
  private static final String CHANNEL_DATA_ERROR_ZH_TW = "重新載入";
  private static final String EMPTY_PROMPT_BAD_NETWORK_UI_EN = "A network error occurs, please tap the button to reload";
  private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_CN = "网络出错啦，请点击按钮重新加载";
  private static final String EMPTY_PROMPT_BAD_NETWORK_UI_ZH_TW = "網路出錯啦，請點擊按鈕重新載入";
  private static final String LOADINFO_EN = "Loading....";
  private static final String LOADINFO_ZH_CN = "加载中....";
  private static final String LOADINFO_ZH_TW = "載入中....";
  private static final String TAG = WeiboSdkBrowser.class.getName();
  private static final String WEIBOBROWSER_NO_TITLE_EN = "No Title";
  private static final String WEIBOBROWSER_NO_TITLE_ZH_CN = "无标题";
  private static final String WEIBOBROWSER_NO_TITLE_ZH_TW = "無標題";
  private boolean isErrorPage;
  private boolean isLoading;
  private String mHtmlTitle;
  private TextView mLeftBtn;
  private Button mLoadErrorRetryBtn;
  private LinearLayout mLoadErrorView;
  private LoadingBar mLoadingBar;
  private BrowserRequestParamBase mRequestParam;
  private String mSpecifyTitle;
  private TextView mTitleText;
  private String mUrl;
  private WebView mWebView;
  private WeiboWebViewClient mWeiboWebViewClient;

  public static void closeBrowser(Activity paramActivity, String paramString1, String paramString2)
  {
    WeiboCallbackManager localWeiboCallbackManager = WeiboCallbackManager.getInstance(paramActivity.getApplicationContext());
    if (!TextUtils.isEmpty(paramString1))
    {
      localWeiboCallbackManager.removeWeiboAuthListener(paramString1);
      paramActivity.finish();
    }
    if (!TextUtils.isEmpty(paramString2))
    {
      localWeiboCallbackManager.removeWidgetRequestCallback(paramString2);
      paramActivity.finish();
    }
  }

  private BrowserRequestParamBase createBrowserRequestParam(Bundle paramBundle)
  {
    Object localObject = null;
    BrowserLauncher localBrowserLauncher = (BrowserLauncher)paramBundle.getSerializable("key_launcher");
    if (localBrowserLauncher == BrowserLauncher.AUTH)
    {
      localObject = new AuthRequestParam(this);
      ((AuthRequestParam)localObject).setupRequestParam(paramBundle);
      installAuthWeiboWebViewClient((AuthRequestParam)localObject);
      return localObject;
    }
    if (localBrowserLauncher == BrowserLauncher.SHARE)
    {
      localObject = new ShareRequestParam(this);
      ((ShareRequestParam)localObject).setupRequestParam(paramBundle);
      installShareWeiboWebViewClient((ShareRequestParam)localObject);
    }
    while (true)
    {
      return localObject;
      if (localBrowserLauncher == BrowserLauncher.WIDGET)
      {
        localObject = new WidgetRequestParam(this);
        ((WidgetRequestParam)localObject).setupRequestParam(paramBundle);
        installWidgetWeiboWebViewClient((WidgetRequestParam)localObject);
      }
    }
  }

  private void handleReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    if (!paramString2.startsWith("sinaweibo"))
    {
      this.isErrorPage = true;
      promptError();
    }
  }

  private void hiddenErrorPrompt()
  {
    this.mLoadErrorView.setVisibility(8);
    this.mWebView.setVisibility(0);
  }

  private boolean initDataFromIntent(Intent paramIntent)
  {
    this.mRequestParam = createBrowserRequestParam(paramIntent.getExtras());
    if (this.mRequestParam == null);
    do
    {
      return false;
      this.mUrl = this.mRequestParam.getUrl();
    }
    while (TextUtils.isEmpty(this.mUrl));
    LogUtil.d(TAG, "LOAD URL : " + this.mUrl);
    this.mSpecifyTitle = this.mRequestParam.getSpecifyTitle();
    return true;
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  private void initWebView()
  {
    this.mWebView.getSettings().setJavaScriptEnabled(true);
    if (isWeiboShareRequestParam(this.mRequestParam))
      this.mWebView.getSettings().setUserAgentString(Utility.generateUA(this));
    this.mWebView.getSettings().setSavePassword(false);
    this.mWebView.setWebViewClient(this.mWeiboWebViewClient);
    this.mWebView.setWebChromeClient(new WeiboChromeClient(null));
    this.mWebView.requestFocus();
    this.mWebView.setScrollBarStyle(0);
  }

  private void installAuthWeiboWebViewClient(AuthRequestParam paramAuthRequestParam)
  {
    this.mWeiboWebViewClient = new AuthWeiboWebViewClient(this, paramAuthRequestParam);
    this.mWeiboWebViewClient.setBrowserRequestCallBack(this);
  }

  private void installShareWeiboWebViewClient(ShareRequestParam paramShareRequestParam)
  {
    paramShareRequestParam = new ShareWeiboWebViewClient(this, paramShareRequestParam);
    paramShareRequestParam.setBrowserRequestCallBack(this);
    this.mWeiboWebViewClient = paramShareRequestParam;
  }

  private void installWidgetWeiboWebViewClient(WidgetRequestParam paramWidgetRequestParam)
  {
    paramWidgetRequestParam = new WidgetWeiboWebViewClient(this, paramWidgetRequestParam);
    paramWidgetRequestParam.setBrowserRequestCallBack(this);
    this.mWeiboWebViewClient = paramWidgetRequestParam;
  }

  private boolean isWeiboCustomScheme(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    while (!"sinaweibo".equalsIgnoreCase(Uri.parse(paramString).getAuthority()))
      return false;
    return true;
  }

  private boolean isWeiboShareRequestParam(BrowserRequestParamBase paramBrowserRequestParamBase)
  {
    return paramBrowserRequestParamBase.getLauncher() == BrowserLauncher.SHARE;
  }

  private void openUrl(String paramString)
  {
    this.mWebView.loadUrl(paramString);
  }

  private void promptError()
  {
    this.mLoadErrorView.setVisibility(0);
    this.mWebView.setVisibility(8);
  }

  private void setContentView()
  {
    RelativeLayout localRelativeLayout = new RelativeLayout(this);
    localRelativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    localRelativeLayout.setBackgroundColor(-1);
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setId(1);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    Object localObject1 = new RelativeLayout(this);
    ((RelativeLayout)localObject1).setLayoutParams(new ViewGroup.LayoutParams(-1, ResourceManager.dp2px(this, 45)));
    ((RelativeLayout)localObject1).setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_navigationbar_background.9.png"));
    this.mLeftBtn = new TextView(this);
    this.mLeftBtn.setClickable(true);
    this.mLeftBtn.setTextSize(2, 17.0F);
    this.mLeftBtn.setTextColor(ResourceManager.createColorStateList(-32256, 1728020992));
    this.mLeftBtn.setText(ResourceManager.getString(this, "Close", "关闭", "关闭"));
    Object localObject2 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(5);
    ((RelativeLayout.LayoutParams)localObject2).addRule(15);
    ((RelativeLayout.LayoutParams)localObject2).leftMargin = ResourceManager.dp2px(this, 10);
    ((RelativeLayout.LayoutParams)localObject2).rightMargin = ResourceManager.dp2px(this, 10);
    this.mLeftBtn.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((RelativeLayout)localObject1).addView(this.mLeftBtn);
    this.mTitleText = new TextView(this);
    this.mTitleText.setTextSize(2, 18.0F);
    this.mTitleText.setTextColor(-11382190);
    this.mTitleText.setEllipsize(TextUtils.TruncateAt.END);
    this.mTitleText.setSingleLine(true);
    this.mTitleText.setGravity(17);
    this.mTitleText.setMaxWidth(ResourceManager.dp2px(this, 160));
    localObject2 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(13);
    this.mTitleText.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((RelativeLayout)localObject1).addView(this.mTitleText);
    localObject2 = new TextView(this);
    ((TextView)localObject2).setLayoutParams(new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, 2)));
    ((TextView)localObject2).setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_common_shadow_top.9.png"));
    this.mLoadingBar = new LoadingBar(this);
    this.mLoadingBar.setBackgroundColor(0);
    this.mLoadingBar.drawProgress(0);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, 3));
    this.mLoadingBar.setLayoutParams(localLayoutParams);
    localLinearLayout.addView((View)localObject1);
    localLinearLayout.addView((View)localObject2);
    localLinearLayout.addView(this.mLoadingBar);
    this.mWebView = new WebView(this);
    this.mWebView.setBackgroundColor(-1);
    localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(3, 1);
    this.mWebView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.mLoadErrorView = new LinearLayout(this);
    this.mLoadErrorView.setVisibility(8);
    this.mLoadErrorView.setOrientation(1);
    this.mLoadErrorView.setGravity(17);
    localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(3, 1);
    this.mLoadErrorView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    localObject1 = new ImageView(this);
    ((ImageView)localObject1).setImageDrawable(ResourceManager.getDrawable(this, "weibosdk_empty_failed.png"));
    localObject2 = new LinearLayout.LayoutParams(-2, -2);
    int i = ResourceManager.dp2px(this, 8);
    ((LinearLayout.LayoutParams)localObject2).bottomMargin = i;
    ((LinearLayout.LayoutParams)localObject2).rightMargin = i;
    ((LinearLayout.LayoutParams)localObject2).topMargin = i;
    ((LinearLayout.LayoutParams)localObject2).leftMargin = i;
    ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.mLoadErrorView.addView((View)localObject1);
    localObject1 = new TextView(this);
    ((TextView)localObject1).setGravity(1);
    ((TextView)localObject1).setTextColor(-4342339);
    ((TextView)localObject1).setTextSize(2, 14.0F);
    ((TextView)localObject1).setText(ResourceManager.getString(this, "A network error occurs, please tap the button to reload", "网络出错啦，请点击按钮重新加载", "網路出錯啦，請點擊按鈕重新載入"));
    ((TextView)localObject1).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
    this.mLoadErrorView.addView((View)localObject1);
    this.mLoadErrorRetryBtn = new Button(this);
    this.mLoadErrorRetryBtn.setGravity(17);
    this.mLoadErrorRetryBtn.setTextColor(-8882056);
    this.mLoadErrorRetryBtn.setTextSize(2, 16.0F);
    this.mLoadErrorRetryBtn.setText(ResourceManager.getString(this, "channel_data_error", "重新加载", "重新載入"));
    this.mLoadErrorRetryBtn.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "weibosdk_common_button_alpha.9.png", "weibosdk_common_button_alpha_highlighted.9.png"));
    localObject1 = new LinearLayout.LayoutParams(ResourceManager.dp2px(this, 142), ResourceManager.dp2px(this, 46));
    ((LinearLayout.LayoutParams)localObject1).topMargin = ResourceManager.dp2px(this, 10);
    this.mLoadErrorRetryBtn.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.mLoadErrorRetryBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WeiboSdkBrowser.this.openUrl(WeiboSdkBrowser.this.mUrl);
        WeiboSdkBrowser.this.isErrorPage = false;
      }
    });
    this.mLoadErrorView.addView(this.mLoadErrorRetryBtn);
    localRelativeLayout.addView(localLinearLayout);
    localRelativeLayout.addView(this.mWebView);
    localRelativeLayout.addView(this.mLoadErrorView);
    setContentView(localRelativeLayout);
    setTopNavTitle();
  }

  private void setTopNavTitle()
  {
    this.mTitleText.setText(this.mSpecifyTitle);
    this.mLeftBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WeiboSdkBrowser.this.mRequestParam.execRequest(WeiboSdkBrowser.this, 3);
        WeiboSdkBrowser.this.finish();
      }
    });
  }

  private void setViewLoading()
  {
    this.mTitleText.setText(ResourceManager.getString(this, "Loading....", "加载中....", "載入中...."));
    this.mLoadingBar.setVisibility(0);
  }

  private void setViewNormal()
  {
    updateTitleName();
    this.mLoadingBar.setVisibility(8);
  }

  public static void startAuth(Context paramContext, String paramString, AuthInfo paramAuthInfo, WeiboAuthListener paramWeiboAuthListener)
  {
    AuthRequestParam localAuthRequestParam = new AuthRequestParam(paramContext);
    localAuthRequestParam.setLauncher(BrowserLauncher.AUTH);
    localAuthRequestParam.setUrl(paramString);
    localAuthRequestParam.setAuthInfo(paramAuthInfo);
    localAuthRequestParam.setAuthListener(paramWeiboAuthListener);
    paramString = new Intent(paramContext, WeiboSdkBrowser.class);
    paramString.putExtras(localAuthRequestParam.createRequestParamBundle());
    paramContext.startActivity(paramString);
  }

  private void startShare()
  {
    LogUtil.d(TAG, "Enter startShare()............");
    final ShareRequestParam localShareRequestParam = (ShareRequestParam)this.mRequestParam;
    if (localShareRequestParam.hasImage())
    {
      LogUtil.d(TAG, "loadUrl hasImage............");
      WeiboParameters localWeiboParameters = localShareRequestParam.buildUploadPicParam(new WeiboParameters(localShareRequestParam.getAppKey()));
      new AsyncWeiboRunner(this).requestAsync("http://service.weibo.com/share/mobilesdk_uppic.php", localWeiboParameters, "POST", new RequestListener()
      {
        public void onComplete(String paramAnonymousString)
        {
          LogUtil.d(WeiboSdkBrowser.TAG, "post onComplete : " + paramAnonymousString);
          paramAnonymousString = ShareRequestParam.UploadPicResult.parse(paramAnonymousString);
          if ((paramAnonymousString != null) && (paramAnonymousString.getCode() == 1) && (!TextUtils.isEmpty(paramAnonymousString.getPicId())))
          {
            WeiboSdkBrowser.this.openUrl(localShareRequestParam.buildUrl(paramAnonymousString.getPicId()));
            return;
          }
          localShareRequestParam.sendSdkErrorResponse(WeiboSdkBrowser.this, "upload pic faild");
          WeiboSdkBrowser.this.finish();
        }

        public void onWeiboException(WeiboException paramAnonymousWeiboException)
        {
          LogUtil.d(WeiboSdkBrowser.TAG, "post onWeiboException " + paramAnonymousWeiboException.getMessage());
          localShareRequestParam.sendSdkErrorResponse(WeiboSdkBrowser.this, paramAnonymousWeiboException.getMessage());
          WeiboSdkBrowser.this.finish();
        }
      });
      return;
    }
    openUrl(this.mUrl);
  }

  public static void startShared(Context paramContext, String paramString, AuthInfo paramAuthInfo, WeiboAuthListener paramWeiboAuthListener)
  {
  }

  private void updateTitleName()
  {
    String str = "";
    if (!TextUtils.isEmpty(this.mHtmlTitle))
      str = this.mHtmlTitle;
    while (true)
    {
      this.mTitleText.setText(str);
      return;
      if (!TextUtils.isEmpty(this.mSpecifyTitle))
        str = this.mSpecifyTitle;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (!initDataFromIntent(getIntent()))
    {
      finish();
      return;
    }
    setContentView();
    initWebView();
    if (isWeiboShareRequestParam(this.mRequestParam))
    {
      startShare();
      return;
    }
    openUrl(this.mUrl);
  }

  protected void onDestroy()
  {
    NetworkHelper.clearCookies(this);
    super.onDestroy();
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      this.mRequestParam.execRequest(this, 3);
      finish();
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  public void onPageFinishedCallBack(WebView paramWebView, String paramString)
  {
    LogUtil.d(TAG, "onPageFinished URL: " + paramString);
    if (this.isErrorPage)
    {
      promptError();
      return;
    }
    this.isErrorPage = false;
    hiddenErrorPrompt();
  }

  public void onPageStartedCallBack(WebView paramWebView, String paramString, Bitmap paramBitmap)
  {
    LogUtil.d(TAG, "onPageStarted URL: " + paramString);
    this.mUrl = paramString;
    if (!isWeiboCustomScheme(paramString))
      this.mHtmlTitle = "";
  }

  public void onReceivedErrorCallBack(WebView paramWebView, int paramInt, String paramString1, String paramString2)
  {
    LogUtil.d(TAG, "onReceivedError: errorCode = " + paramInt + ", description = " + paramString1 + ", failingUrl = " + paramString2);
    handleReceivedError(paramWebView, paramInt, paramString1, paramString2);
  }

  public void onReceivedSslErrorCallBack(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
  {
    LogUtil.d(TAG, "onReceivedSslErrorCallBack.........");
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void refreshAllViews()
  {
    if (this.isLoading)
    {
      setViewLoading();
      return;
    }
    setViewNormal();
  }

  public boolean shouldOverrideUrlLoadingCallBack(WebView paramWebView, String paramString)
  {
    LogUtil.i(TAG, "shouldOverrideUrlLoading URL: " + paramString);
    return false;
  }

  private class WeiboChromeClient extends WebChromeClient
  {
    private WeiboChromeClient()
    {
    }

    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      WeiboSdkBrowser.this.mLoadingBar.drawProgress(paramInt);
      if (paramInt == 100)
      {
        WeiboSdkBrowser.this.isLoading = false;
        WeiboSdkBrowser.this.refreshAllViews();
      }
      while (WeiboSdkBrowser.this.isLoading)
        return;
      WeiboSdkBrowser.this.isLoading = true;
      WeiboSdkBrowser.this.refreshAllViews();
    }

    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      if (!WeiboSdkBrowser.this.isWeiboCustomScheme(WeiboSdkBrowser.this.mUrl))
      {
        WeiboSdkBrowser.this.mHtmlTitle = paramString;
        WeiboSdkBrowser.this.updateTitleName();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.WeiboSdkBrowser
 * JD-Core Version:    0.6.2
 */