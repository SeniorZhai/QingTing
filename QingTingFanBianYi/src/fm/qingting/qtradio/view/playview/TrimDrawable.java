package fm.qingting.qtradio.view.playview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import fm.qingting.qtradio.manager.SkinManager;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TrimDrawable extends Drawable
{
  private static final int DEFAULT_PAINT_FLAGS = 6;
  private Bitmap mBitmap;
  private int mBitmapHeight;
  private BitmapState mBitmapState;
  private int mBitmapWidth;
  private boolean mMutated;
  private int mTargetDensity;

  public TrimDrawable(Resources paramResources, Bitmap paramBitmap)
  {
    this(new BitmapState(paramBitmap), paramResources);
    this.mBitmapState.mTargetDensity = this.mTargetDensity;
  }

  public TrimDrawable(Bitmap paramBitmap)
  {
    this(new BitmapState(paramBitmap), null);
  }

  private TrimDrawable(BitmapState paramBitmapState, Resources paramResources)
  {
    this.mBitmapState = paramBitmapState;
    if (paramResources != null)
    {
      this.mTargetDensity = paramResources.getDisplayMetrics().densityDpi;
      if (paramBitmapState == null)
        break label50;
    }
    label50: for (paramBitmapState = paramBitmapState.mBitmap; ; paramBitmapState = null)
    {
      setBitmap(paramBitmapState);
      return;
      this.mTargetDensity = paramBitmapState.mTargetDensity;
      break;
    }
  }

  private void computeBitmapSize()
  {
    this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
    this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
  }

  private void setBitmap(Bitmap paramBitmap)
  {
    if (paramBitmap != this.mBitmap)
    {
      this.mBitmap = paramBitmap;
      if (paramBitmap == null)
        break label26;
      computeBitmapSize();
    }
    while (true)
    {
      invalidateSelf();
      return;
      label26: this.mBitmapHeight = -1;
      this.mBitmapWidth = -1;
    }
  }

  public void draw(Canvas paramCanvas)
  {
    Bitmap localBitmap = this.mBitmap;
    BitmapState localBitmapState;
    Rect localRect;
    if (localBitmap != null)
    {
      localBitmapState = this.mBitmapState;
      localRect = getBounds();
      if (!localRect.isEmpty());
    }
    else
    {
      return;
    }
    paramCanvas.drawBitmap(localBitmap, SkinManager.getTrimedBitmapRect(localBitmap, localRect.width(), localRect.height()), localRect, localBitmapState.mPaint);
  }

  public int getAlpha()
  {
    return this.mBitmapState.mPaint.getAlpha();
  }

  public final Bitmap getBitmap()
  {
    return this.mBitmap;
  }

  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | this.mBitmapState.mChangingConfigurations;
  }

  public final Drawable.ConstantState getConstantState()
  {
    this.mBitmapState.mChangingConfigurations = getChangingConfigurations();
    return this.mBitmapState;
  }

  public int getGravity()
  {
    return this.mBitmapState.mGravity;
  }

  public int getIntrinsicHeight()
  {
    return this.mBitmapHeight;
  }

  public int getIntrinsicWidth()
  {
    return this.mBitmapWidth;
  }

  public int getOpacity()
  {
    if (this.mBitmapState.mGravity != 119);
    Bitmap localBitmap;
    do
    {
      return -3;
      localBitmap = this.mBitmap;
    }
    while ((localBitmap == null) || (localBitmap.hasAlpha()) || (this.mBitmapState.mPaint.getAlpha() < 255));
    return -1;
  }

  public final Paint getPaint()
  {
    return this.mBitmapState.mPaint;
  }

  public boolean hasAntiAlias()
  {
    return this.mBitmapState.mPaint.isAntiAlias();
  }

  public void inflate(Resources paramResources, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet)
    throws XmlPullParserException, IOException
  {
    super.inflate(paramResources, paramXmlPullParser, paramAttributeSet);
  }

  public Drawable mutate()
  {
    if ((!this.mMutated) && (super.mutate() == this))
    {
      this.mBitmapState = new BitmapState(this.mBitmapState);
      this.mMutated = true;
    }
    return this;
  }

  protected void onBoundsChange(Rect paramRect)
  {
    super.onBoundsChange(paramRect);
  }

  public void setAlpha(int paramInt)
  {
    if (paramInt != this.mBitmapState.mPaint.getAlpha())
    {
      this.mBitmapState.mPaint.setAlpha(paramInt);
      invalidateSelf();
    }
  }

  public void setAntiAlias(boolean paramBoolean)
  {
    this.mBitmapState.mPaint.setAntiAlias(paramBoolean);
    invalidateSelf();
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.mBitmapState.mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }

  public void setDither(boolean paramBoolean)
  {
    this.mBitmapState.mPaint.setDither(paramBoolean);
    invalidateSelf();
  }

  public void setFilterBitmap(boolean paramBoolean)
  {
    this.mBitmapState.mPaint.setFilterBitmap(paramBoolean);
    invalidateSelf();
  }

  public void setGravity(int paramInt)
  {
    if (this.mBitmapState.mGravity != paramInt)
    {
      this.mBitmapState.mGravity = paramInt;
      invalidateSelf();
    }
  }

  public void setTargetDensity(int paramInt)
  {
    if (this.mTargetDensity != paramInt)
    {
      int i = paramInt;
      if (paramInt == 0)
        i = 160;
      this.mTargetDensity = i;
      if (this.mBitmap != null)
        computeBitmapSize();
      invalidateSelf();
    }
  }

  public void setTargetDensity(Canvas paramCanvas)
  {
    setTargetDensity(paramCanvas.getDensity());
  }

  public void setTargetDensity(DisplayMetrics paramDisplayMetrics)
  {
    setTargetDensity(paramDisplayMetrics.densityDpi);
  }

  public void setXfermode(Xfermode paramXfermode)
  {
    this.mBitmapState.mPaint.setXfermode(paramXfermode);
    invalidateSelf();
  }

  static final class BitmapState extends Drawable.ConstantState
  {
    Bitmap mBitmap;
    int mChangingConfigurations;
    int mGravity = 119;
    Paint mPaint = new Paint(6);
    int mTargetDensity = 160;

    BitmapState(Bitmap paramBitmap)
    {
      this.mBitmap = paramBitmap;
    }

    BitmapState(BitmapState paramBitmapState)
    {
      this(paramBitmapState.mBitmap);
      this.mChangingConfigurations = paramBitmapState.mChangingConfigurations;
      this.mGravity = paramBitmapState.mGravity;
      this.mTargetDensity = paramBitmapState.mTargetDensity;
      this.mPaint = new Paint(paramBitmapState.mPaint);
    }

    public int getChangingConfigurations()
    {
      return this.mChangingConfigurations;
    }

    public Drawable newDrawable()
    {
      return new TrimDrawable(this, null, null);
    }

    public Drawable newDrawable(Resources paramResources)
    {
      return new TrimDrawable(this, paramResources, null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.TrimDrawable
 * JD-Core Version:    0.6.2
 */