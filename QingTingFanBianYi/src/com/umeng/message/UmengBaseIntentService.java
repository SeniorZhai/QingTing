package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.umeng.common.message.Log;
import com.umeng.message.entity.UMessage;
import org.android.agoo.client.BaseIntentService;
import org.json.JSONObject;

public abstract class UmengBaseIntentService extends BaseIntentService
{
  private static final String a = UmengBaseIntentService.class.getName();

  protected final Class<?> getAgooService()
  {
    return UmengService.class.getClass();
  }

  protected void onError(Context paramContext, String paramString)
  {
    Log.c(a, "onError()[" + paramString + "]");
  }

  protected void onMessage(Context paramContext, Intent paramIntent)
  {
    if (Process.getElapsedCpuTime() < 3000L)
    {
      Log.a(a, "App is launched by push message");
      PushAgent.setAppLaunchByMessage();
    }
    paramContext = paramIntent.getStringExtra("body");
    Log.c(a, "onMessage():[" + paramContext + "]");
    try
    {
      paramContext = new UMessage(new JSONObject(paramContext));
      UTrack.getInstance(getApplicationContext()).a(paramContext);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  protected void onRegistered(Context paramContext, String paramString)
  {
    Log.c(a, "onRegistered()[" + paramString + "]");
    try
    {
      UTrack.getInstance(getApplicationContext()).trackRegister();
    }
    catch (Exception localException)
    {
      try
      {
        while (true)
        {
          paramContext = PushAgent.getInstance(paramContext).getRegisterCallback();
          if (paramContext != null)
            paramContext.onRegistered(paramString);
          return;
          localException = localException;
          localException.printStackTrace();
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }

  protected void onUnregistered(Context paramContext, String paramString)
  {
    Log.c(a, "onUnregistered()[" + paramString + "]");
    try
    {
      paramContext = PushAgent.getInstance(paramContext).getUnregisterCallback();
      if (paramContext != null)
        paramContext.onUnregistered(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }

  protected boolean shouldProcessMessage(Context paramContext, Intent paramIntent)
  {
    return PushAgent.getInstance(paramContext).isEnabled();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UmengBaseIntentService
 * JD-Core Version:    0.6.2
 */