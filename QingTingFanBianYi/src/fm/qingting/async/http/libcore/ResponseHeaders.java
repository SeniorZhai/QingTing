package fm.qingting.async.http.libcore;

import java.net.URI;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public final class ResponseHeaders
{
  private static final String RECEIVED_MILLIS = "X-Android-Received-Millis";
  private static final String SENT_MILLIS = "X-Android-Sent-Millis";
  private int ageSeconds = -1;
  private String connection;
  private String contentEncoding;
  private int contentLength = -1;
  private String etag;
  private Date expires;
  private final RawHeaders headers;
  private boolean isPublic;
  private Date lastModified;
  private int maxAgeSeconds = -1;
  private boolean mustRevalidate;
  private boolean noCache;
  private boolean noStore;
  private String proxyAuthenticate;
  private long receivedResponseMillis;
  private int sMaxAgeSeconds = -1;
  private long sentRequestMillis;
  private Date servedDate;
  private String transferEncoding;
  private final URI uri;
  private Set<String> varyFields = Collections.emptySet();
  private String wwwAuthenticate;

  public ResponseHeaders(URI paramURI, RawHeaders paramRawHeaders)
  {
    this.uri = paramURI;
    this.headers = paramRawHeaders;
    paramURI = new HeaderParser.CacheControlHandler()
    {
      public void handle(String paramAnonymousString1, String paramAnonymousString2)
      {
        if (paramAnonymousString1.equalsIgnoreCase("no-cache"))
          ResponseHeaders.access$002(ResponseHeaders.this, true);
        do
        {
          return;
          if (paramAnonymousString1.equalsIgnoreCase("no-store"))
          {
            ResponseHeaders.access$102(ResponseHeaders.this, true);
            return;
          }
          if (paramAnonymousString1.equalsIgnoreCase("max-age"))
          {
            ResponseHeaders.access$202(ResponseHeaders.this, HeaderParser.parseSeconds(paramAnonymousString2));
            return;
          }
          if (paramAnonymousString1.equalsIgnoreCase("s-maxage"))
          {
            ResponseHeaders.access$302(ResponseHeaders.this, HeaderParser.parseSeconds(paramAnonymousString2));
            return;
          }
          if (paramAnonymousString1.equalsIgnoreCase("public"))
          {
            ResponseHeaders.access$402(ResponseHeaders.this, true);
            return;
          }
        }
        while (!paramAnonymousString1.equalsIgnoreCase("must-revalidate"));
        ResponseHeaders.access$502(ResponseHeaders.this, true);
      }
    };
    int i = 0;
    if (i < paramRawHeaders.length())
    {
      Object localObject = paramRawHeaders.getFieldName(i);
      String str = paramRawHeaders.getValue(i);
      if ("Cache-Control".equalsIgnoreCase((String)localObject))
        HeaderParser.parseCacheControl(str, paramURI);
      while (true)
      {
        i += 1;
        break;
        if ("Date".equalsIgnoreCase((String)localObject))
        {
          this.servedDate = HttpDate.parse(str);
        }
        else if ("Expires".equalsIgnoreCase((String)localObject))
        {
          this.expires = HttpDate.parse(str);
        }
        else if ("Last-Modified".equalsIgnoreCase((String)localObject))
        {
          this.lastModified = HttpDate.parse(str);
        }
        else if ("ETag".equalsIgnoreCase((String)localObject))
        {
          this.etag = str;
        }
        else if ("Pragma".equalsIgnoreCase((String)localObject))
        {
          if (str.equalsIgnoreCase("no-cache"))
            this.noCache = true;
        }
        else if ("Age".equalsIgnoreCase((String)localObject))
        {
          this.ageSeconds = HeaderParser.parseSeconds(str);
        }
        else if ("Vary".equalsIgnoreCase((String)localObject))
        {
          if (this.varyFields.isEmpty())
            this.varyFields = new TreeSet(String.CASE_INSENSITIVE_ORDER);
          localObject = str.split(",");
          int k = localObject.length;
          int j = 0;
          while (j < k)
          {
            str = localObject[j];
            this.varyFields.add(str.trim());
            j += 1;
          }
        }
        else if ("Content-Encoding".equalsIgnoreCase((String)localObject))
        {
          this.contentEncoding = str;
        }
        else if ("Transfer-Encoding".equalsIgnoreCase((String)localObject))
        {
          this.transferEncoding = str;
        }
        else if ("Content-Length".equalsIgnoreCase((String)localObject))
        {
          try
          {
            this.contentLength = Integer.parseInt(str);
          }
          catch (NumberFormatException localNumberFormatException)
          {
          }
        }
        else if ("Connection".equalsIgnoreCase(localNumberFormatException))
        {
          this.connection = str;
        }
        else if ("Proxy-Authenticate".equalsIgnoreCase(localNumberFormatException))
        {
          this.proxyAuthenticate = str;
        }
        else if ("WWW-Authenticate".equalsIgnoreCase(localNumberFormatException))
        {
          this.wwwAuthenticate = str;
        }
        else if ("X-Android-Sent-Millis".equalsIgnoreCase(localNumberFormatException))
        {
          this.sentRequestMillis = Long.parseLong(str);
        }
        else if ("X-Android-Received-Millis".equalsIgnoreCase(localNumberFormatException))
        {
          this.receivedResponseMillis = Long.parseLong(str);
        }
      }
    }
  }

  private long computeAge(long paramLong)
  {
    long l1 = 0L;
    if (this.servedDate != null)
      l1 = Math.max(0L, this.receivedResponseMillis - this.servedDate.getTime());
    long l2 = l1;
    if (this.ageSeconds != -1)
      l2 = Math.max(l1, TimeUnit.SECONDS.toMillis(this.ageSeconds));
    return l2 + (this.receivedResponseMillis - this.sentRequestMillis) + (paramLong - this.receivedResponseMillis);
  }

  private long computeFreshnessLifetime()
  {
    long l2 = 0L;
    if (this.maxAgeSeconds != -1)
      l1 = TimeUnit.SECONDS.toMillis(this.maxAgeSeconds);
    label72: 
    do
    {
      do
      {
        return l1;
        if (this.expires != null)
        {
          if (this.servedDate != null)
          {
            l1 = this.servedDate.getTime();
            l1 = this.expires.getTime() - l1;
            if (l1 <= 0L)
              break label72;
          }
          while (true)
          {
            return l1;
            l1 = this.receivedResponseMillis;
            break;
            l1 = 0L;
          }
        }
        l1 = l2;
      }
      while (this.lastModified == null);
      l1 = l2;
    }
    while (this.uri.getRawQuery() != null);
    if (this.servedDate != null);
    for (long l1 = this.servedDate.getTime(); ; l1 = this.sentRequestMillis)
    {
      long l3 = l1 - this.lastModified.getTime();
      l1 = l2;
      if (l3 <= 0L)
        break;
      return l3 / 10L;
    }
  }

  private static boolean isEndToEnd(String paramString)
  {
    return (!paramString.equalsIgnoreCase("Connection")) && (!paramString.equalsIgnoreCase("Keep-Alive")) && (!paramString.equalsIgnoreCase("Proxy-Authenticate")) && (!paramString.equalsIgnoreCase("Proxy-Authorization")) && (!paramString.equalsIgnoreCase("TE")) && (!paramString.equalsIgnoreCase("Trailers")) && (!paramString.equalsIgnoreCase("Transfer-Encoding")) && (!paramString.equalsIgnoreCase("Upgrade"));
  }

  private boolean isFreshnessLifetimeHeuristic()
  {
    return (this.maxAgeSeconds == -1) && (this.expires == null);
  }

  public ResponseSource chooseResponseSource(long paramLong, RequestHeaders paramRequestHeaders)
  {
    long l3 = 0L;
    if (!isCacheable(paramRequestHeaders))
      return ResponseSource.NETWORK;
    if ((paramRequestHeaders.isNoCache()) || (paramRequestHeaders.hasConditions()))
      return ResponseSource.NETWORK;
    long l4 = computeAge(paramLong);
    long l1 = computeFreshnessLifetime();
    paramLong = l1;
    if (paramRequestHeaders.getMaxAgeSeconds() != -1)
      paramLong = Math.min(l1, TimeUnit.SECONDS.toMillis(paramRequestHeaders.getMaxAgeSeconds()));
    if (paramRequestHeaders.getMinFreshSeconds() != -1);
    for (l1 = TimeUnit.SECONDS.toMillis(paramRequestHeaders.getMinFreshSeconds()); ; l1 = 0L)
    {
      long l2 = l3;
      if (!this.mustRevalidate)
      {
        l2 = l3;
        if (paramRequestHeaders.getMaxStaleSeconds() != -1)
          l2 = TimeUnit.SECONDS.toMillis(paramRequestHeaders.getMaxStaleSeconds());
      }
      if ((!this.noCache) && (l4 + l1 < l2 + paramLong))
      {
        if (l1 + l4 >= paramLong)
          this.headers.add("Warning", "110 HttpURLConnection \"Response is stale\"");
        if ((l4 > 86400000L) && (isFreshnessLifetimeHeuristic()))
          this.headers.add("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
        return ResponseSource.CACHE;
      }
      if (this.etag != null)
        paramRequestHeaders.setIfNoneMatch(this.etag);
      while (paramRequestHeaders.hasConditions())
      {
        return ResponseSource.CONDITIONAL_CACHE;
        if (this.lastModified != null)
          paramRequestHeaders.setIfModifiedSince(this.lastModified);
        else if (this.servedDate != null)
          paramRequestHeaders.setIfModifiedSince(this.servedDate);
      }
      return ResponseSource.NETWORK;
    }
  }

  public ResponseHeaders combine(ResponseHeaders paramResponseHeaders)
  {
    int k = 0;
    RawHeaders localRawHeaders = new RawHeaders();
    int i = 0;
    int j = k;
    String str1;
    if (i < this.headers.length())
    {
      str1 = this.headers.getFieldName(i);
      String str2 = this.headers.getValue(i);
      if ((str1.equals("Warning")) && (str2.startsWith("1")));
      while (true)
      {
        i += 1;
        break;
        if ((!isEndToEnd(str1)) || (paramResponseHeaders.headers.get(str1) == null))
          localRawHeaders.add(str1, str2);
      }
    }
    while (j < paramResponseHeaders.headers.length())
    {
      str1 = paramResponseHeaders.headers.getFieldName(j);
      if (isEndToEnd(str1))
        localRawHeaders.add(str1, paramResponseHeaders.headers.getValue(j));
      j += 1;
    }
    return new ResponseHeaders(this.uri, localRawHeaders);
  }

  public String getConnection()
  {
    return this.connection;
  }

  public String getContentEncoding()
  {
    return this.contentEncoding;
  }

  public int getContentLength()
  {
    return this.contentLength;
  }

  public String getEtag()
  {
    return this.etag;
  }

  public Date getExpires()
  {
    return this.expires;
  }

  public RawHeaders getHeaders()
  {
    return this.headers;
  }

  public Date getLastModified()
  {
    return this.lastModified;
  }

  public int getMaxAgeSeconds()
  {
    return this.maxAgeSeconds;
  }

  public String getProxyAuthenticate()
  {
    return this.proxyAuthenticate;
  }

  public int getSMaxAgeSeconds()
  {
    return this.sMaxAgeSeconds;
  }

  public Date getServedDate()
  {
    return this.servedDate;
  }

  public URI getUri()
  {
    return this.uri;
  }

  public Set<String> getVaryFields()
  {
    return this.varyFields;
  }

  public String getWwwAuthenticate()
  {
    return this.wwwAuthenticate;
  }

  public boolean hasConnectionClose()
  {
    return "close".equalsIgnoreCase(this.connection);
  }

  public boolean hasVaryAll()
  {
    return this.varyFields.contains("*");
  }

  public boolean isCacheable(RequestHeaders paramRequestHeaders)
  {
    int i = this.headers.getResponseCode();
    if ((i != 200) && (i != 203) && (i != 300) && (i != 301) && (i != 410));
    while (((paramRequestHeaders.hasAuthorization()) && (!this.isPublic) && (!this.mustRevalidate) && (this.sMaxAgeSeconds == -1)) || (this.noStore))
      return false;
    return true;
  }

  public boolean isChunked()
  {
    return "chunked".equalsIgnoreCase(this.transferEncoding);
  }

  public boolean isContentEncodingGzip()
  {
    return "gzip".equalsIgnoreCase(this.contentEncoding);
  }

  public boolean isMustRevalidate()
  {
    return this.mustRevalidate;
  }

  public boolean isNoCache()
  {
    return this.noCache;
  }

  public boolean isNoStore()
  {
    return this.noStore;
  }

  public boolean isPublic()
  {
    return this.isPublic;
  }

  public void setLocalTimestamps(long paramLong1, long paramLong2)
  {
    this.sentRequestMillis = paramLong1;
    this.headers.add("X-Android-Sent-Millis", Long.toString(paramLong1));
    this.receivedResponseMillis = paramLong2;
    this.headers.add("X-Android-Received-Millis", Long.toString(paramLong2));
  }

  public void stripContentEncoding()
  {
    this.contentEncoding = null;
    this.headers.removeAll("Content-Encoding");
  }

  public boolean validate(ResponseHeaders paramResponseHeaders)
  {
    if (paramResponseHeaders.headers.getResponseCode() == 304);
    while ((this.lastModified != null) && (paramResponseHeaders.lastModified != null) && (paramResponseHeaders.lastModified.getTime() < this.lastModified.getTime()))
      return true;
    return false;
  }

  public boolean varyMatches(Map<String, List<String>> paramMap1, Map<String, List<String>> paramMap2)
  {
    Iterator localIterator = this.varyFields.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!Objects.equal(paramMap1.get(str), paramMap2.get(str)))
        return false;
    }
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.ResponseHeaders
 * JD-Core Version:    0.6.2
 */