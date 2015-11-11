package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import fm.qingting.framework.utils.BitmapResourceCache;

public class ImageViewElement extends ViewElement
{
  private Bitmap mBitmap;
  private final Rect mImageRect = new Rect();
  private int mImageRes = 0;
  private final Paint mPaint = new Paint();

  public ImageViewElement(Context paramContext)
  {
    super(paramContext);
  }

  private void drawImage(Canvas paramCanvas)
  {
    if (this.mImageRes == 0);
    while (true)
    {
      return;
      if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
        this.mImageRect.offset(this.mTranslationX, this.mTranslationY);
      if (this.mBitmap == null)
        paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mImageRes), null, this.mImageRect, this.mPaint);
      while ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      {
        this.mImageRect.offset(-this.mTranslationX, -this.mTranslationY);
        return;
        paramCanvas.drawBitmap(this.mBitmap, null, this.mImageRect, this.mPaint);
      }
    }
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.save();
    drawImage(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mImageRect.set(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setAlpha(int paramInt)
  {
    this.mPaint.setAlpha(paramInt);
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    this.mBitmap = paramBitmap;
  }

  public void setImageRes(int paramInt)
  {
    this.mImageRes = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.ImageViewElement
 * JD-Core Version:    0.6.2
 */