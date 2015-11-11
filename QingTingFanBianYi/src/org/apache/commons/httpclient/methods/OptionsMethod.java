package org.apache.commons.httpclient.methods;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OptionsMethod extends HttpMethodBase
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$methods$OptionsMethod;
  private Vector methodsAllowed = new Vector();

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$OptionsMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.OptionsMethod");
      class$org$apache$commons$httpclient$methods$OptionsMethod = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$methods$OptionsMethod;
    }
  }

  public OptionsMethod()
  {
  }

  public OptionsMethod(String paramString)
  {
    super(paramString);
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

  public Enumeration getAllowedMethods()
  {
    checkUsed();
    return this.methodsAllowed.elements();
  }

  public String getName()
  {
    return "OPTIONS";
  }

  public boolean isAllowed(String paramString)
  {
    checkUsed();
    return this.methodsAllowed.contains(paramString);
  }

  public boolean needContentLength()
  {
    return false;
  }

  protected void processResponseHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
  {
    LOG.trace("enter OptionsMethod.processResponseHeaders(HttpState, HttpConnection)");
    paramHttpState = getResponseHeader("allow");
    if (paramHttpState != null)
      paramHttpState = new StringTokenizer(paramHttpState.getValue(), ",");
    while (true)
    {
      if (!paramHttpState.hasMoreElements())
        return;
      paramHttpConnection = paramHttpState.nextToken().trim().toUpperCase();
      this.methodsAllowed.addElement(paramHttpConnection);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.OptionsMethod
 * JD-Core Version:    0.6.2
 */