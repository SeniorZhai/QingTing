package com.alipay.sdk.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

final class FileFetch
  implements Runnable
{
  private String a;
  private String b;
  private FileDownloader c;
  private boolean d = false;
  private long e;
  private long f;

  public FileFetch(String paramString1, String paramString2, FileDownloader paramFileDownloader)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramFileDownloader;
  }

  public final long a()
  {
    return this.e;
  }

  public final void a(long paramLong)
  {
    this.e = paramLong;
  }

  public final long b()
  {
    return this.f;
  }

  public final void b(long paramLong)
  {
    this.f = paramLong;
  }

  public final boolean c()
  {
    return this.d;
  }

  public final void d()
  {
    this.d = true;
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 32	com/alipay/sdk/util/FileFetch:c	Lcom/alipay/sdk/util/FileDownloader;
    //   4: invokevirtual 52	com/alipay/sdk/util/FileDownloader:a	()Z
    //   7: ifeq +30 -> 37
    //   10: aload_0
    //   11: getfield 40	com/alipay/sdk/util/FileFetch:f	J
    //   14: lconst_0
    //   15: lcmp
    //   16: ifle +15 -> 31
    //   19: aload_0
    //   20: getfield 37	com/alipay/sdk/util/FileFetch:e	J
    //   23: aload_0
    //   24: getfield 40	com/alipay/sdk/util/FileFetch:f	J
    //   27: lcmp
    //   28: iflt +9 -> 37
    //   31: aload_0
    //   32: iconst_1
    //   33: putfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   36: return
    //   37: new 8	com/alipay/sdk/util/FileFetch$FileAccess
    //   40: dup
    //   41: aload_0
    //   42: invokespecial 55	com/alipay/sdk/util/FileFetch$FileAccess:<init>	(Lcom/alipay/sdk/util/FileFetch;)V
    //   45: astore 10
    //   47: aload_0
    //   48: getfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   51: ifne +267 -> 318
    //   54: aconst_null
    //   55: astore_3
    //   56: aconst_null
    //   57: astore 5
    //   59: aconst_null
    //   60: astore 7
    //   62: aconst_null
    //   63: astore 8
    //   65: aconst_null
    //   66: astore 9
    //   68: aconst_null
    //   69: astore 6
    //   71: aconst_null
    //   72: astore_2
    //   73: aload 7
    //   75: astore 4
    //   77: aload 8
    //   79: astore_1
    //   80: new 57	org/apache/http/client/methods/HttpGet
    //   83: dup
    //   84: aload_0
    //   85: getfield 28	com/alipay/sdk/util/FileFetch:a	Ljava/lang/String;
    //   88: invokespecial 60	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   91: astore 11
    //   93: aload 7
    //   95: astore 4
    //   97: aload 8
    //   99: astore_1
    //   100: new 62	org/apache/http/impl/client/DefaultHttpClient
    //   103: dup
    //   104: invokespecial 63	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   107: astore 12
    //   109: aload 7
    //   111: astore 4
    //   113: aload 8
    //   115: astore_1
    //   116: aload_0
    //   117: getfield 32	com/alipay/sdk/util/FileFetch:c	Lcom/alipay/sdk/util/FileDownloader;
    //   120: invokevirtual 52	com/alipay/sdk/util/FileDownloader:a	()Z
    //   123: ifeq +48 -> 171
    //   126: aload 7
    //   128: astore 4
    //   130: aload 8
    //   132: astore_1
    //   133: aload 11
    //   135: ldc 65
    //   137: new 67	java/lang/StringBuilder
    //   140: dup
    //   141: ldc 69
    //   143: invokespecial 70	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   146: aload_0
    //   147: getfield 37	com/alipay/sdk/util/FileFetch:e	J
    //   150: invokevirtual 74	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   153: ldc 76
    //   155: invokevirtual 79	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload_0
    //   159: getfield 40	com/alipay/sdk/util/FileFetch:f	J
    //   162: invokevirtual 74	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   165: invokevirtual 83	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   168: invokevirtual 87	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   171: aload 7
    //   173: astore 4
    //   175: aload 8
    //   177: astore_1
    //   178: aload 12
    //   180: aload 11
    //   182: invokeinterface 93 2 0
    //   187: astore 11
    //   189: aload 7
    //   191: astore 4
    //   193: aload 8
    //   195: astore_1
    //   196: aload 11
    //   198: invokeinterface 99 1 0
    //   203: invokeinterface 105 1 0
    //   208: istore 14
    //   210: iload 14
    //   212: tableswitch	default:+48 -> 260, 200:+112->324, 201:+112->324, 202:+112->324, 203:+112->324, 204:+112->324, 205:+112->324, 206:+112->324, 207:+112->324
    //   261: iconst_4
    //   262: astore 4
    //   264: aload 8
    //   266: astore_1
    //   267: iload 14
    //   269: istore 13
    //   271: aload 9
    //   273: astore 5
    //   275: aload 6
    //   277: astore_3
    //   278: aload_0
    //   279: iconst_1
    //   280: putfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   283: aload_2
    //   284: astore 4
    //   286: aload_2
    //   287: astore_1
    //   288: iload 14
    //   290: istore 13
    //   292: aload_2
    //   293: astore 5
    //   295: aload_2
    //   296: astore_3
    //   297: aload_0
    //   298: getfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   301: istore 17
    //   303: aload_2
    //   304: astore_3
    //   305: iload 17
    //   307: ifeq +100 -> 407
    //   310: aload_2
    //   311: ifnull +7 -> 318
    //   314: aload_2
    //   315: invokevirtual 110	java/io/InputStream:close	()V
    //   318: aload 10
    //   320: invokevirtual 112	com/alipay/sdk/util/FileFetch$FileAccess:a	()V
    //   323: return
    //   324: aload 7
    //   326: astore 4
    //   328: aload 8
    //   330: astore_1
    //   331: iload 14
    //   333: istore 13
    //   335: aload 9
    //   337: astore 5
    //   339: aload 6
    //   341: astore_3
    //   342: aload 11
    //   344: invokeinterface 116 1 0
    //   349: invokeinterface 122 1 0
    //   354: astore_2
    //   355: goto -72 -> 283
    //   358: astore 7
    //   360: iconst_0
    //   361: istore 14
    //   363: aload_3
    //   364: astore_2
    //   365: aload_2
    //   366: astore 6
    //   368: aload_2
    //   369: astore 4
    //   371: aload_2
    //   372: astore_1
    //   373: iload 14
    //   375: istore 13
    //   377: aload_2
    //   378: astore 5
    //   380: aload 7
    //   382: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   385: aload_2
    //   386: astore 6
    //   388: aload_2
    //   389: astore 4
    //   391: aload_2
    //   392: astore_1
    //   393: iload 14
    //   395: istore 13
    //   397: aload_2
    //   398: astore 5
    //   400: aload_0
    //   401: iconst_1
    //   402: putfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   405: aload_2
    //   406: astore_3
    //   407: aload_3
    //   408: ifnonnull +18 -> 426
    //   411: aload_3
    //   412: ifnull -365 -> 47
    //   415: aload_3
    //   416: invokevirtual 110	java/io/InputStream:close	()V
    //   419: goto -372 -> 47
    //   422: astore_1
    //   423: goto -376 -> 47
    //   426: aload_3
    //   427: astore 6
    //   429: aload_3
    //   430: astore 4
    //   432: aload_3
    //   433: astore_1
    //   434: iload 14
    //   436: istore 13
    //   438: aload_3
    //   439: astore 5
    //   441: sipush 1024
    //   444: newarray byte
    //   446: astore_2
    //   447: aload_3
    //   448: astore 6
    //   450: aload_3
    //   451: astore 4
    //   453: aload_3
    //   454: astore_1
    //   455: iload 14
    //   457: istore 13
    //   459: aload_3
    //   460: astore 5
    //   462: aload_3
    //   463: aload_2
    //   464: iconst_0
    //   465: aload_2
    //   466: arraylength
    //   467: invokevirtual 129	java/io/InputStream:read	([BII)I
    //   470: istore 16
    //   472: iload 16
    //   474: iconst_m1
    //   475: if_icmpeq +58 -> 533
    //   478: aload_3
    //   479: astore 6
    //   481: aload_3
    //   482: astore 4
    //   484: aload_3
    //   485: astore_1
    //   486: iload 14
    //   488: istore 13
    //   490: aload_3
    //   491: astore 5
    //   493: aload_0
    //   494: aload_0
    //   495: getfield 37	com/alipay/sdk/util/FileFetch:e	J
    //   498: aload 10
    //   500: aload_2
    //   501: iload 16
    //   503: invokevirtual 132	com/alipay/sdk/util/FileFetch$FileAccess:a	([BI)I
    //   506: i2l
    //   507: ladd
    //   508: putfield 37	com/alipay/sdk/util/FileFetch:e	J
    //   511: aload_3
    //   512: astore 6
    //   514: aload_3
    //   515: astore 4
    //   517: aload_3
    //   518: astore_1
    //   519: iload 14
    //   521: istore 13
    //   523: aload_3
    //   524: astore 5
    //   526: aload_0
    //   527: getfield 32	com/alipay/sdk/util/FileFetch:c	Lcom/alipay/sdk/util/FileDownloader;
    //   530: invokevirtual 134	com/alipay/sdk/util/FileDownloader:d	()V
    //   533: aload_3
    //   534: astore 6
    //   536: aload_3
    //   537: astore 4
    //   539: aload_3
    //   540: astore_1
    //   541: iload 14
    //   543: istore 13
    //   545: aload_3
    //   546: astore 5
    //   548: aload_0
    //   549: getfield 32	com/alipay/sdk/util/FileFetch:c	Lcom/alipay/sdk/util/FileDownloader;
    //   552: invokevirtual 52	com/alipay/sdk/util/FileDownloader:a	()Z
    //   555: ifeq +101 -> 656
    //   558: aload_3
    //   559: astore 6
    //   561: aload_3
    //   562: astore 4
    //   564: aload_3
    //   565: astore_1
    //   566: iload 14
    //   568: istore 13
    //   570: aload_3
    //   571: astore 5
    //   573: aload_0
    //   574: getfield 37	com/alipay/sdk/util/FileFetch:e	J
    //   577: aload_0
    //   578: getfield 40	com/alipay/sdk/util/FileFetch:f	J
    //   581: lcmp
    //   582: iflt +74 -> 656
    //   585: iconst_0
    //   586: istore 15
    //   588: aload_3
    //   589: astore 6
    //   591: aload_3
    //   592: astore 4
    //   594: aload_3
    //   595: astore_1
    //   596: iload 14
    //   598: istore 13
    //   600: aload_3
    //   601: astore 5
    //   603: aload_0
    //   604: getfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   607: ifne +55 -> 662
    //   610: iload 15
    //   612: ifeq +50 -> 662
    //   615: iconst_1
    //   616: istore 13
    //   618: goto +166 -> 784
    //   621: aload_3
    //   622: astore 6
    //   624: aload_3
    //   625: astore 4
    //   627: aload_3
    //   628: astore_1
    //   629: iload 14
    //   631: istore 13
    //   633: aload_3
    //   634: astore 5
    //   636: aload_0
    //   637: iconst_1
    //   638: putfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   641: aload_3
    //   642: ifnull -595 -> 47
    //   645: aload_3
    //   646: invokevirtual 110	java/io/InputStream:close	()V
    //   649: goto -602 -> 47
    //   652: astore_1
    //   653: goto -606 -> 47
    //   656: iconst_1
    //   657: istore 15
    //   659: goto -71 -> 588
    //   662: iconst_0
    //   663: istore 13
    //   665: goto +119 -> 784
    //   668: astore_1
    //   669: iconst_0
    //   670: istore 13
    //   672: iload 13
    //   674: ifne +11 -> 685
    //   677: aload 5
    //   679: astore_1
    //   680: aload_0
    //   681: iconst_1
    //   682: putfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   685: aload 5
    //   687: ifnull -640 -> 47
    //   690: aload 5
    //   692: invokevirtual 110	java/io/InputStream:close	()V
    //   695: goto -648 -> 47
    //   698: astore_1
    //   699: goto -652 -> 47
    //   702: astore_1
    //   703: aload 6
    //   705: astore_1
    //   706: aload_0
    //   707: iconst_1
    //   708: putfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   711: aload 6
    //   713: ifnull -666 -> 47
    //   716: aload 6
    //   718: invokevirtual 110	java/io/InputStream:close	()V
    //   721: goto -674 -> 47
    //   724: astore_1
    //   725: goto -678 -> 47
    //   728: astore_1
    //   729: aload 4
    //   731: astore_1
    //   732: aload_0
    //   733: iconst_1
    //   734: putfield 26	com/alipay/sdk/util/FileFetch:d	Z
    //   737: aload 4
    //   739: ifnull -692 -> 47
    //   742: aload 4
    //   744: invokevirtual 110	java/io/InputStream:close	()V
    //   747: goto -700 -> 47
    //   750: astore_1
    //   751: goto -704 -> 47
    //   754: astore_2
    //   755: aload_1
    //   756: ifnull +7 -> 763
    //   759: aload_1
    //   760: invokevirtual 110	java/io/InputStream:close	()V
    //   763: aload_2
    //   764: athrow
    //   765: astore_1
    //   766: goto -448 -> 318
    //   769: astore_1
    //   770: goto -7 -> 763
    //   773: astore_1
    //   774: goto -102 -> 672
    //   777: astore 7
    //   779: aload_3
    //   780: astore_2
    //   781: goto -416 -> 365
    //   784: iload 16
    //   786: iflt -165 -> 621
    //   789: iload 13
    //   791: ifne -344 -> 447
    //   794: goto -173 -> 621
    //
    // Exception table:
    //   from	to	target	type
    //   80	93	358	java/io/IOException
    //   100	109	358	java/io/IOException
    //   116	126	358	java/io/IOException
    //   133	171	358	java/io/IOException
    //   178	189	358	java/io/IOException
    //   196	210	358	java/io/IOException
    //   415	419	422	java/lang/Exception
    //   645	649	652	java/lang/Exception
    //   80	93	668	java/net/SocketTimeoutException
    //   100	109	668	java/net/SocketTimeoutException
    //   116	126	668	java/net/SocketTimeoutException
    //   133	171	668	java/net/SocketTimeoutException
    //   178	189	668	java/net/SocketTimeoutException
    //   196	210	668	java/net/SocketTimeoutException
    //   690	695	698	java/lang/Exception
    //   380	385	702	java/io/IOException
    //   400	405	702	java/io/IOException
    //   441	447	702	java/io/IOException
    //   462	472	702	java/io/IOException
    //   493	511	702	java/io/IOException
    //   526	533	702	java/io/IOException
    //   548	558	702	java/io/IOException
    //   573	585	702	java/io/IOException
    //   603	610	702	java/io/IOException
    //   636	641	702	java/io/IOException
    //   716	721	724	java/lang/Exception
    //   80	93	728	java/lang/Exception
    //   100	109	728	java/lang/Exception
    //   116	126	728	java/lang/Exception
    //   133	171	728	java/lang/Exception
    //   178	189	728	java/lang/Exception
    //   196	210	728	java/lang/Exception
    //   278	283	728	java/lang/Exception
    //   297	303	728	java/lang/Exception
    //   342	355	728	java/lang/Exception
    //   380	385	728	java/lang/Exception
    //   400	405	728	java/lang/Exception
    //   441	447	728	java/lang/Exception
    //   462	472	728	java/lang/Exception
    //   493	511	728	java/lang/Exception
    //   526	533	728	java/lang/Exception
    //   548	558	728	java/lang/Exception
    //   573	585	728	java/lang/Exception
    //   603	610	728	java/lang/Exception
    //   636	641	728	java/lang/Exception
    //   742	747	750	java/lang/Exception
    //   80	93	754	finally
    //   100	109	754	finally
    //   116	126	754	finally
    //   133	171	754	finally
    //   178	189	754	finally
    //   196	210	754	finally
    //   278	283	754	finally
    //   297	303	754	finally
    //   342	355	754	finally
    //   380	385	754	finally
    //   400	405	754	finally
    //   441	447	754	finally
    //   462	472	754	finally
    //   493	511	754	finally
    //   526	533	754	finally
    //   548	558	754	finally
    //   573	585	754	finally
    //   603	610	754	finally
    //   636	641	754	finally
    //   680	685	754	finally
    //   706	711	754	finally
    //   732	737	754	finally
    //   314	318	765	java/lang/Exception
    //   759	763	769	java/lang/Exception
    //   278	283	773	java/net/SocketTimeoutException
    //   297	303	773	java/net/SocketTimeoutException
    //   342	355	773	java/net/SocketTimeoutException
    //   380	385	773	java/net/SocketTimeoutException
    //   400	405	773	java/net/SocketTimeoutException
    //   441	447	773	java/net/SocketTimeoutException
    //   462	472	773	java/net/SocketTimeoutException
    //   493	511	773	java/net/SocketTimeoutException
    //   526	533	773	java/net/SocketTimeoutException
    //   548	558	773	java/net/SocketTimeoutException
    //   573	585	773	java/net/SocketTimeoutException
    //   603	610	773	java/net/SocketTimeoutException
    //   636	641	773	java/net/SocketTimeoutException
    //   278	283	777	java/io/IOException
    //   297	303	777	java/io/IOException
    //   342	355	777	java/io/IOException
  }

  final class FileAccess
  {
    private FileOutputStream b;

    public FileAccess()
    {
      try
      {
        this.b = new FileOutputStream(FileFetch.a(FileFetch.this), true);
        return;
      }
      catch (FileNotFoundException this$1)
      {
        FileFetch.this.printStackTrace();
      }
    }

    public final int a(byte[] paramArrayOfByte, int paramInt)
      throws IOException
    {
      try
      {
        this.b.write(paramArrayOfByte, 0, paramInt);
        return paramInt;
      }
      finally
      {
        paramArrayOfByte = finally;
      }
      throw paramArrayOfByte;
    }

    public final void a()
    {
      try
      {
        this.b.close();
        return;
      }
      catch (Exception localException)
      {
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.FileFetch
 * JD-Core Version:    0.6.2
 */