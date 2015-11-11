package fm.qingting.qtradio.view.frontpage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.EducationManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.VirtualNode;
import fm.qingting.qtradio.view.education.balloon.EducationType;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverCampusView;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverColumnView;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverNovelView;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverRadioView;
import fm.qingting.qtradio.view.frontpage.discover.DiscoverRecommendView;
import fm.qingting.qtradio.view.groupselect.GroupWebView;
import fm.qingting.qtradio.view.popviews.CategoryCollapseButton;
import fm.qingting.qtradio.view.popviews.CategoryResortPopView;
import fm.qingting.qtradio.view.popviews.CategoryResortPopView.CategoryResortInfo;
import fm.qingting.qtradio.view.popviews.CategoryTagView;
import fm.qingting.qtradio.view.tab.TabPageIndicator;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DiscoverView extends ViewGroupViewImpl
  implements IEventHandler, InfoManager.ISubscribeEventListener
{
  private static final String STATISTIC_TAG = "v6_categoryselect";
  private static final String STATISTIC_TAG_EXPAND = "v6_category_expand";
  private static final String STATISTIC_TAG_ORDER = "v6_category_order_";
  private final ViewLayout buttonLayout = this.tabLayout.createChildLT(114, 76, 606, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout educationLayout = this.tabLayout.createChildLT(6, 20, 445, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private CategoryCollapseButton mCollapseButton;
  private int mDefaultAnimTime;
  private TabPageIndicator mIndicator;
  private ImageView mLineView;
  private MyPagerAdapter mPagerAdapter;
  private CategoryResortPopView mPopView;
  private boolean mShowEducationCollapse = true;
  private boolean mShowEducationSort = true;
  private CategoryTagView mTagView;
  private ViewPager mViewPager;
  private final ViewLayout seperateLineLayout = this.tabLayout.createChildLT(12, 76, 594, 1, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout tabLayout = this.standardLayout.createChildLT(720, 76, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    InfoManager.getInstance().loadLocalCategory(this);
    List localList = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getLstCategoryNodes();
    if ((localList != null) && (localList.size() > 0))
      generateSortArray(localList, false);
    this.mViewPager = new ViewPager(paramContext);
    this.mPagerAdapter = new MyPagerAdapter(sortArray(localList));
    this.mViewPager.setAdapter(this.mPagerAdapter);
    addView(this.mViewPager);
    this.mIndicator = new TabPageIndicator(paramContext);
    this.mIndicator.setViewPager(this.mViewPager);
    addView(this.mIndicator);
    QTMSGManage.getInstance().sendStatistcsMessage("v6_categoryselect", "精选");
    this.mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
      }

      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2)
      {
      }

      public void onPageSelected(int paramAnonymousInt)
      {
        QTMSGManage.getInstance().sendStatistcsMessage("v6_categoryselect", ((DiscoverView.MyPagerAdapter)DiscoverView.this.mViewPager.getAdapter()).getPageTitle(paramAnonymousInt).toString());
      }
    });
    this.mLineView = new ImageView(paramContext);
    this.mLineView.setBackgroundResource(2130837837);
    this.mLineView.setScaleType(ImageView.ScaleType.FIT_XY);
    addView(this.mLineView);
    this.mCollapseButton = new CategoryCollapseButton(paramContext);
    addView(this.mCollapseButton);
    this.mCollapseButton.setEventHandler(this);
    this.mDefaultAnimTime = getResources().getInteger(2131427328);
  }

  private void generateSortArray(List<CategoryNode> paramList, boolean paramBoolean)
  {
    boolean bool = false;
    ArrayList localArrayList = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
    if (localArrayList.size() > 0);
    for (int i = 0; ; i = 1)
    {
      if (paramBoolean)
      {
        localArrayList.clear();
        i = 1;
      }
      while (true)
      {
        if (i != 0)
        {
          j = 0;
          while (j < paramList.size())
          {
            localArrayList.add(Integer.valueOf(((CategoryNode)paramList.get(j)).sectionId));
            j += 1;
          }
        }
        HashSet localHashSet = new HashSet();
        int m = localArrayList.size();
        int n = paramList.size();
        int j = 0;
        int i1;
        if (j < n)
        {
          i1 = ((CategoryNode)paramList.get(j)).sectionId;
          k = 0;
          label130: if (k >= m)
            break label279;
          if (i1 == ((Integer)localArrayList.get(k)).intValue())
            localHashSet.add(Integer.valueOf(k));
        }
        label279: for (int k = 1; ; k = 0)
        {
          if (k == 0)
          {
            localArrayList.add(Integer.valueOf(i1));
            localHashSet.add(Integer.valueOf(localArrayList.size() - 1));
          }
          j += 1;
          break;
          k += 1;
          break label130;
          j = localArrayList.size() - 1;
          while (j >= 0)
          {
            if (!localHashSet.contains(Integer.valueOf(j)))
              localArrayList.remove(j);
            j -= 1;
          }
          localHashSet.clear();
          paramBoolean = bool;
          if (i == 0)
            paramBoolean = true;
          CategoryResortPopView.CategoryResortInfo.setNewSortedList(localArrayList, paramBoolean);
          return;
        }
      }
    }
  }

  private boolean isChanged(ArrayList<Integer> paramArrayList)
  {
    if (paramArrayList == null)
      return false;
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      if (i != ((Integer)paramArrayList.get(i)).intValue())
        return true;
      i += 1;
    }
    return false;
  }

  private void performHideAnimation()
  {
    if (EducationManager.getInstance().isShown())
      EducationManager.getInstance().cancelTip();
    Object localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, 0.0F, 1, -1.0F);
    ((Animation)localObject).setDuration(this.mDefaultAnimTime);
    ((Animation)localObject).setInterpolator(getContext(), 2131099648);
    this.mPopView.startAnimation((Animation)localObject);
    ((Animation)localObject).setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        DiscoverView.this.mPopView.setVisibility(8);
        DiscoverView.this.mTagView.setVisibility(8);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    localObject = new AnimationSet(true);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.5F);
    localAlphaAnimation.setDuration(this.mDefaultAnimTime);
    ((AnimationSet)localObject).addAnimation(localAlphaAnimation);
    this.mTagView.startAnimation((Animation)localObject);
  }

  private void performShowAnimation()
  {
    QTMSGManage.getInstance().sendStatistcsMessage("v6_category_expand");
    Object localObject = new TranslateAnimation(1, 0.0F, 1, 0.0F, 1, -1.0F, 1, 0.0F);
    ((Animation)localObject).setDuration(this.mDefaultAnimTime);
    ((Animation)localObject).setInterpolator(getContext(), 2131099648);
    this.mPopView.startAnimation((Animation)localObject);
    ((Animation)localObject).setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        DiscoverView.this.mIndicator.setVisibility(8);
        DiscoverView.this.showEducationSortIfNeed();
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    localObject = new AnimationSet(true);
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.5F, 1.0F);
    localAlphaAnimation.setDuration(this.mDefaultAnimTime);
    ((AnimationSet)localObject).addAnimation(localAlphaAnimation);
    this.mTagView.startAnimation((Animation)localObject);
  }

  private void resortIfNeed()
  {
    if (this.mPopView == null);
    int i;
    do
    {
      CategoryResortPopView.CategoryResortInfo localCategoryResortInfo;
      do
      {
        return;
        localCategoryResortInfo = this.mPopView.getResortInfo();
      }
      while (localCategoryResortInfo == null);
      ArrayList localArrayList = localCategoryResortInfo.getIndexs();
      if (isChanged(localArrayList))
      {
        List localList = ((MyPagerAdapter)this.mViewPager.getAdapter()).getData();
        this.mViewPager.setAdapter(new MyPagerAdapter(resortSortArray(localList, localArrayList)));
        this.mIndicator.notifyDataSetChanged();
      }
      i = localCategoryResortInfo.getSelectItem();
    }
    while (i == this.mViewPager.getCurrentItem());
    this.mViewPager.setCurrentItem(i, false);
    this.mIndicator.shiftSlide(i);
  }

  private List<CategoryNode> resortSortArray(List<CategoryNode> paramList, ArrayList<Integer> paramArrayList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
    int i = 0;
    while (i < paramArrayList.size())
    {
      localArrayList1.add(localArrayList3.get(((Integer)paramArrayList.get(i)).intValue()));
      localArrayList2.add(paramList.get(((Integer)paramArrayList.get(i)).intValue()));
      i += 1;
    }
    CategoryResortPopView.CategoryResortInfo.setNewSortedList(localArrayList1, true);
    return localArrayList2;
  }

  private void showEducationCollapseIfNeed()
  {
    if (this.mShowEducationCollapse)
      this.mShowEducationCollapse = EducationManager.getInstance().needShowTip(EducationType.COLLAPSE);
    if (this.mShowEducationCollapse)
    {
      EducationManager.getInstance().showTip(EducationType.COLLAPSE, 4112, new Point(this.standardLayout.width - this.educationLayout.width, ScreenConfiguration.getNaviHeight() + ScreenConfiguration.getSubTabHeight() - this.educationLayout.height));
      EducationManager.getInstance().setEducationShowed(EducationType.COLLAPSE);
      this.mShowEducationCollapse = false;
    }
  }

  private void showEducationSortIfNeed()
  {
    if (this.mShowEducationSort)
      this.mShowEducationSort = EducationManager.getInstance().needShowTip(EducationType.SORT);
    if (this.mShowEducationSort)
    {
      EducationManager.getInstance().showTip(EducationType.SORT, 4097, new Point(this.educationLayout.leftMargin, ScreenConfiguration.getNaviHeight() + ScreenConfiguration.getSubTabHeight() - this.educationLayout.height));
      EducationManager.getInstance().setEducationShowed(EducationType.SORT);
      this.mShowEducationSort = false;
    }
  }

  private void showOrHideResortView()
  {
    if (this.mPopView == null)
    {
      this.mPopView = new CategoryResortPopView(getContext(), ((MyPagerAdapter)this.mViewPager.getAdapter()).getData());
      this.mPopView.update("setData", new CategoryResortPopView.CategoryResortInfo(this.mViewPager.getCurrentItem(), this.mViewPager.getAdapter().getCount()));
      this.mPopView.setEventHandler(this);
      addView(this.mPopView, 1);
      this.mTagView = new CategoryTagView(getContext());
      addView(this.mTagView, getChildCount() - 1);
      this.mTagView.setEventHandler(this);
      performShowAnimation();
      return;
    }
    if (this.mPopView.getVisibility() == 8)
    {
      this.mPopView.setVisibility(0);
      this.mTagView.setVisibility(0);
      this.mPopView.update("setData", new CategoryResortPopView.CategoryResortInfo(this.mViewPager.getCurrentItem(), this.mViewPager.getAdapter().getCount()));
      performShowAnimation();
      return;
    }
    this.mIndicator.setVisibility(0);
    this.mPopView.update("disabledragdrop", null);
    this.mTagView.update("reset", null);
    performHideAnimation();
    resortIfNeed();
  }

  private List<CategoryNode> sortArray(List<CategoryNode> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
    int i = 0;
    if (i < localArrayList2.size())
    {
      int k = ((Integer)localArrayList2.get(i)).intValue();
      int j = 0;
      while (true)
      {
        if (j < paramList.size())
        {
          CategoryNode localCategoryNode = (CategoryNode)paramList.get(j);
          if (localCategoryNode.sectionId == k)
          {
            localArrayList1.add(localCategoryNode);
            QTMSGManage.getInstance().sendStatistcsMessage("v6_category_order_" + i, localCategoryNode.name);
          }
        }
        else
        {
          i += 1;
          break;
        }
        j += 1;
      }
    }
    return localArrayList1;
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1) && (this.mPopView != null) && (this.mPopView.getVisibility() == 0))
    {
      showOrHideResortView();
      this.mCollapseButton.update("reset", null);
      return true;
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("ccb_click"))
    {
      if (EducationManager.getInstance().isShown())
        EducationManager.getInstance().cancelTip();
      showOrHideResortView();
    }
    do
    {
      do
      {
        return;
        if (!paramString.equalsIgnoreCase("ctv_click"))
          break;
      }
      while (this.mPopView == null);
      this.mPopView.update("toggledragdrop", paramObject2);
      return;
      if (paramString.equalsIgnoreCase("itemselected"))
      {
        this.mCollapseButton.update("reset", null);
        showOrHideResortView();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("dragdropenabled"));
    this.mTagView.update("entermanage", paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mIndicator.layout(0, 0, this.tabLayout.width - this.buttonLayout.width, this.tabLayout.height);
    this.buttonLayout.layoutView(this.mCollapseButton);
    this.seperateLineLayout.layoutView(this.mLineView);
    this.mViewPager.layout(0, this.tabLayout.height, this.standardLayout.width, this.standardLayout.height);
    if (this.mPopView != null)
      this.mPopView.layout(0, this.tabLayout.height, this.standardLayout.width, this.standardLayout.height);
    if (this.mTagView != null)
      this.mTagView.layout(0, 0, this.tabLayout.width - this.buttonLayout.width, this.tabLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.tabLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.tabLayout);
    this.seperateLineLayout.scaleToBounds(this.tabLayout);
    this.educationLayout.scaleToBounds(this.tabLayout);
    this.mIndicator.measure(View.MeasureSpec.makeMeasureSpec(this.tabLayout.width - this.buttonLayout.width, 1073741824), this.tabLayout.getHeightMeasureSpec());
    this.buttonLayout.measureView(this.mCollapseButton);
    this.seperateLineLayout.measureView(this.mLineView);
    this.mViewPager.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.tabLayout.height, 1073741824));
    if (this.mPopView != null)
      this.mPopView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.tabLayout.height, 1073741824));
    if (this.mTagView != null)
      this.mTagView.measure(View.MeasureSpec.makeMeasureSpec(this.tabLayout.width - this.buttonLayout.width, 1073741824), this.tabLayout.getHeightMeasureSpec());
    showEducationCollapseIfNeed();
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_LOCAL_CATEGORY"))
    {
      paramString = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getLocalCategoryNode();
      if (paramString != null)
      {
        localList = this.mPagerAdapter.getLstCategory();
        if (localList != null)
        {
          i = 0;
          if (i >= localList.size())
            break label82;
          if (((CategoryNode)localList.get(i)).categoryId != paramString.categoryId)
            break label75;
        }
      }
    }
    label75: label82: 
    while (!paramString.equalsIgnoreCase("RECV_LOCAL_RECOMMEND_INFO"))
    {
      List localList;
      while (true)
      {
        int i;
        return;
        i += 1;
      }
      localList.add(2, paramString);
      generateSortArray(localList, true);
      this.mPagerAdapter = new MyPagerAdapter(localList);
      this.mViewPager.setAdapter(this.mPagerAdapter);
      this.mIndicator.notifyDataSetChanged();
      return;
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void resortByPriority(List<CategoryNode> paramList)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("resortCategoryList"))
    {
      this.mViewPager.setAdapter(new MyPagerAdapter(sortArray(((MyPagerAdapter)this.mViewPager.getAdapter()).getData())));
      this.mIndicator.notifyDataSetChanged();
    }
  }

  class MyPagerAdapter extends PagerAdapter
  {
    private List<CategoryNode> mList;

    public MyPagerAdapter()
    {
      Object localObject;
      this.mList = localObject;
    }

    public void destroyItem(View paramView, int paramInt, Object paramObject)
    {
      ((ViewPager)paramView).removeView((View)paramObject);
      if ((paramObject instanceof IView))
        ((IView)paramObject).close(false);
    }

    public int getCount()
    {
      if (this.mList == null)
        return 0;
      return this.mList.size();
    }

    List<CategoryNode> getData()
    {
      return this.mList;
    }

    public int getItemPosition(Object paramObject)
    {
      return super.getItemPosition(paramObject);
    }

    public List<CategoryNode> getLstCategory()
    {
      return this.mList;
    }

    public CharSequence getPageTitle(int paramInt)
    {
      if (this.mList == null)
        return "";
      return ((CategoryNode)this.mList.get(paramInt)).name;
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      CategoryNode localCategoryNode = (CategoryNode)this.mList.get(paramInt);
      Object localObject = InfoManager.getInstance().h5Category(localCategoryNode.categoryId);
      if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
      {
        localObject = new GroupWebView(DiscoverView.this.getContext(), (String)localObject, false, false);
        if (localCategoryNode.sectionId == 0)
          break label249;
        InfoManager.getInstance().loadCategoryAttrs(localCategoryNode, localCategoryNode.categoryId, null);
      }
      label249: 
      while (true)
      {
        ((IView)localObject).update("setData", this.mList.get(paramInt));
        ((ViewPager)paramViewGroup).addView(((IView)localObject).getView());
        return ((IView)localObject).getView();
        if (localCategoryNode.sectionId == 0)
          localObject = new DiscoverRecommendView(DiscoverView.this.getContext());
        else if (localCategoryNode.sectionId == 166)
          localObject = new DiscoverCampusView(DiscoverView.this.getContext());
        else if (localCategoryNode.sectionId == 9999)
          localObject = new DiscoverRadioView(DiscoverView.this.getContext());
        else if (localCategoryNode.sectionId == 208)
          localObject = new DiscoverNovelView(DiscoverView.this.getContext());
        else
          localObject = new DiscoverColumnView(DiscoverView.this.getContext());
      }
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }

    public void setLstCategory(List<CategoryNode> paramList)
    {
      this.mList = paramList;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.DiscoverView
 * JD-Core Version:    0.6.2
 */