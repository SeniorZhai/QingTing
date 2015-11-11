package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import fm.qingting.framework.utils.BitmapResourceCache;

public class ButtonViewElement extends ViewElement
{
  private int mColorDisable = 0;
  private int mColorHighlight = 0;
  private int mColorNormal = 0;
  private final Paint mColorPaint = new Paint();
  private int mDisableRes = 0;
  private int mImageHighlightRes = 0;
  private int mImageNormalRes = 0;
  private final Rect mImageRect = new Rect();
  private int mImageSelectedRes = 0;
  private Paint mPaint = new Paint();
  private boolean mRoundCorner = false;
  private float mRoundCornerRadius;
  private RectF mRoundRectF = new RectF();
  private final Paint mSimpleHighlightPaint = new Paint();
  private Rect mTextBound = new Rect();
  private int mTextColorDisable = 0;
  private int mTextColorHighlight = 0;
  private int mTextColorNormal = 0;
  private int mTextColorSelected = 0;
  private Paint mTextPaint = new Paint();
  private String mTitle;
  private boolean mUsingSimple = false;

  public ButtonViewElement(Context paramContext)
  {
    super(paramContext);
  }

  private void drawImage(Canvas paramCanvas)
  {
    if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
      this.mImageRect.offset(this.mTranslationX, this.mTranslationY);
    if (needHighlight())
      if (this.mUsingSimple)
        if (this.mImageNormalRes != 0)
          paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mImageNormalRes), null, this.mImageRect, this.mSimpleHighlightPaint);
    do
      while (true)
      {
        if ((this.mTranslationX != 0) || (this.mTranslationY != 0))
          this.mImageRect.offset(-this.mTranslationX, -this.mTranslationY);
        return;
        if ((isSelected()) && (this.mImageSelectedRes != 0))
        {
          paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mImageSelectedRes), null, this.mImageRect, this.mPaint);
        }
        else if (this.mImageHighlightRes != 0)
        {
          paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mImageHighlightRes), null, this.mImageRect, this.mPaint);
        }
        else if (this.mColorHighlight != 0)
        {
          this.mColorPaint.setColor(this.mColorHighlight);
          i = paramCanvas.save();
          if (this.mRoundCorner)
          {
            this.mRoundRectF.set(this.mImageRect);
            paramCanvas.drawRoundRect(this.mRoundRectF, this.mRoundCornerRadius, this.mRoundCornerRadius, this.mColorPaint);
          }
          while (true)
          {
            paramCanvas.restoreToCount(i);
            break;
            paramCanvas.clipRect(this.mImageRect);
            paramCanvas.drawColor(this.mColorHighlight);
          }
          if (!this.mEnable)
          {
            if (this.mDisableRes != 0)
            {
              paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mDisableRes), null, this.mImageRect, this.mPaint);
            }
            else if (this.mColorDisable != 0)
            {
              this.mColorPaint.setColor(this.mColorDisable);
              i = paramCanvas.save();
              if (this.mRoundCorner)
              {
                this.mRoundRectF.set(this.mImageRect);
                paramCanvas.drawRoundRect(this.mRoundRectF, this.mRoundCornerRadius, this.mRoundCornerRadius, this.mColorPaint);
              }
              while (true)
              {
                paramCanvas.restoreToCount(i);
                break;
                paramCanvas.clipRect(this.mImageRect);
                paramCanvas.drawColor(this.mColorNormal);
              }
            }
          }
          else
          {
            if (this.mImageNormalRes == 0)
              break;
            paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getContext().getResources(), this.mOwnerId, this.mImageNormalRes), null, this.mImageRect, this.mPaint);
          }
        }
      }
    while (this.mColorNormal == 0);
    this.mColorPaint.setColor(this.mColorNormal);
    int i = paramCanvas.save();
    if (this.mRoundCorner)
    {
      this.mRoundRectF.set(this.mImageRect);
      paramCanvas.drawRoundRect(this.mRoundRectF, this.mRoundCornerRadius, this.mRoundCornerRadius, this.mColorPaint);
    }
    while (true)
    {
      paramCanvas.restoreToCount(i);
      break;
      paramCanvas.clipRect(this.mImageRect);
      paramCanvas.drawColor(this.mColorNormal);
    }
  }

  private void drawTitle(Canvas paramCanvas)
  {
    if ((this.mTitle == null) || (this.mTitle.equalsIgnoreCase("")))
      return;
    if (needHighlight())
      if ((isSelected()) && (this.mTextColorSelected != 0))
        this.mTextPaint.setColor(this.mTextColorSelected);
    while (true)
    {
      this.mTextPaint.getTextBounds(this.mTitle, 0, this.mTitle.length(), this.mTextBound);
      paramCanvas.drawText(this.mTitle, this.mTranslationX + this.mImageRect.centerX() - this.mTextBound.width() / 2, this.mTranslationY + this.mImageRect.centerY() - (this.mTextBound.top + this.mTextBound.bottom) / 2, this.mTextPaint);
      return;
      if (this.mTextColorHighlight != 0)
      {
        this.mTextPaint.setColor(this.mTextColorHighlight);
        continue;
        if ((!this.mEnable) && (this.mTextColorDisable != 0))
          this.mTextPaint.setColor(this.mTextColorDisable);
        else if (this.mTextColorNormal != 0)
          this.mTextPaint.setColor(this.mTextColorNormal);
      }
    }
  }

  private final boolean needHighlight()
  {
    return (isSelected()) || ((this.mEnable) && (isPressed()));
  }

  private void resolveColor(int paramInt)
  {
    ColorMatrixColorFilter localColorMatrixColorFilter = new ColorMatrixColorFilter(new float[] { 0.0F, 0.0F, 0.0F, 0.0F, paramInt & 0xFF0000, 0.0F, 0.0F, 0.0F, 0.0F, paramInt & 0xFF00, 0.0F, 0.0F, 0.0F, 0.0F, paramInt & 0xFF, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F });
    this.mSimpleHighlightPaint.setColorFilter(localColorMatrixColorFilter);
  }

  private void setSimpleBackground(int paramInt1, int paramInt2)
  {
    this.mUsingSimple = true;
    this.mImageNormalRes = paramInt1;
    resolveColor(paramInt2);
  }

  public boolean isEnabled()
  {
    return this.mEnable;
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.save();
    drawImage(paramCanvas);
    drawTitle(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mImageRect.set(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setBackground(int paramInt1, int paramInt2)
  {
    this.mImageHighlightRes = paramInt1;
    this.mImageNormalRes = paramInt2;
  }

  public void setBackground(int paramInt1, int paramInt2, int paramInt3)
  {
    setBackground(paramInt1, paramInt2);
    this.mDisableRes = paramInt3;
  }

  public void setBackground(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setBackground(paramInt1, paramInt2, paramInt3);
    this.mImageSelectedRes = paramInt4;
  }

  public void setBackgroundColor(int paramInt1, int paramInt2)
  {
    this.mColorHighlight = paramInt1;
    this.mColorNormal = paramInt2;
  }

  public void setBackgroundColor(int paramInt1, int paramInt2, int paramInt3)
  {
    setBackgroundColor(paramInt1, paramInt2);
    this.mColorDisable = paramInt3;
  }

  public void setBackgroundColor(int paramInt1, int paramInt2, Paint.Style paramStyle1, Paint.Style paramStyle2)
  {
    setBackgroundColor(paramInt1, paramInt2);
    this.mColorPaint.setStyle(paramStyle1);
  }

  public void setEnable(boolean paramBoolean)
  {
    if (this.mEnable != paramBoolean)
    {
      this.mEnable = paramBoolean;
      invalidateElement();
    }
  }

  public void setRoundCorner(boolean paramBoolean)
  {
    this.mRoundCorner = paramBoolean;
  }

  public void setRoundCornerRadius(float paramFloat)
  {
    this.mRoundCornerRadius = paramFloat;
  }

  public void setText(String paramString)
  {
    this.mTitle = paramString;
  }

  public void setTextColor(int paramInt)
  {
    this.mTextColorNormal = paramInt;
  }

  public void setTextColor(int paramInt1, int paramInt2)
  {
    setTextColor(paramInt2);
    this.mTextColorHighlight = paramInt1;
  }

  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    setTextColor(paramInt1, paramInt2);
    this.mTextColorDisable = paramInt3;
  }

  public void setTextColor(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setTextColor(paramInt1, paramInt2, paramInt3);
    this.mTextColorSelected = paramInt4;
  }

  public void setTextSize(float paramFloat)
  {
    this.mTextPaint.setTextSize(paramFloat);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.ButtonViewElement
 * JD-Core Version:    0.6.2
 */