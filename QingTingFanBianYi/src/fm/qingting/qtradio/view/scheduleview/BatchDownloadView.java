package fm.qingting.qtradio.view.scheduleview;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
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
import fm.qingting.qtradio.view.LoadMoreListView.onLoadMoreListener;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BatchDownloadView extends ViewGroupViewImpl
  implements IEventHandler, InfoManager.ISubscribeEventListener
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(720, 153, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mCheckNew = false;
  private boolean mCheckNow = false;
  private BatchDownloadConfirmView mConfirmView;
  private BatchDownloadListView mListView;
  private HashSet<Integer> mLoadedPosition = new HashSet();
  private int mLoadedTotal = 0;
  private ChannelNode mNode;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public BatchDownloadView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mListView = new BatchDownloadListView(paramContext);
    this.mListView.setEventHandler(this);
    addView(this.mListView);
    this.mListView.setOnLoadMoreListener(new LoadMoreListView.onLoadMoreListener()
    {
      public void onLoadMore(int paramAnonymousInt)
      {
        BatchDownloadView.this.loadMore(paramAnonymousInt);
      }
    });
    this.mConfirmView = new BatchDownloadConfirmView(paramContext);
    this.mConfirmView.setEventHandler(this);
    addView(this.mConfirmView);
  }

  private void checkNew(List<ProgramNode> paramList)
  {
    if ((this.mNode == null) || (!this.mNode.nodeName.equalsIgnoreCase("channel")));
    ArrayList localArrayList;
    do
    {
      return;
      localArrayList = new ArrayList();
      int i = 0;
      while (i < paramList.size())
      {
        if ((((Node)paramList.get(i)).nodeName.equalsIgnoreCase("program")) && (this.mNode.channelType == 1));
        i += 1;
      }
    }
    while (localArrayList.size() <= 0);
    this.mListView.update("checkList", localArrayList);
  }

  private void initData()
  {
    if (this.mNode == null)
      return;
    List localList = this.mNode.getAllLstProgramNode();
    if (localList == null)
    {
      InfoManager.getInstance().loadProgramsScheduleNode(this.mNode, this);
      return;
    }
    setProgramList(localList);
  }

  private void loadMore(int paramInt)
  {
    if ((this.mLoadedPosition.contains(Integer.valueOf(paramInt))) || (this.mNode == null))
      return;
    this.mLoadedTotal = paramInt;
    this.mLoadedPosition.add(Integer.valueOf(paramInt));
    InfoManager.getInstance().loadProgramsScheduleNode(this.mNode, this);
  }

  private int setDownloadProgram(List<ProgramNode> paramList)
  {
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localNode != null) && (paramList != null) && (localNode.nodeName.equalsIgnoreCase("program")))
    {
      int i = 0;
      while (i < paramList.size())
      {
        if (((ProgramNode)paramList.get(i)).id == ((ProgramNode)localNode).id)
          return i;
        i += 1;
      }
    }
    return -1;
  }

  private boolean setProgramList(List<ProgramNode> paramList)
  {
    if (paramList == null)
      return false;
    this.mListView.update("setData", paramList);
    if (this.mCheckNow)
    {
      int i = setDownloadProgram(paramList);
      if (i != -1)
        this.mListView.update("setIndex", Integer.valueOf(i));
    }
    if (this.mCheckNew)
      checkNew(paramList);
    this.mListView.cancelLoadState();
    return true;
  }

  public void close(boolean paramBoolean)
  {
    this.mListView.close(false);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RPS" });
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("stateChanged"))
      this.mConfirmView.update(paramString, this.mListView.getValue("getSizeInfo", null));
    do
    {
      return;
      if (paramString.equalsIgnoreCase("selectAll"))
      {
        this.mListView.update(paramString, paramObject2);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("startDownload"));
    this.mListView.update(paramString, paramObject2);
    QTMSGManage.getInstance().sendStatistcsMessage("downloadclick", "batchdownload");
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height - this.buttonLayout.height);
    this.mConfirmView.layout(0, this.standardLayout.height - this.buttonLayout.height, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.measureView(this.mConfirmView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.buttonLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if ((paramString.equalsIgnoreCase("RPS")) && (!setProgramList(this.mNode.getAllLstProgramNode())));
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("refreshList"))
      this.mListView.update(paramString, paramObject);
    do
    {
      do
      {
        return;
        if (!paramString.equalsIgnoreCase("setData"))
          break;
        paramString = (ChannelNode)paramObject;
      }
      while (this.mNode == paramString);
      this.mNode = paramString;
      initData();
      return;
      if (paramString.equalsIgnoreCase("checkNew"))
      {
        this.mCheckNew = true;
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("checkNow"));
    this.mCheckNow = true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.scheduleview.BatchDownloadView
 * JD-Core Version:    0.6.2
 */