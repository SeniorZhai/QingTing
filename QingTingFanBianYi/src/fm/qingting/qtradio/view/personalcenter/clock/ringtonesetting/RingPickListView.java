package fm.qingting.qtradio.view.personalcenter.clock.ringtonesetting;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.PlayHistoryInfoNode;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.SingleCheckAdapter;
import fm.qingting.qtradio.view.personalcenter.clock.AlarmTagView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RingPickListView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final int MAX_HISTORY = 5;
  private SingleCheckAdapter adapter;
  private int channelId = 0;
  private Node extraNode;
  private IAdapterIViewFactory factory;
  private ListView mListView;
  private AlarmTagView mTagView;
  private List<Object> names;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(480, 50, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public RingPickListView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new RingPickItemView(RingPickListView.this.getContext(), this.val$hash);
      }
    };
    this.adapter = new SingleCheckAdapter(new ArrayList(), this.factory);
    this.adapter.setEventHandler(this);
    this.mListView = new ListView(paramContext);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    this.mTagView = new AlarmTagView(paramContext);
    this.mTagView.setTagName("您可以选择从收藏电台添加");
    addView(this.mTagView);
  }

  private void addDefaultChannel()
  {
    int j;
    if ((this.extraNode != null) && (this.extraNode.nodeName.equalsIgnoreCase("channel")))
    {
      j = ((ChannelNode)this.extraNode).channelId;
      if (!InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(j))
        this.names.add(0, this.extraNode);
    }
    while (true)
    {
      Object localObject1 = new ChannelNode();
      ((ChannelNode)localObject1).channelId = 386;
      ((ChannelNode)localObject1).title = "CNR中国之声";
      ((ChannelNode)localObject1).categoryId = 54;
      if ((j != 386) && (!InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted((Node)localObject1)))
        this.names.add(localObject1);
      localObject1 = new ChannelNode();
      ((ChannelNode)localObject1).channelId = 1006;
      ((ChannelNode)localObject1).title = "CRI轻松调频";
      ((ChannelNode)localObject1).categoryId = 54;
      if ((j != 1006) && (!InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted((Node)localObject1)))
        this.names.add(localObject1);
      localObject1 = new ChannelNode();
      ((ChannelNode)localObject1).channelId = 4935;
      ((ChannelNode)localObject1).title = "iRadio音乐台iPlay";
      ((ChannelNode)localObject1).categoryId = 60;
      if ((j != 4935) && (!InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted((Node)localObject1)))
        this.names.add(localObject1);
      if ((InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes() == null) || (InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes().size() == 0))
        return;
      localObject1 = ",";
      Iterator localIterator = InfoManager.getInstance().root().mPersonalCenterNode.playHistoryNode.getPlayHistoryNodes().iterator();
      int i = 0;
      label332: 
      while ((localIterator.hasNext()) && (i < 5))
      {
        Object localObject2 = (PlayHistoryNode)localIterator.next();
        if ((((PlayHistoryNode)localObject2).playNode == null) || (!((PlayHistoryNode)localObject2).playNode.nodeName.equalsIgnoreCase("program")))
          break label592;
        ChannelNode localChannelNode = new ChannelNode();
        if (((PlayHistoryNode)localObject2).channelName != null)
        {
          localChannelNode.channelId = ((PlayHistoryNode)localObject2).channelId;
          localChannelNode.categoryId = ((PlayHistoryNode)localObject2).categoryId;
          localChannelNode.title = ((PlayHistoryNode)localObject2).channelName;
          if (((PlayHistoryNode)localObject2).playNode != null)
            localChannelNode.channelType = ((ProgramNode)((PlayHistoryNode)localObject2).playNode).channelType;
          if ((((PlayHistoryNode)localObject2).channelId == j) || (((PlayHistoryNode)localObject2).channelId != 386) || (((PlayHistoryNode)localObject2).channelId == 1006) || (((PlayHistoryNode)localObject2).channelId == 4935) || (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(localChannelNode)))
            break label592;
          localObject2 = "," + ((PlayHistoryNode)localObject2).channelId + ",";
          if (((String)localObject1).contains((CharSequence)localObject2))
            break label592;
          this.names.add(localChannelNode);
          i += 1;
          localObject1 = (String)localObject1 + (String)localObject2;
        }
      }
      label592: 
      while (true)
      {
        break label332;
        break;
      }
      j = 0;
    }
  }

  private void findSelected()
  {
    int i = 0;
    if (i < this.names.size())
      if (this.channelId != ((ChannelNode)this.names.get(i)).channelId);
    while (true)
    {
      this.adapter.setData(this.names);
      if (i > -1)
        this.adapter.checkIndex(i);
      return;
      i += 1;
      break;
      i = -1;
    }
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      if (paramObject1.type.equalsIgnoreCase("itemClick"))
      {
        int i = paramObject1.position;
        this.adapter.checkIndex(i);
        dispatchActionEvent("select", paramObject1.param);
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mTagView.layout(0, 0, this.standardLayout.width, this.mTagView.getMeasuredHeight());
    this.mListView.layout(0, this.mTagView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.measureView(this.mTagView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.mTagView.getMeasuredHeight(), 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.names = new ArrayList((List)paramObject);
      addDefaultChannel();
      findSelected();
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("setRingtone"))
      {
        this.channelId = ((Integer)paramObject).intValue();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setRingChannel"));
    this.extraNode = ((Node)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.ringtonesetting.RingPickListView
 * JD-Core Version:    0.6.2
 */