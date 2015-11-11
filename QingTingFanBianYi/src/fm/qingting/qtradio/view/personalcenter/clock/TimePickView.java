package fm.qingting.qtradio.view.personalcenter.clock;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.DrawFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.animation.LinearInterpolator;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Quad.EaseOut;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.FromType;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimePickView extends ViewImpl
  implements IMotionHandler
{
  private final int ARC = 10;
  private int arc = 10;
  private final ViewLayout channelLayout = ViewLayout.createViewLayoutWithBoundsLT(90, 67, 360, 496, 210, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private DrawFilter filter = SkinManager.getInstance().getDrawFilter();
  private final ViewLayout glassLayout = ViewLayout.createViewLayoutWithBoundsLT(360, 105, 360, 496, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(360, 105, 360, 496, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(360, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private AnimationState mAnimationState;
  private Object mAnimator;
  private float mCenterIndex = 0.0F;
  private int mCenterY = 0;
  float[] mDstR = new float[8];
  private RectF mDstRect = new RectF();
  private Paint mGlassPaint = new Paint();
  private Rect mGlassRect = new Rect();
  private boolean mInTouchMode = false;
  private float mInitPosition = 0.0F;
  private float mLastMotionY = 0.0F;
  private long mLastMovementTime = 0L;
  private float mLastScrollPosition = 0.0F;
  private float mLastTwoMoveEventDistance = 0.0F;
  private long mLastTwoMoveEventInterval = 0L;
  private Paint mLinePaint = new Paint();
  private Matrix mMatrix = new Matrix();
  private float mMinimumFlingVelocity;
  private TextPaint mNormalTextPaint = new TextPaint();
  private Paint mPaint = new Paint();
  private float mScrollPosition = 0.0F;
  private int mSelectedIndex = 0;
  private Paint mSelectedTextPaint = new Paint();
  float[] mSrcR = new float[8];
  private Rect mSrcRect = new Rect();
  private TimeType mTimeType = TimeType.Minute;
  private float mTouchedIndex = 0.0F;
  private Paint mUnitPaint = new Paint();
  private long mUpEventMoveEventInterval = 0L;
  private MotionController motionController;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(360, 496, 360, 496, 0, 0, ViewLayout.FILL);
  private Rect textBound = new Rect();
  private Map<Integer, SoftReference<Bitmap>> textCaches = new HashMap();

  public TimePickView(Context paramContext)
  {
    super(paramContext);
    this.mGlassPaint.setColor(-13287874);
    this.mUnitPaint.setColor(SkinManager.getTextColorHighlight());
    this.mLinePaint.setColor(654311423);
    this.mNormalTextPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mNormalTextPaint.setAntiAlias(true);
    ColorMatrixColorFilter localColorMatrixColorFilter = new ColorMatrixColorFilter(new float[] { 0.0F, 0.0F, 0.0F, 0.0F, 245.0F, 0.0F, 0.0F, 0.0F, 0.0F, 53.0F, 0.0F, 0.0F, 0.0F, 0.0F, 45.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F });
    this.mSelectedTextPaint.setColorFilter(localColorMatrixColorFilter);
    init();
    this.mMinimumFlingVelocity = ViewConfiguration.get(paramContext).getScaledMinimumFlingVelocity();
  }

  @TargetApi(11)
  private void cancelAnimation()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ((ValueAnimator)this.mAnimator).cancel();
      return;
    }
    FrameTween.cancel(this);
  }

  private void drawChannels(Canvas paramCanvas)
  {
    int n = paramCanvas.save();
    int m = Math.round(this.mCenterIndex);
    float f1 = this.mCenterY;
    float f2 = this.mCenterIndex;
    float f3 = m;
    float f4 = this.itemLayout.height;
    int j = 0;
    f1 -= (f2 - f3) * f4;
    int i = m;
    while (f1 > 0.0F)
    {
      f2 = (float)(1.0D - Math.pow((f1 - this.mCenterY) / this.standardLayout.height, 2.0D) * 2.0D);
      f3 = this.itemLayout.height * f2 / 2.0F;
      int k = j;
      if (j == 0)
      {
        f3 = this.itemLayout.height * f2 / 2.0F;
        k = 1;
      }
      f1 -= drawItemBitmapBottom(paramCanvas, i, f2, f1);
      i -= 1;
      j = k;
    }
    i = m + 1;
    f1 = this.mCenterY - (this.mCenterIndex - m) * this.itemLayout.height + this.itemLayout.height;
    while (f1 < this.standardLayout.height)
    {
      f2 = (float)(1.0D - Math.pow((f1 - this.mCenterY) / this.standardLayout.height, 2.0D) * 2.0D);
      f3 = this.itemLayout.height * f2 / 2.0F;
      f1 += drawItemBitmapTop(paramCanvas, i, f2, f1);
      i += 1;
    }
    paramCanvas.restoreToCount(n);
  }

  private void drawGlass(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.standardLayout.width, this.mGlassRect.top - this.lineLayout.height, this.lineLayout.height);
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.standardLayout.width, this.mGlassRect.bottom, this.lineLayout.height);
    String str = this.mTimeType.getName();
    this.mUnitPaint.getTextBounds(str, 0, str.length(), this.textBound);
    if (this.mTimeType == TimeType.Hour);
    for (int i = this.channelLayout.leftMargin + this.channelLayout.width; ; i = this.standardLayout.width - this.channelLayout.leftMargin)
    {
      paramCanvas.drawText(str, i, this.mCenterY + this.textBound.height() / 2, this.mUnitPaint);
      return;
    }
  }

  private float drawItemBitmapBottom(Canvas paramCanvas, int paramInt, float paramFloat1, float paramFloat2)
  {
    float f1 = this.itemLayout.height * paramFloat1;
    Bitmap localBitmap = getTextCache(paramInt);
    if (localBitmap == null)
      return f1;
    float f2 = localBitmap.getHeight() * paramFloat1;
    float f3 = this.mCenterY - this.glassLayout.height / 2.0F;
    float f4 = this.mCenterY + this.glassLayout.height / 2.0F;
    if ((f2 / 2.0F + paramFloat2 > f4) && (paramFloat2 - f2 / 2.0F < f4))
    {
      drawReflectionTextBottom(paramCanvas, localBitmap, f4 - paramFloat2 + f2 / 2.0F, f2, paramFloat2);
      return f1;
    }
    if ((f2 / 2.0F + paramFloat2 > f3) && (paramFloat2 - f2 / 2.0F < f3))
    {
      drawReflectionTextTop(paramCanvas, localBitmap, f3 - paramFloat2 + f2 / 2.0F, f2, paramFloat2);
      return f1;
    }
    if ((paramFloat2 - f2 / 2.0F > f3) && (f2 / 2.0F + paramFloat2 < f4))
    {
      drawSelectedText(paramCanvas, localBitmap, this.itemLayout.height * paramFloat1, paramFloat2);
      return f1;
    }
    paramInt = getLeftMargin();
    setArrayValue(this.mSrcR, new float[] { 0.0F, 0.0F, localBitmap.getWidth(), 0.0F, localBitmap.getWidth(), localBitmap.getHeight(), 0.0F, localBitmap.getHeight() });
    setArrayValue(this.mDstR, new float[] { paramInt + this.arc * 5 * (1.0F - paramFloat1), paramFloat2 - f2 / 2.0F, localBitmap.getWidth() + paramInt - this.arc * 5 * (1.0F - paramFloat1), paramFloat2 - f2 / 2.0F, localBitmap.getWidth() + paramInt, f2 / 2.0F + paramFloat2, paramInt, localBitmap.getHeight() * paramFloat1 / 2.0F + paramFloat2 });
    this.mMatrix.setPolyToPoly(this.mSrcR, 0, this.mDstR, 0, this.mSrcR.length >> 1);
    try
    {
      paramCanvas.drawBitmap(localBitmap, this.mMatrix, this.mPaint);
      return f1;
    }
    catch (OutOfMemoryError paramCanvas)
    {
      while (true)
        paramCanvas.printStackTrace();
    }
  }

  private float drawItemBitmapTop(Canvas paramCanvas, int paramInt, float paramFloat1, float paramFloat2)
  {
    float f1 = this.itemLayout.height * paramFloat1;
    Bitmap localBitmap = getTextCache(paramInt);
    if (localBitmap == null)
      return f1;
    float f2 = localBitmap.getHeight() * paramFloat1;
    float f3 = this.mCenterY + this.glassLayout.height / 2.0F;
    if ((f2 / 2.0F + paramFloat2 > f3) && (paramFloat2 - f2 / 2.0F < f3))
    {
      drawReflectionTextBottom(paramCanvas, localBitmap, f3 - paramFloat2 + f2 / 2.0F, f2, paramFloat2);
      return f1;
    }
    paramInt = getLeftMargin();
    setArrayValue(this.mSrcR, new float[] { 0.0F, 0.0F, localBitmap.getWidth(), 0.0F, localBitmap.getWidth(), localBitmap.getHeight(), 0.0F, localBitmap.getHeight() });
    setArrayValue(this.mDstR, new float[] { paramInt, paramFloat2 - f2 / 2.0F, localBitmap.getWidth() + paramInt, paramFloat2 - f2 / 2.0F, localBitmap.getWidth() + paramInt - this.arc * 5 * (1.0F - paramFloat1), f2 / 2.0F + paramFloat2, paramInt + this.arc * 5 * (1.0F - paramFloat1), f2 / 2.0F + paramFloat2 });
    this.mMatrix.setPolyToPoly(this.mSrcR, 0, this.mDstR, 0, this.mSrcR.length >> 1);
    try
    {
      paramCanvas.drawBitmap(localBitmap, this.mMatrix, this.mPaint);
      return f1;
    }
    catch (OutOfMemoryError paramCanvas)
    {
      while (true)
        paramCanvas.printStackTrace();
    }
  }

  private float drawReflectionTextBottom(Canvas paramCanvas, Bitmap paramBitmap, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    int i = getLeftMargin();
    this.mSrcRect.set(0, 0, paramBitmap.getWidth(), (int)(paramBitmap.getHeight() * paramFloat1 / paramFloat2));
    this.mDstRect.set(i, paramFloat3 - paramFloat2 / 2.0F, paramBitmap.getWidth() + i, paramFloat3 - paramFloat2 / 2.0F + paramFloat1);
    paramCanvas.drawBitmap(paramBitmap, this.mSrcRect, this.mDstRect, this.mSelectedTextPaint);
    this.mSrcRect.set(0, (int)(paramBitmap.getHeight() * paramFloat1 / paramFloat2), paramBitmap.getWidth(), paramBitmap.getHeight());
    this.mDstRect.set(i, paramFloat3 - paramFloat2 / 2.0F + paramFloat1, i + paramBitmap.getWidth(), paramFloat2 / 2.0F + paramFloat3);
    paramCanvas.drawBitmap(paramBitmap, this.mSrcRect, this.mDstRect, this.mPaint);
    return paramFloat2;
  }

  private float drawReflectionTextTop(Canvas paramCanvas, Bitmap paramBitmap, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    int i = getLeftMargin();
    this.mSrcRect.set(0, 0, paramBitmap.getWidth(), (int)(paramBitmap.getHeight() * paramFloat1 / paramFloat2));
    this.mDstRect.set(i, paramFloat3 - paramFloat2 / 2.0F, paramBitmap.getWidth() + i, paramFloat3 - paramFloat2 / 2.0F + paramFloat1);
    paramCanvas.drawBitmap(paramBitmap, this.mSrcRect, this.mDstRect, this.mPaint);
    this.mSrcRect.set(0, (int)(paramBitmap.getHeight() * paramFloat1 / paramFloat2), paramBitmap.getWidth(), paramBitmap.getHeight());
    this.mDstRect.set(i, paramFloat3 - paramFloat2 / 2.0F + paramFloat1, i + paramBitmap.getWidth(), paramFloat2 / 2.0F + paramFloat3);
    paramCanvas.drawBitmap(paramBitmap, this.mSrcRect, this.mDstRect, this.mSelectedTextPaint);
    return paramFloat2;
  }

  private float drawSelectedText(Canvas paramCanvas, Bitmap paramBitmap, float paramFloat1, float paramFloat2)
  {
    int i = getLeftMargin();
    this.mSrcRect.set(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    this.mDstRect.set(i, paramFloat2 - paramBitmap.getHeight() / 2.0F, i + paramBitmap.getWidth(), paramBitmap.getHeight() / 2.0F + paramFloat2);
    paramCanvas.drawBitmap(paramBitmap, this.mSrcRect, this.mDstRect, this.mSelectedTextPaint);
    return paramFloat1;
  }

  private void generateRect()
  {
    this.mGlassRect = new Rect(0, this.mCenterY - this.glassLayout.height / 2, this.glassLayout.width, this.mCenterY + this.glassLayout.height / 2);
  }

  private int getAnimationDuration(float paramFloat)
  {
    return (int)(200.0D * Math.sqrt(paramFloat));
  }

  private float getIndex()
  {
    this.mCenterIndex %= this.mTimeType.getTotal();
    if (this.mCenterIndex < 0.0F)
      this.mCenterIndex += this.mTimeType.getTotal();
    return this.mCenterIndex;
  }

  private int getLeftMargin()
  {
    if (this.mTimeType == TimeType.Hour)
      return this.channelLayout.leftMargin;
    return this.standardLayout.width - this.channelLayout.leftMargin - this.channelLayout.width;
  }

  private Bitmap getTextCache(int paramInt)
  {
    paramInt %= this.mTimeType.getTotal();
    if (paramInt < 0)
      paramInt += this.mTimeType.getTotal();
    while (true)
    {
      int i = this.channelLayout.width;
      if ((this.textCaches.get(Integer.valueOf(paramInt)) != null) && (((SoftReference)this.textCaches.get(Integer.valueOf(paramInt))).get() != null))
        return (Bitmap)((SoftReference)this.textCaches.get(Integer.valueOf(paramInt))).get();
      String str = String.format("%02d", new Object[] { Integer.valueOf(paramInt) });
      this.mNormalTextPaint.getTextBounds(str, 0, str.length(), this.textBound);
      if ((this.textBound.width() <= 0) || (this.textBound.height() <= 0))
        return null;
      try
      {
        Bitmap localBitmap = Bitmap.createBitmap(i, this.channelLayout.height, Bitmap.Config.ARGB_4444);
        new Canvas(localBitmap).drawText(str, (i - this.textBound.width()) / 2.0F, (this.channelLayout.height - this.textBound.top - this.textBound.bottom) / 2.0F, this.mNormalTextPaint);
        this.textCaches.put(Integer.valueOf(paramInt), new SoftReference(localBitmap));
        return localBitmap;
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
        return null;
      }
    }
  }

  @TargetApi(11)
  private void init()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.mAnimator = new ValueAnimator();
      ((ValueAnimator)this.mAnimator).setInterpolator(new LinearInterpolator());
      ((ValueAnimator)this.mAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          TimePickView.this.setIndex(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      ((ValueAnimator)this.mAnimator).addListener(new Animator.AnimatorListener()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
          TimePickView.access$102(TimePickView.this, TimePickView.AnimationState.STOP);
        }

        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          TimePickView.access$102(TimePickView.this, TimePickView.AnimationState.STOP);
        }

        public void onAnimationRepeat(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
        }
      });
      return;
    }
    this.motionController = new MotionController(this);
  }

  private void setArrayValue(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
  {
    if ((paramArrayOfFloat2 == null) || (paramArrayOfFloat2.length < paramArrayOfFloat1.length));
    while (true)
    {
      return;
      int i = 0;
      while (i < paramArrayOfFloat1.length)
      {
        paramArrayOfFloat1[i] = paramArrayOfFloat2[i];
        i += 1;
      }
    }
  }

  private void setIndex(float paramFloat)
  {
    this.mScrollPosition = (this.mLastScrollPosition + (this.mTouchedIndex - paramFloat) * this.itemLayout.height);
    int i = this.mTimeType.getTotal();
    this.mCenterIndex = (paramFloat % i);
    if (this.mCenterIndex < 0.0F)
    {
      paramFloat = this.mCenterIndex;
      this.mCenterIndex = (i + paramFloat);
    }
    invalidate();
  }

  @TargetApi(11)
  private void startAnimationTo(float paramFloat, boolean paramBoolean)
  {
    this.mAnimationState = AnimationState.RUNNING;
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      int i = getAnimationDuration(Math.abs(this.mCenterIndex - paramFloat));
      ((ValueAnimator)this.mAnimator).setFloatValues(new float[] { this.mCenterIndex, paramFloat });
      ((ValueAnimator)this.mAnimator).setDuration(i);
      ((ValueAnimator)this.mAnimator).start();
      return;
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new TweenProperty("position", this.mCenterIndex, paramFloat, 10.0F, new Quad.EaseOut()));
    FrameTween.to(this.motionController, this.motionController, localArrayList, FrameTween.SyncType.ASYNC);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("selectedIndex"))
    {
      int i = this.mSelectedIndex;
      int k = this.mTimeType.getTotal();
      int j = i % k;
      i = j;
      if (j < 0)
        i = j + k;
      return Integer.valueOf(i);
    }
    return null;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(this.filter);
    paramCanvas.save();
    drawGlass(paramCanvas);
    drawChannels(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.glassLayout.scaleToBounds(this.standardLayout);
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.channelLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mNormalTextPaint.setTextSize(this.channelLayout.height * 0.7F);
    this.mUnitPaint.setTextSize(this.channelLayout.height * 0.4F);
    this.mCenterY = (this.standardLayout.height / 2);
    this.mLinePaint.setStrokeWidth(this.lineLayout.height);
    generateRect();
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onMotionCancel(MotionController paramMotionController)
  {
    this.mAnimationState = AnimationState.STOP;
  }

  public void onMotionComplete(MotionController paramMotionController)
  {
    this.mAnimationState = AnimationState.STOP;
  }

  public void onMotionProgress(MotionController paramMotionController, float paramFloat1, float paramFloat2)
  {
    setIndex(paramFloat1);
  }

  public void onMotionStart(MotionController paramMotionController)
  {
  }

  public void onTargetChange(MotionController paramMotionController, float paramFloat)
  {
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() != 0) && (!this.mInTouchMode));
    do
    {
      return true;
      switch (paramMotionEvent.getAction())
      {
      default:
        return true;
      case 0:
        if (this.mAnimationState == AnimationState.RUNNING)
          cancelAnimation();
        this.mInTouchMode = true;
        this.mLastScrollPosition = this.mScrollPosition;
        this.mTouchedIndex = this.mCenterIndex;
        this.mInitPosition = paramMotionEvent.getY();
        this.mLastMovementTime = paramMotionEvent.getEventTime();
        this.mLastMotionY = paramMotionEvent.getY();
        return true;
      case 2:
      case 1:
      case 3:
      }
    }
    while (!this.mInTouchMode);
    paramMotionEvent.getX();
    paramMotionEvent.getY();
    long l = paramMotionEvent.getEventTime();
    this.mLastTwoMoveEventInterval = (l - this.mLastMovementTime);
    this.mLastMovementTime = l;
    float f = paramMotionEvent.getY();
    this.mLastTwoMoveEventDistance = (f - this.mLastMotionY);
    this.mLastMotionY = f;
    f = this.mTouchedIndex - (paramMotionEvent.getY() - this.mInitPosition) / this.itemLayout.height;
    setIndex(f);
    this.mSelectedIndex = Math.round(f);
    return true;
    this.mUpEventMoveEventInterval = (paramMotionEvent.getEventTime() - this.mLastMovementTime);
    if ((this.mUpEventMoveEventInterval < ViewConfiguration.getTapTimeout()) && (this.mLastTwoMoveEventInterval > 0L))
    {
      f = this.mLastTwoMoveEventDistance * 1000.0F / (float)this.mLastTwoMoveEventInterval;
      if (Math.abs(f) > this.mMinimumFlingVelocity)
      {
        int i = (int)Math.abs(f / this.standardLayout.height);
        if (f > 0.0F)
        {
          f = Math.round(getIndex() - i);
          this.mSelectedIndex = ((int)f);
          paramMotionEvent = (Node)getValue("selectedChannel", null);
          if (paramMotionEvent != null)
            PlayerAgent.getInstance().setSource(paramMotionEvent);
          InfoManager.getInstance().root().setFromType(RootNode.FromType.WHEEL);
          startAnimationTo(f, true);
        }
        while (true)
        {
          this.mInTouchMode = false;
          return true;
          if (f < 0.0F)
          {
            f = Math.round(getIndex() + i);
            this.mSelectedIndex = ((int)f);
            paramMotionEvent = (Node)getValue("selectedChannel", null);
            if (paramMotionEvent != null)
              PlayerAgent.getInstance().setSource(paramMotionEvent);
            InfoManager.getInstance().root().setFromType(RootNode.FromType.WHEEL);
            startAnimationTo(f, true);
          }
        }
      }
    }
    f = getIndex();
    if (f == Math.round(f))
    {
      this.mAnimationState = AnimationState.STOP;
      return true;
    }
    f = Math.round(f);
    this.mSelectedIndex = ((int)f);
    startAnimationTo(f, true);
    this.mInTouchMode = false;
    return true;
    invalidate();
    return true;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setTimeType"))
    {
      this.mTimeType = ((TimeType)paramObject);
      switch (3.$SwitchMap$fm$qingting$qtradio$view$personalcenter$clock$TimePickView$TimeType[this.mTimeType.ordinal()])
      {
      default:
      case 1:
      case 2:
      }
    }
    while (!paramString.equalsIgnoreCase("setTime"))
    {
      return;
      this.mCenterIndex = 7.0F;
      return;
      this.mCenterIndex = 0.0F;
      return;
    }
    this.mSelectedIndex = ((Integer)paramObject).intValue();
    this.mCenterIndex = this.mSelectedIndex;
    invalidate();
  }

  private static enum AnimationState
  {
    RUNNING, STOP;
  }

  public static enum TimeType
  {
    Hour, Minute;

    public String getName()
    {
      String str = "时";
      if (this == Minute)
        str = "分";
      return str;
    }

    public int getTotal()
    {
      int i = 24;
      if (this == Minute)
        i = 60;
      return i;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.TimePickView
 * JD-Core Version:    0.6.2
 */