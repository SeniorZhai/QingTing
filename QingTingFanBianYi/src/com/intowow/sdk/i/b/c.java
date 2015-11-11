package com.intowow.sdk.i.b;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Method;

public class c
{
  private boolean a;

  public c()
  {
    if (Build.VERSION.SDK_INT >= 11);
    for (boolean bool = true; ; bool = false)
    {
      this.a = bool;
      return;
    }
  }

  private void b(WebView paramWebView, String paramString)
  {
    try
    {
      Class.forName("android.webkit.WebView").getMethod(paramString, null).invoke(paramWebView, null);
      return;
    }
    catch (Exception paramWebView)
    {
    }
  }

  public void a(Handler paramHandler, WebView paramWebView, a parama)
  {
    if ((paramWebView != null) && (paramWebView.getSettings() != null))
    {
      if (this.a)
        paramWebView.getSettings().setDisplayZoomControls(false);
      paramWebView.setVisibility(8);
      a(paramWebView, "onDestroy");
      if (parama != null)
        parama.onDestory();
    }
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  public void a(WebSettings paramWebSettings)
  {
    paramWebSettings.setJavaScriptEnabled(true);
    paramWebSettings.setDomStorageEnabled(true);
    paramWebSettings.setDatabaseEnabled(true);
    paramWebSettings.setDefaultTextEncodingName("UTF-8");
    paramWebSettings.setUseWideViewPort(true);
    paramWebSettings.setLoadWithOverviewMode(true);
    paramWebSettings.setBuiltInZoomControls(true);
  }

  public void a(WebView paramWebView, String paramString)
  {
    if (paramWebView == null);
    do
    {
      return;
      if (!this.a)
        break;
      if (paramString.equals("onResume"))
      {
        paramWebView.onResume();
        return;
      }
      if (paramString.equals("onPause"))
      {
        paramWebView.onPause();
        return;
      }
    }
    while (!paramString.equals("onDestroy"));
    paramWebView.clearCache(true);
    paramString = (ViewGroup)paramWebView.getParent();
    if (paramString != null)
      paramString.removeView(paramWebView);
    paramWebView.clearHistory();
    paramWebView.destroy();
    return;
    b(paramWebView, paramString);
  }

  public static abstract interface a
  {
    public abstract void onDestory();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.b.c
 * JD-Core Version:    0.6.2
 */