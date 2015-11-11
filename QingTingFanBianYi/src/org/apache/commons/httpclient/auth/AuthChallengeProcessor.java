package org.apache.commons.httpclient.auth;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class AuthChallengeProcessor
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
  private HttpParams params = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$AuthChallengeProcessor == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.AuthChallengeProcessor");
      class$org$apache$commons$httpclient$auth$AuthChallengeProcessor = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$auth$AuthChallengeProcessor;
    }
  }

  public AuthChallengeProcessor(HttpParams paramHttpParams)
  {
    if (paramHttpParams == null)
      throw new IllegalArgumentException("Parameter collection may not be null");
    this.params = paramHttpParams;
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

  public AuthScheme processChallenge(AuthState paramAuthState, Map paramMap)
    throws MalformedChallengeException, AuthenticationException
  {
    if (paramAuthState == null)
      throw new IllegalArgumentException("Authentication state may not be null");
    if (paramMap == null)
      throw new IllegalArgumentException("Challenge map may not be null");
    if ((paramAuthState.isPreemptive()) || (paramAuthState.getAuthScheme() == null))
      paramAuthState.setAuthScheme(selectAuthScheme(paramMap));
    paramAuthState = paramAuthState.getAuthScheme();
    String str = paramAuthState.getSchemeName();
    if (LOG.isDebugEnabled())
      LOG.debug("Using authentication scheme: " + str);
    paramMap = (String)paramMap.get(str.toLowerCase());
    if (paramMap == null)
      throw new AuthenticationException(str + " authorization challenge expected, but not found");
    paramAuthState.processChallenge(paramMap);
    LOG.debug("Authorization challenge processed");
    return paramAuthState;
  }

  public AuthScheme selectAuthScheme(Map paramMap)
    throws AuthChallengeException
  {
    if (paramMap == null)
      throw new IllegalArgumentException("Challenge map may not be null");
    Collection localCollection = (Collection)this.params.getParameter("http.auth.scheme-priority");
    if (localCollection != null)
    {
      localObject = localCollection;
      if (!localCollection.isEmpty());
    }
    else
    {
      localObject = AuthPolicy.getDefaultAuthPrefs();
    }
    if (LOG.isDebugEnabled())
      LOG.debug("Supported authentication schemes in the order of preference: " + localObject);
    localCollection = null;
    Object localObject = ((Collection)localObject).iterator();
    while (true)
    {
      if (!((Iterator)localObject).hasNext())
        localObject = localCollection;
      String str;
      while (true)
      {
        if (localObject != null)
          break label273;
        throw new AuthChallengeException("Unable to respond to any of these challenges: " + paramMap);
        str = (String)((Iterator)localObject).next();
        if ((String)paramMap.get(str.toLowerCase()) != null)
        {
          if (LOG.isInfoEnabled())
            LOG.info(str + " authentication scheme selected");
          try
          {
            localObject = AuthPolicy.getAuthScheme(str);
          }
          catch (IllegalStateException paramMap)
          {
            throw new AuthChallengeException(paramMap.getMessage());
          }
        }
      }
      if (LOG.isDebugEnabled())
        LOG.debug("Challenge for " + str + " authentication scheme not available");
    }
    label273: return localObject;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthChallengeProcessor
 * JD-Core Version:    0.6.2
 */