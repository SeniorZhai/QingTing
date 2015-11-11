package fm.qingting.qtradio.fm;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.downloadnew.DownloadListener;
import fm.qingting.downloadnew.DownloadState;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.Download;
import fm.qingting.qtradio.model.Downloadobject;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PlayCacheAgent
  implements DownloadListener
{
  private static final int CLEAR_CACHE = 2;
  private static final int DELAYSTARTCACHE = 5000;
  private static final int DELAYTIME = 500;
  public static final int EXCEED_DURATION = 1;
  public static final int HAS_EXISTED = 2;
  private static final int SELECT_CACHE = 0;
  private static final int SMART_CLEAR_CACHE = 3;
  private static final int START_CACHE = 1;
  private static final String SUFFIX = ".cache";
  private static PlayCacheAgent _instance = null;
  private static HandlerThread cache = new HandlerThread("PlayCacheAgent_Cache_Thread");
  private int MAX_CACHE_DURATION = 3600;
  private int MAX_DURATION = 5400;
  private CacheHandler cacheHandler = new CacheHandler(cache.getLooper());
  private boolean hasRestored = false;
  private List<ProgramNode> lstDownloadingNodes = new ArrayList();
  private ProgramNode mNode;
  private Map<Integer, String> mapDownloadedNodes = new HashMap();
  private Map<Integer, String> mapDownloadingNodes = new HashMap();
  private List<IDownloadInfoEventListener> mlstDLEventListeners = new ArrayList();
  private int refuseErrorCode = 0;

  static
  {
    cache.start();
  }

  public PlayCacheAgent()
  {
    init();
  }

  private void _clearCache()
  {
    Iterator localIterator = this.mapDownloadedNodes.entrySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)((Map.Entry)localIterator.next()).getKey();
      new StringBuilder().append(str).append(".cache").toString();
    }
    this.mapDownloadedNodes.clear();
  }

  private void _smartClearCache()
  {
    int j = 0;
    if (this.mapDownloadedNodes.size() == 0);
    while (true)
    {
      return;
      Object localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
      if ((localObject != null) && (((Node)localObject).nodeName.equalsIgnoreCase("program")))
      {
        localObject = (ProgramNode)localObject;
        ArrayList localArrayList = new ArrayList();
        while (localObject != null)
        {
          if (this.mapDownloadedNodes.get(Integer.valueOf(((ProgramNode)localObject).resId)) != null)
            localArrayList.add(Integer.valueOf(((ProgramNode)localObject).resId));
          localObject = (ProgramNode)((ProgramNode)localObject).nextSibling;
        }
        localObject = this.mapDownloadedNodes.entrySet().iterator();
        if (((Iterator)localObject).hasNext())
        {
          int k = ((Integer)((Map.Entry)((Iterator)localObject).next()).getKey()).intValue();
          i = 0;
          while (true)
          {
            if ((i >= localArrayList.size()) || (((Integer)localArrayList.get(i)).intValue() == k))
            {
              if (i >= localArrayList.size())
                break;
              break;
            }
            i += 1;
          }
        }
        this.mapDownloadedNodes.clear();
        int i = j;
        while (i < localArrayList.size())
        {
          addCacheByName(localArrayList.get(i) + ".cache");
          i += 1;
        }
      }
    }
  }

  private void clearCache()
  {
    if (this.cacheHandler.hasMessages(2))
      this.cacheHandler.removeMessages(2);
    this.cacheHandler.sendEmptyMessageDelayed(2, 500L);
  }

  public static PlayCacheAgent getInstance()
  {
    if (_instance == null)
      _instance = new PlayCacheAgent();
    return _instance;
  }

  private void selectDownloadingNodes(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      break label4;
    while (true)
    {
      label4: return;
      if ((this.mapDownloadedNodes.get(paramProgramNode) == null) && (this.mapDownloadingNodes.get(paramProgramNode) == null))
      {
        this.mapDownloadingNodes.clear();
        this.lstDownloadingNodes.clear();
        int i = 0;
        if (paramProgramNode.getDuration() > this.MAX_DURATION)
          break;
        while ((paramProgramNode != null) && (i < this.MAX_CACHE_DURATION) && (paramProgramNode.getCurrPlayStatus() == 3))
        {
          i += paramProgramNode.getDuration();
          if (this.mapDownloadedNodes.get(Integer.valueOf(paramProgramNode.resId)) == null)
          {
            this.lstDownloadingNodes.add(paramProgramNode);
            this.mapDownloadingNodes.put(Integer.valueOf(paramProgramNode.resId), paramProgramNode.getDownLoadUrlPath());
          }
          paramProgramNode = paramProgramNode.getNextSibling();
        }
      }
    }
  }

  private void startCache()
  {
    if (this.cacheHandler.hasMessages(1))
      this.cacheHandler.removeMessages(1);
    this.cacheHandler.sendEmptyMessageDelayed(1, 5000L);
  }

  private boolean startDownLoad(Node paramNode)
  {
    if (paramNode == null)
      return false;
    if (hasInDownloadList(paramNode))
      return false;
    if (!paramNode.nodeName.equalsIgnoreCase("program"))
      return false;
    if (((ProgramNode)paramNode).channelType != 1)
      return false;
    if (((ProgramNode)paramNode).getCurrPlayStatus() != 3)
      return false;
    int i = ((ProgramNode)paramNode).getDuration();
    String str = ((ProgramNode)paramNode).getDownLoadUrlPath();
    if (((ProgramNode)paramNode).downloadInfo == null)
      ((ProgramNode)paramNode).downloadInfo = new Download();
    if (((ProgramNode)paramNode).downloadInfo != null)
    {
      ((ProgramNode)paramNode).downloadInfo.type = 1;
      ((ProgramNode)paramNode).downloadInfo.downloadPath = str;
      ((ProgramNode)paramNode).downloadInfo.fileSize = (((ProgramNode)paramNode).getDuration() * 24 * 125);
    }
    str = ((ProgramNode)paramNode).getNextDownLoadUrl();
    if ((str == null) || (str.equalsIgnoreCase("")))
      return false;
    int j = ((ProgramNode)paramNode).resId;
    this.mapDownloadingNodes.put(Integer.valueOf(((ProgramNode)paramNode).resId), str);
    paramNode = String.valueOf(j) + ".cache";
    new Downloadobject(paramNode, paramNode, str).setFileSize(i * 24 * 125);
    return true;
  }

  private void startDownload(int paramInt)
  {
    int i = 0;
    while (true)
    {
      if (i < this.lstDownloadingNodes.size())
      {
        if (((ProgramNode)this.lstDownloadingNodes.get(i)).resId != paramInt)
          break label81;
        if (!startDownLoad((Node)this.lstDownloadingNodes.get(i)))
        {
          this.mapDownloadingNodes.remove(Integer.valueOf(paramInt));
          this.lstDownloadingNodes.remove(i);
        }
      }
      return;
      label81: i += 1;
    }
  }

  public void addCacheByName(String paramString)
  {
    if (paramString == null);
    do
    {
      do
      {
        return;
        paramString = paramString.split("\\.");
      }
      while ((paramString == null) || (paramString.length == 0));
      paramString = paramString[0];
    }
    while ((this.mapDownloadedNodes.containsKey(paramString)) || (paramString == null) || (paramString.equalsIgnoreCase("")));
    String str = "file://" + InfoManager.getInstance().root().mDownLoadInfoNode.getDownLoadPath();
    str = str + "/";
    str = str + paramString;
    str = str + ".cache";
    this.mapDownloadedNodes.put(Integer.valueOf(paramString), str);
  }

  public boolean cacheNode(Node paramNode)
  {
    return false;
  }

  public void delCache()
  {
    clearCache();
  }

  public void delDownloading(String paramString, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    this.mapDownloadingNodes.remove(paramString);
  }

  public String getCache(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      paramProgramNode = null;
    String str;
    do
    {
      return paramProgramNode;
      str = (String)this.mapDownloadedNodes.get(Integer.valueOf(paramProgramNode.resId));
      paramProgramNode = str;
    }
    while (str == null);
    MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "HitPlayCache");
    return str;
  }

  public long getCacheSize()
  {
    return 0L;
  }

  public String getDownloadedProgramSource(String paramString)
  {
    if (paramString == null)
      return null;
    return (String)this.mapDownloadedNodes.get(paramString);
  }

  public boolean hasCached(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      return false;
    paramProgramNode = (String)this.mapDownloadedNodes.get(Integer.valueOf(paramProgramNode.resId));
    return (paramProgramNode != null) && (!paramProgramNode.equalsIgnoreCase(""));
  }

  public boolean hasInDownloadList(Node paramNode)
  {
    if (paramNode == null);
    while ((!paramNode.nodeName.equalsIgnoreCase("program")) || (this.mapDownloadedNodes.get(Integer.valueOf(((ProgramNode)paramNode).resId)) == null))
      return false;
    return true;
  }

  public boolean hasInDownloadingNodes(Node paramNode)
  {
    if (paramNode == null);
    while ((!paramNode.nodeName.equalsIgnoreCase("program")) || (this.mapDownloadingNodes.get(Integer.valueOf(((ProgramNode)paramNode).resId)) == null))
      return false;
    return true;
  }

  public void init()
  {
  }

  public void onDownloadEvent(String paramString, DownloadState paramDownloadState, Object paramObject)
  {
  }

  public void pauseDownLoad(Node paramNode, boolean paramBoolean)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("program")));
    int i;
    do
    {
      return;
      i = ((ProgramNode)paramNode).resId;
    }
    while ((String)this.mapDownloadingNodes.get(Integer.valueOf(i)) == null);
  }

  public void registerListener(IDownloadInfoEventListener paramIDownloadInfoEventListener)
  {
    if (paramIDownloadInfoEventListener == null)
      return;
    Iterator localIterator = this.mlstDLEventListeners.iterator();
    while (localIterator.hasNext())
      if ((IDownloadInfoEventListener)localIterator.next() == paramIDownloadInfoEventListener)
        return;
    this.mlstDLEventListeners.add(paramIDownloadInfoEventListener);
  }

  public void resumeDownLoad(Node paramNode)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("program")));
    int i;
    do
    {
      return;
      i = ((ProgramNode)paramNode).resId;
    }
    while ((String)this.mapDownloadingNodes.get(Integer.valueOf(i)) == null);
  }

  public void unregisterListener(IDownloadInfoEventListener paramIDownloadInfoEventListener)
  {
    if (paramIDownloadInfoEventListener == null);
    IDownloadInfoEventListener localIDownloadInfoEventListener;
    do
    {
      return;
      Iterator localIterator;
      while (!localIterator.hasNext())
        localIterator = this.mlstDLEventListeners.iterator();
      localIDownloadInfoEventListener = (IDownloadInfoEventListener)localIterator.next();
    }
    while (localIDownloadInfoEventListener != paramIDownloadInfoEventListener);
    this.mlstDLEventListeners.remove(localIDownloadInfoEventListener);
  }

  public class CacheHandler extends Handler
  {
    public CacheHandler(Looper arg2)
    {
      super();
    }

    public void handleMessage(Message paramMessage)
    {
      if (paramMessage == null);
      while (true)
      {
        return;
        try
        {
          switch (paramMessage.what)
          {
          case 0:
            PlayCacheAgent.this.selectDownloadingNodes(PlayCacheAgent.this.mNode);
            PlayCacheAgent.this.startCache();
            return;
          case 1:
            if (PlayCacheAgent.this.lstDownloadingNodes.size() > 0)
            {
              paramMessage = (ProgramNode)PlayCacheAgent.this.lstDownloadingNodes.get(0);
              PlayCacheAgent.this.startDownload(paramMessage.resId);
              return;
            }
            break;
          case 2:
            PlayCacheAgent.this._clearCache();
            return;
          case 3:
            PlayCacheAgent.this._smartClearCache();
            return;
          }
        }
        catch (Exception paramMessage)
        {
        }
      }
    }
  }

  public static abstract interface IDownloadInfoEventListener
  {
    public static final int DOWNLOAD_ADDED = 8;
    public static final int DOWNLOAD_COMPLETE = 1;
    public static final int DOWNLOAD_DELETED = 4;
    public static final int DOWNLOAD_FAILED = 2;
    public static final int DOWNLOAD_PROGRESS = 0;

    public abstract void onDownLoadInfoUpdated(int paramInt, Node paramNode);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.fm.PlayCacheAgent
 * JD-Core Version:    0.6.2
 */