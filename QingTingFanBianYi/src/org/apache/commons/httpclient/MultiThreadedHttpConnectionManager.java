package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.httpclient.util.IdleConnectionHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultiThreadedHttpConnectionManager
  implements HttpConnectionManager
{
  private static WeakHashMap ALL_CONNECTION_MANAGERS;
  public static final int DEFAULT_MAX_HOST_CONNECTIONS = 2;
  public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;
  private static final Log LOG;
  private static final ReferenceQueue REFERENCE_QUEUE;
  private static ReferenceQueueThread REFERENCE_QUEUE_THREAD;
  private static final Map REFERENCE_TO_CONNECTION_SOURCE;
  static Class class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager;
  private ConnectionPool connectionPool = new ConnectionPool(null);
  private HttpConnectionManagerParams params = new HttpConnectionManagerParams();
  private boolean shutdown = false;

  static
  {
    Class localClass;
    if (class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager == null)
    {
      localClass = class$("org.apache.commons.httpclient.MultiThreadedHttpConnectionManager");
      class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager = localClass;
    }
    while (true)
    {
      LOG = LogFactory.getLog(localClass);
      REFERENCE_TO_CONNECTION_SOURCE = new HashMap();
      REFERENCE_QUEUE = new ReferenceQueue();
      ALL_CONNECTION_MANAGERS = new WeakHashMap();
      return;
      localClass = class$org$apache$commons$httpclient$MultiThreadedHttpConnectionManager;
    }
  }

  public MultiThreadedHttpConnectionManager()
  {
    synchronized (ALL_CONNECTION_MANAGERS)
    {
      ALL_CONNECTION_MANAGERS.put(this, null);
      return;
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

  private HostConfiguration configurationForConnection(HttpConnection paramHttpConnection)
  {
    HostConfiguration localHostConfiguration = new HostConfiguration();
    localHostConfiguration.setHost(paramHttpConnection.getHost(), paramHttpConnection.getPort(), paramHttpConnection.getProtocol());
    if (paramHttpConnection.getLocalAddress() != null)
      localHostConfiguration.setLocalAddress(paramHttpConnection.getLocalAddress());
    if (paramHttpConnection.getProxyHost() != null)
      localHostConfiguration.setProxy(paramHttpConnection.getProxyHost(), paramHttpConnection.getProxyPort());
    return localHostConfiguration;
  }

  // ERROR //
  private HttpConnection doGetConnection(HostConfiguration paramHostConfiguration, long paramLong)
    throws ConnectionPoolTimeoutException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 94	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:params	Lorg/apache/commons/httpclient/params/HttpConnectionManagerParams;
    //   7: aload_1
    //   8: invokevirtual 202	org/apache/commons/httpclient/params/HttpConnectionManagerParams:getMaxConnectionsPerHost	(Lorg/apache/commons/httpclient/HostConfiguration;)I
    //   11: istore 11
    //   13: aload_0
    //   14: getfield 94	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:params	Lorg/apache/commons/httpclient/params/HttpConnectionManagerParams;
    //   17: invokevirtual 205	org/apache/commons/httpclient/params/HttpConnectionManagerParams:getMaxTotalConnections	()I
    //   20: istore 12
    //   22: aload_0
    //   23: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   26: astore 7
    //   28: aload 7
    //   30: monitorenter
    //   31: new 156	org/apache/commons/httpclient/HostConfiguration
    //   34: dup
    //   35: aload_1
    //   36: invokespecial 208	org/apache/commons/httpclient/HostConfiguration:<init>	(Lorg/apache/commons/httpclient/HostConfiguration;)V
    //   39: astore 8
    //   41: aload_0
    //   42: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   45: aload 8
    //   47: invokevirtual 212	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:getHostPool	(Lorg/apache/commons/httpclient/HostConfiguration;)Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$HostConnectionPool;
    //   50: astore 9
    //   52: lload_2
    //   53: lconst_0
    //   54: lcmp
    //   55: ifle +493 -> 548
    //   58: iconst_1
    //   59: istore 10
    //   61: goto +477 -> 538
    //   64: aload 4
    //   66: ifnull +9 -> 75
    //   69: aload 7
    //   71: monitorexit
    //   72: aload 4
    //   74: areturn
    //   75: aload_0
    //   76: getfield 96	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:shutdown	Z
    //   79: ifeq +19 -> 98
    //   82: new 214	java/lang/IllegalStateException
    //   85: dup
    //   86: ldc 216
    //   88: invokespecial 217	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   91: athrow
    //   92: astore_1
    //   93: aload 7
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    //   98: aload 9
    //   100: getfield 221	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$HostConnectionPool:freeConnections	Ljava/util/LinkedList;
    //   103: invokevirtual 226	java/util/LinkedList:size	()I
    //   106: ifle +17 -> 123
    //   109: aload_0
    //   110: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   113: aload 8
    //   115: invokevirtual 230	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:getFreeConnection	(Lorg/apache/commons/httpclient/HostConfiguration;)Lorg/apache/commons/httpclient/HttpConnection;
    //   118: astore 4
    //   120: goto -56 -> 64
    //   123: aload 9
    //   125: getfield 233	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$HostConnectionPool:numConnections	I
    //   128: iload 11
    //   130: if_icmpge +29 -> 159
    //   133: aload_0
    //   134: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   137: invokestatic 237	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:access$200	(Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;)I
    //   140: iload 12
    //   142: if_icmpge +17 -> 159
    //   145: aload_0
    //   146: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   149: aload 8
    //   151: invokevirtual 240	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:createConnection	(Lorg/apache/commons/httpclient/HostConfiguration;)Lorg/apache/commons/httpclient/HttpConnection;
    //   154: astore 4
    //   156: goto -92 -> 64
    //   159: aload 9
    //   161: getfield 233	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$HostConnectionPool:numConnections	I
    //   164: iload 11
    //   166: if_icmpge +37 -> 203
    //   169: aload_0
    //   170: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   173: invokestatic 244	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:access$300	(Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;)Ljava/util/LinkedList;
    //   176: invokevirtual 226	java/util/LinkedList:size	()I
    //   179: ifle +24 -> 203
    //   182: aload_0
    //   183: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   186: invokevirtual 247	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:deleteLeastUsedConnection	()V
    //   189: aload_0
    //   190: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   193: aload 8
    //   195: invokevirtual 240	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:createConnection	(Lorg/apache/commons/httpclient/HostConfiguration;)Lorg/apache/commons/httpclient/HttpConnection;
    //   198: astore 4
    //   200: goto -136 -> 64
    //   203: iload 10
    //   205: ifeq +58 -> 263
    //   208: lload 15
    //   210: lconst_0
    //   211: lcmp
    //   212: ifgt +51 -> 263
    //   215: new 196	org/apache/commons/httpclient/ConnectionPoolTimeoutException
    //   218: dup
    //   219: ldc 249
    //   221: invokespecial 250	org/apache/commons/httpclient/ConnectionPoolTimeoutException:<init>	(Ljava/lang/String;)V
    //   224: athrow
    //   225: astore 5
    //   227: aload_1
    //   228: astore 5
    //   230: lload 15
    //   232: lstore 13
    //   234: iload 10
    //   236: ifeq +17 -> 253
    //   239: invokestatic 256	java/lang/System:currentTimeMillis	()J
    //   242: lstore 13
    //   244: lload 15
    //   246: lload 13
    //   248: lload_2
    //   249: lsub
    //   250: lsub
    //   251: lstore 13
    //   253: aload 5
    //   255: astore_1
    //   256: lload 13
    //   258: lstore 15
    //   260: goto -196 -> 64
    //   263: getstatic 70	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:LOG	Lorg/apache/commons/logging/Log;
    //   266: invokeinterface 262 1 0
    //   271: ifeq +32 -> 303
    //   274: getstatic 70	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:LOG	Lorg/apache/commons/logging/Log;
    //   277: new 264	java/lang/StringBuffer
    //   280: dup
    //   281: invokespecial 265	java/lang/StringBuffer:<init>	()V
    //   284: ldc_w 267
    //   287: invokevirtual 271	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   290: aload 8
    //   292: invokevirtual 274	java/lang/StringBuffer:append	(Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   295: invokevirtual 277	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   298: invokeinterface 281 2 0
    //   303: aload_1
    //   304: ifnonnull +231 -> 535
    //   307: new 28	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$WaitingThread
    //   310: dup
    //   311: aconst_null
    //   312: invokespecial 284	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$WaitingThread:<init>	(Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$1;)V
    //   315: astore 6
    //   317: lload_2
    //   318: lstore 17
    //   320: lload_2
    //   321: lstore 19
    //   323: aload 6
    //   325: astore 5
    //   327: aload 6
    //   329: aload 9
    //   331: putfield 288	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$WaitingThread:hostConnectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$HostConnectionPool;
    //   334: lload_2
    //   335: lstore 17
    //   337: lload_2
    //   338: lstore 19
    //   340: aload 6
    //   342: astore 5
    //   344: aload 6
    //   346: invokestatic 294	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   349: putfield 298	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$WaitingThread:thread	Ljava/lang/Thread;
    //   352: aload 6
    //   354: astore_1
    //   355: lload_2
    //   356: lstore 13
    //   358: iload 10
    //   360: ifeq +17 -> 377
    //   363: lload_2
    //   364: lstore 17
    //   366: lload_2
    //   367: lstore 19
    //   369: aload_1
    //   370: astore 5
    //   372: invokestatic 256	java/lang/System:currentTimeMillis	()J
    //   375: lstore 13
    //   377: lload 13
    //   379: lstore 17
    //   381: lload 13
    //   383: lstore 19
    //   385: aload_1
    //   386: astore 5
    //   388: aload 9
    //   390: getfield 301	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$HostConnectionPool:waitingThreads	Ljava/util/LinkedList;
    //   393: aload_1
    //   394: invokevirtual 304	java/util/LinkedList:addLast	(Ljava/lang/Object;)V
    //   397: lload 13
    //   399: lstore 17
    //   401: lload 13
    //   403: lstore 19
    //   405: aload_1
    //   406: astore 5
    //   408: aload_0
    //   409: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   412: invokestatic 307	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:access$500	(Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;)Ljava/util/LinkedList;
    //   415: aload_1
    //   416: invokevirtual 304	java/util/LinkedList:addLast	(Ljava/lang/Object;)V
    //   419: lload 13
    //   421: lstore 17
    //   423: lload 13
    //   425: lstore 19
    //   427: aload_1
    //   428: astore 5
    //   430: aload_0
    //   431: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   434: lload 15
    //   436: invokevirtual 311	java/lang/Object:wait	(J)V
    //   439: lload 13
    //   441: lstore 17
    //   443: lload 13
    //   445: lstore 19
    //   447: aload_1
    //   448: astore 5
    //   450: aload 9
    //   452: getfield 301	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$HostConnectionPool:waitingThreads	Ljava/util/LinkedList;
    //   455: aload_1
    //   456: invokevirtual 315	java/util/LinkedList:remove	(Ljava/lang/Object;)Z
    //   459: pop
    //   460: lload 13
    //   462: lstore 17
    //   464: lload 13
    //   466: lstore 19
    //   468: aload_1
    //   469: astore 5
    //   471: aload_0
    //   472: getfield 101	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager:connectionPool	Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;
    //   475: invokestatic 307	org/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool:access$500	(Lorg/apache/commons/httpclient/MultiThreadedHttpConnectionManager$ConnectionPool;)Ljava/util/LinkedList;
    //   478: aload_1
    //   479: invokevirtual 315	java/util/LinkedList:remove	(Ljava/lang/Object;)Z
    //   482: pop
    //   483: lload 15
    //   485: lstore 17
    //   487: iload 10
    //   489: ifeq +65 -> 554
    //   492: lload 15
    //   494: invokestatic 256	java/lang/System:currentTimeMillis	()J
    //   497: lload 13
    //   499: lsub
    //   500: lsub
    //   501: lstore 17
    //   503: goto +51 -> 554
    //   506: iload 10
    //   508: ifeq +7 -> 515
    //   511: invokestatic 256	java/lang/System:currentTimeMillis	()J
    //   514: pop2
    //   515: aload_1
    //   516: athrow
    //   517: astore_1
    //   518: goto -425 -> 93
    //   521: astore_1
    //   522: lload 17
    //   524: lstore_2
    //   525: goto -19 -> 506
    //   528: astore_1
    //   529: lload 19
    //   531: lstore_2
    //   532: goto -302 -> 230
    //   535: goto -180 -> 355
    //   538: lload_2
    //   539: lstore 15
    //   541: lconst_0
    //   542: lstore_2
    //   543: aconst_null
    //   544: astore_1
    //   545: goto -481 -> 64
    //   548: iconst_0
    //   549: istore 10
    //   551: goto -13 -> 538
    //   554: lload 13
    //   556: lstore_2
    //   557: lload 17
    //   559: lstore 15
    //   561: goto -497 -> 64
    //   564: astore_1
    //   565: goto -59 -> 506
    //
    // Exception table:
    //   from	to	target	type
    //   41	52	92	finally
    //   69	72	92	finally
    //   75	92	92	finally
    //   98	120	92	finally
    //   123	156	92	finally
    //   159	200	92	finally
    //   239	244	92	finally
    //   492	503	92	finally
    //   511	515	92	finally
    //   515	517	92	finally
    //   215	225	225	java/lang/InterruptedException
    //   263	303	225	java/lang/InterruptedException
    //   307	317	225	java/lang/InterruptedException
    //   31	41	517	finally
    //   327	334	521	finally
    //   344	352	521	finally
    //   372	377	521	finally
    //   388	397	521	finally
    //   408	419	521	finally
    //   430	439	521	finally
    //   450	460	521	finally
    //   471	483	521	finally
    //   327	334	528	java/lang/InterruptedException
    //   344	352	528	java/lang/InterruptedException
    //   372	377	528	java/lang/InterruptedException
    //   388	397	528	java/lang/InterruptedException
    //   408	419	528	java/lang/InterruptedException
    //   430	439	528	java/lang/InterruptedException
    //   450	460	528	java/lang/InterruptedException
    //   471	483	528	java/lang/InterruptedException
    //   215	225	564	finally
    //   263	303	564	finally
    //   307	317	564	finally
  }

  private static void removeReferenceToConnection(HttpConnectionWithReference paramHttpConnectionWithReference)
  {
    synchronized (REFERENCE_TO_CONNECTION_SOURCE)
    {
      REFERENCE_TO_CONNECTION_SOURCE.remove(paramHttpConnectionWithReference.reference);
      return;
    }
  }

  public static void shutdownAll()
  {
    synchronized (REFERENCE_TO_CONNECTION_SOURCE)
    {
      synchronized (ALL_CONNECTION_MANAGERS)
      {
        Iterator localIterator = ALL_CONNECTION_MANAGERS.keySet().iterator();
        if (!localIterator.hasNext())
        {
          if (REFERENCE_QUEUE_THREAD != null)
          {
            REFERENCE_QUEUE_THREAD.shutdown();
            REFERENCE_QUEUE_THREAD = null;
          }
          REFERENCE_TO_CONNECTION_SOURCE.clear();
          return;
        }
        MultiThreadedHttpConnectionManager localMultiThreadedHttpConnectionManager = (MultiThreadedHttpConnectionManager)localIterator.next();
        localIterator.remove();
        localMultiThreadedHttpConnectionManager.shutdown();
      }
    }
  }

  private static void shutdownCheckedOutConnections(ConnectionPool paramConnectionPool)
  {
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      synchronized (REFERENCE_TO_CONNECTION_SOURCE)
      {
        Iterator localIterator = REFERENCE_TO_CONNECTION_SOURCE.keySet().iterator();
        if (!localIterator.hasNext())
        {
          paramConnectionPool = localArrayList.iterator();
          if (paramConnectionPool.hasNext());
        }
        else
        {
          Object localObject2 = (Reference)localIterator.next();
          if (((ConnectionSource)REFERENCE_TO_CONNECTION_SOURCE.get(localObject2)).connectionPool != paramConnectionPool)
            continue;
          localIterator.remove();
          localObject2 = (HttpConnection)((Reference)localObject2).get();
          if (localObject2 == null)
            continue;
          localArrayList.add(localObject2);
        }
      }
      ??? = (HttpConnection)paramConnectionPool.next();
      ((HttpConnection)???).close();
      ((HttpConnection)???).setHttpConnectionManager(null);
      ((HttpConnection)???).releaseConnection();
    }
  }

  private static void storeReferenceToConnection(HttpConnectionWithReference paramHttpConnectionWithReference, HostConfiguration arg1, ConnectionPool paramConnectionPool)
  {
    ConnectionSource localConnectionSource = new ConnectionSource(null);
    localConnectionSource.connectionPool = paramConnectionPool;
    localConnectionSource.hostConfiguration = ???;
    synchronized (REFERENCE_TO_CONNECTION_SOURCE)
    {
      if (REFERENCE_QUEUE_THREAD == null)
      {
        REFERENCE_QUEUE_THREAD = new ReferenceQueueThread();
        REFERENCE_QUEUE_THREAD.start();
      }
      REFERENCE_TO_CONNECTION_SOURCE.put(paramHttpConnectionWithReference.reference, localConnectionSource);
      return;
    }
  }

  public void closeIdleConnections(long paramLong)
  {
    this.connectionPool.closeIdleConnections(paramLong);
  }

  public void deleteClosedConnections()
  {
    this.connectionPool.deleteClosedConnections();
  }

  public HttpConnection getConnection(HostConfiguration paramHostConfiguration)
  {
    while (true)
      try
      {
        HttpConnection localHttpConnection = getConnectionWithTimeout(paramHostConfiguration, 0L);
        return localHttpConnection;
      }
      catch (ConnectionPoolTimeoutException localConnectionPoolTimeoutException)
      {
        LOG.debug("Unexpected exception while waiting for connection", localConnectionPoolTimeoutException);
      }
  }

  public HttpConnection getConnection(HostConfiguration paramHostConfiguration, long paramLong)
    throws HttpException
  {
    LOG.trace("enter HttpConnectionManager.getConnection(HostConfiguration, long)");
    try
    {
      paramHostConfiguration = getConnectionWithTimeout(paramHostConfiguration, paramLong);
      return paramHostConfiguration;
    }
    catch (ConnectionPoolTimeoutException paramHostConfiguration)
    {
    }
    throw new HttpException(paramHostConfiguration.getMessage());
  }

  public HttpConnection getConnectionWithTimeout(HostConfiguration paramHostConfiguration, long paramLong)
    throws ConnectionPoolTimeoutException
  {
    LOG.trace("enter HttpConnectionManager.getConnectionWithTimeout(HostConfiguration, long)");
    if (paramHostConfiguration == null)
      throw new IllegalArgumentException("hostConfiguration is null");
    if (LOG.isDebugEnabled())
      LOG.debug("HttpConnectionManager.getConnection:  config = " + paramHostConfiguration + ", timeout = " + paramLong);
    return new HttpConnectionAdapter(doGetConnection(paramHostConfiguration, paramLong));
  }

  public int getConnectionsInPool()
  {
    synchronized (this.connectionPool)
    {
      int i = this.connectionPool.numConnections;
      return i;
    }
  }

  public int getConnectionsInPool(HostConfiguration paramHostConfiguration)
  {
    synchronized (this.connectionPool)
    {
      int i = this.connectionPool.getHostPool(paramHostConfiguration).numConnections;
      return i;
    }
  }

  public int getConnectionsInUse()
  {
    return getConnectionsInPool();
  }

  public int getConnectionsInUse(HostConfiguration paramHostConfiguration)
  {
    return getConnectionsInPool(paramHostConfiguration);
  }

  public int getMaxConnectionsPerHost()
  {
    return this.params.getDefaultMaxConnectionsPerHost();
  }

  public int getMaxTotalConnections()
  {
    return this.params.getMaxTotalConnections();
  }

  public HttpConnectionManagerParams getParams()
  {
    return this.params;
  }

  public boolean isConnectionStaleCheckingEnabled()
  {
    return this.params.isStaleCheckingEnabled();
  }

  public void releaseConnection(HttpConnection paramHttpConnection)
  {
    LOG.trace("enter HttpConnectionManager.releaseConnection(HttpConnection)");
    HttpConnection localHttpConnection = paramHttpConnection;
    if ((paramHttpConnection instanceof HttpConnectionAdapter))
      localHttpConnection = ((HttpConnectionAdapter)paramHttpConnection).getWrappedConnection();
    SimpleHttpConnectionManager.finishLastResponse(localHttpConnection);
    this.connectionPool.freeConnection(localHttpConnection);
  }

  public void setConnectionStaleCheckingEnabled(boolean paramBoolean)
  {
    this.params.setStaleCheckingEnabled(paramBoolean);
  }

  public void setMaxConnectionsPerHost(int paramInt)
  {
    this.params.setDefaultMaxConnectionsPerHost(paramInt);
  }

  public void setMaxTotalConnections(int paramInt)
  {
    this.params.setMaxTotalConnections(paramInt);
  }

  public void setParams(HttpConnectionManagerParams paramHttpConnectionManagerParams)
  {
    if (paramHttpConnectionManagerParams == null)
      throw new IllegalArgumentException("Parameters may not be null");
    this.params = paramHttpConnectionManagerParams;
  }

  public void shutdown()
  {
    try
    {
      synchronized (this.connectionPool)
      {
        if (!this.shutdown)
        {
          this.shutdown = true;
          this.connectionPool.shutdown();
        }
        return;
      }
    }
    finally
    {
    }
  }

  private class ConnectionPool
  {
    private LinkedList freeConnections = new LinkedList();
    private IdleConnectionHandler idleConnectionHandler = new IdleConnectionHandler();
    private final Map mapHosts = new HashMap();
    private int numConnections = 0;
    private LinkedList waitingThreads = new LinkedList();

    private ConnectionPool()
    {
    }

    ConnectionPool(MultiThreadedHttpConnectionManager.1 arg2)
    {
      this();
    }

    private void deleteConnection(HttpConnection paramHttpConnection)
    {
      try
      {
        Object localObject = MultiThreadedHttpConnectionManager.this.configurationForConnection(paramHttpConnection);
        if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
          MultiThreadedHttpConnectionManager.LOG.debug("Reclaiming connection, hostConfig=" + localObject);
        paramHttpConnection.close();
        localObject = getHostPool((HostConfiguration)localObject);
        ((MultiThreadedHttpConnectionManager.HostConnectionPool)localObject).freeConnections.remove(paramHttpConnection);
        ((MultiThreadedHttpConnectionManager.HostConnectionPool)localObject).numConnections -= 1;
        this.numConnections -= 1;
        this.idleConnectionHandler.remove(paramHttpConnection);
        return;
      }
      finally
      {
      }
      throw paramHttpConnection;
    }

    public void closeIdleConnections(long paramLong)
    {
      try
      {
        this.idleConnectionHandler.closeIdleConnections(paramLong);
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public HttpConnection createConnection(HostConfiguration paramHostConfiguration)
    {
      try
      {
        MultiThreadedHttpConnectionManager.HostConnectionPool localHostConnectionPool = getHostPool(paramHostConfiguration);
        if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
          MultiThreadedHttpConnectionManager.LOG.debug("Allocating new connection, hostConfig=" + paramHostConfiguration);
        MultiThreadedHttpConnectionManager.HttpConnectionWithReference localHttpConnectionWithReference = new MultiThreadedHttpConnectionManager.HttpConnectionWithReference(paramHostConfiguration);
        localHttpConnectionWithReference.getParams().setDefaults(MultiThreadedHttpConnectionManager.this.params);
        localHttpConnectionWithReference.setHttpConnectionManager(MultiThreadedHttpConnectionManager.this);
        this.numConnections += 1;
        localHostConnectionPool.numConnections += 1;
        MultiThreadedHttpConnectionManager.storeReferenceToConnection(localHttpConnectionWithReference, paramHostConfiguration, this);
        return localHttpConnectionWithReference;
      }
      finally
      {
      }
      throw paramHostConfiguration;
    }

    public void deleteClosedConnections()
    {
      try
      {
        Iterator localIterator = this.freeConnections.iterator();
        while (true)
        {
          boolean bool = localIterator.hasNext();
          if (!bool)
            return;
          HttpConnection localHttpConnection = (HttpConnection)localIterator.next();
          if (!localHttpConnection.isOpen())
          {
            localIterator.remove();
            deleteConnection(localHttpConnection);
          }
        }
      }
      finally
      {
      }
    }

    public void deleteLeastUsedConnection()
    {
      try
      {
        HttpConnection localHttpConnection = (HttpConnection)this.freeConnections.removeFirst();
        if (localHttpConnection != null)
          deleteConnection(localHttpConnection);
        while (true)
        {
          return;
          if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
            MultiThreadedHttpConnectionManager.LOG.debug("Attempted to reclaim an unused connection but there were none.");
        }
      }
      finally
      {
      }
    }

    public void freeConnection(HttpConnection paramHttpConnection)
    {
      HostConfiguration localHostConfiguration = MultiThreadedHttpConnectionManager.this.configurationForConnection(paramHttpConnection);
      if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
        MultiThreadedHttpConnectionManager.LOG.debug("Freeing connection, hostConfig=" + localHostConfiguration);
      try
      {
        if (MultiThreadedHttpConnectionManager.this.shutdown)
        {
          paramHttpConnection.close();
          return;
        }
        MultiThreadedHttpConnectionManager.HostConnectionPool localHostConnectionPool = getHostPool(localHostConfiguration);
        localHostConnectionPool.freeConnections.add(paramHttpConnection);
        if (localHostConnectionPool.numConnections == 0)
        {
          MultiThreadedHttpConnectionManager.LOG.error("Host connection pool not found, hostConfig=" + localHostConfiguration);
          localHostConnectionPool.numConnections = 1;
        }
        this.freeConnections.add(paramHttpConnection);
        MultiThreadedHttpConnectionManager.removeReferenceToConnection((MultiThreadedHttpConnectionManager.HttpConnectionWithReference)paramHttpConnection);
        if (this.numConnections == 0)
        {
          MultiThreadedHttpConnectionManager.LOG.error("Host connection pool not found, hostConfig=" + localHostConfiguration);
          this.numConnections = 1;
        }
        this.idleConnectionHandler.add(paramHttpConnection);
        notifyWaitingThread(localHostConnectionPool);
        return;
      }
      finally
      {
      }
      throw paramHttpConnection;
    }

    public HttpConnection getFreeConnection(HostConfiguration paramHostConfiguration)
    {
      Object localObject2 = null;
      try
      {
        Object localObject1 = getHostPool(paramHostConfiguration);
        if (((MultiThreadedHttpConnectionManager.HostConnectionPool)localObject1).freeConnections.size() > 0)
        {
          localObject1 = (MultiThreadedHttpConnectionManager.HttpConnectionWithReference)((MultiThreadedHttpConnectionManager.HostConnectionPool)localObject1).freeConnections.removeFirst();
          this.freeConnections.remove(localObject1);
          MultiThreadedHttpConnectionManager.storeReferenceToConnection((MultiThreadedHttpConnectionManager.HttpConnectionWithReference)localObject1, paramHostConfiguration, this);
          if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
            MultiThreadedHttpConnectionManager.LOG.debug("Getting free connection, hostConfig=" + paramHostConfiguration);
          this.idleConnectionHandler.remove((HttpConnection)localObject1);
        }
        while (true)
        {
          return localObject1;
          localObject1 = localObject2;
          if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
          {
            MultiThreadedHttpConnectionManager.LOG.debug("There were no free connections to get, hostConfig=" + paramHostConfiguration);
            localObject1 = localObject2;
          }
        }
      }
      finally
      {
      }
      throw paramHostConfiguration;
    }

    public MultiThreadedHttpConnectionManager.HostConnectionPool getHostPool(HostConfiguration paramHostConfiguration)
    {
      try
      {
        MultiThreadedHttpConnectionManager.LOG.trace("enter HttpConnectionManager.ConnectionPool.getHostPool(HostConfiguration)");
        MultiThreadedHttpConnectionManager.HostConnectionPool localHostConnectionPool2 = (MultiThreadedHttpConnectionManager.HostConnectionPool)this.mapHosts.get(paramHostConfiguration);
        MultiThreadedHttpConnectionManager.HostConnectionPool localHostConnectionPool1 = localHostConnectionPool2;
        if (localHostConnectionPool2 == null)
        {
          localHostConnectionPool1 = new MultiThreadedHttpConnectionManager.HostConnectionPool(null);
          localHostConnectionPool1.hostConfiguration = paramHostConfiguration;
          this.mapHosts.put(paramHostConfiguration, localHostConnectionPool1);
        }
        return localHostConnectionPool1;
      }
      finally
      {
      }
      throw paramHostConfiguration;
    }

    public void handleLostConnection(HostConfiguration paramHostConfiguration)
    {
      try
      {
        MultiThreadedHttpConnectionManager.HostConnectionPool localHostConnectionPool = getHostPool(paramHostConfiguration);
        localHostConnectionPool.numConnections -= 1;
        this.numConnections -= 1;
        notifyWaitingThread(paramHostConfiguration);
        return;
      }
      finally
      {
        paramHostConfiguration = finally;
      }
      throw paramHostConfiguration;
    }

    public void notifyWaitingThread(HostConfiguration paramHostConfiguration)
    {
      try
      {
        notifyWaitingThread(getHostPool(paramHostConfiguration));
        return;
      }
      finally
      {
        paramHostConfiguration = finally;
      }
      throw paramHostConfiguration;
    }

    public void notifyWaitingThread(MultiThreadedHttpConnectionManager.HostConnectionPool paramHostConnectionPool)
    {
      Object localObject = null;
      while (true)
      {
        try
        {
          if (paramHostConnectionPool.waitingThreads.size() > 0)
          {
            if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
              MultiThreadedHttpConnectionManager.LOG.debug("Notifying thread waiting on host pool, hostConfig=" + paramHostConnectionPool.hostConfiguration);
            paramHostConnectionPool = (MultiThreadedHttpConnectionManager.WaitingThread)paramHostConnectionPool.waitingThreads.removeFirst();
            this.waitingThreads.remove(paramHostConnectionPool);
            if (paramHostConnectionPool != null)
              paramHostConnectionPool.thread.interrupt();
            return;
          }
          if (this.waitingThreads.size() > 0)
          {
            if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
              MultiThreadedHttpConnectionManager.LOG.debug("No-one waiting on host pool, notifying next waiting thread.");
            paramHostConnectionPool = (MultiThreadedHttpConnectionManager.WaitingThread)this.waitingThreads.removeFirst();
            paramHostConnectionPool.hostConnectionPool.waitingThreads.remove(paramHostConnectionPool);
            continue;
          }
        }
        finally
        {
        }
        paramHostConnectionPool = localObject;
        if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
        {
          MultiThreadedHttpConnectionManager.LOG.debug("Notifying no-one, there are no waiting threads");
          paramHostConnectionPool = localObject;
        }
      }
    }

    public void shutdown()
    {
      while (true)
      {
        try
        {
          Iterator localIterator = this.freeConnections.iterator();
          if (!localIterator.hasNext())
          {
            MultiThreadedHttpConnectionManager.shutdownCheckedOutConnections(this);
            localIterator = this.waitingThreads.iterator();
            if (!localIterator.hasNext())
            {
              this.mapHosts.clear();
              this.idleConnectionHandler.removeAll();
            }
          }
          else
          {
            localObject2 = (HttpConnection)localIterator.next();
            localIterator.remove();
            ((HttpConnection)localObject2).close();
            continue;
          }
        }
        finally
        {
        }
        Object localObject2 = (MultiThreadedHttpConnectionManager.WaitingThread)localObject1.next();
        localObject1.remove();
        ((MultiThreadedHttpConnectionManager.WaitingThread)localObject2).thread.interrupt();
      }
    }
  }

  private static class ConnectionSource
  {
    public MultiThreadedHttpConnectionManager.ConnectionPool connectionPool;
    public HostConfiguration hostConfiguration;

    private ConnectionSource()
    {
    }

    ConnectionSource(MultiThreadedHttpConnectionManager.1 param1)
    {
      this();
    }
  }

  private static class HostConnectionPool
  {
    public LinkedList freeConnections = new LinkedList();
    public HostConfiguration hostConfiguration;
    public int numConnections = 0;
    public LinkedList waitingThreads = new LinkedList();

    private HostConnectionPool()
    {
    }

    HostConnectionPool(MultiThreadedHttpConnectionManager.1 param1)
    {
      this();
    }
  }

  private static class HttpConnectionAdapter extends HttpConnection
  {
    private HttpConnection wrappedConnection;

    public HttpConnectionAdapter(HttpConnection paramHttpConnection)
    {
      super(paramHttpConnection.getPort(), paramHttpConnection.getProtocol());
      this.wrappedConnection = paramHttpConnection;
    }

    public void close()
    {
      if (hasConnection())
        this.wrappedConnection.close();
    }

    public boolean closeIfStale()
      throws IOException
    {
      if (hasConnection())
        return this.wrappedConnection.closeIfStale();
      return false;
    }

    public void flushRequestOutputStream()
      throws IOException
    {
      if (hasConnection())
      {
        this.wrappedConnection.flushRequestOutputStream();
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public String getHost()
    {
      if (hasConnection())
        return this.wrappedConnection.getHost();
      return null;
    }

    public HttpConnectionManager getHttpConnectionManager()
    {
      if (hasConnection())
        return this.wrappedConnection.getHttpConnectionManager();
      return null;
    }

    public InputStream getLastResponseInputStream()
    {
      if (hasConnection())
        return this.wrappedConnection.getLastResponseInputStream();
      return null;
    }

    public InetAddress getLocalAddress()
    {
      if (hasConnection())
        return this.wrappedConnection.getLocalAddress();
      return null;
    }

    public HttpConnectionParams getParams()
    {
      if (hasConnection())
        return this.wrappedConnection.getParams();
      throw new IllegalStateException("Connection has been released");
    }

    public int getPort()
    {
      if (hasConnection())
        return this.wrappedConnection.getPort();
      return -1;
    }

    public Protocol getProtocol()
    {
      if (hasConnection())
        return this.wrappedConnection.getProtocol();
      return null;
    }

    public String getProxyHost()
    {
      if (hasConnection())
        return this.wrappedConnection.getProxyHost();
      return null;
    }

    public int getProxyPort()
    {
      if (hasConnection())
        return this.wrappedConnection.getProxyPort();
      return -1;
    }

    public OutputStream getRequestOutputStream()
      throws IOException, IllegalStateException
    {
      if (hasConnection())
        return this.wrappedConnection.getRequestOutputStream();
      return null;
    }

    public InputStream getResponseInputStream()
      throws IOException, IllegalStateException
    {
      if (hasConnection())
        return this.wrappedConnection.getResponseInputStream();
      return null;
    }

    public int getSendBufferSize()
      throws SocketException
    {
      if (hasConnection())
        return this.wrappedConnection.getSendBufferSize();
      throw new IllegalStateException("Connection has been released");
    }

    public int getSoTimeout()
      throws SocketException
    {
      if (hasConnection())
        return this.wrappedConnection.getSoTimeout();
      throw new IllegalStateException("Connection has been released");
    }

    public String getVirtualHost()
    {
      if (hasConnection())
        return this.wrappedConnection.getVirtualHost();
      throw new IllegalStateException("Connection has been released");
    }

    HttpConnection getWrappedConnection()
    {
      return this.wrappedConnection;
    }

    protected boolean hasConnection()
    {
      return this.wrappedConnection != null;
    }

    public boolean isOpen()
    {
      if (hasConnection())
        return this.wrappedConnection.isOpen();
      return false;
    }

    public boolean isProxied()
    {
      if (hasConnection())
        return this.wrappedConnection.isProxied();
      return false;
    }

    public boolean isResponseAvailable()
      throws IOException
    {
      if (hasConnection())
        return this.wrappedConnection.isResponseAvailable();
      return false;
    }

    public boolean isResponseAvailable(int paramInt)
      throws IOException
    {
      if (hasConnection())
        return this.wrappedConnection.isResponseAvailable(paramInt);
      return false;
    }

    public boolean isSecure()
    {
      if (hasConnection())
        return this.wrappedConnection.isSecure();
      return false;
    }

    public boolean isStaleCheckingEnabled()
    {
      if (hasConnection())
        return this.wrappedConnection.isStaleCheckingEnabled();
      return false;
    }

    public boolean isTransparent()
    {
      if (hasConnection())
        return this.wrappedConnection.isTransparent();
      return false;
    }

    public void open()
      throws IOException
    {
      if (hasConnection())
      {
        this.wrappedConnection.open();
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void print(String paramString)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.print(paramString);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void print(String paramString1, String paramString2)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.print(paramString1, paramString2);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void printLine()
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.printLine();
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void printLine(String paramString)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.printLine(paramString);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void printLine(String paramString1, String paramString2)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.printLine(paramString1, paramString2);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public String readLine()
      throws IOException, IllegalStateException
    {
      if (hasConnection())
        return this.wrappedConnection.readLine();
      throw new IllegalStateException("Connection has been released");
    }

    public String readLine(String paramString)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
        return this.wrappedConnection.readLine(paramString);
      throw new IllegalStateException("Connection has been released");
    }

    public void releaseConnection()
    {
      if ((!isLocked()) && (hasConnection()))
      {
        HttpConnection localHttpConnection = this.wrappedConnection;
        this.wrappedConnection = null;
        localHttpConnection.releaseConnection();
      }
    }

    public void setConnectionTimeout(int paramInt)
    {
      if (hasConnection())
        this.wrappedConnection.setConnectionTimeout(paramInt);
    }

    public void setHost(String paramString)
      throws IllegalStateException
    {
      if (hasConnection())
        this.wrappedConnection.setHost(paramString);
    }

    public void setHttpConnectionManager(HttpConnectionManager paramHttpConnectionManager)
    {
      if (hasConnection())
        this.wrappedConnection.setHttpConnectionManager(paramHttpConnectionManager);
    }

    public void setLastResponseInputStream(InputStream paramInputStream)
    {
      if (hasConnection())
        this.wrappedConnection.setLastResponseInputStream(paramInputStream);
    }

    public void setLocalAddress(InetAddress paramInetAddress)
    {
      if (hasConnection())
      {
        this.wrappedConnection.setLocalAddress(paramInetAddress);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void setParams(HttpConnectionParams paramHttpConnectionParams)
    {
      if (hasConnection())
      {
        this.wrappedConnection.setParams(paramHttpConnectionParams);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void setPort(int paramInt)
      throws IllegalStateException
    {
      if (hasConnection())
        this.wrappedConnection.setPort(paramInt);
    }

    public void setProtocol(Protocol paramProtocol)
    {
      if (hasConnection())
        this.wrappedConnection.setProtocol(paramProtocol);
    }

    public void setProxyHost(String paramString)
      throws IllegalStateException
    {
      if (hasConnection())
        this.wrappedConnection.setProxyHost(paramString);
    }

    public void setProxyPort(int paramInt)
      throws IllegalStateException
    {
      if (hasConnection())
        this.wrappedConnection.setProxyPort(paramInt);
    }

    public void setSendBufferSize(int paramInt)
      throws SocketException
    {
      if (hasConnection())
      {
        this.wrappedConnection.setSendBufferSize(paramInt);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void setSoTimeout(int paramInt)
      throws SocketException, IllegalStateException
    {
      if (hasConnection())
        this.wrappedConnection.setSoTimeout(paramInt);
    }

    public void setSocketTimeout(int paramInt)
      throws SocketException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.setSocketTimeout(paramInt);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void setStaleCheckingEnabled(boolean paramBoolean)
    {
      if (hasConnection())
      {
        this.wrappedConnection.setStaleCheckingEnabled(paramBoolean);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void setVirtualHost(String paramString)
      throws IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.setVirtualHost(paramString);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void shutdownOutput()
    {
      if (hasConnection())
        this.wrappedConnection.shutdownOutput();
    }

    public void tunnelCreated()
      throws IllegalStateException, IOException
    {
      if (hasConnection())
        this.wrappedConnection.tunnelCreated();
    }

    public void write(byte[] paramArrayOfByte)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.write(paramArrayOfByte);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.write(paramArrayOfByte, paramInt1, paramInt2);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void writeLine()
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.writeLine();
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }

    public void writeLine(byte[] paramArrayOfByte)
      throws IOException, IllegalStateException
    {
      if (hasConnection())
      {
        this.wrappedConnection.writeLine(paramArrayOfByte);
        return;
      }
      throw new IllegalStateException("Connection has been released");
    }
  }

  private static class HttpConnectionWithReference extends HttpConnection
  {
    public WeakReference reference = new WeakReference(this, MultiThreadedHttpConnectionManager.REFERENCE_QUEUE);

    public HttpConnectionWithReference(HostConfiguration paramHostConfiguration)
    {
      super();
    }
  }

  private static class ReferenceQueueThread extends Thread
  {
    private boolean shutdown = false;

    public ReferenceQueueThread()
    {
      setDaemon(true);
      setName("MultiThreadedHttpConnectionManager cleanup");
    }

    private void handleReference(Reference paramReference)
    {
      synchronized (MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE)
      {
        paramReference = (MultiThreadedHttpConnectionManager.ConnectionSource)MultiThreadedHttpConnectionManager.REFERENCE_TO_CONNECTION_SOURCE.remove(paramReference);
        if (paramReference != null)
        {
          if (MultiThreadedHttpConnectionManager.LOG.isDebugEnabled())
            MultiThreadedHttpConnectionManager.LOG.debug("Connection reclaimed by garbage collector, hostConfig=" + paramReference.hostConfiguration);
          paramReference.connectionPool.handleLostConnection(paramReference.hostConfiguration);
        }
        return;
      }
    }

    public void run()
    {
      while (true)
      {
        if (this.shutdown)
          return;
        try
        {
          Reference localReference = MultiThreadedHttpConnectionManager.REFERENCE_QUEUE.remove(1000L);
          if (localReference != null)
            handleReference(localReference);
        }
        catch (InterruptedException localInterruptedException)
        {
          MultiThreadedHttpConnectionManager.LOG.debug("ReferenceQueueThread interrupted", localInterruptedException);
        }
      }
    }

    public void shutdown()
    {
      this.shutdown = true;
    }
  }

  private static class WaitingThread
  {
    public MultiThreadedHttpConnectionManager.HostConnectionPool hostConnectionPool;
    public Thread thread;

    private WaitingThread()
    {
    }

    WaitingThread(MultiThreadedHttpConnectionManager.1 param1)
    {
      this();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.apache.commons.httpclient.MultiThreadedHttpConnectionManager
 * JD-Core Version:    0.6.2
 */