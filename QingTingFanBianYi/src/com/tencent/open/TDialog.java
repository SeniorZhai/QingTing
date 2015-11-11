package com.tencent.open;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.auth.QQToken;
import com.tencent.open.a.c;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.Util;
import java.io.File;
import java.lang.ref.WeakReference;
import org.json.JSONException;
import org.json.JSONObject;

public class TDialog extends b
{
  static final FrameLayout.LayoutParams a = new FrameLayout.LayoutParams(-1, -1);
  static Toast b = null;
  private static WeakReference<Context> c;
  private static WeakReference<View> d;
  private static WeakReference<ProgressDialog> e;
  private String f;
  private OnTimeListener g;
  private IUiListener h;
  private FrameLayout i;
  private WebView j;
  private FrameLayout k;
  private ProgressBar l;
  private Handler m;
  private boolean n = false;
  private QQToken o = null;

  public TDialog(Context paramContext, String paramString1, String paramString2, IUiListener paramIUiListener, QQToken paramQQToken)
  {
    super(paramContext, 16973840);
    c = new WeakReference(paramContext);
    this.f = paramString2;
    this.g = new OnTimeListener(paramContext, paramString1, paramString2, paramQQToken.getAppId(), paramIUiListener);
    this.m = new THandler(this.g, paramContext.getMainLooper());
    this.h = paramIUiListener;
    this.o = paramQQToken;
  }

  private void c()
  {
    this.l = new ProgressBar((Context)c.get());
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-2, -2);
    localLayoutParams.gravity = 17;
    this.l.setLayoutParams(localLayoutParams);
    new TextView((Context)c.get()).setText("test");
    this.k = new FrameLayout((Context)c.get());
    localLayoutParams = new FrameLayout.LayoutParams(-1, -2);
    localLayoutParams.bottomMargin = 40;
    localLayoutParams.leftMargin = 80;
    localLayoutParams.rightMargin = 80;
    localLayoutParams.topMargin = 40;
    localLayoutParams.gravity = 17;
    this.k.setLayoutParams(localLayoutParams);
    this.k.setBackgroundResource(17301504);
    this.k.addView(this.l);
    localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    this.j = new WebView((Context)c.get());
    this.j.setLayoutParams(localLayoutParams);
    this.i = new FrameLayout((Context)c.get());
    localLayoutParams.gravity = 17;
    this.i.setLayoutParams(localLayoutParams);
    this.i.addView(this.j);
    this.i.addView(this.k);
    d = new WeakReference(this.k);
    setContentView(this.i);
  }

  private static void c(Context paramContext, String paramString)
  {
    int i1;
    try
    {
      paramString = Util.parseJson(paramString);
      i1 = paramString.getInt("type");
      paramString = paramString.getString("msg");
      if (i1 == 0)
      {
        if (b == null)
          b = Toast.makeText(paramContext, paramString, 0);
        while (true)
        {
          b.show();
          return;
          b.setView(b.getView());
          b.setText(paramString);
          b.setDuration(0);
        }
      }
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    if (i1 == 1)
    {
      if (b == null)
        b = Toast.makeText(paramContext, paramString, 1);
      while (true)
      {
        b.show();
        return;
        b.setView(b.getView());
        b.setText(paramString);
        b.setDuration(1);
      }
    }
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  private void d()
  {
    this.j.setVerticalScrollBarEnabled(false);
    this.j.setHorizontalScrollBarEnabled(false);
    this.j.setWebViewClient(new FbWebViewClient(null));
    this.j.setWebChromeClient(this.mChromeClient);
    this.j.clearFormData();
    WebSettings localWebSettings = this.j.getSettings();
    localWebSettings.setSavePassword(false);
    localWebSettings.setSaveFormData(false);
    localWebSettings.setCacheMode(-1);
    localWebSettings.setNeedInitialFocus(false);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    localWebSettings.setJavaScriptEnabled(true);
    if ((c != null) && (c.get() != null))
    {
      localWebSettings.setDatabaseEnabled(true);
      localWebSettings.setDatabasePath(((Context)c.get()).getApplicationContext().getDir("databases", 0).getPath());
    }
    localWebSettings.setDomStorageEnabled(true);
    this.jsBridge.a(new JsListener(null), "sdk_js_if");
    this.j.loadUrl(this.f);
    this.j.setLayoutParams(a);
    this.j.setVisibility(4);
    this.j.getSettings().setSavePassword(false);
  }

  private static void d(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null));
    int i1;
    do
    {
      do
      {
        return;
        try
        {
          paramString = Util.parseJson(paramString);
          i1 = paramString.getInt("action");
          paramString = paramString.getString("msg");
          if (i1 != 1)
            break;
          if (e == null)
          {
            paramContext = new ProgressDialog(paramContext);
            paramContext.setMessage(paramString);
            e = new WeakReference(paramContext);
            paramContext.show();
            return;
          }
        }
        catch (JSONException paramContext)
        {
          paramContext.printStackTrace();
          return;
        }
        ((ProgressDialog)e.get()).setMessage(paramString);
      }
      while (((ProgressDialog)e.get()).isShowing());
      ((ProgressDialog)e.get()).show();
      return;
    }
    while ((i1 != 0) || (e == null) || (e.get() == null) || (!((ProgressDialog)e.get()).isShowing()));
    ((ProgressDialog)e.get()).dismiss();
    e = null;
  }

  public void onBackPressed()
  {
    if (this.g != null)
      this.g.onCancel();
    super.onBackPressed();
  }

  protected void onConsoleMessage(String paramString)
  {
    Log.d("TDialog", "--onConsoleMessage--");
    try
    {
      this.jsBridge.a(this.j, paramString);
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
    c();
    d();
  }

  private class FbWebViewClient extends WebViewClient
  {
    private FbWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      if ((TDialog.b() != null) && (TDialog.b().get() != null))
        ((View)TDialog.b().get()).setVisibility(8);
      TDialog.c(TDialog.this).setVisibility(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      Util.logd("TDialog", "Webview loading URL: " + paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if ((TDialog.b() != null) && (TDialog.b().get() != null))
        ((View)TDialog.b().get()).setVisibility(0);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      TDialog.b(TDialog.this).onError(new UiError(paramInt, paramString1, paramString2));
      if ((TDialog.a() != null) && (TDialog.a().get() != null))
        Toast.makeText((Context)TDialog.a().get(), "网络连接异常或系统错误", 0).show();
      TDialog.this.dismiss();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Util.logd("TDialog", "Redirect URL: " + paramString);
      if (paramString.startsWith(ServerSetting.getInstance().getEnvUrl((Context)TDialog.a().get(), "auth://tauth.qq.com/")))
      {
        TDialog.b(TDialog.this).onComplete(Util.parseUrlToJson(paramString));
        if (TDialog.this.isShowing())
          TDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://cancel"))
      {
        TDialog.b(TDialog.this).onCancel();
        if (TDialog.this.isShowing())
          TDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://close"))
      {
        if (TDialog.this.isShowing())
          TDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("download://"))
      {
        paramWebView = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(paramString.substring("download://".length()))));
        if ((TDialog.a() != null) && (TDialog.a().get() != null))
          ((Context)TDialog.a().get()).startActivity(paramWebView);
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

    public void onAddShare(String paramString)
    {
      Log.d("TDialog", "onAddShare");
      onComplete(paramString);
    }

    public void onCancel(String paramString)
    {
      Log.d("TDialog", "onCancel --msg = " + paramString);
      TDialog.a(TDialog.this).obtainMessage(2, paramString).sendToTarget();
      TDialog.this.dismiss();
    }

    public void onCancelAddShare(String paramString)
    {
      Log.d("TDialog", "onCancelAddShare");
      onCancel("cancel");
    }

    public void onCancelInvite()
    {
      Log.d("TDialog", "onCancelInvite");
      onCancel("");
    }

    public void onCancelLogin()
    {
      onCancel("");
    }

    public void onComplete(String paramString)
    {
      TDialog.a(TDialog.this).obtainMessage(1, paramString).sendToTarget();
      Log.e("onComplete", paramString);
      TDialog.this.dismiss();
    }

    public void onInvite(String paramString)
    {
      onComplete(paramString);
    }

    public void onLoad(String paramString)
    {
      TDialog.a(TDialog.this).obtainMessage(4, paramString).sendToTarget();
    }

    public void showMsg(String paramString)
    {
      TDialog.a(TDialog.this).obtainMessage(3, paramString).sendToTarget();
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
    private TDialog.OnTimeListener mL;

    public THandler(TDialog.OnTimeListener paramOnTimeListener, Looper paramLooper)
    {
      super();
      this.mL = paramOnTimeListener;
    }

    public void handleMessage(Message paramMessage)
    {
      Log.d("TAG", "--handleMessage--msg.WHAT = " + paramMessage.what);
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      }
      do
      {
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
          while ((TDialog.a() == null) || (TDialog.a().get() == null));
          TDialog.a((Context)TDialog.a().get(), (String)paramMessage.obj);
          return;
        }
        while ((TDialog.b() == null) || (TDialog.b().get() == null));
        ((View)TDialog.b().get()).setVisibility(8);
        return;
      }
      while ((TDialog.a() == null) || (TDialog.a().get() == null));
      TDialog.b((Context)TDialog.a().get(), (String)paramMessage.obj);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.TDialog
 * JD-Core Version:    0.6.2
 */