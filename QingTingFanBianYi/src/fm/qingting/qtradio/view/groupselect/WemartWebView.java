package fm.qingting.qtradio.view.groupselect;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.widget.LinearLayout.LayoutParams;
import cn.wemart.sdk.app.bridge.WemartJSBridgeCallBack;
import cn.wemart.sdk.app.bridge.WemartJSBridgeHandler;
import cn.wemart.sdk.app.bridge.WemartJSBridgeWebView;
import cn.wemart.sdk.app.pay.PayResult;
import cn.wemart.sdk.app.pay.PayUtils;
import com.alipay.sdk.app.PayTask;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.qtradio.view.MiniPlayerView;

public class WemartWebView extends ViewGroupViewImpl
{
  private static final int SDK_PAY_FLAG = 1;
  private String URL = "http://qingting.fm";
  private boolean hasRemoved = false;
  private Context mContext;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
      }
      ((WemartJSBridgeCallBack)paramAnonymousMessage.obj).onCallBack(String.valueOf(paramAnonymousMessage.arg1));
    }
  };
  private MiniPlayerView mMiniView;
  private boolean needMiniPlayer = false;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private WemartJSBridgeWebView webView = null;
  private final ViewLayout webviewLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL | ViewLayout.SLTR | ViewLayout.SC | ViewLayout.CW | ViewLayout.SVH);

  public WemartWebView(Context paramContext, String paramString, boolean paramBoolean)
  {
    super(paramContext);
    this.mContext = paramContext;
    this.needMiniPlayer = paramBoolean;
    this.URL = paramString;
    init();
    if (this.needMiniPlayer)
    {
      this.mMiniView = new MiniPlayerView(paramContext);
      addView(this.mMiniView);
    }
  }

  private void removeLoading()
  {
  }

  public boolean canBack()
  {
    if (this.webView != null)
      return this.webView.canGoBack();
    return false;
  }

  public void destroy()
  {
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == null)
      return false;
    if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1));
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public String getUrl()
  {
    return this.URL;
  }

  public void goBack()
  {
    if (this.webView != null)
      this.webView.goBack();
  }

  @TargetApi(18)
  protected void init()
  {
    try
    {
      this.webView = new WemartJSBridgeWebView(this.mContext);
      Object localObject = this.webView.getSettings().getUserAgentString();
      this.webView.getSettings().setUserAgentString((String)localObject + "; WemartApp/1.0; app=" + Constants.WEMART_APP_ID);
      this.webView.initContext("http://www.wemart.cn/v2/sdk/WemartJSBridge.js");
      this.webView.loadUrl(this.URL);
      this.webView.registerEvent("nativePay", new WemartJSBridgeHandler()
      {
        public void handler(String paramAnonymousString, WemartJSBridgeCallBack paramAnonymousWemartJSBridgeCallBack)
        {
          if (paramAnonymousString != null)
            WemartWebView.this.pay(paramAnonymousString, paramAnonymousWemartJSBridgeCallBack);
        }
      });
      localObject = new LinearLayout.LayoutParams(-1, -1);
      addView(this.webView, (ViewGroup.LayoutParams)localObject);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = 0;
    if (this.needMiniPlayer)
    {
      this.mMiniView.layout(0, this.standardLayout.height - this.mMiniView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
      paramInt1 = this.mMiniView.getHeightWithoutShadow();
    }
    this.webView.layout(this.webviewLayout.getLeft(), this.webviewLayout.getTop(), this.webviewLayout.getRight(), this.webviewLayout.getBottom() - paramInt1);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.webviewLayout.scaleToBounds(this.standardLayout);
    paramInt1 = 0;
    if (this.needMiniPlayer)
    {
      this.standardLayout.measureView(this.mMiniView);
      paramInt1 = this.mMiniView.getHeightWithoutShadow();
    }
    this.webView.measure(this.webviewLayout.getWidthMeasureSpec(), this.webviewLayout.getHeightMeasureSpec() - paramInt1);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  protected void onViewWillClose()
  {
  }

  public void pay(String paramString, final WemartJSBridgeCallBack paramWemartJSBridgeCallBack)
  {
    if (paramString == null)
      return;
    new Thread(new Runnable()
    {
      public void run()
      {
        String str = new PayResult(new PayTask((Activity)WemartWebView.this.mContext).pay(this.val$payInfo)).getResultStatus();
        Message localMessage = new Message();
        localMessage.arg1 = Integer.parseInt(str);
        localMessage.what = 1;
        localMessage.obj = paramWemartJSBridgeCallBack;
        WemartWebView.this.mHandler.sendMessage(localMessage);
      }
    }).start();
  }

  public void release()
  {
    if (this.webView != null)
    {
      this.webView.removeAllViews();
      this.webView.destroy();
      this.webView = null;
    }
  }

  public void update(String paramString, Object paramObject)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.groupselect.WemartWebView
 * JD-Core Version:    0.6.2
 */