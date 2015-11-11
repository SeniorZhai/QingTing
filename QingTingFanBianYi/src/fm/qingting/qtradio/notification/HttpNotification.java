package fm.qingting.qtradio.notification;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class HttpNotification
{
  private static HttpNotification _instance;
  private SharedPreferences sharedPrefs;

  private String connect(HttpUriRequest paramHttpUriRequest)
    throws IOException
  {
    Object localObject4 = null;
    Object localObject1 = null;
    Object localObject3 = null;
    paramHttpUriRequest.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:5.0) Gecko/20100101 Firefox/5.0");
    paramHttpUriRequest.addHeader("Accept-Encoding", "gzip");
    Object localObject5 = getLastModified();
    if (localObject5 != null)
      paramHttpUriRequest.addHeader("if-modified-since", (String)localObject5);
    paramHttpUriRequest.getURI().getHost();
    localObject5 = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject5, 60000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject5, 60000);
    localObject5 = new DefaultHttpClient((HttpParams)localObject5);
    Object localObject6;
    try
    {
      localObject6 = ((DefaultHttpClient)localObject5).execute(paramHttpUriRequest);
      if (localObject6 == null)
        return "";
    }
    catch (ClientProtocolException paramHttpUriRequest)
    {
      paramHttpUriRequest.printStackTrace();
      return "";
    }
    catch (SocketTimeoutException paramHttpUriRequest)
    {
      paramHttpUriRequest.printStackTrace();
      return "";
    }
    catch (IOException paramHttpUriRequest)
    {
      while (true)
      {
        paramHttpUriRequest.printStackTrace();
        if (paramHttpUriRequest.getClass().toString().indexOf("ConnectTimeoutException") != -1)
          return "";
        if (paramHttpUriRequest.getClass().toString().indexOf("UnknownHostException") != -1)
          return "";
        localObject6 = null;
      }
    }
    catch (IllegalStateException paramHttpUriRequest)
    {
      paramHttpUriRequest.printStackTrace();
      return "";
    }
    HttpEntity localHttpEntity = ((HttpResponse)localObject6).getEntity();
    if ((((HttpResponse)localObject6).getStatusLine() != null) && (((HttpResponse)localObject6).getStatusLine().getStatusCode() == 304))
      return "304";
    if (localHttpEntity != null)
    {
      String str = "";
      paramHttpUriRequest = (HttpUriRequest)localObject1;
      try
      {
        localObject5 = localHttpEntity.getContent();
        localObject3 = localObject5;
        localObject4 = localObject5;
        paramHttpUriRequest = (HttpUriRequest)localObject5;
        Header localHeader = ((HttpResponse)localObject6).getFirstHeader("Content-Encoding");
        localObject1 = localObject5;
        if (localHeader != null)
        {
          localObject1 = localObject5;
          localObject3 = localObject5;
          localObject4 = localObject5;
          paramHttpUriRequest = (HttpUriRequest)localObject5;
          if (localHeader.getValue().equalsIgnoreCase("gzip"))
          {
            localObject3 = localObject5;
            localObject4 = localObject5;
            paramHttpUriRequest = (HttpUriRequest)localObject5;
            localObject1 = new GZIPInputStream((InputStream)localObject5);
          }
        }
        localObject5 = str;
        localObject3 = localObject1;
        localObject4 = localObject1;
        paramHttpUriRequest = (HttpUriRequest)localObject1;
        if (((HttpResponse)localObject6).containsHeader("Last-Modified"))
        {
          localObject3 = localObject1;
          localObject4 = localObject1;
          paramHttpUriRequest = (HttpUriRequest)localObject1;
          localObject5 = ((HttpResponse)localObject6).getFirstHeader("Last-Modified").getValue();
        }
        localObject3 = localObject1;
        localObject4 = localObject1;
        paramHttpUriRequest = (HttpUriRequest)localObject1;
        saveLastModified((String)localObject5);
        localObject3 = localObject1;
        localObject4 = localObject1;
        paramHttpUriRequest = (HttpUriRequest)localObject1;
        localObject5 = new ByteArrayOutputStream();
        localObject3 = localObject1;
        localObject4 = localObject1;
        paramHttpUriRequest = (HttpUriRequest)localObject1;
        localObject6 = new byte[1024];
        while (true)
        {
          localObject3 = localObject1;
          localObject4 = localObject1;
          paramHttpUriRequest = (HttpUriRequest)localObject1;
          int i = ((InputStream)localObject1).read((byte[])localObject6, 0, localObject6.length);
          if (i == -1)
            break;
          localObject3 = localObject1;
          localObject4 = localObject1;
          paramHttpUriRequest = (HttpUriRequest)localObject1;
          ((ByteArrayOutputStream)localObject5).write((byte[])localObject6, 0, i);
        }
      }
      catch (ParseException localParseException)
      {
        paramHttpUriRequest = (HttpUriRequest)localObject3;
        localParseException.printStackTrace();
        if (localObject3 != null)
          ((InputStream)localObject3).close();
        localHttpEntity.consumeContent();
        return "";
        localObject3 = localParseException;
        localObject4 = localParseException;
        paramHttpUriRequest = localParseException;
        localObject5 = new String(((ByteArrayOutputStream)localObject5).toByteArray());
        return localObject5;
      }
      catch (IOException localIOException)
      {
        paramHttpUriRequest = localObject4;
        localIOException.printStackTrace();
        return "";
      }
      finally
      {
        if (paramHttpUriRequest != null)
          paramHttpUriRequest.close();
        localHttpEntity.consumeContent();
      }
    }
    return "";
  }

  public static HttpNotification getInstance()
  {
    if (_instance == null)
      _instance = new HttpNotification();
    return _instance;
  }

  private String getLastModified()
  {
    if (this.sharedPrefs == null)
      return "000000";
    return this.sharedPrefs.getString("lastmodified", "000000");
  }

  private void saveLastModified(String paramString)
  {
    if ((paramString == null) || (this.sharedPrefs == null))
      return;
    SharedPreferences.Editor localEditor = this.sharedPrefs.edit();
    localEditor.putString("lastmodified", paramString);
    localEditor.commit();
  }

  public String getNotify(String paramString, Map<String, Object> paramMap, Map<String, String> paramMap1)
  {
    try
    {
      paramString = new HttpGet(paramString);
      if (paramMap1 != null)
      {
        paramMap = paramMap1.keySet().iterator();
        while (paramMap.hasNext())
        {
          String str = (String)paramMap.next();
          paramString.setHeader(str, (String)paramMap1.get(str));
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return "";
    }
    paramString = connect(paramString);
    return paramString;
  }

  public void init(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.sharedPrefs = paramContext.getSharedPreferences("client_preferences", 0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.notification.HttpNotification
 * JD-Core Version:    0.6.2
 */