package fm.qingting.async.http.libcore;

import java.net.URI;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class RequestHeaders
{
  private String acceptEncoding;
  private String connection;
  private int contentLength = -1;
  private String contentType;
  private boolean hasAuthorization;
  private final RawHeaders headers;
  private String host;
  private String ifModifiedSince;
  private String ifNoneMatch;
  private int maxAgeSeconds = -1;
  private int maxStaleSeconds = -1;
  private int minFreshSeconds = -1;
  private boolean noCache;
  private boolean onlyIfCached;
  private String proxyAuthorization;
  private String transferEncoding;
  private final URI uri;
  private String userAgent;

  public RequestHeaders(URI paramURI, RawHeaders paramRawHeaders)
  {
    this.uri = paramURI;
    this.headers = paramRawHeaders;
    paramURI = new HeaderParser.CacheControlHandler()
    {
      public void handle(String paramAnonymousString1, String paramAnonymousString2)
      {
        if (paramAnonymousString1.equalsIgnoreCase("no-cache"))
          RequestHeaders.access$002(RequestHeaders.this, true);
        do
        {
          return;
          if (paramAnonymousString1.equalsIgnoreCase("max-age"))
          {
            RequestHeaders.access$102(RequestHeaders.this, HeaderParser.parseSeconds(paramAnonymousString2));
            return;
          }
          if (paramAnonymousString1.equalsIgnoreCase("max-stale"))
          {
            RequestHeaders.access$202(RequestHeaders.this, HeaderParser.parseSeconds(paramAnonymousString2));
            return;
          }
          if (paramAnonymousString1.equalsIgnoreCase("min-fresh"))
          {
            RequestHeaders.access$302(RequestHeaders.this, HeaderParser.parseSeconds(paramAnonymousString2));
            return;
          }
        }
        while (!paramAnonymousString1.equalsIgnoreCase("only-if-cached"));
        RequestHeaders.access$402(RequestHeaders.this, true);
      }
    };
    int i = 0;
    if (i < paramRawHeaders.length())
    {
      String str1 = paramRawHeaders.getFieldName(i);
      String str2 = paramRawHeaders.getValue(i);
      if ("Cache-Control".equalsIgnoreCase(str1))
        HeaderParser.parseCacheControl(str2, paramURI);
      while (true)
      {
        i += 1;
        break;
        if ("Pragma".equalsIgnoreCase(str1))
        {
          if (str2.equalsIgnoreCase("no-cache"))
            this.noCache = true;
        }
        else if ("If-None-Match".equalsIgnoreCase(str1))
          this.ifNoneMatch = str2;
        else if ("If-Modified-Since".equalsIgnoreCase(str1))
          this.ifModifiedSince = str2;
        else if ("Authorization".equalsIgnoreCase(str1))
          this.hasAuthorization = true;
        else if ("Content-Length".equalsIgnoreCase(str1))
          try
          {
            this.contentLength = Integer.parseInt(str2);
          }
          catch (NumberFormatException localNumberFormatException)
          {
          }
        else if ("Transfer-Encoding".equalsIgnoreCase(localNumberFormatException))
          this.transferEncoding = str2;
        else if ("User-Agent".equalsIgnoreCase(localNumberFormatException))
          this.userAgent = str2;
        else if ("Host".equalsIgnoreCase(localNumberFormatException))
          this.host = str2;
        else if ("Connection".equalsIgnoreCase(localNumberFormatException))
          this.connection = str2;
        else if ("Accept-Encoding".equalsIgnoreCase(localNumberFormatException))
          this.acceptEncoding = str2;
        else if ("Content-Type".equalsIgnoreCase(localNumberFormatException))
          this.contentType = str2;
        else if ("Proxy-Authorization".equalsIgnoreCase(localNumberFormatException))
          this.proxyAuthorization = str2;
      }
    }
  }

  public void addCookies(Map<String, List<String>> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      String str = (String)localEntry.getKey();
      if (("Cookie".equalsIgnoreCase(str)) || ("Cookie2".equalsIgnoreCase(str)))
        this.headers.addAll(str, (List)localEntry.getValue());
    }
  }

  public String getAcceptEncoding()
  {
    return this.acceptEncoding;
  }

  public String getConnection()
  {
    return this.connection;
  }

  public int getContentLength()
  {
    return this.contentLength;
  }

  public String getContentType()
  {
    return this.contentType;
  }

  public RawHeaders getHeaders()
  {
    return this.headers;
  }

  public String getHost()
  {
    return this.host;
  }

  public String getIfModifiedSince()
  {
    return this.ifModifiedSince;
  }

  public String getIfNoneMatch()
  {
    return this.ifNoneMatch;
  }

  public int getMaxAgeSeconds()
  {
    return this.maxAgeSeconds;
  }

  public int getMaxStaleSeconds()
  {
    return this.maxStaleSeconds;
  }

  public int getMinFreshSeconds()
  {
    return this.minFreshSeconds;
  }

  public String getProxyAuthorization()
  {
    return this.proxyAuthorization;
  }

  public String getTransferEncoding()
  {
    return this.transferEncoding;
  }

  public URI getUri()
  {
    return this.uri;
  }

  public String getUserAgent()
  {
    return this.userAgent;
  }

  public boolean hasAuthorization()
  {
    return this.hasAuthorization;
  }

  public boolean hasConditions()
  {
    return (this.ifModifiedSince != null) || (this.ifNoneMatch != null);
  }

  public boolean hasConnectionClose()
  {
    return "close".equalsIgnoreCase(this.connection);
  }

  public boolean isChunked()
  {
    return "chunked".equalsIgnoreCase(this.transferEncoding);
  }

  public boolean isNoCache()
  {
    return this.noCache;
  }

  public boolean isOnlyIfCached()
  {
    return this.onlyIfCached;
  }

  public void setAcceptEncoding(String paramString)
  {
    if (this.acceptEncoding != null)
      this.headers.removeAll("Accept-Encoding");
    this.headers.add("Accept-Encoding", paramString);
    this.acceptEncoding = paramString;
  }

  public void setChunked()
  {
    if (this.transferEncoding != null)
      this.headers.removeAll("Transfer-Encoding");
    this.headers.add("Transfer-Encoding", "chunked");
    this.transferEncoding = "chunked";
  }

  public void setConnection(String paramString)
  {
    if (this.connection != null)
      this.headers.removeAll("Connection");
    this.headers.add("Connection", paramString);
    this.connection = paramString;
  }

  public void setContentLength(int paramInt)
  {
    if (this.contentLength != -1)
      this.headers.removeAll("Content-Length");
    this.headers.add("Content-Length", Integer.toString(paramInt));
    this.contentLength = paramInt;
  }

  public void setContentType(String paramString)
  {
    if (this.contentType != null)
      this.headers.removeAll("Content-Type");
    this.headers.add("Content-Type", paramString);
    this.contentType = paramString;
  }

  public void setHost(String paramString)
  {
    if (this.host != null)
      this.headers.removeAll("Host");
    this.headers.add("Host", paramString);
    this.host = paramString;
  }

  public void setIfModifiedSince(Date paramDate)
  {
    if (this.ifModifiedSince != null)
      this.headers.removeAll("If-Modified-Since");
    paramDate = HttpDate.format(paramDate);
    this.headers.add("If-Modified-Since", paramDate);
    this.ifModifiedSince = paramDate;
  }

  public void setIfNoneMatch(String paramString)
  {
    if (this.ifNoneMatch != null)
      this.headers.removeAll("If-None-Match");
    this.headers.add("If-None-Match", paramString);
    this.ifNoneMatch = paramString;
  }

  public void setUserAgent(String paramString)
  {
    if (this.userAgent != null)
      this.headers.removeAll("User-Agent");
    this.headers.add("User-Agent", paramString);
    this.userAgent = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.RequestHeaders
 * JD-Core Version:    0.6.2
 */