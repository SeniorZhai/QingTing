package com.taobao.munion.view.webview.windvane;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import com.taobao.munion.view.webview.BaseWebView;

public class WindVaneWebView extends BaseWebView
{
  protected l c;
  protected d d;
  protected f e;

  public WindVaneWebView(Context paramContext)
  {
    super(paramContext);
  }

  public WindVaneWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public WindVaneWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void a()
  {
    super.a();
    getSettings().setUserAgentString(getSettings().getUserAgentString() + " WindVane/3.0.0");
    if (this.c == null)
      this.c = new l(this);
    setWebViewChromeClient(this.c);
    this.b = new m();
    setWebViewClient(this.b);
    if (this.d == null)
    {
      this.d = new i(this.a);
      setJsBridge(this.d);
    }
    this.e = new f(this.a, this);
  }

  public d getJsBridge()
  {
    return this.d;
  }

  public Object getJsObject(String paramString)
  {
    if (this.e == null)
      return null;
    return this.e.a(paramString);
  }

  public void registerWindVanePlugin(Class paramClass)
  {
    if (this.e == null)
      return;
    this.e.a(paramClass);
  }

  public void setJsBridge(d paramd)
  {
    this.d = paramd;
    paramd.a(this);
  }

  public void setWebViewChromeClient(l paraml)
  {
    this.c = paraml;
    setWebChromeClient(paraml);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.windvane.WindVaneWebView
 * JD-Core Version:    0.6.2
 */