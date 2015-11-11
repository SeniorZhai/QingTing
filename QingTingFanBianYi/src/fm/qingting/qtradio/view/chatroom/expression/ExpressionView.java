package fm.qingting.qtradio.view.chatroom.expression;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.utils.ExpressionUtil;
import java.util.ArrayList;
import java.util.List;

public class ExpressionView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final int MAX_EXPRESSION_CNT = 17;
  private int PICAMOUNT = getPageCnt();
  private int currentIndex = 0;
  private final ViewLayout dotsLayout = this.standardLayout.createChildLT(12, 12, 15, 40, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint greyDotPaint = new Paint();
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 400, 720, 400, 0, 0, ViewLayout.FILL);
  private List<ExpressionGridView> subViews = new ArrayList();
  private ViewPager viewPager;
  private Paint whiteDotPaint = new Paint();

  public ExpressionView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1);
    this.viewPager = new ViewPager(paramContext);
    this.viewPager.setAdapter(new MyAdapter(null));
    this.viewPager.setOnPageChangeListener(new MyPageChangeListener(null));
    addView(this.viewPager);
    this.greyDotPaint.setColor(-2004318072);
    this.greyDotPaint.setStyle(Paint.Style.FILL);
    this.whiteDotPaint.setColor(-14540254);
    this.whiteDotPaint.setStyle(Paint.Style.FILL);
    buildViews();
  }

  private void buildViews()
  {
    int j = 0;
    int m = ExpressionUtil.getInstance().getExpressionCnt();
    int i1 = m / 17;
    int i;
    if (m % 17 > 0)
      i = 1;
    while (j < i1 + i)
    {
      int n = (j + 1) * 17;
      int k = n;
      if (n > m)
        k = m;
      List localList = ExpressionUtil.getInstance().getExpressionSubList(j * 17, k);
      ExpressionGridView localExpressionGridView = new ExpressionGridView(getContext());
      localExpressionGridView.setEventHandler(this);
      localExpressionGridView.update("setData", localList);
      this.subViews.add(localExpressionGridView);
      j += 1;
      continue;
      i = 0;
    }
  }

  private void drawDots(Canvas paramCanvas)
  {
    int j = (this.standardLayout.width - this.PICAMOUNT * this.dotsLayout.width - (this.PICAMOUNT - 1) * this.dotsLayout.width) / 2;
    int i = 0;
    if (i < this.PICAMOUNT)
    {
      RectF localRectF = new RectF(j, this.standardLayout.height - this.dotsLayout.topMargin, this.dotsLayout.width + j, this.standardLayout.height - this.dotsLayout.topMargin + this.dotsLayout.height);
      if (i == this.currentIndex);
      for (Paint localPaint = this.whiteDotPaint; ; localPaint = this.greyDotPaint)
      {
        paramCanvas.drawOval(localRectF, localPaint);
        j += this.dotsLayout.leftMargin + this.dotsLayout.width;
        i += 1;
        break;
      }
    }
  }

  private int getPageCnt()
  {
    int i = ExpressionUtil.getInstance().getExpressionCnt();
    int j = i / 17;
    if (i % 17 > 0);
    for (i = 1; ; i = 0)
      return i + j;
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
    paramCanvas.save();
    drawDots(paramCanvas);
    paramCanvas.restore();
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("selectExpression"))
      dispatchActionEvent(paramString, paramObject2);
    while (!paramString.equalsIgnoreCase("deleteExpression"))
      return;
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.viewPager.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.dotsLayout.scaleToBounds(this.standardLayout);
    this.standardLayout.measureView(this.viewPager);
    setMeasuredDimension(paramInt1, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
  }

  private class MyAdapter extends PagerAdapter
  {
    private MyAdapter()
    {
    }

    public void destroyItem(View paramView, int paramInt, Object paramObject)
    {
      ((ViewPager)paramView).removeView((View)paramObject);
    }

    public void finishUpdate(View paramView)
    {
    }

    public int getCount()
    {
      return ExpressionView.this.PICAMOUNT;
    }

    public Object instantiateItem(View paramView, int paramInt)
    {
      paramInt %= ExpressionView.this.PICAMOUNT;
      if (paramInt < 0)
        paramInt += ExpressionView.this.PICAMOUNT;
      while (true)
      {
        ((ViewPager)paramView).addView((View)ExpressionView.this.subViews.get(paramInt));
        return ExpressionView.this.subViews.get(paramInt);
      }
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }

    public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
    {
    }

    public Parcelable saveState()
    {
      return null;
    }

    public void startUpdate(View paramView)
    {
    }
  }

  private class MyPageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    private MyPageChangeListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
    }

    public void onPageSelected(int paramInt)
    {
      ExpressionView.access$402(ExpressionView.this, paramInt % ExpressionView.this.PICAMOUNT);
      ExpressionView.this.invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.expression.ExpressionView
 * JD-Core Version:    0.6.2
 */