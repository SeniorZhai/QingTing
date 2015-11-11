package com.tencent.weiyun;

public class WeiyunFile
{
  private String a;
  private String b;
  private String c;
  private long d;

  public WeiyunFile(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
    this.d = paramLong;
  }

  public String getCreateTime()
  {
    return this.c;
  }

  public String getFileId()
  {
    return this.a;
  }

  public String getFileName()
  {
    return this.b;
  }

  public long getFileSize()
  {
    return this.d;
  }

  public void setCreateTime(String paramString)
  {
    this.c = paramString;
  }

  public void setFileId(String paramString)
  {
    this.a = paramString;
  }

  public void setFileName(String paramString)
  {
    this.b = paramString;
  }

  public void setFileSize(long paramLong)
  {
    this.d = paramLong;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weiyun.WeiyunFile
 * JD-Core Version:    0.6.2
 */