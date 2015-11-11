package fm.qingting.qtradio.view.chatroom;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import fm.qingting.framework.utils.BitmapResourceCache;

public class ExpressionSpan extends ReplacementSpan
{
  private int mHeight = 0;
  private int mOwnerId;
  private int mResId = 0;
  private Resources mResources;
  private int mWidth = 0;
  private final int margin = 2;

  public ExpressionSpan()
  {
  }

  public ExpressionSpan(Resources paramResources, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mResources = paramResources;
    this.mResId = paramInt1;
    this.mWidth = paramInt2;
    this.mHeight = paramInt3;
    this.mOwnerId = paramInt4;
  }

  public void draw(Canvas paramCanvas, CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint)
  {
    if ((this.mResId == 0) || (this.mWidth == 0) || (this.mHeight == 0) || (this.mResources == null))
      return;
    paramCanvas.save();
    paramCharSequence = BitmapResourceCache.getInstance().getResourceCacheByParent(this.mResources, this.mOwnerId, this.mResId);
    paramCanvas.drawBitmap(paramCharSequence, new Rect(0, 0, paramCharSequence.getWidth(), paramCharSequence.getHeight()), new RectF(2.0F + paramFloat, (paramInt3 + paramInt5 - this.mHeight) / 2, 2.0F + paramFloat + this.mWidth, (paramInt3 + paramInt5 + this.mHeight) / 2), null);
    paramCanvas.restore();
  }

  public int getSize(Paint paramPaint, CharSequence paramCharSequence, int paramInt1, int paramInt2, Paint.FontMetricsInt paramFontMetricsInt)
  {
    return this.mWidth + 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.ExpressionSpan
 * JD-Core Version:    0.6.2
 */