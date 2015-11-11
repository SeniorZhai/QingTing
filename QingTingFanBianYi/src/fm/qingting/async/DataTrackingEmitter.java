package fm.qingting.async;

public abstract interface DataTrackingEmitter extends DataEmitter
{
  public abstract int getBytesRead();

  public abstract DataTracker getDataTracker();

  public abstract void setDataEmitter(DataEmitter paramDataEmitter);

  public abstract void setDataTracker(DataTracker paramDataTracker);

  public static abstract interface DataTracker
  {
    public abstract void onData(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.DataTrackingEmitter
 * JD-Core Version:    0.6.2
 */