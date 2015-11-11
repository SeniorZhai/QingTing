package com.taobao.newxp.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.ImageView;

public class CYCImageView extends ImageView
{
  boolean a = false;

  public CYCImageView(Context paramContext)
  {
    super(paramContext);
  }

  public CYCImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private static void a(Drawable paramDrawable, boolean paramBoolean)
  {
    if ((paramDrawable instanceof RecyclingBitmapDrawable))
      ((RecyclingBitmapDrawable)paramDrawable).setIsDisplayed(paramBoolean);
    while (true)
    {
      return;
      if ((paramDrawable instanceof LayerDrawable))
      {
        paramDrawable = (LayerDrawable)paramDrawable;
        int i = 0;
        int j = paramDrawable.getNumberOfLayers();
        while (i < j)
        {
          a(paramDrawable.getDrawable(i), paramBoolean);
          i += 1;
        }
      }
    }
  }

  protected void onDetachedFromWindow()
  {
    setImageDrawable(null);
    super.onDetachedFromWindow();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (this.a)
      getLayoutParams().height = View.MeasureSpec.getSize(paramInt1);
  }

  public void setAutoFitHeight(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  public void setImageDrawable(Drawable paramDrawable)
  {
    Drawable localDrawable = getDrawable();
    super.setImageDrawable(paramDrawable);
    a(paramDrawable, true);
    a(localDrawable, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.widget.CYCImageView
 * JD-Core Version:    0.6.2
 */