package com.taobao.munion.base.volley.a;

import android.os.SystemClock;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.b.a;
import com.taobao.munion.base.volley.f;
import com.taobao.munion.base.volley.l;
import com.taobao.munion.base.volley.p;
import com.taobao.munion.base.volley.q;
import com.taobao.munion.base.volley.s;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.impl.cookie.DateUtils;

public class a
  implements f
{
  protected static final boolean a = Log.DEBUG;
  private static int d = 3000;
  private static int e = 4096;
  protected final g b;
  protected final b c;

  public a(g paramg)
  {
    this(paramg, new b(e));
  }

  public a(g paramg, b paramb)
  {
    this.b = paramg;
    this.c = paramb;
  }

  private static Map<String, String> a(Header[] paramArrayOfHeader)
  {
    HashMap localHashMap = new HashMap();
    int i = 0;
    while (i < paramArrayOfHeader.length)
    {
      localHashMap.put(paramArrayOfHeader[i].getName(), paramArrayOfHeader[i].getValue());
      i += 1;
    }
    return localHashMap;
  }

  private void a(long paramLong, l<?> paraml, byte[] paramArrayOfByte, StatusLine paramStatusLine)
  {
    if ((a) || (paramLong > d))
      if (paramArrayOfByte == null)
        break label82;
    label82: for (paramArrayOfByte = Integer.valueOf(paramArrayOfByte.length); ; paramArrayOfByte = "null")
    {
      Log.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[] { paraml, Long.valueOf(paramLong), paramArrayOfByte, Integer.valueOf(paramStatusLine.getStatusCode()), Integer.valueOf(paraml.w().b()) });
      return;
    }
  }

  private static void a(String paramString, l<?> paraml, s params)
    throws s
  {
    p localp = paraml.w();
    int i = paraml.v();
    try
    {
      localp.a(params);
      paraml.a(String.format("%s-retry [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      return;
    }
    catch (s params)
    {
      paraml.a(String.format("%s-timeout-giveup [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
    }
    throw params;
  }

  private void a(Map<String, String> paramMap, b.a parama)
  {
    if (parama == null);
    do
    {
      return;
      if (parama.b != null)
        paramMap.put("If-None-Match", parama.b);
    }
    while (parama.c <= 0L);
    paramMap.put("If-Modified-Since", DateUtils.formatDate(new Date(parama.c)));
  }

  private byte[] a(HttpEntity paramHttpEntity)
    throws IOException, q
  {
    j localj = new j(this.c, (int)paramHttpEntity.getContentLength());
    Object localObject2 = null;
    Object localObject1 = localObject2;
    Object localObject4;
    try
    {
      localObject4 = paramHttpEntity.getContent();
      if (localObject4 == null)
      {
        localObject1 = localObject2;
        throw new q();
      }
    }
    finally
    {
    }
    try
    {
      paramHttpEntity.consumeContent();
      this.c.a((byte[])localObject1);
      localj.close();
      throw localObject3;
      localObject1 = localObject3;
      byte[] arrayOfByte = this.c.a(1024);
      while (true)
      {
        localObject1 = arrayOfByte;
        int i = ((InputStream)localObject4).read(arrayOfByte);
        if (i == -1)
          break;
        localObject1 = arrayOfByte;
        localj.write(arrayOfByte, 0, i);
      }
      localObject1 = arrayOfByte;
      localObject4 = localj.toByteArray();
      try
      {
        paramHttpEntity.consumeContent();
        this.c.a(arrayOfByte);
        localj.close();
        return localObject4;
      }
      catch (IOException paramHttpEntity)
      {
        while (true)
          Log.v("Error occured when calling consumingContent", new Object[0]);
      }
    }
    catch (IOException paramHttpEntity)
    {
      while (true)
        Log.v("Error occured when calling consumingContent", new Object[0]);
    }
  }

  // ERROR //
  public com.taobao.munion.base.volley.i a(l<?> paraml)
    throws s
  {
    // Byte code:
    //   0: invokestatic 214	android/os/SystemClock:elapsedRealtime	()J
    //   3: lstore 10
    //   5: aconst_null
    //   6: astore_3
    //   7: aconst_null
    //   8: astore 7
    //   10: aconst_null
    //   11: astore_2
    //   12: new 48	java/util/HashMap
    //   15: dup
    //   16: invokespecial 49	java/util/HashMap:<init>	()V
    //   19: astore 6
    //   21: new 48	java/util/HashMap
    //   24: dup
    //   25: invokespecial 49	java/util/HashMap:<init>	()V
    //   28: astore 4
    //   30: aload_0
    //   31: aload 4
    //   33: aload_1
    //   34: invokevirtual 218	com/taobao/munion/base/volley/l:h	()Lcom/taobao/munion/base/volley/b$a;
    //   37: invokespecial 220	com/taobao/munion/base/volley/a/a:a	(Ljava/util/Map;Lcom/taobao/munion/base/volley/b$a;)V
    //   40: aload_0
    //   41: getfield 43	com/taobao/munion/base/volley/a/a:b	Lcom/taobao/munion/base/volley/a/g;
    //   44: aload_1
    //   45: aload 4
    //   47: invokeinterface 225 3 0
    //   52: astore 4
    //   54: aload 4
    //   56: invokeinterface 231 1 0
    //   61: astore 8
    //   63: aload 8
    //   65: invokeinterface 86 1 0
    //   70: istore 9
    //   72: aload 4
    //   74: invokeinterface 235 1 0
    //   79: invokestatic 237	com/taobao/munion/base/volley/a/a:a	([Lorg/apache/http/Header;)Ljava/util/Map;
    //   82: astore 5
    //   84: iload 9
    //   86: sipush 304
    //   89: if_icmpne +47 -> 136
    //   92: aload 7
    //   94: astore_2
    //   95: aload_1
    //   96: invokevirtual 218	com/taobao/munion/base/volley/l:h	()Lcom/taobao/munion/base/volley/b$a;
    //   99: ifnonnull +23 -> 122
    //   102: aconst_null
    //   103: astore_3
    //   104: aload 7
    //   106: astore_2
    //   107: new 239	com/taobao/munion/base/volley/i
    //   110: dup
    //   111: sipush 304
    //   114: aload_3
    //   115: aload 5
    //   117: iconst_1
    //   118: invokespecial 242	com/taobao/munion/base/volley/i:<init>	(I[BLjava/util/Map;Z)V
    //   121: areturn
    //   122: aload 7
    //   124: astore_2
    //   125: aload_1
    //   126: invokevirtual 218	com/taobao/munion/base/volley/l:h	()Lcom/taobao/munion/base/volley/b$a;
    //   129: getfield 245	com/taobao/munion/base/volley/b$a:a	[B
    //   132: astore_3
    //   133: goto -29 -> 104
    //   136: aload 7
    //   138: astore_2
    //   139: aload 4
    //   141: invokeinterface 249 1 0
    //   146: ifnull +86 -> 232
    //   149: aload 7
    //   151: astore_2
    //   152: aload_0
    //   153: aload 4
    //   155: invokeinterface 249 1 0
    //   160: invokespecial 251	com/taobao/munion/base/volley/a/a:a	(Lorg/apache/http/HttpEntity;)[B
    //   163: astore_3
    //   164: aload_3
    //   165: astore_2
    //   166: aload_0
    //   167: invokestatic 214	android/os/SystemClock:elapsedRealtime	()J
    //   170: lload 10
    //   172: lsub
    //   173: aload_1
    //   174: aload_3
    //   175: aload 8
    //   177: invokespecial 253	com/taobao/munion/base/volley/a/a:a	(JLcom/taobao/munion/base/volley/l;[BLorg/apache/http/StatusLine;)V
    //   180: iload 9
    //   182: sipush 200
    //   185: if_icmplt +11 -> 196
    //   188: iload 9
    //   190: sipush 299
    //   193: if_icmple +49 -> 242
    //   196: iload 9
    //   198: sipush 302
    //   201: if_icmpeq +41 -> 242
    //   204: aload_3
    //   205: astore_2
    //   206: new 154	java/io/IOException
    //   209: dup
    //   210: invokespecial 254	java/io/IOException:<init>	()V
    //   213: athrow
    //   214: astore_2
    //   215: ldc_w 256
    //   218: aload_1
    //   219: new 258	com/taobao/munion/base/volley/r
    //   222: dup
    //   223: invokespecial 259	com/taobao/munion/base/volley/r:<init>	()V
    //   226: invokestatic 261	com/taobao/munion/base/volley/a/a:a	(Ljava/lang/String;Lcom/taobao/munion/base/volley/l;Lcom/taobao/munion/base/volley/s;)V
    //   229: goto -224 -> 5
    //   232: aload 7
    //   234: astore_2
    //   235: iconst_0
    //   236: newarray byte
    //   238: astore_3
    //   239: goto -75 -> 164
    //   242: aload_3
    //   243: astore_2
    //   244: new 239	com/taobao/munion/base/volley/i
    //   247: dup
    //   248: iload 9
    //   250: aload_3
    //   251: aload 5
    //   253: iconst_0
    //   254: invokespecial 242	com/taobao/munion/base/volley/i:<init>	(I[BLjava/util/Map;Z)V
    //   257: astore_3
    //   258: aload_3
    //   259: areturn
    //   260: astore_2
    //   261: ldc_w 263
    //   264: aload_1
    //   265: new 258	com/taobao/munion/base/volley/r
    //   268: dup
    //   269: invokespecial 259	com/taobao/munion/base/volley/r:<init>	()V
    //   272: invokestatic 261	com/taobao/munion/base/volley/a/a:a	(Ljava/lang/String;Lcom/taobao/munion/base/volley/l;Lcom/taobao/munion/base/volley/s;)V
    //   275: goto -270 -> 5
    //   278: astore_2
    //   279: new 265	java/lang/RuntimeException
    //   282: dup
    //   283: new 267	java/lang/StringBuilder
    //   286: dup
    //   287: invokespecial 268	java/lang/StringBuilder:<init>	()V
    //   290: ldc_w 270
    //   293: invokevirtual 274	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: aload_1
    //   297: invokevirtual 277	com/taobao/munion/base/volley/l:f	()Ljava/lang/String;
    //   300: invokevirtual 274	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   303: invokevirtual 280	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   306: aload_2
    //   307: invokespecial 283	java/lang/RuntimeException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   310: athrow
    //   311: astore 7
    //   313: aload_3
    //   314: astore 4
    //   316: aload 6
    //   318: astore 5
    //   320: aload 7
    //   322: astore_3
    //   323: aload 4
    //   325: ifnull +94 -> 419
    //   328: aload 4
    //   330: invokeinterface 231 1 0
    //   335: invokeinterface 86 1 0
    //   340: istore 9
    //   342: ldc_w 285
    //   345: iconst_2
    //   346: anewarray 4	java/lang/Object
    //   349: dup
    //   350: iconst_0
    //   351: iload 9
    //   353: invokestatic 73	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   356: aastore
    //   357: dup
    //   358: iconst_1
    //   359: aload_1
    //   360: invokevirtual 277	com/taobao/munion/base/volley/l:f	()Ljava/lang/String;
    //   363: aastore
    //   364: invokestatic 287	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   367: aload_2
    //   368: ifnull +69 -> 437
    //   371: new 239	com/taobao/munion/base/volley/i
    //   374: dup
    //   375: iload 9
    //   377: aload_2
    //   378: aload 5
    //   380: iconst_0
    //   381: invokespecial 242	com/taobao/munion/base/volley/i:<init>	(I[BLjava/util/Map;Z)V
    //   384: astore_2
    //   385: iload 9
    //   387: sipush 401
    //   390: if_icmpeq +11 -> 401
    //   393: iload 9
    //   395: sipush 403
    //   398: if_icmpne +30 -> 428
    //   401: ldc_w 289
    //   404: aload_1
    //   405: new 291	com/taobao/munion/base/volley/a
    //   408: dup
    //   409: aload_2
    //   410: invokespecial 294	com/taobao/munion/base/volley/a:<init>	(Lcom/taobao/munion/base/volley/i;)V
    //   413: invokestatic 261	com/taobao/munion/base/volley/a/a:a	(Ljava/lang/String;Lcom/taobao/munion/base/volley/l;Lcom/taobao/munion/base/volley/s;)V
    //   416: goto -411 -> 5
    //   419: new 296	com/taobao/munion/base/volley/j
    //   422: dup
    //   423: aload_3
    //   424: invokespecial 299	com/taobao/munion/base/volley/j:<init>	(Ljava/lang/Throwable;)V
    //   427: athrow
    //   428: new 156	com/taobao/munion/base/volley/q
    //   431: dup
    //   432: aload_2
    //   433: invokespecial 300	com/taobao/munion/base/volley/q:<init>	(Lcom/taobao/munion/base/volley/i;)V
    //   436: athrow
    //   437: new 302	com/taobao/munion/base/volley/h
    //   440: dup
    //   441: aconst_null
    //   442: invokespecial 303	com/taobao/munion/base/volley/h:<init>	(Lcom/taobao/munion/base/volley/i;)V
    //   445: athrow
    //   446: astore_3
    //   447: aload 6
    //   449: astore 5
    //   451: goto -128 -> 323
    //   454: astore_3
    //   455: goto -132 -> 323
    //
    // Exception table:
    //   from	to	target	type
    //   21	54	214	java/net/SocketTimeoutException
    //   54	84	214	java/net/SocketTimeoutException
    //   95	102	214	java/net/SocketTimeoutException
    //   107	122	214	java/net/SocketTimeoutException
    //   125	133	214	java/net/SocketTimeoutException
    //   139	149	214	java/net/SocketTimeoutException
    //   152	164	214	java/net/SocketTimeoutException
    //   166	180	214	java/net/SocketTimeoutException
    //   206	214	214	java/net/SocketTimeoutException
    //   235	239	214	java/net/SocketTimeoutException
    //   244	258	214	java/net/SocketTimeoutException
    //   21	54	260	org/apache/http/conn/ConnectTimeoutException
    //   54	84	260	org/apache/http/conn/ConnectTimeoutException
    //   95	102	260	org/apache/http/conn/ConnectTimeoutException
    //   107	122	260	org/apache/http/conn/ConnectTimeoutException
    //   125	133	260	org/apache/http/conn/ConnectTimeoutException
    //   139	149	260	org/apache/http/conn/ConnectTimeoutException
    //   152	164	260	org/apache/http/conn/ConnectTimeoutException
    //   166	180	260	org/apache/http/conn/ConnectTimeoutException
    //   206	214	260	org/apache/http/conn/ConnectTimeoutException
    //   235	239	260	org/apache/http/conn/ConnectTimeoutException
    //   244	258	260	org/apache/http/conn/ConnectTimeoutException
    //   21	54	278	java/net/MalformedURLException
    //   54	84	278	java/net/MalformedURLException
    //   95	102	278	java/net/MalformedURLException
    //   107	122	278	java/net/MalformedURLException
    //   125	133	278	java/net/MalformedURLException
    //   139	149	278	java/net/MalformedURLException
    //   152	164	278	java/net/MalformedURLException
    //   166	180	278	java/net/MalformedURLException
    //   206	214	278	java/net/MalformedURLException
    //   235	239	278	java/net/MalformedURLException
    //   244	258	278	java/net/MalformedURLException
    //   21	54	311	java/io/IOException
    //   54	84	446	java/io/IOException
    //   95	102	454	java/io/IOException
    //   107	122	454	java/io/IOException
    //   125	133	454	java/io/IOException
    //   139	149	454	java/io/IOException
    //   152	164	454	java/io/IOException
    //   166	180	454	java/io/IOException
    //   206	214	454	java/io/IOException
    //   235	239	454	java/io/IOException
    //   244	258	454	java/io/IOException
  }

  protected void a(String paramString1, String paramString2, long paramLong)
  {
    Log.v("HTTP ERROR(%s) %d ms to fetch %s", new Object[] { paramString1, Long.valueOf(SystemClock.elapsedRealtime() - paramLong), paramString2 });
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.volley.a.a
 * JD-Core Version:    0.6.2
 */