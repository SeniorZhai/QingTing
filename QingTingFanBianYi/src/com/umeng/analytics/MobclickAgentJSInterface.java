package com.umeng.analytics;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

public class MobclickAgentJSInterface
{
  private Context a;

  public MobclickAgentJSInterface(Context paramContext, WebView paramWebView)
  {
    this.a = paramContext;
    paramWebView.getSettings().setJavaScriptEnabled(true);
    paramWebView.setWebChromeClient(new a(null));
  }

  public MobclickAgentJSInterface(Context paramContext, WebView paramWebView, WebChromeClient paramWebChromeClient)
  {
    this.a = paramContext;
    paramWebView.getSettings().setJavaScriptEnabled(true);
    paramWebView.setWebChromeClient(new a(paramWebChromeClient));
  }

  final class a extends WebChromeClient
  {
    WebChromeClient a = null;
    private final String c = "ekv";
    private final String d = "event";

    public a(WebChromeClient arg2)
    {
      Object localObject;
      if (localObject == null)
      {
        this.a = new WebChromeClient();
        return;
      }
      this.a = localObject;
    }

    public void onCloseWindow(WebView paramWebView)
    {
      this.a.onCloseWindow(paramWebView);
    }

    public boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
    {
      return this.a.onCreateWindow(paramWebView, paramBoolean1, paramBoolean2, paramMessage);
    }

    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      return this.a.onJsAlert(paramWebView, paramString1, paramString2, paramJsResult);
    }

    public boolean onJsBeforeUnload(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      return this.a.onJsBeforeUnload(paramWebView, paramString1, paramString2, paramJsResult);
    }

    public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
      return this.a.onJsConfirm(paramWebView, paramString1, paramString2, paramJsResult);
    }

    public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
    {
      int i;
      if ("ekv".equals(paramString2))
        try
        {
          paramWebView = new JSONObject(paramString3);
          paramString1 = new HashMap();
          paramString2 = (String)paramWebView.remove("id");
          if (paramWebView.isNull("duration"))
          {
            i = 0;
            paramString3 = paramWebView.keys();
            while (paramString3.hasNext())
            {
              String str = (String)paramString3.next();
              paramString1.put(str, paramWebView.getString(str));
            }
          }
        }
        catch (Exception paramWebView)
        {
          paramWebView.printStackTrace();
        }
      while (true)
      {
        paramJsPromptResult.confirm();
        return true;
        i = ((Integer)paramWebView.remove("duration")).intValue();
        break;
        MobclickAgent.getAgent().a(MobclickAgentJSInterface.a(MobclickAgentJSInterface.this), paramString2, paramString1, i);
        continue;
        if (!"event".equals(paramString2))
          break label217;
        try
        {
          paramString2 = new JSONObject(paramString3);
          paramString1 = paramString2.optString("label");
          paramWebView = paramString1;
          if ("".equals(paramString1))
            paramWebView = null;
          MobclickAgent.getAgent().a(MobclickAgentJSInterface.a(MobclickAgentJSInterface.this), paramString2.getString("tag"), paramWebView, paramString2.optInt("duration"), 1);
        }
        catch (Exception paramWebView)
        {
        }
      }
      label217: return this.a.onJsPrompt(paramWebView, paramString1, paramString2, paramString3, paramJsPromptResult);
    }

    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      this.a.onProgressChanged(paramWebView, paramInt);
    }

    public void onReceivedIcon(WebView paramWebView, Bitmap paramBitmap)
    {
      this.a.onReceivedIcon(paramWebView, paramBitmap);
    }

    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      this.a.onReceivedTitle(paramWebView, paramString);
    }

    public void onRequestFocus(WebView paramWebView)
    {
      this.a.onRequestFocus(paramWebView);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.analytics.MobclickAgentJSInterface
 * JD-Core Version:    0.6.2
 */