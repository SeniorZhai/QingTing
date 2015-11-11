package fm.qingting.utils;

public abstract interface OnPlayProcessListener
{
  public abstract void onManualSeek();

  public abstract void onProcessChanged();

  public abstract void onProcessMaxChanged();

  public abstract void onProgressPause();

  public abstract void onProgressResume();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.OnPlayProcessListener
 * JD-Core Version:    0.6.2
 */