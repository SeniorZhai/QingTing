package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.view.scheduleview.SizeInfo;
import java.util.ArrayList;
import java.util.List;

public class MyDownloadChannelListView extends ViewGroupViewImpl
{
  private CustomizedAdapter adapter = new CustomizedAdapter(new ArrayList(), this.factory);
  private IAdapterIViewFactory factory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new MyDownloadChannelItemView(MyDownloadChannelListView.this.getContext(), this.val$hash);
    }
  };
  private EmptyTipsView mEmptyTipsView;
  private ListView mListView;
  private StorageInfoView mStorageInfoView;

  public MyDownloadChannelListView(Context paramContext)
  {
    super(paramContext);
    this.mEmptyTipsView = new EmptyTipsView(paramContext, 3);
    addView(this.mEmptyTipsView);
    this.mListView = new ListView(paramContext);
    this.mListView.setEmptyView(this.mEmptyTipsView);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setHeaderDividersEnabled(false);
    this.mListView.setSelector(17170445);
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    this.mStorageInfoView = new StorageInfoView(paramContext);
    addView(this.mStorageInfoView);
    update("setUsageInfo", SizeInfo.getStorageInfo(InfoManager.getInstance().root().mDownLoadInfoNode.getTotalProgramCnt(), InfoManager.getInstance().root().mDownLoadInfoNode.getTotalProgramSize()));
  }

  public void close(boolean paramBoolean)
  {
    this.mStorageInfoView.close(paramBoolean);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2 - this.mStorageInfoView.getMeasuredHeight());
    this.mEmptyTipsView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mStorageInfoView.layout(0, paramInt4 - paramInt2 - this.mStorageInfoView.getMeasuredHeight(), paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mStorageInfoView.measure(paramInt1, paramInt2);
    this.mListView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt2) - this.mStorageInfoView.getMeasuredHeight(), 1073741824));
    this.mEmptyTipsView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("refreshList"))
      this.adapter.notifyDataSetChanged();
    do
    {
      return;
      if (paramString.equalsIgnoreCase("resetData"))
      {
        this.adapter.setData((List)paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("setData"))
      {
        paramString = (List)paramObject;
        this.adapter.setData(paramString);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setUsageInfo"));
    this.mStorageInfoView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadChannelListView
 * JD-Core Version:    0.6.2
 */