package org.apache.commons.httpclient.methods;

import org.apache.commons.httpclient.HttpMethodBase;

public class DeleteMethod extends HttpMethodBase
{
  public DeleteMethod()
  {
  }

  public DeleteMethod(String paramString)
  {
    super(paramString);
  }

  public String getName()
  {
    return "DELETE";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.DeleteMethod
 * JD-Core Version:    0.6.2
 */