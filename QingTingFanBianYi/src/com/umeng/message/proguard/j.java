package com.umeng.message.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class j
{
  private static final String a = "t";
  private static final String b = "t2";
  private String c;
  private String d;
  private boolean e;
  private boolean f;
  private boolean g;
  private SharedPreferences h;
  private i i;
  private SharedPreferences.Editor j;
  private i.a k;
  private Context l;
  private k m;
  private boolean n;

  // ERROR //
  public j(Context paramContext, String paramString1, String paramString2, boolean paramBoolean1, boolean paramBoolean2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 37	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: ldc 39
    //   7: putfield 41	com/umeng/message/proguard/j:c	Ljava/lang/String;
    //   10: aload_0
    //   11: ldc 39
    //   13: putfield 43	com/umeng/message/proguard/j:d	Ljava/lang/String;
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield 45	com/umeng/message/proguard/j:e	Z
    //   21: aload_0
    //   22: iconst_0
    //   23: putfield 47	com/umeng/message/proguard/j:f	Z
    //   26: aload_0
    //   27: iconst_0
    //   28: putfield 49	com/umeng/message/proguard/j:g	Z
    //   31: aload_0
    //   32: aconst_null
    //   33: putfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   36: aload_0
    //   37: aconst_null
    //   38: putfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   41: aload_0
    //   42: aconst_null
    //   43: putfield 55	com/umeng/message/proguard/j:j	Landroid/content/SharedPreferences$Editor;
    //   46: aload_0
    //   47: aconst_null
    //   48: putfield 57	com/umeng/message/proguard/j:k	Lcom/umeng/message/proguard/i$a;
    //   51: aload_0
    //   52: aconst_null
    //   53: putfield 59	com/umeng/message/proguard/j:l	Landroid/content/Context;
    //   56: aload_0
    //   57: aconst_null
    //   58: putfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   61: aload_0
    //   62: iconst_0
    //   63: putfield 63	com/umeng/message/proguard/j:n	Z
    //   66: aload_0
    //   67: iload 4
    //   69: putfield 45	com/umeng/message/proguard/j:e	Z
    //   72: aload_0
    //   73: iload 5
    //   75: putfield 63	com/umeng/message/proguard/j:n	Z
    //   78: aload_0
    //   79: aload_3
    //   80: putfield 41	com/umeng/message/proguard/j:c	Ljava/lang/String;
    //   83: aload_0
    //   84: aload_2
    //   85: putfield 43	com/umeng/message/proguard/j:d	Ljava/lang/String;
    //   88: aload_0
    //   89: aload_1
    //   90: putfield 59	com/umeng/message/proguard/j:l	Landroid/content/Context;
    //   93: lconst_0
    //   94: lstore 7
    //   96: lconst_0
    //   97: lstore 13
    //   99: aload_1
    //   100: ifnull +27 -> 127
    //   103: aload_0
    //   104: aload_1
    //   105: aload_3
    //   106: iconst_0
    //   107: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   110: putfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   113: aload_0
    //   114: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   117: ldc 8
    //   119: lconst_0
    //   120: invokeinterface 75 4 0
    //   125: lstore 7
    //   127: invokestatic 81	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   130: astore 6
    //   132: aload 6
    //   134: invokestatic 86	com/umeng/message/proguard/f:a	(Ljava/lang/String;)Z
    //   137: ifeq +301 -> 438
    //   140: aload_0
    //   141: iconst_0
    //   142: putfield 49	com/umeng/message/proguard/j:g	Z
    //   145: aload_0
    //   146: iconst_0
    //   147: putfield 47	com/umeng/message/proguard/j:f	Z
    //   150: aload_0
    //   151: getfield 47	com/umeng/message/proguard/j:f	Z
    //   154: ifne +18 -> 172
    //   157: lload 7
    //   159: lstore 9
    //   161: lload 13
    //   163: lstore 11
    //   165: aload_0
    //   166: getfield 49	com/umeng/message/proguard/j:g	Z
    //   169: ifeq +143 -> 312
    //   172: lload 7
    //   174: lstore 9
    //   176: lload 13
    //   178: lstore 11
    //   180: aload_1
    //   181: ifnull +131 -> 312
    //   184: lload 7
    //   186: lstore 9
    //   188: lload 13
    //   190: lstore 11
    //   192: aload_2
    //   193: invokestatic 86	com/umeng/message/proguard/f:a	(Ljava/lang/String;)Z
    //   196: ifne +116 -> 312
    //   199: aload_0
    //   200: aload_0
    //   201: aload_2
    //   202: invokespecial 89	com/umeng/message/proguard/j:g	(Ljava/lang/String;)Lcom/umeng/message/proguard/k;
    //   205: putfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   208: lload 7
    //   210: lstore 9
    //   212: lload 13
    //   214: lstore 11
    //   216: aload_0
    //   217: getfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   220: ifnull +92 -> 312
    //   223: lload 13
    //   225: lstore 11
    //   227: aload_0
    //   228: aload_0
    //   229: getfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   232: aload_3
    //   233: iconst_0
    //   234: invokevirtual 94	com/umeng/message/proguard/k:a	(Ljava/lang/String;I)Lcom/umeng/message/proguard/i;
    //   237: putfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   240: lload 13
    //   242: lstore 11
    //   244: aload_0
    //   245: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   248: ldc 8
    //   250: lconst_0
    //   251: invokeinterface 98 4 0
    //   256: lstore 9
    //   258: iload 5
    //   260: ifne +322 -> 582
    //   263: lload 7
    //   265: lload 9
    //   267: lcmp
    //   268: ifle +229 -> 497
    //   271: lload 9
    //   273: lstore 11
    //   275: aload_0
    //   276: aload_0
    //   277: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   280: aload_0
    //   281: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   284: invokespecial 101	com/umeng/message/proguard/j:a	(Landroid/content/SharedPreferences;Lcom/umeng/message/proguard/i;)V
    //   287: lload 9
    //   289: lstore 11
    //   291: aload_0
    //   292: aload_0
    //   293: getfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   296: aload_3
    //   297: iconst_0
    //   298: invokevirtual 94	com/umeng/message/proguard/k:a	(Ljava/lang/String;I)Lcom/umeng/message/proguard/i;
    //   301: putfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   304: lload 9
    //   306: lstore 11
    //   308: lload 7
    //   310: lstore 9
    //   312: lload 9
    //   314: lload 11
    //   316: lcmp
    //   317: ifne +17 -> 334
    //   320: lload 9
    //   322: lconst_0
    //   323: lcmp
    //   324: ifne +113 -> 437
    //   327: lload 11
    //   329: lconst_0
    //   330: lcmp
    //   331: ifne +106 -> 437
    //   334: invokestatic 107	java/lang/System:currentTimeMillis	()J
    //   337: lstore 7
    //   339: aload_0
    //   340: getfield 63	com/umeng/message/proguard/j:n	Z
    //   343: ifeq +24 -> 367
    //   346: aload_0
    //   347: getfield 63	com/umeng/message/proguard/j:n	Z
    //   350: ifeq +87 -> 437
    //   353: lload 9
    //   355: lconst_0
    //   356: lcmp
    //   357: ifne +80 -> 437
    //   360: lload 11
    //   362: lconst_0
    //   363: lcmp
    //   364: ifne +73 -> 437
    //   367: aload_0
    //   368: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   371: ifnull +31 -> 402
    //   374: aload_0
    //   375: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   378: invokeinterface 111 1 0
    //   383: astore_1
    //   384: aload_1
    //   385: ldc 11
    //   387: lload 7
    //   389: invokeinterface 117 4 0
    //   394: pop
    //   395: aload_1
    //   396: invokeinterface 121 1 0
    //   401: pop
    //   402: aload_0
    //   403: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   406: ifnull +31 -> 437
    //   409: aload_0
    //   410: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   413: invokeinterface 124 1 0
    //   418: astore_1
    //   419: aload_1
    //   420: ldc 11
    //   422: lload 7
    //   424: invokeinterface 129 4 0
    //   429: pop
    //   430: aload_1
    //   431: invokeinterface 131 1 0
    //   436: pop
    //   437: return
    //   438: aload 6
    //   440: ldc 133
    //   442: invokevirtual 139	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   445: ifeq +16 -> 461
    //   448: aload_0
    //   449: iconst_1
    //   450: putfield 49	com/umeng/message/proguard/j:g	Z
    //   453: aload_0
    //   454: iconst_1
    //   455: putfield 47	com/umeng/message/proguard/j:f	Z
    //   458: goto -308 -> 150
    //   461: aload 6
    //   463: ldc 141
    //   465: invokevirtual 139	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   468: ifeq +16 -> 484
    //   471: aload_0
    //   472: iconst_1
    //   473: putfield 47	com/umeng/message/proguard/j:f	Z
    //   476: aload_0
    //   477: iconst_0
    //   478: putfield 49	com/umeng/message/proguard/j:g	Z
    //   481: goto -331 -> 150
    //   484: aload_0
    //   485: iconst_0
    //   486: putfield 49	com/umeng/message/proguard/j:g	Z
    //   489: aload_0
    //   490: iconst_0
    //   491: putfield 47	com/umeng/message/proguard/j:f	Z
    //   494: goto -344 -> 150
    //   497: lload 7
    //   499: lload 9
    //   501: lcmp
    //   502: ifge +36 -> 538
    //   505: lload 9
    //   507: lstore 11
    //   509: aload_0
    //   510: aload_0
    //   511: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   514: aload_0
    //   515: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   518: invokespecial 144	com/umeng/message/proguard/j:a	(Lcom/umeng/message/proguard/i;Landroid/content/SharedPreferences;)V
    //   521: lload 9
    //   523: lstore 11
    //   525: aload_0
    //   526: aload_1
    //   527: aload_3
    //   528: iconst_0
    //   529: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   532: putfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   535: goto -231 -> 304
    //   538: lload 7
    //   540: lload 9
    //   542: lcmp
    //   543: ifne +305 -> 848
    //   546: lload 9
    //   548: lstore 11
    //   550: aload_0
    //   551: aload_0
    //   552: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   555: aload_0
    //   556: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   559: invokespecial 101	com/umeng/message/proguard/j:a	(Landroid/content/SharedPreferences;Lcom/umeng/message/proguard/i;)V
    //   562: lload 9
    //   564: lstore 11
    //   566: aload_0
    //   567: aload_0
    //   568: getfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   571: aload_3
    //   572: iconst_0
    //   573: invokevirtual 94	com/umeng/message/proguard/k:a	(Ljava/lang/String;I)Lcom/umeng/message/proguard/i;
    //   576: putfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   579: goto -275 -> 304
    //   582: lload 9
    //   584: lstore 11
    //   586: aload_0
    //   587: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   590: ldc 11
    //   592: lconst_0
    //   593: invokeinterface 75 4 0
    //   598: lstore 13
    //   600: lload 13
    //   602: lstore 7
    //   604: aload_0
    //   605: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   608: ldc 11
    //   610: lconst_0
    //   611: invokeinterface 98 4 0
    //   616: lstore 11
    //   618: lload 11
    //   620: lstore 9
    //   622: lload 7
    //   624: lload 9
    //   626: lcmp
    //   627: ifge +38 -> 665
    //   630: lload 7
    //   632: lconst_0
    //   633: lcmp
    //   634: ifle +31 -> 665
    //   637: aload_0
    //   638: aload_0
    //   639: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   642: aload_0
    //   643: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   646: invokespecial 101	com/umeng/message/proguard/j:a	(Landroid/content/SharedPreferences;Lcom/umeng/message/proguard/i;)V
    //   649: aload_0
    //   650: aload_0
    //   651: getfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   654: aload_3
    //   655: iconst_0
    //   656: invokevirtual 94	com/umeng/message/proguard/k:a	(Ljava/lang/String;I)Lcom/umeng/message/proguard/i;
    //   659: putfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   662: goto -358 -> 304
    //   665: lload 7
    //   667: lload 9
    //   669: lcmp
    //   670: ifle +35 -> 705
    //   673: lload 9
    //   675: lconst_0
    //   676: lcmp
    //   677: ifle +28 -> 705
    //   680: aload_0
    //   681: aload_0
    //   682: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   685: aload_0
    //   686: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   689: invokespecial 144	com/umeng/message/proguard/j:a	(Lcom/umeng/message/proguard/i;Landroid/content/SharedPreferences;)V
    //   692: aload_0
    //   693: aload_1
    //   694: aload_3
    //   695: iconst_0
    //   696: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   699: putfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   702: goto -398 -> 304
    //   705: lload 7
    //   707: lconst_0
    //   708: lcmp
    //   709: ifne +35 -> 744
    //   712: lload 9
    //   714: lconst_0
    //   715: lcmp
    //   716: ifle +28 -> 744
    //   719: aload_0
    //   720: aload_0
    //   721: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   724: aload_0
    //   725: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   728: invokespecial 144	com/umeng/message/proguard/j:a	(Lcom/umeng/message/proguard/i;Landroid/content/SharedPreferences;)V
    //   731: aload_0
    //   732: aload_1
    //   733: aload_3
    //   734: iconst_0
    //   735: invokevirtual 69	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   738: putfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   741: goto -437 -> 304
    //   744: lload 9
    //   746: lconst_0
    //   747: lcmp
    //   748: ifne +38 -> 786
    //   751: lload 7
    //   753: lconst_0
    //   754: lcmp
    //   755: ifle +31 -> 786
    //   758: aload_0
    //   759: aload_0
    //   760: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   763: aload_0
    //   764: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   767: invokespecial 101	com/umeng/message/proguard/j:a	(Landroid/content/SharedPreferences;Lcom/umeng/message/proguard/i;)V
    //   770: aload_0
    //   771: aload_0
    //   772: getfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   775: aload_3
    //   776: iconst_0
    //   777: invokevirtual 94	com/umeng/message/proguard/k:a	(Ljava/lang/String;I)Lcom/umeng/message/proguard/i;
    //   780: putfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   783: goto -479 -> 304
    //   786: lload 7
    //   788: lload 9
    //   790: lcmp
    //   791: ifne +28 -> 819
    //   794: aload_0
    //   795: aload_0
    //   796: getfield 51	com/umeng/message/proguard/j:h	Landroid/content/SharedPreferences;
    //   799: aload_0
    //   800: getfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   803: invokespecial 101	com/umeng/message/proguard/j:a	(Landroid/content/SharedPreferences;Lcom/umeng/message/proguard/i;)V
    //   806: aload_0
    //   807: aload_0
    //   808: getfield 61	com/umeng/message/proguard/j:m	Lcom/umeng/message/proguard/k;
    //   811: aload_3
    //   812: iconst_0
    //   813: invokevirtual 94	com/umeng/message/proguard/k:a	(Ljava/lang/String;I)Lcom/umeng/message/proguard/i;
    //   816: putfield 53	com/umeng/message/proguard/j:i	Lcom/umeng/message/proguard/i;
    //   819: goto -515 -> 304
    //   822: astore_1
    //   823: lload 11
    //   825: lstore 9
    //   827: lload 9
    //   829: lstore 11
    //   831: lload 7
    //   833: lstore 9
    //   835: goto -523 -> 312
    //   838: astore_1
    //   839: return
    //   840: astore_1
    //   841: goto -14 -> 827
    //   844: astore_1
    //   845: goto -18 -> 827
    //   848: goto -544 -> 304
    //
    // Exception table:
    //   from	to	target	type
    //   227	240	822	java/lang/Exception
    //   244	258	822	java/lang/Exception
    //   275	287	822	java/lang/Exception
    //   291	304	822	java/lang/Exception
    //   509	521	822	java/lang/Exception
    //   525	535	822	java/lang/Exception
    //   550	562	822	java/lang/Exception
    //   566	579	822	java/lang/Exception
    //   586	600	822	java/lang/Exception
    //   402	437	838	java/lang/Exception
    //   604	618	840	java/lang/Exception
    //   637	662	844	java/lang/Exception
    //   680	702	844	java/lang/Exception
    //   719	741	844	java/lang/Exception
    //   758	783	844	java/lang/Exception
    //   794	819	844	java/lang/Exception
  }

  private void a(SharedPreferences paramSharedPreferences, i parami)
  {
    if ((paramSharedPreferences != null) && (parami != null))
    {
      parami = parami.c();
      if (parami != null)
      {
        parami.a();
        paramSharedPreferences = paramSharedPreferences.getAll().entrySet().iterator();
        while (paramSharedPreferences.hasNext())
        {
          Object localObject = (Map.Entry)paramSharedPreferences.next();
          String str = (String)((Map.Entry)localObject).getKey();
          localObject = ((Map.Entry)localObject).getValue();
          if ((localObject instanceof String))
            parami.a(str, (String)localObject);
          else if ((localObject instanceof Integer))
            parami.a(str, ((Integer)localObject).intValue());
          else if ((localObject instanceof Long))
            parami.a(str, ((Long)localObject).longValue());
          else if ((localObject instanceof Float))
            parami.a(str, ((Float)localObject).floatValue());
          else if ((localObject instanceof Boolean))
            parami.a(str, ((Boolean)localObject).booleanValue());
        }
        parami.b();
      }
    }
  }

  private void a(i parami, SharedPreferences paramSharedPreferences)
  {
    if ((parami != null) && (paramSharedPreferences != null))
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      if (paramSharedPreferences != null)
      {
        paramSharedPreferences.clear();
        parami = parami.b().entrySet().iterator();
        while (parami.hasNext())
        {
          Object localObject = (Map.Entry)parami.next();
          String str = (String)((Map.Entry)localObject).getKey();
          localObject = ((Map.Entry)localObject).getValue();
          if ((localObject instanceof String))
            paramSharedPreferences.putString(str, (String)localObject);
          else if ((localObject instanceof Integer))
            paramSharedPreferences.putInt(str, ((Integer)localObject).intValue());
          else if ((localObject instanceof Long))
            paramSharedPreferences.putLong(str, ((Long)localObject).longValue());
          else if ((localObject instanceof Float))
            paramSharedPreferences.putFloat(str, ((Float)localObject).floatValue());
          else if ((localObject instanceof Boolean))
            paramSharedPreferences.putBoolean(str, ((Boolean)localObject).booleanValue());
        }
        paramSharedPreferences.commit();
      }
    }
  }

  private boolean e()
  {
    if (this.i != null)
    {
      boolean bool = this.i.a();
      if (!bool)
        c();
      return bool;
    }
    return false;
  }

  private void f()
  {
    if ((this.j == null) && (this.h != null))
      this.j = this.h.edit();
    if ((this.g) && (this.k == null) && (this.i != null))
      this.k = this.i.c();
    e();
  }

  private k g(String paramString)
  {
    paramString = h(paramString);
    if (paramString != null)
    {
      this.m = new k(paramString.getAbsolutePath());
      return this.m;
    }
    return null;
  }

  private File h(String paramString)
  {
    File localFile = Environment.getExternalStorageDirectory();
    if (localFile != null)
    {
      paramString = new File(String.format("%s%s%s", new Object[] { localFile.getAbsolutePath(), File.separator, paramString }));
      if ((paramString != null) && (!paramString.exists()))
        paramString.mkdirs();
      return paramString;
    }
    return null;
  }

  public void a()
  {
    if ((this.h != null) && (this.l != null))
      this.h = this.l.getSharedPreferences(this.c, 0);
    String str = Environment.getExternalStorageState();
    if ((!f.a(str)) && ((str.equals("mounted")) || ((str.equals("mounted_ro")) && (this.i != null))));
    try
    {
      if (this.m != null)
        this.i = this.m.a(this.c, 0);
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void a(String paramString)
  {
    if ((!f.a(paramString)) && (!paramString.equals("t")))
    {
      f();
      if (this.j != null)
        this.j.remove(paramString);
      if (this.k != null)
        this.k.a(paramString);
    }
  }

  public void a(String paramString, float paramFloat)
  {
    if ((!f.a(paramString)) && (!paramString.equals("t")))
    {
      f();
      if (this.j != null)
        this.j.putFloat(paramString, paramFloat);
      if (this.k != null)
        this.k.a(paramString, paramFloat);
    }
  }

  public void a(String paramString, int paramInt)
  {
    if ((!f.a(paramString)) && (!paramString.equals("t")))
    {
      f();
      if (this.j != null)
        this.j.putInt(paramString, paramInt);
      if (this.k != null)
        this.k.a(paramString, paramInt);
    }
  }

  public void a(String paramString, long paramLong)
  {
    if ((!f.a(paramString)) && (!paramString.equals("t")))
    {
      f();
      if (this.j != null)
        this.j.putLong(paramString, paramLong);
      if (this.k != null)
        this.k.a(paramString, paramLong);
    }
  }

  public void a(String paramString1, String paramString2)
  {
    if ((!f.a(paramString1)) && (!paramString1.equals("t")))
    {
      f();
      if (this.j != null)
        this.j.putString(paramString1, paramString2);
      if (this.k != null)
        this.k.a(paramString1, paramString2);
    }
  }

  public void a(String paramString, boolean paramBoolean)
  {
    if ((!f.a(paramString)) && (!paramString.equals("t")))
    {
      f();
      if (this.j != null)
        this.j.putBoolean(paramString, paramBoolean);
      if (this.k != null)
        this.k.a(paramString, paramBoolean);
    }
  }

  public String b(String paramString)
  {
    e();
    if (this.h != null)
    {
      String str = this.h.getString(paramString, "");
      if (!f.a(str))
        return str;
    }
    if (this.i != null)
      return this.i.a(paramString, "");
    return "";
  }

  public void b()
  {
    f();
    long l1 = System.currentTimeMillis();
    if (this.j != null)
    {
      this.j.clear();
      this.j.putLong("t", l1);
    }
    if (this.k != null)
    {
      this.k.a();
      this.k.a("t", l1);
    }
  }

  public int c(String paramString)
  {
    int i1 = 0;
    e();
    if (this.h != null)
      i1 = this.h.getInt(paramString, 0);
    while (this.i == null)
      return i1;
    return this.i.a(paramString, 0);
  }

  public boolean c()
  {
    boolean bool2 = true;
    long l1 = System.currentTimeMillis();
    boolean bool1 = bool2;
    if (this.j != null)
    {
      if ((!this.n) && (this.h != null))
        this.j.putLong("t", l1);
      bool1 = bool2;
      if (!this.j.commit())
        bool1 = false;
    }
    if ((this.h != null) && (this.l != null))
      this.h = this.l.getSharedPreferences(this.c, 0);
    String str = Environment.getExternalStorageState();
    boolean bool3 = bool1;
    if (!f.a(str))
    {
      bool2 = bool1;
      if (str.equals("mounted"))
      {
        if (this.i != null)
          break label277;
        k localk = g(this.d);
        bool2 = bool1;
        if (localk != null)
        {
          this.i = localk.a(this.c, 0);
          if (this.n)
            break label262;
          a(this.h, this.i);
        }
      }
    }
    while (true)
    {
      this.k = this.i.c();
      bool2 = bool1;
      label195: if (!str.equals("mounted"))
      {
        bool3 = bool2;
        if (str.equals("mounted_ro"))
        {
          bool3 = bool2;
          if (this.i == null);
        }
      }
      else
      {
        bool3 = bool2;
      }
      try
      {
        if (this.m != null)
        {
          this.i = this.m.a(this.c, 0);
          bool3 = bool2;
        }
        return bool3;
        label262: a(this.i, this.h);
        continue;
        label277: bool2 = bool1;
        if (this.k == null)
          break label195;
        bool2 = bool1;
        if (this.k.b())
          break label195;
        bool2 = false;
      }
      catch (Exception localException)
      {
      }
    }
    return bool2;
  }

  public long d(String paramString)
  {
    long l1 = 0L;
    e();
    if (this.h != null)
      l1 = this.h.getLong(paramString, 0L);
    while (this.i == null)
      return l1;
    return this.i.a(paramString, 0L);
  }

  public Map<String, ?> d()
  {
    e();
    if (this.h != null)
      return this.h.getAll();
    if (this.i != null)
      return this.i.b();
    return null;
  }

  public float e(String paramString)
  {
    float f1 = 0.0F;
    e();
    if (this.h != null)
      f1 = this.h.getFloat(paramString, 0.0F);
    while (this.i == null)
      return f1;
    return this.i.a(paramString, 0.0F);
  }

  public boolean f(String paramString)
  {
    boolean bool = false;
    e();
    if (this.h != null)
      bool = this.h.getBoolean(paramString, false);
    while (this.i == null)
      return bool;
    return this.i.a(paramString, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.j
 * JD-Core Version:    0.6.2
 */