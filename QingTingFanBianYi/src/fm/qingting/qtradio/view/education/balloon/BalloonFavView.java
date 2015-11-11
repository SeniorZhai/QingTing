package fm.qingting.qtradio.view.education.balloon;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View.MeasureSpec;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;

public class BalloonFavView extends ViewImpl
{
  private final ViewLayout bgLayout = this.standardLayout.createChildLT(100, 30, 20, 4, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint mBgPaint = new Paint();
  private RectF mBgRectF = new RectF();
  private Object mFallAnimator;
  private float mOffset = 0.0F;
  private Object mRiseAnimator;
  private Rect mTextBound = new Rect();
  private String mTip = "好听就收藏";
  private Paint mTipPaint = new Paint();
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(100, 50, 100, 50, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout triangularLayout = this.standardLayout.createChildLT(10, 5, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public BalloonFavView(Context paramContext)
  {
    super(paramContext);
    this.mTipPaint.setColor(-16777216);
    this.mBgPaint.setColor(-14025);
    this.mBgPaint.setStyle(Paint.Style.FILL);
    init();
    startUpAndDown();
  }

  private void drawBg(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    paramCanvas.translate(0.0F, this.mOffset * (this.standardLayout.height - this.bgLayout.height - this.triangularLayout.height));
    paramCanvas.drawRoundRect(this.mBgRectF, this.bgLayout.topMargin, this.bgLayout.topMargin, this.mBgPaint);
    paramCanvas.drawPath(SkinManager.getInstance().getLowerTriangularPath(this.bgLayout.width / 2 + this.bgLayout.leftMargin, this.bgLayout.height + this.triangularLayout.height, this.triangularLayout.width, this.triangularLayout.height), this.mBgPaint);
    this.mTipPaint.getTextBounds(this.mTip, 0, this.mTip.length(), this.mTextBound);
    paramCanvas.drawText(this.mTip, (this.bgLayout.width - this.mTextBound.width()) / 2, (this.bgLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTipPaint);
    paramCanvas.restoreToCount(i);
  }

  @TargetApi(11)
  private void init()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      this.mFallAnimator = new ValueAnimator();
      ((ValueAnimator)this.mFallAnimator).setFloatValues(new float[] { 0.0F, 1.0F });
      ((ValueAnimator)this.mFallAnimator).setDuration(500L);
      ((ValueAnimator)this.mFallAnimator).setInterpolator(new AccelerateInterpolator());
      ((ValueAnimator)this.mFallAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          BalloonFavView.access$002(BalloonFavView.this, ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
          BalloonFavView.this.invalidate();
        }
      });
      ((ValueAnimator)this.mFallAnimator).addListener(new Animator.AnimatorListener()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          ((ValueAnimator)BalloonFavView.this.mRiseAnimator).start();
        }

        public void onAnimationRepeat(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
        }
      });
      this.mRiseAnimator = new ValueAnimator();
      ((ValueAnimator)this.mRiseAnimator).setFloatValues(new float[] { 1.0F, 0.0F });
      ((ValueAnimator)this.mRiseAnimator).setDuration(500L);
      ((ValueAnimator)this.mRiseAnimator).setInterpolator(new DecelerateInterpolator());
      ((ValueAnimator)this.mRiseAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          BalloonFavView.access$002(BalloonFavView.this, ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
          BalloonFavView.this.invalidate();
        }
      });
      ((ValueAnimator)this.mRiseAnimator).addListener(new Animator.AnimatorListener()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          ((ValueAnimator)BalloonFavView.this.mFallAnimator).start();
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

  @TargetApi(11)
  private void startUpAndDown()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ((ValueAnimator)this.mFallAnimator).start();
      return;
    }
    this.mOffset = 1.0F;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawBg(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.bgLayout.scaleToBounds(this.standardLayout);
    this.triangularLayout.scaleToBounds(this.standardLayout);
    this.mTipPaint.setTextSize(this.bgLayout.height * 0.6F);
    this.mBgRectF.set(0.0F, 0.0F, this.bgLayout.width, this.bgLayout.height);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.education.balloon.BalloonFavView
 * JD-Core Version:    0.6.2
 */