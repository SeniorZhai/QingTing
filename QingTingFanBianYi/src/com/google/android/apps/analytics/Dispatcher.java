package com.google.android.apps.analytics;

abstract interface Dispatcher
{
  public abstract void dispatchHits(Hit[] paramArrayOfHit);

  public abstract void init(Callbacks paramCallbacks);

  public abstract boolean isDryRun();

  public abstract void setDryRun(boolean paramBoolean);

  public abstract void stop();

  public static abstract interface Callbacks
  {
    public abstract void dispatchFinished();

    public abstract void hitDispatched(long paramLong);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.Dispatcher
 * JD-Core Version:    0.6.2
 */