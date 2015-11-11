package com.handmark.pulltorefresh.library.internal;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;

public class ViewCompat
{
  public static void postOnAnimation(View paramView, Runnable paramRunnable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      SDK16.postOnAnimation(paramView, paramRunnable);
      return;
    }
    paramView.postDelayed(paramRunnable, 16L);
  }

  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      SDK16.setBackground(paramView, paramDrawable);
      return;
    }
    paramView.setBackgroundDrawable(paramDrawable);
  }

  public static void setLayerType(View paramView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 11)
      SDK11.setLayerType(paramView, paramInt);
  }

  @TargetApi(11)
  static class SDK11
  {
    public static void setLayerType(View paramView, int paramInt)
    {
      paramView.setLayerType(paramInt, null);
    }
  }

  @TargetApi(16)
  static class SDK16
  {
    public static void postOnAnimation(View paramView, Runnable paramRunnable)
    {
      paramView.postOnAnimation(paramRunnable);
    }

    public static void setBackground(View paramView, Drawable paramDrawable)
    {
      paramView.setBackground(paramDrawable);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.internal.ViewCompat
 * JD-Core Version:    0.6.2
 */