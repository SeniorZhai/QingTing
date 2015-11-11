package fm.qingting.framework.data;

public class Result
{
  private String _code;
  private Object _data;
  private int _expire = -1;
  private String _message;
  private boolean _success;

  public Result(boolean paramBoolean, Object paramObject)
  {
    setValues(paramBoolean, paramObject, "0", "");
  }

  public Result(boolean paramBoolean, Object paramObject, String paramString1, String paramString2)
  {
    setValues(paramBoolean, paramObject, paramString1, paramString2);
  }

  private void setValues(boolean paramBoolean, Object paramObject, String paramString1, String paramString2)
  {
    this._success = paramBoolean;
    this._data = paramObject;
    this._code = paramString1;
    this._message = paramString2;
  }

  public String getCode()
  {
    return this._code;
  }

  public Object getData()
  {
    return this._data;
  }

  public String getMessage()
  {
    return this._message;
  }

  public boolean getSuccess()
  {
    return this._success;
  }

  public String toString()
  {
    if (this._success);
    for (String str = "true"; ; str = "false")
      return str + " expire:" + this._expire + " code:" + this._code + " message:" + this._message + " data:" + this._data;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.Result
 * JD-Core Version:    0.6.2
 */