package org.apache.commons.httpclient.params;

import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpVersion;

public class DefaultHttpParamsFactory
  implements HttpParamsFactory
{
  static Class class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
  private HttpParams httpParams;

  static Class class$(String paramString)
  {
    try
    {
      paramString = Class.forName(paramString);
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
    }
    throw new NoClassDefFoundError(paramString.getMessage());
  }

  protected HttpParams createParams()
  {
    HttpClientParams localHttpClientParams = new HttpClientParams(null);
    localHttpClientParams.setParameter("http.useragent", "Jakarta Commons-HttpClient/3.0.1");
    localHttpClientParams.setVersion(HttpVersion.HTTP_1_1);
    Object localObject;
    if (class$org$apache$commons$httpclient$SimpleHttpConnectionManager == null)
    {
      localObject = class$("org.apache.commons.httpclient.SimpleHttpConnectionManager");
      class$org$apache$commons$httpclient$SimpleHttpConnectionManager = (Class)localObject;
    }
    while (true)
    {
      localHttpClientParams.setConnectionManagerClass((Class)localObject);
      localHttpClientParams.setCookiePolicy("rfc2109");
      localHttpClientParams.setHttpElementCharset("US-ASCII");
      localHttpClientParams.setContentCharset("ISO-8859-1");
      localHttpClientParams.setParameter("http.method.retry-handler", new DefaultHttpMethodRetryHandler());
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(Arrays.asList(new String[] { "EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z" }));
      localHttpClientParams.setParameter("http.dateparser.patterns", localObject);
      localObject = null;
      try
      {
        String str = System.getProperty("httpclient.useragent");
        localObject = str;
        label192: if (localObject != null)
          localHttpClientParams.setParameter("http.useragent", localObject);
        localObject = null;
        try
        {
          str = System.getProperty("httpclient.authentication.preemptive");
          localObject = str;
          label213: if (localObject != null)
          {
            localObject = ((String)localObject).trim().toLowerCase();
            if (((String)localObject).equals("true"))
              localHttpClientParams.setParameter("http.authentication.preemptive", Boolean.TRUE);
          }
          else
          {
            label243: localObject = null;
          }
          try
          {
            str = System.getProperty("apache.commons.httpclient.cookiespec");
            localObject = str;
            label253: if (localObject != null)
            {
              if (!"COMPATIBILITY".equalsIgnoreCase((String)localObject))
                break label302;
              localHttpClientParams.setCookiePolicy("compatibility");
            }
            label302: 
            do
            {
              return localHttpClientParams;
              localObject = class$org$apache$commons$httpclient$SimpleHttpConnectionManager;
              break;
              if (!((String)localObject).equals("false"))
                break label243;
              localHttpClientParams.setParameter("http.authentication.preemptive", Boolean.FALSE);
              break label243;
              if ("NETSCAPE_DRAFT".equalsIgnoreCase((String)localObject))
              {
                localHttpClientParams.setCookiePolicy("netscape");
                return localHttpClientParams;
              }
            }
            while (!"RFC2109".equalsIgnoreCase((String)localObject));
            localHttpClientParams.setCookiePolicy("rfc2109");
            return localHttpClientParams;
          }
          catch (SecurityException localSecurityException1)
          {
            break label253;
          }
        }
        catch (SecurityException localSecurityException2)
        {
          break label213;
        }
      }
      catch (SecurityException localSecurityException3)
      {
        break label192;
      }
    }
  }

  public HttpParams getDefaultParams()
  {
    try
    {
      if (this.httpParams == null)
        this.httpParams = createParams();
      HttpParams localHttpParams = this.httpParams;
      return localHttpParams;
    }
    finally
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.DefaultHttpParamsFactory
 * JD-Core Version:    0.6.2
 */