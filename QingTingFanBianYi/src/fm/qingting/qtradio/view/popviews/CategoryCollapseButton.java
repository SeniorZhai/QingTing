package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class CategoryCollapseButton extends ViewGroupViewImpl
{
  public static final String CLICK = "ccb_click";
  private final ViewLayout arrowLayout = this.standardLayout.createChildLT(26, 18, 44, 29, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout bgLayout = this.standardLayout.createChildLT(80, 48, 17, 14, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(114, 2, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageView mArrowView;
  private ImageView mBgView;
  private boolean mCollapse = true;
  private LineView mLineView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(114, 76, 114, 76, 0, 0, ViewLayout.FILL);

  public CategoryCollapseButton(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mLineView = new LineView(paramContext);
    addView(this.mLineView);
    this.mBgView = new ImageView(paramContext);
    this.mBgView.setBackgroundResource(2130837721);
    this.mBgView.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mBgView);
    this.mArrowView = new ImageView(paramContext);
    this.mArrowView.setBackgroundResource(2130837720);
    this.mArrowView.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mArrowView);
    setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoryCollapseButton.this.dispatchActionEvent("ccb_click", null);
        CategoryCollapseButton.this.rotate();
        paramAnonymousView = CategoryCollapseButton.this;
        if (!CategoryCollapseButton.this.mCollapse);
        for (boolean bool = true; ; bool = false)
        {
          CategoryCollapseButton.access$202(paramAnonymousView, bool);
          return;
        }
      }
    });
  }

  private void rotate()
  {
    float f2 = 180.0F;
    float f1;
    if (this.mCollapse)
    {
      f1 = 0.0F;
      if (!this.mCollapse)
        break label83;
    }
    while (true)
    {
      RotateAnimation localRotateAnimation = new RotateAnimation(f1, f2, this.mArrowView.getWidth() / 2, this.mArrowView.getHeight() / 2);
      localRotateAnimation.setDuration(getResources().getInteger(2131427328));
      localRotateAnimation.setFillAfter(true);
      this.mArrowView.startAnimation(localRotateAnimation);
      return;
      f1 = 180.0F;
      break;
      label83: f2 = 360.0F;
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLineView.layout(0, this.standardLayout.height - this.lineLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.bgLayout.layoutView(this.mBgView);
    this.arrowLayout.layoutView(this.mArrowView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.bgLayout.scaleToBounds(this.standardLayout);
    this.arrowLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.measureView(this.mLineView);
    this.bgLayout.measureView(this.mBgView);
    this.arrowLayout.measureView(this.mArrowView);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("reset"))
    {
      rotate();
      this.mCollapse = true;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CategoryCollapseButton
 * JD-Core Version:    0.6.2
 */