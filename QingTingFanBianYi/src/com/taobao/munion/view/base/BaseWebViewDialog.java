package com.taobao.munion.view.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import com.taobao.munion.view.webview.BaseWebView;
import com.taobao.newxp.common.ExchangeConstants;
import java.lang.reflect.Method;

public abstract class BaseWebViewDialog extends Dialog
{
  public Context mContext;
  public BaseWebView mWebView;

  public BaseWebViewDialog(Context paramContext)
  {
    this(paramContext, 16973840);
  }

  public BaseWebViewDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.mContext = paramContext;
    paramContext = getWindow().getAttributes();
    paramContext.height = -1;
    paramContext.width = -1;
    paramContext.gravity = 17;
    getWindow().setAttributes(paramContext);
  }

  public void dismiss()
  {
    super.dismiss();
    if (this.mWebView != null);
    try
    {
      this.mWebView.removeAllViews();
      this.mWebView.loadUrl("about:blank");
      this.mWebView.destroy();
      ((ViewGroup)this.mWebView.getParent()).removeView(this.mWebView);
      return;
    }
    catch (Exception localException)
    {
      Log.e("Munion", "", localException);
    }
  }

  public abstract void initContent();

  public void initWebview(WebView paramWebView)
  {
    paramWebView = paramWebView.getSettings();
    paramWebView.setJavaScriptEnabled(true);
    if (Build.VERSION.SDK_INT > 7)
      paramWebView.setPluginState(WebSettings.PluginState.ON);
    paramWebView.setSupportZoom(true);
    paramWebView.setBuiltInZoomControls(true);
    paramWebView.setAllowFileAccess(true);
    paramWebView.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    paramWebView.setUseWideViewPort(true);
    if (Build.VERSION.SDK_INT >= 8)
    {
      paramWebView.setLoadWithOverviewMode(true);
      paramWebView.setDatabaseEnabled(true);
      paramWebView.setDomStorageEnabled(true);
      paramWebView.setGeolocationEnabled(true);
      paramWebView.setAppCacheEnabled(true);
    }
    if (Build.VERSION.SDK_INT >= 11);
    try
    {
      Method localMethod = WebSettings.class.getDeclaredMethod("setDisplayZoomControls", new Class[] { Boolean.TYPE });
      localMethod.setAccessible(true);
      localMethod.invoke(paramWebView, new Object[] { Boolean.valueOf(false) });
      return;
    }
    catch (Exception paramWebView)
    {
      paramWebView.printStackTrace();
    }
  }

  public abstract void onLoadUrl();

  public void show()
  {
    super.show();
    try
    {
      initContent();
      if (this.mWebView == null)
        throw new NullPointerException("the webview is null.");
    }
    catch (Exception localException)
    {
      Log.e(ExchangeConstants.LOG_TAG, "open browser failed.", localException);
      return;
    }
    initWebview(this.mWebView);
    onLoadUrl();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.base.BaseWebViewDialog
 * JD-Core Version:    0.6.2
 */