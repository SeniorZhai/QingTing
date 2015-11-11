package fm.qingting.qtradio;

class NotificationBean
{
  private String mInfo;
  private boolean mPlaying = false;
  private String mThumb;
  private String mTitle;

  protected String getInfo()
  {
    return this.mInfo;
  }

  protected String getThumb()
  {
    return this.mThumb;
  }

  protected String getTitle()
  {
    return this.mTitle;
  }

  protected boolean isPlaying()
  {
    return this.mPlaying;
  }

  protected void setInfo(String paramString)
  {
    this.mInfo = paramString;
  }

  protected void setPlaying(boolean paramBoolean)
  {
    this.mPlaying = paramBoolean;
  }

  protected void setThumb(String paramString)
  {
    this.mThumb = paramString;
  }

  protected void setTitle(String paramString)
  {
    this.mTitle = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.NotificationBean
 * JD-Core Version:    0.6.2
 */