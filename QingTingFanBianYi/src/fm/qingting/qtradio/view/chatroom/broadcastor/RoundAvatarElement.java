package fm.qingting.qtradio.view.chatroom.broadcastor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.widget.ImageView;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.utils.ImageLoaderHandler;
import fm.qingting.framework.view.ViewElement;

public class RoundAvatarElement extends ViewElement
  implements ImageLoaderHandler
{
  private int mCachedBitmapId;
  private int mDefaultImageRes = 0;
  private boolean mError = false;
  private final Rect mImageRect = new Rect();
  private int mLoadingImageRes = 0;
  private final Paint mPaint = new Paint();
  private String mUrl;

  public RoundAvatarElement(Context paramContext)
  {
    super(paramContext);
  }

  private void drawDefault(Canvas paramCanvas)
  {
    if (this.mDefaultImageRes != 0)
      drawRoundBitmap(paramCanvas, BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mDefaultImageRes));
  }

  private void drawLoading(Canvas paramCanvas)
  {
    if (this.mLoadingImageRes != 0)
    {
      drawRoundBitmap(paramCanvas, BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mLoadingImageRes));
      return;
    }
    drawDefault(paramCanvas);
  }

  private void drawRoundBitmap(Canvas paramCanvas, Bitmap paramBitmap)
  {
    int i = paramBitmap.hashCode();
    if (this.mCachedBitmapId != i)
    {
      BitmapShader localBitmapShader = new BitmapShader(paramBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
      this.mPaint.setShader(localBitmapShader);
      this.mCachedBitmapId = i;
    }
    i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k = Math.min(i, j);
    if (k == 0)
      return;
    float f1 = k / 2.0F;
    float f2 = this.mImageRect.width() / k;
    float f3 = (i / 2.0F + f1) / 2.0F;
    float f4 = (j / 2.0F + f1) / 2.0F;
    i = paramCanvas.save();
    paramCanvas.scale(f2, f2, this.mImageRect.centerX() + this.mTranslationX, this.mImageRect.centerY() + this.mTranslationY);
    paramCanvas.translate(this.mImageRect.centerX() + this.mTranslationX - f3, this.mImageRect.centerY() + this.mTranslationY - f4);
    paramCanvas.drawCircle(f3, f4, f1, this.mPaint);
    paramCanvas.restoreToCount(i);
  }

  public void loadImageFinish(boolean paramBoolean, String paramString, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if ((paramBoolean) && (this.mUrl != null) && (this.mUrl.equalsIgnoreCase(paramString)))
    {
      this.mError = false;
      invalidateElement(this.mImageRect);
    }
    while (paramBoolean)
      return;
    this.mError = true;
    invalidateElement(this.mImageRect);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.save();
    if ((this.mUrl == null) || (this.mUrl.equalsIgnoreCase("")))
      drawDefault(paramCanvas);
    while (true)
    {
      paramCanvas.restore();
      return;
      Bitmap localBitmap = ImageLoader.getInstance(getContext()).getImage(this.mUrl, 0, 0);
      if (localBitmap == null)
      {
        if (this.mError)
        {
          drawDefault(paramCanvas);
        }
        else
        {
          drawLoading(paramCanvas);
          ImageLoader.getInstance(getContext()).loadImage(this.mUrl, null, this, 0, 0, this);
        }
      }
      else
        drawRoundBitmap(paramCanvas, localBitmap);
    }
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mImageRect.set(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setDefaultImageRes(int paramInt)
  {
    this.mDefaultImageRes = paramInt;
  }

  public void setImageUrl(String paramString)
  {
    this.mUrl = paramString;
    this.mError = false;
    invalidateAll();
  }

  public void setLoadingImageRes(int paramInt)
  {
    this.mLoadingImageRes = paramInt;
  }

  public void updateImageViewFinish(boolean paramBoolean, ImageView paramImageView, String paramString, Bitmap paramBitmap)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement
 * JD-Core Version:    0.6.2
 */