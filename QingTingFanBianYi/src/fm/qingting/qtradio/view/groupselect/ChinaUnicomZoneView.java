package fm.qingting.qtradio.view.groupselect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View.MeasureSpec;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

@SuppressLint({"SetJavaScriptEnabled"})
public class ChinaUnicomZoneView extends ViewGroupViewImpl
{
  private static final String URL = "http://iread.wo.com.cn/st/womediawap/qingting/";
  private static WebView webView;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);
  private final ViewLayout webviewLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL | ViewLayout.SLTR | ViewLayout.SC | ViewLayout.CW | ViewLayout.SVH);

  @SuppressLint({"JavascriptInterface"})
  public ChinaUnicomZoneView(Context paramContext)
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
    paramContext.setPluginState(WebSettings.PluginState.ON);
    paramContext.setRenderPriority(WebSettings.RenderPriority.HIGH);
    webView.addJavascriptInterface(new QTPlayerJavascriptInterface(), "QTPlayer");
    webView.loadUrl("http://iread.wo.com.cn/st/womediawap/qingting/");
    addView(webView);
  }

  public void goBack()
  {
    webView.loadUrl("javascript:goBack()");
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
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.groupselect.ChinaUnicomZoneView
 * JD-Core Version:    0.6.2
 */