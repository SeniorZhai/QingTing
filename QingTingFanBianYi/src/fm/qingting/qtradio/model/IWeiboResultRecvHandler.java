package fm.qingting.qtradio.model;

import fm.qingting.framework.data.Result;

public abstract interface IWeiboResultRecvHandler
{
  public abstract void onWeiboRecvResult(Result paramResult, WeiboResultToken paramWeiboResultToken);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.IWeiboResultRecvHandler
 * JD-Core Version:    0.6.2
 */