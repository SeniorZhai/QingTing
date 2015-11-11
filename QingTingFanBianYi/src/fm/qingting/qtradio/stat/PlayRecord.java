package fm.qingting.qtradio.stat;

public class PlayRecord
{
  public long broadcast;
  public String catId;
  public String catName;
  public String cid;
  public String cname;
  public int contentType;
  public long duration;
  public String pid;
  public String pname;
  public String subCatId;
  public String subCatName;
  public long time;

  public PlayRecord(long paramLong1, long paramLong2, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, long paramLong3)
  {
    this.time = paramLong1;
    this.duration = paramLong2;
    this.contentType = paramInt;
    this.catId = paramString1;
    this.catName = paramString2;
    this.subCatId = paramString3;
    this.subCatName = paramString4;
    this.cid = paramString5;
    this.cname = paramString6;
    this.pid = paramString7;
    this.pname = paramString8;
    this.broadcast = paramLong3;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.stat.PlayRecord
 * JD-Core Version:    0.6.2
 */