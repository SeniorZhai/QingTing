package fm.qingting.async.http;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import fm.qingting.async.AsyncSSLSocket;
import fm.qingting.async.AsyncServer;
import fm.qingting.async.AsyncSocket;
import fm.qingting.async.ByteBufferList;
import fm.qingting.async.DataEmitter;
import fm.qingting.async.DataEmitterBase;
import fm.qingting.async.FilteredDataEmitter;
import fm.qingting.async.Util;
import fm.qingting.async.callback.CompletedCallback;
import fm.qingting.async.callback.ConnectCallback;
import fm.qingting.async.callback.WritableCallback;
import fm.qingting.async.http.libcore.Charsets;
import fm.qingting.async.http.libcore.DiskLruCache;
import fm.qingting.async.http.libcore.DiskLruCache.Editor;
import fm.qingting.async.http.libcore.DiskLruCache.Snapshot;
import fm.qingting.async.http.libcore.RawHeaders;
import fm.qingting.async.http.libcore.RequestHeaders;
import fm.qingting.async.http.libcore.ResponseHeaders;
import fm.qingting.async.http.libcore.StrictLineReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.net.CacheRequest;
import java.net.CacheResponse;
import java.net.SecureCacheResponse;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;

public class ResponseCacheMiddleware extends SimpleMiddleware
{
  public static final String CACHE = "cache";
  public static final String CONDITIONAL_CACHE = "conditional-cache";
  private static final int ENTRY_BODY = 1;
  private static final int ENTRY_COUNT = 2;
  private static final int ENTRY_METADATA = 0;
  private static final String LOGTAG = "AsyncHttpCache";
  public static final String SERVED_FROM = "Served-From";
  private static final int VERSION = 201105;
  private DiskLruCache cache;
  File cacheDir;
  int cacheHitCount;
  int cacheStoreCount;
  boolean caching = true;
  private AsyncHttpClient client;
  int conditionalCacheHitCount;
  int networkCount;
  long size;
  int writeAbortCount;
  int writeSuccessCount;

  public static ResponseCacheMiddleware addCache(AsyncHttpClient paramAsyncHttpClient, File paramFile, long paramLong)
    throws IOException
  {
    Object localObject = paramAsyncHttpClient.getMiddleware().iterator();
    while (((Iterator)localObject).hasNext())
      if (((AsyncHttpClientMiddleware)((Iterator)localObject).next() instanceof ResponseCacheMiddleware))
        throw new IOException("Response cache already added to http client");
    localObject = new ResponseCacheMiddleware();
    ((ResponseCacheMiddleware)localObject).size = paramLong;
    ((ResponseCacheMiddleware)localObject).client = paramAsyncHttpClient;
    ((ResponseCacheMiddleware)localObject).cacheDir = paramFile;
    ((ResponseCacheMiddleware)localObject).open();
    paramAsyncHttpClient.insertMiddleware((AsyncHttpClientMiddleware)localObject);
    return localObject;
  }

  private static InputStream newBodyInputStream(final DiskLruCache.Snapshot paramSnapshot)
  {
    return new FilterInputStream(paramSnapshot.getInputStream(1))
    {
      public void close()
        throws IOException
      {
        paramSnapshot.close();
        super.close();
      }
    };
  }

  private void open()
    throws IOException
  {
    this.cache = DiskLruCache.open(this.cacheDir, 201105, 2, this.size);
  }

  private static String uriToKey(URI paramURI)
  {
    try
    {
      paramURI = new BigInteger(1, MessageDigest.getInstance("MD5").digest(paramURI.toString().getBytes())).toString(16);
      return paramURI;
    }
    catch (NoSuchAlgorithmException paramURI)
    {
    }
    throw new RuntimeException(paramURI);
  }

  public void clear()
    throws IOException
  {
    if (this.cache != null)
    {
      this.cache.delete();
      open();
    }
  }

  public int getCacheHitCount()
  {
    return this.cacheHitCount;
  }

  public int getCacheStoreCount()
  {
    return this.cacheStoreCount;
  }

  public boolean getCaching()
  {
    return this.caching;
  }

  public int getConditionalCacheHitCount()
  {
    return this.conditionalCacheHitCount;
  }

  public int getNetworkCount()
  {
    return this.networkCount;
  }

  // ERROR //
  public fm.qingting.async.future.Cancellable getSocket(final AsyncHttpClientMiddleware.GetSocketData paramGetSocketData)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 156	fm/qingting/async/http/ResponseCacheMiddleware:cache	Lfm/qingting/async/http/libcore/DiskLruCache;
    //   4: ifnonnull +5 -> 9
    //   7: aconst_null
    //   8: areturn
    //   9: aload_0
    //   10: getfield 84	fm/qingting/async/http/ResponseCacheMiddleware:caching	Z
    //   13: ifne +5 -> 18
    //   16: aconst_null
    //   17: areturn
    //   18: aload_1
    //   19: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   22: invokevirtual 231	fm/qingting/async/http/AsyncHttpRequest:getHeaders	()Lfm/qingting/async/http/libcore/RequestHeaders;
    //   25: invokevirtual 236	fm/qingting/async/http/libcore/RequestHeaders:isNoCache	()Z
    //   28: ifeq +5 -> 33
    //   31: aconst_null
    //   32: areturn
    //   33: aload_1
    //   34: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   37: invokevirtual 240	fm/qingting/async/http/AsyncHttpRequest:getUri	()Ljava/net/URI;
    //   40: invokestatic 242	fm/qingting/async/http/ResponseCacheMiddleware:uriToKey	(Ljava/net/URI;)Ljava/lang/String;
    //   43: astore_2
    //   44: aload_0
    //   45: getfield 156	fm/qingting/async/http/ResponseCacheMiddleware:cache	Lfm/qingting/async/http/libcore/DiskLruCache;
    //   48: aload_2
    //   49: invokevirtual 246	fm/qingting/async/http/libcore/DiskLruCache:get	(Ljava/lang/String;)Lfm/qingting/async/http/libcore/DiskLruCache$Snapshot;
    //   52: astore_3
    //   53: aload_3
    //   54: ifnonnull +15 -> 69
    //   57: aload_0
    //   58: aload_0
    //   59: getfield 215	fm/qingting/async/http/ResponseCacheMiddleware:networkCount	I
    //   62: iconst_1
    //   63: iadd
    //   64: putfield 215	fm/qingting/async/http/ResponseCacheMiddleware:networkCount	I
    //   67: aconst_null
    //   68: areturn
    //   69: new 34	fm/qingting/async/http/ResponseCacheMiddleware$Entry
    //   72: dup
    //   73: aload_3
    //   74: iconst_0
    //   75: invokevirtual 146	fm/qingting/async/http/libcore/DiskLruCache$Snapshot:getInputStream	(I)Ljava/io/InputStream;
    //   78: invokespecial 249	fm/qingting/async/http/ResponseCacheMiddleware$Entry:<init>	(Ljava/io/InputStream;)V
    //   81: astore 4
    //   83: aload 4
    //   85: aload_1
    //   86: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   89: invokevirtual 240	fm/qingting/async/http/AsyncHttpRequest:getUri	()Ljava/net/URI;
    //   92: aload_1
    //   93: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   96: invokevirtual 252	fm/qingting/async/http/AsyncHttpRequest:getMethod	()Ljava/lang/String;
    //   99: aload_1
    //   100: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   103: invokevirtual 231	fm/qingting/async/http/AsyncHttpRequest:getHeaders	()Lfm/qingting/async/http/libcore/RequestHeaders;
    //   106: invokevirtual 255	fm/qingting/async/http/libcore/RequestHeaders:getHeaders	()Lfm/qingting/async/http/libcore/RawHeaders;
    //   109: invokevirtual 261	fm/qingting/async/http/libcore/RawHeaders:toMultimap	()Ljava/util/Map;
    //   112: invokevirtual 265	fm/qingting/async/http/ResponseCacheMiddleware$Entry:matches	(Ljava/net/URI;Ljava/lang/String;Ljava/util/Map;)Z
    //   115: ifne +22 -> 137
    //   118: aload_3
    //   119: invokevirtual 268	fm/qingting/async/http/libcore/DiskLruCache$Snapshot:close	()V
    //   122: aconst_null
    //   123: areturn
    //   124: astore_1
    //   125: aload_0
    //   126: aload_0
    //   127: getfield 215	fm/qingting/async/http/ResponseCacheMiddleware:networkCount	I
    //   130: iconst_1
    //   131: iadd
    //   132: putfield 215	fm/qingting/async/http/ResponseCacheMiddleware:networkCount	I
    //   135: aconst_null
    //   136: areturn
    //   137: aload 4
    //   139: invokestatic 272	fm/qingting/async/http/ResponseCacheMiddleware$Entry:access$100	(Lfm/qingting/async/http/ResponseCacheMiddleware$Entry;)Z
    //   142: ifeq +43 -> 185
    //   145: new 40	fm/qingting/async/http/ResponseCacheMiddleware$EntrySecureCacheResponse
    //   148: dup
    //   149: aload 4
    //   151: aload_3
    //   152: invokespecial 275	fm/qingting/async/http/ResponseCacheMiddleware$EntrySecureCacheResponse:<init>	(Lfm/qingting/async/http/ResponseCacheMiddleware$Entry;Lfm/qingting/async/http/libcore/DiskLruCache$Snapshot;)V
    //   155: astore_2
    //   156: aload_2
    //   157: invokevirtual 279	java/net/CacheResponse:getHeaders	()Ljava/util/Map;
    //   160: astore 5
    //   162: aload_2
    //   163: invokevirtual 283	java/net/CacheResponse:getBody	()Ljava/io/InputStream;
    //   166: astore 6
    //   168: aload 5
    //   170: ifnull +8 -> 178
    //   173: aload 6
    //   175: ifnonnull +27 -> 202
    //   178: aload 6
    //   180: invokevirtual 286	java/io/InputStream:close	()V
    //   183: aconst_null
    //   184: areturn
    //   185: new 37	fm/qingting/async/http/ResponseCacheMiddleware$EntryCacheResponse
    //   188: dup
    //   189: aload 4
    //   191: aload_3
    //   192: invokespecial 287	fm/qingting/async/http/ResponseCacheMiddleware$EntryCacheResponse:<init>	(Lfm/qingting/async/http/ResponseCacheMiddleware$Entry;Lfm/qingting/async/http/libcore/DiskLruCache$Snapshot;)V
    //   195: astore_2
    //   196: goto -40 -> 156
    //   199: astore_1
    //   200: aconst_null
    //   201: areturn
    //   202: aload 5
    //   204: invokestatic 291	fm/qingting/async/http/libcore/RawHeaders:fromMultimap	(Ljava/util/Map;)Lfm/qingting/async/http/libcore/RawHeaders;
    //   207: astore 5
    //   209: new 293	fm/qingting/async/http/libcore/ResponseHeaders
    //   212: dup
    //   213: aload_1
    //   214: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   217: invokevirtual 240	fm/qingting/async/http/AsyncHttpRequest:getUri	()Ljava/net/URI;
    //   220: aload 5
    //   222: invokespecial 296	fm/qingting/async/http/libcore/ResponseHeaders:<init>	(Ljava/net/URI;Lfm/qingting/async/http/libcore/RawHeaders;)V
    //   225: astore 7
    //   227: aload 7
    //   229: invokestatic 302	java/lang/System:currentTimeMillis	()J
    //   232: invokestatic 302	java/lang/System:currentTimeMillis	()J
    //   235: invokevirtual 306	fm/qingting/async/http/libcore/ResponseHeaders:setLocalTimestamps	(JJ)V
    //   238: aload 7
    //   240: invokestatic 302	java/lang/System:currentTimeMillis	()J
    //   243: aload_1
    //   244: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   247: invokevirtual 231	fm/qingting/async/http/AsyncHttpRequest:getHeaders	()Lfm/qingting/async/http/libcore/RequestHeaders;
    //   250: invokevirtual 310	fm/qingting/async/http/libcore/ResponseHeaders:chooseResponseSource	(JLfm/qingting/async/http/libcore/RequestHeaders;)Lfm/qingting/async/http/libcore/ResponseSource;
    //   253: astore 8
    //   255: aload 8
    //   257: getstatic 315	fm/qingting/async/http/libcore/ResponseSource:CACHE	Lfm/qingting/async/http/libcore/ResponseSource;
    //   260: if_acmpne +139 -> 399
    //   263: aload_0
    //   264: aload_0
    //   265: getfield 205	fm/qingting/async/http/ResponseCacheMiddleware:cacheHitCount	I
    //   268: iconst_1
    //   269: iadd
    //   270: putfield 205	fm/qingting/async/http/ResponseCacheMiddleware:cacheHitCount	I
    //   273: aload_1
    //   274: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   277: ldc_w 317
    //   280: invokevirtual 320	fm/qingting/async/http/AsyncHttpRequest:logi	(Ljava/lang/String;)V
    //   283: aload 4
    //   285: invokestatic 272	fm/qingting/async/http/ResponseCacheMiddleware$Entry:access$100	(Lfm/qingting/async/http/ResponseCacheMiddleware$Entry;)Z
    //   288: ifeq +95 -> 383
    //   291: new 26	fm/qingting/async/http/ResponseCacheMiddleware$CachedSSLSocket
    //   294: dup
    //   295: aload_0
    //   296: aload_2
    //   297: checkcast 40	fm/qingting/async/http/ResponseCacheMiddleware$EntrySecureCacheResponse
    //   300: invokespecial 323	fm/qingting/async/http/ResponseCacheMiddleware$CachedSSLSocket:<init>	(Lfm/qingting/async/http/ResponseCacheMiddleware;Ljava/net/CacheResponse;)V
    //   303: astore_2
    //   304: aload 5
    //   306: ldc_w 325
    //   309: invokevirtual 328	fm/qingting/async/http/libcore/RawHeaders:removeAll	(Ljava/lang/String;)V
    //   312: aload 5
    //   314: ldc_w 330
    //   317: invokevirtual 328	fm/qingting/async/http/libcore/RawHeaders:removeAll	(Ljava/lang/String;)V
    //   320: aload 5
    //   322: ldc_w 332
    //   325: aload_3
    //   326: iconst_1
    //   327: invokevirtual 336	fm/qingting/async/http/libcore/DiskLruCache$Snapshot:getLength	(I)J
    //   330: invokestatic 340	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   333: invokevirtual 344	fm/qingting/async/http/libcore/RawHeaders:set	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: aload_2
    //   337: getfield 348	fm/qingting/async/http/ResponseCacheMiddleware$CachedSocket:pending	Lfm/qingting/async/ByteBufferList;
    //   340: aload 5
    //   342: invokevirtual 351	fm/qingting/async/http/libcore/RawHeaders:toHeaderString	()Ljava/lang/String;
    //   345: invokevirtual 182	java/lang/String:getBytes	()[B
    //   348: invokestatic 357	java/nio/ByteBuffer:wrap	([B)Ljava/nio/ByteBuffer;
    //   351: invokevirtual 363	fm/qingting/async/ByteBufferList:add	(Ljava/nio/ByteBuffer;)V
    //   354: aload_0
    //   355: getfield 89	fm/qingting/async/http/ResponseCacheMiddleware:client	Lfm/qingting/async/http/AsyncHttpClient;
    //   358: invokevirtual 367	fm/qingting/async/http/AsyncHttpClient:getServer	()Lfm/qingting/async/AsyncServer;
    //   361: new 6	fm/qingting/async/http/ResponseCacheMiddleware$1
    //   364: dup
    //   365: aload_0
    //   366: aload_1
    //   367: aload_2
    //   368: invokespecial 370	fm/qingting/async/http/ResponseCacheMiddleware$1:<init>	(Lfm/qingting/async/http/ResponseCacheMiddleware;Lfm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData;Lfm/qingting/async/http/ResponseCacheMiddleware$CachedSocket;)V
    //   371: invokevirtual 376	fm/qingting/async/AsyncServer:post	(Ljava/lang/Runnable;)Ljava/lang/Object;
    //   374: pop
    //   375: new 378	fm/qingting/async/future/SimpleCancellable
    //   378: dup
    //   379: invokespecial 379	fm/qingting/async/future/SimpleCancellable:<init>	()V
    //   382: areturn
    //   383: new 29	fm/qingting/async/http/ResponseCacheMiddleware$CachedSocket
    //   386: dup
    //   387: aload_0
    //   388: aload_2
    //   389: checkcast 37	fm/qingting/async/http/ResponseCacheMiddleware$EntryCacheResponse
    //   392: invokespecial 380	fm/qingting/async/http/ResponseCacheMiddleware$CachedSocket:<init>	(Lfm/qingting/async/http/ResponseCacheMiddleware;Ljava/net/CacheResponse;)V
    //   395: astore_2
    //   396: goto -92 -> 304
    //   399: aload 8
    //   401: getstatic 382	fm/qingting/async/http/libcore/ResponseSource:CONDITIONAL_CACHE	Lfm/qingting/async/http/libcore/ResponseSource;
    //   404: if_acmpne +45 -> 449
    //   407: aload_1
    //   408: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   411: ldc_w 384
    //   414: invokevirtual 320	fm/qingting/async/http/AsyncHttpRequest:logi	(Ljava/lang/String;)V
    //   417: new 18	fm/qingting/async/http/ResponseCacheMiddleware$CacheData
    //   420: dup
    //   421: invokespecial 385	fm/qingting/async/http/ResponseCacheMiddleware$CacheData:<init>	()V
    //   424: astore_3
    //   425: aload_3
    //   426: aload 7
    //   428: putfield 389	fm/qingting/async/http/ResponseCacheMiddleware$CacheData:cachedResponseHeaders	Lfm/qingting/async/http/libcore/ResponseHeaders;
    //   431: aload_3
    //   432: aload_2
    //   433: putfield 393	fm/qingting/async/http/ResponseCacheMiddleware$CacheData:candidate	Ljava/net/CacheResponse;
    //   436: aload_1
    //   437: getfield 397	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:state	Landroid/os/Bundle;
    //   440: ldc_w 399
    //   443: aload_3
    //   444: invokevirtual 405	android/os/Bundle:putParcelable	(Ljava/lang/String;Landroid/os/Parcelable;)V
    //   447: aconst_null
    //   448: areturn
    //   449: aload_1
    //   450: getfield 225	fm/qingting/async/http/AsyncHttpClientMiddleware$GetSocketData:request	Lfm/qingting/async/http/AsyncHttpRequest;
    //   453: ldc_w 407
    //   456: invokevirtual 410	fm/qingting/async/http/AsyncHttpRequest:logd	(Ljava/lang/String;)V
    //   459: aload 6
    //   461: invokevirtual 286	java/io/InputStream:close	()V
    //   464: aconst_null
    //   465: areturn
    //   466: astore_1
    //   467: goto -284 -> 183
    //   470: astore_1
    //   471: goto -7 -> 464
    //
    // Exception table:
    //   from	to	target	type
    //   44	53	124	java/io/IOException
    //   57	67	124	java/io/IOException
    //   69	83	124	java/io/IOException
    //   156	168	199	java/lang/Exception
    //   178	183	466	java/lang/Exception
    //   459	464	470	java/lang/Exception
  }

  public void onBodyDecoder(AsyncHttpClientMiddleware.OnBodyData paramOnBodyData)
  {
    if ((CachedSocket)Util.getWrappedSocket(paramOnBodyData.socket, CachedSocket.class) != null)
      paramOnBodyData.headers.getHeaders().set("Served-From", "cache");
    while (true)
    {
      return;
      Object localObject1 = (CacheData)paramOnBodyData.state.getParcelable("cache-data");
      Object localObject2;
      if (localObject1 != null)
      {
        if (((CacheData)localObject1).cachedResponseHeaders.validate(paramOnBodyData.headers))
        {
          paramOnBodyData.request.logi("Serving response from conditional cache");
          paramOnBodyData.headers = ((CacheData)localObject1).cachedResponseHeaders.combine(paramOnBodyData.headers);
          paramOnBodyData.headers.getHeaders().setStatusLine(((CacheData)localObject1).cachedResponseHeaders.getHeaders().getStatusLine());
          paramOnBodyData.headers.getHeaders().set("Served-From", "conditional-cache");
          this.conditionalCacheHitCount += 1;
          localObject2 = new BodySpewer(null);
          ((BodySpewer)localObject2).cacheResponse = ((CacheData)localObject1).candidate;
          ((BodySpewer)localObject2).setDataEmitter(paramOnBodyData.bodyEmitter);
          paramOnBodyData.bodyEmitter = ((DataEmitter)localObject2);
          ((BodySpewer)localObject2).spew();
          return;
        }
        paramOnBodyData.state.remove("cache-data");
      }
      if (this.caching)
      {
        if ((!paramOnBodyData.headers.isCacheable(paramOnBodyData.request.getHeaders())) || (!paramOnBodyData.request.getMethod().equals("GET")))
        {
          this.networkCount += 1;
          paramOnBodyData.request.logd("Response is not cacheable");
          return;
        }
        localObject2 = uriToKey(paramOnBodyData.request.getUri());
        localObject1 = paramOnBodyData.request.getHeaders().getHeaders().getAll(paramOnBodyData.headers.getVaryFields());
        Entry localEntry = new Entry(paramOnBodyData.request.getUri(), (RawHeaders)localObject1, paramOnBodyData.request, paramOnBodyData.headers);
        localObject1 = new BodyCacher(null);
        try
        {
          localObject2 = this.cache.edit((String)localObject2);
          if (localObject2 != null)
          {
            localEntry.writeTo((DiskLruCache.Editor)localObject2);
            ((BodyCacher)localObject1).cacheRequest = new CacheRequestImpl((DiskLruCache.Editor)localObject2);
            if (((BodyCacher)localObject1).cacheRequest.getBody() != null)
            {
              ((BodyCacher)localObject1).setDataEmitter(paramOnBodyData.bodyEmitter);
              paramOnBodyData.bodyEmitter = ((DataEmitter)localObject1);
              paramOnBodyData.state.putParcelable("body-cacher", (Parcelable)localObject1);
              paramOnBodyData.request.logd("Caching response");
              this.cacheStoreCount += 1;
              return;
            }
          }
        }
        catch (Exception paramOnBodyData)
        {
          if (((BodyCacher)localObject1).cacheRequest != null)
            ((BodyCacher)localObject1).cacheRequest.abort();
          ((BodyCacher)localObject1).cacheRequest = null;
          this.networkCount += 1;
        }
      }
    }
  }

  public void onRequestComplete(AsyncHttpClientMiddleware.OnRequestCompleteData paramOnRequestCompleteData)
  {
    BodyCacher localBodyCacher = (BodyCacher)paramOnRequestCompleteData.state.getParcelable("body-cacher");
    if (localBodyCacher == null)
      return;
    try
    {
      if (paramOnRequestCompleteData.exception != null)
      {
        localBodyCacher.abort();
        return;
      }
      localBodyCacher.commit();
      return;
    }
    catch (Exception paramOnRequestCompleteData)
    {
    }
  }

  public void setCaching(boolean paramBoolean)
  {
    this.caching = paramBoolean;
  }

  private static class BodyCacher extends FilteredDataEmitter
    implements Parcelable
  {
    ResponseCacheMiddleware.CacheRequestImpl cacheRequest;
    ByteBufferList cached;

    public void abort()
    {
      if (this.cacheRequest != null)
      {
        this.cacheRequest.abort();
        this.cacheRequest = null;
      }
    }

    public void commit()
    {
      if (this.cacheRequest != null);
      try
      {
        this.cacheRequest.getBody().close();
        return;
      }
      catch (Exception localException)
      {
      }
    }

    public int describeContents()
    {
      return 0;
    }

    public void onDataAvailable(DataEmitter paramDataEmitter, ByteBufferList paramByteBufferList)
    {
      if (this.cached != null)
      {
        Util.emitAllData(this, this.cached);
        if (this.cached.remaining() <= 0);
      }
      while (true)
      {
        return;
        this.cached = null;
        try
        {
          if (this.cacheRequest != null)
          {
            OutputStream localOutputStream = this.cacheRequest.getBody();
            if (localOutputStream != null)
            {
              int j = paramByteBufferList.size();
              int i = 0;
              while (i < j)
              {
                ByteBuffer localByteBuffer = paramByteBufferList.remove();
                localOutputStream.write(localByteBuffer.array(), localByteBuffer.arrayOffset() + localByteBuffer.position(), localByteBuffer.remaining());
                paramByteBufferList.add(localByteBuffer);
                i += 1;
              }
            }
            abort();
          }
          super.onDataAvailable(paramDataEmitter, paramByteBufferList);
          if ((this.cacheRequest == null) || (paramByteBufferList.remaining() <= 0))
            continue;
          this.cached = new ByteBufferList();
          paramByteBufferList.get(this.cached);
          return;
        }
        catch (Exception localException)
        {
          while (true)
            abort();
        }
      }
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
    }
  }

  private static class BodySpewer extends FilteredDataEmitter
  {
    boolean allowEnd;
    CacheResponse cacheResponse;
    boolean paused;
    ByteBufferList pending = new ByteBufferList();

    public boolean isPaused()
    {
      return this.paused;
    }

    protected void report(Exception paramException)
    {
      if (!this.allowEnd)
        return;
      try
      {
        this.cacheResponse.getBody().close();
        label18: super.report(paramException);
        return;
      }
      catch (Exception localException)
      {
        break label18;
      }
    }

    public void resume()
    {
      this.paused = false;
      spew();
    }

    void spew()
    {
      getServer().post(new Runnable()
      {
        public void run()
        {
          ResponseCacheMiddleware.BodySpewer.this.spewInternal();
        }
      });
    }

    void spewInternal()
    {
      if (this.pending.remaining() > 0)
      {
        Util.emitAllData(this, this.pending);
        if (this.pending.remaining() <= 0);
      }
      while (true)
      {
        return;
        try
        {
          int i;
          do
          {
            localByteBuffer.limit(i);
            this.pending.add(localByteBuffer);
            Util.emitAllData(this, this.pending);
            if (this.pending.remaining() != 0)
              break;
            ByteBuffer localByteBuffer = ByteBufferList.obtain(8192);
            i = this.cacheResponse.getBody().read(localByteBuffer.array());
          }
          while (i != -1);
          this.allowEnd = true;
          report(null);
          return;
        }
        catch (IOException localIOException)
        {
          this.allowEnd = true;
          report(localIOException);
        }
      }
    }
  }

  public static class CacheData
    implements Parcelable
  {
    ResponseHeaders cachedResponseHeaders;
    CacheResponse candidate;

    public int describeContents()
    {
      return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
    }
  }

  private final class CacheRequestImpl extends CacheRequest
  {
    private OutputStream body;
    private OutputStream cacheOut;
    private boolean done;
    private final DiskLruCache.Editor editor;

    public CacheRequestImpl(DiskLruCache.Editor arg2)
      throws IOException
    {
      final DiskLruCache.Editor localEditor;
      this.editor = localEditor;
      this.cacheOut = localEditor.newOutputStream(1);
      this.body = new FilterOutputStream(this.cacheOut)
      {
        public void close()
          throws IOException
        {
          synchronized (ResponseCacheMiddleware.this)
          {
            if (ResponseCacheMiddleware.CacheRequestImpl.this.done)
              return;
            ResponseCacheMiddleware.CacheRequestImpl.access$402(ResponseCacheMiddleware.CacheRequestImpl.this, true);
            ResponseCacheMiddleware localResponseCacheMiddleware2 = ResponseCacheMiddleware.this;
            localResponseCacheMiddleware2.writeSuccessCount += 1;
            super.close();
            localEditor.commit();
            return;
          }
        }

        public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
          throws IOException
        {
          this.out.write(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        }
      };
    }

    public void abort()
    {
      synchronized (ResponseCacheMiddleware.this)
      {
        if (this.done)
          return;
        this.done = true;
        ResponseCacheMiddleware localResponseCacheMiddleware2 = ResponseCacheMiddleware.this;
        localResponseCacheMiddleware2.writeAbortCount += 1;
      }
      try
      {
        this.cacheOut.close();
        try
        {
          label46: this.editor.abort();
          return;
        }
        catch (IOException localIOException1)
        {
          return;
        }
        localObject = finally;
        throw localObject;
      }
      catch (IOException localIOException2)
      {
        break label46;
      }
    }

    public OutputStream getBody()
      throws IOException
    {
      return this.body;
    }
  }

  private class CachedSSLSocket extends ResponseCacheMiddleware.CachedSocket
    implements AsyncSSLSocket
  {
    public CachedSSLSocket(CacheResponse arg2)
    {
      super(localCacheResponse);
    }

    public X509Certificate[] getPeerCertificates()
    {
      return null;
    }
  }

  private class CachedSocket extends DataEmitterBase
    implements AsyncSocket
  {
    CacheResponse cacheResponse;
    boolean closed;
    CompletedCallback closedCallback;
    boolean open;
    boolean paused;
    ByteBufferList pending = new ByteBufferList();

    public CachedSocket(CacheResponse arg2)
    {
      Object localObject;
      this.cacheResponse = localObject;
    }

    public void close()
    {
      this.open = false;
    }

    public void end()
    {
    }

    public CompletedCallback getClosedCallback()
    {
      return this.closedCallback;
    }

    public AsyncServer getServer()
    {
      return ResponseCacheMiddleware.this.client.getServer();
    }

    public WritableCallback getWriteableCallback()
    {
      return null;
    }

    public boolean isChunked()
    {
      return false;
    }

    public boolean isOpen()
    {
      return this.open;
    }

    public boolean isPaused()
    {
      return this.paused;
    }

    public void pause()
    {
      this.paused = true;
    }

    protected void report(Exception paramException)
    {
      super.report(paramException);
      try
      {
        this.cacheResponse.getBody().close();
        label15: if (this.closed);
        do
        {
          return;
          this.closed = true;
        }
        while (this.closedCallback == null);
        this.closedCallback.onCompleted(paramException);
        return;
      }
      catch (Exception localException)
      {
        break label15;
      }
    }

    public void resume()
    {
      this.paused = false;
      spew();
    }

    public void setClosedCallback(CompletedCallback paramCompletedCallback)
    {
      this.closedCallback = paramCompletedCallback;
    }

    public void setWriteableCallback(WritableCallback paramWritableCallback)
    {
    }

    void spew()
    {
      getServer().post(new Runnable()
      {
        public void run()
        {
          ResponseCacheMiddleware.CachedSocket.this.spewInternal();
        }
      });
    }

    void spewInternal()
    {
      if (this.pending.remaining() > 0)
      {
        Util.emitAllData(this, this.pending);
        if (this.pending.remaining() <= 0);
      }
      while (true)
      {
        return;
        try
        {
          int i;
          do
          {
            localByteBuffer.limit(i);
            this.pending.add(localByteBuffer);
            Util.emitAllData(this, this.pending);
            if (this.pending.remaining() != 0)
              break;
            ByteBuffer localByteBuffer = ByteBufferList.obtain(8192);
            i = this.cacheResponse.getBody().read(localByteBuffer.array());
          }
          while (i != -1);
          report(null);
          return;
        }
        catch (IOException localIOException)
        {
          report(localIOException);
        }
      }
    }

    public void write(ByteBufferList paramByteBufferList)
    {
      paramByteBufferList.clear();
    }

    public void write(ByteBuffer paramByteBuffer)
    {
      paramByteBuffer.limit(paramByteBuffer.position());
    }
  }

  private static final class Entry
  {
    private final String cipherSuite;
    private final Certificate[] localCertificates;
    private final Certificate[] peerCertificates;
    private final String requestMethod;
    private final RawHeaders responseHeaders;
    private final String uri;
    private final RawHeaders varyHeaders;

    public Entry(InputStream paramInputStream)
      throws IOException
    {
      try
      {
        StrictLineReader localStrictLineReader = new StrictLineReader(paramInputStream, Charsets.US_ASCII);
        this.uri = localStrictLineReader.readLine();
        this.requestMethod = localStrictLineReader.readLine();
        this.varyHeaders = new RawHeaders();
        int k = localStrictLineReader.readInt();
        int i = 0;
        while (i < k)
        {
          this.varyHeaders.addLine(localStrictLineReader.readLine());
          i += 1;
        }
        this.responseHeaders = new RawHeaders();
        this.responseHeaders.setStatusLine(localStrictLineReader.readLine());
        k = localStrictLineReader.readInt();
        i = j;
        while (i < k)
        {
          this.responseHeaders.addLine(localStrictLineReader.readLine());
          i += 1;
        }
        this.cipherSuite = null;
        this.peerCertificates = null;
        this.localCertificates = null;
        return;
      }
      finally
      {
        paramInputStream.close();
      }
    }

    public Entry(URI paramURI, RawHeaders paramRawHeaders, AsyncHttpRequest paramAsyncHttpRequest, ResponseHeaders paramResponseHeaders)
    {
      this.uri = paramURI.toString();
      this.varyHeaders = paramRawHeaders;
      this.requestMethod = paramAsyncHttpRequest.getMethod();
      this.responseHeaders = paramResponseHeaders.getHeaders();
      this.cipherSuite = null;
      this.peerCertificates = null;
      this.localCertificates = null;
    }

    private boolean isHttps()
    {
      return this.uri.startsWith("https://");
    }

    private Certificate[] readCertArray(StrictLineReader paramStrictLineReader)
      throws IOException
    {
      int i = 0;
      int j = paramStrictLineReader.readInt();
      Object localObject;
      if (j == -1)
        localObject = null;
      while (true)
      {
        return localObject;
        try
        {
          CertificateFactory localCertificateFactory = CertificateFactory.getInstance("X.509");
          Certificate[] arrayOfCertificate = new Certificate[j];
          while (true)
          {
            localObject = arrayOfCertificate;
            if (i >= arrayOfCertificate.length)
              break;
            arrayOfCertificate[i] = localCertificateFactory.generateCertificate(new ByteArrayInputStream(Base64.decode(paramStrictLineReader.readLine(), 0)));
            i += 1;
          }
        }
        catch (CertificateException paramStrictLineReader)
        {
        }
      }
      throw new IOException(paramStrictLineReader.getMessage());
    }

    private void writeCertArray(Writer paramWriter, Certificate[] paramArrayOfCertificate)
      throws IOException
    {
      int i = 0;
      if (paramArrayOfCertificate == null)
        paramWriter.write("-1\n");
      while (true)
      {
        return;
        try
        {
          paramWriter.write(Integer.toString(paramArrayOfCertificate.length) + '\n');
          int j = paramArrayOfCertificate.length;
          while (i < j)
          {
            String str = Base64.encodeToString(paramArrayOfCertificate[i].getEncoded(), 0);
            paramWriter.write(str + '\n');
            i += 1;
          }
        }
        catch (CertificateEncodingException paramWriter)
        {
        }
      }
      throw new IOException(paramWriter.getMessage());
    }

    public boolean matches(URI paramURI, String paramString, Map<String, List<String>> paramMap)
    {
      return (this.uri.equals(paramURI.toString())) && (this.requestMethod.equals(paramString)) && (new ResponseHeaders(paramURI, this.responseHeaders).varyMatches(this.varyHeaders.toMultimap(), paramMap));
    }

    public void writeTo(DiskLruCache.Editor paramEditor)
      throws IOException
    {
      int j = 0;
      paramEditor = new BufferedWriter(new OutputStreamWriter(paramEditor.newOutputStream(0), Charsets.UTF_8));
      paramEditor.write(this.uri + '\n');
      paramEditor.write(this.requestMethod + '\n');
      paramEditor.write(Integer.toString(this.varyHeaders.length()) + '\n');
      int i = 0;
      while (i < this.varyHeaders.length())
      {
        paramEditor.write(this.varyHeaders.getFieldName(i) + ": " + this.varyHeaders.getValue(i) + '\n');
        i += 1;
      }
      paramEditor.write(this.responseHeaders.getStatusLine() + '\n');
      paramEditor.write(Integer.toString(this.responseHeaders.length()) + '\n');
      i = j;
      while (i < this.responseHeaders.length())
      {
        paramEditor.write(this.responseHeaders.getFieldName(i) + ": " + this.responseHeaders.getValue(i) + '\n');
        i += 1;
      }
      if (isHttps())
      {
        paramEditor.write(10);
        paramEditor.write(this.cipherSuite + '\n');
        writeCertArray(paramEditor, this.peerCertificates);
        writeCertArray(paramEditor, this.localCertificates);
      }
      paramEditor.close();
    }
  }

  static class EntryCacheResponse extends CacheResponse
  {
    private final ResponseCacheMiddleware.Entry entry;
    private final InputStream in;
    private final DiskLruCache.Snapshot snapshot;

    public EntryCacheResponse(ResponseCacheMiddleware.Entry paramEntry, DiskLruCache.Snapshot paramSnapshot)
    {
      this.entry = paramEntry;
      this.snapshot = paramSnapshot;
      this.in = ResponseCacheMiddleware.newBodyInputStream(paramSnapshot);
    }

    public InputStream getBody()
    {
      return this.in;
    }

    public Map<String, List<String>> getHeaders()
    {
      return this.entry.responseHeaders.toMultimap();
    }
  }

  static class EntrySecureCacheResponse extends SecureCacheResponse
  {
    private final ResponseCacheMiddleware.Entry entry;
    private final InputStream in;
    private final DiskLruCache.Snapshot snapshot;

    public EntrySecureCacheResponse(ResponseCacheMiddleware.Entry paramEntry, DiskLruCache.Snapshot paramSnapshot)
    {
      this.entry = paramEntry;
      this.snapshot = paramSnapshot;
      this.in = ResponseCacheMiddleware.newBodyInputStream(paramSnapshot);
    }

    public InputStream getBody()
    {
      return this.in;
    }

    public String getCipherSuite()
    {
      return this.entry.cipherSuite;
    }

    public Map<String, List<String>> getHeaders()
    {
      return this.entry.responseHeaders.toMultimap();
    }

    public List<Certificate> getLocalCertificateChain()
    {
      if ((this.entry.localCertificates == null) || (this.entry.localCertificates.length == 0))
        return null;
      return Arrays.asList((Object[])this.entry.localCertificates.clone());
    }

    public Principal getLocalPrincipal()
    {
      if ((this.entry.localCertificates == null) || (this.entry.localCertificates.length == 0))
        return null;
      return ((X509Certificate)this.entry.localCertificates[0]).getSubjectX500Principal();
    }

    public Principal getPeerPrincipal()
      throws SSLPeerUnverifiedException
    {
      if ((this.entry.peerCertificates == null) || (this.entry.peerCertificates.length == 0))
        throw new SSLPeerUnverifiedException(null);
      return ((X509Certificate)this.entry.peerCertificates[0]).getSubjectX500Principal();
    }

    public List<Certificate> getServerCertificateChain()
      throws SSLPeerUnverifiedException
    {
      if ((this.entry.peerCertificates == null) || (this.entry.peerCertificates.length == 0))
        throw new SSLPeerUnverifiedException(null);
      return Arrays.asList((Object[])this.entry.peerCertificates.clone());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.ResponseCacheMiddleware
 * JD-Core Version:    0.6.2
 */