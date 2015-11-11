package com.google.android.apps.analytics;

abstract interface HitStore
{
  public abstract void clearReferrer();

  public abstract void deleteHit(long paramLong);

  public abstract int getNumStoredHits();

  public abstract Referrer getReferrer();

  public abstract String getSessionId();

  public abstract int getStoreId();

  public abstract String getVisitorCustomVar(int paramInt);

  public abstract String getVisitorId();

  public abstract void loadExistingSession();

  public abstract Hit[] peekHits();

  public abstract Hit[] peekHits(int paramInt);

  public abstract void putEvent(Event paramEvent);

  public abstract void setAnonymizeIp(boolean paramBoolean);

  public abstract boolean setReferrer(String paramString);

  public abstract void setSampleRate(int paramInt);

  public abstract void startNewVisit();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.HitStore
 * JD-Core Version:    0.6.2
 */