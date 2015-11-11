package com.tencent.open.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import com.tencent.utils.OpenConfig;
import java.util.ArrayList;
import java.util.Random;

public class c
{
  private static c a = null;
  private long b = 0L;
  private int c = 3;
  private boolean d = false;
  private Random e = new Random();
  private b f;
  private ArrayList<d> g = new ArrayList();
  private ArrayList<d> h = new ArrayList();

  public static c a()
  {
    if (a == null)
      a = new c();
    return a;
  }

  private String a(Context paramContext)
  {
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        Log.e("cgi_report_debug", "ReportManager getAPN failed:ConnectivityManager == null");
        return "no_net";
      }
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext == null) || (!paramContext.isAvailable()))
      {
        Log.e("cgi_report_debug", "ReportManager getAPN failed:NetworkInfo == null");
        return "no_net";
      }
      if (paramContext.getTypeName().toUpperCase().equals("WIFI"))
      {
        Log.i("cgi_report_debug", "ReportManager getAPN type = wifi");
        return "wifi";
      }
      paramContext = paramContext.getExtraInfo();
      if (paramContext == null)
      {
        Log.e("cgi_report_debug", "ReportManager getAPN failed:extraInfo == null");
        return "mobile_unknow";
      }
      paramContext = paramContext.toLowerCase();
      Log.i("cgi_report_debug", "ReportManager getAPN type = " + paramContext);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "unknow";
  }

  private void a(Context paramContext, String paramString)
  {
    Log.i("cgi_report_debug", "ReportManager doUpload start");
    this.d = true;
    this.g = this.f.c();
    this.f.b();
    this.h = this.f.d();
    this.f.a();
    Bundle localBundle = new Bundle();
    localBundle.putString("appid", paramString);
    localBundle.putString("releaseversion", "QQConnect_SDK_Android_1_7");
    localBundle.putString("device", Build.DEVICE);
    localBundle.putString("qua", "V1_AND_OpenSDK_2.2.1_1077_RDM_B");
    localBundle.putString("key", "apn,frequency,commandid,resultcode,tmcost,reqsize,rspsize,detail,deviceinfo");
    int i = 0;
    while (i < this.g.size())
    {
      localBundle.putString(i + "_1", ((d)this.g.get(i)).a());
      localBundle.putString(i + "_2", ((d)this.g.get(i)).b());
      localBundle.putString(i + "_3", ((d)this.g.get(i)).c());
      localBundle.putString(i + "_4", ((d)this.g.get(i)).d());
      localBundle.putString(i + "_5", ((d)this.g.get(i)).e());
      localBundle.putString(i + "_6", ((d)this.g.get(i)).f());
      localBundle.putString(i + "_7", ((d)this.g.get(i)).g());
      localBundle.putString(i + "_8", ((d)this.g.get(i)).h());
      paramString = a.b(paramContext) + ((d)this.g.get(i)).i();
      localBundle.putString(i + "_9", paramString);
      i += 1;
    }
    i = this.g.size();
    while (i < this.h.size() + this.g.size())
    {
      int j = i - this.g.size();
      localBundle.putString(i + "_1", ((d)this.h.get(j)).a());
      localBundle.putString(i + "_2", ((d)this.h.get(j)).b());
      localBundle.putString(i + "_3", ((d)this.h.get(j)).c());
      localBundle.putString(i + "_4", ((d)this.h.get(j)).d());
      localBundle.putString(i + "_5", ((d)this.h.get(j)).e());
      localBundle.putString(i + "_6", ((d)this.h.get(j)).f());
      localBundle.putString(i + "_7", ((d)this.h.get(j)).g());
      localBundle.putString(i + "_8", ((d)this.h.get(j)).h());
      paramString = a.b(paramContext) + ((d)this.h.get(j)).i();
      localBundle.putString(i + "_9", paramString);
      i += 1;
    }
    a(paramContext, "http://wspeed.qq.com/w.cgi", "POST", localBundle);
  }

  private void a(Context paramContext, String paramString1, long paramLong1, long paramLong2, long paramLong3, int paramInt, String paramString2, String paramString3)
  {
    paramLong1 = SystemClock.elapsedRealtime() - paramLong1;
    Log.i("cgi_report_debug", "ReportManager updateDB url=" + paramString1 + ",resultCode=" + paramInt + ",timeCost=" + paramLong1 + ",reqSize=" + paramLong2 + ",rspSize=" + paramLong3);
    int i = 100 / b(paramContext, paramInt);
    if (i <= 0)
      i = 1;
    while (true)
    {
      paramContext = a(paramContext);
      this.f.a(paramContext, i + "", paramString1, paramInt, paramLong1, paramLong2, paramLong3, paramString3);
      return;
      if (i > 100)
        i = 100;
    }
  }

  private void a(final Context paramContext, final String paramString1, String paramString2, final Bundle paramBundle)
  {
    new Thread()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: ldc 39
        //   2: new 41	java/lang/StringBuilder
        //   5: dup
        //   6: invokespecial 42	java/lang/StringBuilder:<init>	()V
        //   9: ldc 44
        //   11: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   14: aload_0
        //   15: getfield 22	com/tencent/open/a/c$1:a	Ljava/lang/String;
        //   18: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   21: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   24: invokestatic 58	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   27: pop
        //   28: aload_0
        //   29: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   32: aload_0
        //   33: getfield 24	com/tencent/open/a/c$1:b	Landroid/content/Context;
        //   36: aconst_null
        //   37: invokestatic 64	com/tencent/utils/OpenConfig:getInstance	(Landroid/content/Context;Ljava/lang/String;)Lcom/tencent/utils/OpenConfig;
        //   40: ldc 66
        //   42: invokevirtual 70	com/tencent/utils/OpenConfig:getInt	(Ljava/lang/String;)I
        //   45: invokestatic 73	com/tencent/open/a/c:a	(Lcom/tencent/open/a/c;I)I
        //   48: pop
        //   49: aload_0
        //   50: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   53: astore_1
        //   54: aload_0
        //   55: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   58: invokestatic 76	com/tencent/open/a/c:a	(Lcom/tencent/open/a/c;)I
        //   61: ifne +192 -> 253
        //   64: iconst_3
        //   65: istore_3
        //   66: aload_1
        //   67: iload_3
        //   68: invokestatic 73	com/tencent/open/a/c:a	(Lcom/tencent/open/a/c;I)I
        //   71: pop
        //   72: iconst_0
        //   73: istore_3
        //   74: iconst_0
        //   75: istore 5
        //   77: iload 5
        //   79: iconst_1
        //   80: iadd
        //   81: istore 6
        //   83: ldc 39
        //   85: new 41	java/lang/StringBuilder
        //   88: dup
        //   89: invokespecial 42	java/lang/StringBuilder:<init>	()V
        //   92: ldc 78
        //   94: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   97: iload 6
        //   99: invokevirtual 81	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   102: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   105: invokestatic 58	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   108: pop
        //   109: aload_0
        //   110: getfield 24	com/tencent/open/a/c$1:b	Landroid/content/Context;
        //   113: aconst_null
        //   114: aload_0
        //   115: getfield 22	com/tencent/open/a/c$1:a	Ljava/lang/String;
        //   118: invokestatic 87	com/tencent/utils/HttpUtils:getHttpClient	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Lorg/apache/http/client/HttpClient;
        //   121: astore_1
        //   122: new 89	org/apache/http/client/methods/HttpPost
        //   125: dup
        //   126: aload_0
        //   127: getfield 22	com/tencent/open/a/c$1:a	Ljava/lang/String;
        //   130: invokespecial 92	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
        //   133: astore_2
        //   134: aload_2
        //   135: ldc 94
        //   137: ldc 96
        //   139: invokevirtual 100	org/apache/http/client/methods/HttpPost:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
        //   142: aload_2
        //   143: ldc 102
        //   145: ldc 104
        //   147: invokevirtual 107	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
        //   150: aload_2
        //   151: new 109	org/apache/http/entity/ByteArrayEntity
        //   154: dup
        //   155: aload_0
        //   156: getfield 26	com/tencent/open/a/c$1:c	Landroid/os/Bundle;
        //   159: invokestatic 115	com/tencent/utils/Util:encodeUrl	(Landroid/os/Bundle;)Ljava/lang/String;
        //   162: invokevirtual 121	java/lang/String:getBytes	()[B
        //   165: invokespecial 124	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
        //   168: invokevirtual 128	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
        //   171: aload_1
        //   172: aload_2
        //   173: invokeinterface 134 2 0
        //   178: invokeinterface 140 1 0
        //   183: invokeinterface 146 1 0
        //   188: sipush 200
        //   191: if_icmpeq +73 -> 264
        //   194: ldc 39
        //   196: ldc 148
        //   198: invokestatic 151	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   201: pop
        //   202: aload_0
        //   203: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   206: iconst_0
        //   207: invokestatic 154	com/tencent/open/a/c:a	(Lcom/tencent/open/a/c;Z)Z
        //   210: pop
        //   211: ldc 39
        //   213: new 41	java/lang/StringBuilder
        //   216: dup
        //   217: invokespecial 42	java/lang/StringBuilder:<init>	()V
        //   220: ldc 156
        //   222: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: aload_0
        //   226: getfield 22	com/tencent/open/a/c$1:a	Ljava/lang/String;
        //   229: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   232: invokevirtual 52	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   235: invokestatic 58	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   238: pop
        //   239: iload_3
        //   240: iconst_1
        //   241: if_icmpne +104 -> 345
        //   244: ldc 39
        //   246: ldc 158
        //   248: invokestatic 58	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   251: pop
        //   252: return
        //   253: aload_0
        //   254: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   257: invokestatic 76	com/tencent/open/a/c:a	(Lcom/tencent/open/a/c;)I
        //   260: istore_3
        //   261: goto -195 -> 66
        //   264: ldc 39
        //   266: ldc 160
        //   268: invokestatic 58	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
        //   271: pop
        //   272: iconst_1
        //   273: istore_3
        //   274: goto -72 -> 202
        //   277: astore_1
        //   278: aload_1
        //   279: invokevirtual 163	org/apache/http/conn/ConnectTimeoutException:printStackTrace	()V
        //   282: ldc 39
        //   284: ldc 165
        //   286: invokestatic 151	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   289: pop
        //   290: iload_3
        //   291: istore 4
        //   293: iload 4
        //   295: istore_3
        //   296: iload 6
        //   298: istore 5
        //   300: iload 6
        //   302: aload_0
        //   303: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   306: invokestatic 76	com/tencent/open/a/c:a	(Lcom/tencent/open/a/c;)I
        //   309: if_icmplt -232 -> 77
        //   312: iload 4
        //   314: istore_3
        //   315: goto -113 -> 202
        //   318: astore_1
        //   319: aload_1
        //   320: invokevirtual 166	java/net/SocketTimeoutException:printStackTrace	()V
        //   323: iload_3
        //   324: istore 4
        //   326: goto -33 -> 293
        //   329: astore_1
        //   330: aload_1
        //   331: invokevirtual 167	java/lang/Exception:printStackTrace	()V
        //   334: ldc 39
        //   336: ldc 169
        //   338: invokestatic 151	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   341: pop
        //   342: goto -140 -> 202
        //   345: ldc 39
        //   347: ldc 171
        //   349: invokestatic 151	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
        //   352: pop
        //   353: aload_0
        //   354: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   357: invokestatic 174	com/tencent/open/a/c:c	(Lcom/tencent/open/a/c;)Lcom/tencent/open/a/b;
        //   360: aload_0
        //   361: getfield 20	com/tencent/open/a/c$1:d	Lcom/tencent/open/a/c;
        //   364: invokestatic 177	com/tencent/open/a/c:b	(Lcom/tencent/open/a/c;)Ljava/util/ArrayList;
        //   367: invokevirtual 182	com/tencent/open/a/b:a	(Ljava/util/ArrayList;)I
        //   370: pop
        //   371: return
        //   372: astore_1
        //   373: iconst_1
        //   374: istore_3
        //   375: goto -45 -> 330
        //   378: astore_1
        //   379: iconst_1
        //   380: istore_3
        //   381: goto -62 -> 319
        //   384: astore_1
        //   385: iconst_1
        //   386: istore_3
        //   387: goto -109 -> 278
        //
        // Exception table:
        //   from	to	target	type
        //   109	202	277	org/apache/http/conn/ConnectTimeoutException
        //   109	202	318	java/net/SocketTimeoutException
        //   109	202	329	java/lang/Exception
        //   264	272	372	java/lang/Exception
        //   264	272	378	java/net/SocketTimeoutException
        //   264	272	384	org/apache/http/conn/ConnectTimeoutException
      }
    }
    .start();
  }

  private boolean a(Context paramContext, int paramInt)
  {
    paramInt = b(paramContext, paramInt);
    if (this.e.nextInt(100) < paramInt)
    {
      Log.i("cgi_report_debug", "ReportManager availableForFrequency = ture");
      return true;
    }
    Log.i("cgi_report_debug", "ReportManager availableForFrequency = false");
    return false;
  }

  private int b(Context paramContext, int paramInt)
  {
    if (paramInt == 0)
    {
      i = OpenConfig.getInstance(paramContext, null).getInt("Common_CGIReportFrequencySuccess");
      Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencySuccess     config_value:" + i);
      paramInt = i;
      if (i == 0)
        paramInt = 10;
      Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencySuccess     result_value:" + paramInt);
      return paramInt;
    }
    int i = OpenConfig.getInstance(paramContext, null).getInt("Common_CGIReportFrequencyFailed");
    Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencyFailed     config_value:" + i);
    paramInt = i;
    if (i == 0)
      paramInt = 100;
    Log.d("OpenConfig_agent", "config 4:Common_CGIReportFrequencyFailed     result_value:" + paramInt);
    return paramInt;
  }

  private boolean b(Context paramContext)
  {
    long l2 = OpenConfig.getInstance(paramContext, null).getLong("Common_CGIReportTimeinterval");
    Log.d("OpenConfig_test", "config 5:Common_CGIReportTimeinterval     config_value:" + l2);
    long l1 = l2;
    if (l2 == 0L)
      l1 = 1200L;
    Log.d("OpenConfig_test", "config 5:Common_CGIReportTimeinterval     result_value:" + l1);
    l2 = System.currentTimeMillis() / 1000L;
    if ((this.b == 0L) || (l1 + this.b <= l2))
    {
      this.b = l2;
      Log.i("cgi_report_debug", "ReportManager availableForTime = ture");
      return true;
    }
    Log.i("cgi_report_debug", "ReportManager availableForTime = false");
    return false;
  }

  private boolean c(Context paramContext)
  {
    int j = OpenConfig.getInstance(paramContext, null).getInt("Common_CGIReportMaxcount");
    Log.d("OpenConfig_test", "config 6:Common_CGIReportMaxcount     config_value:" + j);
    int i = j;
    if (j == 0)
      i = 20;
    Log.d("OpenConfig_test", "config 6:Common_CGIReportMaxcount     result_value:" + i);
    if (this.f.e() >= i)
    {
      Log.i("cgi_report_debug", "ReportManager availableForCount = ture");
      return true;
    }
    Log.i("cgi_report_debug", "ReportManager availableForCount = false");
    return false;
  }

  public void a(Context paramContext, String paramString1, long paramLong1, long paramLong2, long paramLong3, int paramInt, String paramString2)
  {
    a(paramContext, paramString1, paramLong1, paramLong2, paramLong3, paramInt, paramString2, "", null);
  }

  public void a(Context paramContext, String paramString1, long paramLong1, long paramLong2, long paramLong3, int paramInt, String paramString2, String paramString3, String paramString4)
  {
    String str = paramString4;
    if (paramString4 == null)
      str = "1000067";
    if (this.f == null)
      this.f = new b(paramContext);
    if (a(paramContext, paramInt) == true)
    {
      a(paramContext, paramString1, paramLong1, paramLong2, paramLong3, paramInt, paramString2, paramString3);
      if (this.d != true)
        break label70;
    }
    label70: 
    do
    {
      return;
      if (b(paramContext) == true)
      {
        a(paramContext, str);
        return;
      }
    }
    while (c(paramContext) != true);
    a(paramContext, str);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.a.c
 * JD-Core Version:    0.6.2
 */