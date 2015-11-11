package com.intowow.sdk.e;

import android.content.Context;
import com.intowow.sdk.j.k;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class b
{
  private long a = 0L;
  private long b = 0L;
  private PriorityQueue<a> c = null;
  private List<a> d = null;
  private boolean e = false;
  private ExecutorService f = null;
  private long g = 0L;
  private Context h = null;

  public b(Context paramContext, long paramLong1, long paramLong2)
  {
    this.h = paramContext;
    this.c = new PriorityQueue(10, new a.a());
    this.d = new ArrayList();
    this.f = Executors.newSingleThreadExecutor();
    this.g = k.a(this.h).d();
    this.a = paramLong1;
    this.b = paramLong2;
  }

  public static String a(int paramInt)
  {
    switch (paramInt)
    {
    case 4:
    default:
      return "UNKNOWN";
    case 0:
      return "SOCKET ERROR";
    case 1:
      return "FILE NOT FOUND";
    case 2:
      return "MALFORMED URL";
    case 3:
      return "NOT AVAILABLE SPACE";
    case 5:
      return "CANCELED";
    case 6:
    }
    return "CHECKSUM MISMATCH";
  }

  private a e()
  {
    try
    {
      a locala = (a)this.c.poll();
      return locala;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a()
  {
    this.g = k.a(this.h).d();
  }

  public void a(long paramLong)
  {
    this.a = paramLong;
  }

  public void a(a parama)
  {
    try
    {
      if (this.e)
        this.d.add(parama);
      while (true)
      {
        return;
        this.c.add(parama);
        this.f.execute(new a());
      }
    }
    finally
    {
    }
    throw parama;
  }

  public void a(PriorityQueue<a> paramPriorityQueue)
  {
    if (paramPriorityQueue == null);
    while (true)
    {
      return;
      try
      {
        this.c.clear();
        while (!paramPriorityQueue.isEmpty())
        {
          this.c.add((a)paramPriorityQueue.poll());
          this.f.execute(new a());
        }
      }
      finally
      {
      }
    }
    throw paramPriorityQueue;
  }

  public Set<Integer> b()
  {
    try
    {
      Object[] arrayOfObject = this.c.toArray();
      HashSet localHashSet = new HashSet();
      int j = arrayOfObject.length;
      int i = 0;
      while (true)
      {
        if (i >= j)
          return localHashSet;
        localHashSet.add(Integer.valueOf(((a)arrayOfObject[i]).b()));
        i += 1;
      }
    }
    finally
    {
    }
  }

  public void b(long paramLong)
  {
    this.b = paramLong;
  }

  public void c()
  {
    try
    {
      boolean bool = this.e;
      if (bool);
      while (true)
      {
        return;
        this.e = true;
        while (!this.c.isEmpty())
          this.d.add((a)this.c.poll());
      }
    }
    finally
    {
    }
  }

  public void d()
  {
    while (true)
    {
      try
      {
        boolean bool = this.e;
        if (!bool)
          return;
        this.e = false;
        Iterator localIterator = this.d.iterator();
        if (!localIterator.hasNext())
        {
          this.d.clear();
          continue;
        }
      }
      finally
      {
      }
      a locala = (a)localObject.next();
      this.c.add(locala);
    }
  }

  class a
    implements Runnable
  {
    a()
    {
    }

    private void a(InputStream paramInputStream, RandomAccessFile paramRandomAccessFile, HttpURLConnection paramHttpURLConnection)
    {
      if (paramInputStream != null);
      try
      {
        paramInputStream.close();
        if (paramRandomAccessFile == null);
      }
      catch (IOException paramInputStream)
      {
        try
        {
          paramRandomAccessFile.close();
          label16: if (paramHttpURLConnection != null)
            paramHttpURLConnection.disconnect();
          return;
          paramInputStream = paramInputStream;
        }
        catch (IOException paramInputStream)
        {
          break label16;
        }
      }
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 14	com/intowow/sdk/e/b$a:a	Lcom/intowow/sdk/e/b;
      //   4: invokestatic 44	com/intowow/sdk/e/b:a	(Lcom/intowow/sdk/e/b;)Lcom/intowow/sdk/e/a;
      //   7: astore 7
      //   9: aload 7
      //   11: ifnonnull +4 -> 15
      //   14: return
      //   15: aload 7
      //   17: invokevirtual 50	com/intowow/sdk/e/a:j	()Lcom/intowow/sdk/e/b$b;
      //   20: astore 8
      //   22: aload 8
      //   24: ifnull +12 -> 36
      //   27: aload 8
      //   29: aload 7
      //   31: invokeinterface 55 2 0
      //   36: aconst_null
      //   37: astore 4
      //   39: sipush 8192
      //   42: newarray byte
      //   44: astore 9
      //   46: aconst_null
      //   47: astore 5
      //   49: iconst_0
      //   50: istore 11
      //   52: aload 7
      //   54: invokevirtual 59	com/intowow/sdk/e/a:h	()Z
      //   57: ifeq +925 -> 982
      //   60: aload 7
      //   62: invokevirtual 63	com/intowow/sdk/e/a:g	()J
      //   65: lstore 13
      //   67: aload 7
      //   69: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   72: aload_0
      //   73: getfield 14	com/intowow/sdk/e/b$a:a	Lcom/intowow/sdk/e/b;
      //   76: invokestatic 71	com/intowow/sdk/e/b:b	(Lcom/intowow/sdk/e/b;)Landroid/content/Context;
      //   79: invokestatic 76	com/intowow/sdk/j/k:a	(Landroid/content/Context;)Lcom/intowow/sdk/j/k;
      //   82: invokevirtual 78	com/intowow/sdk/j/k:a	()Ljava/lang/String;
      //   85: invokevirtual 84	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   88: istore 15
      //   90: iload 15
      //   92: ifeq +64 -> 156
      //   95: aload 7
      //   97: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   100: invokestatic 87	com/intowow/sdk/j/k:b	(Ljava/lang/String;)J
      //   103: aload_0
      //   104: getfield 14	com/intowow/sdk/e/b$a:a	Lcom/intowow/sdk/e/b;
      //   107: invokestatic 91	com/intowow/sdk/e/b:c	(Lcom/intowow/sdk/e/b;)J
      //   110: lcmp
      //   111: ifge +45 -> 156
      //   114: ldc 93
      //   116: iconst_0
      //   117: anewarray 4	java/lang/Object
      //   120: invokestatic 98	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   123: aload 8
      //   125: ifnull -111 -> 14
      //   128: aload 8
      //   130: aload 7
      //   132: iconst_3
      //   133: invokeinterface 101 3 0
      //   138: return
      //   139: astore_1
      //   140: aload 8
      //   142: ifnull -128 -> 14
      //   145: aload 8
      //   147: aload 7
      //   149: iconst_4
      //   150: invokeinterface 101 3 0
      //   155: return
      //   156: iload 15
      //   158: ifeq +52 -> 210
      //   161: aload_0
      //   162: getfield 14	com/intowow/sdk/e/b$a:a	Lcom/intowow/sdk/e/b;
      //   165: invokestatic 104	com/intowow/sdk/e/b:d	(Lcom/intowow/sdk/e/b;)J
      //   168: aload 7
      //   170: invokevirtual 63	com/intowow/sdk/e/a:g	()J
      //   173: ladd
      //   174: aload_0
      //   175: getfield 14	com/intowow/sdk/e/b$a:a	Lcom/intowow/sdk/e/b;
      //   178: invokestatic 106	com/intowow/sdk/e/b:e	(Lcom/intowow/sdk/e/b;)J
      //   181: lcmp
      //   182: ifle +28 -> 210
      //   185: ldc 108
      //   187: iconst_0
      //   188: anewarray 4	java/lang/Object
      //   191: invokestatic 98	com/intowow/sdk/j/h:b	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   194: aload 8
      //   196: ifnull -182 -> 14
      //   199: aload 8
      //   201: aload 7
      //   203: iconst_3
      //   204: invokeinterface 101 3 0
      //   209: return
      //   210: new 110	java/io/File
      //   213: dup
      //   214: aload 7
      //   216: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   219: invokespecial 113	java/io/File:<init>	(Ljava/lang/String;)V
      //   222: astore 10
      //   224: aload 10
      //   226: invokevirtual 116	java/io/File:exists	()Z
      //   229: ifeq +108 -> 337
      //   232: aload 7
      //   234: aload 10
      //   236: invokevirtual 119	java/io/File:length	()J
      //   239: invokevirtual 122	com/intowow/sdk/e/a:b	(J)V
      //   242: aload 7
      //   244: invokevirtual 125	com/intowow/sdk/e/a:i	()J
      //   247: lload 13
      //   249: lcmp
      //   250: ifne +48 -> 298
      //   253: aload 7
      //   255: invokevirtual 127	com/intowow/sdk/e/a:c	()Ljava/lang/String;
      //   258: ifnull +94 -> 352
      //   261: aload 7
      //   263: invokevirtual 127	com/intowow/sdk/e/a:c	()Ljava/lang/String;
      //   266: aload 7
      //   268: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   271: invokestatic 130	com/intowow/sdk/j/k:d	(Ljava/lang/String;)Ljava/lang/String;
      //   274: invokevirtual 134	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   277: ifne +75 -> 352
      //   280: aload 10
      //   282: invokevirtual 137	java/io/File:delete	()Z
      //   285: pop
      //   286: aload 10
      //   288: invokevirtual 140	java/io/File:createNewFile	()Z
      //   291: pop
      //   292: aload 7
      //   294: lconst_0
      //   295: invokevirtual 122	com/intowow/sdk/e/a:b	(J)V
      //   298: new 142	java/net/URL
      //   301: dup
      //   302: aload 7
      //   304: invokevirtual 145	com/intowow/sdk/e/a:f	()Ljava/lang/String;
      //   307: invokespecial 146	java/net/URL:<init>	(Ljava/lang/String;)V
      //   310: astore_2
      //   311: aconst_null
      //   312: astore_3
      //   313: aload 7
      //   315: invokevirtual 149	com/intowow/sdk/e/a:d	()I
      //   318: ifgt +56 -> 374
      //   321: aload 8
      //   323: ifnull -309 -> 14
      //   326: aload 8
      //   328: aload 7
      //   330: iconst_4
      //   331: invokeinterface 101 3 0
      //   336: return
      //   337: aload 10
      //   339: invokevirtual 140	java/io/File:createNewFile	()Z
      //   342: pop
      //   343: aload 7
      //   345: lconst_0
      //   346: invokevirtual 122	com/intowow/sdk/e/a:b	(J)V
      //   349: goto -107 -> 242
      //   352: aload 8
      //   354: ifnull +12 -> 366
      //   357: aload 8
      //   359: aload 7
      //   361: invokeinterface 151 2 0
      //   366: aload_0
      //   367: aconst_null
      //   368: aconst_null
      //   369: aconst_null
      //   370: invokespecial 153	com/intowow/sdk/e/b$a:a	(Ljava/io/InputStream;Ljava/io/RandomAccessFile;Ljava/net/HttpURLConnection;)V
      //   373: return
      //   374: aload 7
      //   376: aload 7
      //   378: invokevirtual 149	com/intowow/sdk/e/a:d	()I
      //   381: iconst_1
      //   382: isub
      //   383: invokevirtual 156	com/intowow/sdk/e/a:b	(I)V
      //   386: aload_2
      //   387: invokevirtual 160	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   390: checkcast 31	java/net/HttpURLConnection
      //   393: astore_1
      //   394: aload_1
      //   395: sipush 5000
      //   398: invokevirtual 163	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   401: aload_1
      //   402: ldc 165
      //   404: invokevirtual 168	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   407: aload_1
      //   408: iconst_0
      //   409: invokevirtual 172	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
      //   412: lload 13
      //   414: ldc2_w 173
      //   417: lcmp
      //   418: ifeq +39 -> 457
      //   421: aload_1
      //   422: ldc 176
      //   424: ldc 178
      //   426: iconst_2
      //   427: anewarray 4	java/lang/Object
      //   430: dup
      //   431: iconst_0
      //   432: aload 7
      //   434: invokevirtual 125	com/intowow/sdk/e/a:i	()J
      //   437: invokestatic 184	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   440: aastore
      //   441: dup
      //   442: iconst_1
      //   443: lload 13
      //   445: lconst_1
      //   446: lsub
      //   447: invokestatic 184	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   450: aastore
      //   451: invokestatic 188	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   454: invokevirtual 192	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   457: aload_1
      //   458: invokevirtual 195	java/net/HttpURLConnection:connect	()V
      //   461: aload_1
      //   462: invokevirtual 198	java/net/HttpURLConnection:getResponseCode	()I
      //   465: istore 12
      //   467: iload 12
      //   469: bipush 100
      //   471: idiv
      //   472: iconst_3
      //   473: if_icmpne +33 -> 506
      //   476: new 142	java/net/URL
      //   479: dup
      //   480: aload_1
      //   481: ldc 200
      //   483: invokevirtual 203	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
      //   486: invokespecial 146	java/net/URL:<init>	(Ljava/lang/String;)V
      //   489: astore_3
      //   490: aload_0
      //   491: aload 4
      //   493: aload 5
      //   495: aload_1
      //   496: invokespecial 153	com/intowow/sdk/e/b$a:a	(Ljava/io/InputStream;Ljava/io/RandomAccessFile;Ljava/net/HttpURLConnection;)V
      //   499: aload_3
      //   500: astore_2
      //   501: aload_1
      //   502: astore_3
      //   503: goto -190 -> 313
      //   506: iload 12
      //   508: sipush 200
      //   511: if_icmpeq +25 -> 536
      //   514: iload 12
      //   516: sipush 206
      //   519: if_icmpeq +17 -> 536
      //   522: aload_0
      //   523: aload 4
      //   525: aload 5
      //   527: aload_1
      //   528: invokespecial 153	com/intowow/sdk/e/b$a:a	(Ljava/io/InputStream;Ljava/io/RandomAccessFile;Ljava/net/HttpURLConnection;)V
      //   531: aload_1
      //   532: astore_3
      //   533: goto -220 -> 313
      //   536: new 28	java/io/RandomAccessFile
      //   539: dup
      //   540: aload 10
      //   542: ldc 205
      //   544: invokespecial 208	java/io/RandomAccessFile:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   547: astore_3
      //   548: aload_3
      //   549: aload 7
      //   551: invokevirtual 125	com/intowow/sdk/e/a:i	()J
      //   554: invokevirtual 211	java/io/RandomAccessFile:seek	(J)V
      //   557: aload_1
      //   558: invokevirtual 215	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   561: astore 5
      //   563: aload 5
      //   565: astore 4
      //   567: aload 4
      //   569: aload 9
      //   571: invokevirtual 219	java/io/InputStream:read	([B)I
      //   574: istore 12
      //   576: iload 12
      //   578: iconst_m1
      //   579: if_icmpne +109 -> 688
      //   582: aload 7
      //   584: invokevirtual 127	com/intowow/sdk/e/a:c	()Ljava/lang/String;
      //   587: ifnull +392 -> 979
      //   590: aload 7
      //   592: invokevirtual 127	com/intowow/sdk/e/a:c	()Ljava/lang/String;
      //   595: aload 7
      //   597: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   600: invokestatic 130	com/intowow/sdk/j/k:d	(Ljava/lang/String;)Ljava/lang/String;
      //   603: invokevirtual 134	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   606: istore 15
      //   608: iload 15
      //   610: ifne +369 -> 979
      //   613: iconst_1
      //   614: istore 11
      //   616: aload 8
      //   618: ifnull +19 -> 637
      //   621: iload 11
      //   623: ifeq +134 -> 757
      //   626: aload 8
      //   628: aload 7
      //   630: bipush 6
      //   632: invokeinterface 101 3 0
      //   637: aload_0
      //   638: aload 4
      //   640: aload_3
      //   641: aload_1
      //   642: invokespecial 153	com/intowow/sdk/e/b$a:a	(Ljava/io/InputStream;Ljava/io/RandomAccessFile;Ljava/net/HttpURLConnection;)V
      //   645: iload 11
      //   647: ifeq +184 -> 831
      //   650: aload 10
      //   652: invokevirtual 137	java/io/File:delete	()Z
      //   655: pop
      //   656: return
      //   657: astore_2
      //   658: aload_1
      //   659: astore_2
      //   660: aload_3
      //   661: astore_1
      //   662: aload_2
      //   663: astore_3
      //   664: aload 8
      //   666: ifnull +13 -> 679
      //   669: aload 8
      //   671: aload 7
      //   673: iconst_1
      //   674: invokeinterface 101 3 0
      //   679: aload_0
      //   680: aload 4
      //   682: aload_1
      //   683: aload_3
      //   684: invokespecial 153	com/intowow/sdk/e/b$a:a	(Ljava/io/InputStream;Ljava/io/RandomAccessFile;Ljava/net/HttpURLConnection;)V
      //   687: return
      //   688: aload_3
      //   689: aload 9
      //   691: iconst_0
      //   692: iload 12
      //   694: invokevirtual 223	java/io/RandomAccessFile:write	([BII)V
      //   697: aload 7
      //   699: aload 7
      //   701: invokevirtual 125	com/intowow/sdk/e/a:i	()J
      //   704: iload 12
      //   706: i2l
      //   707: ladd
      //   708: invokevirtual 122	com/intowow/sdk/e/a:b	(J)V
      //   711: aload 8
      //   713: ifnull -146 -> 567
      //   716: aload 8
      //   718: aload 7
      //   720: invokeinterface 225 2 0
      //   725: goto -158 -> 567
      //   728: astore_2
      //   729: aload_3
      //   730: astore 5
      //   732: aload 8
      //   734: ifnull +13 -> 747
      //   737: aload 8
      //   739: aload 7
      //   741: iconst_2
      //   742: invokeinterface 101 3 0
      //   747: aload_0
      //   748: aload 4
      //   750: aload 5
      //   752: aload_1
      //   753: invokespecial 153	com/intowow/sdk/e/b$a:a	(Ljava/io/InputStream;Ljava/io/RandomAccessFile;Ljava/net/HttpURLConnection;)V
      //   756: return
      //   757: aload 8
      //   759: aload 7
      //   761: invokeinterface 151 2 0
      //   766: goto -129 -> 637
      //   769: astore 5
      //   771: aload_2
      //   772: astore 5
      //   774: aload_1
      //   775: astore_2
      //   776: aload 5
      //   778: astore_1
      //   779: aload_3
      //   780: astore 5
      //   782: aload_0
      //   783: aload 4
      //   785: aload 5
      //   787: aload_2
      //   788: invokespecial 153	com/intowow/sdk/e/b$a:a	(Ljava/io/InputStream;Ljava/io/RandomAccessFile;Ljava/net/HttpURLConnection;)V
      //   791: aload 7
      //   793: ifnull +31 -> 824
      //   796: aload 7
      //   798: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   801: ifnull +23 -> 824
      //   804: aload 7
      //   806: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   809: aload 7
      //   811: invokevirtual 127	com/intowow/sdk/e/a:c	()Ljava/lang/String;
      //   814: invokestatic 230	com/intowow/sdk/j/f:a	(Ljava/lang/String;Ljava/lang/String;)Z
      //   817: istore 15
      //   819: iload 15
      //   821: ifne -807 -> 14
      //   824: aload_2
      //   825: astore_3
      //   826: aload_1
      //   827: astore_2
      //   828: goto -515 -> 313
      //   831: aload 7
      //   833: invokevirtual 67	com/intowow/sdk/e/a:e	()Ljava/lang/String;
      //   836: aload_0
      //   837: getfield 14	com/intowow/sdk/e/b$a:a	Lcom/intowow/sdk/e/b;
      //   840: invokestatic 71	com/intowow/sdk/e/b:b	(Lcom/intowow/sdk/e/b;)Landroid/content/Context;
      //   843: invokestatic 76	com/intowow/sdk/j/k:a	(Landroid/content/Context;)Lcom/intowow/sdk/j/k;
      //   846: invokevirtual 78	com/intowow/sdk/j/k:a	()Ljava/lang/String;
      //   849: invokevirtual 84	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
      //   852: ifeq -838 -> 14
      //   855: aload_0
      //   856: getfield 14	com/intowow/sdk/e/b$a:a	Lcom/intowow/sdk/e/b;
      //   859: astore 5
      //   861: aload 5
      //   863: aload 5
      //   865: invokestatic 104	com/intowow/sdk/e/b:d	(Lcom/intowow/sdk/e/b;)J
      //   868: aload 7
      //   870: invokevirtual 63	com/intowow/sdk/e/a:g	()J
      //   873: ladd
      //   874: invokestatic 233	com/intowow/sdk/e/b:a	(Lcom/intowow/sdk/e/b;J)V
      //   877: return
      //   878: astore 5
      //   880: aload_2
      //   881: astore 6
      //   883: aload_1
      //   884: astore_2
      //   885: aload_3
      //   886: astore 5
      //   888: aload 6
      //   890: astore_1
      //   891: goto -109 -> 782
      //   894: astore_1
      //   895: aload_2
      //   896: astore_1
      //   897: aload_3
      //   898: astore_2
      //   899: goto -117 -> 782
      //   902: astore_3
      //   903: aload_2
      //   904: astore_3
      //   905: aload_1
      //   906: astore_2
      //   907: aload_3
      //   908: astore_1
      //   909: goto -127 -> 782
      //   912: astore_2
      //   913: aload_1
      //   914: astore_2
      //   915: aload_3
      //   916: astore_1
      //   917: goto -135 -> 782
      //   920: astore 5
      //   922: aload_2
      //   923: astore 6
      //   925: aload_1
      //   926: astore_2
      //   927: aload_3
      //   928: astore 5
      //   930: aload 6
      //   932: astore_1
      //   933: goto -151 -> 782
      //   936: astore_1
      //   937: aload_3
      //   938: astore_1
      //   939: goto -207 -> 732
      //   942: astore_2
      //   943: goto -211 -> 732
      //   946: astore_2
      //   947: aload_3
      //   948: astore 5
      //   950: goto -218 -> 732
      //   953: astore_1
      //   954: aload 5
      //   956: astore_1
      //   957: goto -293 -> 664
      //   960: astore_2
      //   961: aload_1
      //   962: astore_3
      //   963: aload 5
      //   965: astore_1
      //   966: goto -302 -> 664
      //   969: astore_2
      //   970: aload_1
      //   971: astore_2
      //   972: aload_3
      //   973: astore_1
      //   974: aload_2
      //   975: astore_3
      //   976: goto -312 -> 664
      //   979: goto -363 -> 616
      //   982: ldc2_w 173
      //   985: lstore 13
      //   987: goto -920 -> 67
      //
      // Exception table:
      //   from	to	target	type
      //   67	90	139	java/lang/Exception
      //   95	123	139	java/lang/Exception
      //   128	138	139	java/lang/Exception
      //   161	194	139	java/lang/Exception
      //   199	209	139	java/lang/Exception
      //   210	242	139	java/lang/Exception
      //   242	298	139	java/lang/Exception
      //   298	311	139	java/lang/Exception
      //   313	321	139	java/lang/Exception
      //   326	336	139	java/lang/Exception
      //   337	349	139	java/lang/Exception
      //   357	366	139	java/lang/Exception
      //   366	373	139	java/lang/Exception
      //   669	679	139	java/lang/Exception
      //   679	687	139	java/lang/Exception
      //   737	747	139	java/lang/Exception
      //   747	756	139	java/lang/Exception
      //   782	791	139	java/lang/Exception
      //   796	819	139	java/lang/Exception
      //   567	576	657	java/io/FileNotFoundException
      //   582	608	657	java/io/FileNotFoundException
      //   626	637	657	java/io/FileNotFoundException
      //   637	645	657	java/io/FileNotFoundException
      //   650	656	657	java/io/FileNotFoundException
      //   688	711	657	java/io/FileNotFoundException
      //   716	725	657	java/io/FileNotFoundException
      //   757	766	657	java/io/FileNotFoundException
      //   831	877	657	java/io/FileNotFoundException
      //   567	576	728	java/net/MalformedURLException
      //   582	608	728	java/net/MalformedURLException
      //   626	637	728	java/net/MalformedURLException
      //   637	645	728	java/net/MalformedURLException
      //   650	656	728	java/net/MalformedURLException
      //   688	711	728	java/net/MalformedURLException
      //   716	725	728	java/net/MalformedURLException
      //   757	766	728	java/net/MalformedURLException
      //   831	877	728	java/net/MalformedURLException
      //   626	637	769	java/lang/Exception
      //   637	645	769	java/lang/Exception
      //   650	656	769	java/lang/Exception
      //   757	766	769	java/lang/Exception
      //   831	877	769	java/lang/Exception
      //   567	576	878	java/lang/Exception
      //   582	608	878	java/lang/Exception
      //   688	711	878	java/lang/Exception
      //   716	725	878	java/lang/Exception
      //   374	394	894	java/lang/Exception
      //   394	412	902	java/lang/Exception
      //   421	457	902	java/lang/Exception
      //   457	490	902	java/lang/Exception
      //   522	531	902	java/lang/Exception
      //   536	548	902	java/lang/Exception
      //   490	499	912	java/lang/Exception
      //   548	563	920	java/lang/Exception
      //   374	394	936	java/net/MalformedURLException
      //   394	412	942	java/net/MalformedURLException
      //   421	457	942	java/net/MalformedURLException
      //   457	490	942	java/net/MalformedURLException
      //   490	499	942	java/net/MalformedURLException
      //   522	531	942	java/net/MalformedURLException
      //   536	548	942	java/net/MalformedURLException
      //   548	563	946	java/net/MalformedURLException
      //   374	394	953	java/io/FileNotFoundException
      //   394	412	960	java/io/FileNotFoundException
      //   421	457	960	java/io/FileNotFoundException
      //   457	490	960	java/io/FileNotFoundException
      //   490	499	960	java/io/FileNotFoundException
      //   522	531	960	java/io/FileNotFoundException
      //   536	548	960	java/io/FileNotFoundException
      //   548	563	969	java/io/FileNotFoundException
    }
  }

  public static abstract interface b
  {
    public abstract void a(a parama);

    public abstract void a(a parama, int paramInt);

    public abstract void b(a parama);

    public abstract void c(a parama);
  }

  public static class c
  {
    public static final c a = new c(true, 63, 15, 15, 63, 7);
    private static int b = 8388608;
    private static int c = 17;
    private static int d = 14;
    private static int e = 9;
    private static int f = 3;
    private boolean g = false;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l = 0;
    private int m = 0;

    public c(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      this.g = paramBoolean;
      this.h = paramInt1;
      this.i = paramInt2;
      this.j = paramInt3;
      this.k = paramInt4;
      this.l = paramInt5;
      b();
    }

    private void b()
    {
      if (this.g);
      for (int n = b; ; n = 0)
      {
        this.m = (n + (this.h << c) + (this.i << d) + (this.k << f) + this.l);
        return;
      }
    }

    public int a()
    {
      return this.m;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.e.b
 * JD-Core Version:    0.6.2
 */