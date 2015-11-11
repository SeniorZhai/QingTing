package fm.qingting.qtradio.view.channelcategoryview;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PlayingProgramInfoNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.LoadingView;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List<Lfm.qingting.qtradio.model.Node;>;

public class RadioChannelView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener
{
  private CustomizedAdapter adapter;
  private IAdapterIViewFactory factory;
  private ListView mListView;
  private LoadingView mLoadingView;
  private MiniPlayerView mMiniView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public RadioChannelView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new RadioChannelItemView(RadioChannelView.this.getContext(), this.val$hash);
      }
    };
    this.adapter = new CustomizedAdapter(new ArrayList(), this.factory);
    this.mListView = new ListView(paramContext);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mLoadingView = new LoadingView(paramContext);
    addView(this.mLoadingView);
    this.mListView.setEmptyView(this.mLoadingView);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    this.mMiniView = new MiniPlayerView(paramContext);
    addView(this.mMiniView);
  }

  private void invalidateCurrentPlayingProgram()
  {
    int j = this.mListView.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = this.mListView.getChildAt(i);
      if ((localView != null) && ((localView instanceof IView)))
        ((IView)localView).update("ip", null);
      i += 1;
    }
  }

  private void loadPlayingProgram(List<Node> paramList)
  {
    if (paramList == null)
      return;
    Object localObject = "";
    Iterator localIterator = paramList.iterator();
    paramList = (List<Node>)localObject;
    label17: int i;
    if (localIterator.hasNext())
    {
      localObject = (Node)localIterator.next();
      if (((Node)localObject).nodeName.equalsIgnoreCase("channel"))
        i = ((ChannelNode)localObject).channelId;
    }
    while (true)
    {
      label57: if (!InfoManager.getInstance().root().mPlayingProgramInfo.isExist(i))
        if (paramList.equalsIgnoreCase(""))
          paramList = paramList + i;
      while (true)
      {
        break label17;
        if (!((Node)localObject).nodeName.equalsIgnoreCase("radiochannel"))
          break label179;
        i = ((RadioChannelNode)localObject).channelId;
        break label57;
        paramList = paramList + "," + i;
        continue;
        if (paramList.equalsIgnoreCase(""))
          break;
        InfoManager.getInstance().loadCurrentPlayingPrograms(paramList, this);
        return;
      }
      label179: i = 0;
    }
  }

  public void close(boolean paramBoolean)
  {
    this.mMiniView.destroy();
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RCPPL" });
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mLoadingView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mMiniView.layout(0, this.standardLayout.height - this.mMiniView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.standardLayout.measureView(this.mMiniView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    this.mLoadingView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RCPPL"))
      invalidateCurrentPlayingProgram();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (List)paramObject;
      this.adapter.setData(ListUtils.convertToObjectList(paramString));
      loadPlayingProgram(paramString);
    }
    while (!paramString.equalsIgnoreCase("refresh"))
      return;
    this.adapter.notifyDataSetChanged();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.channelcategoryview.RadioChannelView
 * JD-Core Version:    0.6.2
 */