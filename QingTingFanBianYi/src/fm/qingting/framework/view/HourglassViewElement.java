package fm.qingting.framework.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.animation.AccelerateInterpolator;

public class HourglassViewElement extends ViewElement
{
  private final boolean COUTINIOUS = false;
  private final float FLOWLIMIT = 0.1F;
  private final int LENGTH = 50;
  private float mDegree = 0.0F;
  private final RectF mDstRectF = new RectF();
  private Paint mFramePaint = new Paint();
  private Path mFramePath = new Path();
  private Handler mLoadHandler = new Handler();
  private Runnable mLoadRunnable = new Runnable()
  {
    public void run()
    {
      HourglassViewElement localHourglassViewElement = HourglassViewElement.this;
      localHourglassViewElement.mDegree += 10.0F;
      if (HourglassViewElement.this.mDegree > 360.0F)
        HourglassViewElement.this.mDegree = 0.0F;
      HourglassViewElement.this.invalidateAll();
      HourglassViewElement.this.mLoadHandler.postDelayed(HourglassViewElement.this.mLoadRunnable, 50L);
    }
  };
  private Path mLowerPath = new Path();
  private float mProcess = 0.0F;
  private Object mProcessAnimator;
  private final Rect mRect = new Rect();
  private Object mRotateAnimator;
  private Paint mSandPaint = new Paint();

  public HourglassViewElement(Context paramContext)
  {
    super(paramContext);
    this.mSandPaint.setColor(-4980740);
    this.mFramePaint.setColor(-1179648);
    this.mFramePaint.setStyle(Paint.Style.STROKE);
    this.mFramePaint.setStrokeWidth(2.0F);
    init();
  }

  private void changeProcess(float paramFloat)
  {
    this.mProcess = paramFloat;
    invalidateElement();
  }

  private void drawHourglass(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    if (this.mDegree > 0.0F)
      paramCanvas.rotate(this.mDegree, getLeftMargin() + getWidth() / 2, getTopMargin() + getHeight() / 2);
    paramCanvas.clipPath(this.mFramePath);
    float f1 = this.mRect.left;
    float f2 = this.mRect.top;
    paramCanvas.drawRect(f1, this.mProcess * this.mRect.height() / 2.0F + f2, this.mRect.right, this.mRect.centerY(), this.mSandPaint);
    f1 = this.mRect.bottom;
    if (this.mProcess < 0.1F)
      f1 = this.mRect.centerY() + this.mProcess / 0.1F * this.mRect.height() / 2.0F;
    paramCanvas.drawRect(this.mRect.centerX() - 2, this.mRect.centerY(), this.mRect.centerX() + 2, f1, this.mSandPaint);
    int j = paramCanvas.save();
    paramCanvas.translate(0.0F, (1.0F - this.mProcess) * this.mRect.height() / 2.0F);
    paramCanvas.drawPath(this.mLowerPath, this.mSandPaint);
    paramCanvas.restoreToCount(j);
    paramCanvas.restoreToCount(i);
  }

  @TargetApi(11)
  private void init()
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      this.mProcessAnimator = new ValueAnimator();
      ((ValueAnimator)this.mProcessAnimator).setDuration(1000L);
      ((ValueAnimator)this.mProcessAnimator).setInterpolator(new AccelerateInterpolator());
      ((ValueAnimator)this.mProcessAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          HourglassViewElement.this.changeProcess(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      ((ValueAnimator)this.mProcessAnimator).addListener(new Animator.AnimatorListener()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          ((ValueAnimator)HourglassViewElement.this.mRotateAnimator).start();
        }

        public void onAnimationRepeat(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
        }
      });
      this.mRotateAnimator = new ValueAnimator();
      ((ValueAnimator)this.mRotateAnimator).setFloatValues(new float[] { 0.0F, 180.0F });
      ((ValueAnimator)this.mRotateAnimator).setStartDelay(50L);
      ((ValueAnimator)this.mRotateAnimator).setDuration(200L);
      ((ValueAnimator)this.mRotateAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          HourglassViewElement.this.rotate(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
      ((ValueAnimator)this.mRotateAnimator).addListener(new Animator.AnimatorListener()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          HourglassViewElement.this.mProcess = 0.0F;
          HourglassViewElement.this.mDegree = 0.0F;
          HourglassViewElement.this.invalidateElement();
          ((ValueAnimator)HourglassViewElement.this.mProcessAnimator).start();
        }

        public void onAnimationRepeat(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
        }
      });
    }
  }

  private void rotate(float paramFloat)
  {
    this.mDegree = paramFloat;
    if (this.mDegree > 180.0F)
      this.mDegree -= 180.0F;
    if (this.mDegree < 0.0F)
      this.mDegree += 180.0F;
    invalidateElement();
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    paramCanvas.save();
    drawHourglass(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mRect.set(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mDstRectF.set(paramInt1, paramInt2, paramInt3, paramInt4);
    float f1 = (paramInt1 + paramInt3) / 2;
    float f2 = (paramInt2 + paramInt4) / 2;
    this.mFramePath.rewind();
    this.mFramePath.moveTo(paramInt1, paramInt2);
    this.mFramePath.lineTo(paramInt3, paramInt2);
    this.mFramePath.lineTo(5.0F / 2.0F + f1, f2);
    this.mFramePath.lineTo(paramInt3, paramInt4);
    this.mFramePath.lineTo(paramInt1, paramInt4);
    this.mFramePath.lineTo(f1 - 5.0F / 2.0F, f2);
    this.mFramePath.close();
    this.mLowerPath.reset();
    this.mLowerPath.moveTo(paramInt1, paramInt4);
    this.mLowerPath.lineTo(paramInt3, paramInt4);
    this.mLowerPath.lineTo(f1, f2);
    this.mLowerPath.lineTo(paramInt1, paramInt4);
  }

  @TargetApi(11)
  public void startLoading()
  {
    setVisible(0);
    ((ValueAnimator)this.mProcessAnimator).setFloatValues(new float[] { 0.0F, 1.0F });
    ((ValueAnimator)this.mProcessAnimator).setStartDelay(100L);
    ((ValueAnimator)this.mProcessAnimator).start();
  }

  public void stopLoading()
  {
    setVisible(4);
    this.mLoadHandler.removeCallbacks(this.mLoadRunnable);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.HourglassViewElement
 * JD-Core Version:    0.6.2
 */