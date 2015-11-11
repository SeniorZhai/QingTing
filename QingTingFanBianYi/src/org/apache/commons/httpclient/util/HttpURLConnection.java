package org.apache.commons.httpclient.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpURLConnection extends java.net.HttpURLConnection
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$util$HttpURLConnection;
  private HttpMethod method;
  private URL url;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$util$HttpURLConnection == null)
    {
      localClass = class$("org.apache.commons.httpclient.util.HttpURLConnection");
      class$org$apache$commons$httpclient$util$HttpURLConnection = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$util$HttpURLConnection;
    }
  }

  protected HttpURLConnection(URL paramURL)
  {
    super(paramURL);
    throw new RuntimeException("An HTTP URL connection can only be constructed from a HttpMethod class");
  }

  public HttpURLConnection(HttpMethod paramHttpMethod, URL paramURL)
  {
    super(paramURL);
    this.method = paramHttpMethod;
    this.url = paramURL;
  }

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

  public void connect()
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.connect()");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void disconnect()
  {
    LOG.trace("enter HttpURLConnection.disconnect()");
    throw new RuntimeException("Not implemented yet");
  }

  public boolean getAllowUserInteraction()
  {
    LOG.trace("enter HttpURLConnection.getAllowUserInteraction()");
    throw new RuntimeException("Not implemented yet");
  }

  public Object getContent()
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.getContent()");
    throw new RuntimeException("Not implemented yet");
  }

  public Object getContent(Class[] paramArrayOfClass)
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.getContent(Class[])");
    throw new RuntimeException("Not implemented yet");
  }

  public boolean getDefaultUseCaches()
  {
    LOG.trace("enter HttpURLConnection.getDefaultUseCaches()");
    throw new RuntimeException("Not implemented yet");
  }

  public boolean getDoInput()
  {
    LOG.trace("enter HttpURLConnection.getDoInput()");
    throw new RuntimeException("Not implemented yet");
  }

  public boolean getDoOutput()
  {
    LOG.trace("enter HttpURLConnection.getDoOutput()");
    throw new RuntimeException("Not implemented yet");
  }

  public InputStream getErrorStream()
  {
    LOG.trace("enter HttpURLConnection.getErrorStream()");
    throw new RuntimeException("Not implemented yet");
  }

  public String getHeaderField(int paramInt)
  {
    LOG.trace("enter HttpURLConnection.getHeaderField(int)");
    if (paramInt == 0)
      return this.method.getStatusLine().toString();
    Header[] arrayOfHeader = this.method.getResponseHeaders();
    if ((paramInt < 0) || (paramInt > arrayOfHeader.length))
      return null;
    return arrayOfHeader[(paramInt - 1)].getValue();
  }

  public String getHeaderField(String paramString)
  {
    LOG.trace("enter HttpURLConnection.getHeaderField(String)");
    Header[] arrayOfHeader = this.method.getResponseHeaders();
    int i = arrayOfHeader.length - 1;
    while (true)
    {
      if (i < 0)
        return null;
      if (arrayOfHeader[i].getName().equalsIgnoreCase(paramString))
        return arrayOfHeader[i].getValue();
      i -= 1;
    }
  }

  public String getHeaderFieldKey(int paramInt)
  {
    LOG.trace("enter HttpURLConnection.getHeaderFieldKey(int)");
    if (paramInt == 0);
    Header[] arrayOfHeader;
    do
    {
      return null;
      arrayOfHeader = this.method.getResponseHeaders();
    }
    while ((paramInt < 0) || (paramInt > arrayOfHeader.length));
    return arrayOfHeader[(paramInt - 1)].getName();
  }

  public long getIfModifiedSince()
  {
    LOG.trace("enter HttpURLConnection.getIfmodifiedSince()");
    throw new RuntimeException("Not implemented yet");
  }

  public InputStream getInputStream()
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.getInputStream()");
    return this.method.getResponseBodyAsStream();
  }

  public boolean getInstanceFollowRedirects()
  {
    LOG.trace("enter HttpURLConnection.getInstanceFollowRedirects()");
    throw new RuntimeException("Not implemented yet");
  }

  public OutputStream getOutputStream()
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.getOutputStream()");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public Permission getPermission()
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.getPermission()");
    throw new RuntimeException("Not implemented yet");
  }

  public String getRequestMethod()
  {
    LOG.trace("enter HttpURLConnection.getRequestMethod()");
    return this.method.getName();
  }

  public String getRequestProperty(String paramString)
  {
    LOG.trace("enter HttpURLConnection.getRequestProperty()");
    throw new RuntimeException("Not implemented yet");
  }

  public int getResponseCode()
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.getResponseCode()");
    return this.method.getStatusCode();
  }

  public String getResponseMessage()
    throws IOException
  {
    LOG.trace("enter HttpURLConnection.getResponseMessage()");
    return this.method.getStatusText();
  }

  public URL getURL()
  {
    LOG.trace("enter HttpURLConnection.getURL()");
    return this.url;
  }

  public boolean getUseCaches()
  {
    LOG.trace("enter HttpURLConnection.getUseCaches()");
    throw new RuntimeException("Not implemented yet");
  }

  public void setAllowUserInteraction(boolean paramBoolean)
  {
    LOG.trace("enter HttpURLConnection.setAllowUserInteraction(boolean)");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setDefaultUseCaches(boolean paramBoolean)
  {
    LOG.trace("enter HttpURLConnection.setDefaultUseCaches(boolean)");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setDoInput(boolean paramBoolean)
  {
    LOG.trace("enter HttpURLConnection.setDoInput()");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setDoOutput(boolean paramBoolean)
  {
    LOG.trace("enter HttpURLConnection.setDoOutput()");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setIfModifiedSince(long paramLong)
  {
    LOG.trace("enter HttpURLConnection.setIfModifiedSince(long)");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setInstanceFollowRedirects(boolean paramBoolean)
  {
    LOG.trace("enter HttpURLConnection.setInstanceFollowRedirects(boolean)");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setRequestMethod(String paramString)
    throws ProtocolException
  {
    LOG.trace("enter HttpURLConnection.setRequestMethod(String)");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setRequestProperty(String paramString1, String paramString2)
  {
    LOG.trace("enter HttpURLConnection.setRequestProperty()");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public void setUseCaches(boolean paramBoolean)
  {
    LOG.trace("enter HttpURLConnection.setUseCaches(boolean)");
    throw new RuntimeException("This class can only be used with alreadyretrieved data");
  }

  public boolean usingProxy()
  {
    LOG.trace("enter HttpURLConnection.usingProxy()");
    throw new RuntimeException("Not implemented yet");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.HttpURLConnection
 * JD-Core Version:    0.6.2
 */