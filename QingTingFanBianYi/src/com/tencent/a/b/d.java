package com.tencent.a.b;

import android.content.Context;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public final class d
{
  private Context a = null;
  private TelephonyManager b = null;
  private a c = null;
  private c d = null;
  private b e = null;
  private boolean f = false;
  private List<NeighboringCellInfo> g = new LinkedList();
  private byte[] h = new byte[0];
  private byte[] i = new byte[0];
  private boolean j = false;

  private int a(int paramInt)
  {
    String str = this.b.getNetworkOperator();
    if ((str != null) && (str.length() >= 3));
    int k;
    while (true)
    {
      try
      {
        k = Integer.valueOf(str.substring(0, 3)).intValue();
        if ((paramInt != 2) || (k != -1))
          break;
        return 0;
      }
      catch (Exception localException)
      {
      }
      k = -1;
    }
    return k;
  }

  public final void a()
  {
    synchronized (this.h)
    {
      if (!this.f)
        return;
      if (this.b != null)
      {
        a locala = this.c;
        if (locala == null);
      }
    }
    try
    {
      this.b.listen(this.c, 0);
      this.f = false;
      return;
      localObject = finally;
      throw localObject;
    }
    catch (Exception localException)
    {
      while (true)
        this.f = false;
    }
  }

  public final boolean a(Context paramContext, c paramc)
  {
    synchronized (this.h)
    {
      if (this.f)
        return true;
      if ((paramContext == null) || (paramc == null))
        return false;
      this.a = paramContext;
      this.d = paramc;
      try
      {
        this.b = ((TelephonyManager)this.a.getSystemService("phone"));
        paramContext = this.b;
        if (paramContext == null)
          return false;
        int k = this.b.getPhoneType();
        this.c = new a(a(k), k);
        paramContext = this.c;
        if (paramContext == null)
          return false;
        this.b.listen(this.c, 18);
        this.f = true;
        return this.f;
      }
      catch (Exception paramContext)
      {
        return false;
      }
    }
  }

  public final List<NeighboringCellInfo> b()
  {
    LinkedList localLinkedList = null;
    synchronized (this.i)
    {
      if (this.g != null)
      {
        localLinkedList = new LinkedList();
        localLinkedList.addAll(this.g);
      }
      return localLinkedList;
    }
  }

  public final class a extends PhoneStateListener
  {
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = -1;
    private int g = 2147483647;
    private int h = 2147483647;
    private Method i = null;
    private Method j = null;
    private Method k = null;
    private Method l = null;
    private Method m = null;

    public a(int paramInt1, int arg3)
    {
      this.b = paramInt1;
      int i1;
      this.a = i1;
    }

    // ERROR //
    public final void onCellLocationChanged(android.telephony.CellLocation paramCellLocation)
    {
      // Byte code:
      //   0: aload_0
      //   1: iconst_m1
      //   2: putfield 42	com/tencent/a/b/d$a:f	I
      //   5: aload_0
      //   6: iconst_m1
      //   7: putfield 40	com/tencent/a/b/d$a:e	I
      //   10: aload_0
      //   11: iconst_m1
      //   12: putfield 38	com/tencent/a/b/d$a:d	I
      //   15: aload_0
      //   16: iconst_m1
      //   17: putfield 36	com/tencent/a/b/d$a:c	I
      //   20: aload_1
      //   21: ifnull +31 -> 52
      //   24: aload_0
      //   25: getfield 32	com/tencent/a/b/d$a:a	I
      //   28: tableswitch	default:+24 -> 52, 1:+105->133, 2:+243->271
      //   53: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   56: new 64	com/tencent/a/b/d$b
      //   59: dup
      //   60: aload_0
      //   61: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   64: aload_0
      //   65: getfield 32	com/tencent/a/b/d$a:a	I
      //   68: aload_0
      //   69: getfield 34	com/tencent/a/b/d$a:b	I
      //   72: aload_0
      //   73: getfield 36	com/tencent/a/b/d$a:c	I
      //   76: aload_0
      //   77: getfield 38	com/tencent/a/b/d$a:d	I
      //   80: aload_0
      //   81: getfield 40	com/tencent/a/b/d$a:e	I
      //   84: aload_0
      //   85: getfield 42	com/tencent/a/b/d$a:f	I
      //   88: aload_0
      //   89: getfield 45	com/tencent/a/b/d$a:g	I
      //   92: aload_0
      //   93: getfield 47	com/tencent/a/b/d$a:h	I
      //   96: invokespecial 67	com/tencent/a/b/d$b:<init>	(Lcom/tencent/a/b/d;IIIIIIII)V
      //   99: invokestatic 70	com/tencent/a/b/d:a	(Lcom/tencent/a/b/d;Lcom/tencent/a/b/d$b;)Lcom/tencent/a/b/d$b;
      //   102: pop
      //   103: aload_0
      //   104: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   107: invokestatic 73	com/tencent/a/b/d:a	(Lcom/tencent/a/b/d;)Lcom/tencent/a/b/d$c;
      //   110: ifnull +22 -> 132
      //   113: aload_0
      //   114: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   117: invokestatic 73	com/tencent/a/b/d:a	(Lcom/tencent/a/b/d;)Lcom/tencent/a/b/d$c;
      //   120: aload_0
      //   121: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   124: invokestatic 76	com/tencent/a/b/d:b	(Lcom/tencent/a/b/d;)Lcom/tencent/a/b/d$b;
      //   127: invokeinterface 81 2 0
      //   132: return
      //   133: aload_1
      //   134: checkcast 83	android/telephony/gsm/GsmCellLocation
      //   137: astore_2
      //   138: aload_2
      //   139: astore_1
      //   140: aload_2
      //   141: invokevirtual 87	android/telephony/gsm/GsmCellLocation:getLac	()I
      //   144: ifgt +26 -> 170
      //   147: aload_2
      //   148: astore_1
      //   149: aload_2
      //   150: invokevirtual 90	android/telephony/gsm/GsmCellLocation:getCid	()I
      //   153: ifgt +17 -> 170
      //   156: aload_0
      //   157: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   160: invokestatic 93	com/tencent/a/b/d:d	(Lcom/tencent/a/b/d;)Landroid/telephony/TelephonyManager;
      //   163: invokevirtual 99	android/telephony/TelephonyManager:getCellLocation	()Landroid/telephony/CellLocation;
      //   166: checkcast 83	android/telephony/gsm/GsmCellLocation
      //   169: astore_1
      //   170: iconst_1
      //   171: istore_3
      //   172: iload_3
      //   173: ifeq -121 -> 52
      //   176: aload_1
      //   177: ifnull -125 -> 52
      //   180: aload_0
      //   181: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   184: invokestatic 93	com/tencent/a/b/d:d	(Lcom/tencent/a/b/d;)Landroid/telephony/TelephonyManager;
      //   187: invokevirtual 103	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
      //   190: astore_2
      //   191: aload_2
      //   192: ifnull +26 -> 218
      //   195: aload_2
      //   196: invokevirtual 108	java/lang/String:length	()I
      //   199: iconst_3
      //   200: if_icmple +18 -> 218
      //   203: aload_0
      //   204: aload_2
      //   205: iconst_3
      //   206: invokevirtual 112	java/lang/String:substring	(I)Ljava/lang/String;
      //   209: invokestatic 118	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
      //   212: invokevirtual 121	java/lang/Integer:intValue	()I
      //   215: putfield 36	com/tencent/a/b/d$a:c	I
      //   218: aload_0
      //   219: aload_1
      //   220: invokevirtual 87	android/telephony/gsm/GsmCellLocation:getLac	()I
      //   223: putfield 38	com/tencent/a/b/d$a:d	I
      //   226: aload_0
      //   227: aload_1
      //   228: invokevirtual 90	android/telephony/gsm/GsmCellLocation:getCid	()I
      //   231: putfield 40	com/tencent/a/b/d$a:e	I
      //   234: aload_0
      //   235: getfield 27	com/tencent/a/b/d$a:n	Lcom/tencent/a/b/d;
      //   238: invokestatic 124	com/tencent/a/b/d:c	(Lcom/tencent/a/b/d;)V
      //   241: goto -189 -> 52
      //   244: astore_1
      //   245: aconst_null
      //   246: astore_1
      //   247: iconst_0
      //   248: istore_3
      //   249: goto -77 -> 172
      //   252: astore_1
      //   253: aload_0
      //   254: iconst_m1
      //   255: putfield 40	com/tencent/a/b/d$a:e	I
      //   258: aload_0
      //   259: iconst_m1
      //   260: putfield 38	com/tencent/a/b/d$a:d	I
      //   263: aload_0
      //   264: iconst_m1
      //   265: putfield 36	com/tencent/a/b/d$a:c	I
      //   268: goto -34 -> 234
      //   271: aload_1
      //   272: ifnull -220 -> 52
      //   275: aload_0
      //   276: getfield 49	com/tencent/a/b/d$a:i	Ljava/lang/reflect/Method;
      //   279: ifnonnull +93 -> 372
      //   282: aload_0
      //   283: ldc 126
      //   285: invokestatic 132	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   288: ldc 134
      //   290: iconst_0
      //   291: anewarray 128	java/lang/Class
      //   294: invokevirtual 138	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   297: putfield 49	com/tencent/a/b/d$a:i	Ljava/lang/reflect/Method;
      //   300: aload_0
      //   301: ldc 126
      //   303: invokestatic 132	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   306: ldc 140
      //   308: iconst_0
      //   309: anewarray 128	java/lang/Class
      //   312: invokevirtual 138	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   315: putfield 51	com/tencent/a/b/d$a:j	Ljava/lang/reflect/Method;
      //   318: aload_0
      //   319: ldc 126
      //   321: invokestatic 132	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   324: ldc 142
      //   326: iconst_0
      //   327: anewarray 128	java/lang/Class
      //   330: invokevirtual 138	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   333: putfield 53	com/tencent/a/b/d$a:k	Ljava/lang/reflect/Method;
      //   336: aload_0
      //   337: ldc 126
      //   339: invokestatic 132	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   342: ldc 144
      //   344: iconst_0
      //   345: anewarray 128	java/lang/Class
      //   348: invokevirtual 138	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   351: putfield 55	com/tencent/a/b/d$a:l	Ljava/lang/reflect/Method;
      //   354: aload_0
      //   355: ldc 126
      //   357: invokestatic 132	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   360: ldc 146
      //   362: iconst_0
      //   363: anewarray 128	java/lang/Class
      //   366: invokevirtual 138	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   369: putfield 57	com/tencent/a/b/d$a:m	Ljava/lang/reflect/Method;
      //   372: aload_0
      //   373: aload_0
      //   374: getfield 51	com/tencent/a/b/d$a:j	Ljava/lang/reflect/Method;
      //   377: aload_1
      //   378: iconst_0
      //   379: anewarray 148	java/lang/Object
      //   382: invokevirtual 154	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   385: checkcast 114	java/lang/Integer
      //   388: invokevirtual 121	java/lang/Integer:intValue	()I
      //   391: putfield 36	com/tencent/a/b/d$a:c	I
      //   394: aload_0
      //   395: aload_0
      //   396: getfield 53	com/tencent/a/b/d$a:k	Ljava/lang/reflect/Method;
      //   399: aload_1
      //   400: iconst_0
      //   401: anewarray 148	java/lang/Object
      //   404: invokevirtual 154	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   407: checkcast 114	java/lang/Integer
      //   410: invokevirtual 121	java/lang/Integer:intValue	()I
      //   413: putfield 38	com/tencent/a/b/d$a:d	I
      //   416: aload_0
      //   417: aload_0
      //   418: getfield 49	com/tencent/a/b/d$a:i	Ljava/lang/reflect/Method;
      //   421: aload_1
      //   422: iconst_0
      //   423: anewarray 148	java/lang/Object
      //   426: invokevirtual 154	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   429: checkcast 114	java/lang/Integer
      //   432: invokevirtual 121	java/lang/Integer:intValue	()I
      //   435: putfield 40	com/tencent/a/b/d$a:e	I
      //   438: aload_0
      //   439: aload_0
      //   440: getfield 55	com/tencent/a/b/d$a:l	Ljava/lang/reflect/Method;
      //   443: aload_1
      //   444: iconst_0
      //   445: anewarray 148	java/lang/Object
      //   448: invokevirtual 154	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   451: checkcast 114	java/lang/Integer
      //   454: invokevirtual 121	java/lang/Integer:intValue	()I
      //   457: putfield 45	com/tencent/a/b/d$a:g	I
      //   460: aload_0
      //   461: aload_0
      //   462: getfield 57	com/tencent/a/b/d$a:m	Ljava/lang/reflect/Method;
      //   465: aload_1
      //   466: iconst_0
      //   467: anewarray 148	java/lang/Object
      //   470: invokevirtual 154	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   473: checkcast 114	java/lang/Integer
      //   476: invokevirtual 121	java/lang/Integer:intValue	()I
      //   479: putfield 47	com/tencent/a/b/d$a:h	I
      //   482: goto -430 -> 52
      //   485: astore_1
      //   486: aload_0
      //   487: iconst_m1
      //   488: putfield 40	com/tencent/a/b/d$a:e	I
      //   491: aload_0
      //   492: iconst_m1
      //   493: putfield 38	com/tencent/a/b/d$a:d	I
      //   496: aload_0
      //   497: iconst_m1
      //   498: putfield 36	com/tencent/a/b/d$a:c	I
      //   501: aload_0
      //   502: ldc 43
      //   504: putfield 45	com/tencent/a/b/d$a:g	I
      //   507: aload_0
      //   508: ldc 43
      //   510: putfield 47	com/tencent/a/b/d$a:h	I
      //   513: goto -461 -> 52
      //   516: astore_1
      //   517: aload_2
      //   518: astore_1
      //   519: goto -272 -> 247
      //
      // Exception table:
      //   from	to	target	type
      //   133	138	244	java/lang/Exception
      //   195	218	252	java/lang/Exception
      //   218	234	252	java/lang/Exception
      //   275	372	485	java/lang/Exception
      //   372	482	485	java/lang/Exception
      //   140	147	516	java/lang/Exception
      //   149	170	516	java/lang/Exception
    }

    public final void onSignalStrengthChanged(int paramInt)
    {
      if (this.a == 1)
        d.c(d.this);
      if (Math.abs(paramInt - (this.f + 113) / 2) > 3)
      {
        if (this.f != -1)
          break label52;
        this.f = ((paramInt << 1) - 113);
      }
      label52: 
      do
      {
        return;
        this.f = ((paramInt << 1) - 113);
        d.a(d.this, new d.b(d.this, this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h));
      }
      while (d.a(d.this) == null);
      d.a(d.this).a(d.b(d.this));
    }
  }

  public final class b
    implements Cloneable
  {
    public int a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 2147483647;
    public int h = 2147483647;

    public b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int arg9)
    {
      this.a = paramInt1;
      this.b = paramInt2;
      this.c = paramInt3;
      this.d = paramInt4;
      this.e = paramInt5;
      this.f = paramInt6;
      this.g = paramInt7;
      int i;
      this.h = i;
    }

    public final Object clone()
    {
      try
      {
        b localb = (b)super.clone();
        return localb;
      }
      catch (Exception localException)
      {
      }
      return null;
    }
  }

  public static abstract interface c
  {
    public abstract void a(d.b paramb);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.d
 * JD-Core Version:    0.6.2
 */