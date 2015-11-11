package org.android.agoo.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import com.umeng.message.proguard.P;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.U;
import com.umeng.message.proguard.W;
import com.umeng.message.proguard.Z;
import com.umeng.message.proguard.aM;
import com.umeng.message.proguard.aR;
import com.umeng.message.proguard.aT;
import com.umeng.message.proguard.aa;
import java.util.Iterator;
import java.util.Random;
import org.android.agoo.client.BaseRegistrar;
import org.android.agoo.client.IntentHelper;
import org.android.agoo.client.IppFacade;
import org.android.agoo.proc.b;
import org.json.JSONException;
import org.json.JSONObject;

public class AgooService extends b
  implements Z
{
  private static final String b = "AgooService";
  private static final String c = "head";
  private volatile W d;
  private volatile long e;
  private volatile ReElection f = null;
  private volatile AlarmManager g = null;
  private volatile String h = null;
  private volatile String i = null;
  private volatile String j = null;
  private volatile String k = null;
  private volatile String l = null;
  private volatile String m = null;
  private final IMessageService.Stub n = new AgooService.1(this);

  private void a(String paramString, Bundle paramBundle)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("org.agoo.android.intent.action.RECEIVE");
      localIntent.setPackage(paramString);
      localIntent.putExtras(paramBundle);
      localIntent.putExtra("message_source", "apoll");
      localIntent.addFlags(32);
      this.a.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramString)
    {
      Q.d("AgooService", "handleMessage", paramString);
    }
  }

  private void a(String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = IntentHelper.createComandIntent(this.a, "error");
      localIntent.setPackage(paramString1);
      localIntent.putExtra("error", paramString2);
      this.a.sendBroadcast(localIntent);
      return;
    }
    catch (Throwable paramString1)
    {
      Q.d("AgooService", "handleError", paramString1);
    }
  }

  private boolean f()
  {
    try
    {
      if (this.a == null)
      {
        Q.c("AgooService", "mContext == null");
        return false;
      }
      this.h = P.n(this.a);
      this.k = P.q(this.a);
      this.i = P.p(this.a);
      this.j = P.o(this.a);
      if (TextUtils.isEmpty(this.k))
      {
        a(this.l, "ERROR_DEVICETOKEN_NULL");
        return false;
      }
      if (this.d == null)
        this.d = new aa(this.a, this);
      this.d.b(this.h);
      this.d.a(this.i);
      this.d.c(this.j);
      this.d.d(this.k);
      return true;
    }
    catch (Throwable localThrowable)
    {
    }
    return false;
  }

  private boolean g()
  {
    try
    {
      if (!BaseRegistrar.isRegistered(this.a))
        return true;
      String str = P.d(this.a);
      if (TextUtils.isEmpty(str))
      {
        Q.c("AgooService", "[currentSudoPack==null]");
        return true;
      }
      if (!TextUtils.equals(this.l, str))
      {
        Q.c("AgooService", "[currentSudoPack(" + str + ")!=appPackage(" + this.l + ")]");
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
    }
    return false;
  }

  private void h()
  {
    try
    {
      if (this.d != null)
        this.d.e();
      if (this.f != null)
        this.f.start();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  protected void a(Intent paramIntent, int paramInt1, int paramInt2)
  {
    try
    {
      if (!f())
      {
        a();
        return;
      }
      String str1 = paramIntent.getAction();
      String str2 = IntentHelper.getAgooStart(this.a);
      Q.c("AgooService", "action [" + str1 + "]");
      if (TextUtils.equals(str1, str2))
      {
        paramIntent = paramIntent.getStringExtra("method");
        Q.c("AgooService", "startCommand method--->[" + paramIntent + "]");
        if ((!TextUtils.isEmpty(paramIntent)) && (TextUtils.equals(paramIntent, "start")))
        {
          h();
          return;
        }
        a();
      }
      return;
    }
    catch (Throwable paramIntent)
    {
    }
  }

  protected void b()
  {
    Q.c("AgooService", "create--->[current-thread-name:" + Thread.currentThread().getName() + "]");
    try
    {
      this.l = this.a.getPackageName();
      this.g = ((AlarmManager)getSystemService("alarm"));
      if (Build.VERSION.SDK_INT < 18)
        startForeground(-1469, new Notification());
      new Handler(Looper.getMainLooper());
      this.e = System.currentTimeMillis();
      this.f = new ReElection();
      this.d = new aa(this.a, this);
      aR.a(this.a);
      IppFacade.performProtectOnlyOnce(this.a);
      return;
    }
    catch (Throwable localThrowable)
    {
      Q.d("AgooService", "create", localThrowable);
    }
  }

  protected void c()
  {
    try
    {
      Q.c("AgooService", "AgooService[current-thread-name:" + Thread.currentThread().getName() + "]");
      Q.c("AgooService", "AgooService destroying");
      U.a(this.a, this.e);
      if (this.d != null)
        this.d.g();
      if (this.f != null)
        this.f.destory();
      Q.c("AgooService", "AgooService destroyed");
      return;
    }
    catch (Throwable localThrowable)
    {
      Q.d("AgooService", "destroy", localThrowable);
    }
  }

  protected void d()
  {
    try
    {
      U.d(this.a);
      if (g())
      {
        a(this.l, "ERROR_NEED_ELECTION");
        a();
        return;
      }
      if (!f())
      {
        a();
        return;
      }
      h();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  protected void e()
  {
    try
    {
      U.d(this.a);
      if (g())
      {
        a(this.l, "ERROR_NEED_ELECTION");
        a();
        return;
      }
      if (!f())
      {
        a();
        return;
      }
      h();
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public final IBinder onBind(Intent paramIntent)
  {
    try
    {
      String str = paramIntent.getAction();
      Q.c("AgooService", "onBind:[" + str + "]");
      if ((!TextUtils.isEmpty(str)) && (TextUtils.equals(str, "org.agoo.android.intent.action.PING")))
      {
        this.m = paramIntent.getPackage();
        paramIntent = this.n;
        return paramIntent;
      }
    }
    catch (Throwable paramIntent)
    {
    }
    return null;
  }

  public void onHandleError(String paramString)
  {
    a(this.a.getPackageName(), paramString);
  }

  public final void onHandleMessage(String paramString)
  {
    String str1;
    String str2;
    String str3;
    try
    {
      if (TextUtils.isEmpty(paramString))
        return;
      Q.c("AgooService", "onHandleMessage--->[current-thread-name:" + Thread.currentThread().getName() + "]");
      Bundle localBundle = new Bundle();
      localJSONObject = new JSONObject(paramString);
      str1 = localJSONObject.getString("pack");
      localObject = localJSONObject.getString("id");
      str2 = localJSONObject.getString("type");
      str3 = localJSONObject.getString("body");
      if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty((CharSequence)localObject)) || (TextUtils.isEmpty(str3)))
      {
        U.d(this.a, paramString);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      U.d(this.a, paramString);
      Q.d("AgooService", "JSONException parse errormessage content[" + paramString + "]", localThrowable);
      return;
    }
    localThrowable.putString("id", (String)localObject);
    localThrowable.putString("type", str2);
    localThrowable.putString("body", str3);
    JSONObject localJSONObject = localJSONObject.getJSONObject("head");
    Object localObject = localJSONObject.keys();
    while (true)
    {
      boolean bool = ((Iterator)localObject).hasNext();
      if (!bool)
        break;
      try
      {
        str2 = (String)((Iterator)localObject).next();
        if (!TextUtils.isEmpty(str2))
        {
          str3 = localJSONObject.getString(str2);
          if (!TextUtils.isEmpty(str3))
            localThrowable.putString(str2, str3);
        }
      }
      catch (JSONException localJSONException)
      {
        Q.e("AgooService", "JSONException parse error[message header]", localJSONException);
      }
    }
    a(str1, localThrowable);
  }

  private class ReElection extends BroadcastReceiver
  {
    private static final int b = 45613913;
    private static final String c = "agoo_action_re_election";
    private IntentFilter d = null;
    private PendingIntent e = null;
    private Intent f = null;
    private volatile boolean g = false;

    public ReElection()
    {
      try
      {
        this.g = false;
        return;
      }
      catch (Throwable this$1)
      {
      }
    }

    private void a()
    {
      try
      {
        if (this.g)
          return;
        this.d = new IntentFilter();
        this.d.addAction("agoo_action_re_election");
        AgooService.h(AgooService.this).registerReceiver(this, this.d);
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }

    public void destory()
    {
      try
      {
        if (AgooService.l(AgooService.this) != null)
          AgooService.this.unregisterReceiver(this);
        if (this.e != null)
          this.e.cancel();
        if (AgooService.j(AgooService.this) != null)
          AgooService.j(AgooService.this).cancel(this.e);
        this.e = null;
        AgooService.a(AgooService.this, null);
        return;
      }
      catch (Throwable localThrowable)
      {
      }
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      try
      {
        if (TextUtils.equals("agoo_action_re_election", paramIntent.getAction()))
          aT.a(new AgooService.ReElection.1(this, paramContext));
        return;
      }
      catch (Throwable paramContext)
      {
        Q.e("AgooService", "onReceive", paramContext);
      }
    }

    public void start()
    {
      while (true)
      {
        long l2;
        long l3;
        try
        {
          boolean bool = this.g;
          if (bool)
            return;
          this.g = true;
          a();
          this.f = new Intent("agoo_action_re_election");
          this.f.setPackage(AgooService.c(AgooService.this));
          int i = new Random().nextInt(120);
          l2 = System.currentTimeMillis();
          l3 = i + 1320;
          l1 = P.c(AgooService.i(AgooService.this));
          Q.c("AgooService", "re_election_start[timeout:" + l1 + "]");
          if (l1 > System.currentTimeMillis() + 1800000L)
          {
            if (this.e != null)
            {
              this.e.cancel();
              AgooService.j(AgooService.this).cancel(this.e);
            }
            this.e = PendingIntent.getBroadcast(AgooService.k(AgooService.this), 45613913, this.f, 134217728);
            Q.c("AgooService", "election next time[current-thread-name:" + Thread.currentThread().getName() + "][" + aM.a(l1) + "] ");
            AgooService.j(AgooService.this).set(1, l1, this.e);
            continue;
          }
        }
        catch (Throwable localThrowable)
        {
          Q.e("AgooService", "ReElection start", localThrowable);
          continue;
        }
        finally
        {
        }
        long l1 = l2 + l3 * 60L * 1000L;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.AgooService
 * JD-Core Version:    0.6.2
 */