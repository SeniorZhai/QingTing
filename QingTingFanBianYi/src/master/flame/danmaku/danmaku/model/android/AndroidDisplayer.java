package master.flame.danmaku.danmaku.model.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.TextPaint;
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.model.AbsDisplayer;
import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.BaseDanmaku;

public class AndroidDisplayer extends AbsDisplayer<Canvas>
{
  private static Paint ALPHA_PAINT;
  private static boolean ANTI_ALIAS = false;
  public static final int BG_CORNER = 6;
  public static final int BG_HEIGHT = 12;
  private static Paint BG_PAINT;
  private static RectF BG_RECTF = new RectF();
  public static final int BG_WIDTH = 24;
  private static Paint BORDER_PAINT;
  public static final int BORDER_WIDTH = 4;
  public static boolean CONFIG_ANTI_ALIAS = false;
  public static boolean CONFIG_HAS_PROJECTION = false;
  public static boolean CONFIG_HAS_SHADOW = false;
  public static boolean CONFIG_HAS_STROKE = false;
  public static final int DRAWABLE_PADDING = 20;
  private static Paint DRAWABLE_PAINT;
  private static Rect DRAWABLE_RECT = new Rect();
  public static final int DRAWABLE_SIZE = 44;
  private static boolean HAS_PROJECTION;
  private static boolean HAS_SHADOW;
  private static boolean HAS_STROKE;
  public static TextPaint PAINT;
  public static TextPaint PAINT_DUPLICATE;
  private static float SHADOW_RADIUS;
  private static float STROKE_WIDTH;
  public static int UNDERLINE_HEIGHT;
  private static Paint UNDERLINE_PAINT;
  private static final Map<Integer, Bitmap> sCachedBitmap;
  private static final Map<Float, Float> sCachedScaleSize;
  private static float sLastScaleTextSize;
  private static int sProjectionAlpha;
  private static float sProjectionOffsetX;
  private static float sProjectionOffsetY;
  private static final Map<Float, Float> sTextHeightCache = new HashMap();
  private int HIT_CACHE_COUNT = 0;
  private int NO_CACHE_COUNT = 0;
  private Camera camera = new Camera();
  public Canvas canvas;
  private Context context;
  private float density = 1.0F;
  private int densityDpi = 160;
  private int height;
  private boolean mIsHardwareAccelerated = true;
  private int mMaximumBitmapHeight = 2048;
  private int mMaximumBitmapWidth = 2048;
  private int mSlopPixel = 0;
  private Matrix matrix = new Matrix();
  private float scaledDensity = 1.0F;
  private int width;

  static
  {
    sCachedScaleSize = new HashMap(10);
    sCachedBitmap = new HashMap(2);
    UNDERLINE_HEIGHT = 4;
    SHADOW_RADIUS = 4.0F;
    STROKE_WIDTH = 3.5F;
    sProjectionOffsetX = 1.0F;
    sProjectionOffsetY = 1.0F;
    sProjectionAlpha = 204;
    CONFIG_HAS_SHADOW = false;
    HAS_SHADOW = CONFIG_HAS_SHADOW;
    CONFIG_HAS_STROKE = true;
    HAS_STROKE = CONFIG_HAS_STROKE;
    CONFIG_HAS_PROJECTION = false;
    HAS_PROJECTION = CONFIG_HAS_PROJECTION;
    CONFIG_ANTI_ALIAS = true;
    ANTI_ALIAS = CONFIG_ANTI_ALIAS;
    PAINT = new TextPaint();
    PAINT.setStrokeWidth(STROKE_WIDTH);
    PAINT_DUPLICATE = new TextPaint(PAINT);
    ALPHA_PAINT = new Paint();
    UNDERLINE_PAINT = new Paint();
    UNDERLINE_PAINT.setStrokeWidth(UNDERLINE_HEIGHT);
    UNDERLINE_PAINT.setStyle(Paint.Style.STROKE);
    BORDER_PAINT = new Paint();
    BORDER_PAINT.setStyle(Paint.Style.STROKE);
    BORDER_PAINT.setStrokeWidth(4.0F);
    DRAWABLE_PAINT = new Paint();
    BG_PAINT = new Paint();
    BG_PAINT.setStyle(Paint.Style.FILL);
  }

  private static void applyPaintConfig(BaseDanmaku paramBaseDanmaku, Paint paramPaint, boolean paramBoolean)
  {
    Paint.Style localStyle;
    int i;
    if (DanmakuGlobalConfig.DEFAULT.isTranslucent)
    {
      if (paramBoolean)
      {
        if (HAS_PROJECTION)
        {
          localStyle = Paint.Style.FILL;
          paramPaint.setStyle(localStyle);
          paramPaint.setColor(paramBaseDanmaku.textShadowColor & 0xFFFFFF);
          if (!HAS_PROJECTION)
            break label79;
        }
        label79: for (i = (int)(sProjectionAlpha * (DanmakuGlobalConfig.DEFAULT.transparency / AlphaValue.MAX)); ; i = DanmakuGlobalConfig.DEFAULT.transparency)
        {
          paramPaint.setAlpha(i);
          return;
          localStyle = Paint.Style.STROKE;
          break;
        }
      }
      paramPaint.setStyle(Paint.Style.FILL);
      paramPaint.setColor(paramBaseDanmaku.textColor & 0xFFFFFF);
      paramPaint.setAlpha(DanmakuGlobalConfig.DEFAULT.transparency);
      return;
    }
    if (paramBoolean)
    {
      if (HAS_PROJECTION)
      {
        localStyle = Paint.Style.FILL;
        paramPaint.setStyle(localStyle);
        paramPaint.setColor(paramBaseDanmaku.textShadowColor & 0xFFFFFF);
        if (!HAS_PROJECTION)
          break label174;
      }
      label174: for (i = sProjectionAlpha; ; i = AlphaValue.MAX)
      {
        paramPaint.setAlpha(i);
        return;
        localStyle = Paint.Style.STROKE;
        break;
      }
    }
    paramPaint.setStyle(Paint.Style.FILL);
    paramPaint.setColor(paramBaseDanmaku.textColor & 0xFFFFFF);
    paramPaint.setAlpha(AlphaValue.MAX);
  }

  private static void applyTextScaleConfig(BaseDanmaku paramBaseDanmaku, Paint paramPaint)
  {
    if (!DanmakuGlobalConfig.DEFAULT.isTextScaled)
      return;
    Float localFloat = (Float)sCachedScaleSize.get(Float.valueOf(paramBaseDanmaku.textSize));
    if ((localFloat == null) || (sLastScaleTextSize != DanmakuGlobalConfig.DEFAULT.scaleTextSize))
    {
      sLastScaleTextSize = DanmakuGlobalConfig.DEFAULT.scaleTextSize;
      localFloat = Float.valueOf(paramBaseDanmaku.textSize * DanmakuGlobalConfig.DEFAULT.scaleTextSize);
      sCachedScaleSize.put(Float.valueOf(paramBaseDanmaku.textSize), localFloat);
    }
    paramPaint.setTextSize(localFloat.floatValue());
  }

  private void calcPaintWH(BaseDanmaku paramBaseDanmaku, TextPaint paramTextPaint)
  {
    float f2 = 0.0F;
    float f1 = 0.0F;
    Float localFloat = Float.valueOf(getTextHeight(paramTextPaint));
    if (paramBaseDanmaku.lines == null)
    {
      if (paramBaseDanmaku.text == null);
      while (true)
      {
        setDanmakuPaintWidthAndHeight(paramBaseDanmaku, f1, localFloat.floatValue());
        return;
        f1 = paramTextPaint.measureText(paramBaseDanmaku.text);
      }
    }
    String[] arrayOfString = paramBaseDanmaku.lines;
    int j = arrayOfString.length;
    int i = 0;
    for (f1 = f2; i < j; f1 = f2)
    {
      String str = arrayOfString[i];
      f2 = f1;
      if (str.length() > 0)
        f2 = Math.max(paramTextPaint.measureText(str), f1);
      i += 1;
    }
    setDanmakuPaintWidthAndHeight(paramBaseDanmaku, f1, paramBaseDanmaku.lines.length * localFloat.floatValue());
  }

  public static void clearTextHeightCache()
  {
    sTextHeightCache.clear();
    sCachedScaleSize.clear();
  }

  public static void drawDanmaku(Context paramContext, BaseDanmaku paramBaseDanmaku, Canvas paramCanvas, float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    float f1 = paramFloat1 + paramBaseDanmaku.padding;
    float f2 = paramFloat2 + paramBaseDanmaku.padding;
    int i = 0;
    if (paramBaseDanmaku.borderColor != 0)
    {
      f2 = 4.0F + f2;
      f1 += 4.0F;
    }
    label154: label448: label704: label711: 
    while (true)
    {
      if (paramBaseDanmaku.drawableLeftResid != 0)
      {
        paramContext = getDrawableBitmap(paramContext, paramBaseDanmaku.drawableLeftResid);
        i = (int)f1;
        int j = (int)(12.0F + paramFloat2 + (paramBaseDanmaku.paintHeight - 44.0F) / 2.0F);
        DRAWABLE_RECT.set(i - 44 - 20, j, i - 20, j + 44);
        paramCanvas.drawBitmap(paramContext, null, DRAWABLE_RECT, DRAWABLE_PAINT);
      }
      HAS_STROKE = CONFIG_HAS_STROKE;
      HAS_SHADOW = CONFIG_HAS_SHADOW;
      HAS_PROJECTION = CONFIG_HAS_PROJECTION;
      boolean bool;
      Object localObject;
      float f3;
      float f4;
      if ((!paramBoolean) && (CONFIG_ANTI_ALIAS))
      {
        bool = true;
        ANTI_ALIAS = bool;
        paramContext = getPaint(paramBaseDanmaku, paramBoolean);
        if (paramBaseDanmaku.lines == null)
          break label606;
        localObject = paramBaseDanmaku.lines;
        if (localObject.length != 1)
          break label448;
        if (hasStroke(paramBaseDanmaku))
        {
          applyPaintConfig(paramBaseDanmaku, paramContext, true);
          f3 = f2 - paramContext.ascent();
          if (!HAS_PROJECTION)
            break label704;
          f4 = sProjectionOffsetX + f1;
          f3 += sProjectionOffsetY;
        }
      }
      while (true)
      {
        paramCanvas.drawText(localObject[0], f4, f3, paramContext);
        applyPaintConfig(paramBaseDanmaku, paramContext, false);
        paramCanvas.drawText(localObject[0], f1, f2 - paramContext.ascent(), paramContext);
        float f5;
        while (true)
        {
          if (paramBaseDanmaku.underlineColor != 0)
          {
            paramContext = getUnderlinePaint(paramBaseDanmaku);
            f1 = paramBaseDanmaku.paintHeight + paramFloat2 - UNDERLINE_HEIGHT;
            paramCanvas.drawLine(paramFloat1, f1, paramFloat1 + paramBaseDanmaku.paintWidth, f1, paramContext);
          }
          if (paramBaseDanmaku.borderColor != 0)
          {
            paramContext = getBorderPaint(paramBaseDanmaku);
            paramCanvas.drawRect(paramFloat1, paramFloat2, paramFloat1 + paramBaseDanmaku.paintWidth, paramFloat2 + paramBaseDanmaku.paintHeight, paramContext);
          }
          return;
          if (paramBaseDanmaku.bgColor == 0)
            break label711;
          localObject = getBgPaint(paramBaseDanmaku);
          if (paramBaseDanmaku.drawableLeftResid != 0)
            i = 64;
          BG_RECTF.set(paramFloat1, paramFloat2, paramBaseDanmaku.paintWidth + paramFloat1 + 48.0F + i, paramBaseDanmaku.paintHeight + paramFloat2 + 24.0F);
          paramCanvas.drawRoundRect(BG_RECTF, 6.0F, 6.0F, (Paint)localObject);
          f3 = i + 24;
          f2 = 12.0F + f2;
          f1 += f3;
          break;
          bool = false;
          break label154;
          f5 = (paramBaseDanmaku.paintHeight - paramBaseDanmaku.padding * 2) / localObject.length;
          i = 0;
          while (i < localObject.length)
          {
            if ((localObject[i] != null) && (localObject[i].length() != 0))
              break label506;
            label497: i += 1;
          }
        }
        label506: if (hasStroke(paramBaseDanmaku))
        {
          applyPaintConfig(paramBaseDanmaku, paramContext, true);
          f3 = i * f5 + f2 - paramContext.ascent();
          if (!HAS_PROJECTION)
            break label697;
          f4 = sProjectionOffsetX + f1;
          f3 += sProjectionOffsetY;
        }
        while (true)
        {
          paramCanvas.drawText(localObject[i], f4, f3, paramContext);
          applyPaintConfig(paramBaseDanmaku, paramContext, false);
          paramCanvas.drawText(localObject[i], f1, i * f5 + f2 - paramContext.ascent(), paramContext);
          break label497;
          label606: if (hasStroke(paramBaseDanmaku))
          {
            applyPaintConfig(paramBaseDanmaku, paramContext, true);
            f3 = f2 - paramContext.ascent();
            if (!HAS_PROJECTION)
              break label690;
            f4 = sProjectionOffsetX + f1;
            f3 += sProjectionOffsetY;
          }
          while (true)
          {
            paramCanvas.drawText(paramBaseDanmaku.text, f4, f3, paramContext);
            applyPaintConfig(paramBaseDanmaku, paramContext, false);
            paramCanvas.drawText(paramBaseDanmaku.text, f1, f2 - paramContext.ascent(), paramContext);
            break;
            f4 = f1;
          }
          f4 = f1;
        }
        f4 = f1;
      }
    }
  }

  public static Paint getBgPaint(BaseDanmaku paramBaseDanmaku)
  {
    BG_PAINT.setColor(paramBaseDanmaku.bgColor);
    return BG_PAINT;
  }

  public static Paint getBorderPaint(BaseDanmaku paramBaseDanmaku)
  {
    BORDER_PAINT.setColor(paramBaseDanmaku.borderColor);
    return BORDER_PAINT;
  }

  private static Bitmap getDrawableBitmap(Context paramContext, int paramInt)
  {
    Bitmap localBitmap2 = (Bitmap)sCachedBitmap.get(Integer.valueOf(paramInt));
    Bitmap localBitmap1;
    if (localBitmap2 != null)
    {
      localBitmap1 = localBitmap2;
      if (!localBitmap2.isRecycled());
    }
    else
    {
      localBitmap1 = BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
      sCachedBitmap.put(Integer.valueOf(paramInt), localBitmap1);
    }
    return localBitmap1;
  }

  @SuppressLint({"NewApi"})
  private static final int getMaximumBitmapHeight(Canvas paramCanvas)
  {
    if (Build.VERSION.SDK_INT >= 14)
      return paramCanvas.getMaximumBitmapHeight();
    return paramCanvas.getHeight();
  }

  @SuppressLint({"NewApi"})
  private static final int getMaximumBitmapWidth(Canvas paramCanvas)
  {
    if (Build.VERSION.SDK_INT >= 14)
      return paramCanvas.getMaximumBitmapWidth();
    return paramCanvas.getWidth();
  }

  public static TextPaint getPaint(BaseDanmaku paramBaseDanmaku)
  {
    return getPaint(paramBaseDanmaku, false);
  }

  private static TextPaint getPaint(BaseDanmaku paramBaseDanmaku, boolean paramBoolean)
  {
    TextPaint localTextPaint;
    if (paramBoolean)
    {
      localTextPaint = PAINT_DUPLICATE;
      localTextPaint.set(PAINT);
      localTextPaint.setTextSize(paramBaseDanmaku.textSize);
      applyTextScaleConfig(paramBaseDanmaku, localTextPaint);
      if ((HAS_SHADOW) && (SHADOW_RADIUS > 0.0F) && (paramBaseDanmaku.textShadowColor != 0))
        break label69;
      localTextPaint.clearShadowLayer();
    }
    while (true)
    {
      localTextPaint.setAntiAlias(ANTI_ALIAS);
      return localTextPaint;
      localTextPaint = PAINT;
      break;
      label69: localTextPaint.setShadowLayer(SHADOW_RADIUS, 0.0F, 0.0F, paramBaseDanmaku.textShadowColor);
    }
  }

  private static float getTextHeight(TextPaint paramTextPaint)
  {
    Float localFloat3 = Float.valueOf(paramTextPaint.getTextSize());
    Float localFloat2 = (Float)sTextHeightCache.get(localFloat3);
    Float localFloat1 = localFloat2;
    if (localFloat2 == null)
    {
      paramTextPaint = paramTextPaint.getFontMetrics();
      float f1 = paramTextPaint.descent;
      float f2 = paramTextPaint.ascent;
      localFloat1 = Float.valueOf(paramTextPaint.leading + (f1 - f2));
      sTextHeightCache.put(localFloat3, localFloat1);
    }
    return localFloat1.floatValue();
  }

  public static Paint getUnderlinePaint(BaseDanmaku paramBaseDanmaku)
  {
    UNDERLINE_PAINT.setColor(paramBaseDanmaku.underlineColor);
    return UNDERLINE_PAINT;
  }

  private static boolean hasStroke(BaseDanmaku paramBaseDanmaku)
  {
    return ((HAS_STROKE) || (HAS_PROJECTION)) && (STROKE_WIDTH > 0.0F) && (paramBaseDanmaku.textShadowColor != 0);
  }

  private void resetPaintAlpha(Paint paramPaint)
  {
    if (paramPaint.getAlpha() != AlphaValue.MAX)
      paramPaint.setAlpha(AlphaValue.MAX);
  }

  private void restoreCanvas(Canvas paramCanvas)
  {
    paramCanvas.restore();
  }

  private int saveCanvas(BaseDanmaku paramBaseDanmaku, Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    this.camera.save();
    this.camera.rotateY(-paramBaseDanmaku.rotationY);
    this.camera.rotateZ(-paramBaseDanmaku.rotationZ);
    this.camera.getMatrix(this.matrix);
    this.matrix.preTranslate(-paramFloat1, -paramFloat2);
    this.matrix.postTranslate(paramFloat1, paramFloat2);
    this.camera.restore();
    int i = paramCanvas.save();
    paramCanvas.concat(this.matrix);
    return i;
  }

  private void setDanmakuPaintWidthAndHeight(BaseDanmaku paramBaseDanmaku, float paramFloat1, float paramFloat2)
  {
    float f1 = paramFloat1 + paramBaseDanmaku.padding * 2;
    float f2 = paramBaseDanmaku.padding * 2 + paramFloat2;
    paramFloat2 = f2;
    paramFloat1 = f1;
    if (paramBaseDanmaku.borderColor != 0)
    {
      paramFloat1 = f1 + 8.0F;
      paramFloat2 = f2 + 8.0F;
    }
    paramBaseDanmaku.paintWidth = (paramFloat1 + getStrokeWidth());
    paramBaseDanmaku.paintHeight = paramFloat2;
  }

  public static void setFakeBoldText(boolean paramBoolean)
  {
    PAINT.setFakeBoldText(paramBoolean);
  }

  public static void setPaintStorkeWidth(float paramFloat)
  {
    PAINT.setStrokeWidth(paramFloat);
    STROKE_WIDTH = paramFloat;
  }

  public static void setProjectionConfig(float paramFloat1, float paramFloat2, int paramInt)
  {
    label39: int i;
    if ((sProjectionOffsetX != paramFloat1) || (sProjectionOffsetY != paramFloat2) || (sProjectionAlpha != paramInt))
    {
      if (paramFloat1 <= 1.0F)
        break label54;
      sProjectionOffsetX = paramFloat1;
      if (paramFloat2 <= 1.0F)
        break label59;
      sProjectionOffsetY = paramFloat2;
      if (paramInt >= 0)
        break label64;
      i = 0;
    }
    while (true)
    {
      sProjectionAlpha = i;
      return;
      label54: paramFloat1 = 1.0F;
      break;
      label59: paramFloat2 = 1.0F;
      break label39;
      label64: i = paramInt;
      if (paramInt > 255)
        i = 255;
    }
  }

  public static void setShadowRadius(float paramFloat)
  {
    SHADOW_RADIUS = paramFloat;
  }

  public static void setTypeFace(Typeface paramTypeface)
  {
    if (PAINT != null)
      PAINT.setTypeface(paramTypeface);
  }

  private void update(Canvas paramCanvas)
  {
    this.canvas = paramCanvas;
    if (paramCanvas != null)
    {
      this.width = paramCanvas.getWidth();
      this.height = paramCanvas.getHeight();
      if (this.mIsHardwareAccelerated)
      {
        this.mMaximumBitmapWidth = getMaximumBitmapWidth(paramCanvas);
        this.mMaximumBitmapHeight = getMaximumBitmapHeight(paramCanvas);
      }
    }
  }

  public int draw(BaseDanmaku paramBaseDanmaku)
  {
    boolean bool2 = false;
    int m = 0;
    float f1 = paramBaseDanmaku.getTop();
    float f2 = paramBaseDanmaku.getLeft();
    int k = m;
    DrawingCacheHolder localDrawingCacheHolder;
    Object localObject;
    if (this.canvas != null)
    {
      localDrawingCacheHolder = null;
      localObject = null;
      if (paramBaseDanmaku.getType() != 7)
        break label268;
      if (paramBaseDanmaku.getAlpha() == AlphaValue.TRANSPARENT)
        k = m;
    }
    else
    {
      return k;
    }
    int i;
    label92: int j;
    if ((paramBaseDanmaku.rotationZ != 0.0F) || (paramBaseDanmaku.rotationY != 0.0F))
    {
      saveCanvas(paramBaseDanmaku, this.canvas, f2, f1);
      i = 1;
      if (paramBaseDanmaku.getAlpha() != AlphaValue.MAX)
      {
        localObject = ALPHA_PAINT;
        ((Paint)localObject).setAlpha(paramBaseDanmaku.getAlpha());
      }
      j = i;
    }
    while (true)
    {
      if (localObject != null)
      {
        k = m;
        if (((Paint)localObject).getAlpha() == AlphaValue.TRANSPARENT)
          break;
      }
      boolean bool1 = bool2;
      if (paramBaseDanmaku.hasDrawingCache())
      {
        localDrawingCacheHolder = ((DrawingCache)paramBaseDanmaku.cache).get();
        bool1 = bool2;
        if (localDrawingCacheHolder != null)
          bool1 = localDrawingCacheHolder.draw(this.canvas, f2, f1, (Paint)localObject);
      }
      if (!bool1)
        if (localObject != null)
        {
          PAINT.setAlpha(((Paint)localObject).getAlpha());
          label208: drawDanmaku(this.context, paramBaseDanmaku, this.canvas, f2, f1, true);
        }
      for (i = 2; ; i = 1)
      {
        k = i;
        if (j == 0)
          break;
        restoreCanvas(this.canvas);
        return i;
        resetPaintAlpha(PAINT);
        break label208;
      }
      i = 0;
      break label92;
      label268: j = 0;
      localObject = localDrawingCacheHolder;
    }
  }

  public Context getContext()
  {
    return this.context;
  }

  public float getDensity()
  {
    return this.density;
  }

  public int getDensityDpi()
  {
    return this.densityDpi;
  }

  public Canvas getExtraData()
  {
    return this.canvas;
  }

  public int getHeight()
  {
    return this.height;
  }

  public int getMaximumCacheHeight()
  {
    return this.mMaximumBitmapHeight;
  }

  public int getMaximumCacheWidth()
  {
    return this.mMaximumBitmapWidth;
  }

  public float getScaledDensity()
  {
    return this.scaledDensity;
  }

  public int getSlopPixel()
  {
    return this.mSlopPixel;
  }

  public float getStrokeWidth()
  {
    if ((HAS_SHADOW) && (HAS_STROKE))
      return Math.max(SHADOW_RADIUS, STROKE_WIDTH);
    if (HAS_SHADOW)
      return SHADOW_RADIUS;
    if (HAS_STROKE)
      return STROKE_WIDTH;
    return 0.0F;
  }

  public int getWidth()
  {
    return this.width;
  }

  public boolean isHardwareAccelerated()
  {
    return this.mIsHardwareAccelerated;
  }

  public void measure(BaseDanmaku paramBaseDanmaku)
  {
    TextPaint localTextPaint = getPaint(paramBaseDanmaku);
    if (HAS_STROKE)
      applyPaintConfig(paramBaseDanmaku, localTextPaint, true);
    calcPaintWH(paramBaseDanmaku, localTextPaint);
    if (HAS_STROKE)
      applyPaintConfig(paramBaseDanmaku, localTextPaint, false);
  }

  public void resetSlopPixel(float paramFloat)
  {
    Math.max(this.density, this.scaledDensity);
    float f = Math.max(paramFloat, getWidth() / 682.0F) * 25.0F;
    this.mSlopPixel = ((int)f);
    if (paramFloat > 1.0F)
      this.mSlopPixel = ((int)(f * paramFloat));
  }

  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }

  public void setDensities(float paramFloat1, int paramInt, float paramFloat2)
  {
    this.density = paramFloat1;
    this.densityDpi = paramInt;
    this.scaledDensity = paramFloat2;
  }

  public void setExtraData(Canvas paramCanvas)
  {
    update(paramCanvas);
  }

  public void setHardwareAccelerated(boolean paramBoolean)
  {
    this.mIsHardwareAccelerated = paramBoolean;
  }

  public void setSize(int paramInt1, int paramInt2)
  {
    this.width = paramInt1;
    this.height = paramInt2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.android.AndroidDisplayer
 * JD-Core Version:    0.6.2
 */