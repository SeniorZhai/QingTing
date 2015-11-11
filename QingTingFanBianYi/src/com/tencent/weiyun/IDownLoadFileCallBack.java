package com.tencent.weiyun;

import com.tencent.tauth.UiError;

public abstract interface IDownLoadFileCallBack
{
  public abstract void onDownloadProgress(int paramInt);

  public abstract void onDownloadStart();

  public abstract void onDownloadSuccess(String paramString);

  public abstract void onError(UiError paramUiError);

  public abstract void onPrepareStart();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weiyun.IDownLoadFileCallBack
 * JD-Core Version:    0.6.2
 */