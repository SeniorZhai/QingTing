package fm.qingting.downloadnew;

import android.text.TextUtils;

public class DownloadTask
  implements Comparable<DownloadTask>
{
  long mCurSize;
  public String mExtra;
  String mFileName;
  public Runnable mFinishRunnable;
  long mFinishTimeMS;
  int mId;
  int mPriority = 0;
  transient String mRedirectUrl;
  public RetryPolicy mRetryPolicy;
  int mSequence = 0;
  DownloadState mState = DownloadState.UNSPECIFIED;
  String mTaskId;
  long mTotalSize;
  String[] mUrls;

  DownloadTask()
  {
  }

  public DownloadTask(String paramString1, String[] paramArrayOfString, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      throw new IllegalArgumentException("taskId不能为空");
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0) || (TextUtils.isEmpty(paramArrayOfString[0])))
      throw new IllegalArgumentException("url不能为空");
    if (TextUtils.isEmpty(paramString2))
      throw new IllegalArgumentException("fileName不能为空");
    this.mTaskId = paramString1;
    this.mFileName = paramString2;
    this.mState = DownloadState.READY;
    this.mUrls = paramArrayOfString;
    this.mRetryPolicy = new DefaultRetryPolicy(paramArrayOfString);
  }

  public int compareTo(DownloadTask paramDownloadTask)
  {
    if (paramDownloadTask == null);
    do
    {
      int i;
      do
      {
        return -1;
        i = this.mPriority - paramDownloadTask.mPriority;
      }
      while (i > 0);
      if (i < 0)
        return 1;
      if (this.mSequence != 0)
        break;
    }
    while (paramDownloadTask.mSequence != 0);
    return this.mId - paramDownloadTask.mId;
    if (paramDownloadTask.mSequence == 0)
      return 1;
    return this.mSequence - paramDownloadTask.mSequence;
  }

  public int getCurRetryTime()
  {
    return this.mRetryPolicy.getCurrentRetryCount();
  }

  public long getCurSize()
  {
    return this.mCurSize;
  }

  public DownloadState getDownloadState()
  {
    return this.mState;
  }

  public String getTaskId()
  {
    return this.mTaskId;
  }

  public int getTimeoutMs()
  {
    return this.mRetryPolicy.getCurrentTimeout();
  }

  public long getTotalSize()
  {
    return this.mTotalSize;
  }

  public String getUrl()
  {
    if (this.mRedirectUrl != null)
      return this.mRedirectUrl;
    return this.mRetryPolicy.getCurrentUrl();
  }

  public void pauseTask()
  {
    this.mState = DownloadState.PAUSED;
  }

  public void resetTask()
  {
    this.mState = DownloadState.READY;
    this.mRetryPolicy = new DefaultRetryPolicy(this.mUrls);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.DownloadTask
 * JD-Core Version:    0.6.2
 */