package com.miaozhen.mzmonitor;

import android.content.Context;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;

class c
{
  private static c a;
  private Context b;
  private c c;
  private String d;

  private c(Context paramContext)
  {
    this.b = paramContext;
  }

  // ERROR //
  private c a(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 12	com/miaozhen/mzmonitor/c$c
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 36	com/miaozhen/mzmonitor/c$c:<init>	(Lcom/miaozhen/mzmonitor/c;)V
    //   8: astore 6
    //   10: iconst_0
    //   11: istore 8
    //   13: invokestatic 42	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   16: astore 7
    //   18: aload 7
    //   20: aload_1
    //   21: ldc 44
    //   23: invokeinterface 50 3 0
    //   28: aload 7
    //   30: invokeinterface 54 1 0
    //   35: istore 9
    //   37: aconst_null
    //   38: astore_3
    //   39: aconst_null
    //   40: astore_2
    //   41: aconst_null
    //   42: astore 4
    //   44: iload 9
    //   46: iconst_1
    //   47: if_icmpne +19 -> 66
    //   50: aload_0
    //   51: aconst_null
    //   52: putfield 56	com/miaozhen/mzmonitor/c:d	Ljava/lang/String;
    //   55: aload_1
    //   56: ifnull +1067 -> 1123
    //   59: aload_1
    //   60: invokevirtual 61	java/io/InputStream:close	()V
    //   63: aload 6
    //   65: areturn
    //   66: iload 9
    //   68: tableswitch	default:+32 -> 100, 0:+60->128, 1:+32->100, 2:+71->139, 3:+848->916
    //   101: astore 5
    //   103: aload_2
    //   104: astore_3
    //   105: aload 5
    //   107: astore_2
    //   108: aload 7
    //   110: invokeinterface 64 1 0
    //   115: istore 9
    //   117: aload_2
    //   118: astore 5
    //   120: aload_3
    //   121: astore_2
    //   122: aload 5
    //   124: astore_3
    //   125: goto -81 -> 44
    //   128: aload_3
    //   129: astore 5
    //   131: aload_2
    //   132: astore_3
    //   133: aload 5
    //   135: astore_2
    //   136: goto -28 -> 108
    //   139: aload_0
    //   140: aload 7
    //   142: invokeinterface 68 1 0
    //   147: putfield 56	com/miaozhen/mzmonitor/c:d	Ljava/lang/String;
    //   150: aload_0
    //   151: ldc 70
    //   153: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   156: ifeq +15 -> 171
    //   159: aload 6
    //   161: new 75	java/util/ArrayList
    //   164: dup
    //   165: invokespecial 76	java/util/ArrayList:<init>	()V
    //   168: putfield 79	com/miaozhen/mzmonitor/c$c:a	Ljava/util/List;
    //   171: aload 6
    //   173: getfield 79	com/miaozhen/mzmonitor/c$c:a	Ljava/util/List;
    //   176: ifnull +1014 -> 1190
    //   179: aload_0
    //   180: ldc 81
    //   182: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   185: ifeq +1005 -> 1190
    //   188: new 9	com/miaozhen/mzmonitor/c$b
    //   191: dup
    //   192: aload_0
    //   193: invokespecial 82	com/miaozhen/mzmonitor/c$b:<init>	(Lcom/miaozhen/mzmonitor/c;)V
    //   196: astore 4
    //   198: aload 4
    //   200: ifnull +979 -> 1179
    //   203: aload_0
    //   204: ldc 84
    //   206: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   209: ifeq +35 -> 244
    //   212: iload 8
    //   214: ifne +30 -> 244
    //   217: aload_3
    //   218: ifnonnull +26 -> 244
    //   221: aload 4
    //   223: aload 7
    //   225: invokeinterface 87 1 0
    //   230: putfield 89	com/miaozhen/mzmonitor/c$b:a	Ljava/lang/String;
    //   233: aload_3
    //   234: astore 5
    //   236: aload_2
    //   237: astore_3
    //   238: aload 5
    //   240: astore_2
    //   241: goto -133 -> 108
    //   244: aload_0
    //   245: ldc 91
    //   247: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   250: ifeq +52 -> 302
    //   253: aload 4
    //   255: getfield 93	com/miaozhen/mzmonitor/c$b:b	Ljava/util/List;
    //   258: ifnonnull +15 -> 273
    //   261: aload 4
    //   263: new 75	java/util/ArrayList
    //   266: dup
    //   267: invokespecial 76	java/util/ArrayList:<init>	()V
    //   270: putfield 93	com/miaozhen/mzmonitor/c$b:b	Ljava/util/List;
    //   273: aload 4
    //   275: getfield 93	com/miaozhen/mzmonitor/c$b:b	Ljava/util/List;
    //   278: aload 7
    //   280: invokeinterface 87 1 0
    //   285: invokeinterface 99 2 0
    //   290: pop
    //   291: aload_3
    //   292: astore 5
    //   294: aload_2
    //   295: astore_3
    //   296: aload 5
    //   298: astore_2
    //   299: goto -191 -> 108
    //   302: aload_0
    //   303: ldc 101
    //   305: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   308: ifeq +26 -> 334
    //   311: aload 4
    //   313: aload 7
    //   315: invokeinterface 87 1 0
    //   320: putfield 103	com/miaozhen/mzmonitor/c$b:c	Ljava/lang/String;
    //   323: aload_3
    //   324: astore 5
    //   326: aload_2
    //   327: astore_3
    //   328: aload 5
    //   330: astore_2
    //   331: goto -223 -> 108
    //   334: aload_0
    //   335: ldc 105
    //   337: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   340: ifeq +26 -> 366
    //   343: aload 4
    //   345: aload 7
    //   347: invokeinterface 87 1 0
    //   352: putfield 106	com/miaozhen/mzmonitor/c$b:d	Ljava/lang/String;
    //   355: aload_3
    //   356: astore 5
    //   358: aload_2
    //   359: astore_3
    //   360: aload 5
    //   362: astore_2
    //   363: goto -255 -> 108
    //   366: aload_0
    //   367: ldc 108
    //   369: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   372: ifeq +26 -> 398
    //   375: aload 4
    //   377: aload 7
    //   379: invokeinterface 87 1 0
    //   384: putfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   387: aload_3
    //   388: astore 5
    //   390: aload_2
    //   391: astore_3
    //   392: aload 5
    //   394: astore_2
    //   395: goto -287 -> 108
    //   398: aload_0
    //   399: ldc 113
    //   401: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   404: ifeq +52 -> 456
    //   407: aload 7
    //   409: invokeinterface 87 1 0
    //   414: invokevirtual 118	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   417: astore 5
    //   419: aload 5
    //   421: ldc 120
    //   423: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   426: ifne +13 -> 439
    //   429: aload 5
    //   431: ldc 125
    //   433: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   436: ifeq +743 -> 1179
    //   439: aload 4
    //   441: iconst_0
    //   442: putfield 129	com/miaozhen/mzmonitor/c$b:j	Z
    //   445: aload_3
    //   446: astore 5
    //   448: aload_2
    //   449: astore_3
    //   450: aload 5
    //   452: astore_2
    //   453: goto -345 -> 108
    //   456: aload_0
    //   457: ldc 131
    //   459: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   462: ifeq +52 -> 514
    //   465: aload 7
    //   467: invokeinterface 87 1 0
    //   472: invokevirtual 118	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   475: astore 5
    //   477: aload 5
    //   479: ldc 133
    //   481: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   484: ifne +13 -> 497
    //   487: aload 5
    //   489: ldc 135
    //   491: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   494: ifeq +685 -> 1179
    //   497: aload 4
    //   499: iconst_1
    //   500: putfield 138	com/miaozhen/mzmonitor/c$b:i	Z
    //   503: aload_3
    //   504: astore 5
    //   506: aload_2
    //   507: astore_3
    //   508: aload 5
    //   510: astore_2
    //   511: goto -403 -> 108
    //   514: aload_0
    //   515: ldc 140
    //   517: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   520: ifeq +29 -> 549
    //   523: aload 4
    //   525: new 75	java/util/ArrayList
    //   528: dup
    //   529: invokespecial 76	java/util/ArrayList:<init>	()V
    //   532: putfield 143	com/miaozhen/mzmonitor/c$b:g	Ljava/util/List;
    //   535: iconst_1
    //   536: istore 8
    //   538: aload_3
    //   539: astore 5
    //   541: aload_2
    //   542: astore_3
    //   543: aload 5
    //   545: astore_2
    //   546: goto -438 -> 108
    //   549: iload 8
    //   551: ifeq +152 -> 703
    //   554: aload_0
    //   555: ldc 145
    //   557: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   560: ifeq +25 -> 585
    //   563: aload_2
    //   564: aload 7
    //   566: invokeinterface 87 1 0
    //   571: putfield 146	com/miaozhen/mzmonitor/c$a:c	Ljava/lang/String;
    //   574: aload_3
    //   575: astore 5
    //   577: aload_2
    //   578: astore_3
    //   579: aload 5
    //   581: astore_2
    //   582: goto -474 -> 108
    //   585: aload_0
    //   586: ldc 84
    //   588: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   591: ifeq +25 -> 616
    //   594: aload_2
    //   595: aload 7
    //   597: invokeinterface 87 1 0
    //   602: putfield 148	com/miaozhen/mzmonitor/c$a:b	Ljava/lang/String;
    //   605: aload_3
    //   606: astore 5
    //   608: aload_2
    //   609: astore_3
    //   610: aload 5
    //   612: astore_2
    //   613: goto -505 -> 108
    //   616: aload_0
    //   617: ldc 150
    //   619: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   622: ifeq +51 -> 673
    //   625: aload 7
    //   627: invokeinterface 87 1 0
    //   632: invokevirtual 118	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   635: astore 5
    //   637: aload 5
    //   639: ldc 120
    //   641: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   644: ifne +13 -> 657
    //   647: aload 5
    //   649: ldc 125
    //   651: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   654: ifeq +525 -> 1179
    //   657: aload_2
    //   658: iconst_0
    //   659: putfield 152	com/miaozhen/mzmonitor/c$a:d	Z
    //   662: aload_3
    //   663: astore 5
    //   665: aload_2
    //   666: astore_3
    //   667: aload 5
    //   669: astore_2
    //   670: goto -562 -> 108
    //   673: new 6	com/miaozhen/mzmonitor/c$a
    //   676: dup
    //   677: aload_0
    //   678: invokespecial 153	com/miaozhen/mzmonitor/c$a:<init>	(Lcom/miaozhen/mzmonitor/c;)V
    //   681: astore 5
    //   683: aload 5
    //   685: aload 7
    //   687: invokeinterface 68 1 0
    //   692: putfield 154	com/miaozhen/mzmonitor/c$a:a	Ljava/lang/String;
    //   695: aload_3
    //   696: astore_2
    //   697: aload 5
    //   699: astore_3
    //   700: goto -592 -> 108
    //   703: aload_0
    //   704: ldc 156
    //   706: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   709: ifeq +26 -> 735
    //   712: aload 4
    //   714: new 75	java/util/ArrayList
    //   717: dup
    //   718: invokespecial 76	java/util/ArrayList:<init>	()V
    //   721: putfield 159	com/miaozhen/mzmonitor/c$b:h	Ljava/util/List;
    //   724: aload_3
    //   725: astore 5
    //   727: aload_2
    //   728: astore_3
    //   729: aload 5
    //   731: astore_2
    //   732: goto -624 -> 108
    //   735: aload_0
    //   736: ldc 161
    //   738: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   741: ifeq +21 -> 762
    //   744: new 15	com/miaozhen/mzmonitor/c$d
    //   747: dup
    //   748: aload_0
    //   749: invokespecial 162	com/miaozhen/mzmonitor/c$d:<init>	(Lcom/miaozhen/mzmonitor/c;)V
    //   752: astore 5
    //   754: aload_2
    //   755: astore_3
    //   756: aload 5
    //   758: astore_2
    //   759: goto -651 -> 108
    //   762: aload_3
    //   763: ifnull +416 -> 1179
    //   766: aload_0
    //   767: ldc 164
    //   769: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   772: ifeq +25 -> 797
    //   775: aload_3
    //   776: aload 7
    //   778: invokeinterface 87 1 0
    //   783: putfield 165	com/miaozhen/mzmonitor/c$d:a	Ljava/lang/String;
    //   786: aload_3
    //   787: astore 5
    //   789: aload_2
    //   790: astore_3
    //   791: aload 5
    //   793: astore_2
    //   794: goto -686 -> 108
    //   797: aload_0
    //   798: ldc 84
    //   800: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   803: ifeq +25 -> 828
    //   806: aload_3
    //   807: aload 7
    //   809: invokeinterface 87 1 0
    //   814: putfield 166	com/miaozhen/mzmonitor/c$d:b	Ljava/lang/String;
    //   817: aload_3
    //   818: astore 5
    //   820: aload_2
    //   821: astore_3
    //   822: aload 5
    //   824: astore_2
    //   825: goto -717 -> 108
    //   828: aload_0
    //   829: ldc 168
    //   831: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   834: ifeq +25 -> 859
    //   837: aload_3
    //   838: aload 7
    //   840: invokeinterface 87 1 0
    //   845: putfield 169	com/miaozhen/mzmonitor/c$d:c	Ljava/lang/String;
    //   848: aload_3
    //   849: astore 5
    //   851: aload_2
    //   852: astore_3
    //   853: aload 5
    //   855: astore_2
    //   856: goto -748 -> 108
    //   859: aload_0
    //   860: ldc 150
    //   862: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   865: ifeq +314 -> 1179
    //   868: aload 7
    //   870: invokeinterface 87 1 0
    //   875: invokevirtual 118	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   878: astore 5
    //   880: aload 5
    //   882: ldc 120
    //   884: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   887: ifne +13 -> 900
    //   890: aload 5
    //   892: ldc 125
    //   894: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   897: ifeq +282 -> 1179
    //   900: aload_3
    //   901: iconst_0
    //   902: putfield 170	com/miaozhen/mzmonitor/c$d:d	Z
    //   905: aload_3
    //   906: astore 5
    //   908: aload_2
    //   909: astore_3
    //   910: aload 5
    //   912: astore_2
    //   913: goto -805 -> 108
    //   916: aload_0
    //   917: aload 7
    //   919: invokeinterface 68 1 0
    //   924: putfield 56	com/miaozhen/mzmonitor/c:d	Ljava/lang/String;
    //   927: aload_0
    //   928: ldc 81
    //   930: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   933: ifeq +243 -> 1176
    //   936: aload 6
    //   938: getfield 79	com/miaozhen/mzmonitor/c$c:a	Ljava/util/List;
    //   941: aload 4
    //   943: invokeinterface 99 2 0
    //   948: pop
    //   949: aconst_null
    //   950: astore 4
    //   952: aload_0
    //   953: ldc 140
    //   955: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   958: ifeq +6 -> 964
    //   961: iconst_0
    //   962: istore 8
    //   964: iload 8
    //   966: ifeq +207 -> 1173
    //   969: aload_0
    //   970: ldc 145
    //   972: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   975: ifne +198 -> 1173
    //   978: aload_0
    //   979: ldc 84
    //   981: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   984: ifne +189 -> 1173
    //   987: aload_0
    //   988: ldc 150
    //   990: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   993: ifne +180 -> 1173
    //   996: aload_0
    //   997: ldc 113
    //   999: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   1002: ifne +171 -> 1173
    //   1005: aload 4
    //   1007: getfield 143	com/miaozhen/mzmonitor/c$b:g	Ljava/util/List;
    //   1010: aload_2
    //   1011: invokeinterface 99 2 0
    //   1016: pop
    //   1017: aconst_null
    //   1018: astore_2
    //   1019: aload_0
    //   1020: ldc 161
    //   1022: invokespecial 73	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;)Z
    //   1025: ifeq +137 -> 1162
    //   1028: aload 4
    //   1030: getfield 159	com/miaozhen/mzmonitor/c$b:h	Ljava/util/List;
    //   1033: aload_3
    //   1034: invokeinterface 99 2 0
    //   1039: pop
    //   1040: aconst_null
    //   1041: astore 5
    //   1043: aload_2
    //   1044: astore_3
    //   1045: aload 5
    //   1047: astore_2
    //   1048: goto -940 -> 108
    //   1051: astore_2
    //   1052: aload_2
    //   1053: invokevirtual 173	org/xmlpull/v1/XmlPullParserException:printStackTrace	()V
    //   1056: aload_1
    //   1057: ifnull -994 -> 63
    //   1060: aload_1
    //   1061: invokevirtual 61	java/io/InputStream:close	()V
    //   1064: aload 6
    //   1066: areturn
    //   1067: astore_1
    //   1068: aload_1
    //   1069: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   1072: aload 6
    //   1074: areturn
    //   1075: astore_2
    //   1076: aload_2
    //   1077: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   1080: aload_1
    //   1081: ifnull -1018 -> 63
    //   1084: aload_1
    //   1085: invokevirtual 61	java/io/InputStream:close	()V
    //   1088: aload 6
    //   1090: areturn
    //   1091: astore_1
    //   1092: aload_1
    //   1093: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   1096: aload 6
    //   1098: areturn
    //   1099: astore_2
    //   1100: aload_1
    //   1101: ifnull +7 -> 1108
    //   1104: aload_1
    //   1105: invokevirtual 61	java/io/InputStream:close	()V
    //   1108: aload_2
    //   1109: athrow
    //   1110: astore_1
    //   1111: aload_1
    //   1112: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   1115: goto -7 -> 1108
    //   1118: astore_1
    //   1119: aload_1
    //   1120: invokevirtual 174	java/io/IOException:printStackTrace	()V
    //   1123: aload 6
    //   1125: areturn
    //   1126: astore_2
    //   1127: goto -27 -> 1100
    //   1130: astore_2
    //   1131: goto -31 -> 1100
    //   1134: astore_2
    //   1135: goto -35 -> 1100
    //   1138: astore_2
    //   1139: goto -63 -> 1076
    //   1142: astore_2
    //   1143: goto -67 -> 1076
    //   1146: astore_2
    //   1147: goto -71 -> 1076
    //   1150: astore_2
    //   1151: goto -99 -> 1052
    //   1154: astore_2
    //   1155: goto -103 -> 1052
    //   1158: astore_2
    //   1159: goto -107 -> 1052
    //   1162: aload_3
    //   1163: astore 5
    //   1165: aload_2
    //   1166: astore_3
    //   1167: aload 5
    //   1169: astore_2
    //   1170: goto -1062 -> 108
    //   1173: goto -154 -> 1019
    //   1176: goto -224 -> 952
    //   1179: aload_3
    //   1180: astore 5
    //   1182: aload_2
    //   1183: astore_3
    //   1184: aload 5
    //   1186: astore_2
    //   1187: goto -1079 -> 108
    //   1190: goto -992 -> 198
    //
    // Exception table:
    //   from	to	target	type
    //   13	37	1051	org/xmlpull/v1/XmlPullParserException
    //   108	117	1051	org/xmlpull/v1/XmlPullParserException
    //   1060	1064	1067	java/io/IOException
    //   13	37	1075	java/io/IOException
    //   108	117	1075	java/io/IOException
    //   1084	1088	1091	java/io/IOException
    //   13	37	1099	finally
    //   108	117	1099	finally
    //   1052	1056	1099	finally
    //   1076	1080	1099	finally
    //   1104	1108	1110	java/io/IOException
    //   59	63	1118	java/io/IOException
    //   50	55	1126	finally
    //   139	171	1126	finally
    //   171	198	1126	finally
    //   916	949	1126	finally
    //   203	212	1130	finally
    //   221	233	1130	finally
    //   244	273	1130	finally
    //   273	291	1130	finally
    //   302	323	1130	finally
    //   334	355	1130	finally
    //   366	387	1130	finally
    //   398	439	1130	finally
    //   439	445	1130	finally
    //   456	497	1130	finally
    //   497	503	1130	finally
    //   514	535	1130	finally
    //   554	574	1130	finally
    //   585	605	1130	finally
    //   616	657	1130	finally
    //   657	662	1130	finally
    //   673	683	1130	finally
    //   703	724	1130	finally
    //   735	754	1130	finally
    //   766	786	1130	finally
    //   797	817	1130	finally
    //   828	848	1130	finally
    //   859	900	1130	finally
    //   900	905	1130	finally
    //   952	961	1130	finally
    //   969	1017	1130	finally
    //   683	695	1134	finally
    //   1019	1040	1134	finally
    //   50	55	1138	java/io/IOException
    //   139	171	1138	java/io/IOException
    //   171	198	1138	java/io/IOException
    //   916	949	1138	java/io/IOException
    //   203	212	1142	java/io/IOException
    //   221	233	1142	java/io/IOException
    //   244	273	1142	java/io/IOException
    //   273	291	1142	java/io/IOException
    //   302	323	1142	java/io/IOException
    //   334	355	1142	java/io/IOException
    //   366	387	1142	java/io/IOException
    //   398	439	1142	java/io/IOException
    //   439	445	1142	java/io/IOException
    //   456	497	1142	java/io/IOException
    //   497	503	1142	java/io/IOException
    //   514	535	1142	java/io/IOException
    //   554	574	1142	java/io/IOException
    //   585	605	1142	java/io/IOException
    //   616	657	1142	java/io/IOException
    //   657	662	1142	java/io/IOException
    //   673	683	1142	java/io/IOException
    //   703	724	1142	java/io/IOException
    //   735	754	1142	java/io/IOException
    //   766	786	1142	java/io/IOException
    //   797	817	1142	java/io/IOException
    //   828	848	1142	java/io/IOException
    //   859	900	1142	java/io/IOException
    //   900	905	1142	java/io/IOException
    //   952	961	1142	java/io/IOException
    //   969	1017	1142	java/io/IOException
    //   683	695	1146	java/io/IOException
    //   1019	1040	1146	java/io/IOException
    //   50	55	1150	org/xmlpull/v1/XmlPullParserException
    //   139	171	1150	org/xmlpull/v1/XmlPullParserException
    //   171	198	1150	org/xmlpull/v1/XmlPullParserException
    //   916	949	1150	org/xmlpull/v1/XmlPullParserException
    //   203	212	1154	org/xmlpull/v1/XmlPullParserException
    //   221	233	1154	org/xmlpull/v1/XmlPullParserException
    //   244	273	1154	org/xmlpull/v1/XmlPullParserException
    //   273	291	1154	org/xmlpull/v1/XmlPullParserException
    //   302	323	1154	org/xmlpull/v1/XmlPullParserException
    //   334	355	1154	org/xmlpull/v1/XmlPullParserException
    //   366	387	1154	org/xmlpull/v1/XmlPullParserException
    //   398	439	1154	org/xmlpull/v1/XmlPullParserException
    //   439	445	1154	org/xmlpull/v1/XmlPullParserException
    //   456	497	1154	org/xmlpull/v1/XmlPullParserException
    //   497	503	1154	org/xmlpull/v1/XmlPullParserException
    //   514	535	1154	org/xmlpull/v1/XmlPullParserException
    //   554	574	1154	org/xmlpull/v1/XmlPullParserException
    //   585	605	1154	org/xmlpull/v1/XmlPullParserException
    //   616	657	1154	org/xmlpull/v1/XmlPullParserException
    //   657	662	1154	org/xmlpull/v1/XmlPullParserException
    //   673	683	1154	org/xmlpull/v1/XmlPullParserException
    //   703	724	1154	org/xmlpull/v1/XmlPullParserException
    //   735	754	1154	org/xmlpull/v1/XmlPullParserException
    //   766	786	1154	org/xmlpull/v1/XmlPullParserException
    //   797	817	1154	org/xmlpull/v1/XmlPullParserException
    //   828	848	1154	org/xmlpull/v1/XmlPullParserException
    //   859	900	1154	org/xmlpull/v1/XmlPullParserException
    //   900	905	1154	org/xmlpull/v1/XmlPullParserException
    //   952	961	1154	org/xmlpull/v1/XmlPullParserException
    //   969	1017	1154	org/xmlpull/v1/XmlPullParserException
    //   683	695	1158	org/xmlpull/v1/XmlPullParserException
    //   1019	1040	1158	org/xmlpull/v1/XmlPullParserException
  }

  private static d a(String paramString, b paramb)
  {
    if ((paramString != null) && (paramb.h != null))
      paramb = paramb.h.iterator();
    d locald;
    do
    {
      if (!paramb.hasNext())
        return null;
      locald = (d)paramb.next();
    }
    while (!paramString.equals(locald.a));
    return locald;
  }

  public static c a(Context paramContext)
  {
    try
    {
      if (a == null)
        a = new c(paramContext.getApplicationContext());
      return a;
    }
    finally
    {
    }
    throw paramContext;
  }

  private String a(a parama, b paramb)
  {
    f localf = f.a(this.b);
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = paramb.c;
    String str2 = paramb.d;
    try
    {
      localStringBuilder.append(str1 + "mv" + str2 + URLEncoder.encode("a3.2", "UTF-8"));
      localStringBuilder.append(str1 + "mr" + str2 + URLEncoder.encode(new StringBuilder().append(parama.h()).toString(), "UTF-8"));
      localStringBuilder.append(str1 + "mc" + str2 + URLEncoder.encode(new StringBuilder().append(j.c(this.b)).toString(), "UTF-8"));
      localStringBuilder.append(str1 + "mw" + str2 + URLEncoder.encode(localf.l(), "UTF-8"));
      String str3 = localf.m();
      if (str3 != null)
        localStringBuilder.append(paramb.c + "mj" + paramb.d + URLEncoder.encode(str3, "UTF-8"));
      if (parama.h() > 0)
      {
        parama = a.a() - parama.g();
        localStringBuilder.append(str1 + "mu" + str2 + URLEncoder.encode(parama, "UTF-8"));
      }
      while (true)
      {
        parama = j.g(this.b);
        if ((parama != null) && (!parama.contains("UNKNOWN")))
          localStringBuilder.append(str1 + "mg" + str2 + URLEncoder.encode(parama, "UTF-8"));
        parama = localf.h();
        if ((parama != null) && (!parama.equals("")))
          localStringBuilder.append(str1 + "m6" + str2 + URLEncoder.encode(a.a(parama).toUpperCase(), "UTF-8"));
        return localStringBuilder.toString();
        localStringBuilder.append(str1 + "mu" + str2 + URLEncoder.encode("0", "UTF-8"));
      }
    }
    catch (UnsupportedEncodingException parama)
    {
      while (true)
        parama.printStackTrace();
    }
  }

  private String a(String paramString, a parama, b paramb)
  {
    Object localObject = paramb.g;
    String str1 = paramb.c;
    String str2 = paramb.d;
    localObject = ((List)localObject).iterator();
    while (true)
    {
      if (!((Iterator)localObject).hasNext())
      {
        paramb = a(parama.d(), paramb);
        parama = paramString;
        if (paramb != null)
          parama = paramString.replaceAll(str1 + paramb.b + str2 + "[^" + str1 + "]*", "");
        return parama;
      }
      a locala = (a)((Iterator)localObject).next();
      if (((!locala.a.equals("PANELID")) || (parama.b() != null)) && ((!locala.a.equals("MUID")) || (parama.c() != null)) && ((!locala.a.equals("IESID")) || (parama.i() != null)) && (paramString.contains(str1 + locala.b + str2)))
        paramString = paramString.replaceAll(str1 + locala.b + str2 + "[^" + str1 + "]*", "");
    }
  }

  private static String a(String paramString, a parama)
  {
    if ((paramString != null) && (!paramString.equals("")))
    {
      String str;
      if (parama.c.equals("md5"))
        str = a.a(paramString);
      while (true)
      {
        paramString = str;
        if (parama.d);
        try
        {
          paramString = URLEncoder.encode(str, "UTF-8");
          return paramString;
          str = paramString;
          if (parama.c.equals("sha1"))
            str = a.b(paramString);
        }
        catch (UnsupportedEncodingException paramString)
        {
          paramString.printStackTrace();
          return str;
        }
      }
    }
    return "";
  }

  private boolean a(String paramString)
  {
    return paramString.equals(this.d);
  }

  public final b a(URL paramURL)
  {
    if ((this.c != null) && (this.c.a != null))
      paramURL = paramURL.getHost();
    b localb;
    Iterator localIterator2;
    do
    {
      Iterator localIterator1 = this.c.a.iterator();
      while (!localIterator2.hasNext())
      {
        if (!localIterator1.hasNext())
          return null;
        localb = (b)localIterator1.next();
        localIterator2 = localb.b.iterator();
      }
    }
    while (!paramURL.endsWith((String)localIterator2.next()));
    return localb;
  }

  final c a()
  {
    try
    {
      c localc = this.c;
      return localc;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public final URL a(a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_0
    //   5: getfield 27	com/miaozhen/mzmonitor/c:b	Landroid/content/Context;
    //   8: invokestatic 207	com/miaozhen/mzmonitor/f:a	(Landroid/content/Context;)Lcom/miaozhen/mzmonitor/f;
    //   11: astore 10
    //   13: new 209	java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial 210	java/lang/StringBuilder:<init>	()V
    //   20: astore 8
    //   22: aload_1
    //   23: invokevirtual 341	com/miaozhen/mzmonitor/a:a	()Ljava/lang/String;
    //   26: astore_2
    //   27: new 329	java/net/URL
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 342	java/net/URL:<init>	(Ljava/lang/String;)V
    //   35: astore 4
    //   37: aload_0
    //   38: aload 4
    //   40: invokevirtual 344	com/miaozhen/mzmonitor/c:a	(Ljava/net/URL;)Lcom/miaozhen/mzmonitor/c$b;
    //   43: astore 9
    //   45: aload 9
    //   47: ifnull +1280 -> 1327
    //   50: aload 9
    //   52: getfield 138	com/miaozhen/mzmonitor/c$b:i	Z
    //   55: ifeq +1272 -> 1327
    //   58: aload 9
    //   60: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   63: ifnull +448 -> 511
    //   66: aload 9
    //   68: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   71: ldc_w 285
    //   74: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   77: ifne +434 -> 511
    //   80: aload_2
    //   81: aload 9
    //   83: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   86: invokevirtual 279	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   89: ifeq +422 -> 511
    //   92: aload_2
    //   93: aload_2
    //   94: aload 9
    //   96: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   99: invokevirtual 348	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   102: invokevirtual 352	java/lang/String:substring	(I)Ljava/lang/String;
    //   105: astore 5
    //   107: aload_2
    //   108: astore_3
    //   109: aload 9
    //   111: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   114: ifnull +48 -> 162
    //   117: aload_2
    //   118: astore_3
    //   119: aload 9
    //   121: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   124: ldc_w 285
    //   127: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   130: ifne +32 -> 162
    //   133: aload_2
    //   134: astore_3
    //   135: aload_2
    //   136: aload 9
    //   138: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   141: invokevirtual 279	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   144: ifeq +18 -> 162
    //   147: aload_2
    //   148: iconst_0
    //   149: aload_2
    //   150: aload 9
    //   152: getfield 111	com/miaozhen/mzmonitor/c$b:e	Ljava/lang/String;
    //   155: invokevirtual 348	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   158: invokevirtual 355	java/lang/String:substring	(II)Ljava/lang/String;
    //   161: astore_3
    //   162: aload_0
    //   163: aload_3
    //   164: aload_1
    //   165: aload 9
    //   167: invokespecial 357	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;Lcom/miaozhen/mzmonitor/a;Lcom/miaozhen/mzmonitor/c$b;)Ljava/lang/String;
    //   170: astore_3
    //   171: aload 8
    //   173: aload_3
    //   174: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: pop
    //   178: aload 9
    //   180: getfield 89	com/miaozhen/mzmonitor/c$b:a	Ljava/lang/String;
    //   183: ldc_w 359
    //   186: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   189: ifeq +16 -> 205
    //   192: aload 8
    //   194: aload_0
    //   195: aload_1
    //   196: aload 9
    //   198: invokespecial 361	com/miaozhen/mzmonitor/c:a	(Lcom/miaozhen/mzmonitor/a;Lcom/miaozhen/mzmonitor/c$b;)Ljava/lang/String;
    //   201: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: pop
    //   205: aload 9
    //   207: getfield 143	com/miaozhen/mzmonitor/c$b:g	Ljava/util/List;
    //   210: invokeinterface 179 1 0
    //   215: astore 11
    //   217: aload 11
    //   219: invokeinterface 185 1 0
    //   224: ifne +295 -> 519
    //   227: aload_3
    //   228: astore_2
    //   229: aload_1
    //   230: invokevirtual 296	com/miaozhen/mzmonitor/a:d	()Ljava/lang/String;
    //   233: ifnull +141 -> 374
    //   236: aload 9
    //   238: getfield 103	com/miaozhen/mzmonitor/c$b:c	Ljava/lang/String;
    //   241: astore 7
    //   243: aload_1
    //   244: invokevirtual 296	com/miaozhen/mzmonitor/a:d	()Ljava/lang/String;
    //   247: ldc 44
    //   249: invokestatic 231	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   252: astore_2
    //   253: aload_1
    //   254: invokevirtual 296	com/miaozhen/mzmonitor/a:d	()Ljava/lang/String;
    //   257: aload 9
    //   259: invokestatic 298	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;Lcom/miaozhen/mzmonitor/c$b;)Lcom/miaozhen/mzmonitor/c$d;
    //   262: astore 10
    //   264: aload 7
    //   266: astore 6
    //   268: aload 10
    //   270: ifnull +55 -> 325
    //   273: new 209	java/lang/StringBuilder
    //   276: dup
    //   277: aload 7
    //   279: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   282: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   285: aload 10
    //   287: getfield 166	com/miaozhen/mzmonitor/c$d:b	Ljava/lang/String;
    //   290: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: aload 9
    //   295: getfield 106	com/miaozhen/mzmonitor/c$b:d	Ljava/lang/String;
    //   298: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   301: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   304: astore 6
    //   306: aload 10
    //   308: getfield 170	com/miaozhen/mzmonitor/c$d:d	Z
    //   311: ifeq +1001 -> 1312
    //   314: aload 10
    //   316: getfield 169	com/miaozhen/mzmonitor/c$d:c	Ljava/lang/String;
    //   319: ldc 44
    //   321: invokestatic 231	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   324: astore_2
    //   325: aload 8
    //   327: new 209	java/lang/StringBuilder
    //   330: dup
    //   331: aload 6
    //   333: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   336: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   339: aload_2
    //   340: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   346: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: pop
    //   350: new 209	java/lang/StringBuilder
    //   353: dup
    //   354: aload_3
    //   355: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   358: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   361: aload 6
    //   363: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   366: aload_2
    //   367: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   373: astore_2
    //   374: aload 9
    //   376: getfield 364	com/miaozhen/mzmonitor/c$b:f	Ljava/lang/String;
    //   379: ifnull +58 -> 437
    //   382: aload 8
    //   384: new 209	java/lang/StringBuilder
    //   387: dup
    //   388: aload 9
    //   390: getfield 103	com/miaozhen/mzmonitor/c$b:c	Ljava/lang/String;
    //   393: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   396: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   399: aload 9
    //   401: getfield 364	com/miaozhen/mzmonitor/c$b:f	Ljava/lang/String;
    //   404: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: aload 9
    //   409: getfield 106	com/miaozhen/mzmonitor/c$b:d	Ljava/lang/String;
    //   412: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload_0
    //   416: getfield 27	com/miaozhen/mzmonitor/c:b	Landroid/content/Context;
    //   419: aload 8
    //   421: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   424: invokestatic 369	com/miaozhen/mzmonitor/k:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   427: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   433: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: pop
    //   437: aload_1
    //   438: new 209	java/lang/StringBuilder
    //   441: dup
    //   442: aload_2
    //   443: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   446: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   449: aload 5
    //   451: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   454: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   457: invokevirtual 371	com/miaozhen/mzmonitor/a:a	(Ljava/lang/String;)V
    //   460: ldc_w 373
    //   463: new 209	java/lang/StringBuilder
    //   466: dup
    //   467: ldc_w 375
    //   470: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   473: aload_2
    //   474: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: aload 5
    //   479: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   482: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   485: invokestatic 380	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   488: pop
    //   489: new 329	java/net/URL
    //   492: dup
    //   493: aload 8
    //   495: aload 5
    //   497: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   503: invokespecial 342	java/net/URL:<init>	(Ljava/lang/String;)V
    //   506: astore_1
    //   507: aload_0
    //   508: monitorexit
    //   509: aload_1
    //   510: areturn
    //   511: ldc_w 285
    //   514: astore 5
    //   516: goto -409 -> 107
    //   519: aload 11
    //   521: invokeinterface 188 1 0
    //   526: checkcast 6	com/miaozhen/mzmonitor/c$a
    //   529: astore 14
    //   531: new 209	java/lang/StringBuilder
    //   534: dup
    //   535: aload 9
    //   537: getfield 103	com/miaozhen/mzmonitor/c$b:c	Ljava/lang/String;
    //   540: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   543: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   546: aload 14
    //   548: getfield 148	com/miaozhen/mzmonitor/c$a:b	Ljava/lang/String;
    //   551: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: aload 9
    //   556: getfield 106	com/miaozhen/mzmonitor/c$b:d	Ljava/lang/String;
    //   559: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   562: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   565: astore 12
    //   567: aload 14
    //   569: getfield 154	com/miaozhen/mzmonitor/c$a:a	Ljava/lang/String;
    //   572: astore 13
    //   574: ldc_w 285
    //   577: astore 6
    //   579: aload 13
    //   581: ldc_w 382
    //   584: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   587: ifeq +205 -> 792
    //   590: aload 10
    //   592: invokevirtual 383	com/miaozhen/mzmonitor/f:d	()Ljava/lang/String;
    //   595: astore_2
    //   596: aload_2
    //   597: aload 14
    //   599: invokestatic 385	com/miaozhen/mzmonitor/c:a	(Ljava/lang/String;Lcom/miaozhen/mzmonitor/c$a;)Ljava/lang/String;
    //   602: astore 6
    //   604: aload 13
    //   606: ldc_w 307
    //   609: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   612: ifne +27 -> 639
    //   615: aload 13
    //   617: ldc_w 311
    //   620: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   623: ifne +16 -> 639
    //   626: aload_3
    //   627: astore_2
    //   628: aload 13
    //   630: ldc_w 315
    //   633: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   636: ifeq +62 -> 698
    //   639: aload_3
    //   640: aload 12
    //   642: invokevirtual 279	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   645: ifne -428 -> 217
    //   648: aload 6
    //   650: ldc_w 285
    //   653: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   656: ifeq +17 -> 673
    //   659: aload 9
    //   661: getfield 89	com/miaozhen/mzmonitor/c$b:a	Ljava/lang/String;
    //   664: ldc_w 359
    //   667: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   670: ifne -453 -> 217
    //   673: new 209	java/lang/StringBuilder
    //   676: dup
    //   677: aload_3
    //   678: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   681: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   684: aload 12
    //   686: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   689: aload 6
    //   691: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   694: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   697: astore_2
    //   698: aload 6
    //   700: ldc_w 285
    //   703: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   706: ifeq +19 -> 725
    //   709: aload_2
    //   710: astore_3
    //   711: aload 9
    //   713: getfield 89	com/miaozhen/mzmonitor/c$b:a	Ljava/lang/String;
    //   716: ldc_w 359
    //   719: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   722: ifne -505 -> 217
    //   725: aload 8
    //   727: new 209	java/lang/StringBuilder
    //   730: dup
    //   731: aload 12
    //   733: invokestatic 214	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   736: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   739: aload 6
    //   741: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   744: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   747: invokevirtual 223	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: pop
    //   751: aload_2
    //   752: astore_3
    //   753: goto -536 -> 217
    //   756: astore_2
    //   757: aload 4
    //   759: astore_1
    //   760: ldc_w 373
    //   763: new 209	java/lang/StringBuilder
    //   766: dup
    //   767: ldc_w 387
    //   770: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   773: aload_2
    //   774: invokevirtual 390	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   777: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   780: invokestatic 380	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   783: pop
    //   784: goto -277 -> 507
    //   787: astore_1
    //   788: aload_0
    //   789: monitorexit
    //   790: aload_1
    //   791: athrow
    //   792: aload 13
    //   794: ldc_w 392
    //   797: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   800: ifeq +12 -> 812
    //   803: aload 10
    //   805: invokevirtual 394	com/miaozhen/mzmonitor/f:e	()Ljava/lang/String;
    //   808: astore_2
    //   809: goto -213 -> 596
    //   812: aload 13
    //   814: ldc_w 396
    //   817: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   820: ifeq +12 -> 832
    //   823: aload 10
    //   825: invokevirtual 397	com/miaozhen/mzmonitor/f:c	()Ljava/lang/String;
    //   828: astore_2
    //   829: goto -233 -> 596
    //   832: aload 13
    //   834: ldc_w 399
    //   837: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   840: ifeq +50 -> 890
    //   843: aload 10
    //   845: invokevirtual 283	com/miaozhen/mzmonitor/f:h	()Ljava/lang/String;
    //   848: astore 7
    //   850: aload 6
    //   852: astore_2
    //   853: aload 7
    //   855: ifnull -259 -> 596
    //   858: aload 6
    //   860: astore_2
    //   861: aload 7
    //   863: ldc_w 285
    //   866: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   869: ifne -273 -> 596
    //   872: aload 7
    //   874: ldc_w 401
    //   877: ldc_w 285
    //   880: invokevirtual 305	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   883: invokevirtual 118	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   886: astore_2
    //   887: goto -291 -> 596
    //   890: aload 13
    //   892: ldc_w 403
    //   895: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   898: ifeq +12 -> 910
    //   901: aload 10
    //   903: invokevirtual 404	com/miaozhen/mzmonitor/f:b	()Ljava/lang/String;
    //   906: astore_2
    //   907: goto -311 -> 596
    //   910: aload 13
    //   912: ldc_w 406
    //   915: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   918: ifeq +18 -> 936
    //   921: aload 10
    //   923: invokevirtual 409	com/miaozhen/mzmonitor/f:k	()Z
    //   926: ifeq +410 -> 1336
    //   929: ldc_w 411
    //   932: astore_2
    //   933: goto +400 -> 1333
    //   936: aload 13
    //   938: ldc_w 413
    //   941: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   944: ifeq +10 -> 954
    //   947: invokestatic 414	com/miaozhen/mzmonitor/f:a	()Ljava/lang/String;
    //   950: astore_2
    //   951: goto -355 -> 596
    //   954: aload 13
    //   956: ldc_w 416
    //   959: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   962: ifeq +59 -> 1021
    //   965: aload_1
    //   966: invokevirtual 418	com/miaozhen/mzmonitor/a:f	()J
    //   969: lstore 15
    //   971: aload 9
    //   973: getfield 129	com/miaozhen/mzmonitor/c$b:j	Z
    //   976: ifeq +26 -> 1002
    //   979: new 209	java/lang/StringBuilder
    //   982: dup
    //   983: lload 15
    //   985: ldc2_w 419
    //   988: ldiv
    //   989: invokestatic 268	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   992: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   995: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   998: astore_2
    //   999: goto +344 -> 1343
    //   1002: new 209	java/lang/StringBuilder
    //   1005: dup
    //   1006: lload 15
    //   1008: invokestatic 268	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   1011: invokespecial 217	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1014: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1017: astore_2
    //   1018: goto +325 -> 1343
    //   1021: aload 13
    //   1023: ldc_w 422
    //   1026: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1029: ifeq +32 -> 1061
    //   1032: aload_0
    //   1033: getfield 27	com/miaozhen/mzmonitor/c:b	Landroid/content/Context;
    //   1036: invokestatic 424	com/miaozhen/mzmonitor/j:h	(Landroid/content/Context;)Ljava/lang/String;
    //   1039: astore 7
    //   1041: aload 6
    //   1043: astore_2
    //   1044: aload 7
    //   1046: ldc_w 275
    //   1049: invokevirtual 279	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   1052: ifne -456 -> 596
    //   1055: aload 7
    //   1057: astore_2
    //   1058: goto -462 -> 596
    //   1061: aload 13
    //   1063: ldc_w 426
    //   1066: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1069: ifeq +12 -> 1081
    //   1072: aload 10
    //   1074: invokevirtual 428	com/miaozhen/mzmonitor/f:f	()Ljava/lang/String;
    //   1077: astore_2
    //   1078: goto -482 -> 596
    //   1081: aload 13
    //   1083: ldc_w 430
    //   1086: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1089: ifeq +10 -> 1099
    //   1092: ldc_w 292
    //   1095: astore_2
    //   1096: goto -500 -> 596
    //   1099: aload 13
    //   1101: ldc_w 432
    //   1104: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1107: ifeq +10 -> 1117
    //   1110: invokestatic 434	com/miaozhen/mzmonitor/f:g	()Ljava/lang/String;
    //   1113: astore_2
    //   1114: goto -518 -> 596
    //   1117: aload 13
    //   1119: ldc_w 436
    //   1122: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1125: ifeq +12 -> 1137
    //   1128: aload 10
    //   1130: invokevirtual 437	com/miaozhen/mzmonitor/f:i	()Ljava/lang/String;
    //   1133: astore_2
    //   1134: goto -538 -> 596
    //   1137: aload 13
    //   1139: ldc_w 439
    //   1142: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1145: ifeq +12 -> 1157
    //   1148: aload 10
    //   1150: invokevirtual 441	com/miaozhen/mzmonitor/f:j	()Ljava/lang/String;
    //   1153: astore_2
    //   1154: goto -558 -> 596
    //   1157: aload 13
    //   1159: ldc_w 307
    //   1162: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1165: ifeq +11 -> 1176
    //   1168: aload_1
    //   1169: invokevirtual 309	com/miaozhen/mzmonitor/a:b	()Ljava/lang/String;
    //   1172: astore_2
    //   1173: goto -577 -> 596
    //   1176: aload 13
    //   1178: ldc_w 311
    //   1181: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1184: ifeq +11 -> 1195
    //   1187: aload_1
    //   1188: invokevirtual 313	com/miaozhen/mzmonitor/a:c	()Ljava/lang/String;
    //   1191: astore_2
    //   1192: goto -596 -> 596
    //   1195: aload 13
    //   1197: ldc_w 315
    //   1200: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1203: ifeq +11 -> 1214
    //   1206: aload_1
    //   1207: invokevirtual 317	com/miaozhen/mzmonitor/a:i	()Ljava/lang/String;
    //   1210: astore_2
    //   1211: goto -615 -> 596
    //   1214: aload 13
    //   1216: ldc_w 443
    //   1219: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1222: ifeq +14 -> 1236
    //   1225: aload_0
    //   1226: getfield 27	com/miaozhen/mzmonitor/c:b	Landroid/content/Context;
    //   1229: invokestatic 445	com/miaozhen/mzmonitor/f:b	(Landroid/content/Context;)Ljava/lang/String;
    //   1232: astore_2
    //   1233: goto -637 -> 596
    //   1236: aload 13
    //   1238: ldc_w 447
    //   1241: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1244: ifeq +10 -> 1254
    //   1247: invokestatic 450	com/miaozhen/mzmonitor/f:n	()Ljava/lang/String;
    //   1250: astore_2
    //   1251: goto -655 -> 596
    //   1254: aload 13
    //   1256: ldc_w 452
    //   1259: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1262: ifeq +15 -> 1277
    //   1265: aload_0
    //   1266: getfield 27	com/miaozhen/mzmonitor/c:b	Landroid/content/Context;
    //   1269: astore_2
    //   1270: invokestatic 455	com/miaozhen/mzmonitor/f:o	()Ljava/lang/String;
    //   1273: astore_2
    //   1274: goto -678 -> 596
    //   1277: aload 6
    //   1279: astore_2
    //   1280: aload 13
    //   1282: ldc_w 457
    //   1285: invokevirtual 123	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1288: ifeq -692 -> 596
    //   1291: aload 9
    //   1293: getfield 364	com/miaozhen/mzmonitor/c$b:f	Ljava/lang/String;
    //   1296: ifnonnull -1079 -> 217
    //   1299: aload 9
    //   1301: aload 14
    //   1303: getfield 148	com/miaozhen/mzmonitor/c$a:b	Ljava/lang/String;
    //   1306: putfield 364	com/miaozhen/mzmonitor/c$b:f	Ljava/lang/String;
    //   1309: goto -1092 -> 217
    //   1312: aload 10
    //   1314: getfield 169	com/miaozhen/mzmonitor/c$d:c	Ljava/lang/String;
    //   1317: astore_2
    //   1318: goto -993 -> 325
    //   1321: astore_2
    //   1322: aload_3
    //   1323: astore_1
    //   1324: goto -564 -> 760
    //   1327: aload 4
    //   1329: astore_1
    //   1330: goto -823 -> 507
    //   1333: goto -737 -> 596
    //   1336: ldc_w 292
    //   1339: astore_2
    //   1340: goto -7 -> 1333
    //   1343: goto -747 -> 596
    //   1346: astore_1
    //   1347: goto -559 -> 788
    //
    // Exception table:
    //   from	to	target	type
    //   37	45	756	java/lang/Exception
    //   50	107	756	java/lang/Exception
    //   109	117	756	java/lang/Exception
    //   119	133	756	java/lang/Exception
    //   135	162	756	java/lang/Exception
    //   162	205	756	java/lang/Exception
    //   205	217	756	java/lang/Exception
    //   217	227	756	java/lang/Exception
    //   229	264	756	java/lang/Exception
    //   273	325	756	java/lang/Exception
    //   325	374	756	java/lang/Exception
    //   374	437	756	java/lang/Exception
    //   437	507	756	java/lang/Exception
    //   519	574	756	java/lang/Exception
    //   579	596	756	java/lang/Exception
    //   596	626	756	java/lang/Exception
    //   628	639	756	java/lang/Exception
    //   639	673	756	java/lang/Exception
    //   673	698	756	java/lang/Exception
    //   698	709	756	java/lang/Exception
    //   711	725	756	java/lang/Exception
    //   725	751	756	java/lang/Exception
    //   792	809	756	java/lang/Exception
    //   812	829	756	java/lang/Exception
    //   832	850	756	java/lang/Exception
    //   861	887	756	java/lang/Exception
    //   890	907	756	java/lang/Exception
    //   910	929	756	java/lang/Exception
    //   936	951	756	java/lang/Exception
    //   954	999	756	java/lang/Exception
    //   1002	1018	756	java/lang/Exception
    //   1021	1041	756	java/lang/Exception
    //   1044	1055	756	java/lang/Exception
    //   1061	1078	756	java/lang/Exception
    //   1081	1092	756	java/lang/Exception
    //   1099	1114	756	java/lang/Exception
    //   1117	1134	756	java/lang/Exception
    //   1137	1154	756	java/lang/Exception
    //   1157	1173	756	java/lang/Exception
    //   1176	1192	756	java/lang/Exception
    //   1195	1211	756	java/lang/Exception
    //   1214	1233	756	java/lang/Exception
    //   1236	1251	756	java/lang/Exception
    //   1254	1274	756	java/lang/Exception
    //   1280	1309	756	java/lang/Exception
    //   1312	1318	756	java/lang/Exception
    //   4	27	787	finally
    //   27	37	787	finally
    //   760	784	787	finally
    //   27	37	1321	java/lang/Exception
    //   37	45	1346	finally
    //   50	107	1346	finally
    //   109	117	1346	finally
    //   119	133	1346	finally
    //   135	162	1346	finally
    //   162	205	1346	finally
    //   205	217	1346	finally
    //   217	227	1346	finally
    //   229	264	1346	finally
    //   273	325	1346	finally
    //   325	374	1346	finally
    //   374	437	1346	finally
    //   437	507	1346	finally
    //   519	574	1346	finally
    //   579	596	1346	finally
    //   596	626	1346	finally
    //   628	639	1346	finally
    //   639	673	1346	finally
    //   673	698	1346	finally
    //   698	709	1346	finally
    //   711	725	1346	finally
    //   725	751	1346	finally
    //   792	809	1346	finally
    //   812	829	1346	finally
    //   832	850	1346	finally
    //   861	887	1346	finally
    //   890	907	1346	finally
    //   910	929	1346	finally
    //   936	951	1346	finally
    //   954	999	1346	finally
    //   1002	1018	1346	finally
    //   1021	1041	1346	finally
    //   1044	1055	1346	finally
    //   1061	1078	1346	finally
    //   1081	1092	1346	finally
    //   1099	1114	1346	finally
    //   1117	1134	1346	finally
    //   1137	1154	1346	finally
    //   1157	1173	1346	finally
    //   1176	1192	1346	finally
    //   1195	1211	1346	finally
    //   1214	1233	1346	finally
    //   1236	1251	1346	finally
    //   1254	1274	1346	finally
    //   1280	1309	1346	finally
    //   1312	1318	1346	finally
  }

  public final void b()
  {
    try
    {
      this.c = a(new ByteArrayInputStream(j.k(this.b).getBytes("UTF-8")));
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        localUnsupportedEncodingException.printStackTrace();
    }
    finally
    {
    }
  }

  public class a
  {
    String a;
    String b;
    String c = "raw";
    boolean d = true;

    a()
    {
    }

    public static long a()
    {
      return System.currentTimeMillis() / 1000L;
    }

    public static String a(String paramString)
    {
      if ((paramString == null) || (paramString.length() == 0))
        return "";
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.reset();
        localMessageDigest.update(paramString.getBytes("UTF-8"));
        paramString = a(localMessageDigest.digest());
        return paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return "";
    }

    private static String a(byte[] paramArrayOfByte)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int j = paramArrayOfByte.length;
      int i = 0;
      if (i >= j)
        return localStringBuilder.toString();
      String str = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
      if (str.length() == 1)
        localStringBuilder.append("0").append(str);
      while (true)
      {
        i += 1;
        break;
        localStringBuilder.append(str);
      }
    }

    public static String b(String paramString)
    {
      if ((paramString == null) || (paramString.length() == 0))
        return "";
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
        localMessageDigest.update(paramString.getBytes("UTF-8"), 0, paramString.length());
        paramString = a(localMessageDigest.digest());
        return paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return "";
    }
  }

  final class b
  {
    String a;
    List<String> b;
    String c;
    String d;
    String e;
    String f;
    List<c.a> g;
    List<c.d> h;
    boolean i = false;
    boolean j = true;

    b()
    {
    }
  }

  final class c
  {
    List<c.b> a;

    c()
    {
    }
  }

  final class d
  {
    String a;
    String b;
    String c;
    boolean d = true;

    d()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.miaozhen.mzmonitor.c
 * JD-Core Version:    0.6.2
 */