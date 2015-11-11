package fm.qingting.framework.data;

public enum DataError
{
  DATA_304("304", "服务器内容未改变"), NETWORK_ERROR("1000", "网络错误"), REQUEST_ERROR("1001", "无效请求"), CONFIG_ERROR("1002", "配置文件错误"), DATASOURCE_ERROR("1003", "无效数据源"), DATA_ERROR("1004", "数据错误");

  private String _code;
  private String _message;

  private DataError(String arg3, String arg4)
  {
    Object localObject1;
    this._code = localObject1;
    Object localObject2;
    this._message = localObject2;
  }

  public String getCode()
  {
    return this._code;
  }

  public String getMessage()
  {
    return this._message;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.DataError
 * JD-Core Version:    0.6.2
 */