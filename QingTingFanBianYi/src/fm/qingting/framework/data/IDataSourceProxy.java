package fm.qingting.framework.data;

import java.util.Map;

public abstract interface IDataSourceProxy extends IDataSource
{
  public abstract IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler, IDataSource paramIDataSource);

  public abstract boolean isSynchronous(String paramString, Map<String, Object> paramMap, IDataSource paramIDataSource);

  public abstract boolean proxyAvailable(DataCommand paramDataCommand, IDataSource paramIDataSource);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IDataSourceProxy
 * JD-Core Version:    0.6.2
 */