package fm.qingting.qtradio.view.frontpage.discover;

import android.app.Activity;
import android.content.Context;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.ad.AdPos;
import fm.qingting.qtradio.ad.platforms.mediav.MediaVAgent;
import fm.qingting.qtradio.ad.platforms.mediav.model.IMediaVListener;
import fm.qingting.qtradio.ad.platforms.mediav.model.MediaVNativeAd;
import fm.qingting.qtradio.ad.platforms.mediav.model.MediaVRequest;
import fm.qingting.qtradio.jd.view.JDItemView;
import fm.qingting.qtradio.jd.view.JDRecommendTagView;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.AdvertisementItemNode3rdParty;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.LoadingView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.moreContentView.CustomSectionView;
import fm.qingting.qtradio.view.switchview.SwitchView;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.List;

public class DiscoverColumnView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, IMediaVListener, UserDataUtil.UserDataResponse
{
  private static final int AD_POS = 1;
  private AdvertisementItemNode mAdNode;
  private SectionAdapter mAdapter = new SectionAdapter(null, this.mFactory);
  private LoadingView mEmptyTipsView;
  private ISectionFactory mFactory = new ISectionFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      case 4:
      default:
        return null;
      case 0:
        return new CustomSectionView(DiscoverColumnView.this.getContext());
      case 1:
        return new DiscoverItemAllView(DiscoverColumnView.this.getContext(), this.val$hash);
      case 3:
        return new DiscoverItemOrdinaryViewV2(DiscoverColumnView.this.getContext(), this.val$hash);
      case 2:
        return new DiscoverItemTagView(DiscoverColumnView.this.getContext(), this.val$hash);
      case 5:
        localObject = new JDRecommendTagView(DiscoverColumnView.this.getContext(), this.val$hash);
        ((JDRecommendTagView)localObject).hideTag();
        return localObject;
      case 6:
        return new JDItemView(DiscoverColumnView.this.getContext(), this.val$hash);
      case 7:
        localObject = new DiscoverAdItemView(DiscoverColumnView.this.getContext(), this.val$hash);
        ((DiscoverAdItemView)localObject).setListener(new DiscoverAdItemView.OnDeleteListener()
        {
          public void onDelete()
          {
            DiscoverColumnView.this.mListView.setOnScrollListener(null);
            DiscoverColumnView.access$102(DiscoverColumnView.this, null);
            DiscoverColumnView.access$202(DiscoverColumnView.this, null);
            DiscoverColumnView.this.setData(DiscoverColumnView.this.mRecommendCategoryNode);
          }
        });
        return localObject;
      case 8:
      }
      Object localObject = new DiscoverAdItemView(DiscoverColumnView.this.getContext(), this.val$hash);
      ((DiscoverAdItemView)localObject).setListener(new DiscoverAdItemView.OnDeleteListener()
      {
        public void onDelete()
        {
          DiscoverColumnView.this.mListView.setOnScrollListener(null);
          DiscoverColumnView.access$502(DiscoverColumnView.this, null);
          DiscoverColumnView.this.setData(DiscoverColumnView.this.mRecommendCategoryNode);
        }
      });
      return localObject;
    }
  };
  private SwitchView mHeaderView;
  private ListView mListView;
  private long mMediaVUpdateTime = 0L;
  private CategoryNode mNode;
  private RecommendCategoryNode mRecommendCategoryNode;
  private AdPos mSectionPos;
  private AdvertisementItemNode3rdParty mThirdPartyAd;

  public DiscoverColumnView(Context paramContext)
  {
    super(paramContext);
    this.mListView = new ListView(paramContext);
    this.mHeaderView = new SwitchView(paramContext);
    this.mListView.addHeaderView(this.mHeaderView);
    this.mListView.setDivider(null);
    this.mListView.setSelector(17170445);
    this.mEmptyTipsView = new LoadingView(paramContext);
    addView(this.mEmptyTipsView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setEmptyView(this.mEmptyTipsView);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        if (DiscoverColumnView.this.mAdNode == null)
          DiscoverColumnView.this.load3rdPartyAd();
      }
    });
  }

  private void load3rdPartyAd()
  {
    loadMediaVAd();
  }

  private void loadMediaVAd()
  {
    long l = System.currentTimeMillis() / 1000L;
    if ((this.mNode == null) || (l - this.mMediaVUpdateTime < 50L));
    while (true)
    {
      return;
      if (MediaVAgent.isSectionEnabled(getContext(), this.mNode.sectionId))
      {
        this.mMediaVUpdateTime = l;
        Object localObject = this.mSectionPos;
        if (localObject == null)
        {
          localObject = new AdPos();
          ((AdPos)localObject).adtype = 5;
        }
        while (localObject != null)
        {
          Activity localActivity = (Activity)getContext();
          MediaVAgent.getInstance(localActivity).addListener(this);
          String str1 = this.mNode.name;
          int i = this.mNode.categoryId;
          String str2 = this.mNode.name;
          localObject = new MediaVRequest((AdPos)localObject, new String[] { str1 }, i, str2);
          MediaVAgent.getInstance(localActivity).loadAds((MediaVRequest)localObject);
          return;
        }
      }
    }
  }

  private void requetUserData()
  {
    if (this.mRecommendCategoryNode == null);
    List localList;
    do
    {
      return;
      localList = this.mRecommendCategoryNode.lstRecMain;
    }
    while (localList == null);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localList.size())
    {
      int j = 0;
      while (j < ((List)localList.get(i)).size())
      {
        RecommendItemNode localRecommendItemNode = (RecommendItemNode)((List)localList.get(i)).get(j);
        if (((localRecommendItemNode.mNode instanceof ChannelNode)) || ((localRecommendItemNode.mNode instanceof ProgramNode)))
          localArrayList.add(((List)localList.get(i)).get(j));
        j += 1;
      }
      i += 1;
    }
    UserDataUtil.request(this, localArrayList);
  }

  private void setData(RecommendCategoryNode paramRecommendCategoryNode)
  {
    if (paramRecommendCategoryNode == null);
    do
    {
      return;
      this.mHeaderView.update("setData", paramRecommendCategoryNode.getLstBanner());
      paramRecommendCategoryNode = paramRecommendCategoryNode.lstRecMain;
    }
    while (paramRecommendCategoryNode == null);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new SectionItem(0, null));
    localArrayList.add(new SectionItem(1, this.mNode));
    if ((this.mSectionPos != null) && (this.mSectionPos.sectionPosition >= paramRecommendCategoryNode.size()))
      this.mSectionPos.sectionPosition = (paramRecommendCategoryNode.size() - 1);
    int i = 0;
    if (i < paramRecommendCategoryNode.size())
    {
      List localList = (List)paramRecommendCategoryNode.get(i);
      if (localList.size() > 0)
      {
        localArrayList.add(new SectionItem(0, null));
        localArrayList.add(new SectionItem(2, localList.get(0)));
        int k = Math.min(3, localList.size());
        int j = 0;
        while (j < k)
        {
          localArrayList.add(new SectionItem(3, localList.get(j)));
          j += 1;
        }
        if ((this.mSectionPos == null) || (this.mSectionPos.sectionPosition != i) || (this.mAdNode == null))
          break label290;
        localArrayList.add(new SectionItem(7, this.mAdNode));
      }
      while (true)
      {
        i += 1;
        break;
        label290: if ((this.mThirdPartyAd != null) && (i == 1))
          localArrayList.add(new SectionItem(8, this.mThirdPartyAd));
      }
    }
    localArrayList.add(new SectionItem(0, null));
    this.mAdapter.setData(localArrayList);
  }

  public void close(boolean paramBoolean)
  {
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("headOffset"))
      return Integer.valueOf(this.mHeaderView.getBottom());
    return super.getValue(paramString, paramObject);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.mListView.getMeasuredWidth(), this.mListView.getMeasuredHeight());
    this.mEmptyTipsView.layout(0, 0, this.mListView.getMeasuredWidth(), this.mListView.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mEmptyTipsView.measure(paramInt1, paramInt2);
    this.mListView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void onMediaVNativeAdResponse(MediaVRequest paramMediaVRequest, ArrayList<MediaVNativeAd> paramArrayList)
  {
    if ((this.mNode != null) && (this.mNode.name != null) && (this.mNode.name.equals(paramMediaVRequest.getRequestId())) && (paramArrayList != null) && (paramArrayList.size() > 0))
    {
      this.mThirdPartyAd = new AdvertisementItemNode3rdParty((MediaVNativeAd)paramArrayList.get(0));
      setData(this.mRecommendCategoryNode);
    }
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_RECOMMEND_INFO"))
    {
      this.mRecommendCategoryNode = InfoManager.getInstance().root().getRecommendCategoryNode(this.mNode.sectionId);
      if (this.mRecommendCategoryNode != null)
      {
        setData(this.mRecommendCategoryNode);
        requetUserData();
      }
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("RADI"))
      {
        this.mAdNode = InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(this.mSectionPos.posid);
        if (this.mAdNode == null)
          load3rdPartyAd();
        setData(this.mRecommendCategoryNode);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("RECV_EMPTY_AD"));
    load3rdPartyAd();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onResponse()
  {
    setData(this.mRecommendCategoryNode);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((CategoryNode)paramObject);
      this.mSectionPos = InfoManager.getInstance().root().mAdvertisementInfoNode.getSection(this.mNode.categoryId);
      if (this.mSectionPos != null)
      {
        this.mAdNode = InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(this.mSectionPos.posid);
        if (this.mAdNode == null)
          InfoManager.getInstance().loadAdvertisement(this.mSectionPos, -1, this);
      }
      this.mRecommendCategoryNode = InfoManager.getInstance().root().getRecommendCategoryNode(this.mNode.sectionId);
      if (this.mRecommendCategoryNode == null)
        InfoManager.getInstance().loadRecommendInfo(this.mNode.sectionId, this);
    }
    else
    {
      return;
    }
    setData(this.mRecommendCategoryNode);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverColumnView
 * JD-Core Version:    0.6.2
 */