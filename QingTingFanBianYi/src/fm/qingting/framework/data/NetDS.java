package fm.qingting.framework.data;

import fm.qingting.framework.net.NetRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.StatusLine;

public class NetDS
  implements IDataSource, IDataRecvHandler
{
  private static NetDS instance = null;
  private Map<NetRequest, DataToken> _cbMap = new HashMap();
  private IMonitorMobAgent _mobAgent;
  private IDataParser _parser;

  private Map<String, Object> convertParam(Map<String, Object> paramMap)
  {
    Object localObject;
    if (paramMap == null)
    {
      localObject = null;
      return localObject;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramMap.keySet().iterator();
    while (true)
    {
      localObject = localHashMap;
      if (!localIterator.hasNext())
        break;
      localObject = (String)localIterator.next();
      localHashMap.put(localObject, paramMap.get(localObject));
    }
  }

  public static NetDS getInstance()
  {
    try
    {
      if (instance == null)
        instance = new NetDS();
      NetDS localNetDS = instance;
      return localNetDS;
    }
    finally
    {
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
    paramIDataParser.setNextParser(this._parser);
    this._parser = paramIDataParser;
  }

  public String dataSourceName()
  {
    return "net";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    try
    {
      DataToken localDataToken = new DataToken();
      localDataToken.setDataRecvHandler(paramIDataRecvHandler);
      localDataToken.setDataInfo(paramDataCommand);
      paramIDataRecvHandler = new NetRequest(paramDataCommand.getCurrentCommand(), this, convertParam(paramDataCommand.getExtendedParam()), paramDataCommand.getMethod(), paramDataCommand.getEncoding());
      paramDataCommand = (Boolean)paramDataCommand.getParam().get("use_time_stamp_cache");
      if ((paramDataCommand != null) && (paramDataCommand.booleanValue()))
        paramIDataRecvHandler.enableTimeStampCache();
      this._cbMap.put(paramIDataRecvHandler, localDataToken);
      new Thread(paramIDataRecvHandler).start();
      return localDataToken;
    }
    finally
    {
    }
    throw paramDataCommand;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return false;
  }

  public void onRecvData(Object paramObject1, Object paramObject2, IDataToken paramIDataToken, Object paramObject3)
  {
    paramIDataToken = (DataToken)this._cbMap.get(paramObject2);
    this._cbMap.remove(paramObject2);
    if (paramIDataToken == null)
      return;
    paramObject2 = (DataCommand)paramIDataToken.getDataInfo();
    paramObject3 = (Map)paramObject3;
    paramObject3.put("command", paramObject2);
    Object localObject;
    if (paramObject3 != null)
    {
      localObject = (String)paramObject3.get("request");
      ((Long)paramObject3.get("duration")).longValue();
      if (localObject != null)
        ((String)localObject).indexOf("googleapis");
    }
    if (this._parser == null)
      paramObject1 = new Result(true, paramObject1);
    while (true)
    {
      localObject = (StatusLine)paramObject3.get("statusline");
      if (paramObject1 != null)
        break label230;
      if ((localObject == null) || (((StatusLine)localObject).getStatusCode() != 304))
        break;
      paramIDataToken.dispatchErrorEvent(DataError.DATA_304.getCode(), DataError.DATA_304.getMessage(), this, paramObject2);
      return;
      try
      {
        paramObject1 = this._parser.parse(paramObject2.getType(), paramObject2.getParam(), paramObject1);
      }
      catch (Exception paramObject1)
      {
        paramObject1 = null;
      }
    }
    if (this._mobAgent != null);
    paramIDataToken.dispatchErrorEvent(DataError.DATA_ERROR.getCode(), DataError.DATA_ERROR.getMessage(), this, paramObject2);
    return;
    label230: if (this._mobAgent != null);
    paramIDataToken.dispatchDataEvent(paramObject1, this, paramObject3);
  }

  public void onRecvError(String paramString1, String paramString2, Object paramObject1, IDataToken paramIDataToken, Object paramObject2)
  {
    paramIDataToken = (DataToken)this._cbMap.get(paramObject1);
    this._cbMap.remove(paramObject1);
    if (paramIDataToken == null)
      return;
    paramObject1 = (DataCommand)paramIDataToken.getDataInfo();
    paramObject2 = (Map)paramObject2;
    if (paramObject2 != null)
    {
      String str = (String)paramObject2.get("request");
      ((Long)paramObject2.get("duration")).longValue();
      if (str != null)
        str.indexOf("googleapis");
    }
    if (paramString1.equalsIgnoreCase("2000"))
      if ((this._mobAgent != null) && (paramObject2 != null))
        this._mobAgent.sendMobAgentAPITIMEOUT(paramObject1.getType(), paramObject2);
    while (true)
    {
      paramIDataToken.dispatchErrorEvent(paramString1, paramString2, this, paramObject1);
      return;
      if (paramString1.equalsIgnoreCase("3000"))
      {
        if ((this._mobAgent != null) && (paramObject2 != null))
          this._mobAgent.sendMobAgentAPIUNKNOWHOST(paramObject1.getType(), paramObject2);
      }
      else if ((this._mobAgent != null) && (paramObject2 != null))
        this._mobAgent.sendMobAgentAPIERROR(paramObject1.getType(), "网络请求失败", paramObject2);
    }
  }

  public void setMobLister(IMonitorMobAgent paramIMonitorMobAgent)
  {
    this._mobAgent = paramIMonitorMobAgent;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.NetDS
 * JD-Core Version:    0.6.2
 */