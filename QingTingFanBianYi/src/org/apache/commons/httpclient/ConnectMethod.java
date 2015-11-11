package org.apache.commons.httpclient;

import java.io.IOException;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConnectMethod extends HttpMethodBase
{
  private static final Log LOG;
  public static final String NAME = "CONNECT";
  static Class class$org$apache$commons$httpclient$ConnectMethod;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$ConnectMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.ConnectMethod");
      class$org$apache$commons$httpclient$ConnectMethod = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$ConnectMethod;
    }
  }

  public ConnectMethod()
  {
    LOG.trace("enter ConnectMethod()");
  }

  public ConnectMethod(HttpMethod paramHttpMethod)
  {
    LOG.trace("enter ConnectMethod(HttpMethod)");
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

  protected void addCookieRequestHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws IOException, HttpException
  {
  }

  protected void addRequestHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws IOException, HttpException
  {
    LOG.trace("enter ConnectMethod.addRequestHeaders(HttpState, HttpConnection)");
    addUserAgentRequestHeader(paramHttpState, paramHttpConnection);
    addHostRequestHeader(paramHttpState, paramHttpConnection);
    addProxyConnectionHeader(paramHttpState, paramHttpConnection);
  }

  public int execute(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws IOException, HttpException
  {
    LOG.trace("enter ConnectMethod.execute(HttpState, HttpConnection)");
    int i = super.execute(paramHttpState, paramHttpConnection);
    if (LOG.isDebugEnabled())
      LOG.debug("CONNECT status code " + i);
    return i;
  }

  public String getName()
  {
    return "CONNECT";
  }

  protected boolean shouldCloseConnection(HttpConnection paramHttpConnection)
  {
    if (getStatusCode() == 200)
    {
      Header localHeader = null;
      if (!paramHttpConnection.isTransparent())
        localHeader = getResponseHeader("proxy-connection");
      paramHttpConnection = localHeader;
      if (localHeader == null)
        paramHttpConnection = getResponseHeader("connection");
      if ((paramHttpConnection != null) && (paramHttpConnection.getValue().equalsIgnoreCase("close")) && (LOG.isWarnEnabled()))
        LOG.warn("Invalid header encountered '" + paramHttpConnection.toExternalForm() + "' in response " + getStatusLine().toString());
      return false;
    }
    return super.shouldCloseConnection(paramHttpConnection);
  }

  protected void writeRequestLine(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws IOException, HttpException
  {
    int j = paramHttpConnection.getPort();
    int i = j;
    if (j == -1)
      i = paramHttpConnection.getProtocol().getDefaultPort();
    paramHttpState = new StringBuffer();
    paramHttpState.append(getName());
    paramHttpState.append(' ');
    paramHttpState.append(paramHttpConnection.getHost());
    if (i > -1)
    {
      paramHttpState.append(':');
      paramHttpState.append(i);
    }
    paramHttpState.append(" ");
    paramHttpState.append(getEffectiveVersion());
    paramHttpState = paramHttpState.toString();
    paramHttpConnection.printLine(paramHttpState, getParams().getHttpElementCharset());
    if (Wire.HEADER_WIRE.enabled())
      Wire.HEADER_WIRE.output(paramHttpState);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.ConnectMethod
 * JD-Core Version:    0.6.2
 */