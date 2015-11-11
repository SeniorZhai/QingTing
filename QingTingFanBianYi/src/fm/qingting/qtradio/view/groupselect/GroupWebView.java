package fm.qingting.qtradio.view.groupselect;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.fm.WebViewPlayer;
import fm.qingting.qtradio.manager.advertisement.AdvertisementManage;
import fm.qingting.qtradio.manager.advertisement.UMengLogger;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.view.LoadingView;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.webview.WebViewFunc;
import fm.qingting.utils.AppInfo;

public class GroupWebView extends ViewGroupViewImpl
{
  public static final String AndroidUA = "Android-QingtingFM Mozilla/5.0 (Linux; U; Android 4.4.0; zh-cn; MB200 Build/GRJ22;) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1";
  private String URL = "http://qingting.fm";
  private boolean hasRemoved = false;
  private Context mContext;
  private LoadingView mLoadingView;
  private MiniPlayerView mMiniView;
  private boolean needMiniPlayer = false;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private MyWebView webView = null;
  private qtWebViewClient webViewClient = new qtWebViewClient(null);
  private final ViewLayout webviewLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL | ViewLayout.SLTR | ViewLayout.SC | ViewLayout.CW | ViewLayout.SVH);

  public GroupWebView(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext);
    this.mContext = paramContext;
    this.needMiniPlayer = paramBoolean2;
    this.URL = paramString;
    if (paramBoolean1)
      buildUrl();
    init();
    this.mLoadingView = new LoadingView(paramContext);
    addView(this.mLoadingView);
    if (this.needMiniPlayer)
    {
      this.mMiniView = new MiniPlayerView(paramContext);
      addView(this.mMiniView);
    }
  }

  private void removeLoading()
  {
    if (!this.hasRemoved)
    {
      removeView(this.mLoadingView);
      this.hasRemoved = true;
    }
  }

  protected String buildUrl()
  {
    if (this.URL == null)
      return "";
    this.URL += "&phonetype=android";
    Object localObject = AppInfo.getCurrentInternalVersion(this.mContext);
    if (localObject != null)
      this.URL = (this.URL + "&versioncode=" + (String)localObject);
    String str = InfoManager.getInstance().getDeviceId();
    localObject = str;
    if (str == null)
      localObject = "UnknownUser";
    this.URL = (this.URL + "&deviceId=" + (String)localObject);
    return this.URL;
  }

  public boolean canBack()
  {
    if (this.webView != null)
    {
      String str = WebViewPlayer.getInstance().getBackPolicy();
      if ((str != null) && (!str.equalsIgnoreCase("")))
        return true;
    }
    return false;
  }

  public void destroy()
  {
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == null)
      return false;
    if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1) && (canBack()))
    {
      goBack();
      return true;
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public String getUrl()
  {
    return this.URL;
  }

  public void goBack()
  {
    if (this.webView != null)
    {
      String str = WebViewPlayer.getInstance().getBackPolicy();
      if ((str != null) && (!str.equalsIgnoreCase("")))
      {
        this.webView.loadUrl(str);
        WebViewPlayer.getInstance().setbackPolicy(null);
      }
    }
    else
    {
      return;
    }
    this.webView.goBack();
  }

  @TargetApi(18)
  protected void init()
  {
    try
    {
      this.webView = new MyWebView(this.mContext);
      Object localObject = this.webView.getSettings();
      if (localObject != null)
      {
        ((WebSettings)localObject).setJavaScriptEnabled(true);
        ((WebSettings)localObject).setUserAgentString("Android-QingtingFM Mozilla/5.0 (Linux; U; Android 4.4.0; zh-cn; MB200 Build/GRJ22;) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
        ((WebSettings)localObject).setSupportZoom(true);
        ((WebSettings)localObject).setCacheMode(2);
        ((WebSettings)localObject).setJavaScriptCanOpenWindowsAutomatically(true);
        ((WebSettings)localObject).setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
      }
      WebViewPlayer.getInstance().setbackPolicy(null);
      WebViewPlayer.getInstance().setDisableLongClick(0);
      this.webView.addJavascriptInterface(WebViewPlayer.getInstance(), "QTJsPlayer");
      this.webView.addJavascriptInterface(WebViewFunc.getInstance(), "QTJsReserve");
      this.webView.setWebChromeClient(new WebChromeClient()
      {
        public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
        {
          paramAnonymousWebView = new AlertDialog.Builder(paramAnonymousWebView.getContext());
          paramAnonymousWebView.setTitle("蜻蜓提示").setMessage(paramAnonymousString2).setPositiveButton("确定", null);
          paramAnonymousWebView.setCancelable(false);
          paramAnonymousWebView.create().show();
          paramAnonymousJsResult.confirm();
          return true;
        }

        public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
        {
          if (paramAnonymousInt > 70)
            GroupWebView.this.removeLoading();
        }

        public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
        {
        }
      });
      this.webView.setHorizontalScrollBarEnabled(false);
      this.webView.setVerticalScrollBarEnabled(false);
      this.webView.setWebViewClient(this.webViewClient);
      this.webView.loadUrl(getUrl());
      this.webView.setDownloadListener(new DownloadListener()
      {
        public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
        {
          paramAnonymousString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString1));
          GroupWebView.this.getContext().startActivity(paramAnonymousString1);
        }
      });
      WebViewPlayer.getInstance().setWebview(this.webView);
      WebViewFunc.getInstance().setWebview(this.webView);
      localObject = new LinearLayout.LayoutParams(-1, -1);
      addView(this.webView, (ViewGroup.LayoutParams)localObject);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.needMiniPlayer)
      this.mMiniView.layout(0, this.standardLayout.height - this.mMiniView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
    for (paramInt1 = this.mMiniView.getHeightWithoutShadow(); ; paramInt1 = 0)
    {
      this.webView.layout(this.webviewLayout.getLeft(), this.webviewLayout.getTop(), this.webviewLayout.getRight(), this.webviewLayout.getBottom() - paramInt1);
      this.mLoadingView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
      return;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.webviewLayout.scaleToBounds(this.standardLayout);
    paramInt1 = 0;
    if (this.needMiniPlayer)
    {
      this.standardLayout.measureView(this.mMiniView);
      paramInt1 = this.mMiniView.getHeightWithoutShadow();
    }
    this.webView.measure(this.webviewLayout.getWidthMeasureSpec(), this.webviewLayout.getHeightMeasureSpec() - paramInt1);
    this.mLoadingView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - paramInt1, 1073741824));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  protected void onViewWillClose()
  {
  }

  public void release()
  {
    if (this.webView != null)
    {
      this.webView.removeAllViews();
      this.webView.destroy();
      this.webView = null;
    }
  }

  public void update(String paramString, Object paramObject)
  {
  }

  public class ADWebAppInterface
  {
    Context mContext;

    ADWebAppInterface()
    {
    }

    public void onClickAppDownload()
    {
      UMengLogger.sendmessage(GroupWebView.this.getContext(), "adPageDownload", AdvertisementManage.getInstance().currentADKey);
    }

    public void onClickDraw()
    {
      UMengLogger.sendmessage(GroupWebView.this.getContext(), "adPageRequestCoupon", AdvertisementManage.getInstance().currentADKey);
    }

    public void onClickEmailSend()
    {
      Toast.makeText(GroupWebView.this.getContext(), "正在发送邮件", 1).show();
      UMengLogger.sendmessage(GroupWebView.this.getContext(), "adPageSendEmail", AdvertisementManage.getInstance().currentADKey);
    }

    public void onPageLoaded()
    {
      UMengLogger.sendmessage(GroupWebView.this.getContext(), "adPageDisplay", AdvertisementManage.getInstance().currentADKey);
    }
  }

  private class MyWebView extends WebView
  {
    public MyWebView(Context arg2)
    {
      super();
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
      boolean bool = super.onTouchEvent(paramMotionEvent);
      if (WebViewPlayer.getInstance().mPreventParentTouch)
        switch (paramMotionEvent.getAction())
        {
        default:
        case 2:
        case 1:
        case 3:
        }
      while (true)
      {
        return bool;
        requestDisallowInterceptTouchEvent(true);
        return true;
        requestDisallowInterceptTouchEvent(false);
        WebViewPlayer.getInstance().mPreventParentTouch = false;
      }
    }
  }

  private class qtWebViewClient extends WebViewClient
  {
    private qtWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      if (WebViewPlayer.getInstance().disableLongClick())
        GroupWebView.this.webView.setOnLongClickListener(new View.OnLongClickListener()
        {
          public boolean onLongClick(View paramAnonymousView)
          {
            return true;
          }
        });
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      if (paramString2 != null)
      {
        paramString1 = GroupWebView.this.getUrl();
        if ((paramString1 != null) && (paramString1.equalsIgnoreCase(paramString2)))
          paramWebView.loadUrl("http://wx.qingting.fm");
      }
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (paramString.startsWith("tel:"))
      {
        paramWebView = new Intent("android.intent.action.DIAL", Uri.parse(paramString));
        GroupWebView.this.getContext().startActivity(paramWebView);
        return true;
      }
      if (((paramString != null) && ((paramString.startsWith("rtsp")) || (paramString.startsWith("mms")) || (paramString.endsWith(".mp3")) || (paramString.endsWith(".apk")))) || (paramString.contains("active.coupon.360buy.com")))
      {
        paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
        paramWebView.getContext().startActivity(paramString);
        return true;
      }
      if ((paramString != null) && (!paramString.startsWith("http")))
        try
        {
          paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
          paramString.addFlags(268435456);
          paramWebView.getContext().startActivity(paramString);
          return true;
        }
        catch (Exception paramWebView)
        {
        }
      return false;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.groupselect.GroupWebView
 * JD-Core Version:    0.6.2
 */