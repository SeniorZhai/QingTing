package com.tencent.stat;

public class StatAppMonitor
{
  public static final int FAILURE_RESULT_TYPE = 1;
  public static final int LOGIC_FAILURE_RESULT_TYPE = 2;
  public static final int SUCCESS_RESULT_TYPE = 0;
  private String interfaceName = null;
  private long millisecondsConsume = 0L;
  private long reqSize = 0L;
  private long respSize = 0L;
  private int resultType = 0;
  private int returnCode = 0;
  private int sampling = 1;

  public StatAppMonitor(String paramString)
  {
    this.interfaceName = paramString;
  }

  public StatAppMonitor(String paramString, int paramInt1, int paramInt2, long paramLong1, long paramLong2, long paramLong3, int paramInt3)
  {
    this.interfaceName = paramString;
    this.reqSize = paramLong1;
    this.respSize = paramLong2;
    this.resultType = paramInt1;
    this.millisecondsConsume = paramLong3;
    this.returnCode = paramInt2;
    this.sampling = paramInt3;
  }

  public String getInterfaceName()
  {
    return this.interfaceName;
  }

  public long getMillisecondsConsume()
  {
    return this.millisecondsConsume;
  }

  public long getReqSize()
  {
    return this.reqSize;
  }

  public long getRespSize()
  {
    return this.respSize;
  }

  public int getResultType()
  {
    return this.resultType;
  }

  public int getReturnCode()
  {
    return this.returnCode;
  }

  public int getSampling()
  {
    return this.sampling;
  }

  public void setInterfaceName(String paramString)
  {
    this.interfaceName = paramString;
  }

  public void setMillisecondsConsume(long paramLong)
  {
    this.millisecondsConsume = paramLong;
  }

  public void setReqSize(long paramLong)
  {
    this.reqSize = paramLong;
  }

  public void setRespSize(long paramLong)
  {
    this.respSize = paramLong;
  }

  public void setResultType(int paramInt)
  {
    this.resultType = paramInt;
  }

  public void setReturnCode(int paramInt)
  {
    this.returnCode = paramInt;
  }

  public void setSampling(int paramInt)
  {
    int i = paramInt;
    if (paramInt <= 0)
      i = 1;
    this.sampling = i;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.stat.StatAppMonitor
 * JD-Core Version:    0.6.2
 */