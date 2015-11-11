package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
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
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IPlayInfoEventListener;
import fm.qingting.utils.ListUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SchedulePopView extends ViewGroupViewImpl
  implements IEventHandler, InfoManager.ISubscribeEventListener, RootNode.IPlayInfoEventListener
{
  private CustomizedAdapter adapter = new CustomizedAdapter(new ArrayList(), this.factory);
  private IAdapterIViewFactory factory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new SchedulePopItemView(SchedulePopView.this.getContext(), this.val$hash);
    }
  };
  private final ViewLayout listLayout = this.standardLayout.createChildLT(480, 300, 0, 200, ViewLayout.SCALE_FLAG_SLTCW);
  private ListView mListView;
  private HashSet<Integer> mLoadedPosition = new HashSet();
  private ChannelNode mNode;
  private int mSchedulePopViewBottom = 0;
  private SchedulePopHeaderView mTitleView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(480, 68, 0, 200, ViewLayout.SCALE_FLAG_SLTCW);

  public SchedulePopView(Context paramContext)
  {
    super(paramContext);
    this.adapter.setEventHandler(this);
    this.mListView = new ListView(paramContext);
    this.mListView.setBackgroundColor(SkinManager.getBackgroundColor());
    this.mListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if ((paramAnonymousInt3 - 10 > 0) && (paramAnonymousInt1 + paramAnonymousInt2 > paramAnonymousInt3 - 10))
          SchedulePopView.this.loadMore(paramAnonymousInt3);
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
      }
    });
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    this.mTitleView = new SchedulePopHeaderView(paramContext);
    this.mTitleView.setEventHandler(this);
    addView(this.mTitleView);
    InfoManager.getInstance().root().registerSubscribeEventListener(this, 1);
  }

  private int getToday()
  {
    int j = Calendar.getInstance().get(7);
    int i = j;
    if (j == 0)
      i = 7;
    j = i - 1;
    if (j < 1);
    j = i + 1;
    if (j > 7);
    return i;
  }

  private void loadMore(int paramInt)
  {
    if (this.mNode == null);
    while ((this.mLoadedPosition.contains(Integer.valueOf(paramInt))) || (!this.mNode.nodeName.equalsIgnoreCase("channel")))
      return;
    this.mLoadedPosition.add(Integer.valueOf(paramInt));
    InfoManager.getInstance().loadProgramsScheduleNode(this.mNode, this);
  }

  private void refreshByDayofweek(int paramInt)
  {
    List localList;
    if (this.mNode != null)
    {
      localList = this.mNode.getLstProgramNode(paramInt);
      if (localList != null);
    }
    else
    {
      return;
    }
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    int j;
    int i;
    if ((localObject != null) && (((Node)localObject).nodeName.equalsIgnoreCase("program")))
    {
      j = ((ProgramNode)localObject).id;
      localObject = localList.iterator();
      i = 0;
      if (((Iterator)localObject).hasNext())
        if (((ProgramNode)((Iterator)localObject).next()).id == j)
          j = 1;
    }
    while (true)
    {
      this.adapter.setData(ListUtils.convertToObjectList(localList));
      this.mTitleView.update("setData", Integer.valueOf(paramInt));
      if ((j != 0) && (i > 0))
      {
        this.mListView.setSelectionFromTop(i, this.titleLayout.height);
        return;
        i += 1;
        break;
      }
      this.mListView.setSelection(0);
      return;
      j = 0;
      continue;
      i = 0;
      j = 0;
    }
  }

  public void close(boolean paramBoolean)
  {
    InfoManager.getInstance().root().unRegisterSubscribeEventListener(1, this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RPS" });
    super.close(paramBoolean);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
    {
      int i = this.mSchedulePopViewBottom;
      int j = this.mListView.getMeasuredHeight();
      int k = this.titleLayout.height;
      if (paramMotionEvent.getY() < i - j - k)
      {
        dispatchActionEvent("cancelPop", null);
        return true;
      }
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
      if (paramObject2 != null)
      {
        paramObject1 = ((ItemParam)paramObject2).type;
        if ((paramObject1 != null) && (paramObject1.equalsIgnoreCase("refresh")))
          dispatchActionEvent("cancelPop", null);
      }
    while (!paramString.equalsIgnoreCase("selectSchedule"))
      return;
    refreshByDayofweek(((Integer)paramObject2).intValue());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mSchedulePopViewBottom = paramInt4;
    try
    {
      this.mListView.layout(this.listLayout.leftMargin, paramInt4 - this.mListView.getMeasuredHeight(), this.standardLayout.width - this.listLayout.leftMargin, paramInt4);
      this.mTitleView.layout(this.titleLayout.leftMargin, paramInt4 - this.mListView.getMeasuredHeight() - this.titleLayout.height, this.standardLayout.width - this.titleLayout.leftMargin, paramInt4 - this.mListView.getMeasuredHeight());
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.listLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.measureView(this.mTitleView);
    this.mTitleView.setPadding(this.titleLayout.width / 20, 0, 0, 0);
    this.mListView.measure(this.listLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height * 2 / 3, -2147483648));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
  }

  public void onPlayInfoUpdated(int paramInt)
  {
    if ((paramInt == 1) && (this.adapter != null))
      this.adapter.notifyDataSetChanged();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      this.mLoadedPosition.clear();
      this.mNode = ((ChannelNode)paramObject);
      if (this.mNode.channelType != 1)
        break label187;
      paramObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((paramObject != null) && (paramObject.nodeName.equalsIgnoreCase("program")))
      {
        paramString = this.mNode.getAllLstProgramNode();
        if (paramString != null)
          break label75;
      }
    }
    return;
    label75: int j = ((ProgramNode)paramObject).id;
    paramObject = paramString.iterator();
    int i = 0;
    if (paramObject.hasNext())
      if (((ProgramNode)paramObject.next()).id != j);
    for (j = 1; ; j = 0)
    {
      this.adapter.setData(ListUtils.convertToObjectList(paramString));
      this.mTitleView.update("setData", Integer.valueOf(0));
      if ((j != 0) && (i > 0))
      {
        this.mListView.setSelectionFromTop(i, this.titleLayout.height);
        return;
        i += 1;
        break;
      }
      this.mListView.setSelection(0);
      return;
      label187: refreshByDayofweek(getToday());
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.SchedulePopView
 * JD-Core Version:    0.6.2
 */