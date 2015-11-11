package fm.qingting.qtradio.view.tab;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Layout.Alignment;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ScreenConfiguration;

public class TabPageIndicator extends HorizontalScrollView
  implements PageIndicator
{
  private static final CharSequence EMPTY_TITLE = "";
  private ViewPager.OnPageChangeListener mListener;
  private int mMaxTabWidth;
  private int mSelectedTabIndex;
  private final TabContainer mTabLayout;
  private OnTabReselectedListener mTabReselectedListener;
  private Runnable mTabSelector;
  private ViewPager mViewPager;

  public TabPageIndicator(Context paramContext)
  {
    this(paramContext, null);
  }

  public TabPageIndicator(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setBackgroundColor(SkinManager.getCardColor());
    setHorizontalScrollBarEnabled(false);
    this.mTabLayout = new TabContainer(paramContext);
    addView(this.mTabLayout, new ViewGroup.LayoutParams(-2, -2));
  }

  private void addTab(int paramInt, CharSequence paramCharSequence)
  {
    TabView localTabView = new TabView(getContext());
    localTabView.mIndex = paramInt;
    localTabView.setFocusable(true);
    localTabView.setText((String)paramCharSequence);
    this.mTabLayout.getTabLayout().addView(localTabView, new LinearLayout.LayoutParams(0, -1, 1.0F));
  }

  private void animateToTab(int paramInt)
  {
    final View localView = this.mTabLayout.getTabLayout().getChildAt(paramInt);
    if (this.mTabSelector != null)
      removeCallbacks(this.mTabSelector);
    this.mTabSelector = new Runnable()
    {
      public void run()
      {
        int i = localView.getLeft();
        int j = (TabPageIndicator.this.getWidth() - localView.getWidth()) / 2;
        TabPageIndicator.this.smoothScrollTo(i - j, 0);
        TabPageIndicator.access$002(TabPageIndicator.this, null);
      }
    };
    post(this.mTabSelector);
  }

  public void notifyDataSetChanged()
  {
    this.mTabLayout.getTabLayout().removeAllViews();
    PagerAdapter localPagerAdapter = this.mViewPager.getAdapter();
    int j = localPagerAdapter.getCount();
    int i = 0;
    while (i < j)
    {
      CharSequence localCharSequence2 = localPagerAdapter.getPageTitle(i);
      CharSequence localCharSequence1 = localCharSequence2;
      if (localCharSequence2 == null)
        localCharSequence1 = EMPTY_TITLE;
      addTab(i, localCharSequence1);
      i += 1;
    }
    if (this.mSelectedTabIndex > j)
      this.mSelectedTabIndex = (j - 1);
    setCurrentItem(this.mSelectedTabIndex);
    requestLayout();
  }

  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (this.mTabSelector != null)
      post(this.mTabSelector);
  }

  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mTabSelector != null)
      removeCallbacks(this.mTabSelector);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    boolean bool;
    if (i == 1073741824)
    {
      bool = true;
      setFillViewport(bool);
      int j = this.mTabLayout.getTabLayout().getChildCount();
      if ((j <= 1) || ((i != 1073741824) && (i != -2147483648)))
        break label123;
      if (j <= 2)
        break label110;
      this.mMaxTabWidth = ((int)(View.MeasureSpec.getSize(paramInt1) * 0.4F));
    }
    while (true)
    {
      i = getMeasuredWidth();
      super.onMeasure(paramInt1, paramInt2);
      paramInt1 = getMeasuredWidth();
      if ((bool) && (i != paramInt1))
        setCurrentItem(this.mSelectedTabIndex);
      return;
      bool = false;
      break;
      label110: this.mMaxTabWidth = (View.MeasureSpec.getSize(paramInt1) / 2);
      continue;
      label123: this.mMaxTabWidth = View.MeasureSpec.getSize(paramInt1);
    }
  }

  public void onPageScrollStateChanged(int paramInt)
  {
    if (this.mListener != null)
      this.mListener.onPageScrollStateChanged(paramInt);
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (this.mListener != null)
      this.mListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    this.mTabLayout.shiftSlide(paramInt1, paramInt2);
  }

  public void onPageSelected(int paramInt)
  {
    setCurrentItem(paramInt);
    if (this.mListener != null)
      this.mListener.onPageSelected(paramInt);
  }

  public void setCurrentItem(int paramInt)
  {
    if (this.mViewPager == null)
      throw new IllegalStateException("ViewPager has not been bound.");
    this.mSelectedTabIndex = paramInt;
    this.mViewPager.setCurrentItem(paramInt);
    int j = this.mTabLayout.getTabLayout().getChildCount();
    int i = 0;
    if (i < j)
    {
      View localView = this.mTabLayout.getTabLayout().getChildAt(i);
      if (i == paramInt);
      for (boolean bool = true; ; bool = false)
      {
        localView.setSelected(bool);
        if (bool)
          animateToTab(paramInt);
        i += 1;
        break;
      }
    }
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.mListener = paramOnPageChangeListener;
  }

  public void setOnTabReselectedListener(OnTabReselectedListener paramOnTabReselectedListener)
  {
    this.mTabReselectedListener = paramOnTabReselectedListener;
  }

  public void setViewPager(ViewPager paramViewPager)
  {
    if (this.mViewPager == paramViewPager)
      return;
    if (this.mViewPager != null)
      this.mViewPager.setOnPageChangeListener(null);
    if (paramViewPager.getAdapter() == null)
      throw new IllegalStateException("ViewPager does not have adapter instance.");
    this.mViewPager = paramViewPager;
    paramViewPager.setOnPageChangeListener(this);
    notifyDataSetChanged();
  }

  public void setViewPager(ViewPager paramViewPager, int paramInt)
  {
    setViewPager(paramViewPager);
    setCurrentItem(paramInt);
  }

  public void shiftSlide(int paramInt)
  {
    this.mTabLayout.shiftSlide(paramInt, 0);
  }

  public static abstract interface OnTabReselectedListener
  {
    public abstract void onTabReselected(int paramInt);
  }

  private class TabView extends QtView
  {
    int mIndex;
    private TextViewElement mTextViewElement;

    public TabView(Context arg2)
    {
      super();
      this.mTextViewElement = new TextViewElement(localContext);
      addElement(this.mTextViewElement);
      this.mTextViewElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
      this.mTextViewElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
      this.mTextViewElement.setColor(SkinManager.getTextColorSecondLevel());
      this.mTextViewElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
      {
        public void onElementClick(ViewElement paramAnonymousViewElement)
        {
          int i = TabPageIndicator.this.mViewPager.getCurrentItem();
          int j = TabPageIndicator.TabView.this.mIndex;
          TabPageIndicator.this.mViewPager.setCurrentItem(j);
          if ((i == j) && (TabPageIndicator.this.mTabReselectedListener != null))
            TabPageIndicator.this.mTabReselectedListener.onTabReselected(j);
        }
      });
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
      this.mTextViewElement.measure(0, 0, TabPageIndicator.this.mMaxTabWidth, View.MeasureSpec.getSize(paramInt2));
      this.mTextViewElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
      paramInt1 = this.mTextViewElement.getWidth() + ScreenConfiguration.getTabPadding() * 2;
      this.mTextViewElement.setTranslationX(-(TabPageIndicator.this.mMaxTabWidth - paramInt1) / 2);
      this.mTextViewElement.setTranslationY((View.MeasureSpec.getSize(paramInt2) - this.mTextViewElement.getHeight()) / 2);
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), paramInt2);
    }

    public void setSelected(boolean paramBoolean)
    {
      TextViewElement localTextViewElement = this.mTextViewElement;
      if (paramBoolean);
      for (int i = SkinManager.getTextColorHighlight(); ; i = SkinManager.getTextColorSecondLevel())
      {
        localTextViewElement.setColor(i);
        return;
      }
    }

    void setText(String paramString)
    {
      this.mTextViewElement.setText(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.tab.TabPageIndicator
 * JD-Core Version:    0.6.2
 */