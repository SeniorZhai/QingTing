package org.apache.commons.httpclient.params;

import java.io.Serializable;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultHttpParams
  implements HttpParams, Serializable, Cloneable
{
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$params$DefaultHttpParams;
  private static HttpParamsFactory httpParamsFactory;
  private HttpParams defaults = null;
  private HashMap parameters = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$params$DefaultHttpParams == null)
    {
      localClass = class$("org.apache.commons.httpclient.params.DefaultHttpParams");
      class$org$apache$commons$httpclient$params$DefaultHttpParams = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      httpParamsFactory = new DefaultHttpParamsFactory();
      return;
      localClass = class$org$apache$commons$httpclient$params$DefaultHttpParams;
    }
  }

  public DefaultHttpParams()
  {
    this(getDefaultParams());
  }

  public DefaultHttpParams(HttpParams paramHttpParams)
  {
    this.defaults = paramHttpParams;
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

  public static HttpParams getDefaultParams()
  {
    return httpParamsFactory.getDefaultParams();
  }

  public static void setHttpParamsFactory(HttpParamsFactory paramHttpParamsFactory)
  {
    if (paramHttpParamsFactory == null)
      throw new IllegalArgumentException("httpParamsFactory may not be null");
    httpParamsFactory = paramHttpParamsFactory;
  }

  public void clear()
  {
    this.parameters = null;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    DefaultHttpParams localDefaultHttpParams = (DefaultHttpParams)super.clone();
    if (this.parameters != null)
      localDefaultHttpParams.parameters = ((HashMap)this.parameters.clone());
    localDefaultHttpParams.setDefaults(this.defaults);
    return localDefaultHttpParams;
  }

  public boolean getBooleanParameter(String paramString, boolean paramBoolean)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramBoolean;
    return ((Boolean)paramString).booleanValue();
  }

  public HttpParams getDefaults()
  {
    try
    {
      HttpParams localHttpParams = this.defaults;
      return localHttpParams;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public double getDoubleParameter(String paramString, double paramDouble)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramDouble;
    return ((Double)paramString).doubleValue();
  }

  public int getIntParameter(String paramString, int paramInt)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramInt;
    return ((Integer)paramString).intValue();
  }

  public long getLongParameter(String paramString, long paramLong)
  {
    paramString = getParameter(paramString);
    if (paramString == null)
      return paramLong;
    return ((Long)paramString).longValue();
  }

  public Object getParameter(String paramString)
  {
    Object localObject = null;
    try
    {
      if (this.parameters != null)
        localObject = this.parameters.get(paramString);
      if (localObject != null);
      while (true)
      {
        return localObject;
        if (this.defaults != null)
          localObject = this.defaults.getParameter(paramString);
        else
          localObject = null;
      }
    }
    finally
    {
    }
    throw paramString;
  }

  public boolean isParameterFalse(String paramString)
  {
    boolean bool = false;
    if (!getBooleanParameter(paramString, false))
      bool = true;
    return bool;
  }

  public boolean isParameterSet(String paramString)
  {
    return getParameter(paramString) != null;
  }

  public boolean isParameterSetLocally(String paramString)
  {
    return (this.parameters != null) && (this.parameters.get(paramString) != null);
  }

  public boolean isParameterTrue(String paramString)
  {
    return getBooleanParameter(paramString, false);
  }

  public void setBooleanParameter(String paramString, boolean paramBoolean)
  {
    setParameter(paramString, new Boolean(paramBoolean));
  }

  public void setDefaults(HttpParams paramHttpParams)
  {
    try
    {
      this.defaults = paramHttpParams;
      return;
    }
    finally
    {
      paramHttpParams = finally;
    }
    throw paramHttpParams;
  }

  public void setDoubleParameter(String paramString, double paramDouble)
  {
    setParameter(paramString, new Double(paramDouble));
  }

  public void setIntParameter(String paramString, int paramInt)
  {
    setParameter(paramString, new Integer(paramInt));
  }

  public void setLongParameter(String paramString, long paramLong)
  {
    setParameter(paramString, new Long(paramLong));
  }

  public void setParameter(String paramString, Object paramObject)
  {
    try
    {
      if (this.parameters == null)
        this.parameters = new HashMap();
      this.parameters.put(paramString, paramObject);
      if (LOG.isDebugEnabled())
        LOG.debug("Set parameter " + paramString + " = " + paramObject);
      return;
    }
    finally
    {
    }
    throw paramString;
  }

  public void setParameters(String[] paramArrayOfString, Object paramObject)
  {
    int i = 0;
    try
    {
      while (true)
      {
        int j = paramArrayOfString.length;
        if (i >= j)
          return;
        setParameter(paramArrayOfString[i], paramObject);
        i += 1;
      }
    }
    finally
    {
    }
    throw paramArrayOfString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.DefaultHttpParams
 * JD-Core Version:    0.6.2
 */