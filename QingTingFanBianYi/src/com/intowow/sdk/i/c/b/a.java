package com.intowow.sdk.i.c.b;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.view.View;
import com.intowow.sdk.i.c.c.b;

public class a extends View
  implements b
{
  private int a = 0;
  private int b = 0;
  private float c = 0.0F;
  private float d = 0.0F;
  private float e = 0.0F;
  private String f = "";
  private Paint g = null;

  public a(Activity paramActivity, int paramInt1, int paramInt2)
  {
    super(paramActivity);
    this.a = paramInt1;
    this.c = paramInt2;
    b();
  }

  private void b()
  {
    this.g = new Paint();
    this.g.setColor(-1);
    this.g.setShadowLayer(2.0F, 0.0F, 0.0F, Color.parseColor("#5c5c5c"));
    this.g.setTextSize(this.c);
    this.g.setStyle(Paint.Style.FILL);
    this.g.setAntiAlias(true);
    this.g.setTextAlign(Paint.Align.RIGHT);
    this.g.setTypeface(Typeface.defaultFromStyle(0));
    if (Build.VERSION.SDK_INT >= 11)
      setLayerType(1, this.g);
  }

  public void a()
  {
    this.f = "";
  }

  public void a(int paramInt)
  {
    this.b = paramInt;
    String str = String.format("%d sec", new Object[] { Integer.valueOf((int)Math.floor((this.a - this.b) / 1000)) });
    if (this.f.equals(str))
      return;
    this.f = str;
    invalidate();
  }

  public void e()
  {
  }

  public void f()
  {
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawText(this.f, this.d, this.e, this.g);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.e = (paramInt2 - 3);
    this.d = (paramInt1 - 3);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.i.c.b.a
 * JD-Core Version:    0.6.2
 */