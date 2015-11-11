package com.intowow.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.a.a.a.a;
import com.a.c.b;
import com.intowow.sdk.f.d;
import com.intowow.sdk.f.d.a;
import com.intowow.sdk.i.b.c;
import com.intowow.sdk.i.b.c.a;
import com.intowow.sdk.j.n;
import java.net.URI;

@SuppressLint({"SetJavaScriptEnabled"})
public class WebViewActivity extends Activity
{
  private RelativeLayout a = null;
  private ImageButton b = null;
  private ImageButton c = null;
  private ImageButton d = null;
  private TextView e = null;
  private TextView f = null;
  private I2WProgress g = null;
  private WebView h = null;
  private View i = null;
  private I2WWebChromeClient j = null;
  private Handler k = null;
  private String l = null;
  private String m = null;
  private boolean n = false;
  private boolean o = false;
  private c p = null;

  private RelativeLayout a()
  {
    Object localObject2 = com.intowow.sdk.f.a.a(this);
    Object localObject1 = d.a(this);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    RelativeLayout localRelativeLayout = new RelativeLayout(this);
    localRelativeLayout.setBackgroundColor(-16777216);
    localRelativeLayout.setLayoutParams(localLayoutParams);
    localLayoutParams = new RelativeLayout.LayoutParams(-1, ((d)localObject1).a(d.a.bb));
    this.a = new RelativeLayout(this);
    this.a.setId(100);
    this.a.setBackgroundColor(Color.parseColor("#eaeaea"));
    this.a.setLayoutParams(localLayoutParams);
    localLayoutParams = new RelativeLayout.LayoutParams(((d)localObject1).a(d.a.bl), ((d)localObject1).a(d.a.bm));
    this.b = new ImageButton(this);
    this.b.setId(200);
    this.b.setLayoutParams(localLayoutParams);
    this.b.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WebViewActivity.this.onBackPressed();
      }
    });
    b(this.b);
    localLayoutParams = new RelativeLayout.LayoutParams(((d)localObject1).a(d.a.bl), ((d)localObject1).a(d.a.bm));
    localLayoutParams.addRule(1, 200);
    this.c = new ImageButton(this);
    this.c.setId(300);
    this.c.setLayoutParams(localLayoutParams);
    this.c.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WebViewActivity.g(WebViewActivity.this).goForward();
      }
    });
    b(this.c);
    localLayoutParams = new RelativeLayout.LayoutParams(((d)localObject1).a(d.a.be), -2);
    localLayoutParams.topMargin = ((d)localObject1).a(d.a.bg);
    localLayoutParams.leftMargin = ((d)localObject1).a(d.a.bj);
    localLayoutParams.addRule(1, 300);
    this.e = new TextView(this);
    this.e.setId(400);
    this.e.setSingleLine(true);
    this.e.setEllipsize(TextUtils.TruncateAt.END);
    this.e.setTextColor(Color.parseColor("#737373"));
    this.e.setTextSize(0, ((d)localObject1).a(d.a.bd));
    this.e.setLayoutParams(localLayoutParams);
    this.e.setText("Loading ...");
    localLayoutParams = new RelativeLayout.LayoutParams(((d)localObject1).a(d.a.be), -2);
    localLayoutParams.addRule(3, 400);
    localLayoutParams.addRule(1, 300);
    localLayoutParams.leftMargin = ((d)localObject1).a(d.a.bj);
    this.f = new TextView(this);
    this.f.setId(500);
    this.f.setSingleLine(true);
    this.f.setEllipsize(TextUtils.TruncateAt.END);
    this.f.setTextColor(Color.parseColor("#a9a9a9"));
    this.f.setTextSize(0, ((d)localObject1).a(d.a.bh));
    this.f.setLayoutParams(localLayoutParams);
    localLayoutParams = new RelativeLayout.LayoutParams(((d)localObject1).a(d.a.bc), ((d)localObject1).a(d.a.bc));
    localLayoutParams.addRule(11);
    this.d = new ImageButton(this);
    this.d.setId(600);
    this.d.setBackgroundDrawable(((com.intowow.sdk.f.a)localObject2).b("btn_webview_close_nm.png"));
    this.d.setOnTouchListener(n.a(((com.intowow.sdk.f.a)localObject2).b("btn_webview_close_at.png"), ((com.intowow.sdk.f.a)localObject2).b("btn_webview_close_nm.png")));
    this.d.setLayoutParams(localLayoutParams);
    this.d.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WebViewActivity.this.finish();
      }
    });
    localObject2 = new RelativeLayout.LayoutParams(-1, ((d)localObject1).a(d.a.bk));
    ((RelativeLayout.LayoutParams)localObject2).addRule(3, 100);
    this.i = new View(this);
    this.i.setId(700);
    this.i.setBackgroundColor(Color.parseColor("#535353"));
    this.i.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localObject1 = new RelativeLayout.LayoutParams(-1, ((d)localObject1).a(d.a.bi));
    ((RelativeLayout.LayoutParams)localObject1).addRule(3, 700);
    this.g = new I2WProgress(this);
    this.g.setId(800);
    this.g.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.g.setProgress(0);
    localObject1 = new RelativeLayout.LayoutParams(-1, -1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(3, 700);
    this.h = new WebView(this);
    this.h.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.a.addView(this.b);
    this.a.addView(this.c);
    this.a.addView(this.e);
    this.a.addView(this.f);
    this.a.addView(this.d);
    localRelativeLayout.addView(this.a);
    localRelativeLayout.addView(this.i);
    localRelativeLayout.addView(this.h);
    localRelativeLayout.addView(this.g);
    return localRelativeLayout;
  }

  private String a(String paramString)
  {
    try
    {
      paramString = new URI(paramString);
      paramString = paramString.getScheme() + "://" + paramString.getHost();
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return "";
  }

  private void a(View paramView)
  {
    if ((paramView != null) && (paramView.getVisibility() != 0))
      paramView.setVisibility(0);
  }

  @SuppressLint({"RtlHardcoded"})
  private void a(WebView paramWebView)
  {
    com.intowow.sdk.f.a locala = com.intowow.sdk.f.a.a(this);
    d locald = d.a(this);
    if ((!paramWebView.canGoBack()) && (!this.j.isFullScreen()));
    boolean bool2;
    RelativeLayout.LayoutParams localLayoutParams;
    for (boolean bool1 = false; ; bool1 = true)
    {
      bool2 = paramWebView.canGoForward();
      paramWebView = (RelativeLayout.LayoutParams)this.e.getLayoutParams();
      localLayoutParams = (RelativeLayout.LayoutParams)this.f.getLayoutParams();
      if ((bool1) || (bool2))
        break;
      b(this.b);
      b(this.c);
      this.e.getLayoutParams().width = locald.a(d.a.be);
      this.f.getLayoutParams().width = locald.a(d.a.be);
      paramWebView.leftMargin = locald.a(d.a.bj);
      localLayoutParams.leftMargin = locald.a(d.a.bj);
      this.e.setGravity(3);
      this.f.setGravity(3);
      return;
    }
    a(this.b);
    a(this.c);
    this.e.getLayoutParams().width = locald.a(d.a.bf);
    this.f.getLayoutParams().width = locald.a(d.a.bf);
    paramWebView.addRule(1, 0);
    localLayoutParams.addRule(1, 0);
    paramWebView.addRule(14);
    localLayoutParams.addRule(14);
    this.e.setGravity(1);
    this.f.setGravity(1);
    a(locala, this.b, bool1, "btn_webview_back_nm.png", "btn_webview_back_at.png", "btn_webview_back_disable.png");
    a(locala, this.c, bool2, "btn_webview_next_nm.png", "btn_webview_next_at.png", "btn_webview_next_disable.png");
  }

  private void a(com.intowow.sdk.f.a parama, ImageButton paramImageButton, boolean paramBoolean, String paramString1, String paramString2, String paramString3)
  {
    if (paramBoolean)
    {
      paramImageButton.setEnabled(true);
      paramImageButton.setBackgroundDrawable(parama.b(paramString1));
      paramImageButton.setOnTouchListener(n.a(parama.b(paramString2), parama.b(paramString1)));
      return;
    }
    paramImageButton.setEnabled(false);
    paramImageButton.setBackgroundDrawable(parama.b(paramString3));
  }

  private void b(View paramView)
  {
    if ((paramView != null) && (paramView.getVisibility() != 8))
      paramView.setVisibility(8);
  }

  public void onBackPressed()
  {
    try
    {
      if (this.j.isFullScreen())
      {
        this.j.onHideCustomView();
        return;
      }
      if (this.h.canGoBack())
      {
        this.h.goBack();
        return;
      }
    }
    catch (Exception localException)
    {
      super.onBackPressed();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    try
    {
      this.p = new c();
      setContentView(a());
      this.k = new Handler();
      if (getIntent() != null)
      {
        Bundle localBundle = getIntent().getExtras();
        if (localBundle != null)
          this.l = localBundle.getString("mUrlPath");
      }
      if ((paramBundle != null) && (paramBundle.containsKey("mUrlPath")))
      {
        this.l = paramBundle.getString("mUrlPath");
        paramBundle.remove("mUrlPath");
      }
      if (this.l != null)
      {
        paramBundle = this.h.getSettings();
        this.p.a(paramBundle);
        this.j = new I2WWebChromeClient(null);
        this.h.setWebChromeClient(this.j);
        this.h.setWebViewClient(new I2WWebViewClient(null));
        this.h.loadUrl(this.l);
      }
      return;
    }
    catch (Exception paramBundle)
    {
    }
  }

  protected void onDestroy()
  {
    try
    {
      this.p.a(this.k, this.h, new c.a()
      {
        public void onDestory()
        {
          WebViewActivity.b(WebViewActivity.this, null);
          WebViewActivity.a(WebViewActivity.this, null);
        }
      });
      label23: super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      break label23;
    }
  }

  public void onPause()
  {
    super.onPause();
    I2WAPI.onActivityPause(this);
    try
    {
      this.p.a(this.h, "onPause");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void onResume()
  {
    super.onResume();
    I2WAPI.onActivityResume(this);
    try
    {
      this.p.a(this.h, "onResume");
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    paramBundle.putString("mUrlPath", this.l);
    super.onSaveInstanceState(paramBundle);
  }

  protected void onStop()
  {
    super.onStop();
    try
    {
      if ((this.j != null) && (this.j.isFullScreen()))
        this.j.onHideCustomView();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  class I2WProgress extends View
  {
    private Paint b = null;
    private final float c = 100.0F;
    private int d = 0;

    public I2WProgress(Context arg2)
    {
      super();
      this.b.setColor(Color.parseColor("#7d7d7d"));
      this.b.setAntiAlias(true);
      this.b.setStyle(Paint.Style.FILL);
    }

    protected void onDraw(Canvas paramCanvas)
    {
      super.onDraw(paramCanvas);
      paramCanvas.drawRect(0.0F, 0.0F, (int)(getWidth() * (this.d / 100.0F)), getHeight(), this.b);
    }

    public void setProgress(int paramInt)
    {
      this.d = paramInt;
      invalidate();
    }
  }

  private class I2WWebChromeClient extends WebChromeClient
  {
    private View b = null;
    private WebChromeClient.CustomViewCallback c = null;

    private I2WWebChromeClient()
    {
    }

    public boolean isFullScreen()
    {
      return this.b != null;
    }

    public void onHideCustomView()
    {
      try
      {
        if (this.b != null)
        {
          if (this.c != null)
          {
            this.c.onCustomViewHidden();
            this.c = null;
          }
          ((ViewGroup)this.b.getParent()).removeView(this.b);
          WebViewActivity.g(WebViewActivity.this).setVisibility(0);
          this.b = null;
          WebViewActivity.a(WebViewActivity.this, WebViewActivity.g(WebViewActivity.this));
        }
        return;
      }
      catch (Exception localException)
      {
      }
    }

    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
      if ((WebViewActivity.c(WebViewActivity.this)) && (!WebViewActivity.b(WebViewActivity.this)))
        WebViewActivity.a(WebViewActivity.this).setProgress(paramInt);
    }

    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
      if (WebViewActivity.d(WebViewActivity.this) != null)
      {
        WebViewActivity.d(WebViewActivity.this).setText(paramString);
        WebViewActivity.e(WebViewActivity.this).setText(WebViewActivity.b(WebViewActivity.this, WebViewActivity.f(WebViewActivity.this)));
      }
    }

    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
      try
      {
        if (this.c != null)
        {
          this.c.onCustomViewHidden();
          this.c = null;
          return;
        }
        ViewGroup localViewGroup = (ViewGroup)WebViewActivity.g(WebViewActivity.this).getParent();
        WebViewActivity.g(WebViewActivity.this).setVisibility(8);
        paramView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        localViewGroup.addView(paramView);
        this.b = paramView;
        this.c = paramCustomViewCallback;
        WebViewActivity.a(WebViewActivity.this, WebViewActivity.g(WebViewActivity.this));
        return;
      }
      catch (Exception paramView)
      {
      }
    }
  }

  private class I2WWebViewClient extends WebViewClient
  {
    private I2WWebViewClient()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      try
      {
        WebViewActivity.b(WebViewActivity.this, false);
        WebViewActivity.a(WebViewActivity.this, false);
        WebViewActivity.a(WebViewActivity.this, paramWebView);
        b.a(WebViewActivity.a(WebViewActivity.this)).g(0.0F).a(500L).a(new a.a()
        {
          public void onAnimationCancel(com.a.a.a paramAnonymousa)
          {
          }

          public void onAnimationEnd(com.a.a.a paramAnonymousa)
          {
            WebViewActivity.a(WebViewActivity.this).setVisibility(8);
            WebViewActivity.a(WebViewActivity.this).setProgress(0);
          }

          public void onAnimationRepeat(com.a.a.a paramAnonymousa)
          {
          }

          public void onAnimationStart(com.a.a.a paramAnonymousa)
          {
          }
        }).a();
        return;
      }
      catch (Exception paramWebView)
      {
      }
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      WebViewActivity.b(WebViewActivity.this, true);
      WebViewActivity.a(WebViewActivity.this).clearAnimation();
      if (!WebViewActivity.b(WebViewActivity.this))
      {
        com.a.c.a.a(WebViewActivity.a(WebViewActivity.this), 1.0F);
        WebViewActivity.a(WebViewActivity.this).setVisibility(0);
      }
      WebViewActivity.a(WebViewActivity.this, paramString);
    }

    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      paramSslErrorHandler.proceed();
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      WebViewActivity.a(WebViewActivity.this, true);
      paramWebView.loadUrl(paramString);
      return true;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.WebViewActivity
 * JD-Core Version:    0.6.2
 */