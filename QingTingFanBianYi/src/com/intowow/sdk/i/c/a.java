package com.intowow.sdk.i.c;

import android.content.Context;
import android.widget.ImageView;

public class a extends ImageView
{
  private int a;
  private int b;

  public a(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext);
    this.a = paramInt1;
    this.b = paramInt2;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(this.a, this.b);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.a
 * JD-Core Version:    0.6.2
 */