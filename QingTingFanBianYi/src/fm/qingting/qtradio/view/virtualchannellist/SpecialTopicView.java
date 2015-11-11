package fm.qingting.qtradio.view.virtualchannellist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tendcloud.tenddata.TCAgent;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.moreContentView.CustomSectionView;
import fm.qingting.utils.ListUtils;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.List;

public class SpecialTopicView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, UserDataUtil.UserDataResponse
{
  private CustomizedAdapter mAdapter;
  private IAdapterIViewFactory mFactory;
  private HeaderView mHeaderView;
  private LinearLayout mLinearLayout;
  private PullToRefreshListView mListView;
  private SpecialTopicNode mNode;
  private MiniPlayerView mPlayerView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public SpecialTopicView(Context paramContext)
  {
    super(paramContext);
    final int i = hashCode();
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mFactory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new SpecialTopicItemView(SpecialTopicView.this.getContext(), i);
      }
    };
    this.mAdapter = new CustomizedAdapter(new ArrayList(), this.mFactory);
    this.mLinearLayout = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903040, null));
    this.mListView = ((PullToRefreshListView)this.mLinearLayout.findViewById(2131230731));
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setSelector(17170445);
    this.mHeaderView = new HeaderView(paramContext);
    this.mListView.addListHeaderView(this.mHeaderView);
    this.mListView.addListHeaderView(new CustomSectionView(paramContext));
    MiniPlayerPlaceHolder.wrapListView(paramContext, (ListView)this.mListView.getRefreshableView());
    this.mListView.setAdapter(this.mAdapter);
    addView(this.mLinearLayout);
    this.mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener()
    {
      public void onRefresh(PullToRefreshBase<ListView> paramAnonymousPullToRefreshBase)
      {
        if (SpecialTopicView.this.mNode != null)
          InfoManager.getInstance().loadSpecialTopicNode(SpecialTopicView.this.mNode, SpecialTopicView.this);
      }
    });
    this.mPlayerView = new MiniPlayerView(paramContext);
    addView(this.mPlayerView);
    MobclickAgent.onEvent(paramContext, "enterTopicView");
    TCAgent.onEvent(paramContext, "enterTopicView");
  }

  public void close(boolean paramBoolean)
  {
    this.mPlayerView.destroy();
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RECV_SPECIAL_TOPIC_CHANNELS" });
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLinearLayout.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mPlayerView);
    this.mLinearLayout.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_SPECIAL_TOPIC_CHANNELS"))
    {
      paramString = ChannelHelper.getInstance().getLstChannelsByKey(this.mNode.getKey());
      if ((paramString != null) && (paramString.size() > 0))
      {
        UserDataUtil.request(this, paramString);
        this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
        this.mListView.onRefreshComplete();
      }
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onResponse()
  {
    List localList = ChannelHelper.getInstance().getLstChannelsByKey(this.mNode.getKey());
    if (localList != null)
      this.mAdapter.setData(ListUtils.convertToObjectList(localList));
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((SpecialTopicNode)paramObject);
      this.mHeaderView.update(paramString, paramObject);
      paramString = ChannelHelper.getInstance().getLstChannelsByKey(this.mNode.getKey());
      if (paramString == null)
        this.mListView.setRefreshing();
    }
    else
    {
      return;
    }
    this.mAdapter.setData(ListUtils.convertToObjectList(paramString));
    this.mListView.onRefreshComplete();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannellist.SpecialTopicView
 * JD-Core Version:    0.6.2
 */