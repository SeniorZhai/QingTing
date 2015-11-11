package fm.qingting.qtradio.jd.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View.MeasureSpec;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class JDShopView extends ViewGroupViewImpl
{
  private static WebView webView;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);
  private final ViewLayout webviewLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL | ViewLayout.SLTR | ViewLayout.SC | ViewLayout.CW | ViewLayout.SVH);

  @SuppressLint({"JavascriptInterface"})
  public JDShopView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(17170443);
    webView = new WebView(paramContext);
    webView.setWebChromeClient(new WebChromeClient()
    {
    });
    webView.setWebViewClient(new WebViewClient()
    {
    });
    paramContext = webView.getSettings();
    paramContext.setJavaScriptEnabled(true);
    paramContext.setJavaScriptCanOpenWindowsAutomatically(true);
    paramContext.setAppCacheEnabled(false);
    paramContext.setJavaScriptEnabled(true);
    addView(webView);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.standardLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.webviewLayout.scaleToBounds(this.standardLayout);
    webView.layout(this.webviewLayout.getLeft(), this.webviewLayout.getTop(), this.webviewLayout.getRight(), this.webviewLayout.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    webView.measure(this.webviewLayout.getWidthMeasureSpec(), this.webviewLayout.getHeightMeasureSpec());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      webView.loadUrl((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.jd.view.JDShopView
 * JD-Core Version:    0.6.2
 */