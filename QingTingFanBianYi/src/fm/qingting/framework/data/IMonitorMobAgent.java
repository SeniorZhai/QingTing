package fm.qingting.framework.data;

public abstract interface IMonitorMobAgent
{
  public abstract void sendMobAgentAPI(String paramString1, String paramString2, Object paramObject);

  public abstract void sendMobAgentAPIERROR(String paramString1, String paramString2, Object paramObject);

  public abstract void sendMobAgentAPITIMEOUT(String paramString, Object paramObject);

  public abstract void sendMobAgentAPIUNKNOWHOST(String paramString, Object paramObject);

  public abstract void sendMobAgentEvent(String paramString);

  public abstract void sendMobAgentEvent(String paramString1, String paramString2);

  public abstract void sendMobAgentEventDuration(String paramString, Long paramLong);

  public abstract void sendMobAgentEventDuration(String paramString1, String paramString2, Long paramLong);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.IMonitorMobAgent
 * JD-Core Version:    0.6.2
 */