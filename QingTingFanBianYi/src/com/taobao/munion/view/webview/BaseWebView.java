package com.taobao.munion.view.webview;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.taobao.munion.base.Log;
import com.taobao.newxp.common.ExchangeConstants;
import java.io.File;
import java.lang.reflect.Method;

public class BaseWebView extends WebView
{
  protected Context a;
  protected b b;

  public BaseWebView(Context paramContext)
  {
    super(paramContext);
    this.a = paramContext;
    a();
  }

  public BaseWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.a = paramContext;
    a();
  }

  public BaseWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.a = paramContext;
    a();
  }

  protected void a()
  {
    if (this.b == null)
    {
      this.b = new b();
      setWebViewClient(this.b);
    }
    setVerticalScrollBarEnabled(false);
    requestFocus();
    WebSettings localWebSettings = getSettings();
    localWebSettings.setAllowFileAccess(true);
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setAppCacheEnabled(true);
    localWebSettings.setAppCacheMaxSize(5242880L);
    localWebSettings.setAllowFileAccess(true);
    localWebSettings.setBuiltInZoomControls(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setSupportZoom(false);
    localWebSettings.setSavePassword(false);
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setUseWideViewPort(true);
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
    Object localObject;
    if (Build.VERSION.SDK_INT >= 5)
    {
      localWebSettings.setDatabaseEnabled(true);
      localObject = this.a.getDir("database", 0).getPath();
      localWebSettings.setDatabasePath((String)localObject);
      localWebSettings.setGeolocationEnabled(true);
      localWebSettings.setGeolocationDatabasePath((String)localObject);
    }
    if (Build.VERSION.SDK_INT >= 11);
    try
    {
      localObject = WebSettings.class.getDeclaredMethod("setDisplayZoomControls", new Class[] { Boolean.TYPE });
      ((Method)localObject).setAccessible(true);
      ((Method)localObject).invoke(localWebSettings, new Object[] { Boolean.valueOf(false) });
      return;
    }
    catch (Exception localException)
    {
      Log.w(ExchangeConstants.LOG_TAG, new Object[] { "", localException });
    }
  }

  public void setFilter(a parama)
  {
    if (this.b != null)
      this.b.a(parama);
  }

  public void setWebViewClient(WebViewClient paramWebViewClient)
  {
    super.setWebViewClient(paramWebViewClient);
    if ((paramWebViewClient instanceof b))
      this.b = ((b)paramWebViewClient);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.BaseWebView
 * JD-Core Version:    0.6.2
 */