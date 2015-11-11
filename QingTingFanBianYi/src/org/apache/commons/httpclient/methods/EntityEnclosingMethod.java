package org.apache.commons.httpclient.methods;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.ChunkedOutputStream;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.ProtocolException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class EntityEnclosingMethod extends ExpectContinueMethod
{
  public static final long CONTENT_LENGTH_AUTO = -2L;
  public static final long CONTENT_LENGTH_CHUNKED = -1L;
  private static final Log LOG;
  static Class class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
  private boolean chunked = false;
  private int repeatCount = 0;
  private long requestContentLength = -2L;
  private RequestEntity requestEntity;
  private InputStream requestStream = null;
  private String requestString = null;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$methods$EntityEnclosingMethod == null)
    {
      localClass = class$("org.apache.commons.httpclient.methods.EntityEnclosingMethod");
      class$org$apache$commons$httpclient$methods$EntityEnclosingMethod = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      return;
      localClass = class$org$apache$commons$httpclient$methods$EntityEnclosingMethod;
    }
  }

  public EntityEnclosingMethod()
  {
    setFollowRedirects(false);
  }

  public EntityEnclosingMethod(String paramString)
  {
    super(paramString);
    setFollowRedirects(false);
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

  protected void addContentLengthRequestHeader(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws IOException, HttpException
  {
    LOG.trace("enter EntityEnclosingMethod.addContentLengthRequestHeader(HttpState, HttpConnection)");
    long l;
    if ((getRequestHeader("content-length") == null) && (getRequestHeader("Transfer-Encoding") == null))
    {
      l = getRequestContentLength();
      if (l >= 0L)
        break label91;
      if (getEffectiveVersion().greaterEquals(HttpVersion.HTTP_1_1))
        addRequestHeader("Transfer-Encoding", "chunked");
    }
    else
    {
      return;
    }
    throw new ProtocolException(getEffectiveVersion() + " does not support chunk encoding");
    label91: addRequestHeader("Content-Length", String.valueOf(l));
  }

  protected void addRequestHeaders(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws IOException, HttpException
  {
    LOG.trace("enter EntityEnclosingMethod.addRequestHeaders(HttpState, HttpConnection)");
    super.addRequestHeaders(paramHttpState, paramHttpConnection);
    addContentLengthRequestHeader(paramHttpState, paramHttpConnection);
    if (getRequestHeader("Content-Type") == null)
    {
      paramHttpState = getRequestEntity();
      if ((paramHttpState != null) && (paramHttpState.getContentType() != null))
        setRequestHeader("Content-Type", paramHttpState.getContentType());
    }
  }

  protected void clearRequestBody()
  {
    LOG.trace("enter EntityEnclosingMethod.clearRequestBody()");
    this.requestStream = null;
    this.requestString = null;
    this.requestEntity = null;
  }

  protected byte[] generateRequestBody()
  {
    LOG.trace("enter EntityEnclosingMethod.renerateRequestBody()");
    return null;
  }

  protected RequestEntity generateRequestEntity()
  {
    Object localObject = generateRequestBody();
    if (localObject != null)
      this.requestEntity = new ByteArrayRequestEntity((byte[])localObject);
    while (true)
    {
      return this.requestEntity;
      if (this.requestStream != null)
      {
        this.requestEntity = new InputStreamRequestEntity(this.requestStream, this.requestContentLength);
        this.requestStream = null;
      }
      else if (this.requestString != null)
      {
        localObject = getRequestCharSet();
        try
        {
          this.requestEntity = new StringRequestEntity(this.requestString, null, (String)localObject);
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          if (LOG.isWarnEnabled())
            LOG.warn((String)localObject + " not supported");
          this.requestEntity = new StringRequestEntity(this.requestString);
        }
      }
    }
  }

  public boolean getFollowRedirects()
  {
    return false;
  }

  public String getRequestCharSet()
  {
    if (getRequestHeader("Content-Type") == null)
    {
      if (this.requestEntity != null)
        return getContentCharSet(new Header("Content-Type", this.requestEntity.getContentType()));
      return super.getRequestCharSet();
    }
    return super.getRequestCharSet();
  }

  protected long getRequestContentLength()
  {
    LOG.trace("enter EntityEnclosingMethod.getRequestContentLength()");
    if (!hasRequestContent());
    do
    {
      return 0L;
      if (this.chunked)
        return -1L;
      if (this.requestEntity == null)
        this.requestEntity = generateRequestEntity();
    }
    while (this.requestEntity == null);
    return this.requestEntity.getContentLength();
  }

  public RequestEntity getRequestEntity()
  {
    return generateRequestEntity();
  }

  protected boolean hasRequestContent()
  {
    LOG.trace("enter EntityEnclosingMethod.hasRequestContent()");
    return (this.requestEntity != null) || (this.requestStream != null) || (this.requestString != null);
  }

  public void recycle()
  {
    LOG.trace("enter EntityEnclosingMethod.recycle()");
    clearRequestBody();
    this.requestContentLength = -2L;
    this.repeatCount = 0;
    this.chunked = false;
    super.recycle();
  }

  public void setContentChunked(boolean paramBoolean)
  {
    this.chunked = paramBoolean;
  }

  public void setFollowRedirects(boolean paramBoolean)
  {
    if (paramBoolean == true)
      throw new IllegalArgumentException("Entity enclosing requests cannot be redirected without user intervention");
    super.setFollowRedirects(false);
  }

  public void setRequestBody(InputStream paramInputStream)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestBody(InputStream)");
    clearRequestBody();
    this.requestStream = paramInputStream;
  }

  public void setRequestBody(String paramString)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestBody(String)");
    clearRequestBody();
    this.requestString = paramString;
  }

  public void setRequestContentLength(int paramInt)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
    this.requestContentLength = paramInt;
  }

  public void setRequestContentLength(long paramLong)
  {
    LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
    this.requestContentLength = paramLong;
  }

  public void setRequestEntity(RequestEntity paramRequestEntity)
  {
    clearRequestBody();
    this.requestEntity = paramRequestEntity;
  }

  protected boolean writeRequestBody(HttpState paramHttpState, HttpConnection paramHttpConnection)
    throws IOException, HttpException
  {
    LOG.trace("enter EntityEnclosingMethod.writeRequestBody(HttpState, HttpConnection)");
    if (!hasRequestContent())
    {
      LOG.debug("Request body has not been specified");
      return true;
    }
    if (this.requestEntity == null)
      this.requestEntity = generateRequestEntity();
    if (this.requestEntity == null)
    {
      LOG.debug("Request body is empty");
      return true;
    }
    long l = getRequestContentLength();
    if ((this.repeatCount > 0) && (!this.requestEntity.isRepeatable()))
      throw new ProtocolException("Unbuffered entity enclosing request can not be repeated.");
    this.repeatCount += 1;
    paramHttpConnection = paramHttpConnection.getRequestOutputStream();
    paramHttpState = paramHttpConnection;
    if (l < 0L)
      paramHttpState = new ChunkedOutputStream(paramHttpConnection);
    this.requestEntity.writeRequest(paramHttpState);
    if ((paramHttpState instanceof ChunkedOutputStream))
      ((ChunkedOutputStream)paramHttpState).finish();
    paramHttpState.flush();
    LOG.debug("Request body sent");
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.methods.EntityEnclosingMethod
 * JD-Core Version:    0.6.2
 */