package org.apache.commons.httpclient.methods;

public class PutMethod extends EntityEnclosingMethod
{
  public PutMethod()
  {
  }

  public PutMethod(String paramString)
  {
    super(paramString);
  }

  public String getName()
  {
    return "PUT";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.PutMethod
 * JD-Core Version:    0.6.2
 */