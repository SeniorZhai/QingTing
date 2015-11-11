package fm.qingting.qtradio.view.scheduleview;

import android.content.Context;
import android.view.View.MeasureSpec;
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
import fm.qingting.qtradio.view.QtViewPager;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BatchDownloadTraditionView extends ViewGroupViewImpl
  implements IEventHandler, InfoManager.ISubscribeEventListener
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(720, 153, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private BatchDownloadConfirmView mConfirmView;
  private Node mNode;
  private BatchDownloadListView mTodayView;
  private QtViewPager mViewPager;
  private BatchDownloadListView mYesterdayView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public BatchDownloadTraditionView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.mTodayView = new BatchDownloadListView(paramContext);
    this.mTodayView.setEventHandler(this);
    this.mYesterdayView = new BatchDownloadListView(paramContext);
    this.mYesterdayView.setEventHandler(this);
    this.mViewPager = new MyViewPager(paramContext);
    this.mViewPager.setTabBackgroundColor(SkinManager.getCardColor());
    addView(this.mViewPager);
    this.mConfirmView = new BatchDownloadConfirmView(paramContext);
    this.mConfirmView.setEventHandler(this);
    addView(this.mConfirmView);
  }

  private int getDownloadProgramIndex(List<ProgramNode> paramList)
  {
    int j;
    if (paramList == null)
    {
      j = -1;
      return j;
    }
    Node localNode = InfoManager.getInstance().root().getCurrentPlayingNode();
    if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
    {
      int i = 0;
      while (true)
      {
        if (i >= paramList.size())
          break label81;
        j = i;
        if (((ProgramNode)paramList.get(i)).id == ((ProgramNode)localNode).id)
          break;
        i += 1;
      }
    }
    label81: return -1;
  }

  private void initData()
  {
    if (this.mNode == null);
    while (!this.mNode.nodeName.equalsIgnoreCase("channel"))
      return;
    if (((ChannelNode)this.mNode).hasEmptyProgramSchedule())
    {
      InfoManager.getInstance().loadProgramsScheduleNode((ChannelNode)this.mNode, this);
      return;
    }
    setProgramList();
  }

  private boolean setProgramList()
  {
    int k = 0;
    if ((this.mNode == null) || (!this.mNode.nodeName.equalsIgnoreCase("channel")))
      return false;
    int i = Calendar.getInstance().get(7);
    if (i == 0)
      i = 7;
    while (true)
    {
      int j = i - 1;
      if (j < 1)
        j += 7;
      while (true)
      {
        Object localObject = ((ChannelNode)this.mNode).getLstProgramNode(j);
        if (localObject != null)
        {
          this.mYesterdayView.update("setData", localObject);
          j = getDownloadProgramIndex((List)localObject);
          if (j != -1)
          {
            this.mViewPager.initCurrentItem(1);
            this.mViewPager.setCurrentItem(1, false);
            this.mYesterdayView.update("setIndex", Integer.valueOf(j));
          }
        }
        while (true)
        {
          ArrayList localArrayList = new ArrayList();
          List localList = ((ChannelNode)this.mNode).getLstProgramNode(i);
          if (localObject != null)
          {
            i = k;
            while (i < localList.size())
            {
              localObject = (ProgramNode)localList.get(i);
              if (((ProgramNode)localObject).getCurrPlayStatus() == 3)
                localArrayList.add(localObject);
              i += 1;
            }
            this.mTodayView.update("setData", localArrayList);
            if (j == -1)
            {
              i = getDownloadProgramIndex(localList);
              this.mTodayView.update("setIndex", Integer.valueOf(i));
            }
          }
          return true;
          j = -1;
        }
      }
    }
  }

  public void close(boolean paramBoolean)
  {
    this.mViewPager.close(false);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RPS" });
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("stateChanged"))
    {
      paramObject1 = (SizeInfo)this.mTodayView.getValue("getSizeInfo", null);
      paramObject2 = (SizeInfo)this.mYesterdayView.getValue("getSizeInfo", null);
      if (paramObject1 == null)
        this.mConfirmView.update(paramString, paramObject2);
    }
    do
    {
      return;
      if (paramObject2 == null)
      {
        this.mConfirmView.update(paramString, paramObject1);
        return;
      }
      this.mConfirmView.update(paramString, SizeInfo.getSumInfo(paramObject1, paramObject2));
      return;
      if (paramString.equalsIgnoreCase("selectAll"))
      {
        this.mTodayView.update(paramString, paramObject2);
        this.mYesterdayView.update(paramString, paramObject2);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("startDownload"));
    this.mTodayView.update(paramString, paramObject2);
    this.mYesterdayView.update(paramString, paramObject2);
    QTMSGManage.getInstance().sendStatistcsMessage("downloadclick", "batchdownload");
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mConfirmView.layout(0, this.standardLayout.height - this.buttonLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mViewPager.layout(0, 0, this.standardLayout.width, this.standardLayout.height - this.buttonLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.measureView(this.mConfirmView);
    this.mViewPager.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.buttonLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if ((!paramString.equalsIgnoreCase("RPS")) || (!this.mNode.nodeName.equalsIgnoreCase("channel")) || (((ChannelNode)this.mNode).hasEmptyProgramSchedule()))
      return;
    setProgramList();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("refreshList"))
    {
      this.mTodayView.update(paramString, paramObject);
      this.mYesterdayView.update(paramString, paramObject);
    }
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("setData"));
      paramString = (Node)paramObject;
    }
    while (this.mNode == paramString);
    this.mNode = paramString;
    initData();
  }

  private class MyViewPager extends QtViewPager
  {
    public MyViewPager(Context arg2)
    {
      super();
    }

    protected boolean enableSlide()
    {
      return true;
    }

    protected IView generateView(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 0:
        return BatchDownloadTraditionView.this.mTodayView;
      case 1:
      }
      return BatchDownloadTraditionView.this.mYesterdayView;
    }

    protected int getSubViewCnt()
    {
      return 2;
    }

    protected String getTab(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return null;
      case 0:
        return "今天";
      case 1:
      }
      return "昨天";
    }

    protected void setSubViewData(IView paramIView, int paramInt)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.scheduleview.BatchDownloadTraditionView
 * JD-Core Version:    0.6.2
 */