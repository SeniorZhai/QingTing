package com.android.volley;

import android.annotation.SuppressLint;
import java.util.concurrent.BlockingQueue;

@SuppressLint({"NewApi"})
public class NetworkDispatcher extends Thread
{
  private final Cache mCache;
  private final ResponseDelivery mDelivery;
  private final Network mNetwork;
  private final BlockingQueue<Request> mQueue;
  private volatile boolean mQuit = false;

  public NetworkDispatcher(BlockingQueue<Request> paramBlockingQueue, Network paramNetwork, Cache paramCache, ResponseDelivery paramResponseDelivery)
  {
    this.mQueue = paramBlockingQueue;
    this.mNetwork = paramNetwork;
    this.mCache = paramCache;
    this.mDelivery = paramResponseDelivery;
  }

  private void parseAndDeliverNetworkError(Request<?> paramRequest, VolleyError paramVolleyError)
  {
    paramVolleyError = paramRequest.parseNetworkError(paramVolleyError);
    this.mDelivery.postError(paramRequest, paramVolleyError);
  }

  public void quit()
  {
    this.mQuit = true;
    interrupt();
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: bipush 10
    //   2: invokestatic 67	android/os/Process:setThreadPriority	(I)V
    //   5: aload_0
    //   6: getfield 27	com/android/volley/NetworkDispatcher:mQueue	Ljava/util/concurrent/BlockingQueue;
    //   9: invokeinterface 73 1 0
    //   14: checkcast 40	com/android/volley/Request
    //   17: astore_1
    //   18: aload_1
    //   19: ldc 75
    //   21: invokevirtual 79	com/android/volley/Request:addMarker	(Ljava/lang/String;)V
    //   24: aload_1
    //   25: invokevirtual 83	com/android/volley/Request:isCanceled	()Z
    //   28: ifeq +44 -> 72
    //   31: aload_1
    //   32: ldc 85
    //   34: invokevirtual 88	com/android/volley/Request:finish	(Ljava/lang/String;)V
    //   37: goto -32 -> 5
    //   40: astore_2
    //   41: aload_0
    //   42: aload_1
    //   43: aload_2
    //   44: invokespecial 90	com/android/volley/NetworkDispatcher:parseAndDeliverNetworkError	(Lcom/android/volley/Request;Lcom/android/volley/VolleyError;)V
    //   47: goto -42 -> 5
    //   50: astore_1
    //   51: aload_0
    //   52: getfield 25	com/android/volley/NetworkDispatcher:mQuit	Z
    //   55: ifeq +4 -> 59
    //   58: return
    //   59: ldc2_w 91
    //   62: invokestatic 96	java/lang/Thread:sleep	(J)V
    //   65: goto -60 -> 5
    //   68: astore_1
    //   69: goto -64 -> 5
    //   72: getstatic 102	android/os/Build$VERSION:SDK_INT	I
    //   75: bipush 14
    //   77: if_icmplt +10 -> 87
    //   80: aload_1
    //   81: invokevirtual 106	com/android/volley/Request:getTrafficStatsTag	()I
    //   84: invokestatic 111	android/net/TrafficStats:setThreadStatsTag	(I)V
    //   87: aload_0
    //   88: getfield 29	com/android/volley/NetworkDispatcher:mNetwork	Lcom/android/volley/Network;
    //   91: aload_1
    //   92: invokeinterface 117 2 0
    //   97: astore_2
    //   98: aload_1
    //   99: ldc 119
    //   101: invokevirtual 79	com/android/volley/Request:addMarker	(Ljava/lang/String;)V
    //   104: aload_2
    //   105: getfield 124	com/android/volley/NetworkResponse:notModified	Z
    //   108: ifeq +58 -> 166
    //   111: aload_1
    //   112: invokevirtual 127	com/android/volley/Request:hasHadResponseDelivered	()Z
    //   115: ifeq +51 -> 166
    //   118: aload_1
    //   119: ldc 129
    //   121: invokevirtual 88	com/android/volley/Request:finish	(Ljava/lang/String;)V
    //   124: goto -119 -> 5
    //   127: astore_2
    //   128: aload_2
    //   129: ldc 131
    //   131: iconst_1
    //   132: anewarray 133	java/lang/Object
    //   135: dup
    //   136: iconst_0
    //   137: aload_2
    //   138: invokevirtual 137	java/lang/Exception:toString	()Ljava/lang/String;
    //   141: aastore
    //   142: invokestatic 143	com/android/volley/VolleyLog:w	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   145: aload_0
    //   146: getfield 33	com/android/volley/NetworkDispatcher:mDelivery	Lcom/android/volley/ResponseDelivery;
    //   149: aload_1
    //   150: new 59	com/android/volley/VolleyError
    //   153: dup
    //   154: aload_2
    //   155: invokespecial 146	com/android/volley/VolleyError:<init>	(Ljava/lang/Throwable;)V
    //   158: invokeinterface 49 3 0
    //   163: goto -158 -> 5
    //   166: aload_1
    //   167: aload_2
    //   168: invokevirtual 150	com/android/volley/Request:parseNetworkResponse	(Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response;
    //   171: astore_2
    //   172: aload_1
    //   173: ldc 152
    //   175: invokevirtual 79	com/android/volley/Request:addMarker	(Ljava/lang/String;)V
    //   178: aload_1
    //   179: invokevirtual 155	com/android/volley/Request:shouldCache	()Z
    //   182: ifeq +33 -> 215
    //   185: aload_2
    //   186: getfield 161	com/android/volley/Response:cacheEntry	Lcom/android/volley/Cache$Entry;
    //   189: ifnull +26 -> 215
    //   192: aload_0
    //   193: getfield 31	com/android/volley/NetworkDispatcher:mCache	Lcom/android/volley/Cache;
    //   196: aload_1
    //   197: invokevirtual 164	com/android/volley/Request:getCacheKey	()Ljava/lang/String;
    //   200: aload_2
    //   201: getfield 161	com/android/volley/Response:cacheEntry	Lcom/android/volley/Cache$Entry;
    //   204: invokeinterface 170 3 0
    //   209: aload_1
    //   210: ldc 172
    //   212: invokevirtual 79	com/android/volley/Request:addMarker	(Ljava/lang/String;)V
    //   215: aload_1
    //   216: invokevirtual 175	com/android/volley/Request:markDelivered	()V
    //   219: aload_0
    //   220: getfield 33	com/android/volley/NetworkDispatcher:mDelivery	Lcom/android/volley/ResponseDelivery;
    //   223: aload_1
    //   224: aload_2
    //   225: invokeinterface 179 3 0
    //   230: goto -225 -> 5
    //
    // Exception table:
    //   from	to	target	type
    //   18	37	40	com/android/volley/VolleyError
    //   72	87	40	com/android/volley/VolleyError
    //   87	124	40	com/android/volley/VolleyError
    //   166	215	40	com/android/volley/VolleyError
    //   215	230	40	com/android/volley/VolleyError
    //   5	18	50	java/lang/InterruptedException
    //   59	65	68	java/lang/Exception
    //   18	37	127	java/lang/Exception
    //   72	87	127	java/lang/Exception
    //   87	124	127	java/lang/Exception
    //   166	215	127	java/lang/Exception
    //   215	230	127	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.android.volley.NetworkDispatcher
 * JD-Core Version:    0.6.2
 */