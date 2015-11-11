package fm.qingting.framework.data;

public class ResultToken
  implements IResultToken
{
  private String _event;
  private int _id = 0;
  private Object _parameters = null;
  private String _pos;
  private IResultRecvHandler _recvHandler = null;
  private Result _result;
  private String _source;
  private String _type = "";
  private String dateType;

  public void cancel()
  {
    this._recvHandler = null;
  }

  public void dispatchResultEvent(Object paramObject)
  {
    dispatchResultEvent(paramObject, null);
  }

  public void dispatchResultEvent(Object paramObject, Result paramResult)
  {
    if (paramResult != null)
      this._result = paramResult;
    if ((this._recvHandler != null) && (this._result != null))
      this._recvHandler.onRecvResult(this._result, paramObject, this, this._parameters);
  }

  public String getDataType()
  {
    return this.dateType;
  }

  public String getEventM()
  {
    if ((this._event == null) || (this._event.equalsIgnoreCase("")))
      return null;
    return this._event;
  }

  public int getID()
  {
    return this._id;
  }

  public Object getParametets()
  {
    return this._parameters;
  }

  public String getPosM()
  {
    if ((this._pos == null) || (this._pos.equalsIgnoreCase("")))
      return null;
    return this._pos;
  }

  public Result getResult()
  {
    return this._result;
  }

  public String getSourceM()
  {
    if ((this._source == null) || (this._source.equalsIgnoreCase("")))
      return null;
    return this._source;
  }

  public String getType()
  {
    return this._type;
  }

  public void setDataType(String paramString)
  {
    this.dateType = paramString;
  }

  public void setID(int paramInt)
  {
    this._id = paramInt;
  }

  public void setMobParam(String paramString1, String paramString2, String paramString3)
  {
    this._event = paramString1;
    this._pos = paramString2;
    this._source = paramString3;
  }

  public void setParametets(Object paramObject)
  {
    this._parameters = paramObject;
  }

  public void setResult(Result paramResult)
  {
    this._result = paramResult;
  }

  public void setResultRecvHandler(IResultRecvHandler paramIResultRecvHandler)
  {
    this._recvHandler = paramIResultRecvHandler;
  }

  public void setType(String paramString)
  {
    this._type = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.ResultToken
 * JD-Core Version:    0.6.2
 */