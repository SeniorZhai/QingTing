package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.weibo.sdk.android.api.util.BackGroudSeletor;
import com.tencent.weibo.sdk.android.api.util.Util;
import java.lang.reflect.Method;
import java.util.Properties;

public class Authorize extends Activity
{
  public static final int ALERT_DOWNLOAD = 0;
  public static final int ALERT_FAV = 1;
  public static final int ALERT_NETWORK = 4;
  public static final int PROGRESS_H = 3;
  public static int WEBVIEWSTATE_1 = 0;
  Dialog _dialog;
  String _fileName;
  String _url;
  private String clientId = null;
  private ProgressDialog dialog;
  Handler handle = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 100:
      }
      Authorize.this.showDialog(4);
    }
  };
  private boolean isShow = false;
  private LinearLayout layout = null;
  String path;
  private String redirectUri = null;
  WebView webView;
  int webview_state = 0;

  public void initLayout()
  {
    Object localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    Object localObject3 = new RelativeLayout.LayoutParams(-1, -2);
    Object localObject2 = new RelativeLayout.LayoutParams(-2, -2);
    this.dialog = new ProgressDialog(this);
    this.dialog.setProgressStyle(0);
    this.dialog.requestWindowFeature(1);
    this.dialog.setMessage("请稍后...");
    this.dialog.setIndeterminate(false);
    this.dialog.setCancelable(false);
    this.dialog.show();
    this.layout = new LinearLayout(this);
    this.layout.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.layout.setOrientation(1);
    localObject1 = new RelativeLayout(this);
    ((RelativeLayout)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject3);
    ((RelativeLayout)localObject1).setBackgroundDrawable(BackGroudSeletor.getdrawble("up_bg2x", getApplication()));
    ((RelativeLayout)localObject1).setGravity(0);
    localObject3 = new Button(this);
    Application localApplication = getApplication();
    ((Button)localObject3).setBackgroundDrawable(BackGroudSeletor.createBgByImageIds(new String[] { "quxiao_btn2x", "quxiao_btn_hover" }, localApplication));
    ((Button)localObject3).setText("取消");
    ((RelativeLayout.LayoutParams)localObject2).addRule(9, -1);
    ((RelativeLayout.LayoutParams)localObject2).addRule(15, -1);
    ((RelativeLayout.LayoutParams)localObject2).leftMargin = 10;
    ((RelativeLayout.LayoutParams)localObject2).topMargin = 10;
    ((RelativeLayout.LayoutParams)localObject2).bottomMargin = 10;
    ((Button)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((Button)localObject3).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Authorize.this.finish();
      }
    });
    ((RelativeLayout)localObject1).addView((View)localObject3);
    localObject2 = new TextView(this);
    ((TextView)localObject2).setText("授权");
    ((TextView)localObject2).setTextColor(-1);
    ((TextView)localObject2).setTextSize(22.0F);
    localObject3 = new RelativeLayout.LayoutParams(-2, -2);
    ((RelativeLayout.LayoutParams)localObject3).addRule(13, -1);
    ((TextView)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
    ((RelativeLayout)localObject1).addView((View)localObject2);
    this.layout.addView((View)localObject1);
    this.webView = new WebView(this);
    if (Build.VERSION.SDK_INT >= 11);
    try
    {
      this.webView.getClass().getDeclaredMethod("removeJavascriptInterface", new Class[] { String.class }).invoke(this.webView, new Object[] { "searchBoxJavaBridge_" });
      localObject1 = new LinearLayout.LayoutParams(-2, -2);
      this.webView.setLayoutParams((ViewGroup.LayoutParams)localObject1);
      localObject1 = this.webView.getSettings();
      this.webView.setVerticalScrollBarEnabled(false);
      ((WebSettings)localObject1).setJavaScriptEnabled(true);
      ((WebSettings)localObject1).setUseWideViewPort(true);
      ((WebSettings)localObject1).setLoadWithOverviewMode(false);
      this.webView.loadUrl(this.path);
      this.webView.setWebChromeClient(new WebChromeClient()
      {
        public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
        {
          super.onProgressChanged(paramAnonymousWebView, paramAnonymousInt);
          Log.d("newProgress", paramAnonymousInt + "..");
        }
      });
      this.webView.setWebViewClient(new WebViewClient()
      {
        public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          Log.d("backurl", "page finished:" + paramAnonymousString);
          if ((paramAnonymousString.indexOf("access_token") != -1) && (!Authorize.this.isShow))
            Authorize.this.jumpResultParser(paramAnonymousString);
          if ((Authorize.this.dialog != null) && (Authorize.this.dialog.isShowing()))
            Authorize.this.dialog.cancel();
        }

        public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          Log.d("backurl", "page start:" + paramAnonymousString);
          if ((paramAnonymousString.indexOf("access_token") != -1) && (!Authorize.this.isShow))
            Authorize.this.jumpResultParser(paramAnonymousString);
          if ((Authorize.this.dialog != null) && (Authorize.this.dialog.isShowing()))
            Authorize.this.dialog.cancel();
        }

        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          if ((paramAnonymousString.indexOf("access_token") != -1) && (!Authorize.this.isShow))
            Authorize.this.jumpResultParser(paramAnonymousString);
          return false;
        }
      });
      this.layout.addView(this.webView);
      setContentView(this.layout);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void jumpResultParser(String paramString)
  {
    Object localObject = paramString.split("#")[1].split("&");
    paramString = localObject[0].split("=")[1];
    String str1 = localObject[1].split("=")[1];
    String str2 = localObject[2].split("=")[1];
    String str3 = localObject[3].split("=")[1];
    String str4 = localObject[4].split("=")[1];
    String str5 = localObject[5].split("=")[1];
    str5 = localObject[6].split("=")[1];
    localObject = localObject[7].split("=")[1];
    Context localContext = getApplicationContext();
    if ((paramString != null) && (!"".equals(paramString)))
    {
      Util.saveSharePersistent(localContext, "ACCESS_TOKEN", paramString);
      Util.saveSharePersistent(localContext, "EXPIRES_IN", str1);
      Util.saveSharePersistent(localContext, "OPEN_ID", str2);
      Util.saveSharePersistent(localContext, "OPEN_KEY", str3);
      Util.saveSharePersistent(localContext, "REFRESH_TOKEN", str4);
      Util.saveSharePersistent(localContext, "NAME", str5);
      Util.saveSharePersistent(localContext, "NICK", (String)localObject);
      Util.saveSharePersistent(localContext, "CLIENT_ID", this.clientId);
      Util.saveSharePersistent(localContext, "AUTHORIZETIME", String.valueOf(System.currentTimeMillis() / 1000L));
      Toast.makeText(this, "授权成功", 0).show();
      finish();
      this.isShow = true;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (!Util.isNetworkAvailable(this))
    {
      showDialog(4);
      return;
    }
    paramBundle = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(paramBundle);
    BackGroudSeletor.setPix(paramBundle.widthPixels + "x" + paramBundle.heightPixels);
    try
    {
      this.clientId = Util.getConfig().getProperty("APP_KEY");
      this.redirectUri = Util.getConfig().getProperty("REDIRECT_URI");
      if ((this.clientId == null) || ("".equals(this.clientId)) || (this.redirectUri == null) || ("".equals(this.redirectUri)))
        Toast.makeText(this, "请在配置文件中填写相应的信息", 0).show();
      Log.d("redirectUri", this.redirectUri);
      requestWindowFeature(1);
      int i = (int)Math.random();
      this.path = ("http://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=" + this.clientId + "&response_type=token&redirect_uri=" + this.redirectUri + "&state=" + (i * 1000 + 111));
      Log.e("qtradio", this.path);
      initLayout();
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 3:
    case 4:
    }
    while (true)
    {
      return this._dialog;
      this._dialog = new ProgressDialog(this);
      ((ProgressDialog)this._dialog).setMessage("加载中...");
      continue;
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle("网络连接异常，是否重新连接？");
      localBuilder.setPositiveButton("是", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (Util.isNetworkAvailable(Authorize.this))
          {
            Authorize.this.webView.loadUrl(Authorize.this.path);
            return;
          }
          paramAnonymousDialogInterface = Message.obtain();
          paramAnonymousDialogInterface.what = 100;
          Authorize.this.handle.sendMessage(paramAnonymousDialogInterface);
        }
      });
      localBuilder.setNegativeButton("否", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Authorize.this.finish();
        }
      });
      this._dialog = localBuilder.create();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.Authorize
 * JD-Core Version:    0.6.2
 */