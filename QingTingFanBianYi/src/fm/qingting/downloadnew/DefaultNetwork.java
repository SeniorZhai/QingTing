package fm.qingting.downloadnew;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

class DefaultNetwork
  implements Network
{
  final String TAG;
  private byte[] mBuffer;
  private Context mContext;
  protected DownloadTask mCurTask;
  private EventDispatcher mDispatcher;
  protected RandomAccessFile mRAFile;
  private UrlRewriter mRewriter;

  DefaultNetwork(Context paramContext, EventDispatcher paramEventDispatcher, String paramString)
  {
    this.mContext = paramContext;
    this.mBuffer = new byte[4096];
    this.mDispatcher = paramEventDispatcher;
    this.TAG = paramString;
  }

  private void attemptRetryTimeoutOnException(String paramString, HttpURLConnection paramHttpURLConnection, DownloadError paramDownloadError)
    throws DownloadError
  {
    RetryPolicy localRetryPolicy = this.mCurTask.mRetryPolicy;
    try
    {
      localRetryPolicy.retryTimeout(paramDownloadError);
      Log.d(this.TAG, paramString + "，进行第" + this.mCurTask.getCurRetryTime() + "次重试，超时设置为" + localRetryPolicy.getCurrentTimeout() + "ms");
      return;
    }
    catch (DownloadError paramString)
    {
      finishTask(paramHttpURLConnection, DownloadState.ERROR, paramString.getMessage());
    }
    throw paramString;
  }

  private void attemptRetryUrlOnConnectionFail(String paramString, HttpURLConnection paramHttpURLConnection, DownloadError paramDownloadError)
    throws DownloadError
  {
    RetryPolicy localRetryPolicy = this.mCurTask.mRetryPolicy;
    try
    {
      localRetryPolicy.retryUrl(paramDownloadError);
      this.mCurTask.mRedirectUrl = null;
      Log.d(this.TAG, paramString + "，进行重试，url设置为" + localRetryPolicy.getCurrentUrl());
      return;
    }
    catch (DownloadError paramString)
    {
      finishTask(paramHttpURLConnection, DownloadState.ERROR, paramString.getMessage());
    }
    throw paramString;
  }

  // ERROR //
  private void downloadToFile(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 31	fm/qingting/downloadnew/DefaultNetwork:mDispatcher	Lfm/qingting/downloadnew/EventDispatcher;
    //   4: aload_0
    //   5: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   8: getfield 118	fm/qingting/downloadnew/DownloadTask:mTaskId	Ljava/lang/String;
    //   11: getstatic 121	fm/qingting/downloadnew/DownloadState:DOWNLOADING	Lfm/qingting/downloadnew/DownloadState;
    //   14: invokevirtual 127	fm/qingting/downloadnew/EventDispatcher:sendLoopEvent	(Ljava/lang/String;Lfm/qingting/downloadnew/DownloadState;)V
    //   17: getstatic 130	fm/qingting/downloadnew/DownloadState:SUCCESS	Lfm/qingting/downloadnew/DownloadState;
    //   20: astore_3
    //   21: aload_1
    //   22: invokevirtual 136	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   25: astore 4
    //   27: aload_0
    //   28: getfield 138	fm/qingting/downloadnew/DefaultNetwork:mRAFile	Ljava/io/RandomAccessFile;
    //   31: aload_0
    //   32: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   35: getfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   38: invokevirtual 148	java/io/RandomAccessFile:seek	(J)V
    //   41: aload 4
    //   43: aload_0
    //   44: getfield 29	fm/qingting/downloadnew/DefaultNetwork:mBuffer	[B
    //   47: invokevirtual 154	java/io/InputStream:read	([B)I
    //   50: istore 5
    //   52: aload_3
    //   53: astore_2
    //   54: iload 5
    //   56: iconst_m1
    //   57: if_icmpeq +57 -> 114
    //   60: aload_0
    //   61: getfield 138	fm/qingting/downloadnew/DefaultNetwork:mRAFile	Ljava/io/RandomAccessFile;
    //   64: aload_0
    //   65: getfield 29	fm/qingting/downloadnew/DefaultNetwork:mBuffer	[B
    //   68: iconst_0
    //   69: iload 5
    //   71: invokevirtual 158	java/io/RandomAccessFile:write	([BII)V
    //   74: aload_0
    //   75: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   78: astore_2
    //   79: aload_2
    //   80: aload_2
    //   81: getfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   84: iload 5
    //   86: i2l
    //   87: ladd
    //   88: putfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   91: aload_0
    //   92: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   95: getfield 161	fm/qingting/downloadnew/DownloadTask:mState	Lfm/qingting/downloadnew/DownloadState;
    //   98: getstatic 164	fm/qingting/downloadnew/DownloadState:PAUSED	Lfm/qingting/downloadnew/DownloadState;
    //   101: if_acmpeq +9 -> 110
    //   104: invokestatic 170	java/lang/Thread:interrupted	()Z
    //   107: ifeq -66 -> 41
    //   110: getstatic 164	fm/qingting/downloadnew/DownloadState:PAUSED	Lfm/qingting/downloadnew/DownloadState;
    //   113: astore_2
    //   114: aload 4
    //   116: invokevirtual 173	java/io/InputStream:close	()V
    //   119: aload_2
    //   120: getstatic 130	fm/qingting/downloadnew/DownloadState:SUCCESS	Lfm/qingting/downloadnew/DownloadState;
    //   123: if_acmpne +90 -> 213
    //   126: aconst_null
    //   127: astore_3
    //   128: aload_0
    //   129: aload_1
    //   130: aload_2
    //   131: aload_3
    //   132: invokespecial 98	fm/qingting/downloadnew/DefaultNetwork:finishTask	(Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadState;Ljava/lang/Object;)V
    //   135: return
    //   136: astore_1
    //   137: aload 4
    //   139: ifnull +8 -> 147
    //   142: aload 4
    //   144: invokevirtual 173	java/io/InputStream:close	()V
    //   147: new 115	java/io/IOException
    //   150: dup
    //   151: new 54	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   158: ldc 175
    //   160: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: aload_1
    //   164: invokevirtual 176	java/io/IOException:getMessage	()Ljava/lang/String;
    //   167: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokespecial 179	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   176: athrow
    //   177: astore_1
    //   178: aload 4
    //   180: invokevirtual 173	java/io/InputStream:close	()V
    //   183: new 115	java/io/IOException
    //   186: dup
    //   187: new 54	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   194: ldc 181
    //   196: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   199: aload_1
    //   200: invokevirtual 176	java/io/IOException:getMessage	()Ljava/lang/String;
    //   203: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   209: invokespecial 179	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   212: athrow
    //   213: ldc 183
    //   215: astore_3
    //   216: goto -88 -> 128
    //
    // Exception table:
    //   from	to	target	type
    //   27	41	136	java/io/IOException
    //   60	74	177	java/io/IOException
  }

  private void finishTask(HttpURLConnection paramHttpURLConnection, DownloadState paramDownloadState, Object paramObject)
  {
    this.mDispatcher.stopLoopEvent();
    try
    {
      if (this.mRAFile != null)
        this.mRAFile.close();
      if (paramHttpURLConnection != null)
        paramHttpURLConnection.disconnect();
      DownloadState localDownloadState1 = paramDownloadState;
      paramHttpURLConnection = paramObject;
      if (paramDownloadState == DownloadState.SUCCESS)
      {
        this.mCurTask.mTotalSize = this.mCurTask.mCurSize;
        if (checkFile())
        {
          this.mCurTask.mFinishTimeMS = System.currentTimeMillis();
          paramHttpURLConnection = paramObject;
          localDownloadState1 = paramDownloadState;
        }
      }
      else
      {
        this.mCurTask.mState = localDownloadState1;
        DownloadDAO.updateTask(this.mContext, this.mCurTask);
        paramObject = this.TAG;
        StringBuilder localStringBuilder = new StringBuilder().append("任务").append(this.mCurTask.mTaskId).append("结束，");
        if (localDownloadState1 != DownloadState.SUCCESS)
          break label200;
        paramDownloadState = "下载成功";
        Log.d(paramObject, paramDownloadState);
        this.mDispatcher.sendEvent(this.mCurTask.mTaskId, localDownloadState1, paramHttpURLConnection);
        return;
      }
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        Log.e(this.TAG, "关闭文件时出错！");
        continue;
        DownloadState localDownloadState2 = DownloadState.ERROR;
        paramHttpURLConnection = "文件完整性检查错误";
        continue;
        label200: paramDownloadState = paramHttpURLConnection;
      }
    }
  }

  protected boolean checkFile()
  {
    return true;
  }

  protected HttpURLConnection openConnection(URL paramURL)
    throws IOException
  {
    return (HttpURLConnection)paramURL.openConnection();
  }

  // ERROR //
  public void performDownload(DownloadTask paramDownloadTask)
    throws DownloadError
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: putfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   5: aload_0
    //   6: aconst_null
    //   7: putfield 138	fm/qingting/downloadnew/DefaultNetwork:mRAFile	Ljava/io/RandomAccessFile;
    //   10: aload_0
    //   11: new 144	java/io/RandomAccessFile
    //   14: dup
    //   15: aload_0
    //   16: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   19: getfield 249	fm/qingting/downloadnew/DownloadTask:mFileName	Ljava/lang/String;
    //   22: invokestatic 255	fm/qingting/downloadnew/DownloadUtils:createFile	(Ljava/lang/String;)Ljava/io/File;
    //   25: ldc_w 257
    //   28: invokespecial 260	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   31: putfield 138	fm/qingting/downloadnew/DefaultNetwork:mRAFile	Ljava/io/RandomAccessFile;
    //   34: invokestatic 265	android/os/SystemClock:elapsedRealtime	()J
    //   37: lstore 7
    //   39: iconst_0
    //   40: istore 4
    //   42: iconst_0
    //   43: istore 5
    //   45: aconst_null
    //   46: astore_1
    //   47: aload_0
    //   48: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   51: getfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   54: lstore 9
    //   56: aload_0
    //   57: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   60: invokevirtual 268	fm/qingting/downloadnew/DownloadTask:getUrl	()Ljava/lang/String;
    //   63: astore_2
    //   64: aload_0
    //   65: getfield 270	fm/qingting/downloadnew/DefaultNetwork:mRewriter	Lfm/qingting/downloadnew/UrlRewriter;
    //   68: ifnull +1090 -> 1158
    //   71: aload_0
    //   72: getfield 270	fm/qingting/downloadnew/DefaultNetwork:mRewriter	Lfm/qingting/downloadnew/UrlRewriter;
    //   75: aload_2
    //   76: invokeinterface 276 2 0
    //   81: astore_2
    //   82: aload_2
    //   83: ifnonnull +144 -> 227
    //   86: aload_0
    //   87: aconst_null
    //   88: getstatic 91	fm/qingting/downloadnew/DownloadState:ERROR	Lfm/qingting/downloadnew/DownloadState;
    //   91: ldc_w 278
    //   94: invokespecial 98	fm/qingting/downloadnew/DefaultNetwork:finishTask	(Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadState;Ljava/lang/Object;)V
    //   97: new 38	fm/qingting/downloadnew/DownloadError
    //   100: dup
    //   101: ldc_w 278
    //   104: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   107: athrow
    //   108: astore_2
    //   109: aload_0
    //   110: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   113: getfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   116: lload 9
    //   118: lsub
    //   119: lconst_0
    //   120: lcmp
    //   121: ifne +836 -> 957
    //   124: aload_0
    //   125: ldc_w 281
    //   128: aload_1
    //   129: new 38	fm/qingting/downloadnew/DownloadError
    //   132: dup
    //   133: ldc_w 281
    //   136: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   139: invokespecial 283	fm/qingting/downloadnew/DefaultNetwork:attemptRetryUrlOnConnectionFail	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadError;)V
    //   142: goto -108 -> 34
    //   145: astore_1
    //   146: aload_0
    //   147: aconst_null
    //   148: getstatic 91	fm/qingting/downloadnew/DownloadState:ERROR	Lfm/qingting/downloadnew/DownloadState;
    //   151: new 54	java/lang/StringBuilder
    //   154: dup
    //   155: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   158: ldc_w 285
    //   161: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload_1
    //   165: invokevirtual 286	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   168: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokespecial 98	fm/qingting/downloadnew/DefaultNetwork:finishTask	(Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadState;Ljava/lang/Object;)V
    //   177: return
    //   178: astore_1
    //   179: aload_0
    //   180: aconst_null
    //   181: getstatic 91	fm/qingting/downloadnew/DownloadState:ERROR	Lfm/qingting/downloadnew/DownloadState;
    //   184: new 54	java/lang/StringBuilder
    //   187: dup
    //   188: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   191: ldc_w 288
    //   194: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   197: aload_0
    //   198: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   201: getfield 249	fm/qingting/downloadnew/DownloadTask:mFileName	Ljava/lang/String;
    //   204: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: ldc_w 290
    //   210: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: aload_1
    //   214: invokevirtual 176	java/io/IOException:getMessage	()Ljava/lang/String;
    //   217: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   223: invokespecial 98	fm/qingting/downloadnew/DefaultNetwork:finishTask	(Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadState;Ljava/lang/Object;)V
    //   226: return
    //   227: aload_0
    //   228: new 235	java/net/URL
    //   231: dup
    //   232: aload_2
    //   233: invokespecial 291	java/net/URL:<init>	(Ljava/lang/String;)V
    //   236: invokevirtual 293	fm/qingting/downloadnew/DefaultNetwork:openConnection	(Ljava/net/URL;)Ljava/net/HttpURLConnection;
    //   239: astore_3
    //   240: aload_3
    //   241: astore_1
    //   242: iload 5
    //   244: istore 4
    //   246: aload_1
    //   247: ldc_w 295
    //   250: invokevirtual 298	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   253: iload 5
    //   255: istore 4
    //   257: aload_0
    //   258: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   261: invokevirtual 301	fm/qingting/downloadnew/DownloadTask:getTimeoutMs	()I
    //   264: istore 6
    //   266: iload 5
    //   268: istore 4
    //   270: aload_1
    //   271: iload 6
    //   273: invokevirtual 305	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   276: iload 5
    //   278: istore 4
    //   280: aload_1
    //   281: iload 6
    //   283: iconst_2
    //   284: imul
    //   285: invokevirtual 308	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   288: iload 5
    //   290: istore 4
    //   292: aload_1
    //   293: iconst_0
    //   294: invokevirtual 312	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   297: iload 5
    //   299: istore 4
    //   301: aload_1
    //   302: iconst_1
    //   303: invokevirtual 315	java/net/HttpURLConnection:setDoInput	(Z)V
    //   306: iload 5
    //   308: istore 4
    //   310: aload_0
    //   311: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   314: getfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   317: lconst_0
    //   318: lcmp
    //   319: ifle +46 -> 365
    //   322: iload 5
    //   324: istore 4
    //   326: aload_1
    //   327: ldc_w 317
    //   330: new 54	java/lang/StringBuilder
    //   333: dup
    //   334: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   337: ldc_w 319
    //   340: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: aload_0
    //   344: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   347: getfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   350: invokevirtual 322	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   353: ldc_w 324
    //   356: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   359: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   362: invokevirtual 328	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   365: iload 5
    //   367: istore 4
    //   369: aload_0
    //   370: getfield 33	fm/qingting/downloadnew/DefaultNetwork:TAG	Ljava/lang/String;
    //   373: new 54	java/lang/StringBuilder
    //   376: dup
    //   377: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   380: ldc_w 330
    //   383: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: aload_2
    //   387: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   393: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   396: pop
    //   397: iload 5
    //   399: istore 4
    //   401: aload_0
    //   402: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   405: getstatic 333	fm/qingting/downloadnew/DownloadState:CONNECTING	Lfm/qingting/downloadnew/DownloadState;
    //   408: putfield 161	fm/qingting/downloadnew/DownloadTask:mState	Lfm/qingting/downloadnew/DownloadState;
    //   411: iload 5
    //   413: istore 4
    //   415: aload_0
    //   416: getfield 31	fm/qingting/downloadnew/DefaultNetwork:mDispatcher	Lfm/qingting/downloadnew/EventDispatcher;
    //   419: aload_0
    //   420: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   423: getfield 118	fm/qingting/downloadnew/DownloadTask:mTaskId	Ljava/lang/String;
    //   426: getstatic 333	fm/qingting/downloadnew/DownloadState:CONNECTING	Lfm/qingting/downloadnew/DownloadState;
    //   429: aconst_null
    //   430: invokevirtual 224	fm/qingting/downloadnew/EventDispatcher:sendEvent	(Ljava/lang/String;Lfm/qingting/downloadnew/DownloadState;Ljava/lang/Object;)V
    //   433: iload 5
    //   435: istore 4
    //   437: aload_1
    //   438: invokevirtual 336	java/net/HttpURLConnection:getResponseCode	()I
    //   441: istore 5
    //   443: iload 5
    //   445: istore 4
    //   447: invokestatic 265	android/os/SystemClock:elapsedRealtime	()J
    //   450: lstore 11
    //   452: iload 5
    //   454: istore 4
    //   456: aload_0
    //   457: getfield 33	fm/qingting/downloadnew/DefaultNetwork:TAG	Ljava/lang/String;
    //   460: new 54	java/lang/StringBuilder
    //   463: dup
    //   464: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   467: ldc_w 338
    //   470: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   473: lload 11
    //   475: lload 7
    //   477: lsub
    //   478: invokevirtual 322	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   481: ldc_w 340
    //   484: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   487: iload 5
    //   489: invokevirtual 68	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   492: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   495: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   498: pop
    //   499: iload 5
    //   501: istore 4
    //   503: aload_0
    //   504: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   507: getfield 161	fm/qingting/downloadnew/DownloadTask:mState	Lfm/qingting/downloadnew/DownloadState;
    //   510: getstatic 164	fm/qingting/downloadnew/DownloadState:PAUSED	Lfm/qingting/downloadnew/DownloadState;
    //   513: if_acmpne +18 -> 531
    //   516: iload 5
    //   518: istore 4
    //   520: aload_0
    //   521: aload_1
    //   522: getstatic 164	fm/qingting/downloadnew/DownloadState:PAUSED	Lfm/qingting/downloadnew/DownloadState;
    //   525: ldc 183
    //   527: invokespecial 98	fm/qingting/downloadnew/DefaultNetwork:finishTask	(Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadState;Ljava/lang/Object;)V
    //   530: return
    //   531: iload 5
    //   533: iconst_m1
    //   534: if_icmpne +75 -> 609
    //   537: iload 5
    //   539: istore 4
    //   541: aload_0
    //   542: getfield 33	fm/qingting/downloadnew/DefaultNetwork:TAG	Ljava/lang/String;
    //   545: ldc_w 342
    //   548: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   551: pop
    //   552: iload 5
    //   554: istore 4
    //   556: new 115	java/io/IOException
    //   559: dup
    //   560: invokespecial 343	java/io/IOException:<init>	()V
    //   563: athrow
    //   564: astore_2
    //   565: aload_0
    //   566: ldc_w 345
    //   569: aload_1
    //   570: new 38	fm/qingting/downloadnew/DownloadError
    //   573: dup
    //   574: new 54	java/lang/StringBuilder
    //   577: dup
    //   578: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   581: ldc_w 347
    //   584: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   587: aload_0
    //   588: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   591: invokevirtual 268	fm/qingting/downloadnew/DownloadTask:getUrl	()Ljava/lang/String;
    //   594: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   600: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   603: invokespecial 283	fm/qingting/downloadnew/DefaultNetwork:attemptRetryUrlOnConnectionFail	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadError;)V
    //   606: goto -572 -> 34
    //   609: iload 5
    //   611: sipush 200
    //   614: if_icmpne +101 -> 715
    //   617: iload 5
    //   619: istore 4
    //   621: aload_0
    //   622: getfield 33	fm/qingting/downloadnew/DefaultNetwork:TAG	Ljava/lang/String;
    //   625: ldc_w 349
    //   628: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   631: pop
    //   632: iload 5
    //   634: istore 4
    //   636: aload_0
    //   637: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   640: getstatic 121	fm/qingting/downloadnew/DownloadState:DOWNLOADING	Lfm/qingting/downloadnew/DownloadState;
    //   643: putfield 161	fm/qingting/downloadnew/DownloadTask:mState	Lfm/qingting/downloadnew/DownloadState;
    //   646: iload 5
    //   648: istore 4
    //   650: aload_0
    //   651: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   654: aload_1
    //   655: invokevirtual 352	java/net/HttpURLConnection:getContentLength	()I
    //   658: i2l
    //   659: putfield 193	fm/qingting/downloadnew/DownloadTask:mTotalSize	J
    //   662: iload 5
    //   664: istore 4
    //   666: aload_0
    //   667: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   670: lconst_0
    //   671: putfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   674: iload 5
    //   676: istore 4
    //   678: aload_0
    //   679: aload_1
    //   680: invokespecial 354	fm/qingting/downloadnew/DefaultNetwork:downloadToFile	(Ljava/net/HttpURLConnection;)V
    //   683: return
    //   684: astore_3
    //   685: aload_1
    //   686: astore_2
    //   687: aload_3
    //   688: astore_1
    //   689: iload 4
    //   691: ifne +287 -> 978
    //   694: aload_0
    //   695: ldc_w 356
    //   698: aload_2
    //   699: new 38	fm/qingting/downloadnew/DownloadError
    //   702: dup
    //   703: ldc_w 356
    //   706: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   709: invokespecial 283	fm/qingting/downloadnew/DefaultNetwork:attemptRetryUrlOnConnectionFail	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadError;)V
    //   712: goto -678 -> 34
    //   715: iload 5
    //   717: sipush 206
    //   720: if_icmpne +445 -> 1165
    //   723: iload 5
    //   725: istore 4
    //   727: aload_0
    //   728: getfield 33	fm/qingting/downloadnew/DefaultNetwork:TAG	Ljava/lang/String;
    //   731: new 54	java/lang/StringBuilder
    //   734: dup
    //   735: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   738: ldc_w 358
    //   741: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: aload_0
    //   745: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   748: getfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   751: invokevirtual 322	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   754: ldc_w 360
    //   757: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   760: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   763: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   766: pop
    //   767: iload 5
    //   769: istore 4
    //   771: aload_0
    //   772: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   775: getstatic 121	fm/qingting/downloadnew/DownloadState:DOWNLOADING	Lfm/qingting/downloadnew/DownloadState;
    //   778: putfield 161	fm/qingting/downloadnew/DownloadTask:mState	Lfm/qingting/downloadnew/DownloadState;
    //   781: iload 5
    //   783: istore 4
    //   785: aload_0
    //   786: aload_1
    //   787: invokespecial 354	fm/qingting/downloadnew/DefaultNetwork:downloadToFile	(Ljava/net/HttpURLConnection;)V
    //   790: return
    //   791: iload 5
    //   793: istore 4
    //   795: aload_1
    //   796: invokevirtual 364	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   799: ldc_w 366
    //   802: invokeinterface 372 2 0
    //   807: checkcast 374	java/util/List
    //   810: astore_2
    //   811: aload_2
    //   812: ifnull +78 -> 890
    //   815: iload 5
    //   817: istore 4
    //   819: aload_2
    //   820: invokeinterface 377 1 0
    //   825: ifle +65 -> 890
    //   828: iload 5
    //   830: istore 4
    //   832: aload_0
    //   833: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   836: aload_2
    //   837: iconst_0
    //   838: invokeinterface 380 2 0
    //   843: checkcast 382	java/lang/String
    //   846: putfield 106	fm/qingting/downloadnew/DownloadTask:mRedirectUrl	Ljava/lang/String;
    //   849: iload 5
    //   851: istore 4
    //   853: aload_0
    //   854: getfield 33	fm/qingting/downloadnew/DefaultNetwork:TAG	Ljava/lang/String;
    //   857: new 54	java/lang/StringBuilder
    //   860: dup
    //   861: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   864: ldc_w 384
    //   867: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   870: aload_0
    //   871: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   874: getfield 106	fm/qingting/downloadnew/DownloadTask:mRedirectUrl	Ljava/lang/String;
    //   877: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   880: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   883: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   886: pop
    //   887: goto -853 -> 34
    //   890: iload 5
    //   892: istore 4
    //   894: aload_0
    //   895: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   898: ldc_w 386
    //   901: putfield 106	fm/qingting/downloadnew/DownloadTask:mRedirectUrl	Ljava/lang/String;
    //   904: goto -55 -> 849
    //   907: iload 5
    //   909: sipush 416
    //   912: if_icmpne +33 -> 945
    //   915: iload 5
    //   917: istore 4
    //   919: aload_0
    //   920: getfield 40	fm/qingting/downloadnew/DefaultNetwork:mCurTask	Lfm/qingting/downloadnew/DownloadTask;
    //   923: lconst_0
    //   924: putfield 142	fm/qingting/downloadnew/DownloadTask:mCurSize	J
    //   927: iload 5
    //   929: istore 4
    //   931: aload_0
    //   932: getfield 33	fm/qingting/downloadnew/DefaultNetwork:TAG	Ljava/lang/String;
    //   935: ldc_w 388
    //   938: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   941: pop
    //   942: goto -908 -> 34
    //   945: iload 5
    //   947: istore 4
    //   949: new 115	java/io/IOException
    //   952: dup
    //   953: invokespecial 343	java/io/IOException:<init>	()V
    //   956: athrow
    //   957: aload_0
    //   958: ldc_w 281
    //   961: aload_1
    //   962: new 38	fm/qingting/downloadnew/DownloadError
    //   965: dup
    //   966: ldc_w 281
    //   969: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   972: invokespecial 390	fm/qingting/downloadnew/DefaultNetwork:attemptRetryTimeoutOnException	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadError;)V
    //   975: goto -941 -> 34
    //   978: iload 4
    //   980: sipush 200
    //   983: if_icmpeq +11 -> 994
    //   986: iload 4
    //   988: sipush 206
    //   991: if_icmpne +61 -> 1052
    //   994: aload_1
    //   995: invokevirtual 176	java/io/IOException:getMessage	()Ljava/lang/String;
    //   998: ldc_w 392
    //   1001: invokevirtual 396	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   1004: ifeq +27 -> 1031
    //   1007: aload_0
    //   1008: aload_2
    //   1009: getstatic 91	fm/qingting/downloadnew/DownloadState:ERROR	Lfm/qingting/downloadnew/DownloadState;
    //   1012: aload_1
    //   1013: invokevirtual 176	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1016: invokespecial 98	fm/qingting/downloadnew/DefaultNetwork:finishTask	(Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadState;Ljava/lang/Object;)V
    //   1019: new 38	fm/qingting/downloadnew/DownloadError
    //   1022: dup
    //   1023: aload_1
    //   1024: invokevirtual 176	java/io/IOException:getMessage	()Ljava/lang/String;
    //   1027: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   1030: athrow
    //   1031: aload_0
    //   1032: ldc_w 398
    //   1035: aload_2
    //   1036: new 38	fm/qingting/downloadnew/DownloadError
    //   1039: dup
    //   1040: ldc_w 398
    //   1043: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   1046: invokespecial 390	fm/qingting/downloadnew/DefaultNetwork:attemptRetryTimeoutOnException	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadError;)V
    //   1049: goto -1015 -> 34
    //   1052: iload 4
    //   1054: sipush 401
    //   1057: if_icmpeq +11 -> 1068
    //   1060: iload 4
    //   1062: sipush 403
    //   1065: if_icmpne +24 -> 1089
    //   1068: aload_0
    //   1069: ldc_w 400
    //   1072: aload_2
    //   1073: new 38	fm/qingting/downloadnew/DownloadError
    //   1076: dup
    //   1077: ldc_w 402
    //   1080: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   1083: invokespecial 390	fm/qingting/downloadnew/DefaultNetwork:attemptRetryTimeoutOnException	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadError;)V
    //   1086: goto -1052 -> 34
    //   1089: aload_0
    //   1090: new 54	java/lang/StringBuilder
    //   1093: dup
    //   1094: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   1097: ldc_w 404
    //   1100: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1103: iload 4
    //   1105: invokevirtual 68	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1108: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1111: aload_2
    //   1112: new 38	fm/qingting/downloadnew/DownloadError
    //   1115: dup
    //   1116: new 54	java/lang/StringBuilder
    //   1119: dup
    //   1120: invokespecial 55	java/lang/StringBuilder:<init>	()V
    //   1123: ldc_w 404
    //   1126: invokevirtual 59	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1129: iload 4
    //   1131: invokevirtual 68	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1134: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1137: invokespecial 279	fm/qingting/downloadnew/DownloadError:<init>	(Ljava/lang/String;)V
    //   1140: invokespecial 283	fm/qingting/downloadnew/DefaultNetwork:attemptRetryUrlOnConnectionFail	(Ljava/lang/String;Ljava/net/HttpURLConnection;Lfm/qingting/downloadnew/DownloadError;)V
    //   1143: goto -1109 -> 34
    //   1146: astore_1
    //   1147: aconst_null
    //   1148: astore_2
    //   1149: goto -460 -> 689
    //   1152: astore_1
    //   1153: aconst_null
    //   1154: astore_1
    //   1155: goto -590 -> 565
    //   1158: goto -931 -> 227
    //   1161: astore_2
    //   1162: goto -1053 -> 109
    //   1165: iload 5
    //   1167: sipush 301
    //   1170: if_icmpeq -379 -> 791
    //   1173: iload 5
    //   1175: sipush 302
    //   1178: if_icmpne -271 -> 907
    //   1181: goto -390 -> 791
    //
    // Exception table:
    //   from	to	target	type
    //   56	82	108	java/net/SocketTimeoutException
    //   86	108	108	java/net/SocketTimeoutException
    //   227	240	108	java/net/SocketTimeoutException
    //   10	34	145	java/io/FileNotFoundException
    //   10	34	178	java/io/IOException
    //   246	253	564	java/net/MalformedURLException
    //   257	266	564	java/net/MalformedURLException
    //   270	276	564	java/net/MalformedURLException
    //   280	288	564	java/net/MalformedURLException
    //   292	297	564	java/net/MalformedURLException
    //   301	306	564	java/net/MalformedURLException
    //   310	322	564	java/net/MalformedURLException
    //   326	365	564	java/net/MalformedURLException
    //   369	397	564	java/net/MalformedURLException
    //   401	411	564	java/net/MalformedURLException
    //   415	433	564	java/net/MalformedURLException
    //   437	443	564	java/net/MalformedURLException
    //   447	452	564	java/net/MalformedURLException
    //   456	499	564	java/net/MalformedURLException
    //   503	516	564	java/net/MalformedURLException
    //   520	530	564	java/net/MalformedURLException
    //   541	552	564	java/net/MalformedURLException
    //   556	564	564	java/net/MalformedURLException
    //   621	632	564	java/net/MalformedURLException
    //   636	646	564	java/net/MalformedURLException
    //   650	662	564	java/net/MalformedURLException
    //   666	674	564	java/net/MalformedURLException
    //   678	683	564	java/net/MalformedURLException
    //   727	767	564	java/net/MalformedURLException
    //   771	781	564	java/net/MalformedURLException
    //   785	790	564	java/net/MalformedURLException
    //   795	811	564	java/net/MalformedURLException
    //   819	828	564	java/net/MalformedURLException
    //   832	849	564	java/net/MalformedURLException
    //   853	887	564	java/net/MalformedURLException
    //   894	904	564	java/net/MalformedURLException
    //   919	927	564	java/net/MalformedURLException
    //   931	942	564	java/net/MalformedURLException
    //   949	957	564	java/net/MalformedURLException
    //   246	253	684	java/io/IOException
    //   257	266	684	java/io/IOException
    //   270	276	684	java/io/IOException
    //   280	288	684	java/io/IOException
    //   292	297	684	java/io/IOException
    //   301	306	684	java/io/IOException
    //   310	322	684	java/io/IOException
    //   326	365	684	java/io/IOException
    //   369	397	684	java/io/IOException
    //   401	411	684	java/io/IOException
    //   415	433	684	java/io/IOException
    //   437	443	684	java/io/IOException
    //   447	452	684	java/io/IOException
    //   456	499	684	java/io/IOException
    //   503	516	684	java/io/IOException
    //   520	530	684	java/io/IOException
    //   541	552	684	java/io/IOException
    //   556	564	684	java/io/IOException
    //   621	632	684	java/io/IOException
    //   636	646	684	java/io/IOException
    //   650	662	684	java/io/IOException
    //   666	674	684	java/io/IOException
    //   678	683	684	java/io/IOException
    //   727	767	684	java/io/IOException
    //   771	781	684	java/io/IOException
    //   785	790	684	java/io/IOException
    //   795	811	684	java/io/IOException
    //   819	828	684	java/io/IOException
    //   832	849	684	java/io/IOException
    //   853	887	684	java/io/IOException
    //   894	904	684	java/io/IOException
    //   919	927	684	java/io/IOException
    //   931	942	684	java/io/IOException
    //   949	957	684	java/io/IOException
    //   56	82	1146	java/io/IOException
    //   86	108	1146	java/io/IOException
    //   227	240	1146	java/io/IOException
    //   56	82	1152	java/net/MalformedURLException
    //   86	108	1152	java/net/MalformedURLException
    //   227	240	1152	java/net/MalformedURLException
    //   246	253	1161	java/net/SocketTimeoutException
    //   257	266	1161	java/net/SocketTimeoutException
    //   270	276	1161	java/net/SocketTimeoutException
    //   280	288	1161	java/net/SocketTimeoutException
    //   292	297	1161	java/net/SocketTimeoutException
    //   301	306	1161	java/net/SocketTimeoutException
    //   310	322	1161	java/net/SocketTimeoutException
    //   326	365	1161	java/net/SocketTimeoutException
    //   369	397	1161	java/net/SocketTimeoutException
    //   401	411	1161	java/net/SocketTimeoutException
    //   415	433	1161	java/net/SocketTimeoutException
    //   437	443	1161	java/net/SocketTimeoutException
    //   447	452	1161	java/net/SocketTimeoutException
    //   456	499	1161	java/net/SocketTimeoutException
    //   503	516	1161	java/net/SocketTimeoutException
    //   520	530	1161	java/net/SocketTimeoutException
    //   541	552	1161	java/net/SocketTimeoutException
    //   556	564	1161	java/net/SocketTimeoutException
    //   621	632	1161	java/net/SocketTimeoutException
    //   636	646	1161	java/net/SocketTimeoutException
    //   650	662	1161	java/net/SocketTimeoutException
    //   666	674	1161	java/net/SocketTimeoutException
    //   678	683	1161	java/net/SocketTimeoutException
    //   727	767	1161	java/net/SocketTimeoutException
    //   771	781	1161	java/net/SocketTimeoutException
    //   785	790	1161	java/net/SocketTimeoutException
    //   795	811	1161	java/net/SocketTimeoutException
    //   819	828	1161	java/net/SocketTimeoutException
    //   832	849	1161	java/net/SocketTimeoutException
    //   853	887	1161	java/net/SocketTimeoutException
    //   894	904	1161	java/net/SocketTimeoutException
    //   919	927	1161	java/net/SocketTimeoutException
    //   931	942	1161	java/net/SocketTimeoutException
    //   949	957	1161	java/net/SocketTimeoutException
  }

  public void setUrlRewriter(UrlRewriter paramUrlRewriter)
  {
    this.mRewriter = paramUrlRewriter;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.DefaultNetwork
 * JD-Core Version:    0.6.2
 */