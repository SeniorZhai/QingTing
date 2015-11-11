package fm.qingting.framework.data;

public abstract interface IDataParser
{
  public abstract IDataParser getNextParser();

  public abstract Result parse(String paramString, Object paramObject1, Object paramObject2);

  public abstract void setNextParser(IDataParser paramIDataParser);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IDataParser
 * JD-Core Version:    0.6.2
 */