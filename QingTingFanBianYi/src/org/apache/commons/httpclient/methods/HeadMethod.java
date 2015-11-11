package org.apache.commons.httpclient.methods;

import java.io.IOException;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HeadMethod extends HttpMethodBase
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$methods$HeadMethod;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$HeadMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.HeadMethod");
      class$org$apache$commons$httpclient$methods$HeadMethod = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$methods$HeadMethod;
    }
  }

  public HeadMethod()
  {
    setFollowRedirects(true);
  }

  public HeadMethod(String paramString)
  {
    super(paramString);
    setFollowRedirects(true);
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

  public int getBodyCheckTimeout()
  {
    return getParams().getIntParameter("http.protocol.head-body-timeout", -1);
  }

  public String getName()
  {
    return "HEAD";
  }

  protected void readResponseBody(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws HttpException, IOException
  {
    LOG.trace("enter HeadMethod.readResponseBody(HttpState, HttpConnection)");
    int i = getParams().getIntParameter("http.protocol.head-body-timeout", -1);
    if (i < 0)
      responseBodyConsumed();
    while (true)
    {
      return;
      if (LOG.isDebugEnabled())
        LOG.debug("Check for non-compliant response body. Timeout in " + i + " ms");
      try
      {
        bool = paramHttpConnection.isResponseAvailable(i);
        if (bool)
          if (getParams().isParameterTrue("http.protocol.reject-head-body"))
            throw new ProtocolException("Body content may not be sent in response to HTTP HEAD request");
      }
      catch (IOException localIOException)
      {
        while (true)
        {
          LOG.debug("An IOException occurred while testing if a response was available, we will assume one is not.", localIOException);
          boolean bool = false;
        }
        LOG.warn("Body content returned in response to HTTP HEAD");
        super.readResponseBody(paramHttpState, paramHttpConnection);
      }
    }
  }

  public void recycle()
  {
    super.recycle();
    setFollowRedirects(true);
  }

  public void setBodyCheckTimeout(int paramInt)
  {
    getParams().setIntParameter("http.protocol.head-body-timeout", paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.HeadMethod
 * JD-Core Version:    0.6.2
 */