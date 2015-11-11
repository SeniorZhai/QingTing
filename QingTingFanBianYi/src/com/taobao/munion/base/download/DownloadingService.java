package com.taobao.munion.base.download;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.SparseArray;
import android.widget.Toast;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.volley.a.m;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DownloadingService extends Service
{
  private static final long B = 8000L;
  private static final long C = 500L;
  private static Map<c.a, Messenger> D = new HashMap();
  private static SparseArray<f.c> E = new SparseArray();
  private static Boolean H = Boolean.valueOf(false);
  public static boolean a = false;
  static final int b = 1;
  static final int c = 2;
  static final int d = 3;
  static final int e = 4;
  static final int f = 5;
  static final int g = 6;
  public static final int h = 0;
  public static final int i = 1;
  public static final int j = 2;
  public static final int k = 3;
  public static final int l = 4;
  public static final int m = 5;
  public static final int n = 6;
  public static final int o = 7;
  static final int p = 100;
  static final String q = "filename";
  private static final long v = 104857600L;
  private static final long w = 10485760L;
  private static final long x = 259200000L;
  private static final int y = 3;
  private Handler A;
  private i F;
  private boolean G = true;
  a r;
  final Messenger s = new Messenger(new c());
  private NotificationManager t;
  private f u;
  private Context z;

  private void a(c.a parama)
  {
    Log.d("startDownload([mComponentName:" + parama.b + " mTitle:" + parama.c + " mUrl:" + parama.d + "])", new Object[0]);
    this.u.c(parama);
    int i1 = this.u.a(parama);
    b localb = new b(getApplicationContext(), parama, i1, 0, this.r);
    parama = new f.c(parama, i1);
    this.F.a(i1);
    parama.a(E);
    parama.a = localb;
    localb.start();
    d();
    if (a)
    {
      i1 = 0;
      while (i1 < E.size())
      {
        parama = (f.c)E.valueAt(i1);
        Log.d("Running task " + parama.e.c, new Object[0]);
        i1 += 1;
      }
    }
  }

  private void a(final String paramString)
  {
    synchronized (H)
    {
      if (!H.booleanValue())
      {
        Log.d("show single toast.[" + paramString + "]", new Object[0]);
        H = Boolean.valueOf(true);
        this.A.post(new Runnable()
        {
          public void run()
          {
            Toast.makeText(DownloadingService.b(DownloadingService.this), paramString, 0).show();
          }
        });
        this.A.postDelayed(new Runnable()
        {
          public void run()
          {
            DownloadingService.a(Boolean.valueOf(false));
          }
        }
        , 1200L);
      }
      return;
    }
  }

  private void c()
  {
    Iterator localIterator = this.F.a().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      this.t.cancel(localInteger.intValue());
    }
  }

  private void d()
  {
    if (a)
    {
      int i1 = D.size();
      int i2 = E.size();
      Log.i("Client size =" + i1 + "   cacheSize = " + i2, new Object[0]);
      if (i1 != i2)
        throw new RuntimeException("Client size =" + i1 + "   cacheSize = " + i2);
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    Log.d("onBind ", new Object[0]);
    return this.s.getBinder();
  }

  public void onCreate()
  {
    super.onCreate();
    if (a)
      Debug.waitForDebugger();
    this.t = ((NotificationManager)getSystemService("notification"));
    this.z = this;
    this.F = new i(this.z);
    this.u = new f(E, D, this.F);
    this.u.a(m.a(getApplicationContext()));
    this.A = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default:
          return;
        case 5:
          locala = (c.a)paramAnonymousMessage.obj;
          i = paramAnonymousMessage.arg2;
          while (true)
          {
            try
            {
              localObject1 = paramAnonymousMessage.getData().getString("filename");
              Log.d("Cancel old notification....", new Object[0]);
              localObject2 = new Intent("android.intent.action.VIEW");
              ((Intent)localObject2).addFlags(268435456);
              ((Intent)localObject2).setDataAndType(Uri.fromFile(new File((String)localObject1)), "application/vnd.android.package-archive");
              localObject3 = PendingIntent.getActivity(DownloadingService.b(DownloadingService.this), 0, (Intent)localObject2, 134217728);
              if (locala.n)
              {
                paramAnonymousMessage = new Notification(17301634, k.k, System.currentTimeMillis());
                paramAnonymousMessage.setLatestEventInfo(DownloadingService.b(DownloadingService.this), locala.c, k.k, (PendingIntent)localObject3);
                paramAnonymousMessage.flags = 16;
                DownloadingService.a(DownloadingService.this, (NotificationManager)DownloadingService.this.getSystemService("notification"));
                DownloadingService.c(DownloadingService.this).notify(i + 1, paramAnonymousMessage);
                Log.d("Show new  notification....", new Object[0]);
                boolean bool = DownloadingService.a(DownloadingService.this).a(DownloadingService.b(DownloadingService.this));
                Log.d(String.format("isAppOnForeground = %1$B", new Object[] { Boolean.valueOf(bool) }), new Object[0]);
                if ((bool) && (!locala.n))
                {
                  DownloadingService.c(DownloadingService.this).cancel(i + 1);
                  DownloadingService.b(DownloadingService.this).startActivity((Intent)localObject2);
                }
                Log.i(String.format("%1$10s downloaded. Saved to: %2$s", new Object[] { locala.c, localObject1 }), new Object[0]);
                return;
              }
            }
            catch (Exception paramAnonymousMessage)
            {
              Log.e("can not install. " + paramAnonymousMessage.getMessage(), new Object[0]);
              DownloadingService.c(DownloadingService.this).cancel(i + 1);
              return;
            }
            paramAnonymousMessage = new Notification(17301634, k.j, System.currentTimeMillis());
            paramAnonymousMessage.setLatestEventInfo(DownloadingService.b(DownloadingService.this), locala.c, k.j, (PendingIntent)localObject3);
          }
        case 6:
        }
        c.a locala = (c.a)paramAnonymousMessage.obj;
        int i = paramAnonymousMessage.arg2;
        paramAnonymousMessage = paramAnonymousMessage.getData().getString("filename");
        DownloadingService.c(DownloadingService.this).cancel(i);
        Object localObject1 = new Notification(17301633, k.l, System.currentTimeMillis());
        Object localObject2 = PendingIntent.getActivity(DownloadingService.b(DownloadingService.this), 0, new Intent(), 134217728);
        ((Notification)localObject1).setLatestEventInfo(DownloadingService.b(DownloadingService.this), f.c(DownloadingService.b(DownloadingService.this)), k.l, (PendingIntent)localObject2);
        DownloadingService.c(DownloadingService.this).notify(i + 1, (Notification)localObject1);
        localObject1 = paramAnonymousMessage.replace(".patch", ".apk");
        localObject2 = DeltaUpdate.a(DownloadingService.this);
        Object localObject3 = DownloadingService.a(DownloadingService.this);
        localObject3.getClass();
        new f.d((f)localObject3, DownloadingService.b(DownloadingService.this), i, locala, (String)localObject1).execute(new String[] { localObject2, localObject1, paramAnonymousMessage });
      }
    };
    this.r = new a()
    {
      SparseArray<Long> a = new SparseArray();

      public void a(int paramAnonymousInt)
      {
        int j = 0;
        if (DownloadingService.a().indexOfKey(paramAnonymousInt) >= 0)
        {
          f.c localc = (f.c)DownloadingService.a().get(paramAnonymousInt);
          Object localObject = localc.f;
          int i = j;
          if (localObject != null)
          {
            i = j;
            if (localObject[1] > 0L)
            {
              j = (int)((float)localObject[0] / (float)localObject[1] * 100.0F);
              i = j;
              if (j > 100)
                i = 99;
            }
          }
          if (!localc.e.n)
          {
            this.a.put(paramAnonymousInt, Long.valueOf(-1L));
            localObject = DownloadingService.a(DownloadingService.this).a(DownloadingService.this, localc.e, paramAnonymousInt, i);
            localc.b = ((f.a)localObject);
            DownloadingService.c(DownloadingService.this).notify(paramAnonymousInt, ((f.a)localObject).a());
          }
        }
      }

      public void a(int paramAnonymousInt1, int paramAnonymousInt2)
      {
        if (DownloadingService.a().indexOfKey(paramAnonymousInt1) >= 0)
        {
          Object localObject = (f.c)DownloadingService.a().get(paramAnonymousInt1);
          c.a locala = ((f.c)localObject).e;
          long l = System.currentTimeMillis();
          if ((!locala.n) && (l - ((Long)this.a.get(paramAnonymousInt1)).longValue() > 500L))
          {
            this.a.put(paramAnonymousInt1, Long.valueOf(l));
            localObject = ((f.c)localObject).b;
            ((f.a)localObject).a(100, paramAnonymousInt2, false).a(String.valueOf(paramAnonymousInt2) + "%");
            DownloadingService.c(DownloadingService.this).notify(paramAnonymousInt1, ((f.a)localObject).a());
          }
          Log.d(String.format("%3$10s Notification: mNotificationId = %1$15s\t|\tprogress = %2$15s", new Object[] { Integer.valueOf(paramAnonymousInt1), Integer.valueOf(paramAnonymousInt2), locala.c }), new Object[0]);
        }
      }

      public void a(int paramAnonymousInt, Exception paramAnonymousException)
      {
        if (DownloadingService.a().indexOfKey(paramAnonymousInt) >= 0)
          DownloadingService.a(DownloadingService.this).b(DownloadingService.b(DownloadingService.this), paramAnonymousInt);
      }

      public void a(int paramAnonymousInt, String paramAnonymousString)
      {
        Object localObject;
        Bundle localBundle;
        if (DownloadingService.a().indexOfKey(paramAnonymousInt) >= 0)
        {
          localObject = (f.c)DownloadingService.a().get(paramAnonymousInt);
          if (localObject != null)
          {
            localObject = ((f.c)localObject).e;
            e.a(DownloadingService.b(DownloadingService.this)).a(((c.a)localObject).b, ((c.a)localObject).d, 100);
            localBundle = new Bundle();
            localBundle.putString("filename", paramAnonymousString);
            if (!((c.a)localObject).b.equalsIgnoreCase("delta_update"))
              break label126;
            paramAnonymousString = Message.obtain();
            paramAnonymousString.what = 6;
            paramAnonymousString.arg1 = 1;
            paramAnonymousString.obj = localObject;
            paramAnonymousString.arg2 = paramAnonymousInt;
            paramAnonymousString.setData(localBundle);
            DownloadingService.d(DownloadingService.this).sendMessage(paramAnonymousString);
          }
        }
        return;
        label126: paramAnonymousString = Message.obtain();
        paramAnonymousString.what = 5;
        paramAnonymousString.arg1 = 1;
        paramAnonymousString.obj = localObject;
        paramAnonymousString.arg2 = paramAnonymousInt;
        paramAnonymousString.setData(localBundle);
        DownloadingService.d(DownloadingService.this).sendMessage(paramAnonymousString);
        paramAnonymousString = Message.obtain();
        paramAnonymousString.what = 5;
        paramAnonymousString.arg1 = 1;
        paramAnonymousString.arg2 = paramAnonymousInt;
        paramAnonymousString.setData(localBundle);
        try
        {
          if (DownloadingService.b().get(localObject) != null)
            ((Messenger)DownloadingService.b().get(localObject)).send(paramAnonymousString);
          DownloadingService.a(DownloadingService.this).b(DownloadingService.b(DownloadingService.this), paramAnonymousInt);
          return;
        }
        catch (RemoteException paramAnonymousString)
        {
          DownloadingService.a(DownloadingService.this).b(DownloadingService.b(DownloadingService.this), paramAnonymousInt);
        }
      }
    };
  }

  public void onDestroy()
  {
    try
    {
      e.a(getApplicationContext()).a(259200);
      e.a(getApplicationContext()).finalize();
      super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e(localException.getMessage(), new Object[0]);
    }
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if ((paramIntent != null) && (paramIntent.getExtras() != null))
      this.u.a(this, paramIntent);
    if ((Build.VERSION.SDK_INT >= 19) && ((this.F.b()) || (this.G)));
    try
    {
      paramIntent = new Intent(getApplicationContext(), getClass());
      paramIntent.setPackage(getPackageName());
      paramIntent = PendingIntent.getService(getApplicationContext(), 1, paramIntent, 1073741824);
      ((AlarmManager)getApplicationContext().getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + 5000L, paramIntent);
      label109: if (this.G)
      {
        c();
        this.G = false;
      }
      return 1;
    }
    catch (Exception paramIntent)
    {
      break label109;
    }
  }

  static abstract interface a
  {
    public abstract void a(int paramInt);

    public abstract void a(int paramInt1, int paramInt2);

    public abstract void a(int paramInt, Exception paramException);

    public abstract void a(int paramInt, String paramString);
  }

  class b extends Thread
  {
    private Context b;
    private boolean c;
    private File d;
    private int e = 0;
    private long f = -1L;
    private long g = -1L;
    private int h = -1;
    private int i;
    private DownloadingService.a j;
    private c.a k;

    public b(Context parama, c.a paramInt1, int paramInt2, int parama1, DownloadingService.a arg6)
    {
      try
      {
        this.b = parama;
        this.k = paramInt1;
        this.e = parama1;
        if (DownloadingService.a().indexOfKey(paramInt2) >= 0)
        {
          this$1 = ((f.c)DownloadingService.a().get(paramInt2)).f;
          if ((DownloadingService.this != null) && (DownloadingService.this.length > 1))
          {
            this.f = DownloadingService.this[0];
            this.g = DownloadingService.this[1];
          }
        }
        Object localObject;
        this.j = localObject;
        this.i = paramInt2;
        this$1 = new boolean[1];
        this.d = f.a("/apk", parama, DownloadingService.this);
        this.c = DownloadingService.this[0];
        if (this.c);
        this$1 = a(this.k);
        this.d = new File(this.d, DownloadingService.this);
        return;
      }
      catch (Exception this$1)
      {
        Log.d(DownloadingService.this.getMessage(), new Object[] { DownloadingService.this });
        this.j.a(this.i, DownloadingService.this);
      }
    }

    private String a(c.a parama)
    {
      if (parama.f != null);
      for (String str1 = parama.f + ".apk.tmp"; ; str1 = f.a(parama.d) + ".apk.tmp")
      {
        String str2 = str1;
        if (parama.b.equalsIgnoreCase("delta_update"))
          str2 = str1.replace(".apk", ".patch");
        return str2;
      }
    }

    private HttpURLConnection a(URL paramURL, File paramFile)
      throws IOException
    {
      paramURL = (HttpURLConnection)paramURL.openConnection();
      paramURL.setRequestMethod("GET");
      paramURL.setRequestProperty("Accept-Encoding", "identity");
      paramURL.addRequestProperty("Connection", "keep-alive");
      paramURL.setConnectTimeout(5000);
      paramURL.setReadTimeout(10000);
      if ((paramFile.exists()) && (paramFile.length() > 0L))
      {
        Log.d(String.format(this.k.c + " getFileLength: %1$15s", new Object[] { Long.valueOf(paramFile.length()) }), new Object[0]);
        paramURL.setRequestProperty("Range", "bytes=" + paramFile.length() + "-");
      }
      return paramURL;
    }

    private void a()
    {
      Log.d("wait for repeating Test network repeat count=" + this.e, new Object[0]);
      try
      {
        if (!this.k.m)
        {
          Thread.sleep(8000L);
          if (this.g < 1L)
          {
            a(false);
            return;
          }
          a(true);
          return;
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        a(localInterruptedException);
        DownloadingService.a(DownloadingService.this).b(this.b, this.i);
        return;
      }
      Object localObject = (f.c)DownloadingService.a().get(this.i);
      ((f.c)localObject).f[0] = this.f;
      ((f.c)localObject).f[1] = this.g;
      ((f.c)localObject).f[2] = this.e;
      localObject = j.a(this.i, "continue");
      Intent localIntent = new Intent(this.b, DownloadingService.class);
      localIntent.putExtra("com.taobao.broadcast.download.msg", (String)localObject);
      DownloadingService.a(DownloadingService.this).a(DownloadingService.this, localIntent);
      DownloadingService.a(DownloadingService.this, k.n);
      Log.d("changed play state button on op-notification.", new Object[0]);
    }

    private void a(File paramFile, String paramString)
      throws RemoteException
    {
      Log.d("itemMd5 " + this.k.e, new Object[0]);
      Log.d("fileMd5 " + f.a(paramFile), new Object[0]);
      if ((this.k.e != null) && (!this.k.e.equalsIgnoreCase(f.a(paramFile))))
      {
        if (!this.k.b.equalsIgnoreCase("delta_update"))
          break label235;
        DownloadingService.c(DownloadingService.this).cancel(this.i);
        paramFile = new Bundle();
        paramFile.putString("filename", paramString);
        paramString = Message.obtain();
        paramString.what = 5;
        paramString.arg1 = 3;
        paramString.arg2 = this.i;
        paramString.setData(paramFile);
      }
      label235: 
      do
      {
        try
        {
          if (DownloadingService.b().get(this.k) != null)
            ((Messenger)DownloadingService.b().get(this.k)).send(paramString);
          DownloadingService.a(DownloadingService.this).b(this.b, this.i);
          return;
        }
        catch (RemoteException paramFile)
        {
          DownloadingService.a(DownloadingService.this).b(this.b, this.i);
          return;
        }
        ((Messenger)DownloadingService.b().get(this.k)).send(Message.obtain(null, 5, 0, 0));
      }
      while (this.k.n);
      DownloadingService.a(DownloadingService.this).b(this.b, this.i);
      paramFile = new Notification(17301634, k.m, System.currentTimeMillis());
      paramString = PendingIntent.getActivity(this.b, 0, new Intent(), 0);
      paramFile.setLatestEventInfo(this.b, f.c(this.b), k.m, paramString);
      paramFile.flags |= 16;
      DownloadingService.c(DownloadingService.this).notify(this.i, paramFile);
    }

    private void a(Exception paramException)
    {
      Log.e("can not install. " + paramException.getMessage(), new Object[0]);
      if (this.j != null)
        this.j.a(this.i, paramException);
      DownloadingService.a(DownloadingService.this).a(this.f, this.g, this.e, this.k);
    }

    // ERROR //
    private void a(boolean paramBoolean)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: iconst_0
      //   3: istore 10
      //   5: aload_0
      //   6: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   9: invokevirtual 389	java/io/File:getName	()Ljava/lang/String;
      //   12: astore 4
      //   14: new 391	java/io/FileOutputStream
      //   17: dup
      //   18: aload_0
      //   19: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   22: iconst_1
      //   23: invokespecial 394	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
      //   26: astore_2
      //   27: aload_0
      //   28: getfield 83	com/taobao/munion/base/download/DownloadingService$b:c	Z
      //   31: ifne +1968 -> 1999
      //   34: aload_0
      //   35: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   38: invokevirtual 397	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   41: iconst_3
      //   42: invokestatic 400	com/taobao/munion/base/download/f:a	(Ljava/lang/String;I)Z
      //   45: ifne +1954 -> 1999
      //   48: aload_2
      //   49: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   52: aload_0
      //   53: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   56: aload 4
      //   58: ldc_w 404
      //   61: invokevirtual 410	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   64: astore_3
      //   65: aload_0
      //   66: aload_0
      //   67: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   70: aload 4
      //   72: invokevirtual 414	android/content/Context:getFileStreamPath	(Ljava/lang/String;)Ljava/io/File;
      //   75: putfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   78: aload_3
      //   79: astore_2
      //   80: ldc_w 416
      //   83: iconst_2
      //   84: anewarray 97	java/lang/Object
      //   87: dup
      //   88: iconst_0
      //   89: aload_0
      //   90: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   93: getfield 145	com/taobao/munion/base/download/c$a:d	Ljava/lang/String;
      //   96: aastore
      //   97: dup
      //   98: iconst_1
      //   99: aload_0
      //   100: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   103: invokevirtual 397	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   106: aastore
      //   107: invokestatic 209	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   110: iconst_0
      //   111: anewarray 97	java/lang/Object
      //   114: invokestatic 102	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   117: aload_0
      //   118: new 153	java/net/URL
      //   121: dup
      //   122: aload_0
      //   123: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   126: getfield 145	com/taobao/munion/base/download/c$a:d	Ljava/lang/String;
      //   129: invokespecial 418	java/net/URL:<init>	(Ljava/lang/String;)V
      //   132: aload_0
      //   133: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   136: invokespecial 420	com/taobao/munion/base/download/DownloadingService$b:a	(Ljava/net/URL;Ljava/io/File;)Ljava/net/HttpURLConnection;
      //   139: astore 4
      //   141: aload 4
      //   143: ldc 161
      //   145: invokevirtual 165	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   148: aload 4
      //   150: invokevirtual 423	java/net/HttpURLConnection:connect	()V
      //   153: aload 4
      //   155: invokevirtual 427	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   158: astore_3
      //   159: iload_1
      //   160: ifne +196 -> 356
      //   163: lconst_0
      //   164: lstore 14
      //   166: lload 14
      //   168: lstore 12
      //   170: aload_2
      //   171: astore 6
      //   173: aload_3
      //   174: astore 5
      //   176: aload_0
      //   177: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   180: invokevirtual 191	java/io/File:exists	()Z
      //   183: ifeq +42 -> 225
      //   186: lload 14
      //   188: lstore 12
      //   190: aload_2
      //   191: astore 6
      //   193: aload_3
      //   194: astore 5
      //   196: aload_0
      //   197: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   200: invokevirtual 195	java/io/File:length	()J
      //   203: lconst_0
      //   204: lcmp
      //   205: ifle +20 -> 225
      //   208: aload_2
      //   209: astore 6
      //   211: aload_3
      //   212: astore 5
      //   214: lconst_0
      //   215: aload_0
      //   216: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   219: invokevirtual 195	java/io/File:length	()J
      //   222: ladd
      //   223: lstore 12
      //   225: aload_2
      //   226: astore 6
      //   228: aload_3
      //   229: astore 5
      //   231: aload_0
      //   232: lload 12
      //   234: putfield 42	com/taobao/munion/base/download/DownloadingService$b:f	J
      //   237: aload_2
      //   238: astore 6
      //   240: aload_3
      //   241: astore 5
      //   243: aload_0
      //   244: lload 12
      //   246: aload 4
      //   248: invokevirtual 431	java/net/HttpURLConnection:getContentLength	()I
      //   251: i2l
      //   252: ladd
      //   253: putfield 44	com/taobao/munion/base/download/DownloadingService$b:g	J
      //   256: aload_2
      //   257: astore 6
      //   259: aload_3
      //   260: astore 5
      //   262: ldc_w 433
      //   265: iconst_1
      //   266: anewarray 97	java/lang/Object
      //   269: dup
      //   270: iconst_0
      //   271: aload_0
      //   272: getfield 42	com/taobao/munion/base/download/DownloadingService$b:f	J
      //   275: invokestatic 205	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   278: aastore
      //   279: invokestatic 209	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   282: iconst_0
      //   283: anewarray 97	java/lang/Object
      //   286: invokestatic 102	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   289: aload_2
      //   290: astore 6
      //   292: aload_3
      //   293: astore 5
      //   295: ldc_w 435
      //   298: iconst_1
      //   299: anewarray 97	java/lang/Object
      //   302: dup
      //   303: iconst_0
      //   304: aload 4
      //   306: invokevirtual 431	java/net/HttpURLConnection:getContentLength	()I
      //   309: invokestatic 440	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   312: aastore
      //   313: invokestatic 209	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   316: iconst_0
      //   317: anewarray 97	java/lang/Object
      //   320: invokestatic 102	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   323: aload_2
      //   324: astore 6
      //   326: aload_3
      //   327: astore 5
      //   329: ldc_w 442
      //   332: iconst_1
      //   333: anewarray 97	java/lang/Object
      //   336: dup
      //   337: iconst_0
      //   338: aload_0
      //   339: getfield 44	com/taobao/munion/base/download/DownloadingService$b:g	J
      //   342: invokestatic 205	java/lang/Long:valueOf	(J)Ljava/lang/Long;
      //   345: aastore
      //   346: invokestatic 209	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   349: iconst_0
      //   350: anewarray 97	java/lang/Object
      //   353: invokestatic 102	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   356: aload_2
      //   357: astore 6
      //   359: aload_3
      //   360: astore 5
      //   362: sipush 4096
      //   365: newarray byte
      //   367: astore 4
      //   369: aload_2
      //   370: astore 6
      //   372: aload_3
      //   373: astore 5
      //   375: new 115	java/lang/StringBuilder
      //   378: dup
      //   379: invokespecial 116	java/lang/StringBuilder:<init>	()V
      //   382: aload_0
      //   383: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   386: getfield 197	com/taobao/munion/base/download/c$a:c	Ljava/lang/String;
      //   389: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   392: ldc_w 444
      //   395: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   398: aload_0
      //   399: getfield 44	com/taobao/munion/base/download/DownloadingService$b:g	J
      //   402: invokestatic 447	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   405: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   408: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   411: iconst_0
      //   412: anewarray 97	java/lang/Object
      //   415: invokestatic 102	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   418: aload_2
      //   419: astore 6
      //   421: aload_3
      //   422: astore 5
      //   424: aload_0
      //   425: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   428: invokestatic 452	com/taobao/munion/base/download/e:a	(Landroid/content/Context;)Lcom/taobao/munion/base/download/e;
      //   431: aload_0
      //   432: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   435: getfield 127	com/taobao/munion/base/download/c$a:b	Ljava/lang/String;
      //   438: aload_0
      //   439: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   442: getfield 145	com/taobao/munion/base/download/c$a:d	Ljava/lang/String;
      //   445: invokevirtual 455	com/taobao/munion/base/download/e:a	(Ljava/lang/String;Ljava/lang/String;)Z
      //   448: pop
      //   449: iconst_0
      //   450: istore 8
      //   452: aload_2
      //   453: astore 6
      //   455: aload_3
      //   456: astore 5
      //   458: aload_0
      //   459: getfield 46	com/taobao/munion/base/download/DownloadingService$b:h	I
      //   462: ifge +1531 -> 1993
      //   465: aload_2
      //   466: astore 6
      //   468: aload_3
      //   469: astore 5
      //   471: aload_3
      //   472: aload 4
      //   474: invokevirtual 461	java/io/InputStream:read	([B)I
      //   477: istore 9
      //   479: iload 9
      //   481: ifle +1512 -> 1993
      //   484: aload_2
      //   485: astore 6
      //   487: aload_3
      //   488: astore 5
      //   490: aload_2
      //   491: aload 4
      //   493: iconst_0
      //   494: iload 9
      //   496: invokevirtual 465	java/io/FileOutputStream:write	([BII)V
      //   499: aload_2
      //   500: astore 6
      //   502: aload_3
      //   503: astore 5
      //   505: aload_0
      //   506: aload_0
      //   507: getfield 42	com/taobao/munion/base/download/DownloadingService$b:f	J
      //   510: iload 9
      //   512: i2l
      //   513: ladd
      //   514: putfield 42	com/taobao/munion/base/download/DownloadingService$b:f	J
      //   517: iload 8
      //   519: iconst_1
      //   520: iadd
      //   521: istore 9
      //   523: iload 8
      //   525: bipush 50
      //   527: irem
      //   528: ifne +1458 -> 1986
      //   531: aload_2
      //   532: astore 6
      //   534: aload_3
      //   535: astore 5
      //   537: aload_0
      //   538: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   541: invokestatic 468	com/taobao/munion/base/download/f:b	(Landroid/content/Context;)Z
      //   544: ifne +297 -> 841
      //   547: iload 10
      //   549: istore 8
      //   551: aload_2
      //   552: astore 6
      //   554: aload_3
      //   555: astore 5
      //   557: aload_3
      //   558: invokevirtual 469	java/io/InputStream:close	()V
      //   561: aload_2
      //   562: astore 6
      //   564: aload_3
      //   565: astore 5
      //   567: aload_2
      //   568: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   571: aload_2
      //   572: astore 6
      //   574: aload_3
      //   575: astore 5
      //   577: aload_0
      //   578: getfield 46	com/taobao/munion/base/download/DownloadingService$b:h	I
      //   581: iconst_1
      //   582: if_icmpne +493 -> 1075
      //   585: aload_2
      //   586: astore 6
      //   588: aload_3
      //   589: astore 5
      //   591: invokestatic 53	com/taobao/munion/base/download/DownloadingService:a	()Landroid/util/SparseArray;
      //   594: aload_0
      //   595: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   598: invokevirtual 63	android/util/SparseArray:get	(I)Ljava/lang/Object;
      //   601: checkcast 65	com/taobao/munion/base/download/f$c
      //   604: astore 4
      //   606: aload 4
      //   608: ifnull +55 -> 663
      //   611: aload_2
      //   612: astore 6
      //   614: aload_3
      //   615: astore 5
      //   617: aload 4
      //   619: getfield 68	com/taobao/munion/base/download/f$c:f	[J
      //   622: iconst_0
      //   623: aload_0
      //   624: getfield 42	com/taobao/munion/base/download/DownloadingService$b:f	J
      //   627: lastore
      //   628: aload_2
      //   629: astore 6
      //   631: aload_3
      //   632: astore 5
      //   634: aload 4
      //   636: getfield 68	com/taobao/munion/base/download/f$c:f	[J
      //   639: iconst_1
      //   640: aload_0
      //   641: getfield 44	com/taobao/munion/base/download/DownloadingService$b:g	J
      //   644: lastore
      //   645: aload_2
      //   646: astore 6
      //   648: aload_3
      //   649: astore 5
      //   651: aload 4
      //   653: getfield 68	com/taobao/munion/base/download/f$c:f	[J
      //   656: iconst_2
      //   657: aload_0
      //   658: getfield 38	com/taobao/munion/base/download/DownloadingService$b:e	I
      //   661: i2l
      //   662: lastore
      //   663: aload_3
      //   664: ifnull +7 -> 671
      //   667: aload_3
      //   668: invokevirtual 469	java/io/InputStream:close	()V
      //   671: aload_2
      //   672: ifnull +7 -> 679
      //   675: aload_2
      //   676: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   679: return
      //   680: astore_3
      //   681: aload_0
      //   682: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   685: invokevirtual 472	java/io/File:delete	()Z
      //   688: pop
      //   689: goto -536 -> 153
      //   692: astore 4
      //   694: aconst_null
      //   695: astore_3
      //   696: aload 4
      //   698: ldc_w 474
      //   701: iconst_0
      //   702: anewarray 97	java/lang/Object
      //   705: invokestatic 477	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
      //   708: aload_0
      //   709: getfield 38	com/taobao/munion/base/download/DownloadingService$b:e	I
      //   712: iconst_1
      //   713: iadd
      //   714: istore 8
      //   716: aload_0
      //   717: iload 8
      //   719: putfield 38	com/taobao/munion/base/download/DownloadingService$b:e	I
      //   722: iload 8
      //   724: iconst_3
      //   725: if_icmple +1011 -> 1736
      //   728: aload_0
      //   729: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   732: getfield 229	com/taobao/munion/base/download/c$a:m	Z
      //   735: istore_1
      //   736: iload_1
      //   737: ifne +999 -> 1736
      //   740: ldc_w 479
      //   743: iconst_0
      //   744: anewarray 97	java/lang/Object
      //   747: invokestatic 102	com/taobao/munion/base/Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   750: invokestatic 327	com/taobao/munion/base/download/DownloadingService:b	()Ljava/util/Map;
      //   753: aload_0
      //   754: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   757: invokeinterface 332 2 0
      //   762: checkcast 334	android/os/Messenger
      //   765: aconst_null
      //   766: iconst_5
      //   767: iconst_0
      //   768: iconst_0
      //   769: invokestatic 341	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   772: invokevirtual 338	android/os/Messenger:send	(Landroid/os/Message;)V
      //   775: aload_0
      //   776: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   779: invokestatic 244	com/taobao/munion/base/download/DownloadingService:a	(Lcom/taobao/munion/base/download/DownloadingService;)Lcom/taobao/munion/base/download/f;
      //   782: aload_0
      //   783: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   786: aload_0
      //   787: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   790: invokevirtual 247	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
      //   793: aload_0
      //   794: aload 4
      //   796: invokespecial 241	com/taobao/munion/base/download/DownloadingService$b:a	(Ljava/lang/Exception;)V
      //   799: aload_0
      //   800: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   803: invokestatic 482	com/taobao/munion/base/download/DownloadingService:d	(Lcom/taobao/munion/base/download/DownloadingService;)Landroid/os/Handler;
      //   806: new 9	com/taobao/munion/base/download/DownloadingService$b$1
      //   809: dup
      //   810: aload_0
      //   811: invokespecial 485	com/taobao/munion/base/download/DownloadingService$b$1:<init>	(Lcom/taobao/munion/base/download/DownloadingService$b;)V
      //   814: invokevirtual 491	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   817: pop
      //   818: aload_3
      //   819: ifnull +7 -> 826
      //   822: aload_3
      //   823: invokevirtual 469	java/io/InputStream:close	()V
      //   826: aload_2
      //   827: ifnull -148 -> 679
      //   830: aload_2
      //   831: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   834: return
      //   835: astore_2
      //   836: aload_2
      //   837: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   840: return
      //   841: aload_2
      //   842: astore 6
      //   844: aload_3
      //   845: astore 5
      //   847: aload_0
      //   848: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   851: invokestatic 496	com/taobao/munion/base/download/f:e	(Landroid/content/Context;)Z
      //   854: ifne +52 -> 906
      //   857: aload_2
      //   858: astore 6
      //   860: aload_3
      //   861: astore 5
      //   863: aload_0
      //   864: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   867: getfield 499	com/taobao/munion/base/download/c$a:o	Z
      //   870: ifeq +36 -> 906
      //   873: aload_2
      //   874: astore 6
      //   876: aload_3
      //   877: astore 5
      //   879: ldc_w 501
      //   882: iconst_0
      //   883: anewarray 97	java/lang/Object
      //   886: invokestatic 381	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   889: aload_2
      //   890: astore 6
      //   892: aload_3
      //   893: astore 5
      //   895: new 151	java/io/IOException
      //   898: dup
      //   899: ldc_w 501
      //   902: invokespecial 502	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   905: athrow
      //   906: aload_2
      //   907: astore 6
      //   909: aload_3
      //   910: astore 5
      //   912: aload_0
      //   913: getfield 42	com/taobao/munion/base/download/DownloadingService$b:f	J
      //   916: l2f
      //   917: ldc_w 503
      //   920: fmul
      //   921: aload_0
      //   922: getfield 44	com/taobao/munion/base/download/DownloadingService$b:g	J
      //   925: l2f
      //   926: fdiv
      //   927: f2i
      //   928: istore 11
      //   930: iload 11
      //   932: istore 8
      //   934: iload 11
      //   936: bipush 100
      //   938: if_icmple +7 -> 945
      //   941: bipush 99
      //   943: istore 8
      //   945: aload_2
      //   946: astore 6
      //   948: aload_3
      //   949: astore 5
      //   951: aload_0
      //   952: getfield 70	com/taobao/munion/base/download/DownloadingService$b:j	Lcom/taobao/munion/base/download/DownloadingService$a;
      //   955: ifnull +24 -> 979
      //   958: aload_2
      //   959: astore 6
      //   961: aload_3
      //   962: astore 5
      //   964: aload_0
      //   965: getfield 70	com/taobao/munion/base/download/DownloadingService$b:j	Lcom/taobao/munion/base/download/DownloadingService$a;
      //   968: aload_0
      //   969: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   972: iload 8
      //   974: invokeinterface 506 3 0
      //   979: aload_2
      //   980: astore 6
      //   982: aload_3
      //   983: astore 5
      //   985: aload_0
      //   986: iload 8
      //   988: invokespecial 508	com/taobao/munion/base/download/DownloadingService$b:b	(I)V
      //   991: aload_2
      //   992: astore 6
      //   994: aload_3
      //   995: astore 5
      //   997: aload_0
      //   998: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   1001: invokestatic 452	com/taobao/munion/base/download/e:a	(Landroid/content/Context;)Lcom/taobao/munion/base/download/e;
      //   1004: aload_0
      //   1005: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   1008: getfield 127	com/taobao/munion/base/download/c$a:b	Ljava/lang/String;
      //   1011: aload_0
      //   1012: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   1015: getfield 145	com/taobao/munion/base/download/c$a:d	Ljava/lang/String;
      //   1018: iload 8
      //   1020: invokevirtual 511	com/taobao/munion/base/download/e:a	(Ljava/lang/String;Ljava/lang/String;I)V
      //   1023: iload 9
      //   1025: istore 8
      //   1027: goto -575 -> 452
      //   1030: astore_2
      //   1031: aload_2
      //   1032: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1035: return
      //   1036: astore_3
      //   1037: aload_3
      //   1038: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1041: aload_2
      //   1042: ifnull -363 -> 679
      //   1045: aload_2
      //   1046: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1049: return
      //   1050: astore_2
      //   1051: aload_2
      //   1052: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1055: return
      //   1056: astore_3
      //   1057: aload_2
      //   1058: ifnull +7 -> 1065
      //   1061: aload_2
      //   1062: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1065: aload_3
      //   1066: athrow
      //   1067: astore_2
      //   1068: aload_2
      //   1069: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1072: goto -7 -> 1065
      //   1075: aload_2
      //   1076: astore 6
      //   1078: aload_3
      //   1079: astore 5
      //   1081: aload_0
      //   1082: getfield 46	com/taobao/munion/base/download/DownloadingService$b:h	I
      //   1085: iconst_2
      //   1086: if_icmpne +118 -> 1204
      //   1089: aload_2
      //   1090: astore 6
      //   1092: aload_3
      //   1093: astore 5
      //   1095: aload_0
      //   1096: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1099: invokestatic 244	com/taobao/munion/base/download/DownloadingService:a	(Lcom/taobao/munion/base/download/DownloadingService;)Lcom/taobao/munion/base/download/f;
      //   1102: aload_0
      //   1103: getfield 42	com/taobao/munion/base/download/DownloadingService$b:f	J
      //   1106: aload_0
      //   1107: getfield 44	com/taobao/munion/base/download/DownloadingService$b:g	J
      //   1110: aload_0
      //   1111: getfield 38	com/taobao/munion/base/download/DownloadingService$b:e	I
      //   1114: i2l
      //   1115: aload_0
      //   1116: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   1119: invokevirtual 513	com/taobao/munion/base/download/f:d	(JJJLcom/taobao/munion/base/download/c$a;)V
      //   1122: aload_2
      //   1123: astore 6
      //   1125: aload_3
      //   1126: astore 5
      //   1128: aload_0
      //   1129: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1132: invokestatic 292	com/taobao/munion/base/download/DownloadingService:c	(Lcom/taobao/munion/base/download/DownloadingService;)Landroid/app/NotificationManager;
      //   1135: aload_0
      //   1136: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   1139: invokevirtual 297	android/app/NotificationManager:cancel	(I)V
      //   1142: aload_3
      //   1143: ifnull +7 -> 1150
      //   1146: aload_3
      //   1147: invokevirtual 469	java/io/InputStream:close	()V
      //   1150: aload_2
      //   1151: ifnull -472 -> 679
      //   1154: aload_2
      //   1155: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1158: return
      //   1159: astore_2
      //   1160: aload_2
      //   1161: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1164: return
      //   1165: astore_3
      //   1166: aload_3
      //   1167: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1170: aload_2
      //   1171: ifnull -492 -> 679
      //   1174: aload_2
      //   1175: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1178: return
      //   1179: astore_2
      //   1180: aload_2
      //   1181: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1184: return
      //   1185: astore_3
      //   1186: aload_2
      //   1187: ifnull +7 -> 1194
      //   1190: aload_2
      //   1191: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1194: aload_3
      //   1195: athrow
      //   1196: astore_2
      //   1197: aload_2
      //   1198: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1201: goto -7 -> 1194
      //   1204: iload 8
      //   1206: ifne +157 -> 1363
      //   1209: aload_2
      //   1210: astore 6
      //   1212: aload_3
      //   1213: astore 5
      //   1215: new 115	java/lang/StringBuilder
      //   1218: dup
      //   1219: invokespecial 116	java/lang/StringBuilder:<init>	()V
      //   1222: ldc_w 515
      //   1225: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1228: aload_0
      //   1229: getfield 38	com/taobao/munion/base/download/DownloadingService$b:e	I
      //   1232: invokevirtual 226	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1235: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1238: iconst_0
      //   1239: anewarray 97	java/lang/Object
      //   1242: invokestatic 381	com/taobao/munion/base/Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   1245: aload_2
      //   1246: astore 6
      //   1248: aload_3
      //   1249: astore 5
      //   1251: invokestatic 327	com/taobao/munion/base/download/DownloadingService:b	()Ljava/util/Map;
      //   1254: aload_0
      //   1255: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   1258: invokeinterface 332 2 0
      //   1263: checkcast 334	android/os/Messenger
      //   1266: aconst_null
      //   1267: iconst_5
      //   1268: iconst_0
      //   1269: iconst_0
      //   1270: invokestatic 341	android/os/Message:obtain	(Landroid/os/Handler;III)Landroid/os/Message;
      //   1273: invokevirtual 338	android/os/Messenger:send	(Landroid/os/Message;)V
      //   1276: aload_2
      //   1277: astore 6
      //   1279: aload_3
      //   1280: astore 5
      //   1282: aload_0
      //   1283: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1286: invokestatic 244	com/taobao/munion/base/download/DownloadingService:a	(Lcom/taobao/munion/base/download/DownloadingService;)Lcom/taobao/munion/base/download/f;
      //   1289: aload_0
      //   1290: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   1293: aload_0
      //   1294: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   1297: invokevirtual 247	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
      //   1300: aload_2
      //   1301: astore 6
      //   1303: aload_3
      //   1304: astore 5
      //   1306: aload_0
      //   1307: new 31	java/lang/Exception
      //   1310: dup
      //   1311: new 115	java/lang/StringBuilder
      //   1314: dup
      //   1315: invokespecial 116	java/lang/StringBuilder:<init>	()V
      //   1318: ldc_w 515
      //   1321: invokevirtual 120	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   1324: aload_0
      //   1325: getfield 38	com/taobao/munion/base/download/DownloadingService$b:e	I
      //   1328: invokevirtual 226	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   1331: invokevirtual 125	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   1334: invokespecial 516	java/lang/Exception:<init>	(Ljava/lang/String;)V
      //   1337: invokespecial 241	com/taobao/munion/base/download/DownloadingService$b:a	(Ljava/lang/Exception;)V
      //   1340: aload_3
      //   1341: ifnull +7 -> 1348
      //   1344: aload_3
      //   1345: invokevirtual 469	java/io/InputStream:close	()V
      //   1348: aload_2
      //   1349: ifnull -670 -> 679
      //   1352: aload_2
      //   1353: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1356: return
      //   1357: astore_2
      //   1358: aload_2
      //   1359: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1362: return
      //   1363: aload_2
      //   1364: astore 6
      //   1366: aload_3
      //   1367: astore 5
      //   1369: aload_0
      //   1370: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1373: invokestatic 244	com/taobao/munion/base/download/DownloadingService:a	(Lcom/taobao/munion/base/download/DownloadingService;)Lcom/taobao/munion/base/download/f;
      //   1376: aload_0
      //   1377: getfield 50	com/taobao/munion/base/download/DownloadingService$b:k	Lcom/taobao/munion/base/download/c$a;
      //   1380: invokevirtual 519	com/taobao/munion/base/download/f:d	(Lcom/taobao/munion/base/download/c$a;)V
      //   1383: aload_2
      //   1384: astore 6
      //   1386: aload_3
      //   1387: astore 5
      //   1389: new 88	java/io/File
      //   1392: dup
      //   1393: aload_0
      //   1394: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   1397: invokevirtual 522	java/io/File:getParent	()Ljava/lang/String;
      //   1400: aload_0
      //   1401: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   1404: invokevirtual 389	java/io/File:getName	()Ljava/lang/String;
      //   1407: ldc_w 524
      //   1410: ldc_w 526
      //   1413: invokevirtual 143	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
      //   1416: invokespecial 528	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
      //   1419: astore 4
      //   1421: aload_2
      //   1422: astore 6
      //   1424: aload_3
      //   1425: astore 5
      //   1427: aload_0
      //   1428: getfield 81	com/taobao/munion/base/download/DownloadingService$b:d	Ljava/io/File;
      //   1431: aload 4
      //   1433: invokevirtual 532	java/io/File:renameTo	(Ljava/io/File;)Z
      //   1436: pop
      //   1437: aload_2
      //   1438: astore 6
      //   1440: aload_3
      //   1441: astore 5
      //   1443: aload 4
      //   1445: invokevirtual 397	java/io/File:getAbsolutePath	()Ljava/lang/String;
      //   1448: astore 7
      //   1450: aload_2
      //   1451: astore 6
      //   1453: aload_3
      //   1454: astore 5
      //   1456: aload_0
      //   1457: aload 4
      //   1459: aload 7
      //   1461: invokespecial 534	com/taobao/munion/base/download/DownloadingService$b:a	(Ljava/io/File;Ljava/lang/String;)V
      //   1464: aload_2
      //   1465: astore 6
      //   1467: aload_3
      //   1468: astore 5
      //   1470: aload_0
      //   1471: getfield 70	com/taobao/munion/base/download/DownloadingService$b:j	Lcom/taobao/munion/base/download/DownloadingService$a;
      //   1474: ifnull -134 -> 1340
      //   1477: aload_2
      //   1478: astore 6
      //   1480: aload_3
      //   1481: astore 5
      //   1483: aload_0
      //   1484: getfield 70	com/taobao/munion/base/download/DownloadingService$b:j	Lcom/taobao/munion/base/download/DownloadingService$a;
      //   1487: aload_0
      //   1488: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   1491: aload 7
      //   1493: invokeinterface 537 3 0
      //   1498: goto -158 -> 1340
      //   1501: astore 4
      //   1503: aload_2
      //   1504: astore 6
      //   1506: aload_3
      //   1507: astore 5
      //   1509: aload_0
      //   1510: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1513: invokestatic 244	com/taobao/munion/base/download/DownloadingService:a	(Lcom/taobao/munion/base/download/DownloadingService;)Lcom/taobao/munion/base/download/f;
      //   1516: aload_0
      //   1517: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   1520: aload_0
      //   1521: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   1524: invokevirtual 247	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
      //   1527: aload_2
      //   1528: astore 6
      //   1530: aload_3
      //   1531: astore 5
      //   1533: aload 4
      //   1535: ldc_w 539
      //   1538: iconst_0
      //   1539: anewarray 97	java/lang/Object
      //   1542: invokestatic 477	com/taobao/munion/base/Log:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
      //   1545: aload_3
      //   1546: ifnull +7 -> 1553
      //   1549: aload_3
      //   1550: invokevirtual 469	java/io/InputStream:close	()V
      //   1553: aload_2
      //   1554: ifnull -875 -> 679
      //   1557: aload_2
      //   1558: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1561: return
      //   1562: astore_2
      //   1563: aload_2
      //   1564: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1567: return
      //   1568: astore_3
      //   1569: aload_3
      //   1570: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1573: aload_2
      //   1574: ifnull -895 -> 679
      //   1577: aload_2
      //   1578: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1581: return
      //   1582: astore_2
      //   1583: aload_2
      //   1584: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1587: return
      //   1588: astore_3
      //   1589: aload_2
      //   1590: ifnull +7 -> 1597
      //   1593: aload_2
      //   1594: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1597: aload_3
      //   1598: athrow
      //   1599: astore_2
      //   1600: aload_2
      //   1601: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1604: goto -7 -> 1597
      //   1607: astore 5
      //   1609: aload 5
      //   1611: invokevirtual 540	android/os/RemoteException:printStackTrace	()V
      //   1614: aload_0
      //   1615: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1618: invokestatic 244	com/taobao/munion/base/download/DownloadingService:a	(Lcom/taobao/munion/base/download/DownloadingService;)Lcom/taobao/munion/base/download/f;
      //   1621: aload_0
      //   1622: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   1625: aload_0
      //   1626: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   1629: invokevirtual 247	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
      //   1632: aload_0
      //   1633: aload 4
      //   1635: invokespecial 241	com/taobao/munion/base/download/DownloadingService$b:a	(Ljava/lang/Exception;)V
      //   1638: aload_0
      //   1639: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1642: invokestatic 482	com/taobao/munion/base/download/DownloadingService:d	(Lcom/taobao/munion/base/download/DownloadingService;)Landroid/os/Handler;
      //   1645: new 9	com/taobao/munion/base/download/DownloadingService$b$1
      //   1648: dup
      //   1649: aload_0
      //   1650: invokespecial 485	com/taobao/munion/base/download/DownloadingService$b$1:<init>	(Lcom/taobao/munion/base/download/DownloadingService$b;)V
      //   1653: invokevirtual 491	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   1656: pop
      //   1657: goto -839 -> 818
      //   1660: astore 5
      //   1662: aload_3
      //   1663: astore 4
      //   1665: aload 5
      //   1667: astore_3
      //   1668: aload 4
      //   1670: ifnull +8 -> 1678
      //   1673: aload 4
      //   1675: invokevirtual 469	java/io/InputStream:close	()V
      //   1678: aload_2
      //   1679: ifnull +7 -> 1686
      //   1682: aload_2
      //   1683: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1686: aload_3
      //   1687: athrow
      //   1688: astore 5
      //   1690: aload_0
      //   1691: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1694: invokestatic 244	com/taobao/munion/base/download/DownloadingService:a	(Lcom/taobao/munion/base/download/DownloadingService;)Lcom/taobao/munion/base/download/f;
      //   1697: aload_0
      //   1698: getfield 48	com/taobao/munion/base/download/DownloadingService$b:b	Landroid/content/Context;
      //   1701: aload_0
      //   1702: getfield 72	com/taobao/munion/base/download/DownloadingService$b:i	I
      //   1705: invokevirtual 247	com/taobao/munion/base/download/f:b	(Landroid/content/Context;I)V
      //   1708: aload_0
      //   1709: aload 4
      //   1711: invokespecial 241	com/taobao/munion/base/download/DownloadingService$b:a	(Ljava/lang/Exception;)V
      //   1714: aload_0
      //   1715: getfield 33	com/taobao/munion/base/download/DownloadingService$b:a	Lcom/taobao/munion/base/download/DownloadingService;
      //   1718: invokestatic 482	com/taobao/munion/base/download/DownloadingService:d	(Lcom/taobao/munion/base/download/DownloadingService;)Landroid/os/Handler;
      //   1721: new 9	com/taobao/munion/base/download/DownloadingService$b$1
      //   1724: dup
      //   1725: aload_0
      //   1726: invokespecial 485	com/taobao/munion/base/download/DownloadingService$b$1:<init>	(Lcom/taobao/munion/base/download/DownloadingService$b;)V
      //   1729: invokevirtual 491	android/os/Handler:post	(Ljava/lang/Runnable;)Z
      //   1732: pop
      //   1733: aload 5
      //   1735: athrow
      //   1736: aload_0
      //   1737: invokespecial 542	com/taobao/munion/base/download/DownloadingService$b:a	()V
      //   1740: goto -922 -> 818
      //   1743: astore_3
      //   1744: aload_3
      //   1745: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1748: aload_2
      //   1749: ifnull -1070 -> 679
      //   1752: aload_2
      //   1753: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1756: return
      //   1757: astore_2
      //   1758: aload_2
      //   1759: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1762: return
      //   1763: astore_3
      //   1764: aload_2
      //   1765: ifnull +7 -> 1772
      //   1768: aload_2
      //   1769: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1772: aload_3
      //   1773: athrow
      //   1774: astore_2
      //   1775: aload_2
      //   1776: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1779: goto -7 -> 1772
      //   1782: astore_3
      //   1783: aload_3
      //   1784: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1787: aload_2
      //   1788: ifnull -1109 -> 679
      //   1791: aload_2
      //   1792: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1795: return
      //   1796: astore_2
      //   1797: aload_2
      //   1798: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1801: return
      //   1802: astore_3
      //   1803: aload_2
      //   1804: ifnull +7 -> 1811
      //   1807: aload_2
      //   1808: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1811: aload_3
      //   1812: athrow
      //   1813: astore_2
      //   1814: aload_2
      //   1815: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1818: goto -7 -> 1811
      //   1821: astore_2
      //   1822: aload_2
      //   1823: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1826: goto -140 -> 1686
      //   1829: astore 4
      //   1831: aload 4
      //   1833: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1836: aload_2
      //   1837: ifnull -151 -> 1686
      //   1840: aload_2
      //   1841: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1844: goto -158 -> 1686
      //   1847: astore_2
      //   1848: aload_2
      //   1849: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1852: goto -166 -> 1686
      //   1855: astore_3
      //   1856: aload_2
      //   1857: ifnull +7 -> 1864
      //   1860: aload_2
      //   1861: invokevirtual 403	java/io/FileOutputStream:close	()V
      //   1864: aload_3
      //   1865: athrow
      //   1866: astore_2
      //   1867: aload_2
      //   1868: invokevirtual 494	java/io/IOException:printStackTrace	()V
      //   1871: goto -7 -> 1864
      //   1874: astore_3
      //   1875: aconst_null
      //   1876: astore_2
      //   1877: aconst_null
      //   1878: astore 4
      //   1880: goto -212 -> 1668
      //   1883: astore_3
      //   1884: aconst_null
      //   1885: astore 4
      //   1887: goto -219 -> 1668
      //   1890: astore 5
      //   1892: aload_3
      //   1893: astore_2
      //   1894: aconst_null
      //   1895: astore 4
      //   1897: aload 5
      //   1899: astore_3
      //   1900: goto -232 -> 1668
      //   1903: astore_3
      //   1904: aconst_null
      //   1905: astore 4
      //   1907: goto -239 -> 1668
      //   1910: astore_3
      //   1911: aload 6
      //   1913: astore_2
      //   1914: aload 5
      //   1916: astore 4
      //   1918: goto -250 -> 1668
      //   1921: astore 4
      //   1923: aconst_null
      //   1924: astore_2
      //   1925: aconst_null
      //   1926: astore_3
      //   1927: goto -424 -> 1503
      //   1930: astore 4
      //   1932: aconst_null
      //   1933: astore_3
      //   1934: goto -431 -> 1503
      //   1937: astore 4
      //   1939: aload_3
      //   1940: astore_2
      //   1941: aconst_null
      //   1942: astore_3
      //   1943: goto -440 -> 1503
      //   1946: astore 4
      //   1948: aconst_null
      //   1949: astore_3
      //   1950: goto -447 -> 1503
      //   1953: astore 4
      //   1955: aconst_null
      //   1956: astore 5
      //   1958: aload_3
      //   1959: astore_2
      //   1960: aload 5
      //   1962: astore_3
      //   1963: goto -1267 -> 696
      //   1966: astore 4
      //   1968: aconst_null
      //   1969: astore_3
      //   1970: goto -1274 -> 696
      //   1973: astore 4
      //   1975: aconst_null
      //   1976: astore 5
      //   1978: aload_3
      //   1979: astore_2
      //   1980: aload 5
      //   1982: astore_3
      //   1983: goto -1287 -> 696
      //   1986: iload 9
      //   1988: istore 8
      //   1990: goto -1538 -> 452
      //   1993: iconst_1
      //   1994: istore 8
      //   1996: goto -1445 -> 551
      //   1999: goto -1919 -> 80
      //   2002: astore 4
      //   2004: goto -1308 -> 696
      //
      // Exception table:
      //   from	to	target	type
      //   148	153	680	java/io/FileNotFoundException
      //   80	148	692	java/io/IOException
      //   148	153	692	java/io/IOException
      //   153	159	692	java/io/IOException
      //   681	689	692	java/io/IOException
      //   830	834	835	java/io/IOException
      //   675	679	1030	java/io/IOException
      //   667	671	1036	java/io/IOException
      //   1045	1049	1050	java/io/IOException
      //   667	671	1056	finally
      //   1037	1041	1056	finally
      //   1061	1065	1067	java/io/IOException
      //   1154	1158	1159	java/io/IOException
      //   1146	1150	1165	java/io/IOException
      //   1174	1178	1179	java/io/IOException
      //   1146	1150	1185	finally
      //   1166	1170	1185	finally
      //   1190	1194	1196	java/io/IOException
      //   1352	1356	1357	java/io/IOException
      //   176	186	1501	java/lang/Exception
      //   196	208	1501	java/lang/Exception
      //   214	225	1501	java/lang/Exception
      //   231	237	1501	java/lang/Exception
      //   243	256	1501	java/lang/Exception
      //   262	289	1501	java/lang/Exception
      //   295	323	1501	java/lang/Exception
      //   329	356	1501	java/lang/Exception
      //   362	369	1501	java/lang/Exception
      //   375	418	1501	java/lang/Exception
      //   424	449	1501	java/lang/Exception
      //   458	465	1501	java/lang/Exception
      //   471	479	1501	java/lang/Exception
      //   490	499	1501	java/lang/Exception
      //   505	517	1501	java/lang/Exception
      //   537	547	1501	java/lang/Exception
      //   557	561	1501	java/lang/Exception
      //   567	571	1501	java/lang/Exception
      //   577	585	1501	java/lang/Exception
      //   591	606	1501	java/lang/Exception
      //   617	628	1501	java/lang/Exception
      //   634	645	1501	java/lang/Exception
      //   651	663	1501	java/lang/Exception
      //   847	857	1501	java/lang/Exception
      //   863	873	1501	java/lang/Exception
      //   879	889	1501	java/lang/Exception
      //   895	906	1501	java/lang/Exception
      //   912	930	1501	java/lang/Exception
      //   951	958	1501	java/lang/Exception
      //   964	979	1501	java/lang/Exception
      //   985	991	1501	java/lang/Exception
      //   997	1023	1501	java/lang/Exception
      //   1081	1089	1501	java/lang/Exception
      //   1095	1122	1501	java/lang/Exception
      //   1128	1142	1501	java/lang/Exception
      //   1215	1245	1501	java/lang/Exception
      //   1251	1276	1501	java/lang/Exception
      //   1282	1300	1501	java/lang/Exception
      //   1306	1340	1501	java/lang/Exception
      //   1369	1383	1501	java/lang/Exception
      //   1389	1421	1501	java/lang/Exception
      //   1427	1437	1501	java/lang/Exception
      //   1443	1450	1501	java/lang/Exception
      //   1456	1464	1501	java/lang/Exception
      //   1470	1477	1501	java/lang/Exception
      //   1483	1498	1501	java/lang/Exception
      //   1557	1561	1562	java/io/IOException
      //   1344	1348	1568	java/io/IOException
      //   1577	1581	1582	java/io/IOException
      //   1344	1348	1588	finally
      //   1569	1573	1588	finally
      //   1593	1597	1599	java/io/IOException
      //   740	775	1607	android/os/RemoteException
      //   696	722	1660	finally
      //   728	736	1660	finally
      //   775	818	1660	finally
      //   1614	1657	1660	finally
      //   1690	1736	1660	finally
      //   1736	1740	1660	finally
      //   740	775	1688	finally
      //   1609	1614	1688	finally
      //   822	826	1743	java/io/IOException
      //   1752	1756	1757	java/io/IOException
      //   822	826	1763	finally
      //   1744	1748	1763	finally
      //   1768	1772	1774	java/io/IOException
      //   1549	1553	1782	java/io/IOException
      //   1791	1795	1796	java/io/IOException
      //   1549	1553	1802	finally
      //   1783	1787	1802	finally
      //   1807	1811	1813	java/io/IOException
      //   1682	1686	1821	java/io/IOException
      //   1673	1678	1829	java/io/IOException
      //   1840	1844	1847	java/io/IOException
      //   1673	1678	1855	finally
      //   1831	1836	1855	finally
      //   1860	1864	1866	java/io/IOException
      //   14	27	1874	finally
      //   27	65	1883	finally
      //   65	78	1890	finally
      //   80	148	1903	finally
      //   148	153	1903	finally
      //   153	159	1903	finally
      //   681	689	1903	finally
      //   176	186	1910	finally
      //   196	208	1910	finally
      //   214	225	1910	finally
      //   231	237	1910	finally
      //   243	256	1910	finally
      //   262	289	1910	finally
      //   295	323	1910	finally
      //   329	356	1910	finally
      //   362	369	1910	finally
      //   375	418	1910	finally
      //   424	449	1910	finally
      //   458	465	1910	finally
      //   471	479	1910	finally
      //   490	499	1910	finally
      //   505	517	1910	finally
      //   537	547	1910	finally
      //   557	561	1910	finally
      //   567	571	1910	finally
      //   577	585	1910	finally
      //   591	606	1910	finally
      //   617	628	1910	finally
      //   634	645	1910	finally
      //   651	663	1910	finally
      //   847	857	1910	finally
      //   863	873	1910	finally
      //   879	889	1910	finally
      //   895	906	1910	finally
      //   912	930	1910	finally
      //   951	958	1910	finally
      //   964	979	1910	finally
      //   985	991	1910	finally
      //   997	1023	1910	finally
      //   1081	1089	1910	finally
      //   1095	1122	1910	finally
      //   1128	1142	1910	finally
      //   1215	1245	1910	finally
      //   1251	1276	1910	finally
      //   1282	1300	1910	finally
      //   1306	1340	1910	finally
      //   1369	1383	1910	finally
      //   1389	1421	1910	finally
      //   1427	1437	1910	finally
      //   1443	1450	1910	finally
      //   1456	1464	1910	finally
      //   1470	1477	1910	finally
      //   1483	1498	1910	finally
      //   1509	1527	1910	finally
      //   1533	1545	1910	finally
      //   14	27	1921	java/lang/Exception
      //   27	65	1930	java/lang/Exception
      //   65	78	1937	java/lang/Exception
      //   80	148	1946	java/lang/Exception
      //   148	153	1946	java/lang/Exception
      //   153	159	1946	java/lang/Exception
      //   681	689	1946	java/lang/Exception
      //   14	27	1953	java/io/IOException
      //   27	65	1966	java/io/IOException
      //   65	78	1973	java/io/IOException
      //   176	186	2002	java/io/IOException
      //   196	208	2002	java/io/IOException
      //   214	225	2002	java/io/IOException
      //   231	237	2002	java/io/IOException
      //   243	256	2002	java/io/IOException
      //   262	289	2002	java/io/IOException
      //   295	323	2002	java/io/IOException
      //   329	356	2002	java/io/IOException
      //   362	369	2002	java/io/IOException
      //   375	418	2002	java/io/IOException
      //   424	449	2002	java/io/IOException
      //   458	465	2002	java/io/IOException
      //   471	479	2002	java/io/IOException
      //   490	499	2002	java/io/IOException
      //   505	517	2002	java/io/IOException
      //   537	547	2002	java/io/IOException
      //   557	561	2002	java/io/IOException
      //   567	571	2002	java/io/IOException
      //   577	585	2002	java/io/IOException
      //   591	606	2002	java/io/IOException
      //   617	628	2002	java/io/IOException
      //   634	645	2002	java/io/IOException
      //   651	663	2002	java/io/IOException
      //   847	857	2002	java/io/IOException
      //   863	873	2002	java/io/IOException
      //   879	889	2002	java/io/IOException
      //   895	906	2002	java/io/IOException
      //   912	930	2002	java/io/IOException
      //   951	958	2002	java/io/IOException
      //   964	979	2002	java/io/IOException
      //   985	991	2002	java/io/IOException
      //   997	1023	2002	java/io/IOException
      //   1081	1089	2002	java/io/IOException
      //   1095	1122	2002	java/io/IOException
      //   1128	1142	2002	java/io/IOException
      //   1215	1245	2002	java/io/IOException
      //   1251	1276	2002	java/io/IOException
      //   1282	1300	2002	java/io/IOException
      //   1306	1340	2002	java/io/IOException
      //   1369	1383	2002	java/io/IOException
      //   1389	1421	2002	java/io/IOException
      //   1427	1437	2002	java/io/IOException
      //   1443	1450	2002	java/io/IOException
      //   1456	1464	2002	java/io/IOException
      //   1470	1477	2002	java/io/IOException
      //   1483	1498	2002	java/io/IOException
    }

    private void b(int paramInt)
      throws RemoteException
    {
      try
      {
        if (DownloadingService.b().get(this.k) != null)
          ((Messenger)DownloadingService.b().get(this.k)).send(Message.obtain(null, 3, paramInt, 0));
        return;
      }
      catch (DeadObjectException localDeadObjectException)
      {
        Log.e(String.format("Service Client for downloading %1$15s is dead. Removing messenger from the service", new Object[] { this.k.c }), new Object[0]);
        DownloadingService.b().put(this.k, null);
      }
    }

    public void a(int paramInt)
    {
      this.h = paramInt;
    }

    public void run()
    {
      boolean bool = false;
      this.e = 0;
      try
      {
        if (this.j != null)
          this.j.a(this.i);
        if (this.f > 0L)
          bool = true;
        a(bool);
        if (DownloadingService.b().size() <= 0)
          DownloadingService.this.stopSelf();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  class c extends Handler
  {
    c()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      Log.d("IncomingHandler(msg.what:" + paramMessage.what + " msg.arg1:" + paramMessage.arg1 + " msg.arg2:" + paramMessage.arg2 + " msg.replyTo:" + paramMessage.replyTo, new Object[0]);
      switch (paramMessage.what)
      {
      default:
        super.handleMessage(paramMessage);
        return;
      case 4:
      }
      Object localObject = paramMessage.getData();
      Log.d("IncomingHandler(msg.getData():" + localObject, new Object[0]);
      localObject = c.a.a((Bundle)localObject);
      if (DownloadingService.a(DownloadingService.this).a((c.a)localObject, DownloadingService.a, paramMessage.replyTo))
      {
        Log.i(((c.a)localObject).c + " is already in downloading list. ", new Object[0]);
        int i = DownloadingService.a(DownloadingService.this).b((c.a)localObject);
        if ((i != -1) && (((f.c)DownloadingService.a().get(i)).a == null))
        {
          paramMessage = j.a(i, "continue");
          localObject = new Intent(DownloadingService.b(DownloadingService.this), DownloadingService.class);
          ((Intent)localObject).putExtra("com.taobao.broadcast.download.msg", paramMessage);
          DownloadingService.a(DownloadingService.this).a(DownloadingService.this, (Intent)localObject);
          return;
        }
        Toast.makeText(DownloadingService.b(DownloadingService.this), k.i, 0).show();
        localObject = Message.obtain();
        ((Message)localObject).what = 2;
        ((Message)localObject).arg1 = 2;
        ((Message)localObject).arg2 = 0;
        try
        {
          paramMessage.replyTo.send((Message)localObject);
          return;
        }
        catch (RemoteException paramMessage)
        {
          paramMessage.printStackTrace();
          return;
        }
      }
      if (f.b(DownloadingService.b(DownloadingService.this)))
      {
        DownloadingService.b().put(localObject, paramMessage.replyTo);
        Message localMessage = Message.obtain();
        localMessage.what = 1;
        localMessage.arg1 = 1;
        localMessage.arg2 = 0;
        try
        {
          paramMessage.replyTo.send(localMessage);
          DownloadingService.a(DownloadingService.this, (c.a)localObject);
          return;
        }
        catch (RemoteException paramMessage)
        {
          while (true)
            paramMessage.printStackTrace();
        }
      }
      Toast.makeText(DownloadingService.b(DownloadingService.this), k.d, 0).show();
      localObject = Message.obtain();
      ((Message)localObject).what = 2;
      ((Message)localObject).arg1 = 4;
      ((Message)localObject).arg2 = 0;
      try
      {
        paramMessage.replyTo.send((Message)localObject);
        return;
      }
      catch (RemoteException paramMessage)
      {
        paramMessage.printStackTrace();
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.download.DownloadingService
 * JD-Core Version:    0.6.2
 */