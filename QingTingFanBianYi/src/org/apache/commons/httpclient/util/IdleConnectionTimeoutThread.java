package org.apache.commons.httpclient.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.httpclient.HttpConnectionManager;

public class IdleConnectionTimeoutThread extends Thread
{
  private List connectionManagers = new ArrayList();
  private long connectionTimeout = 3000L;
  private boolean shutdown = false;
  private long timeoutInterval = 1000L;

  public IdleConnectionTimeoutThread()
  {
    setDaemon(true);
  }

  public void addConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
    }
    finally
    {
    }
    this.connectionManagers.add(paramHttpConnectionManager);
  }

  public void removeConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
    }
    finally
    {
    }
    this.connectionManagers.remove(paramHttpConnectionManager);
  }

  public void run()
  {
    try
    {
      if (this.shutdown)
      {
        this.connectionManagers.clear();
        return;
      }
      Iterator localIterator = this.connectionManagers.iterator();
      while (true)
      {
        while (true)
        {
          boolean bool = localIterator.hasNext();
          if (bool)
            break label57;
          try
          {
            wait(this.timeoutInterval);
          }
          catch (InterruptedException localInterruptedException)
          {
          }
        }
        break;
        label57: ((HttpConnectionManager)localInterruptedException.next()).closeIdleConnections(this.connectionTimeout);
      }
    }
    finally
    {
    }
  }

  public void setConnectionTimeout(long paramLong)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
    }
    finally
    {
    }
    this.connectionTimeout = paramLong;
  }

  public void setTimeoutInterval(long paramLong)
  {
    try
    {
      if (this.shutdown)
        throw new IllegalStateException("IdleConnectionTimeoutThread has been shutdown");
    }
    finally
    {
    }
    this.timeoutInterval = paramLong;
  }

  public void shutdown()
  {
    try
    {
      this.shutdown = true;
      notifyAll();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.util.IdleConnectionTimeoutThread
 * JD-Core Version:    0.6.2
 */