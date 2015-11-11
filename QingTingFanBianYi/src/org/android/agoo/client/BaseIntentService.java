package org.android.agoo.client;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.SystemClock;
import android.text.TextUtils;
import com.umeng.message.proguard.P;
import com.umeng.message.proguard.Q;
import com.umeng.message.proguard.U;
import com.umeng.message.proguard.aI;
import com.umeng.message.proguard.aK;
import com.umeng.message.proguard.aO;
import com.umeng.message.proguard.aR;
import com.umeng.message.proguard.aS;
import com.umeng.message.proguard.aT;
import com.umeng.message.proguard.ag;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import org.android.agoo.net.mtop.IMtopSynClient;
import org.android.agoo.net.mtop.MtopRequest;
import org.android.agoo.net.mtop.MtopSyncClientV3;
import org.android.agoo.net.mtop.Result;
import org.android.agoo.service.AgooService;
import org.android.agoo.service.ElectionService.ElectionResult;
import org.android.agoo.service.IElectionService;
import org.android.agoo.service.IMessageService;
import org.json.JSONObject;

public abstract class BaseIntentService extends AgooIntentService
{
  private static final String a = "SERVICE_NOT_AVAILABLE";
  private static final String f = "report";
  private static final String g = "time";
  private static final String h = "BaseIntentService";
  private static final String i = "AGOO_LIB";
  private static volatile PowerManager.WakeLock j;
  private static final Object k = BaseIntentService.class;
  private static final Random l = new Random();
  private static final int m = 3600000;
  private volatile IMtopSynClient b = null;
  private volatile String c;
  private volatile String d;
  private volatile String e;
  private volatile IElectionService n = null;
  private ServiceConnection o = new BaseIntentService.1(this);
  private volatile boolean p = false;
  private volatile IMessageService q = null;
  private ServiceConnection r = new BaseIntentService.3(this);

  protected BaseIntentService()
  {
    super("AgooDynamicSenderIds");
    a();
    setIntentRedelivery(false);
  }

  private void a()
  {
    this.b = new MtopSyncClientV3();
  }

  private void a(Context paramContext, Intent paramIntent)
  {
    if (!BaseRegistrar.isRegistered(paramContext))
    {
      Q.c("BaseIntentService", "deviceToken is null--->[re-registration]");
      k(paramContext, paramIntent);
      return;
    }
    if (!ag.a(paramContext))
    {
      Q.c("BaseIntentService", "connectManager[network connectedOrConnecting failed]");
      return;
    }
    MessageService.getSingleton(paramContext).reloadMessageAtTime();
    paramIntent = paramContext.getPackageName();
    String str = P.d(paramContext);
    if ((!TextUtils.isEmpty(paramIntent)) && (!TextUtils.isEmpty(str)) && (TextUtils.equals(paramIntent, str)))
    {
      b(paramContext, paramContext.getPackageName(), str);
      return;
    }
    h(paramContext);
  }

  static void a(Context paramContext, Intent paramIntent, String paramString)
  {
    try
    {
      synchronized (k)
      {
        if (j == null)
        {
          j = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "AGOO_LIB");
          j.setReferenceCounted(false);
        }
        j.acquire(5000L);
        paramIntent.setClassName(paramContext, paramString);
        paramContext.startService(paramIntent);
        return;
      }
    }
    catch (Throwable paramContext)
    {
      Q.d("BaseIntentService", "runIntentInService--Throwable", paramContext);
    }
  }

  private void a(Context paramContext, String paramString)
  {
    try
    {
      if ("SERVICE_NOT_AVAILABLE".equals(paramString))
      {
        if (onRecoverableError(paramContext, paramString))
        {
          int i1 = P.k(paramContext);
          int i2 = i1 / 2;
          i2 = l.nextInt(i1) + i2;
          Q.c("BaseIntentService", "registration retry--->[nextAttempt:" + i2 + "|backoffTimeMs:" + i1 + "]");
          paramString = IntentHelper.createComandIntent(paramContext, "register_retry");
          paramString.setPackage(paramContext.getPackageName());
          paramString = PendingIntent.getBroadcast(paramContext, 0, paramString, 0);
          ((AlarmManager)paramContext.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + i2, paramString);
          if (i1 < 3600000)
            P.a(paramContext, i1 * 2);
        }
        else
        {
          Q.c("BaseIntentService", "Not retrying failed operation");
        }
      }
      else
        onError(paramContext, paramString);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private void a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramString2 = new JSONObject(paramString2);
      Q.c("BaseIntentService", "handleRegisterSuccess--->[" + paramString2.toString() + "]");
      P.j(paramContext);
      P.b(paramContext, paramString1);
      P.t(paramContext);
      b(paramContext);
      U.g(paramContext);
      return;
    }
    catch (Throwable paramString1)
    {
      a(paramContext, "SERVICE_NOT_AVAILABLE");
      U.h(paramContext, "data_parse_error");
    }
  }

  private void a(Context paramContext, String paramString, String[] paramArrayOfString)
  {
    if (TextUtils.equals(paramString, "channel"))
      b(paramContext, paramString, paramArrayOfString);
  }

  private boolean a(Context paramContext)
  {
    String str1 = P.n(paramContext);
    String str2 = P.o(paramContext);
    if ((TextUtils.isEmpty(str1)) || (TextUtils.isEmpty(str2)));
    do
    {
      return false;
      this.c = str1;
      this.d = str2;
      this.b.setDefaultAppkey(str1);
      str1 = P.p(paramContext);
    }
    while ((TextUtils.isEmpty(str1)) && (!AgooSettings.isAgooSoSecurityMode(paramContext)));
    this.e = str1;
    this.b.setDefaultAppSecret(str1);
    return true;
  }

  private void b(Context paramContext)
  {
    Intent localIntent = IntentHelper.createComandIntent(paramContext, "registration");
    localIntent.setPackage(paramContext.getPackageName());
    paramContext.sendBroadcast(localIntent);
  }

  private void b(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("id");
    MessageService.getSingleton(paramContext).notice(str);
    onMessage(paramContext, paramIntent);
  }

  private void b(Context paramContext, String paramString)
  {
    if ((BaseRegistrar.isRegistered(paramContext)) && (a(paramContext)))
    {
      String str = BaseRegistrar.getRegistrationId(paramContext);
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi("mtop.push.device.uninstall");
      localMtopRequest.setV("4.0");
      localMtopRequest.setTtId(this.d);
      localMtopRequest.setDeviceId(str);
      localMtopRequest.putParams("app_version", aI.a(paramContext));
      localMtopRequest.putParams("sdk_version", Long.valueOf(AgooSettings.getAgooReleaseTime()));
      localMtopRequest.putParams("app_pack", paramString);
      this.b.setBaseUrl(AgooSettings.getPullUrl(paramContext));
      paramContext = this.b.getV3(paramContext, localMtopRequest);
      Q.c("BaseIntentService", "uninstall--->[result:" + paramContext.getData() + "]");
    }
  }

  private void b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      aT.a(new BaseIntentService.2(this, paramString2, paramString1, paramContext));
      return;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  private void b(Context paramContext, String paramString, String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (2 <= paramArrayOfString.length))
      try
      {
        if ((TextUtils.equals(paramArrayOfString[0], "multiplex")) && (!P.w(paramContext)))
        {
          P.a(paramContext, true, -1L);
          f(paramContext);
          return;
        }
        long l1 = Long.parseLong(paramArrayOfString[1]);
        if ((TextUtils.equals(paramArrayOfString[0], "single")) && (P.w(paramContext)) && (l1 >= System.currentTimeMillis() + 300000L))
        {
          P.a(paramContext, false, l1);
          if (b())
          {
            Q.c("BaseIntentService", "enabledService---->[" + getAgooService() + "]");
            aR.b(paramContext, getAgooService());
          }
          aS.a(paramContext, getAgooService());
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Q.d("BaseIntentService", "commandByChannel", paramContext);
      }
  }

  private boolean b()
  {
    return (getAgooService() != null) && (TextUtils.equals(getAgooService().getSuperclass().getName(), AgooService.class.getName()));
  }

  private void c()
  {
    Context localContext = getApplicationContext();
    String str = P.d(localContext);
    if (TextUtils.isEmpty(str))
    {
      Q.c("BaseIntentService", "onPingMessage:[currentPack==null][retry election]");
      f(localContext);
      return;
    }
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("org.agoo.android.intent.action.PING");
      localIntent.setPackage(str);
      localContext.bindService(localIntent, this.r, 1);
      return;
    }
    catch (Throwable localThrowable)
    {
      Q.d("BaseIntentService", "onPingMessage", localThrowable);
    }
  }

  private void c(Context paramContext)
  {
    if ((BaseRegistrar.isRegistered(paramContext)) && (a(paramContext)))
    {
      String str = BaseRegistrar.getRegistrationId(paramContext);
      MtopRequest localMtopRequest = new MtopRequest();
      localMtopRequest.setApi("mtop.push.device.unregister");
      localMtopRequest.setV("4.0");
      localMtopRequest.setTtId(this.d);
      localMtopRequest.setDeviceId(str);
      localMtopRequest.putParams("app_version", aI.a(paramContext));
      localMtopRequest.putParams("sdk_version", Long.valueOf(AgooSettings.getAgooReleaseTime()));
      localMtopRequest.putParams("app_pack", paramContext.getPackageName());
      this.b.setBaseUrl(AgooSettings.getPullUrl(paramContext));
      paramContext = this.b.getV3(paramContext, localMtopRequest);
      Q.c("BaseIntentService", "unregister--->[server result:" + paramContext.getData() + "]");
    }
  }

  private void c(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramIntent.getStringExtra("x_command").split(";");
    int i2 = paramIntent.length;
    int i1 = 0;
    if (i1 < i2)
    {
      Object localObject = paramIntent[i1];
      if (TextUtils.isEmpty((CharSequence)localObject));
      while (true)
      {
        i1 += 1;
        break;
        localObject = ((String)localObject).split("=");
        if ((localObject != null) && (localObject.length == 2) && (!TextUtils.isEmpty(localObject[0])) && (!TextUtils.isEmpty(localObject[1])))
        {
          String[] arrayOfString = localObject[1].split(",");
          if (arrayOfString != null)
            a(paramContext, localObject[0], arrayOfString);
        }
      }
    }
  }

  private void d(Context paramContext)
  {
    String str = DeviceService.getRegistrationId(paramContext, this.c, this.e, this.d);
    if (TextUtils.isEmpty(str))
    {
      Q.c("BaseIntentService", "doRegister---deviceId---->[null]");
      a(paramContext, "SERVICE_NOT_AVAILABLE");
      return;
    }
    Object localObject = new MtopRequest();
    ((MtopRequest)localObject).setApi("mtop.push.device.register");
    ((MtopRequest)localObject).setV("4.0");
    ((MtopRequest)localObject).setTtId(this.d);
    ((MtopRequest)localObject).setDeviceId(str);
    ((MtopRequest)localObject).putParams("device_id", str);
    ((MtopRequest)localObject).putParams("app_version", aI.a(paramContext));
    ((MtopRequest)localObject).putParams("sdk_version", Long.valueOf(AgooSettings.getAgooReleaseTime()));
    this.b.setBaseUrl(AgooSettings.getPullUrl(paramContext));
    localObject = this.b.getV3(paramContext, (MtopRequest)localObject);
    Q.c("BaseIntentService", "register--->[result:" + ((Result)localObject).getData() + "]");
    if (((Result)localObject).isSuccess())
    {
      a(paramContext, str, ((Result)localObject).getData());
      return;
    }
    str = ((Result)localObject).getRetCode();
    if (!TextUtils.isEmpty(str))
    {
      U.h(paramContext, str);
      if (str.indexOf("ERRCODE_AUTH_REJECT") != -1)
      {
        Q.c("BaseIntentService", "doRegister---->[" + str + "]");
        P.s(paramContext);
        return;
      }
    }
    a(paramContext, "SERVICE_NOT_AVAILABLE");
  }

  private void d(Context paramContext, Intent paramIntent)
  {
    h(paramContext);
    if (paramIntent.getBooleanExtra("x_command_type", false))
      c(paramContext, paramIntent);
    while (true)
    {
      return;
      if (!BaseRegistrar.isRegistered(paramContext))
      {
        Q.c("BaseIntentService", "handleRemoteMessage[deviceToken==null]");
        return;
      }
      String str1 = paramIntent.getStringExtra("id");
      Object localObject3 = paramIntent.getStringExtra("body");
      String str2 = paramIntent.getStringExtra("type");
      if (TextUtils.isEmpty((CharSequence)localObject3))
      {
        Q.c("BaseIntentService", "handleMessage--->[null]");
        U.e(paramContext, str1);
        return;
      }
      Object localObject1 = localObject3;
      String str3;
      try
      {
        str3 = paramIntent.getStringExtra("encrypted");
        localObject1 = localObject3;
        localObject2 = localObject3;
        if (!TextUtils.equals("1", str3))
          break label203;
        localObject1 = localObject3;
        localObject2 = aO.a(BaseRegistrar.getRegistrationId(paramContext), (String)localObject3, 0);
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject1 = localObject2;
          U.b(paramContext, str1, (String)localObject2);
          return;
        }
      }
      catch (Throwable paramContext)
      {
        Q.d("BaseIntentService", "encrypt--aesdecrypt[" + (String)localObject1 + "]", paramContext);
        return;
      }
      localObject1 = localObject2;
      paramIntent.putExtra("body", (String)localObject2);
      label203: localObject1 = localObject2;
      localObject3 = localObject2;
      if (TextUtils.equals("2", str3))
      {
        localObject1 = localObject2;
        localObject3 = aO.a(BaseRegistrar.getRegistrationId(paramContext), (String)localObject2, 1);
        localObject1 = localObject3;
        if (TextUtils.isEmpty((CharSequence)localObject3))
        {
          localObject1 = localObject3;
          U.b(paramContext, str1, (String)localObject3);
          return;
        }
        localObject1 = localObject3;
        paramIntent.putExtra("body", (String)localObject3);
      }
      localObject1 = localObject3;
      Object localObject2 = localObject3;
      if (TextUtils.equals("3", str3))
      {
        localObject1 = localObject3;
        localObject2 = aO.a(BaseRegistrar.getRegistrationId(paramContext), (String)localObject3, 2);
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2))
        {
          localObject1 = localObject2;
          U.b(paramContext, str1, (String)localObject2);
          return;
        }
        localObject1 = localObject2;
        paramIntent.putExtra("body", (String)localObject2);
      }
      localObject1 = null;
      try
      {
        localObject3 = paramIntent.getStringExtra("task_id");
        localObject1 = localObject3;
        try
        {
          label355: localObject3 = paramIntent.getStringExtra("message_source");
          str3 = paramIntent.getStringExtra("report");
          if (MessageService.getSingleton(paramContext).report(str1, (String)localObject1, str3, (String)localObject3))
            paramIntent.removeExtra("report");
          label395: Q.c("BaseIntentService", "handleMessage--->[" + (String)localObject2 + "]");
          U.b(paramContext, str1);
          int i1 = ((String)localObject2).hashCode();
          if (MessageService.getSingleton(paramContext).hasMessageDuplicate(str1, i1))
            continue;
          i1 = -1;
          try
          {
            int i2 = Integer.parseInt(paramIntent.getStringExtra("notify"));
            i1 = i2;
            label472: localObject1 = paramIntent.getStringExtra("time");
            if (!TextUtils.isEmpty((CharSequence)localObject1))
            {
              MessageService.getSingleton(paramContext).handleMessageAtTime(str1, (String)localObject2, str2, (String)localObject1, i1);
              return;
            }
            long l1 = AgooSettings.getTargetTime(paramContext);
            if (l1 != -1L)
            {
              MessageService.getSingleton(paramContext).handleMessageAtTime(str1, (String)localObject2, str2, l1 + "_30", i1);
              return;
            }
            MessageService.getSingleton(paramContext).addMessage(str1, (String)localObject2, str2, i1);
            onMessage(paramContext, paramIntent);
            return;
          }
          catch (Throwable localThrowable1)
          {
            break label472;
          }
        }
        catch (Throwable localThrowable2)
        {
          break label395;
        }
      }
      catch (Throwable localThrowable3)
      {
        break label355;
      }
    }
  }

  private void e(Context paramContext)
  {
    P.s(paramContext);
  }

  private void e(Context paramContext, Intent paramIntent)
  {
    if (!shouldProcessMessage(paramContext, paramIntent))
      return;
    if (P.u(paramContext))
    {
      Q.a("BaseIntentService", "handleMessage[" + paramContext.getPackageName() + "]--->[disable]");
      return;
    }
    if (paramIntent.getBooleanExtra("local", false))
    {
      b(paramContext, paramIntent);
      return;
    }
    d(paramContext, paramIntent);
  }

  private void f(Context paramContext)
  {
    Q.c("BaseIntentService", "retry election");
    Intent localIntent = new Intent();
    localIntent.setAction("org.agoo.android.intent.action.RE_ELECTION_V2");
    localIntent.setFlags(32);
    paramContext.sendBroadcast(localIntent);
  }

  private void f(Context paramContext, Intent paramIntent)
  {
    if (!a(paramContext))
    {
      Q.c("BaseIntentService", "handleAddPackage---->[appkey or appSecret ===null]");
      return;
    }
    if (!BaseRegistrar.isRegistered(paramContext))
    {
      Q.c("BaseIntentService", "handleAddPackage---->[devicetoken ===null]");
      return;
    }
    f(paramContext);
  }

  private void g(Context paramContext)
  {
    if (paramContext != null);
    try
    {
      paramContext.unbindService(this.o);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private void g(Context paramContext, Intent paramIntent)
  {
    if ((paramIntent == null) || (paramContext == null));
    String str;
    Object localObject;
    do
    {
      do
      {
        return;
        str = null;
        localObject = paramIntent.getData();
        if (localObject != null)
          str = ((Uri)localObject).getSchemeSpecificPart();
      }
      while (TextUtils.isEmpty(str));
      if ((!TextUtils.isEmpty(str)) && (!TextUtils.equals(str, paramContext.getPackageName())))
        b(paramContext, str);
      localObject = P.d(paramContext);
    }
    while ((TextUtils.isEmpty((CharSequence)localObject)) || (TextUtils.equals(str, paramContext.getPackageName())) || (!TextUtils.equals(str, (CharSequence)localObject)));
    i(paramContext, paramIntent);
  }

  private void h(Context paramContext)
  {
    if ((this.p) && (this.q != null));
    try
    {
      boolean bool = this.q.ping();
      if (!bool)
        f(paramContext);
      Q.c("BaseIntentService", "pingMessage[ping:" + bool + "]");
      return;
    }
    catch (Throwable paramContext)
    {
      Q.d("BaseIntentService", "pingMessage", paramContext);
    }
  }

  private final void h(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("command");
    Q.c("BaseIntentService", "command --->[" + str + "]");
    if (str.equals("registration"))
    {
      onRegistered(paramContext, BaseRegistrar.getRegistrationId(paramContext));
      f(paramContext, paramIntent);
      return;
    }
    if (str.equals("unregister"))
    {
      m(paramContext, paramIntent);
      return;
    }
    if (str.equals("error"))
    {
      l(paramContext, paramIntent);
      return;
    }
    if (str.equals("register"))
    {
      j(paramContext, paramIntent);
      return;
    }
    if (str.equals("register_retry"))
    {
      k(paramContext, paramIntent);
      return;
    }
    onUserCommand(paramContext, paramIntent);
  }

  private void i(Context paramContext)
  {
    try
    {
      paramContext.unbindService(this.r);
      return;
    }
    catch (Throwable paramContext)
    {
      Q.d("BaseIntentService", "closeElection", paramContext);
    }
  }

  private void i(Context paramContext, Intent paramIntent)
  {
    try
    {
      if (!BaseRegistrar.isRegistered(paramContext))
      {
        Q.c("BaseIntentService", "handleElection---->[devicetoken == null]");
        return;
      }
      if (P.u(paramContext))
      {
        Q.c("BaseIntentService", "handleElection--->[app:disable]");
        return;
      }
      if (!P.w(paramContext))
      {
        Q.c("BaseIntentService", "handleElection--->[channel:single]");
        return;
      }
      if (TextUtils.equals(paramIntent.getStringExtra("election_type"), "election_notice"))
      {
        paramIntent = (ElectionService.ElectionResult)paramIntent.getParcelableExtra("election_result");
        Object localObject1 = paramIntent.getSudoMap();
        long l1 = paramIntent.getTimeout();
        paramIntent = paramIntent.getElectionSource();
        localObject1 = ((HashMap)localObject1).entrySet().iterator();
        String str1 = paramContext.getPackageName();
        Object localObject2;
        String str2;
        do
        {
          if (!((Iterator)localObject1).hasNext())
            break;
          localObject2 = (Map.Entry)((Iterator)localObject1).next();
          str2 = (String)((Map.Entry)localObject2).getKey();
          localObject2 = (String)((Map.Entry)localObject2).getValue();
        }
        while (!TextUtils.equals(str2, str1));
        P.a(paramContext, (String)localObject2, l1, paramIntent);
        b(paramContext, str1, (String)localObject2);
        return;
      }
      paramIntent = new Intent();
      paramIntent.setAction("org.agoo.android.intent.action.ELECTION_V2");
      paramContext.startService(paramIntent);
      paramContext.bindService(paramIntent, this.o, 1);
      return;
    }
    catch (Throwable paramContext)
    {
    }
  }

  private void j(Context paramContext, Intent paramIntent)
  {
    if (!a(paramContext))
    {
      Q.a("BaseIntentService", "handleRegister[" + paramContext.getPackageName() + "]--->[appkey==null,appSecret==nullttid,ttid==null]");
      e(paramContext);
      return;
    }
    if (!BaseRegistrar.isRegistered(paramContext))
    {
      Q.a("BaseIntentService", "handleRegister[" + paramContext.getPackageName() + "]--->[deviceToken==null]");
      P.t(paramContext);
      aK.a(paramContext);
      d(paramContext);
      return;
    }
    if (P.a(paramContext, true))
    {
      Q.a("BaseIntentService", "handleRegister[" + paramContext.getPackageName() + "]--->[disable]");
      return;
    }
    aR.a(paramContext);
    c();
  }

  private void k(Context paramContext, Intent paramIntent)
  {
    if (BaseRegistrar.isRegistered(paramContext))
      return;
    BaseRegistrar.b(paramContext);
  }

  private void l(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramIntent.getStringExtra("error");
    if (TextUtils.equals(paramIntent, "ERROR_NEED_ELECTION"))
    {
      f(paramContext);
      return;
    }
    if (TextUtils.equals(paramIntent, "ERRCODE_AUTH_REJECT"))
    {
      e(paramContext);
      f(paramContext);
      return;
    }
    if ("ERROR_DEVICETOKEN_NULL".equals(paramIntent))
    {
      U.g(paramContext, "ERROR_DEVICETOKEN_NULL");
      BaseRegistrar.b(paramContext);
      return;
    }
    if ("ERROR_NEED_REGISTER".equals(paramIntent))
      U.g(paramContext, "ERROR_NEED_REGISTER");
    if (("ERROR_APPKEY_NULL".equals(paramIntent)) || ("ERROR_APPSECRET_NULL".equals(paramIntent)) || ("ERROR_TTID_NULL".equals(paramIntent)))
    {
      U.g(paramContext, "APPKEY_OR_SECRET_IS_NULL");
      onError(paramContext, paramIntent);
      return;
    }
    onError(paramContext, paramIntent);
  }

  private void m(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramContext.getPackageName();
    String str = P.d(paramContext);
    if ((TextUtils.isEmpty(str)) || (TextUtils.equals(paramIntent, str)))
    {
      Q.c("BaseIntentService", "handleUnRegister---->[currentPack:" + paramIntent + "][currentSudoPack:" + str + "]:[retryElection]");
      if (b())
      {
        Q.c("BaseIntentService", "disableService---->[" + getAgooService() + "]");
        aR.a(paramContext, getAgooService());
      }
      aS.b(paramContext, getAgooService());
      f(paramContext);
    }
    c(paramContext);
    paramIntent = P.q(paramContext);
    P.j(paramContext);
    P.i(paramContext);
    onUnregistered(paramContext, paramIntent);
  }

  protected Class<?> getAgooService()
  {
    return null;
  }

  protected void onDeletedMessages(Context paramContext, int paramInt)
  {
  }

  protected abstract void onError(Context paramContext, String paramString);

  // ERROR //
  public final void onHandleIntent(Intent arg1)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 480	org/android/agoo/client/BaseIntentService:getApplicationContext	()Landroid/content/Context;
    //   4: astore_2
    //   5: aload_1
    //   6: invokevirtual 837	android/content/Intent:getAction	()Ljava/lang/String;
    //   9: astore_3
    //   10: aload_2
    //   11: invokestatic 840	org/android/agoo/client/IntentHelper:getAgooCommand	(Landroid/content/Context;)Ljava/lang/String;
    //   14: astore 4
    //   16: ldc_w 842
    //   19: aload_3
    //   20: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   23: ifne +7 -> 30
    //   26: aload_2
    //   27: invokestatic 843	com/umeng/message/proguard/Q:a	(Landroid/content/Context;)V
    //   30: aload_3
    //   31: aload 4
    //   33: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   36: ifeq +30 -> 66
    //   39: aload_0
    //   40: aload_2
    //   41: aload_1
    //   42: invokespecial 845	org/android/agoo/client/BaseIntentService:h	(Landroid/content/Context;Landroid/content/Intent;)V
    //   45: getstatic 47	org/android/agoo/client/BaseIntentService:k	Ljava/lang/Object;
    //   48: astore_1
    //   49: aload_1
    //   50: monitorenter
    //   51: getstatic 155	org/android/agoo/client/BaseIntentService:j	Landroid/os/PowerManager$WakeLock;
    //   54: ifnull +9 -> 63
    //   57: getstatic 155	org/android/agoo/client/BaseIntentService:j	Landroid/os/PowerManager$WakeLock;
    //   60: invokevirtual 848	android/os/PowerManager$WakeLock:release	()V
    //   63: aload_1
    //   64: monitorexit
    //   65: return
    //   66: ldc_w 842
    //   69: aload_3
    //   70: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   73: ifeq +59 -> 132
    //   76: aload_0
    //   77: aload_2
    //   78: aload_1
    //   79: invokespecial 850	org/android/agoo/client/BaseIntentService:g	(Landroid/content/Context;Landroid/content/Intent;)V
    //   82: goto -37 -> 45
    //   85: astore_1
    //   86: ldc 17
    //   88: ldc_w 851
    //   91: aload_1
    //   92: invokestatic 193	com/umeng/message/proguard/Q:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   95: getstatic 47	org/android/agoo/client/BaseIntentService:k	Ljava/lang/Object;
    //   98: astore_1
    //   99: aload_1
    //   100: monitorenter
    //   101: getstatic 155	org/android/agoo/client/BaseIntentService:j	Landroid/os/PowerManager$WakeLock;
    //   104: ifnull +9 -> 113
    //   107: getstatic 155	org/android/agoo/client/BaseIntentService:j	Landroid/os/PowerManager$WakeLock;
    //   110: invokevirtual 848	android/os/PowerManager$WakeLock:release	()V
    //   113: aload_1
    //   114: monitorexit
    //   115: return
    //   116: astore_2
    //   117: aload_1
    //   118: monitorexit
    //   119: aload_2
    //   120: athrow
    //   121: astore_1
    //   122: ldc 17
    //   124: ldc_w 853
    //   127: aload_1
    //   128: invokestatic 193	com/umeng/message/proguard/Q:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   131: return
    //   132: aload_3
    //   133: ldc_w 855
    //   136: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   139: ifeq +35 -> 174
    //   142: aload_0
    //   143: aload_2
    //   144: aload_1
    //   145: invokespecial 857	org/android/agoo/client/BaseIntentService:e	(Landroid/content/Context;Landroid/content/Intent;)V
    //   148: goto -103 -> 45
    //   151: astore_1
    //   152: getstatic 47	org/android/agoo/client/BaseIntentService:k	Ljava/lang/Object;
    //   155: astore_2
    //   156: aload_2
    //   157: monitorenter
    //   158: getstatic 155	org/android/agoo/client/BaseIntentService:j	Landroid/os/PowerManager$WakeLock;
    //   161: ifnull +9 -> 170
    //   164: getstatic 155	org/android/agoo/client/BaseIntentService:j	Landroid/os/PowerManager$WakeLock;
    //   167: invokevirtual 848	android/os/PowerManager$WakeLock:release	()V
    //   170: aload_2
    //   171: monitorexit
    //   172: aload_1
    //   173: athrow
    //   174: aload_3
    //   175: ldc_w 652
    //   178: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   181: ifeq +12 -> 193
    //   184: aload_0
    //   185: aload_2
    //   186: aload_1
    //   187: invokespecial 675	org/android/agoo/client/BaseIntentService:i	(Landroid/content/Context;Landroid/content/Intent;)V
    //   190: goto -145 -> 45
    //   193: aload_3
    //   194: ldc_w 859
    //   197: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   200: ifne +43 -> 243
    //   203: aload_3
    //   204: ldc_w 861
    //   207: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   210: ifne +33 -> 243
    //   213: aload_3
    //   214: ldc_w 863
    //   217: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   220: ifne +23 -> 243
    //   223: aload_3
    //   224: ldc_w 865
    //   227: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   230: ifne +13 -> 243
    //   233: aload_3
    //   234: ldc_w 867
    //   237: invokestatic 144	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   240: ifeq +12 -> 252
    //   243: aload_0
    //   244: aload_2
    //   245: aload_1
    //   246: invokespecial 869	org/android/agoo/client/BaseIntentService:a	(Landroid/content/Context;Landroid/content/Intent;)V
    //   249: goto -204 -> 45
    //   252: aload_0
    //   253: aload_2
    //   254: aload_1
    //   255: invokevirtual 872	org/android/agoo/client/BaseIntentService:onUserHandleIntent	(Landroid/content/Context;Landroid/content/Intent;)V
    //   258: goto -213 -> 45
    //   261: astore_2
    //   262: aload_1
    //   263: monitorexit
    //   264: aload_2
    //   265: athrow
    //   266: astore_1
    //   267: ldc 17
    //   269: ldc_w 853
    //   272: aload_1
    //   273: invokestatic 193	com/umeng/message/proguard/Q:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   276: return
    //   277: astore_3
    //   278: aload_2
    //   279: monitorexit
    //   280: aload_3
    //   281: athrow
    //   282: astore_2
    //   283: ldc 17
    //   285: ldc_w 853
    //   288: aload_2
    //   289: invokestatic 193	com/umeng/message/proguard/Q:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   292: goto -120 -> 172
    //
    // Exception table:
    //   from	to	target	type
    //   0	30	85	java/lang/Throwable
    //   30	45	85	java/lang/Throwable
    //   66	82	85	java/lang/Throwable
    //   132	148	85	java/lang/Throwable
    //   174	190	85	java/lang/Throwable
    //   193	243	85	java/lang/Throwable
    //   243	249	85	java/lang/Throwable
    //   252	258	85	java/lang/Throwable
    //   101	113	116	finally
    //   113	115	116	finally
    //   117	119	116	finally
    //   95	101	121	java/lang/Throwable
    //   119	121	121	java/lang/Throwable
    //   0	30	151	finally
    //   30	45	151	finally
    //   66	82	151	finally
    //   86	95	151	finally
    //   132	148	151	finally
    //   174	190	151	finally
    //   193	243	151	finally
    //   243	249	151	finally
    //   252	258	151	finally
    //   51	63	261	finally
    //   63	65	261	finally
    //   262	264	261	finally
    //   45	51	266	java/lang/Throwable
    //   264	266	266	java/lang/Throwable
    //   158	170	277	finally
    //   170	172	277	finally
    //   278	280	277	finally
    //   152	158	282	java/lang/Throwable
    //   280	282	282	java/lang/Throwable
  }

  protected abstract void onMessage(Context paramContext, Intent paramIntent);

  protected boolean onRecoverableError(Context paramContext, String paramString)
  {
    return true;
  }

  protected abstract void onRegistered(Context paramContext, String paramString);

  protected abstract void onUnregistered(Context paramContext, String paramString);

  protected void onUserCommand(Context paramContext, Intent paramIntent)
  {
  }

  protected void onUserHandleIntent(Context paramContext, Intent paramIntent)
  {
  }

  protected boolean shouldProcessMessage(Context paramContext, Intent paramIntent)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.client.BaseIntentService
 * JD-Core Version:    0.6.2
 */