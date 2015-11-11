package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class TraceMethod extends HttpMethodBase
{
  public TraceMethod(String paramString)
  {
    super(paramString);
    setFollowRedirects(false);
  }

  public String getName()
  {
    return "TRACE";
  }

  public void recycle()
  {
    super.recycle();
    setFollowRedirects(false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.TraceMethod
 * JD-Core Version:    0.6.2
 */