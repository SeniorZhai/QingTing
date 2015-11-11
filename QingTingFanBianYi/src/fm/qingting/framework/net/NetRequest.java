package fm.qingting.framework.net;

import fm.qingting.framework.data.DataError;
import fm.qingting.framework.data.IDataRecvHandler;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NetRequest
  implements Runnable, IRequestData
{
  private String _UriData;
  private String _charset;
  private Map<String, Object> _dataInfo;
  private IDataRecvHandler _eventHandler;
  private Map<String, String> _headers;
  private String _method;
  private Map<String, Object> _param;
  private String _url;
  private boolean useTimeStampCache = false;

  public NetRequest(String paramString1, IDataRecvHandler paramIDataRecvHandler, Map<String, Object> paramMap, String paramString2)
  {
    this(paramString1, paramIDataRecvHandler, paramMap, paramString2, null);
  }

  public NetRequest(String paramString1, IDataRecvHandler paramIDataRecvHandler, Map<String, Object> paramMap, String paramString2, String paramString3)
  {
    this(paramString1, paramIDataRecvHandler, paramMap, paramString2, paramString3, null);
  }

  public NetRequest(String paramString1, IDataRecvHandler paramIDataRecvHandler, Map<String, Object> paramMap, String paramString2, String paramString3, Map<String, String> paramMap1)
  {
    this._url = paramString1;
    this._eventHandler = paramIDataRecvHandler;
    this._param = paramMap;
    this._method = paramString2;
    this._charset = paramString3;
    this._headers = paramMap1;
    this._dataInfo = new HashMap();
    this._dataInfo.put("url", this._url);
    this._dataInfo.put("param", this._param);
    this._dataInfo.put("method", this._method);
    this._UriData = null;
  }

  public void enableTimeStampCache()
  {
    this.useTimeStampCache = true;
  }

  public void requestURI(String paramString)
  {
    this._UriData = paramString;
  }

  public void run()
  {
    HTTPConnection localHTTPConnection = new HTTPConnection();
    if (this.useTimeStampCache)
      localHTTPConnection.enableTimeStampCache();
    localHTTPConnection.irData = this;
    long l1 = Calendar.getInstance().getTimeInMillis();
    String str = localHTTPConnection.getData(this._url, this._param, this._method, this._charset, this._headers);
    long l2 = Calendar.getInstance().getTimeInMillis();
    this._dataInfo.put("duration", Long.valueOf(l2 - l1));
    this._dataInfo.put("request", this._UriData);
    this._dataInfo.put("statusline", localHTTPConnection.getmStatusLine());
    this._dataInfo.put("headers", localHTTPConnection.getmHeaders());
    if (str == null)
    {
      this._eventHandler.onRecvError(DataError.NETWORK_ERROR.getCode(), DataError.NETWORK_ERROR.getMessage(), this, null, this._dataInfo);
      return;
    }
    if (str.equalsIgnoreCase("timeout"))
    {
      this._eventHandler.onRecvError("2000", "网络超时", this, null, this._dataInfo);
      return;
    }
    if (str.equalsIgnoreCase("UnknownHost"))
    {
      this._eventHandler.onRecvError("3000", "无法解析主机地址", this, null, this._dataInfo);
      return;
    }
    this._eventHandler.onRecvData(str, this, null, this._dataInfo);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.net.NetRequest
 * JD-Core Version:    0.6.2
 */