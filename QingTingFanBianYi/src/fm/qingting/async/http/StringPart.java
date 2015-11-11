package fm.qingting.async.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringPart extends StreamPart
{
  String value;

  public StringPart(String paramString1, String paramString2)
  {
    super(paramString1, paramString2.getBytes().length, null);
    this.value = paramString2;
  }

  protected InputStream getInputStream()
    throws IOException
  {
    return new ByteArrayInputStream(this.value.getBytes());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.StringPart
 * JD-Core Version:    0.6.2
 */