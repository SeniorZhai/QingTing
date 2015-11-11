package com.taobao.munion.view.webview.windvane;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class l extends WebChromeClient
{
  WindVaneWebView b;

  public l(WindVaneWebView paramWindVaneWebView)
  {
    this.b = paramWindVaneWebView;
  }

  public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
  {
    paramWebView = this.b.getJsBridge();
    if ((paramWebView != null) && (paramString3 != null) && (paramWebView.a(paramString3)))
    {
      paramWebView.c(paramString2);
      paramJsPromptResult.confirm("");
      return true;
    }
    return false;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.l
 * JD-Core Version:    0.6.2
 */