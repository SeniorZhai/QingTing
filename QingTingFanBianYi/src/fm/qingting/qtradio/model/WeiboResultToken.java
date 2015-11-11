package fm.qingting.qtradio.model;

import fm.qingting.framework.event.IEventHandler;
import fm.qingting.qtradio.weiboAgent.WeiboAgent.WeiboDataType;
import java.util.Map;

public class WeiboResultToken
{
  private String _event;
  private IEventHandler _eventHandler;
  private Map<String, Object> _param;
  private String _pos;
  private IWeiboResultRecvHandler _recvHandler;
  private Map<String, Object> _saveparam;
  private String _source;
  private WeiboAgent.WeiboDataType _wd;
  private WeiboRecordData recordData = new WeiboRecordData();

  public void cancel()
  {
    this._recvHandler = null;
  }

  public IEventHandler getEventHandler()
  {
    return this._eventHandler;
  }

  public String getEventM()
  {
    if ((this._event == null) || (this._event.equalsIgnoreCase("")))
      return null;
    return this._event;
  }

  public IWeiboResultRecvHandler getHandler()
  {
    return this._recvHandler;
  }

  public Map<String, Object> getParam()
  {
    return this._param;
  }

  public String getPosM()
  {
    if ((this._pos == null) || (this._pos.equalsIgnoreCase("")))
      return null;
    return this._pos;
  }

  public WeiboRecordData getRecordData()
  {
    return this.recordData;
  }

  public Map<String, Object> getSaveParam()
  {
    return this._saveparam;
  }

  public String getSourceM()
  {
    if ((this._source == null) || (this._source.equalsIgnoreCase("")))
      return null;
    return this._source;
  }

  public WeiboAgent.WeiboDataType getType()
  {
    return this._wd;
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this._eventHandler = paramIEventHandler;
  }

  public void setHandler(IWeiboResultRecvHandler paramIWeiboResultRecvHandler)
  {
    this._recvHandler = paramIWeiboResultRecvHandler;
  }

  public void setMobParam(String paramString1, String paramString2, String paramString3)
  {
    this._event = paramString1;
    this._pos = paramString2;
    this._source = paramString3;
  }

  public void setParam(Map<String, Object> paramMap)
  {
    this._param = paramMap;
  }

  public void setRecordData(WeiboRecordData paramWeiboRecordData)
  {
    this.recordData.Clone(paramWeiboRecordData);
  }

  public void setSaveParam(Map<String, Object> paramMap)
  {
    this._saveparam = paramMap;
  }

  public void setWeiboDataType(WeiboAgent.WeiboDataType paramWeiboDataType)
  {
    this._wd = paramWeiboDataType;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.WeiboResultToken
 * JD-Core Version:    0.6.2
 */