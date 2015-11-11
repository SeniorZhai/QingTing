package fm.qingting.qtradio.view.popviews;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.qtradio.manager.QtApiLevelManager;

class AnimatorImageViewElement extends ViewElement
{
  private ValueAnimator mAnimator;
  private int mBCenterX;
  private int mBCenterY;
  private int mBRadiusX;
  private int mBRadiusY;
  private Bitmap mBitmap;
  private Rect mBitmapRect;
  private int mCenterX;
  private int mCenterY;
  private Rect mDstRect = new Rect();
  private Rect mImageRect = new Rect();
  private Paint mPaint = new Paint();
  private float mRadiusFactor = 0.0F;
  private int mRadiusX;
  private int mRadiusY;
  private Rect mSrcRect = new Rect();

  public AnimatorImageViewElement(Context paramContext)
  {
    super(paramContext);
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.mAnimator = new ValueAnimator();
      this.mAnimator.setDuration(200L);
      this.mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          AnimatorImageViewElement.access$002(AnimatorImageViewElement.this, ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
          AnimatorImageViewElement.this.invalidateAll();
        }
      });
    }
    startAnimator();
  }

  private void drawImage(Canvas paramCanvas)
  {
    if (this.mBitmap == null)
      return;
    this.mSrcRect.set((int)(this.mBCenterX - this.mBRadiusX * this.mRadiusFactor), (int)(this.mBCenterY - this.mBRadiusY * this.mRadiusFactor), (int)(this.mBCenterX + this.mBRadiusX * this.mRadiusFactor), (int)(this.mBCenterY + this.mBRadiusY * this.mRadiusFactor));
    this.mDstRect.set((int)(this.mCenterX - this.mRadiusX * this.mRadiusFactor), (int)(this.mCenterY - this.mRadiusY * this.mRadiusFactor), (int)(this.mCenterX + this.mRadiusX * this.mRadiusFactor), (int)(this.mCenterY + this.mRadiusY * this.mRadiusFactor));
    paramCanvas.drawBitmap(this.mBitmap, this.mSrcRect, this.mDstRect, this.mPaint);
  }

  private void startAnimator()
  {
    if (this.mAnimator != null)
    {
      this.mAnimator.setFloatValues(new float[] { 0.0F, 1.0F });
      this.mAnimator.start();
      this.mRadiusFactor = 0.0F;
      return;
    }
    this.mRadiusFactor = 1.0F;
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
    this.mCenterX = this.mImageRect.centerX();
    this.mCenterY = this.mImageRect.centerY();
    this.mRadiusX = (this.mImageRect.width() / 2);
    this.mRadiusY = (this.mImageRect.height() / 2);
  }

  public void setImageRes(int paramInt)
  {
    this.mBitmap = BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, paramInt);
    if (this.mBitmap != null)
    {
      this.mBitmapRect = new Rect(0, 0, this.mBitmap.getWidth(), this.mBitmap.getHeight());
      this.mBCenterX = this.mBitmapRect.centerX();
      this.mBCenterY = this.mBitmapRect.centerY();
      this.mBRadiusX = (this.mBitmapRect.width() / 2);
      this.mBRadiusY = (this.mBitmapRect.height() / 2);
    }
    startAnimator();
    invalidateAll();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.AnimatorImageViewElement
 * JD-Core Version:    0.6.2
 */