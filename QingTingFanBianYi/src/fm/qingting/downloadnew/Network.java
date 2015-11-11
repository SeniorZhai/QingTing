package fm.qingting.downloadnew;

abstract interface Network
{
  public abstract void performDownload(DownloadTask paramDownloadTask)
    throws DownloadError;

  public abstract void setUrlRewriter(UrlRewriter paramUrlRewriter);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.Network
 * JD-Core Version:    0.6.2
 */