package fm.qingting.downloadnew;

import android.content.Context;
import android.util.Log;
import java.util.concurrent.BlockingQueue;

class DownloadThread extends Thread
{
  static int count = 0;
  private final String TAG = "DownloadThread" + count;
  private DownloadTask mCurTask;
  private EventDispatcher mDispatcher;
  private Network mNetwork;
  private volatile boolean mQuit = false;
  private final BlockingQueue<DownloadTask> mTaskQueue;

  DownloadThread(Context paramContext, BlockingQueue<DownloadTask> paramBlockingQueue)
  {
    super("download thread" + count);
    count += 1;
    this.mTaskQueue = paramBlockingQueue;
    this.mDispatcher = new EventDispatcher();
    this.mNetwork = new QTDownloadNetwork(paramContext, this.mDispatcher, this.TAG);
  }

  public void addDownloadListener(DownloadListener paramDownloadListener)
  {
    this.mDispatcher.addListener(paramDownloadListener);
  }

  public DownloadTask getCurrentTask()
  {
    return this.mCurTask;
  }

  public void quit()
  {
    Log.d(this.TAG, "下载线程被要求退出");
    this.mQuit = true;
    interrupt();
  }

  public void removeDownloadListener(DownloadListener paramDownloadListener)
  {
    this.mDispatcher.removeListener(paramDownloadListener);
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 105	android/os/Process:setThreadPriority	(I)V
    //   5: invokestatic 111	android/os/SystemClock:elapsedRealtime	()J
    //   8: pop2
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 77	fm/qingting/downloadnew/DownloadThread:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   14: aload_0
    //   15: aload_0
    //   16: getfield 54	fm/qingting/downloadnew/DownloadThread:mTaskQueue	Ljava/util/concurrent/BlockingQueue;
    //   19: invokeinterface 117 1 0
    //   24: checkcast 119	fm/qingting/downloadnew/DownloadTask
    //   27: putfield 77	fm/qingting/downloadnew/DownloadThread:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   30: aload_0
    //   31: getfield 77	fm/qingting/downloadnew/DownloadThread:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   34: getfield 123	fm/qingting/downloadnew/DownloadTask:mState	Lfm/qingting/downloadnew/DownloadState;
    //   37: getstatic 128	fm/qingting/downloadnew/DownloadState:READY	Lfm/qingting/downloadnew/DownloadState;
    //   40: if_acmpne +107 -> 147
    //   43: aload_0
    //   44: getfield 52	fm/qingting/downloadnew/DownloadThread:TAG	Ljava/lang/String;
    //   47: ldc 130
    //   49: invokestatic 86	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   52: pop
    //   53: aload_0
    //   54: getfield 52	fm/qingting/downloadnew/DownloadThread:TAG	Ljava/lang/String;
    //   57: new 28	java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   64: ldc 132
    //   66: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_0
    //   70: getfield 77	fm/qingting/downloadnew/DownloadThread:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   73: getfield 135	fm/qingting/downloadnew/DownloadTask:mTaskId	Ljava/lang/String;
    //   76: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokestatic 138	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   85: pop
    //   86: aload_0
    //   87: getfield 59	fm/qingting/downloadnew/DownloadThread:mDispatcher	Lfm/qingting/downloadnew/EventDispatcher;
    //   90: aload_0
    //   91: getfield 77	fm/qingting/downloadnew/DownloadThread:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   94: invokevirtual 142	fm/qingting/downloadnew/EventDispatcher:setCurrentTask	(Lfm/qingting/downloadnew/DownloadTask;)V
    //   97: aload_0
    //   98: getfield 66	fm/qingting/downloadnew/DownloadThread:mNetwork	Lfm/qingting/downloadnew/Network;
    //   101: aload_0
    //   102: getfield 77	fm/qingting/downloadnew/DownloadThread:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   105: invokeinterface 147 2 0
    //   110: aload_0
    //   111: getfield 48	fm/qingting/downloadnew/DownloadThread:mQuit	Z
    //   114: ifeq -109 -> 5
    //   117: aload_0
    //   118: getfield 52	fm/qingting/downloadnew/DownloadThread:TAG	Ljava/lang/String;
    //   121: ldc 149
    //   123: invokestatic 86	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   126: pop
    //   127: return
    //   128: astore_1
    //   129: aload_0
    //   130: getfield 48	fm/qingting/downloadnew/DownloadThread:mQuit	Z
    //   133: ifeq -128 -> 5
    //   136: aload_0
    //   137: getfield 52	fm/qingting/downloadnew/DownloadThread:TAG	Ljava/lang/String;
    //   140: ldc 151
    //   142: invokestatic 86	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   145: pop
    //   146: return
    //   147: aload_0
    //   148: getfield 52	fm/qingting/downloadnew/DownloadThread:TAG	Ljava/lang/String;
    //   151: new 28	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   158: ldc 153
    //   160: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: aload_0
    //   164: getfield 77	fm/qingting/downloadnew/DownloadThread:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   167: getfield 135	fm/qingting/downloadnew/DownloadTask:mTaskId	Ljava/lang/String;
    //   170: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: ldc 155
    //   175: invokevirtual 36	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   178: invokevirtual 43	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   181: invokestatic 158	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   184: pop
    //   185: goto -180 -> 5
    //   188: astore_1
    //   189: goto -79 -> 110
    //
    // Exception table:
    //   from	to	target	type
    //   14	30	128	java/lang/InterruptedException
    //   97	110	188	fm/qingting/downloadnew/DownloadError
  }

  public void setUrlRewriter(UrlRewriter paramUrlRewriter)
  {
    this.mNetwork.setUrlRewriter(paramUrlRewriter);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.DownloadThread
 * JD-Core Version:    0.6.2
 */