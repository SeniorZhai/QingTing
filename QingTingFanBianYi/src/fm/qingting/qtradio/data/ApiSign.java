package fm.qingting.qtradio.data;

import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataSourceProxy;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.NetDS;
import fm.qingting.framework.utils.MD5;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ApiSign extends DataSourceProxy
  implements IDataRecvHandler
{
  private static final String APP_KEY = "mobile.android";
  private static final String APP_SECRET = "5c47b5d23bebc6c1b96bd2be93f41860";
  private static final String V = "1.0";
  private static String clientID = null;
  private static ApiSign instance;
  private HashMap<IDataToken, DataToken> requests = new HashMap();

  public static ApiSign getInstance()
  {
    if (instance == null)
      instance = new ApiSign();
    return instance;
  }

  public static String getSign(Map<String, Object> paramMap)
  {
    Object localObject = new ArrayList(paramMap.keySet());
    Collections.sort((List)localObject, new ComparatorKey());
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("5c47b5d23bebc6c1b96bd2be93f41860");
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      localStringBuilder.append(str);
      localStringBuilder.append(paramMap.get(str));
    }
    return MD5.md5Encode(localStringBuilder.toString());
  }

  public static HashMap<String, Object> getSignParam()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("app_key", "mobile.android");
    localHashMap.put("clientid", clientID);
    localHashMap.put("phoneagent", "android");
    localHashMap.put("rtime", String.format("%d", new Object[] { Long.valueOf(Calendar.getInstance().getTimeInMillis() / 1000L) }));
    localHashMap.put("v", "1.0");
    return localHashMap;
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "ApiSign";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler, IDataSource paramIDataSource)
  {
    paramIDataSource = new DataToken();
    paramIDataSource.setDataRecvHandler(paramIDataRecvHandler);
    paramIDataSource.setDataInfo(paramDataCommand);
    paramIDataRecvHandler = paramDataCommand.getParam();
    HashMap localHashMap = getSignParam();
    localHashMap.putAll(paramIDataRecvHandler);
    localHashMap.put("sign", getSign(localHashMap));
    paramDataCommand = new DataCommand(paramDataCommand.getRequestTrait(), localHashMap);
    paramDataCommand = NetDS.getInstance().doCommand(paramDataCommand, this);
    this.requests.put(paramDataCommand, paramIDataSource);
    return paramIDataSource;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap, IDataSource paramIDataSource)
  {
    return false;
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

  public void setClientID(String paramString)
  {
    clientID = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.ApiSign
 * JD-Core Version:    0.6.2
 */