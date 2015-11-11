package com.umeng.message.proguard;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.android.agoo.client.AgooSettings;
import org.android.agoo.helper.a;
import org.android.agoo.net.mtop.MtopHttpChunked;
import org.android.agoo.net.mtop.MtopRequest;

public class aa extends W
{
  private static final String L = "Hb";
  private static final String M = "Dye";
  private static final String N = "X-AT";
  private static final String O = "X-COMMAND";
  private static final int Q = 456139;
  private static final int R = 456126;
  private static final String e = "MessagePush";
  private static final String f = "init_connect";
  private static final String g = "network_change_connect";
  private static final String h = "screen_on_connect";
  private static final String i = "error_connect";
  private static final String j = "heart_connect";
  private static final String k = "host_error_connect";
  private static final String l = "network_error_connect";
  private static final String m = "heart_connect_network_wap";
  private volatile long A = -1L;
  private volatile boolean B = false;
  private volatile b C;
  private volatile a D;
  private volatile MtopHttpChunked E = null;
  private volatile String F;
  private volatile af G;
  private volatile a H;
  private volatile int I = -1;
  private volatile AlarmManager J = null;
  private volatile String K = null;
  private BroadcastReceiver P = new ab(this);
  private volatile long n;
  private volatile long o;
  private volatile long p;
  private volatile long q;
  private volatile long r;
  private volatile long s;
  private volatile long t;
  private volatile long u;
  private volatile long v;
  private volatile long w;
  private volatile long x;
  private volatile long y;
  private volatile long z;

  public aa(Context paramContext, Z paramZ)
  {
    super(paramContext, paramZ);
    U.a(paramContext);
    this.G = new af(AgooSettings.isAgooTestMode(paramContext), super.d());
    this.n = this.G.c();
    this.o = this.G.d();
    this.v = this.G.e();
    this.t = this.G.f();
    this.q = this.G.h();
    this.p = this.G.g();
    this.r = this.G.j();
    this.s = this.G.l();
    this.u = this.G.k();
    this.z = this.G.i();
    this.y = this.G.b();
    this.w = this.G.m();
    this.x = this.G.a();
    this.F = paramContext.getPackageName();
    this.K = P.b(paramContext);
    this.J = ((AlarmManager)paramContext.getSystemService("alarm"));
    this.H = new a(paramContext, d());
    this.E = new MtopHttpChunked();
    this.E.setDefaultAppkey(this.b);
    this.E.setDefaultAppSecret(this.c);
    this.D = new a(null);
    this.C = new b();
  }

  private final void a(int paramInt, Map<String, String> paramMap)
  {
    switch (paramInt)
    {
    default:
      if (a(paramMap))
      {
        e("ERRCODE_AUTH_REJECT");
        return;
      }
      break;
    case 404:
      d(n(), "error_connect");
      return;
    case 408:
      d(n(), "error_connect");
      return;
    case 504:
      d(n(), "error_connect");
      return;
    }
    d(n(), "error_connect");
  }

  private void a(long paramLong, String paramString)
  {
    if (this.D == null)
    {
      Q.c("MessagePush", "open heart==[null]");
      return;
    }
    if (!TextUtils.isEmpty(paramString));
    try
    {
      int i1 = Integer.parseInt(paramString);
      if (i1 >= 20)
        this.x = (i1 * 1000);
      if (this.x <= this.w)
      {
        l1 = this.x * 2L;
        this.y = this.x;
        Q.c("MessagePush", "heart[" + this.I + "]heart--->[start checktime:" + this.y + "|timeout:" + l1 + "ms][current-thread-name:" + Thread.currentThread().getName() + "]");
        this.D.a(this.y, l1, paramLong);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        Q.e("MessagePush", "startHeart(" + paramString + ")", localThrowable);
        continue;
        this.y = (this.x / 2L);
        long l1 = ()(this.x * 1.1D);
      }
    }
  }

  private void a(Context paramContext)
  {
    this.x = this.G.a();
    this.E.addHeader("Hb", "" + this.x / 1000L);
  }

  private void a(boolean paramBoolean)
  {
    while (true)
    {
      try
      {
        if (!ag.a(this.a))
        {
          Q.c("MessagePush", "connectManager[network connectedOrConnecting failed]");
          d(n(), "network_error_connect");
          return;
        }
        if ((paramBoolean) && (this.H != null))
        {
          this.H.a(new ad(this));
          continue;
        }
      }
      finally
      {
      }
      k();
    }
  }

  private void a(char[] paramArrayOfChar, long paramLong)
  {
    try
    {
      if (paramArrayOfChar.length == 1)
      {
        Q.c("MessagePush", "connect[" + this.I + "]--->[onHeart()]");
        this.A = System.currentTimeMillis();
        return;
      }
      String str;
      int i1;
      if (paramArrayOfChar.length == 6)
      {
        Q.c("MessagePush", "cs[1]:[" + paramArrayOfChar[1] + "]cs[2]:[" + paramArrayOfChar[2] + "]|cs[3]:[" + paramArrayOfChar[3] + "]|cs[4]:[" + paramArrayOfChar[5] + "]");
        p();
        str = new String(paramArrayOfChar);
        i1 = (paramArrayOfChar[2] * 'Ϩ' + paramArrayOfChar[3] * 'd' + paramArrayOfChar[4] * '\n' + paramArrayOfChar[5]) * 1000;
      }
      switch (paramArrayOfChar[1])
      {
      case '\001':
        Q.c("MessagePush", "connect[" + this.I + "][nginx_lease_timeout][" + str + "][random time conntect][httpchunked connect time:" + (System.currentTimeMillis() - paramLong) + "ms]");
        U.a(this.a, paramLong, "onNginxError", "[nginx_lease_timeout][" + str + "]");
        d(this.o, "error_connect");
        return;
      case '\002':
        Q.c("MessagePush", "connect[" + this.I + "][nginx_connect_mtop_error][" + str + "][delay time: " + i1 + " ms connect]--httpchunked connect time:" + (System.currentTimeMillis() - paramLong) + "ms]");
        U.a(this.a, paramLong, "onNginxError", "[nginx_connect_mtop_error][" + str + "]");
        d(this.G.a(i1), "error_connect");
        return;
      case '':
        Q.c("MessagePush", "connect[" + this.I + "][nginx_connect_mtop_error][" + str + "] [random time conntect][httpchunked connect time:" + (System.currentTimeMillis() - paramLong) + "ms]");
        U.a(this.a, paramLong, "onNginxError", "[nginx_connect_mtop_error][" + str + "]");
        paramLong = this.o;
        d(i1 + paramLong, "error_connect");
        return;
      case '\003':
        Q.c("MessagePush", "connect[" + this.I + "][nginx_connect_mtop_error][" + str + "][delay time: " + i1 + " ms connect][httpchunked connect time:" + (System.currentTimeMillis() - paramLong) + "ms]");
        U.a(this.a, paramLong, "onNginxError", "[nginx_config_update][" + str + "]");
        d(this.G.a(i1), "error_connect");
        return;
      case '':
        Q.c("MessagePush", "connect[" + this.I + "][nginx_config_update][" + str + "][random time conntect connect][httpchunked connect time==" + (System.currentTimeMillis() - paramLong) + "ms]");
        U.a(this.a, paramLong, "onNginxError", "[nginx_config_update][" + str + "]");
        paramLong = this.o;
        d(i1 + paramLong, "error_connect");
        return;
      }
      return;
    }
    catch (Throwable paramArrayOfChar)
    {
    }
  }

  private boolean a(Map<String, String> paramMap)
  {
    if (paramMap != null)
    {
      paramMap = (String)paramMap.get("MTOP-ST");
      if (!TextUtils.isEmpty(paramMap))
        try
        {
          paramMap = URLDecoder.decode(paramMap, "utf-8");
          Q.c("MessagePush", "handlerError---->[MTOP-ST_ERROR_CODE][" + paramMap + "]");
          int i1 = paramMap.indexOf("ERRCODE_AUTH_REJECT");
          if (i1 != -1)
            return true;
        }
        catch (UnsupportedEncodingException paramMap)
        {
        }
    }
    return false;
  }

  private void b(long paramLong, String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (!TextUtils.equals("0", paramString)));
    try
    {
      int i1 = Integer.parseInt(paramString);
      if (i1 > 0)
        P.a(this.a, paramLong, i1);
      return;
    }
    catch (Throwable localThrowable)
    {
      Q.e("MessagePush", "handlerDye(" + paramString + ")" + localThrowable.getMessage());
    }
  }

  private void b(Context paramContext)
  {
    this.E.addHeader("X-COMMAND", "vote=" + this.K);
  }

  private void c(long paramLong, String paramString)
  {
    if ((this.C != null) && (this.B))
      this.C.a(paramLong, paramString, false);
  }

  private void d(long paramLong, String paramString)
  {
    if ((this.C != null) && (this.B))
      this.C.a(paramLong, paramString, true);
  }

  private void g(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      this.E.addHeader("X-AT", paramString);
  }

  private void h()
  {
    try
    {
      if (this.a != null)
      {
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.SCREEN_ON");
        localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.a.registerReceiver(this.P, localIntentFilter);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      Q.d("MessagePush", "initReceiver", localThrowable);
    }
  }

  private void h(String paramString)
  {
    if ((!TextUtils.isEmpty(paramString)) && (this.a != null))
    {
      Intent localIntent = new Intent();
      localIntent.setAction("org.agoo.android.intent.action.RECEIVE");
      localIntent.putExtra("x_command_type", true);
      localIntent.putExtra("x_command", paramString);
      this.a.sendBroadcast(localIntent);
    }
  }

  private void i()
  {
    try
    {
      if (this.a != null)
        this.a.unregisterReceiver(this.P);
      return;
    }
    catch (Throwable localThrowable)
    {
      Q.d("MessagePush", "unregisterReceiver", localThrowable);
    }
  }

  private void j()
  {
    if (this.C != null)
      b.a(this.C);
    h();
  }

  private void k()
  {
    a(this.a);
    b(this.a);
    MtopRequest localMtopRequest = new MtopRequest();
    localMtopRequest.setApi("mtop.push.msg.new");
    localMtopRequest.setV("6.0");
    localMtopRequest.setAppKey(super.b());
    localMtopRequest.setAppSecret(super.a());
    localMtopRequest.setTtId(super.c());
    localMtopRequest.setDeviceId(super.d());
    localMtopRequest.putParams("appPackage", this.a.getPackageName());
    localMtopRequest.putParams("agooSdkVersion", Long.valueOf(AgooSettings.getAgooReleaseTime()));
    localMtopRequest.putParams("deviceId", super.d());
    localMtopRequest.putParams("vote", this.K);
    this.E.connect(this.a, localMtopRequest, this.z, new ac(this));
  }

  private long l()
  {
    if (new ag(this.a).a())
    {
      Q.c("MessagePush", "current network [*wap]");
      this.u *= 2L;
      if (this.u >= this.s)
        this.u = this.s;
      return this.u;
    }
    this.u = this.G.k();
    return -1L;
  }

  private void m()
  {
    this.r = this.G.j();
    this.o = this.G.d();
  }

  private long n()
  {
    long l1 = l();
    if (l1 != -1L)
      return l1;
    this.r *= 2L;
    if (this.r >= this.s)
      this.r = this.s;
    return this.r;
  }

  private void o()
  {
    try
    {
      boolean bool = this.B;
      if (!bool);
      while (true)
      {
        return;
        this.B = false;
        Q.c("MessagePush", "[stopping]");
        m();
        if (this.E != null)
        {
          ay localay = this.E.readyState();
          if ((localay == ay.b) || (localay == ay.a))
          {
            Q.c("MessagePush", "[stop]:[close http chunked]");
            this.E.close();
            this.E = null;
          }
        }
        if (this.C != null)
        {
          this.C.a();
          this.C = null;
        }
        Q.c("MessagePush", "[stoped]");
      }
    }
    finally
    {
    }
  }

  private void p()
  {
    try
    {
      if (this.D != null)
        this.D.b();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  private void q()
  {
    try
    {
      p();
      this.D = null;
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void e()
  {
    try
    {
      boolean bool = this.B;
      if (bool);
      while (true)
      {
        return;
        this.B = true;
        Q.c("MessagePush", "MessagePush [starting]");
        j();
        d(this.n, "init_connect");
      }
    }
    finally
    {
    }
  }

  public boolean f()
  {
    try
    {
      ay localay1 = this.E.readyState();
      if (localay1 != ay.a)
      {
        ay localay2 = ay.b;
        if (localay1 != localay2);
      }
      else
      {
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
    }
    return this.B;
  }

  public void g()
  {
    try
    {
      boolean bool = this.B;
      if (!bool);
      while (true)
      {
        return;
        try
        {
          Q.c("MessagePush", "[destroying]");
          q();
          o();
          i();
          U.b(this.a);
          this.B = false;
          Q.c("MessagePush", "[destroyed]");
        }
        catch (Throwable localThrowable)
        {
        }
      }
    }
    finally
    {
    }
  }

  private class a extends BroadcastReceiver
  {
    private static final String b = "agoo_action_heart";
    private IntentFilter c = null;
    private PendingIntent d = null;
    private Intent e = null;
    private volatile boolean f = false;
    private volatile long g = -1L;
    private long h = -1L;

    private a()
    {
    }

    public void a()
    {
      try
      {
        this.c = new IntentFilter();
        this.c.addAction("agoo_action_heart");
        aa.this.a.registerReceiver(this, this.c);
        this.e = new Intent("agoo_action_heart");
        this.e.setPackage(aa.l(aa.this));
        this.d = PendingIntent.getBroadcast(aa.this.a, 456126, this.e, 268435456);
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }

    public void a(long paramLong1, long paramLong2, long paramLong3)
    {
      try
      {
        boolean bool = this.f;
        if (bool);
        while (true)
        {
          return;
          this.f = true;
          this.h = paramLong3;
          this.g = paramLong2;
          aa.a(aa.this, -1L);
          a();
          aa.n(aa.this).cancel(this.d);
          aa.n(aa.this).setRepeating(1, System.currentTimeMillis(), paramLong1, this.d);
        }
      }
      finally
      {
      }
    }

    public void b()
    {
      try
      {
        boolean bool = this.f;
        if (!bool);
        while (true)
        {
          return;
          aa.a(aa.this, -1L);
          this.f = false;
          Q.c("MessagePush", "connect[" + aa.d(aa.this) + "]heart--->[stopping]");
          if (aa.n(aa.this) != null)
            aa.n(aa.this).cancel(this.d);
          if (this.d != null)
            this.d.cancel();
          if (aa.this.a != null)
            aa.this.a.unregisterReceiver(this);
          Q.c("MessagePush", "connect[" + aa.d(aa.this) + "]heart--->[stoped]");
        }
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Q.e("MessagePush", "heart", localThrowable);
      }
      finally
      {
      }
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      aT.a(new ae(this, paramIntent, paramContext));
    }
  }

  private class b extends BroadcastReceiver
    implements Handler.Callback
  {
    private static final String b = "agoo_action_re_connect";
    private static final int c = 10000;
    private static final int d = 2000;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    private static final int h = 3;
    private static final int i = -1;
    private volatile IntentFilter j = null;
    private volatile PendingIntent k = null;
    private volatile Intent l = null;
    private volatile Handler m = null;
    private volatile HandlerThread n = null;
    private volatile boolean o = false;
    private volatile boolean p = false;
    private volatile long q = -1L;
    private volatile String r;
    private Lock s = new ReentrantLock();

    public b()
    {
      b();
    }

    private boolean a(int paramInt, long paramLong)
    {
      try
      {
        ay localay = aa.a(aa.this).readyState();
        if (localay == ay.a)
        {
          Q.c("MessagePush", "connect[" + paramInt + "][state:" + localay + "][interval:" + paramLong + "]");
          return false;
        }
        if (localay == ay.b)
          aa.a(aa.this).disconnect(paramInt);
        return true;
      }
      catch (Throwable localThrowable)
      {
      }
      return false;
    }

    private void b()
    {
      try
      {
        this.j = new IntentFilter();
        this.j.addAction("agoo_action_re_connect");
        aa.this.a.registerReceiver(this, this.j);
        d();
        this.o = false;
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }

    private void c()
    {
      try
      {
        if (this.k != null)
        {
          this.k.cancel();
          aa.n(aa.this).cancel(this.k);
        }
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }

    private void d()
    {
      try
      {
        this.n = new HandlerThread("reconnect");
        this.n.start();
        this.m = new Handler(this.n.getLooper(), this);
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }

    public void a()
    {
      try
      {
        if (aa.this.a != null)
          aa.this.a.unregisterReceiver(this);
        if (this.k != null)
          aa.n(aa.this).cancel(this.k);
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
          Q.d("MessagePush", "reconnect stop", localThrowable);
      }
      finally
      {
      }
    }

    public void a(long paramLong)
    {
      try
      {
        this.s.lock();
        Q.c("MessagePush", "handleConnect[interval:" + paramLong + "][state:" + this.r + "]");
        this.l = new Intent("agoo_action_re_connect");
        this.l.setPackage(aa.l(aa.this));
        if (this.k != null)
        {
          this.k.cancel();
          aa.n(aa.this).cancel(this.k);
        }
        aa.f(aa.this);
        boolean bool = a(aa.d(aa.this), paramLong);
        if (!bool)
          return;
        this.k = PendingIntent.getBroadcast(aa.this.a, 456139, this.l, 134217728);
        aa.n(aa.this).set(1, System.currentTimeMillis() + paramLong, this.k);
        return;
      }
      catch (Throwable localThrowable)
      {
      }
      finally
      {
        this.s.unlock();
      }
    }

    public void a(long paramLong, String paramString, boolean paramBoolean)
    {
      try
      {
        if (this.m != null)
          if (!paramBoolean)
          {
            if (!this.o)
            {
              this.o = true;
              Q.c("MessagePush", "tryConnect[interval:" + paramLong + "][state:" + paramString + "]");
              if ((paramLong < this.q) && (this.q != -1L))
                this.m.sendEmptyMessage(3);
              this.q = paramLong;
              this.p = true;
              this.r = paramString;
              this.m.sendEmptyMessage(0);
              this.m.sendEmptyMessageDelayed(1, 10000L);
            }
          }
          else
          {
            Q.c("MessagePush", "forceConnect[interval:" + paramLong + "][state:" + paramString + "]");
            if ((paramLong < this.q) && (this.q != -1L))
              this.m.sendEmptyMessage(3);
            this.q = paramLong;
            this.p = true;
            this.r = paramString;
            this.m.sendEmptyMessageDelayed(-1, 2000L);
            return;
          }
      }
      catch (Throwable paramString)
      {
      }
    }

    public boolean handleMessage(Message paramMessage)
    {
      try
      {
        switch (paramMessage.what)
        {
        case 0:
          a(this.q);
          break;
        case 1:
          this.o = false;
          break;
        case 2:
          aa.a(aa.this, this.p);
          break;
        case 3:
          c();
          break;
        case -1:
          a(this.q);
          break;
        }
        label91: return true;
      }
      catch (Throwable paramMessage)
      {
        break label91;
      }
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        if ((TextUtils.equals("agoo_action_re_connect", paramIntent.getAction())) && (this.m != null))
          this.m.sendEmptyMessage(2);
        return;
      }
      catch (Throwable paramContext)
      {
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.proguard.aa
 * JD-Core Version:    0.6.2
 */