package com.taobao.munion.view.webview;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;
import com.taobao.munion.Munion;
import com.taobao.munion.base.ResourceManager;
import com.taobao.munion.base.ioc.x;
import com.taobao.munion.view.base.BaseWebViewDialog;
import com.taobao.munion.view.webview.windvane.WindVaneWebView;
import com.taobao.munion.view.webview.windvane.l;
import com.taobao.munion.view.webview.windvane.m;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MunionBrowser extends BaseWebViewDialog
{
  String a;
  ProgressBar b;
  boolean c = false;
  Map<String, String> d = new HashMap();
  Handler e;
  private final FrameLayout f;
  private final ActionBar g;
  private final List<ActionBar.a> h;
  private final ResourceManager i;

  public MunionBrowser(Context paramContext)
  {
    super(paramContext);
    this.d.put("Referer", "native null refer");
    this.e = new Handler();
    this.i = ((ResourceManager)Munion.init(paramContext.getApplicationContext()).d("resource"));
    int j = (int)dipToPixels(this.mContext, 48.0F);
    paramContext = new RelativeLayout(this.mContext);
    paramContext.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, j);
    localLayoutParams.addRule(12);
    this.g = newActionLayout();
    this.g.setId(305419896);
    this.h = newDefaultActions();
    if ((this.h != null) && (this.h.size() > 0))
    {
      Iterator localIterator = this.h.iterator();
      while (localIterator.hasNext())
      {
        ActionBar.a locala = (ActionBar.a)localIterator.next();
        this.g.addAction(locala);
      }
      this.g.setVisibility(0);
    }
    paramContext.addView(this.g, localLayoutParams);
    this.f = new FrameLayout(this.mContext);
    localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    localLayoutParams.addRule(2, this.g.getId());
    paramContext.addView(this.f, localLayoutParams);
    setContentView(paramContext);
  }

  private void a()
  {
    if (this.h == null);
    while (true)
    {
      return;
      Iterator localIterator = this.h.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (ActionBar.a)localIterator.next();
        if ((localObject instanceof a))
        {
          localObject = (a)localObject;
          if ("back".equals(((a)localObject).b()))
          {
            if (this.mWebView.canGoBack())
              ((a)localObject).a(true);
            else
              ((a)localObject).a(false);
          }
          else if ("forward".equals(((a)localObject).b()))
            if (this.mWebView.canGoForward())
              ((a)localObject).a(true);
            else
              ((a)localObject).a(false);
        }
      }
    }
  }

  public static float dipToPixels(Context paramContext, float paramFloat)
  {
    return TypedValue.applyDimension(1, paramFloat, paramContext.getResources().getDisplayMetrics());
  }

  public void initContent()
  {
    this.mWebView = new WindVaneWebView(this.mContext);
    this.f.addView(this.mWebView, new ViewGroup.LayoutParams(-1, -1));
    this.g.setWebView(this.mWebView);
  }

  public void initWebview(WebView paramWebView)
  {
    super.initWebview(paramWebView);
    paramWebView.setWebChromeClient(new l((WindVaneWebView)paramWebView)
    {
      public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
      {
        if (MunionBrowser.this.b != null)
        {
          MunionBrowser.this.b.setProgress(paramAnonymousInt);
          if (paramAnonymousInt > 90)
            MunionBrowser.this.b.setVisibility(4);
        }
      }
    });
    paramWebView.setWebViewClient(new m(1)
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
        MunionBrowser.a(MunionBrowser.this);
      }

      public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
      {
        try
        {
          paramAnonymousWebView = Uri.parse(paramAnonymousString2);
          paramAnonymousString1 = paramAnonymousWebView.getScheme();
          if ((!paramAnonymousString1.equals("http")) && (!paramAnonymousString1.equals("https")))
          {
            paramAnonymousString1 = new Intent();
            paramAnonymousString1.setData(paramAnonymousWebView);
            MunionBrowser.this.getContext().startActivity(paramAnonymousString1);
            if (!MunionBrowser.this.mWebView.canGoBack())
              MunionBrowser.this.dismiss();
          }
          return;
        }
        catch (Exception paramAnonymousWebView)
        {
          paramAnonymousWebView.printStackTrace();
        }
      }
    });
    paramWebView.setDownloadListener(new DownloadListener()
    {
      public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong)
      {
        paramAnonymousString1 = new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString1));
        MunionBrowser.this.mContext.startActivity(paramAnonymousString1);
      }
    });
  }

  public void loadAndShow(String paramString)
  {
    this.a = paramString;
    show();
  }

  public ActionBar newActionLayout()
  {
    ActionBar localActionBar = new ActionBar(getContext());
    localActionBar.setBackgroundColor(-1);
    localActionBar.setGravity(16);
    localActionBar.setOrientation(0);
    localActionBar.setVisibility(8);
    return localActionBar;
  }

  public List<ActionBar.a> newDefaultActions()
  {
    final int j = (int)dipToPixels(this.mContext, 40.0F);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new ImageView(this.mContext);
    Object localObject2 = new FrameLayout.LayoutParams(j, j);
    ((FrameLayout.LayoutParams)localObject2).gravity = 17;
    ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localObject2 = new StateListDrawable();
    Drawable localDrawable = (Drawable)this.i.a("back_click.png");
    ((StateListDrawable)localObject2).addState(new int[] { 16842919 }, localDrawable);
    localDrawable = (Drawable)this.i.a("back_click.png");
    ((StateListDrawable)localObject2).addState(new int[] { 16842908 }, localDrawable);
    localDrawable = (Drawable)this.i.a("back.png");
    ((StateListDrawable)localObject2).addState(new int[0], localDrawable);
    ((ImageView)localObject1).setImageDrawable((Drawable)localObject2);
    localObject1 = new a((View)localObject1)
    {
      public void a(View paramAnonymousView, WebView paramAnonymousWebView)
      {
        if (paramAnonymousWebView.canGoBack())
        {
          paramAnonymousWebView.goBack();
          return;
        }
        Toast.makeText(MunionBrowser.this.mContext, "已经是第一页了，亲～", 0).show();
      }

      public void a(boolean paramAnonymousBoolean)
      {
        super.a(paramAnonymousBoolean);
        if (Build.VERSION.SDK_INT > 11)
        {
          if (!paramAnonymousBoolean)
            a().setAlpha(0.3F);
        }
        else
          return;
        a().setAlpha(1.0F);
      }
    };
    ((a)localObject1).a(false);
    ((a)localObject1).a("back");
    localArrayList.add(localObject1);
    localObject1 = new ImageView(this.mContext);
    localObject2 = new FrameLayout.LayoutParams(j, j);
    ((FrameLayout.LayoutParams)localObject2).gravity = 17;
    ((ImageView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    localObject2 = new StateListDrawable();
    localDrawable = (Drawable)this.i.a("forward_click.png");
    ((StateListDrawable)localObject2).addState(new int[] { 16842919 }, localDrawable);
    localDrawable = (Drawable)this.i.a("forward_click.png");
    ((StateListDrawable)localObject2).addState(new int[] { 16842908 }, localDrawable);
    localDrawable = (Drawable)this.i.a("forward.png");
    ((StateListDrawable)localObject2).addState(new int[0], localDrawable);
    ((ImageView)localObject1).setImageDrawable((Drawable)localObject2);
    localObject1 = new a((View)localObject1)
    {
      public void a(View paramAnonymousView, WebView paramAnonymousWebView)
      {
        if (paramAnonymousWebView.canGoForward())
        {
          paramAnonymousWebView.goForward();
          return;
        }
        Toast.makeText(MunionBrowser.this.mContext, "已经是最后一页了，亲～", 0).show();
      }

      public void a(boolean paramAnonymousBoolean)
      {
        super.a(paramAnonymousBoolean);
        if (Build.VERSION.SDK_INT > 11)
        {
          if (!paramAnonymousBoolean)
            a().setAlpha(0.3F);
        }
        else
          return;
        a().setAlpha(1.0F);
      }
    };
    ((a)localObject1).a(false);
    ((a)localObject1).a("forward");
    localArrayList.add(localObject1);
    localArrayList.add(new ActionBar.a()
    {
      public View a()
      {
        View localView = new View(MunionBrowser.this.mContext);
        FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(j, j);
        localLayoutParams.gravity = 17;
        localView.setLayoutParams(localLayoutParams);
        localView.setClickable(false);
        localView.setFocusable(false);
        return localView;
      }

      public void a(View paramAnonymousView, WebView paramAnonymousWebView)
      {
      }
    });
    localArrayList.add(new ActionBar.a()
    {
      public View a()
      {
        ImageView localImageView = new ImageView(MunionBrowser.this.mContext);
        Object localObject = new FrameLayout.LayoutParams(j, j);
        ((FrameLayout.LayoutParams)localObject).gravity = 17;
        localImageView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        localObject = new StateListDrawable();
        Drawable localDrawable = (Drawable)MunionBrowser.b(MunionBrowser.this).a("reflush_click.png");
        ((StateListDrawable)localObject).addState(new int[] { 16842919 }, localDrawable);
        localDrawable = (Drawable)MunionBrowser.b(MunionBrowser.this).a("reflush_click.png");
        ((StateListDrawable)localObject).addState(new int[] { 16842908 }, localDrawable);
        localDrawable = (Drawable)MunionBrowser.b(MunionBrowser.this).a("reflush.png");
        ((StateListDrawable)localObject).addState(new int[0], localDrawable);
        localImageView.setImageDrawable((Drawable)localObject);
        return localImageView;
      }

      public void a(View paramAnonymousView, WebView paramAnonymousWebView)
      {
        paramAnonymousWebView.reload();
      }
    });
    localArrayList.add(new ActionBar.a()
    {
      public View a()
      {
        ImageView localImageView = new ImageView(MunionBrowser.this.mContext);
        Object localObject = new FrameLayout.LayoutParams(j, j);
        ((FrameLayout.LayoutParams)localObject).gravity = 17;
        localImageView.setLayoutParams((ViewGroup.LayoutParams)localObject);
        localObject = new StateListDrawable();
        Drawable localDrawable = (Drawable)MunionBrowser.b(MunionBrowser.this).a("close_click.png");
        ((StateListDrawable)localObject).addState(new int[] { 16842919 }, localDrawable);
        localDrawable = (Drawable)MunionBrowser.b(MunionBrowser.this).a("close_click.png");
        ((StateListDrawable)localObject).addState(new int[] { 16842908 }, localDrawable);
        localDrawable = (Drawable)MunionBrowser.b(MunionBrowser.this).a("close.png");
        ((StateListDrawable)localObject).addState(new int[0], localDrawable);
        localImageView.setImageDrawable((Drawable)localObject);
        return localImageView;
      }

      public void a(View paramAnonymousView, WebView paramAnonymousWebView)
      {
        MunionBrowser.this.dismiss();
      }
    });
    return localArrayList;
  }

  public void onLoadUrl()
  {
    if (this.b != null)
      this.b.setVisibility(0);
    if (!TextUtils.isEmpty(this.a))
    {
      if ((Build.VERSION.SDK_INT >= 8) && (this.d != null))
        this.mWebView.loadUrl(this.a, this.d);
    }
    else
      return;
    this.mWebView.loadUrl(this.a);
  }

  public MunionBrowser setExtraHeaders(Map<String, String> paramMap)
  {
    this.d.putAll(paramMap);
    return this;
  }

  public MunionBrowser setInterceptBack(boolean paramBoolean)
  {
    this.c = paramBoolean;
    return this;
  }

  static abstract class a
    implements ActionBar.a
  {
    private View a;
    private Object b;

    protected a(View paramView)
    {
      this.a = paramView;
    }

    public final View a()
    {
      return this.a;
    }

    public void a(Object paramObject)
    {
      this.b = paramObject;
    }

    public void a(boolean paramBoolean)
    {
      if (paramBoolean != this.a.isClickable())
      {
        this.a.setClickable(paramBoolean);
        this.a.setFocusable(paramBoolean);
      }
    }

    public Object b()
    {
      return this.b;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.MunionBrowser
 * JD-Core Version:    0.6.2
 */