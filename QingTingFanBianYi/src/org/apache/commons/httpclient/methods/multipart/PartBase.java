package org.apache.commons.httpclient.methods.multipart;

public abstract class PartBase extends Part
{
  private String charSet;
  private String contentType;
  private String name;
  private String transferEncoding;

  public PartBase(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Name must not be null");
    this.name = paramString1;
    this.contentType = paramString2;
    this.charSet = paramString3;
    this.transferEncoding = paramString4;
  }

  public String getCharSet()
  {
    return this.charSet;
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public String getName()
  {
    return this.name;
  }

  public String getTransferEncoding()
  {
    return this.transferEncoding;
  }

  public void setCharSet(String paramString)
  {
    this.charSet = paramString;
  }

  public void setContentType(String paramString)
  {
    this.contentType = paramString;
  }

  public void setName(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name must not be null");
    this.name = paramString;
  }

  public void setTransferEncoding(String paramString)
  {
    this.transferEncoding = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.multipart.PartBase
 * JD-Core Version:    0.6.2
 */