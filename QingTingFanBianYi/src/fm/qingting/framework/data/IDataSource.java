package fm.qingting.framework.data;

import java.util.Map;

public abstract interface IDataSource
{
  public abstract void addParser(IDataParser paramIDataParser);

  public abstract String dataSourceName();

  public abstract IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler);

  public abstract boolean isSynchronous(String paramString, Map<String, Object> paramMap);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IDataSource
 * JD-Core Version:    0.6.2
 */