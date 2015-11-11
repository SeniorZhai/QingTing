package com.tencent.open;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.a.c;
import com.tencent.open.b.a.a;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.Util;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

public class PKDialog extends b
  implements a.a
{
  private static final int MSG_CANCEL = 2;
  private static final int MSG_COMPLETE = 1;
  private static final int MSG_ON_LOAD = 4;
  private static final int MSG_SHOW_PROCESS = 5;
  private static final int MSG_SHOW_TIPS = 3;
  private static final String TAG = PKDialog.class.getName();
  private static final int WEBVIEW_HEIGHT = 185;
  static Toast sToast = null;
  private static WeakReference<Context> sWeakContext;
  private IUiListener listener;
  private com.tencent.open.b.a mFlMain;
  private Handler mHandler;
  private OnTimeListener mListener;
  private String mUrl;
  private WebView mWebView;
  private int mWebviewHeight;

  public PKDialog(Context paramContext, String paramString1, String paramString2, IUiListener paramIUiListener, QQToken paramQQToken)
  {
    super(paramContext, 16973840);
    sWeakContext = new WeakReference(paramContext);
    this.mUrl = paramString2;
    this.mListener = new OnTimeListener(paramContext, paramString1, paramString2, paramQQToken.getAppId(), paramIUiListener);
    this.mHandler = new THandler(this.mListener, paramContext.getMainLooper());
    this.listener = paramIUiListener;
    this.mWebviewHeight = Math.round(185.0F * paramContext.getResources().getDisplayMetrics().density);
    Log.e(TAG, "density=" + paramContext.getResources().getDisplayMetrics().density + "; webviewHeight=" + this.mWebviewHeight);
  }

  private void createViews()
  {
    this.mFlMain = new com.tencent.open.b.a((Context)sWeakContext.get());
    this.mFlMain.setBackgroundColor(1711276032);
    this.mFlMain.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    this.mWebView = new WebView((Context)sWeakContext.get());
    this.mWebView.setBackgroundColor(0);
    this.mWebView.setBackgroundDrawable(null);
    if (Build.VERSION.SDK_INT >= 11);
    try
    {
      View.class.getMethod("setLayerType", new Class[] { Integer.TYPE, Paint.class }).invoke(this.mWebView, new Object[] { Integer.valueOf(1), new Paint() });
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, this.mWebviewHeight);
      localLayoutParams.addRule(13, -1);
      this.mWebView.setLayoutParams(localLayoutParams);
      this.mFlMain.addView(this.mWebView);
      this.mFlMain.a(this);
      setContentView(this.mFlMain);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  private void initViews()
  {
    this.mWebView.setVerticalScrollBarEnabled(false);
    this.mWebView.setHorizontalScrollBarEnabled(false);
    this.mWebView.setWebViewClient(new FbWebViewClient(null));
    this.mWebView.setWebChromeClient(this.mChromeClient);
    this.mWebView.clearFormData();
    WebSettings localWebSettings = this.mWebView.getSettings();
    localWebSettings.setSavePassword(false);
    localWebSettings.setSaveFormData(false);
    localWebSettings.setCacheMode(-1);
    localWebSettings.setNeedInitialFocus(false);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    localWebSettings.setJavaScriptEnabled(true);
    if ((sWeakContext != null) && (sWeakContext.get() != null))
    {
      localWebSettings.setDatabaseEnabled(true);
      localWebSettings.setDatabasePath(((Context)sWeakContext.get()).getApplicationContext().getDir("databases", 0).getPath());
    }
    localWebSettings.setDomStorageEnabled(true);
    this.jsBridge.a(new JsListener(null), "sdk_js_if");
    this.mWebView.clearView();
    this.mWebView.loadUrl(this.mUrl);
    this.mWebView.getSettings().setSavePassword(false);
  }

  private void loadUrlWithBrowser(String paramString1, String paramString2, String paramString3)
    throws Exception
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.addFlags(1073741824);
    localIntent.addFlags(268435456);
    localIntent.setData(Uri.parse(paramString3));
    if ((sWeakContext != null) && (sWeakContext.get() != null))
      ((Context)sWeakContext.get()).startActivity(localIntent);
  }

  private static void showProcessDialog(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null));
    while (true)
    {
      return;
      try
      {
        paramContext = Util.parseJson(paramString);
        int i = paramContext.getInt("action");
        paramContext.getString("msg");
        if (i == 1);
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }

  private static void showTips(Context paramContext, String paramString)
  {
    int i;
    try
    {
      paramString = Util.parseJson(paramString);
      i = paramString.getInt("type");
      paramString = paramString.getString("msg");
      if (i == 0)
      {
        if (sToast == null)
          sToast = Toast.makeText(paramContext, paramString, 0);
        while (true)
        {
          sToast.show();
          return;
          sToast.setView(sToast.getView());
          sToast.setText(paramString);
          sToast.setDuration(0);
        }
      }
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    if (i == 1)
    {
      if (sToast == null)
        sToast = Toast.makeText(paramContext, paramString, 1);
      while (true)
      {
        sToast.show();
        return;
        sToast.setView(sToast.getView());
        sToast.setText(paramString);
        sToast.setDuration(1);
      }
    }
  }

  public void callJs(String paramString1, String paramString2)
  {
    paramString1 = "javascript:" + paramString1 + "(" + paramString2 + ")";
    this.mWebView.loadUrl(paramString1);
  }

  public void onBackPressed()
  {
    super.onBackPressed();
  }

  protected void onConsoleMessage(String paramString)
  {
    Log.d("PKDialog", "--onConsoleMessage--");
    try
    {
      this.jsBridge.a(this.mWebView, paramString);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    getWindow().setSoftInputMode(16);
    getWindow().setSoftInputMode(1);
    createViews();
    initViews();
  }

  public void onKeyboardHidden()
  {
    this.mWebView.getLayoutParams().height = this.mWebviewHeight;
    Log.e(TAG, "keyboard hide");
  }

  public void onKeyboardShown(int paramInt)
  {
    if ((sWeakContext != null) && (sWeakContext.get() != null))
      if ((paramInt >= this.mWebviewHeight) || (2 != ((Context)sWeakContext.get()).getResources().getConfiguration().orientation))
        break label67;
    label67: for (this.mWebView.getLayoutParams().height = paramInt; ; this.mWebView.getLayoutParams().height = this.mWebviewHeight)
    {
      Log.e(TAG, "keyboard show");
      return;
    }
  }

  private class FbWebViewClient extends WebViewClient
  {
    private FbWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      PKDialog.this.mWebView.setVisibility(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      Util.logd(PKDialog.TAG, "Webview loading URL: " + paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      PKDialog.this.mListener.onError(new UiError(paramInt, paramString1, paramString2));
      if ((PKDialog.sWeakContext != null) && (PKDialog.sWeakContext.get() != null))
        Toast.makeText((Context)PKDialog.sWeakContext.get(), "网络连接异常或系统错误", 0).show();
      PKDialog.this.dismiss();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Util.logd(PKDialog.TAG, "Redirect URL: " + paramString);
      if (paramString.startsWith(ServerSetting.getInstance().getEnvUrl((Context)PKDialog.sWeakContext.get(), "auth://tauth.qq.com/")))
      {
        PKDialog.this.mListener.onComplete(Util.parseUrlToJson(paramString));
        PKDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://cancel"))
      {
        PKDialog.this.mListener.onCancel();
        PKDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://close"))
      {
        PKDialog.this.dismiss();
        return true;
      }
      return false;
    }
  }

  private class JsListener extends a.b
  {
    private JsListener()
    {
    }

    public void onCancel(String paramString)
    {
      PKDialog.this.mHandler.obtainMessage(2, paramString).sendToTarget();
      PKDialog.this.dismiss();
    }

    public void onComplete(String paramString)
    {
      PKDialog.this.mHandler.obtainMessage(1, paramString).sendToTarget();
      Log.e("onComplete", paramString);
      PKDialog.this.dismiss();
    }

    public void onLoad(String paramString)
    {
      PKDialog.this.mHandler.obtainMessage(4, paramString).sendToTarget();
    }

    public void showMsg(String paramString)
    {
      PKDialog.this.mHandler.obtainMessage(3, paramString).sendToTarget();
    }
  }

  private static class OnTimeListener
    implements IUiListener
  {
    private String mAction;
    String mAppid;
    String mUrl;
    private WeakReference<Context> mWeakCtx;
    private IUiListener mWeakL;

    public OnTimeListener(Context paramContext, String paramString1, String paramString2, String paramString3, IUiListener paramIUiListener)
    {
      this.mWeakCtx = new WeakReference(paramContext);
      this.mAction = paramString1;
      this.mUrl = paramString2;
      this.mAppid = paramString3;
      this.mWeakL = paramIUiListener;
    }

    private void onComplete(String paramString)
    {
      try
      {
        onComplete(Util.parseJson(paramString));
        return;
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        onError(new UiError(-4, "服务器返回数据格式有误!", paramString));
      }
    }

    public void onCancel()
    {
      if (this.mWeakL != null)
      {
        this.mWeakL.onCancel();
        this.mWeakL = null;
      }
    }

    public void onComplete(Object paramObject)
    {
      paramObject = (JSONObject)paramObject;
      c.a().a((Context)this.mWeakCtx.get(), this.mAction + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, paramObject.optInt("ret", -6), this.mAppid, this.mUrl, "1000067");
      if (this.mWeakL != null)
      {
        this.mWeakL.onComplete(paramObject);
        this.mWeakL = null;
      }
    }

    public void onError(UiError paramUiError)
    {
      if (paramUiError.errorMessage != null);
      for (String str = paramUiError.errorMessage + this.mUrl; ; str = this.mUrl)
      {
        c.a().a((Context)this.mWeakCtx.get(), this.mAction + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, paramUiError.errorCode, this.mAppid, str, "1000067");
        if (this.mWeakL != null)
        {
          this.mWeakL.onError(paramUiError);
          this.mWeakL = null;
        }
        return;
      }
    }
  }

  private static class THandler extends Handler
  {
    private PKDialog.OnTimeListener mL;

    public THandler(PKDialog.OnTimeListener paramOnTimeListener, Looper paramLooper)
    {
      super();
      this.mL = paramOnTimeListener;
    }

    public void handleMessage(Message paramMessage)
    {
      Log.d("PKDialog", "msg = " + paramMessage.what);
      switch (paramMessage.what)
      {
      case 4:
      default:
      case 1:
      case 2:
      case 3:
      case 5:
      }
      do
      {
        do
        {
          return;
          this.mL.onComplete((String)paramMessage.obj);
          return;
          this.mL.onCancel();
          return;
        }
        while ((PKDialog.sWeakContext == null) || (PKDialog.sWeakContext.get() == null));
        PKDialog.showTips((Context)PKDialog.sWeakContext.get(), (String)paramMessage.obj);
        return;
      }
      while ((PKDialog.sWeakContext == null) || (PKDialog.sWeakContext.get() == null));
      PKDialog.showProcessDialog((Context)PKDialog.sWeakContext.get(), (String)paramMessage.obj);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.PKDialog
 * JD-Core Version:    0.6.2
 */