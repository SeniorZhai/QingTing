package fm.qingting.downloadnew;

abstract interface RetryPolicy
{
  public abstract int getCurrentRetryCount();

  public abstract int getCurrentTimeout();

  public abstract String getCurrentUrl();

  public abstract void retryTimeout(DownloadError paramDownloadError)
    throws DownloadError;

  public abstract void retryUrl(DownloadError paramDownloadError)
    throws DownloadError;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.RetryPolicy
 * JD-Core Version:    0.6.2
 */