package fm.qingting.framework.data;

public abstract interface IDataToken
{
  public abstract void cancel();

  public abstract Object getData();

  public abstract Object getDataInfo();

  public abstract int getID();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IDataToken
 * JD-Core Version:    0.6.2
 */