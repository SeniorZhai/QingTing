package com.neusoft.ssp.api;

public abstract interface QTFM_NEW_RequestListener
{
  public abstract void notifyAllList(Object paramObject);

  public abstract void notifyCityList(Object paramObject);

  public abstract void notifyCollectList(Object paramObject);

  public abstract void notifyCollectProgram(Object paramObject, int paramInt1, int paramInt2, int paramInt3);

  public abstract void notifyConnectStatus(int paramInt);

  public abstract void notifyDownLoadProgramList(Object paramObject, int paramInt);

  public abstract void notifyDownLoadRadioList(Object paramObject);

  public abstract void notifyExitApp(Object paramObject);

  public abstract void notifyGetRadioPic(Object paramObject, String paramString, int paramInt);

  public abstract void notifyKeyWordList(Object paramObject);

  public abstract void notifyLabelList(Object paramObject, int paramInt1, int paramInt2);

  public abstract void notifyLiveRadioList(Object paramObject, int paramInt, String paramString);

  public abstract void notifyLivingRadio(Object paramObject);

  public abstract void notifyNextProgram(Object paramObject);

  public abstract void notifyPlayOrPause(Object paramObject, int paramInt);

  public abstract void notifyPlayRadio(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4);

  public abstract void notifyPreProgram(Object paramObject);

  public abstract void notifyProgramList(Object paramObject, int paramInt1, int paramInt2, int paramInt3);

  public abstract void notifyRadioList(Object paramObject, int paramInt1, String paramString, int paramInt2);

  public abstract void notifyRecentList(Object paramObject);

  public abstract void notifyResultList(Object paramObject, String paramString);

  public abstract void notifySelectList(Object paramObject);

  public abstract void notifyWakeUp();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.api.QTFM_NEW_RequestListener
 * JD-Core Version:    0.6.2
 */