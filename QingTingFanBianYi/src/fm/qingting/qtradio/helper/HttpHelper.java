package fm.qingting.qtradio.helper;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpHelper
{
  private static final String ACCEPT_ENCODING = "Accept-Encoding";
  private static final int CONNECTIONS_MAX_PERROUTE = 15;
  private static final int CONNECTIONS_MAX_TOTAL = 15;
  private static final String HEADER_ACCEPT_ENCODING = "gzip, deflate";
  private static final String HEADER_IF_MODIFIED_SINCE = "If-Modified-Since";
  private static final String HEADER_LAST_MODIFIED = "Last-Modified";
  private static final String HEADER_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:22.0) Gecko/20100101 Firefox/22.0";
  public static final int MAX_HTTP_TRY_TIMES = 3;
  private static final int SOCKET_BUFFER_SIZE = 16384;
  private static final int TIME_OUT_CONNECTION = 6000;
  private static final int TIME_OUT_CONNECTIONPOOL = 1000;
  private static final int TIME_OUT_SOCKET = 6000;
  private static HttpHelper mInstance;
  HttpClient mHttpClient;

  private HttpHelper()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, "UTF-8");
    HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, true);
    HttpProtocolParams.setUserAgent(localBasicHttpParams, "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:22.0) Gecko/20100101 Firefox/22.0");
    HttpConnectionParams.setStaleCheckingEnabled(localBasicHttpParams, false);
    HttpConnectionParams.setTcpNoDelay(localBasicHttpParams, true);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 16384);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 6000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 6000);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRouteBean(15));
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 15);
    ConnManagerParams.setTimeout(localBasicHttpParams, 1000L);
    HttpClientParams.setRedirecting(localBasicHttpParams, false);
    SchemeRegistry localSchemeRegistry = new SchemeRegistry();
    localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
    localSchemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
    this.mHttpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(localBasicHttpParams, localSchemeRegistry), localBasicHttpParams);
    this.mHttpClient.getParams().setParameter("http.protocol.handle-redirects", Boolean.valueOf(true));
  }

  private String bindArgsToUrl(String paramString, String[] paramArrayOfString)
  {
    int i = 0;
    if (paramArrayOfString == null)
      return paramString;
    paramString = new StringBuffer(paramString);
    int k;
    for (int j = 0; (i < paramString.length()) && (j < paramArrayOfString.length); j = k)
    {
      k = j;
      if (paramString.charAt(i) == '?')
      {
        paramString.deleteCharAt(i);
        paramString.insert(i, paramArrayOfString[j]);
        k = j + 1;
      }
      i += 1;
    }
    if (j != paramArrayOfString.length)
      throw new IllegalArgumentException("You should input " + j + " Arguments.");
    return paramString.toString();
  }

  private HttpResult doPost(String paramString1, HashMap<String, String> paramHashMap1, HashMap<String, String> paramHashMap2, String paramString2, String paramString3)
  {
    try
    {
      paramString1 = new HttpPost(paramString1);
      if (paramHashMap1 != null)
      {
        paramHashMap1 = paramHashMap1.entrySet().iterator();
        if (paramHashMap1.hasNext())
        {
          paramHashMap1 = (Map.Entry)paramHashMap1.next();
          paramString1.addHeader((String)paramHashMap1.getKey(), (String)paramHashMap1.getValue());
        }
      }
      if (paramHashMap2 != null)
      {
        paramHashMap1 = new ArrayList();
        paramHashMap2 = paramHashMap2.entrySet().iterator();
        if (paramHashMap2.hasNext())
        {
          paramHashMap2 = (Map.Entry)paramHashMap2.next();
          paramHashMap1.add(new BasicNameValuePair((String)paramHashMap2.getKey(), (String)paramHashMap2.getValue()));
        }
        paramString1.setEntity(new UrlEncodedFormEntity(paramHashMap1));
      }
      if (paramString2 != null)
        paramString1.setEntity(new StringEntity(paramString2, "UTF-8"));
      paramString1 = connect(paramString1, paramString3);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public static HttpHelper getInstance()
  {
    try
    {
      if (mInstance == null)
        mInstance = new HttpHelper();
      HttpHelper localHttpHelper = mInstance;
      return localHttpHelper;
    }
    finally
    {
    }
  }

  public HttpResult connect(HttpUriRequest paramHttpUriRequest, String paramString)
  {
    HttpResult localHttpResult = new HttpResult();
    label278: 
    while (true)
      try
      {
        paramHttpUriRequest.addHeader("Accept-Encoding", "gzip, deflate");
        paramHttpUriRequest.addHeader("If-Modified-Since", paramString);
        paramString = this.mHttpClient.execute(paramHttpUriRequest);
        if (paramString != null)
        {
          localHttpResult.statusCode = paramString.getStatusLine().getStatusCode();
          if (localHttpResult.statusCode == 200)
          {
            paramHttpUriRequest = paramString.getFirstHeader("Last-Modified");
            if (paramHttpUriRequest != null)
              localHttpResult.lastModified = paramHttpUriRequest.getValue();
            paramHttpUriRequest = paramString.getEntity();
            if (paramHttpUriRequest != null)
            {
              paramHttpUriRequest = paramHttpUriRequest.getContent();
              paramString = paramString.getFirstHeader("Content-Encoding");
              if ((paramString == null) || (!paramString.getValue().equalsIgnoreCase("gzip")))
                break label278;
              paramHttpUriRequest = new GZIPInputStream(paramHttpUriRequest);
              paramString = new ByteArrayOutputStream();
              arrayOfByte = new byte[1024];
              int i = paramHttpUriRequest.read(arrayOfByte, 0, arrayOfByte.length);
              if (i == -1)
                continue;
              paramString.write(arrayOfByte, 0, i);
              continue;
            }
          }
        }
      }
      catch (Exception paramHttpUriRequest)
      {
        byte[] arrayOfByte;
        if (((paramHttpUriRequest instanceof ConnectTimeoutException)) || ((paramHttpUriRequest instanceof SocketTimeoutException)))
        {
          localHttpResult.statusCode = 601;
          return localHttpResult;
          arrayOfByte = paramString.toByteArray();
          localHttpResult.message = new String(arrayOfByte, "UTF-8");
          localHttpResult.message_bytes = arrayOfByte;
          paramString.close();
          paramHttpUriRequest.close();
          return localHttpResult;
        }
        if ((paramHttpUriRequest instanceof UnknownHostException))
        {
          localHttpResult.statusCode = 602;
          return localHttpResult;
        }
        localHttpResult.statusCode = 603;
        return localHttpResult;
      }
  }

  public HttpResult doGet(String paramString)
  {
    return doGet(paramString, null, "");
  }

  public HttpResult doGet(String paramString1, String paramString2)
  {
    return doGet(paramString1, null, paramString2);
  }

  public HttpResult doGet(String paramString, String[] paramArrayOfString)
  {
    return doGet(paramString, paramArrayOfString, "");
  }

  public HttpResult doGet(String paramString1, String[] paramArrayOfString, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("the url must not be null.");
    return connect(new HttpGet(bindArgsToUrl(paramString1, paramArrayOfString)), paramString2);
  }

  public HttpResult doPost(String paramString)
  {
    return doPost(paramString, null, null, null, "");
  }

  public HttpResult doPost(String paramString1, String paramString2)
  {
    return doPost(paramString1, null, null, paramString2, "");
  }

  public HttpResult doPost(String paramString1, HashMap<String, String> paramHashMap, String paramString2)
  {
    return doPost(paramString1, paramHashMap, null, paramString2, "");
  }

  public HttpResult doPostProtobuf(String paramString, byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    try
    {
      paramString = new HttpPost(paramString);
      paramString.addHeader("Content-Type", "application/octet-stream;charset=utf-8");
      paramString.setEntity(new ByteArrayEntity(paramArrayOfByte));
      paramString = connect(paramString, "");
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public class HttpResult
  {
    public static final int STATUS_CODE_NOCONTENT = 604;
    public static final int STATUS_CODE_NOMODIFY = 304;
    public static final int STATUS_CODE_OK = 200;
    public static final int STATUS_CODE_TIME_OUT = 601;
    public static final int STATUS_CODE_UNKNOW = 603;
    public static final int STATUS_CODE_UNKNOW_HOST = 602;
    public HttpEntity entity;
    public String lastModified;
    public String message;
    public byte[] message_bytes;
    public int statusCode;

    public HttpResult()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.helper.HttpHelper
 * JD-Core Version:    0.6.2
 */