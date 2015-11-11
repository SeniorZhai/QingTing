package cn.com.iresearch.mapptracker.fm;

public abstract interface IRCallBack
{
  public abstract void preSend();

  public abstract void sendFail(String paramString);

  public abstract void sendSuccess();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.iresearch.mapptracker.fm.IRCallBack
 * JD-Core Version:    0.6.2
 */