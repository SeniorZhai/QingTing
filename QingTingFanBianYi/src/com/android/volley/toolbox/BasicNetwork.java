package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache.Entry;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

public class BasicNetwork
  implements Network
{
  protected static final boolean DEBUG = VolleyLog.DEBUG;
  private static int DEFAULT_POOL_SIZE = 4096;
  private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
  protected final HttpStack mHttpStack;
  protected final ByteArrayPool mPool;

  public BasicNetwork(HttpStack paramHttpStack)
  {
    this(paramHttpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
  }

  public BasicNetwork(HttpStack paramHttpStack, ByteArrayPool paramByteArrayPool)
  {
    this.mHttpStack = paramHttpStack;
    this.mPool = paramByteArrayPool;
  }

  private void addCacheHeaders(Map<String, String> paramMap, Cache.Entry paramEntry)
  {
    if (paramEntry == null);
    do
    {
      return;
      if (paramEntry.etag != null)
        paramMap.put("If-None-Match", paramEntry.etag);
    }
    while (paramEntry.serverDate <= 0L);
    paramMap.put("If-Modified-Since", DateUtils.formatDate(new Date(paramEntry.serverDate)));
  }

  private static void attemptRetryOnException(String paramString, Request<?> paramRequest, VolleyError paramVolleyError)
    throws VolleyError
  {
    RetryPolicy localRetryPolicy = paramRequest.getRetryPolicy();
    int i = paramRequest.getTimeoutMs();
    try
    {
      localRetryPolicy.retry(paramVolleyError);
      paramRequest.addMarker(String.format("%s-retry [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      return;
    }
    catch (VolleyError paramVolleyError)
    {
      paramRequest.addMarker(String.format("%s-timeout-giveup [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
    }
    throw paramVolleyError;
  }

  private static Map<String, String> convertHeaders(Header[] paramArrayOfHeader)
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfHeader.length)
        return localHashMap;
      localHashMap.put(paramArrayOfHeader[i].getName(), paramArrayOfHeader[i].getValue());
      i += 1;
    }
  }

  // ERROR //
  private byte[] entityToBytes(org.apache.http.HttpEntity paramHttpEntity)
    throws IOException, ServerError, OutOfMemoryError
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 7
    //   5: aconst_null
    //   6: astore 8
    //   8: aconst_null
    //   9: astore_2
    //   10: aconst_null
    //   11: astore 6
    //   13: aconst_null
    //   14: astore 5
    //   16: new 145	com/android/volley/toolbox/PoolingByteArrayOutputStream
    //   19: dup
    //   20: aload_0
    //   21: getfield 43	com/android/volley/toolbox/BasicNetwork:mPool	Lcom/android/volley/toolbox/ByteArrayPool;
    //   24: aload_1
    //   25: invokeinterface 151 1 0
    //   30: l2i
    //   31: invokespecial 154	com/android/volley/toolbox/PoolingByteArrayOutputStream:<init>	(Lcom/android/volley/toolbox/ByteArrayPool;I)V
    //   34: astore 4
    //   36: aload 5
    //   38: astore_2
    //   39: aload 6
    //   41: astore_3
    //   42: aload_1
    //   43: invokeinterface 158 1 0
    //   48: astore 7
    //   50: aload 7
    //   52: ifnonnull +56 -> 108
    //   55: aload 5
    //   57: astore_2
    //   58: aload 6
    //   60: astore_3
    //   61: new 141	com/android/volley/ServerError
    //   64: dup
    //   65: invokespecial 159	com/android/volley/ServerError:<init>	()V
    //   68: athrow
    //   69: astore 5
    //   71: aload 4
    //   73: astore_3
    //   74: aload 5
    //   76: astore 4
    //   78: aload 4
    //   80: athrow
    //   81: astore 4
    //   83: aload_1
    //   84: invokeinterface 162 1 0
    //   89: aload_0
    //   90: getfield 43	com/android/volley/toolbox/BasicNetwork:mPool	Lcom/android/volley/toolbox/ByteArrayPool;
    //   93: aload_2
    //   94: invokevirtual 166	com/android/volley/toolbox/ByteArrayPool:returnBuf	([B)V
    //   97: aload_3
    //   98: ifnull +7 -> 105
    //   101: aload_3
    //   102: invokevirtual 169	com/android/volley/toolbox/PoolingByteArrayOutputStream:close	()V
    //   105: aload 4
    //   107: athrow
    //   108: aload 5
    //   110: astore_2
    //   111: aload 6
    //   113: astore_3
    //   114: aload_0
    //   115: getfield 43	com/android/volley/toolbox/BasicNetwork:mPool	Lcom/android/volley/toolbox/ByteArrayPool;
    //   118: sipush 1024
    //   121: invokevirtual 173	com/android/volley/toolbox/ByteArrayPool:getBuf	(I)[B
    //   124: astore 5
    //   126: aload 5
    //   128: astore_2
    //   129: aload 5
    //   131: astore_3
    //   132: aload 7
    //   134: aload 5
    //   136: invokevirtual 179	java/io/InputStream:read	([B)I
    //   139: istore 9
    //   141: iload 9
    //   143: iconst_m1
    //   144: if_icmpne +44 -> 188
    //   147: aload 5
    //   149: astore_2
    //   150: aload 5
    //   152: astore_3
    //   153: aload 4
    //   155: invokevirtual 183	com/android/volley/toolbox/PoolingByteArrayOutputStream:toByteArray	()[B
    //   158: astore 6
    //   160: aload_1
    //   161: invokeinterface 162 1 0
    //   166: aload_0
    //   167: getfield 43	com/android/volley/toolbox/BasicNetwork:mPool	Lcom/android/volley/toolbox/ByteArrayPool;
    //   170: aload 5
    //   172: invokevirtual 166	com/android/volley/toolbox/ByteArrayPool:returnBuf	([B)V
    //   175: aload 4
    //   177: ifnull +8 -> 185
    //   180: aload 4
    //   182: invokevirtual 169	com/android/volley/toolbox/PoolingByteArrayOutputStream:close	()V
    //   185: aload 6
    //   187: areturn
    //   188: aload 5
    //   190: astore_2
    //   191: aload 5
    //   193: astore_3
    //   194: aload 4
    //   196: aload 5
    //   198: iconst_0
    //   199: iload 9
    //   201: invokevirtual 187	com/android/volley/toolbox/PoolingByteArrayOutputStream:write	([BII)V
    //   204: goto -78 -> 126
    //   207: astore 5
    //   209: aload_3
    //   210: astore_2
    //   211: aload 4
    //   213: astore_3
    //   214: aload 5
    //   216: astore 4
    //   218: goto -135 -> 83
    //   221: astore_1
    //   222: ldc 189
    //   224: iconst_0
    //   225: anewarray 4	java/lang/Object
    //   228: invokestatic 193	com/android/volley/VolleyLog:v	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   231: goto -65 -> 166
    //   234: astore_1
    //   235: ldc 189
    //   237: iconst_0
    //   238: anewarray 4	java/lang/Object
    //   241: invokestatic 193	com/android/volley/VolleyLog:v	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   244: goto -155 -> 89
    //   247: astore 4
    //   249: aload 8
    //   251: astore_2
    //   252: aload 7
    //   254: astore_3
    //   255: goto -177 -> 78
    //
    // Exception table:
    //   from	to	target	type
    //   42	50	69	java/lang/OutOfMemoryError
    //   61	69	69	java/lang/OutOfMemoryError
    //   114	126	69	java/lang/OutOfMemoryError
    //   132	141	69	java/lang/OutOfMemoryError
    //   153	160	69	java/lang/OutOfMemoryError
    //   194	204	69	java/lang/OutOfMemoryError
    //   16	36	81	finally
    //   78	81	81	finally
    //   42	50	207	finally
    //   61	69	207	finally
    //   114	126	207	finally
    //   132	141	207	finally
    //   153	160	207	finally
    //   194	204	207	finally
    //   160	166	221	java/io/IOException
    //   83	89	234	java/io/IOException
    //   16	36	247	java/lang/OutOfMemoryError
  }

  private void logSlowRequests(long paramLong, Request<?> paramRequest, byte[] paramArrayOfByte, StatusLine paramStatusLine)
  {
    if ((DEBUG) || (paramLong > SLOW_REQUEST_THRESHOLD_MS))
      if (paramArrayOfByte == null)
        break label82;
    label82: for (paramArrayOfByte = Integer.valueOf(paramArrayOfByte.length); ; paramArrayOfByte = "null")
    {
      VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[] { paramRequest, Long.valueOf(paramLong), paramArrayOfByte, Integer.valueOf(paramStatusLine.getStatusCode()), Integer.valueOf(paramRequest.getRetryPolicy().getCurrentRetryCount()) });
      return;
    }
  }

  protected void logError(String paramString1, String paramString2, long paramLong)
  {
    VolleyLog.v("HTTP ERROR(%s) %d ms to fetch %s", new Object[] { paramString1, Long.valueOf(SystemClock.elapsedRealtime() - paramLong), paramString2 });
  }

  public NetworkResponse performRequest(Request<?> paramRequest)
    throws VolleyError
  {
    long l = SystemClock.elapsedRealtime();
    while (true)
    {
      HttpResponse localHttpResponse2 = null;
      byte[] arrayOfByte = null;
      Object localObject4 = new HashMap();
      Object localObject2 = arrayOfByte;
      HttpResponse localHttpResponse1 = localHttpResponse2;
      Object localObject3 = localObject4;
      try
      {
        Object localObject5 = new HashMap();
        localObject2 = arrayOfByte;
        localHttpResponse1 = localHttpResponse2;
        localObject3 = localObject4;
        addCacheHeaders((Map)localObject5, paramRequest.getCacheEntry());
        localObject2 = arrayOfByte;
        localHttpResponse1 = localHttpResponse2;
        localObject3 = localObject4;
        localHttpResponse2 = this.mHttpStack.performRequest(paramRequest, (Map)localObject5);
        localObject2 = arrayOfByte;
        localHttpResponse1 = localHttpResponse2;
        localObject3 = localObject4;
        localObject5 = localHttpResponse2.getStatusLine();
        localObject2 = arrayOfByte;
        localHttpResponse1 = localHttpResponse2;
        localObject3 = localObject4;
        i = ((StatusLine)localObject5).getStatusCode();
        localObject2 = arrayOfByte;
        localHttpResponse1 = localHttpResponse2;
        localObject3 = localObject4;
        localObject4 = convertHeaders(localHttpResponse2.getAllHeaders());
        if (i == 304)
        {
          localObject2 = arrayOfByte;
          localHttpResponse1 = localHttpResponse2;
          localObject3 = localObject4;
          return new NetworkResponse(304, paramRequest.getCacheEntry().data, (Map)localObject4, true);
        }
        localObject2 = arrayOfByte;
        localHttpResponse1 = localHttpResponse2;
        localObject3 = localObject4;
        if (localHttpResponse2.getEntity() != null)
        {
          localObject2 = arrayOfByte;
          localHttpResponse1 = localHttpResponse2;
          localObject3 = localObject4;
          arrayOfByte = entityToBytes(localHttpResponse2.getEntity());
          localObject2 = arrayOfByte;
          localHttpResponse1 = localHttpResponse2;
          localObject3 = localObject4;
          logSlowRequests(SystemClock.elapsedRealtime() - l, paramRequest, arrayOfByte, (StatusLine)localObject5);
          if ((i >= 200) && (i <= 299))
            break label316;
          localObject2 = arrayOfByte;
          localHttpResponse1 = localHttpResponse2;
          localObject3 = localObject4;
          throw new IOException();
        }
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        while (true)
        {
          attemptRetryOnException("socket", paramRequest, new TimeoutError());
          break;
          localObject2 = localSocketTimeoutException;
          localHttpResponse1 = localHttpResponse2;
          localObject3 = localObject4;
          localObject1 = new byte[0];
        }
        localObject2 = localObject1;
        localHttpResponse1 = localHttpResponse2;
        localObject3 = localObject4;
        Object localObject1 = new NetworkResponse(i, (byte[])localObject1, (Map)localObject4, false);
        return localObject1;
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        attemptRetryOnException("connection", paramRequest, new TimeoutError());
      }
      catch (MalformedURLException localMalformedURLException)
      {
        throw new RuntimeException("Bad URL " + paramRequest.getUrl(), localMalformedURLException);
      }
      catch (IOException localIOException)
      {
        int i;
        label316: NetworkResponse localNetworkResponse;
        if (localHttpResponse1 != null)
        {
          i = localHttpResponse1.getStatusLine().getStatusCode();
          VolleyLog.e("Unexpected response code %d for %s", new Object[] { Integer.valueOf(i), paramRequest.getUrl() });
          if (localObject2 != null)
          {
            localNetworkResponse = new NetworkResponse(i, (byte[])localObject2, localObject3, false);
            if ((i == 401) || (i == 403))
              attemptRetryOnException("auth", paramRequest, new AuthFailureError(localNetworkResponse));
          }
        }
        else
        {
          throw new NoConnectionError(localNetworkResponse);
          throw new ServerError(localNetworkResponse);
          throw new NetworkError(null);
        }
      }
      catch (OutOfMemoryError paramRequest)
      {
      }
    }
    throw new VolleyError();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.toolbox.BasicNetwork
 * JD-Core Version:    0.6.2
 */