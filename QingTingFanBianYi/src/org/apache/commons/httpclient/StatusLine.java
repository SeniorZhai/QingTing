package org.apache.commons.httpclient;

public class StatusLine
{
  private final String httpVersion;
  private final String reasonPhrase;
  private final int statusCode;
  private final String statusLine;

  public StatusLine(String paramString)
    throws HttpException
  {
    int m = paramString.length();
    int i = 0;
    int j = 0;
    try
    {
      int k;
      while (true)
      {
        if (!Character.isWhitespace(paramString.charAt(j)))
        {
          k = j + 4;
          try
          {
            if ("HTTP".equals(paramString.substring(j, k)))
              break;
            throw new HttpException("Status-Line '" + paramString + "' does not start with HTTP");
          }
          catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException1)
          {
          }
          label82: throw new HttpException("Status-Line '" + paramString + "' is not valid");
        }
        i += 1;
        j += 1;
      }
      j = paramString.indexOf(" ", k);
      if (j <= 0)
        throw new ProtocolException("Unable to parse HTTP-Version from the status line: '" + paramString + "'");
      this.httpVersion = paramString.substring(i, j).toUpperCase();
      i = j;
      if (paramString.charAt(i) != ' ')
      {
        j = paramString.indexOf(" ", i);
        k = j;
        if (j < 0)
          k = m;
      }
      while (true)
      {
        try
        {
          this.statusCode = Integer.parseInt(paramString.substring(i, k));
          i = k + 1;
          if (i < m)
          {
            this.reasonPhrase = paramString.substring(i).trim();
            this.statusLine = paramString;
            return;
            i += 1;
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
          throw new ProtocolException("Unable to parse status code from status line: '" + paramString + "'");
        }
        this.reasonPhrase = "";
      }
    }
    catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException2)
    {
      break label82;
    }
  }

  public static boolean startsWithHTTP(String paramString)
  {
    int i = 0;
    try
    {
      while (true)
      {
        if (!Character.isWhitespace(paramString.charAt(i)))
        {
          boolean bool = "HTTP".equals(paramString.substring(i, i + 4));
          return bool;
        }
        i += 1;
      }
    }
    catch (StringIndexOutOfBoundsException paramString)
    {
    }
    return false;
  }

  public final String getHttpVersion()
  {
    return this.httpVersion;
  }

  public final String getReasonPhrase()
  {
    return this.reasonPhrase;
  }

  public final int getStatusCode()
  {
    return this.statusCode;
  }

  public final String toString()
  {
    return this.statusLine;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.StatusLine
 * JD-Core Version:    0.6.2
 */