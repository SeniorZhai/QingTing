package org.apache.commons.httpclient.params;

public abstract interface HttpParams
{
  public abstract boolean getBooleanParameter(String paramString, boolean paramBoolean);

  public abstract HttpParams getDefaults();

  public abstract double getDoubleParameter(String paramString, double paramDouble);

  public abstract int getIntParameter(String paramString, int paramInt);

  public abstract long getLongParameter(String paramString, long paramLong);

  public abstract Object getParameter(String paramString);

  public abstract boolean isParameterFalse(String paramString);

  public abstract boolean isParameterSet(String paramString);

  public abstract boolean isParameterSetLocally(String paramString);

  public abstract boolean isParameterTrue(String paramString);

  public abstract void setBooleanParameter(String paramString, boolean paramBoolean);

  public abstract void setDefaults(HttpParams paramHttpParams);

  public abstract void setDoubleParameter(String paramString, double paramDouble);

  public abstract void setIntParameter(String paramString, int paramInt);

  public abstract void setLongParameter(String paramString, long paramLong);

  public abstract void setParameter(String paramString, Object paramObject);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.HttpParams
 * JD-Core Version:    0.6.2
 */