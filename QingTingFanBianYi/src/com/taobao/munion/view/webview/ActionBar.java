package com.taobao.munion.view.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class ActionBar extends LinearLayout
  implements View.OnClickListener
{
  private WebView a;

  public ActionBar(Context paramContext)
  {
    super(paramContext);
  }

  public ActionBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private View a(a parama)
  {
    View localView1 = null;
    View localView2 = parama.a();
    if (localView2 != null)
    {
      localView1 = newActionItem();
      ((ViewGroup)((ViewGroup)localView1).getChildAt(0)).addView(localView2);
      localView2.setTag(parama);
      localView2.setOnClickListener(this);
    }
    return localView1;
  }

  public void addAction(a parama)
  {
    addAction(parama, getChildCount());
  }

  public void addAction(a parama, int paramInt)
  {
    addView(a(parama), paramInt);
  }

  public int getActionCount()
  {
    return getChildCount();
  }

  public WebView getWebView()
  {
    return this.a;
  }

  public View newActionItem()
  {
    Object localObject = getContext();
    LinearLayout localLinearLayout = new LinearLayout((Context)localObject);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -1);
    localLayoutParams.weight = 1.0F;
    localLinearLayout.setLayoutParams(localLayoutParams);
    localObject = new FrameLayout((Context)localObject);
    localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    localLayoutParams.gravity = 17;
    ((FrameLayout)localObject).setLayoutParams(localLayoutParams);
    localLinearLayout.addView((View)localObject);
    return localLinearLayout;
  }

  public void onClick(View paramView)
  {
    Object localObject = paramView.getTag();
    if ((localObject instanceof a))
      ((a)localObject).a(paramView, this.a);
  }

  public boolean removeAction(a parama)
  {
    boolean bool2 = false;
    int j = getChildCount();
    int i = 0;
    while (true)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        View localView = getChildAt(i);
        if (localView != null)
        {
          Object localObject = localView.getTag();
          if (((localObject instanceof a)) && (localObject.equals(parama)))
          {
            removeView(localView);
            bool1 = true;
          }
        }
      }
      else
      {
        return bool1;
      }
      i += 1;
    }
  }

  public void removeActionAt(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < getChildCount()))
      removeViewAt(paramInt);
  }

  public void removeAllActions()
  {
    removeAllViews();
  }

  public void setWebView(WebView paramWebView)
  {
    this.a = paramWebView;
  }

  public static abstract interface a
  {
    public abstract View a();

    public abstract void a(View paramView, WebView paramWebView);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.webview.ActionBar
 * JD-Core Version:    0.6.2
 */