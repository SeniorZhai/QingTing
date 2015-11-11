package com.umeng.message.proguard;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

class ar
  implements HttpRequestRetryHandler
{
  private static final int a = 3000;
  private static HashSet<Class<?>> b = new HashSet();
  private static HashSet<Class<?>> c = new HashSet();
  private final int d;

  static
  {
    b.add(NoHttpResponseException.class);
    b.add(UnknownHostException.class);
    b.add(SocketException.class);
    c.add(InterruptedIOException.class);
    c.add(SSLHandshakeException.class);
  }

  public ar(int paramInt)
  {
    this.d = paramInt;
  }

  public boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
  {
    boolean bool2 = false;
    Boolean localBoolean = (Boolean)paramHttpContext.getAttribute("http.request_sent");
    int i;
    boolean bool1;
    if ((localBoolean != null) && (localBoolean.booleanValue()))
    {
      i = 1;
      if (paramInt <= this.d)
        break label64;
      bool1 = bool2;
    }
    while (true)
    {
      if (!bool1)
        break label145;
      SystemClock.sleep(3000L);
      return bool1;
      i = 0;
      break;
      label64: bool1 = bool2;
      if (!c.contains(paramIOException.getClass()))
        if (b.contains(paramIOException.getClass()))
        {
          bool1 = true;
        }
        else if (i == 0)
        {
          bool1 = true;
        }
        else
        {
          bool1 = bool2;
          if (!((HttpUriRequest)paramHttpContext.getAttribute("http.request")).getMethod().equals("POST"))
            bool1 = true;
        }
    }
    label145: paramIOException.printStackTrace();
    return bool1;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.ar
 * JD-Core Version:    0.6.2
 */