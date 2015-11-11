package org.apache.commons.httpclient.params;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HostConfiguration;

public class HttpConnectionManagerParams extends HttpConnectionParams
{
  public static final String MAX_HOST_CONNECTIONS = "http.connection-manager.max-per-host";
  public static final String MAX_TOTAL_CONNECTIONS = "http.connection-manager.max-total";

  public int getDefaultMaxConnectionsPerHost()
  {
    return getMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION);
  }

  public int getMaxConnectionsPerHost(HostConfiguration paramHostConfiguration)
  {
    Object localObject = (Map)getParameter("http.connection-manager.max-per-host");
    if (localObject == null);
    do
    {
      return 2;
      localObject = (Integer)((Map)localObject).get(paramHostConfiguration);
      if ((localObject == null) && (paramHostConfiguration != HostConfiguration.ANY_HOST_CONFIGURATION))
        return getMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION);
    }
    while (localObject == null);
    return ((Integer)localObject).intValue();
  }

  public int getMaxTotalConnections()
  {
    return getIntParameter("http.connection-manager.max-total", 20);
  }

  public void setDefaultMaxConnectionsPerHost(int paramInt)
  {
    setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, paramInt);
  }

  public void setMaxConnectionsPerHost(HostConfiguration paramHostConfiguration, int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxHostConnections must be greater than 0");
    Object localObject = (Map)getParameter("http.connection-manager.max-per-host");
    if (localObject == null);
    for (localObject = new HashMap(); ; localObject = new HashMap((Map)localObject))
    {
      ((Map)localObject).put(paramHostConfiguration, new Integer(paramInt));
      setParameter("http.connection-manager.max-per-host", localObject);
      return;
    }
  }

  public void setMaxTotalConnections(int paramInt)
  {
    setIntParameter("http.connection-manager.max-total", paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.params.HttpConnectionManagerParams
 * JD-Core Version:    0.6.2
 */