package com.tencent.weiyun;

import com.tencent.tauth.UiError;

public abstract interface IUploadFileCallBack
{
  public abstract void onError(UiError paramUiError);

  public abstract void onPrepareStart();

  public abstract void onUploadProgress(int paramInt);

  public abstract void onUploadStart();

  public abstract void onUploadSuccess();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weiyun.IUploadFileCallBack
 * JD-Core Version:    0.6.2
 */