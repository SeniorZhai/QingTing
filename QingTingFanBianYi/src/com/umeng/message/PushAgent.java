package com.umeng.message;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.common.message.DeviceConfig;
import com.umeng.common.message.Log;
import com.umeng.message.proguard.C.e;
import com.umeng.message.tag.TagManager;
import java.util.Random;
import org.android.agoo.client.AgooSettings;
import org.json.JSONException;

public class PushAgent
{
  public static boolean DEBUG = false;
  private static PushAgent a;
  private static boolean d = false;
  private static final String e = PushAgent.class.getName();
  private TagManager b;
  private Context c;
  private UHandler f;
  private UHandler g;
  private IUmengRegisterCallback h;
  private IUmengUnregisterCallback i;

  private PushAgent(Context paramContext)
  {
    try
    {
      this.c = paramContext;
      this.b = TagManager.getInstance(paramContext);
      this.f = new UmengMessageHandler();
      this.g = new UmengNotificationClickHandler();
      return;
    }
    catch (Exception paramContext)
    {
      Log.b(e, paramContext.getMessage());
    }
  }

  public static PushAgent getInstance(Context paramContext)
  {
    try
    {
      if (a == null)
        a = new PushAgent(paramContext.getApplicationContext());
      paramContext = a;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public static boolean isAppLaunchByMessage()
  {
    return d;
  }

  public static void setAppLaunchByMessage()
  {
    d = true;
  }

  public boolean addAlias(String paramString1, String paramString2)
    throws C.e, JSONException
  {
    return UTrack.getInstance(this.c).addAlias(paramString1, paramString2);
  }

  public void disable()
  {
    try
    {
      MessageSharedPrefs.getInstance(this.c).f();
      if (UmengRegistrar.isRegistered(this.c))
        UmengRegistrar.unregister(this.c);
      return;
    }
    catch (Exception localException)
    {
      Log.b(e, localException.getMessage());
    }
  }

  public void disable(IUmengUnregisterCallback paramIUmengUnregisterCallback)
  {
    setUnregisterCallback(paramIUmengUnregisterCallback);
    disable();
  }

  public void enable()
  {
    try
    {
      if (Build.VERSION.SDK_INT < 8)
      {
        Log.b(e, "Push SDK does not work for Android Verion < 8");
        return;
      }
      MessageSharedPrefs.getInstance(this.c).e();
      Log.c(e, "enable(): register");
      UmengRegistrar.register(this.c, getMessageAppkey(), getMessageSecret());
      return;
    }
    catch (Exception localException)
    {
      Log.b(e, localException.getMessage());
    }
  }

  public void enable(IUmengRegisterCallback paramIUmengRegisterCallback)
  {
    setRegisterCallback(paramIUmengRegisterCallback);
    enable();
  }

  public boolean getMergeNotificaiton()
  {
    return MessageSharedPrefs.getInstance(this.c).getMergeNotificaiton();
  }

  public String getMessageAppkey()
  {
    String str2 = MessageSharedPrefs.getInstance(this.c).getMessageAppKey();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = DeviceConfig.getAppkey(this.c);
    return str1;
  }

  public String getMessageChannel()
  {
    String str2 = MessageSharedPrefs.getInstance(this.c).getMessageChannel();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = DeviceConfig.getChannel(this.c);
    return str1;
  }

  public UHandler getMessageHandler()
  {
    return this.f;
  }

  public String getMessageSecret()
  {
    String str2 = MessageSharedPrefs.getInstance(this.c).getMessageAppSecret();
    String str1 = str2;
    if (TextUtils.isEmpty(str2))
      str1 = DeviceConfig.getMetaData(this.c, "UMENG_MESSAGE_SECRET");
    return str1;
  }

  public int getNoDisturbEndHour()
  {
    return MessageSharedPrefs.getInstance(this.c).c();
  }

  public int getNoDisturbEndMinute()
  {
    return MessageSharedPrefs.getInstance(this.c).d();
  }

  public int getNoDisturbStartHour()
  {
    return MessageSharedPrefs.getInstance(this.c).a();
  }

  public int getNoDisturbStartMinute()
  {
    return MessageSharedPrefs.getInstance(this.c).b();
  }

  public UHandler getNotificationClickHandler()
  {
    return this.g;
  }

  public String getPushIntentServiceClass()
  {
    return MessageSharedPrefs.getInstance(this.c).getPushIntentServiceClass();
  }

  public IUmengRegisterCallback getRegisterCallback()
  {
    return this.h;
  }

  public String getRegistrationId()
  {
    return UmengRegistrar.getRegistrationId(this.c);
  }

  public TagManager getTagManager()
  {
    return this.b;
  }

  public IUmengUnregisterCallback getUnregisterCallback()
  {
    return this.i;
  }

  public boolean isEnabled()
  {
    try
    {
      boolean bool = MessageSharedPrefs.getInstance(this.c).g();
      return bool;
    }
    catch (Exception localException)
    {
      Log.b(e, localException.getMessage());
    }
    return false;
  }

  public boolean isRegistered()
  {
    return UmengRegistrar.isRegistered(this.c);
  }

  public void onAppStart()
  {
    long l = 0L;
    UmengRegistrar.checkRegisteredToUmeng(this.c);
    if (!UmengRegistrar.isRegistered(this.c))
      return;
    if (MessageSharedPrefs.getInstance(this.c).getAppLaunchLogSendPolicy() == 1)
      Log.c(e, "launch_policy=1, skip sending app launch info.");
    while (true)
    {
      if (isAppLaunchByMessage())
        l = Math.abs(new Random().nextLong() % MsgConstant.a);
      UTrack.getInstance(this.c).sendCachedMsgLog(l);
      return;
      if (!MessageSharedPrefs.getInstance(this.c).hasAppLaunchLogSentToday())
        UTrack.getInstance(this.c).trackAppLaunch(0L);
    }
  }

  public boolean removeAlias(String paramString1, String paramString2)
    throws C.e, JSONException
  {
    return UTrack.getInstance(this.c).removeAlias(paramString1, paramString2);
  }

  public void setAppkeyAndSecret(String paramString1, String paramString2)
  {
    MessageSharedPrefs.getInstance(this.c).setMessageAppKey(paramString1);
    MessageSharedPrefs.getInstance(this.c).setMessageAppSecret(paramString2);
  }

  public void setDebugMode(boolean paramBoolean)
  {
    AgooSettings.setDebugMode(paramBoolean);
    Log.LOG = paramBoolean;
    AgooSettings.setLog(this.c, true, false);
  }

  public void setMergeNotificaiton(boolean paramBoolean)
  {
    MessageSharedPrefs.getInstance(this.c).setMergeNotificaiton(paramBoolean);
  }

  public void setMessageChannel(String paramString)
  {
    MessageSharedPrefs.getInstance(this.c).setMessageChannel(paramString);
  }

  public void setMessageHandler(UHandler paramUHandler)
  {
    this.f = paramUHandler;
  }

  public void setNoDisturbMode(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    MessageSharedPrefs.getInstance(this.c).a(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void setNotificationClickHandler(UHandler paramUHandler)
  {
    this.g = paramUHandler;
  }

  public <U extends UmengBaseIntentService> void setPushIntentServiceClass(Class<U> paramClass)
  {
    MessageSharedPrefs.getInstance(this.c).setPushIntentServiceClass(paramClass);
  }

  public void setRegisterCallback(IUmengRegisterCallback paramIUmengRegisterCallback)
  {
    this.h = paramIUmengRegisterCallback;
  }

  public void setUnregisterCallback(IUmengUnregisterCallback paramIUmengUnregisterCallback)
  {
    this.i = paramIUmengUnregisterCallback;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.PushAgent
 * JD-Core Version:    0.6.2
 */