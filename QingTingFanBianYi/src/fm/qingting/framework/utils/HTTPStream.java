package fm.qingting.framework.utils;

public class HTTPStream
{
  // ERROR //
  public java.io.InputStream getStream(java.lang.String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +12 -> 13
    //   4: aload_1
    //   5: ldc 23
    //   7: invokevirtual 29	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   10: ifeq +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: new 31	org/apache/http/client/methods/HttpGet
    //   18: dup
    //   19: aload_1
    //   20: invokespecial 34	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   23: astore_2
    //   24: new 36	org/apache/http/params/BasicHttpParams
    //   27: dup
    //   28: invokespecial 37	org/apache/http/params/BasicHttpParams:<init>	()V
    //   31: astore_1
    //   32: aload_1
    //   33: sipush 5000
    //   36: invokestatic 43	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   39: aload_1
    //   40: sipush 5000
    //   43: invokestatic 46	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   46: new 48	org/apache/http/impl/client/DefaultHttpClient
    //   49: dup
    //   50: aload_1
    //   51: invokespecial 51	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   54: astore_3
    //   55: aconst_null
    //   56: astore_1
    //   57: aload_3
    //   58: aload_2
    //   59: invokevirtual 55	org/apache/http/impl/client/DefaultHttpClient:execute	(Lorg/apache/http/client/methods/HttpUriRequest;)Lorg/apache/http/HttpResponse;
    //   62: astore_2
    //   63: aload_2
    //   64: astore_1
    //   65: aload_1
    //   66: ifnull -53 -> 13
    //   69: aload_1
    //   70: invokeinterface 61 1 0
    //   75: astore_1
    //   76: aload_1
    //   77: invokeinterface 67 1 0
    //   82: astore_1
    //   83: aload_1
    //   84: areturn
    //   85: astore_2
    //   86: aload_2
    //   87: invokevirtual 70	org/apache/http/client/ClientProtocolException:printStackTrace	()V
    //   90: goto -25 -> 65
    //   93: astore_2
    //   94: aload_2
    //   95: invokevirtual 71	java/net/SocketTimeoutException:printStackTrace	()V
    //   98: goto -33 -> 65
    //   101: astore_2
    //   102: aload_2
    //   103: invokevirtual 72	java/io/IOException:printStackTrace	()V
    //   106: goto -41 -> 65
    //   109: astore_1
    //   110: aload_1
    //   111: invokevirtual 73	java/lang/IllegalStateException:printStackTrace	()V
    //   114: aconst_null
    //   115: areturn
    //   116: astore_1
    //   117: aload_1
    //   118: invokevirtual 72	java/io/IOException:printStackTrace	()V
    //   121: aconst_null
    //   122: areturn
    //   123: astore_2
    //   124: goto -59 -> 65
    //
    // Exception table:
    //   from	to	target	type
    //   57	63	85	org/apache/http/client/ClientProtocolException
    //   57	63	93	java/net/SocketTimeoutException
    //   57	63	101	java/io/IOException
    //   76	83	109	java/lang/IllegalStateException
    //   76	83	116	java/io/IOException
    //   57	63	123	java/lang/OutOfMemoryError
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.HTTPStream
 * JD-Core Version:    0.6.2
 */