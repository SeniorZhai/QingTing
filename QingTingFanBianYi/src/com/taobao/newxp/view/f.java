package com.taobao.newxp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.taobao.newxp.common.Log;
import java.util.ArrayList;
import java.util.List;

public class f
  implements e
{
  static Drawable[] a;
  static int b = 0;
  private static final String e = f.class.getSimpleName();
  int c;
  List<a> d;

  public static f a(List<a> paramList)
  {
    f localf = new f();
    localf.d = paramList;
    if ((paramList == null) && (a != null))
    {
      paramList = new ArrayList();
      Drawable[] arrayOfDrawable = a;
      int j = arrayOfDrawable.length;
      int i = 0;
      while (i < j)
      {
        paramList.add(new a(null, arrayOfDrawable[i]));
        i += 1;
      }
    }
    return localf;
  }

  public int a()
  {
    return this.d.size();
  }

  public Drawable a(int paramInt)
  {
    return ((a)this.d.get(paramInt)).b;
  }

  public View a(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    Drawable localDrawable = a(paramInt3);
    paramInt1 -= 10;
    paramInt3 = localDrawable.getIntrinsicHeight();
    int i = localDrawable.getIntrinsicWidth();
    RelativeLayout localRelativeLayout = new RelativeLayout(paramContext);
    localRelativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    RelativeLayout.LayoutParams localLayoutParams;
    if (i > paramInt3)
    {
      float f = paramInt1 / i;
      localLayoutParams = new RelativeLayout.LayoutParams(paramInt1, (int)(paramInt3 * f));
      localLayoutParams.addRule(14);
    }
    while (true)
    {
      localLayoutParams.addRule(15);
      paramContext = new UMScreenshot(paramContext);
      paramContext.setScreenshotDrawable(localDrawable);
      localRelativeLayout.addView(paramContext, localLayoutParams);
      return localRelativeLayout;
      localLayoutParams = new RelativeLayout.LayoutParams((int)(paramInt2 / paramInt3 * i), paramInt2);
      localLayoutParams.addRule(14);
    }
  }

  public View a(final Context paramContext, final HorizontalStrip paramHorizontalStrip, final int paramInt)
  {
    UMScreenshot localUMScreenshot = new UMScreenshot(paramContext);
    Drawable localDrawable = a(paramInt);
    int i = localDrawable.getIntrinsicHeight();
    int j = localDrawable.getIntrinsicWidth();
    float f = this.c / i;
    j = (int)(j * f);
    localUMScreenshot.setLayoutParams(new ViewGroup.LayoutParams(j, -1));
    Log.a(e, "getView at pos=" + paramInt + " viewWidth=" + j + "  " + "  dh=" + i + " totalH=" + this.c);
    localUMScreenshot.setBackgroundColor(-12303292);
    localUMScreenshot.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new ScreenshotDialog(paramContext, f.this)
        {
          public void dismiss()
          {
            super.dismiss();
            f.1.this.b.c();
          }
        }
        .show(paramInt);
      }
    });
    return localUMScreenshot;
  }

  public void a(int paramInt, c paramc, float paramFloat)
  {
    Drawable localDrawable = a(paramInt);
    paramc.b(localDrawable.getIntrinsicHeight());
    paramc.a(localDrawable.getIntrinsicWidth());
  }

  public void a(HorizontalStrip.a parama)
  {
  }

  public void b(int paramInt)
  {
    this.c = paramInt;
  }

  public boolean b()
  {
    return true;
  }

  public static class a
  {
    public String a;
    public Drawable b;
    public float c = 0.0F;

    public a(String paramString, Drawable paramDrawable)
    {
      this.a = paramString;
      this.b = paramDrawable;
      this.c = (paramDrawable.getIntrinsicHeight() * 100 / (paramDrawable.getIntrinsicWidth() * 100));
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.f
 * JD-Core Version:    0.6.2
 */