package fm.qingting.async;

import android.os.Build.VERSION;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.DataCallback;
import fm.qingting.async.callback.WritableCallback;
import fm.qingting.async.wrapper.AsyncSocketWrapper;
import java.nio.ByteBuffer;
import java.security.cert.X509Certificate;
import java.util.Set;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class AsyncSSLSocketWrapper
  implements AsyncSocketWrapper, AsyncSSLSocket
{
  static SSLContext sslContext;
  boolean clientMode;
  SSLEngine engine;
  boolean finishedHandshake = false;
  DataCallback mDataCallback;
  BufferedDataEmitter mEmitter;
  private String mHost;
  private int mPort;
  ByteBuffer mReadTmp = ByteBufferList.obtain(8192);
  BufferedDataSink mSink;
  AsyncSocket mSocket;
  boolean mUnwrapping = false;
  private boolean mWrapping = false;
  ByteBuffer mWriteTmp = ByteBufferList.obtain(8192);
  WritableCallback mWriteableCallback;
  X509Certificate[] peerCertificates;
  TrustManager[] trustManagers;

  static
  {
    try
    {
      if (Build.VERSION.SDK_INT <= 15)
        throw new Exception();
    }
    catch (Exception localException1)
    {
      try
      {
        sslContext = SSLContext.getInstance("TLS");
        X509TrustManager local2 = new X509TrustManager()
        {
          public void checkClientTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
          {
          }

          public void checkServerTrusted(X509Certificate[] paramAnonymousArrayOfX509Certificate, String paramAnonymousString)
          {
            int j = paramAnonymousArrayOfX509Certificate.length;
            int i = 0;
            while (i < j)
            {
              paramAnonymousString = paramAnonymousArrayOfX509Certificate[i];
              if ((paramAnonymousString != null) && (paramAnonymousString.getCriticalExtensionOIDs() != null))
                paramAnonymousString.getCriticalExtensionOIDs().remove("2.5.29.15");
              i += 1;
            }
          }

          public X509Certificate[] getAcceptedIssuers()
          {
            return new X509Certificate[0];
          }
        };
        sslContext.init(null, new TrustManager[] { local2 }, null);
        return;
        sslContext = SSLContext.getInstance("Default");
        return;
      }
      catch (Exception localException2)
      {
        localException1.printStackTrace();
        localException2.printStackTrace();
      }
    }
  }

  public AsyncSSLSocketWrapper(AsyncSocket paramAsyncSocket, String paramString, int paramInt)
  {
    this(paramAsyncSocket, paramString, paramInt, sslContext, null, true);
  }

  public AsyncSSLSocketWrapper(AsyncSocket paramAsyncSocket, String paramString, int paramInt, SSLContext paramSSLContext, TrustManager[] paramArrayOfTrustManager, boolean paramBoolean)
  {
    this.mSocket = paramAsyncSocket;
    this.clientMode = paramBoolean;
    this.trustManagers = paramArrayOfTrustManager;
    paramArrayOfTrustManager = paramSSLContext;
    if (paramSSLContext == null)
      paramArrayOfTrustManager = sslContext;
    if (paramString != null);
    for (this.engine = paramArrayOfTrustManager.createSSLEngine(paramString, paramInt); ; this.engine = paramArrayOfTrustManager.createSSLEngine())
    {
      this.mHost = paramString;
      this.mPort = paramInt;
      this.engine.setUseClientMode(paramBoolean);
      this.mSink = new BufferedDataSink(paramAsyncSocket);
      this.mSink.setMaxBuffer(0);
      this.mEmitter = new BufferedDataEmitter(paramAsyncSocket);
      this.mEmitter.setDataCallback(new DataCallback()
      {
        public void onDataAvailable(DataEmitter paramAnonymousDataEmitter, ByteBufferList paramAnonymousByteBufferList)
        {
          if (AsyncSSLSocketWrapper.this.mUnwrapping)
            return;
          try
          {
            AsyncSSLSocketWrapper.this.mUnwrapping = true;
            ByteBufferList localByteBufferList = new ByteBufferList();
            AsyncSSLSocketWrapper.this.mReadTmp.position(0);
            AsyncSSLSocketWrapper.this.mReadTmp.limit(AsyncSSLSocketWrapper.this.mReadTmp.capacity());
            if (paramAnonymousByteBufferList.size() > 0)
              paramAnonymousDataEmitter = paramAnonymousByteBufferList.getAll();
            while (true)
            {
              int i = paramAnonymousDataEmitter.remaining();
              SSLEngineResult localSSLEngineResult = AsyncSSLSocketWrapper.this.engine.unwrap(paramAnonymousDataEmitter, AsyncSSLSocketWrapper.this.mReadTmp);
              if (localSSLEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_OVERFLOW)
              {
                AsyncSSLSocketWrapper.this.addToPending(localByteBufferList);
                AsyncSSLSocketWrapper.this.mReadTmp = ByteBuffer.allocate(AsyncSSLSocketWrapper.this.mReadTmp.remaining() * 2);
                i = -1;
              }
              AsyncSSLSocketWrapper.this.handleResult(localSSLEngineResult);
              if (paramAnonymousDataEmitter.remaining() == i)
              {
                if (paramAnonymousDataEmitter.remaining() > 0)
                  paramAnonymousByteBufferList.add(0, paramAnonymousDataEmitter);
                AsyncSSLSocketWrapper.this.addToPending(localByteBufferList);
                Util.emitAllData(AsyncSSLSocketWrapper.this, localByteBufferList);
                return;
                paramAnonymousDataEmitter = ByteBuffer.allocate(0);
              }
            }
          }
          catch (Exception paramAnonymousDataEmitter)
          {
            paramAnonymousDataEmitter.printStackTrace();
            AsyncSSLSocketWrapper.this.report(paramAnonymousDataEmitter);
            return;
          }
          finally
          {
            AsyncSSLSocketWrapper.this.mUnwrapping = false;
          }
          throw paramAnonymousDataEmitter;
        }
      });
      return;
    }
  }

  // ERROR //
  private void handleResult(SSLEngineResult paramSSLEngineResult)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 166	javax/net/ssl/SSLEngineResult:getHandshakeStatus	()Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   4: getstatic 172	javax/net/ssl/SSLEngineResult$HandshakeStatus:NEED_TASK	Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   7: if_acmpne +15 -> 22
    //   10: aload_0
    //   11: getfield 110	fm/qingting/async/AsyncSSLSocketWrapper:engine	Ljavax/net/ssl/SSLEngine;
    //   14: invokevirtual 176	javax/net/ssl/SSLEngine:getDelegatedTask	()Ljava/lang/Runnable;
    //   17: invokeinterface 181 1 0
    //   22: aload_1
    //   23: invokevirtual 166	javax/net/ssl/SSLEngineResult:getHandshakeStatus	()Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   26: getstatic 184	javax/net/ssl/SSLEngineResult$HandshakeStatus:NEED_WRAP	Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   29: if_acmpne +11 -> 40
    //   32: aload_0
    //   33: iconst_0
    //   34: invokestatic 189	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   37: invokevirtual 193	fm/qingting/async/AsyncSSLSocketWrapper:write	(Ljava/nio/ByteBuffer;)V
    //   40: aload_1
    //   41: invokevirtual 166	javax/net/ssl/SSLEngineResult:getHandshakeStatus	()Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   44: getstatic 196	javax/net/ssl/SSLEngineResult$HandshakeStatus:NEED_UNWRAP	Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   47: if_acmpne +10 -> 57
    //   50: aload_0
    //   51: getfield 138	fm/qingting/async/AsyncSSLSocketWrapper:mEmitter	Lfm/qingting/async/BufferedDataEmitter;
    //   54: invokevirtual 199	fm/qingting/async/BufferedDataEmitter:onDataAvailable	()V
    //   57: aload_0
    //   58: getfield 94	fm/qingting/async/AsyncSSLSocketWrapper:finishedHandshake	Z
    //   61: ifne +198 -> 259
    //   64: aload_0
    //   65: getfield 110	fm/qingting/async/AsyncSSLSocketWrapper:engine	Ljavax/net/ssl/SSLEngine;
    //   68: invokevirtual 200	javax/net/ssl/SSLEngine:getHandshakeStatus	()Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   71: getstatic 203	javax/net/ssl/SSLEngineResult$HandshakeStatus:NOT_HANDSHAKING	Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   74: if_acmpeq +16 -> 90
    //   77: aload_0
    //   78: getfield 110	fm/qingting/async/AsyncSSLSocketWrapper:engine	Ljavax/net/ssl/SSLEngine;
    //   81: invokevirtual 200	javax/net/ssl/SSLEngine:getHandshakeStatus	()Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   84: getstatic 206	javax/net/ssl/SSLEngineResult$HandshakeStatus:FINISHED	Ljavax/net/ssl/SSLEngineResult$HandshakeStatus;
    //   87: if_acmpne +172 -> 259
    //   90: aload_0
    //   91: getfield 102	fm/qingting/async/AsyncSSLSocketWrapper:clientMode	Z
    //   94: ifeq +178 -> 272
    //   97: aload_0
    //   98: getfield 104	fm/qingting/async/AsyncSSLSocketWrapper:trustManagers	[Ljavax/net/ssl/TrustManager;
    //   101: astore_1
    //   102: aload_1
    //   103: ifnonnull +198 -> 301
    //   106: invokestatic 212	javax/net/ssl/TrustManagerFactory:getDefaultAlgorithm	()Ljava/lang/String;
    //   109: invokestatic 215	javax/net/ssl/TrustManagerFactory:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/TrustManagerFactory;
    //   112: astore_1
    //   113: aload_1
    //   114: aconst_null
    //   115: checkcast 217	java/security/KeyStore
    //   118: invokevirtual 220	javax/net/ssl/TrustManagerFactory:init	(Ljava/security/KeyStore;)V
    //   121: aload_1
    //   122: invokevirtual 224	javax/net/ssl/TrustManagerFactory:getTrustManagers	()[Ljavax/net/ssl/TrustManager;
    //   125: astore_1
    //   126: aload_1
    //   127: arraylength
    //   128: istore 4
    //   130: iconst_0
    //   131: istore_3
    //   132: iload_3
    //   133: iload 4
    //   135: if_icmpge +161 -> 296
    //   138: aload_1
    //   139: iload_3
    //   140: aaload
    //   141: astore_2
    //   142: aload_2
    //   143: checkcast 226	javax/net/ssl/X509TrustManager
    //   146: astore_2
    //   147: aload_0
    //   148: aload_0
    //   149: getfield 110	fm/qingting/async/AsyncSSLSocketWrapper:engine	Ljavax/net/ssl/SSLEngine;
    //   152: invokevirtual 230	javax/net/ssl/SSLEngine:getSession	()Ljavax/net/ssl/SSLSession;
    //   155: invokeinterface 236 1 0
    //   160: checkcast 237	[Ljava/security/cert/X509Certificate;
    //   163: checkcast 237	[Ljava/security/cert/X509Certificate;
    //   166: putfield 239	fm/qingting/async/AsyncSSLSocketWrapper:peerCertificates	[Ljava/security/cert/X509Certificate;
    //   169: aload_2
    //   170: aload_0
    //   171: getfield 239	fm/qingting/async/AsyncSSLSocketWrapper:peerCertificates	[Ljava/security/cert/X509Certificate;
    //   174: ldc 241
    //   176: invokeinterface 245 3 0
    //   181: aload_0
    //   182: getfield 112	fm/qingting/async/AsyncSSLSocketWrapper:mHost	Ljava/lang/String;
    //   185: ifnull +35 -> 220
    //   188: new 247	org/apache/http/conn/ssl/StrictHostnameVerifier
    //   191: dup
    //   192: invokespecial 248	org/apache/http/conn/ssl/StrictHostnameVerifier:<init>	()V
    //   195: aload_0
    //   196: getfield 112	fm/qingting/async/AsyncSSLSocketWrapper:mHost	Ljava/lang/String;
    //   199: aload_0
    //   200: getfield 239	fm/qingting/async/AsyncSSLSocketWrapper:peerCertificates	[Ljava/security/cert/X509Certificate;
    //   203: iconst_0
    //   204: aaload
    //   205: invokestatic 252	org/apache/http/conn/ssl/StrictHostnameVerifier:getCNs	(Ljava/security/cert/X509Certificate;)[Ljava/lang/String;
    //   208: aload_0
    //   209: getfield 239	fm/qingting/async/AsyncSSLSocketWrapper:peerCertificates	[Ljava/security/cert/X509Certificate;
    //   212: iconst_0
    //   213: aaload
    //   214: invokestatic 255	org/apache/http/conn/ssl/StrictHostnameVerifier:getDNSSubjectAlts	(Ljava/security/cert/X509Certificate;)[Ljava/lang/String;
    //   217: invokevirtual 259	org/apache/http/conn/ssl/StrictHostnameVerifier:verify	(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;)V
    //   220: iconst_1
    //   221: istore_3
    //   222: aload_0
    //   223: iconst_1
    //   224: putfield 94	fm/qingting/async/AsyncSSLSocketWrapper:finishedHandshake	Z
    //   227: iload_3
    //   228: ifne +44 -> 272
    //   231: new 261	fm/qingting/async/AsyncSSLException
    //   234: dup
    //   235: invokespecial 262	fm/qingting/async/AsyncSSLException:<init>	()V
    //   238: astore_1
    //   239: aload_0
    //   240: aload_1
    //   241: invokespecial 160	fm/qingting/async/AsyncSSLSocketWrapper:report	(Ljava/lang/Exception;)V
    //   244: aload_1
    //   245: invokevirtual 266	fm/qingting/async/AsyncSSLException:getIgnore	()Z
    //   248: ifne +24 -> 272
    //   251: aload_1
    //   252: athrow
    //   253: astore_1
    //   254: aload_0
    //   255: aload_1
    //   256: invokespecial 160	fm/qingting/async/AsyncSSLSocketWrapper:report	(Ljava/lang/Exception;)V
    //   259: return
    //   260: astore_2
    //   261: aload_2
    //   262: invokevirtual 76	java/lang/Exception:printStackTrace	()V
    //   265: iload_3
    //   266: iconst_1
    //   267: iadd
    //   268: istore_3
    //   269: goto -137 -> 132
    //   272: aload_0
    //   273: getfield 268	fm/qingting/async/AsyncSSLSocketWrapper:mWriteableCallback	Lfm/qingting/async/callback/WritableCallback;
    //   276: ifnull +12 -> 288
    //   279: aload_0
    //   280: getfield 268	fm/qingting/async/AsyncSSLSocketWrapper:mWriteableCallback	Lfm/qingting/async/callback/WritableCallback;
    //   283: invokeinterface 273 1 0
    //   288: aload_0
    //   289: getfield 138	fm/qingting/async/AsyncSSLSocketWrapper:mEmitter	Lfm/qingting/async/BufferedDataEmitter;
    //   292: invokevirtual 199	fm/qingting/async/BufferedDataEmitter:onDataAvailable	()V
    //   295: return
    //   296: iconst_0
    //   297: istore_3
    //   298: goto -76 -> 222
    //   301: goto -175 -> 126
    //
    // Exception table:
    //   from	to	target	type
    //   57	90	253	java/lang/Exception
    //   90	102	253	java/lang/Exception
    //   106	126	253	java/lang/Exception
    //   126	130	253	java/lang/Exception
    //   222	227	253	java/lang/Exception
    //   231	253	253	java/lang/Exception
    //   261	265	253	java/lang/Exception
    //   272	288	253	java/lang/Exception
    //   288	295	253	java/lang/Exception
    //   142	220	260	java/lang/Exception
  }

  private void report(Exception paramException)
  {
    CompletedCallback localCompletedCallback = getEndCallback();
    if (localCompletedCallback != null)
      localCompletedCallback.onCompleted(paramException);
  }

  private void writeTmp()
  {
    this.mWriteTmp.limit(this.mWriteTmp.position());
    this.mWriteTmp.position(0);
    if (this.mWriteTmp.remaining() > 0)
      this.mSink.write(this.mWriteTmp);
  }

  void addToPending(ByteBufferList paramByteBufferList)
  {
    if (this.mReadTmp.position() > 0)
    {
      this.mReadTmp.limit(this.mReadTmp.position());
      this.mReadTmp.position(0);
      paramByteBufferList.add(this.mReadTmp);
      this.mReadTmp = ByteBufferList.obtain(this.mReadTmp.capacity());
    }
  }

  boolean checkWrapResult(SSLEngineResult paramSSLEngineResult)
  {
    if (paramSSLEngineResult.getStatus() == SSLEngineResult.Status.BUFFER_OVERFLOW)
    {
      this.mWriteTmp = ByteBuffer.allocate(this.mWriteTmp.remaining() * 2);
      return false;
    }
    return true;
  }

  public void close()
  {
    this.mSocket.close();
  }

  public void end()
  {
    this.mSocket.end();
  }

  public CompletedCallback getClosedCallback()
  {
    return this.mSocket.getClosedCallback();
  }

  public DataCallback getDataCallback()
  {
    return this.mDataCallback;
  }

  public DataEmitter getDataEmitter()
  {
    return this.mSocket;
  }

  public CompletedCallback getEndCallback()
  {
    return this.mSocket.getEndCallback();
  }

  public String getHost()
  {
    return this.mHost;
  }

  public X509Certificate[] getPeerCertificates()
  {
    return this.peerCertificates;
  }

  public int getPort()
  {
    return this.mPort;
  }

  public AsyncServer getServer()
  {
    return this.mSocket.getServer();
  }

  public AsyncSocket getSocket()
  {
    return this.mSocket;
  }

  public WritableCallback getWriteableCallback()
  {
    return this.mWriteableCallback;
  }

  public boolean isChunked()
  {
    return this.mSocket.isChunked();
  }

  public boolean isOpen()
  {
    return this.mSocket.isOpen();
  }

  public boolean isPaused()
  {
    return this.mSocket.isPaused();
  }

  public void pause()
  {
    this.mSocket.pause();
  }

  public void resume()
  {
    this.mSocket.resume();
  }

  public void setClosedCallback(CompletedCallback paramCompletedCallback)
  {
    this.mSocket.setClosedCallback(paramCompletedCallback);
  }

  public void setDataCallback(DataCallback paramDataCallback)
  {
    this.mDataCallback = paramDataCallback;
  }

  public void setEndCallback(CompletedCallback paramCompletedCallback)
  {
    this.mSocket.setEndCallback(paramCompletedCallback);
  }

  public void setWriteableCallback(WritableCallback paramWritableCallback)
  {
    this.mWriteableCallback = paramWritableCallback;
  }

  public void write(ByteBufferList paramByteBufferList)
  {
    if (this.mWrapping);
    while (this.mSink.remaining() > 0)
      return;
    this.mWrapping = true;
    Object localObject2 = null;
    while (true)
    {
      if ((this.finishedHandshake) && (paramByteBufferList.remaining() == 0))
      {
        this.mWrapping = false;
        return;
      }
      int i = paramByteBufferList.remaining();
      this.mWriteTmp.position(0);
      this.mWriteTmp.limit(this.mWriteTmp.capacity());
      Object localObject1 = localObject2;
      try
      {
        ByteBuffer[] arrayOfByteBuffer = paramByteBufferList.getAllArray();
        localObject1 = localObject2;
        localObject2 = this.engine.wrap(arrayOfByteBuffer, this.mWriteTmp);
        localObject1 = localObject2;
        paramByteBufferList.addAll(arrayOfByteBuffer);
        localObject1 = localObject2;
        boolean bool = checkWrapResult((SSLEngineResult)localObject2);
        if (!bool)
          i = -1;
      }
      catch (SSLException localSSLException1)
      {
        while (true)
          try
          {
            writeTmp();
            handleResult((SSLEngineResult)localObject2);
            localObject1 = localObject2;
            if ((i != paramByteBufferList.remaining()) || ((localObject1 != null) && (((SSLEngineResult)localObject1).getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP)))
            {
              localObject2 = localObject1;
              if (this.mSink.remaining() == 0)
                break;
            }
            this.mWrapping = false;
            return;
            localSSLException1 = localSSLException1;
            report(localSSLException1);
          }
          catch (SSLException localSSLException2)
          {
            localObject1 = localSSLException1;
            Object localObject3 = localSSLException2;
          }
      }
    }
  }

  public void write(ByteBuffer paramByteBuffer)
  {
    if (this.mWrapping);
    while (this.mSink.remaining() > 0)
      return;
    this.mWrapping = true;
    Object localObject2 = null;
    while (true)
    {
      if ((this.finishedHandshake) && (paramByteBuffer.remaining() == 0))
      {
        this.mWrapping = false;
        return;
      }
      int i = paramByteBuffer.remaining();
      this.mWriteTmp.position(0);
      this.mWriteTmp.limit(this.mWriteTmp.capacity());
      try
      {
        localObject1 = this.engine.wrap(paramByteBuffer, this.mWriteTmp);
        localObject2 = localObject1;
        boolean bool = checkWrapResult((SSLEngineResult)localObject1);
        if (!bool)
        {
          i = -1;
          try
          {
            writeTmp();
            handleResult((SSLEngineResult)localObject1);
            if ((i != paramByteBuffer.remaining()) || ((localObject1 != null) && (((SSLEngineResult)localObject1).getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP)))
            {
              localObject2 = localObject1;
              if (this.mSink.remaining() == 0)
                continue;
            }
            this.mWrapping = false;
            return;
          }
          catch (SSLException localSSLException1)
          {
            while (true)
              report(localSSLException1);
          }
        }
      }
      catch (SSLException localSSLException2)
      {
        while (true)
        {
          Object localObject1 = localSSLException1;
          Object localObject3 = localSSLException2;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.AsyncSSLSocketWrapper
 * JD-Core Version:    0.6.2
 */