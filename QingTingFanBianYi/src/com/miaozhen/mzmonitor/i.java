package com.miaozhen.mzmonitor;

import android.content.Context;
import java.net.HttpURLConnection;
import java.net.URL;

final class i
{
  private a a;
  private Context b;

  public i(Context paramContext, a parama)
  {
    this.b = paramContext.getApplicationContext();
    this.a = parama;
  }

  private static void a(String paramString)
  {
    if (paramString != null);
    try
    {
      paramString = new URL(paramString);
      HttpURLConnection.setFollowRedirects(true);
      paramString = (HttpURLConnection)paramString.openConnection();
      if (paramString != null)
      {
        paramString.setDefaultUseCaches(false);
        paramString.setUseCaches(false);
        paramString.setConnectTimeout(5000);
        paramString.setReadTimeout(5000);
        paramString.connect();
        paramString.getResponseCode();
        paramString.disconnect();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  // ERROR //
  public final void a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: aconst_null
    //   4: astore 12
    //   6: aconst_null
    //   7: astore 11
    //   9: aconst_null
    //   10: astore 7
    //   12: aconst_null
    //   13: astore 5
    //   15: aconst_null
    //   16: astore 4
    //   18: aconst_null
    //   19: astore 13
    //   21: aconst_null
    //   22: astore 8
    //   24: aconst_null
    //   25: astore 9
    //   27: aconst_null
    //   28: astore 6
    //   30: aload_0
    //   31: getfield 21	com/miaozhen/mzmonitor/i:b	Landroid/content/Context;
    //   34: invokestatic 76	com/miaozhen/mzmonitor/b:a	(Landroid/content/Context;)Lcom/miaozhen/mzmonitor/b;
    //   37: astore 14
    //   39: aload 12
    //   41: astore_1
    //   42: aload 13
    //   44: astore_2
    //   45: aload_0
    //   46: getfield 21	com/miaozhen/mzmonitor/i:b	Landroid/content/Context;
    //   49: invokestatic 81	com/miaozhen/mzmonitor/c:a	(Landroid/content/Context;)Lcom/miaozhen/mzmonitor/c;
    //   52: aload_0
    //   53: getfield 23	com/miaozhen/mzmonitor/i:a	Lcom/miaozhen/mzmonitor/a;
    //   56: invokevirtual 84	com/miaozhen/mzmonitor/c:a	(Lcom/miaozhen/mzmonitor/a;)Ljava/net/URL;
    //   59: astore 15
    //   61: aload 12
    //   63: astore_1
    //   64: aload 13
    //   66: astore_2
    //   67: aload 15
    //   69: invokevirtual 41	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   72: checkcast 33	java/net/HttpURLConnection
    //   75: astore_3
    //   76: aload 12
    //   78: astore_1
    //   79: aload 13
    //   81: astore_2
    //   82: aload_0
    //   83: getfield 21	com/miaozhen/mzmonitor/i:b	Landroid/content/Context;
    //   86: invokestatic 89	com/miaozhen/mzmonitor/f:a	(Landroid/content/Context;)Lcom/miaozhen/mzmonitor/f;
    //   89: invokevirtual 93	com/miaozhen/mzmonitor/f:l	()Ljava/lang/String;
    //   92: ldc 95
    //   94: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   97: ifeq +281 -> 378
    //   100: sipush 5000
    //   103: istore 17
    //   105: aload 12
    //   107: astore_1
    //   108: aload 13
    //   110: astore_2
    //   111: aload_3
    //   112: iload 17
    //   114: invokevirtual 54	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   117: aload 12
    //   119: astore_1
    //   120: aload 13
    //   122: astore_2
    //   123: aload_3
    //   124: iload 17
    //   126: invokevirtual 51	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   129: aload 12
    //   131: astore_1
    //   132: aload 13
    //   134: astore_2
    //   135: aload_3
    //   136: iconst_0
    //   137: invokevirtual 47	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   140: aload 12
    //   142: astore_1
    //   143: aload 13
    //   145: astore_2
    //   146: aload_3
    //   147: iconst_0
    //   148: invokevirtual 104	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   151: aload 12
    //   153: astore_1
    //   154: aload 13
    //   156: astore_2
    //   157: aload_0
    //   158: getfield 21	com/miaozhen/mzmonitor/i:b	Landroid/content/Context;
    //   161: invokestatic 81	com/miaozhen/mzmonitor/c:a	(Landroid/content/Context;)Lcom/miaozhen/mzmonitor/c;
    //   164: aload 15
    //   166: invokevirtual 107	com/miaozhen/mzmonitor/c:a	(Ljava/net/URL;)Lcom/miaozhen/mzmonitor/c$b;
    //   169: getfield 112	com/miaozhen/mzmonitor/c$b:a	Ljava/lang/String;
    //   172: astore 16
    //   174: aload 16
    //   176: ifnull +39 -> 215
    //   179: aload 12
    //   181: astore_1
    //   182: aload 13
    //   184: astore_2
    //   185: aload 16
    //   187: ldc 114
    //   189: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   192: ifeq +23 -> 215
    //   195: aload 12
    //   197: astore_1
    //   198: aload 13
    //   200: astore_2
    //   201: aload_3
    //   202: ldc 116
    //   204: aload 15
    //   206: invokevirtual 119	java/net/URL:toString	()Ljava/lang/String;
    //   209: invokestatic 124	com/miaozhen/mzmonitor/c$a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   212: invokevirtual 128	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   215: aload 6
    //   217: astore_1
    //   218: aload_3
    //   219: ifnull +106 -> 325
    //   222: aload 7
    //   224: astore 5
    //   226: aload 8
    //   228: astore_2
    //   229: aload 9
    //   231: astore 4
    //   233: aload_3
    //   234: invokevirtual 57	java/net/HttpURLConnection:connect	()V
    //   237: aload 7
    //   239: astore 5
    //   241: aload 8
    //   243: astore_2
    //   244: aload 9
    //   246: astore 4
    //   248: aload_3
    //   249: invokevirtual 61	java/net/HttpURLConnection:getResponseCode	()I
    //   252: istore 17
    //   254: iload 17
    //   256: iflt +130 -> 386
    //   259: iload 17
    //   261: sipush 400
    //   264: if_icmpge +122 -> 386
    //   267: aload 7
    //   269: astore 5
    //   271: aload 8
    //   273: astore_2
    //   274: aload 9
    //   276: astore 4
    //   278: ldc 130
    //   280: ldc 132
    //   282: invokestatic 138	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   285: pop
    //   286: aload 7
    //   288: astore 5
    //   290: aload 8
    //   292: astore_2
    //   293: aload 9
    //   295: astore 4
    //   297: aload 14
    //   299: aload_0
    //   300: getfield 23	com/miaozhen/mzmonitor/i:a	Lcom/miaozhen/mzmonitor/a;
    //   303: iconst_1
    //   304: invokevirtual 141	com/miaozhen/mzmonitor/b:a	(Lcom/miaozhen/mzmonitor/a;Z)V
    //   307: aload 7
    //   309: astore 5
    //   311: aload 8
    //   313: astore_2
    //   314: aload 9
    //   316: astore 4
    //   318: aload_3
    //   319: ldc 143
    //   321: invokevirtual 146	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   324: astore_1
    //   325: aload_1
    //   326: astore 5
    //   328: aload_1
    //   329: astore_2
    //   330: aload_1
    //   331: astore 4
    //   333: aload_0
    //   334: getfield 21	com/miaozhen/mzmonitor/i:b	Landroid/content/Context;
    //   337: invokestatic 152	com/miaozhen/mzmonitor/j:j	(Landroid/content/Context;)Z
    //   340: ifeq +25 -> 365
    //   343: aload_1
    //   344: astore 5
    //   346: aload_1
    //   347: astore_2
    //   348: aload_1
    //   349: astore 4
    //   351: aload_0
    //   352: getfield 21	com/miaozhen/mzmonitor/i:b	Landroid/content/Context;
    //   355: invokestatic 157	com/miaozhen/mzmonitor/g:a	(Landroid/content/Context;)Lcom/miaozhen/mzmonitor/g;
    //   358: aload_0
    //   359: getfield 21	com/miaozhen/mzmonitor/i:b	Landroid/content/Context;
    //   362: invokevirtual 160	com/miaozhen/mzmonitor/g:b	(Landroid/content/Context;)V
    //   365: aload_1
    //   366: invokestatic 162	com/miaozhen/mzmonitor/i:a	(Ljava/lang/String;)V
    //   369: aload_3
    //   370: ifnull +7 -> 377
    //   373: aload_3
    //   374: invokevirtual 64	java/net/HttpURLConnection:disconnect	()V
    //   377: return
    //   378: sipush 10000
    //   381: istore 17
    //   383: goto -278 -> 105
    //   386: aload 7
    //   388: astore 5
    //   390: aload 8
    //   392: astore_2
    //   393: aload 9
    //   395: astore 4
    //   397: ldc 130
    //   399: ldc 164
    //   401: invokestatic 138	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   404: pop
    //   405: aload 7
    //   407: astore 5
    //   409: aload 8
    //   411: astore_2
    //   412: aload 9
    //   414: astore 4
    //   416: aload 14
    //   418: aload_0
    //   419: getfield 23	com/miaozhen/mzmonitor/i:a	Lcom/miaozhen/mzmonitor/a;
    //   422: iconst_0
    //   423: invokevirtual 141	com/miaozhen/mzmonitor/b:a	(Lcom/miaozhen/mzmonitor/a;Z)V
    //   426: aload 6
    //   428: astore_1
    //   429: goto -104 -> 325
    //   432: astore 4
    //   434: aload_3
    //   435: astore_1
    //   436: aload 5
    //   438: astore_2
    //   439: aload 4
    //   441: invokevirtual 165	java/io/IOException:printStackTrace	()V
    //   444: aload_3
    //   445: astore_1
    //   446: aload 5
    //   448: astore_2
    //   449: ldc 130
    //   451: new 167	java/lang/StringBuilder
    //   454: dup
    //   455: ldc 169
    //   457: invokespecial 170	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   460: aload 4
    //   462: invokevirtual 173	java/io/IOException:getMessage	()Ljava/lang/String;
    //   465: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: invokestatic 138	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   474: pop
    //   475: aload_3
    //   476: astore_1
    //   477: aload 5
    //   479: astore_2
    //   480: aload 14
    //   482: aload_0
    //   483: getfield 23	com/miaozhen/mzmonitor/i:a	Lcom/miaozhen/mzmonitor/a;
    //   486: iconst_0
    //   487: invokevirtual 141	com/miaozhen/mzmonitor/b:a	(Lcom/miaozhen/mzmonitor/a;Z)V
    //   490: aload 5
    //   492: invokestatic 162	com/miaozhen/mzmonitor/i:a	(Ljava/lang/String;)V
    //   495: aload_3
    //   496: ifnull -119 -> 377
    //   499: aload_3
    //   500: invokevirtual 64	java/net/HttpURLConnection:disconnect	()V
    //   503: return
    //   504: astore 5
    //   506: aload 10
    //   508: astore_3
    //   509: aload_3
    //   510: astore_1
    //   511: aload 4
    //   513: astore_2
    //   514: aload 5
    //   516: invokevirtual 179	java/lang/NullPointerException:printStackTrace	()V
    //   519: aload_3
    //   520: astore_1
    //   521: aload 4
    //   523: astore_2
    //   524: ldc 130
    //   526: new 167	java/lang/StringBuilder
    //   529: dup
    //   530: ldc 181
    //   532: invokespecial 170	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   535: aload_0
    //   536: getfield 23	com/miaozhen/mzmonitor/i:a	Lcom/miaozhen/mzmonitor/a;
    //   539: invokevirtual 185	com/miaozhen/mzmonitor/a:a	()Ljava/lang/String;
    //   542: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: invokevirtual 178	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   548: invokestatic 138	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   551: pop
    //   552: aload 4
    //   554: invokestatic 162	com/miaozhen/mzmonitor/i:a	(Ljava/lang/String;)V
    //   557: aload_3
    //   558: ifnull -181 -> 377
    //   561: aload_3
    //   562: invokevirtual 64	java/net/HttpURLConnection:disconnect	()V
    //   565: return
    //   566: astore_3
    //   567: aload_2
    //   568: invokestatic 162	com/miaozhen/mzmonitor/i:a	(Ljava/lang/String;)V
    //   571: aload_1
    //   572: ifnull +7 -> 579
    //   575: aload_1
    //   576: invokevirtual 64	java/net/HttpURLConnection:disconnect	()V
    //   579: aload_3
    //   580: athrow
    //   581: astore 4
    //   583: aload_3
    //   584: astore_1
    //   585: aload 4
    //   587: astore_3
    //   588: goto -21 -> 567
    //   591: astore 5
    //   593: goto -84 -> 509
    //   596: astore 4
    //   598: aload 11
    //   600: astore_3
    //   601: goto -167 -> 434
    //
    // Exception table:
    //   from	to	target	type
    //   233	237	432	java/io/IOException
    //   248	254	432	java/io/IOException
    //   278	286	432	java/io/IOException
    //   297	307	432	java/io/IOException
    //   318	325	432	java/io/IOException
    //   333	343	432	java/io/IOException
    //   351	365	432	java/io/IOException
    //   397	405	432	java/io/IOException
    //   416	426	432	java/io/IOException
    //   45	61	504	java/lang/NullPointerException
    //   67	76	504	java/lang/NullPointerException
    //   82	100	504	java/lang/NullPointerException
    //   111	117	504	java/lang/NullPointerException
    //   123	129	504	java/lang/NullPointerException
    //   135	140	504	java/lang/NullPointerException
    //   146	151	504	java/lang/NullPointerException
    //   157	174	504	java/lang/NullPointerException
    //   185	195	504	java/lang/NullPointerException
    //   201	215	504	java/lang/NullPointerException
    //   45	61	566	finally
    //   67	76	566	finally
    //   82	100	566	finally
    //   111	117	566	finally
    //   123	129	566	finally
    //   135	140	566	finally
    //   146	151	566	finally
    //   157	174	566	finally
    //   185	195	566	finally
    //   201	215	566	finally
    //   439	444	566	finally
    //   449	475	566	finally
    //   480	490	566	finally
    //   514	519	566	finally
    //   524	552	566	finally
    //   233	237	581	finally
    //   248	254	581	finally
    //   278	286	581	finally
    //   297	307	581	finally
    //   318	325	581	finally
    //   333	343	581	finally
    //   351	365	581	finally
    //   397	405	581	finally
    //   416	426	581	finally
    //   233	237	591	java/lang/NullPointerException
    //   248	254	591	java/lang/NullPointerException
    //   278	286	591	java/lang/NullPointerException
    //   297	307	591	java/lang/NullPointerException
    //   318	325	591	java/lang/NullPointerException
    //   333	343	591	java/lang/NullPointerException
    //   351	365	591	java/lang/NullPointerException
    //   397	405	591	java/lang/NullPointerException
    //   416	426	591	java/lang/NullPointerException
    //   45	61	596	java/io/IOException
    //   67	76	596	java/io/IOException
    //   82	100	596	java/io/IOException
    //   111	117	596	java/io/IOException
    //   123	129	596	java/io/IOException
    //   135	140	596	java/io/IOException
    //   146	151	596	java/io/IOException
    //   157	174	596	java/io/IOException
    //   185	195	596	java/io/IOException
    //   201	215	596	java/io/IOException
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.i
 * JD-Core Version:    0.6.2
 */