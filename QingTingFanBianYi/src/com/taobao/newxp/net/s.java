package com.taobao.newxp.net;

import com.taobao.munion.base.volley.i;
import com.taobao.munion.base.volley.k;
import com.taobao.munion.base.volley.n;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.common.Log;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class s extends a
{
  public static String[] a;
  private boolean b = false;

  private s(String paramString, com.taobao.munion.base.f paramf)
  {
    super(paramString, paramf);
  }

  protected n<JSONObject> a(i parami)
  {
    try
    {
      parami = n.a(new JSONObject(new String(parami.b, com.taobao.munion.base.volley.a.f.a(parami.c))), com.taobao.munion.base.volley.a.f.a(parami));
      return parami;
    }
    catch (UnsupportedEncodingException parami)
    {
      return n.a(new k(parami));
    }
    catch (JSONException parami)
    {
    }
    return n.a(new k(parami));
  }

  public void a()
  {
    if (!this.b)
    {
      AlimmContext.getAliContext().getReportQueue().a(this);
      this.b = true;
    }
  }

  public static class a
  {
    private static final Random k = new Random();
    private static final int l = 32767;
    List<Promoter> a = new ArrayList();
    private MMEntity b;
    private String c;
    private String d;
    private String e;
    private int f;
    private int g;
    private int h;
    private String i;
    private String j;
    private int m = 0;
    private String n = "";
    private int o = 1;

    public a(MMEntity paramMMEntity)
    {
      this.b = paramMMEntity;
    }

    private boolean a(Map<String, Object> paramMap)
    {
      boolean bool1 = true;
      if (s.a == null)
        s.a = new String[] { "category", "sid", "device_id", "idmd5", "mc", "action_type", "action_index", "layout_type", "time", "date", "access", "access_subtype" };
      boolean bool2;
      if ((paramMap == null) || (paramMap.size() == 0))
      {
        bool2 = false;
        return bool2;
      }
      String[] arrayOfString = s.a;
      int i2 = arrayOfString.length;
      int i1 = 0;
      while (true)
      {
        bool2 = bool1;
        if (i1 >= i2)
          break;
        String str = arrayOfString[i1];
        if (!paramMap.containsKey(str))
        {
          Log.e(ExchangeConstants.LOG_TAG, "Report params has no required param [" + str + "]");
          bool1 = false;
        }
        i1 += 1;
      }
    }

    // ERROR //
    private Map<String, Object> c()
    {
      // Byte code:
      //   0: iconst_0
      //   1: istore 7
      //   3: invokestatic 136	com/taobao/newxp/common/AlimmContext:getAliContext	()Lcom/taobao/newxp/common/AlimmContext;
      //   6: astore_3
      //   7: aload_3
      //   8: invokevirtual 140	com/taobao/newxp/common/AlimmContext:getAppUtils	()Lcom/taobao/munion/base/a;
      //   11: astore 4
      //   13: new 142	java/util/HashMap
      //   16: dup
      //   17: invokespecial 143	java/util/HashMap:<init>	()V
      //   20: astore_2
      //   21: aload_0
      //   22: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   25: getfield 148	com/taobao/newxp/net/MMEntity:slotId	Ljava/lang/String;
      //   28: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   31: ifne +258 -> 289
      //   34: aload_2
      //   35: ldc 156
      //   37: aload_0
      //   38: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   41: getfield 148	com/taobao/newxp/net/MMEntity:slotId	Ljava/lang/String;
      //   44: invokeinterface 160 3 0
      //   49: pop
      //   50: aload_0
      //   51: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   54: invokevirtual 163	com/taobao/newxp/net/MMEntity:getTimeConsuming	()Ljava/lang/String;
      //   57: astore_1
      //   58: aload_1
      //   59: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   62: ifne +13 -> 75
      //   65: aload_2
      //   66: ldc 165
      //   68: aload_1
      //   69: invokeinterface 160 3 0
      //   74: pop
      //   75: aload_0
      //   76: getfield 167	com/taobao/newxp/net/s$a:i	Ljava/lang/String;
      //   79: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   82: ifne +16 -> 98
      //   85: aload_2
      //   86: ldc 169
      //   88: aload_0
      //   89: getfield 167	com/taobao/newxp/net/s$a:i	Ljava/lang/String;
      //   92: invokeinterface 160 3 0
      //   97: pop
      //   98: aload_0
      //   99: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   102: getfield 172	com/taobao/newxp/net/MMEntity:tabId	Ljava/lang/String;
      //   105: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   108: ifne +19 -> 127
      //   111: aload_2
      //   112: ldc 174
      //   114: aload_0
      //   115: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   118: getfield 172	com/taobao/newxp/net/MMEntity:tabId	Ljava/lang/String;
      //   121: invokeinterface 160 3 0
      //   126: pop
      //   127: aload_0
      //   128: getfield 176	com/taobao/newxp/net/s$a:j	Ljava/lang/String;
      //   131: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   134: ifne +16 -> 150
      //   137: aload_2
      //   138: ldc 178
      //   140: aload_0
      //   141: getfield 176	com/taobao/newxp/net/s$a:j	Ljava/lang/String;
      //   144: invokeinterface 160 3 0
      //   149: pop
      //   150: aload_2
      //   151: ldc 180
      //   153: getstatic 182	com/taobao/newxp/common/ExchangeConstants:sdk_version	Ljava/lang/String;
      //   156: invokeinterface 160 3 0
      //   161: pop
      //   162: aload_2
      //   163: ldc 184
      //   165: getstatic 186	com/taobao/newxp/common/ExchangeConstants:protocol_version	Ljava/lang/String;
      //   168: invokeinterface 160 3 0
      //   173: pop
      //   174: aload_2
      //   175: ldc 188
      //   177: invokestatic 194	java/lang/System:currentTimeMillis	()J
      //   180: invokestatic 200	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   183: invokeinterface 160 3 0
      //   188: pop
      //   189: aload_2
      //   190: ldc 202
      //   192: getstatic 207	android/os/Build:MODEL	Ljava/lang/String;
      //   195: invokeinterface 160 3 0
      //   200: pop
      //   201: aload_0
      //   202: getfield 46	com/taobao/newxp/net/s$a:n	Ljava/lang/String;
      //   205: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   208: istore 9
      //   210: iload 9
      //   212: ifne +185 -> 397
      //   215: aload_0
      //   216: getfield 46	com/taobao/newxp/net/s$a:n	Ljava/lang/String;
      //   219: ldc 209
      //   221: invokevirtual 213	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   224: astore 5
      //   226: new 142	java/util/HashMap
      //   229: dup
      //   230: invokespecial 143	java/util/HashMap:<init>	()V
      //   233: astore_1
      //   234: aload 5
      //   236: arraylength
      //   237: istore 8
      //   239: iload 7
      //   241: iload 8
      //   243: if_icmpge +98 -> 341
      //   246: aload 5
      //   248: iload 7
      //   250: aaload
      //   251: ldc 215
      //   253: invokevirtual 213	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   256: astore 6
      //   258: aload 6
      //   260: arraylength
      //   261: iconst_2
      //   262: if_icmpne +18 -> 280
      //   265: aload_1
      //   266: aload 6
      //   268: iconst_0
      //   269: aaload
      //   270: aload 6
      //   272: iconst_1
      //   273: aaload
      //   274: invokeinterface 160 3 0
      //   279: pop
      //   280: iload 7
      //   282: iconst_1
      //   283: iadd
      //   284: istore 7
      //   286: goto -47 -> 239
      //   289: aload_0
      //   290: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   293: getfield 218	com/taobao/newxp/net/MMEntity:appkey	Ljava/lang/String;
      //   296: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   299: ifne +32 -> 331
      //   302: aload_2
      //   303: ldc 220
      //   305: aload_0
      //   306: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   309: getfield 218	com/taobao/newxp/net/MMEntity:appkey	Ljava/lang/String;
      //   312: invokeinterface 160 3 0
      //   317: pop
      //   318: goto -268 -> 50
      //   321: astore_1
      //   322: new 222	java/lang/RuntimeException
      //   325: dup
      //   326: aload_1
      //   327: invokespecial 225	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
      //   330: athrow
      //   331: getstatic 103	com/taobao/newxp/common/ExchangeConstants:LOG_TAG	Ljava/lang/String;
      //   334: ldc 227
      //   336: invokestatic 229	com/taobao/newxp/common/Log:b	(Ljava/lang/String;Ljava/lang/String;)V
      //   339: aconst_null
      //   340: areturn
      //   341: aload_1
      //   342: invokeinterface 233 1 0
      //   347: invokeinterface 239 1 0
      //   352: astore 5
      //   354: aload 5
      //   356: invokeinterface 245 1 0
      //   361: ifeq +36 -> 397
      //   364: aload 5
      //   366: invokeinterface 249 1 0
      //   371: checkcast 64	java/lang/String
      //   374: astore 6
      //   376: aload_2
      //   377: aload 6
      //   379: aload_1
      //   380: aload 6
      //   382: invokeinterface 253 2 0
      //   387: invokeinterface 160 3 0
      //   392: pop
      //   393: goto -39 -> 354
      //   396: astore_1
      //   397: aload 4
      //   399: invokeinterface 258 1 0
      //   404: astore_1
      //   405: aload_1
      //   406: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   409: ifne +13 -> 422
      //   412: aload_2
      //   413: ldc 74
      //   415: aload_1
      //   416: invokeinterface 160 3 0
      //   421: pop
      //   422: aload_2
      //   423: ldc_w 260
      //   426: aload 4
      //   428: invokeinterface 263 1 0
      //   433: invokeinterface 160 3 0
      //   438: pop
      //   439: aload_0
      //   440: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   443: getfield 267	com/taobao/newxp/net/MMEntity:module	Lcom/taobao/newxp/a;
      //   446: ifnull +20 -> 466
      //   449: aload_2
      //   450: ldc_w 268
      //   453: aload_0
      //   454: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   457: getfield 267	com/taobao/newxp/net/MMEntity:module	Lcom/taobao/newxp/a;
      //   460: invokeinterface 160 3 0
      //   465: pop
      //   466: aload_2
      //   467: ldc_w 270
      //   470: getstatic 275	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   473: invokeinterface 160 3 0
      //   478: pop
      //   479: aload_2
      //   480: ldc_w 277
      //   483: ldc_w 279
      //   486: invokeinterface 160 3 0
      //   491: pop
      //   492: aload_2
      //   493: ldc_w 281
      //   496: getstatic 37	com/taobao/newxp/net/s$a:k	Ljava/util/Random;
      //   499: sipush 32767
      //   502: invokevirtual 285	java/util/Random:nextInt	(I)I
      //   505: invokestatic 290	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   508: invokeinterface 160 3 0
      //   513: pop
      //   514: aload_2
      //   515: ldc_w 292
      //   518: aload 4
      //   520: invokeinterface 295 1 0
      //   525: invokeinterface 160 3 0
      //   530: pop
      //   531: aload 4
      //   533: invokeinterface 299 1 0
      //   538: astore_1
      //   539: aload_2
      //   540: ldc 86
      //   542: aload_1
      //   543: iconst_0
      //   544: aaload
      //   545: invokeinterface 160 3 0
      //   550: pop
      //   551: aload_2
      //   552: ldc 88
      //   554: aload_1
      //   555: iconst_1
      //   556: aaload
      //   557: invokeinterface 160 3 0
      //   562: pop
      //   563: aload_0
      //   564: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   567: getfield 301	com/taobao/newxp/net/MMEntity:sid	Ljava/lang/String;
      //   570: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   573: ifne +19 -> 592
      //   576: aload_2
      //   577: ldc 68
      //   579: aload_0
      //   580: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   583: getfield 301	com/taobao/newxp/net/MMEntity:sid	Ljava/lang/String;
      //   586: invokeinterface 160 3 0
      //   591: pop
      //   592: aload_0
      //   593: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   596: getfield 304	com/taobao/newxp/net/MMEntity:psid	Ljava/lang/String;
      //   599: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   602: ifne +20 -> 622
      //   605: aload_2
      //   606: ldc_w 305
      //   609: aload_0
      //   610: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   613: getfield 304	com/taobao/newxp/net/MMEntity:psid	Ljava/lang/String;
      //   616: invokeinterface 160 3 0
      //   621: pop
      //   622: aload_2
      //   623: ldc 70
      //   625: aload 4
      //   627: invokeinterface 308 1 0
      //   632: invokeinterface 160 3 0
      //   637: pop
      //   638: aload_2
      //   639: ldc 72
      //   641: aload 4
      //   643: invokeinterface 308 1 0
      //   648: invokestatic 313	com/taobao/newxp/common/b/b:a	(Ljava/lang/String;)Ljava/lang/String;
      //   651: invokeinterface 160 3 0
      //   656: pop
      //   657: aload_3
      //   658: invokevirtual 317	com/taobao/newxp/common/AlimmContext:getAppContext	()Landroid/content/Context;
      //   661: aload 4
      //   663: invokestatic 322	com/taobao/newxp/net/b:a	(Landroid/content/Context;Lcom/taobao/munion/base/a;)Landroid/location/Location;
      //   666: astore_1
      //   667: aload_1
      //   668: ifnull +85 -> 753
      //   671: aload_2
      //   672: ldc_w 324
      //   675: aload_1
      //   676: invokevirtual 330	android/location/Location:getLatitude	()D
      //   679: invokestatic 333	java/lang/String:valueOf	(D)Ljava/lang/String;
      //   682: invokeinterface 160 3 0
      //   687: pop
      //   688: aload_2
      //   689: ldc_w 335
      //   692: aload_1
      //   693: invokevirtual 338	android/location/Location:getLongitude	()D
      //   696: invokestatic 333	java/lang/String:valueOf	(D)Ljava/lang/String;
      //   699: invokeinterface 160 3 0
      //   704: pop
      //   705: aload_2
      //   706: ldc_w 340
      //   709: aload_1
      //   710: invokevirtual 343	android/location/Location:getProvider	()Ljava/lang/String;
      //   713: invokeinterface 160 3 0
      //   718: pop
      //   719: aload_2
      //   720: ldc_w 345
      //   723: aload_1
      //   724: invokevirtual 348	android/location/Location:getTime	()J
      //   727: invokestatic 351	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   730: invokeinterface 160 3 0
      //   735: pop
      //   736: aload_2
      //   737: ldc_w 353
      //   740: aload_1
      //   741: invokevirtual 357	android/location/Location:getAccuracy	()F
      //   744: invokestatic 360	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   747: invokeinterface 160 3 0
      //   752: pop
      //   753: new 362	java/text/SimpleDateFormat
      //   756: dup
      //   757: ldc_w 364
      //   760: invokespecial 367	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
      //   763: new 369	java/util/Date
      //   766: dup
      //   767: invokespecial 370	java/util/Date:<init>	()V
      //   770: invokevirtual 376	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   773: astore 5
      //   775: aload 5
      //   777: ldc_w 378
      //   780: invokevirtual 213	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   783: iconst_0
      //   784: aaload
      //   785: astore_1
      //   786: aload 5
      //   788: ldc_w 378
      //   791: invokevirtual 213	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
      //   794: iconst_1
      //   795: aaload
      //   796: astore 5
      //   798: aload_2
      //   799: ldc 84
      //   801: aload_1
      //   802: invokeinterface 160 3 0
      //   807: pop
      //   808: aload_2
      //   809: ldc 82
      //   811: aload 5
      //   813: invokeinterface 160 3 0
      //   818: pop
      //   819: aload_2
      //   820: ldc_w 380
      //   823: aload 4
      //   825: invokeinterface 382 1 0
      //   830: invokeinterface 160 3 0
      //   835: pop
      //   836: aload_2
      //   837: ldc_w 384
      //   840: aload_0
      //   841: getfield 386	com/taobao/newxp/net/s$a:c	Ljava/lang/String;
      //   844: invokeinterface 160 3 0
      //   849: pop
      //   850: aload_0
      //   851: getfield 58	com/taobao/newxp/net/s$a:d	Ljava/lang/String;
      //   854: ifnull +291 -> 1145
      //   857: aload_0
      //   858: getfield 58	com/taobao/newxp/net/s$a:d	Ljava/lang/String;
      //   861: astore_1
      //   862: aload_2
      //   863: ldc_w 388
      //   866: aload_1
      //   867: invokeinterface 160 3 0
      //   872: pop
      //   873: aload_2
      //   874: ldc 66
      //   876: aload_0
      //   877: getfield 127	com/taobao/newxp/net/s$a:e	Ljava/lang/String;
      //   880: invokeinterface 160 3 0
      //   885: pop
      //   886: aload_2
      //   887: ldc 76
      //   889: aload_0
      //   890: getfield 390	com/taobao/newxp/net/s$a:f	I
      //   893: invokestatic 290	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   896: invokeinterface 160 3 0
      //   901: pop
      //   902: aload_2
      //   903: ldc 78
      //   905: aload_0
      //   906: getfield 392	com/taobao/newxp/net/s$a:g	I
      //   909: invokestatic 290	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   912: invokeinterface 160 3 0
      //   917: pop
      //   918: aload_2
      //   919: ldc 80
      //   921: aload_0
      //   922: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   925: getfield 395	com/taobao/newxp/net/MMEntity:layoutType	I
      //   928: invokestatic 290	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   931: invokeinterface 160 3 0
      //   936: pop
      //   937: aload_2
      //   938: ldc_w 397
      //   941: aload_0
      //   942: getfield 399	com/taobao/newxp/net/s$a:h	I
      //   945: invokestatic 290	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   948: invokeinterface 160 3 0
      //   953: pop
      //   954: getstatic 402	com/taobao/newxp/common/ExchangeConstants:CHANNEL	Ljava/lang/String;
      //   957: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   960: ifeq +150 -> 1110
      //   963: aload 4
      //   965: ldc_w 404
      //   968: invokeinterface 406 2 0
      //   973: astore_1
      //   974: aload_1
      //   975: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   978: ifne +14 -> 992
      //   981: aload_2
      //   982: ldc_w 408
      //   985: aload_1
      //   986: invokeinterface 160 3 0
      //   991: pop
      //   992: aload_0
      //   993: getfield 42	com/taobao/newxp/net/s$a:m	I
      //   996: ifeq +20 -> 1016
      //   999: aload_2
      //   1000: ldc_w 410
      //   1003: aload_0
      //   1004: getfield 42	com/taobao/newxp/net/s$a:m	I
      //   1007: invokestatic 290	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1010: invokeinterface 160 3 0
      //   1015: pop
      //   1016: aload_2
      //   1017: ldc_w 412
      //   1020: aload_0
      //   1021: getfield 48	com/taobao/newxp/net/s$a:o	I
      //   1024: invokestatic 290	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1027: invokeinterface 160 3 0
      //   1032: pop
      //   1033: aload_0
      //   1034: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   1037: getfield 148	com/taobao/newxp/net/MMEntity:slotId	Ljava/lang/String;
      //   1040: invokestatic 154	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
      //   1043: ifeq +74 -> 1117
      //   1046: aload_0
      //   1047: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   1050: getfield 218	com/taobao/newxp/net/MMEntity:appkey	Ljava/lang/String;
      //   1053: astore_1
      //   1054: new 414	com/taobao/newxp/controller/g
      //   1057: dup
      //   1058: aload_3
      //   1059: invokevirtual 317	com/taobao/newxp/common/AlimmContext:getAppContext	()Landroid/content/Context;
      //   1062: aload_1
      //   1063: invokespecial 417	com/taobao/newxp/controller/g:<init>	(Landroid/content/Context;Ljava/lang/String;)V
      //   1066: astore_1
      //   1067: aload_1
      //   1068: invokevirtual 419	com/taobao/newxp/controller/g:b	()Z
      //   1071: ifeq +23 -> 1094
      //   1074: aload_1
      //   1075: invokevirtual 421	com/taobao/newxp/controller/g:c	()Ljava/lang/String;
      //   1078: astore_1
      //   1079: aload_1
      //   1080: ifnull +14 -> 1094
      //   1083: aload_2
      //   1084: ldc_w 423
      //   1087: aload_1
      //   1088: invokeinterface 160 3 0
      //   1093: pop
      //   1094: aload_2
      //   1095: areturn
      //   1096: astore_1
      //   1097: getstatic 103	com/taobao/newxp/common/ExchangeConstants:LOG_TAG	Ljava/lang/String;
      //   1100: ldc 44
      //   1102: aload_1
      //   1103: invokestatic 429	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   1106: pop
      //   1107: goto -576 -> 531
      //   1110: getstatic 402	com/taobao/newxp/common/ExchangeConstants:CHANNEL	Ljava/lang/String;
      //   1113: astore_1
      //   1114: goto -140 -> 974
      //   1117: aload_0
      //   1118: getfield 55	com/taobao/newxp/net/s$a:b	Lcom/taobao/newxp/net/MMEntity;
      //   1121: getfield 148	com/taobao/newxp/net/MMEntity:slotId	Ljava/lang/String;
      //   1124: astore_1
      //   1125: goto -71 -> 1054
      //   1128: astore_1
      //   1129: getstatic 103	com/taobao/newxp/common/ExchangeConstants:LOG_TAG	Ljava/lang/String;
      //   1132: ldc 44
      //   1134: aload_1
      //   1135: invokestatic 432	com/taobao/newxp/common/Log:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
      //   1138: goto -44 -> 1094
      //   1141: astore_1
      //   1142: goto -389 -> 753
      //   1145: ldc 44
      //   1147: astore_1
      //   1148: goto -286 -> 862
      //
      // Exception table:
      //   from	to	target	type
      //   21	50	321	java/lang/Exception
      //   50	75	321	java/lang/Exception
      //   75	98	321	java/lang/Exception
      //   98	127	321	java/lang/Exception
      //   127	150	321	java/lang/Exception
      //   150	210	321	java/lang/Exception
      //   289	318	321	java/lang/Exception
      //   331	339	321	java/lang/Exception
      //   397	422	321	java/lang/Exception
      //   422	466	321	java/lang/Exception
      //   466	514	321	java/lang/Exception
      //   531	592	321	java/lang/Exception
      //   592	622	321	java/lang/Exception
      //   622	657	321	java/lang/Exception
      //   753	862	321	java/lang/Exception
      //   862	974	321	java/lang/Exception
      //   974	992	321	java/lang/Exception
      //   992	1016	321	java/lang/Exception
      //   1016	1054	321	java/lang/Exception
      //   1097	1107	321	java/lang/Exception
      //   1110	1114	321	java/lang/Exception
      //   1117	1125	321	java/lang/Exception
      //   1129	1138	321	java/lang/Exception
      //   215	239	396	java/lang/Exception
      //   246	280	396	java/lang/Exception
      //   341	354	396	java/lang/Exception
      //   354	393	396	java/lang/Exception
      //   514	531	1096	java/lang/Exception
      //   1054	1079	1128	java/lang/Exception
      //   1083	1094	1128	java/lang/Exception
      //   657	667	1141	java/lang/Exception
      //   671	753	1141	java/lang/Exception
    }

    public a a(int paramInt)
    {
      this.f = paramInt;
      return this;
    }

    public a a(String paramString)
    {
      this.i = paramString;
      return this;
    }

    public a a(Promoter[] paramArrayOfPromoter)
    {
      int i2 = 0;
      int i3 = paramArrayOfPromoter.length;
      int i1 = 0;
      Object localObject;
      while (i1 < i3)
      {
        localObject = paramArrayOfPromoter[i1];
        this.a.add(localObject);
        i1 += 1;
      }
      if ((this.a != null) && (this.a.size() > 0))
      {
        i3 = this.a.size();
        try
        {
          paramArrayOfPromoter = (Promoter)this.a.get(0);
          if (this.a.size() == 1)
          {
            this.n = paramArrayOfPromoter.prom_act_pams;
            Log.a(ExchangeConstants.LOG_TAG, "set promoter act_pams to report act_params. [" + this.n + "]");
          }
          while (true)
          {
            label141: paramArrayOfPromoter = new StringBuffer();
            localObject = new StringBuffer();
            i1 = i2;
            while (i1 < i3)
            {
              Promoter localPromoter = (Promoter)this.a.get(i1);
              paramArrayOfPromoter.append(localPromoter.promoter + ",");
              ((StringBuffer)localObject).append(localPromoter.category + ",");
              i1 += 1;
            }
            this.n = paramArrayOfPromoter.slot_act_pams;
            Log.a(ExchangeConstants.LOG_TAG, "set slot act_pams to report act_params. [" + this.n + "]");
          }
        }
        catch (NullPointerException paramArrayOfPromoter)
        {
          break label141;
          paramArrayOfPromoter.deleteCharAt(paramArrayOfPromoter.length() - 1);
          this.d = paramArrayOfPromoter.toString();
          this.e = ((StringBuffer)localObject).toString();
        }
      }
      return this;
    }

    public s a()
    {
      Map localMap = b();
      return new s(b.a(n.f[0], localMap), null, null);
    }

    public a b(int paramInt)
    {
      this.g = paramInt;
      return this;
    }

    public a b(String paramString)
    {
      this.j = paramString;
      return this;
    }

    public Map<String, Object> b()
    {
      if (AlimmContext.getAliContext().getAppUtils().l());
      for (Object localObject = "0"; ; localObject = "1")
      {
        this.c = ((String)localObject);
        localObject = c();
        if ((ExchangeConstants.DEBUG_MODE) && (!a((Map)localObject)))
          Log.e(ExchangeConstants.LOG_TAG, "Report params verify failed...");
        return localObject;
      }
    }

    public a c(int paramInt)
    {
      this.h = paramInt;
      return this;
    }

    public a d(int paramInt)
    {
      this.o = paramInt;
      return this;
    }

    public a e(int paramInt)
    {
      this.m = paramInt;
      return this;
    }
  }

  public static class b extends s.a
  {
    public b(MMEntity paramMMEntity)
    {
      super();
      s.a.a(this, "custom");
      s.a.b(this, "-1");
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.s
 * JD-Core Version:    0.6.2
 */