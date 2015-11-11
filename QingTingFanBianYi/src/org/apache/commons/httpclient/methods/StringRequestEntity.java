package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.NameValuePair;

public class StringRequestEntity
  implements RequestEntity
{
  private String charset;
  private byte[] content;
  private String contentType;

  public StringRequestEntity(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("The content cannot be null");
    this.contentType = null;
    this.charset = null;
    this.content = paramString.getBytes();
  }

  public StringRequestEntity(String paramString1, String paramString2, String paramString3)
    throws UnsupportedEncodingException
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("The content cannot be null");
    this.contentType = paramString2;
    this.charset = paramString3;
    HeaderElement[] arrayOfHeaderElement;
    Object localObject;
    int i;
    if (paramString2 != null)
    {
      arrayOfHeaderElement = HeaderElement.parseElements(paramString2);
      localObject = null;
      i = 0;
      if (i < arrayOfHeaderElement.length)
        break label90;
      label52: if ((paramString3 != null) || (localObject == null))
        break label124;
      this.charset = localObject.getValue();
    }
    while (true)
    {
      if (this.charset == null)
        break label163;
      this.content = paramString1.getBytes(this.charset);
      return;
      label90: NameValuePair localNameValuePair = arrayOfHeaderElement[i].getParameterByName("charset");
      localObject = localNameValuePair;
      if (localNameValuePair != null)
        break label52;
      i += 1;
      localObject = localNameValuePair;
      break;
      label124: if ((paramString3 != null) && (localObject == null))
        this.contentType = (paramString2 + "; charset=" + paramString3);
    }
    label163: this.content = paramString1.getBytes();
  }

  public String getCharset()
  {
    return this.charset;
  }

  public String getContent()
  {
    if (this.charset != null)
      try
      {
        String str = new String(this.content, this.charset);
        return str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        return new String(this.content);
      }
    return new String(this.content);
  }

  public long getContentLength()
  {
    return this.content.length;
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public void writeRequest(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    paramOutputStream.write(this.content);
    paramOutputStream.flush();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.StringRequestEntity
 * JD-Core Version:    0.6.2
 */