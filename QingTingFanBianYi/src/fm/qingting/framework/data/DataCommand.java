package fm.qingting.framework.data;

import java.util.HashMap;
import java.util.Map;

public final class DataCommand
{
  protected String dataType;
  protected String method;
  protected int nextCount = 0;
  protected Map<String, Object> param;
  protected RequestTrait requestTrait;
  protected String type;

  public DataCommand(DataCommand paramDataCommand, boolean paramBoolean)
  {
    this.method = paramDataCommand.method;
    this.type = paramDataCommand.type;
    this.dataType = paramDataCommand.dataType;
    this.nextCount = paramDataCommand.nextCount;
    this.requestTrait = paramDataCommand.requestTrait;
    if (!paramBoolean)
    {
      this.param = paramDataCommand.param;
      return;
    }
    this.param = new HashMap(paramDataCommand.param);
  }

  public DataCommand(RequestTrait paramRequestTrait, Map<String, Object> paramMap)
  {
    this.type = paramRequestTrait.getType();
    this.method = paramRequestTrait.getMethod();
    Object localObject = paramMap;
    if (paramMap == null)
      localObject = new HashMap();
    this.param = ((Map)localObject);
    this.dataType = paramRequestTrait.getDataType();
    this.requestTrait = paramRequestTrait;
  }

  public String getCurrentCommand()
  {
    return this.requestTrait.commandWithParam(this.param);
  }

  public String getDataType()
  {
    return this.dataType;
  }

  public String getEncoding()
  {
    return this.requestTrait.encoding;
  }

  public Map<String, Object> getExtendedParam()
  {
    return this.requestTrait.extendedParamWithParam(this.param);
  }

  public String getMethod()
  {
    return this.method;
  }

  public final String getNextCommand()
  {
    this.nextCount += 1;
    return this.requestTrait.nextCommandWithParam(this.param);
  }

  public final int getNextCount()
  {
    return this.nextCount;
  }

  public Map<String, Object> getParam()
  {
    return this.param;
  }

  public RequestTrait getRequestTrait()
  {
    return this.requestTrait;
  }

  public String getType()
  {
    return this.type;
  }

  public final boolean hasRetryAllCommands()
  {
    return this.nextCount >= this.requestTrait.getTotalCommands();
  }

  public final void resetNextCount()
  {
    this.nextCount = 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.DataCommand
 * JD-Core Version:    0.6.2
 */