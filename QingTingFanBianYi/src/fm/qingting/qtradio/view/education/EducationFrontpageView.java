package fm.qingting.qtradio.view.education;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View.MeasureSpec;
import android.view.animation.CycleInterpolator;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;

public class EducationFrontpageView extends QtView
{
  private final ViewLayout arrowLayout = this.standardLayout.createChildLT(578, 38, 71, 350, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout handLayout = this.standardLayout.createChildLT(140, 248, 340, 330, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mArrowElement;
  private Point mDivide;
  private ImageViewElement mHandElement;
  private float mOffsetFactor = 0.0F;
  private ImageViewElement mTipElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout tipLayout = this.standardLayout.createChildLT(316, 72, 202, 260, ViewLayout.SCALE_FLAG_SLTCW);

  public EducationFrontpageView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mArrowElement = new ImageViewElement(paramContext);
    this.mArrowElement.setImageRes(2130837728);
    addElement(this.mArrowElement, i);
    this.mTipElement = new ImageViewElement(paramContext);
    this.mTipElement.setImageRes(2130837730);
    addElement(this.mTipElement, i);
    this.mHandElement = new ImageViewElement(paramContext);
    this.mHandElement.setImageRes(2130837729);
    addElement(this.mHandElement, i);
  }

  @TargetApi(11)
  private void doAnimation()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { 1.0F, 0.0F });
      localValueAnimator.setInterpolator(new CycleInterpolator(1.0F));
      localValueAnimator.setDuration(2000L).addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          EducationFrontpageView.access$002(EducationFrontpageView.this, ((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue() - 1.0F);
          EducationFrontpageView.this.invalidate();
        }
      });
      localValueAnimator.addListener(new Animator.AnimatorListener()
      {
        public void onAnimationCancel(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationEnd(Animator paramAnonymousAnimator)
        {
          EventDispacthManager.getInstance().dispatchAction("cancelTip", null);
        }

        public void onAnimationRepeat(Animator paramAnonymousAnimator)
        {
        }

        public void onAnimationStart(Animator paramAnonymousAnimator)
        {
        }
      });
      localValueAnimator.start();
    }
  }

  private void drawBg(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    paramCanvas.clipRect(0, 0, this.standardLayout.width, this.mDivide.x);
    paramCanvas.drawColor(-872415232);
    paramCanvas.restoreToCount(i);
    i = paramCanvas.save();
    paramCanvas.clipRect(0, this.mDivide.y, this.standardLayout.width, this.standardLayout.height);
    paramCanvas.drawColor(-872415232);
    paramCanvas.restoreToCount(i);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mDivide == null)
      return;
    drawBg(paramCanvas);
    this.mTipElement.setTranslationY(this.mDivide.y);
    this.mArrowElement.setTranslationY(this.mDivide.y);
    this.mHandElement.setTranslationX((int)(this.mOffsetFactor * this.arrowLayout.width) / 3);
    this.mHandElement.setTranslationY(this.mDivide.y);
    super.onDraw(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.arrowLayout.scaleToBounds(this.standardLayout);
    this.tipLayout.scaleToBounds(this.standardLayout);
    this.handLayout.scaleToBounds(this.standardLayout);
    this.mArrowElement.measure(this.arrowLayout);
    this.mTipElement.measure(this.tipLayout);
    this.mHandElement.measure(this.handLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mDivide = ((Point)paramObject);
      doAnimation();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.education.EducationFrontpageView
 * JD-Core Version:    0.6.2
 */