package org.apache.commons.httpclient.auth;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AuthPolicy
{
  public static final String AUTH_SCHEME_PRIORITY = "http.auth.scheme-priority";
  public static final String BASIC = "Basic";
  public static final String DIGEST = "Digest";
  protected static final Log LOG;
  public static final String NTLM = "NTLM";
  private static final HashMap SCHEMES = new HashMap();
  private static final ArrayList SCHEME_LIST = new ArrayList();
  static Class class$org$apache$commons$httpclient$auth$AuthPolicy;
  static Class class$org$apache$commons$httpclient$auth$BasicScheme;
  static Class class$org$apache$commons$httpclient$auth$DigestScheme;
  static Class class$org$apache$commons$httpclient$auth$NTLMScheme;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$auth$NTLMScheme == null)
    {
      localClass = class$("org.apache.commons.httpclient.auth.NTLMScheme");
      class$org$apache$commons$httpclient$auth$NTLMScheme = localClass;
      registerAuthScheme("NTLM", localClass);
      if (class$org$apache$commons$httpclient$auth$DigestScheme != null)
        break label117;
      localClass = class$("org.apache.commons.httpclient.auth.DigestScheme");
      class$org$apache$commons$httpclient$auth$DigestScheme = localClass;
      label58: registerAuthScheme("Digest", localClass);
      if (class$org$apache$commons$httpclient$auth$BasicScheme != null)
        break label124;
      localClass = class$("org.apache.commons.httpclient.auth.BasicScheme");
      class$org$apache$commons$httpclient$auth$BasicScheme = localClass;
      label80: registerAuthScheme("Basic", localClass);
      if (class$org$apache$commons$httpclient$auth$AuthPolicy != null)
        break label131;
      localClass = class$("org.apache.commons.httpclient.auth.AuthPolicy");
      class$org$apache$commons$httpclient$auth$AuthPolicy = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$auth$NTLMScheme;
      break;
      label117: localClass = class$org$apache$commons$httpclient$auth$DigestScheme;
      break label58;
      label124: localClass = class$org$apache$commons$httpclient$auth$BasicScheme;
      break label80;
      label131: localClass = class$org$apache$commons$httpclient$auth$AuthPolicy;
    }
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

  public static AuthScheme getAuthScheme(String paramString)
    throws IllegalStateException
  {
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("Id may not be null");
      }
      finally
      {
      }
    Class localClass = (Class)SCHEMES.get(paramString.toLowerCase());
    if (localClass != null)
      try
      {
        AuthScheme localAuthScheme = (AuthScheme)localClass.newInstance();
        return localAuthScheme;
      }
      catch (Exception localException)
      {
        LOG.error("Error initializing authentication scheme: " + paramString, localException);
        throw new IllegalStateException(paramString + " authentication scheme implemented by " + localClass.getName() + " could not be initialized");
      }
    throw new IllegalStateException("Unsupported authentication scheme " + paramString);
  }

  public static List getDefaultAuthPrefs()
  {
    try
    {
      List localList = (List)SCHEME_LIST.clone();
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static void registerAuthScheme(String paramString, Class paramClass)
  {
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("Id may not be null");
      }
      finally
      {
      }
    if (paramClass == null)
      throw new IllegalArgumentException("Authentication scheme class may not be null");
    SCHEMES.put(paramString.toLowerCase(), paramClass);
    SCHEME_LIST.add(paramString.toLowerCase());
  }

  public static void unregisterAuthScheme(String paramString)
  {
    if (paramString == null)
      try
      {
        throw new IllegalArgumentException("Id may not be null");
      }
      finally
      {
      }
    SCHEMES.remove(paramString.toLowerCase());
    SCHEME_LIST.remove(paramString.toLowerCase());
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.auth.AuthPolicy
 * JD-Core Version:    0.6.2
 */