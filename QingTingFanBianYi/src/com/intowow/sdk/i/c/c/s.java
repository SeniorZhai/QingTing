package com.intowow.sdk.i.c.c;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.intowow.sdk.i.b.c;
import com.intowow.sdk.model.ADProfile;
import com.intowow.sdk.model.ADProfile.d;
import com.intowow.sdk.model.ADProfile.o;
import com.intowow.sdk.model.j;

public class s extends a
{
  protected ProgressBar A = null;
  protected c B = null;
  protected boolean C = false;
  protected boolean D = false;
  protected boolean E = false;
  protected Runnable F = new Runnable()
  {
    public void run()
    {
      if (s.this.D)
        return;
      s.this.E = true;
      s.this.a(8);
      s.this.h();
    }
  };
  protected WebView y = null;
  protected View z = null;

  public s(Activity paramActivity, j paramj, ADProfile paramADProfile, c.a parama)
  {
    super(paramActivity, paramj, paramADProfile, parama);
  }

  private void b(String paramString)
  {
    String str = paramString;
    try
    {
      if (paramString.indexOf("http") == -1)
        str = "http://" + paramString;
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(str));
      paramString.addFlags(268435456);
      this.a.startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  protected void a(int paramInt)
  {
    if (this.z != null)
      this.z.setVisibility(paramInt);
    if (this.A != null)
      this.A.setVisibility(paramInt);
  }

  @SuppressLint({"SetJavaScriptEnabled"})
  public void a(RelativeLayout paramRelativeLayout)
  {
    super.a(paramRelativeLayout);
    this.B = new c();
    paramRelativeLayout.setBackgroundColor(-1);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(a(), b());
    localLayoutParams.addRule(13);
    String str = ((ADProfile.o)this.c.a(ADProfile.d.d)).d();
    this.y = new WebView(this.a);
    Object localObject = this.y.getSettings();
    this.B.a((WebSettings)localObject);
    localObject = new a(null);
    this.y.setWebViewClient((WebViewClient)localObject);
    this.y.setLayoutParams(localLayoutParams);
    this.y.getSettings().setJavaScriptEnabled(true);
    this.y.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
    this.y.setVerticalScrollBarEnabled(false);
    this.y.setHorizontalScrollBarEnabled(false);
    paramRelativeLayout.addView(this.y);
    this.z = new View(this.a);
    this.z.setBackgroundColor(-1);
    this.z.setLayoutParams(localLayoutParams);
    paramRelativeLayout.addView(this.z);
    localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(13);
    this.A = new ProgressBar(this.a);
    this.A.setId(10001);
    this.A.setLayoutParams(localLayoutParams);
    this.A.setVisibility(8);
    paramRelativeLayout.addView(this.A);
  }

  protected boolean a(WebView paramWebView, String paramString)
  {
    this.j.removeCallbacks(this.F);
    if (this.E)
    {
      this.e.onClick(paramWebView);
      b(paramString);
      return true;
    }
    return false;
  }

  public boolean c()
  {
    if (!super.c())
      return false;
    this.C = true;
    this.B.a(this.y, "onResume");
    return true;
  }

  public boolean d()
  {
    if (!super.d())
      return false;
    this.C = false;
    this.B.a(this.y, "onPause");
    return true;
  }

  protected void h()
  {
  }

  private class a extends WebViewClient
  {
    private a()
    {
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
      s.this.D = false;
      s.this.j.postDelayed(s.this.F, 1000L);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      s.this.D = true;
      s.this.a(0);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      return s.this.a(paramWebView, paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.c.s
 * JD-Core Version:    0.6.2
 */