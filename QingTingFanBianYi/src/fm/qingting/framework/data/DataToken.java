package fm.qingting.framework.data;

public class DataToken
  implements IDataToken
{
  private Object _data;
  private Object _dataInfo = null;
  private IDataRecvHandler _handler = null;
  private int _id = 0;

  public void cancel()
  {
    this._handler = null;
  }

  public void dispatchDataEvent(Object paramObject1, Object paramObject2, Object paramObject3)
  {
    if (paramObject1 != null)
      this._data = paramObject1;
    if (this._handler != null)
      this._handler.onRecvData(this._data, paramObject2, this, this._dataInfo);
  }

  public void dispatchErrorEvent(String paramString1, String paramString2, Object paramObject1, Object paramObject2)
  {
    if (this._handler != null)
      this._handler.onRecvError(paramString1, paramString2, paramObject1, this, this._dataInfo);
  }

  public Object getData()
  {
    return this._data;
  }

  public Object getDataInfo()
  {
    return this._dataInfo;
  }

  public int getID()
  {
    return this._id;
  }

  public void setData(Object paramObject)
  {
    this._data = paramObject;
  }

  public void setDataInfo(Object paramObject)
  {
    this._dataInfo = paramObject;
  }

  public void setDataRecvHandler(IDataRecvHandler paramIDataRecvHandler)
  {
    this._handler = paramIDataRecvHandler;
  }

  public void setID(int paramInt)
  {
    this._id = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.DataToken
 * JD-Core Version:    0.6.2
 */