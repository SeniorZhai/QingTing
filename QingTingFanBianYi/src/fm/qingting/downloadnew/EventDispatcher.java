package fm.qingting.downloadnew;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class EventDispatcher extends Handler
{
  private static final int EVENT_LOOP = 2;
  private static final int EVENT_ONE_TIME = 1;
  private static final String KEY_EXTRA = "extra";
  private static final String KEY_ID = "taskid";
  private DownloadTask mCurTask;
  private final List<WeakReference<DownloadListener>> mListeners = new ArrayList(5);

  EventDispatcher()
  {
    super(Looper.getMainLooper());
  }

  public void addListener(DownloadListener paramDownloadListener)
  {
    int i = 0;
    if (i < this.mListeners.size())
    {
      DownloadListener localDownloadListener = (DownloadListener)((WeakReference)this.mListeners.get(i)).get();
      if ((localDownloadListener == null) || (localDownloadListener != paramDownloadListener));
    }
    while (true)
    {
      if (i == -1)
        this.mListeners.add(new WeakReference(paramDownloadListener));
      return;
      i += 1;
      break;
      i = -1;
    }
  }

  public void handleMessage(Message paramMessage)
  {
    Object localObject1 = DownloadState.UNSPECIFIED;
    Object localObject2 = (Map)paramMessage.obj;
    String str;
    int i;
    switch (paramMessage.what)
    {
    default:
      str = null;
      i = 0;
      paramMessage = null;
    case 1:
    case 2:
    }
    while (str != null)
    {
      localObject2 = this.mListeners.iterator();
      while (true)
        if (((Iterator)localObject2).hasNext())
        {
          DownloadListener localDownloadListener = (DownloadListener)((WeakReference)((Iterator)localObject2).next()).get();
          if (localDownloadListener != null)
          {
            localDownloadListener.onDownloadEvent(str, (DownloadState)localObject1, paramMessage);
            continue;
            str = (String)((Map)localObject2).get("taskid");
            localObject1 = DownloadState.valueOf(paramMessage.arg1);
            paramMessage = ((Map)localObject2).get("extra");
            i = 0;
            break;
            str = (String)((Map)localObject2).get("taskid");
            localObject1 = DownloadState.valueOf(paramMessage.arg1);
            paramMessage = ((Map)localObject2).get("extra");
            i = 1;
            break;
          }
        }
      if (i != 0)
      {
        paramMessage = obtainMessage(2);
        paramMessage.arg1 = ((DownloadState)localObject1).ordinal();
        localObject1 = new HashMap();
        ((Map)localObject1).put("taskid", str);
        ((Map)localObject1).put("extra", Long.valueOf(this.mCurTask.getCurSize()));
        paramMessage.obj = localObject1;
        sendMessageDelayed(paramMessage, 500L);
      }
    }
  }

  public boolean removeListener(DownloadListener paramDownloadListener)
  {
    Iterator localIterator = this.mListeners.iterator();
    while (localIterator.hasNext())
    {
      WeakReference localWeakReference = (WeakReference)localIterator.next();
      if (localWeakReference.get() == null)
        localIterator.remove();
      if (localWeakReference.get() == paramDownloadListener)
        localIterator.remove();
    }
    return false;
  }

  public void sendEvent(String paramString, DownloadState paramDownloadState, Object paramObject)
  {
    Message localMessage = obtainMessage(1);
    localMessage.arg1 = paramDownloadState.ordinal();
    paramDownloadState = new HashMap();
    paramDownloadState.put("taskid", paramString);
    paramDownloadState.put("extra", paramObject);
    localMessage.obj = paramDownloadState;
    sendMessage(localMessage);
  }

  public void sendLoopEvent(String paramString, DownloadState paramDownloadState)
  {
    removeMessages(2);
    Message localMessage = obtainMessage(2);
    localMessage.arg1 = paramDownloadState.ordinal();
    paramDownloadState = new HashMap();
    paramDownloadState.put("taskid", paramString);
    paramDownloadState.put("extra", Long.valueOf(this.mCurTask.getCurSize()));
    localMessage.obj = paramDownloadState;
    sendMessage(localMessage);
  }

  public void setCurrentTask(DownloadTask paramDownloadTask)
  {
    this.mCurTask = paramDownloadTask;
  }

  public void stopLoopEvent()
  {
    removeMessages(2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.EventDispatcher
 * JD-Core Version:    0.6.2
 */