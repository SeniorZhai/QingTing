package fm.qingting.qtradio.view.scheduleview;

import android.content.Context;
import android.widget.Toast;
import fm.qingting.downloadnew.DownloadUtils;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.MutiCheckAdapter;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.LoadMoreListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BatchDownloadListView extends LoadMoreListView
  implements IEventHandler
{
  private MutiCheckAdapter adapter = new MutiCheckAdapter(new ArrayList(), this.factory);
  private IAdapterIViewFactory factory = new IAdapterIViewFactory()
  {
    public IView createView(int paramAnonymousInt)
    {
      return new BatchDownloadItemView(BatchDownloadListView.this.getContext(), this.val$hash);
    }
  };

  public BatchDownloadListView(Context paramContext)
  {
    super(paramContext);
    this.adapter.setEventHandler(this);
    setVerticalScrollBarEnabled(false);
    setVerticalFadingEdgeEnabled(false);
    setCacheColorHint(0);
    setDivider(null);
    setHeaderDividersEnabled(false);
    setSelector(17170445);
    setAdapter(this.adapter);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    Iterator localIterator;
    List localList;
    if (paramString.equalsIgnoreCase("getSizeInfo"))
    {
      localIterator = this.adapter.getCheckList();
      localList = this.adapter.getData();
      if ((localIterator != null) && (localList != null))
        break label214;
      return null;
    }
    while (true)
    {
      if (localIterator.hasNext())
      {
        int k = ((Integer)localIterator.next()).intValue();
        if ((k < 0) || (k >= localList.size()))
          break label211;
        Object localObject = (Node)localList.get(k);
        if (!((Node)localObject).nodeName.equalsIgnoreCase("program"))
          break label211;
        localObject = (ProgramNode)localObject;
        if (InfoManager.getInstance().root().mDownLoadInfoNode.hasDownLoad((Node)localObject) != 0)
          break label211;
        k = ((ProgramNode)localObject).getDuration();
        if (k <= 0)
          break label211;
        i += k * 24 * 125;
        j += 1;
      }
      label211: 
      while (true)
      {
        break;
        if ((j > 0) && (i > 0))
        {
          paramString = new SizeInfo();
          paramString.mCnt = j;
          paramString.mFileSize = i;
          paramString.mSizeString = SizeInfo.getFileSize(i);
          return paramString;
        }
        return super.getValue(paramString, paramObject);
      }
      label214: int i = 0;
      int j = 0;
    }
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      i = ((ItemParam)paramObject2).position;
      this.adapter.checkIndex(i);
    }
    while (!paramString.equalsIgnoreCase("stateChanged"))
    {
      int i;
      return;
    }
    dispatchActionEvent(paramString, null);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("refreshList"))
      this.adapter.notifyDataSetChanged();
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              if (!paramString.equalsIgnoreCase("setData"))
                break;
            }
            while (paramObject == null);
            paramString = (List)paramObject;
            this.adapter.setData(paramString);
            return;
            if (!paramString.equalsIgnoreCase("setIndex"))
              break;
            i = ((Integer)paramObject).intValue();
          }
          while ((i == -1) || (i >= this.adapter.getCount()));
          setSelection(i);
          this.adapter.checkIndex(i);
          return;
          if (!paramString.equalsIgnoreCase("checklist"))
            break;
          paramString = (List)paramObject;
        }
        while (paramString.size() <= 0);
        setSelection(((Integer)paramString.get(0)).intValue());
        this.adapter.checkIndexs(paramString);
        return;
        if (!paramString.equalsIgnoreCase("startDownload"))
          break;
        paramString = this.adapter.getCheckList();
        paramObject = this.adapter.getData();
      }
      while ((paramString == null) || (paramObject == null));
      if (!InfoManager.getInstance().root().mDownLoadInfoNode.isSDCardAvailable())
      {
        Toast.makeText(getContext(), "无法开始下载，请检查您的SD卡", 0).show();
        return;
      }
      long l = DownloadUtils.getAvailableExternalMemorySize(InfoManager.getInstance().root().mDownLoadInfoNode.getDownLoadPath());
      ArrayList localArrayList = new ArrayList();
      int j;
      for (int i = 0; paramString.hasNext(); i = j)
      {
        int k = ((Integer)paramString.next()).intValue();
        j = i;
        if (k >= 0)
        {
          j = i;
          if (k < paramObject.size())
          {
            ProgramNode localProgramNode = (ProgramNode)paramObject.get(k);
            j = i;
            if (InfoManager.getInstance().root().mDownLoadInfoNode.hasDownLoad(localProgramNode) == 0)
            {
              j = i + localProgramNode.getDuration() * 24 * 125;
              if (j > l)
              {
                Toast.makeText(getContext(), "存储空间不足，请重新选择下载", 0).show();
                return;
              }
              localArrayList.add(Integer.valueOf(k));
            }
          }
        }
      }
      Collections.sort(localArrayList);
      i = 0;
      while (i < localArrayList.size())
      {
        InfoManager.getInstance().root().mDownLoadInfoNode.addToDownloadList((Node)paramObject.get(((Integer)localArrayList.get(i)).intValue()));
        i += 1;
      }
      Toast.makeText(getContext(), "开始下载...", 0).show();
      this.adapter.resetCheck();
      return;
    }
    while (!paramString.equalsIgnoreCase("selectAll"));
    if (((Boolean)paramObject).booleanValue())
    {
      this.adapter.checkAll();
      return;
    }
    this.adapter.resetCheck();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.scheduleview.BatchDownloadListView
 * JD-Core Version:    0.6.2
 */