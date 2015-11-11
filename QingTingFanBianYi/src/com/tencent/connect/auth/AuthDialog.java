package com.tencent.connect.auth;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.open.a.c;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.ServerSetting;
import com.tencent.utils.Util;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthDialog extends Dialog
{
  private static WeakReference<Context> a;
  private static WeakReference<View> l;
  private String b;
  private OnTimeListener c;
  private IUiListener d;
  private Handler e;
  private FrameLayout f;
  private LinearLayout g;
  private FrameLayout h;
  private ProgressBar i;
  private String j;
  private WebView k;
  private boolean m = false;

  public AuthDialog(Context paramContext, String paramString1, String paramString2, IUiListener paramIUiListener, QQToken paramQQToken)
  {
    super(paramContext, 16973840);
    a = new WeakReference(paramContext.getApplicationContext());
    this.b = paramString2;
    this.c = new OnTimeListener(paramString1, paramString2, paramQQToken.getAppId(), paramIUiListener);
    this.e = new THandler(this.c, paramContext.getMainLooper());
    this.d = paramIUiListener;
    this.j = paramString1;
  }

  private static void b(Context paramContext, String paramString)
  {
    try
    {
      paramString = Util.parseJson(paramString);
      int n = paramString.getInt("type");
      paramString = paramString.getString("msg");
      Toast.makeText(paramContext.getApplicationContext(), paramString, n).show();
      return;
    }
    catch (JSONException paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  private void c()
  {
    d();
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(-1, -1);
    this.k = new WebView((Context)a.get());
    this.k.setLayoutParams(localLayoutParams);
    this.f = new FrameLayout((Context)a.get());
    localLayoutParams.gravity = 17;
    this.f.setLayoutParams(localLayoutParams);
    this.f.addView(this.k);
    this.f.addView(this.h);
    l = new WeakReference(this.h);
    setContentView(this.f);
  }

  private void d()
  {
    this.i = new ProgressBar((Context)a.get());
    Object localObject1 = new LinearLayout.LayoutParams(-2, -2);
    this.i.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.g = new LinearLayout((Context)a.get());
    localObject1 = null;
    Object localObject2;
    if (this.j.equals("action_login"))
    {
      localObject2 = new LinearLayout.LayoutParams(-2, -2);
      ((LinearLayout.LayoutParams)localObject2).gravity = 16;
      ((LinearLayout.LayoutParams)localObject2).leftMargin = 5;
      localObject1 = new TextView((Context)a.get());
      if (!Locale.getDefault().getLanguage().equals("zh"))
        break label301;
      ((TextView)localObject1).setText("登录中...");
    }
    while (true)
    {
      ((TextView)localObject1).setTextColor(Color.rgb(255, 255, 255));
      ((TextView)localObject1).setTextSize(18.0F);
      ((TextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
      localObject2 = new FrameLayout.LayoutParams(-2, -2);
      ((FrameLayout.LayoutParams)localObject2).gravity = 17;
      this.g.setLayoutParams((ViewGroup.LayoutParams)localObject2);
      this.g.addView(this.i);
      if (localObject1 != null)
        this.g.addView((View)localObject1);
      this.h = new FrameLayout((Context)a.get());
      localObject1 = new FrameLayout.LayoutParams(-1, -2);
      ((FrameLayout.LayoutParams)localObject1).leftMargin = 80;
      ((FrameLayout.LayoutParams)localObject1).rightMargin = 80;
      ((FrameLayout.LayoutParams)localObject1).topMargin = 40;
      ((FrameLayout.LayoutParams)localObject1).bottomMargin = 40;
      ((FrameLayout.LayoutParams)localObject1).gravity = 17;
      this.h.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      this.h.setBackgroundResource(17301504);
      this.h.addView(this.g);
      return;
      label301: ((TextView)localObject1).setText("Logging in...");
    }
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  private void e()
  {
    this.k.setVerticalScrollBarEnabled(false);
    this.k.setHorizontalScrollBarEnabled(false);
    this.k.setWebViewClient(new LoginWebViewClient(null));
    this.k.setWebChromeClient(new WebChromeClient());
    this.k.clearFormData();
    WebSettings localWebSettings = this.k.getSettings();
    localWebSettings.setSavePassword(false);
    localWebSettings.setSaveFormData(false);
    localWebSettings.setCacheMode(-1);
    localWebSettings.setNeedInitialFocus(false);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    localWebSettings.setJavaScriptEnabled(true);
    if ((a != null) && (a.get() != null))
    {
      localWebSettings.setDatabaseEnabled(true);
      localWebSettings.setDatabasePath(((Context)a.get()).getApplicationContext().getDir("databases", 0).getPath());
    }
    localWebSettings.setDomStorageEnabled(true);
    try
    {
      WebView.class.getMethod("addJavascriptInterface", new Class[] { Object.class, String.class }).invoke(this.k, new Object[] { new JsListener(null), "sdk_js_if" });
      this.k.loadUrl(this.b);
      this.k.setVisibility(4);
      this.k.getSettings().setSavePassword(false);
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      while (true)
        localNoSuchMethodException.printStackTrace();
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        localIllegalArgumentException.printStackTrace();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      while (true)
        localIllegalAccessException.printStackTrace();
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      while (true)
        localInvocationTargetException.printStackTrace();
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("AuthDialog", localException.getMessage());
    }
  }

  private boolean f()
  {
    boolean bool2 = false;
    Object localObject1 = AuthMap.getInstance();
    String str = ((AuthMap)localObject1).makeKey();
    Object localObject2 = new AuthMap.Auth();
    ((AuthMap.Auth)localObject2).listener = this.d;
    ((AuthMap.Auth)localObject2).dialog = this;
    ((AuthMap.Auth)localObject2).key = str;
    localObject1 = ((AuthMap)localObject1).set((AuthMap.Auth)localObject2);
    localObject2 = this.b.substring(0, this.b.indexOf("?"));
    Bundle localBundle = Util.parseUrl(this.b);
    localBundle.putString("token_key", str);
    localBundle.putString("serial", (String)localObject1);
    localBundle.putString("browser", "1");
    this.b = ((String)localObject2 + "?" + Util.encodeUrl(localBundle));
    boolean bool1 = bool2;
    if (a != null)
    {
      bool1 = bool2;
      if (a.get() != null)
        bool1 = Util.openBrowser((Context)a.get(), this.b);
    }
    return bool1;
  }

  public void callJs(String paramString1, String paramString2)
  {
    paramString1 = "javascript:" + paramString1 + "(" + paramString2 + ");void(" + System.currentTimeMillis() + ");";
    this.k.loadUrl(paramString1);
  }

  public void onBackPressed()
  {
    if (!this.m)
      this.c.onCancel();
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    c();
    e();
  }

  private class JsListener
  {
    private JsListener()
    {
    }

    public void onCancel(String paramString)
    {
      AuthDialog.f(AuthDialog.this).obtainMessage(2, paramString).sendToTarget();
      AuthDialog.this.dismiss();
    }

    public void onCancelLogin()
    {
      onCancel(null);
    }

    public void onLoad(String paramString)
    {
      AuthDialog.f(AuthDialog.this).obtainMessage(4, paramString).sendToTarget();
    }

    public void showMsg(String paramString)
    {
      AuthDialog.f(AuthDialog.this).obtainMessage(3, paramString).sendToTarget();
    }
  }

  private class LoginWebViewClient extends WebViewClient
  {
    private LoginWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      if ((AuthDialog.b() != null) && (AuthDialog.b().get() != null))
        ((View)AuthDialog.b().get()).setVisibility(8);
      AuthDialog.d(AuthDialog.this).setVisibility(0);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      Util.logd("AuthDialog", "Webview loading URL: " + paramString);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      if ((AuthDialog.b() != null) && (AuthDialog.b().get() != null))
        ((View)AuthDialog.b().get()).setVisibility(0);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
      AuthDialog.e(AuthDialog.this).onError(new UiError(paramInt, paramString1, paramString2));
      if ((AuthDialog.a() != null) && (AuthDialog.a().get() != null))
        Toast.makeText((Context)AuthDialog.a().get(), "网络连接异常或系统错误", 0).show();
      AuthDialog.this.dismiss();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Util.logd("AuthDialog", "Redirect URL: " + paramString);
      if (paramString.startsWith("auth://browser"))
      {
        paramWebView = Util.parseUrlToJson(paramString);
        AuthDialog.a(AuthDialog.this, AuthDialog.a(AuthDialog.this));
        if (AuthDialog.b(AuthDialog.this));
        while (true)
        {
          return true;
          if (paramWebView.optString("fail_cb", null) != null)
          {
            AuthDialog.this.callJs(paramWebView.optString("fail_cb"), "");
          }
          else
          {
            if (paramWebView.optInt("fall_to_wv") == 1)
            {
              paramString = AuthDialog.this;
              if (AuthDialog.c(AuthDialog.this).indexOf("?") > -1);
              for (paramWebView = "&"; ; paramWebView = "?")
              {
                AuthDialog.a(paramString, paramWebView);
                AuthDialog.a(AuthDialog.this, "browser_error=1");
                AuthDialog.d(AuthDialog.this).loadUrl(AuthDialog.c(AuthDialog.this));
                break;
              }
            }
            paramWebView = paramWebView.optString("redir", null);
            if (paramWebView != null)
              AuthDialog.d(AuthDialog.this).loadUrl(paramWebView);
          }
        }
      }
      if (paramString.startsWith(ServerSetting.getInstance().getEnvUrl((Context)AuthDialog.a().get(), "auth://tauth.qq.com/")))
      {
        AuthDialog.e(AuthDialog.this).onComplete(Util.parseUrlToJson(paramString));
        AuthDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://cancel"))
      {
        AuthDialog.e(AuthDialog.this).onCancel();
        AuthDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("auth://close"))
      {
        AuthDialog.this.dismiss();
        return true;
      }
      if (paramString.startsWith("download://"))
      {
        paramWebView = new Intent("android.intent.action.VIEW", Uri.parse(Uri.decode(paramString.substring("download://".length()))));
        paramWebView.addFlags(268435456);
        if ((AuthDialog.a() != null) && (AuthDialog.a().get() != null))
          ((Context)AuthDialog.a().get()).startActivity(paramWebView);
        return true;
      }
      return false;
    }
  }

  private class OnTimeListener
    implements IUiListener
  {
    String a;
    String b;
    private String d;
    private IUiListener e;

    public OnTimeListener(String paramString1, String paramString2, String paramIUiListener, IUiListener arg5)
    {
      this.d = paramString1;
      this.a = paramString2;
      this.b = paramIUiListener;
      Object localObject;
      this.e = localObject;
    }

    private void a(String paramString)
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
      if (this.e != null)
      {
        this.e.onCancel();
        this.e = null;
      }
    }

    public void onComplete(Object paramObject)
    {
      paramObject = (JSONObject)paramObject;
      c.a().a((Context)AuthDialog.a().get(), this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, paramObject.optInt("ret", -6), this.b, this.a, "1000067");
      if (this.e != null)
      {
        this.e.onComplete(paramObject);
        this.e = null;
      }
    }

    public void onError(UiError paramUiError)
    {
      if (paramUiError.errorMessage != null);
      for (String str = paramUiError.errorMessage + this.a; ; str = this.a)
      {
        c.a().a((Context)AuthDialog.a().get(), this.d + "_H5", SystemClock.elapsedRealtime(), 0L, 0L, paramUiError.errorCode, this.b, str, "1000067");
        if (this.e != null)
        {
          this.e.onError(paramUiError);
          this.e = null;
        }
        return;
      }
    }
  }

  private static class THandler extends Handler
  {
    private AuthDialog.OnTimeListener a;

    public THandler(AuthDialog.OnTimeListener paramOnTimeListener, Looper paramLooper)
    {
      super();
      this.a = paramOnTimeListener;
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      }
      do
      {
        return;
        AuthDialog.OnTimeListener.a(this.a, (String)paramMessage.obj);
        return;
        this.a.onCancel();
        return;
      }
      while ((AuthDialog.a() == null) || (AuthDialog.a().get() == null));
      AuthDialog.a((Context)AuthDialog.a().get(), (String)paramMessage.obj);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.auth.AuthDialog
 * JD-Core Version:    0.6.2
 */