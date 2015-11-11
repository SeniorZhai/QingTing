package fm.qingting.framework.data;

import java.util.HashMap;
import java.util.Map;

public class DataSourceProxy
  implements IDataSourceProxy, IDataRecvHandler
{
  protected HashMap<IDataToken, DataToken> requests = new HashMap();

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "DataSourceProxy";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    return null;
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler, IDataSource paramIDataSource)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataRecvHandler(paramIDataRecvHandler);
    localDataToken.setDataInfo(paramDataCommand);
    paramDataCommand = paramIDataSource.doCommand(paramDataCommand, this);
    this.requests.put(paramDataCommand, localDataToken);
    return localDataToken;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return false;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap, IDataSource paramIDataSource)
  {
    return paramIDataSource.isSynchronous(paramString, paramMap);
  }

  public void onRecvData(Object paramObject1, Object paramObject2, IDataToken paramIDataToken, Object paramObject3)
  {
    paramObject2 = (DataToken)this.requests.remove(paramIDataToken);
    if (paramObject2 == null)
      return;
    paramObject2.dispatchDataEvent(paramObject1, this, paramObject3);
  }

  public void onRecvError(String paramString1, String paramString2, Object paramObject1, IDataToken paramIDataToken, Object paramObject2)
  {
    paramIDataToken = (DataToken)this.requests.remove(paramIDataToken);
    if (paramIDataToken == null)
      return;
    paramIDataToken.dispatchErrorEvent(paramString1, paramString2, paramObject1, paramObject2);
  }

  public boolean proxyAvailable(DataCommand paramDataCommand, IDataSource paramIDataSource)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.DataSourceProxy
 * JD-Core Version:    0.6.2
 */