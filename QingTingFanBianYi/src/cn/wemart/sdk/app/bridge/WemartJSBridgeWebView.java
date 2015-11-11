package cn.wemart.sdk.app.bridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.wemart.sdk.app.Logger;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WemartJSBridgeWebView extends WebView
  implements JSBridge
{
  private final String TAG = "WemartJSBridge";
  WemartJSBridgeHandler defaultHander = new DefaultJSBridgeHandler();
  Map<String, WemartJSBridgeHandler> messageHandlers = new HashMap();
  Map<String, WemartJSBridgeCallBack> responseCallbacks = new HashMap();
  List<JSBridgeMessage> startupMessage = new ArrayList();
  String toLoadJs = null;
  long uniqueId = 0L;

  public WemartJSBridgeWebView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public WemartJSBridgeWebView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public WemartJSBridgeWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void dispatchMessage(JSBridgeMessage paramJSBridgeMessage)
  {
    paramJSBridgeMessage = String.format("javascript:WemartJSBridge._handleMessageFromNative('%s');", new Object[] { paramJSBridgeMessage.toJson() });
    Logger.i("WemartJSBridge", "分发JavaScript运行脚本到Web端，脚本内容： " + paramJSBridgeMessage);
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      loadUrl(paramJSBridgeMessage);
      return;
    }
    Logger.e("WemartJSBridge", "Cause: Thread.currentThread() != Looper.getMainLooper().getThread(), 当前线程与Main Looper中线程不一致，不能发送JavaScript脚本内容到Web端。");
  }

  private void doSend(String paramString1, WemartJSBridgeCallBack paramWemartJSBridgeCallBack, String paramString2)
  {
    JSBridgeMessage localJSBridgeMessage = new JSBridgeMessage();
    if (!TextUtils.isEmpty(paramString1))
      localJSBridgeMessage.setData(paramString1);
    if (paramWemartJSBridgeCallBack != null)
    {
      long l = this.uniqueId + 1L;
      this.uniqueId = l;
      paramString1 = String.format("JAVA_CB_%s", new Object[] { l + "_" + SystemClock.currentThreadTimeMillis() });
      this.responseCallbacks.put(paramString1, paramWemartJSBridgeCallBack);
      localJSBridgeMessage.setCallbackId(paramString1);
    }
    if (!TextUtils.isEmpty(paramString2))
      localJSBridgeMessage.setHandlerName(paramString2);
    queueMessage(localJSBridgeMessage);
  }

  private void handlerReturnData(String paramString)
  {
    String str = JSBridgeUtil.getFunctionFromReturnUrl(paramString);
    WemartJSBridgeCallBack localWemartJSBridgeCallBack = (WemartJSBridgeCallBack)this.responseCallbacks.get(str);
    paramString = JSBridgeUtil.getDataFromReturnUrl(paramString);
    Logger.i("WemartJSBridge", "处理返回的数据： functionName = " + str + " f = " + localWemartJSBridgeCallBack + " data = " + paramString);
    if (localWemartJSBridgeCallBack != null)
    {
      localWemartJSBridgeCallBack.onCallBack(paramString);
      this.responseCallbacks.remove(str);
    }
  }

  private void init(Context paramContext)
  {
    initWebViewSettings();
    setWebViewClient(new BridgeWebViewClient());
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  private void initWebViewSettings()
  {
    setInitialScale(0);
    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    WebSettings localWebSettings = getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    localWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    localWebSettings.setSaveFormData(false);
    localWebSettings.setSavePassword(false);
    String str = getContext().getApplicationContext().getDir("database", 0).getPath();
    localWebSettings.setDatabaseEnabled(true);
    localWebSettings.setDatabasePath(str);
    localWebSettings.setDomStorageEnabled(true);
    localWebSettings.setAppCacheMaxSize(5242880L);
    localWebSettings.setAppCachePath(str);
    localWebSettings.setAppCacheEnabled(true);
    localWebSettings.getUserAgentString();
  }

  private void queueMessage(JSBridgeMessage paramJSBridgeMessage)
  {
    if (this.startupMessage != null)
    {
      this.startupMessage.add(paramJSBridgeMessage);
      return;
    }
    dispatchMessage(paramJSBridgeMessage);
  }

  public void callAPI(String paramString, WemartJSBridgeCallBack paramWemartJSBridgeCallBack)
  {
    doSend("", paramWemartJSBridgeCallBack, paramString);
  }

  public void callAPI(String paramString1, String paramString2, WemartJSBridgeCallBack paramWemartJSBridgeCallBack)
  {
    doSend(paramString2, paramWemartJSBridgeCallBack, paramString1);
  }

  public void flushMessageQueue()
  {
    if (Thread.currentThread() == Looper.getMainLooper().getThread())
    {
      loadUrl("javascript:WemartJSBridge._fetchJSQueue();", new WemartJSBridgeCallBack()
      {
        public void onCallBack(final String paramAnonymousString)
        {
          List localList;
          try
          {
            localList = JSBridgeMessage.toArrayList(paramAnonymousString);
            if ((localList == null) || (localList.size() == 0))
              return;
          }
          catch (Exception paramAnonymousString)
          {
            return;
          }
          int i = 0;
          JSBridgeMessage localJSBridgeMessage;
          while (i < localList.size())
          {
            localJSBridgeMessage = (JSBridgeMessage)localList.get(i);
            paramAnonymousString = localJSBridgeMessage.getResponseId();
            if (TextUtils.isEmpty(paramAnonymousString))
              break label110;
            ((WemartJSBridgeCallBack)WemartJSBridgeWebView.this.responseCallbacks.get(paramAnonymousString)).onCallBack(localJSBridgeMessage.getResponseData());
            WemartJSBridgeWebView.this.responseCallbacks.remove(paramAnonymousString);
            i += 1;
          }
          label110: paramAnonymousString = localJSBridgeMessage.getCallbackId();
          if (!TextUtils.isEmpty(paramAnonymousString))
          {
            paramAnonymousString = new WemartJSBridgeCallBack()
            {
              public void onCallBack(String paramAnonymous2String)
              {
                Logger.i("WemartJSBridge", "responseFunction " + paramAnonymous2String);
                JSBridgeMessage localJSBridgeMessage = new JSBridgeMessage();
                localJSBridgeMessage.setResponseId(paramAnonymousString);
                localJSBridgeMessage.setResponseData(paramAnonymous2String);
                WemartJSBridgeWebView.this.queueMessage(localJSBridgeMessage);
              }
            };
            label133: if (TextUtils.isEmpty(localJSBridgeMessage.getHandlerName()))
              break label192;
          }
          label192: for (WemartJSBridgeHandler localWemartJSBridgeHandler = (WemartJSBridgeHandler)WemartJSBridgeWebView.this.messageHandlers.get(localJSBridgeMessage.getHandlerName()); ; localWemartJSBridgeHandler = WemartJSBridgeWebView.this.defaultHander)
          {
            localWemartJSBridgeHandler.handler(localJSBridgeMessage.getData(), paramAnonymousString);
            break;
            paramAnonymousString = new WemartJSBridgeCallBack()
            {
              public void onCallBack(String paramAnonymous2String)
              {
              }
            };
            break label133;
          }
        }
      });
      return;
    }
    Logger.i("WemartJSBridge", "Thread.currentThread() != Looper.getMainLooper().getThread(), 当前线程与Main Looper中线程不一致，不能执行flushMessageQueue。");
  }

  public void initContext(String paramString)
  {
    if (paramString != null)
      this.toLoadJs = paramString;
  }

  public void initContextWithHandler(String paramString, WemartJSBridgeHandler paramWemartJSBridgeHandler)
  {
    if (paramString != null)
      this.toLoadJs = paramString;
    if (paramWemartJSBridgeHandler != null)
      this.defaultHander = paramWemartJSBridgeHandler;
  }

  public void loadUrl(String paramString, WemartJSBridgeCallBack paramWemartJSBridgeCallBack)
  {
    loadUrl(paramString);
    this.responseCallbacks.put(JSBridgeUtil.parseFunctionName(paramString), paramWemartJSBridgeCallBack);
    Logger.i("WemartJSBridge", "WemartJSBridgeWebView put map key = " + JSBridgeUtil.parseFunctionName(paramString) + " value = " + paramWemartJSBridgeCallBack);
  }

  public void registerEvent(String paramString, WemartJSBridgeHandler paramWemartJSBridgeHandler)
  {
    if (paramWemartJSBridgeHandler != null)
      this.messageHandlers.put(paramString, paramWemartJSBridgeHandler);
  }

  public void send(String paramString)
  {
    send(paramString, null);
  }

  public void send(String paramString, WemartJSBridgeCallBack paramWemartJSBridgeCallBack)
  {
    doSend(paramString, paramWemartJSBridgeCallBack, null);
  }

  class BridgeWebViewClient extends WebViewClient
  {
    boolean isCurrentlyLoading;

    BridgeWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      super.onPageFinished(paramWebView, paramString);
      if ((!this.isCurrentlyLoading) && (!paramString.startsWith("about:")));
      do
      {
        return;
        this.isCurrentlyLoading = false;
        if (WemartJSBridgeWebView.this.toLoadJs != null)
          JSBridgeUtil.webViewLoadJs(paramWebView, WemartJSBridgeWebView.this.toLoadJs);
      }
      while (WemartJSBridgeWebView.this.startupMessage == null);
      paramWebView = WemartJSBridgeWebView.this.startupMessage.iterator();
      while (true)
      {
        if (!paramWebView.hasNext())
        {
          WemartJSBridgeWebView.this.startupMessage = null;
          return;
        }
        paramString = (JSBridgeMessage)paramWebView.next();
        WemartJSBridgeWebView.this.dispatchMessage(paramString);
      }
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      super.onPageStarted(paramWebView, paramString, paramBitmap);
      this.isCurrentlyLoading = true;
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
      if (!this.isCurrentlyLoading);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      try
      {
        String str = URLDecoder.decode(paramString, "UTF-8");
        paramString = str;
        Logger.i("WemartJSBridge", "调用 shouldOverrideUrlLoading, url = " + paramString);
        if (paramString.startsWith("wtjs://return/"))
        {
          WemartJSBridgeWebView.this.handlerReturnData(paramString);
          return true;
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        while (true)
          localUnsupportedEncodingException.printStackTrace();
        if (paramString.startsWith("wtjs://"))
        {
          WemartJSBridgeWebView.this.flushMessageQueue();
          return true;
        }
      }
      return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.wemart.sdk.app.bridge.WemartJSBridgeWebView
 * JD-Core Version:    0.6.2
 */