package org.apache.commons.httpclient.auth;

import java.util.Map;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpMethod;

public abstract class RFC2617Scheme
  implements AuthScheme
{
  private Map params = null;

  public RFC2617Scheme()
  {
  }

  public RFC2617Scheme(String paramString)
    throws MalformedChallengeException
  {
    processChallenge(paramString);
  }

  public abstract String authenticate(Credentials paramCredentials, String paramString1, String paramString2)
    throws AuthenticationException;

  public abstract String authenticate(Credentials paramCredentials, HttpMethod paramHttpMethod)
    throws AuthenticationException;

  public String getID()
  {
    return getRealm();
  }

  public String getParameter(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter name may not be null");
    if (this.params == null)
      return null;
    return (String)this.params.get(paramString.toLowerCase());
  }

  protected Map getParameters()
  {
    return this.params;
  }

  public String getRealm()
  {
    return getParameter("realm");
  }

  public abstract String getSchemeName();

  public abstract boolean isComplete();

  public abstract boolean isConnectionBased();

  public void processChallenge(String paramString)
    throws MalformedChallengeException
  {
    if (!AuthChallengeParser.extractScheme(paramString).equalsIgnoreCase(getSchemeName()))
      throw new MalformedChallengeException("Invalid " + getSchemeName() + " challenge: " + paramString);
    this.params = AuthChallengeParser.extractParams(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.RFC2617Scheme
 * JD-Core Version:    0.6.2
 */