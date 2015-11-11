package cn.com.mma.mobile.tracking.util;

public class XmlUtil
{
  public static final String XMLFILE = "sdkconfig.xml";

  // ERROR //
  public static cn.com.mma.mobile.tracking.bean.SDK doParser(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_1
    //   3: astore 4
    //   5: invokestatic 23	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   8: astore 6
    //   10: aload_1
    //   11: astore 4
    //   13: aload 6
    //   15: aload_0
    //   16: ldc 25
    //   18: invokeinterface 31 3 0
    //   23: aload_1
    //   24: astore 4
    //   26: aload 6
    //   28: invokeinterface 35 1 0
    //   33: istore 8
    //   35: aconst_null
    //   36: astore_2
    //   37: aconst_null
    //   38: astore_3
    //   39: aconst_null
    //   40: astore_1
    //   41: aconst_null
    //   42: astore_0
    //   43: goto +1123 -> 1166
    //   46: aload_0
    //   47: astore 4
    //   49: aload 6
    //   51: invokeinterface 38 1 0
    //   56: istore 8
    //   58: aload_1
    //   59: astore 4
    //   61: aload_2
    //   62: astore_1
    //   63: aload 4
    //   65: astore_2
    //   66: goto +1100 -> 1166
    //   69: new 40	cn/com/mma/mobile/tracking/bean/SDK
    //   72: dup
    //   73: invokespecial 41	cn/com/mma/mobile/tracking/bean/SDK:<init>	()V
    //   76: astore 4
    //   78: aload_2
    //   79: astore_0
    //   80: aload_1
    //   81: astore_2
    //   82: aload_0
    //   83: astore_1
    //   84: aload 4
    //   86: astore_0
    //   87: goto -41 -> 46
    //   90: aload 6
    //   92: invokeinterface 45 1 0
    //   97: astore 7
    //   99: ldc 47
    //   101: aload 7
    //   103: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   106: ifeq +14 -> 120
    //   109: aload_0
    //   110: new 55	cn/com/mma/mobile/tracking/bean/OfflineCache
    //   113: dup
    //   114: invokespecial 56	cn/com/mma/mobile/tracking/bean/OfflineCache:<init>	()V
    //   117: putfield 59	cn/com/mma/mobile/tracking/bean/SDK:offlineCache	Lcn/com/mma/mobile/tracking/bean/OfflineCache;
    //   120: aload_0
    //   121: getfield 59	cn/com/mma/mobile/tracking/bean/SDK:offlineCache	Lcn/com/mma/mobile/tracking/bean/OfflineCache;
    //   124: ifnull +75 -> 199
    //   127: ldc 61
    //   129: aload 7
    //   131: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   134: ifeq +17 -> 151
    //   137: aload_0
    //   138: getfield 59	cn/com/mma/mobile/tracking/bean/SDK:offlineCache	Lcn/com/mma/mobile/tracking/bean/OfflineCache;
    //   141: aload 6
    //   143: invokeinterface 64 1 0
    //   148: putfield 66	cn/com/mma/mobile/tracking/bean/OfflineCache:length	Ljava/lang/String;
    //   151: ldc 68
    //   153: aload 7
    //   155: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   158: ifeq +17 -> 175
    //   161: aload_0
    //   162: getfield 59	cn/com/mma/mobile/tracking/bean/SDK:offlineCache	Lcn/com/mma/mobile/tracking/bean/OfflineCache;
    //   165: aload 6
    //   167: invokeinterface 64 1 0
    //   172: putfield 70	cn/com/mma/mobile/tracking/bean/OfflineCache:queueExpirationSecs	Ljava/lang/String;
    //   175: ldc 72
    //   177: aload 7
    //   179: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   182: ifeq +17 -> 199
    //   185: aload_0
    //   186: getfield 59	cn/com/mma/mobile/tracking/bean/SDK:offlineCache	Lcn/com/mma/mobile/tracking/bean/OfflineCache;
    //   189: aload 6
    //   191: invokeinterface 64 1 0
    //   196: putfield 74	cn/com/mma/mobile/tracking/bean/OfflineCache:timeout	Ljava/lang/String;
    //   199: ldc 76
    //   201: aload 7
    //   203: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   206: ifeq +14 -> 220
    //   209: aload_0
    //   210: new 78	java/util/ArrayList
    //   213: dup
    //   214: invokespecial 79	java/util/ArrayList:<init>	()V
    //   217: putfield 82	cn/com/mma/mobile/tracking/bean/SDK:companies	Ljava/util/List;
    //   220: aload_0
    //   221: getfield 82	cn/com/mma/mobile/tracking/bean/SDK:companies	Ljava/util/List;
    //   224: ifnull +939 -> 1163
    //   227: ldc 84
    //   229: aload 7
    //   231: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   234: ifeq +929 -> 1163
    //   237: new 86	cn/com/mma/mobile/tracking/bean/Company
    //   240: dup
    //   241: invokespecial 87	cn/com/mma/mobile/tracking/bean/Company:<init>	()V
    //   244: astore_3
    //   245: aload_3
    //   246: ifnull +906 -> 1152
    //   249: ldc 89
    //   251: aload 7
    //   253: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   256: ifeq +21 -> 277
    //   259: aload_3
    //   260: getfield 91	cn/com/mma/mobile/tracking/bean/Company:name	Ljava/lang/String;
    //   263: ifnonnull +14 -> 277
    //   266: aload_3
    //   267: aload 6
    //   269: invokeinterface 64 1 0
    //   274: putfield 91	cn/com/mma/mobile/tracking/bean/Company:name	Ljava/lang/String;
    //   277: ldc 93
    //   279: aload 7
    //   281: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   284: ifeq +14 -> 298
    //   287: aload_3
    //   288: new 95	cn/com/mma/mobile/tracking/bean/Domain
    //   291: dup
    //   292: invokespecial 96	cn/com/mma/mobile/tracking/bean/Domain:<init>	()V
    //   295: putfield 99	cn/com/mma/mobile/tracking/bean/Company:domain	Lcn/com/mma/mobile/tracking/bean/Domain;
    //   298: aload_3
    //   299: getfield 99	cn/com/mma/mobile/tracking/bean/Company:domain	Lcn/com/mma/mobile/tracking/bean/Domain;
    //   302: ifnull +27 -> 329
    //   305: ldc 101
    //   307: aload 7
    //   309: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   312: ifeq +17 -> 329
    //   315: aload_3
    //   316: getfield 99	cn/com/mma/mobile/tracking/bean/Company:domain	Lcn/com/mma/mobile/tracking/bean/Domain;
    //   319: aload 6
    //   321: invokeinterface 64 1 0
    //   326: putfield 103	cn/com/mma/mobile/tracking/bean/Domain:url	Ljava/lang/String;
    //   329: ldc 105
    //   331: aload 7
    //   333: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   336: ifeq +14 -> 350
    //   339: aload_3
    //   340: new 107	cn/com/mma/mobile/tracking/bean/Signature
    //   343: dup
    //   344: invokespecial 108	cn/com/mma/mobile/tracking/bean/Signature:<init>	()V
    //   347: putfield 111	cn/com/mma/mobile/tracking/bean/Company:signature	Lcn/com/mma/mobile/tracking/bean/Signature;
    //   350: aload_3
    //   351: getfield 111	cn/com/mma/mobile/tracking/bean/Company:signature	Lcn/com/mma/mobile/tracking/bean/Signature;
    //   354: ifnull +51 -> 405
    //   357: ldc 113
    //   359: aload 7
    //   361: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   364: ifeq +17 -> 381
    //   367: aload_3
    //   368: getfield 111	cn/com/mma/mobile/tracking/bean/Company:signature	Lcn/com/mma/mobile/tracking/bean/Signature;
    //   371: aload 6
    //   373: invokeinterface 64 1 0
    //   378: putfield 115	cn/com/mma/mobile/tracking/bean/Signature:publicKey	Ljava/lang/String;
    //   381: ldc 117
    //   383: aload 7
    //   385: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   388: ifeq +17 -> 405
    //   391: aload_3
    //   392: getfield 111	cn/com/mma/mobile/tracking/bean/Company:signature	Lcn/com/mma/mobile/tracking/bean/Signature;
    //   395: aload 6
    //   397: invokeinterface 64 1 0
    //   402: putfield 119	cn/com/mma/mobile/tracking/bean/Signature:paramKey	Ljava/lang/String;
    //   405: ldc 121
    //   407: aload 7
    //   409: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   412: ifeq +14 -> 426
    //   415: aload_3
    //   416: new 123	cn/com/mma/mobile/tracking/bean/Switch
    //   419: dup
    //   420: invokespecial 124	cn/com/mma/mobile/tracking/bean/Switch:<init>	()V
    //   423: putfield 128	cn/com/mma/mobile/tracking/bean/Company:sswitch	Lcn/com/mma/mobile/tracking/bean/Switch;
    //   426: aload_3
    //   427: getfield 128	cn/com/mma/mobile/tracking/bean/Company:sswitch	Lcn/com/mma/mobile/tracking/bean/Switch;
    //   430: ifnull +150 -> 580
    //   433: ldc 130
    //   435: aload 7
    //   437: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   440: ifeq +20 -> 460
    //   443: aload_3
    //   444: getfield 128	cn/com/mma/mobile/tracking/bean/Company:sswitch	Lcn/com/mma/mobile/tracking/bean/Switch;
    //   447: aload 6
    //   449: invokeinterface 64 1 0
    //   454: invokestatic 136	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   457: putfield 139	cn/com/mma/mobile/tracking/bean/Switch:isTrackLocation	Z
    //   460: ldc 141
    //   462: aload 7
    //   464: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   467: ifeq +17 -> 484
    //   470: aload_3
    //   471: getfield 128	cn/com/mma/mobile/tracking/bean/Company:sswitch	Lcn/com/mma/mobile/tracking/bean/Switch;
    //   474: aload 6
    //   476: invokeinterface 64 1 0
    //   481: putfield 143	cn/com/mma/mobile/tracking/bean/Switch:offlineCacheExpiration	Ljava/lang/String;
    //   484: ldc 145
    //   486: aload 7
    //   488: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   491: ifeq +17 -> 508
    //   494: aload_3
    //   495: getfield 128	cn/com/mma/mobile/tracking/bean/Company:sswitch	Lcn/com/mma/mobile/tracking/bean/Switch;
    //   498: new 147	java/util/HashMap
    //   501: dup
    //   502: invokespecial 148	java/util/HashMap:<init>	()V
    //   505: putfield 151	cn/com/mma/mobile/tracking/bean/Switch:encrypt	Ljava/util/Map;
    //   508: aload_3
    //   509: getfield 128	cn/com/mma/mobile/tracking/bean/Company:sswitch	Lcn/com/mma/mobile/tracking/bean/Switch;
    //   512: getfield 151	cn/com/mma/mobile/tracking/bean/Switch:encrypt	Ljava/util/Map;
    //   515: ifnull +65 -> 580
    //   518: ldc 153
    //   520: aload 7
    //   522: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   525: ifne +33 -> 558
    //   528: ldc 155
    //   530: aload 7
    //   532: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   535: ifne +23 -> 558
    //   538: ldc 157
    //   540: aload 7
    //   542: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   545: ifne +13 -> 558
    //   548: ldc 159
    //   550: aload 7
    //   552: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   555: ifeq +25 -> 580
    //   558: aload_3
    //   559: getfield 128	cn/com/mma/mobile/tracking/bean/Company:sswitch	Lcn/com/mma/mobile/tracking/bean/Switch;
    //   562: getfield 151	cn/com/mma/mobile/tracking/bean/Switch:encrypt	Ljava/util/Map;
    //   565: aload 7
    //   567: aload 6
    //   569: invokeinterface 64 1 0
    //   574: invokeinterface 165 3 0
    //   579: pop
    //   580: ldc 167
    //   582: aload 7
    //   584: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   587: ifeq +14 -> 601
    //   590: aload_3
    //   591: new 169	cn/com/mma/mobile/tracking/bean/Config
    //   594: dup
    //   595: invokespecial 170	cn/com/mma/mobile/tracking/bean/Config:<init>	()V
    //   598: putfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   601: aload_3
    //   602: getfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   605: ifnull +536 -> 1141
    //   608: ldc 175
    //   610: aload 7
    //   612: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   615: ifeq +17 -> 632
    //   618: aload_3
    //   619: getfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   622: new 78	java/util/ArrayList
    //   625: dup
    //   626: invokespecial 79	java/util/ArrayList:<init>	()V
    //   629: putfield 177	cn/com/mma/mobile/tracking/bean/Config:arguments	Ljava/util/List;
    //   632: aload_3
    //   633: getfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   636: getfield 177	cn/com/mma/mobile/tracking/bean/Config:arguments	Ljava/util/List;
    //   639: ifnull +496 -> 1135
    //   642: ldc 179
    //   644: aload 7
    //   646: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   649: ifeq +486 -> 1135
    //   652: new 181	cn/com/mma/mobile/tracking/bean/Argument
    //   655: dup
    //   656: invokespecial 182	cn/com/mma/mobile/tracking/bean/Argument:<init>	()V
    //   659: astore 4
    //   661: aload 4
    //   663: ifnull +97 -> 760
    //   666: ldc 184
    //   668: aload 7
    //   670: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   673: ifeq +15 -> 688
    //   676: aload 4
    //   678: aload 6
    //   680: invokeinterface 64 1 0
    //   685: putfield 186	cn/com/mma/mobile/tracking/bean/Argument:key	Ljava/lang/String;
    //   688: ldc 188
    //   690: aload 7
    //   692: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   695: ifeq +15 -> 710
    //   698: aload 4
    //   700: aload 6
    //   702: invokeinterface 64 1 0
    //   707: putfield 190	cn/com/mma/mobile/tracking/bean/Argument:value	Ljava/lang/String;
    //   710: ldc 192
    //   712: aload 7
    //   714: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   717: ifeq +18 -> 735
    //   720: aload 4
    //   722: aload 6
    //   724: invokeinterface 64 1 0
    //   729: invokestatic 136	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   732: putfield 194	cn/com/mma/mobile/tracking/bean/Argument:urlEncode	Z
    //   735: ldc 196
    //   737: aload 7
    //   739: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   742: ifeq +18 -> 760
    //   745: aload 4
    //   747: aload 6
    //   749: invokeinterface 64 1 0
    //   754: invokestatic 136	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   757: putfield 198	cn/com/mma/mobile/tracking/bean/Argument:isRequired	Z
    //   760: ldc 200
    //   762: aload 7
    //   764: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   767: ifeq +17 -> 784
    //   770: aload_3
    //   771: getfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   774: new 78	java/util/ArrayList
    //   777: dup
    //   778: invokespecial 79	java/util/ArrayList:<init>	()V
    //   781: putfield 202	cn/com/mma/mobile/tracking/bean/Config:events	Ljava/util/List;
    //   784: aload_3
    //   785: getfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   788: getfield 202	cn/com/mma/mobile/tracking/bean/Config:events	Ljava/util/List;
    //   791: ifnull +338 -> 1129
    //   794: ldc 204
    //   796: aload 7
    //   798: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   801: ifeq +328 -> 1129
    //   804: new 206	cn/com/mma/mobile/tracking/bean/Event
    //   807: dup
    //   808: invokespecial 207	cn/com/mma/mobile/tracking/bean/Event:<init>	()V
    //   811: astore 5
    //   813: aload 4
    //   815: astore_2
    //   816: aload 5
    //   818: astore_1
    //   819: aload 5
    //   821: ifnull +84 -> 905
    //   824: ldc 184
    //   826: aload 7
    //   828: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   831: ifeq +15 -> 846
    //   834: aload 5
    //   836: aload 6
    //   838: invokeinterface 64 1 0
    //   843: putfield 208	cn/com/mma/mobile/tracking/bean/Event:key	Ljava/lang/String;
    //   846: ldc 188
    //   848: aload 7
    //   850: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   853: ifeq +15 -> 868
    //   856: aload 5
    //   858: aload 6
    //   860: invokeinterface 64 1 0
    //   865: putfield 209	cn/com/mma/mobile/tracking/bean/Event:value	Ljava/lang/String;
    //   868: aload 4
    //   870: astore_2
    //   871: aload 5
    //   873: astore_1
    //   874: ldc 192
    //   876: aload 7
    //   878: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   881: ifeq +24 -> 905
    //   884: aload 5
    //   886: aload 6
    //   888: invokeinterface 64 1 0
    //   893: invokestatic 136	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   896: putfield 210	cn/com/mma/mobile/tracking/bean/Event:urlEncode	Z
    //   899: aload 5
    //   901: astore_1
    //   902: aload 4
    //   904: astore_2
    //   905: ldc 212
    //   907: aload 7
    //   909: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   912: ifeq +14 -> 926
    //   915: aload_3
    //   916: aload 6
    //   918: invokeinterface 64 1 0
    //   923: putfield 214	cn/com/mma/mobile/tracking/bean/Company:separator	Ljava/lang/String;
    //   926: ldc 216
    //   928: aload 7
    //   930: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   933: ifeq +14 -> 947
    //   936: aload_3
    //   937: aload 6
    //   939: invokeinterface 64 1 0
    //   944: putfield 218	cn/com/mma/mobile/tracking/bean/Company:equalizer	Ljava/lang/String;
    //   947: ldc 220
    //   949: aload 7
    //   951: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   954: ifeq +172 -> 1126
    //   957: aload_3
    //   958: aload 6
    //   960: invokeinterface 64 1 0
    //   965: invokestatic 136	java/lang/Boolean:parseBoolean	(Ljava/lang/String;)Z
    //   968: putfield 222	cn/com/mma/mobile/tracking/bean/Company:timeStampUseSecond	Z
    //   971: goto -925 -> 46
    //   974: aload 6
    //   976: invokeinterface 45 1 0
    //   981: astore 4
    //   983: ldc 84
    //   985: aload 4
    //   987: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   990: ifeq +133 -> 1123
    //   993: aload_0
    //   994: getfield 82	cn/com/mma/mobile/tracking/bean/SDK:companies	Ljava/util/List;
    //   997: aload_3
    //   998: invokeinterface 227 2 0
    //   1003: pop
    //   1004: aconst_null
    //   1005: astore_3
    //   1006: ldc 179
    //   1008: aload 4
    //   1010: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1013: ifeq +107 -> 1120
    //   1016: aload_3
    //   1017: getfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   1020: getfield 177	cn/com/mma/mobile/tracking/bean/Config:arguments	Ljava/util/List;
    //   1023: aload_1
    //   1024: invokeinterface 227 2 0
    //   1029: pop
    //   1030: aconst_null
    //   1031: astore_1
    //   1032: ldc 204
    //   1034: aload 4
    //   1036: invokevirtual 53	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1039: ifeq +70 -> 1109
    //   1042: aload_3
    //   1043: getfield 173	cn/com/mma/mobile/tracking/bean/Company:config	Lcn/com/mma/mobile/tracking/bean/Config;
    //   1046: getfield 202	cn/com/mma/mobile/tracking/bean/Config:events	Ljava/util/List;
    //   1049: aload_2
    //   1050: invokeinterface 227 2 0
    //   1055: pop
    //   1056: aconst_null
    //   1057: astore 4
    //   1059: aload_1
    //   1060: astore_2
    //   1061: aload 4
    //   1063: astore_1
    //   1064: goto -1018 -> 46
    //   1067: astore_0
    //   1068: aload 4
    //   1070: astore_1
    //   1071: aload_0
    //   1072: invokevirtual 230	java/lang/Exception:printStackTrace	()V
    //   1075: aload_1
    //   1076: areturn
    //   1077: astore_2
    //   1078: aload_0
    //   1079: astore_1
    //   1080: aload_2
    //   1081: astore_0
    //   1082: goto -11 -> 1071
    //   1085: astore_2
    //   1086: aload_0
    //   1087: astore_1
    //   1088: aload_2
    //   1089: astore_0
    //   1090: goto -19 -> 1071
    //   1093: astore_2
    //   1094: aload_0
    //   1095: astore_1
    //   1096: aload_2
    //   1097: astore_0
    //   1098: goto -27 -> 1071
    //   1101: astore_2
    //   1102: aload_0
    //   1103: astore_1
    //   1104: aload_2
    //   1105: astore_0
    //   1106: goto -35 -> 1071
    //   1109: aload_2
    //   1110: astore 4
    //   1112: aload_1
    //   1113: astore_2
    //   1114: aload 4
    //   1116: astore_1
    //   1117: goto -1071 -> 46
    //   1120: goto -88 -> 1032
    //   1123: goto -117 -> 1006
    //   1126: goto -1080 -> 46
    //   1129: aload_2
    //   1130: astore 5
    //   1132: goto -319 -> 813
    //   1135: aload_1
    //   1136: astore 4
    //   1138: goto -477 -> 661
    //   1141: aload_2
    //   1142: astore 4
    //   1144: aload_1
    //   1145: astore_2
    //   1146: aload 4
    //   1148: astore_1
    //   1149: goto -244 -> 905
    //   1152: aload_2
    //   1153: astore 4
    //   1155: aload_1
    //   1156: astore_2
    //   1157: aload 4
    //   1159: astore_1
    //   1160: goto -1114 -> 46
    //   1163: goto -918 -> 245
    //   1166: iload 8
    //   1168: iconst_1
    //   1169: if_icmpne +5 -> 1174
    //   1172: aload_0
    //   1173: areturn
    //   1174: iload 8
    //   1176: tableswitch	default:+32 -> 1208, 0:+-1107->69, 1:+32->1208, 2:+-1086->90, 3:+-202->974
    //   1209: astore 4
    //   1211: aload_1
    //   1212: astore_2
    //   1213: aload 4
    //   1215: astore_1
    //   1216: goto -1170 -> 46
    //
    // Exception table:
    //   from	to	target	type
    //   5	10	1067	java/lang/Exception
    //   13	23	1067	java/lang/Exception
    //   26	35	1067	java/lang/Exception
    //   49	58	1067	java/lang/Exception
    //   69	78	1077	java/lang/Exception
    //   90	120	1077	java/lang/Exception
    //   120	151	1077	java/lang/Exception
    //   151	175	1077	java/lang/Exception
    //   175	199	1077	java/lang/Exception
    //   199	220	1077	java/lang/Exception
    //   220	245	1077	java/lang/Exception
    //   974	1004	1077	java/lang/Exception
    //   249	277	1085	java/lang/Exception
    //   277	298	1085	java/lang/Exception
    //   298	329	1085	java/lang/Exception
    //   329	350	1085	java/lang/Exception
    //   350	381	1085	java/lang/Exception
    //   381	405	1085	java/lang/Exception
    //   405	426	1085	java/lang/Exception
    //   426	460	1085	java/lang/Exception
    //   460	484	1085	java/lang/Exception
    //   484	508	1085	java/lang/Exception
    //   508	558	1085	java/lang/Exception
    //   558	580	1085	java/lang/Exception
    //   580	601	1085	java/lang/Exception
    //   601	632	1085	java/lang/Exception
    //   632	661	1085	java/lang/Exception
    //   1006	1030	1085	java/lang/Exception
    //   666	688	1093	java/lang/Exception
    //   688	710	1093	java/lang/Exception
    //   710	735	1093	java/lang/Exception
    //   735	760	1093	java/lang/Exception
    //   760	784	1093	java/lang/Exception
    //   784	813	1093	java/lang/Exception
    //   1032	1056	1093	java/lang/Exception
    //   824	846	1101	java/lang/Exception
    //   846	868	1101	java/lang/Exception
    //   874	899	1101	java/lang/Exception
    //   905	926	1101	java/lang/Exception
    //   926	947	1101	java/lang/Exception
    //   947	971	1101	java/lang/Exception
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     cn.com.mma.mobile.tracking.util.XmlUtil
 * JD-Core Version:    0.6.2
 */