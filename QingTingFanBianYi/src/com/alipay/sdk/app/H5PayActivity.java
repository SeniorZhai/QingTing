package com.alipay.sdk.app;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.alipay.sdk.cons.GlobalConstants;
import com.alipay.sdk.util.Utils;
import com.alipay.sdk.widget.Loading;
import java.lang.reflect.Method;
import java.net.URLDecoder;

public class H5PayActivity extends Activity
{
  private WebView a;
  private Loading b;
  private Handler c = new Handler();
  private boolean d;
  private boolean e;
  private Runnable f = new H5PayActivity.1(this);

  private static void a()
  {
    synchronized (PayTask.a)
    {
      try
      {
        ???.notify();
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  private void b()
  {
    if (this.b == null)
      this.b = new Loading(this);
    this.b.b();
  }

  private void c()
  {
    if ((this.b != null) && (this.b.a()))
      this.b.c();
    this.b = null;
  }

  public void finish()
  {
    synchronized (PayTask.a)
    {
      try
      {
        ???.notify();
        super.finish();
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  public void onBackPressed()
  {
    if (this.a.canGoBack())
    {
      if (this.d)
      {
        ResultStatus localResultStatus = ResultStatus.a(ResultStatus.d.a());
        Result.a(Result.a(localResultStatus.a(), localResultStatus.b(), ""));
        finish();
      }
      return;
    }
    Result.a(Result.b());
    finish();
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject = getIntent().getExtras();
    if (localObject == null)
      finish();
    while (true)
    {
      return;
      paramBundle = ((Bundle)localObject).getString("url");
      if (!Utils.a(paramBundle))
      {
        finish();
        return;
      }
      super.requestWindowFeature(1);
      localObject = ((Bundle)localObject).getString("cookie");
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        CookieSyncManager.createInstance(this).sync();
        CookieManager.getInstance().setCookie(paramBundle, (String)localObject);
        CookieSyncManager.getInstance().sync();
      }
      localObject = new LinearLayout(this);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
      ((LinearLayout)localObject).setOrientation(1);
      setContentView((View)localObject, localLayoutParams);
      this.a = new WebView(this);
      localLayoutParams.weight = 1.0F;
      this.a.setVisibility(0);
      ((LinearLayout)localObject).addView(this.a, localLayoutParams);
      localObject = this.a.getSettings();
      ((WebSettings)localObject).setUserAgentString(((WebSettings)localObject).getUserAgentString() + Utils.c(this));
      ((WebSettings)localObject).setRenderPriority(WebSettings.RenderPriority.HIGH);
      ((WebSettings)localObject).setSupportMultipleWindows(true);
      ((WebSettings)localObject).setJavaScriptEnabled(true);
      ((WebSettings)localObject).setSavePassword(false);
      ((WebSettings)localObject).setJavaScriptCanOpenWindowsAutomatically(true);
      ((WebSettings)localObject).setMinimumFontSize(((WebSettings)localObject).getMinimumFontSize() + 8);
      ((WebSettings)localObject).setAllowFileAccess(false);
      this.a.setVerticalScrollbarOverlay(true);
      this.a.setWebViewClient(new MyWebViewClient((byte)0));
      this.a.setWebChromeClient(new MyWebChromeClient((byte)0));
      this.a.loadUrl(paramBundle);
      if (Build.VERSION.SDK_INT >= 7);
      try
      {
        paramBundle = this.a.getSettings().getClass().getMethod("setDomStorageEnabled", new Class[] { Boolean.TYPE });
        if (paramBundle != null)
          paramBundle.invoke(this.a.getSettings(), new Object[] { Boolean.valueOf(true) });
        try
        {
          label335: paramBundle = this.a.getClass().getMethod("removeJavascriptInterface", new Class[0]);
          if (paramBundle == null)
            continue;
          paramBundle.invoke(this.a, new Object[] { "searchBoxJavaBridge_" });
          return;
        }
        catch (Exception paramBundle)
        {
        }
      }
      catch (Exception paramBundle)
      {
        break label335;
      }
    }
  }

  private class MyWebChromeClient extends WebChromeClient
  {
    private MyWebChromeClient()
    {
    }

    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      new AlertDialog.Builder(H5PayActivity.this).setTitle("提示").setMessage(paramString2).setPositiveButton("确定", new H5PayActivity.MyWebChromeClient.2(this, paramJsResult)).setNegativeButton("取消", new H5PayActivity.MyWebChromeClient.1(this, paramJsResult)).show();
      return true;
    }

    public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      new AlertDialog.Builder(H5PayActivity.this).setTitle("提示").setMessage(paramString2).setPositiveButton("确定", new H5PayActivity.MyWebChromeClient.4(this, paramJsResult)).setNegativeButton("取消", new H5PayActivity.MyWebChromeClient.3(this, paramJsResult)).show();
      return true;
    }

    public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
    {
      new AlertDialog.Builder(H5PayActivity.this).setTitle("提示").setMessage(paramString2).setPositiveButton("确定", new H5PayActivity.MyWebChromeClient.6(this, paramJsPromptResult)).setNegativeButton("取消", new H5PayActivity.MyWebChromeClient.5(this, paramJsPromptResult)).show();
      return true;
    }
  }

  private class MyWebViewClient extends WebViewClient
  {
    private MyWebViewClient()
    {
    }

    public void onFormResubmission(WebView paramWebView, Message paramMessage1, Message paramMessage2)
    {
    }

    public void onLoadResource(WebView paramWebView, String paramString)
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      H5PayActivity.f(H5PayActivity.this);
      H5PayActivity.e(H5PayActivity.this).removeCallbacks(H5PayActivity.d(H5PayActivity.this));
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      H5PayActivity.c(H5PayActivity.this);
      H5PayActivity.e(H5PayActivity.this).postDelayed(H5PayActivity.d(H5PayActivity.this), 30000L);
      super.onPageStarted(paramWebView, paramString, paramBitmap);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      H5PayActivity.a(H5PayActivity.this);
      super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }

    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      if (H5PayActivity.b(H5PayActivity.this))
      {
        paramSslErrorHandler.proceed();
        H5PayActivity.a(H5PayActivity.this, false);
        return;
      }
      H5PayActivity.this.runOnUiThread(new H5PayActivity.MyWebViewClient.1(this, paramSslErrorHandler));
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      if (paramString.startsWith("alipays://platformapi/startApp?"))
        return false;
      if ((TextUtils.equals(paramString, "sdklite://h5quit")) || (TextUtils.equals(paramString, "http://m.alipay.com/?action=h5quit")))
      {
        Result.a(Result.b());
        H5PayActivity.this.finish();
        return true;
      }
      if (paramString.startsWith("sdklite://h5quit?result="))
        try
        {
          paramWebView = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + 24);
          int i = Integer.parseInt(paramWebView.substring(paramWebView.lastIndexOf("&end_code=") + 10));
          StringBuilder localStringBuilder;
          int j;
          if ((i == ResultStatus.a.a()) || (i == ResultStatus.f.a()))
          {
            localStringBuilder = new StringBuilder();
            if (GlobalConstants.n)
            {
              paramWebView = URLDecoder.decode(paramString);
              paramString = URLDecoder.decode(paramWebView);
              paramString = paramString.substring(paramString.indexOf("sdklite://h5quit?result=") + 24, paramString.lastIndexOf("&end_code=")).split("&return_url=")[0];
              j = paramWebView.indexOf("&return_url=") + 12;
              localStringBuilder.append(paramString).append("&return_url=").append(paramWebView.substring(j, paramWebView.indexOf("&", j))).append(paramWebView.substring(paramWebView.indexOf("&", j)));
              paramWebView = localStringBuilder.toString();
              paramString = ResultStatus.a(i);
              Result.a(Result.a(paramString.a(), paramString.b(), paramWebView));
            }
          }
          while (true)
          {
            paramWebView = new H5PayActivity.MyWebViewClient.2(this);
            H5PayActivity.this.runOnUiThread(paramWebView);
            return true;
            paramWebView = URLDecoder.decode(paramString);
            paramString = paramWebView.substring(paramWebView.indexOf("sdklite://h5quit?result=") + 24, paramWebView.lastIndexOf("&end_code="));
            paramWebView = paramString;
            if (!paramString.contains("&return_url=\""))
              break;
            paramWebView = paramString.split("&return_url=\"")[0];
            j = paramString.indexOf("&return_url=\"") + 13;
            localStringBuilder.append(paramWebView).append("&return_url=\"").append(paramString.substring(j, paramString.indexOf("\"&", j))).append(paramString.substring(paramString.indexOf("\"&", j)));
            paramWebView = localStringBuilder.toString();
            break;
            paramWebView = ResultStatus.a(ResultStatus.b.a());
            Result.a(Result.a(paramWebView.a(), paramWebView.b(), ""));
          }
        }
        catch (Exception paramWebView)
        {
          while (true)
            Result.a(Result.c());
        }
      paramWebView.loadUrl(paramString);
      return true;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.app.H5PayActivity
 * JD-Core Version:    0.6.2
 */