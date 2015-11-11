package com.google.android.apps.analytics;

class Referrer
{
  private final int index;
  private final String referrer;
  private final long timeStamp;
  private final int visit;

  Referrer(String paramString, long paramLong, int paramInt1, int paramInt2)
  {
    this.referrer = paramString;
    this.timeStamp = paramLong;
    this.visit = paramInt1;
    this.index = paramInt2;
  }

  int getIndex()
  {
    return this.index;
  }

  String getReferrerString()
  {
    return this.referrer;
  }

  long getTimeStamp()
  {
    return this.timeStamp;
  }

  int getVisit()
  {
    return this.visit;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.Referrer
 * JD-Core Version:    0.6.2
 */