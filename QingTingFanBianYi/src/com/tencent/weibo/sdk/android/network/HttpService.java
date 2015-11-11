package com.tencent.weibo.sdk.android.network;

import java.util.Iterator;
import java.util.List;

public class HttpService
{
  private static HttpService instance = null;
  private final int TAG_RUNNING = 1;
  private final int TAG_WAITTING = 0;
  private List<HttpReq> mRunningReqList = null;
  private int mThreadNum = 4;
  private List<HttpReq> mWaitReqList = null;

  public static HttpService getInstance()
  {
    if (instance == null)
      instance = new HttpService();
    return instance;
  }

  public void SetAsynchronousTaskNum(int paramInt)
  {
  }

  public void addImmediateReq(HttpReq paramHttpReq)
  {
    paramHttpReq.setServiceTag(1);
    this.mRunningReqList.add(paramHttpReq);
    paramHttpReq.execute(new Void[0]);
  }

  public void addNormalReq(HttpReq paramHttpReq)
  {
    if (this.mRunningReqList.size() < this.mThreadNum)
    {
      paramHttpReq.setServiceTag(1);
      this.mRunningReqList.add(paramHttpReq);
      paramHttpReq.execute(new Void[0]);
      return;
    }
    paramHttpReq.setServiceTag(0);
    this.mWaitReqList.add(paramHttpReq);
  }

  public void cancelAllReq()
  {
    Iterator localIterator = this.mRunningReqList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        this.mWaitReqList.clear();
        return;
      }
      ((HttpReq)localIterator.next()).cancel(true);
    }
  }

  public void cancelReq(HttpReq paramHttpReq)
  {
    Iterator localIterator;
    if (paramHttpReq.getServiceTag() == 1)
    {
      localIterator = this.mRunningReqList.iterator();
      break label27;
      label18: if (localIterator.hasNext());
    }
    while (true)
    {
      label27: return;
      if ((HttpReq)localIterator.next() != paramHttpReq)
        break label18;
      paramHttpReq.cancel(true);
      this.mRunningReqList.remove(paramHttpReq);
      break label18;
      if (paramHttpReq.getServiceTag() != 0)
        break;
      localIterator = this.mWaitReqList.iterator();
      while (localIterator.hasNext())
        if (paramHttpReq == localIterator.next())
          this.mWaitReqList.remove(paramHttpReq);
    }
  }

  public void onReqFinish(HttpReq paramHttpReq)
  {
    Object localObject = this.mRunningReqList.iterator();
    if (!((Iterator)localObject).hasNext());
    while (true)
    {
      if ((this.mWaitReqList.size() > 0) && (this.mRunningReqList.size() < this.mThreadNum))
      {
        paramHttpReq = this.mWaitReqList.iterator();
        localObject = (HttpReq)paramHttpReq.next();
        paramHttpReq.remove();
        ((HttpReq)localObject).setServiceTag(1);
        this.mRunningReqList.add(localObject);
        ((HttpReq)localObject).execute(new Void[0]);
      }
      return;
      if (paramHttpReq != (HttpReq)((Iterator)localObject).next())
        break;
      ((Iterator)localObject).remove();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.network.HttpService
 * JD-Core Version:    0.6.2
 */