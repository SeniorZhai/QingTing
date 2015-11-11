package org.apache.commons.httpclient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;
import org.apache.commons.httpclient.protocol.SecureProtocolSocketFactory;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.commons.httpclient.util.ExceptionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpConnection
{
  private static final byte[] CRLF = { 13, 10 };
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$HttpConnection;
  private String hostName = null;
  private HttpConnectionManager httpConnectionManager;
  private InputStream inputStream = null;
  protected boolean isOpen = false;
  private InputStream lastResponseInputStream = null;
  private InetAddress localAddress;
  private boolean locked = false;
  private OutputStream outputStream = null;
  private HttpConnectionParams params = new HttpConnectionParams();
  private int portNumber = -1;
  private Protocol protocolInUse;
  private String proxyHostName = null;
  private int proxyPortNumber = -1;
  private Socket socket = null;
  private boolean tunnelEstablished = false;
  private boolean usingSecureSocket = false;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$HttpConnection == null)
    {
      localClass = class$("org.apache.commons.httpclient.HttpConnection");
      class$org$apache$commons$httpclient$HttpConnection = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$HttpConnection;
    }
  }

  public HttpConnection(String paramString, int paramInt)
  {
    this(null, -1, paramString, null, paramInt, Protocol.getProtocol("http"));
  }

  public HttpConnection(String paramString1, int paramInt1, String paramString2, int paramInt2)
  {
    this(paramString1, paramInt1, paramString2, null, paramInt2, Protocol.getProtocol("http"));
  }

  public HttpConnection(String paramString1, int paramInt1, String paramString2, int paramInt2, Protocol paramProtocol)
  {
    if (paramString2 == null)
      throw new IllegalArgumentException("host parameter is null");
    if (paramProtocol == null)
      throw new IllegalArgumentException("protocol is null");
    this.proxyHostName = paramString1;
    this.proxyPortNumber = paramInt1;
    this.hostName = paramString2;
    this.portNumber = paramProtocol.resolvePort(paramInt2);
    this.protocolInUse = paramProtocol;
  }

  public HttpConnection(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, Protocol paramProtocol)
  {
    this(paramString1, paramInt1, paramString2, paramInt2, paramProtocol);
  }

  public HttpConnection(String paramString, int paramInt, Protocol paramProtocol)
  {
    this(null, -1, paramString, null, paramInt, paramProtocol);
  }

  public HttpConnection(String paramString1, String paramString2, int paramInt, Protocol paramProtocol)
  {
    this(null, -1, paramString1, paramString2, paramInt, paramProtocol);
  }

  public HttpConnection(HostConfiguration paramHostConfiguration)
  {
    this(paramHostConfiguration.getProxyHost(), paramHostConfiguration.getProxyPort(), paramHostConfiguration.getHost(), paramHostConfiguration.getPort(), paramHostConfiguration.getProtocol());
    this.localAddress = paramHostConfiguration.getLocalAddress();
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

  protected void assertNotOpen()
    throws IllegalStateException
  {
    if (this.isOpen)
      throw new IllegalStateException("Connection is open");
  }

  protected void assertOpen()
    throws IllegalStateException
  {
    if (!this.isOpen)
      throw new IllegalStateException("Connection is not open");
  }

  public void close()
  {
    LOG.trace("enter HttpConnection.close()");
    closeSocketAndStreams();
  }

  public boolean closeIfStale()
    throws IOException
  {
    if ((this.isOpen) && (isStale()))
    {
      LOG.debug("Connection is stale, closing...");
      close();
      return true;
    }
    return false;
  }

  protected void closeSocketAndStreams()
  {
    LOG.trace("enter HttpConnection.closeSockedAndStreams()");
    this.isOpen = false;
    this.lastResponseInputStream = null;
    Object localObject;
    if (this.outputStream != null)
    {
      localObject = this.outputStream;
      this.outputStream = null;
    }
    try
    {
      ((OutputStream)localObject).close();
      if (this.inputStream != null)
      {
        localObject = this.inputStream;
        this.inputStream = null;
      }
    }
    catch (Exception localException2)
    {
      try
      {
        ((InputStream)localObject).close();
        if (this.socket != null)
        {
          localObject = this.socket;
          this.socket = null;
        }
      }
      catch (Exception localException2)
      {
        try
        {
          while (true)
          {
            ((Socket)localObject).close();
            this.tunnelEstablished = false;
            this.usingSecureSocket = false;
            return;
            localException1 = localException1;
            LOG.debug("Exception caught when closing output", localException1);
          }
          localException2 = localException2;
          LOG.debug("Exception caught when closing input", localException2);
        }
        catch (Exception localException3)
        {
          while (true)
            LOG.debug("Exception caught when closing socket", localException3);
        }
      }
    }
  }

  public void flushRequestOutputStream()
    throws IOException
  {
    LOG.trace("enter HttpConnection.flushRequestOutputStream()");
    assertOpen();
    this.outputStream.flush();
  }

  public String getHost()
  {
    return this.hostName;
  }

  public HttpConnectionManager getHttpConnectionManager()
  {
    return this.httpConnectionManager;
  }

  public InputStream getLastResponseInputStream()
  {
    return this.lastResponseInputStream;
  }

  public InetAddress getLocalAddress()
  {
    return this.localAddress;
  }

  public HttpConnectionParams getParams()
  {
    return this.params;
  }

  public int getPort()
  {
    if (this.portNumber < 0)
    {
      if (isSecure())
        return 443;
      return 80;
    }
    return this.portNumber;
  }

  public Protocol getProtocol()
  {
    return this.protocolInUse;
  }

  public String getProxyHost()
  {
    return this.proxyHostName;
  }

  public int getProxyPort()
  {
    return this.proxyPortNumber;
  }

  public OutputStream getRequestOutputStream()
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.getRequestOutputStream()");
    assertOpen();
    OutputStream localOutputStream = this.outputStream;
    Object localObject = localOutputStream;
    if (Wire.CONTENT_WIRE.enabled())
      localObject = new WireLogOutputStream(localOutputStream, Wire.CONTENT_WIRE);
    return localObject;
  }

  public InputStream getResponseInputStream()
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.getResponseInputStream()");
    assertOpen();
    return this.inputStream;
  }

  public int getSendBufferSize()
    throws SocketException
  {
    if (this.socket == null)
      return -1;
    return this.socket.getSendBufferSize();
  }

  public int getSoTimeout()
    throws SocketException
  {
    return this.params.getSoTimeout();
  }

  protected Socket getSocket()
  {
    return this.socket;
  }

  public String getVirtualHost()
  {
    return this.hostName;
  }

  protected boolean isLocked()
  {
    return this.locked;
  }

  public boolean isOpen()
  {
    return this.isOpen;
  }

  public boolean isProxied()
  {
    return (this.proxyHostName != null) && (this.proxyPortNumber > 0);
  }

  public boolean isResponseAvailable()
    throws IOException
  {
    boolean bool2 = false;
    LOG.trace("enter HttpConnection.isResponseAvailable()");
    boolean bool1 = bool2;
    if (this.isOpen)
    {
      bool1 = bool2;
      if (this.inputStream.available() > 0)
        bool1 = true;
    }
    return bool1;
  }

  public boolean isResponseAvailable(int paramInt)
    throws IOException
  {
    LOG.trace("enter HttpConnection.isResponseAvailable(int)");
    assertOpen();
    boolean bool = false;
    if (this.inputStream.available() > 0)
      return true;
    while (true)
    {
      try
      {
        this.socket.setSoTimeout(paramInt);
        this.inputStream.mark(1);
        if (this.inputStream.read() != -1)
        {
          this.inputStream.reset();
          LOG.debug("Input data available");
          bool = true;
          try
          {
            this.socket.setSoTimeout(this.params.getSoTimeout());
            return bool;
          }
          catch (IOException localIOException1)
          {
            LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", localIOException1);
          }
          return false;
        }
        LOG.debug("Input data not available");
        continue;
      }
      catch (InterruptedIOException localInterruptedIOException)
      {
        localInterruptedIOException = localInterruptedIOException;
        if (!ExceptionUtil.isSocketTimeoutException(localInterruptedIOException))
          throw localInterruptedIOException;
      }
      finally
      {
      }
      try
      {
        this.socket.setSoTimeout(this.params.getSoTimeout());
        throw localObject;
        if (LOG.isDebugEnabled())
          LOG.debug("Input data not available after " + paramInt + " ms");
        try
        {
          this.socket.setSoTimeout(this.params.getSoTimeout());
          return false;
        }
        catch (IOException localIOException2)
        {
          LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", localIOException2);
        }
      }
      catch (IOException localIOException3)
      {
        while (true)
          LOG.debug("An error ocurred while resetting soTimeout, we will assume that no response is available.", localIOException3);
      }
    }
  }

  public boolean isSecure()
  {
    return this.protocolInUse.isSecure();
  }

  protected boolean isStale()
    throws IOException
  {
    boolean bool1 = true;
    boolean bool4;
    boolean bool3;
    boolean bool2;
    if (this.isOpen)
    {
      bool1 = false;
      bool4 = false;
      bool3 = false;
      bool2 = bool4;
    }
    try
    {
      int i = this.inputStream.available();
      if (i <= 0);
      try
      {
        this.socket.setSoTimeout(1);
        this.inputStream.mark(1);
        i = this.inputStream.read();
        if (i == -1);
        for (bool1 = true; ; bool1 = bool3)
        {
          bool2 = bool1;
          this.socket.setSoTimeout(this.params.getSoTimeout());
          return bool1;
          this.inputStream.reset();
        }
      }
      finally
      {
        bool2 = bool4;
        this.socket.setSoTimeout(this.params.getSoTimeout());
        bool2 = bool4;
      }
    }
    catch (InterruptedIOException localInterruptedIOException)
    {
      do
        bool1 = bool2;
      while (ExceptionUtil.isSocketTimeoutException(localInterruptedIOException));
      throw localInterruptedIOException;
    }
    catch (IOException localIOException)
    {
      LOG.debug("An error occurred while reading from the socket, is appears to be stale", localIOException);
    }
    return true;
  }

  public boolean isStaleCheckingEnabled()
  {
    return this.params.isStaleCheckingEnabled();
  }

  public boolean isTransparent()
  {
    return (!isProxied()) || (this.tunnelEstablished);
  }

  public void open()
    throws IOException
  {
    LOG.trace("enter HttpConnection.open()");
    Object localObject;
    int i;
    if (this.proxyHostName == null)
    {
      localObject = this.hostName;
      if (this.proxyHostName != null)
        break label371;
      i = this.portNumber;
      label35: assertNotOpen();
      if (LOG.isDebugEnabled())
        LOG.debug("Open connection to " + (String)localObject + ":" + i);
    }
    while (true)
    {
      try
      {
        if (this.socket == null)
        {
          if ((isSecure()) && (!isProxied()))
          {
            bool = true;
            this.usingSecureSocket = bool;
            if ((!isSecure()) || (!isProxied()))
              continue;
            localProtocolSocketFactory = Protocol.getProtocol("http").getSocketFactory();
            this.socket = localProtocolSocketFactory.createSocket((String)localObject, i, this.localAddress, 0, this.params);
          }
        }
        else
        {
          this.socket.setTcpNoDelay(this.params.getTcpNoDelay());
          this.socket.setSoTimeout(this.params.getSoTimeout());
          i = this.params.getLinger();
          if (i >= 0)
          {
            localObject = this.socket;
            if (i <= 0)
              continue;
            bool = true;
            ((Socket)localObject).setSoLinger(bool, i);
          }
          i = this.params.getSendBufferSize();
          if (i >= 0)
            this.socket.setSendBufferSize(i);
          i = this.params.getReceiveBufferSize();
          if (i >= 0)
            this.socket.setReceiveBufferSize(i);
          j = this.socket.getSendBufferSize();
          if (j > 2048)
            break label409;
          i = j;
          if (j <= 0)
            break label409;
          int k = this.socket.getReceiveBufferSize();
          if (k > 2048)
            break label416;
          j = k;
          if (k <= 0)
            break label416;
          this.inputStream = new BufferedInputStream(this.socket.getInputStream(), j);
          this.outputStream = new BufferedOutputStream(this.socket.getOutputStream(), i);
          this.isOpen = true;
          return;
          localObject = this.proxyHostName;
          break;
          label371: i = this.proxyPortNumber;
          break label35;
        }
        boolean bool = false;
        continue;
        ProtocolSocketFactory localProtocolSocketFactory = this.protocolInUse.getSocketFactory();
        continue;
        bool = false;
        continue;
      }
      catch (IOException localIOException)
      {
        closeSocketAndStreams();
        throw localIOException;
      }
      label409: i = 2048;
      continue;
      label416: int j = 2048;
    }
  }

  public void print(String paramString)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.print(String)");
    write(EncodingUtil.getBytes(paramString, "ISO-8859-1"));
  }

  public void print(String paramString1, String paramString2)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.print(String)");
    write(EncodingUtil.getBytes(paramString1, paramString2));
  }

  public void printLine()
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.printLine()");
    writeLine();
  }

  public void printLine(String paramString)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.printLine(String)");
    writeLine(EncodingUtil.getBytes(paramString, "ISO-8859-1"));
  }

  public void printLine(String paramString1, String paramString2)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.printLine(String)");
    writeLine(EncodingUtil.getBytes(paramString1, paramString2));
  }

  public String readLine()
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.readLine()");
    assertOpen();
    return HttpParser.readLine(this.inputStream);
  }

  public String readLine(String paramString)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.readLine()");
    assertOpen();
    return HttpParser.readLine(this.inputStream, paramString);
  }

  public void releaseConnection()
  {
    LOG.trace("enter HttpConnection.releaseConnection()");
    if (this.locked)
    {
      LOG.debug("Connection is locked.  Call to releaseConnection() ignored.");
      return;
    }
    if (this.httpConnectionManager != null)
    {
      LOG.debug("Releasing connection back to connection manager.");
      this.httpConnectionManager.releaseConnection(this);
      return;
    }
    LOG.warn("HttpConnectionManager is null.  Connection cannot be released.");
  }

  public void setConnectionTimeout(int paramInt)
  {
    this.params.setConnectionTimeout(paramInt);
  }

  public void setHost(String paramString)
    throws IllegalStateException
  {
    if (paramString == null)
      throw new IllegalArgumentException("host parameter is null");
    assertNotOpen();
    this.hostName = paramString;
  }

  public void setHttpConnectionManager(HttpConnectionManager paramHttpConnectionManager)
  {
    this.httpConnectionManager = paramHttpConnectionManager;
  }

  public void setLastResponseInputStream(InputStream paramInputStream)
  {
    this.lastResponseInputStream = paramInputStream;
  }

  public void setLocalAddress(InetAddress paramInetAddress)
  {
    assertNotOpen();
    this.localAddress = paramInetAddress;
  }

  protected void setLocked(boolean paramBoolean)
  {
    this.locked = paramBoolean;
  }

  public void setParams(HttpConnectionParams paramHttpConnectionParams)
  {
    if (paramHttpConnectionParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpConnectionParams;
  }

  public void setPort(int paramInt)
    throws IllegalStateException
  {
    assertNotOpen();
    this.portNumber = paramInt;
  }

  public void setProtocol(Protocol paramProtocol)
  {
    assertNotOpen();
    if (paramProtocol == null)
      throw new IllegalArgumentException("protocol is null");
    this.protocolInUse = paramProtocol;
  }

  public void setProxyHost(String paramString)
    throws IllegalStateException
  {
    assertNotOpen();
    this.proxyHostName = paramString;
  }

  public void setProxyPort(int paramInt)
    throws IllegalStateException
  {
    assertNotOpen();
    this.proxyPortNumber = paramInt;
  }

  public void setSendBufferSize(int paramInt)
    throws SocketException
  {
    this.params.setSendBufferSize(paramInt);
  }

  public void setSoTimeout(int paramInt)
    throws SocketException, IllegalStateException
  {
    this.params.setSoTimeout(paramInt);
    if (this.socket != null)
      this.socket.setSoTimeout(paramInt);
  }

  public void setSocketTimeout(int paramInt)
    throws SocketException, IllegalStateException
  {
    assertOpen();
    if (this.socket != null)
      this.socket.setSoTimeout(paramInt);
  }

  public void setStaleCheckingEnabled(boolean paramBoolean)
  {
    this.params.setStaleCheckingEnabled(paramBoolean);
  }

  public void setVirtualHost(String paramString)
    throws IllegalStateException
  {
    assertNotOpen();
  }

  public void shutdownOutput()
  {
    LOG.trace("enter HttpConnection.shutdownOutput()");
    try
    {
      this.socket.getClass().getMethod("shutdownOutput", new Class[0]).invoke(this.socket, new Object[0]);
      return;
    }
    catch (Exception localException)
    {
      LOG.debug("Unexpected Exception caught", localException);
    }
  }

  public void tunnelCreated()
    throws IllegalStateException, IOException
  {
    LOG.trace("enter HttpConnection.tunnelCreated()");
    if ((!isSecure()) || (!isProxied()))
      throw new IllegalStateException("Connection must be secure and proxied to use this feature");
    if (this.usingSecureSocket)
      throw new IllegalStateException("Already using a secure socket");
    if (LOG.isDebugEnabled())
      LOG.debug("Secure tunnel to " + this.hostName + ":" + this.portNumber);
    this.socket = ((SecureProtocolSocketFactory)this.protocolInUse.getSocketFactory()).createSocket(this.socket, this.hostName, this.portNumber, true);
    int i = this.params.getSendBufferSize();
    if (i >= 0)
      this.socket.setSendBufferSize(i);
    i = this.params.getReceiveBufferSize();
    if (i >= 0)
      this.socket.setReceiveBufferSize(i);
    int j = this.socket.getSendBufferSize();
    i = j;
    if (j > 2048)
      i = 2048;
    int k = this.socket.getReceiveBufferSize();
    j = k;
    if (k > 2048)
      j = 2048;
    this.inputStream = new BufferedInputStream(this.socket.getInputStream(), j);
    this.outputStream = new BufferedOutputStream(this.socket.getOutputStream(), i);
    this.usingSecureSocket = true;
    this.tunnelEstablished = true;
  }

  public void write(byte[] paramArrayOfByte)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.write(byte[])");
    write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.write(byte[], int, int)");
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Array offset may not be negative");
    if (paramInt2 < 0)
      throw new IllegalArgumentException("Array length may not be negative");
    if (paramInt1 + paramInt2 > paramArrayOfByte.length)
      throw new IllegalArgumentException("Given offset and length exceed the array length");
    assertOpen();
    this.outputStream.write(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeLine()
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.writeLine()");
    write(CRLF);
  }

  public void writeLine(byte[] paramArrayOfByte)
    throws IOException, IllegalStateException
  {
    LOG.trace("enter HttpConnection.writeLine(byte[])");
    write(paramArrayOfByte);
    writeLine();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.HttpConnection
 * JD-Core Version:    0.6.2
 */