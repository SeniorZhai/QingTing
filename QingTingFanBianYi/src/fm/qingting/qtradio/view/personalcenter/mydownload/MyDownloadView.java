package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.DownLoadInfoNode.IDownloadInfoEventListener;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.QtViewPager;
import fm.qingting.qtradio.view.scheduleview.SizeInfo;
import java.util.List;

public class MyDownloadView extends ViewGroupViewImpl
  implements IEventHandler, DownLoadInfoNode.IDownloadInfoEventListener
{
  private MyDownloadChannelListView mDownloadedView;
  private MyDownloadingView mDownloadingView;
  private MyViewPager mViewPager;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public MyDownloadView(Context paramContext)
  {
    super(paramContext);
    try
    {
      setBackgroundColor(SkinManager.getBackgroundColor());
      this.mViewPager = new MyViewPager(paramContext);
      this.mViewPager.setTabBackgroundColor(SkinManager.getCardColor());
      addView(this.mViewPager);
      InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
        localOutOfMemoryError.printStackTrace();
    }
  }

  private void caculateStorage()
  {
    int i = InfoManager.getInstance().root().mDownLoadInfoNode.getTotalProgramCnt();
    long l = InfoManager.getInstance().root().mDownLoadInfoNode.getTotalProgramSize();
    if (this.mDownloadedView != null)
      this.mDownloadedView.update("setUsageInfo", SizeInfo.getStorageInfo(i, l));
  }

  private void layoutMoveableViews()
  {
  }

  public void close(boolean paramBoolean)
  {
    this.mViewPager.close(paramBoolean);
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    if ((paramInt == 1) || (paramInt == 2) || (paramInt == 4))
    {
      if (this.mDownloadedView != null)
        this.mDownloadedView.update("refreshList", null);
      if (this.mDownloadingView != null)
      {
        paramNode = InfoManager.getInstance().root().mDownLoadInfoNode.getAllDownloadingNodes();
        this.mDownloadingView.update("refreshList", paramNode);
      }
      caculateStorage();
    }
    do
    {
      do
      {
        return;
        if (paramInt != 0)
          break;
        if (this.mDownloadedView != null)
          this.mDownloadedView.update("invalidateList", null);
      }
      while (this.mDownloadingView == null);
      this.mDownloadingView.update("invalidateList", null);
      return;
    }
    while ((paramInt != 8) || (this.mDownloadingView == null));
    this.mViewPager.setSubViewData(this.mDownloadingView, 1);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("pathchanged"))
      caculateStorage();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mViewPager.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    layoutMoveableViews();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.mViewPager.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = InfoManager.getInstance().root().mDownLoadInfoNode.getAllDownloadingNodes();
      if ((paramString != null) && (paramString.size() > 0))
      {
        this.mViewPager.setCurrentItem(1, false);
        this.mViewPager.initCurrentItem(1);
      }
      caculateStorage();
    }
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("resetCheckList"));
      caculateStorage();
      if (this.mDownloadedView != null)
        this.mDownloadedView.update(paramString, paramObject);
    }
    while (this.mDownloadingView == null);
    this.mDownloadingView.update(paramString, paramObject);
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
        if (MyDownloadView.this.mDownloadedView == null)
        {
          MyDownloadView.access$002(MyDownloadView.this, new MyDownloadChannelListView(getContext()));
          MyDownloadView.this.mDownloadedView.setEventHandler(MyDownloadView.this);
        }
        return MyDownloadView.this.mDownloadedView;
      case 1:
      }
      if (MyDownloadView.this.mDownloadingView == null)
      {
        MyDownloadView.access$102(MyDownloadView.this, new MyDownloadingView(getContext()));
        MyDownloadView.this.mDownloadingView.setEventHandler(MyDownloadView.this);
      }
      return MyDownloadView.this.mDownloadingView;
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
        return "已下载";
      case 1:
      }
      return "正在下载";
    }

    protected void setSubViewData(IView paramIView, int paramInt)
    {
      switch (paramInt)
      {
      default:
      case 0:
      case 1:
      }
      do
      {
        do
          return;
        while (MyDownloadView.this.mDownloadedView == null);
        MyDownloadView.this.mDownloadedView.update("setData", InfoManager.getInstance().root().mDownLoadInfoNode.getLstChannelNodes());
        return;
      }
      while (MyDownloadView.this.mDownloadingView == null);
      MyDownloadView.this.mDownloadingView.update("setData", InfoManager.getInstance().root().mDownLoadInfoNode.getAllDownloadingNodes());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadView
 * JD-Core Version:    0.6.2
 */