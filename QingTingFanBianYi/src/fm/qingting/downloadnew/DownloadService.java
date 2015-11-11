package fm.qingting.downloadnew;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class DownloadService
  implements DownloadListener
{
  private static final String TAG = "DownloadService";
  private static DownloadService _instance;
  private Context mAppContext;
  private List<WeakReference<DownloadListener>> mDownloadListenerLst;
  private DownloadThread mDownloadThread;
  private final List<DownloadTask> mFinishedList;
  private UrlRewriter mRewriter;
  private AtomicInteger mSequenceGenerator = new AtomicInteger(0);
  private final Map<String, DownloadTask> mTaskMap;
  private BlockingQueue<DownloadTask> mTaskQueue;
  private final List<DownloadTask> mWaitingList;

  private DownloadService(Context paramContext)
  {
    this.mAppContext = paramContext.getApplicationContext();
    this.mWaitingList = new LinkedList();
    this.mFinishedList = new LinkedList();
    this.mTaskMap = new HashMap();
    this.mDownloadListenerLst = new ArrayList();
    paramContext = DownloadDAO.getAllTasks(this.mAppContext);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        DownloadTask localDownloadTask = (DownloadTask)paramContext.next();
        this.mTaskMap.put(localDownloadTask.mTaskId, localDownloadTask);
        if (localDownloadTask.mState == DownloadState.SUCCESS)
        {
          this.mFinishedList.add(localDownloadTask);
        }
        else
        {
          this.mWaitingList.add(localDownloadTask);
          localDownloadTask.mState = DownloadState.PAUSED;
        }
      }
      Collections.sort(this.mFinishedList, new Comparator()
      {
        public int compare(DownloadTask paramAnonymousDownloadTask1, DownloadTask paramAnonymousDownloadTask2)
        {
          long l = paramAnonymousDownloadTask1.mFinishTimeMS - paramAnonymousDownloadTask2.mFinishTimeMS;
          if (l > 0L)
            return -1;
          if (l < 0L)
            return 1;
          return 0;
        }
      });
    }
    this.mTaskQueue = new PriorityBlockingQueue();
  }

  private boolean checkTask(DownloadTask paramDownloadTask)
  {
    if (paramDownloadTask == null)
      return false;
    if (TextUtils.isEmpty(paramDownloadTask.mTaskId))
    {
      Log.e("DownloadService", "添加下载任务失败，任务的id为空");
      return false;
    }
    if ((paramDownloadTask.mUrls == null) || (paramDownloadTask.mUrls.length == 0))
    {
      Log.e("DownloadService", "添加下载任务失败，任务" + paramDownloadTask.mTaskId + "的下载地址为空");
      return false;
    }
    if (TextUtils.isEmpty(paramDownloadTask.mFileName))
    {
      Log.e("DownloadService", "添加下载任务失败，任务" + paramDownloadTask.mTaskId + "的目标文件路径为空");
      return false;
    }
    return true;
  }

  private int generateSequence()
  {
    return this.mSequenceGenerator.incrementAndGet();
  }

  public static DownloadService getInstance(Context paramContext)
  {
    if (_instance == null)
      _instance = new DownloadService(paramContext);
    return _instance;
  }

  public void addDownloadListener(DownloadListener paramDownloadListener)
  {
    int i = 0;
    if (i < this.mDownloadListenerLst.size())
    {
      DownloadListener localDownloadListener = (DownloadListener)((WeakReference)this.mDownloadListenerLst.get(i)).get();
      if ((localDownloadListener == null) || (localDownloadListener != paramDownloadListener));
    }
    while (true)
    {
      if (i == -1)
        this.mDownloadListenerLst.add(new WeakReference(paramDownloadListener));
      if (this.mDownloadThread != null)
        this.mDownloadThread.addDownloadListener(paramDownloadListener);
      return;
      i += 1;
      break;
      i = -1;
    }
  }

  public boolean addTask(DownloadTask paramDownloadTask)
  {
    if (!checkTask(paramDownloadTask))
      return false;
    if ((DownloadTask)this.mTaskMap.get(paramDownloadTask.mTaskId) != null)
    {
      Log.i("DownloadService", "任务" + paramDownloadTask.mTaskId + "已经存在，不要重复添加");
      return true;
    }
    paramDownloadTask.mTotalSize = 0L;
    paramDownloadTask.mCurSize = 0L;
    paramDownloadTask.mFinishTimeMS = 0L;
    paramDownloadTask.mSequence = generateSequence();
    this.mTaskMap.put(paramDownloadTask.mTaskId, paramDownloadTask);
    this.mWaitingList.add(paramDownloadTask);
    DownloadDAO.insertNewTask(this.mAppContext, paramDownloadTask);
    if (paramDownloadTask.mState == DownloadState.READY)
      this.mTaskQueue.add(paramDownloadTask);
    Log.i("DownloadService", "添加新任务" + paramDownloadTask.mTaskId + "成功");
    return true;
  }

  public void addTaskInBatch(List<DownloadTask> paramList)
  {
    if (paramList == null)
      return;
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    DownloadTask localDownloadTask;
    while (paramList.hasNext())
    {
      localDownloadTask = (DownloadTask)paramList.next();
      if (checkTask(localDownloadTask))
        localArrayList.add(localDownloadTask);
    }
    paramList = localArrayList.iterator();
    while (paramList.hasNext())
    {
      localDownloadTask = (DownloadTask)paramList.next();
      localDownloadTask.mState = DownloadState.READY;
      localDownloadTask.mTotalSize = 0L;
      localDownloadTask.mCurSize = 0L;
      localDownloadTask.mFinishTimeMS = 0L;
      localDownloadTask.mSequence = generateSequence();
      this.mTaskMap.put(localDownloadTask.mTaskId, localDownloadTask);
      this.mWaitingList.add(localDownloadTask);
    }
    DownloadDAO.insertNewTaskInBatch(this.mAppContext, localArrayList);
    this.mTaskQueue.addAll(localArrayList);
  }

  public void enqueueAllTasks()
  {
    Iterator localIterator = this.mWaitingList.iterator();
    while (localIterator.hasNext())
    {
      DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
      if (((localDownloadTask.getDownloadState() == DownloadState.ERROR) || (localDownloadTask.getDownloadState() == DownloadState.PAUSED)) && (!this.mTaskQueue.contains(localDownloadTask)))
      {
        localDownloadTask.resetTask();
        this.mTaskQueue.add(localDownloadTask);
      }
    }
  }

  public List<DownloadTask> getFinishedList()
  {
    return new ArrayList(this.mFinishedList);
  }

  public DownloadTask getTask(String paramString)
  {
    return (DownloadTask)this.mTaskMap.get(paramString);
  }

  public List<DownloadTask> getWaitingList()
  {
    return new ArrayList(this.mWaitingList);
  }

  public boolean isDownloading()
  {
    return (this.mDownloadThread != null) && (this.mDownloadThread.isAlive());
  }

  public void onDownloadEvent(String paramString, DownloadState paramDownloadState, Object paramObject)
  {
    paramString = (DownloadTask)this.mTaskMap.get(paramString);
    if ((paramString != null) && (paramDownloadState == DownloadState.SUCCESS))
    {
      this.mWaitingList.remove(paramString);
      this.mFinishedList.add(0, paramString);
    }
  }

  public void pauseTask(String paramString)
  {
    DownloadTask localDownloadTask = (DownloadTask)this.mTaskMap.get(paramString);
    if (localDownloadTask != null)
    {
      localDownloadTask.mState = DownloadState.PAUSED;
      this.mTaskQueue.remove(localDownloadTask);
      return;
    }
    Log.e("DownloadService", "没有找到id=" + paramString + "的任务");
  }

  public void removeAllWaitingTask()
  {
    throw new RuntimeException("尚未实现");
  }

  public void removeDownloadListener(DownloadListener paramDownloadListener)
  {
    Iterator localIterator = this.mDownloadListenerLst.iterator();
    while (localIterator.hasNext())
    {
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      if ((localWeakReference.get() == null) || (localWeakReference.get() == paramDownloadListener))
        localIterator.remove();
    }
    if (this.mDownloadThread != null)
      this.mDownloadThread.removeDownloadListener(paramDownloadListener);
  }

  public boolean removeTask(String paramString)
  {
    DownloadTask localDownloadTask = (DownloadTask)this.mTaskMap.get(paramString);
    if (localDownloadTask != null)
    {
      if ((isDownloading()) && (this.mDownloadThread.getCurrentTask() == localDownloadTask))
        this.mDownloadThread.interrupt();
      this.mTaskMap.remove(paramString);
      if (localDownloadTask.mState == DownloadState.SUCCESS)
        this.mFinishedList.remove(localDownloadTask);
      while (true)
      {
        DownloadDAO.deleteTask(this.mAppContext, localDownloadTask.mTaskId);
        DownloadUtils.deleteFile(localDownloadTask.mFileName);
        Log.i("DownloadService", "删除下载任务" + localDownloadTask.mTaskId + "成功");
        return true;
        this.mWaitingList.remove(localDownloadTask);
      }
    }
    Log.d("DownloadService", "删除下载任务失败，没有找到id为'" + paramString + "'的任务");
    return false;
  }

  public void removeTaskInBatch(List<String> paramList)
  {
    if (paramList == null)
      return;
    stop();
    paramList = paramList.iterator();
    while (paramList.hasNext())
      removeTask((String)paramList.next());
    start();
  }

  public void resumeTask(String paramString)
  {
    DownloadTask localDownloadTask = (DownloadTask)this.mTaskMap.get(paramString);
    if (localDownloadTask != null)
    {
      localDownloadTask.resetTask();
      if (!this.mTaskQueue.contains(localDownloadTask))
        this.mTaskQueue.add(localDownloadTask);
      return;
    }
    Log.e("DownloadService", "没有找到id=" + paramString + "的任务");
  }

  public void setUrlRewriter(UrlRewriter paramUrlRewriter)
  {
    this.mRewriter = paramUrlRewriter;
    if (this.mDownloadThread != null)
      this.mDownloadThread.setUrlRewriter(this.mRewriter);
  }

  public void start()
  {
    if (!isDownloading())
    {
      this.mDownloadThread = new DownloadThread(this.mAppContext, this.mTaskQueue);
      this.mDownloadThread.addDownloadListener(this);
      Iterator localIterator = this.mDownloadListenerLst.iterator();
      Object localObject;
      while (localIterator.hasNext())
      {
        localObject = (WeakReference)localIterator.next();
        if (((WeakReference)localObject).get() != null)
          this.mDownloadThread.addDownloadListener((DownloadListener)((WeakReference)localObject).get());
      }
      this.mDownloadThread.setUrlRewriter(this.mRewriter);
      localIterator = this.mWaitingList.iterator();
      while (localIterator.hasNext())
      {
        localObject = (DownloadTask)localIterator.next();
        if ((((DownloadTask)localObject).mState == DownloadState.READY) && (!this.mTaskQueue.contains(localObject)))
          this.mTaskQueue.add(localObject);
      }
      this.mDownloadThread.start();
      Log.d("DownloadService", "下载服务开始运行");
      return;
    }
    Log.d("DownloadService", "下载服务已经在运行");
  }

  public void stop()
  {
    if (isDownloading())
    {
      this.mDownloadThread.quit();
      Iterator localIterator = this.mWaitingList.iterator();
      while (localIterator.hasNext())
      {
        DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
        localDownloadTask.pauseTask();
        DownloadDAO.updateTask(this.mAppContext, localDownloadTask);
      }
      this.mDownloadThread = null;
      return;
    }
    this.mDownloadThread = null;
    Log.d("DownloadService", "下载服务尚未运行");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.DownloadService
 * JD-Core Version:    0.6.2
 */