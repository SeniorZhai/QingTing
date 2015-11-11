package fm.qingting.framework.data;

public abstract interface IDataRecvHandler
{
  public abstract void onRecvData(Object paramObject1, Object paramObject2, IDataToken paramIDataToken, Object paramObject3);

  public abstract void onRecvError(String paramString1, String paramString2, Object paramObject1, IDataToken paramIDataToken, Object paramObject2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IDataRecvHandler
 * JD-Core Version:    0.6.2
 */