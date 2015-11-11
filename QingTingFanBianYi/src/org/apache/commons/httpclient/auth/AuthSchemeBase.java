package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

public abstract class AuthSchemeBase
  implements AuthScheme
{
  private String challenge = null;

  public AuthSchemeBase(String paramString)
    throws MalformedChallengeException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Challenge may not be null");
    this.challenge = paramString;
  }

  public abstract String authenticate(Credentials paramCredentials, String paramString1, String paramString2)
    throws AuthenticationException;

  public abstract String authenticate(Credentials paramCredentials, HttpMethod paramHttpMethod)
    throws AuthenticationException;

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof AuthSchemeBase))
      return this.challenge.equals(((AuthSchemeBase)paramObject).challenge);
    return super.equals(paramObject);
  }

  public abstract String getID();

  public abstract String getParameter(String paramString);

  public abstract String getRealm();

  public abstract String getSchemeName();

  public int hashCode()
  {
    return this.challenge.hashCode();
  }

  public abstract boolean isComplete();

  public abstract boolean isConnectionBased();

  public abstract void processChallenge(String paramString)
    throws MalformedChallengeException;

  public String toString()
  {
    return this.challenge;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthSchemeBase
 * JD-Core Version:    0.6.2
 */