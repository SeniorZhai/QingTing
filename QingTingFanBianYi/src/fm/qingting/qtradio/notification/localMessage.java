package fm.qingting.qtradio.notification;

class localMessage
{
  public int categoryId = 0;
  public int channelid = 0;
  public String channelname = "";
  public String content = "";
  public boolean hasSend = false;
  public String page = "";
  public int programId = 0;
  public int ringtoneid = 0;
  public String startTime = "";
  public String title = "";
  public int type = 0;

  public localMessage(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, String paramString4, String paramString5, int paramInt3, int paramInt4, int paramInt5)
  {
    this.title = paramString1;
    this.content = paramString2;
    this.channelname = paramString3;
    this.channelid = paramInt1;
    this.startTime = paramString4;
    this.page = paramString5;
    this.type = paramInt4;
    this.categoryId = paramInt5;
    this.programId = paramInt2;
    this.ringtoneid = paramInt3;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.localMessage
 * JD-Core Version:    0.6.2
 */