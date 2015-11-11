package org.apache.commons.httpclient.auth;

public class AuthState
{
  public static final String PREEMPTIVE_AUTH_SCHEME = "basic";
  private boolean authAttempted = false;
  private boolean authRequested = false;
  private AuthScheme authScheme = null;
  private boolean preemptive = false;

  public AuthScheme getAuthScheme()
  {
    return this.authScheme;
  }

  public String getRealm()
  {
    if (this.authScheme != null)
      return this.authScheme.getRealm();
    return null;
  }

  public void invalidate()
  {
    this.authScheme = null;
    this.authRequested = false;
    this.authAttempted = false;
    this.preemptive = false;
  }

  public boolean isAuthAttempted()
  {
    return this.authAttempted;
  }

  public boolean isAuthRequested()
  {
    return this.authRequested;
  }

  public boolean isPreemptive()
  {
    return this.preemptive;
  }

  public void setAuthAttempted(boolean paramBoolean)
  {
    this.authAttempted = paramBoolean;
  }

  public void setAuthRequested(boolean paramBoolean)
  {
    this.authRequested = paramBoolean;
  }

  public void setAuthScheme(AuthScheme paramAuthScheme)
  {
    if (paramAuthScheme == null)
    {
      invalidate();
      return;
    }
    if ((this.preemptive) && (!this.authScheme.getClass().isInstance(paramAuthScheme)))
    {
      this.preemptive = false;
      this.authAttempted = false;
    }
    this.authScheme = paramAuthScheme;
  }

  public void setPreemptive()
  {
    if (!this.preemptive)
    {
      if (this.authScheme != null)
        throw new IllegalStateException("Authentication state already initialized");
      this.authScheme = AuthPolicy.getAuthScheme("basic");
      this.preemptive = true;
    }
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("Auth state: auth requested [");
    localStringBuffer.append(this.authRequested);
    localStringBuffer.append("]; auth attempted [");
    localStringBuffer.append(this.authAttempted);
    if (this.authScheme != null)
    {
      localStringBuffer.append("]; auth scheme [");
      localStringBuffer.append(this.authScheme.getSchemeName());
      localStringBuffer.append("]; realm [");
      localStringBuffer.append(this.authScheme.getRealm());
    }
    localStringBuffer.append("] preemptive [");
    localStringBuffer.append(this.preemptive);
    localStringBuffer.append("]");
    return localStringBuffer.toString();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthState
 * JD-Core Version:    0.6.2
 */