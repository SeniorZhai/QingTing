package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.ad.AdPos;
import fm.qingting.qtradio.baidu.BDResponse;
import fm.qingting.qtradio.baidu.BaiduApi;
import fm.qingting.qtradio.baidu.BaiduApi.BDResponseListener;
import fm.qingting.utils.ThirdAdv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendCategoryNode extends Node
{
  private transient boolean hasInsertedBannerAdvertisement = false;
  private transient boolean hasLoadAudioAdvertisement = false;
  private transient boolean hasRestored = false;
  private transient boolean hasRestoredSucc = false;
  public transient boolean hasUpdate = false;
  private List<RecommendItemNode> lstBanner = new ArrayList();
  public List<List<RecommendItemNode>> lstRecMain = new ArrayList();
  public String name = "";
  public int sectionId = -1;

  public RecommendCategoryNode()
  {
    this.nodeName = "recommendcategory";
  }

  public void KeepNewRecommendItem(RecommendItemNode paramRecommendItemNode, int paramInt)
  {
    if (paramInt == 1)
    {
      if (this.lstRecMain.size() > 0)
      {
        this.lstRecMain.clear();
        paramRecommendItemNode.parent = this;
        ((List)this.lstRecMain.get(0)).add(paramRecommendItemNode);
        return;
      }
      ArrayList localArrayList = new ArrayList();
      paramRecommendItemNode.parent = this;
      localArrayList.add(paramRecommendItemNode);
      this.lstRecMain.add(localArrayList);
      return;
    }
    if (this.lstBanner.size() > 0)
    {
      this.lstBanner.clear();
      paramRecommendItemNode.parent = this;
      this.lstBanner.add(paramRecommendItemNode);
      return;
    }
    paramRecommendItemNode.parent = this;
    this.lstBanner.add(paramRecommendItemNode);
  }

  public List<RecommendItemNode> getLstBanner()
  {
    insertBannerAdvertisement();
    loadAudioAdvertisement();
    int i = InfoManager.getInstance().root().getCatIdBySecId(this.sectionId);
    insertAdvFromThirdParty(ThirdAdv.getInstance().getRecommendNodes(i));
    return this.lstBanner;
  }

  public void insertAdvFromThirdParty(List<RecommendItemNode> paramList)
  {
    int i = 0;
    if (paramList == null);
    int k;
    do
    {
      do
        return;
      while ((this.lstBanner == null) || (this.lstBanner.size() <= 0) || (paramList.size() <= 0));
      paramList = (RecommendItemNode)paramList.get(0);
      k = InfoManager.getInstance().getAirwaveBanner();
    }
    while (this.lstBanner.get(this.lstBanner.size() - 1) == paramList);
    while ((i < this.lstBanner.size()) && (i >= 0))
    {
      int j = i;
      if (this.lstBanner.get(i) == paramList)
      {
        this.lstBanner.remove(i);
        j = i - 1;
      }
      i = j + 1;
    }
    if (k >= this.lstBanner.size())
    {
      this.lstBanner.add(paramList);
      return;
    }
    if (k >= 1)
    {
      if ((!((RecommendItemNode)this.lstBanner.get(k)).isAds) && (!((RecommendItemNode)this.lstBanner.get(k - 1)).isAds))
      {
        this.lstBanner.add(k, paramList);
        return;
      }
      this.lstBanner.add(paramList);
      return;
    }
    this.lstBanner.add(paramList);
  }

  public boolean insertBannerAdvertisement()
  {
    final int j;
    Object localObject1;
    int k;
    int i;
    label121: int m;
    Object localObject2;
    if ((this.lstBanner != null) && (this.lstBanner.size() > 0) && (!this.hasInsertedBannerAdvertisement))
    {
      int i2 = this.lstBanner.size();
      if (isFrontpage())
      {
        j = 0;
        localObject1 = ThirdAdv.getInstance().getRecommendNodes(j);
        if ((localObject1 == null) || (((List)localObject1).size() == 0))
        {
          InfoManager.getInstance().loadAdvFromThirdParty(j);
          if ((InfoManager.getInstance().enableAdvBaidu()) && (InfoManager.getInstance().enableAdvBaiduCategory(j)))
            BaiduApi.request(new BaiduApi.BDResponseListener()
            {
              public void onResponse(BDResponse paramAnonymousBDResponse)
              {
                if (paramAnonymousBDResponse != null)
                {
                  paramAnonymousBDResponse = paramAnonymousBDResponse.getAdvNodes();
                  if ((paramAnonymousBDResponse != null) && (paramAnonymousBDResponse.size() > 0))
                  {
                    ThirdAdv.getInstance().setAdv(((AdvertisementItemNode)paramAnonymousBDResponse.get(0)).convertToRecommendItem(j), j, "4");
                    RecommendCategoryNode.this.insertAdvFromThirdParty(ThirdAdv.getInstance().getRecommendNodes(j));
                  }
                }
              }
            });
        }
        if (j < 0)
          break label510;
        k = 0;
        i = 0;
        m = i;
        if (k > i2)
          break label513;
        localObject1 = InfoManager.getInstance().root().mAdvertisementInfoNode.getBannerAdPos(j, k);
        if (localObject1 == null)
          break label533;
        ((AdPos)localObject1).parent = this;
        localObject2 = InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(((AdPos)localObject1).posid);
        if (localObject2 == null)
          break label476;
        localObject2 = ((AdvertisementItemNode)localObject2).convertToRecommendItem(j);
        if (localObject2 != null)
        {
          String str1 = ((RecommendItemNode)localObject2).getApproximativeThumb();
          m = 0;
          label198: if (m >= this.lstBanner.size())
            break label527;
          String str2 = ((RecommendItemNode)this.lstBanner.get(m)).getApproximativeThumb();
          if ((!((RecommendItemNode)this.lstBanner.get(m)).isAds) || (str2 == null) || (str1 == null) || (!str2.equalsIgnoreCase(str1)))
            break label348;
          m = 1;
          label272: if (m == 0)
          {
            if ((((AdPos)localObject1).bannerPos >= this.lstBanner.size()) || (((AdPos)localObject1).bannerPos < 0))
              break label462;
            if (((AdPos)localObject1).bannerPos != 0)
              break label357;
            this.lstBanner.add(((AdPos)localObject1).bannerPos, localObject2);
          }
        }
      }
    }
    label513: label527: label533: 
    while (true)
    {
      k += 1;
      break label121;
      j = InfoManager.getInstance().root().getCatIdBySecId(this.sectionId);
      break;
      label348: m += 1;
      break label198;
      label357: m = 0;
      int i1;
      for (int n = 0; ; n = i1)
      {
        if (m >= this.lstBanner.size())
          break label460;
        i1 = n;
        if (!((RecommendItemNode)this.lstBanner.get(m)).isAds)
          i1 = n + 1;
        if ((i1 == ((AdPos)localObject1).bannerPos) && (m + 1 < this.lstBanner.size()))
        {
          this.lstBanner.add(m + 1, localObject2);
          break;
        }
        m += 1;
      }
      label460: continue;
      label462: this.lstBanner.add(localObject2);
      continue;
      label476: InfoManager.getInstance().loadAdvertisement((AdPos)localObject1, -1, null);
      if (ThirdAdv.getInstance().getRecommendNodes(j) == null)
        InfoManager.getInstance().loadAdvFromThirdParty(j);
      i = 1;
      continue;
      label510: m = 0;
      if (m == 0)
      {
        this.hasInsertedBannerAdvertisement = true;
        return true;
      }
      return false;
      m = 0;
      break label272;
    }
  }

  public void insertRecCategory(RecommendItemNode paramRecommendItemNode, int paramInt)
  {
    int i = 0;
    if (paramInt == 1)
    {
      paramRecommendItemNode.categoryPos = 1;
      paramInt = 0;
      while (paramInt < this.lstRecMain.size())
      {
        if (((RecommendItemNode)((List)this.lstRecMain.get(paramInt)).get(0)).sectionId == paramRecommendItemNode.sectionId)
        {
          paramRecommendItemNode.parent = this;
          ((List)this.lstRecMain.get(paramInt)).add(paramRecommendItemNode);
          return;
        }
        paramInt += 1;
      }
      ArrayList localArrayList = new ArrayList();
      paramRecommendItemNode.parent = this;
      localArrayList.add(paramRecommendItemNode);
      this.lstRecMain.add(localArrayList);
      return;
    }
    paramRecommendItemNode.categoryPos = 0;
    paramInt = i;
    while (paramInt < this.lstBanner.size())
    {
      if (((RecommendItemNode)this.lstBanner.get(paramInt)).sectionId == paramRecommendItemNode.sectionId)
      {
        paramRecommendItemNode.parent = this;
        this.lstBanner.add(paramRecommendItemNode);
        return;
      }
      paramInt += 1;
    }
    paramRecommendItemNode.parent = this;
    this.lstBanner.add(paramRecommendItemNode);
  }

  public boolean isFrontpage()
  {
    return this.sectionId == 0;
  }

  public void loadAudioAdvertisement()
  {
    if ((this.hasLoadAudioAdvertisement) || (isFrontpage()));
    int i;
    do
    {
      return;
      int k = InfoManager.getInstance().root().getCatIdBySecId(this.sectionId);
      int j = 0;
      i = j;
      if (k > 0)
      {
        AdPos localAdPos = InfoManager.getInstance().root().mAdvertisementInfoNode.getAudioAdPos(k, 4);
        i = j;
        if (localAdPos != null)
        {
          i = j;
          if (InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(localAdPos.posid) == null)
          {
            InfoManager.getInstance().loadAdvertisement(localAdPos, -1, null);
            i = 1;
          }
        }
      }
    }
    while (i != 0);
    this.hasLoadAudioAdvertisement = true;
  }

  public boolean restoreFromDB()
  {
    RecommendCategoryNode localRecommendCategoryNode = null;
    if (this.hasRestored)
      return this.hasRestoredSucc;
    this.hasRestored = true;
    Object localObject = new HashMap();
    ((Map)localObject).put("id", Integer.valueOf(this.sectionId));
    localObject = DataManager.getInstance().getData("getdb_reccategory_node", null, (Map)localObject).getResult();
    if (((Result)localObject).getSuccess())
      localRecommendCategoryNode = (RecommendCategoryNode)((Result)localObject).getData();
    if (localRecommendCategoryNode != null)
    {
      this.lstBanner = localRecommendCategoryNode.lstBanner;
      this.lstRecMain = localRecommendCategoryNode.lstRecMain;
    }
    this.hasRestoredSucc = true;
    return true;
  }

  public void updateToDB()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("id", Integer.valueOf(this.sectionId));
    if (this.lstRecMain.size() > 0)
      localHashMap.put("main", this.lstRecMain);
    if (this.lstBanner.size() > 0)
      localHashMap.put("banner", this.lstBanner);
    DataManager.getInstance().getData("updatedb_reccategory_node", null, localHashMap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.RecommendCategoryNode
 * JD-Core Version:    0.6.2
 */