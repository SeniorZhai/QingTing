package fm.qingting.qtradio.manager;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextPaint;

public enum SkinManager
{
  INSTANCE;

  private final float FONT_SIZE_22_PX = 22.0F;
  private final float LARGEFONTSIZE = 46.0F;
  private final float MIDDLEFONTSIZE = 30.0F;
  private final float NORMALFONTSIZE = 34.0F;
  private final float RECOMMENDFONTSIZE = 26.0F;
  private final float SMALLLABELFONTSIZE = 18.0F;
  private final int STANDARDWIDTH = 720;
  private final float SUBFONTSIZE = 26.0F;
  private final float TEENYTNYFONTSIZE = 22.0F;
  private final float TINYFONTSIZE = 24.0F;
  private DrawFilter mDrawfilter;
  private float mFontSize22px;
  private final TextPaint mHighlightTextPaint = new TextPaint();
  private final TextPaint mHighlightTextPaint2 = new TextPaint();
  private float mLargeTextSize;
  private boolean mLineColorSetted = false;
  private int mLineWidth = 1;
  private float mMiddleTextSize;
  private final TextPaint mNormalTextPaint = new TextPaint();
  private float mNormalTextSize;
  private float mRecommendTextSize;
  private float mSmallLabelTextSize;
  private final TextPaint mSubTextPaint = new TextPaint();
  private float mSubTextSize;
  private float mTeenyTinyTextSize;
  private float mTinyTextSize;
  private Path mTriangularPath;
  private final Paint mUpperLinePaint = new Paint();

  public static int getBackgroundColor()
  {
    return -1118482;
  }

  public static int getBackgroundColor_New()
  {
    return -328966;
  }

  public static int getBackgroundColor_item()
  {
    return -723463;
  }

  public static int getCardColor()
  {
    return -1;
  }

  public static int getDefaultPicColor()
  {
    return -9013642;
  }

  public static int getDividerColor()
  {
    return -2236963;
  }

  public static int getDividerColor_new()
  {
    return -4671302;
  }

  public static int getDownloadTipBgColor()
  {
    return getTextColorHighlight();
  }

  public static int getGenerabButtonDisableColor()
  {
    return getGeneralButtonColor();
  }

  public static int getGeneralButtonColor()
  {
    return -286331154;
  }

  public static int getGreenColor()
  {
    return -14365861;
  }

  public static int getGreenHighlightColor()
  {
    return -15418549;
  }

  public static SkinManager getInstance()
  {
    return INSTANCE;
  }

  public static int getItemHighlightMaskColor()
  {
    return 855638016;
  }

  public static int getItemHighlightMaskColor_new()
  {
    return -2302237;
  }

  public static int getLiveColor()
  {
    return -45747;
  }

  public static int getLoadMoreFooterColor()
  {
    return getTextColorSubInfo();
  }

  public static int getMiniplayerBgColor()
  {
    return -1;
  }

  public static int getNaviBgColor()
  {
    return -3061434;
  }

  public static int getNewPlaySubColor()
  {
    return -3355444;
  }

  public static int getNewPopBgColor()
  {
    return -986895;
  }

  public static int getNewPopTextColor()
  {
    return -16777216;
  }

  public static int getPopBgColor()
  {
    return -1579033;
  }

  public static int getPopButtonHighlightColor()
  {
    return getTextColorHighlight();
  }

  public static int getPopButtonNormalColor()
  {
    return -592138;
  }

  public static int getPopTextColor()
  {
    return -9145228;
  }

  public static int getSpecialButtonHighlightColor()
  {
    return -2804432;
  }

  public static int getTagBackgroundColor()
  {
    return -1381654;
  }

  public static int getTextColorDisable()
  {
    return -4671304;
  }

  public static int getTextColorHeat()
  {
    return -1541249;
  }

  public static int getTextColorHighlight()
  {
    return -2018256;
  }

  public static int getTextColorHighlight2()
  {
    return -3061434;
  }

  public static int getTextColorNormal()
  {
    return -14210768;
  }

  public static int getTextColorNormal_New()
  {
    return -11908534;
  }

  public static int getTextColorRecommend()
  {
    return -10461088;
  }

  public static int getTextColorSecondLevel()
  {
    return -7566196;
  }

  public static int getTextColorSubInfo()
  {
    return -8882056;
  }

  public static int getTextColorThirdLevel()
  {
    return -6974059;
  }

  public static int getTextColorWhite()
  {
    return -1;
  }

  public static Rect getTrimedBitmapRect(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (paramInt1 * j > i * paramInt2)
    {
      paramInt1 = i * paramInt2 / paramInt1;
      return new Rect(0, (j - paramInt1) / 2, i, (j + paramInt1) / 2);
    }
    paramInt1 = paramInt1 * j / paramInt2;
    return new Rect((i - paramInt1) / 2, 0, (i + paramInt1) / 2, j);
  }

  public static Rect getTrimedBitmapRectBaseBottom(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (paramInt1 * j > i * paramInt2)
      return new Rect(0, j - i * paramInt2 / paramInt1, i, j);
    paramInt1 = paramInt1 * j / paramInt2;
    return new Rect((i - paramInt1) / 2, 0, (i + paramInt1) / 2, j);
  }

  public static Rect getTrimedBitmapRectBaseTop(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    if (paramInt1 * j > i * paramInt2)
      return new Rect(0, 0, i, i * paramInt2 / paramInt1);
    paramInt1 = paramInt1 * j / paramInt2;
    return new Rect((i - paramInt1) / 2, 0, (i + paramInt1) / 2, j);
  }

  public static int getUploadPageElementColor()
  {
    return Color.parseColor("#62e0d7");
  }

  public void calculateFontSize(int paramInt)
  {
    this.mNormalTextSize = (34.0F * paramInt / 720.0F);
    this.mSubTextSize = (paramInt * 26.0F / 720.0F);
    this.mMiddleTextSize = (30.0F * paramInt / 720.0F);
    this.mRecommendTextSize = (paramInt * 26.0F / 720.0F);
    this.mTinyTextSize = (24.0F * paramInt / 720.0F);
    this.mLargeTextSize = (46.0F * paramInt / 720.0F);
    this.mTeenyTinyTextSize = (paramInt * 22.0F / 720.0F);
    this.mSmallLabelTextSize = (18.0F * paramInt / 720.0F);
    this.mFontSize22px = (paramInt * 22.0F / 720.0F);
    this.mNormalTextPaint.setTextSize(this.mNormalTextSize);
    this.mHighlightTextPaint.setTextSize(this.mNormalTextSize);
    this.mHighlightTextPaint2.setTextSize(this.mNormalTextSize);
    this.mSubTextPaint.setTextSize(this.mSubTextSize);
    this.mNormalTextPaint.setColor(getTextColorNormal());
    this.mHighlightTextPaint.setColor(getTextColorHighlight());
    this.mHighlightTextPaint2.setColor(getTextColorHighlight2());
    this.mSubTextPaint.setColor(getTextColorSubInfo());
  }

  public void drawHorizontalLine(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!this.mLineColorSetted)
    {
      this.mUpperLinePaint.setColor(getDividerColor());
      this.mLineColorSetted = true;
    }
    if (this.mLineWidth != paramInt4)
    {
      this.mLineWidth = paramInt4;
      this.mUpperLinePaint.setStrokeWidth(this.mLineWidth);
    }
    float f1 = paramInt1;
    float f2 = paramInt3;
    float f3 = this.mLineWidth;
    float f4 = paramInt2;
    float f5 = paramInt3;
    paramCanvas.drawLine(f1, f3 * 0.5F + f2, f4, this.mLineWidth * 0.5F + f5, this.mUpperLinePaint);
  }

  public void drawVerticalLine(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!this.mLineColorSetted)
    {
      this.mUpperLinePaint.setColor(getDividerColor());
      this.mLineColorSetted = true;
    }
    if (this.mLineWidth != paramInt4)
    {
      this.mLineWidth = paramInt4;
      this.mUpperLinePaint.setStrokeWidth(this.mLineWidth);
    }
    float f1 = paramInt3;
    float f2 = this.mLineWidth;
    float f3 = paramInt1;
    float f4 = paramInt3;
    paramCanvas.drawLine(f2 * 0.5F + f1, f3, this.mLineWidth * 0.5F + f4, paramInt2, this.mUpperLinePaint);
  }

  public DrawFilter getDrawFilter()
  {
    if (this.mDrawfilter == null)
      this.mDrawfilter = new PaintFlagsDrawFilter(0, 67);
    return this.mDrawfilter;
  }

  public float getFontSize22px()
  {
    return this.mFontSize22px;
  }

  public TextPaint getHighlightTextPaint()
  {
    return this.mHighlightTextPaint;
  }

  public float getLargeTextSize()
  {
    return this.mLargeTextSize;
  }

  public Path getLowerTriangularPath(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    if (this.mTriangularPath == null)
      this.mTriangularPath = new Path();
    this.mTriangularPath.rewind();
    this.mTriangularPath.moveTo(paramFloat1 - paramInt1 / 2.0F, paramFloat2 - paramInt2);
    this.mTriangularPath.lineTo(paramInt1 / 2.0F + paramFloat1, paramFloat2 - paramInt2);
    this.mTriangularPath.lineTo(paramFloat1, paramFloat2);
    this.mTriangularPath.lineTo(paramFloat1 - paramInt1 / 2.0F, paramFloat2 - paramInt2);
    return this.mTriangularPath;
  }

  public float getMiddleTextSize()
  {
    return this.mMiddleTextSize;
  }

  public TextPaint getNormalTextPaint()
  {
    return this.mNormalTextPaint;
  }

  public float getNormalTextSize()
  {
    return this.mNormalTextSize;
  }

  public float getRecommendTextSize()
  {
    return this.mRecommendTextSize;
  }

  public float getSmallLabelTextSize()
  {
    return this.mSmallLabelTextSize;
  }

  public TextPaint getSubTextPaint()
  {
    return this.mSubTextPaint;
  }

  public float getSubTextSize()
  {
    return this.mSubTextSize;
  }

  public float getTeenyTinyTextSize()
  {
    return this.mTeenyTinyTextSize;
  }

  public float getTinyTextSize()
  {
    return this.mTinyTextSize;
  }

  public Path getUpperTriangularPath(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2)
  {
    if (this.mTriangularPath == null)
      this.mTriangularPath = new Path();
    this.mTriangularPath.rewind();
    this.mTriangularPath.moveTo(paramFloat1 - paramInt1 / 2.0F, paramInt2 + paramFloat2);
    this.mTriangularPath.lineTo(paramInt1 / 2.0F + paramFloat1, paramInt2 + paramFloat2);
    this.mTriangularPath.lineTo(paramFloat1, paramFloat2);
    this.mTriangularPath.lineTo(paramFloat1 - paramInt1 / 2.0F, paramInt2 + paramFloat2);
    return this.mTriangularPath;
  }

  public TextPaint getmHighlightTextPaint2()
  {
    return this.mHighlightTextPaint2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.SkinManager
 * JD-Core Version:    0.6.2
 */