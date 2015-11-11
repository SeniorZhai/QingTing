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
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.moreContentView.CustomSectionView;
import fm.qingting.qtradio.view.switchview.SwitchView;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DiscoverNovelView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, IMediaVListener, UserDataUtil.UserDataResponse
{
  private AdvertisementItemNode mAdNode;
  private SectionAdapter mAdapter;
  private NovelCategoryView mCategoryView;
  private ISectionFactory mFactory;
  private SwitchView mHeaderView;
  private ListView mListView;
  private long mMediaVUpdateTime = 0L;
  private CategoryNode mNode;
  private RecommendCategoryNode mRecommendCategoryNode;
  private AdPos mSectionPos;
  private AdvertisementItemNode3rdParty mThirdPartyAd;

  public DiscoverNovelView(Context paramContext)
  {
    super(paramContext);
    final int i = hashCode();
    this.mFactory = new ISectionFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        case 4:
        case 5:
        case 6:
        default:
          return null;
        case 0:
          return new CustomSectionView(DiscoverNovelView.this.getContext());
        case 1:
          return new DiscoverItemAllView(DiscoverNovelView.this.getContext(), i);
        case 3:
          return new DiscoverItemOrdinaryViewV2(DiscoverNovelView.this.getContext(), i);
        case 2:
          return new DiscoverItemTagView(DiscoverNovelView.this.getContext(), i);
        case 7:
          localDiscoverAdItemView = new DiscoverAdItemView(DiscoverNovelView.this.getContext(), i);
          localDiscoverAdItemView.setListener(new DiscoverAdItemView.OnDeleteListener()
          {
            public void onDelete()
            {
              DiscoverNovelView.this.mListView.setOnScrollListener(null);
              DiscoverNovelView.access$102(DiscoverNovelView.this, null);
              DiscoverNovelView.access$202(DiscoverNovelView.this, null);
              DiscoverNovelView.this.setData(DiscoverNovelView.this.mRecommendCategoryNode);
            }
          });
          return localDiscoverAdItemView;
        case 8:
        }
        DiscoverAdItemView localDiscoverAdItemView = new DiscoverAdItemView(DiscoverNovelView.this.getContext(), i);
        localDiscoverAdItemView.setListener(new DiscoverAdItemView.OnDeleteListener()
        {
          public void onDelete()
          {
            DiscoverNovelView.this.mListView.setOnScrollListener(null);
            DiscoverNovelView.access$102(DiscoverNovelView.this, null);
            DiscoverNovelView.access$502(DiscoverNovelView.this, null);
            DiscoverNovelView.this.setData(DiscoverNovelView.this.mRecommendCategoryNode);
          }
        });
        return localDiscoverAdItemView;
      }
    };
    this.mAdapter = new SectionAdapter(null, this.mFactory);
    this.mListView = new ListView(paramContext);
    this.mHeaderView = new SwitchView(paramContext);
    this.mListView.addHeaderView(this.mHeaderView);
    this.mListView.setDivider(null);
    this.mListView.setSelector(17170445);
    this.mListView.addFooterView(new NovelTagView(paramContext, i));
    this.mCategoryView = new NovelCategoryView(paramContext);
    this.mListView.addFooterView(this.mCategoryView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mListView);
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (DiscoverNovelView.this.mAdNode == null)
          DiscoverNovelView.this.load3rdPartyAd();
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
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
          break label291;
        localArrayList.add(new SectionItem(7, this.mAdNode));
      }
      while (true)
      {
        i += 1;
        break;
        label291: if ((this.mThirdPartyAd != null) && (i == 1))
          localArrayList.add(new SectionItem(8, this.mThirdPartyAd));
      }
    }
    this.mAdapter.setData(localArrayList);
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

  public void onMediaVNativeAdResponse(MediaVRequest paramMediaVRequest, ArrayList<MediaVNativeAd> paramArrayList)
  {
    if ((this.mNode != null) && (this.mNode.name != null) && (this.mNode.name.equals(paramMediaVRequest.getRequestId())) && (paramArrayList != null))
    {
      paramMediaVRequest = paramArrayList.iterator();
      while (paramMediaVRequest.hasNext())
      {
        this.mThirdPartyAd = new AdvertisementItemNode3rdParty((MediaVNativeAd)paramMediaVRequest.next());
        setData(this.mRecommendCategoryNode);
      }
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
      do
      {
        return;
        if (!paramString.equalsIgnoreCase("RADI"))
          break;
        this.mAdNode = InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(this.mSectionPos.posid);
        setData(this.mRecommendCategoryNode);
      }
      while (this.mAdNode != null);
      load3rdPartyAd();
      return;
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
      this.mRecommendCategoryNode = InfoManager.getInstance().root().getRecommendCategoryNode(this.mNode.sectionId);
      if (this.mRecommendCategoryNode != null)
        break label146;
      InfoManager.getInstance().loadRecommendInfo(this.mNode.sectionId, this);
    }
    while (true)
    {
      this.mCategoryView.update("setData", this.mNode);
      this.mSectionPos = InfoManager.getInstance().root().mAdvertisementInfoNode.getSection(this.mNode.categoryId);
      if (this.mSectionPos != null)
      {
        this.mAdNode = InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(this.mSectionPos.posid);
        if (this.mAdNode == null)
          InfoManager.getInstance().loadAdvertisement(this.mSectionPos, -1, this);
      }
      return;
      label146: setData(this.mRecommendCategoryNode);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverNovelView
 * JD-Core Version:    0.6.2
 */