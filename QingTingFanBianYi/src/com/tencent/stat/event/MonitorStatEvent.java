package com.tencent.stat.event;

import android.content.Context;
import com.tencent.stat.StatAppMonitor;
import com.tencent.stat.common.StatCommonHelper;
import org.json.JSONException;
import org.json.JSONObject;

public class MonitorStatEvent extends Event
{
  private static String appVersion = null;
  private static String simOperator = null;
  private StatAppMonitor monitor = null;

  public MonitorStatEvent(Context paramContext, int paramInt, StatAppMonitor paramStatAppMonitor)
  {
    super(paramContext, paramInt);
    this.monitor = paramStatAppMonitor;
  }

  public EventType getType()
  {
    return EventType.MONITOR_STAT;
  }

  public boolean onEncode(JSONObject paramJSONObject)
    throws JSONException
  {
    if (this.monitor == null)
      return false;
    paramJSONObject.put("na", this.monitor.getInterfaceName());
    paramJSONObject.put("rq", this.monitor.getReqSize());
    paramJSONObject.put("rp", this.monitor.getRespSize());
    paramJSONObject.put("rt", this.monitor.getResultType());
    paramJSONObject.put("tm", this.monitor.getMillisecondsConsume());
    paramJSONObject.put("rc", this.monitor.getReturnCode());
    paramJSONObject.put("sp", this.monitor.getSampling());
    if (appVersion == null)
      appVersion = StatCommonHelper.getAppVersion(this.ctx);
    StatCommonHelper.jsonPut(paramJSONObject, "av", appVersion);
    if (simOperator == null)
      simOperator = StatCommonHelper.getSimOperator(this.ctx);
    StatCommonHelper.jsonPut(paramJSONObject, "op", simOperator);
    paramJSONObject.put("cn", StatCommonHelper.getLinkedWay(this.ctx));
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.event.MonitorStatEvent
 * JD-Core Version:    0.6.2
 */