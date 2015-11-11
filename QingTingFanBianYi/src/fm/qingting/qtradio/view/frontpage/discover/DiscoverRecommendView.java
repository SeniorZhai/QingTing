package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.util.SparseArray;
import android.widget.ListView;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.jd.data.CommodityInfo;
import fm.qingting.qtradio.jd.data.JDApi;
import fm.qingting.qtradio.jd.data.JDApi.ErrorCode;
import fm.qingting.qtradio.jd.data.JDApi.OnResponseListener;
import fm.qingting.qtradio.jd.data.Response;
import fm.qingting.qtradio.jd.view.JDRecommendItemView;
import fm.qingting.qtradio.jd.view.JDRecommendTagView;
import fm.qingting.qtradio.jd.view.JDRecommendTagView.OnDeleteListener;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.qtradio.view.LoadingView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.moreContentView.CustomSectionView;
import fm.qingting.qtradio.view.popviews.CategoryResortPopView.CategoryResortInfo;
import fm.qingting.qtradio.view.switchview.SwitchView;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ThirdTracker;
import java.util.ArrayList;
import java.util.List;

public class DiscoverRecommendView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, RootNode.IInfoUpdateEventListener
{
  private SectionAdapter mAdapter;
  private ArrayList<CommodityInfo> mCommodityInfos;
  private ISectionFactory mFactory = new ISectionFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      case 1:
      default:
        return null;
      case 0:
        return new CustomSectionView(DiscoverRecommendView.this.getContext());
      case 3:
        return new DiscoverItemRecommendView(DiscoverRecommendView.this.getContext(), this.val$hash);
      case 2:
        return new DiscoverItemRecommendTagView(DiscoverRecommendView.this.getContext(), this.val$hash);
      case 4:
        return new DiscoverCollectionItemView(DiscoverRecommendView.this.getContext(), this.val$hash);
      case 5:
        JDRecommendTagView localJDRecommendTagView = new JDRecommendTagView(DiscoverRecommendView.this.getContext(), this.val$hash);
        localJDRecommendTagView.setListenr(new JDRecommendTagView.OnDeleteListener()
        {
          public void onDelete()
          {
            DiscoverRecommendView.access$002(DiscoverRecommendView.this, null);
            DiscoverRecommendView.this.setData(DiscoverRecommendView.this.mRecommendCategoryNode);
          }
        });
        return localJDRecommendTagView;
      case 6:
      }
      return new JDRecommendItemView(DiscoverRecommendView.this.getContext(), this.val$hash);
    }
  };
  private SwitchView mHeaderView;
  private ListView mListView;
  private LoadingView mLoadingView;
  private RecommendCategoryNode mRecommendCategoryNode;

  public DiscoverRecommendView(Context paramContext)
  {
    super(paramContext);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 0);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 12);
    this.mAdapter = new SectionAdapter(null, this.mFactory);
    this.mListView = new ListView(paramContext);
    this.mHeaderView = new SwitchView(paramContext);
    this.mListView.addHeaderView(this.mHeaderView);
    this.mListView.setDivider(null);
    this.mListView.setSelector(17170445);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mLoadingView = new LoadingView(paramContext);
    addView(this.mLoadingView);
    this.mListView.setEmptyView(this.mLoadingView);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    if ((InfoManager.getInstance().enableJdAd()) && (InfoManager.getInstance().getJdAdPosition() >= 0))
    {
      JDApi.request(new JDApi.OnResponseListener()
      {
        public void onResponse(Response paramAnonymousResponse)
        {
          if (paramAnonymousResponse.getErrorCode() == JDApi.ErrorCode.SUCCESS)
          {
            DiscoverRecommendView.access$002(DiscoverRecommendView.this, (ArrayList)paramAnonymousResponse.getData());
            DiscoverRecommendView.this.setData(DiscoverRecommendView.this.mRecommendCategoryNode);
            ThirdTracker.getInstance().setJDAdv(DiscoverRecommendView.this.mCommodityInfos, true);
          }
        }
      });
      int i = 0;
      while (i < 3)
      {
        QTMSGManage.getInstance().sendStatistcsMessage("jdimpression", "real");
        i += 1;
      }
      ThirdTracker.getInstance().sendThirdTrackLog("ThirdAdv", "1", 0, InfoManager.getInstance().getJdAdPosition(), Constants.ADV_IMPRESSION, 0);
    }
  }

  private List<List<RecommendItemNode>> getCustomCategoryList(List<List<RecommendItemNode>> paramList)
  {
    int k = 0;
    ArrayList localArrayList2 = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
    SparseArray localSparseArray = new SparseArray();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject = InfoManager.getInstance().root().mLocalRecommendCategoryNode;
    if ((localObject != null) && (InfoManager.getInstance().isLocalRecommendInfoAdded()))
    {
      localObject = ((RecommendCategoryNode)localObject).lstRecMain;
      if ((localObject != null) && (((List)localObject).get(0) != null))
      {
        localObject = (List)((List)localObject).get(0);
        if ((localObject != null) && (((List)localObject).size() > 0))
        {
          i = localArrayList2.indexOf(Integer.valueOf(((RecommendItemNode)((List)localObject).get(0)).sectionId));
          if (i < 0)
            break label224;
          localSparseArray.put(i, localObject);
        }
      }
    }
    int i = 0;
    label142: int j;
    if (i < paramList.size())
    {
      localObject = (List)paramList.get(i);
      if (((List)localObject).size() > 0)
      {
        j = localArrayList2.indexOf(Integer.valueOf(((RecommendItemNode)((List)localObject).get(0)).sectionId));
        if (j < 0)
          break label246;
      }
      while (true)
      {
        try
        {
          localSparseArray.put(j, localObject);
          i += 1;
          break label142;
          label224: localArrayList1.add(localObject);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          continue;
        }
        label246: localArrayList1.add(localException);
      }
    }
    paramList = new ArrayList();
    i = 0;
    while (true)
    {
      j = k;
      if (i >= localSparseArray.size())
        break;
      j = localSparseArray.keyAt(i);
      if (localSparseArray.get(j) != null)
        paramList.add(localSparseArray.get(j));
      i += 1;
    }
    while (j < localArrayList1.size())
    {
      if (localArrayList1.get(j) != null)
        paramList.add(localArrayList1.get(j));
      j += 1;
    }
    return paramList;
  }

  private void setData(RecommendCategoryNode paramRecommendCategoryNode)
  {
    if (paramRecommendCategoryNode == null);
    do
    {
      return;
      this.mHeaderView.update("setData", paramRecommendCategoryNode.getLstBanner());
      localList = paramRecommendCategoryNode.lstRecMain;
    }
    while (localList == null);
    paramRecommendCategoryNode = localList;
    if (InfoManager.getInstance().getEnableCustomCategory())
    {
      localObject1 = getCustomCategoryList(localList);
      paramRecommendCategoryNode = localList;
      if (((List)localObject1).size() > 0)
        paramRecommendCategoryNode = (RecommendCategoryNode)localObject1;
    }
    Object localObject1 = new ArrayList();
    List localList = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.getFavouriteNodes();
    if (!InfoManager.getInstance().getEnableFrontCollection())
      localList = null;
    while (true)
    {
      Object localObject2;
      if ((localList != null) && (localList.size() > 0))
      {
        localObject2 = new RecommendItemNode();
        ((RecommendItemNode)localObject2).sectionId = -100;
        ((RecommendItemNode)localObject2).belongName = "我的收藏";
        ((List)localObject1).add(new SectionItem(0, null));
        ((List)localObject1).add(new SectionItem(2, localObject2));
        if (localList.size() >= 2)
          break label773;
      }
      label773: for (int k = localList.size(); ; k = 2)
      {
        long l = SharedCfg.getInstance().getLatestBootstrapTime();
        int i = 0;
        int m = 0;
        int j = i;
        if (m < localList.size())
        {
          j = i;
          if (i < k)
          {
            localObject2 = (ChannelNode)localList.get(m);
            j = i;
            if (((ChannelNode)localObject2).channelType != 1)
              break label606;
            j = i;
            if (((ChannelNode)localObject2).getUpdateTime() <= l)
              break label606;
            i += 1;
            ((List)localObject1).add(new SectionItem(4, localObject2));
            j = i;
            if (i < k)
              break label606;
            j = i;
          }
        }
        m = 0;
        i = j;
        label306: j = i;
        if (m < localList.size())
        {
          j = i;
          if (i < k)
          {
            localObject2 = (ChannelNode)localList.get(m);
            j = i;
            if (((ChannelNode)localObject2).channelType != 0)
              break label619;
            i += 1;
            ((List)localObject1).add(new SectionItem(4, localObject2));
            j = i;
            if (i < k)
              break label619;
            j = i;
          }
        }
        i = 0;
        m = j;
        j = i;
        while (true)
        {
          if ((j < localList.size()) && (m < k))
          {
            localObject2 = (ChannelNode)localList.get(j);
            i = m;
            if (((ChannelNode)localObject2).channelType == 1)
            {
              m += 1;
              ((List)localObject1).add(new SectionItem(4, localObject2));
              i = m;
              if (m < k);
            }
          }
          else
          {
            MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "frontCollection");
            TCAgent.onEvent(InfoManager.getInstance().getContext(), "frontCollection");
            i = 0;
            while (i < paramRecommendCategoryNode.size())
            {
              localList = (List)paramRecommendCategoryNode.get(i);
              if (localList.size() > 0)
              {
                ((List)localObject1).add(new SectionItem(0, null));
                ((List)localObject1).add(new SectionItem(2, localList.get(0)));
                ((List)localObject1).add(new SectionItem(3, localList));
              }
              i += 1;
            }
            label606: m += 1;
            i = j;
            break;
            label619: m += 1;
            i = j;
            break label306;
          }
          j += 1;
          m = i;
        }
        if (this.mCommodityInfos != null)
        {
          j = InfoManager.getInstance().getJdAdPosition() * 3 + 1;
          i = j;
          if (j > ((List)localObject1).size())
            i = ((List)localObject1).size();
          ((List)localObject1).add(i + 0, new SectionItem(5, null));
          ((List)localObject1).add(i + 1, new SectionItem(6, this.mCommodityInfos));
          ((List)localObject1).add(i + 2, new SectionItem(0, null));
        }
        ((List)localObject1).add(new SectionItem(0, null));
        this.mAdapter.setData((List)localObject1);
        return;
      }
    }
  }

  public void close(boolean paramBoolean)
  {
    super.close(paramBoolean);
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 0)
      update("setData", null);
    while (paramInt != 12)
      return;
    update("updatejd", null);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.mListView.getMeasuredWidth(), this.mListView.getMeasuredHeight());
    this.mLoadingView.layout(0, 0, this.mListView.getMeasuredWidth(), this.mListView.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mListView.measure(paramInt1, paramInt2);
    this.mLoadingView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_RECOMMEND_INFO"))
    {
      this.mRecommendCategoryNode = InfoManager.getInstance().root().getRecommendCategoryNode(0);
      if (this.mRecommendCategoryNode != null)
        setData(this.mRecommendCategoryNode);
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mRecommendCategoryNode = InfoManager.getInstance().root().getRecommendCategoryNode(0);
      if (this.mRecommendCategoryNode == null)
        InfoManager.getInstance().loadRecommendInfo(0, this);
    }
    do
    {
      do
      {
        return;
        setData(this.mRecommendCategoryNode);
        return;
      }
      while (!paramString.equalsIgnoreCase("updatejd"));
      paramString = ThirdTracker.getInstance().getJDAdv();
    }
    while ((paramString == null) || (paramString.size() <= 0));
    this.mCommodityInfos = ((ArrayList)paramString);
    this.mAdapter.notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverRecommendView
 * JD-Core Version:    0.6.2
 */