package fm.qingting.qtradio.view.virtualcategoryview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.Attributes;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.tab.TabPageIndicator;
import fm.qingting.qtradio.view.virtualchannellist.NovelChannelListByAttrView;
import fm.qingting.qtradio.view.virtualchannellist.VirtualChannelListAllView;
import fm.qingting.qtradio.view.virtualchannellist.VirtualChannelListByAttrsView;
import java.util.List;

public class NovelAllContentView extends ViewGroupViewImpl
  implements IEventHandler, InfoManager.ISubscribeEventListener
{
  private final int ID = 32;
  private List<Attribute> mAttributes;
  private VirtualChannelListByAttrsView mFilteredView;
  private TabPageIndicator mIndicator;
  private ChoosenLabelsView mLabelsView;
  private CategoryNode mNode;
  private MiniPlayerView mPlayerView;
  private ViewPager mViewPager;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout tabLayout = this.standardLayout.createChildLT(720, 76, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public NovelAllContentView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mViewPager = new ViewPager(paramContext);
    this.mViewPager.setAdapter(new MyPagerAdapter(null));
    addView(this.mViewPager);
    this.mIndicator = new TabPageIndicator(paramContext);
    this.mIndicator.setViewPager(this.mViewPager);
    addView(this.mIndicator);
    this.mLabelsView = new ChoosenLabelsView(paramContext);
    addView(this.mLabelsView);
    this.mLabelsView.setVisibility(8);
    this.mLabelsView.setEventHandler(this);
    this.mFilteredView = new VirtualChannelListByAttrsView(paramContext);
    addView(this.mFilteredView);
    this.mFilteredView.setVisibility(8);
    this.mPlayerView = new MiniPlayerView(paramContext);
    addView(this.mPlayerView);
  }

  private void hideFilteredView()
  {
    this.mLabelsView.setVisibility(8);
    this.mFilteredView.setVisibility(8);
    this.mViewPager.setVisibility(0);
  }

  private void showFilteredView(List<Attribute> paramList)
  {
    this.mLabelsView.setVisibility(0);
    this.mFilteredView.setVisibility(0);
    this.mViewPager.setVisibility(8);
    this.mFilteredView.update("setFilter", paramList);
  }

  public void close(boolean paramBoolean)
  {
    this.mPlayerView.destroy();
    this.mLabelsView.close(paramBoolean);
    this.mFilteredView.close(paramBoolean);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    Object localObject = null;
    paramObject = localObject;
    if (paramString.equalsIgnoreCase("getAttributes"))
    {
      paramObject = localObject;
      if (this.mLabelsView.getVisibility() == 0)
        paramObject = this.mAttributes;
    }
    return paramObject;
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("clear"))
      hideFilteredView();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLabelsView.layout(0, 0, this.mLabelsView.getMeasuredWidth(), this.mLabelsView.getMeasuredHeight());
    this.mFilteredView.layout(0, this.mLabelsView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
    this.tabLayout.layoutView(this.mIndicator);
    this.mViewPager.layout(0, this.tabLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.tabLayout.scaleToBounds(this.standardLayout);
    this.tabLayout.measureView(this.mIndicator);
    this.standardLayout.measureView(this.mPlayerView);
    this.mViewPager.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.tabLayout.height, 1073741824));
    this.tabLayout.measureView(this.mLabelsView);
    this.mFilteredView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.mLabelsView.getMeasuredHeight(), 1073741824));
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    int i;
    if (paramString.equalsIgnoreCase("RECV_RECOMMEND_INFO"))
    {
      paramString = this.mNode.getLstAttributes(true);
      if (paramString != null)
        i = 0;
    }
    while (true)
    {
      if (i < paramString.size())
      {
        Attributes localAttributes = (Attributes)paramString.get(i);
        if (localAttributes.id == 32)
        {
          this.mViewPager.setAdapter(new MyPagerAdapter(localAttributes.mLstAttribute));
          this.mIndicator.notifyDataSetChanged();
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    int i = 0;
    if (paramString.equalsIgnoreCase("resetFilter"))
    {
      this.mAttributes = ((List)paramObject);
      if ((this.mAttributes == null) || (this.mAttributes.size() == 0))
      {
        hideFilteredView();
        this.mLabelsView.update(paramString, paramObject);
      }
    }
    while (!paramString.equalsIgnoreCase("setNode"))
      while (true)
      {
        return;
        showFilteredView(this.mAttributes);
      }
    this.mNode = ((CategoryNode)paramObject);
    this.mFilteredView.update(paramString, paramObject);
    paramString = this.mNode.getLstAttributes(false);
    if (paramString == null)
    {
      InfoManager.getInstance().loadCategoryAttrs(this.mNode, this.mNode.categoryId, this);
      return;
    }
    do
    {
      i += 1;
      if (i >= paramString.size())
        break;
      paramObject = (Attributes)paramString.get(i);
    }
    while (paramObject.id != 32);
    this.mViewPager.setAdapter(new MyPagerAdapter(paramObject.mLstAttribute));
    this.mIndicator.notifyDataSetChanged();
  }

  class MyPagerAdapter extends PagerAdapter
  {
    private List<Attribute> mList;

    public MyPagerAdapter()
    {
      Object localObject;
      this.mList = localObject;
    }

    public void destroyItem(View paramView, int paramInt, Object paramObject)
    {
      ((ViewPager)paramView).removeView((View)paramObject);
    }

    public int getCount()
    {
      if (this.mList == null)
        return 1;
      return this.mList.size() + 1;
    }

    public int getItemPosition(Object paramObject)
    {
      return super.getItemPosition(paramObject);
    }

    public CharSequence getPageTitle(int paramInt)
    {
      if (this.mList == null)
        return "全部";
      if (paramInt == 0)
        return "全部";
      return ((Attribute)this.mList.get(paramInt - 1)).name;
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      Object localObject;
      if (paramInt == 0)
      {
        localObject = new VirtualChannelListAllView(NovelAllContentView.this.getContext());
        ((IView)localObject).update("setData", NovelAllContentView.this.mNode);
      }
      while (true)
      {
        ((ViewPager)paramViewGroup).addView(((IView)localObject).getView());
        return ((IView)localObject).getView();
        localObject = new NovelChannelListByAttrView(NovelAllContentView.this.getContext());
        ((IView)localObject).update("setNode", NovelAllContentView.this.mNode);
        ((IView)localObject).update("setData", this.mList.get(paramInt - 1));
      }
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualcategoryview.NovelAllContentView
 * JD-Core Version:    0.6.2
 */