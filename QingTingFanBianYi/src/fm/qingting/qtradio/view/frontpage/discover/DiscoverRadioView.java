package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.widget.ListView;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.LiveNode;
import fm.qingting.qtradio.model.RecommendPlayingInfoNode;
import fm.qingting.qtradio.model.RecommendPlayingItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.moreContentView.CustomSectionView;
import java.util.ArrayList;
import java.util.List;

public class DiscoverRadioView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener
{
  static final String STATISTIC_TAG = "v6_category_live_click";
  private SectionAdapter mAdapter;
  private CategoryByContentView mContentView;
  private ISectionFactory mFactory;
  private ListView mListView;
  private CategoryByRegionView mRegionView;

  public DiscoverRadioView(Context paramContext)
  {
    super(paramContext);
    final int i = hashCode();
    this.mFactory = new ISectionFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          return null;
        case 0:
          return new CustomSectionView(DiscoverRadioView.this.getContext());
        case 1:
          return new DiscoverItemAllView(DiscoverRadioView.this.getContext(), i);
        case 3:
          return new DiscoverItemRecommendPlayingView(DiscoverRadioView.this.getContext(), i);
        case 2:
        }
        return new DiscoverItemRecommendPlayingTagView(DiscoverRadioView.this.getContext(), i);
      }
    };
    this.mAdapter = new SectionAdapter(null, this.mFactory);
    this.mListView = new ListView(paramContext);
    RadioTagView localRadioTagView = new RadioTagView(paramContext, i);
    localRadioTagView.update("setData", "地区");
    this.mListView.addHeaderView(localRadioTagView);
    this.mRegionView = new CategoryByRegionView(paramContext);
    this.mListView.addHeaderView(this.mRegionView);
    localRadioTagView = new RadioTagView(paramContext, i);
    localRadioTagView.update("setData", "内容");
    this.mListView.addHeaderView(localRadioTagView);
    this.mContentView = new CategoryByContentView(paramContext);
    this.mListView.addHeaderView(this.mContentView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setDivider(null);
    this.mListView.setSelector(17170445);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
  }

  private void setCategoryData()
  {
    this.mRegionView.update("setData", InfoManager.getInstance().root().mContentCategory.mLiveNode.getRegionCategory());
    this.mContentView.update("setData", InfoManager.getInstance().root().mContentCategory.mLiveNode.getContentCategory());
  }

  private void setData(List<RecommendPlayingItemNode> paramList)
  {
    int i = 0;
    if ((paramList == null) || (paramList.size() == 0))
      return;
    ArrayList localArrayList = new ArrayList();
    if (paramList.size() > 0)
    {
      localArrayList.add(new SectionItem(0, null));
      localArrayList.add(new SectionItem(2, null));
      while (i < paramList.size())
      {
        localArrayList.add(new SectionItem(3, paramList.get(i)));
        i += 1;
      }
    }
    this.mAdapter.setData(localArrayList);
  }

  public void close(boolean paramBoolean)
  {
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RRPPI" });
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.mListView.getMeasuredWidth(), this.mListView.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mListView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RRPPI"))
    {
      paramString = InfoManager.getInstance().root().mRecommendPlayingInfo.getCurrPlayingForShow();
      if (paramString != null)
        setData(paramString);
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      setCategoryData();
      paramString = InfoManager.getInstance().root().mRecommendPlayingInfo.getCurrPlayingForShow();
      if (paramString != null)
        setData(paramString);
    }
    else
    {
      return;
    }
    InfoManager.getInstance().loadRecommendPlayingProgramsInfo(this);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverRadioView
 * JD-Core Version:    0.6.2
 */