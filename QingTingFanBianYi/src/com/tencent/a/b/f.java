package com.tencent.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public final class f
  implements b.a, d.c, e.c, g.c
{
  private static boolean t = false;
  private static f u = null;
  private com.tencent.a.a.a.d A = null;
  private int B = 0;
  private int C = 0;
  private int D = 1;
  private String E = "";
  private String F = "";
  private String G = "";
  private String H = "";
  private String I = "";
  private String J = "";
  private boolean K = false;
  private boolean L = false;
  private long M = 0L;
  private Handler N = null;
  private Runnable O = new Runnable()
  {
    public final void run()
    {
      if (System.currentTimeMillis() - f.a(f.this) < 8000L)
        return;
      if ((f.b(f.this).b()) && (f.b(f.this).c()))
      {
        f.b(f.this).a(0L);
        return;
      }
      f.c(f.this);
    }
  };
  private final BroadcastReceiver P = new BroadcastReceiver()
  {
    public final void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ((!paramAnonymousIntent.getBooleanExtra("noConnectivity", false)) && (f.d(f.this) != null))
        f.d(f.this).sendEmptyMessage(256);
    }
  };
  private long a = 5000L;
  private Context b = null;
  private e c = null;
  private d d = null;
  private g e = null;
  private int f = 1024;
  private int g = 4;
  private c h = null;
  private b i = null;
  private com.tencent.a.a.a.b j = null;
  private int k;
  private int l;
  private int m;
  private byte[] n = new byte[0];
  private byte[] o = new byte[0];
  private boolean p = false;
  private c q = null;
  private b r = null;
  private a s = null;
  private long v = -1L;
  private e.b w = null;
  private d.b x = null;
  private g.b y = null;
  private com.tencent.a.a.a.d z = null;

  public static f a()
  {
    try
    {
      if (u == null)
        u = new f();
      f localf = u;
      return localf;
    }
    finally
    {
    }
  }

  private static ArrayList<com.tencent.a.a.a.c> a(JSONArray paramJSONArray)
    throws Exception
  {
    int i2 = paramJSONArray.length();
    ArrayList localArrayList = new ArrayList();
    int i1 = 0;
    while (i1 < i2)
    {
      JSONObject localJSONObject = paramJSONArray.getJSONObject(i1);
      localArrayList.add(new com.tencent.a.a.a.c(localJSONObject.getString("name"), localJSONObject.getString("addr"), localJSONObject.getString("catalog"), localJSONObject.getDouble("dist"), Double.parseDouble(localJSONObject.getString("latitude")), Double.parseDouble(localJSONObject.getString("longitude"))));
      i1 += 1;
    }
    return localArrayList;
  }

  private void a(String paramString)
  {
    int i3 = 0;
    try
    {
      this.z = new com.tencent.a.a.a.d();
      JSONObject localJSONObject = new JSONObject(paramString);
      Object localObject = localJSONObject.getJSONObject("location");
      this.z.a = 1;
      this.z.b = i.a(((JSONObject)localObject).getDouble("latitude"), 6);
      this.z.c = i.a(((JSONObject)localObject).getDouble("longitude"), 6);
      this.z.d = i.a(((JSONObject)localObject).getDouble("altitude"), 1);
      this.z.e = i.a(((JSONObject)localObject).getDouble("accuracy"), 1);
      localObject = this.z;
      boolean bool;
      int i2;
      int i1;
      double d1;
      if (this.m == 1)
      {
        bool = true;
        ((com.tencent.a.a.a.d)localObject).x = bool;
        localObject = localJSONObject.getString("bearing");
        i2 = -100;
        i1 = i3;
        if (localObject != null)
        {
          i1 = i3;
          if (((String)localObject).split(",").length > 1)
            i1 = Integer.parseInt(localObject.split(",")[1]);
        }
        if (this.x != null)
          i2 = this.x.f;
        localObject = this.z;
        d1 = this.z.e;
        if (i1 < 6)
          break label678;
        d1 = 40.0D;
        label238: ((com.tencent.a.a.a.d)localObject).e = d1;
        this.z.z = 0;
        if (((this.l == 3) || (this.l == 4)) && (this.m == 1))
        {
          localObject = localJSONObject.getJSONObject("details").getJSONObject("subnation");
          this.z.a(((JSONObject)localObject).getString("name"));
          this.z.m = ((JSONObject)localObject).getString("town");
          this.z.n = ((JSONObject)localObject).getString("village");
          this.z.o = ((JSONObject)localObject).getString("street");
          this.z.p = ((JSONObject)localObject).getString("street_no");
          this.z.z = 3;
          this.z.h = 0;
        }
        if ((this.l == 4) && (this.m == 1))
        {
          localObject = localJSONObject.getJSONObject("details").getJSONArray("poilist");
          this.z.w = a((JSONArray)localObject);
          this.z.z = 4;
        }
        if ((this.l == 7) && (this.m == 1))
        {
          localJSONObject = localJSONObject.getJSONObject("details");
          i1 = localJSONObject.getInt("stat");
          localJSONObject = localJSONObject.getJSONObject("subnation");
          if (i1 != 0)
            break label842;
          this.z.a(localJSONObject.getString("name"));
          this.z.m = localJSONObject.getString("town");
          this.z.n = localJSONObject.getString("village");
          this.z.o = localJSONObject.getString("street");
          this.z.p = localJSONObject.getString("street_no");
        }
      }
      while (true)
      {
        this.z.h = i1;
        this.z.z = 7;
        this.z.y = 0;
        this.A = new com.tencent.a.a.a.d(this.z);
        this.B = 0;
        if (this.h != null)
          this.h.a(paramString);
        if ((this.j != null) && (this.k == 1) && ((this.w == null) || (!this.w.a())))
        {
          this.j.onLocationUpdate(this.z);
          this.v = System.currentTimeMillis();
        }
        return;
        bool = false;
        break;
        label678: if (i1 == 5)
        {
          d1 = 60.0D;
          break label238;
        }
        if (i1 == 4)
        {
          d1 = 70.0D;
          break label238;
        }
        if (i1 == 3)
        {
          d1 = 90.0D;
          break label238;
        }
        if (i1 == 2)
        {
          d1 = 110.0D;
          break label238;
        }
        if ((i2 >= -72) && (i1 == 0))
          i1 = (int)(0.45D * d1 / 10.0D) * 10;
        while (true)
        {
          d1 = i1;
          break;
          if (d1 <= 100.0D)
            i1 = (int)((d1 - 1.0D) / 10.0D + 1.0D) * 10;
          else if ((d1 > 100.0D) && (d1 <= 800.0D))
            i1 = (int)(0.85D * d1 / 10.0D) * 10;
          else
            i1 = (int)(0.8D * d1 / 10.0D) * 10;
        }
        label842: if (i1 == 1)
        {
          this.z.i = localJSONObject.getString("nation");
          this.z.q = localJSONObject.getString("admin_level_1");
          this.z.r = localJSONObject.getString("admin_level_2");
          this.z.s = localJSONObject.getString("admin_level_3");
          this.z.t = localJSONObject.getString("locality");
          this.z.u = localJSONObject.getString("sublocality");
          this.z.v = localJSONObject.getString("route");
        }
      }
    }
    catch (Exception paramString)
    {
      while (true)
      {
        this.z = new com.tencent.a.a.a.d();
        this.z.z = -1;
        this.z.y = 2;
        this.B = 2;
      }
    }
  }

  private void b(boolean paramBoolean)
  {
    if ((this.w != null) && (this.w.a()))
    {
      Location localLocation = this.w.b();
      this.z = new com.tencent.a.a.a.d();
      this.z.b = i.a(localLocation.getLatitude(), 6);
      this.z.c = i.a(localLocation.getLongitude(), 6);
      this.z.d = i.a(localLocation.getAltitude(), 1);
      this.z.e = i.a(localLocation.getAccuracy(), 1);
      this.z.f = i.a(localLocation.getSpeed(), 1);
      this.z.g = i.a(localLocation.getBearing(), 1);
      this.z.a = 0;
      this.z.x = false;
      if (paramBoolean)
        break label237;
    }
    label237: for (this.z.y = 0; ; this.z.y = 1)
    {
      this.z.z = 0;
      this.A = new com.tencent.a.a.a.d(this.z);
      this.B = 0;
      if ((System.currentTimeMillis() - this.v >= this.a) && (this.j != null) && (this.k == 1))
      {
        this.j.onLocationUpdate(this.z);
        this.v = System.currentTimeMillis();
      }
      return;
    }
  }

  private void d()
  {
    if (this.s != null)
      return;
    this.s = new a(this.w, this.x, this.y);
    this.s.start();
  }

  private void e()
  {
    this.z = new com.tencent.a.a.a.d();
    this.B = 1;
    this.z.y = 1;
    this.z.z = -1;
    this.z.a = 1;
    if ((this.j != null) && (this.k == 1))
      this.j.onLocationUpdate(this.z);
  }

  public final void a(double paramDouble1, double paramDouble2)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(6);
      Location localLocation = new Location("Deflect");
      localLocation.setLatitude(paramDouble1);
      localLocation.setLongitude(paramDouble2);
      localMessage.obj = localLocation;
      this.q.sendMessage(localMessage);
      return;
    }
  }

  public final void a(int paramInt)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(4, paramInt, 0);
      this.q.sendMessage(localMessage);
      return;
    }
  }

  public final void a(d.b paramb)
  {
    synchronized (this.o)
    {
      paramb = this.q.obtainMessage(2, paramb);
      this.q.sendMessage(paramb);
      return;
    }
  }

  public final void a(e.b paramb)
  {
    synchronized (this.o)
    {
      paramb = this.q.obtainMessage(1, paramb);
      this.q.sendMessage(paramb);
      return;
    }
  }

  public final void a(g.b paramb)
  {
    synchronized (this.o)
    {
      paramb = this.q.obtainMessage(3, paramb);
      this.q.sendMessage(paramb);
      return;
    }
  }

  // ERROR //
  public final boolean a(Context paramContext, com.tencent.a.a.a.b paramb)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 119	com/tencent/a/b/f:n	[B
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_1
    //   8: ifnull +7 -> 15
    //   11: aload_2
    //   12: ifnonnull +7 -> 19
    //   15: aload_3
    //   16: monitorexit
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_0
    //   20: getfield 163	com/tencent/a/b/f:J	Ljava/lang/String;
    //   23: invokestatic 598	com/tencent/a/b/i:a	(Ljava/lang/String;)Z
    //   26: ifne +7 -> 33
    //   29: aload_3
    //   30: monitorexit
    //   31: iconst_0
    //   32: ireturn
    //   33: aload_0
    //   34: new 24	com/tencent/a/b/f$c
    //   37: dup
    //   38: aload_0
    //   39: invokespecial 599	com/tencent/a/b/f$c:<init>	(Lcom/tencent/a/b/f;)V
    //   42: putfield 125	com/tencent/a/b/f:q	Lcom/tencent/a/b/f$c;
    //   45: aload_0
    //   46: new 601	android/os/Handler
    //   49: dup
    //   50: invokestatic 607	android/os/Looper:getMainLooper	()Landroid/os/Looper;
    //   53: invokespecial 610	android/os/Handler:<init>	(Landroid/os/Looper;)V
    //   56: putfield 171	com/tencent/a/b/f:N	Landroid/os/Handler;
    //   59: aload_0
    //   60: aload_1
    //   61: putfield 101	com/tencent/a/b/f:b	Landroid/content/Context;
    //   64: aload_0
    //   65: aload_2
    //   66: putfield 117	com/tencent/a/b/f:j	Lcom/tencent/a/a/a/b;
    //   69: invokestatic 615	com/tencent/a/b/l:a	()Lcom/tencent/a/b/l;
    //   72: aload_0
    //   73: getfield 101	com/tencent/a/b/f:b	Landroid/content/Context;
    //   76: invokevirtual 621	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   79: invokevirtual 624	com/tencent/a/b/l:a	(Landroid/content/Context;)V
    //   82: aload_1
    //   83: ldc_w 626
    //   86: invokevirtual 630	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   89: checkcast 632	android/net/ConnectivityManager
    //   92: astore_1
    //   93: aload_1
    //   94: ifnull +21 -> 115
    //   97: aload_1
    //   98: invokevirtual 636	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   101: ifnull +14 -> 115
    //   104: aload_0
    //   105: aload_1
    //   106: invokevirtual 636	android/net/ConnectivityManager:getActiveNetworkInfo	()Landroid/net/NetworkInfo;
    //   109: invokevirtual 641	android/net/NetworkInfo:isRoaming	()Z
    //   112: putfield 165	com/tencent/a/b/f:K	Z
    //   115: aload_0
    //   116: getfield 101	com/tencent/a/b/f:b	Landroid/content/Context;
    //   119: aload_0
    //   120: getfield 179	com/tencent/a/b/f:P	Landroid/content/BroadcastReceiver;
    //   123: new 643	android/content/IntentFilter
    //   126: dup
    //   127: ldc_w 645
    //   130: invokespecial 646	android/content/IntentFilter:<init>	(Ljava/lang/String;)V
    //   133: invokevirtual 650	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   136: pop
    //   137: aload_0
    //   138: aload_0
    //   139: getfield 117	com/tencent/a/b/f:j	Lcom/tencent/a/a/a/b;
    //   142: invokevirtual 653	com/tencent/a/a/a/b:getReqType	()I
    //   145: putfield 368	com/tencent/a/b/f:k	I
    //   148: aload_0
    //   149: aload_0
    //   150: getfield 117	com/tencent/a/b/f:j	Lcom/tencent/a/a/a/b;
    //   153: invokevirtual 656	com/tencent/a/a/a/b:getReqLevel	()I
    //   156: putfield 321	com/tencent/a/b/f:l	I
    //   159: aload_0
    //   160: aload_0
    //   161: getfield 117	com/tencent/a/b/f:j	Lcom/tencent/a/a/a/b;
    //   164: invokevirtual 659	com/tencent/a/a/a/b:getReqGeoType	()I
    //   167: putfield 411	com/tencent/a/b/f:m	I
    //   170: aload_0
    //   171: ldc2_w 130
    //   174: putfield 133	com/tencent/a/b/f:v	J
    //   177: aload_0
    //   178: getfield 321	com/tencent/a/b/f:l	I
    //   181: bipush 7
    //   183: if_icmpne +8 -> 191
    //   186: aload_0
    //   187: iconst_0
    //   188: putfield 321	com/tencent/a/b/f:l	I
    //   191: aload_0
    //   192: iconst_0
    //   193: putfield 167	com/tencent/a/b/f:L	Z
    //   196: aload_0
    //   197: iconst_1
    //   198: putfield 149	com/tencent/a/b/f:D	I
    //   201: aload_0
    //   202: getfield 103	com/tencent/a/b/f:c	Lcom/tencent/a/b/e;
    //   205: aload_0
    //   206: aload_0
    //   207: getfield 101	com/tencent/a/b/f:b	Landroid/content/Context;
    //   210: invokevirtual 662	com/tencent/a/b/e:a	(Lcom/tencent/a/b/e$c;Landroid/content/Context;)Z
    //   213: istore 4
    //   215: aload_0
    //   216: getfield 105	com/tencent/a/b/f:d	Lcom/tencent/a/b/d;
    //   219: aload_0
    //   220: getfield 101	com/tencent/a/b/f:b	Landroid/content/Context;
    //   223: aload_0
    //   224: invokevirtual 665	com/tencent/a/b/d:a	(Landroid/content/Context;Lcom/tencent/a/b/d$c;)Z
    //   227: istore 5
    //   229: aload_0
    //   230: getfield 107	com/tencent/a/b/f:e	Lcom/tencent/a/b/g;
    //   233: aload_0
    //   234: getfield 101	com/tencent/a/b/f:b	Landroid/content/Context;
    //   237: aload_0
    //   238: iconst_1
    //   239: invokevirtual 668	com/tencent/a/b/g:a	(Landroid/content/Context;Lcom/tencent/a/b/g$c;I)Z
    //   242: istore 6
    //   244: aload_0
    //   245: invokestatic 671	com/tencent/a/b/c:a	()Lcom/tencent/a/b/c;
    //   248: putfield 113	com/tencent/a/b/f:h	Lcom/tencent/a/b/c;
    //   251: aload_0
    //   252: invokestatic 674	com/tencent/a/b/b:a	()Lcom/tencent/a/b/b;
    //   255: putfield 115	com/tencent/a/b/f:i	Lcom/tencent/a/b/b;
    //   258: aload_0
    //   259: aconst_null
    //   260: putfield 135	com/tencent/a/b/f:w	Lcom/tencent/a/b/e$b;
    //   263: aload_0
    //   264: aconst_null
    //   265: putfield 137	com/tencent/a/b/f:x	Lcom/tencent/a/b/d$b;
    //   268: aload_0
    //   269: aconst_null
    //   270: putfield 139	com/tencent/a/b/f:y	Lcom/tencent/a/b/g$b;
    //   273: aload_0
    //   274: aconst_null
    //   275: putfield 141	com/tencent/a/b/f:z	Lcom/tencent/a/a/a/d;
    //   278: aload_0
    //   279: aconst_null
    //   280: putfield 143	com/tencent/a/b/f:A	Lcom/tencent/a/a/a/d;
    //   283: aload_0
    //   284: iconst_0
    //   285: putfield 145	com/tencent/a/b/f:B	I
    //   288: aload_0
    //   289: getfield 113	com/tencent/a/b/f:h	Lcom/tencent/a/b/c;
    //   292: ifnull +10 -> 302
    //   295: aload_0
    //   296: getfield 113	com/tencent/a/b/f:h	Lcom/tencent/a/b/c;
    //   299: invokevirtual 676	com/tencent/a/b/c:b	()V
    //   302: aload_0
    //   303: iconst_1
    //   304: putfield 147	com/tencent/a/b/f:C	I
    //   307: iload 4
    //   309: ifeq +14 -> 323
    //   312: aload_0
    //   313: getfield 411	com/tencent/a/b/f:m	I
    //   316: ifne +7 -> 323
    //   319: aload_3
    //   320: monitorexit
    //   321: iconst_1
    //   322: ireturn
    //   323: iload 5
    //   325: ifne +8 -> 333
    //   328: iload 6
    //   330: ifeq +7 -> 337
    //   333: aload_3
    //   334: monitorexit
    //   335: iconst_1
    //   336: ireturn
    //   337: aload_3
    //   338: monitorexit
    //   339: iconst_0
    //   340: ireturn
    //   341: astore_1
    //   342: aload_3
    //   343: monitorexit
    //   344: aload_1
    //   345: athrow
    //   346: astore_1
    //   347: goto -210 -> 137
    //
    // Exception table:
    //   from	to	target	type
    //   19	31	341	finally
    //   33	82	341	finally
    //   82	93	341	finally
    //   97	115	341	finally
    //   115	137	341	finally
    //   137	191	341	finally
    //   191	302	341	finally
    //   302	307	341	finally
    //   312	321	341	finally
    //   82	93	346	java/lang/Exception
    //   97	115	346	java/lang/Exception
    //   115	137	346	java/lang/Exception
  }

  public final boolean a(String paramString1, String paramString2)
  {
    synchronized (this.n)
    {
      if (a.a().a(paramString1, paramString2))
      {
        this.J = paramString1;
        return true;
      }
      return false;
    }
  }

  public final void b()
  {
    try
    {
      synchronized (this.n)
      {
        if (this.j != null)
        {
          this.j = null;
          this.N.removeCallbacks(this.O);
          this.b.unregisterReceiver(this.P);
          this.c.a();
          this.d.a();
          this.e.a();
        }
        label62: return;
      }
    }
    catch (Exception localException)
    {
      break label62;
    }
  }

  public final void b(int paramInt)
  {
    synchronized (this.o)
    {
      Message localMessage = this.q.obtainMessage(5, paramInt, 0);
      this.q.sendMessage(localMessage);
      return;
    }
  }

  final class a extends Thread
  {
    private e.b a = null;
    private d.b b = null;
    private g.b c = null;

    a(e.b paramb, d.b paramb1, g.b arg4)
    {
      if (paramb != null)
        this.a = ((e.b)paramb.clone());
      if (paramb1 != null)
        this.b = ((d.b)paramb1.clone());
      Object localObject;
      if (localObject != null)
        this.c = ((g.b)localObject.clone());
    }

    public final void run()
    {
      if (!f.c());
      try
      {
        Object localObject1 = (TelephonyManager)f.k(f.this).getSystemService("phone");
        f.c(f.this, ((TelephonyManager)localObject1).getDeviceId());
        f.d(f.this, ((TelephonyManager)localObject1).getSubscriberId());
        f.e(f.this, ((TelephonyManager)localObject1).getLine1Number());
        Object localObject2 = Pattern.compile("[0-9a-zA-Z+-]*");
        Object localObject3 = f.this;
        label123: label253: label382: String str1;
        if (f.l(f.this) == null)
        {
          localObject1 = "";
          f.c((f)localObject3, (String)localObject1);
          if (!((Pattern)localObject2).matcher(f.l(f.this)).matches())
            break label653;
          localObject3 = f.this;
          if (f.l(f.this) != null)
            break label642;
          localObject1 = "";
          f.c((f)localObject3, (String)localObject1);
          label129: localObject3 = f.this;
          if (f.m(f.this) != null)
            break label666;
          localObject1 = "";
          label147: f.d((f)localObject3, (String)localObject1);
          if (!((Pattern)localObject2).matcher(f.m(f.this)).matches())
            break label688;
          localObject3 = f.this;
          if (f.m(f.this) != null)
            break label677;
          localObject1 = "";
          label188: f.d((f)localObject3, (String)localObject1);
          label194: localObject3 = f.this;
          if (f.n(f.this) != null)
            break label701;
          localObject1 = "";
          label212: f.e((f)localObject3, (String)localObject1);
          if (!((Pattern)localObject2).matcher(f.n(f.this)).matches())
            break label723;
          localObject2 = f.this;
          if (f.n(f.this) != null)
            break label712;
          localObject1 = "";
          f.e((f)localObject2, (String)localObject1);
          label259: f.a(true);
          localObject2 = f.this;
          if (f.l(f.this) != null)
            break label736;
          localObject1 = "";
          label282: f.c((f)localObject2, (String)localObject1);
          localObject2 = f.this;
          if (f.m(f.this) != null)
            break label747;
          localObject1 = "";
          label306: f.d((f)localObject2, (String)localObject1);
          localObject2 = f.this;
          if (f.n(f.this) != null)
            break label758;
          localObject1 = "";
          label330: f.e((f)localObject2, (String)localObject1);
          localObject2 = f.this;
          if (f.l(f.this) != null)
            break label769;
          localObject1 = "0123456789ABCDEF";
          label354: f.f((f)localObject2, j.a((String)localObject1));
          if (f.o(f.this) != 4)
            break label780;
          localObject1 = i.a(this.c);
          localObject3 = i.a(this.b, f.p(f.this).b());
          str1 = i.a(f.l(f.this), f.m(f.this), f.n(f.this), f.q(f.this), f.r(f.this));
          if ((this.a == null) || (!this.a.a()))
            break label786;
        }
        label642: label653: label786: for (localObject2 = i.a(this.a); ; localObject2 = "{}")
        {
          String str2 = "{\"version\":\"1.1.8\",\"address\":" + f.s(f.this);
          str2 = str2 + ",\"source\":203,\"access_token\":\"" + f.t(f.this) + "\",\"app_name\":" + "\"" + f.u(f.this) + "\",\"bearing\":1";
          localObject1 = str2 + ",\"attribute\":" + str1 + ",\"location\":" + (String)localObject2 + ",\"cells\":" + (String)localObject3 + ",\"wifis\":" + (String)localObject1 + "}";
          localObject1 = f.d(f.this).obtainMessage(16, localObject1);
          f.d(f.this).sendMessage((Message)localObject1);
          return;
          localObject1 = f.l(f.this);
          break;
          localObject1 = f.l(f.this);
          break label123;
          f.c(f.this, "");
          break label129;
          label666: localObject1 = f.m(f.this);
          break label147;
          label677: localObject1 = f.m(f.this);
          break label188;
          label688: f.d(f.this, "");
          break label194;
          label701: localObject1 = f.n(f.this);
          break label212;
          label712: localObject1 = f.n(f.this);
          break label253;
          label723: f.e(f.this, "");
          break label259;
          label736: localObject1 = f.l(f.this);
          break label282;
          label747: localObject1 = f.m(f.this);
          break label306;
          label758: localObject1 = f.n(f.this);
          break label330;
          localObject1 = f.l(f.this);
          break label354;
          localObject1 = "[]";
          break label382;
        }
      }
      catch (Exception localException)
      {
        label769: label780: break label259;
      }
    }
  }

  final class b extends Thread
  {
    private String a = null;
    private String b = null;
    private String c = null;

    public b(String arg2)
    {
      this.a = str;
      StringBuilder localStringBuilder = new StringBuilder();
      if (f.h(f.this) == 0);
      for (String str = "http://lstest.map.soso.com/loc?c=1"; ; str = "http://lbs.map.qq.com/loc?c=1")
      {
        this.b = (str + "&mars=" + f.i(f.this));
        return;
      }
    }

    private String a(byte[] paramArrayOfByte, String paramString)
    {
      f.a(f.this, System.currentTimeMillis());
      StringBuffer localStringBuffer = new StringBuffer();
      try
      {
        localStringBuffer.append(new String(paramArrayOfByte, paramString));
        return localStringBuffer.toString();
      }
      catch (Exception paramArrayOfByte)
      {
      }
      return null;
    }

    public final void run()
    {
      Message localMessage = new Message();
      localMessage.what = 8;
      int i;
      try
      {
        Object localObject1 = j.a(this.a.getBytes());
        f.a(f.this, true);
        localObject1 = b.a(this.b, "SOSO MAP LBS SDK", (byte[])localObject1);
        f.a(f.this, false);
        this.c = a(j.b(((n)localObject1).a), ((n)localObject1).b);
        if (this.c != null)
        {
          localMessage.arg1 = 0;
          localMessage.obj = this.c;
        }
        while (true)
        {
          f.j(f.this);
          f.d(f.this).sendMessage(localMessage);
          return;
          localMessage.arg1 = 1;
        }
      }
      catch (Exception localException1)
      {
        i = 0;
      }
      while (true)
        while (true)
        {
          i += 1;
          if (i <= 3);
          try
          {
            sleep(1000L);
            Object localObject2 = j.a(this.a.getBytes());
            f.a(f.this, true);
            localObject2 = b.a(this.b, "SOSO MAP LBS SDK", (byte[])localObject2);
            f.a(f.this, false);
            this.c = a(j.b(((n)localObject2).a), ((n)localObject2).b);
            if (this.c != null)
            {
              localMessage.arg1 = 0;
              localMessage.obj = this.c;
            }
            else
            {
              localMessage.arg1 = 1;
              continue;
              f.a(f.this, false);
              localMessage.arg1 = 1;
            }
          }
          catch (Exception localException2)
          {
          }
        }
    }
  }

  final class c extends Handler
  {
    public c()
    {
      super();
    }

    public final void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 256:
      case 16:
      case 8:
      }
      do
      {
        do
        {
          do
          {
            return;
            f.a(f.this, (e.b)paramMessage.obj);
            return;
            f.a(f.this, (d.b)paramMessage.obj);
            return;
            f.a(f.this, (g.b)paramMessage.obj);
            return;
            f.a(f.this, paramMessage.arg1);
            return;
            f.b(f.this, paramMessage.arg1);
            return;
            f.a(f.this, (Location)paramMessage.obj);
            return;
          }
          while (f.e(f.this) != 1);
          f.c(f.this);
          return;
        }
        while (paramMessage.obj == null);
        f.a(f.this, (String)paramMessage.obj);
        f.a(f.this, null);
        return;
        if (paramMessage.arg1 == 0)
        {
          f.b(f.this, (String)paramMessage.obj);
          return;
        }
      }
      while ((f.f(f.this) != null) && (f.f(f.this).a()));
      f.g(f.this);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.f
 * JD-Core Version:    0.6.2
 */