package com.tencent.a.b;

import android.net.Proxy;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public final class q
{
  private static int a = 0;
  private static boolean b;

  // ERROR //
  private static n a(HttpURLConnection paramHttpURLConnection, boolean paramBoolean)
    throws IOException
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 11
    //   3: iconst_0
    //   4: istore 10
    //   6: aconst_null
    //   7: astore 4
    //   9: aload 4
    //   11: astore_2
    //   12: new 18	com/tencent/a/b/n
    //   15: dup
    //   16: invokespecial 21	com/tencent/a/b/n:<init>	()V
    //   19: astore 6
    //   21: aload 4
    //   23: astore_2
    //   24: aload_0
    //   25: invokevirtual 27	java/net/HttpURLConnection:getContentType	()Ljava/lang/String;
    //   28: astore 7
    //   30: ldc 29
    //   32: astore 5
    //   34: aload 5
    //   36: astore_3
    //   37: aload 7
    //   39: ifnull +27 -> 66
    //   42: aload 4
    //   44: astore_2
    //   45: aload 7
    //   47: ldc 31
    //   49: invokevirtual 37	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   52: astore_3
    //   53: aload 4
    //   55: astore_2
    //   56: aload_3
    //   57: arraylength
    //   58: istore 12
    //   60: iconst_0
    //   61: istore 9
    //   63: goto +282 -> 345
    //   66: aload 4
    //   68: astore_2
    //   69: aload 6
    //   71: aload_3
    //   72: putfield 40	com/tencent/a/b/n:b	Ljava/lang/String;
    //   75: iload_1
    //   76: ifeq +44 -> 120
    //   79: aload 7
    //   81: ifnull +239 -> 320
    //   84: aload 4
    //   86: astore_2
    //   87: aload 7
    //   89: ldc 42
    //   91: invokevirtual 46	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   94: ifeq +226 -> 320
    //   97: iload 11
    //   99: istore 9
    //   101: iload 9
    //   103: ifeq +17 -> 120
    //   106: aload 4
    //   108: astore_2
    //   109: aload_0
    //   110: invokevirtual 49	java/net/HttpURLConnection:disconnect	()V
    //   113: aload 4
    //   115: astore_2
    //   116: aload_0
    //   117: invokevirtual 52	java/net/HttpURLConnection:connect	()V
    //   120: aload 4
    //   122: astore_2
    //   123: aload_0
    //   124: invokevirtual 56	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   127: astore_0
    //   128: aload_0
    //   129: ifnull +113 -> 242
    //   132: aload_0
    //   133: astore_2
    //   134: aload 6
    //   136: iconst_0
    //   137: newarray byte
    //   139: putfield 59	com/tencent/a/b/n:a	[B
    //   142: aload_0
    //   143: astore_2
    //   144: sipush 1024
    //   147: newarray byte
    //   149: astore_3
    //   150: iload 10
    //   152: istore 9
    //   154: aload_0
    //   155: astore_2
    //   156: aload_0
    //   157: aload_3
    //   158: invokevirtual 65	java/io/InputStream:read	([B)I
    //   161: istore 11
    //   163: iload 9
    //   165: istore 10
    //   167: iload 11
    //   169: ifle +64 -> 233
    //   172: iload 9
    //   174: iload 11
    //   176: iadd
    //   177: istore 10
    //   179: aload_0
    //   180: astore_2
    //   181: iload 10
    //   183: newarray byte
    //   185: astore 4
    //   187: aload_0
    //   188: astore_2
    //   189: aload 6
    //   191: getfield 59	com/tencent/a/b/n:a	[B
    //   194: iconst_0
    //   195: aload 4
    //   197: iconst_0
    //   198: aload 6
    //   200: getfield 59	com/tencent/a/b/n:a	[B
    //   203: arraylength
    //   204: invokestatic 71	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   207: aload_0
    //   208: astore_2
    //   209: aload_3
    //   210: iconst_0
    //   211: aload 4
    //   213: aload 6
    //   215: getfield 59	com/tencent/a/b/n:a	[B
    //   218: arraylength
    //   219: iload 11
    //   221: invokestatic 71	java/lang/System:arraycopy	(Ljava/lang/Object;ILjava/lang/Object;II)V
    //   224: aload_0
    //   225: astore_2
    //   226: aload 6
    //   228: aload 4
    //   230: putfield 59	com/tencent/a/b/n:a	[B
    //   233: iload 10
    //   235: istore 9
    //   237: iload 11
    //   239: ifgt -85 -> 154
    //   242: aload_0
    //   243: ifnull +7 -> 250
    //   246: aload_0
    //   247: invokevirtual 74	java/io/InputStream:close	()V
    //   250: aload 6
    //   252: areturn
    //   253: aload_3
    //   254: iload 9
    //   256: aaload
    //   257: astore 8
    //   259: aload 4
    //   261: astore_2
    //   262: aload 8
    //   264: ldc 76
    //   266: invokevirtual 46	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   269: ifeq +42 -> 311
    //   272: aload 4
    //   274: astore_2
    //   275: aload 8
    //   277: ldc 78
    //   279: invokevirtual 37	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   282: astore 8
    //   284: aload 5
    //   286: astore_3
    //   287: aload 4
    //   289: astore_2
    //   290: aload 8
    //   292: arraylength
    //   293: iconst_1
    //   294: if_icmple -228 -> 66
    //   297: aload 4
    //   299: astore_2
    //   300: aload 8
    //   302: iconst_1
    //   303: aaload
    //   304: invokevirtual 81	java/lang/String:trim	()Ljava/lang/String;
    //   307: astore_3
    //   308: goto -242 -> 66
    //   311: iload 9
    //   313: iconst_1
    //   314: iadd
    //   315: istore 9
    //   317: goto +28 -> 345
    //   320: iconst_0
    //   321: istore 9
    //   323: goto -222 -> 101
    //   326: astore_0
    //   327: aload_2
    //   328: ifnull +7 -> 335
    //   331: aload_2
    //   332: invokevirtual 74	java/io/InputStream:close	()V
    //   335: aload_0
    //   336: athrow
    //   337: astore_0
    //   338: aload 6
    //   340: areturn
    //   341: astore_2
    //   342: goto -7 -> 335
    //   345: iload 9
    //   347: iload 12
    //   349: if_icmplt -96 -> 253
    //   352: aload 5
    //   354: astore_3
    //   355: goto -289 -> 66
    //
    // Exception table:
    //   from	to	target	type
    //   12	21	326	finally
    //   24	30	326	finally
    //   45	53	326	finally
    //   56	60	326	finally
    //   69	75	326	finally
    //   87	97	326	finally
    //   109	113	326	finally
    //   116	120	326	finally
    //   123	128	326	finally
    //   134	142	326	finally
    //   144	150	326	finally
    //   156	163	326	finally
    //   181	187	326	finally
    //   189	207	326	finally
    //   209	224	326	finally
    //   226	233	326	finally
    //   262	272	326	finally
    //   275	284	326	finally
    //   290	297	326	finally
    //   300	308	326	finally
    //   246	250	337	java/io/IOException
    //   331	335	341	java/io/IOException
  }

  // ERROR //
  public static n a(boolean paramBoolean1, String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte, boolean paramBoolean2, boolean paramBoolean3)
    throws java.lang.Exception
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 13
    //   3: iconst_0
    //   4: istore 12
    //   6: aconst_null
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 9
    //   11: aconst_null
    //   12: astore 10
    //   14: aconst_null
    //   15: astore 8
    //   17: invokestatic 93	com/tencent/a/b/l:d	()Z
    //   20: ifne +11 -> 31
    //   23: new 95	com/tencent/a/b/r
    //   26: dup
    //   27: invokespecial 96	com/tencent/a/b/r:<init>	()V
    //   30: athrow
    //   31: aload_1
    //   32: iload 6
    //   34: invokestatic 99	com/tencent/a/b/q:a	(Ljava/lang/String;Z)Ljava/net/HttpURLConnection;
    //   37: astore_1
    //   38: aload 10
    //   40: astore_3
    //   41: aload_1
    //   42: astore 7
    //   44: aconst_null
    //   45: invokestatic 104	com/tencent/a/b/b:a	(Ljava/lang/String;)Z
    //   48: ifeq +331 -> 379
    //   51: aload 10
    //   53: astore_3
    //   54: aload_1
    //   55: astore 7
    //   57: aload_1
    //   58: invokevirtual 108	java/net/HttpURLConnection:getURL	()Ljava/net/URL;
    //   61: invokevirtual 113	java/net/URL:getHost	()Ljava/lang/String;
    //   64: invokestatic 104	com/tencent/a/b/b:a	(Ljava/lang/String;)Z
    //   67: ifeq +3 -> 70
    //   70: iload_0
    //   71: ifeq +353 -> 424
    //   74: aload 10
    //   76: astore_3
    //   77: aload_1
    //   78: astore 7
    //   80: aload_1
    //   81: ldc 115
    //   83: invokevirtual 119	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   86: aload 10
    //   88: astore_3
    //   89: aload_1
    //   90: astore 7
    //   92: aload_1
    //   93: invokestatic 124	com/tencent/a/b/k:a	()I
    //   96: invokevirtual 128	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   99: aload 10
    //   101: astore_3
    //   102: aload_1
    //   103: astore 7
    //   105: aload_1
    //   106: invokestatic 130	com/tencent/a/b/k:b	()I
    //   109: invokevirtual 133	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   112: aload 10
    //   114: astore_3
    //   115: aload_1
    //   116: astore 7
    //   118: aload_1
    //   119: ldc 135
    //   121: aload_2
    //   122: invokevirtual 139	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   125: aload 10
    //   127: astore_3
    //   128: aload_1
    //   129: astore 7
    //   131: aload_1
    //   132: iconst_1
    //   133: invokevirtual 143	java/net/HttpURLConnection:setDoInput	(Z)V
    //   136: iload 13
    //   138: istore 6
    //   140: iload_0
    //   141: ifeq +6 -> 147
    //   144: iconst_0
    //   145: istore 6
    //   147: aload 10
    //   149: astore_3
    //   150: aload_1
    //   151: astore 7
    //   153: aload_1
    //   154: iload 6
    //   156: invokevirtual 146	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   159: aload 10
    //   161: astore_3
    //   162: aload_1
    //   163: astore 7
    //   165: aload_1
    //   166: iconst_0
    //   167: invokevirtual 149	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   170: iload 5
    //   172: ifeq +17 -> 189
    //   175: aload 10
    //   177: astore_3
    //   178: aload_1
    //   179: astore 7
    //   181: aload_1
    //   182: ldc 151
    //   184: ldc 153
    //   186: invokevirtual 139	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   189: aload 10
    //   191: astore_3
    //   192: aload_1
    //   193: astore 7
    //   195: aload_1
    //   196: invokestatic 156	com/tencent/a/b/k:a	(Ljava/net/HttpURLConnection;)V
    //   199: aload 10
    //   201: astore_3
    //   202: aload_1
    //   203: astore 7
    //   205: aload_1
    //   206: invokevirtual 52	java/net/HttpURLConnection:connect	()V
    //   209: aload 10
    //   211: astore_3
    //   212: aload_1
    //   213: astore 7
    //   215: invokestatic 159	com/tencent/a/b/k:c	()V
    //   218: iload_0
    //   219: ifne +52 -> 271
    //   222: aload 4
    //   224: ifnull +47 -> 271
    //   227: aload 10
    //   229: astore_3
    //   230: aload_1
    //   231: astore 7
    //   233: aload 4
    //   235: arraylength
    //   236: ifeq +35 -> 271
    //   239: aload 10
    //   241: astore_3
    //   242: aload_1
    //   243: astore 7
    //   245: new 161	java/io/DataOutputStream
    //   248: dup
    //   249: aload_1
    //   250: invokevirtual 165	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   253: invokespecial 168	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   256: astore_2
    //   257: aload_2
    //   258: aload 4
    //   260: invokevirtual 172	java/io/DataOutputStream:write	([B)V
    //   263: aload_2
    //   264: invokevirtual 175	java/io/DataOutputStream:flush	()V
    //   267: aload_2
    //   268: invokevirtual 176	java/io/DataOutputStream:close	()V
    //   271: aload 10
    //   273: astore_3
    //   274: aload_1
    //   275: astore 7
    //   277: aload_1
    //   278: invokevirtual 179	java/net/HttpURLConnection:getResponseCode	()I
    //   281: istore 11
    //   283: iload 11
    //   285: sipush 200
    //   288: if_icmpeq +11 -> 299
    //   291: iload 11
    //   293: sipush 206
    //   296: if_icmpne +173 -> 469
    //   299: aload 10
    //   301: astore_3
    //   302: aload_1
    //   303: astore 7
    //   305: invokestatic 181	com/tencent/a/b/k:d	()V
    //   308: aload 10
    //   310: astore_3
    //   311: aload_1
    //   312: astore 7
    //   314: aload_1
    //   315: iload_0
    //   316: invokestatic 183	com/tencent/a/b/q:a	(Ljava/net/HttpURLConnection;Z)Lcom/tencent/a/b/n;
    //   319: astore_2
    //   320: iload 12
    //   322: istore 11
    //   324: aload_2
    //   325: ifnull +33 -> 358
    //   328: iload 12
    //   330: istore 11
    //   332: aload 10
    //   334: astore_3
    //   335: aload_1
    //   336: astore 7
    //   338: aload_2
    //   339: getfield 59	com/tencent/a/b/n:a	[B
    //   342: ifnull +16 -> 358
    //   345: aload 10
    //   347: astore_3
    //   348: aload_1
    //   349: astore 7
    //   351: aload_2
    //   352: getfield 59	com/tencent/a/b/n:a	[B
    //   355: arraylength
    //   356: istore 11
    //   358: aload 10
    //   360: astore_3
    //   361: aload_1
    //   362: astore 7
    //   364: iload 11
    //   366: invokestatic 185	com/tencent/a/b/k:a	(I)V
    //   369: aload_1
    //   370: ifnull +7 -> 377
    //   373: aload_1
    //   374: invokevirtual 49	java/net/HttpURLConnection:disconnect	()V
    //   377: aload_2
    //   378: areturn
    //   379: aload 10
    //   381: astore_3
    //   382: aload_1
    //   383: astore 7
    //   385: aload_1
    //   386: ldc 187
    //   388: aconst_null
    //   389: invokevirtual 139	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   392: goto -322 -> 70
    //   395: astore_2
    //   396: aload 8
    //   398: astore_3
    //   399: iconst_1
    //   400: invokestatic 189	com/tencent/a/b/k:a	(Z)V
    //   403: aload_2
    //   404: athrow
    //   405: astore_2
    //   406: aload_3
    //   407: ifnull +7 -> 414
    //   410: aload_3
    //   411: invokevirtual 176	java/io/DataOutputStream:close	()V
    //   414: aload_1
    //   415: ifnull +7 -> 422
    //   418: aload_1
    //   419: invokevirtual 49	java/net/HttpURLConnection:disconnect	()V
    //   422: aload_2
    //   423: athrow
    //   424: aload 10
    //   426: astore_3
    //   427: aload_1
    //   428: astore 7
    //   430: aload_1
    //   431: ldc 191
    //   433: invokevirtual 119	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   436: goto -350 -> 86
    //   439: astore_2
    //   440: aload 9
    //   442: astore 4
    //   444: aload 4
    //   446: astore_3
    //   447: aload_1
    //   448: astore 7
    //   450: iconst_0
    //   451: invokestatic 189	com/tencent/a/b/k:a	(Z)V
    //   454: aload 4
    //   456: astore_3
    //   457: aload_1
    //   458: astore 7
    //   460: aload_2
    //   461: athrow
    //   462: astore_2
    //   463: aload 7
    //   465: astore_1
    //   466: goto -60 -> 406
    //   469: iload 11
    //   471: sipush 202
    //   474: if_icmpeq +75 -> 549
    //   477: iload 11
    //   479: sipush 201
    //   482: if_icmpeq +67 -> 549
    //   485: iload 11
    //   487: sipush 204
    //   490: if_icmpeq +59 -> 549
    //   493: iload 11
    //   495: sipush 205
    //   498: if_icmpeq +51 -> 549
    //   501: iload 11
    //   503: sipush 304
    //   506: if_icmpeq +43 -> 549
    //   509: iload 11
    //   511: sipush 305
    //   514: if_icmpeq +35 -> 549
    //   517: iload 11
    //   519: sipush 408
    //   522: if_icmpeq +27 -> 549
    //   525: iload 11
    //   527: sipush 502
    //   530: if_icmpeq +19 -> 549
    //   533: iload 11
    //   535: sipush 504
    //   538: if_icmpeq +11 -> 549
    //   541: iload 11
    //   543: sipush 503
    //   546: if_icmpne +19 -> 565
    //   549: aload 10
    //   551: astore_3
    //   552: aload_1
    //   553: astore 7
    //   555: new 16	java/io/IOException
    //   558: dup
    //   559: ldc 193
    //   561: invokespecial 195	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   564: athrow
    //   565: aload 10
    //   567: astore_3
    //   568: aload_1
    //   569: astore 7
    //   571: new 87	com/tencent/a/b/p
    //   574: dup
    //   575: new 197	java/lang/StringBuilder
    //   578: dup
    //   579: ldc 199
    //   581: invokespecial 200	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   584: iload 11
    //   586: invokevirtual 204	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   589: invokevirtual 207	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   592: invokespecial 208	com/tencent/a/b/p:<init>	(Ljava/lang/String;)V
    //   595: athrow
    //   596: astore_2
    //   597: aconst_null
    //   598: astore_1
    //   599: goto -193 -> 406
    //   602: astore 4
    //   604: aload_2
    //   605: astore_3
    //   606: aload 4
    //   608: astore_2
    //   609: goto -203 -> 406
    //   612: astore_2
    //   613: aconst_null
    //   614: astore_1
    //   615: aload 9
    //   617: astore 4
    //   619: goto -175 -> 444
    //   622: astore_3
    //   623: aload_2
    //   624: astore 4
    //   626: aload_3
    //   627: astore_2
    //   628: goto -184 -> 444
    //   631: astore_2
    //   632: aconst_null
    //   633: astore_1
    //   634: aload 8
    //   636: astore_3
    //   637: goto -238 -> 399
    //   640: astore 4
    //   642: aload_2
    //   643: astore_3
    //   644: aload 4
    //   646: astore_2
    //   647: goto -248 -> 399
    //
    // Exception table:
    //   from	to	target	type
    //   44	51	395	com/tencent/a/b/p
    //   57	70	395	com/tencent/a/b/p
    //   80	86	395	com/tencent/a/b/p
    //   92	99	395	com/tencent/a/b/p
    //   105	112	395	com/tencent/a/b/p
    //   118	125	395	com/tencent/a/b/p
    //   131	136	395	com/tencent/a/b/p
    //   153	159	395	com/tencent/a/b/p
    //   165	170	395	com/tencent/a/b/p
    //   181	189	395	com/tencent/a/b/p
    //   195	199	395	com/tencent/a/b/p
    //   205	209	395	com/tencent/a/b/p
    //   215	218	395	com/tencent/a/b/p
    //   233	239	395	com/tencent/a/b/p
    //   245	257	395	com/tencent/a/b/p
    //   277	283	395	com/tencent/a/b/p
    //   305	308	395	com/tencent/a/b/p
    //   314	320	395	com/tencent/a/b/p
    //   338	345	395	com/tencent/a/b/p
    //   351	358	395	com/tencent/a/b/p
    //   364	369	395	com/tencent/a/b/p
    //   385	392	395	com/tencent/a/b/p
    //   430	436	395	com/tencent/a/b/p
    //   555	565	395	com/tencent/a/b/p
    //   571	596	395	com/tencent/a/b/p
    //   399	405	405	finally
    //   44	51	439	java/lang/Exception
    //   57	70	439	java/lang/Exception
    //   80	86	439	java/lang/Exception
    //   92	99	439	java/lang/Exception
    //   105	112	439	java/lang/Exception
    //   118	125	439	java/lang/Exception
    //   131	136	439	java/lang/Exception
    //   153	159	439	java/lang/Exception
    //   165	170	439	java/lang/Exception
    //   181	189	439	java/lang/Exception
    //   195	199	439	java/lang/Exception
    //   205	209	439	java/lang/Exception
    //   215	218	439	java/lang/Exception
    //   233	239	439	java/lang/Exception
    //   245	257	439	java/lang/Exception
    //   277	283	439	java/lang/Exception
    //   305	308	439	java/lang/Exception
    //   314	320	439	java/lang/Exception
    //   338	345	439	java/lang/Exception
    //   351	358	439	java/lang/Exception
    //   364	369	439	java/lang/Exception
    //   385	392	439	java/lang/Exception
    //   430	436	439	java/lang/Exception
    //   555	565	439	java/lang/Exception
    //   571	596	439	java/lang/Exception
    //   44	51	462	finally
    //   57	70	462	finally
    //   80	86	462	finally
    //   92	99	462	finally
    //   105	112	462	finally
    //   118	125	462	finally
    //   131	136	462	finally
    //   153	159	462	finally
    //   165	170	462	finally
    //   181	189	462	finally
    //   195	199	462	finally
    //   205	209	462	finally
    //   215	218	462	finally
    //   233	239	462	finally
    //   245	257	462	finally
    //   277	283	462	finally
    //   305	308	462	finally
    //   314	320	462	finally
    //   338	345	462	finally
    //   351	358	462	finally
    //   364	369	462	finally
    //   385	392	462	finally
    //   430	436	462	finally
    //   450	454	462	finally
    //   460	462	462	finally
    //   555	565	462	finally
    //   571	596	462	finally
    //   31	38	596	finally
    //   257	271	602	finally
    //   31	38	612	java/lang/Exception
    //   257	271	622	java/lang/Exception
    //   31	38	631	com/tencent/a/b/p
    //   257	271	640	com/tencent/a/b/p
  }

  // ERROR //
  private static HttpURLConnection a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 110	java/net/URL
    //   5: dup
    //   6: aload_0
    //   7: invokespecial 211	java/net/URL:<init>	(Ljava/lang/String;)V
    //   10: astore 4
    //   12: invokestatic 213	com/tencent/a/b/l:c	()Z
    //   15: ifeq +29 -> 44
    //   18: iconst_0
    //   19: istore 6
    //   21: iload 6
    //   23: ifne +53 -> 76
    //   26: aload 4
    //   28: invokevirtual 217	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   31: checkcast 23	java/net/HttpURLConnection
    //   34: astore_0
    //   35: aload_0
    //   36: areturn
    //   37: astore_0
    //   38: aload_0
    //   39: invokevirtual 220	java/net/MalformedURLException:printStackTrace	()V
    //   42: aconst_null
    //   43: areturn
    //   44: invokestatic 225	com/tencent/a/b/m:a	()Lcom/tencent/a/b/m;
    //   47: pop
    //   48: invokestatic 230	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   51: invokestatic 104	com/tencent/a/b/b:a	(Ljava/lang/String;)Z
    //   54: ifeq +9 -> 63
    //   57: iconst_0
    //   58: istore 6
    //   60: goto -39 -> 21
    //   63: iconst_1
    //   64: istore 6
    //   66: goto -45 -> 21
    //   69: astore_0
    //   70: aload_0
    //   71: invokevirtual 231	java/io/IOException:printStackTrace	()V
    //   74: aconst_null
    //   75: areturn
    //   76: getstatic 12	com/tencent/a/b/q:a	I
    //   79: ifne +153 -> 232
    //   82: getstatic 233	com/tencent/a/b/q:b	Z
    //   85: ifne +147 -> 232
    //   88: iconst_1
    //   89: putstatic 233	com/tencent/a/b/q:b	Z
    //   92: new 110	java/net/URL
    //   95: dup
    //   96: ldc 235
    //   98: invokespecial 211	java/net/URL:<init>	(Ljava/lang/String;)V
    //   101: astore_2
    //   102: invokestatic 230	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   105: astore 5
    //   107: invokestatic 238	android/net/Proxy:getDefaultPort	()I
    //   110: istore 7
    //   112: iload 7
    //   114: istore 6
    //   116: iload 7
    //   118: iconst_m1
    //   119: if_icmpne +7 -> 126
    //   122: bipush 80
    //   124: istore 6
    //   126: new 240	java/net/InetSocketAddress
    //   129: dup
    //   130: aload 5
    //   132: iload 6
    //   134: invokespecial 243	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   137: astore 5
    //   139: new 245	java/net/Proxy
    //   142: dup
    //   143: getstatic 251	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   146: aload 5
    //   148: invokespecial 254	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   151: astore 5
    //   153: aload_2
    //   154: aload 5
    //   156: invokevirtual 257	java/net/URL:openConnection	(Ljava/net/Proxy;)Ljava/net/URLConnection;
    //   159: checkcast 23	java/net/HttpURLConnection
    //   162: astore_2
    //   163: aload_2
    //   164: ldc 115
    //   166: invokevirtual 119	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   169: aload_2
    //   170: sipush 15000
    //   173: invokevirtual 128	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   176: aload_2
    //   177: ldc_w 258
    //   180: invokevirtual 133	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   183: aload_2
    //   184: ldc 135
    //   186: ldc_w 260
    //   189: invokevirtual 139	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   192: aload_2
    //   193: iconst_1
    //   194: invokevirtual 143	java/net/HttpURLConnection:setDoInput	(Z)V
    //   197: aload_2
    //   198: iconst_0
    //   199: invokevirtual 146	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   202: aload_2
    //   203: iconst_0
    //   204: invokevirtual 149	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   207: aload_2
    //   208: invokestatic 263	com/tencent/a/b/q:a	(Ljava/net/HttpURLConnection;)Z
    //   211: istore_1
    //   212: aload_2
    //   213: invokevirtual 52	java/net/HttpURLConnection:connect	()V
    //   216: iload_1
    //   217: ifeq +99 -> 316
    //   220: iconst_1
    //   221: invokestatic 264	com/tencent/a/b/q:a	(I)V
    //   224: aload_2
    //   225: ifnull +7 -> 232
    //   228: aload_2
    //   229: invokevirtual 49	java/net/HttpURLConnection:disconnect	()V
    //   232: getstatic 12	com/tencent/a/b/q:a	I
    //   235: tableswitch	default:+151 -> 386, 2:+121->356
    //   253: nop
    //   254: <illegal opcode>
    //   255: astore_0
    //   256: invokestatic 238	android/net/Proxy:getDefaultPort	()I
    //   259: istore 7
    //   261: iload 7
    //   263: istore 6
    //   265: iload 7
    //   267: iconst_m1
    //   268: if_icmpne +7 -> 275
    //   271: bipush 80
    //   273: istore 6
    //   275: new 240	java/net/InetSocketAddress
    //   278: dup
    //   279: aload_0
    //   280: iload 6
    //   282: invokespecial 243	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   285: astore_0
    //   286: aload 4
    //   288: new 245	java/net/Proxy
    //   291: dup
    //   292: getstatic 251	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   295: aload_0
    //   296: invokespecial 254	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   299: invokevirtual 257	java/net/URL:openConnection	(Ljava/net/Proxy;)Ljava/net/URLConnection;
    //   302: checkcast 23	java/net/HttpURLConnection
    //   305: astore_0
    //   306: aload_0
    //   307: areturn
    //   308: astore_2
    //   309: iconst_0
    //   310: putstatic 233	com/tencent/a/b/q:b	Z
    //   313: goto -81 -> 232
    //   316: iconst_2
    //   317: invokestatic 264	com/tencent/a/b/q:a	(I)V
    //   320: goto -96 -> 224
    //   323: astore_3
    //   324: aload_3
    //   325: invokevirtual 265	java/lang/Exception:printStackTrace	()V
    //   328: iconst_2
    //   329: invokestatic 264	com/tencent/a/b/q:a	(I)V
    //   332: aload_2
    //   333: ifnull -101 -> 232
    //   336: aload_2
    //   337: invokevirtual 49	java/net/HttpURLConnection:disconnect	()V
    //   340: goto -108 -> 232
    //   343: astore_0
    //   344: aload_3
    //   345: astore_2
    //   346: aload_2
    //   347: ifnull +7 -> 354
    //   350: aload_2
    //   351: invokevirtual 49	java/net/HttpURLConnection:disconnect	()V
    //   354: aload_0
    //   355: athrow
    //   356: aload 4
    //   358: aload_0
    //   359: invokestatic 268	com/tencent/a/b/q:a	(Ljava/net/URL;Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   362: astore_0
    //   363: aload_0
    //   364: areturn
    //   365: astore_0
    //   366: aload_0
    //   367: invokevirtual 231	java/io/IOException:printStackTrace	()V
    //   370: aconst_null
    //   371: areturn
    //   372: astore_0
    //   373: goto -27 -> 346
    //   376: astore_0
    //   377: goto -31 -> 346
    //   380: astore_3
    //   381: aconst_null
    //   382: astore_2
    //   383: goto -59 -> 324
    //   386: goto -134 -> 252
    //
    // Exception table:
    //   from	to	target	type
    //   2	12	37	java/net/MalformedURLException
    //   26	35	69	java/io/IOException
    //   92	102	308	java/net/MalformedURLException
    //   163	216	323	java/lang/Exception
    //   220	224	323	java/lang/Exception
    //   316	320	323	java/lang/Exception
    //   153	163	343	finally
    //   232	252	365	java/io/IOException
    //   252	261	365	java/io/IOException
    //   275	306	365	java/io/IOException
    //   356	363	365	java/io/IOException
    //   163	216	372	finally
    //   220	224	372	finally
    //   316	320	372	finally
    //   324	332	376	finally
    //   153	163	380	java/lang/Exception
  }

  private static HttpURLConnection a(URL paramURL, String paramString)
    throws IOException
  {
    int j = 80;
    String str2 = Proxy.getDefaultHost();
    int k = Proxy.getDefaultPort();
    int i = k;
    if (k == -1)
      i = 80;
    String str1 = paramURL.getHost();
    k = paramURL.getPort();
    if (k == -1);
    while (true)
    {
      if (paramString.indexOf(str1 + ":" + j) != -1)
        paramURL = paramString.replaceFirst(str1 + ":" + j, str2 + ":" + i);
      try
      {
        while (true)
        {
          paramURL = new URL(paramURL);
          paramURL = (HttpURLConnection)paramURL.openConnection();
          paramURL.setRequestProperty("X-Online-Host", str1 + ":" + j);
          return paramURL;
          paramURL = paramString.replaceFirst(str1, str2 + ":" + i);
        }
      }
      catch (MalformedURLException paramURL)
      {
        return null;
      }
      j = k;
    }
  }

  private static void a(int paramInt)
  {
    if (a == paramInt)
      return;
    a = paramInt;
  }

  private static boolean a(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    Object localObject = null;
    try
    {
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      localObject = localInputStream;
      boolean bool1 = paramHttpURLConnection.getContentType().equals("text/html");
      if (!bool1)
      {
        if (localInputStream != null)
          localInputStream.close();
        bool1 = false;
        return bool1;
      }
      localObject = localInputStream;
      paramHttpURLConnection = new ByteArrayOutputStream();
      while (true)
      {
        localObject = localInputStream;
        if (localInputStream.available() <= 0)
        {
          localObject = localInputStream;
          boolean bool2 = new String(paramHttpURLConnection.toByteArray()).trim().equals("1");
          bool1 = bool2;
          return bool2;
        }
        localObject = localInputStream;
        paramHttpURLConnection.write(localInputStream.read());
      }
    }
    finally
    {
      if (localObject != null)
        localObject.close();
    }
    throw paramHttpURLConnection;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.q
 * JD-Core Version:    0.6.2
 */